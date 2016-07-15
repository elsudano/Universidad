/* 
 * File:   pregunta1.hxx
 * Author: Carlos de la Torre
 *
 * Created on 3 de noviembre de 2015, 13:06
 */

#include "pregunta.h"


/*************************
 *  Constructor vacío.
 *************************/
Pregunta::Pregunta() {
    this->Num_preg = 0;
    this->Enunciado = "";
    this->Respuestas = "";
    this->cant_respu = 0;
}

/****************************
 *  Constructor de copia.
 ****************************/
Pregunta::Pregunta(const Pregunta &p) {
    this->Num_preg = p.Num_preg;
    this->Enunciado = p.Enunciado;
    this->cant_respu = p.cant_respu;
    this->Respuestas = p.Respuestas;
}

/*******************************
 *  Constructor con parámetros.
 *******************************/
Pregunta::Pregunta(const string &p) {
    //@TODO
}

/****************************
 *  Destructor por defecto
 ****************************/
Pregunta::~Pregunta() {

}

/************************************
 *  Consultor de numero de Pregunta
 ***********************************/
Pregunta::num Pregunta::GetNumero() const {
    return this->Num_preg;
}

/****************************
 *  Operador de Asignación
 ****************************/
Pregunta &Pregunta::operator=(const Pregunta &p) {
    this->Num_preg = p.Num_preg;
    this->Enunciado = p.Enunciado;
    this->Respuestas = p.Respuestas;
    this->cant_respu = p.cant_respu;
    return *this;
}

/****************************
 *  Operador de comparación
 ****************************/
bool Pregunta::operator==(const Pregunta &p) {
    bool respuesta = true;
    if (Enunciado.compare(p.Enunciado) != 0)
        respuesta = false;
    if (cant_respu != p.cant_respu)
        respuesta = false;
    if (Respuestas.compare(p.Respuestas) != 0)
        respuesta = false;
    return respuesta;
}

/****************************
 *  Operador de Lectura
 ****************************/
istream &operator>>(istream &is, Pregunta &p) {
    string pregunta;
    is >> pregunta;
    p = Pregunta(pregunta);
    return is;
}

/****************************
 *  Operador de impresión
 ****************************/
ostream &operator<<(ostream &os, const Pregunta &p) {
    return os << p.Num_preg << ".- " <<p.Enunciado << endl << p.cant_respu << endl << p.Respuestas << endl;
}