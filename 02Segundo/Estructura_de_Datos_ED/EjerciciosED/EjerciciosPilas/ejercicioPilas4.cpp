/*
 * ejercicio4.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioPilas4.h"

template<typename T>
void ejercicio4::mostrar(laCola<T> c){
	while(c.size() != 0){
		cout << c.front() << " ";
		c.pop();
	}
	cout << endl;
}

//int main(int argc,char *argv[])
void ejercicio4::ejecutar()
{
	laCola<int> c;
	for (int i = 0; i < 5; i++)
		c.push(i);
	mostrar(c);
	c.pop();
	mostrar(c);
	c.push(199);
	cout << c.back() << endl;
	cout << c.front() << endl;
}

