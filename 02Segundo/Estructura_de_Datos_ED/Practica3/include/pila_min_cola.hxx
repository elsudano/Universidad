/* 
 * File:   pila_min_cola.hxx
 * Author: Carlos de la Torre
 *
 * Created on 22 de noviembre de 2015, 13:40
 *
 */

#include "pila_min.h"
#include "cola.h"

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
    Cola<Elemento<T> > aux(pm.datos_pila_min);
    this->datos_pila_min = aux;
}

/***************************
 *  Destructor por defecto.
 ***************************/
template <class T>
Pila_min<T>::~Pila_min() {
    this->datos_pila_min.~Cola();
}

/*************************
 *  Operador de Igualdad.
 *************************/
template <class T>
Pila_min<T> &Pila_min<T>::operator=(const Pila_min<T> &pm) {
    if (this != pm) {
        this->datos_pila_min = pm.datos_pila_min;
    }
    return * this;
}

/*************************
 *  Metodo de Acceso.
 *************************/
template <class T>
bool Pila_min<T>::operator==(const Pila_min<T> &pm) {
    Pila_min<T> aux_pm(pm);
    Pila_min<T> aux_this;
    aux_this.datos_pila_min = this->datos_pila_min;
    bool ret = true;
    if (aux_this.size() == aux_pm.size()) {
        while (!aux_pm.vacia()) {
            if (aux_this.tope() != aux_pm.tope())
                ret = false;
            aux_pm.quitar();
            aux_this.quitar();
        }
    }else
        ret = false;
    return ret;
}

/*************************
 *  Metodo de Acceso.
 *************************/
template <class T>
T Pila_min<T>::tope() {
    Cola<Elemento<T> > auxC;
    Elemento<T> auxE;

    while (!this->datos_pila_min.vacia()) {
        auxE = this->datos_pila_min.frente();
        this->datos_pila_min.quitar();
        auxC.poner(auxE);
    }
    this->datos_pila_min = auxC;
    return auxE.dato;
}

/*************************
 *  Metodo Auxiliar.
 *************************/
template <class T>
T Pila_min<T>::minimo() {
    Cola<Elemento<T> > auxC;
    Elemento<T> auxE;

    while (!this->datos_pila_min.vacia()) {
        auxE = this->datos_pila_min.frente();
        this->datos_pila_min.quitar();
        auxC.poner(auxE);
    }
    this->datos_pila_min = auxC;
    return auxE.min;
}

/*************************
 *  Metodo de Insercción.
 *************************/
template <class T>
void Pila_min<T>::poner(const T dato) {
    Cola<Elemento<T> > auxC;
    Elemento<T> aux, auxE;
    aux.dato = dato;

    if (this->datos_pila_min.size() == 0) {
        aux.min = dato;
    } else {
        while (!this->datos_pila_min.vacia()) {
            auxE = this->datos_pila_min.frente();
            this->datos_pila_min.quitar();
            auxC.poner(auxE);
        }
        this->datos_pila_min = auxC;
        if (dato > auxE.min)
            aux.min = auxE.min;
        else
            aux.min = dato;
    }
    this->datos_pila_min.poner(aux);
}

/*************************
 *  Metodo de Eliminación.
 *************************/
template <class T>
void Pila_min<T>::quitar() {
    Cola<Elemento<T> > auxC;
    Elemento<T> auxE;

    while (!this->datos_pila_min.vacia()) {
        auxE = this->datos_pila_min.frente();
        this->datos_pila_min.quitar();
        if (!this->datos_pila_min.vacia())
            auxC.poner(auxE);
    }
    this->datos_pila_min = auxC;
}

/********************************
 *  Metodo de Consulta isEmpty().
 ********************************/
template <class T>
bool Pila_min<T>::vacia()const {
    return this->datos_pila_min.vacia();
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
    //@TODO Implementar este operador si se me da tiempo
    return os;
}