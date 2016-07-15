/*
 * ejercicio5.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioPilas5.h"

template<typename T>
void ejercicio5::mostrar(laPila<T> p){
    while(p.size() != 0){
        cout << p.top() << " ";
        p.pop();
    }
    cout << endl;
}

//int main(int argc,char *argv[])
void ejercicio5::ejecutar()
{
    laPila<int> p;
    for(int i=0; i<5; i++)
        p.push(i);
    mostrar(p);  cout << endl;
    p.pop();
    mostrar(p); cout << endl;
    p.push(199);
    mostrar(p); cout << endl;
}

