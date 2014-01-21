/*
 * ejercicio2.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioPilas9.h"

template<typename Z>
void ejercicio9::mostrar(ventana<Z> v){

}

//int main(int argc,char *argv[])
void ejercicio9::ejecutar()
{
   ventana<int> vent;
   cout << "Estamos en la posición " << vent.getPos() << " y la longitud de la ventana es: " << vent.size() << endl;
   for(int i=1; i<10; i++){
	   vent.inserta(i);
	   cout << "Insertamos en la posición " << vent.getPos() << " el valor: " << i << endl;
   }
   cout << "Nos movemos 3 veces a la izquierda" << endl;
   vent.movIzq();
   vent.movIzq();
   vent.movIzq();
   cout << "Estamos en la posición " << vent.getPos() << " y la longitud de la ventana es: " << vent.size() << endl;
   cout << "El valor de esta Posición es: " << vent.getVal() << endl;
   cout << "Nos movemos 2 veces a la derecha" << endl;
   vent.movDer();
   vent.movDer();
   cout << "Estamos en la posición " << vent.getPos() << " y la longitud de la ventana es: " << vent.size() << endl;
   cout << "El valor de esta Posición es: " << vent.getVal() << endl;
   cout << "Eliminamos la posición actual " << vent.getPos() << " y la longitud de la ventana es: " << vent.size() << endl;
   vent.elimina();
   cout << "El valor de esta Posición es: " << vent.getVal() << endl;
   cout << "Estamos en la posición " << vent.getPos() << " y la longitud de la ventana es: " << vent.size() << endl;
}
