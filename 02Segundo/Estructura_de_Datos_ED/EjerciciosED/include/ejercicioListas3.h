/*
 * ejercicioListas3.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO3_LISTAS_H_
#define EJERCICIO3_LISTAS_H_

using namespace std;

class ejercicioListas3{
private:
	/**
	 *  @brief Invertir la lista "l"
	 * @param[in] l puntero a una lista
	 * @return la lista invertida
	 */
	template<typename T>
	list<T> inversa(const list<T> &l);

	/**
	 * @brief Muestra por pantalla el contenido de la lista "l"
	 * @param[in] puntero a una pila
	 */
	template<typename T>
	void mostrar(const list<T> l);

public:
	void ejecutar();
};

#endif /* EJERCICIO3_LISTAS_H_ */
