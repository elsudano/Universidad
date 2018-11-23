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
    if action == 'read' and user in db:
        if passwd == None or passwd == '':
            result = False
        else:
            passwd = salt + passwd
            passwd = passwd.encode('utf-8')
            passwd = md5.update(passwd)
            if md5.hexdigest() == db[user].get("passwd"):
                result = True
            else:
                result = False
    elif action == 'write':
        passwd = salt + passwd
        passwd = passwd.encode('utf-8')
        passwd = md5.update(passwd)
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
        {'href':'/register','caption':'Registro de Usuario'},
        {'href':'/test_user','caption':'Test para el Usuario'},
    ]
    return menu_items

# ---------------------- Parte pública ---------------------------
@app.route('/', methods=['GET', 'POST'])
def index():
#    if session['auth']:
#        result = render_template('sin_bootstrap/index.html', user=session['user'], navigation=make_menu())
#    else:
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
    return render_template('sin_bootstrap/doc.html', docitems=docitems, navigation=make_menu())

@app.route('/var/<name>')
def var(name):
    return render_template('sin_bootstrap/var.html', name=name, navigation=make_menu())

@app.route('/register', methods=['GET', 'POST'])
def register():
    if register_user(request.form['user'], request.form['passwd']):
        result = render_template('sin_bootstrap/register.html', reg=True, name=name, navigation=make_menu())
    else:
        result = render_template('sin_bootstrap/register.html', reg=False, name=name, navigation=make_menu())
    return result

@app.route('/test_user', methods=['GET', 'POST'])
def test_user():
    if exist_user(request.form['user'], request.form['passwd']):
        result = render_template('sin_bootstrap/test_user.html', exist=True, name=name, navigation=make_menu())
    else:
        result = render_template('sin_bootstrap/test_user.html', exist=False, name=name, navigation=make_menu())
    return result

@app.route('/session', methods=['GET', 'POST'])
def session():
    result = None
    if request.form['action'] == 'login':
        if exist_user(request.form['user'], request.form['passwd']) != False:
            # aquí tenemos que abrir la session
            print ('Open session')
            session['auth'] = True
            result = render_template('sin_bootstrap/index.html', user=user, navigation=make_menu())
        else:
            result = render_template('sin_bootstrap/session.html', navigation=make_menu())
    elif request.form['action'] == 'logout':
        # aquí tenemos que cerrar la session
        session['auth'] = False
        session.clear()
        print ('Close session')
    elif request.form['action'] == 'register':
        register_user(request.form['user'], request.form['passwd'])
        # aquí tenemos que abrir la session
        print ('Open session')
        result = render_template('sin_bootstrap/index.html', user=user, navigation=make_menu())
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

