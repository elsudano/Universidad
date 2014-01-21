/*
 * ejercicioListas4.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO4_LISTAS_H_
#define EJERCICIO4_LISTAS_H_

using namespace std;

class ejercicioListas4{
private:
	/**
	 *  @brief Invertir la lista "l"
	 * @param[in] l puntero a una lista
	 * @return la lista invertida
	 */
	template<typename T>
	void inversa(list<T> &l);

	/**
	 * @brief Muestra por pantalla el contenido de la lista "l"
	 * @param[in] puntero a una pila
	 */
	template<typename T>
	void mostrar(const list<T> l);

public:
	void ejecutar();
};

#endif /* EJERCICIO4_LISTAS_H_ */
