#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""Vista principal del programa.

Aqui es donde se pondrán los menus de la aplicación junto con los botones,
necesarios para que funcione la primera vista de la aplicación.
"""

from src.view_app.View import *

class EjerciciosTema1View(View):
    """Ventana que muestra todos los ejercicios que hay en las diapositivas del tema 1 de PDS UGR"""

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
        self.b_back.grid(column=6, row=6, sticky='SE')
        # agregamos el comando del bóton
        self.b_back.bind("<Button>", self._controller.back)
        # --------------------------------------------------------------------------------
        self.b_pag8 = ttk.Button(self._principal_frame, text="Ejercicio Pagina 08")
        self.b_pag8.grid(column=0, row=0, sticky='NW')
        self.b_pag8.bind("<Button>", self._controller.pag8)
        self.b_pag13 = ttk.Button(self._principal_frame, text="Ejercicio Pagina 13")
        self.b_pag13.grid(column=0, row=1, sticky='NW')
        self.b_pag13.bind("<Button>", self._controller.pag13)
        self.b_pag15 = ttk.Button(self._principal_frame, text="Ejercicio Pagina 15")
        self.b_pag15.grid(column=0, row=2, sticky='NW')
        self.b_pag15.bind("<Button>", self._controller.pag15)
        self.b_pag18 = ttk.Button(self._principal_frame, text="Ejercicio Pagina 18")
        self.b_pag18.grid(column=0, row=3, sticky='NW')
        self.b_pag18.bind("<Button>", self._controller.pag18)
        self.b_pag56 = ttk.Button(self._principal_frame, text="Ejercicio Pagina 56")
        self.b_pag56.grid(column=6, row=0, sticky='NW')
        self.b_pag56.bind("<Button>", self._controller.pag56)

class EjerciciosTema2View(View):
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

class MainView(View):
    """Vista Principal del programa."""

    def _init_view(self):
        """Método de creación de vista.

        En este método es donde realmente se genera la vista de la,
        ventana pues es donde se crean todos los widgets y se colocán,
        en su lugar correspondiente.
        """
        # Creamos el marco
        self._principal_frame = ttk.Frame(self._window.get(), padding="3 3 12 12")
        # El marco está en la posición 0,0 de la ventana en el centro
        self._principal_frame.grid(column=0, row=0, sticky='N'+'S'+'E'+'W')
        # cantidad de columnas que tiene el marco
        self._principal_frame.columnconfigure(0, weight=1)
        # cantidad de filas que tiene el marco
        self._principal_frame.rowconfigure(0, weight=1)
        # crea bóton dentro de marco
        self.b_exit = ttk.Button(self._principal_frame, text="Exit")
        # ponemos en la posición 0,1 y que se expanda a SurEste
        self.b_exit.grid(column=2, row=12, sticky='SE')
        # agregamos el comando del bóton
        self.b_exit.bind("<Button>", self._controller.exit_application)
        # --------------------------------------------------------------------------------
        self.b_tema1 = ttk.Button(self._principal_frame, text="Ejercicios teoria tema 1")
        self.b_tema1.grid(column=0, row=0, sticky='NESW')
        self.b_tema1.bind("<Button>", self._controller.eje_teo_tema1)