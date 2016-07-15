/* 
 * File:   ArbolGeneralPrivate.hxx
 *
 * Created on 11 de diciembre de 2015, 19:20
 */

/*--------------------------------Private-------------------------------------*/
template <class Tbase>
void ArbolGeneral<Tbase>::destruir(nodo *n) {
    if (n != 0) {
        destruir(n->izqda);
        destruir(n->drcha);
        delete n;
    }
}

template <class Tbase>
void ArbolGeneral<Tbase>::copiar(nodo *&dest, nodo *orig) {
    if (orig == 0)
        dest = 0;
    else {
        dest = new nodo();
        dest->etiqueta = orig->etiqueta;
        dest->padre = dest->izqda = dest->drcha = 0;
        copiar(dest->izqda, orig->izqda);
        copiar(dest->drcha, orig->drcha);
        if (dest->izqda != 0){
            Nodo aux = dest->izqda;
            aux->padre = dest;
            while (aux->drcha != 0) {
                aux->drcha->padre = dest;
                aux = aux->drcha;
            }
//            if (dest->drcha != 0)
//                dest->drcha->padre = dest->padre;
        }
    }
}

template <class Tbase>
int ArbolGeneral<Tbase>::contar(const nodo *n) const {
    int tam;
    if (n == 0) {
        tam = 0;
    } else {
        Nodo *aux = n;
        for (aux = aux->izqda; aux != 0; aux = aux->drcha)
            tam = contar(aux);
        tam += 1;
    }
    return tam;
}

template <class Tbase>
bool ArbolGeneral<Tbase>::soniguales(const nodo *n1, const nodo *n2) const {
    bool ret, idem = true;
    nodo *aux1, *aux2;

    if (n1 == 0 && n2 == 0)
        ret = true;
    if (n1 == 0 || n2 == 0) {
        ret = false;
    } else {
        if (n1->etiqueta != n2->etiqueta) {
            ret = false;
        } else {
            for (aux1 = n1->izqda, aux2 = n2->izqda; idem && aux1 != 0 && aux2 != 0; aux1 = aux1->drcha, aux1->drcha) {
                idem = soniguales(aux1, aux2);
            }
            ret = idem && aux1 == 0 && aux2 == 0;
        }
    }
    return ret;
}

template <class Tbase>
void ArbolGeneral<Tbase>::lee_arbol(std::istream &in, nodo *&nod) {
    char c;
    in.get(c);
    switch (c) {
        case 'x':
            nod = 0;
            break;
        case 'n':
            Tbase et;
            in>>et;
            nod = new nodo;
            nod->etiqueta = et;
            nod->padre = nod->izqda = nod->drcha = 0;
            lee_arbol(in, nod->izqda);
            lee_arbol(in, nod->drcha);
            if (nod->izqda != 0) {
                nod->izqda->padre = nod;
                Nodo aux = nod->izqda;
                while (aux->drcha != 0) {
                    aux->drcha->padre = nod;
                    aux = aux->drcha;
                }
            }
            break;
    }
}

template <class Tbase>
void ArbolGeneral<Tbase>::escribe_arbol(std::ostream &out, nodo *nod) const {
    if (nod == 0) {
        out << "x";
    } else {
        out << "n" << nod->etiqueta;
        escribe_arbol(out, nod->izqda);
        escribe_arbol(out, nod->drcha);
    }
}
