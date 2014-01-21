/*
 * ejercicioListas2.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO2_LISTAS_H_
#define EJERCICIO2_LISTAS_H_

using namespace std;

class ejercicioListas2{
private:
	/**
	 * @brief Función que elimina "x" de la pila "l"
	 * @param[in] l puntero a una lista
	 * @param[in] x parámetro para comprobar que existe.
	 */
	template<typename T>
	bool esta(const list<T> &l,const T &x);
	/**
	 *  @brief Elimina los registros duplicados en la lista
	 * @param[in] l puntero a una lista
	 */
	template<typename T>
	void elimina_duplicados(list<T> &l);

	/**
	 * @brief Muestra por pantalla el contenido de la lista "l"
	 * @param[in] puntero a una pila
	 */
	template<typename T>
	void mostrar(const list<T> l);

public:
	void ejecutar();
};

#endif /* EJERCICIO2_LISTAS_H_ */
