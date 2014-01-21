/*
 * ejercicioListas5.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO5_LISTAS_H_
#define EJERCICIO5_LISTAS_H_

using namespace std;

class ejercicioListas5{
private:
	/**
	 *  @brief Invertir la lista "l"
	 * @param[in] l puntero a una lista 1
	 * @param[in] l puntero a una lista 2
	 * @return la lista mezclada de la unión de las dos listas de entrada
	 */
	template<typename T>
	list<T> mezclar(const list<T> &l1, const list<T> &l2);

	/**
	 * @brief Muestra por pantalla el contenido de la lista "l"
	 * @param[in] puntero a una pila
	 */
	template<typename T>
	void mostrar(const list<T> l);

public:
	void ejecutar();
};

#endif /* EJERCICIO5_LISTAS_H_ */
