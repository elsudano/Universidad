#include <iostream>
#include "conjuntopreguntas.h"
#include "test.h"
#include "solucion.h"
#include <fstream>
#include <cstdlib>
#include <cctype>
using namespace std;

/**
 *@brief Mueve los iteradores dentro de un conjunto de preguntas hasta alcanzar un codigo de pregunta
 *@param it_cp: iterador dentro del conjunto de preguntas
 *@param end_cp: iterador que indica el fin del conjunto de preguntas
 *@param it_sol: iterador al conjunto de soluciones 
 *@param codigo: codigo de la pregunta a la que apuntaran el iterador it_cp.
 * 
 * 
 **/
void MueveApregunta(ConjuntoPreguntas::const_iterator & it_cp, const ConjuntoPreguntas::const_iterator & end_cp, Solucion::const_iterator &it_sol, int codigo) {
    while (it_cp != end_cp && (*it_cp).first != codigo) {
        ++it_cp;
        ++it_sol;
    }
}

/**
 *@brief Muestra las preguntas y sus soluciones desde un codigo de pregunta hasta otro
 *@param CP: conjunto de preguntas
 *@param S: conjunto de soluciones
 *@param cod_ini: codigo de la pregunta donde se empieza a repasar
 *@param cod_fin: codigo de la pregunta donde termina el repaso. Esta no se muestra al usuario
 *@pre cod_ini < cod_fin
 * 
 **/
void Repasa(const ConjuntoPreguntas & CP, const Solucion &S, int cod_ini, int cod_fin) {

    ConjuntoPreguntas::const_iterator it_cp = CP.begin(), end_cp = CP.end();

    Solucion::const_iterator it_sol = S.begin();


    MueveApregunta(it_cp, end_cp, it_sol, cod_ini); //avanzamos los iteradores  a la pregunta con cod_ini

    Solucion::const_iterator it_sol_end = it_sol;
    ++it_sol_end;
    ConjuntoPreguntas::const_iterator it_cp_end = it_cp;
    ++it_cp_end;

    MueveApregunta(it_cp_end, end_cp, it_sol_end, cod_fin); //avanzamos los iteradores a la siguiente a la pregunta con cod_fin

    while (it_cp != it_cp_end && it_sol != it_sol_end) {
        cout << (*it_cp).first << ".-" << (*it_cp).second << endl;
        int pos = (tolower((*it_sol).second) - 'a'); //Pasamos a  entero la solucion que esta en char
        cout << endl << "Solucion es:" << (*it_cp).second.GetAnswer(pos) << endl;
        cout << "________________________________________________________________" << endl;

        ++it_cp;
        ++it_sol;
        cout << "Pulsa una tecla..." << endl;
        cin.get();
    }

}

int main(int argc, char * argv[]) {
    if (argc != 5) {
        cout << "Los parametros son:" << endl;
        cout << "1.Dime el nombre del fichero con las preguntas y posibles respuestas" << endl;
        cout << "2.Dime el nombre del fichero con las soluciones" << endl;
        cout << "3.Desde que pregunta quieres repasar" << endl;
        cout << "4.Hasta que pregunta quieres repasar" << endl;
        return 0;
    }

    ifstream fin(argv[1]);
    if (!fin) {
        cout << "No puedo abrir el fichero " << argv[1] << endl;
        return 0;
    }

    ConjuntoPreguntas CP;
    fin>>CP;
    cout << "Leidas las preguntas. Numero Total : " << CP.Size() << endl;
    fin.close();

    fin.open(argv[2]);
    if (!fin) {
        cout << "No puedo abrir el fichero" << argv[2] << endl;
        return 0;
    }

    Solucion S;
    fin>>S;
    cout << "Leidas las soluciones. Numero de Soluciones:" << S.Size() << endl;
    fin.close();

    int inicio_ask, fin_ask;
    inicio_ask = atoi(argv[3]);
    fin_ask = atoi(argv[4]);
    ++fin_ask;

    Repasa(CP, S, inicio_ask, fin_ask);
}