/* 
 * File:   Jugador.hxx
 *
 * Created on 26 de diciembre de 2015, 11:50
 */

void Jugador::toUpper(string &cadena) {
    for (unsigned int i = 0; i < cadena.length(); i++)
        cadena[i] = toupper(cadena[i]);
}

Jugador::Jugador() {
    this->nombre = "Maquina";
    this->tipo = false;
}

Jugador::Jugador(Personas pers, Preguntas preg) {
    this->nombre = "Maquina";
    this->posibles_personajes = pers;
    this->posibles_preguntas = preg;
    this->tipo = false;
}

Jugador::Jugador(Jugador &p) {
    this->nombre = p.nombre;
    this->personaje = p.personaje;
    this->posibles_personajes = p.posibles_personajes;
    this->posibles_preguntas = p.posibles_preguntas;
    this->tipo = p.tipo;
}

Jugador::~Jugador() {
    this->personaje.~Persona();
    this->posibles_personajes.~Personas();
    this->posibles_preguntas.~Preguntas();
}

void Jugador::setNombre(const string &n) {
    this->nombre = n;
}

const string Jugador::getNombre() {
    return this->nombre;
}

void Jugador::setPersona(Persona &per) {
    this->personaje = per;
}

Persona Jugador::getPersona() {
    return this->personaje;
}

void Jugador::setHumano(){
    this->tipo = true;
}

bool Jugador::esHumano() {
    return this->tipo;
}

void Jugador::setPreguntaUsada(int n, string &r) {
    char respu = '0';
    Personas copia(this->posibles_personajes);
    /*
     * Tienes que eliminar la pregunta del conjuto de preguntas del jugador
     * y con la respuesta tienes que recorrer los personajes y eliminar los
     * personajes que no coincdan con la respuesta dada.
     * Es como si actualizaras todos los datos del jugador para poder
     * calcular la entropia cuando se realice la siguiente pregunta
     */
    this->posibles_preguntas.DelPregunta(n);
    if (r == "SI" || r == "si" || r == "sI" || r == "Si"){
        respu = '1';
    }
    Personas::iterator it;
    for (it = copia.begin();it != copia.end();++it){
        if ((*it).GetCodigo()[n-1] != respu)
            this->posibles_personajes.DelPersona((*it).GetNumero());
    }
}

Preguntas &Jugador::getPreguntas() {
    return this->posibles_preguntas;
}

const int Jugador::elegirPregunta() {
    float count_si = 0, count_no = 0, num_pregunta = 1;
    float max_entropia = 0.0, entropia = 0.0;
    float total_personajes = this->posibles_personajes.Size();
    Preguntas::iterator it_pregu;
    Personas::iterator it_perso;
    // recorremos las preguntas del jugador
    for (it_pregu = this->posibles_preguntas.begin();it_pregu != this->posibles_preguntas.end(); ++it_pregu){
        // recorremos los personajes del jugador
        for (it_perso = this->posibles_personajes.begin(); it_perso != this->posibles_personajes.end(); ++it_perso){
            // pongo menos 1 por que el codigo empieza en 0 y las preguntas en 1
            if ((*it_perso).GetCodigo()[it_pregu.getNumero()-1] == '1')
                count_si++;
            else
                count_no++;
        }
        // comienza el cálculo de la entropia
        //float p_si = count_si/total_personajes, p_no = count_no/total_personajes;
        //float l_si = log2(p_si), l_no = log2(p_no);
        //float suma1 = p_si*l_si, suma2 = p_no*l_no;
        //entropia = -(suma1+suma2);
        entropia = -(((count_no/total_personajes)*log2(count_no/total_personajes))+((count_si/total_personajes)*log2(count_si/total_personajes)));
        //cout << "Este es el valor de la entropia: " << entropia << " para la pregunta:" << *it_pregu << endl;
        if (entropia>max_entropia){
            max_entropia = entropia;
            num_pregunta = it_pregu.getNumero();
        }else if(this->posibles_personajes.Size() == 1){
            it_pregu = this->posibles_preguntas.end();
            --it_pregu;
            num_pregunta = it_pregu.getNumero() + 1;
        }
        count_si = 0;
        count_no = 0;
    }
    
    return num_pregunta;
}

const string Jugador::responderPregunta(int &n) {
    string respu = "No";
    // le ponemos n-1 por que las preguntas empiezan en 1 y las respuestas en 0
    char aux = this->personaje.GetCodigo()[n - 1];
    if (aux == '1')
        respu = "Si";
    return respu;
}

const string Jugador::resuelvePersonaje() {
    Personas::iterator it = this->posibles_personajes.begin();
    return (*it).GetNombre();
}

bool Jugador::Resolver(string &perso) {
    string orig = this->personaje.GetNombre();
    toUpper(orig);
    toUpper(perso);
    return orig == perso;
}

std::istream &operator>>(std::istream &in, Jugador &jug) {
    //@TODO
    return in;
}

std::ostream &operator<<(std::ostream &out, Jugador &jug) {
//    out << "Jugador Nombre: " << jug.nombre << endl
//        << "Personaje: " << jug.personaje << endl
//        << "Personajes no descartados: " << jug.posibles_personajes << endl
//        << "Preguntas no realizadas: " << jug.posibles_preguntas << endl;
    return out;
}