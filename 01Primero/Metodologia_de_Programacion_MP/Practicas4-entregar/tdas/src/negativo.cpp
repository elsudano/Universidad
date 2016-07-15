/**
  * @file negativo.cpp
  * @brief Fichero de definiciones para el programa principal de Negativo
  * 
  * Con este programa conseguiremos conseguir el negativo de la imagen que queramos
  * Trabaja con vectores de memoria dinamica y punteros.
  * 
  * @param <archivo_de_entrada> Este es el fichero a convertir.
  * @param <archivo_de_salida> Este es el fichero convertido.
  * 
  */

#include <iostream>
#include <stdlib.h>
#include "transformar.h"

using namespace std;

int main(int argc, char *argv[]){
  int res,fil,col;
  res=fil=col=0;
  if (argc==3){
    if (LeerTipoImagen(argv[1], fil, col)){
      if (negativo(argv[1],argv[2]))
	cout << "El proceso de cambio se ha realizado correctamente." << endl;
      else{
	cout << "Error nº 3 Error de Tipo desconocido." << endl;
	res=3;
      }
    }else{
      cout << "Error nº 2 Tipo de Fichero incorrecto." << endl;
      res=2;
    }
  }else{
    cout << "Error nº 1 Parametros incorrectos." << endl
	 << " Usage: negativo <archivo de entrada> <archivo de salida>" << endl;
    res=1;
  }
  return res;
}