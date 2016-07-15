/* 
 * File:   pregunta1.hxx
 * Author: usuario
 *
 * Created on 3 de noviembre de 2015, 13:06
 */

/*************************
 *  Constructor vacío.
 *************************/
Pregunta::Pregunta(){
    this->Enunciado = "";
    this->Respuestas = "";
    this->cant_respu = 0;
}

/****************************
 *  Constructor de copia.
 ****************************/
Pregunta::Pregunta(const Pregunta& P){
    this->Enunciado = P.Enunciado;
    this->Respuestas = P.Respuestas;
    this->cant_respu = P.cant_respu;
}

/*******************************
 *  Constructor con parámetros.
 *******************************/
Pregunta::Pregunta(Pregunta::enunciado E, Pregunta::size_type N, Pregunta::respuestas R){
    this->Enunciado = E;
    this->cant_respu = N;
    this->Respuestas = R;
}

/****************************
 *  Destructor por defecto
 ****************************/
Pregunta::~Pregunta(){
    
}

/****************************
 *  Operador de Asignación
 ****************************/
Pregunta& Pregunta::operator=(const string& P){
    // TODO
    // Tienes que implementarlo para poder 
    // insertar una pregunta desde una cadena
}

/****************************
 *  Operador de comparación
 ****************************/
bool Pregunta::operator==(const Pregunta& P){
    bool respuesta=true;
    if (Enunciado.compare(P.Enunciado) != 0)
        respuesta = false;
    if (cant_respu != P.cant_respu)
        respuesta = false;
    if (Respuestas.compare(P.Respuestas) != 0)
        respuesta = false;
    return respuesta;
}

/****************************
 *  Operador de Lectura
 ****************************/
istream& operator>>(istream& IS, Pregunta& P){
    Pregunta::enunciado enunciado;
    Pregunta::respuestas respuestas;
    Pregunta::size_type n;
    char separador = '\n';
    IS >> enunciado >> separador >> n >> separador >> respuestas;
    P = Pregunta(enunciado,n,respuestas);
    return IS;
}

/****************************
 *  Operador de impresión
 ****************************/
ostream& operator<<(ostream& OS, const Pregunta& P){
    return OS << P.Enunciado << endl << P.cant_respu << endl << P.Respuestas << endl;
}