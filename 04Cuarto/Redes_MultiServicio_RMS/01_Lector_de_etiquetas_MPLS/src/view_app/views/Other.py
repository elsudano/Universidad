#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""Vista principal del programa.

Aqui es donde se pondrán los menus de la aplicación junto con los botones,
necesarios para que funcione la primera vista de la aplicación.
"""
from src.view_app.View import View

class OtherView(View):
    """Ventana que muestra todos los ejercicios que hay en las diapositivas del tema 2 de PDS UGR"""

    def _init_view(self):
        """Método de creación de vista.
        """
        # Creamos el marco
        self._principal_frame = ttk.Frame(self._window.get(), padding="3 3 12 12")
        # El marco está en la posición 0,0 de la ventana en el centro
        self._principal_frame.grid(column=0, row=0, sticky='NESW')
        # cantidad de columnas que tiene el marco
        self._principal_frame.columnconfigure(0, weight=1)
        # cantidad de filas que tiene el marco
        self._principal_frame.rowconfigure(0, weight=1)
        # crea bóton dentro de marco
        self.b_back = ttk.Button(self._principal_frame, text="Back")
        # ponemos en la posición 0,0 y que se expanda a SurEste
        self.b_back.grid(column=12, row=12, sticky='SE')
        # agregamos el comando del bóton
        self.b_back.bind("<Button>", self._controller.back)
        # --------------------------------------------------------------------------------