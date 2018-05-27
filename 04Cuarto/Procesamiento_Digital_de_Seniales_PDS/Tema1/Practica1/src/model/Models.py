#!/usr/bin/python
# -*- coding: UTF-8 -*-

"""Lista de modelos del programa.

En este fichero podemos encontrarnos todos los modelos,
que se pueden usar para nuestro programa.
"""

from src.model.Model import Model, os, play, mix, wavfile, \
    plt, patches, numpy, matplotlib, signal, random, \
    struct, imread, imsave, imresize, imrotate, imshow
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
        qstep = (high - low) / levels
        # Paso de cuantización.
        q = numpy.floor((input - low) / qstep)
        low = low + qstep / 2
        output = low + qstep * q
        return output, q


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
        framerate, data = wavfile.read(filename)
        plt.figure(1, figsize=(9, 9))
        plt.subplot(211)
        plt.title('Onda de:\n' + filename)
        plt.ylabel('Amplitud')
        plt.xlabel('Tiempo [muestras]')
        plt.plot(data)
        return data, framerate

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

    # digitos posibles para codificar
    _keys = '123A' \
            '456B' \
            '789C' \
            '*0#D'
    # frequencias de fila
    _row_freq = [697, 770, 852, 941]
    # frecuencias de columnas
    _col_freq = [1209, 1336, 1477, 1633]

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
        framerate, data = wavfile.read(filename)
        return data, framerate

    def escribir_wave(self, filename, framerate, data):
        """Escribe los datos en un fichero de audio y muestra en la salida
        si se ha grabado o no

        Entrada:
        - filename, cadena de texto, ruta del fichero que queremos leer
        -
        Salidas:
        - success, boolean, 1 todo Ok, 0 algo mal"""
        data_bytes = numpy.array(data)
        success = wavfile.write(filename, framerate, data_bytes)
        return success

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
        time = numpy.linspace(-distance, distance, endpoint=False)
        # time = numpy.arange(-distance, distance, 0.1);
        sin_signal = (numpy.sin(frequency * time)) / frequency
        return time, sin_signal

    def square_signal(self, frequency, distance):
        """Genera una señal seno con los parametros necesarios
        Entradas:
        frecuency, int, la frecuencia que queremos dibujar
        distance, int, en el eje x desde se dibuja desde:
            distancia negativa hasta distancia positiva

        Salida:
        time, array, tiempo en el que se dibuja la señal
        square_signal, array, valores de señal en cada instante de tiempo"""
        time = numpy.linspace(-distance, distance, 12 * distance, endpoint=False)
        square_signal = signal.square(frequency * time)
        return time, square_signal

    def DTMF_encode(self, digits, framerate, noise=False):
        """Función para codificar un digito dado como un tono de telefóno

        Entradas:
            digits, string, cadena de caracteres con los digitos a codificar
            framerate, entero, numero de muestras que se usaran por cada tono
            noise, boolean, genera la señal con ruido o sin el
        Salida:
            data, array, con los datos completos de todos los tonos

        Notas:
            Si cada tono tiene que tener el framerate especificado, se utilizara
            la mitad del framerate para los espacios entre tonos"""
        # amplitud de la señal
        amplitude = 1
        # cantidad de digitos a codificar
        digi_cant = len(digits)
        # tamaño del espacio
        space = int(framerate / 4)
        # vector con la información de la señal
        data = []

        for digit in range(digi_cant):
            pos_symbol = self._keys.index(digits[digit])
            if pos_symbol > -1:
                row_freq_val = self._row_freq[int(pos_symbol / 4)]
                col_freq_val = self._col_freq[pos_symbol % 4]
                # espacio en blanco
                for i in range(space):
                    tone3 = 0
                    if noise:
                        tone3 = random.random()
                    data.append(amplitude * tone3)
                # tono
                for frame in range(1000):
                    time = frame * (1.0 / framerate)
                    tone1 = numpy.sin(time * row_freq_val * 2 * numpy.pi)
                    tone2 = numpy.cos(time * col_freq_val * 2 * numpy.pi)
                    tone3 = 0
                    if noise:
                        tone3 = random.random()
                    data.append(amplitude * (tone1 + tone2 + tone3))
        # espacio en blanco del final
        for i in range(space):
            tone3 = 0
            if noise:
                tone3 = random.random()
            data.append(amplitude * tone3)
        return data

    def DTMF_decode(self, data, framerate):
        """Función para decodificar un sonido dado como un digito del telefóno
        Entradas:
            data, array, datos en bruto con el sonido
            framerate, entero, numero de muestras que se usaran por cada tono
        Salida:
            digits, string, con los digitos correspondientes al sonido
        """
        frame_length = 256  # Longitud de la frame.
        steps = 512  # Desplazamiento.
        freq_num = 256  # Número de frecuencias.
        threshold = 100  # Umbral de corte para la selección de frecuencias

        # Numero de ventanas (frames) en las que vamos a trocear los datos
        nframes = int((len(data) - frame_length) / steps) + 1
        # Vector de frecuencias posibles, según el framerate y el numero de frecuencias que queremos
        freq_list = numpy.linspace(0, framerate / 2, freq_num / 2 + 1)
        # frecuencia alta del tono
        freq_high = None
        # frecuencia baja del tono
        freq_low = None
        # digitos encontrados por sus frecuencias
        digits = ''
        for frame in range(nframes):
            # lista con la cantidad de datos definida por la ventana
            data_win = data[frame * steps:frame * steps + frame_length]
            # calculamos la fft a los datos anteriores
            fft_result = numpy.fft.fft(data_win)
            # calculamos el angulo para test nada mas
            # fft_angle = numpy.angle(fft_result)
            # calculamos el absolute a los datos anteriores
            fft_result = numpy.absolute(fft_result)
            # recorremos la lista de frecuencias posibles generadas
            for pos in range(len(freq_list)):
                # miramos si esta dentro del umbral
                if fft_result[pos] > threshold:
                    # guardamos la frecuencia alta y baja del tono
                    if freq_list[pos] > 1000:
                        freq_high = min(self._col_freq, key=lambda x: abs(x - freq_list[pos]))
                    else:
                        freq_low = min(self._row_freq, key=lambda x: abs(x - freq_list[pos]))
                    # cuando tenemos las dos frecuencias calculamos la posición del digito
                    if freq_high and freq_low:
                        # columnas mas filas multiplicado por 4 para la posición del digito
                        pos_digit = self._col_freq.index(freq_high) + (self._row_freq.index(freq_low) * 4)
                        digits = digits + self._keys[pos_digit]
        return digits


