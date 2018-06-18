#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""Lista de controladores del programa.

En este fichero podemos encontrarnos todos los controladores,
de todas las vistas de nuestro programa.
"""

from src.controller.Controller import Controller, Path, FileScanner
from src.model.models import *
from src.controller.controllers import *
from src.view_app.views import *

class RtpController(Controller):
    """Gestión de eventos para la ventana de los ejercicios del tema 1
    Con esta controlaremos cada uno de los codigos que tenemos en las
    diapositivas del tema 1
    """
    def back(self, event):
        model = MainModel()
        controller = MainController(self._window, model)
        view = MainView(self._window, controller)
        # Ponemos el tirulo a la nueva vista
        self._window.set_title("Lector de paquetes")
        view.init_view()

    def open_pcapng(self, event):
        filename = "/home/usuario/nextCloud/Facultad/02_Redes_MultiServicio_RMS-17-18/Trabajos_entregados/09_Identificar_fuentes_(servidores)_y_tipo_de_trafico/capture.pcapng"
        if not filename:
            filename = filediag.askopenfilename(initialdir='$USER',
                                                title="Selecciona el fichero a estudiar",
                                                filetypes=(("Network Captured files", "*.pcapng"),
                                                           ("all files", "*.*"))
                                                )
        filename = Path(filename)
        with open(filename, 'rb') as fp:
            scanner = FileScanner(fp)
            for block in scanner:
                print(block)

    def search(self, event):
        pass

    def view(self, event):
        pass