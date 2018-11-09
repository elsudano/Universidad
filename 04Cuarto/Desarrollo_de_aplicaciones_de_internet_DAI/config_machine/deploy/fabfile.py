#!/usr/bin/python
# -*- coding: UTF-8 -*-
import os
from fabric.api import env, local, run, sudo
from fabric.operations import put

env.user = 'vagrant'
env.password = 'vagrant'
env.hosts = ['localhost:2222']

def _levantar_maquina():
    local('vagrant up --no-provision')

def _detener_maquina():
    local('vagrant halt --force')

def _configurar_maquina():
    local('vagrant provision')
    local('sed "/localhost/d" ~/.ssh/known_hosts > ~/.ssh/known_hosts')

def _ejecutar_aplicacion():
    run('flask-3.7 run -h 0.0.0.0 -p 5000')
    
# Esto es para poder copiar la aplicación al servidor remoto cuando
# no tengamos la opción de sincronizar carpetas locales con la maquina
# de desarrollo
def _topost():
    run('mkdir -p ~/src')
    put('~/GitHub/Universidad/04Cuarto/Desarrollo_de_aplicaciones_de_internet_DAI/config_machine/src', '~/')

def start():
    _levantar_maquina()
    _configurar_maquina()
    _ejecutar_aplicacion()
    
def restart():
    _configurar_maquina()
    _ejecutar_aplicacion()
    
def stop():
    _detener_maquina()

def remove():
    stop()
    local('vagrant destroy --force')

def tests_app():
    local('curl localhost:8080')
    local('curl localhost:8080/static')
    local('curl localhost:8080/var/Carlos_de_la_Torre')
    local('curl localhost:8080/static-data')
    local('curl localhost:8080/random-image --output -')
    local('curl localhost:8080/dynamic-image/2/2/-2/-2/300/75/0 --output -')
    local('curl localhost:8080/error')

def test():
    run('env')
