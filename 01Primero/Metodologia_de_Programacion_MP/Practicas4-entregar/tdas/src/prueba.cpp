#include <iostream>
#include <stdlib.h>
#ifndef _IMAGEN_H_
#define _IMAGEN_H_
#include "imagen.h" // Aquí se define el valor de CUAL_COMPILO.
#endif

using namespace std;
  
int main(int argc, char *argv[]){
  int res,fil1,col1;
  Imagen imagen;
  res=fil1=col1=0;
  if (argc==3){
    if (LeerImagen(argv[1],imagen)){
      cout << "Terminó Leer" << endl;
      cout << "Filas: " << Filas(imagen) << endl;
      cout << "Columnas: " << Columnas(imagen) << endl;
      cout << "Imagen: ";
      for (int count=0;count<Filas(imagen);count++)
	for (int count2=0;count2<Columnas(imagen);count2++){
	  //cout << Get(imagen,count,count2) << " ";
	  Set (imagen,Filas(imagen),Columnas(imagen),Get(imagen,count,count2));
	}
      cout << endl << "Escribir: " << EscribirImagen(argv[2],imagen) << endl;
      cout << "Terminó escribir" << endl;
      Destruir(imagen);
      cout << "Terminó destruir" << endl;
    }else{
      cout << "Error nº 2 Tipo de Fichero incorrecto." 
	   << "O ..." << endl;
      res=2;
    }
  }else{
    cout << "Error nº 1 Parametros incorrectos." << endl
	 << " Usage: pueba <archivo de entrada> <archivo de salida>" << endl;
    res=1;
  }
  return res;
}