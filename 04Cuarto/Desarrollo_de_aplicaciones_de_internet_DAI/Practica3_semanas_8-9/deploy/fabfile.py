#!/usr/bin/python3.7
# -*- coding: UTF-8 -*-
import os, sys, requests, datetime
from fabric.api import env, local, run, sudo, prompt
from fabric.operations import put
from fabric.contrib.files import exists

env.pythonbin = 'python3.6'
env.datenow = datetime.datetime.now().isoformat()
env.user = 'vagrant'
env.pass_admin_app = "pbkdf2_sha256$120000$39fUzRVqPpfn$WYKC0q6ZSMXqq6fuFl57wqkRYhEwvXFS31ummVsmScA="
env.key_filename = "~/.ssh/id_rsa_deploying"
env.path_django = '/home/vagrant/src'
env.project = 'DAIPROJECT'
env.mongodb_host = os.environ['MONGODB_HOST']
env.mongodb_port = os.environ['MONGODB_PORT']
env.token = os.environ['DO_TOKEN']
env.fip = os.environ['DO_FIP']
env.name_app = os.environ['NAME_OF_APP']
env.name_dbapp = os.environ['NAME_OF_DBAPP']
env.github_client_id = os.environ['GITHUB_CLIENT_ID']
env.github_client_secret = os.environ['GITHUB_CLIENT_SECRET']
env.google_client_id = os.environ['GOOGLE_CLIENT_ID']
env.google_client_secret = os.environ['GOOGLE_CLIENT_SECRET']

def _set_env(envirotment):
    if envirotment == "local":
        env.password = 'vagrant'
        env.host_string = 'localhost:2222'
    elif envirotment == "prolocal":
        env.password = 'vagrant'
        env.host_string = 'localhost:2500'
    elif envirotment == "remote":
        env.host_string = 'dai.sudano.net:22'
    else:
        print ("Por favor indique una maquina valida para desplegar")
        sys.exit()

def _levantar_maquina(envirotment):
    if envirotment == "local":
        local('vagrant up --no-provision --provider=virtualbox local')
    elif envirotment == "prolocal":
        local('vagrant up --no-provision --provider=virtualbox prolocal')
    elif envirotment == "remote":
        local('vagrant up --no-provision --provider=digital_ocean remote')

def _detener_maquina(envirotment):
    if envirotment == "local":
        local('vagrant halt --force local')
    elif envirotment == "prolocal":
        local('vagrant halt --force prolocal')
    elif envirotment == "remote":
        local('vagrant halt --force remote')

def _configurar_maquina(envirotment):
    if envirotment == "local":
        local('sed "/localhost/d" ~/.ssh/known_hosts > ~/.ssh/known_hosts.tmp')
        local('sed "/127.0.0.1/d" ~/.ssh/known_hosts.tmp > ~/.ssh/known_hosts')
        local('ssh-copy-id -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no -i \
        /home/usuario/.ssh/id_rsa_deploying vagrant@localhost -p 2222')
        local('vagrant provision local')
        install_tools = prompt('¿Quieres instalar las tools a la maquina? [y/N]?: ', default='N')
        if install_tools == 'y' or install_tools == 'Y':
            install_vbguest(envirotment)
        _configurar_django()
    elif envirotment == "prolocal":
        local('sed "/localhost/d" ~/.ssh/known_hosts > ~/.ssh/known_hosts.tmp')
        local('sed "/127.0.0.1/d" ~/.ssh/known_hosts.tmp > ~/.ssh/known_hosts')
        local('ssh-copy-id -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no -i \
        /home/usuario/.ssh/id_rsa_deploying vagrant@localhost -p 2500')
        local('vagrant provision prolocal')
        install_tools = prompt('¿Quieres instalar las tools a la maquina? [y/N]?: ', default='N')
        if install_tools == 'y' or install_tools == 'Y':
            install_vbguest(envirotment)
        _configurar_django()
    elif envirotment == "remote":
        local('vagrant ssh remote -c "sudo dnf install python2.x86_64 firewalld.noarch -y"')
        local('vagrant provision remote')
        sudo('chown vagrant:vagrant -R /home/vagrant/src')
        _configurar_django()

def _ejecutar_aplicacion(envirotment):
    if envirotment == "local":
        run('%(pythonbin)s %(path_django)s/manage.py runserver 0.0.0.0:8080' % env)
    elif envirotment == "prolocal":
        sudo('gunicorn --bind 0.0.0.0:8000 %(project)s.wsgi' % env)
    elif envirotment == "remote":
        pass


