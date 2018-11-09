#!/usr/bin/python
# -*- coding: UTF-8 -*-

from PIL import Image
from flask import Flask, send_file

app = Flask(__name__)

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
    return 'static content'

@app.route('/var/<var>')
def var_name(var):
    return 'Value Var is:  %s' % var

@app.errorhandler(404)
def page_not_found(e):
    return 'Error 404 página no encontrada'

@app.route('/static-image')
def static_image():
    return 'dynamic image'

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

