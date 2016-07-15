#include <fstream>
#include <iostream>
#include <cstdlib>
#include <ctime>
#include <sstream>
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
    cout << "Leido el arbol con las descripciones de los personajes" << endl;
    //recorremos en postorden el arbol
    ArbolGeneral<int>::iter_preorden it_tree = ab.begin();

    while (it_tree != ab.end()) {
        if (it_tree.Hoja() && (*it_tree) != -1) {
            ArbolGeneral<int>::Nodo nod = it_tree.GetNodo();
            cout << "***************\nPersonaje: " << People.GetPersona(*it_tree).GetNombre() << endl;
            cout << "Preguntas y contestaciones son:" << endl;
            Preguntas::iterator itask = ask.end();
            --itask;
            Preguntas::iterator itask_prev = ask.end();
            string codigo(ask.NumPreguntas(), '0');
            int pos = ask.NumPreguntas() - 1;
            do {
                cout << *itask;
                if (nod == ab.hijomasizquierda(ab.padre(nod))) {
                    cout << " Si" << endl;
                    codigo[pos] = '1';
                }
                else {
                    cout << " No" << endl;
                    codigo[pos] = '0';
                }
                itask_prev = itask;
                --itask;
                pos--;
                nod = ab.padre(nod);
            } while (itask_prev != ask.begin());
            cout << "Codigo asociado " << codigo << endl;
            //Le ponemos al personaje el codigo
            People.GetPersona(*it_tree).SetCodigoRespuestas(codigo);
        }
        ++it_tree;
    }
    cout << "Imprimimos todos los Personajes: " << endl << People << endl;
}


