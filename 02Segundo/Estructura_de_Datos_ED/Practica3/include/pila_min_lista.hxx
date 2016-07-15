/* 
 * File:   pila_min_lista.hxx
 * Author: Carlos de la Torre
 *
 * Created on 22 de noviembre de 2015, 13:40
 *
 */

#include "pila_min.h"

/*************************
 *  Constructor vacío.
 *************************/
template <class T>
Pila_min<T>::Pila_min() {
}

/*************************
 *  Constructor de Copia.
 *************************/
template <class T>
Pila_min<T>::Pila_min(const Pila_min<T> &pm) {
    Lista<Elemento<T> > aux(pm.datos_pila_min);
    this->datos_pila_min = aux;
}

/***************************
 *  Destructor por defecto.
 ***************************/
template <class T>
Pila_min<T>::~Pila_min() {
    this->datos_pila_min.~Lista();
}

/***************************
 *  Operador de Asignación.
 ***************************/
template <class T>
Pila_min<T> &Pila_min<T>::operator=(const Pila_min<T> &pm) {
    if (this != pm) {
        this->datos_pila_min = pm.datos_pila_min;
    }
    return * this;
}

/*************************
 *  Metodo de Igualdad.
 *************************/
template <class T>
bool Pila_min<T>::operator==(const Pila_min<T> &pm) {
    //@TODO Implementar este operador si se me da tiempo
    return false;
}

/*************************
 *  Metodo de Acceso.
 *************************/
template <class T>
T Pila_min<T>::tope() {
    T ret;
    if (this->datos_pila_min.size() > 0)
        ret = this->datos_pila_min.get(0).dato;
    return ret;
}

/*************************
 *  Metodo Auxiliar.
 *************************/
template <class T>
T Pila_min<T>::minimo() {
    T ret;
    if (this->datos_pila_min.size() > 0)
        ret = this->datos_pila_min.get(0).min;
    return ret;
}

/*************************
 *  Metodo de Insercción.
 *************************/
template <class T>
void Pila_min<T>::poner(const T dato) {
    Elemento<T> aux;
    aux.dato = dato;

    if (this->datos_pila_min.size() == 0)
        aux.min = dato;
    else {
        if (dato > this->datos_pila_min.get(0).min)
            aux.min = this->datos_pila_min.get(0).min;
        else
            aux.min = dato;
    }
    this->datos_pila_min.insertar(0, aux);
}

/*************************
 *  Metodo de Borrado.
 *************************/
template <class T>
void Pila_min<T>::quitar() {
    if (datos_pila_min.size() > 0)
        datos_pila_min.borrar(0);
}

/********************************
 *  Metodo de Consulta isEmpty().
 ********************************/
template <class T>
bool Pila_min<T>::vacia()const {
    return (datos_pila_min.size() == 0);
}

/*************************
 *  Consultor de tamaño.
 *************************/
template <class T>
num Pila_min<T>::size()const {
    return this->datos_pila_min.size();
}

/*************************
 *  Operador de Lectura.
 *************************/
template <class T>
std::istream &operator>>(std::istream &is, Pila_min<T> &pm) {
    //@TODO Implementar este operador si se me da tiempo
    return is;
}

/***************************
 *  Operador de Impresión.
 ***************************/
template <class T>
std::ostream &operator<<(std::ostream &os, const Pila_min<T> &pm) {
    //@TODO Revisar el metodo para imprimir por pantalla los elementos
    //@TODO Implementar este operador si se me da tiempo
    return os;
}
