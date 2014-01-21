/*
 * ejercicioArboles1.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO1_ARBOLES_H_
#define EJERCICIO1_ARBOLES_H_


using namespace std;

class ejercicioArboles1{
private:
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
#endif /* EJERCICIO1_ATBOLES_H_ */
