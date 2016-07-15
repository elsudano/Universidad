/*
 * ejercicio7.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO7_H_
#define EJERCICIO7_H_
#include <stack>

using namespace std;

class ejercicio7{
private:
	template<typename Z>
	void Mostrar(stack<Z> p);

	template<typename Z>
	void insertar(stack<Z> &p, int pos, Z x);
public:
	void ejecutar();
};


#endif /* EJERCICIO7_H_ */
