/*
 * Examen2.h
 *
 *  Created on: 18/02/2014
 *      Author: Carlos de la Torre
 */

#ifndef EXAMEN2_H_
#define EXAMEN2_H_

using namespace std;

class examen2{
private:
	/**
	 * @brief muestra los datos de un multiset de enteros
	 */
	template<typename T>
	void mostrar(const multiset<T> &datos);

	/**
	 * @brief Esta es la funcion que se encarga de hacer la interseccion
	 */
	template<typename T>
	multiset<T> multi_interseccion(const multiset<T> &m1 , const multiset<T> &m2);
public:
	/**
	 * @brief Esta es la ejecución del programa
	 */
	void ejecutar();
};
#endif /* EXAMEN2_H_ */
