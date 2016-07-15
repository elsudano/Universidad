/* 
 * File:   arbolGeneralIteratorConst.hxx
 *
 * Created on 12 de diciembre de 2015, 14:21
 */

/*----------------------------Iterator Constante------------------------------*/

template <class Tbase>
ArbolGeneral<Tbase>::const_iter_preorden::const_iter_preorden(const Nodo &n){
    this->it = n;
    this->raiz = n;
    this->level = 0;
}

template <class Tbase>
const Tbase &ArbolGeneral<Tbase>::const_iter_preorden::operator*(){
    return this->it->etiqueta;
}

template <class Tbase>
int ArbolGeneral<Tbase>::const_iter_preorden::getlevel()const{
    return this->level;
}

template <class Tbase>
typename ArbolGeneral<Tbase>::const_iter_preorden &ArbolGeneral<Tbase>::const_iter_preorden::operator++(){
    if (it->izqda != 0) {
        it = it->izqda;
        level = level + 1;
    } else {
        if (it->drcha != 0)
            it = it->drcha;
        else {
            if (it->padre != 0) {
                while (it->padre != 0 && it->drcha == 0) {
                    it = it->padre;
                    level = level - 1;
                }
                it = it->drcha;

            } else {
                it = 0;
                level = -1;
            }
        }
    }
    return *this;
}

template <class Tbase>
bool ArbolGeneral<Tbase>::const_iter_preorden::operator==(const const_iter_preorden &i){
    return this->it->etiqueta == i.it->etiqueta &&
            this->it->padre == i.it->padre &&
            this->it->izqda == i.it->izqda &&
            this->it->drcha == i.it->drcha;
}

template <class Tbase>
bool ArbolGeneral<Tbase>::const_iter_preorden::operator!=(const const_iter_preorden &i){
    return this->it->etiqueta != i.it->etiqueta ||
            this->it->padre != i.it->padre ||
            this->it->izqda != i.it->izqda ||
            this->it->drcha != i.it->drcha;
}

template <class Tbase>
bool ArbolGeneral<Tbase>::const_iter_preorden::Hoja(){
    return this->it->izqda == 0;
}

template <class Tbase>
const typename ArbolGeneral<Tbase>::Nodo ArbolGeneral<Tbase>::const_iter_preorden::GetNodo(){
    return this->it;
}