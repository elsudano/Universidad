/*
 * ejercicioListas8.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO8_LISTAS_H_
#define EJERCICIO8_LISTAS_H_

using namespace std;

class ejercicioListas8{
private:
	/**
	 *  @brief Invertir la lista "l"
	 * @param[in] l puntero a una lista 1
	 * @param[in] l puntero a una lista 2
	 * @return la con solo las entradas seleccionadas por la lista 2
	 */
	template<typename T1, typename T2>
	list<T1> buscar(const list<T1> &l, const list<T2> &li);

	/**
	 * @brief Muestra por pantalla el contenido de la lista "l"
	 * @param[in] puntero a una lista
	 */
	template<typename T>
	void mostrar(const list<T> l);

public:
	void ejecutar();
};

#endif /* EJERCICIO8_LISTAS_H_ */
