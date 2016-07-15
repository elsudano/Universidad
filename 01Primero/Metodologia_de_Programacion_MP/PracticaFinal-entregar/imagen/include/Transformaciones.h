 
/**
  * @file Transformaciones.h
  * @brief Fichero de cabeceras para definir todas las funciones de las diferentes Transformaciones.
  * 
  * Aqui es donde diseñaremos los diferentes algoritmos para las diferentes transformaciones
  * de esa manera podemos utilizar este modulo en varios programas sin tener que repetir
  * los mismos algoritmos en los diferentes programas.
  *
  */

#ifndef _TRANSFORMACIONES_H_
#define _TRANSFORMACIONES_H_
#include "transformacion.h"
#include "imagen.h" // Aquí es donde se encuentran las funciones para manejar las imagenes.

Transformacion negativo(); ///< Devuelve una transformacion que convierte cualquier imagen a su negativo.
Transformacion desplazar(int bits); ///< Devuelve una transformacion que desplaza la cantidad de bits indicada de una imagen.
Transformacion umbralizar(int cantidad); ///< Devuelve una transformacion que umbraliza cualquier imagen a partir de la cantidad que se especifique
Transformacion brillo(int valor); ///< Devuelve una transformacion que realza el brillo de cualquier imagen tantas unidades como indique el valor.

#endif 