/*
 * ejercicioListas6.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO6_LISTAS_H_
#define EJERCICIO6_LISTAS_H_

using namespace std;

class ejercicioListas6{
private:
	/**
	 *  @brief Invertir la lista "l"
	 * @param[in] l puntero a una lista 1
	 * @param[in] x numero entero al que se le restara 1
	 */
	void funcion(list<int> &l,int x);

	/**
	 * @brief Muestra por pantalla el contenido de la lista "l"
	 * @param[in] puntero a una pila
	 */
	void mostrar(const list<int> l);

public:
	void ejecutar();
};

#endif /* EJERCICIO6_LISTAS_H_ */
