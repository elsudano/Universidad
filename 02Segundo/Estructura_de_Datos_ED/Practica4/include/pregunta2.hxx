/* 
 * File:   pregunta2.hxx
 * Author: Carlos de la Torre
 *
 * Created on 3 de Diciembre de 2015, 13:06
 */

/*************************
 *  Constructor vacío.
 *************************/
Pregunta::Pregunta() {
    this->Num_preg = 0;
    this->Enunciado = "";
}

/****************************
 *  Constructor de copia.
 ****************************/
Pregunta::Pregunta(const Pregunta &p) {
    //@TODO Comprobar que está bien el método
    num aux_num = p.Num_preg;
    string aux_enu = p.Enunciado;
    vector<respuesta> aux_respu = p.Respuestas;
    this->Num_preg = aux_num;
    this->Enunciado = aux_enu;
    this->Respuestas = aux_respu;
}

/*******************************
 *  Constructor con parámetros.
 *******************************/
Pregunta::Pregunta(const string &p) {
    string cadena_aux = p, mi_respuesta;
    string letra;
    string::size_type inicio = 0;
    string::size_type fin;
    respuesta aux_resp;
    locale loc; //< esto es para convertir la letra a mayusculas
    fin = cadena_aux.find(".- ", inicio);
    /* Me posiciono en el final del numero de pregunta */
    this->Num_preg = atoi(cadena_aux.substr(inicio, fin - inicio).c_str());
    inicio = fin + 3;
    fin = cadena_aux.find("\n", inicio);
    /* Me posiciono en el final del Enunciado */
    this->Enunciado = cadena_aux.substr(inicio, fin - inicio);
    inicio = fin + 1;
    fin = cadena_aux.find("\n", inicio);
    /* Me posiciono en el final de la cantidad de respuestas */
    num cantidad_respuestas = atoi(cadena_aux.substr(inicio, fin - inicio).c_str());
    inicio = fin + 1;
    cadena_aux = cadena_aux.substr(inicio, cadena_aux.size() - inicio);
    /* La cadena auxiliar contiene solo las respuestas antes del for */
    for (num i = 0; i < cantidad_respuestas; i++) {
        /*
         * Lo que hago aqui es trocear la cadena en dos para poner la letra de
         * la respuesta y la respuesta por separado asi luego será mas fácil
         * compararlo con las respuestas
         */
        inicio = 0;
        fin = cadena_aux.find(") ", inicio);
        letra = cadena_aux.substr(inicio, fin - inicio)[0]; //< Esta es la primera parte de la respuesta que es la letra
        //letra = toupper(cadena_aux.substr(inicio, fin - inicio)[0], loc); //< Esta es la primera parte de la respuesta que es la letra
        inicio += 3; //< 3 por que el anterior fin tenia la cadena ') ' y la letra
        fin = cadena_aux.find("\n", inicio); //< para que llegue hasta antes de la siguiente respuesta
        mi_respuesta = cadena_aux.substr(inicio, fin - inicio); //< esta es la segunda parte de la respuesta que es la definición
        aux_resp = pair<string, string>(letra, mi_respuesta);
        this->Respuestas.push_back(aux_resp);
        inicio = fin + 1; //< quitamos de la cadena auxiliar la respuesta ya introducida
        cadena_aux = cadena_aux.substr(inicio, cadena_aux.size()); //< recortamos la cadena para seguir usandola
    }
}

/****************************
 *  Destructor por defecto
 ****************************/
Pregunta::~Pregunta() {
    if (this->Respuestas.size() > 0)
        this->Respuestas.clear();
}

/************************************
 *  Consultor de numero de Pregunta
 ***********************************/
Pregunta::num Pregunta::GetNumero() const {
    return this->Num_preg;
}

/************************************
 *  Consultor de respuesta de Pregunta
 ***********************************/
string Pregunta::GetAnswer(num pos) const{
    return this->Respuestas.at(pos).first + ") " + this->Respuestas.at(pos).second + "\n";
}

/****************************
 *  Operador de Asignación
 ****************************/
Pregunta &Pregunta::operator=(const Pregunta &p) {
    this->Enunciado = p.Enunciado;
    this->Num_preg = p.Num_preg;
    this->Respuestas = p.Respuestas;
    return *this;
}

/****************************
 *  Operador de comparación
 ****************************/
bool Pregunta::operator==(const Pregunta &p) {
    bool ret = true;
    if (this->Num_preg != p.Num_preg)
        ret = false;
    if (this->Enunciado != p.Enunciado && ret)
        ret = false;
    if (this->Respuestas.size() != p.Respuestas.size() && ret)
        ret = false;
    for (num i = 0; i < p.Respuestas.size(); i++)
        if (this->Respuestas[i] != p.Respuestas[i] && ret)
            ret = false;
    return ret;
}

/****************************
 *  Operador de Lectura
 ****************************/
istream &operator>>(istream &is, Pregunta &p) {
    string pregunta;
    is >> pregunta;
    p = Pregunta(pregunta);
    return is;
}

/****************************
 *  Operador de impresión
 ****************************/
ostream &operator<<(ostream &os, const Pregunta &p) {
    stringstream numero, cantidad;
    numero << p.Num_preg;
    cantidad << p.Respuestas.size();
    //string ret = numero.str() + ".- " + p.Enunciado + "\n" + cantidad.str() + "\n";
    string ret = p.Enunciado + "\n" + cantidad.str() + "\n";
    for (Pregunta::num i = 0; i < p.Respuestas.size(); i++)
        ret = ret + p.Respuestas[i].first + ") " + p.Respuestas[i].second + "\n";
    return os << ret;
}