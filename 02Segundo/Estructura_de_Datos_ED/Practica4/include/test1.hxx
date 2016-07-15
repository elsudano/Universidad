/* 
 * File:   test1.hxx
 * Author: Carlos de la Torre
 *
 * Created on 3 de noviembre de 2015, 13:58
 */

/*************************
 *  Constructor vacío.
 *************************/
Test::Test() {
    this->reservado = 1;
    this->cant_pregu = 0;
    this->preguntasTest = new Pregunta[reservado];
}

/*************************
 *  Constructor copia.
 *************************/
Test::Test(const Test &T){
    this->cant_pregu=T.cant_pregu;
    this->reservado=T.reservado;
    this->preguntasTest=T.preguntasTest;
}

/*************************
 *  Destructor por defecto.
 *************************/
Test::~Test(){
    if (this->reservado<1)
        delete []this->preguntasTest;
}

/***************************
 *  Constructor parametros.
 ***************************/
Test::Test(ConjuntoPreguntas &cp, Test::num np) {
    this->reservado = np+1;
    this->cant_pregu = np;
    this->preguntasTest = new Pregunta[reservado];
    /* Inicializamos la semilla */
    srand(time(NULL));
    /* Generamos primer aleatorio  */
    num aleatorio = rand()%CP.Size();
    num num_aleatorios[this->cant_pregu];
    
    for(num n=0;n<this->cant_pregu;n++){
        /* El siguiente for es para comprobar preguntas repertidas*/
        for (num i=0;i<this->cant_pregu;i++){
            if (num_aleatorios[i]==aleatorio){
                aleatorio = rand()%CP.Size();
                i=0;
            }
        }
        /*Como el numero no esta en el vector lo metemos*/
        num_aleatorios[n]=aleatorio;
        /*Generamos siguiente aleatorio*/
        aleatorio = rand()%CP.Size(); /** generamos los siguientes */
    }
    
    for (num p=0;p<this->cant_pregu;p++){
        this->preguntasTest[p]=CP[num_aleatorios[p]];
    }
}

/***************************
 *  Constructor parametros.
 ***************************/
Test::Test(ConjuntoPreguntas &cp, int &np, set<int> &nr) {
    
}

/*************************
 *  Consultor de Tamaño.
 *************************/
Test::num Test::Size(){
    return this->cant_pregu;
}

/**************************
 *  Operador de impresión.
 **************************/
ostream &operator<<(ostream &os, const Test &t) {
    for (Test::num p=0;p<t.cant_pregu;p++)
        os << t.preguntasTest[p];
    return os;
}
