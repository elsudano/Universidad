/**
  * @file revelar.cpp
  * @brief Fichero con definiciones para revelar mensajes de las imágenes
  *
  * Permite mostrar un Mensaje de una imagen que previamente ha sido tratada.
  *
  */

#include <iostream>
#include "codificar.h"

using namespace std;

/**
  * @brief Programa principal para revelar el mensaje.
  * Con este programa conseguiremos que nuestro mensaje de texto
  * oculto en la Imagen se muestre por pantalla.
  */
int main(){
  Codificar Imagen;
  Imagen.leer_datos('R');
  Imagen.revelar();
}
