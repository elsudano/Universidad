#!/usr/bin/python
# -*- coding: UTF-8 -*-

from src.view_app.View import *
from src.controller.Controller import Controller, plt


class MainController(Controller):

    def back(self, event):
        # FIXME: implementar esta función para regresar al menu anterior
        pass

    def clean_all(self, event):
        plt.close("all")
        self._view.e_text1.delete(0, END)
        self._view.e_text2.delete(0, END)
        self._view.e_text3.delete(0, END)
        self._view.e_text4.delete(0, END)
        self._view.t_textarea1.delete(1.0, END)

    def genera_hex(self, event):
        self._view.e_text2.delete(0, END)
        self._view.e_text2.insert(0, self._model.random_hex_string(int(self._view.cb_select1.get())))

    def eje_1(self, event):
        plt.close("all")
        text = self._view.e_text1.get()
        string_n_bits = self._view.e_text2.get()
        b_bits = int(self._view.e_text3.get())
        result = self._model.first_function(text, b_bits, string_n_bits)
        self._view.t_textarea1.delete(1.0, END)
        self._view.t_textarea1.insert(INSERT, 'DATOS DE ENTRADA: \n')
        self._view.t_textarea1.tag_add("title_input", "1.0", "1.18")
        self._view.t_textarea1.tag_config("title_input", background="black", foreground="yellow")
        self._view.t_textarea1.insert(INSERT, 'Texto de Entrada: {0}\n'.format(text))
        self._view.t_textarea1.insert(INSERT, 'Cadena de N bits: {0}\n'.format(string_n_bits))
        self._view.t_textarea1.insert(INSERT, 'Número de Bits b: {0}\n\n'.format(b_bits))
        self._view.t_textarea1.insert(INSERT, 'DATOS DE SALIDA: \n')
        self._view.t_textarea1.tag_add("title_output", "6.0", "6.17")
        self._view.t_textarea1.tag_config("title_output", background="black", foreground="green")
        self._view.t_textarea1.insert(INSERT, 'ID: {0}\n'.format(result[0]))
        self._view.t_textarea1.insert(INSERT, 'Cadena X: {0}\n'.format(result[1]))
        self._view.t_textarea1.insert(INSERT, 'Valor del Hash: {0}\n'.format(result[2]))
        self._view.t_textarea1.insert(INSERT, 'Número de intentos: {0}\n'.format(result[3]))
        # Guardamos el ultimo hash utilizado para el siguiente ejercicio
        self._view.e_text4.delete(0, END)
        self._view.e_text4.insert(0, result[2])
        # self._view.t_textarea1.insert(INSERT, self._model.convert_hex_to_bin(result[2]))

    def eje_2(self, event):
        plt.close("all")
        text = self._view.e_text1.get()
        string_n_bits = self._view.e_text2.get()
        b_bits = int(self._view.e_text3.get())
        line_jump = 5
        num_of_block = 10
        result = self._model.first_function(text, b_bits, string_n_bits)
        self._view.t_textarea1.delete(1.0, END)
        self._view.t_textarea1.insert(INSERT, 'DATOS DE ENTRADA: \n')
        self._view.t_textarea1.tag_add("title_input", "1.0", "1.18")
        self._view.t_textarea1.tag_config("title_input", background="black", foreground="yellow")
        self._view.t_textarea1.insert(INSERT, 'Texto de Entrada: {0}\n'.format(text))
        self._view.t_textarea1.insert(INSERT, 'Cadena de N bits: {0}\n'.format(string_n_bits))
        self._view.t_textarea1.insert(INSERT, 'Número de Bits b: {0}\n\n'.format(b_bits))
        self._view.t_textarea1.insert(INSERT, 'DATOS DE SALIDA: \n')
        self._view.t_textarea1.tag_add("title_output", "6.0", "6.17")
        self._view.t_textarea1.tag_config("title_output", background="black", foreground="green")
        for i in range(num_of_block):
            result = self._model.first_function(text, b_bits, result[2])
            self._view.t_textarea1.insert(INSERT, '[BLOQUE {0}]: \n'.format(i))
            line = 7+(line_jump*i)
            begin = str(line) + '.0'
            end = str(line) + '.13'
            self._view.t_textarea1.tag_add("title_block", begin, end)
            self._view.t_textarea1.tag_config("title_block", background="darkgray", foreground="darkblue")
            self._view.t_textarea1.insert(INSERT, 'ID: {0}\n'.format(result[0]))
            self._view.t_textarea1.insert(INSERT, 'Cadena X: {0}\n'.format(result[1]))
            self._view.t_textarea1.insert(INSERT, 'Valor del Hash: {0}\n'.format(result[2]))
            self._view.t_textarea1.insert(INSERT, 'Número de intentos: {0}\n'.format(result[3]))
        # Guardamos el ultimo hash utilizado para el siguiente ejercicio
        self._view.e_text4.delete(0, END)
        self._view.e_text4.insert(0, result[2])
        # self._view.t_textarea1.insert(INSERT, self._model.convert_hex_to_bin(result[2]))

    def eje_3(self, event):
        plt.close("all")
        text = self._view.e_text1.get()
        string_n_bits = self._view.e_text2.get()
        b_bits = int(self._view.e_text3.get())
        line_jump = 5
        num_of_block = 10
        result = self._model.first_function(text, b_bits, string_n_bits)
        # Convertimos a bits la cadena recogida
        # last_hash_value_in_bits = self._model.convert_hex_to_bin(string_n_bits)
        # result = self._model.first_function_bin(text, b_bits, last_hash_value_in_bits)
        self._view.t_textarea1.delete(1.0, END)
        self._view.t_textarea1.insert(INSERT, 'DATOS DE ENTRADA: \n')
        self._view.t_textarea1.tag_add("title_input", "1.0", "1.18")
        self._view.t_textarea1.tag_config("title_input", background="black", foreground="yellow")
        self._view.t_textarea1.insert(INSERT, 'Texto de Entrada: {0}\n'.format(text))
        self._view.t_textarea1.insert(INSERT, 'Cadena de bits: {0}\n'.format(string_n_bits))
        # self._view.t_textarea1.insert(INSERT, 'Cadena de bits: {0}\n'.format(last_hash_value_in_bits))
        self._view.t_textarea1.insert(INSERT, 'Número de Bits b: {0}\n\n'.format(b_bits))
        self._view.t_textarea1.insert(INSERT, 'DATOS DE SALIDA: \n')
        self._view.t_textarea1.tag_add("title_output", "6.0", "6.17")
        self._view.t_textarea1.tag_config("title_output", background="black", foreground="green")
        for i in range(num_of_block):
            result = self._model.first_function(text, b_bits, result[2])
            # result = self._model.first_function_bin(text, b_bits, result[2])
            self._view.t_textarea1.insert(INSERT, '[BLOQUE {0}]: \n'.format(i))
            line = 7 + (line_jump * i)
            begin = str(line) + '.0'
            end = str(line) + '.13'
            self._view.t_textarea1.tag_add("title_block", begin, end)
            self._view.t_textarea1.tag_config("title_block", background="darkgray", foreground="darkblue")
            self._view.t_textarea1.insert(INSERT, 'ID: {0}\n'.format(result[0]))
            self._view.t_textarea1.insert(INSERT, 'Cadena X: {0}\n'.format(result[1]))
            self._view.t_textarea1.insert(INSERT, 'Valor del Hash: {0}\n'.format(result[2]))
            self._view.t_textarea1.insert(INSERT, 'Número de intentos: {0}\n'.format(result[3]))
        # Guardamos el ultimo hash utilizado para el siguiente ejercicio
        self._view.e_text4.delete(0, END)
        self._view.e_text4.insert(0, result[2])
        # self._view.t_textarea1.insert(INSERT, self._model.convert_hex_to_bin(result[2]))

    def eje_4(self, event):
        text = self._view.e_text1.get()
        string_n_bits = self._view.e_text2.get()
        b_bits = int(self._view.e_text3.get())
        line_jump = 5
        num_of_block = 10
        avrg = 0
        avrg_n_iter = []
        self._view.t_textarea1.delete(1.0, END)
        self._view.t_textarea1.insert(INSERT, 'DATOS DE ENTRADA: \n')
        self._view.t_textarea1.tag_add("title_input", "1.0", "1.18")
        self._view.t_textarea1.tag_config("title_input", background="black", foreground="yellow")
        self._view.t_textarea1.insert(INSERT, 'Texto de Entrada: {0}\n'.format(text))
        self._view.t_textarea1.insert(INSERT, 'Cadena de bits: {0}\n'.format(string_n_bits))
        self._view.t_textarea1.insert(INSERT, 'Número de Bits b: {0}\n\n'.format(b_bits))
        self._view.t_textarea1.insert(INSERT, 'DATOS DE SALIDA: \n')
        self._view.t_textarea1.tag_add("title_output", "6.0", "6.17")
        self._view.t_textarea1.tag_config("title_output", background="black", foreground="green")
        for j in range(b_bits):
            result = self._model.first_function(text, j, string_n_bits)
            line_bit = 8 + (line_jump * (num_of_block * j))
            begin_bit = str(line_bit) + '.0'
            end_bit = str(line_bit) + '.19'
            self._view.t_textarea1.tag_add("num_bit", begin_bit, end_bit)
            self._view.t_textarea1.tag_config("num_bit", background="black", foreground="red")
            self._view.t_textarea1.insert(INSERT, '[Número de bits {0}]\n'.format(j))
            for i in range(num_of_block):
                result = self._model.first_function(text, j, result[2])
                line = 9 + (line_jump * (i + num_of_block*j))
                begin = str(line) + '.0'
                end = str(line) + '.13'
                self._view.t_textarea1.insert(INSERT, '[BLOQUE {0}]: \n'.format(i + num_of_block * j))
                self._view.t_textarea1.tag_add("title_block", begin, end)
                self._view.t_textarea1.tag_config("title_block", background="darkgray", foreground="darkblue")
                self._view.t_textarea1.insert(INSERT, 'ID: {0}\n'.format(result[0]))
                self._view.t_textarea1.insert(INSERT, 'Cadena X: {0}\n'.format(result[1]))
                self._view.t_textarea1.insert(INSERT, 'Valor del Hash: {0}\n'.format(result[2]))
                self._view.t_textarea1.insert(INSERT, 'Número de intentos: {0}\n'.format(result[3]))
                avrg += result[3]
            # Cálculamos la media y la añadimos al array para pintarlo
            avrg_n_iter.append(avrg/num_of_block)
            # Guardamos el ultimo hash utilizado para el siguiente ejercicio
            self._view.e_text4.delete(0, END)
            self._view.e_text4.insert(0, result[2])
        plt.xlabel('Bits')
        plt.ylabel('Avrg Iteration')
        plt.grid(True)
        plt.plot(range(1, b_bits + 1), avrg_n_iter)
        plt.show()
        # self._view.t_textarea1.insert(INSERT, self._model.convert_hex_to_bin(result[2]))

    def eje_5(self, event):
        pass

    def eje_6(self, event):
        plt.close("all")
        text = self._view.e_text1.get()
        string_n_bits = self._view.e_text2.get()
        b_bits = int(self._view.e_text3.get())
        result = self._model.second_function(text, b_bits, string_n_bits)
        self._view.t_textarea1.delete(1.0, END)
        self._view.t_textarea1.insert(INSERT, 'DATOS DE ENTRADA: \n')
        self._view.t_textarea1.tag_add("title_input", "1.0", "1.18")
        self._view.t_textarea1.tag_config("title_input", background="black", foreground="yellow")
        self._view.t_textarea1.insert(INSERT, 'Texto de Entrada: {0}\n'.format(text))
        self._view.t_textarea1.insert(INSERT, 'Cadena de N bits: {0}\n'.format(string_n_bits))
        self._view.t_textarea1.insert(INSERT, 'Número de Bits b: {0}\n\n'.format(b_bits))
        self._view.t_textarea1.insert(INSERT, 'DATOS DE SALIDA: \n')
        self._view.t_textarea1.tag_add("title_output", "6.0", "6.17")
        self._view.t_textarea1.tag_config("title_output", background="black", foreground="green")
        self._view.t_textarea1.insert(INSERT, 'ID: {0}\n'.format(result[0]))
        self._view.t_textarea1.insert(INSERT, 'Cadena X: {0}\n'.format(result[1]))
        self._view.t_textarea1.insert(INSERT, 'Valor del Hash: {0}\n'.format(result[2]))
        self._view.t_textarea1.insert(INSERT, 'Número de intentos: {0}\n'.format(result[3]))
        # Guardamos el ultimo hash utilizado para el siguiente ejercicio
        self._view.e_text4.delete(0, END)
        self._view.e_text4.insert(0, result[2])
        # self._view.t_textarea1.insert(INSERT, self._model.convert_hex_to_bin(result[2]))

    def eje_7_a(self, event):
        plt.close("all")
        text = self._view.e_text1.get()
        string_n_bits = self._view.e_text2.get()
        b_bits = int(self._view.e_text3.get())
        line_jump = 5
        num_of_block = 10
        result = self._model.second_function(text, b_bits, string_n_bits)
        self._view.t_textarea1.delete(1.0, END)
        self._view.t_textarea1.insert(INSERT, 'DATOS DE ENTRADA: \n')
        self._view.t_textarea1.tag_add("title_input", "1.0", "1.18")
        self._view.t_textarea1.tag_config("title_input", background="black", foreground="yellow")
        self._view.t_textarea1.insert(INSERT, 'Texto de Entrada: {0}\n'.format(text))
        self._view.t_textarea1.insert(INSERT, 'Cadena de N bits: {0}\n'.format(string_n_bits))
        self._view.t_textarea1.insert(INSERT, 'Número de Bits b: {0}\n\n'.format(b_bits))
        self._view.t_textarea1.insert(INSERT, 'DATOS DE SALIDA: \n')
        self._view.t_textarea1.tag_add("title_output", "6.0", "6.17")
        self._view.t_textarea1.tag_config("title_output", background="black", foreground="green")
        for i in range(num_of_block):
            result = self._model.second_function(text, b_bits, result[2])
            self._view.t_textarea1.insert(INSERT, '[BLOQUE {0}]: \n'.format(i))
            line = 7+(line_jump*i)
            begin = str(line) + '.0'
            end = str(line) + '.13'
            self._view.t_textarea1.tag_add("title_block", begin, end)
            self._view.t_textarea1.tag_config("title_block", background="darkgray", foreground="darkblue")
            self._view.t_textarea1.insert(INSERT, 'ID: {0}\n'.format(result[0]))
            self._view.t_textarea1.insert(INSERT, 'Cadena X: {0}\n'.format(result[1]))
            self._view.t_textarea1.insert(INSERT, 'Valor del Hash: {0}\n'.format(result[2]))
            self._view.t_textarea1.insert(INSERT, 'Número de intentos: {0}\n'.format(result[3]))
        # Guardamos el ultimo hash utilizado para el siguiente ejercicio
        self._view.e_text4.delete(0, END)
        self._view.e_text4.insert(0, result[2])
        # self._view.t_textarea1.insert(INSERT, self._model.convert_hex_to_bin(result[2]))

    def eje_7_b(self, event):
        plt.close("all")
        text = self._view.e_text1.get()
        string_n_bits = self._view.e_text2.get()
        b_bits = int(self._view.e_text3.get())
        line_jump = 5
        num_of_block = 10
        result = self._model.second_function(text, b_bits, string_n_bits)
        # Convertimos a bits la cadena recogida
        # last_hash_value_in_bits = self._model.convert_hex_to_bin(string_n_bits)
        # result = self._model.second_function_bin(text, b_bits, last_hash_value_in_bits)
        self._view.t_textarea1.delete(1.0, END)
        self._view.t_textarea1.insert(INSERT, 'DATOS DE ENTRADA: \n')
        self._view.t_textarea1.tag_add("title_input", "1.0", "1.18")
        self._view.t_textarea1.tag_config("title_input", background="black", foreground="yellow")
        self._view.t_textarea1.insert(INSERT, 'Texto de Entrada: {0}\n'.format(text))
        self._view.t_textarea1.insert(INSERT, 'Cadena de bits: {0}\n'.format(string_n_bits))
        # self._view.t_textarea1.insert(INSERT, 'Cadena de bits: {0}\n'.format(last_hash_value_in_bits))
        self._view.t_textarea1.insert(INSERT, 'Número de Bits b: {0}\n\n'.format(b_bits))
        self._view.t_textarea1.insert(INSERT, 'DATOS DE SALIDA: \n')
        self._view.t_textarea1.tag_add("title_output", "6.0", "6.17")
        self._view.t_textarea1.tag_config("title_output", background="black", foreground="green")
        for i in range(num_of_block):
            result = self._model.second_function(text, b_bits, result[2])
            # result = self._model.second_function_bin(text, b_bits, result[2])
            self._view.t_textarea1.insert(INSERT, '[BLOQUE {0}]: \n'.format(i))
            line = 7 + (line_jump * i)
            begin = str(line) + '.0'
            end = str(line) + '.13'
            self._view.t_textarea1.tag_add("title_block", begin, end)
            self._view.t_textarea1.tag_config("title_block", background="darkgray", foreground="darkblue")
            self._view.t_textarea1.insert(INSERT, 'ID: {0}\n'.format(result[0]))
            self._view.t_textarea1.insert(INSERT, 'Cadena X: {0}\n'.format(result[1]))
            self._view.t_textarea1.insert(INSERT, 'Valor del Hash: {0}\n'.format(result[2]))
            self._view.t_textarea1.insert(INSERT, 'Número de intentos: {0}\n'.format(result[3]))
        # Guardamos el ultimo hash utilizado para el siguiente ejercicio
        self._view.e_text4.delete(0, END)
        self._view.e_text4.insert(0, result[2])
        # self._view.t_textarea1.insert(INSERT, self._model.convert_hex_to_bin(result[2]))

    def eje_7_c(self, event):
        text = self._view.e_text1.get()
        string_n_bits = self._view.e_text2.get()
        b_bits = int(self._view.e_text3.get())
        line_jump = 5
        num_of_block = 10
        avrg = 0
        avrg_n_iter = []
        self._view.t_textarea1.delete(1.0, END)
        self._view.t_textarea1.insert(INSERT, 'DATOS DE ENTRADA: \n')
        self._view.t_textarea1.tag_add("title_input", "1.0", "1.18")
        self._view.t_textarea1.tag_config("title_input", background="black", foreground="yellow")
        self._view.t_textarea1.insert(INSERT, 'Texto de Entrada: {0}\n'.format(text))
        self._view.t_textarea1.insert(INSERT, 'Cadena de bits: {0}\n'.format(string_n_bits))
        self._view.t_textarea1.insert(INSERT, 'Número de Bits b: {0}\n\n'.format(b_bits))
        self._view.t_textarea1.insert(INSERT, 'DATOS DE SALIDA: \n')
        self._view.t_textarea1.tag_add("title_output", "6.0", "6.17")
        self._view.t_textarea1.tag_config("title_output", background="black", foreground="green")
        for j in range(b_bits):
            result = self._model.second_function(text, j, string_n_bits)
            for i in range(num_of_block):
                result = self._model.second_function(text, b_bits, result[2])
                self._view.t_textarea1.insert(INSERT, '[BLOQUE {0}]: \n'.format(i + num_of_block*j))
                line = 7 + (line_jump * (i + num_of_block*j))
                begin = str(line) + '.0'
                end = str(line) + '.13'
                self._view.t_textarea1.tag_add("title_block", begin, end)
                self._view.t_textarea1.tag_config("title_block", background="darkgray", foreground="darkblue")
                self._view.t_textarea1.insert(INSERT, 'ID: {0}\n'.format(result[0]))
                self._view.t_textarea1.insert(INSERT, 'Cadena X: {0}\n'.format(result[1]))
                self._view.t_textarea1.insert(INSERT, 'Valor del Hash: {0}\n'.format(result[2]))
                self._view.t_textarea1.insert(INSERT, 'Número de intentos: {0}\n'.format(result[3]))
                avrg += result[3]
            # Cálculamos la media y la añadimos al array para pintarlo
            avrg_n_iter.append(avrg / num_of_block)
            # Guardamos el ultimo hash utilizado para el siguiente ejercicio
            self._view.e_text4.delete(0, END)
            self._view.e_text4.insert(0, result[2])
        plt.xlabel('Bits')
        plt.ylabel('Avrg Iteration')
        plt.grid(True)
        plt.plot(range(1, b_bits+1), avrg_n_iter)
        plt.show()
        # self._view.t_textarea1.insert(INSERT, self._model.convert_hex_to_bin(result[2]))

    def eje_8(self, event):
        pass
