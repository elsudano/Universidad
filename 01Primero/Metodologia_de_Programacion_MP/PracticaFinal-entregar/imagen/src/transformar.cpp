/**
  * @file transformar.cpp
  * @brief Programa principal para transformar una imagen a partir de un fichero.
  *
  * Con este programa conseguiremos que pasando 3 parametros podamos transformar
  * una imagen a partir de un fichero de transformacion ya sea binario o de texto
  * obteniendo el resultado en un tercer parametro que sera el fichero de la imagen transformada
  *
  */

#include <iostream>
#include "transformacion.h"

using namespace std;

int main(int argc, char *argv[]){
  if (argc==4){
    Imagen imagen;
    Transformacion trans;
    if (imagen.LeerImagen(argv[1])){
      if (trans.LeerTrans(argv[2]))
	for (int fil=0;fil<imagen.Filas();fil++)
	  for (int col=0;col<imagen.Columnas();col++){
	    imagen.Set(fil,col,char(trans.Get(int(imagen.Get(fil,col)))));
	    if (fil+1==imagen.Filas() && col+1==imagen.Columnas()){
	      if (imagen.EscribirImagen(argv[3]))
		cout << "Proceso realizado correctamente" << endl;
	      else
		cout << "Error nº 5 No se puede escribir en disco o Error en la Transformacion" << endl;
	    }
	  }
      else
	cout << "Error nº 3 Fichero de transformacion no encontrado o no valido." << endl;
    }else
      cout << "Error nº 2 Error al Leer el fichero de Imagen." << endl;
  }else
    cout << "Error nº 1 Parametros incorrectos." << endl << " Usage: transformar <fichero_imagen_de_entrada> <fichero_transformacion_de_entrada> <fichero_imagen_de_salida> " << endl;
}