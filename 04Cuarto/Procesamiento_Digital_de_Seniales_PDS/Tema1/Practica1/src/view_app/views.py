#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""Vista principal del programa.

Aqui es donde se pondrán los menus de la aplicación junto con los botones,
necesarios para que funcione la primera vista de la aplicación.
"""
from src.view_app.view import *


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
        self.b_exit.grid(column=2, row=1, sticky='SE')
        # agregamos el comando del bóton
        self.b_exit.bind("<Button>", self._controller.exit_application)
        # --------------------------------------------------------------------------------
        self.b_tema1 = ttk.Button(self._principal_frame, text="Ejercicios teoria tema 1")
        self.b_tema1.grid(column=0, row=0, sticky='NW')
        self.b_tema1.bind("<Button>", self._controller.eje_teo_tema1)
        self.b_practica1 = ttk.Button(self._principal_frame, text="Practica 1")
        self.b_practica1.grid(column=1, row=0, sticky='NW')
        self.b_practica1.bind("<Button>", self._controller.practica1)
        self.b_tema2 = ttk.Button(self._principal_frame, text="Ejercicios teoria tema 2")
        self.b_tema2.grid(column=0, row=1, sticky='NW')
        self.b_tema2.bind("<Button>", self._controller.eje_teo_tema2)
        self.b_practica2 = ttk.Button(self._principal_frame, text="Practica 2")
        self.b_practica2.grid(column=1, row=1, sticky='NW')
        self.b_practica2.bind("<Button>", self._controller.practica2)

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
        self.b_back.grid(column=12, row=12, sticky='SE')
        # agregamos el comando del bóton
        self.b_back.bind("<Button>", self._controller.back)
        # --------------------------------------------------------------------------------
        self.b_pag8 = ttk.Button(self._principal_frame, text="Ejercicio Pagina 8")
        self.b_pag8.grid(column=0, row=0, sticky='NW')
        self.b_pag8.bind("<Button>", self._controller.pag8)

class FirstView(View):
    """Segunda Ventana del programa."""

    def _init_view(self):
        """Método de creación de vista.

        En este método es donde realmente se genera la vista de la,
        ventana pues es donde se crean todos los widgets y se colocán,
        en su lugar correspondiente.
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
        #self.e_file_name = ttk.Entry(self._principal_frame, width=39)
        #self.e_file_name.grid(column=0, row=0, sticky='NW')
        # FIXME: Estas lineas son para poder poner imagenes en los botones por eso no las borro
        #i_select_file = ImageTk.PhotoImage(Image.open('design/dark_style/i_select_file.png'))
        #self.b_select_file = ttk.Button(self._principal_frame, text="Select File", image=i_select_file)
        #self.b_select_file = ttk.Button(self._principal_frame, text="Select File")
        #self.b_select_file.image = i_select_file
        #self.b_select_file.grid(column=11, row=0, sticky='NE')
        #self.b_select_file.bind("<Button>", self._controller.select_file)
        #i_open_wav = ImageTk.PhotoImage(Image.open('design/dark_style/i_open_file.png'))
        #self.b_open_wav = ttk.Button(self._principal_frame, text="Open Wav File", image=i_open_wav)
        self.b_open_wav = ttk.Button(self._principal_frame, text="Open Wav File")
        #self.b_open_wav.image = i_open_wav
        self.b_open_wav.grid(column=0, row=11, sticky='SW')
        self.b_open_wav.bind("<Button>", self._controller.open_wav_file)

class SecondView(View):
    """Vista de Utilidades para la Practica."""

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
        self.b_back = ttk.Button(self._principal_frame, text="Atras")
        # ponemos en la posición 0,0 y que se expanda a SurEste
        self.b_back.grid(column=12, row=12, sticky='SE')
        # agregamos el comando del bóton
        self.b_back.bind("<Button>", self._controller.back)
        # --------------------------------------------------------------------------------
