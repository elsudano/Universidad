#!/usr/bin/python
# -*- coding: UTF-8 -*-

"""Lista de modelos del programa.

En este fichero podemos encontrarnos todos los modelos,
que se pueden usar para nuestro programa.
"""

from src.model.Model import Model

class RtpModel(Model):

    def search(self, filename):
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

    def view(self, x, NFFT, Fs, noverlap):
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
