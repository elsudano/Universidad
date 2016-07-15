/*
 * ejercicioListas2.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioListas2.h"

template<typename T>
void ejercicioListas2::mostrar(const list<T> l){
    typename list<T>::const_iterator i;
    for(i=l.begin(); i!=l.end(); i++){
        cout << *i << " ";
    }
    cout << endl;
}

template<typename T>
bool ejercicioListas2::esta(const list<T> &l,const T &x){
    typename list<T>::const_iterator i;
    bool esta=false,seguir=true;
    
    for(i=l.begin(); i!=l.end() && seguir; i++){
        if(*i == x){
            esta=true;
            seguir=false;
        }
    }
    return esta;
}

template<typename T>
void ejercicioListas2::elimina_duplicados(list<T> &l){
    typename list<T>::const_iterator i;
    list<T> aux;
    for(i=l.begin(); i!=l.end(); i++){
        if(!esta(aux,*i)){
            aux.push_back(*i);
        }
    }
    l.clear();
    l = aux;
}

void ejercicioListas2::ejecutar(){
    list<int> lista;
    for(int i=0; i<5; i++){
        lista.push_back(4);
    }
    lista.push_back(6);
    lista.push_front(5);
    mostrar(lista);
    elimina_duplicados(lista);
    mostrar(lista);
}
