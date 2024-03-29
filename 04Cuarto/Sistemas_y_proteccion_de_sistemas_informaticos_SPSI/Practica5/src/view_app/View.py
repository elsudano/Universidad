#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""Clase Base de las vistas del programa.

A partir de esta clase se crearán todas las vistas que tendnrá el programa
"""
# para diferenciar la versión de python
import sys
# para la interfaz gráfica
if sys.version_info.major == 2 and sys.version_info.minor == 7:
    import Tkinter as ttk
    from Tkinter import END, INSERT
    from Tkinter import Menu as menu
    from Tkinter import Listbox as combo
    import tkMessageBox as msgbox
    import tkFileDialog as filediag
    from Tkinter import Text as textarea
    from Tkinter import IntVar
    from Tkinter import BooleanVar
    from Tkinter import StringVar
elif sys.version_info.major == 3 and sys.version_info.minor == 7:
    # for python 3.x
    from tkinter import Menu as menu
    from tkinter import END, INSERT
    from tkinter import ttk
    from tkinter import messagebox as msgbox
    from tkinter import filedialog as filediag
    from tkinter import scrolledtext as textarea
    from tkinter import IntVar
    from tkinter import BooleanVar
    from tkinter import StringVar
# para crear clases con padres e hijos
import abc
import six


@six.add_metaclass(abc.ABCMeta)
class View:
    """Clase Vista Principal."""

    _window = None  # Tipo ventana y se crea vacío.
    _controller = None  # Tipo Controlador y se crea vacio
    _principal_frame = None  # Tipo Frame y se crea vacío, se aloja la parte principal de la ventana
    _menu_bar = None  # Esta es la barra de menus
    _menus = None  # Estos son los menus de la vista

    def __init__(self, window, controller):
        """Constructor por defecto.

        Parameters
        ----------
        window : Window
            Esta es una ventana donde se alojarán las diferentes vistas.
        """
        # Esto es para inicializar la Abstract Base Class
        super(View, self).__init__()
        self._window = window
        self._controller = controller
        self._menu_bar = menu(self._window.get())
        self._menus = list()
        self._window.get().config(menu=self._menu_bar)

    @abc.abstractmethod
    def _init_view(self):
        pass

    def _add_menu(self, name):
        """Añade un menú a la barra de menus"""
        menu_aux = menu(self._menu_bar)
        menu_aux.config(title=name)
        # FIXME: arreglar la inclusión de posicionamiento del menú
        # si queremos cambiar para que se pueda añadir un menu en la posicón
        # que queremos basta con poner un indice en los parametros de la
        # función y cambiar el indice por el len de la siguiente linea
        self._menus.insert(len(self._menus), menu_aux)

    def _add_item_menu(self, parent, name, command):
        """Añade una opción a los menus"""
        for menu in self._menus:
            if menu.cget('title') == parent:
                menu.add_command(label=name, command=command)
                # FIXME: arreglar la inclusión de posicionamiento del item del menú
                # menu.insert_command(0, label=name, command=command)
                # si queremos cambiar para que se pueda añadir una opción de menu
                # en la posicón que queremos basta con poner un indice en los
                # parámetros de la función y cambiar el indice de la función anterior
                # por el que se pasa por parámetros.

    def _add_menu_separator(self, name):
        """Añade un separador al menu"""
        for menu in self._menus:
            if menu.cget('title') == name:
                menu.add_separator()

    def init_view(self):
        """Muestra la vista en la ventana.

        Con este método conseguimos que se muestre la vista en la ventana,
        la cual se le ha pasado por párametros.
        """
        # aquí tienes que mirar el tamaño de la ventana y modificarlo para que se ajuste a la vista
        self._window.init_ui()
        # con lo siguiente generamos toda la barra de menus que se ha creado para la vista
        self._add_menu("Archivo")
        self._add_item_menu("Archivo", "Nuevo", self._controller.menu_item_new)
        self._add_item_menu("Archivo", "Abrir", self._controller.menu_item_open)
        self._add_item_menu("Archivo", "Guardar", self._controller.menu_item_save)
        self._add_menu_separator("Archivo")
        self._add_item_menu("Archivo", "Salir", self._controller.menu_item_exit)
        self._add_menu("Editar")
        self._add_item_menu("Editar", "Cortar", self._controller.menu_item_cut)
        self._add_item_menu("Editar", "Copiar", self._controller.menu_item_copy)
        self._add_item_menu("Editar", "Pegar", self._controller.menu_item_paste)
        self._add_menu("Ver")
        self._add_item_menu("Ver", "Barra de Herramientas", self._controller.menu_item_show_tools_bar)
        self._add_item_menu("Ver", "Barra de Estado", self._controller.menu_item_show_status_bar)
        self._add_menu("Herramientas")
        self._add_item_menu("Herramientas", "Utilidades", self._controller.menu_item_other)
        # Primero montamos los items genericos de la vista y luego montamos los de cada vista
        self._init_view()
        for menu in self._menus:
            self._menu_bar.add_cascade(label=menu.cget('title'), menu=menu)

    def __delete__(self, instance):
        """Con este metodo destruimos el frame principal de la vista."""
        self._principal_frame.destroy()
