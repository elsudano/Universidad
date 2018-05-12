#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""Modelo de datos del programa.

Con esto se pretende abstraer toda la logica del programa para que sea mucho,
mas fácil encontrar en donde se encuentra cada parte del programa.
"""
# para crear clases con padres e hijos
from abc import ABC, abstractmethod
# para la reproducción de sonidos
from pydub import AudioSegment as mix
from pydub.playback import play
# para calculos matematicos
from scipy import signal
from scipy.io import wavfile
# para valores random de las señales
import random
#
import struct
import matplotlib
import matplotlib.pyplot as plt
import matplotlib.patches as patches
import numpy
import sys
#
class Model(ABC):
    """Clase controlador."""

    def __init__(self):
        """Constructor por defecto."""
        # para la represntación interactiva
        plt.ion()

    @abstractmethod
    def hacer_algo(self):
        pass
