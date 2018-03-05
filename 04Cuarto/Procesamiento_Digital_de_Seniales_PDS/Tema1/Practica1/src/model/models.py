#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""Lista de modelos del programa.

En este fichero podemos encontrarnos todos los modelos,
que se pueden usar para nuestro programa.
"""

from src.model.model import Model
from pydub import AudioSegment as mix
from pydub.playback import play
import matplotlib.pyplot as plt
import numpy as np
import wave


class MainModel(Model):

    def hacer_algo(self):
        pass


class FirstModel(Model):

    def hacer_algo(self):
        pass

    def leer_wave(self, filename):
        spf = wave.open(filename, 'r')
        params = spf.getparams()
        framerate= params[2]
        x = spf.readframes(-1)
        x = np.fromstring(x, 'Int16')
        plt.title(filename)
        plt.plot(x)
        plt.show()
        return x, framerate

    def representa_espectrograma(self, x, NFFT, Fs, noverlap):
        Pxx, freqs, bins, im= plt.specgram(x, NFFT=NFFT, Fs=Fs, cmap='jet', noverlap=noverlap)
        plt.ylabel('Frequencia [Hz]')
        plt.xlabel('Tiempo [seg]')
        return Pxx, freqs

    def reproducir(self, filename):
        play(mix.from_wav(filename))

class SecondModel(Model):

    def hacer_algo(self):
        pass