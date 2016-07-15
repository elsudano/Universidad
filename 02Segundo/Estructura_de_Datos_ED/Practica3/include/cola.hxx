/* 
 * File:   cola.hxx
 * Author: Carlos de la Torre
 *
 * Created on 22 de noviembre de 2015, 17:33
 *
 */

#include "basico.h"
#include "cola.h"

/*****************************************************************/
template <class T>
void Cola<T>::copiar(const Cola<T> &c) {
    //@TODO Implementar este operador si se me da tiempo
}

/*****************************************************************/
template <class T>
void Cola<T>::limpiar() {
    //@TODO Implementar este operador si se me da tiempo
}

/*****************************************************************/
template <class T >
Cola<T>::Cola() {
    this->primera = this->ultima = 0;
    this->elem = 0;
}

/*****************************************************************/
template <class T >
Cola<T>::Cola(const Cola<T> &c) {
    if (c.primera != 0) { //Si tiene elementos
        Celda<T> *p = c.primera; //Copiamos el puntero a la primera celda
        this->primera = this->ultima = new Celda<T>(p->ele, 0, 0); //Inicializamos la lista de nodos
        p = p->sig; //Avanzamos el puntero
        while (p != 0) { //Mientras queden elementos
            this->ultima->sig = new Celda<T>(p->ele, 0, 0); //Copiamos el elemento
            this->ultima = this->ultima->sig; //Avanzamos los punteros
            p = p->sig;
        }
    } else
        this->primera = this->ultima = 0; //Si no tiene elementos
    this->elem = c.elem;
}

/*****************************************************************/
template <class T >
Cola<T>::~Cola() {
    Celda<T> * aux;
    while (this->primera != 0) { //Mientras queden elementos
        aux = this->primera; //Copiamos el puntero
        this->primera = this->primera->sig; //Avanzamos primera
        delete aux; //Borramos el nodo
    }
}

/*****************************************************************/
template <class T >
Cola<T> &Cola<T>::operator=(const Cola<T> &c) {
    Celda<T> * aux;
    if (this != &c) { //Comprobación de rigor. Si son diferentes objetos
        while (this->primera != 0) { //Borramos la lista de nodos de la cola *this
            aux = this->primera;
            this->primera = this->primera->sig;
            delete aux;
        }
        if (c.primera != 0) { //Si la otra cola tiene elementos
            aux = c.primera; //Copiamos el puntero al primer nodo
            this->primera = this->ultima = new Celda<T>(aux->ele, 0, 0); //Reservamos el primer nodo
            aux = aux->sig; //Avanzamos el puntero
            while (aux != 0) { //Mientras queden elementos
                this->ultima->sig = new Celda<T>(aux->ele, 0, 0); //Creamos un nuevo nodo
                this->ultima = this->ultima->sig; //Actualizamos ultima
                aux = aux->sig; //Avanzamos el puntero
            }
        } else
            this->primera = this->ultima = 0; //Si la otra cola está vacía
        this->elem = c.elem;
    }
    return *this; //Devolvemos el objeto para permitir el encadenamiento (a=b=c)
}

/*****************************************************************/
template <class T>
bool &Cola<T>::operator==(const Cola<T> &c) {
    Celda<T> * aux = c.primera;
    bool ret = true;
    if (this->elem == c.elem){
        while (aux != this->ultima && ret == true) {
            if (aux != c.primera)
                ret = false;
            aux = aux->sig;
        }
    }
    return ret;
}

/*****************************************************************/
template <class T>
bool Cola<T>::vacia() const {
    return this->elem == 0;
}

/*****************************************************************/
template <class T >
T &Cola<T>::frente() {
    //if (this->primera != 0)
    return this->primera->ele; //Devuelve el elemento del frente de la cola
}

/*****************************************************************/
template <class T>
const T &Cola<T>::frente() const {
    //if (this->primera != 0)
    return this->primera->ele; //Devuelve el elemento del frente de la cola
}

/*****************************************************************/
template <class T>
void Cola<T>::poner(const T &c) {
    Celda<T> * aux = new Celda<T>(c, 0, 0); //Creamos un nuevo nodo
    if (this->primera == 0) //Si la cola está vacía,
        this->primera = this->ultima = aux; //primera y ultima apuntan a ese nodo
    else { //Si la cola ya tenia nodos,
        this->ultima->sig = aux; //Actualizamos el puntero siguiente del último nodo
        this->ultima = aux; //Actualizamos ultima
    }
    this->elem++; //Incrementamos el numero de elementos
}

/*****************************************************************/
template <class T>
void Cola<T>::quitar() {
    if (this->primera != 0) {//Si la cola está vacía, abortar
        Celda<T> *aux = this->primera; //Copiamos el puntero al primer nodo
        this->primera = this->primera->sig; //Actualizamos primera
        delete aux; //Borramos el primer nodo
        if (this->primera == 0) //Si no quedan nodos,
            this->ultima = 0; //actualizamos ultima
        this->elem--; //Actualizamos el número de elementos
    }
}

/*****************************************************************/
template <class T >
num Cola<T>::size() const {
    return this->elem;
}