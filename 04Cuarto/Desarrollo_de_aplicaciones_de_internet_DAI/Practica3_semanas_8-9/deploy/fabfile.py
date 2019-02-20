#!/usr/bin/python3.7
# -*- coding: UTF-8 -*-
import os, sys, requests, datetime
from fabric.api import env, local, run, sudo, prompt
from fabric.operations import put
from fabric.contrib.files import exists, contains

env.pythonbin = "python3.6"
env.datenow = datetime.datetime.now().isoformat()
env.user = "vagrant"
env.key_filename = "~/.ssh/id_rsa_deploying"
env.django_local_path = "/home/usuario/GitHub/Universidad/04Cuarto/Desarrollo_de_aplicaciones_de_internet_DAI/Practica3_semanas_8-9/src"
env.conn = "mongo mongodb://%s:%s" % (os.environ["MONGODB_HOST"], os.environ["MONGODB_PORT"])
env.path_django = os.environ["DJANGO_REMOTE_ROOT_PATH"]
env.pass_admin_app = os.environ["DJANGO_ADMIN_PASS"]
env.secret_key = os.environ["DJANGO_SECRET_KEY"]
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
        _configurar_django()

def _ejecutar_aplicacion():
    run("%(pythonbin)s %(path_django)s/manage.py runserver 0.0.0.0:8080" % env)


def _eliminar_maquina(envirotment):
    if envirotment == "local":
        local("vagrant destroy --force local")
    elif envirotment == "remote":
        local("vagrant destroy --force remote")

def _migrate():
    run("%(pythonbin)s %(path_django)s/manage.py makemigrations %(name_app)s" % env)
    run("%(pythonbin)s %(path_django)s/manage.py migrate" % env)

