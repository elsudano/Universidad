/*
 * Ejercicio1.cpp
 *
 *  Created on: 17/02/2014
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "examen1.h"

//int main(int argc,char *argv[]){
void examen1::ejecutar(){
	srand(time(0));
	Carta estaCarta;
	for (int j = 0; j < 30; j++){
		estaCarta.num = rand()%11;
		estaCarta.palo = rand()%3;
		miMontonDeCartas.InsertarCarta(estaCarta);
	}
	cout << "Se ha llenado el Montón de Cartas y ahora se va a barajar" << endl << endl;
	while (miMontonDeCartas.Size() != 0){
		cout << "Carta " << miMontonDeCartas.CogerCarta().num << " de " << miMontonDeCartas.CogerCarta().palo << endl;
		miMontonDeCartas.EliminarCarta();
	}
	miMontonDeCartas.Barajar();
	cout << "Se ha barajado el Montón de Cartas y ahora se va a coger la de arriba" << endl << endl;

}

