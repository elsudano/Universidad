#!/usr/bin/python
# -*- coding: UTF-8 -*-
"""Lista de controladores del programa.

En este fichero podemos encontrarnos todos los controladores,
de todas las vistas de nuestro programa.
"""

from src.controller.controller import *
from src.model.models import *
from src.view_app.views import *

class MainController(Controller):

    def back(self, event):
        # FIXME: implementar esta función para regresar al menu anterior
        pass

    def eje_teo_tema1(self, event):
        """Cambia la vista de la ventana.
        pasamos a crear todos los componentes para la primera practica
        """
        model = EjerciciosTema1Model()
        controller = EjerciciosTema1Controller(self._window, model)
        view = EjerciciosTema1View(self._window, controller)
        controller.set_view(view)
        view.init_view()

    def practica1(self, event):
        """Cambia la vista de la ventana.
        creamos todos los elementos para los ejercicios del tema 1
        que salen en las transparencias
        """
        model = Practica1Model()
        controller = FirstController(self._window, model)
        view = FirstView(self._window, controller)
        controller.set_view(view)
        view.init_view()

    def eje_teo_tema2(self, event):
        """Cambia la vista de la ventana.
        pasamos a crear todos los componentes para la primera practica
        """
        model = EjerciciosTema2Model()
        controller = FirstController(self._window, model)
        view = FirstView(self._window, controller)
        controller.set_view(view)
        view.init_view()

    def practica2(self, event):
        """Cambia la vista de la ventana.
        pasamos a crear todos los componentes para la segunda practica
        """
        model = SecondModel()
        controller = SecondController(self._window, model)
        view = SecondView(self._window, controller)
        controller.set_view(view)
        view.init_view()

class EjerciciosTema1Controller(Controller):
    """Gestión de eventos para la ventana de los ejercicios del tema 1
    Con esta controlaremos cada uno de los codigos que tenemos en las
    diapositivas del tema 1
    """
    def back(self, event):
        model = MainModel()
        controller = MainController(self._window, model)
        view = MainView(self._window, controller)
        view.init_view()

    def pag8(self, event):
        """El ejercicio que hay para mostrar y reproducir un fichero wav

        Ponemos un archivo por defecto pero si ese archivo no esta
        se abre una ventana en la que se solicita un fichero"""
        filename = Path('/home/usuario/nextCloud/Facultad/03_Procesamiento_Digital_de_Señales_PDS_5to/Ejemplos_Python/This_is_a_test.wav')
        if not filename.is_file():
            filename = Path(filedialog.askopenfilename(initialdir='$USER',
                                                  title="Selecciona el fichero a estudiar",
                                                  filetypes=(("Sound files", "*.wav"),
                                                             ("all files", "*.*"))
                                                  ))
        if filename:
            if filename.suffix == '.wav':
                self._model.reproducir(str(filename))
                plt, x, framerate = self._model.leer_wave(str(filename))
                plt, Pxx, freqs = self._model.representa_espectrograma(x, 256, framerate, 128)
                plt.show()
            elif len(filename) > 1:
                messagebox.showerror("Open file", "Cannot open this file\n(%s)" % filename)
                return

class FirstController(Controller):

    def back(self, event):
        model = MainModel()
        controller = MainController(self._window, model)
        view = MainView(self._window, controller)
        view.init_view()

    def open_wav_file(self, event):
        filename = filedialog.askopenfilename(initialdir='$USER',
                               title="Select file",
                               filetypes=(("Sound files", "*.wav"),
                                          ("all files", "*.*"))
                               )
        if filename:
            if filename.find('.wav') > 1:
                self._model.reproducir(filename)
                x, framerate = self._model.leer_wave(filename)
                self._model.representa_espectrograma(x, 256, framerate, 128)
            elif len(filename) > 1:
                messagebox.showerror("Open file", "Cannot open this file\n(%s)" % filename)
                return

class SecondController(Controller):

    def back(self, event):
        model = MainModel()
        controller = MainController(self._window, model)
        view = MainView(self._window, controller)
        view.init_view()

    def open_wav_file(self, event):
        pass