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
    this->cant_pregu = 0;
    this->reservado = 1;
    this->miConjuntoPreguntas = new Pregunta[reservado];
}

/*************************
 *  Constructor Copia.
 *************************/
ConjuntoPreguntas::ConjuntoPreguntas(const ConjuntoPreguntas &cp) {
    this->cant_pregu = CP.cant_pregu;
    this->reservado = CP.reservado;
    this->miConjuntoPreguntas = CP.miConjuntoPreguntas;
}

/*************************
 *  Destructor por defecto.
 *************************/
ConjuntoPreguntas::~ConjuntoPreguntas() {
    if (this->reservado < 1)
        delete []this->miConjuntoPreguntas;
}

/*************************
 *  Metodo de Insercción.
 *************************/
void ConjuntoPreguntas::setPregunta(const Pregunta &p) {
    this->Redimencionar(this->reservado + 1);
    this->miConjuntoPreguntas[this->cant_pregu] = P;
    this->cant_pregu++;
}

/*************************
 *  Metodo de Acceso.
 *************************/
Pregunta ConjuntoPreguntas::getPregunta(const num &n) {
    return this->miConjuntoPreguntas[N];
}

/*************************
 *  Operador de Acceso.
 *************************/
const Pregunta &ConjuntoPreguntas::operator[](num &n) {
    return this->miConjuntoPreguntas[N];
}

/*************************
 *  Consultor de tamaño.
 *************************/
ConjuntoPreguntas::num ConjuntoPreguntas::Size() {
    return this->cant_pregu;
}

/*************************
 *  Función Auxiliar.
 *************************/
void ConjuntoPreguntas::Redimencionar(const num &n) {
    Pregunta *aux = new Pregunta[N];
    for (ConjuntoPreguntas::num j = 0; j<this->cant_pregu; j++)
        aux[j] = this->miConjuntoPreguntas[j];
    delete []this->miConjuntoPreguntas;
    this->miConjuntoPreguntas = aux;
    this->reservado = N;
}

/********************************
 *  Operador de Post Incremento.
 ********************************/
ConjuntoPreguntas::iterator &ConjuntoPreguntas::iterator::operator++(){
    
}

/********************************
 *  Operador de Post Decremento.
 ********************************/
ConjuntoPreguntas::iterator &ConjuntoPreguntas::iterator::operator--(){
    
}

/**********************************
 *  Operador de Acceso al iterador.
 **********************************/
Pregunta &ConjuntoPreguntas::iterator::operator*(){
    
}

/********************************
 *  Operador de Comparación.
 ********************************/
bool ConjuntoPreguntas::iterator::operator==(const iterator &i){
    
}

/********************************
 *  Operador de Desigualdad.
 ********************************/
bool ConjuntoPreguntas::iterator::operator!=(const iterator &i){
    
}

/********************************
 *  Iterador del Primer elemento.
 ********************************/
ConjuntoPreguntas::iterator ConjuntoPreguntas::begin(){
    
}

/********************************
 *  Iterador del Ultimo elemento
 ********************************/
ConjuntoPreguntas::iterator ConjuntoPreguntas::end(){
    
}

/*************************
 *  Operador de Lectura.
 *************************/
istream &operator>>(istream &is, ConjuntoPreguntas &cp) {
    string enunciado;
    string cantidad; /** Pongo un string por si las respuestas son mas de 9 */
    string respuestas, aux;
    Pregunta P;

    while (!is.eof()) {
        getline(is, enunciado);
        getline(is, cantidad);
        ConjuntoPreguntas::num n = atoi(cantidad.c_str());
        for (ConjuntoPreguntas::num i = 0; i < n; i++) {
            getline(is, aux);
            respuestas += aux + '\n';
        }
        /** Genero la pregunta para insertarla*/
        P = Pregunta(enunciado, n, respuestas);

        cp.setPregunta(P);
        respuestas = "";
    }
    return is;
}

/***************************
 *  Operador de Impresión.
 ***************************/
ostream &operator<<(ostream &os, const ConjuntoPreguntas &cp) {
    for (ConjuntoPreguntas::num z = 1; z < cp.cant_pregu; z++)
        os << cp.miConjuntoPreguntas[z];
    return os;
}