/* 
 * File:   vd.hxx
 * Author: Carlos de la Torre
 *
 * Created on 22 de noviembre de 2015, 13:40
 *
 */

#include "vd.h"

template <class T>
void VD<T>::resize(num tam) {
    T * aux = new T[tam];
    int minimo = this->ele;
    if (tam < this->ele)
        minimo = tam;
    for (int i = 0; i < minimo; i++)
        aux[i] = this->datos_vd[i];
    this->tam = tam;
    this->ele = minimo;
    delete []datos_vd;
    this->datos_vd = aux;
}

/***************************************************/
template <class T>
void VD<T>::copiar(const VD<T> &v) {
    this->tam = v.tam;
    this->datos_vd = new T[this->tam];
    this->ele = v.ele;
    for (num i = 0; i<this->ele; i++)
        this->datos_vd[i] = v.datos_vd[i];
}

/***************************************************/
template <class T>
void VD<T>::limpiar() {
    if (this->datos_vd != 0)
        delete []this->datos_vd;
}

/***************************************************/
template <class T>
VD<T>::VD(num tam) {
    this->tam = tam;
    this->datos_vd = new T[this->tam];
    this->ele = 0;
}

/***************************************************/
template <class T>
VD<T>::VD(const VD<T> &v) {
    this->copiar(v);
}

/***************************************************/
template <class T>
VD<T>::~VD() {
    this->limpiar();
}

/***************************************************/
template <class T>
VD<T> & VD<T>::operator=(const VD<T> &v) {
    if (this != &v) {
        delete []this->datos_vd;
        copiar(v);
    }
    return *this;
}

/***************************************************/
template <class T>
T VD<T>::get(num pos) const {
    T ret = T();
    if (pos >= 0 && pos<this->ele)
        ret = this->datos_vd[pos];
    return ret;
}

/***************************************************/
template <class T>
const T &VD<T>::operator[](num pos) const {
    return this->datos_vd[pos];
}

/***************************************************/
template <class T>
T &VD<T>::operator[](num pos) {
    return this->datos_vd[pos];
}

/***************************************************/
template <class T>
num VD<T>::size() const {
    return this->ele;
}

/***************************************************/
template <class T>
void VD<T>::borrar(num pos) {
    if (pos >= 0 && pos<this->ele) {
        for (num i = pos + 1; i < this->ele; i++)
            this->datos_vd[i - 1] = this->datos_vd[i];
        this->ele--;
        if (this->tam / 4 >= this->ele)
            this->resize(this->tam / 2);
    }
}

/***************************************************/
template <class T>
void VD<T>::insertar(const T &nuevo, num pos) {
    if (pos >= 0 && pos <= this->ele) {//podemos inserarlo por el final
        if (this->tam == this->ele)
            this->resize(this->tam * 2);
        for (num i = this->ele; i > pos; i--)
            this->datos_vd[i] = this->datos_vd[i - 1];
        this->datos_vd[pos] = nuevo;
        this->ele++;
    }
}


