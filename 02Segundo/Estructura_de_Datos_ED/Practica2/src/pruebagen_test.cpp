#include <iostream>
#include "../include/conjuntopreguntas.h"
#include "../include/test.h"
#include <fstream>
#include <cstdlib>
using namespace std;

int main(int argc, char * argv[]) {
    if (argc != 3 && argc != 4) {
        cout << "Los parametros son:" << endl;
        cout << "1.Dime el nombre del fichero con las preguntas y posibles respuestas" << endl;
        cout << "2.El numero de preguntas que quieres en el test" << endl;
        cout << "3.-(Opcional) El nombre del fichero donde escribir el test" << endl;
        return 0;
    }

    ifstream fin(argv[1]);
    if (!fin) {
        cout << "No puedo abrir el fichero " << argv[1] << endl;
        return 0;
    }

    ConjuntoPreguntas CP;
    fin>>CP;
    //cout << CP;
    cout << "Leidas las preguntas. Numero Total : " << CP.Size() << endl;
    int np = atoi(argv[2]);

    Test T(CP, np); // Se genera el test de forma aleatoria con el conjunto de preguntas
    cout << "Generado el test..." << endl;
    if (argc == 3) {
        cout << T << endl;
    } else {
        ofstream fout(argv[3]);
        if (!fout) {
            cout << "No puedo crear el fichero " << argv[3] << endl;
            return 0;
        }
        fout << T;
    }
} 