/*
 * ejercicioArboles1.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO1_ARBOLES_H_
#define EJERCICIO1_ARBOLES_H_
#include "bintree.h"

using namespace std;

class ejercicioArboles1{
private:
	/**
	 * @brief Muestra por pantalla el contenido del arbol
	 */
	template<typename T>
	void mostrar(const bintree<T> &arb);

	/**
	 * @brief llena de datos un arbol dado hasta alcanzar los n nodos
	 */
	template<typename T>
	bintree<T> llenar(bintree<T> arbol, const vector<T> &datos);

	/**
	 * @brief Calcular la Altura del arbol
	 */
	template<typename T>
	int altura(const bintree<T> &arbol, const typename bintree<T>::node &nodo);
public:
	/**
	 * @brief Esta es la ejecución del programa
	 */
	void ejecutar();
};
#endif /* EJERCICIO1_ARBOLES_H_ */
