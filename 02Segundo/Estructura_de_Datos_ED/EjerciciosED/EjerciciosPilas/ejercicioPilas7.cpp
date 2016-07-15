/*
 * ejercicio7.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioPilas7.h"

template<typename Z>
void ejercicio7::insertar(stack<Z> &p, int pos, Z x){
    stack<Z> aux;
    int tam = p.size();
    while(p.size() != (tam-pos)){
        aux.push(p.top());
        p.pop();
    }
    p.push(x);
    while(aux.size() != 0){
        p.push(aux.top());
        aux.pop();
    }
}

template<typename Z>
void ejercicio7::Mostrar(stack<Z> p){
    while(p.size() != 0){
        cout << p.top(); cout << endl;
        p.pop();
    }

}

//int main(int argc,char *argv[])
void ejercicio7::ejecutar()
{
    stack<int> p;
    p.push(1); p.push(2); p.push(3); p.push(4);
    cout << "Pila p antes de insertar:" << endl;
    Mostrar(p);
    insertar(p,2,5);
    cout << "Pila p insertando el 5 en la pos 2:" << endl;
    Mostrar(p);
}
