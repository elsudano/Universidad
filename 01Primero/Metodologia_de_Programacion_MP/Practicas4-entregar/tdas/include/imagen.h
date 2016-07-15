/**
  * @file imagen.h
  * @brief Fichero cabecera para insertar la implementación deseada
  *
  * Permite modificar la implementación mediante un número del 1 a 4
  *
  */
#include <iostream>

using namespace std;
/**
  * @brief Variable de Compilación
  *
  * Modificando la variable CUAL_COMPILO podremos compilar
  * de una u otra manera a la hora de tratar la imagen de entrada del programa.
  *
  */
#define CUAL_COMPILO 4

#if CUAL_COMPILO==1
  #include "imagen1.h"
#elif CUAL_COMPILO==2
  #include "imagen2.h"
#elif CUAL_COMPILO==3
  #include "imagen3.h"
#else
  #include "imagen4.h"
#endif
