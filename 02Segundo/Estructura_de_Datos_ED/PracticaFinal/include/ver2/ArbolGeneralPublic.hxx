/* 
 * File:   ArbolGeneralPublic1.hxx
 * Author: Carlos de la Torre
 *
 * Created on 12 de diciembre de 2015, 14:20
 */

/*--------------------------------Public--------------------------------------*/

template <class Tbase>
ArbolGeneral::ArbolGeneral() {

}

template <class Tbase>
ArbolGeneral::ArbolGeneral(const Tbase& e) {

}

template <class Tbase>
ArbolGeneral::ArbolGeneral(const ArbolGeneral<Tbase>& v) {

}

template <class Tbase>
ArbolGeneral::~ArbolGeneral() {

}

template <class Tbase>
ArbolGeneral<Tbase> &ArbolGeneral::operator=(const ArbolGeneral<Tbase> &v) {

}

template <class Tbase>
void ArbolGeneral::AsignaRaiz(const Tbase& e) {

}

template <class Tbase>
ArbolGeneral::Nodo ArbolGeneral::raiz() const {

}

template <class Tbase>
ArbolGeneral::Nodo ArbolGeneral::hijomasizquierda(const ArbolGeneral::Nodo n) const {

}

template <class Tbase>
ArbolGeneral::Nodo ArbolGeneral::hermanoderecha(const ArbolGeneral::Nodo n) const {

}

template <class Tbase>
ArbolGeneral::Nodo ArbolGeneral::padre(const ArbolGeneral::Nodo n) const {

}

template <class Tbase>
Tbase& ArbolGeneral::etiqueta(const ArbolGeneral::Nodo n) {

}

template <class Tbase>
const Tbase& ArbolGeneral::etiqueta(const ArbolGeneral::Nodo n) const {

}

template <class Tbase>
void ArbolGeneral::asignar_subarbol(const ArbolGeneral<Tbase>& orig, const ArbolGeneral::Nodo nod) {

}

template <class Tbase>
void ArbolGeneral::podar_hijomasizquierda(ArbolGeneral::Nodo n, ArbolGeneral<Tbase>& dest) {

}

template <class Tbase>
void ArbolGeneral::podar_hermanoderecha(ArbolGeneral::Nodo n, ArbolGeneral<Tbase>& dest) {

}

template <class Tbase>
void ArbolGeneral::insertar_hijomasizquierda(ArbolGeneral::Nodo n, ArbolGeneral<Tbase>& rama) {

}

template <class Tbase>
void ArbolGeneral::insertar_hermanoderecha(ArbolGeneral::Nodo n, ArbolGeneral<Tbase>& rama) {

}

template <class Tbase>
void ArbolGeneral::clear() {

}

template <class Tbase>
int ArbolGeneral::size() const {

}

template <class Tbase>
bool ArbolGeneral::empty() const {

}

template <class Tbase>
bool ArbolGeneral::operator==(const ArbolGeneral<Tbase>& v) const {

}

template <class Tbase>
bool ArbolGeneral::operator!=(const ArbolGeneral<Tbase>& v) const {

}

template<class T>
friend std::istream& operator>>(std::istream& in, ArbolGeneral<T>& v) {

}

template<class T>
friend std::ostream& operator<<(std::ostream& out, const ArbolGeneral<T>& v) {

}