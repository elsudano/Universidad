#!/usr/bin/python3.7
# -*- coding: UTF-8 -*-
import os, sys, requests, datetime
from fabric.api import env, local, run, sudo, prompt
from fabric.operations import put
from fabric.contrib.files import exists

env.pythonbin = "python3.6"
env.datenow = datetime.datetime.now().isoformat()
env.user = "vagrant"
env.key_filename = "~/.ssh/id_rsa_deploying"

env.path_django = os.environ["DJANGO_REMOTE_ROOT_PATH"]
env.pass_admin_app = os.environ["DJANGO_ADMIN_PASS"]
env.secret_key = os.environ["DJANGO_SECRET_KEY"]
env.mongodb_host = os.environ["MONGODB_HOST"]
env.mongodb_port = os.environ["MONGODB_PORT"]
env.token = os.environ["DO_TOKEN"]
env.fip = os.environ["DO_FIP"]
env.project = os.environ["DJANGO_NAME_OF_PROJECT"]
env.name_app = os.environ["DJANGO_NAME_OF_APP"]
env.name_dbapp = os.environ["DJANGO_NAME_OF_DBAPP"]
env.github_client_id_dev = os.environ["GITHUB_CLIENT_ID_DEV"]
env.github_client_secret_dev = os.environ["GITHUB_CLIENT_SECRET_DEV"]
env.google_client_id_dev = os.environ["GOOGLE_CLIENT_ID_DEV"]
env.google_client_secret_dev = os.environ["GOOGLE_CLIENT_SECRET_DEV"]
env.github_client_id_pro = os.environ["GITHUB_CLIENT_ID_PRO"]
env.github_client_secret_pro = os.environ["GITHUB_CLIENT_SECRET_PRO"]
env.google_client_id_pro = os.environ["GOOGLE_CLIENT_ID_PRO"]
env.google_client_secret_pro = os.environ["GOOGLE_CLIENT_SECRET_PRO"]

def _set_env(envirotment):
    if envirotment == "local":
        env.password = "vagrant"
        env.host_string = "localhost:2222"
    elif envirotment == "remote":
        env.host_string = "dai.sudano.net:22"
    else:
        print ("Por favor indique una maquina valida para desplegar")
        sys.exit()

def _levantar_maquina(envirotment):
    if envirotment == "local":
        local("vagrant up --no-provision --provider=virtualbox local")
    elif envirotment == "remote":
        local("vagrant up --no-provision --provider=digital_ocean remote")

def _detener_maquina(envirotment):
    if envirotment == "local":
        local("vagrant halt --force local")
    elif envirotment == "remote":
        local("vagrant halt --force remote")

def _configurar_maquina(envirotment):
    if envirotment == "local":
        local("sed \"/localhost/d\" ~/.ssh/known_hosts > ~/.ssh/known_hosts.tmp")
        local("sed \"/127.0.0.1/d\" ~/.ssh/known_hosts.tmp > ~/.ssh/known_hosts")
        local("ssh-copy-id -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no -i \
        /home/usuario/.ssh/id_rsa_deploying vagrant@localhost -p 2222")
        install_tools = prompt("¿Quieres instalar las tools a la maquina? [y/N]?: ", default="N")
        if install_tools == "y" or install_tools == "Y":
            install_vbguest(envirotment)
        local("vagrant provision local")
        _configurar_django()
    elif envirotment == "remote":
        if not exists("/usr/bin/python"):
            sudo("ln -s /usr/bin/python3.6 /usr/bin/python")
        local("vagrant provision remote")
        sudo("chown vagrant:vagrant -R /home/vagrant/src")
        _configurar_django()

def _ejecutar_aplicacion():
    run("%(pythonbin)s %(path_django)s/manage.py runserver 0.0.0.0:8080" % env)


def _eliminar_maquina(envirotment):
    if envirotment == "local":
        local("vagrant destroy --force local")
    elif envirotment == "remote":
        local("vagrant destroy --force remote")

