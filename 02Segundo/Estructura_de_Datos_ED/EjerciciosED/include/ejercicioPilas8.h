/*
 * ejercicio8.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO8_H_
#define EJERCICIO8_H_
#include<queue>

using namespace std;

class ejercicio8{
private:
	int tam;

	template<typename Q>
	void insertar(queue<Q> &c, int pos, Q x);

	template<typename Q>
	void Mostrar(queue<Q> c);
public:
	void ejecutar();
};

#endif /* EJERCICIO8_H_ */
