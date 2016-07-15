/*
 * comunes.h
 *
 *  Created on: 11/10/2013
 *      Author: Carlos de la Torre
 */

#ifndef COMUNES_H_
#define COMUNES_H_
#include <vector>

const int num_items = 1000000;			// Cantidad de Datos que vamos a Producir
const unsigned int tam_buff = 10;	// tamaño del buffer en memoria para pasar datos entre hebras


//----------------------------------------------------------------------
// devuelve un numero entero
int producir_dato();

//----------------------------------------------------------------------
// imprime por pantalla el numero entero que se le pasa a la variable dato
void consumir_dato( int dato );

#endif /* COMUNES_H_ */
