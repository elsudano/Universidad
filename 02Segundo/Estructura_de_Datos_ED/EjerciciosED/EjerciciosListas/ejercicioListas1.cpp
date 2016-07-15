/*
 * ejercicioListas1.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioListas1.h"

template<typename T>
void ejercicioListas1::f(list<T> &l, T x){
    l.remove(x);
}

template<typename T>
void ejercicioListas1::mostrar(const list<T> l){
    typename list<T>::const_iterator i;
    for(i=l.begin(); i!=l.end(); i++){
        cout << *i << " ";
    }
    cout << endl;
}

void ejercicioListas1::ejecutar(){
    list<int> lista;
    for(int i=0; i<5; i++){
        lista.push_back(4);
    }
    lista.push_back(6);
    lista.push_front(5);
    mostrar(lista);
    f(lista,4);
    mostrar(lista);
}
