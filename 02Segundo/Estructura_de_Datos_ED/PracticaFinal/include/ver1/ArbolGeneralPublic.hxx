/*
 * File:   ArbolGeneralPublic.hxx
 *
 * Created on 12 de diciembre de 2015, 14:20
 */

/*--------------------------------Public--------------------------------------*/

template <class Tbase>
ArbolGeneral<Tbase>::ArbolGeneral() {
    nodo *aux = new nodo;
    aux->padre = aux->izqda = aux->drcha = 0;
    this->laraiz = aux;
}

template <class Tbase>
ArbolGeneral<Tbase>::ArbolGeneral(const Tbase &e) {
    this->laraiz->etiqueta = e;
    this->laraiz->padre = this->laraiz->izqda = this->laraiz->drcha = 0;
}

template <class Tbase>
ArbolGeneral<Tbase>::ArbolGeneral(const ArbolGeneral<Tbase> &v) {
    copiar(this->laraiz, v.laraiz);
}

template <class Tbase>
ArbolGeneral<Tbase>::~ArbolGeneral() {
    destruir(this->laraiz);
}

template <class Tbase>
ArbolGeneral<Tbase> &ArbolGeneral<Tbase>::operator=(const ArbolGeneral<Tbase> &v) {
    copiar(this->laraiz, v.laraiz);
}

template <class Tbase>
const ArbolGeneral<Tbase> &ArbolGeneral<Tbase>::operator=(ArbolGeneral<Tbase> &v) {
    copiar(this->laraiz, v.laraiz);
    return v;
}

template <class Tbase>
void ArbolGeneral<Tbase>::AsignaRaiz(const Tbase &e) {
    this->laraiz->etiqueta = e;
}

template <class Tbase>
typename ArbolGeneral<Tbase>::Nodo ArbolGeneral<Tbase>::raiz() const {
    return this->laraiz;
}

template <class Tbase>
typename ArbolGeneral<Tbase>::Nodo ArbolGeneral<Tbase>::padre(const ArbolGeneral<Tbase>::Nodo n) const {
    return n->padre;
}

template <class Tbase>
Tbase& ArbolGeneral<Tbase>::etiqueta(const ArbolGeneral<Tbase>::Nodo n) {
    return n->etiqueta;
}

template <class Tbase>
const Tbase &ArbolGeneral<Tbase>::etiqueta(const ArbolGeneral<Tbase>::Nodo n) const {
    return n->etiqueta;
}

template <class Tbase>
typename ArbolGeneral<Tbase>::Nodo ArbolGeneral<Tbase>::hijomasizquierda(const ArbolGeneral<Tbase>::Nodo n) const {
    /*
     * Le preguntamos al maestro de practicas y nos dijo que estaba bien así
     * por que solo queriamos el hijo a la izquierda del nodo, no hacia falta
     * recorrer el subarbol hasta abajo que sería el nodo mas a la izquierda
     * del subarbol.
     */
    return n->izqda;
}

template <class Tbase>
typename ArbolGeneral<Tbase>::Nodo ArbolGeneral<Tbase>::hermanoderecha(const ArbolGeneral<Tbase>::Nodo n) const {
    return n->drcha;
}

template <class Tbase>
void ArbolGeneral<Tbase>::asignar_subarbol(const ArbolGeneral<Tbase> &orig, const ArbolGeneral<Tbase>::Nodo nod) {
    Nodo aux = nod;
    if (aux->izqda == 0) {
        orig.laraiz->padre = aux;
        aux->izqda = orig.laraiz;
    } else {
        aux = nod->izqda;
        while (aux->drcha != 0) {
            aux = aux->drcha;
        }
        orig.laraiz->padre = aux->padre;
        aux->drcha = orig.laraiz;
    }
}

template <class Tbase>
void ArbolGeneral<Tbase>::podar_hijomasizquierda(ArbolGeneral<Tbase>::Nodo n, ArbolGeneral<Tbase> &dest) {
    if (n->izqda != 0) {
        dest.laraiz = n->izqda;
        dest.laraiz->padre = 0;
        n->izqda = n->izqda->drcha;
        dest.laraiz->drcha = 0;
    }
}

