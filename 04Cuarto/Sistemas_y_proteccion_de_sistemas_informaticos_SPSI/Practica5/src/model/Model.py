#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""Modelo de datos del programa.

Con esto se pretende abstraer toda la logica del programa para que sea mucho,
mas fácil encontrar en donde se encuentra cada parte del programa.
"""

# para crear clases con padres e hijos
import abc
import six
# para funciones hash
import hashlib
# para numeros aleatorios
import random
# para cadenas
import string


@six.add_metaclass(abc.ABCMeta)
class Model():
    """Clase controlador."""

    def __init__(self):
        """Constructor por defecto."""
        pass

    @abc.abstractmethod
    def hacer_algo(self):
        pass