def _configurar_django():
    if not exists("%(path_django)s/%(project)s" % env):
        run('mkdir %(path_django)s; chown -R %(user)s:%(user)s %(path_django)s' % env)
        run("django-admin startproject %(project)s %(path_django)s" % env)
    if not exists("%(path_django)s/%(name_app)s" % env):
        run("cd %(path_django)s; %(pythonbin)s %(path_django)s/manage.py startapp %(name_app)s" % env)
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "'DIRS': [os.path.join(BASE_DIR, 'templates/')],"):
        run("sed -i \"/'DIRS': \[\],/ c\ \t'DIRS': [os.path.join(BASE_DIR, 'templates/')],\" %(path_django)s/%(project)s/settings.py" % env)
    if contains("%(path_django)s/%(project)s/settings.py" % env, "LANGUAGE_CODE = 'en-us'"):
        run("sed -i \"/LANGUAGE_CODE = 'en-us'/ cLANGUAGE_CODE = 'es-ES'\" %(path_django)s/%(project)s/settings.py" % env)
    if contains("%(path_django)s/%(project)s/settings.py" % env, "TIME_ZONE = 'UTC'"):
        run("sed -i \"/TIME_ZONE = 'UTC'/ cTIME_ZONE = 'Europe\/Madrid'\" %(path_django)s/%(project)s/settings.py" % env)
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "SECRET_KEY = '%(secret_key)s'" % env):
        run("sed -i \"/SECRET_KEY =/ cSECRET_KEY = '%(secret_key)s'\" %(path_django)s/%(project)s/settings.py" % env)
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "%(name_app)s" % env):
        run("sed -i \"/'django.contrib.staticfiles',/ a\ \t'%(name_app)s',\" %(path_django)s/%(project)s/settings.py" % env)
    debug_on = prompt("¿Quieres el modo debug? [Y/n]?: ", default="Y")
    if debug_on == "y" or debug_on == "Y":
        run("sed -i \"/DEBUG = False/ cDEBUG = True\" %(path_django)s/%(project)s/settings.py" % env)
    elif debug_on == "n" or debug_on == "N":
        run("sed -i \"/DEBUG = True/ cDEBUG = False\" %(path_django)s/%(project)s/settings.py" % env)
    if contains("%(path_django)s/%(project)s/settings.py" % env, "ALLOWED_HOSTS = []"):
        run("sed -i \"/ALLOWED_HOSTS = \[\]/ cALLOWED_HOSTS = ['dai.sudano.net','localhost','127.0.0.1','[::1]']\" %(path_django)s/%(project)s/settings.py" % env)
    collect = prompt("¿Quiere realizar la recolección de ficheros estáticos? [y/N]?: ", default="N")
    if collect == "y" or collect == "Y":
        run("sed -i \"/STATIC_URL = '\/static\/'/ cSTATIC_URL = os.path.join(BASE_DIR, '\/static\/')\" %(path_django)s/%(project)s/settings.py" % env)
        if not contains("%(path_django)s/%(project)s/settings.py" % env, "STATIC_ROOT = "):
            run("sed -i \"/STATIC_URL = os.path.join(BASE_DIR, '\/static\/')/ aSTATIC_ROOT = os.path.join(BASE_DIR, '%(name_app)s\/static\/')\" %(path_django)s/%(project)s/settings.py" % env)
        sudo("%(pythonbin)s %(path_django)s/manage.py collectstatic --noinput" % env)
    config_mongodb = prompt("¿Quieres configurar DJango con MongoDB? [y/N]?: ", default="N")
    if config_mongodb == "y" or config_mongodb == "Y":
        _config_mongodb()
        config_allauth = prompt("¿Quieres configurar AllAuth en MongoDB? [y/N]?: ", default="N")
        if config_allauth == "y" or config_allauth == "Y":
            _config_allauth()
    create_user = prompt("¿Quieres crear el super usuario? [y/N]?: ", default="N")
    if create_user == "y" or create_user == "Y":
        auto = prompt("¿Modo interactivo o automático? [i/A]?: ", default="A")
        if auto == "a" or auto == "A":
            run("%(conn)s/%(name_dbapp)s --eval \
            \"db.auth_user.remove({'id':NumberInt(1)})\"" % env)
            run("%(conn)s/%(name_dbapp)s --eval \
            \"db.auth_user.insert({'id':NumberInt(1),'password':'%(pass_admin_app)s', 'last_login':null, \
            'is_superuser':true,'username':'admin','first_name':'Administrator', \
            'last_name':'of App','email':'admin@localhost.local','is_staff':true, \
            'is_active':true,'date_joined':ISODate('%(datenow)s')})\"" % env)
        elif auto == "i" or auto == "I":
            run("%(conn)s/%(name_dbapp)s --eval \
            \"db.auth_user.remove({'id':NumberInt(1)})\"" % env)
            run("%(pythonbin)s %(path_django)s/manage.py createsuperuser" % env)
    migrate = prompt("¿Quiere realiazar migraciones para la aplicación %(name_app)s? [y/N]?: " % env, default="N")
    if migrate == "y" or migrate == "Y":
        _migrate()
    if not contains("%(path_django)s/%(project)s/urls.py" % env, "from django.urls import path, include"):
        run("sed -i \"/from django.urls import path/ cfrom django.urls import path, include\" %(path_django)s/%(project)s/urls.py" % env)
    if not contains("%(path_django)s/%(project)s/urls.py" % env, "path('', include('%(name_app)s.urls'))," % env):
        run("sed -i \"/path('admin\/', admin.site.urls),/ apath('', include('%(name_app)s.urls')),\" %(path_django)s/%(project)s/urls.py" % env)
    _toput()
    _migrate()
    import_data = prompt("¿Quiere importar los datos de los restaurantes y los vecinos? [y/N]?: ", default="N")
    if import_data == "y" or import_data == "Y":
        _import_data_mongodb()
    _migrate()
    

