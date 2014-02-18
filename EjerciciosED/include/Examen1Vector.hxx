/*
 * Examen1Vector.hxx
 *
 *  Created on: 17/02/2014
 *      Author: Carlos de la Torre
 */

#ifndef EXAMEN1VECTOR_HXX_
#define EXAMEN1VECTOR_HXX_
#include <vector>
/**
 * @brief
 */
template<typename T>
void MontonCartas<T>::Barajar() {
	int tam = this->miContenedor.size();
	T cont_tmp(tam);
	int pos;
	Carta carta_tmp;

	for (int i = 0; i < tam; i++){
		pos  = rand()%tam-1;
		carta_tmp.num = rand()%11;
		carta_tmp.palo = rand()%3;
		cont_tmp.at(pos) = carta_tmp;
	}
	this->miContenedor=cont_tmp;
}

/**
 * @brief
 */
template<typename T>
Carta MontonCartas<T>::CogerCarta() {
	return this->miContenedor.back();
}

/**
 * @brief
 */
template<typename T>
void MontonCartas<T>::EliminarCarta() {
	this->miContenedor.pop_back();
}

/**
 * @brief
 */
template<typename T>
void MontonCartas<T>::InsertarCarta(Carta c) {
	this->miContenedor.push_back(c);
}

/**
* @brief devuelve el tamaño del montón de cartas
*/
template<typename T>
int MontonCartas<T>::Size() {
	return this->miContenedor.size();
}

#endif /* EXAMEN1VECTOR_HXX_ */
