#!/usr/bin/python
# -*- coding: UTF-8 -*-

"""Menú principal de la aplicación.

Con este codigo se pretende llevar a cabo la primera practica de PDS
"""

import src.view_app.Window as w
from src.controller.Controllers import MainController
from src.model.Models import MainModel
from src.view_app.Views import MainView

if __name__ == '__main__':
    global DEBUG # Variable para depurar programa
    DEBUG = True
    # Creamos la Ventana
    myWindow = w("Ejercicios y Practicas de PDS", 400, 170)
    # Creamos el modelo
    myModel = MainModel()
    # Creamos el controlador de la vista principal y le añadimos la ventana y el modelo
    myController = MainController(myWindow, myModel)
    # Creamos la vista principal de la aplicación y le añadimos la ventana y el controlador
    myView = MainView(myWindow, myController)
    # añadimos la vista al controlador para la comunicación bidireccional
    myController.set_view(myView)
    # inicializamos la vista principal
    myView.init_view()
    # Hacemos que se muestre la ventana y hacemos funcionar el bucle principal
    myWindow.start()