def _config_mongodb():
    if contains("%(path_django)s/%(project)s/settings.py" % env, "'NAME': os.path.join(BASE_DIR, 'db.sqlite3'),"):
        run("sed -i \"/'NAME': os.path.join(BASE_DIR, 'db.sqlite3'),/d\" %(path_django)s/%(project)s/settings.py" % env)
    if contains("%(path_django)s/%(project)s/settings.py" % env, "'ENGINE': 'django.db.backends.sqlite3',"):
        run("sed -i \"/'ENGINE': 'django.db.backends.sqlite3',/ c\ \t'ENGINE': 'djongo',\" %(path_django)s/%(project)s/settings.py" % env)
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "'ENFORCE_SCHEMA': True,"):
        run("sed -i \"/'ENGINE': 'djongo',/ a\ \t'ENFORCE_SCHEMA': True,\" %(path_django)s/%(project)s/settings.py" % env)
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "'NAME': '%(name_dbapp)s'," % env):
        run("sed -i \"/'ENFORCE_SCHEMA': True,/ a\ \t'NAME': '%(name_dbapp)s',\" %(path_django)s/%(project)s/settings.py" % env)
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "'HOST': 'localhost',"):
        run("sed -i \"/'NAME': '%(name_dbapp)s',/ a\ \t'HOST': 'localhost',\" %(path_django)s/%(project)s/settings.py" % env)
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "'PORT': 27017,"):
        run("sed -i \"/'HOST': 'localhost',/ a\ \t'PORT': 27017,\" %(path_django)s/%(project)s/settings.py" % env)
    are_you_sure = prompt("¿Seguro que quieres borrar la base de datos y la configuración actual? [y/N]?: ", default="N")
    if are_you_sure == "y" or are_you_sure == "Y":
        run("%(conn)s/%(name_dbapp)s --eval \"db.dropDatabase()\"" % env)
        run("%(conn)s/config --eval \"db.dropDatabase()\"" % env)
        _migrate()

def _config_allauth():
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "'django.contrib.sites',"):
        run("sed -i \"/'django.contrib.staticfiles',/ a\ \t'django.contrib.sites',\" %(path_django)s/%(project)s/settings.py" % env)
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "'allauth',"):
        run("sed -i \"/'restaurantes',/ a\ \t'allauth',\" %(path_django)s/%(project)s/settings.py" % env)
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "'allauth.account',"):
        run("sed -i \"/'allauth',/ a\ \t'allauth.account',\" %(path_django)s/%(project)s/settings.py" % env)
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "'allauth.socialaccount',"):
        run("sed -i \"/'allauth.account',/ a\ \t'allauth.socialaccount',\" %(path_django)s/%(project)s/settings.py" % env)
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "'allauth.socialaccount.providers.github',"):
        run("sed -i \"/'allauth.socialaccount',/ a\ \t'allauth.socialaccount.providers.github',\" %(path_django)s/%(project)s/settings.py" % env)
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "'allauth.socialaccount.providers.google',"):
        run("sed -i \"/'allauth.socialaccount.providers.github',/ a\ \t'allauth.socialaccount.providers.google',\" %(path_django)s/%(project)s/settings.py" % env)
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "SITE_ID = 1"):
        run("sed -i \"/USE_TZ =/ a\SITE_ID = 1\" %(path_django)s/%(project)s/settings.py" % env)
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "LOGIN_REDIRECT_URL"):
        run("sed -i \"/SITE_ID = 1/ a\LOGIN_REDIRECT_URL = '\/'\" %(path_django)s/%(project)s/settings.py" % env)
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "LOGOUT_REDIRECT_URL"):
        run("sed -i \"/LOGIN_REDIRECT_URL = '\/'/ a\LOGOUT_REDIRECT_URL = '\/login'\" %(path_django)s/%(project)s/settings.py" % env)
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "ACCOUNT_LOGOUT_ON_GET"):
        run("sed -i \"/LOGOUT_REDIRECT_URL = '\/login'/ a\ACCOUNT_LOGOUT_ON_GET = True\" %(path_django)s/%(project)s/settings.py" % env)
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "AUTHENTICATION_BACKENDS"):
        run("sed -i \"/ACCOUNT_LOGOUT_ON_GET = True/ aAUTHENTICATION_BACKENDS = (\\n \
            'django.contrib.auth.backends.ModelBackend',\\n \
            'allauth.account.auth_backends.AuthenticationBackend',\\n)\" \
            %(path_django)s/%(project)s/settings.py" % env)
    if not contains("%(path_django)s/%(project)s/settings.py" % env, "ACCOUNT_FORMS"):
        run("sed -i \"/ACCOUNT_LOGOUT_ON_GET = True/ aACCOUNT_FORMS = {\\n \
            'login': 'restaurantes.forms.MyLoginForm',\\n \
            'signup': 'restaurantes.forms.MySignupForm',\\n \
        }\" %(path_django)s/%(project)s/settings.py" % env)
    _migrate()
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp.remove({'id':NumberInt(1)})\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp.insert({'id':NumberInt(1),'provider':'github','name': \
    'Github Dev','client_id':'%(github_client_id_dev)s','secret':'%(github_client_secret_dev)s' \
    ,'key':''})\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp.remove({'id':NumberInt(2)})\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp.insert({'id':NumberInt(2),'provider':'google','name': \
    'Google Dev','client_id':'%(google_client_id_dev)s','secret':'%(google_client_secret_dev)s' \
    ,'key':''})\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp.remove({'id':NumberInt(3)})\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp.insert({'id':NumberInt(3),'provider':'github','name': \
    'Github Pro','client_id':'%(github_client_id_pro)s','secret':'%(github_client_secret_pro)s' \
    ,'key':''})\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp.remove({'id':NumberInt(4)})\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp.insert({'id':NumberInt(4),'provider':'google','name': \
    'Google Pro','client_id':'%(google_client_id_pro)s','secret':'%(google_client_secret_pro)s' \
    ,'key':''})\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.django_site.remove({'id':NumberInt(1)})\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.django_site.insert({'id':NumberInt(1),'domain':'localhost','name':'dai.sudano.net'})\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp_sites.remove({'id':NumberInt(1)})\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp_sites.insert({'id':NumberInt(1),'socialapp_id':NumberInt(1),'site_id':NumberInt(1)})\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp_sites.remove({'id':NumberInt(2)})\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp_sites.insert({'id':NumberInt(2),'socialapp_id':NumberInt(2),'site_id':NumberInt(1)})\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp_sites.remove({'id':NumberInt(3)})\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp_sites.insert({'id':NumberInt(3),'socialapp_id':NumberInt(3),'site_id':NumberInt(1)})\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp_sites.remove({'id':NumberInt(4)})\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.socialaccount_socialapp_sites.insert({'id':NumberInt(4),'socialapp_id':NumberInt(4),'site_id':NumberInt(1)})\"" % env)

