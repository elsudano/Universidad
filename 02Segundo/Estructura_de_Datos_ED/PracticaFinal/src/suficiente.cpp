#include <fstream>
#include <iostream>
#include <cstdlib>
#include <ctime>
#include <sstream>
#include <vector>
#include "ArbolGeneral.h"
#include "Personas.h"
#include "QuitaComentarios.h"
#include "Preguntas.h"

using namespace std;

int main(int argc, char *argv[]) {

    if ((argc != 2)) {
        cout << "Dime el nombre del fichero de configuracion" << endl;
        return 0;
    }
    ifstream f(argv[1], ios::in);
    QuitaComentarios(f);

    Preguntas ask;
    f>>ask;

    Personas People;
    f>>People;

    QuitaComentarios(f);
    //Cadena para guardar el nombre del fichero que contiene el arbol
    string str;
    //Leemos el nombre del fichero con el arbol
    f>>str;
    //Abrimos el fichero con los datos del arbol
    ifstream farbol(str.c_str());
    if (!farbol) {
        cout << "No puedo abrir el fichero " << str << endl;
        exit(1);
    }
    ArbolGeneral<int> ab;
    farbol>>ab;

    f.close();
    farbol.close();
    // Creamos un iterador para poder recorrer el árbol
    ArbolGeneral<int>::iter_preorden it_tree = ab.begin();
    while (it_tree != ab.end()) {
        // Comprobamos que estamos en un nodo hoja y que tiene un personaje
        if (it_tree.Hoja() && (*it_tree) != -1) {
            // recogemos el nodo para tratarlo
            ArbolGeneral<int>::Nodo nod = it_tree.GetNodo();
            // vamos a la ultima pregunta con --itask
            Preguntas::iterator itask = ask.end();
            --itask;
            // Guardamos cual es el final real de las preguntas
            Preguntas::iterator itask_prev = ask.end();
            // Creamos una cadena con la cantidad igual que el numero de preguntas
            string codigo(ask.NumPreguntas(), '0');
            // según en que pregunta estemos guardamos la respuesta en la cadena
            int pos = ask.NumPreguntas() - 1;
            do {
                if (nod == ab.hijomasizquierda(ab.padre(nod)))
                    // si la respuesta es SI
                    codigo[pos] = '1';
                else
                    // si la respuesta es NO
                    codigo[pos] = '0';
                // Movemos los punteros para la siguiente pregunta
                itask_prev = itask;
                // y ahora nos ponemos en la siguiente pregunta (anterior!!)
                --itask;
                // movemos la posicion de la cadena
                pos--;
                // subimos de nivel por que es la pregunta anterior
                nod = ab.padre(nod);
                // hasta que no llegemos a la primera pregunta
            } while (itask_prev != ask.begin());
            //Le ponemos al personaje el codigo
            People.GetPersona(*it_tree).SetCodigoRespuestas(codigo);
        }
        // y ahora vamos al siguiente personaje
        ++it_tree;
    }

    //cout << "Imprimimos todas las Preguntas: " << endl << ask << endl;
    //cout << "Imprimimos todos los Personajes: " << endl << People << endl;
    //cout << "Imprimimos el árbol: " << endl << ab << endl;

    bool suficiente = true;
    string codigo1, codigo2;
    Personas::iterator it_perso = People.begin();
    while (it_perso != People.end()) {
        Personas::iterator it_movil = ++it_perso;
        --it_perso;
        while (it_movil != People.end()) {
            codigo1 = (*it_perso).GetCodigo();
            codigo2 = (*it_movil).GetCodigo();
            if (codigo1 == codigo2) {
                cout << "Las respuestas para " << (*it_perso).GetNombre() << " (" << (*it_perso).GetCodigo() << ") y para " << (*it_movil).GetNombre() << " (" << (*it_movil).GetCodigo() << ") son iguales" << endl;
                suficiente = false;
            }
            ++it_movil;
        }
        ++it_perso;
    }
    if (!suficiente)
        cout << "El conjunto NO es Suficiente" << endl;
    else
        cout << "El conjunto SI es Suficiente" << endl;
}