class EjerciciosTema3Model(Model):

    def hacer_algo(self):
        pass


class Practica3Model(Model):
    """Se trata la creación de filtros

    En esta practica se realizan los calculos a mano y utilizando diferentes
    herramientas de matlab para poder calcular los filtros a partir de unos
    coeficientes dados."""

    def hacer_algo(self):
        pass

    def make_signal_in(self, first_element, others_elements, size):
        x = [first_element]
        for i in range(1, size - 1):
            x.append(others_elements)
        return x

    def filter(self, signal_in, coef_a, coef_b):
        signal_out = signal.lfilter(coef_b, coef_a, signal_in)
        return signal_out

    def convolution(self, signal_in, coef_b):
        signal_out = signal.convolve(coef_b, signal_in, 'full')
        return signal_out


class EjerciciosTema4Model(Model):

    def hacer_algo(self):
        pass


class Practica4Model(Model):
    """Se trata de entender correctamente la transformada Z

    Para ello seguiremos utilizando los filtros y la convolución
    pero esta vez tenemos que pintar el diagrama de cero y polos"""

    def hacer_algo(self):
        pass

    def pulse(self, initial_steps, ending_steps):
        """Genera un array con los datos de una señal de pulso

        Entrada:
        - initial_steps, entero, numero entero desde donde comienzan
        los valores del array, con la cantidad especificada
        - ending_steps, entero, numero entero hasta donde llega el array
        note: todos estos valores serán de valor 0 menos el valor del
        pulso que será 1 en la posicion intermedia entre los dos valores
        antes dados.

        Salidas:
        - x, ndarray, array con todas las posiciones a 0 menos 1
        que se encuentra en initial_steps > pos > ending_esteps"""
        if (initial_steps == 0 or initial_steps == 1):
            initial_steps = 1
            ending_steps -= 1
        x = numpy.array(numpy.zeros(numpy.abs(initial_steps)))
        x[numpy.abs(initial_steps) - 1] = 1
        signal_out = numpy.concatenate((x, numpy.array(numpy.zeros(numpy.abs(ending_steps)))))
        return signal_out

    def unit_step(self, initial_steps, ending_steps):
        """Genera un array con los datos de una señal de escalon unitario

        Entrada:
        - initial_steps, entero, numero entero desde donde comienzan
        los valores del array, con la cantidad especificada
        - ending_steps, entero, numero entero hasta donde llega el array
        note: todos estos valores serán de valor 0 menos el valor del
        pulso que será 1 en la posicion intermedia entre los dos valores
        antes dados.

        Salidas:
        - x, ndarray, array con todas las posiciones a 0 menos 1
        que se encuentra en initial_steps > pos > ending_esteps"""
        x = numpy.array(numpy.zeros(numpy.abs(initial_steps)))
        x[numpy.abs(initial_steps) - 1] = 1
        signal_out = numpy.concatenate((x, numpy.array(numpy.ones(numpy.abs(ending_steps)))))
        return signal_out

    def filter(self, signal_in, coef_a, coef_b):
        signal_out = signal.lfilter(coef_b, coef_a, signal_in)
        return signal_out

    def convolution(self, signal_in, coef_b):
        signal_out = signal.convolve(coef_b, signal_in, 'full')
        return signal_out

    def impz(self, signal_in, a, b):
        impulse = numpy.repeat(0., len(signal_in))
        impulse[numpy.where(signal_in == 1)] = 1.
        response = signal.lfilter(b, a, impulse)
        return response

    def stepz(self, signal_in, a, b):
        step = numpy.repeat(0., len(signal_in))
        step[numpy.where(signal_in >= 0)] = 1.
        response = signal.lfilter(b, a, step)
        return response
        # plt.stem(n, response)
        # plt.ylabel('Amplitud')
        # plt.xlabel('n (muestras)')
        # plt.title('Respuesta al escalon')
        # plt.show()

    def zplane(self, a, b):
        # Fix axes
        ax = plt.gca()
        ax.spines['left'].set_position('zero')
        ax.spines['bottom'].set_position('zero')
        ax.spines['right'].set_visible(False)
        ax.spines['top'].set_visible(False)

        # Draw circle
        circle = patches.Circle((0, 0), radius=1, color='black', ls='dashed', fill=False)
        ax.add_patch(circle)

        # Make a and b of equal length
        a = numpy.append(a, numpy.repeat(0, max(len(a), len(b)) - len(a)))
        b = numpy.append(b, numpy.repeat(0, max(len(a), len(b)) - len(b)))

        # Plot poles
        p = numpy.roots(a)
        plt.plot(p.real, p.imag, 'kx', ms=7)

        # Plot zeros
        z = numpy.roots(b)
        plt.plot(z.real, z.imag, 'ko', ms=7)

        plt.axis('scaled')
        plt.show()

    def plot_freq_resp(self, a, b, worN=None):
        w, h = signal.freqz(b, a, worN)
        plt.plot(w, 20 * numpy.log10(abs(h)), 'b')
        plt.ylabel('Amplitud [dB]', color='b')
        plt.xlabel('Frecuencia [rad/muestra]')
        plt.gca().twinx()
        angles = numpy.unwrap(numpy.angle(h))
        plt.plot(w, angles, 'g')
        plt.ylabel('Fase (rad)', color='g')
        plt.title('Respuesta en frecuencia')
        plt.grid()
        plt.show()

    def plot_group_delay(self, a, b):
        w, gd = signal.group_delay((b, a))
        plt.plot(w, numpy.round(gd, 5))
        plt.ylabel('Retardo de grupo [muestras]')
        plt.xlabel('Frecuencia [rad/muestra]')
        plt.title('Retardo de grupo')
        plt.show()


