/*
 * Ejercicio1.cpp
 *
 *  Created on: 17/02/2014
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "Examen1.h"

//int main(int argc,char *argv[]){
void Examen1::ejecutar(){
	srand(time(0));
	Carta miCarta;
	for (int j = 0; j < 10; j++){
		miCarta.num = rand()%11;
		miCarta.palo = rand()%3;
		miMontonDeCartas.InsertarCarta(miCarta);
	}
	cout << "Se ha llenado el Montón de Cartas y ahora se va a barajar" << endl << endl;
	for (int z = 0; z < miMontonDeCartas.Size(); z++){
		cout << "Carta " << miMontonDeCartas.CogerCarta().num << " de " << miMontonDeCartas.CogerCarta().palo << endl;
	}
	miMontonDeCartas.Barajar();
	cout << "Se ha barajado el Montón de Cartas y ahora se va a coger la de arriba" << endl << endl;

}

