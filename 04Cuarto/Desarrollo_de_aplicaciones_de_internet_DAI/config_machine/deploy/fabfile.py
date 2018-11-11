#!/usr/bin/python
# -*- coding: UTF-8 -*-
import os, sys
from fabric.api import env, local, run, sudo
from fabric.operations import put

env.user = 'vagrant'
env.key_filename = "~/.ssh/id_rsa_deploying"

def _set_env(envirotment):
    if envirotment == "local":
        env.host_string = 'localhost:2222'
    elif envirotment == "remote":
        env.host_string = 'dai.sudano.net:22'
    else:
        print "Por favor indique una maquina valida para desplegar"
        sys.exit()
    #print "Connect to Host: %s" %(env.host_string)
            
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
        local('vagrant provision local')
        local('sed "/localhost/d" ~/.ssh/known_hosts > ~/.ssh/known_hosts')
    elif envirotment == "remote":
        local('vagrant provision remote')

def _ejecutar_aplicacion(envirotment):
    run('flask-3.6 run -h 0.0.0.0 -p 8080')

def _eliminar_maquina(envirotment):
    if envirotment == "local":
        local('vagrant destroy local --force')
    elif envirotment == "remote":
        local('vagrant destroy remote --force')

def _create_floating_ip():
    print "En esta funcion creamos la ip publica y la presentamos por pantalla para que se pueda asignar al dominio que nosostros queremos ademas se guarda en una variable global para que la podamos asignar luego a la maquina que hemos creado"

def _assing_floating_ip():
    fip = os.environ['DO_FIP']
    print "Tienes que asignar la ip publica %s que se ha creado antes a la maquina que queremos que responda" % fip

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
    if envirotment == "local":
        local('ssh-copy-id -i /home/usuario/.ssh/id_rsa_deploying vagrant@127.0.0.1 -p 2222')
        _toput()
    elif envirotment == "remote":
        local('vagrant ssh remote -c "sudo dnf install python2.x86_64 firewalld.noarch -y"')
    _configurar_maquina(envirotment)
    _ejecutar_aplicacion(envirotment)

def restart(envirotment):
    _set_env(envirotment)
    _configurar_maquina(envirotment)
    _ejecutar_aplicacion(envirotment)
    
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
    run('env')
