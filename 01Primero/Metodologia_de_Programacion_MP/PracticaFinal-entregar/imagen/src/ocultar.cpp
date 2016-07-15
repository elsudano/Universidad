/**
  * @file ocultar.cpp
  * @brief Programa principal para ocultar el mensaje.
  *
  * Con este programa conseguiremos que nuestro mensaje de texto
  * pase inadvertido y algo mas seguro que si fuera texto plano.
  *
  */

#include <iostream>
#include "procesar.h"

using namespace std;

int main(int argc, char *argv[]){
  if (argc==3){
    Imagen imagen;
    if (imagen.LeerImagen(argv[1])){
      const int MAXTXT=imagen.Filas()*imagen.Columnas()/8;
      char *mensaje=new char[MAXTXT];
      if (LeerMensaje(mensaje,MAXTXT))
	if (ocultar(imagen,mensaje))
	  if (imagen.EscribirImagen(argv[2]))
	    cout << "Proceso realizado correctamente" << endl;
	  else
	    cout << "Error nº 5 No se puede escribir en disco" << endl;
	else
	  cout << "Error nº 4 Error al ocultar el Mensaje." << endl;
      else
	cout << "Error nº 3 Error al Leer el Mensaje" << endl;
    }else
      cout << "Error nº 2 Error al Leer el fichero de Imagen." << endl;
  }else
    cout << "Error nº 1 Parametros incorrectos." << endl << " Usage: ocultar <fichero_imagen_de_entrada> <fichero_imagen_de_salida> < <fichero_mensaje_ocultar.txt> " << endl;
}