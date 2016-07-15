/*
 * ejercicio2.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioPilas12.h"

void LongPalabras::intercambiar(string &a, string &b) {
	string tmp = b;
	b = a;
	a = tmp;
}

bool LongPalabras::empty() {
	return (v.size() == 0);
}

string LongPalabras::top() {
    return v.at(v.size() - 1);
}

void LongPalabras::push(string ele) {
    v.push_back(ele);
    for (unsigned i = 0; i < v.size() - 1; i++)
        for (unsigned j = 0; j < v.size() - 1; j++) {
            if (v[j].size() > v[j + 1].size())
                intercambiar(v[j], v[j + 1]);
            if (v[j].size() == v[j + 1].size())
                if (v[j].compare(v[j + 1]) < 0)
                    intercambiar(v[j], v[j + 1]);
        }
}

int LongPalabras::size() {
    return v.size();
}

void LongPalabras::pop() {
    v.pop_back();
}

//int main(int argc,char *argv[])
void ejercicio12::ejecutar()
{
    string cad;
    LongPalabras cp;
    cad = "ESTERNOCLEIDOMASTOIDEO";
    cp.push(cad);
    cad = "SUPERCALIFRAGILISTICOESPIALIDOSO";
    cp.push(cad);
    cad = "WENCESLAO";
    cp.push(cad);
    cad = "GATO";
	cp.push(cad);
	cad = "mesa";
	cp.push(cad);
    cad = "minusculas";
    cp.push(cad);
    while (cp.size() != 0) {
        cout << cp.top() << endl;
        cp.pop();
    }
}