class Practica5Model(Model):
    """Se trata de entender correctamente la creación de filtros

    Para ello se van a generar diferentes tipos de filtros con diferentes
    ventanas de actuación, que es la manera de utilizar la FFT rapida para
    poder realizar la creación de los mismos.
    """

    def hacer_algo(self):
        pass

    def pulse(self, initial_steps, ending_steps):
        """Genera un array con los datos de una señal de pulso

        Entrada:
        - initial_steps, entero, numero entero desde donde comienzan
        los valores del array, con la cantidad especificada
        - ending_steps, entero, numero entero hasta donde llega el array
        note: todos estos valores serán de valor 0 menos el valor del
        pulso que será 1 en la posicion intermedia entre los dos valores
        antes dados.

        Salidas:
        - x, ndarray, array con todas las posiciones a 0 menos 1
        que se encuentra en initial_steps > pos > ending_esteps"""
        if (initial_steps == 0 or initial_steps == 1):
            initial_steps = 1
            ending_steps -= 1
        x = numpy.array(numpy.zeros(numpy.abs(initial_steps)))
        x[numpy.abs(initial_steps) - 1] = 1
        signal_out = numpy.concatenate((x, numpy.array(numpy.zeros(numpy.abs(ending_steps)))))
        return signal_out

    def unit_step(self, initial_steps, ending_steps):
        """Genera un array con los datos de una señal de escalon unitario

        Entrada:
        - initial_steps, entero, numero entero desde donde comienzan
        los valores del array, con la cantidad especificada
        - ending_steps, entero, numero entero hasta donde llega el array
        note: todos estos valores serán de valor 0 menos el valor del
        pulso que será 1 en la posicion intermedia entre los dos valores
        antes dados.

        Salidas:
        - x, ndarray, array con todas las posiciones a 0 menos 1
        que se encuentra en initial_steps > pos > ending_esteps"""
        x = numpy.array(numpy.zeros(numpy.abs(initial_steps)))
        x[numpy.abs(initial_steps) - 1] = 1
        signal_out = numpy.concatenate((x, numpy.array(numpy.ones(numpy.abs(ending_steps)))))
        return signal_out

    def pass_low_FIR(self, Fs, Fc, num_of_samples=200):
        """Genera los 2 arrays que definen las frecuencias y las aplificaciones
           que tendrán cada una de ellas para aplicarlas a un filtro.

        Entrada:
        - Fs, entero, numero que indica la frcuencia de muestreo
        - FC, entero, numero que indica la frcuencia de corte

        Salidas:
        - freqs, ndarray, 200 frecuencias dentro del rango especificado
        - amps, ndarray, 200 valores con las amplificaciones de la señal"""
        freqs = numpy.linspace(0, Fs / 2, num_of_samples)
        for pos in range(0, len(freqs)):
            if (freqs[pos] >= Fc):
                num_of_ones = pos
                break
        ones = numpy.ones(num_of_ones, dtype=int)
        num_of_zeros = num_of_samples - num_of_ones
        zeros = numpy.zeros(num_of_zeros, dtype=int)
        amps = numpy.concatenate((ones, zeros))
        return freqs, amps

    def fir_win_rect(self, components, freqs, amps):
        from math import ceil, log
        if (len(freqs) != len(amps)):
            raise ValueError('El tamaño de los vectores tiene que ser igual')
        # Calculamos el numero de componentes en una escala logaritmica
        ncomps = 1 + 2 ** int(ceil(log(components, 2)))
        # Generamos la señal de entrada según el numero de componentes
        signal_in = numpy.linspace(0.0, freqs[-1], ncomps)
        # Sacamos los puntos interpolados según las frecuencias y la amplitud de cada punto
        signal_out = numpy.interp(signal_in, freqs, amps)
        # Ejecutamos la función que se refleja en la teoría y luego hacemos lo contrario del logaritmo
        shift = numpy.exp(-(components - 1) / 2. * 1.j * numpy.pi * signal_in / freqs[-1])
        # para obtener el resultado final multiplicamos la señal de salida por el resulado anterior
        signal_out2 = signal_out * shift
        # y aplicamos la transformada de furier inversa
        b = numpy.fft.irfft(signal_out2)
        return b

    def filter(self, signal_in, coef_a, coef_b):
        signal_out = signal.lfilter(coef_b, coef_a, signal_in)
        return signal_out

    def impz(self, signal_in, a, b):
        impulse = numpy.repeat(0., len(signal_in))
        impulse[numpy.where(signal_in == 1)] = 1.
        response = signal.lfilter(b, a, impulse)
        return response

    def stepz(self, signal_in, a, b):
        step = numpy.repeat(0., len(signal_in))
        step[numpy.where(signal_in >= 0)] = 1.
        response = signal.lfilter(b, a, step)
        return response

    def zplane(self, a, b):
        # Fix axes
        ax = plt.gca()
        ax.spines['left'].set_position('zero')
        ax.spines['bottom'].set_position('zero')
        ax.spines['right'].set_visible(False)
        ax.spines['top'].set_visible(False)
        # Draw circle
        circle = patches.Circle((0, 0), radius=1, color='black', ls='dashed', fill=False)
        ax.add_patch(circle)
        # Make a and b of equal length
        a = numpy.append(a, numpy.repeat(0, max(len(a), len(b)) - len(a)))
        b = numpy.append(b, numpy.repeat(0, max(len(a), len(b)) - len(b)))
        # Plot poles
        p = numpy.roots(a)
        plt.plot(p.real, p.imag, 'kx', ms=7)
        # Plot zeros
        z = numpy.roots(b)
        plt.plot(z.real, z.imag, 'ko', ms=7)
        plt.axis('scaled')
        plt.show()

    def plot_freq_resp(self, a, b, worN=None):
        w, h = signal.freqz(b, a, worN)
        plt.plot(w, 20 * numpy.log10(abs(h)), 'b')
        plt.ylabel('Amplitud [dB]', color='b')
        plt.xlabel('Frecuencia [rad/muestra]')
        plt.gca().twinx()
        angles = numpy.unwrap(numpy.angle(h))
        plt.plot(w, angles, 'g')
        plt.ylabel('Fase (rad)', color='g')
        plt.grid()
        plt.show()

    def plot_group_delay(self, a, b):
        w, gd = signal.group_delay((b, a))
        plt.plot(w, numpy.round(gd, 5))
        plt.ylabel('Retardo de grupo [muestras]')
        plt.xlabel('Frecuencia [rad/muestra]')
        plt.title('Retardo de grupo')
        plt.show()


