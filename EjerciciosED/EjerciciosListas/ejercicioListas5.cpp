/*
 * ejercicioListas5.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioListas5.h"

template<typename T>
void ejercicioListas5::mostrar(const list<T> l) {
	typename list<T>::const_iterator i;
	for (i = l.begin(); i != l.end(); i++) {
		cout << *i << " ";
	}
	cout << endl;
}

template<typename T>
list<T> ejercicioListas5::mezclar(const list<T> &l1, const list<T> &l2) {
	list<T> res;
	typename list<T>::const_iterator i, j;
	for (i = l1.begin(); i != l1.end(); i++) {
		res.push_back(*i);
	}
	for (j = l2.begin(); j != l2.end(); j++) {
		res.push_back(*j);
	}
	res.sort();
	return res;
}

void ejercicioListas5::ejecutar() {
	list<int> lista1, lista2, final;
	for (int i = 0; i < 5; i++) {
		lista1.push_back(i);
	}
	for (int j = 5; j < 10; j++) {
		lista2.push_back(j);
	}
	final = mezclar(lista1, lista2);
	mostrar(lista1);
	mostrar(lista2);
	mostrar(final);
}
