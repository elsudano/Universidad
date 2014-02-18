/*
 * Examen1Pila.hxx
 *
 *  Created on: 17/02/2014
 *      Author: Carlos de la Torre
 */

#ifndef EXAMEN1PILA_HXX_
#define EXAMEN1PILA_HXX_

/**
 * @brief Constructor por defecto
 */
template<typename T>
MontonCartas<T>::MontonCartas() {
	this->miContenedor = T();
	this->tam = 0;
}
/**
 * @brief
 */
template<typename T>
void MontonCartas<T>::Barajar() {

}

/**
 * @brief
 */
template<typename T>
Carta MontonCartas<T>::CogerCarta() {
	Carta laCarta = this->miContenedor.top();
	return laCarta;
}

/**
 * @brief
 */
template<typename T>
void MontonCartas<T>::EliminarCarta() {
	this->miContenedor.pop();
	this->tam -= 1;
}

/**
 * @brief
 */
template<typename T>
void MontonCartas<T>::InsertarCarta(Carta c) {
	this->miContenedor.push(c);
	this->tam += 1;
}

/**
* @brief devuelve el tamaño del montón de cartas
*/
template<typename T>
int MontonCartas<T>::Size() {
	return this->tam;
}

#endif /* EXAMEN1PILA_HXX_ */
