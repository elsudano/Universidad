/*
 * ejercicio11.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO11_H_
#define EJERCICIO11_H_
#include <vector>

using namespace std;

struct Elemento {
    string nombre, apellido;
    int prioridad;
};

class OrdenPropio {
private:
    vector<Elemento> v;
    void intercambiar(Elemento &a, Elemento &b);

public:

    OrdenPropio() {}
    bool empty();
    Elemento top();
    void push(Elemento ele);
    int size();
    void pop();
};

class ejercicio11{
private:

public:
	void ejecutar();
};

#endif /* EJERCICIO11_H_ */
