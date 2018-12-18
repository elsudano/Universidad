#!/usr/bin/python3.7
# -*- coding: UTF-8 -*-
import os, sys, requests
from fabric.api import env, local, run, sudo
from fabric.operations import put
from fabric.contrib.files import exists

env.user = 'vagrant'
env.key_filename = "~/.ssh/id_rsa_deploying"
env.path_django = '/home/vagrant/src'
env.project = 'DAIPROJECT'
env.app = 'restaurantes'

def _set_env(envirotment):
    if envirotment == "local":
        env.password = 'vagrant'
        env.host_string = 'localhost:2222'
    elif envirotment == "remote":
        env.host_string = 'dai.sudano.net:22'
    else:
        print ("Por favor indique una maquina valida para desplegar")
        sys.exit()

def _levantar_maquina(envirotment):
    if envirotment == "local":
        local('vagrant up --no-provision --provider=virtualbox local')
    elif envirotment == "remote":
        local('vagrant up --no-provision --provider=digital_ocean remote')

def _detener_maquina(envirotment):
    if envirotment == "local":
        local('vagrant halt --force local')
    elif envirotment == "remote":
        local('vagrant halt --force remote')

def _configurar_maquina(envirotment):
    if envirotment == "local":
        local('sed "/localhost/d" ~/.ssh/known_hosts > ~/.ssh/known_hosts.tmp')
        local('sed "/127.0.0.1/d" ~/.ssh/known_hosts.tmp > ~/.ssh/known_hosts')
        local('ssh-copy-id -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no -i /home/usuario/.ssh/id_rsa_deploying vagrant@localhost -p 2222')
        local('vagrant provision local')
        _configurar_django()
    elif envirotment == "remote":
        local('vagrant provision remote')
        local('vagrant ssh remote -c "sudo dnf install python2.x86_64 firewalld.noarch -y"')

def _ejecutar_aplicacion():
    run('python3.6 %(path_django)s/manage.py runserver 0.0.0.0:8080' % env)

def _eliminar_maquina(envirotment):
    if envirotment == "local":
        local('vagrant destroy --force local')
    elif envirotment == "remote":
        local('vagrant destroy --force remote')

def _configurar_django():
    if not exists('%(path_django)s/%(project)s' % env):
        run('django-admin startproject %(project)s %(path_django)s' % env)
    run('sed -i "s/\'DIRS\': \\\[\\\]\\\,/\'DIRS\': [os.path.join(BASE_DIR, \'\/templates\/\')],/" %(path_django)s/%(project)s/settings.py' % env)
    run('sed -i "s/LANGUAGE_CODE = \'en-us\'/LANGUAGE_CODE = \'es-ES\'/" %(path_django)s/%(project)s/settings.py' % env)
    run('sed -i "s/TIME_ZONE = \'UTC\'/TIME_ZONE = \'Europe\/Madrid\'/" %(path_django)s/%(project)s/settings.py' % env)
    if not exists('%(path_django)s/%(app)s' % env):
        run('cd %(path_django)s; python3.6 %(path_django)s/manage.py startapp %(app)s' % env)
    run('python3.6 %(path_django)s/manage.py migrate' % env)
    run('python3.6 %(path_django)s/manage.py createsuperuser' % env)


def _import_data_mongodb():
    mongodb_host = os.environ['MONGODB_HOST']
    mongodb_port = os.environ['MONGODB_PORT']
    run('mongo mongodb://%s:%s/dai --eval "db.dropDatabase()"' % (mongodb_host, mongodb_port))
    run('mongoimport ~/src/restaurants.json --db dai --collection restaurants')
    run('mongoimport ~/src/neighborhoods.json --db dai --collection neighborhoods')
    run('mongo mongodb://%s:%s/dai --eval "db.restaurants.createIndex({name: \'text\'})"' % (mongodb_host, mongodb_port))
    run('mongo mongodb://%s:%s/dai --eval "db.neighborhoods.createIndex({name: \'text\'})"' % (mongodb_host, mongodb_port))

def _assing_floating_ip():
    token = os.environ['DO_TOKEN']
    fip = os.environ['DO_FIP']
    headers = {'Content-Type': 'application/json','Authorization': 'Bearer %s' % token}
    url_get_droplets = 'https://api.digitalocean.com/v2/droplets?page=1&per_page=1'
    url_assign_ip_droplet = 'https://api.digitalocean.com/v2/floating_ips/%s/actions' % fip    
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
    _import_data_mongodb()

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
        print('Por favor indique la maquina que quiere desplegar local/remote?')
        sys.exit()
    _set_env(envirotment)
    # primero se crea la maquina, despues se actualiza la maquina, se tiene que reiniciar,
    # despues se instala las vbguest y despues se ejecuta todo
    local('vagrant vbguest %s -f -b --do install' % envirotment)
    sudo('shutdown -r 0')