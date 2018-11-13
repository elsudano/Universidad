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
    return render_template('sin_bootstrap/index.html')

@app.route('/doc')
def doc():
    return render_template('sin_bootstrap/doc.html')

@app.route('/var/<myvar>')
def var_name(myvar):
    return "Hola"

@app.route('/static-data')
def static_data():
    return "Hola"

@app.route('/random-image')
def dynamic_image_random():
    return "Hola"

@app.errorhandler(404)
def page_not_found(e):
    data = """<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Error 404</title>
        <style type="text/css">
            body, html  { height: 100%; }html, body, div, span, applet, object, iframe,/*h1,2, h3, h4, h5, h6,*/ p, blockquote, pre,a, abbr, acronym, address,del, dfn, em, font, img, ins, kbd, q, s, samp,small, strike, strong, sub, sup, tt, var,b, u, i, center,dl, dt, dd, ol, ul, li,fieldset, form, label, legend,table, caption, tbody, tfoot, thead, tr, th, td {margin: 0;padding: 0;border: 0;outline: 0;font-size: 100%;vertical-align: baseline;background: transparent;}body { line-height: 1; }ol, ul { list-style: none; }blockquote, q { quotes: none; }blockquote:before, blockquote:after, q:before, q:after { content: ''; content: none; }:focus { outline: 0; }del { text-decoration: line-through; }table {border-spacing: 0; }
            .clear {clear: both;display: block;overflow: hidden;visibility: hidden;width: 0;height: 0;}
            body{
                color: #e9e2ee;
                font-size: 14px;
                font-family: sans-serif;
                line-height: 20px;
                background: #55575c;
                text-shadow: 0 1px 1px rgba(0,0,0,0.75);
                -webkit-box-shadow: inset 0 0 300px rgba(0,0,0,0.5);
                -moz-box-shadow: inset 0 0 300px rgba(0,0,0,0.5);
                        box-shadow: inset 0 0 300px rgba(0,0,0,0.5);
            }
            h1{
                text-align: center;
                font-size: 40px;
            }
        </style>
    </head>
    <body>
        <br>
        <h1>Lo siento, no encuentro la página que estas buscando</h1>
    </body>
    </html>"""
    return data

if __name__ == '__main__':
    app.jinja_env.auto_reload = True
    app.config['TEMPLATES_AUTO_RELOAD'] = True
    app.run(host='0.0.0.0', debug=True, ssl_context=context)

