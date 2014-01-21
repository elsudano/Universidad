/*
 * ejercicioListas8.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioListas8.h"

template<typename T>
void ejercicioListas8::mostrar(const list<T> l){
    typename list<T>::const_iterator i;
    for(i=l.begin(); i!=l.end(); i++){
        cout << *i << " ";
    }
    cout << endl;
}

template<typename T1, typename T2>
list<T1> ejercicioListas8::buscar(const list<T1> &l, const list<T2> &li){
	return l;
}

void ejercicioListas8::ejecutar(){
    list<int> lista1;
    list<list<int>::const_iterator> lista2;
    for(int i=0; i<10; i++){
        lista1.push_back(i);
    }
    int count=0;
    list<int>::const_iterator posicion1, posicion2, posicion3;
    while (count < 3){
    	posicion1++;
    	count++;
    }
    count=0;
	while (count < 5){
		posicion2++;
		count++;
	}
	count=0;
	while (count < 8){
		posicion3++;
		count++;
	}
	lista2.push_back(posicion1);
	lista2.push_back(posicion2);
	lista2.push_back(posicion3);
	mostrar(lista1);
	mostrar(buscar(lista1,lista2));
}
