#!/usr/bin/python3.7
# -*- coding: UTF-8 -*-
import os, sys, requests
from fabric.api import env, local, run, sudo
from fabric.operations import put, abort

env.user = 'vagrant'
env.key_filename = "~/.ssh/id_rsa_deploying"
env.machine = "local"

def _set_env():
    if env.machine == "local":
        env.password = 'vagrant'
        env.host_string = 'localhost:2222'
    elif env.machine == "remote":
        env.host_string = 'spsi.sudano.net:22'
    else:
        abort("Por favor indique una maquina valida para desplegar")

def _levantar_maquina():
    if env.machine == "local":
        local('vagrant up --no-provision --provider=virtualbox local')
    elif env.machine == "remote":
        local('vagrant up --no-provision --provider=digital_ocean remote')

def _detener_maquina():
    if env.machine == "local":
        local('vagrant halt --force local')
    elif env.machine == "remote":
        local('vagrant halt --force remote')

def _configurar_maquina():
    if env.machine == "local":
        #local('sed "/localhost/d" ~/.ssh/known_hosts > ~/.ssh/known_hosts.tmp')
        #local('sed "/127.0.0.1/d" ~/.ssh/known_hosts.tmp > ~/.ssh/known_hosts.tmp')
        #local('mv -f ~/.ssh/known_hosts.tmp ~/.ssh/known_hosts')
        local('ssh-copy-id -o UserKnownHostsFile=/dev/null -o StrictHostKeyChecking=no -i /home/usuario/.ssh/id_rsa_deploying vagrant@localhost -p 2222')
        local('vagrant provision local')
    elif env.machine == "remote":
        local('vagrant ssh remote -c "sudo dnf install python2.x86_64 firewalld.noarch -y"')
        local('vagrant provision remote')


def _ejecutar_aplicacion():
    run('openssl version')

def _eliminar_maquina():
    if env.machine == "local":
        local('vagrant destroy local --force')
    elif env.machine == "remote":
        local('vagrant destroy remote --force')
    
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
    put('~/GitHub/Universidad/04Cuarto/Sistemas_y_proteccion_de_sistemas_informaticos_SPSI/Practica4/src', '~/')

def start():
    _set_env()
    _levantar_maquina()
    if env.machine == "remote":
        _assing_floating_ip()

def play():
    _set_env()
    #_toput()
    _configurar_maquina()
    _ejecutar_aplicacion()

def restart():
    _detener_maquina()
    _set_env()
    _levantar_maquina()
    _configurar_maquina()
    _ejecutar_aplicacion()
    
def stop():
    _set_env()
    _detener_maquina()

def remove():
    _set_env()
    _detener_maquina()
    _eliminar_maquina()

def install_vbguest():
    _set_env()
    # primero se crea la maquina, despues se actualiza la maquina despues se instala las vbguest y despues se ejecuta todo
    local('vagrant vbguest %s -f -b --do install' % env.machine)
    run('shutdown -r 0')
