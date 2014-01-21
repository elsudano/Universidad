/*
 * ejercicio4.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO4_H_
#define EJERCICIO4_H_
#include <stack>

using namespace std;

template<typename W>
class laCola{
	private:
		stack<W> s1;
		stack<W> s2;

	public:
		laCola();
		void push(W x);
		void pop();
		W back();
		W front();
		bool empty();
		int size();
};
#include "ejercicioPilas4.hxx"

class ejercicio4{
	private:
		template<typename J>
		void mostrar(laCola<J> c);
	public:
		void ejecutar();
};
#endif /* EJERCICIO4_H_ */
