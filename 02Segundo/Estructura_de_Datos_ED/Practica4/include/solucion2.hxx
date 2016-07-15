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
    map<Solucion::num, char> aux_sol;
    if (s.misSoluciones.size() != 0)
        aux_sol = s.misSoluciones;
    this->misSoluciones = aux_sol;
}

/*************************
 *  Destructor por defecto.
 *************************/
Solucion::~Solucion() {
    if (this->misSoluciones.size() != 0)
        this->misSoluciones.clear();
}

/***************************
 *  Constructor parametros.
 ***************************/
Solucion::Solucion(Solucion::num np, char &s) {
    this->misSoluciones.insert(pair<Solucion::num,char>(np,s));
}

/***************************
 *  Operador de Asignación.
 ***************************/
Solucion &Solucion::operator=(const char &s) {
    this->misSoluciones.insert(pair<Solucion::num,char>(this->misSoluciones.size()+1,s));
    return *this;
}

/*************************
 *  Operador de Igualdad.
 *************************/
bool Solucion::operator==(const Solucion &s) {
    return this->misSoluciones == s.misSoluciones;
}

/*************************
 *  Operador de Consulta.
 *************************/
const char Solucion::operator[](num &n) {
    return this->misSoluciones[n];
}

/*************************
 *  Metodo de Inserción.
 *************************/
void Solucion::SetSolucion(const num np, const char &s){
    this->misSoluciones.insert(pair<Solucion::num,char>(np,s));
}

/*************************
 *  Metodo de Consulta.
 *************************/
char Solucion::GetSolucion(const num &n) {
    return this->misSoluciones.at(n);
}

/*************************
 *  Consultor de Tamaño.
 *************************/
Solucion::num Solucion::Size() {
    return this->misSoluciones.size();
}

/********************************
 *  Operador de Post Incremento.
 ********************************/
Solucion::iterator &Solucion::iterator::operator++() {
    ++it;
    return *this;
}

Solucion::const_iterator &Solucion::const_iterator::operator++() {
    ++it;
    return *this;
}

/********************************
 *  Operador de Post Decremento.
 ********************************/
Solucion::iterator &Solucion::iterator::operator--() {
    --it;
    return *this;
}

Solucion::const_iterator &Solucion::const_iterator::operator--() {
    --it;
    return *this;
}

/**********************************
 *  Operador de Acceso al iterador.
 **********************************/
pair<Solucion::num, char> Solucion::iterator::operator*() {
    return *it;
}

pair<Solucion::num, char> Solucion::const_iterator::operator*() const {
    return *it;
}

/********************************
 *  Operador de Comparación.
 ********************************/
bool Solucion::iterator::operator==(const iterator &i) const {
    return i.it == this->it;
}

bool Solucion::const_iterator::operator==(const const_iterator &i) const {
    return i.it == this->it;
}

/********************************
 *  Operador de Desigualdad.
 ********************************/
bool Solucion::iterator::operator!=(const iterator &i) const {
    return i.it != this->it;
}

bool Solucion::const_iterator::operator!=(const const_iterator &i) const {
    return i.it != this->it;
}

/********************************
 *  Iterador del Primer elemento.
 ********************************/
Solucion::iterator Solucion::begin() {
    iterator i;
    i.it = this->misSoluciones.begin();
    return i;
}

Solucion::const_iterator Solucion::begin() const {
    const_iterator i;
    i.it = this->misSoluciones.begin();
    return i;
}

/********************************
 *  Iterador del Ultimo elemento
 ********************************/
Solucion::iterator Solucion::end() {
    iterator i;
    i.it = this->misSoluciones.end()++;
    return i;
}

Solucion::const_iterator Solucion::end() const {
    const_iterator i;
    i.it = this->misSoluciones.end()++;
    return i;
}

/**************************
 *  Operador de Lectura.
 **************************/
istream &operator>>(istream &is, Solucion &s) {
    string linea;
    char letra;
    string::size_type inicio, fin;
    int numero;

    while (!is.eof()) {
        inicio = 0;
        getline(is, linea);
        fin = linea.find(".- ", inicio);
        numero = atoi(linea.substr(inicio, fin - inicio).c_str());
        inicio = fin+3;
        letra = linea.substr(inicio, linea.size() - inicio)[0];
        s.SetSolucion(numero,letra);
    }
    return is;
}

/**************************
 *  Operador de impresión.
 **************************/
ostream &operator<<(ostream &os, Solucion &s) {
     for (unsigned int z = 0; z < s.Size(); z++)
        os << s.GetSolucion(z);
    return os;
}