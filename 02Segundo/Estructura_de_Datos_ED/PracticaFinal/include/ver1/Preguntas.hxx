/* 
 * File:   Preguntas.hxx
 *
 * Created on 24 de diciembre de 2015, 15:21
 */
/*
 * Esta funcion la he puesto para que a la hora de elegir como
 * quiero imprimir las preguntas me sea fácil cambiar la manera
 * basta con descomentar las lineas de codigo que quiero que 
 * se pinten.
 */
string Preguntas::imprime(const pair<int, string> &p) {
    stringstream ss;
    ss << p.first;
    string aux;
    //aux += ss.str();
    //aux += ".-";
    aux += p.second;
    return aux;
}

Preguntas::Preguntas() {

}

Preguntas::Preguntas(int n, string &p) {
    pair<int, string> aux;
    aux.first = n;
    aux.second = p;
    this->bd_preguntas.insert(aux);
}

Preguntas::Preguntas(Preguntas &preguntas) {
    this->bd_preguntas = preguntas.bd_preguntas;
}

Preguntas::~Preguntas() {
    this->bd_preguntas.clear();
}

void Preguntas::SetPregunta(int n, string &p) {
    pair<int, string> aux;
    aux.first = n;
    aux.second = p;
    this->bd_preguntas.insert(aux);
}

string Preguntas::GetPregunta(int n) {
    pair<int, string> mipre = *this->bd_preguntas.find(n);
    string pregunta = imprime(mipre);
    if (mipre.second == "")
        pregunta = "No existe pregunta...";
    return pregunta;
}

void Preguntas::DelPregunta(int n){
    this->bd_preguntas.erase(this->bd_preguntas.find(n));
}

int Preguntas::NumPreguntas() {
    return this->bd_preguntas.size();
}

std::istream &operator>>(std::istream &in, Preguntas &pre) {
    string cadena;
    while (!isdigit(in.peek())) {
        in.get();
    }
    getline(in, cadena, '\n');
    int num_preg = atoi(cadena.c_str());
    for (int c = 1; c <= num_preg; c++) {
        getline(in, cadena, '\n');
        pre.SetPregunta(c, cadena);
    }
    return in;
}

std::ostream &operator<<(std::ostream &out, Preguntas &pre) {
    //Usamos el iterador de la clase por que es mas fácil
    Preguntas::iterator myit = pre.begin();
    while (myit != pre.end()) {
        out << myit.getNumero() << ".-" << *myit << endl;
        ++myit;
    }
    return out;
}

Preguntas::iterator::iterator() {
    //@TODO
}

const string &Preguntas::iterator::operator*() {
    return (*this->it).second;
}

const int &Preguntas::iterator::getNumero(){
    return (*this->it).first;
}

Preguntas::iterator &Preguntas::iterator::operator++() {
    ++this->it;
    return *this;
}

Preguntas::iterator &Preguntas::iterator::operator--() {
    --this->it;
    return *this;
}

bool Preguntas::iterator::operator==(const Preguntas::iterator &i) {
    return this->it == i.it;
}

bool Preguntas::iterator::operator!=(const Preguntas::iterator &i) {
    return this->it != i.it;
}

Preguntas::iterator Preguntas::begin() {
    Preguntas::iterator itaux;
    itaux.it = this->bd_preguntas.begin();
    return itaux;
}

Preguntas::iterator Preguntas::end() {
    Preguntas::iterator itaux;
    itaux.it = this->bd_preguntas.end();
    return itaux;
}