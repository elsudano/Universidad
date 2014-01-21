/*
 * ejercicioListas4.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioListas4.h"

template<typename T>
void ejercicioListas4::mostrar(const list<T> l){
    typename list<T>::const_iterator i;
    for(i=l.begin(); i!=l.end(); i++){
        cout << *i << " ";
    }
    cout << endl;
}

template<typename T>
void ejercicioListas4::inversa(list<T> &l){
    typename list<T>::const_iterator i;
    for(i=l.begin(); i!=l.end(); i++){
        l.push_front(*i);
    }
    for(int j=0; j<=(l.size()/2)+1; j++){
        l.pop_back();
    }
    
}

void ejercicioListas4::ejecutar(){
    list<int> lista;
    for(int i=0; i<5; i++){
        lista.push_back(i);
    }
    mostrar(lista);
    inversa(lista);
    mostrar(lista);
}
