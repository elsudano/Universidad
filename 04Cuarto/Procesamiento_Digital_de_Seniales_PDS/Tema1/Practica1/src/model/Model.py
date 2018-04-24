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
#
import matplotlib
import matplotlib.pyplot as plt
import numpy
import wave
import sys

class Model(ABC):
    """Clase controlador."""

    def __init__(self):
        """Constructor por defecto."""
        # para la represntación interactiva
        plt.ion()

    @abstractmethod
    def hacer_algo(self):
        pass
