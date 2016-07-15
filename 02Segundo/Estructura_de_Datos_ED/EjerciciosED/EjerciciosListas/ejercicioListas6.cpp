/*
 * ejercicioListas6.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioListas6.h"

void ejercicioListas6::mostrar(const list<int> l){
    typename list<int>::const_iterator i;
    for(i=l.begin(); i!=l.end(); i++){
        cout << *i << " ";
    }
    cout << endl;
}

void ejercicioListas6::funcion(list<int> &l,int x){
    list<int>::iterator i;
    for(i=l.begin(); i!=l.end(); i++){
        if(*i == x){
            i++;
            l.insert(i,x-1);
            i--;
        }

    }
}

void ejercicioListas6::ejecutar(){
    list<int> lista1;
    for(int i=0; i<5; i++){
        lista1.push_back(4);
    }
    lista1.push_back(2);
    lista1.push_front(30);
    mostrar(lista1);
    funcion(lista1,4);
    mostrar(lista1);
}
