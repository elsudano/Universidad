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
    //ab.clear();
    /**
     * si ponemos esta linea cuando ejecutamos el programa da una violacion de
     * segmento, creo que es por que al limpiar el arbol, la primera vez,
     * si lo borra correctamente, pero cuando se finaliza el programa, y se
     * llama al destructor y al intentar borrarlo por segunda vez falla.
     */

    //cout << "Imprimimos todas las Preguntas: " << endl << ask << endl;
    //cout << "Imprimimos todos los Personajes: " << endl << People << endl;
    //cout << "Imprimimos el árbol: " << endl << ab << endl;
    bool minimal = true,iguales;
    char muestra, prueba;
    Personas::iterator it_perso;
    // volvemos a empezar en el 1 por que las preguntas las contamos desde 1
    for (int i = 1; i < ask.NumPreguntas(); i++) {
        // siempre empezamos por el primer personaje
        it_perso = People.begin();
        // movemos el puntero segun la pregunta donde estamos
        muestra = (*it_perso).GetCodigo()[i - 1];
        iguales = true;
        // nos movemos al segundo personaje para no comparar consigo mismo
        ++it_perso;
        while (it_perso != People.end() && iguales) {
            // miramos la respuesta del personaje que estamos recorriendo
            prueba = (*it_perso).GetCodigo()[i - 1];
            //cout << ask.mostrarPregunta(i) << " " << (*it_perso).GetNombre() << " " << muestra << " " << prueba << endl;
            // comparamos las dos respuestas
            if (muestra != prueba)
                iguales = false;
            // recorremos los personajes
            ++it_perso;
        }
        // si las dos son iguales el conjunto no es minimal y imprimimos la pregunta
        if (iguales){
            cout << "La pregunta " << ask.GetPregunta(i) << " no discrimina a los personajes" << endl;
            minimal = false;
        }
    }
    if (!minimal)
        cout << "El conjunto NO es Minimal" << endl;
    else
        cout << "El conjunto SI es Minimal" << endl;
}


