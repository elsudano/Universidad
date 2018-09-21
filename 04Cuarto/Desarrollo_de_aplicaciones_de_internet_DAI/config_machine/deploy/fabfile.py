import os
from fabric.api import env, local, run, sudo

env.user = 'usuario'
env.hosts = ['middleware.westeurope.cloudapp.azure.com']
env.key_filename = '~/.ssh/id_rsa_deploying'

def _levantar_maquina():
    local('vagrant up --no-provision')

def _destruir_maquina_zeit():
    local('make clean_deploy')

def _destruir_maquina():
    local('vagrant destroy --force')

def _configurar_maquina():
    local('vagrant provision')
    local('sed "/middleware.westeurope.cloudapp.azure.com/d" ~/.ssh/known_hosts > ~/.ssh/known_hosts')
    if run('echo $DOMAIN') == '':
        sudo('echo DOMAIN="' + os.environ['DOMAIN'] + '" >> /etc/environment')
    if run('echo $URL_BASE') == '':
        sudo('echo URL_BASE="' + os.environ['URL_BASE'] + '" >> /etc/environment')
    if run('echo $USER_NEXTCLOUD') == '':
        sudo('echo USER_NEXTCLOUD="' + os.environ['USER_NEXTCLOUD'] + '" >> /etc/environment')
    if run('echo $PASS_NEXTCLOUD') == '':
        sudo('echo PASS_NEXTCLOUD="' + os.environ['PASS_NEXTCLOUD'] + '" >> /etc/environment')
    if run('echo $PORT') == '':
        sudo('echo PORT="80"' + ' >> /etc/environment')

def _ejecutar_aplicacion():
    local('make run &')
    local('make deploy &')
    sudo('~/go/bin/MiddleWare_NextCloud')

def _detener_aplicacion():
    local('curl http://localhost:8080/exit &')
    local('curl https://middleware-nextcloud.herokuapp.com/exit &')
    local('curl https://middlewarenextcloud-ovqbkksdcd.now.sh/exit &')
    local('curl http://middleware.westeurope.cloudapp.azure.com/exit &')

def deploy():
    _levantar_maquina()
    _configurar_maquina()
    _ejecutar_aplicacion()

def undeploy():
    _detener_aplicacion()
    _destruir_maquina_zeit()
    _destruir_maquina() 
