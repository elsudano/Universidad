#!/usr/bin/python
# -*- coding: UTF-8 -*-

from PIL import Image
from flask import Flask, send_file, render_template, request, redirect, url_for, session
from OpenSSL import SSL
context = SSL.Context(SSL.SSLv23_METHOD)
context.use_privatekey_file('src/secure/privkey.pem')
context.use_certificate_file('src/secure/cert.pem')

app = Flask(__name__, static_folder='static', template_folder='templates')

@app.route('/', methods=['GET'])
def index():
    return render_template('con_bootstrap/index.html')

@app.route('/login')
def login():
    return render_template('con_bootstrap/login.html')

#@app.route('/settings')
#def settings():
#    return render_template('con_bootstrap/settings.html')

#@app.route('/user')
#def user():
#    return render_template('con_bootstrap/user.html')

@app.route('/doc')
def doc():
    return render_template('con_bootstrap/doc.html')

@app.errorhandler(404)
def page_not_found(e):
    return render_template('con_bootstrap/404.html')

if __name__ == '__main__':
    app.jinja_env.auto_reload = True
    app.config['TEMPLATES_AUTO_RELOAD'] = True
    app.run(host='0.0.0.0', debug=True, ssl_context=context)

