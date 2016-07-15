/**
  * @file transformacion.h
  * @brief Fichero cabecera para insertar la implementación deseada
  *
  * Permite modificar la implementación mediante un número del 1 a 2
  * Modificando la variable CUAL_COMPILO podremos compilar
  * de que manera usaremos nuestra representacion a la hora 
  * de tratar la imagen de entrada del programa.
  * @param CUAL_COMPILO Con este parametro podemos seleccionar
  * cual sera el include que usaremos en nuestros programas
  * cada uno de los include tiene un tipo de representacion.
  * el primero representacion estatica y el segundo dinamica.
  */

#define CUAL_COMPILO 2 ///< Parametro de selección

#if CUAL_COMPILO==1
  #include "transformacion1.h"
#else
  #include "transformacion2.h"
#endif 
