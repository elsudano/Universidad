#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""Vista principal del programa.

Aqui es donde se pondrán los menus de la aplicación junto con los botones,
necesarios para que funcione la primera vista de la aplicación.
"""
from src.view_app.View import View, ttk

class RtpView(View):
    """Ventana que muestra todos los ejercicios que hay en las diapositivas del tema 1 de PDS UGR"""

    def _init_view(self):
        """Método de creación de vista.
        """
        # establecemos el tamaño de la ventana
        self._window.size(300, 120)
        # Creamos el marco
        self._principal_frame = ttk.Frame(self._window.get(), padding="3 3 12 12")
        # El marco está en la posición 0,0 de la ventana en el centro
        self._principal_frame.grid(column=0, row=0, sticky='NESW')
        # cantidad de columnas que tiene el marco
        self._principal_frame.columnconfigure(0, weight=1)
        # cantidad de filas que tiene el marco
        self._principal_frame.rowconfigure(0, weight=1)
        # crea bóton dentro de marco
        self.b_back = ttk.Button(self._principal_frame, text="Atras")
        # ponemos en la posición 0,0 y que se expanda a SurEste
        self.b_back.grid(column=6, row=6, sticky='SE')
        # agregamos el comando del bóton
        self.b_back.bind("<Button>", self._controller.back)
        # --------------------------------------------------------------------------------
        self.b_open = ttk.Button(self._principal_frame, text="Abrir fichero Wireshark")
        self.b_open.grid(column=0, row=0, sticky='NW')
        self.b_open.bind("<Button>", self._controller.open_pcapng)
        self.b_search = ttk.Button(self._principal_frame, text="Buscar Paquetes RTP")
        self.b_search.grid(column=0, row=1, sticky='NW')
        self.b_search.bind("<Button>", self._controller.search)
        self.b_view = ttk.Button(self._principal_frame, text="Mostrar valor PT")
        self.b_view.grid(column=0, row=2, sticky='NW')
        self.b_view.bind("<Button>", self._controller.view)