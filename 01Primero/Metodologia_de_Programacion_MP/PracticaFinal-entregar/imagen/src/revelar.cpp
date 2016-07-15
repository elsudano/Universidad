/**
  * @file revelar.cpp
  * @brief Fichero con definiciones para revelar mensajes de las imágenes
  *
  * Permite mostrar un Mensaje de una imagen que previamente ha sido tratada.
  *
  */

#include <iostream>
#include "procesar.h"

using namespace std;

int main(int argc, char *argv[]){
  if (argc==2){
    Imagen imagen;
    if (imagen.LeerImagen(argv[1])){
      const int MAXTXT=((imagen.Filas()*imagen.Columnas())/8)+((imagen.Filas()*imagen.Columnas())%8);
      char *mensaje=new char [MAXTXT];
      if (revelar(imagen,mensaje)){
	cout << mensaje << endl;
	//cout << "Proceso realizado correctamente" << endl;
      }else
	cout << "Error nº 3 Error al revelar el Mensaje." << endl;
    }else
	cout << "Error nº 2 No se Ha podido Leer la Imagen." << endl;
  }else
      cout << "Error nº 1 Parametros incorrectos." << endl << " Usage: revelar <fichero_imagen_de_entrada> > <fichero_mensaje_revelado.txt> " << endl;
}