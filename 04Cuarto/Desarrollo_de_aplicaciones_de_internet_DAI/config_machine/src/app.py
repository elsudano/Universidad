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

# ---------------------- Parte pública ---------------------------
@app.route('/', methods=['GET', 'POST'])
def index():
    if session.get('auth'):
        result = render_template('con_bootstrap/index.html', user=session['user'])
    else:
        result = render_template('con_bootstrap/login.html')
    return result

@app.route('/settings')
def settings():
    if session.get('auth'):
        result = render_template('con_bootstrap/settings.html', user=session['user'])
    else:
        result = render_template('con_bootstrap/login.html')
    return result

@app.route('/user')
def user():
    if session.get('auth'):
        result = render_template('con_bootstrap/user.html', user=session['user'])
    else:
        result = render_template('con_bootstrap/login.html')
    return result

@app.route('/doc')
def doc():
    if session.get('auth'):
        result = render_template('con_bootstrap/doc.html')
    else:
        result = render_template('con_bootstrap/login.html')
    return result

@app.route('/flot')
def flot():
    if session.get('auth'):
        result = render_template('con_bootstrap/flot.html')
    else:
        result = render_template('con_bootstrap/login.html')
    return result

@app.route('/morris')
def morris():
    if session.get('auth'):
        result = render_template('con_bootstrap/morris.html')
    else:
        result = render_template('con_bootstrap/login.html')
    return result

@app.route('/tables')
def tables():
    if session.get('auth'):
        result = render_template('con_bootstrap/tables.html')
    else:
        result = render_template('con_bootstrap/login.html')
    return result

@app.route('/forms')
def forms():
    if session.get('auth'):
        result = render_template('con_bootstrap/forms.html')
    else:
        result = render_template('con_bootstrap/login.html')
    return result

@app.route('/panels-wells')
def panels_wells():
    if session.get('auth'):
        result = render_template('con_bootstrap/panels-wells.html')
    else:
        result = render_template('con_bootstrap/login.html')
    return result

@app.route('/buttons')
def buttons():
    if session.get('auth'):
        result = render_template('con_bootstrap/buttons.html')
    else:
        result = render_template('con_bootstrap/login.html')
    return result

@app.route('/notifications')
def notifications():
    if session.get('auth'):
        result = render_template('con_bootstrap/notifications.html')
    else:
        result = render_template('con_bootstrap/login.html')
    return result

@app.route('/typography')
def typography():
    if session.get('auth'):
        result = render_template('con_bootstrap/typography.html')
    else:
        result = render_template('con_bootstrap/login.html')
    return result

@app.route('/icons')
def icons():
    if session.get('auth'):
        result = render_template('con_bootstrap/icons.html')
    else:
        result = render_template('con_bootstrap/login.html')
    return result

@app.route('/grid')
def grid():
    if session.get('auth'):
        result = render_template('con_bootstrap/grid.html')
    else:
        result = render_template('con_bootstrap/login.html')
    return result

@app.route('/blank')
def blank():
    if session.get('auth'):
        result = render_template('con_bootstrap/blank.html')
    else:
        result = render_template('con_bootstrap/login.html')
    return result

@app.route('/session', methods=['GET', 'POST'])
def session_user():
    if request.method == 'GET':
        action = request.args.get('action')
        if action == 'logout':
            session['auth'] = False
            session.clear()
            result = render_template('con_bootstrap/index.html')
    elif request.method == 'POST':
        user = request.form.get('user')
        passwd = request.form.get('passwd')
        action = request.form.get('action')
    else:
        result = render_template('con_bootstrap/login.html')

    if action == 'login':
        if exist_user(user, passwd) != False:
            session['auth'] = True
            session['user'] = user
            result = render_template('con_bootstrap/index.html', user=session['user'])
        else:
            result = render_template('con_bootstrap/login.html', fail=True)
    elif action == 'register':
        register_user(user, passwd)
        session['auth'] = True
        session['user'] = user
        result = render_template('con_bootstrap/index.html', user=session['user'])
    else:
        result = render_template('con_bootstrap/login.html')
    return result

@app.errorhandler(404)
def page_not_found(e):
    if session.get('auth'):
        result = render_template('con_bootstrap/404.html')
    else:
        result = render_template('con_bootstrap/login.html')
    return result

if __name__ == '__main__':
    app.jinja_env.auto_reload = True
    app.config['TEMPLATES_AUTO_RELOAD'] = True
    app.run(host='0.0.0.0', debug=True, ssl_context=context)

