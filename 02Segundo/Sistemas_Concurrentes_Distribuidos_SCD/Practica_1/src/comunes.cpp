/*
 * comunes.cpp
 *
 *  Created on: 14/10/2013
 *      Author: Carlos de la Torre
 */
#include <iostream>
#include <vector>

using namespace std;

int producir_dato(){
	static int contador = 1;
	return contador ++ ;
}

void consumir_dato( int dato ){
	cout << "dato recibido: " << dato << endl;
}