def _eliminar_maquina(envirotment):
    if envirotment == "local":
        local('vagrant destroy --force local')
    elif envirotment == "prolocal":
        local('vagrant destroy --force prolocal')
    elif envirotment == "remote":
        local('vagrant destroy --force remote')

def _configurar_django():
    if not exists('%(path_django)s/%(project)s' % env):
        run('django-admin startproject %(project)s %(path_django)s' % env)
    if not exists('%(path_django)s/%(name_app)s' % env):
        run('cd %(path_django)s; python3.6 %(path_django)s/manage.py startapp %(name_app)s' % env)
    run('sed -i "s/\'DIRS\': \\\[\\\]\\\,/\'DIRS\': [os.path.join(BASE_DIR, \'\/templates\/\')],/" \
    %(path_django)s/%(project)s/settings.py' % env)
    run('sed -i "s/LANGUAGE_CODE = \'en-us\'/LANGUAGE_CODE = \'es-ES\'/" %(path_django)s/%(project)s/settings.py' % env)
    run('sed -i "s/TIME_ZONE = \'UTC\'/TIME_ZONE = \'Europe\/Madrid\'/" %(path_django)s/%(project)s/settings.py' % env)
    debug_on = prompt('¿Quieres el modo debug? [Y/n]?: ', default='Y')
    if debug_on == 'y' or debug_on == 'Y':
        run('sed -i "s/DEBUG = False/DEBUG = True/" %(path_django)s/%(project)s/settings.py' % env)
        run('sed -i "s/ALLOWED_HOSTS = \[\'dai.sudano.net\', \'localhost\', \'127.0.0.1\', \
        \'\[::1\]\'\]/ALLOWED_HOSTS = \[\]/" %(path_django)s/%(project)s/settings.py' % env)
    elif debug_on == 'n' or debug_on == 'N':
        run('sed -i "s/DEBUG = True/DEBUG = False/" %(path_django)s/%(project)s/settings.py' % env)
        run('sed -i "s/ALLOWED_HOSTS = \[\]/ALLOWED_HOSTS = \[\'dai.sudano.net\', \'localhost\', \
        \'127.0.0.1\', \'\[::1\]\'\]/" %(path_django)s/%(project)s/settings.py' % env)
    run('%(pythonbin)s %(path_django)s/manage.py migrate' % env)
    config_allauth = prompt('¿Quieres configurar AllAuth con MongoDB? [y/N]?: ', default='N')
    if config_allauth == 'y' or config_allauth == 'Y':
        _config_allauth_in_mongodb()
    migrate = prompt('¿Quiere realiazar migraciones para la aplicación %(name_app)s? [y/N]?: ' % env, default='N')
    if migrate == 'y' or migrate == 'Y':
        run('%(pythonbin)s %(path_django)s/manage.py makemigrations %(name_app)s' % env)
        run('%(pythonbin)s %(path_django)s/manage.py migrate %(name_app)s' % env)
    create_user = prompt('¿Quieres crear el super usuario? [y/N]?: ', default='N')
    if create_user == 'y' or create_user == 'Y':
        auto = prompt('¿Modo interactivo o automático? [i/A]?: ', default='A')
        if auto == 'a' or auto == 'A':
            run('mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
            "db.auth_user.remove({\'id\':NumberInt(1)})"' % env)
            run('mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
            "db.auth_user.insert({\'id\':NumberInt(1),\'password\':\'%(pass_admin_app)s\', \'last_login\':null, \
            \'is_superuser\':true,\'username\':\'admin\',\'first_name\':\'Administrator\', \
            \'last_name\':\'of App\',\'email\':\'admin@localhost.local\',\'is_staff\':true, \
            \'is_active\':true,\'date_joined\':ISODate(\'%(datenow)s\')})"' % env)
        elif auto == 'i' or auto == 'I':
            run('mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
            "db.auth_user.remove({\'id\':NumberInt(1)})"' % env)
            run('%(pythonbin)s %(path_django)s/manage.py createsuperuser' % env)
    import_data = prompt('¿Quiere importar los datos de los restaurantes y los vecinos? [y/N]?: ', default='N')
    if import_data == 'y' or import_data == 'Y':
        _import_data_mongodb()

def _config_allauth_in_mongodb():
    run('mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    "db.socialaccount_socialapp.remove({\'id\':NumberInt(1)})"' % env)
    run('mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    "db.socialaccount_socialapp.insert({\'id\':NumberInt(1),\'provider\':\'github\',\'name\': \
    \'Github\',\'client_id\':\'%(github_client_id)s\',\'secret\':\'%(github_client_secret)s\' \
    ,\'key\':\'\'})"' % env)
    run('mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    "db.socialaccount_socialapp.remove({\'id\':NumberInt(2)})"' % env)
    run('mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    "db.socialaccount_socialapp.insert({\'id\':NumberInt(2),\'provider\':\'google\',\'name\': \
    \'Google\',\'client_id\':\'%(google_client_id)s\',\'secret\':\'%(google_client_secret)s\' \
    ,\'key\':\'\'})"' % env)
    run('mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    "db.django_site.remove({\'id\':NumberInt(1)})"' % env)
    run('mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    "db.django_site.insert({\'id\':NumberInt(1),\'domain\':\'localhost\',\'name\':\'dai.sudano.net\'})"' % env)
    run('mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    "db.socialaccount_socialapp_sites.remove({\'id\':NumberInt(1)})"' % env)
    run('mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    "db.socialaccount_socialapp_sites.insert({\'id\':NumberInt(1),\'socialapp_id\':NumberInt(1),\'site_id\':NumberInt(1)})"' % env)
    run('mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    "db.socialaccount_socialapp_sites.remove({\'id\':NumberInt(2)})"' % env)
    run('mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    "db.socialaccount_socialapp_sites.insert({\'id\':NumberInt(2),\'socialapp_id\':NumberInt(2),\'site_id\':NumberInt(1)})"' % env)

def _import_data_mongodb():
    run('mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval "db.%(name_app)s_restaurants.drop()"' % env)
    run('mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval "db.%(name_app)s_neighborhoods.drop()"' % env)
    run('mongoimport ~/src/restaurants.json --db %(name_dbapp)s --collection %(name_app)s_restaurants' % env)
    run('mongoimport ~/src/neighborhoods.json --db %(name_dbapp)s --collection %(name_app)s_neighborhoods' % env)
    run('mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    "db.%(name_app)s_restaurants.createIndex({name: \'text\'})"' % env)
    run('mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    "db.%(name_app)s_neighborhoods.createIndex({name: \'text\'})"' % env)

def _assing_floating_ip():
    headers = {'Content-Type': 'application/json','Authorization': 'Bearer %(token)s' % env}
    url_get_droplets = 'https://api.digitalocean.com/v2/droplets?page=1&per_page=1'
    url_assign_ip_droplet = 'https://api.digitalocean.com/v2/floating_ips/%(fip)s/actions' % env
    id_droplet = requests.get(url_get_droplets, headers=headers).json()['droplets'][0]['id']
    payload = '{"type":"assign","droplet_id":"%s"}' % id_droplet
    requests.post(url_assign_ip_droplet, headers=headers, data=payload)

# Esto es para poder copiar la aplicación al servidor remoto cuando
# no tengamos la opción de sincronizar carpetas locales con la maquina
# de desarrollo
def _toput():
    run('mkdir -p ~/src')
    put('~/GitHub/Universidad/04Cuarto/Desarrollo_de_aplicaciones_de_internet_DAI/config_machine/src', '~/')

def start(envirotment):
    _set_env(envirotment)
    _levantar_maquina(envirotment)
    if envirotment == "remote":
        _assing_floating_ip()
    _configurar_maquina(envirotment)


def play(envirotment):
    _set_env(envirotment)
    _ejecutar_aplicacion(envirotment)

def restart(envirotment):
    _detener_maquina(envirotment)
    _set_env(envirotment)
    _levantar_maquina(envirotment)
    _configurar_maquina(envirotment)
    _import_data_mongodb()
    
def stop(envirotment):
    _set_env(envirotment)
    _detener_maquina(envirotment)

def remove(envirotment):
    _set_env(envirotment)
    _detener_maquina(envirotment)
    _eliminar_maquina(envirotment)

def tests_app(envirotment):
    local('curl localhost:8080')
    local('curl localhost:8080/static')
    local('curl localhost:8080/var/Carlos_de_la_Torre')
    local('curl localhost:8080/static-data')
    local('curl localhost:8080/random-image --output -')
    local('curl localhost:8080/dynamic-image/2/2/-2/-2/300/75/0 --output -')
    local('curl localhost:8080/error')

def test(envirotment):
    _assing_floating_ip()

def install_vbguest(envirotment):
    if envirotment == '':
        print('Por favor indique la maquina que quiere desplegar local, prolocal ó remote?')
        sys.exit()
    _set_env(envirotment)
    # primero se crea la maquina, despues se actualiza la maquina, se tiene que reiniciar,
    # despues se instala las vbguest y despues se ejecuta todo
    local('vagrant vbguest %s -f -b --do install' % envirotment)
    sudo('shutdown now')