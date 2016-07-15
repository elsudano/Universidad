/**
  * @file transformar.h
  * @brief Fichero donde se encuentran las cabeceras de las funciones principales
  *
  * Incluye las cabeceras de las dos funciones principales para los programas
  * negativo y desplazar.
  *
  */

#ifndef _IMAGEN_H_
#define _IMAGEN_H_
#include "imagen.h" // Aquí se define el valor de CUAL_COMPILO.
#endif

/**
  * @brief Convertir la Imagen en su negativo.
  * 
  * Con esta función conseguiremos el negativo de la Imagen.
  * @param file_name_in Parametro de entrada, tipo de dato char[],
  * sirve para pasarle a la funcion el nombre de entrada
  * del fichero a convertir.
  * @param file_name_out Parametro de entrada, tipo de dato char[],
  * sirve para pasarle a la funcion el nombre de salida
  * del fichero convertido.
  * @return Devuelve verdadero si el proceso se realiza correctamente.
  * 
  */
bool negativo(const char *file_name_in, const char *file_name_out);

/**
  * @brief Desplazar el numero de bits de la Imagen.
  * 
  * Con esta función conseguiremos el negativo de la Imagen.
  * @param file_name_in Parametro de entrada, tipo de dato char[],
  * sirve para pasarle a la funcion el nombre de entrada
  * del fichero a convertir.
  * @param file_name_out Parametro de entrada, tipo de dato char[],
  * sirve para pasarle a la funcion el nombre de salida
  * del fichero convertido.
  * @param bits Parametro de entrada, tipo de dato int,
  * sirve para informar a la función de la cantidad de posiciones
  * a desplazar los bits en la imagen resultado.
  * @return Devuelve verdadero si el proceso se realiza correctamente.
  * 
  */
bool desplazar(const int& bits, const char *file_name_in, const char *file_name_out);