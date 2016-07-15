/**
  * @file desplazar.cpp
  * @brief Fichero de definiciones para el programa principal de desplazar
  * 
  * Con este programa conseguiremos mover todos los bits de menor peso un sitio hacia la izquierda
  *
  */

#include <iostream>
#include <stdlib.h>
#include "transformar.h"

using namespace std;

int main(int argc, char *argv[]){
  int res,fil,col;
  res=fil=col=0;
  if (argc==4){
    if (LeerTipoImagen(argv[2], fil, col)){
      if (desplazar(atoi(argv[1]),argv[2],argv[3]))
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
	 << " Usage: negativo <bits a desplazar> <archivo de entrada> <archivo de salida>" << endl;
    res=1;
  }
  return res;
}