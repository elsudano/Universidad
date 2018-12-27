#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""Vista principal del programa.

Aqui es donde se pondrán los menus de la aplicación junto con los botones,
necesarios para que funcione la primera vista de la aplicación.
"""

from src.view_app.View import *


class MainView(View):
    """Vista Principal del programa."""

    def _init_view(self):
        """Método de creación de vista.

        En este método es donde realmente se genera la vista de la,
        ventana pues es donde se crean todos los widgets y se colocán,
        en su lugar correspondiente.
        """
        # Creamos el marco
        try:
            self._principal_frame = ttk.Frame(self._window.get(), padding="3 3 12 12")
        except:
            self._principal_frame = ttk.Frame(self._window.get())
        # El marco está en la posición 0,0 de la ventana en el centro
        self._principal_frame.grid(column=0, row=0, sticky='N'+'S'+'E'+'W')
        # cantidad de columnas que tiene el marco
        self._principal_frame.columnconfigure(0, weight=1)
        # cantidad de filas que tiene el marco
        self._principal_frame.rowconfigure(0, weight=1)
        # crea bóton dentro de marco
        self.b_exit = ttk.Button(self._principal_frame, text="Exit")
        # ponemos en la posición 1,12 y que se expanda a SurEste
        self.b_exit.grid(column=1, columnspan=12, row=12, sticky='SE')
        # agregamos el comando del bóton
        self.b_exit.bind("<Button>", self._controller.exit_application)
        # --------------------------------------------------------------------------------
        self.l_title = ttk.Label(self._principal_frame, text="Generación de Blockchain", font=('Helvetica', 20, 'bold'))
        self.l_title.grid(column=0, row=0, columnspan=7, sticky='SN')
        try:
            self.g_group1 = ttk.Labelframe(self._principal_frame, text="Primeros datos de entrada:", borderwidth=4, padding=2)
        except:
            self.g_group1 = ttk.Frame(self._principal_frame, borderwidth=4)
        self.g_group1.grid(column=0, row=1, columnspan=7, sticky='NWE')
        try:
            self.l_text1 = ttk.Label(self.g_group1, text="Texto inicial:", font=('Arial', 12, 'bold'), padding=2)
        except:
            self.l_text1 = ttk.Label(self.g_group1, text="Texto inicial:", font=('Arial', 12, 'bold'))
        self.l_text1.grid(column=0, row=1, sticky='NW')
        self.e_text1 = ttk.Entry(self.g_group1, width=23)
        self.e_text1.grid(column=1, row=1, sticky='W')
        try:
            self.l_text3 = ttk.Label(self.g_group1, text="Número de bits b:", font=('Arial', 12, 'bold'), padding=4)
        except:
            self.l_text3 = ttk.Label(self.g_group1, text="Número de bits b:", font=('Arial', 12, 'bold'))
        self.l_text3.grid(column=2, row=1, sticky='NW')
        self.e_text3 = ttk.Entry(self.g_group1, width=18)
        self.e_text3.grid(column=3, row=1, sticky='W')
        try:
            self.l_select1 = ttk.Label(self.g_group1, text="Número de Bits Función Hash:", font=('Arial', 12, 'bold'), padding=4)
        except:
            self.l_select1 = ttk.Label(self.g_group1, text="Número de Bits Función Hash:", font=('Arial', 12, 'bold'))
        self.l_select1.grid(column=4, row=1, sticky='WE')
        try:
            self.cb_select1 = combo(self.g_group1, values=['256', '384', '512'], state="readonly")
        except:
            self.cb_select1 = ttk.Combobox(self.g_group1, values=['256', '384', '512'], state="readonly")
        self.cb_select1.current(0)
        self.cb_select1.grid(column=5, row=1, sticky='WE')
        self.b_boton1 = ttk.Button(self.g_group1, text="Generar")
        self.b_boton1.grid(column=6, row=1, sticky='E')
        self.b_boton1.bind("<Button>", self._controller.genera_hex)
        try:
            self.l_text2 = ttk.Label(self.g_group1, text="Cadena de n bits:", font=('Arial', 12, 'bold'), padding=4)
        except:
            self.l_text2 = ttk.Label(self.g_group1, text="Cadena de n bits:", font=('Arial', 12, 'bold'))
        self.l_text2.grid(column=0, row=2, sticky='NW')
        self.e_text2 = ttk.Entry(self.g_group1, width=125)
        self.e_text2.grid(column=1, columnspan=6, row=2, sticky='NW')
        try:
            self.l_text4 = ttk.Label(self.g_group1, text="Último valor Hash:", font=('Arial', 12, 'bold'), padding=4)
        except:
            self.l_text4 = ttk.Label(self.g_group1, text="Último valor Hash:", font=('Arial', 12, 'bold'))
        self.l_text4.grid(column=0, row=3, sticky='NW')
        self.e_text4 = ttk.Entry(self.g_group1, width=125)
        self.e_text4.grid(column=1, columnspan=6, row=3, sticky='NW')
        try:
            self.g_group2 = ttk.Labelframe(self._principal_frame, text='Resultados', borderwidth=4, padding=5)
        except:
            self.g_group2 = ttk.Frame(self._principal_frame)
        self.g_group2.grid(column=0, row=2, columnspan=7, sticky='NWE')
        try:
            self.t_textarea1 = textarea.ScrolledText(self.g_group2, width=157)
        except:
            self.t_textarea1 = textarea(self.g_group2, width=155)
        self.t_textarea1.grid(column=0, row=0, sticky='NWES')
        try:
            self.g_group3 = ttk.Labelframe(self._principal_frame, relief='flat', padding=10)
        except:
            self.g_group3 = ttk.Frame(self._principal_frame, relief='flat')
        self.g_group3.grid(column=0, row=3, columnspan=5, sticky='NWE')
        self.b_boton2 = ttk.Button(self.g_group3, text="1ª Función")
        self.b_boton2.grid(column=0, row=0, sticky='')
        self.b_boton2.bind("<Button>", self._controller.eje_1)
        self.b_boton3 = ttk.Button(self.g_group3, text="1ª BlockChain 1ª Función b=2")
        self.b_boton3.grid(column=1, row=0, sticky='')
        self.b_boton3.bind("<Button>", self._controller.eje_2)
        self.b_boton4 = ttk.Button(self.g_group3, text="2ª BlockChain 1ª Función b=3")
        self.b_boton4.grid(column=2, row=0, sticky='')
        self.b_boton4.bind("<Button>", self._controller.eje_3)
        self.b_boton5 = ttk.Button(self.g_group3, text="3ª BlockChain 1ª Función b<20")
        self.b_boton5.grid(column=3, row=0, sticky='')
        self.b_boton5.bind("<Button>", self._controller.eje_4)
        self.b_boton6 = ttk.Button(self.g_group3, text="2ª Función")
        self.b_boton6.grid(column=0, row=1, sticky='')
        self.b_boton6.bind("<Button>", self._controller.eje_5)
        self.b_boton7 = ttk.Button(self.g_group3, text="1ª BlockChain 2ª Función b=2")
        self.b_boton7.grid(column=1, row=1, sticky='')
        self.b_boton7.bind("<Button>", self._controller.eje_6)
        self.b_boton8 = ttk.Button(self.g_group3, text="2ª BlockChain 2ª Función b=3")
        self.b_boton8.grid(column=2, row=1, sticky='')
        self.b_boton8.bind("<Button>", self._controller.eje_7)
        self.b_boton9 = ttk.Button(self.g_group3, text="3ª BlockChain 2ª Función b<20")
        self.b_boton9.grid(column=3, row=1, sticky='')
        self.b_boton9.bind("<Button>", self._controller.eje_8)
