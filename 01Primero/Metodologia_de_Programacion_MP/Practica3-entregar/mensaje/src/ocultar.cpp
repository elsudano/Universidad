/**
  * @file ocultar.cpp
  * @brief Fichero con definiciones para ocultar mensajes en las imágenes
  *
  * Permite ocultar un Mensaje en una imagen sin que esta se vea demaciado afectada.
  *
  */

#include <iostream>
#include "codificar.h"

using namespace std;

/**
  * @brief Programa principal para ocultar el mensaje.
  * Con este programa conseguiremos que nuestro mensaje de texto
  * no se pueda detectar a simple vista.
  */
int main(){
  Codificar Imagen;
  Imagen.leer_datos('O');
  Imagen.ocultar();
}
