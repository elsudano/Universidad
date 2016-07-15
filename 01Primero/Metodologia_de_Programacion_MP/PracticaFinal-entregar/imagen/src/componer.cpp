/**
  * @file componer.cpp
  * @brief Programa principal que sirve para guardar en disco una transformacion.
  *
  * Con este programa lo que conseguiremos que pasandole dos nombres
  * de dos transformaciones diferentes al final tengamos un tercer
  * fichero que contiene la suma de las dos transformaciones.
  * 
  */

#include <iostream>
#include "Transformaciones.h"

using namespace std;

int main(int argc,char *argv[]){
  if (argc==5){
    Transformacion trans1,trans2,trans_aux;
    if (trans1.LeerTrans(argv[2]))
      if (trans2.LeerTrans(argv[3])){
	trans_aux=trans1+trans2;
	trans_aux.Set_tipo(argv[1]);
	if (trans_aux.EscribirTrans(argv[4]))
	  cout << "Proceso realizado correctamente." << endl;
	else
	  cout << "Error Nº 4: No se ha podido guardar la Transformación." << endl;
      }else
	cout << "Error Nº 3: No se puede leer la segunda transformación." << endl;
    else
      cout << "Error Nº 2: No se puede leer la primera transformación." << endl;
  }else
    cout << "Error Nº 1: Parametros incorrectos." << endl << " Uso: componer <tipo_fichero_salida> <fichero_transformación_de_entrada_1> <fichero_transformación_de_entrada_2> <fichero_transformación_de_salida>" << endl;
}
