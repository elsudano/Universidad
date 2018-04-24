#!/usr/bin/python
# -*- coding: UTF-8 -*-

"""Lista de modelos del programa.

En este fichero podemos encontrarnos todos los modelos,
que se pueden usar para nuestro programa.
"""

from src.model.Model import Model, play, mix, wave, plt, numpy, matplotlib
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
        spf = wave.open(filename, 'r')
        params = spf.getparams()
        framerate = params[2]
        x = spf.readframes(-1)
        x = numpy.fromstring(x, 'Int16')
        t = numpy.arange(start=0, stop=1.0 * x.size / framerate, step=1. / framerate)
        plt.figure(1)
        plt.subplot(211)
        plt.title('Onda de:\n' + filename)
        plt.xlabel('Tiempo (seg)')
        plt.plot(x)
        spf.close()
        return plt, x, framerate

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

class Practica1Model(Model):
    """Primera practica de PDS

    Se intenta desarrollar en Matlab y en python diferentes filtros para el audio
    se implementa en Matlab una versión que produce eco en el sonido, gracias al retardo
    producido por un componente.
    Tambien se presentan los histogramas de los sonidos al digital el audio."""
    def hacer_algo(self):
        pass

    def leer_wave(self, filename):
        """Lee un fichero de audio y muestra en la salida los datos leidos en x
        y la frecuencia de muestreo a la que se ha grabado el audio

        Entrada:
        - filename, cadena de texto, ruta del fichero que queremos leer

        Salidas:
        - x, array, datos en bruto del fichero leido
        - framerate, entero, frecuencia de muestreo del audio"""
        spf = wave.open(filename, 'r')
        params = spf.getparams()
        framerate = params[2]
        x = spf.readframes(-1)
        x = numpy.fromstring(x, 'Int16')
        plt.figure(1, figsize=(9, 9))
        plt.subplot(211)
        plt.title('Onda de:\n' + filename)
        plt.ylabel('Amplitud')
        plt.xlabel('Tiempo [muestras]')
        plt.plot(x)
        return x, framerate

    def representa_espectrograma(self, x, NFFT, Fs, noverlap):
        """Representacion del espectograma de un fichero de audio

        Entradas:
        - x, array, datos de la señal
        - NFFT, int, la cantidad de puntos de datos utilizados en cada bloque para la Transformada de Furier.
        - Fs, long, frecuencia de muestreo
        - noverlap, int, el número de puntos de superposición entre bloques

        Salidas:
        - plt, ploteo del espectograma
        - Pxx, Datos del ploteo
        - freqs, Frecuencias que se ven en el espectograma"""
        plt.figure(1)
        plt.subplot(212)
        plt.title('Espectograma')
        plt.ylabel('Frequencia [Hz]')
        plt.xlabel('Tiempo [seg]')
        Pxx, freqs, bins, im = plt.specgram(x, NFFT=NFFT, Fs=Fs, cmap='jet', noverlap=noverlap)
        return plt, Pxx, freqs

    def reproducir(self, filename):
        """Función para reproducir el sonido que queremos

        prametros de entrada:
        filename: cadena de texto, fichero a reproducir."""
        play(mix.from_wav(filename))

class EjerciciosTema2Model(Model):

    def hacer_algo(self):
        pass

