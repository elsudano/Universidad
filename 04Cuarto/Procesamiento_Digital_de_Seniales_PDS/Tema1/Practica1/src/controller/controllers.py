#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""Lista de controladores del programa.

En este fichero podemos encontrarnos todos los controladores,
de todas las vistas de nuestro programa.
"""

from src.controller.controller import Controller
from src.model.models import *
from src.view_app.views import *


class MainController(Controller):

    def back(self, event):
        # FIXME: implementar esta función para regresar al menu anterior
        pass

    def practica1(self, event):
        """Cambia la vista de la ventana.
        pasamos a crear todos los componentes para la primera practica
        """
        model = FirstModel()
        controller = FirstController(self._window, model)
        view = FirstView(self._window, controller)
        view.init_view()

    def practica2(self, event):
        """Cambia la vista de la ventana.
        pasamos a crear todos los componentes para la segunda practica
        """
        model = SecondModel()
        controller = SecondController(self._window, model)
        view = SecondView(self._window, controller)
        view.init_view()

class FirstController(Controller):

    def back(self, event):
        model = MainModel()
        controller = MainController(self._window, model)
        view = MainView(self._window, controller)
        view.init_view()

    def open_wav_file(self, event):
        pass

class SecondController(Controller):

    def back(self, event):
        model = MainModel()
        controller = MainController(self._window, model)
        view = MainView(self._window, controller)
        view.init_view()

    def open_wav_file(self, event):
        pass