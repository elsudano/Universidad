/**
  * @file imagen.cpp
  * @brief Fichero de definiciones para insertar la implementación deseada
  *
  * Incluye la definición correspondiente dependiendo de la selección en imagen.h
  *
  */

#include "imagen.h" // Aquí se define el valor de CUAL_COMPILO

#if CUAL_COMPILO==1
  #include "imagen1.cpp"
#elif CUAL_COMPILO==2
  #include "imagen2.cpp"
#elif CUAL_COMPILO==3
  #include "imagen3.cpp"
#else
  #include "imagen4.cpp"
#endif