class Practica6Model(Model):
    """Se intenta realizar los mismos filtros que la practica anterior pero en 2 dimensiones

    Aunque los principios son iguales hay que tener cuidadopara realizar los calculos en 2 dimensiones
    """

    def hacer_algo(self):
        pass

    def image_equalized_histogram(self, image, number_bins=256):
        # conseguimos el histograma de la imagen
        image_histogram, bins = numpy.histogram(image.flatten(), number_bins, normed=True)
        fda = image_histogram.cumsum()  # función de distribución acumulativa
        fda = 255 * fda / fda[-1]  # normalizamos
        # Usamos la interpolación lineal de fda para encontrar los nuevos valores de los pixeles
        image_equalized = numpy.interp(image.flatten(), bins[:-1], fda)
        return image_equalized.reshape(image.shape), fda

    def salt_and_pepper(self, image):
        # Detect if a signed image was input
        if image.min() < 0:
            low_clip = -1.
        else:
            low_clip = 0.

        out = image.copy()
        ###
        # amount : float
        #     Proportion of image pixels to replace with noise on range [0, 1].
        #     Used in 'salt', 'pepper', and 'salt & pepper'. Default : 0.05
        # salt_vs_pepper : float
        #     Proportion of salt vs. pepper noise for 's&p' on range [0, 1].
        #     Higher values represent more salt. Default : 0.5 (equal amounts)
        ###
        p = 0.15
        q = 0.7
        flipped = numpy.random.choice([True, False], size=image.shape, p=[p, 1 - p])
        salted = numpy.random.choice([True, False], size=image.shape, p=[q, 1 - q])
        peppered = ~salted
        out[flipped & salted] = 1
        out[flipped & peppered] = low_clip
        return out

    def linear_filter(self, image):
        pass

    def median_filter(self, image, matriz_size=3):
        if (matriz_size % 2) != 1:
            raise ValueError("El tamaño de la matriz tiene que ser impar.")
        if matriz_size < 3:
            raise ValueError("El tamaño minimo de la matriz de filtro es 3")
        pixels_mm = int((matriz_size - 1) / 2)
        nfil = image.shape[0]
        ncol = image.shape[1]
        filter_image = image.copy()
        matriz = numpy.zeros((matriz_size, matriz_size))
        for fil in range(nfil - pixels_mm):
            for col in range(ncol - pixels_mm):
                if (fil - pixels_mm >= 0 and col - pixels_mm >= 0):
                    ini_fil = fil - pixels_mm
                    end_fil = fil + pixels_mm + 1
                    ini_col = col - pixels_mm
                    end_col = col + pixels_mm + 1
                    matriz = image[ini_fil:end_fil, ini_col:end_col]
                    mediana = numpy.median(matriz)
                    filter_image[fil][col] = mediana
        return filter_image


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
        return int(num / 1589)

    def text(self, string, pos):
        """Función que devuelve los 10 siguiente caracteres de la cadena
        que pasamos por párametros, a partir de la posición que indiquemos
        en el segundo párametro.

        párametros de entrada:
        str: cadena de texto, cadena que analizamos
        pos: entero, posición desde donde se empieza a analizar

        párametros de salida:
        cadena de texto, los 10 caracteres que están justo a continuación de pos."""

        return string[pos:pos + 10]


class MainModel(Model):

    def hacer_algo(self):
        pass
