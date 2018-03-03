#!/usr/bin/python
# -*- coding: UTF-8 -*-

"""Menú principal de la aplicación.

Con este codigo se pretende llevar a cabo la primera practica de PDS
"""
from src.view_app.window import Window
from src.model.models import MainModel
from src.controller.controllers import MainController
from src.view_app.views import MainView

if __name__ == '__main__':
    # Creamos la Ventana
    myWindow = Window("Ventana Principal", 500, 300)
    # Creamos el modelo
    myModel = MainModel()
    # Creamos el controlador de la vista principal y le añadimos la ventana y el modelo
    myController = MainController(myWindow, myModel)
    # Creamos la vista principal de la aplicación y le añadimos la ventana y el controlador
    myView = MainView(myWindow, myController)
    # inicializamos la vista principal
    myView.init_view()
    # Hacemos que se muestre la ventana y hacemos funcionar el bucle principal
    myWindow.start()

