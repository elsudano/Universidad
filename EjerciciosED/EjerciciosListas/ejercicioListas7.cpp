/*
 * ejercicioListas6.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioListas7.h"

void ejercicioListas7::mostrar(const list<int> l){
    typename list<int>::const_iterator i;
    for(i=l.begin(); i!=l.end(); i++){
        cout << *i << " ";
    }
    cout << endl;
}

template<typename T>
bool ejercicioListas7::contenida(const list<T> &l1, const list<T> &l2){
    typename list<T>::const_iterator i;
    list<T> aux;
    bool contenida=false,seguir=true;
    int cont=0;
    for(i=l2.begin(); i!=l2.end() && seguir; i++){
        aux.push_back(*i);
        cont++;
        if(cont == l1.size()){
            cont--;
            if(aux == l1){
                contenida = true;
                seguir = false;
            }
            else
                aux.pop_front();
        }
    }  
    return contenida;
}

void ejercicioListas7::ejecutar(){
    list<int> lista1,lista2;
    for(int i=0; i<10; i++){
        lista2.push_back(i);
    }
    for(int j=3; j<8; j++){
        lista1.push_back(j);
    }
    cout << "Lista1 -> ";
    mostrar(lista1);
    cout << "Lista2 -> ";
    mostrar(lista2);
    if(contenida(lista1,lista2))
        cout << "Lista2 contiene a Lista1" << endl;
    else
        cout << "Lo siento, no la contiene" << endl;
}
