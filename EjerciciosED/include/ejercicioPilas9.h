/*
 * ejercicio9.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO9_H_
#define EJERCICIO9_H_
#include <stack>

using namespace std;

template<typename Y>
class ventana{
	private:
		stack<Y> pilaizq;
		stack<Y> pilader;
	public:
		ventana();
		void inserta(Y ele);
		void movIzq();
		void movDer();
		int getPos();
		int getVal();
		int size();
		void elimina();
};
#include "ejercicioPilas9.hxx"

class ejercicio9{
	private:
		template <typename J>
		void mostrar(ventana<J> v);

	public:
		void ejecutar();
};

#endif /* EJERCICIO9_H_ */
