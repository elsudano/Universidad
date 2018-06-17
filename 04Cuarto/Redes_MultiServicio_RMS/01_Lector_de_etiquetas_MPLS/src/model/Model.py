#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""Modelo de datos del programa.

Con esto se pretende abstraer toda la logica del programa para que sea mucho,
mas fácil encontrar en donde se encuentra cada parte del programa.
"""
# para crear clases con padres e hijos
from abc import ABC, abstractmethod
import sys
#
class Model(ABC):
    """Clase controlador."""

    def __init__(self):
        """Constructor por defecto."""
        # para la represntación interactiva

    @abstractmethod
    def open_pcapng(self):
        pass
