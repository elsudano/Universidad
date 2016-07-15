/**
  * @file transformacion.cpp
  * @brief Fichero de definiciones para insertar la implementación deseada
  *
  * Permite modificar la implementación mediante un número del 1 a 2
  * Modificando la variable CUAL_COMPILO podremos compilar
  * de que manera usaremos nuestra representacion a la hora 
  * de tratar la imagen de entrada del programa.
  *
  */

#define CUAL_COMPILO 2

#if CUAL_COMPILO==1
  #include "transformacion1.cpp"
#else
  #include "transformacion2.cpp"
#endif  
