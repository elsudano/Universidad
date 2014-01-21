/*
 * ejercicio2.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO2_H_
#define EJERCICIO2_H_
#include <stack>

using namespace std;

class ejercicio2{
private:
	stack<char> miPila;
	bool valor;
	int corchetes;
	int parentesis;
	int llaves;
	bool comprobar(string cadena);
public:
	ejercicio2();
	void ejecutar();
};
#endif
