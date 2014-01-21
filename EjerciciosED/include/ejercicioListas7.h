/*
 * ejercicioListas7.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO7_LISTAS_H_
#define EJERCICIO7_LISTAS_H_

using namespace std;

class ejercicioListas7{
private:
	/**
	 *  @brief Invertir la lista "l"
	 * @param[in] l puntero a una lista 1
	 * @param[in] l puntero a una lista 2
	 * @return la lista mezclada de la unión de las dos listas de entrada
	 */
	template<typename T>
	bool contenida(const list<T> &l1, const list<T> &l2);

	/**
	 * @brief Muestra por pantalla el contenido de la lista "l"
	 * @param[in] puntero a una pila
	 */
	void mostrar(const list<int> l);

public:
	void ejecutar();
};

#endif /* EJERCICIO7_LISTAS_H_ */
