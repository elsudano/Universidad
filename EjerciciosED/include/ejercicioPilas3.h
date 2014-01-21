/*
 * ejercicio3.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO3_H_
#define EJERCICIO3_H_
#include <vector>
#include <stack>

using namespace std;

class laberinto{
private:
	typedef pair<int,int> pos;
    int filas, columnas;
    vector<char> lab, vis;
    stack<pos> sol;
    pos entrada, salida;
    void busca_entrada_salida();
    bool esPared(const pos &pos);
    void setVisitada(const pos &p);
    bool getVisitada(const pos &p);
public:
    laberinto();
    bool leer(const char* fichero);
    void pintar(bool sol) const;
    bool resolver();
};

void ejecutar_ejercicio3();

#endif /* EJERCICIO3_H_ */
