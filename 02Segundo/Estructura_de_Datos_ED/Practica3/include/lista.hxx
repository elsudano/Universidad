/* 
 * File:   lista.hxx
 * Author: Carlos de la Torre
 *
 * Created on 23 de noviembre de 2015, 11:55
 *
 */
template <class T>
void Lista<T>::copiar(const Lista<T> &l) {
    this->cabecera = new Celda<T>;
    this->cabecera->sig = this->cabecera->ant = 0;
    if (l.cabecera->sig != l.cabecera->ant) { // no está vacía
        const Celda<T> * aux = l.cabecera;
        while (aux->sig != 0) {
            Celda<T> * nuevo = new Celda<T>(aux->sig->ele, 0, this->cabecera);
            this->cabecera->sig = nuevo;
            aux = aux->sig;
            this->cabecera = this->cabecera->sig;
        }
        this->ele = l.ele;
    }
}

/*****************************************************************/
template <class T>
void Lista<T>::limpiar() {
    while (this->cabecera->sig != 0) {
        Celda<T> * aux = this->cabecera->sig;
        this->cabecera->sig = this->cabecera->sig->sig;
        delete aux;
    }
    this->cabecera->ant = this->cabecera->sig;
}

/*****************************************************************/
template <class T>
Lista<T>::Lista() {
    this->cabecera = new Celda<T>;
    this->cabecera->sig = this->cabecera->ant = 0;
    this->ele = 0;
}

/*****************************************************************/
template <class T>
Lista<T>::Lista(const Lista<T> &l) {
    copiar(l);
}

/*****************************************************************/
template <class T>
Lista<T>::~Lista() {
    this->limpiar();
    delete this->cabecera;
}

/*****************************************************************/
template <class T>
Lista<T> &Lista<T>::operator=(const Lista<T> &l) {
    if (this != &l) {
        this->limpiar();
        delete this->cabecera;
        copiar(l);
    }
    return * this;
}

/*****************************************************************/
template <class T>
bool Lista<T>::vacia() const {
    return (this->cabecera->sig == this->cabecera->ant);
}

/*****************************************************************/
template <class T>
const T &Lista<T>::get(num pos) const {
    T ret;
    if (pos >= 0 && pos < this->ele) {
        int i = 0;
        while (this->cabecera->sig != 0 && i < pos) {
            this->cabecera = this->cabecera->sig;
            i++;
        }
        ret = this->cabecera->sig->ele;
    }
    return ret;
}

/*****************************************************************/
template <class T>
T &Lista<T>::get(num pos) {
    if (pos >= 0 && pos < this->ele) {
        num i = 0;
        while (this->cabecera->sig != 0 && i < pos) {
            this->cabecera = this->cabecera->sig;
            i++;
        }
        return this->cabecera->sig->ele;
    }
}

/*****************************************************************/
template <class T>
void Lista<T>::set(num pos, const T &v) {
    if (pos >= 0 && pos < this->ele) {
        num i = 0;
        while (this->cabecera->sig != 0 && i < pos) {
            this->cabecera = this->cabecera->sig;
            i++;
        }
        this->cabecera->sig->ele = v;
    }
}

/*****************************************************************/
template <class T>
void Lista<T>::insertar(num pos, const T &v) {
    if (pos >= 0 && pos <= this->ele) {
        num i = 0;
        while (this->cabecera->sig != 0 && i < pos) {
            this->cabecera = this->cabecera->sig;
            i++;
        }
        Celda<T> * nueva = new Celda<T>(v, this->cabecera->sig, this->cabecera);

        this->cabecera->sig = nueva;
        if (nueva->sig != 0)
            nueva->sig->ant = nueva;
        this->ele++;
    }
}

/*****************************************************************/
template <class T>
void Lista<T>::borrar(num pos) {
    if (pos >= 0 && pos < this->ele) {
        num i = 0;
        while (this->cabecera->sig != 0 && i < pos) {
            this->cabecera = this->cabecera->sig;
            i++;
        }
        Celda<T> * aux = this->cabecera->sig;
        this->cabecera->sig = aux->sig;
        if (aux->sig != 0)
            aux->sig->ant = this->cabecera;
        delete aux;
        this->ele--;
    }
}

/*****************************************************************/
template <class T>
num Lista<T>::size()const {
    return this->ele;
}