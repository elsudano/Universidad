/* 
 * File:   solucion.hxx
 * Author: Carlos de la Torre
 *
 * Created on 3 de diciembre de 2015, 12:54
 */

#include "solucion.h"

/*************************
 *  Constructor vacío.
 *************************/
Solucion::Solucion() {

}

/*************************
 *  Constructor copia.
 *************************/
Solucion::Solucion(const Solucion &s) {

}

/*************************
 *  Destructor por defecto.
 *************************/
Solucion::~Solucion() {

}

/***************************
 *  Constructor parametros.
 ***************************/
Solucion::Solucion(ConjuntoPreguntas &cp, Solucion::num np) {

}

/***************************
 *  Operador de Asignación.
 ***************************/
Solucion &Solucion::operator=(const string &s) {

}

/*************************
 *  Operador de Igualdad.
 *************************/
bool Solucion::operator==(const Solucion &s) {

}

/*************************
 *  Operador de Consulta.
 *************************/
const Solucion &Solucion::operator[](Solucion::num &n) {

}

/*************************
 *  Consultor de Tamaño.
 *************************/
Solucion::num Solucion::Size() {

}

/**************************
 *  Operador de Lectura.
 **************************/
istream &operator>>(istream &is, Solucion &s) {
    getline(is, s.first, '.- ');
    getline(is, s.second);
    return is;
}

/**************************
 *  Operador de impresión.
 **************************/
ostream &operator<<(ostream &os, Solucion &s) {
    os << s.first << '.- ' << s.second << endl;
    return os;
}