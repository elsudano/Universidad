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
        self.b_back = ttk.Button(self._principal_frame, text="Atras")
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
        self.b_back = ttk.Button(self._principal_frame, text="Atras")
        # ponemos en la posición 0,0 y que se expanda a SurEste
        self.b_back.grid(column=12, row=12, sticky='SE')
        # agregamos el comando del bóton
        self.b_back.bind("<Button>", self._controller.back)
        # --------------------------------------------------------------------------------

class Practica1View(View):
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
        self.b_back = ttk.Button(self._principal_frame, text="Atras")
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

class Practica2View(View):
    """Ventana que muestra los componente de la Prctica 2"""

    def _init_view(self):
        """Método de creación de vista.

        En este método es donde realmente se genera la vista de la,
        ventana pues es donde se crean todos los widgets y se colocán,
        en su lugar correspondiente.
        """
        # Modificamos el tamaño de la ventana en esta vista
        self._window.size(425,170)
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
        self.b_back.grid(column=12, row=12, sticky='SE')
        # agregamos el comando del bóton
        self.b_back.bind("<Button>", self._controller.back)
        # --------------------------------------------------------------------------------
        self.b_select_audio = ttk.Button(self._principal_frame, text="Select Audio File")
        self.b_select_audio.grid(column=4, row=0, sticky='NESW')
        self.b_select_audio.bind("<Button>", self._controller.select_audio_file)
        self._controller._noise_signal = BooleanVar()
        self.cb_noise_signal = ttk.Checkbutton(self._principal_frame, text="ruido en sonido", variable=self._controller._noise_signal)
        self.cb_noise_signal.grid(column=4, row=1, sticky='NESW')
        self.e_digits = ttk.Entry(self._principal_frame)
        self.e_digits.grid(column=4, row=2, sticky='NESW')
        self.b_encode = ttk.Button(self._principal_frame, text="Encode Digits")
        self.b_encode.grid(column=4, row=3, sticky='NESW')
        self.b_encode.bind("<Button>", self._controller.myencode)
        self.b_decode = ttk.Button(self._principal_frame, text="Decode Tones")
        self.b_decode.grid(column=4, row=4, sticky='NESW')
        self.b_decode.bind("<Button>", self._controller.mydecode)
        self.b_ejercicio2 = ttk.Button(self._principal_frame, text="Ejercicio 2")
        self.b_ejercicio2.grid(column=0, columnspan=4, row=0, sticky='NESW')
        self.b_ejercicio2.bind("<Button>", self._controller.ejercicio2)
        # inicializamos todas las variables para los checkbuttons
        for x in range(0, 8):
            self._controller._num_signal[x] = IntVar()
        self.cb_signal1 = ttk.Checkbutton(self._principal_frame, text="1ra", variable=self._controller._num_signal[0])
        self.cb_signal1.grid(column=0, row=1, sticky='E')
        self.cb_signal2 = ttk.Checkbutton(self._principal_frame, text="2da", variable=self._controller._num_signal[1])
        self.cb_signal2.grid(column=1, row=1, sticky='E')
        self.cb_signal3 = ttk.Checkbutton(self._principal_frame, text="3ra", variable=self._controller._num_signal[2])
        self.cb_signal3.grid(column=2, row=1, sticky='E')
        self.cb_signal4 = ttk.Checkbutton(self._principal_frame, text="4ta", variable=self._controller._num_signal[3])
        self.cb_signal4.grid(column=3, row=1, sticky='E')
        self.cb_signal5 = ttk.Checkbutton(self._principal_frame, text="5ta", variable=self._controller._num_signal[4])
        self.cb_signal5.grid(column=0, row=2, sticky='E')
        self.cb_signal6 = ttk.Checkbutton(self._principal_frame, text="6ta", variable=self._controller._num_signal[5])
        self.cb_signal6.grid(column=1, row=2, sticky='E')
        self.cb_signal7 = ttk.Checkbutton(self._principal_frame, text="7ma", variable=self._controller._num_signal[6])
        self.cb_signal7.grid(column=2, row=2, sticky='E')
        self.cb_signal8 = ttk.Checkbutton(self._principal_frame, text="8va", variable=self._controller._num_signal[7])
        self.cb_signal8.grid(column=3, row=2, sticky='E')
        self.b_ejercicio3 = ttk.Button(self._principal_frame, text="Ejercicio 3")
        self.b_ejercicio3.grid(column=0, columnspan=4, row=3, sticky='NESW')
        self.b_ejercicio3.bind("<Button>", self._controller.ejercicio3)

