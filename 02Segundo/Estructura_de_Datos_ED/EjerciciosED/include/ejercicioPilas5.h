/*
 * ejercicio5.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO5_H_
#define EJERCICIO5_H_
#include <queue>

using namespace std;

template<typename W>
class laPila{
	private:
		queue<W> s1;
		queue<W> s2;

	public:
		laPila();
		W top();
		void push(W x);
		void pop();
		bool empty();
		int size();
};
#include "ejercicioPilas5.hxx"

class ejercicio5{
	private:
		template<typename J>
		void mostrar(laPila<J> p);

	public:
		void ejecutar();
};
#endif /* EJERCICIO5_H_ */
