/*
 * ejercicio2.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioPilas13.h"

void MasVocales::intercambiar(string &a, string &b) {
	string tmp = b;
	b = a;
	a = tmp;
}

bool MasVocales::esvocal(char c){
    if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
        return true;
    else
        return false;
}

bool MasVocales::empty() {
	return v.empty();
}

string MasVocales::top() {
    return v.at(v.size() - 1);
}

void MasVocales::push(string ele) {
    v.push_back(ele);
    int aux1=0,aux2=0;
    for (unsigned i = 0; i < v.size() - 1; i++)
        for (unsigned j = 0; j < v.size() - 1; j++) {
            for(unsigned k=0; k<v[j].size(); k++)
                if(esvocal(v[j][k]))
                    aux1++;
            for(unsigned k=0; k<v[j].size(); k++)
                if(esvocal(v[j+1][k]))
                    aux2++;
            if(aux1 > aux2){
                intercambiar(v[j], v[j + 1]);
                aux1=0; aux2=0;
            }
            if (aux1 == aux2)
                if (v[j].compare(v[j + 1]) < 0)
                    intercambiar(v[j], v[j + 1]);
        }
}

int MasVocales::size() {
    return v.size();
}

void MasVocales::pop() {
    v.pop_back();
}

//int main(int argc,char *argv[])
void ejercicio13::ejecutar()
{
    string cad;
    MasVocales cp;
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
    while (cp.size() != 0) {
        cout << cp.top() << endl;
        cp.pop();
    }
}
