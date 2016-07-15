/* 
 * File:   conjuntopreguntas1.hxx
 * Author: usuario
 *
 * Created on 3 de noviembre de 2015, 10:36
 */

/*************************
 *  Constructor vacío.
 *************************/
ConjuntoPreguntas::ConjuntoPreguntas() {

}

/*************************
 *  Constructor Copia.
 *************************/
ConjuntoPreguntas::ConjuntoPreguntas(const ConjuntoPreguntas &cp) {
    vector<pregunta> conjunto_aux = cp.miConjuntoPreguntas;
    this->miConjuntoPreguntas = conjunto_aux;
}

/*************************
 *  Destructor por defecto.
 *************************/
ConjuntoPreguntas::~ConjuntoPreguntas() {
    if (this->miConjuntoPreguntas.size() > 0)
        this->miConjuntoPreguntas.clear();
}

/*************************
 *  Metodo de Insercción.
 *************************/
void ConjuntoPreguntas::setPregunta(const Pregunta &p) {
    pregunta pre(p.GetNumero(),p);
    this->miConjuntoPreguntas.push_back(pre);
}

/*************************
 *  Metodo de Acceso.
 *************************/
Pregunta ConjuntoPreguntas::getPregunta(const num &n) {
    Pregunta pre;
    for (ConjuntoPreguntas::num i = 0; i < this->miConjuntoPreguntas.size(); i++){
        if (n == this->miConjuntoPreguntas.at(i).first){
            pre = this->miConjuntoPreguntas.at(i).second;
            break;
        }
    }
    return pre;
}

/*************************
 *  Operador de Acceso.
 *************************/
const Pregunta &ConjuntoPreguntas::operator[](num &n) {
    return this->miConjuntoPreguntas[n].second;
}

/*************************
 *  Consultor de tamaño.
 *************************/
ConjuntoPreguntas::num ConjuntoPreguntas::Size() {
    return this->miConjuntoPreguntas.size()-1;
}

/********************************
 *  Operador de Post Incremento.
 ********************************/
ConjuntoPreguntas::iterator &ConjuntoPreguntas::iterator::operator++() {
    ++it;
    return *this;
}

ConjuntoPreguntas::const_iterator &ConjuntoPreguntas::const_iterator::operator++() {
    ++it;
    return *this;
}

/********************************
 *  Operador de Post Decremento.
 ********************************/
ConjuntoPreguntas::iterator &ConjuntoPreguntas::iterator::operator--() {
    --it;
    return *this;
}

ConjuntoPreguntas::const_iterator &ConjuntoPreguntas::const_iterator::operator--() {
    --it;
    return *this;
}

/**********************************
 *  Operador de Acceso al iterador.
 **********************************/
ConjuntoPreguntas::pregunta &ConjuntoPreguntas::iterator::operator*() {
    return *it;
}

ConjuntoPreguntas::pregunta ConjuntoPreguntas::const_iterator::operator*(){
    return *it;
}

/********************************
 *  Operador de Comparación.
 ********************************/
bool ConjuntoPreguntas::iterator::operator==(const iterator &i) const{
    return i.it == this->it;
}

bool ConjuntoPreguntas::const_iterator::operator==(const const_iterator &i) const{
    return i.it == this->it;
}

/********************************
 *  Operador de Desigualdad.
 ********************************/
bool ConjuntoPreguntas::iterator::operator!=(const iterator &i) const{
    return i.it != this->it;
}

bool ConjuntoPreguntas::const_iterator::operator!=(const const_iterator &i) const{
    return i.it != this->it;
}

/********************************
 *  Iterador del Primer elemento.
 ********************************/
ConjuntoPreguntas::iterator ConjuntoPreguntas::begin() {
    ConjuntoPreguntas::iterator i;
    i.it = this->miConjuntoPreguntas.begin();
    return i;
}

ConjuntoPreguntas::const_iterator ConjuntoPreguntas::begin() const {
    ConjuntoPreguntas::const_iterator i;
    i.it = this->miConjuntoPreguntas.begin();
    return i;
}

/********************************
 *  Iterador del Ultimo elemento
 ********************************/
ConjuntoPreguntas::iterator ConjuntoPreguntas::end() {
    ConjuntoPreguntas::iterator i;
    i.it = this->miConjuntoPreguntas.end();
    return i;
}

ConjuntoPreguntas::const_iterator ConjuntoPreguntas::end() const {
    ConjuntoPreguntas::const_iterator i;
    i.it = this->miConjuntoPreguntas.end();
    return i;
}

/*************************
 *  Operador de Lectura.
 *************************/
istream &operator>>(istream &is, ConjuntoPreguntas &cp) {
    /** Pongo cantidad como string en vez de un char por si las respuestas son mas de 9 */
    string pregunta, aux, cantidad;
    while (!is.eof()) {
        getline(is, pregunta);
        getline(is, cantidad);
        ConjuntoPreguntas::num n = atoi(cantidad.c_str());
        pregunta += "\n" + cantidad;
        for (ConjuntoPreguntas::num i = 0; i < n; i++) {
            getline(is, aux);
            pregunta += "\n" + aux;
        }
        /** Genero la pregunta para insertarla*/
        Pregunta P = Pregunta(pregunta);
        cp.setPregunta(P);
    }
    return is;
}

/***************************
 *  Operador de Impresión.
 ***************************/
ostream &operator<<(ostream &os, const ConjuntoPreguntas &cp) {
    for (ConjuntoPreguntas::num z = 0; z < cp.miConjuntoPreguntas.size(); z++)
        os << cp.miConjuntoPreguntas[z].second;
    return os;
}