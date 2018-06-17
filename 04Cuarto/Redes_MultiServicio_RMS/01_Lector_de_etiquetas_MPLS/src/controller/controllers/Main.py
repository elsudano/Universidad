#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""Lista de controladores del programa.

En este fichero podemos encontrarnos todos los controladores,
de todas las vistas de nuestro programa.
"""

from src.controller.Controller import Controller
from src.model import models
from src.controller import controllers
from src.view_app import views

class MainController(Controller):

    def back(self, event):
        # FIXME: implementar esta función para regresar al menu anterior
        pass

    def open_pcapng(self, event):
        pass

    def captura_mpls(self, event):
        """Cambia la vista de la ventana.
        Creamos todos los componentes necesarios para realizar
        la captura de los paquetes MPLS y mostrarlos en pantalla
        """
        model = models.MplsModel()
        controller = controllers.MplsController(self._window, model)
        view = views.MplsView(self._window, controller)
        # Ponemos el tirulo a la nueva vista
        self._window.set_title("Lector cabecera MPLS")
        controller.set_view(view)
        view.init_view()

    def captura_rtp(self, event):
        """Cambia la vista de la ventana.
        pasamos a crear todos los componentes para la primera practica
        """
        model = models.RtpModel()
        controller = controllers.RtpController(self._window, model)
        view = views.RtpView(self._window, controller)
        # Ponemos el tirulo a la nueva vista
        self._window.set_title("Lector de RTP")
        controller.set_view(view)
        view.init_view()