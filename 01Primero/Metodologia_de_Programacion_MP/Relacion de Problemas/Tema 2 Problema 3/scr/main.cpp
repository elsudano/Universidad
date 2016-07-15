#include <stdlib.h>
#include <time.h>
#include <iostream>

using namespace std;

int main (){
  /* Ponemos la Constante del MAXIMO */
  const int MAXIMO=100;
  /* Inicializamos la Semilla Aleatoria: */
  srand ( time(NULL) );
  /* Inicializamos la Semilla Aleatoria: */
  int *vector=new int[MAXIMO];
  for (int i=0;i<MAXIMO;i++)
    vector[i]=rand() % MAXIMO + 1;
// Hasta aqui programamos el llenado del vector
//---------------------------------------------

// Hasta aqui programamos el metodo de Invertir
//---------------------------------------------

// Hasta aqui pintamos los resultados
//----------------------------------------------
}