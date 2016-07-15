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
  int *p_maximo,*p_minimo;
  for (int i=0;i<MAXIMO;i++)
    vector[i]=rand() % MAXIMO + 1;
// Hasta aqui programamos el llenado del vector
//---------------------------------------------
  int *inicio=vector;
  while (vector!=inicio+MAXIMO){
    if (*vector>*p_maximo)
      p_maximo=vector;
    if (*vector<*p_minimo)
      p_minimo=vector;
    vector=vector++;
  }
  delete[] inicio;
// Hasta aqui programamos el metodo de Busqueda
  cout << "Maximo: " << *p_maximo << endl;
  cout << "Minimo: " << *p_minimo << endl;
// Hasta aqui pintamos los resultados
//----------------------------------------------
}