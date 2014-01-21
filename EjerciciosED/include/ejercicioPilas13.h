/*
 * ejercicio13.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO13_H_
#define EJERCICIO13_H_
#include <vector>

using namespace std;

class MasVocales {
private:
    vector<string> v;
    void intercambiar(string &a, string &b);
    bool esvocal(char c);

public:

    MasVocales() {}
    bool empty();
    string top();
    void push(string ele);
    int size();
    void pop();
};

class ejercicio13{
private:

public:
	void ejecutar();
};


#endif /* EJERCICIO13_H_ */
