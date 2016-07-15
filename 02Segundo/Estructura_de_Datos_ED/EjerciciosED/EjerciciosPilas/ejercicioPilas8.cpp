/*
 * ejercicio8.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioPilas8.h"

template<typename Q>
void ejercicio8::insertar(queue<Q> &c, int pos, Q x){
	queue<Q> aux;
	tam = c.size();
    for(unsigned j=0;j<tam;j++){
    	if (j==pos)
    		aux.push(x);
        aux.push(c.front());
        c.pop();
    }
    c = aux;
}

template<typename Q>
void ejercicio8::Mostrar(queue<Q> c){
    while(c.size() != 0){
        cout << c.front(); cout << endl;
        c.pop();
    }

}

//int main(int argc,char *argv[])
void ejercicio8::ejecutar()
{
    queue<int> cola;
    for (int c=0;c<5;c++)
    	cola.push(c);
    cout << "La Cola c antes de insertar:" << endl;
    Mostrar(cola);
    insertar(cola,2,5);
    cout << "La Cola c insertando el 5 en la pos 2:" << endl;
    Mostrar(cola);
}
