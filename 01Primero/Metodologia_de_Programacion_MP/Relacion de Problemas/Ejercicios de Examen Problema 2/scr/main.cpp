#include <iostream>
#include <fstream>
#include "matriz.h"

using namespace std;

int main (){
  cout << "Linea 1: Matriz prueba1" << endl;
  Matriz prueba1;
  for (int f=0;f<prueba1.f();f++)
    for (int c=0;c<prueba1.c();c++)
      cout << "prueba1.Get(" << f << "," << c << ") " << prueba1.Get(f,c) << endl;
  prueba1.Set(3,3,5.6);
  cout << "Linea 3: Matriz prueba2(5,5);" << endl;
  Matriz prueba2(5,5);
  cout << "Linea 4: prueba1.Get(3,3)" << prueba1.Get(3,3) << endl;
  cout << "Linea 5: prueba2.Get(3,3)" << prueba2.Get(3,3) << endl;
  cout << "Linea 6: prueba1=prueba2" << endl;
  prueba1=prueba2;
  cout << "Linea 7: prueba1.Get(3,3)" << prueba1.Get(3,3) << endl;
  cout << "Linea 8: prueba2.Get(3,3)" << prueba2.Get(3,3) << endl;
  cout << "Linea 9: prueba1.Set(3,3,5.6)" << endl;
  prueba1.Set(3,3,5.6);
  cout << "Linea 10: prueba1.Get(3,3)" << prueba1.Get(3,3) << endl;
  cout << "Linea 11: for (int f=0;f<5;f++)" << endl;
  cout << "Linea 12: 	for (int f=0;f<5;f++)" << endl;
  for (int f=0;f<5;f++)
    for (int c=0;c<5;c++)
      cout << "prueba1.Get(" << f << "," << c << ") " << prueba1.Get(f,c) << endl;
  cout << "Linea 13: for (int f=0;f<5;f++)" << endl;
  cout << "Linea 14: 	for (int f=0;f<5;f++)" << endl;
  for (int f=0;f<5;f++)
    for (int c=0;c<5;c++)
      cout << "prueba2.Get(" << f << "," << c << ") " << prueba2.Get(f,c) << endl;
  /**cout << "Linea 13: " << << endl;
  cout << "Linea 14: " << << endl;
  cout << "Linea 15: " << << endl;
  cout << "Linea 16: " << << endl;
  cout << "Linea 17: " << << endl;
  cout << "Linea 18: " << << endl;
  cout << "Linea 19: " << << endl;
  cout << "Linea 20: " << << endl;
  cout << "Linea 21: " << << endl;
  cout << "Linea 22: " << << endl;
  cout << "Linea 23: " << << endl;**/
  cout << "Ha terminado el programa de la Matriz" << endl;
// Hasta aqui programamos el llenado del vector
//---------------------------------------------

// Hasta aqui programamos el metodo de Invertir
//---------------------------------------------

// Hasta aqui pintamos los resultados
//----------------------------------------------
}