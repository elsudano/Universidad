#include <iostream>
#include "pila_min.h"

using namespace std;

int main(){

  Pila_min<int> p;
  Pila_min<float> s;
  Pila_min<string> t;
  int i;
  
  for ( i=10; i>=0 ; i--) 
    p.poner(i);
  
  for ( i=0; i<=10 ; i++) 
    s.poner(i);
  
  string letras[] = {"b","B","C","D","A","E","F","Z","a"};
  for (i=0; i<=8; i++) 
    t.poner(letras[i]);
  
  Pila_min<string> copia(t);
  
  if (copia == t)
      cout << "Son iguales" << endl;
  else
      cout << "Son diferentes" << endl;
  
  while (!p.vacia() ){
    cout << "Pila P Tope: " << p.tope() << " Minimo: " << p.minimo() << endl;
    p.quitar();
  }     
  cout << endl << endl;
  while (!s.vacia() ){
    cout << "Pila S Tope: " << s.tope() << " Minimo: " << s.minimo() << endl;
    s.quitar();
  }
  cout << endl << endl;
  while (!t.vacia() ){
    cout << "Pila T Tope: " << t.tope() << " Minimo: " << t.minimo() << endl;
    t.quitar();
  }
  return 0;
}
