/* 
 * File:   test2.hxx
 * Author: Carlos de la Torre
 *
 * Created on 4 de diciembre de 2015, 13:24
 */

/*************************
 *  Constructor vacío.
 *************************/
Test::Test() {

}

/*************************
 *  Constructor copia.
 *************************/
Test::Test(const Test &t) {
    vector<Pregunta> test_aux = t.preguntasTest;
    this->preguntasTest = test_aux;
}

/*************************
 *  Destructor por defecto.
 *************************/
Test::~Test() {
    if (this->preguntasTest.size() > 0)
        this->preguntasTest.clear();
}

/***************************
 *  Constructor parametros.
 ***************************/
Test::Test(ConjuntoPreguntas &cp, num np) {
    /* Inicializamos la semilla */
    srand(time(NULL));
    /* Generamos primer aleatorio  */
    num aleatorio = (rand() % cp.Size() - 1) + 1;
    /* Array de numeros de pregunta aleatorios */
    num num_aleatorios[np];

    for (num n = 0; n < np; n++) {
        /* El siguiente for es para comprobar preguntas repertidas*/
        for (num i = 0; i < np; i++) {
            if (num_aleatorios[i] == aleatorio) {
                aleatorio = (rand() % cp.Size() - 1) + 1;
                i = 0;
            }
        }
        /*Como el numero no esta en el vector lo metemos*/
        num_aleatorios[n] = aleatorio;
        /*Generamos siguiente aleatorio*/
        aleatorio = (rand() % cp.Size() - 1) + 1; /** generamos los siguientes */
    }

    for (num p = 0; p < np; p++) {
        /**
         * Recorremos el array de numeros aleatorios y insertamos la pregunta
         * dentro del vector de Test una detras de otra.
         */
        this->preguntasTest.push_back(cp.getPregunta(num_aleatorios[p]));
    }
}

/***************************
 *  Constructor parametros.
 ***************************/
Test::Test(ConjuntoPreguntas &cp, int np, set<int> &nr) {
    /* Inicializamos la semilla */
    srand(time(NULL));
    /* Generamos primer aleatorio
     * Lo hacemos así para no coger el 0 y que tampoco se
     * pase del tamaño total de las posibles preguntas
     * cp.Size()-1 < quitamos la ultima pregunta posible
     * rand() % lo anterior + 1 < empezamos a partir del 1 hasta el final
     */
    int aleatorio = (rand() % cp.Size() - 1) + 1;
    /* Array de numeros de pregunta aleatorios */
    int num_aleatorios[np];

    for (int n = 0; n < np; n++) {
        /* El siguiente for es para comprobar preguntas repertidas*/
        while (*nr.find(aleatorio) == aleatorio || aleatorio == 0)
            aleatorio = (rand() % cp.Size() - 1) + 1;
        /* Como el numero no esta en los usados lo metemos */
        num_aleatorios[n] = aleatorio;
        /* Y tambien en lo ya usados */
        nr.insert(aleatorio);
        /* Generamos siguiente aleatorio */
        aleatorio = (rand() % cp.Size() - 1) + 1; /** generamos los siguientes */
    }

    for (int p = 0; p < np; p++) {
        /**
         * Recorremos el array de numeros aleatorios y insertamos la pregunta
         * dentro del vector de Test una detras de otra.
         */
        this->preguntasTest.push_back(cp.getPregunta(num_aleatorios[p]));
    }
}

/*************************
 *  Consultor de Tamaño.
 *************************/
Test::num Test::Size() {
    return this->preguntasTest.size();
}

/********************************
 *  Operador de Post Incremento.
 ********************************/
Test::iterator &Test::iterator::operator++() {
    ++it;
    return *this;
}

Test::const_iterator &Test::const_iterator::operator++() {
    ++it;
    return *this;
}

/********************************
 *  Operador de Post Decremento.
 ********************************/
Test::iterator &Test::iterator::operator--() {
    --it;
    return *this;
}

Test::const_iterator &Test::const_iterator::operator--() {
    --it;
    return *this;
}

/**********************************
 *  Operador de Acceso al iterador.
 **********************************/
Pregunta &Test::iterator::operator*() {
    return *it;
}

Pregunta Test::const_iterator::operator*() const {
    return *it;
}

/********************************
 *  Operador de Comparación.
 ********************************/
bool Test::iterator::operator==(const iterator &i) const {
    return i.it == this->it;
}

bool Test::const_iterator::operator==(const const_iterator &i) const {
    return i.it == this->it;
}

/********************************
 *  Operador de Desigualdad.
 ********************************/
bool Test::iterator::operator!=(const iterator &i) const {
    return i.it != this->it;
}

bool Test::const_iterator::operator!=(const const_iterator &i) const {
    return i.it != this->it;
}

/********************************
 *  Iterador del Primer elemento.
 ********************************/
typename Test::iterator Test::begin() {
    iterator i;
    i.it = this->preguntasTest.begin();
    return i;
}

typename Test::const_iterator Test::begin() const {
    const_iterator i;
    i.it = this->preguntasTest.begin();
    return i;
}

/********************************
 *  Iterador del Ultimo elemento
 ********************************/
typename Test::iterator Test::end() {
    iterator i;
    i.it = this->preguntasTest.end();
    return i;
}

typename Test::const_iterator Test::end() const {
    const_iterator i;
    i.it = this->preguntasTest.end();
    return i;
}

/**************************
 *  Operador de impresión.
 **************************/
ostream &operator<<(ostream &os, const Test &t) {
    for (Test::num p = 0; p < t.preguntasTest.size(); p++)
        os << t.preguntasTest[p];
    return os;
}
