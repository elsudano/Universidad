/* 
 * File:   ArbolGeneralIterator1.hxx
 * Author: Carlos de la Torre
 *
 * Created on 12 de diciembre de 2015, 14:17
 */

/*------------------------------Iterator--------------------------------------*/

template <class Tbase>
ArbolGeneral::iter_preorden::iter_preorden() {

}

template <class Tbase>
Tbase &ArbolGeneral::iter_preorden::operator*() {

}

template <class Tbase>
int ArbolGeneral::iter_preorden::getlevel()const {

}

template <class Tbase>
ArbolGeneral::iter_preorden &ArbolGeneral::iter_preorden::operator++() {
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
bool ArbolGeneral::iter_preorden::operator==(const iter_preorden &i) {

}

template <class Tbase>
bool ArbolGeneral::iter_preorden::operator!=(const iter_preorden &i) {

}