#!/usr/bin/python
# -*- coding: UTF-8 -*-

"""Lista de modelos del programa.

En este fichero podemos encontrarnos todos los modelos,
que se pueden usar para nuestro programa.
"""

from src.model.Model import Model
from src.view_app.View import img_as_float, exposure

class EjerciciosTema1Model(Model):
    """Funciones necesarias para los ejercicios del tema 1

    Estas funciones ayudan a la realización de los diferentes apartados
    de los ejemplos del tema 1"""

    def hacer_algo(self):
        pass

    def leer_wave(self, filename):
        """Lectura de un fichero de audio para representación gráfica

        - filename, cadena de texto, es la ruta donde se encuentra el fichero a analizar"""
        framerate, data = wavfile.read(filename)
        t = numpy.arange(start=0, stop=1.0 * data.size / framerate, step=1. / framerate)
        plt.figure(1)
        plt.subplot(211)
        plt.title('Onda de:\n' + filename)
        plt.xlabel('Tiempo (seg)')
        plt.plot(data)
        return plt, data, framerate

    def representa_espectrograma(self, x, NFFT, Fs, noverlap):
        """Representacion del espectograma de un fichero de audio

        - x, array, datos de la señal
        - NFFT, int, la cantidad de puntos de datos utilizados en cada bloque para la Transformada de Furier.
        - Fs, long, frecuencia de muestreo
        - noverlap, int, el número de puntos de superposición entre bloques"""
        plt.figure(1)
        plt.subplot(212)
        plt.title('Espectograma')
        plt.ylabel('Frequencia [Hz]')
        plt.xlabel('Tiempo [seg]')
        Pxx, freqs, bins, im = plt.specgram(x, NFFT=NFFT, Fs=Fs, cmap='jet', noverlap=noverlap)
        return plt, Pxx, freqs

    def reproducir(self, filename):
        """Reproducir un fichero de audio

        - filename, cadena de texto, es la ruta donde se encuentra el fichero a reproducir"""
        play(mix.from_wav(filename))

    def plot_img_and_hist(self, img, axes, bins=256):
        """Muestra la imagen y su histograma en una ventana con plot

        - img, array, datos en bruto de la imagen que queremos mostrar
        - axes, array, con los ejes en donde vamos a represntar la imagen
        - bins, int, número de contenedores para histograma de imagen"""
        img = img_as_float(img)
        ax_img, ax_hist = axes
        ax_cdf = ax_hist.twinx()
        # Visualización de imagen
        ax_img.imshow(img, cmap=plt.cm.gray)
        ax_img.set_axis_off()
        ax_img.set_adjustable('box-forced')

        # Visualización del histograma
        ax_hist.hist(img.ravel(), bins=bins, histtype='step', color='black')
        ax_hist.ticklabel_format(axis='y', style='scien-fic', scilimits=(0, 0))
        ax_hist.set_xlabel('Pixel intensity')
        ax_hist.set_xlim(0, 1)
        ax_hist.set_yticks([])

        # Visualización de la distribución acumulada
        img_cdf, bins = exposure.cumulative_distribution(img, bins)
        ax_cdf.plot(bins, img_cdf, 'r')
        ax_cdf.set_yticks([])

        return ax_img, ax_hist, ax_cdf

    def quantization(self, input, levels):
        """Cuantizacion uniforme de la señal

        - input, array, datos en bruto para analizar
        - levels, int, número de niveles de cuantización
        - output, array, datos en bruto de salida
        - q, Valor cuantizado (entre 0 y 2^log2(N)-1)
        """
        high = numpy.ceil(max(input))
        low = numpy.floor(min(input))
        qstep = (high-low)/levels
         # Paso de cuantización.
        q = numpy.floor((input-low)/qstep)
        low = low + qstep/2
        output = low + qstep*q
        return output,q

class MainModel(Model):

    def hacer_algo(self):
        pass