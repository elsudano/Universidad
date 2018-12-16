#!/usr/bin/python
# -*- coding: UTF-8 -*-

from PIL import Image
import hashlib
from flask import Flask, send_file, render_template, request, redirect, url_for, session, escape
from pickleshare import *
from OpenSSL import SSL

context = SSL.Context(SSL.SSLv23_METHOD)
context.use_privatekey_file('src/secure/privkey.pem')
context.use_certificate_file('src/secure/cert.pem')

app = Flask(__name__, static_folder='static', template_folder='templates')
app.secret_key = b'una cadena secreta'

# ---------------------- Parte privada ---------------------------

# Función para manejar la base de datos
def db_manage(action,user,passwd=None):
    db = PickleShareDB('~/src/database')
    md5 = hashlib.md5()
    salt = 'sal&pimienta'
    if action == 'read' and user != None and user in db:
        if passwd == None or passwd == '':
            result = False
        else:
            passwd = salt + passwd
            passwd = passwd.encode('utf-8')
            md5.update(passwd)
            if md5.hexdigest() == db[user].get("passwd"):
                result = True
            else:
                result = False
    elif action == 'write':
        passwd = salt + passwd
        passwd = passwd.encode('utf-8')
        md5.update(passwd)
        passwd = md5.hexdigest()
        db[user] = {'passwd': passwd}
        db[user] = db[user]
        result = True
    else:
        result = False
    print (f'Todos los items: {db.keys()}')
    return result

# Comprobamos is existe le usuario
def exist_user(user, passwd=None):
    return db_manage('read',user,passwd)

# Registramos al usuario
def register_user(user,passwd):
    if user == None or passwd == None or user == '' or passwd == '':
        result = False
    else:
        result = db_manage('write',user,passwd)
    return result

# Esta función es para generar el Menú de la página
def make_menu():
    menu_items = [
        {'href':'/','caption':'Inicio'},
        {'href':'/about','caption':'Sobre mi..'},
        {'href':'/doc','caption':'Documentación'},
        {'href':'/var/test','caption':'Test de variable'},
        {'href':'/test_user','caption':'Test para el Usuario'},
    ]
    return menu_items

# ---------------------- Parte pública ---------------------------
@app.route('/', methods=['GET', 'POST'])
def index():
    if session.get('auth') == True:
        result = render_template('sin_bootstrap/index.html', user=session['user'], navigation=make_menu())
    else:
        result = render_template('sin_bootstrap/index.html', navigation=make_menu())
    return result

@app.route('/doc')
def doc():
    docitems = [
        {'deno':'Index Principal /','desc':'Muestra la primera plantilla utilizada con Jinja32'},
        {'deno':'Recogiendo Variables /var/(nombre)','desc':'En esta dirección nos saldrá un saludo personalizado en el cual sustituirá lo que pongamos en la URL en el lugar donde se encuentra (nombre) por un valor dado, es una forma de probar la entrada de datos a la aplicación'},
        {'deno':'Manejo de Sesiones /session','desc':'Desde esta página podemos controlar a los usuarios que se conectan o que se quieren dar de alta en la página'},
        {'deno':'Página de Error 404','desc':'Por último si no encuentra la página que el usuario esta buscando, la aplicación mostrará una pagina de error personalizada'}
    ]
    if session.get('auth') == True:
        result = render_template('sin_bootstrap/doc.html', user=session['user'], docitems=docitems, navigation=make_menu())
    else:
        result = render_template('sin_bootstrap/doc.html', navigation=make_menu())
    return result

@app.route('/var/<name>')
def var(name):
    if session.get('auth') == True:
        result = render_template('sin_bootstrap/var.html', user=session['user'], name=name, navigation=make_menu())
    else:
        result = render_template('sin_bootstrap/var.html', navigation=make_menu())
    return result

@app.route('/register', methods=['GET', 'POST'])
def register():
    user = request.form.get('user')
    passwd = request.form.get('passwd')
    if register_user(user, passwd):
        result = render_template('sin_bootstrap/register.html', reg=True, user=session['user'], navigation=make_menu())
    else:
        result = render_template('sin_bootstrap/register.html', reg=False, user=user, navigation=make_menu())
    return result

@app.route('/test_user', methods=['GET','POST'])
def test_user():
    user = request.form.get('user')
    passwd = request.form.get('passwd')
    if request.method == 'GET' and session.get('auth') == True:
        result = render_template('sin_bootstrap/test_user.html', exist=True, user=session['user'], navigation=make_menu())
    elif request.method == 'POST' and exist_user(user, passwd):
        result = render_template('sin_bootstrap/test_user.html', exist=True, user=user, navigation=make_menu())
    else:
        result = render_template('sin_bootstrap/test_user.html', navigation=make_menu())
    return result

@app.route('/session', methods=['GET', 'POST'])
def session_user():
    result = None
    user = request.form.get('user')
    passwd = request.form.get('passwd')
    action = request.form.get('action')
    if action == 'login':
        if exist_user(user, passwd) != False:
            session['auth'] = True
            session['user'] = user
            result = render_template('sin_bootstrap/index.html', user=session['user'], navigation=make_menu())
        else:
            result = render_template('sin_bootstrap/session.html', fail=True, navigation=make_menu())
    elif action == 'logout':
        session['auth'] = False
        session.clear()
        result = render_template('sin_bootstrap/index.html', navigation=make_menu())
    elif action == 'register':
        register_user(user, passwd)
        session['auth'] = True
        session['user'] = user
        result = render_template('sin_bootstrap/index.html', user=session['user'], navigation=make_menu())
    else:
        result = render_template('sin_bootstrap/session.html', navigation=make_menu())
    return result

@app.errorhandler(404)
def page_not_found(e):
    return render_template('sin_bootstrap/error.html', navigation=make_menu())

if __name__ == '__main__':
    app.jinja_env.auto_reload = True
    app.config['TEMPLATES_AUTO_RELOAD'] = True
    app.run(host='0.0.0.0', debug=True, ssl_context=context)

