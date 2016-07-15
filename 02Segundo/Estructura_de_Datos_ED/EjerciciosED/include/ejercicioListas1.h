/*
 * ejercicio1.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO1_LISTAS_H_
#define EJERCICIO1_LISTAS_H_


using namespace std;

class ejercicioListas1{
private:
	/**
	 * @brief Función que elimina "x" de la pila "l"
	 * @param[in] l puntero a una lista
	 * @param[in] x parametro que especifica lo que se elimina
	 */
	template<typename T>
	void f(list<T> &l, T x);

	/**
	 * @brief Muestra por pantalla el contenido de la lista "l"
	 * @param[in] puntero a una pila
	 */
	template<typename T>
	void mostrar(const list<T> l);
public:
	/**
	 * @brief Esta es la ejecución del programa
	 */
	void ejecutar();
};
#endif /* EJERCICIO1_LISTAS_H_ */
