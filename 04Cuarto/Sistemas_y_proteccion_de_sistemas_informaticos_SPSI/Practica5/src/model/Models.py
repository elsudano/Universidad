#!/usr/bin/python
# -*- coding: UTF-8 -*-

from src.model.Model import Model
import hashlib
import random
import string


class MainModel(Model):

    def hacer_algo(self):
        pass

    @staticmethod
    def random_hex_string(bits=256):
        # Ejemplos de algoritmos que podemos usar sha3_256(), sha3_384(), sha3_512()
        hex_string = None
        chars = string.ascii_uppercase + string.digits
        size = random.randint(0, 10)
        rand_string = ''.join(random.choice(chars) for _ in range(size))
        if bits == 256:
            hex_string = hashlib.sha256(rand_string.encode('utf-8')).hexdigest()
        if bits == 384:
            hex_string = hashlib.sha384(rand_string.encode('utf-8')).hexdigest()
        if bits == 512:
            hex_string = hashlib.sha512(rand_string.encode('utf-8')).hexdigest()
        return str(hex_string)

    @staticmethod
    def convert_hex_to_bin(hex_string=0xFF):
        bits_of_string = len(hex_string)*4
        binary = bin(int(hex_string, 16))[2:].zfill(bits_of_string)
        return binary

    # for hexadecimal version
    def first_function(self, text, n_bits, string_n_bits):
        bits_of_string = len(string_n_bits)*4
        myid = string_n_bits+text
        hash_value = None
        count = 0
        while True:
            string_x = self.random_hex_string(bits_of_string)
            count += 1
            if bits_of_string == 256:
                hash_value = hashlib.sha256((myid + string_x).encode('utf-8')).hexdigest()
            if bits_of_string == 384:
                hash_value = hashlib.sha384((myid + string_x).encode('utf-8')).hexdigest()
            if bits_of_string == 512:
                hash_value = hashlib.sha512((myid + string_x).encode('utf-8')).hexdigest()
            # Justo aquí es donde miramos si la cadena tiene la cantidad de 0 que queremos
            if bits_of_string-int(hash_value, 16).bit_length() == n_bits:
                break
        return [myid, string_x, hash_value, count]

    # for binary version
    def second_function(self, text, n_bits, string_n_bits):
        bits_of_string = len(string_n_bits)
        myid = string_n_bits+text
        hash_value = None
        count = 0
        while True:
            string_x = self.convert_hex_to_bin(self.random_hex_string(bits_of_string))
            count += 1
            if bits_of_string == 256:
                hash_value = hashlib.sha256((myid + string_x).encode('utf-8')).hexdigest()
            if bits_of_string == 384:
                hash_value = hashlib.sha384((myid + string_x).encode('utf-8')).hexdigest()
            if bits_of_string == 512:
                hash_value = hashlib.sha512((myid + string_x).encode('utf-8')).hexdigest()
            # Justo aquí es donde miramos si la cadena tiene la cantidad de 0 que queremos
            if bits_of_string-int(hash_value, 16).bit_length() == n_bits:
                hash_value = self.convert_hex_to_bin(hash_value)
                break
        return [myid, string_x, hash_value, count]
