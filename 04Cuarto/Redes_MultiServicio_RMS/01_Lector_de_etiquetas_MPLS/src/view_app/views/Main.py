#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""Vista principal del programa.

Aqui es donde se pondrán los menus de la aplicación junto con los botones,
necesarios para que funcione la vista principal de la aplicación
"""
from src.view_app.View import View, ttk

class MainView(View):
    """Vista Principal del programa."""

    def _init_view(self):
        """Método de creación de vista.

        En este método es donde realmente se genera la vista de la,
        ventana pues es donde se crean todos los widgets y se colocán,
        en su lugar correspondiente.
        """
        # establecemos el tamaño de la ventana
        self._window.size(300, 110)
        # Creamos el marco
        self._principal_frame = ttk.Frame(self._window.get(), padding="3 3 12 12")
        # El marco está en la posición 0,0 de la ventana en el centro
        self._principal_frame.grid(column=0, row=0, sticky='N'+'S'+'E'+'W')
        # cantidad de columnas que tiene el marco
        self._principal_frame.columnconfigure(0, weight=1)
        # cantidad de filas que tiene el marco
        self._principal_frame.rowconfigure(0, weight=1)
        # crea bóton dentro de marco
        self.b_exit = ttk.Button(self._principal_frame, text="Salir")
        # ponemos en la posición 0,1 y que se expanda a SurEste
        self.b_exit.grid(column=0, columnspan=12, row=12, sticky='NE')
        # agregamos el comando del bóton
        self.b_exit.bind("<Button>", self._controller.exit_application)
        # --------------------------------------------------------------------------------
        self.b_mpls = ttk.Button(self._principal_frame, text="Capturar cabeceras MPLS")
        self.b_mpls.grid(column=0, row=0, sticky='NEW')
        self.b_mpls.bind("<Button>", self._controller.captura_mpls)
        self.b_arp = ttk.Button(self._principal_frame, text="Capturar paquetes RTP")
        self.b_arp.grid(column=0, row=1, sticky='NEW')
        self.b_arp.bind("<Button>", self._controller.captura_rtp)