class Practica3View(View):
    """Ventana que muestra los componente de la Prctica 2"""

    def _init_view(self):
        """Método de creación de vista.

        En este método es donde realmente se genera la vista de la,
        ventana pues es donde se crean todos los widgets y se colocán,
        en su lugar correspondiente.
        """
        # Modificamos el tamaño de la ventana en esta vista
        #self._window.size(425,170)
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
        self.b_back.grid(column=12, row=12, sticky='SE')
        # agregamos el comando del bóton
        self.b_back.bind("<Button>", self._controller.back)
        # --------------------------------------------------------------------------------
        self.b_ejercicio1 = ttk.Button(self._principal_frame, text="IIR Punto 1")
        self.b_ejercicio1.grid(column=0, row=0, sticky='NESW')
        self.b_ejercicio1.bind("<Button>", self._controller.iir1)
        self.b_ejercicio2 = ttk.Button(self._principal_frame, text="IIR Punto 2")
        self.b_ejercicio2.grid(column=0, row=1, sticky='NESW')
        self.b_ejercicio2.bind("<Button>", self._controller.iir2)
        self.b_ejercicio3 = ttk.Button(self._principal_frame, text="IIR Punto 3")
        self.b_ejercicio3.grid(column=0, row=2, sticky='NESW')
        self.b_ejercicio3.bind("<Button>", self._controller.iir3)
        self.b_ejercicio4 = ttk.Button(self._principal_frame, text="IIR Punto 4")
        self.b_ejercicio4.grid(column=0, row=3, sticky='NESW')
        self.b_ejercicio4.bind("<Button>", self._controller.iir4)
        self.b_ejercicio5 = ttk.Button(self._principal_frame, text="IIR Punto 5")
        self.b_ejercicio5.grid(column=0, row=4, sticky='NESW')
        self.b_ejercicio5.bind("<Button>", self._controller.iir5)
        self.b_ejercicio6 = ttk.Button(self._principal_frame, text="FIR Punto 1")
        self.b_ejercicio6.grid(column=1, row=0, sticky='NESW')
        self.b_ejercicio6.bind("<Button>", self._controller.fir1)
        self.b_ejercicio7 = ttk.Button(self._principal_frame, text="FIR Punto 2")
        self.b_ejercicio7.grid(column=1, row=1, sticky='NESW')
        self.b_ejercicio7.bind("<Button>", self._controller.fir2)

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

class OtherView(View):
    """Vista de Utilidades para la resolución de los retos de hacking."""

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
        self.e_bin_num = ttk.Entry(self._principal_frame)
        self.e_bin_num.grid(column=0, row=0, sticky='NW')
        self.b_reto1 = ttk.Button(self._principal_frame, text="Reto numero binario y Poema")
        self.b_reto1.grid(column=0, row=1, sticky='NESW')
        self.b_reto1.bind("<Button>", self._controller.reto1)

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
        self.b_practica1 = ttk.Button(self._principal_frame, text="Practica 1")
        self.b_practica1.grid(column=1, row=0, sticky='NESW')
        self.b_practica1.bind("<Button>", self._controller.practica1)
        self.b_tema2 = ttk.Button(self._principal_frame, text="Ejercicios teoria tema 2")
        self.b_tema2.grid(column=0, row=1, sticky='NESW')
        self.b_tema2.bind("<Button>", self._controller.eje_teo_tema2)
        self.b_practica2 = ttk.Button(self._principal_frame, text="Practica 2")
        self.b_practica2.grid(column=1, row=1, sticky='NESW')
        self.b_practica2.bind("<Button>", self._controller.practica2)
        self.b_practica2 = ttk.Button(self._principal_frame, text="Practica 3")
        self.b_practica2.grid(column=1, row=3, sticky='NESW')
        self.b_practica2.bind("<Button>", self._controller.practica3)
        self.b_other = ttk.Button(self._principal_frame, text="Other Case")
        self.b_other.grid(column=0, row=3, sticky='NESW')
        self.b_other.bind("<Button>", self._controller.other)