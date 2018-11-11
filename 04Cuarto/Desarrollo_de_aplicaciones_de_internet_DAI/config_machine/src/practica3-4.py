#!/usr/bin/python
# -*- coding: UTF-8 -*-

from PIL import Image
from flask import Flask, send_file

app = Flask(__name__, static_folder="static_dir")

@app.route('/')
def hello_world():
    data = """<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Documentación de la APP</title>
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
            p{
                margin-bottom: 0.3em;
                margin-left: 1em;
            }
        </style>
    </head>
    <body>
        <br>
        <h1>Documentación de la Aplicación</h1>
        <p>La siguiente aplicación solamente responde a una serie de URLs, de entre las que se destacan las siguientes:</p>
        <br>
        <p>/hello --> Muestra la primera plantilla utilizada con Jinja32</p>
        <p>/var/(nombre) --> En esta dirección nos saldrá un saludo personalizado en el cual sustituirá lo que pongamos en la URL en el lugar donde se encuentra (nombre) por un valor dado, es una forma de probar la entrada de datos a la aplicación</p>
        <p>/static-data --> En esta dirección lo que vamos a encontrar es una página estática pero esta vez será leida desde un fichero HTML</p>
        <p>/random-image --> Gnerá una imágen fractal y la muestra en el navegador</p>
        <p>/dynamic-image/(x1)/(y1)/(x2)/(y2)/(w)/(i)/(p) --> Gnerá una imágen fractal y la muestra en el navegador, pero en está ocasión hay que poner los párametros que queremos para la imágen, quedando de la siguiente manera los datos a pasar: coordenada x1, coordenada y1, coordenada x2, coordenada y2, ancho de la imágen, cantidad de itraciones para el fractal y paleta de colores</p>
        <br>
        <p>Por último si no encuentra la página que el usuario esta buscando, la aplicación mostrará una pagina de error personalizada</p>
    </body>
    </html>"""
    return data

@app.route('/hello')
def hello_template():
    return "Hola"

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
    app.run(host='0.0.0.0', ssl_context='adhoc')

