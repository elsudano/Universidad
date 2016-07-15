/* 
 * File:   Personas.hxx
 *
 * Created on 22 de diciembre de 2015, 15:16
 */

Personas::Personas() {
    //@TODO
}

Personas::Personas(const int &n, const Persona &p) {
    pair<int, Persona> aux;
    aux.first = n;
    aux.second = p;
    this->bd_personas.insert(aux);
}

Personas::Personas(const Personas &p) {
    this->bd_personas = p.bd_personas;
}

Personas::~Personas() {
    this->bd_personas.clear();
}

void Personas::SetPersona(const int &n, const Persona &p) {
    pair<int, Persona> aux;
    aux.first = n;
    aux.second = p;
    this->bd_personas.insert(aux);
}

Persona &Personas::GetPersona(const int &n) {
    return (*this->bd_personas.find(n)).second;
}

void Personas::DelPersona(const int &n){
    this->bd_personas.erase(this->bd_personas.find(n));
}

unsigned int Personas::Size(){
    return this->bd_personas.size();
}

std::istream &operator>>(std::istream &in, Personas &pers) {
    string num, nombre, ruta;
    char caracter;
    while (!isdigit(in.peek())) {
        in.get();
    }
    getline(in, num, '\n');
    int num_perso = atoi(num.c_str());
    for (int c = 0; c < num_perso; c++) {
        caracter = in.peek();
        while (caracter == '#' || caracter == '\n') {
            in.ignore(500, '\n');
            caracter = in.peek();
        }
        getline(in, nombre, '\n');
        getline(in, ruta, '\n');
        Persona miPerso(c, nombre, ruta);
        pers.SetPersona(c, miPerso);
    }
    return in;
}

std::ostream &operator<<(std::ostream &out, Personas &pers) {
    //Usamos el iterador de la clase por que es mas fácil
    Personas::iterator myit = pers.begin();
    while (myit != pers.end()) {
        out << *myit << endl;
        ++myit;
    }
    return out;
}

Personas::iterator::iterator() {
    //@TODO
}

const Persona &Personas::iterator::operator*() {
    return (*this->it).second;
}

Personas::iterator &Personas::iterator::operator++() {
    ++this->it;
    return *this;
}

Personas::iterator &Personas::iterator::operator--() {
    --this->it;
    return *this;
}

bool Personas::iterator::operator==(const iterator &i) {
    return i.it == this->it;
}

bool Personas::iterator::operator!=(const iterator &i) {
    return i.it != this->it;
}

Personas::iterator Personas::begin() {
    Personas::iterator i;
    i.it = this->bd_personas.begin();
    return i;
}

Personas::iterator Personas::end() {
    Personas::iterator i;
    i.it = this->bd_personas.end();
    return i;
}