def _import_data_mongodb():
    run("%(conn)s/%(name_dbapp)s --eval \"db.%(name_app)s_modelrestaurants.drop()\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \"db.%(name_app)s_neighborhoods.drop()\"" % env)
    run("mongoimport ~/src/restaurants.json --db %(name_dbapp)s --collection %(name_app)s_modelrestaurants" % env)
    run("mongoimport ~/src/neighborhoods.json --db %(name_dbapp)s --collection %(name_app)s_neighborhoods" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
    \"db.%(name_app)s_modelrestaurants.createIndex({name: 'text'})\"" % env)
    run("%(conn)s/%(name_dbapp)s --eval \
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
    sudo("chown -R %(user)s:%(user)s %(path_django)s" % env)
    copy_sure = prompt("¿Quiere copiar los ficheros fuente al servidor remoto? [Y/n]?: ", default="Y")
    if copy_sure == "y" or copy_sure == "Y":
        put("%(django_local_path)s/restaurants.json" % env, "%(path_django)s/restaurants.json" % env)
        put("%(django_local_path)s/neighborhoods.json" % env, "%(path_django)s/neighborhoods.json" % env)
        put("%(django_local_path)s/%(name_app)s/urls.py" % env, "%(path_django)s/%(name_app)s/urls.py" % env)
        put("%(django_local_path)s/%(name_app)s/views.py" % env, "%(path_django)s/%(name_app)s/views.py" % env)
        put("%(django_local_path)s/%(name_app)s/models.py" % env, "%(path_django)s/%(name_app)s/models.py" % env)
        put("%(django_local_path)s/%(name_app)s/forms.py" % env, "%(path_django)s/%(name_app)s/forms.py" % env)
        put("%(django_local_path)s/%(name_app)s/static/" % env, "%(path_django)s/%(name_app)s/" % env)
        put("%(django_local_path)s/%(name_app)s/templates/" % env, "%(path_django)s/%(name_app)s/" % env)
        put("%(django_local_path)s/%(name_app)s/templatetags/" % env, "%(path_django)s/%(name_app)s/" % env)

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