class Practica2Model(Model):
    """Se trata de la utilización de la transformada de Fourier

    Se trata de diseñar un programa que sea capaz de hacer mover una ventana a tráves de la onda
    de un audio y se vaya calculando dicha transformada en todo el espectro de la onda"""
    def hacer_algo(self):
        pass

    def leer_wave(self, filename):
        """Lee un fichero de audio y muestra en la salida los datos leidos en x
        y la frecuencia de muestreo a la que se ha grabado el audio

        Entrada:
        - filename, cadena de texto, ruta del fichero que queremos leer

        Salidas:
        - x, array, datos en bruto del fichero leido
        - framerate, entero, frecuencia de muestreo del audio"""
        spf = wave.open(filename, 'r')
        params = spf.getparams()
        framerate = params[2]
        x = spf.readframes(-1)
        x = numpy.fromstring(x, 'Int16')
        spf.close()
        return x, framerate

    def representa_espectrograma(self, x, NFFT, Fs, noverlap):
        """Representacion del espectograma de un fichero de audio

        Entradas:
        - x, array, datos de la señal
        - NFFT, int, la cantidad de puntos de datos utilizados en cada bloque para la Transformada de Furier.
        - Fs, long, frecuencia de muestreo
        - noverlap, int, el número de puntos de superposición entre bloques

        Salidas:
        - Pxx, Datos del ploteo
        - freqs, Frecuencias que se ven en el espectograma"""
        Pxx, freqs, bins, im = plt.specgram(x, NFFT=NFFT, Fs=Fs, cmap='jet', noverlap=noverlap)
        return Pxx, freqs

    def reproducir(self, filename):
        """Función para reproducir el sonido que queremos

        prametros de entrada:
        filename: cadena de texto, fichero a reproducir."""
        play(mix.from_wav(filename))

    def sin_signal(self, frequency, distance):
        """Genera una señal seno con los parametros necesarios
        Entradas:
        frecuency, int, la frecuencia que queremos dibujar
        distance, int, en el eje x desde se dibuja desde:
            distancia negativa hasta distancia positiva

        Salida:
        time, array, tiempo en el que se dibuja la señal
        sin_signal, array, valores del seno en cada instante de tiempo"""
        time = numpy.arange(-distance, distance, 0.1);
        sin_signal = (numpy.sin(frequency*time))/frequency
        return time, sin_signal

    def square_signal(self, frecuency, distance):
        """Genera una señal seno con los parametros necesarios
        Entradas:
        frecuency, int, la frecuencia que queremos dibujar
        distance, int, en el eje x desde se dibuja desde:
            distancia negativa hasta distancia positiva

        Salida:
        time, array, tiempo en el que se dibuja la señal
        square_signal, array, valores de señal en cada instante de tiempo"""
        x = numpy.arange(-distance, distance, 0.1)
        y = [random.choice([0, 1]) for i in x]

    def DTMF_encode(self, digits, framerate):
        data = [framerate]
        scale = 32767  # 16-bit unsigned short
        keys = '123A' \
               '456B' \
               '789C' \
               '*0#D'
        row_freq = [697, 770, 852, 941]
        col_freq = [1209, 1336, 1477, 1633]
        for digit in range(0,len(digits)):
            symbol = keys.index(digits[digit])
            if symbol > -1:
                row_freq_val = row_freq[symbol % 4]
                col_freq_val = col_freq[symbol % 4]
                for i in range(framerate):
                    time_tone = i * 200.0 / framerate
                    tone1 = numpy.sin(time_tone * row_freq_val * numpy.pi)
                    tone2 = numpy.sin(time_tone * col_freq_val * numpy.pi)
                    # fulltone = scale + tone1 + tone2
                    # data[i] = int(fulltone / 2 * scale)
        return data

    def DTMF_decode(self, tones):
        # TODO tienes que implementar la DTMF para decodificar
        digits = tones
        return digits

class OtherModel(Model):

    def hacer_algo(self):
        pass

    def bin_dec(self, num=0):
        """Función que combierte un numero binario en decimal

        prametros de entrada:
        num: cadena de texto, con el numero binario.

        parametros de salida:
        entero, el numero decimal convertido."""
        return int(num, 2)

    def pos(self, num=0):
        """Función que busca la posición de inicio de la lectura del texto

        párametros de entrada:
        num: entero, con el dividendo para el cálculo.

        párametros de salida:
        entero, la posición de comienzo de la lectura."""
        return int(num/1589)

    def text(self, string, pos):
        """Función que devuelve los 10 siguiente caracteres de la cadena
        que pasamos por párametros, a partir de la posición que indiquemos
        en el segundo párametro.

        párametros de entrada:
        str: cadena de texto, cadena que analizamos
        pos: entero, posición desde donde se empieza a analizar

        párametros de salida:
        cadena de texto, los 10 caracteres que están justo a continuación de pos."""

        return string[pos:pos+10]

class MainModel(Model):

    def hacer_algo(self):
        pass