def _configurar_django():
    if not exists("%(path_django)s/%(project)s" % env):
        run("mkdir -p ~/src")
        run("django-admin startproject %(project)s %(path_django)s" % env)
    if not exists("%(path_django)s/%(name_app)s" % env):
        run("cd %(path_django)s; %(pythonbin)s %(path_django)s/manage.py startapp %(name_app)s" % env)
    _toput()
    run("sed -i \"/'DIRS': \[\],/ c'DIRS': [os.path.join(BASE_DIR, '/templates/')],\" \
    %(path_django)s/%(project)s/settings.py" % env)
    run("sed -i \"/LANGUAGE_CODE = 'en-us'/ cLANGUAGE_CODE = 'es-ES'\" %(path_django)s/%(project)s/settings.py" % env)
    run("sed -i \"/TIME_ZONE = 'UTC'/ cTIME_ZONE = 'Europe\/Madrid'\" %(path_django)s/%(project)s/settings.py" % env)
    run("sed -i \"/SECRET_KEY =/ cSECRET_KEY = '%(secret_key)s'\" %(path_django)s/%(project)s/settings.py" % env)
    run("sed -i \"/'NAME': '',/ c'NAME': '%(name_dbapp)s',\" %(path_django)s/%(project)s/settings.py" % env)
    debug_on = prompt("¿Quieres el modo debug? [Y/n]?: ", default="Y")
    if debug_on == "y" or debug_on == "Y":
        run("sed -i \"/DEBUG = False/ cDEBUG = True\" %(path_django)s/%(project)s/settings.py" % env)
    elif debug_on == "n" or debug_on == "N":
        run("sed -i \"/DEBUG = True/ cDEBUG = False\" %(path_django)s/%(project)s/settings.py" % env)
    run("sed -i \"/ALLOWED_HOSTS = \[\]/ cALLOWED_HOSTS = ['dai.sudano.net','localhost','127.0.0.1','[::1]']\" %(path_django)s/%(project)s/settings.py" % env)
    collect = prompt("¿Quiere realizar la recolección de ficheros estáticos? [y/N]?: ", default="N")
    if collect == "y" or collect == "Y":
        run("sed -i \"/STATIC_URL = '\/static\/'/ cSTATIC_URL = os.path.join(BASE_DIR, '\/static\/')\" %(path_django)s/%(project)s/settings.py" % env)
        run("sed -i \"/STATIC_URL = os.path.join(BASE_DIR, '\/static\/')/ aSTATIC_ROOT = os.path.join(BASE_DIR, '%(name_app)s\/static\/')\" %(path_django)s/%(project)s/settings.py" % env)
        run("%(pythonbin)s %(path_django)s/manage.py collectstatic --noinput" % env)
    config_allauth = prompt("¿Quieres configurar AllAuth con MongoDB? [y/N]?: ", default="N")
    if config_allauth == "y" or config_allauth == "Y":
        _config_allauth_in_mongodb()
    create_user = prompt("¿Quieres crear el super usuario? [y/N]?: ", default="N")
    if create_user == "y" or create_user == "Y":
        auto = prompt("¿Modo interactivo o automático? [i/A]?: ", default="A")
        if auto == "a" or auto == "A":
            run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
            \"db.auth_user.remove({'id':NumberInt(1)})\"" % env)
            run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
            \"db.auth_user.insert({'id':NumberInt(1),'password':'%(pass_admin_app)s', 'last_login':null, \
            'is_superuser':true,'username':'admin','first_name':'Administrator', \
            'last_name':'of App','email':'admin@localhost.local','is_staff':true, \
            'is_active':true,'date_joined':ISODate('%(datenow)s')})\"" % env)
        elif auto == "i" or auto == "I":
            run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
            \"db.auth_user.remove({'id':NumberInt(1)})\"" % env)
            run("%(pythonbin)s %(path_django)s/manage.py createsuperuser" % env)
    import_data = prompt("¿Quiere importar los datos de los restaurantes y los vecinos? [y/N]?: ", default="N")
    if import_data == "y" or import_data == "Y":
        _import_data_mongodb()
    migrate = prompt("¿Quiere realiazar migraciones para la aplicación %(name_app)s? [y/N]?: " % env, default="N")
    if migrate == "y" or migrate == "Y":
        run("%(pythonbin)s %(path_django)s/manage.py makemigrations %(name_app)s" % env)
        run("%(pythonbin)s %(path_django)s/manage.py migrate %(name_app)s" % env)
    run("%(pythonbin)s %(path_django)s/manage.py migrate" % env)

def _config_allauth_in_mongodb():
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp.remove({'id':NumberInt(1)})\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp.insert({'id':NumberInt(1),'provider':'github','name': \
    'Github Dev','client_id':'%(github_client_id_dev)s','secret':'%(github_client_secret_dev)s' \
    ,'key':''})\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp.remove({'id':NumberInt(2)})\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp.insert({'id':NumberInt(2),'provider':'google','name': \
    'Google Dev','client_id':'%(google_client_id_dev)s','secret':'%(google_client_secret_dev)s' \
    ,'key':''})\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp.remove({'id':NumberInt(3)})\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp.insert({'id':NumberInt(3),'provider':'github','name': \
    'Github Pro','client_id':'%(github_client_id_pro)s','secret':'%(github_client_secret_pro)s' \
    ,'key':''})\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp.remove({'id':NumberInt(4)})\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp.insert({'id':NumberInt(4),'provider':'google','name': \
    'Google Pro','client_id':'%(google_client_id_pro)s','secret':'%(google_client_secret_pro)s' \
    ,'key':''})\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.django_site.remove({'id':NumberInt(1)})\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.django_site.insert({'id':NumberInt(1),'domain':'localhost','name':'dai.sudano.net'})\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp_sites.remove({'id':NumberInt(1)})\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp_sites.insert({'id':NumberInt(1),'socialapp_id':NumberInt(1),'site_id':NumberInt(1)})\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp_sites.remove({'id':NumberInt(2)})\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp_sites.insert({'id':NumberInt(2),'socialapp_id':NumberInt(2),'site_id':NumberInt(1)})\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp_sites.remove({'id':NumberInt(3)})\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp_sites.insert({'id':NumberInt(3),'socialapp_id':NumberInt(3),'site_id':NumberInt(1)})\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp_sites.remove({'id':NumberInt(4)})\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp_sites.insert({'id':NumberInt(4),'socialapp_id':NumberInt(4),'site_id':NumberInt(1)})\"" % env)

