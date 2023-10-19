#!/usr/bin/python
# -*- coding: UTF-8 -*-

from PIL import Image
from flask import Flask, send_file
from random import randint
from pathlib import Path

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
        <p>/static --> En esta dirección lo que vamos a encontrar es una página generada staticamente</p>
        <p>/var/(nombre) --> En esta dirección nos saldrá un saludo personalizado en el cual sustituirá lo que pongamos en la URL en el lugar donde se encuentra (nombre) por un valor dado, es una forma de probar la entrada de datos a la aplicación</p>
        <p>/static-data --> En esta dirección lo que vamos a encontrar es una página estática pero esta vez será leida desde un fichero HTML</p>
        <p>/random-image --> Gnerá una imágen fractal y la muestra en el navegador</p>
        <p>/dynamic-image/(x1)/(y1)/(x2)/(y2)/(w)/(i)/(p) --> Gnerá una imágen fractal y la muestra en el navegador, pero en está ocasión hay que poner los párametros que queremos para la imágen, quedando de la siguiente manera los datos a pasar: coordenada x1, coordenada y1, coordenada x2, coordenada y2, ancho de la imágen, cantidad de itraciones para el fractal y paleta de colores</p>
        <br>
        <p>Por último si no encuentra la página que el usuario esta buscando, la aplicación mostrará una pagina de error personalizada</p>
    </body>
    </html>"""
    return data

@app.route('/static')
def static_content():
    data = """<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Mantenimiento</title>
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
                text-align: center;
            }
        </style>
    </head>
    <body>
        <br>
        <h1>Mantenimeinto</h1>
        <p>Volvemos en breve, estamos de mantenimiento, gracias por esperarnos</p>
    </body>
    </html>"""
    return data

@app.route('/var/<myvar>')
def var_name(myvar):
    data = ('<html xmlns="http://www.w3.org/1999/xhtml">'
    '<head>'
        '<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />'
        '<title>Entrada de Datos</title>'
       '<style type="text/css">'
            'body, html  { height: 100%; }html, body, div, span, applet, object, iframe,/*h1,2, h3, h4, h5, h6,*/ p, blockquote, pre,a, abbr, acronym, address,del, dfn, em, font, img, ins, kbd, q, s, samp,small, strike, strong, sub, sup, tt, var,b, u, i, center,dl, dt, dd, ol, ul, li,fieldset, form, label, legend,table, caption, tbody, tfoot, thead, tr, th, td {margin: 0;padding: 0;border: 0;outline: 0;font-size: 100%;vertical-align: baseline;background: transparent;}body { line-height: 1; }ol, ul { list-style: none; }blockquote, q { quotes: none; }blockquote:before, blockquote:after, q:before, q:after { content: ''; content: none; }:focus { outline: 0; }del { text-decoration: line-through; }table {border-spacing: 0; }'
            '.clear {clear: both;display: block;overflow: hidden;visibility: hidden;width: 0;height: 0;}'
            'body{'
                'color: #e9e2ee;'
                'font-size: 14px;'
                'font-family: sans-serif;'
                'line-height: 20px;'
                'background: #55575c;'
                'text-shadow: 0 1px 1px rgba(0,0,0,0.75);'
                '-webkit-box-shadow: inset 0 0 300px rgba(0,0,0,0.5);'
                '-moz-box-shadow: inset 0 0 300px rgba(0,0,0,0.5);'
                        'box-shadow: inset 0 0 300px rgba(0,0,0,0.5);'
            '}'
            'h1{'
                'text-align: center;'
                'font-size: 40px;'
            '}'
        '</style>'
    '</head>'
    '<body>'
        '<br>'
        f'<h1>Lo que he encontrado en la URL es: {myvar}</h1>'
    '</body>'
    '</html>')
    return data

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
    name_imagen = f"/home/vagrant/src/imagen_{npaleta}_{randint(0,100)}.png"
    renderizaMandelbrotBonito(2.0, 2.0, -2.0, -2.0, 400, randint(50,120), name_imagen, paleta, 6)
    return send_file(name_imagen, mimetype='image/png')

@app.route('/dynamic-image/<x1>/<y1>/<x2>/<y2>/<w>/<i>/<p>')
def dynamic_image(x1,y1,x2,y2,w,i,p):
    #print ('This is the values of all vars in URL:')
    #print ('X1: %s' % x1)
    #print ('Y1: %s' % y1)
    #print ('X2: %s' % x2)
    #print ('Y2: %s' % y2)
    #print ('Width: %s' % w)
    #print ('Iterations: %s' % i)
    #print ('Palette Nº: %s' % p)
    npaleta=int(p)
    if (npaleta == 1):
        paleta = [(96,111,140), (80,55,0), (0,100,25)] #cambiar colores
    elif (npaleta == 2):
        paleta = [(0,0,0), (255,255,255)]
    else:
        paleta = [(255,0,0), (0,255,0), (0,0,255)]
    #print (paleta)
    name_imagen = f"/home/vagrant/src/static/imgages/img_{x1}_{y1}_{x2}_{y2}_{w}_{i}_{p}.png"
    fname_imagen = Path(name_imagen)
    if not fname_imagen.exists():
        renderizaMandelbrotBonito(float (x1), float (y1), float (x2), float (y2), int (w), int (i), name_imagen, paleta, 6)
    return send_file(name_imagen, mimetype='image/png')

if __name__ == '__main__':
    app.run(host='0.0.0.0')

