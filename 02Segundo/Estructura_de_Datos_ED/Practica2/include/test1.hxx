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
Test::Test(const Test& T){
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
Test::Test(ConjuntoPreguntas &CP, Test::size_type np) {
    this->reservado = np+1;
    this->cant_pregu = np;
    this->preguntasTest = new Pregunta[reservado];
    /* Inicializamos la semilla */
    srand(time(NULL));
    /* Generamos primer aleatorio  */
    size_type aleatorio = rand()%CP.Size();
    size_type num_aleatorios[this->cant_pregu];
    
    for(size_type n=0;n<this->cant_pregu;n++){
        /* El siguiente for es para comprobar preguntas repertidas*/
        for (size_type i=0;i<this->cant_pregu;i++){
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
    
    for (size_type p=0;p<this->cant_pregu;p++){
        this->preguntasTest[p]=CP[num_aleatorios[p]];
    }
}

/*************************
 *  Consultor de Tamaño.
 *************************/
Test::size_type Test::Size(){
    return this->cant_pregu;
}

/**************************
 *  Operador de impresión.
 **************************/
ostream& operator<<(ostream& os, const Test& t) {
    for (Test::size_type p=0;p<t.cant_pregu;p++)
        os << t.preguntasTest[p];
    return os;
}
