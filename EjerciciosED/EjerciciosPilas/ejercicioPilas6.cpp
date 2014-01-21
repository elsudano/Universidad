/*
 * ejercicio6.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioPilas6.h"

void ejercicio6::evaluar(string e){
    unsigned i;
    int izq, dcha;
    stack<int> v;
    for (i = 0; i != e.size(); ++i) { //Recorrido de los caracteres del string.
        if(e[i] == ' ')//Usado para que pueda haber espacios blancos en la cadena
            i++;
        if ((e[i] >= '0') && (e[i] <= '9'))
            v.push((int)e[i]- (int) '0');
        else {
            dcha = v.top();
            v.pop();
            izq = v.top();
            v.pop();
            switch (e[i]) {
                case '+': v.push(izq + dcha);
                    break;
                case '-': v.push(dcha-izq);
                    break;
                case '*': v.push(izq * dcha);
                    break;
                case '/': v.push(izq / dcha);
                    break;
                case '^': v.push(pow(izq,dcha));
                    break;

            }
        }

    }
    while(v.size() != 0){
        cout << v.top();
        v.pop();
    }
    cout << endl;
}

//int main(int argc,char *argv[])
void ejercicio6::ejecutar()
{
    //ab^c*d/e + donde a = 5, b = 3, c = d = 2, e = 9
    string c1 = "53^2*2/9 +";
    //ab+cd*+e* donde a = 2, b = 6, c = 3, d = 5, e = 9 --> ((2+6)+(3*5))*9
    string c3 = "26+35*+9*";
    evaluar(c1);
    evaluar(c3);
}
