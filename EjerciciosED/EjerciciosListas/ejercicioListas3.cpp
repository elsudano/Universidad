/*
 * ejercicioListas3.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioListas3.h"

template<typename T>
void ejercicioListas3::mostrar(const list<T> l){
    typename list<T>::const_iterator i;
    for(i=l.begin(); i!=l.end(); i++){
        cout << *i << " ";
    }
    cout << endl;
}

template<typename T>
list<T> ejercicioListas3::inversa(const list<T> &l){
    typename list<T>::const_iterator i;
    list<T> invert;
    for(i=l.begin(); i!=l.end(); i++){
        invert.push_front(*i);
    }
    return invert;
}

void ejercicioListas3::ejecutar(){
    list<int> lista;
    for(int i=0; i<5; i++){
        lista.push_back(i);
    }
    mostrar(lista);
    list<int> inv;
    inv = inversa(lista);
    mostrar(inv);
}