def _import_data_mongodb():
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \"db.%(name_app)s_restaurants.drop()\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \"db.%(name_app)s_neighborhoods.drop()\"" % env)
    run("mongoimport ~/src/restaurants.json --db %(name_dbapp)s --collection %(name_app)s_restaurants" % env)
    run("mongoimport ~/src/neighborhoods.json --db %(name_dbapp)s --collection %(name_app)s_neighborhoods" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.%(name_app)s_restaurants.createIndex({name: 'text'})\"" % env)
    run("mongo mongodb://%(mongodb_host)s:%(mongodb_port)s/%(name_dbapp)s --eval \
    \"db.%(name_app)s_neighborhoods.createIndex({name: 'text'})\"" % env)

def _assing_floating_ip():
    headers = {'Content-Type': 'application/json','Authorization': 'Bearer %(token)s' % env}
    url_get_droplets = "https://api.digitalocean.com/v2/droplets?page=1&per_page=1"
    url_assign_ip_droplet = "https://api.digitalocean.com/v2/floating_ips/%(fip)s/actions" % env
    id_droplet = requests.get(url_get_droplets, headers=headers).json()['droplets'][0]['id']
    payload = '{"type":"assign","droplet_id":"%s"}' % id_droplet
    requests.post(url_assign_ip_droplet, headers=headers, data=payload)

# Esto es para poder copiar la aplicación al servidor remoto cuando
# no tengamos la opción de sincronizar carpetas locales con la maquina
# de desarrollo
def _toput():
    run("mkdir -p ~/src")
    env.django_local_path = "/home/usuario/GitHub/Universidad/04Cuarto/Desarrollo_de_aplicaciones_de_internet_DAI/Practica3_semanas_8-9/src"
    put("%(django_local_path)s/%(project)s/urls.py" % env, "%(path_django)s/%(project)s/urls.py" % env)
    put("%(django_local_path)s/%(name_app)s/views.py" % env, "%(path_django)s/%(name_app)s/views.py" % env)
    put("%(django_local_path)s/%(name_app)s/models.py" % env, "%(path_django)s/%(name_app)s/models.py" % env)
    put("%(django_local_path)s/%(name_app)s/urls.py" % env, "%(path_django)s/%(name_app)s/urls.py" % env)
    put("%(django_local_path)s/%(name_app)s/forms.py" % env, "%(path_django)s/%(name_app)s/forms.py" % env)

def start(envirotment):
    _set_env(envirotment)
    _levantar_maquina(envirotment)
    if envirotment == "remote":
        _assing_floating_ip()
    _configurar_maquina(envirotment)


def play(envirotment):
    _set_env(envirotment)
    _ejecutar_aplicacion()

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
    local("curl localhost:8080")
    local("curl localhost:8080/static")
    local("curl localhost:8080/var/Carlos_de_la_Torre")
    local("curl localhost:8080/static-data")
    local("curl localhost:8080/random-image --output -")
    local("curl localhost:8080/dynamic-image/2/2/-2/-2/300/75/0 --output -")
    local("curl localhost:8080/error")

def test(envirotment):
    _assing_floating_ip()

def showvars():
    print("%(pythonbin)s" % env)
    print("%(datenow)s" % env)
    print("%(user)s" % env)
    print("%(key_filename)s" % env)
    print("%(path_django)s" % env)
    print("%(project)s" % env)
    print("%(pass_admin_app)s" % env)
    print("%(secret_key)s" % env)
    print("%(mongodb_host)s" % env)
    print("%(mongodb_port)s" % env)
    print("%(token)s" % env)
    print("%(fip)s" % env)
    print("%(name_app)s" % env)
    print("%(name_dbapp)s" % env)
    print("%(github_client_id_dev)s" % env)
    print("%(github_client_secret_dev)s" % env)
    print("%(google_client_id_dev)s" % env)
    print("%(google_client_secret_dev)s" % env)
    print("%(github_client_id_pro)s" % env)
    print("%(github_client_secret_pro)s" % env)
    print("%(google_client_id_pro)s" % env)
    print("%(google_client_secret_pro)s" % env)

def install_vbguest(envirotment):
    if envirotment == "":
        print("Por favor indique la maquina que quiere desplegar local, prolocal ó remote?")
        sys.exit()
    _set_env(envirotment)
    # primero se crea la maquina, despues se actualiza la maquina, se tiene que reiniciar,
    # despues se instala las vbguest y despues se ejecuta todo
    local("vagrant vbguest %s -f -b --do install" % envirotment)
    sudo("shutdown now")