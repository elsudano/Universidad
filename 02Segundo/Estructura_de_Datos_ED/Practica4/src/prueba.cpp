#include <stdlib.h>
#include <iostream>
#include <fstream>
#include <time.h>
#include "pregunta.h"
#include "conjuntopreguntas.h"
#include "test.h"

using namespace std;

void wait(int seconds) {
    clock_t endwait;
    endwait = clock() + seconds * CLOCKS_PER_SEC;
    while (clock() < endwait) {
    }
}

int main(int argc, char * argv[]) {

    ifstream fin(argv[1]);
    if (!fin) {
        cout << "No puedo abrir el fichero " << argv[1] << endl;
        return 0;
    }
    ConjuntoPreguntas CP;
    ConjuntoPreguntas::iterator CP_ite;
    fin>>CP;
    cout << "Leidas las preguntas. Numero Total : " << CP.Size() << endl;
    fin.close();

    int misEnteros[]= {1,3,5,7};
    set<int> repetidas(misEnteros,misEnteros+4);
    Test TES(CP, 5);
    Test TER(CP, 5, repetidas);
    Test::iterator TE_ite;

    for (TE_ite = TES.begin(); TE_ite != TES.end(); ++TE_ite)
        if ((*TE_ite).GetNumero() % 2 == 0) {
            cout << *TE_ite << endl;
            cout << "La Solución es: " << (*TE_ite).GetAnswer(0);
        }
    
    cout << "Sin las repetidas" << endl;
    
    for (TE_ite = TER.begin(); TE_ite != TER.end(); ++TE_ite)
        if ((*TE_ite).GetNumero() % 2 == 0) {
            cout << *TE_ite << endl;
            cout << "La Solución es: " << (*TE_ite).GetAnswer(0);
        }
    
    cout << "Con las repetidas" << endl;
    
    for (CP_ite = CP.begin(); CP_ite != CP.end(); ++CP_ite)
        if ((*CP_ite).first % 2 == 0) {
            cout << (*CP_ite).second << endl;
            cout << "La Solución es: " << (*CP_ite).second.GetAnswer(0);
            wait(3);
        }

    Pregunta pregunta;
    string cadena = "1.- La Constitucion se fundamenta:\n4\na) En la unidad de la Nacion Española.\nb) En el compromiso de unidad de todos los pueblos y nacionalidades integrantes de la Nacion Española.\nc) En la indisoluble unidad de la Nacion Española.\nd) En la pluralidad de pueblos integrantes de la Nacion Española.\n";
    pregunta = Pregunta(cadena);
    cout << pregunta << endl;
    Pregunta pregunta2 = Pregunta(pregunta);
    if (pregunta == pregunta2)
        cout << "Son iguales" << endl;
    else
        cout << "No son iguales" << endl;
    cadena = "3.- Solo uno de los siguientes principios esta garantizado constitucionalmente:\n5\na) La responsabilidad e interdiccion de la arbitrariedad de la Administracion Publica, tanto nacional, como autonomica o local.\nb) La responsabilidad de la Administracion Publica.\nc) La interdiccion de la arbitrariedad del poder judicial.\nd) La responsabilidad e interdiccion de la arbitrariedad de los poderes publicos.\ne) Ninguna de las anteriores respuestas es correcta\n";
    pregunta = Pregunta(cadena);
    cout << pregunta << endl;
    cout << pregunta2 << endl;
    if (pregunta == pregunta2)
        cout << "Son iguales" << endl;
    else
        cout << "No son iguales" << endl;
}

