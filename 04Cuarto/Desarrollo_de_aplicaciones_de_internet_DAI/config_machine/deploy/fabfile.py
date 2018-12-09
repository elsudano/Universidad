#!/usr/bin/python3.7
# -*- coding: UTF-8 -*-
import os, sys, requests
from fabric.api import env, local, run, sudo
from fabric.operations import put

env.user = 'vagrant'
env.key_filename = "~/.ssh/id_rsa_deploying"

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
    elif envirotment == "remote":
        local('vagrant provision remote')
        local('vagrant ssh remote -c "sudo dnf install python2.x86_64 firewalld.noarch -y"')

def _ejecutar_aplicacion():
    run('flask-3.6 run -h 0.0.0.0 -p 8080')

def _eliminar_maquina(envirotment):
    if envirotment == "local":
        local('vagrant destroy local --force')
    elif envirotment == "remote":
        local('vagrant destroy remote --force')

def _import_data_mongodb():
    mongodb_host = os.environ['MONGODB_HOST']
    mongodb_port = os.environ['MONGODB_PORT']
    run('mongo mongodb://%s:%s/dai --eval "db.dropDatabase()"' % (mongodb_host, mongodb_port))
    run('mongoimport ~/src/static/json/restaurants.json --db dai --collection restaurants')
    run('mongoimport ~/src/static/json/neighborhoods.json --db dai --collection neighborhoods')
    run('mongo mongodb://%s:%s/dai --eval "db.restaurants.createIndex({name: \'text\'})"' % (mongodb_host, mongodb_port))
    

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

def play(envirotment):
    _set_env(envirotment)
    #_toput()
    _configurar_maquina(envirotment)
    _import_data_mongodb()
    _ejecutar_aplicacion()

def restart(envirotment):
    _detener_maquina(envirotment)
    _set_env(envirotment)
    _levantar_maquina(envirotment)
    _configurar_maquina(envirotment)
    _import_data_mongodb()
    _ejecutar_aplicacion()
    
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
    _set_env(envirotment)
    # primero se crea la maquina, despues se actualiza la maquina, se tiene que reiniciar,
    # despues se instala las vbguest y despues se ejecuta todo
    local('vagrant vbguest %s -f -b --do install' % envirotment)
    run('shutdown -r 0')