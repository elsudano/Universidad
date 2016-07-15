/*
 * ejercicioListas11.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO11_LISTAS_H_
#define EJERCICIO11_LISTAS_H_

using namespace std;

class ejercicioListas11{
private:
	/**
	* @brief "Comprime" una lista con muchos datos en una
	* que tiene los mismos datos pero ordenada de otra manera
	* @param[in] puntero a una lista del tipo de dato T
	* @return una lista de pares con la clave el tipo de
	* dato T y la cantidad de veces que se repite
	*/
	template<typename T>
	list<pair<T, int> > comprimir(const list<T> &l);

	/**
	* @brief "desComprime" una lista de pares con la clave
	* del tipo T, en otra lista que tiene los datos repetidos
	* @param[in] puntero a una lista del tipo de dato T
	* @return una lista con datos repetidos del tipo T
	*/
	template<typename T>
	list<T> descomprimir(const list<pair<T, int> > &lc);

	/**
	 * @brief Muestra por pantalla el contenido de la lista "l" que esta descomprimida
	 * @param[in] puntero a una lista
	 */
	template<typename T>
	void mostrarDesComprimida(const list<T> &l);

	/**
	 * @brief Muestra por pantalla el contenido de la lista "l" que esta comprimida
	 * @param[in] puntero a una lista
	 */
	template<typename T>
	void mostrarComprimida(const list<pair<T,int> > &l);
public:
	void ejecutar();
};

#endif /* EJERCICIO11_LISTAS_H_ */
