#!/usr/bin/python
# -*- coding: UTF-8 -*-
import os, sys
from fabric.api import env, local, run, sudo
from fabric.operations import put

def _set_env(envirotment):
    env.user = 'vagrant'
    env.password = 'vagrant'
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
    run('flask-3.7 run -h 0.0.0.0 -p 5000')

def _eliminar_maquina(envirotment):
    if envirotment == "local":
        local('vagrant destroy local --force')
    elif envirotment == "remote":
        local('vagrant destroy remote --force')

# Esto es para poder copiar la aplicación al servidor remoto cuando
# no tengamos la opción de sincronizar carpetas locales con la maquina
# de desarrollo
def _topost():
    run('mkdir -p ~/src')
    put('~/GitHub/Universidad/04Cuarto/Desarrollo_de_aplicaciones_de_internet_DAI/config_machine/src', '~/')

def start(envirotment):
    _set_env(envirotment)
    _levantar_maquina(envirotment)

def play(envirotment):
    _set_env(envirotment)
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
