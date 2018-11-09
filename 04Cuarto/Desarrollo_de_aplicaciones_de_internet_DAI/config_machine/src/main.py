#!/usr/bin/python
# -*- coding: UTF-8 -*-

from PIL import Image
from flask import Flask, send_file
from random import randint

app = Flask(__name__, static_folder="static_dir")

# paleta es una lista de 3-tuplas con los valores RGB de cada color de la paleta
def getColorPaleta(paleta, nColoresPaleta, color):
	nuevoColor = color % (nColoresPaleta)
	nPuntosControlPaleta = len(paleta)
	icolor1 = float(nuevoColor) * float(nPuntosControlPaleta - 1) / float(nColoresPaleta - 1)
	# print len(paleta)
	# print int(icolor1)
	if (int(icolor1) == len(paleta) - 1):  # Devolvemos el último color
		return paleta[len(paleta) - 1]
        
	porcentajeC2 = icolor1 - int(icolor1)
	porcentajeC1 = 1.0 - porcentajeC2
	icolor1 = int(icolor1)
	icolor2 = icolor1 + 1
        
	nr = int(paleta[icolor1][0] * porcentajeC1 + paleta[icolor2][0] * porcentajeC2)
	ng = int(paleta[icolor1][1] * porcentajeC1 + paleta[icolor2][1] * porcentajeC2)
	nb = int(paleta[icolor1][2] * porcentajeC1 + paleta[icolor2][2] * porcentajeC2)
        
	return (nr, ng, nb)
        
	
# Renderiza un fractal de Mandelbrot de (x1, y1) a (x2, y2) de ancho pixeles, iteraciones máximas y lo guarda en el fichero especificado en nombreFicheroPNG (debe tener extesión .png) y permitiendo especificar una paleta de colores: paleta es una lista de 3-tuplas con los valores RGB de cada color de la paleta

def renderizaMandelbrotBonito(x1, y1, x2, y2, ancho, iteraciones, nombreFicheroPNG, paleta, nColoresPaleta):
	# drawing area
	xa = x1
	xb = x2
	ya = y1 + 0.000001
	yb = y2
	maxIt = iteraciones
	# image size
	imgx = ancho
	imgy = int(abs (y2 - y1) * ancho / abs(x2 - x1));
	image = Image.new("RGB", (imgx, imgy))
	for y in range(imgy):
		zy = y * (yb - ya) / (imgy - 1)  + ya
		for x in range(imgx):
			zx = x * (xb - xa) / (imgx - 1)  + xa
			z = zx + zy * 1j
			c = z
			for i in range(maxIt):
				if abs(z) > 2.0: break 
				z = z * z + c
			if (i == maxIt - 1):
				image.putpixel((x, y), (0, 0, 0))  # El nucleo del fractal en negro
			else:
				image.putpixel((x, y), getColorPaleta(paleta, nColoresPaleta, i))
	image.save(nombreFicheroPNG, "PNG")

@app.route('/')
def hello_world():
    return 'Hello, World!'

@app.route('/static')
def static_content():
    data = """<html xmlns="http://www.w3.org/1999/xhtml"> \
    <head> \
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> \
        <title>Mantenimiento</title> \
        <style type="text/css"> \
            body, html  { height: 100%; }html, body, div, span, applet, object, iframe,/*h1,2, h3, h4, h5, h6,*/ p, blockquote, pre,a, abbr, acronym, address,del, dfn, em, font, img, ins, kbd, q, s, samp,small, strike, strong, sub, sup, tt, var,b, u, i, center,dl, dt, dd, ol, ul, li,fieldset, form, label, legend,table, caption, tbody, tfoot, thead, tr, th, td {margin: 0;padding: 0;border: 0;outline: 0;font-size: 100%;vertical-align: baseline;background: transparent;}body { line-height: 1; }ol, ul { list-style: none; }blockquote, q { quotes: none; }blockquote:before, blockquote:after, q:before, q:after { content: ''; content: none; }:focus { outline: 0; }del { text-decoration: line-through; }table {border-spacing: 0; } \
            .clear {clear: both;display: block;overflow: hidden;visibility: hidden;width: 0;height: 0;} \
            body{ \
                color: #e9e2ee; \
                font-size: 14px; \
                font-family: sans-serif; \
                line-height: 20px; \
                background: #55575c; \
                text-shadow: 0 1px 1px rgba(0,0,0,0.75); \
                -webkit-box-shadow: inset 0 0 300px rgba(0,0,0,0.5); \
                -moz-box-shadow: inset 0 0 300px rgba(0,0,0,0.5); \
                        box-shadow: inset 0 0 300px rgba(0,0,0,0.5); \
            } \
            h1{ \
                text-align: center;
                font-size: 40px;
            } \
            p{ \
                margin-bottom: 0.3em;
                text-align: center;\
            } \
        </style> \
        <link rel="stylesheet" href="style.css"> \
    </head> \
    <body> \
        <br>
        <h1>Mantenimeinto</h1> \
        <p>Volvemos en breve, estamos de mantenimiento, gracias por esperarnos</p> \
    </body> \
    </html>"""
    return data

@app.route('/var/<var>')
def var_name(var):
    return 'Value Var is:  %s' % var

@app.errorhandler(404)
def page_not_found(e):
    return 'Error 404 página no encontrada'

@app.route('/static-data')
def static_data():
    return send_file("/home/vagrant/src/static.html", mimetype='text/html')

@app.route('/random-image')
def dynamic_image_random():
    npaleta=randint(0,2)
    if (npaleta == 1):
        paleta = [(96,111,140), (80,55,0), (0,100,25)] #cambiar colores
    elif (npaleta == 2):
        paleta = [(0,0,0), (255,255,255)]
    else:
        paleta = [(255,0,0), (0,255,0), (0,0,255)]
    renderizaMandelbrotBonito(2.0, 2.0, -2.0, -2.0, 400, randint(50,200), "/home/vagrant/src/imagen.png", paleta, 6)
    return send_file("/home/vagrant/src/imagen.png", mimetype='image/png')

@app.route('/dynamic-image/<x1>/<y1>/<x2>/<y2>/<w>/<i>/<p>')
def dynamic_image(x1,y1,x2,y2,w,i,p):
    print ('This is the values of all vars in URL:')
    print ('X1: %s' % x1)
    print ('Y1: %s' % y1)
    print ('X2: %s' % x2)
    print ('Y2: %s' % y2)
    print ('Width: %s' % w)
    print ('Iterations: %s' % i)
    print ('Palette Nº: %s' % p)
    npaleta=int(p)
    if (npaleta == 1):
        paleta = [(96,111,140), (80,55,0), (0,100,25)] #cambiar colores
    elif (npaleta == 2):
        paleta = [(0,0,0), (255,255,255)]
    else:
        paleta = [(255,0,0), (0,255,0), (0,0,255)]
    #print (paleta)
    renderizaMandelbrotBonito(float (x1), float (y1), float (x2), float (y2), int (w), int (i), "/home/vagrant/src/imagen.png", paleta, 6)
    return send_file("/home/vagrant/src/imagen.png", mimetype='image/png')

if __name__ == '__main__':
    app.run(host='0.0.0.0')

