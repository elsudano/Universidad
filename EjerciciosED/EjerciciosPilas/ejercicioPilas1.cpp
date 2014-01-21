/*
 * ejercicio1.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioPilas1.h"

template<typename T>
void ejercicio1::f(stack<T> &p, const T x, const T y) {
	static stack<T> tmp;
	try{
	if (tmp.size() != this->tam) {
		if (p.top() == x) {
			tmp.push(y);
			p.pop();
		} else {
			tmp.push(p.top());
			p.pop();
		}
		f(p, x, y);
	} else
		while (tmp.size() != 0) {
			p.push(tmp.top());
			tmp.pop();
		}
	}catch (int e) {
		cout << "ERROR: " << e;
	}
}

template<typename T>
void ejercicio1::Mostrar(stack<T> p) {

	while (p.size() != 0) {
		cout << p.top() << " ";
		p.pop();
	}
	cout << endl;
}

/**
 * @brief "tam" Variable que se usa para el guardar
 * el tamaño de la pila
 */
//int main(int argc,char *argv[]){
void ejercicio1::ejecutar(){
		stack<int> p;
		int x = 3, y = 2;
		for (int i = 0; i < 10; i++)
			p.push(i);
		tam = p.size();
		Mostrar(p);
		f(p, x, y);
		Mostrar(p);
	}

