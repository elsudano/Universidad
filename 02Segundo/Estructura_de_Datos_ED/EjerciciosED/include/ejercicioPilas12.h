/*
 * ejercicio12.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO12_H_
#define EJERCICIO12_H_
#include <vector>

using namespace std;

class LongPalabras {
private:
    vector<string> v;
    void intercambiar(string &a, string &b);

public:

    LongPalabras() {}
    bool empty();
    string top();
    void push(string ele);
    int size();
    void pop();
};

class ejercicio12{
private:

public:
	void ejecutar();
};


#endif /* EJERCICIO12_H_ */
