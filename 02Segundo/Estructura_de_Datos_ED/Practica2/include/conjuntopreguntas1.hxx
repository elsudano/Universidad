/* 
 * File:   conjuntopreguntas1.hxx
 * Author: usuario
 *
 * Created on 3 de noviembre de 2015, 10:36
 */

/*************************
 *  Constructor vacío.
 *************************/
ConjuntoPreguntas::ConjuntoPreguntas(){
    this->cant_pregu = 0;
    this->reservado = 1;
    this->miConjuntoPreguntas = new Pregunta[reservado];
}

/*************************
 *  Constructor Copia.
 *************************/
ConjuntoPreguntas::ConjuntoPreguntas(const ConjuntoPreguntas &CP){
    this->cant_pregu = CP.cant_pregu;
    this->reservado = CP.reservado;
    this->miConjuntoPreguntas = CP.miConjuntoPreguntas;
}

/*************************
 *  Destructor por defecto.
 *************************/
ConjuntoPreguntas::~ConjuntoPreguntas(){
    if (this->reservado<1)
        delete []this->miConjuntoPreguntas;
}

/*************************
 *  Metodo de Insercción.
 *************************/
void ConjuntoPreguntas::setPregunta(const Pregunta P){
    this->Redimencionar(this->reservado+1);
    this->miConjuntoPreguntas[this->cant_pregu] = P;
    this->cant_pregu++;
}

/*************************
 *  Metodo de Acceso.
 *************************/
Pregunta ConjuntoPreguntas::getPregunta(const size_type &N){
    return this->miConjuntoPreguntas[N];
}

/*************************
 *  Operador de Acceso.
 *************************/
const Pregunta &ConjuntoPreguntas::operator[](size_type& N){
    return this->miConjuntoPreguntas[N];
}

/*************************
 *  Consultor de tamaño.
 *************************/
ConjuntoPreguntas::size_type ConjuntoPreguntas::Size(){
    return this->cant_pregu;
}

/*************************
 *  Función Auxiliar.
 *************************/
void ConjuntoPreguntas::Redimencionar(const size_type& N){
    Pregunta *aux = new Pregunta[N];
    for (ConjuntoPreguntas::size_type j=0;j<this->cant_pregu;j++)
        aux[j]=this->miConjuntoPreguntas[j];
    delete []this->miConjuntoPreguntas;
    this->miConjuntoPreguntas = aux;
    this->reservado = N;
}

/*************************
 *  Operador de Lectura.
 *************************/
istream& operator>>(istream& IS, ConjuntoPreguntas& CP){
    string enunciado;
    string cantidad; /** Pongo un string por si las respuestas son mas de 9 */
    string respuestas, aux;
    Pregunta P;
    
    while (!IS.eof()){
        getline(IS,enunciado);
        getline(IS,cantidad);
        ConjuntoPreguntas::size_type n = atoi(cantidad.c_str());
        for (ConjuntoPreguntas::size_type i=0;i<n;i++){
            getline(IS,aux);
            respuestas += aux + '\n';
        }
        /** Genero la pregunta para insertarla*/
        P = Pregunta(enunciado,n,respuestas);
        
        CP.setPregunta(P);
        respuestas="";
    }
    return IS;
}

/***************************
 *  Operador de Impresión.
 ***************************/
ostream& operator<<(ostream& OS, const ConjuntoPreguntas& CP){
    for (ConjuntoPreguntas::size_type z=1;z<CP.cant_pregu;z++)
        OS << CP.miConjuntoPreguntas[z];
    return OS;
}