template <class Tbase>
void ArbolGeneral<Tbase>::podar_hermanoderecha(ArbolGeneral<Tbase>::Nodo n, ArbolGeneral<Tbase> &dest) {
    if (n->drcha != 0) {
        dest.laraiz = n->drcha;
        dest.laraiz->padre = 0;
        n->drcha = n->drcha->drcha;
        dest.laraiz->drcha = 0;
    }
}

template <class Tbase>
void ArbolGeneral<Tbase>::insertar_hijomasizquierda(ArbolGeneral<Tbase>::Nodo n, ArbolGeneral<Tbase> &rama) {
    if (n->izqda == 0) {
        rama.laraiz->padre = n;
        rama.laraiz->drcha = n->izqda;
        n->izqda = rama.laraiz;
    } else {
        rama.laraiz->padre = n;
        n->izqda = rama.laraiz;
    }
}

template <class Tbase>
void ArbolGeneral<Tbase>::insertar_hermanoderecha(ArbolGeneral<Tbase>::Nodo n, ArbolGeneral<Tbase> &rama) {
    Nodo aux = n;
    if (aux->drcha == 0) {
        rama.laraiz->padre = aux->padre;
        aux->drcha = rama.laraiz;
    }else{
        while (aux != 0) {
            aux = aux->drcha;
        }
        rama.laraiz->padre = aux->padre;
        aux->drcha = rama.laraiz;
    }
}

template <class Tbase>
void ArbolGeneral<Tbase>::clear() {
    destruir(this->laraiz);
    this->laraiz->etiqueta = 0;
    this->laraiz->izqda = this->laraiz->drcha = this->laraiz->padre = 0;
}

template <class Tbase>
int ArbolGeneral<Tbase>::size() const {
    return this->contar();
}

template <class Tbase>
bool ArbolGeneral<Tbase>::empty() const {
    return this->laraiz->etiqueta == 0 &&
            this->laraiz->padre == 0 &&
            this->laraiz->izqda == 0 &&
            this->laraiz->drcha == 0;
}

template <class Tbase>
bool ArbolGeneral<Tbase>::operator==(const ArbolGeneral<Tbase> &v) const {
    return this->soniguales();
}

template <class Tbase>
bool ArbolGeneral<Tbase>::operator!=(const ArbolGeneral<Tbase> &v) const {
    return !this->soniguales();
}

/*-------------------------------------iterators------------------------------*/
template <class Tbase>
typename ArbolGeneral<Tbase>::iter_preorden ArbolGeneral<Tbase>::begin() {
    typename ArbolGeneral<Tbase>::iter_preorden nuevo_it(this->laraiz);
    return nuevo_it;
}

template <class Tbase>
typename ArbolGeneral<Tbase>::const_iter_preorden ArbolGeneral<Tbase>::begin()const {
    typename ArbolGeneral<Tbase>::const_iter_preorden nuevo_it(this->laraiz);
    return nuevo_it;
}

template <class Tbase>
typename ArbolGeneral<Tbase>::iter_preorden ArbolGeneral<Tbase>::end() {
    Nodo aux = this->laraiz;
    while (aux->izqda != 0 || aux->drcha != 0) {
        if (aux->izqda != 0)
            aux = aux->izqda;
        while (aux->drcha != 0)
            aux = aux->drcha;
    }
    typename ArbolGeneral<Tbase>::iter_preorden nuevo_it(aux);
    return nuevo_it;
}

template <class Tbase>
typename ArbolGeneral<Tbase>::const_iter_preorden ArbolGeneral<Tbase>::end()const {
    Nodo aux = this->laraiz;
    while (aux->izqda != 0 || aux->drcha != 0) {
        if (aux->izqda != 0)
            aux = aux->izqda;
        while (aux->drcha != 0)
            aux = aux->drcha;
    }
    typename ArbolGeneral<Tbase>::iter_preorden nuevo_it(aux);
    return nuevo_it;
}

/*---------------------------iostream-----------------------------------------*/
template<class T>
std::istream &operator>>(std::istream &in, ArbolGeneral<T> &v) {
    v.lee_arbol(in, v.laraiz);
    return in;
}

template<class T>
std::ostream &operator<<(std::ostream &out, const ArbolGeneral<T> &v) {
    v.escribe_arbol(out, v.laraiz);
    return out;
}