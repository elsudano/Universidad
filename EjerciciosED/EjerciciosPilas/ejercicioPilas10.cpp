/*
 * ejercicio10.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioPilas10.h"

bool ejercicio10::funcion(string cad, stack<char> s, queue<char> c){
     for(unsigned i=0; i<cad.size(); i++){
        s.push(cad[i]);
        c.push(cad[i]);
    }
    bool palindromo = true;
    while(s.size() != 0){
        if(s.top() != c.front())
            palindromo = false;
        s.pop();
        c.pop();
    }
    return palindromo;

}


//int main(int argc,char *argv[])
void ejercicio10::ejecutar()
{
    string cad = "000111000";
    stack<char> s;
    queue<char> c;
    if(funcion(cad,s,c))
        cout << cad << " es un palindromo" << endl;
    else
        cout << "Error" << endl;
}
