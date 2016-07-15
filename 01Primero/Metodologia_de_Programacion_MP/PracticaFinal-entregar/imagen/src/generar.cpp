 
/**
  * @file generar.cpp
  * @brief Programa principal para generar los ficheros de transformaciones.
  *
  * Con este programa podremos generar los diferentes ficheros tanto binarios
  * como texto para poder utilizarlos luego para transformar imagenes.
  */

#include <iostream>
#include <stdlib.h>
#include <string.h>
#include "transformacion.h"
#include "Transformaciones.h"

using namespace std;

int main(int argc, char *argv[]){
  Transformacion trans;
  
  bool res=false;
  if (argc==4 && (!strcmp(argv[3],"negativo") || !strcmp(argv[3],"NEGATIVO"))){
    trans=negativo();
    trans.Set_tipo(argv[1]);
    if (trans.EscribirTrans(argv[2]))
      res=true;
  }else if (argc==5){
    if (!strcmp(argv[3],"desplazar") || !strcmp(argv[3],"DESPLAZAR")){
      trans=desplazar(atoi(argv[4]));
      trans.Set_tipo(argv[1]);
      if (trans.EscribirTrans(argv[2]))
	res=true;
    }else  if (!strcmp(argv[3],"umbralizar") || !strcmp(argv[3],"UMBRALIZAR")){
      trans=umbralizar(atoi(argv[4]));
      trans.Set_tipo(argv[1]);
      if (trans.EscribirTrans(argv[2]))
	res=true;
    }else  if (!strcmp(argv[3],"brillo") || !strcmp(argv[3],"BRILLO")){
      trans=brillo(atoi(argv[4]));
      trans.Set_tipo(argv[1]);
      if (trans.EscribirTrans(argv[2]))
	res=true;
    }else
      cout << " Error nº 2: La transformación escogida es incorrecta." << endl;
  }else
    cout << "Error nº 1 Parametros incorrectos." << endl << " Usage: generar <tipo_de_fichero> <fichero_transformación_de_salida> <tipo_de_transformación> <opcional_parametro_de_la_transformación>" << endl;
  if (res)
    cout << " Fichero creado correctamente. " << endl;
  else
    cout << " Error nº 3: No se puede escribir el fichero de transformación. " << endl;
}