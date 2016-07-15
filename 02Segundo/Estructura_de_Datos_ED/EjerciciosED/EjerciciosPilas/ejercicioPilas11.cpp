/*
 * ejercicio2.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioPilas11.h"

void OrdenPropio::intercambiar(Elemento &a, Elemento &b) {
	Elemento tmp = b;
	b = a;
	a = tmp;
}

bool OrdenPropio::empty() {
	return (v.size() == 0);
}

Elemento OrdenPropio::top() {
    return v.at(v.size() - 1);
}

void OrdenPropio::push(Elemento ele) {
    v.push_back(ele);
    for (unsigned i = 0; i < v.size() - 1; i++)
        for (unsigned j = 0; j < v.size() - 1; j++)
            if (v[j].prioridad < v[j + 1].prioridad)
                intercambiar(v[j], v[j + 1]);
}

int OrdenPropio::size() {
    return v.size();
}

void OrdenPropio::pop() {
    v.pop_back();
}

//int main(int argc,char *argv[])
void ejercicio11::ejecutar()
{
    Elemento ele;
    OrdenPropio cp;
    ele.apellido="DE LA TORRE";
    ele.nombre="CARLOS";
    ele.prioridad=15;
    cp.push(ele);
    ele.apellido="GONZALEZ";
    ele.nombre="JESUS";
    ele.prioridad=0;
    cp.push(ele);
    ele.apellido="GRILLO";
    ele.nombre="PEPITO";
    ele.prioridad=5;
    cp.push(ele);

    while (cp.size() != 0) {
        cout << cp.top().nombre << " " << cp.top().apellido << endl;
        cp.pop();
    }
}
