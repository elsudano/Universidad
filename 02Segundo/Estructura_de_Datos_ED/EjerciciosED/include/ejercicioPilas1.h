/*
 * ejercicio1.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO1_H_
#define EJERCICIO1_H_
#include <stack>

using namespace std;

class ejercicio1{
private:
	/**
	 * @brief Variable que se usa para el guardar el tamaño
	 * de la pila
	 */
	int tam;

	/**
	 * @brief Función recursiva que cambia el valor "X" por
	 * el de "Y" en un una pila "P"
	 * @param[in] p puntero a una pila
	 * @param[in] x parametro A que es el origen
	 * @param[in] y parametro B que es el destino
	 */
	template<typename T>
	void f(stack<T> &p,const T x,const T y);

	/**
	 * @brief Muestra por pantalla el contenido de la pila "P"
	 * @param[in] puntero a una pila
	 */
	template<typename T>
	void Mostrar(stack<T> p);

public:
	/**
	 * @brief Esta es la ejecucion del programa
	 */
	void ejecutar();
};
#endif /* EJERCICIO1_H_ */
