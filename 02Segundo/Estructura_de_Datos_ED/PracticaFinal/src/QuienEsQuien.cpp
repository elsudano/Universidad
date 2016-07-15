#include <fstream>
#include <iostream>
#include <cstdlib>
#include <ctime>
#include <sstream>
#include "ArbolGeneral.h"
#include "Personas.h"
#include "QuitaComentarios.h"
#include "Preguntas.h"
#include "Jugador.h"

using namespace std;

/**
 * \mainpage Quien es Quien
 * \section Introducción
 * En esta practica se pretendia trabajar con un TDA que fuese un árbol general.\n
 * Pero para llevar acabo la practica se necesitaban diferentes estructuras de
 * datos, entre las que se destacan el de Persona, Jugador, Preguntas y sus
 * conjuntos tanto de Personas como de Preguntas.\n
 * En ambos conjuntos cabe destacar que se han implementado los iteradores
 * para poder manipular los datos con facilidad.\n
 * Aparte del juego, tambien se ha desarrollado una serie de utilidades para
 * comprobar si los datos que hay en los ficheros de configuración son correctos
 * con este fin se han construido los ejecutables, \b suficiente, \b minimo y \b test
 * con estos programas podemos asegurarnos que los ficheros de configuración están
 * correctamente formados.\n
 * \section Personas
 * Este TDA no es mas que un contenedor de otro TDA (Persona) y que añade utilidades
 * al tipo de dato Persona, como puede ser una manera facil de conocer todos
 * los elementos Persona que se encuentran en el juego.\n
 * 
 * \subsection Persona
 * Este contenedor no es mas que un tipo de dato que almacena los datos basicos
 * para este tipo de objetos, así pues aparte de almacenar estos datos, nos
 * habilita metodos para poder acceder a esos datos.\n
 * 
 * \section Preguntas
 * Este modulo se encarga de organizar todas las preguntas que puede hacer el
 * Jugador para poder descubrir la identidad de quien se esconde.\n
 * Por supuesto como es un contenedor que se encarga de un conjunto, tambien
 * se ha implementado un iterador para poder navegar a traves de sus datos.\n
 * 
 * \section Jugador
 * Este contenedor tiene los datos relacionados con el jugador de la partida
 * ya sea humano o maquina, en ambos casos guarda datos basicos para la correcta
 * ejecución del programa, como pueden ser el nombre, las preguntas ya realizadas
 * o simplemente el personaje que se oculta tras el jugador.\n
 */
int main(int argc, char *argv[]) {

    if ((argc < 3)) {
        if ((argc != 2))
            cout << "Dime el nombre del fichero de configuracion" << endl;
        else
            cout << "¿El jugador es humano o es maquina? (h/m)" << endl;
        exit(0);
    }
    // abrimos el fichero especificado
    ifstream f(argv[1], ios::in);
    // quitamos los comentarios del principio del fichero
    QuitaComentarios(f);
    // Objeto que almacenas las Preguntas
    Preguntas ask;
    // Introducimos las Preguntas
    f>>ask;
    // Objeto que almacena a los Personajes
    Personas People;
    // Introducimos los Personajes
    f>>People;
    // Quitamos los posibles comentarioas antes de leer el árbol
    QuitaComentarios(f);
    //Cadena para guardar el nombre del fichero que contiene el arbol
    string str;
    //Leemos el nombre del fichero con el arbol
    f>>str;
    //Abrimos el fichero con los datos del arbol
    ifstream farbol(str.c_str());
    if (!farbol) {
        cout << "No puedo abrir el fichero que contiene el árbol" << str << endl;
        exit(1);
    }
    //eliminamos memoria
    str.clear();
    // Creamos el árbol y lo llenamos con los datos
    ArbolGeneral<int> ab;
    farbol>>ab;
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
    // Cerramos los dos ficheros.
    f.close();
    farbol.close();
    // y limpiamos la memoria del árbol que ya no hace falta
    ab.clear();

    /******************************Empieza el Juego********************************/
    // Elegimos un Personaje aleatorio para darselo al jugador
    // inicializamos la semilla
    srand(time(NULL));
    // Creamos el jugador para el Ordenador, el que esconderá el personaje
    Jugador ordenador(People, ask);
    ordenador.setNombre("Ordenador");
    // Creamos el jugador que va a descubrir el personaje que esconde el ordenador
    Jugador player(People, ask);
    player.setNombre("Jugador");
    // Comprobamos que el jugador es Humano o Maquina
    string nombre;
    if (*argv[2] == 'h' || *argv[2] == 'H') {
        cout << "Por favor introduzca su nombre: " << endl;
        cin >> nombre;
        // Asignamos el nombre según corresponda
        player.setNombre(nombre);
        player.setHumano();
        // esto es para hacer un clear en la pantalla despúes del nombre
        cout << string(100, '\n');
    }

    // Generamos el nummero entre los posibles
    unsigned int num_ale = rand() % People.Size();
    // Asignamos el personaje para la maquina
    ordenador.setPersona(People.GetPersona(num_ale));
    // generamos un numero nuevo para el personaje del jugador
    num_ale = rand() % People.Size();
    // Le asignamos el personaje elejido al jugador
    player.setPersona(People.GetPersona(num_ale));
    // Imprimimos por pantalla todos los datos recopilados
    // cout << "Imprimimos todas las Preguntas: " << endl << ask << endl;
    // cout << "Imprimimos todos los Personajes: " << endl << People << endl;
    // cout << "Imprimimos el Jugador: " << endl << player << endl;
    // cout << "Imprimimos la Maquina: " << endl << machine << endl;
    // cout << "Imprimimos el árbol: " << endl << ab << endl;
    // Variables para el bucle principal
    int preg_aux = 1, preg_ele = -1;
    string posible_nombre_personaje, respuesta;
    Preguntas::iterator it;
    // Bucle principal del juego
    while (true) {
        // turno del jugador
        it = player.getPreguntas().end();
        --it;
        preg_aux = it.getNumero() + 1;
        cout << "Tu personaje es: " << player.getPersona().GetNombre() << endl;
        cout << "Elige una de las siguientes preguntas:" << endl;
        // imprimo solo las preguntas posibles
        cout << player.getPreguntas();
        // imprimo la posibilidad de decir el personaje
        cout << preg_aux << ".-Digo ya el personaje" << endl;
        if (player.esHumano()) {// si el jugador es humano
            // Contemplamos el 0 para salir y ultima pregunta + 1 para decir personaje
            while (preg_ele < 0 || preg_ele > preg_aux) {
                cout << "¿Cual elige? (0 para salir) ";
                cin >> preg_ele;
                if (preg_ele == 0)
                    exit(0);
            }
        } else {// si el jugador es IA
            cout << "La IA ha elegido como mejor pregunta: " << endl;
            // solo mostramos las preguntas que puede hacer el jugador
            preg_ele = player.elegirPregunta();
        }
        /*
         * En el siguiente if contenplamos la opción de resolver el juego
         */
        if (preg_ele == preg_aux) {
            cout << "Dime el nombre del personaje: ";
            if (player.esHumano()) {
                // Si es humano dejamos que lo escriba
                cin >> posible_nombre_personaje;
            } else {
                // Si es una maquina miramos cual ha descubierto
                posible_nombre_personaje = player.resuelvePersonaje();
                cout << posible_nombre_personaje << endl;
            }
            // ahora le preguntamos al ordenador si es correcto
            if (ordenador.Resolver(posible_nombre_personaje)) {
                cout << "Enhorabuena acertaste" << endl;
                exit(0);
            } else
                cout << "Lo siento ese no es el personaje" << endl;
        } else {
            // imprimo la pregunta que quiere hacer el jugador
            cout << player.getNombre() << "> " << player.getPreguntas().GetPregunta(preg_ele) << endl;
            // pregunto al ordenador cual es la respuesta
            respuesta = ordenador.responderPregunta(preg_ele);
            // La maquina (el otro jugador) responde a la pregunta
            cout << ordenador.getNombre() << "> " << respuesta << endl << endl;
            // metemos la pregunta en las ya usadas
            player.setPreguntaUsada(preg_ele, respuesta);
        }
        // turno del ordenador
        cout << "El Ordenador ha elegido como mejor pregunta: " << endl;
        // compruebo cual es el ultimo número para poder decir el personaje oculto
        it = ordenador.getPreguntas().end();
        --it;
        preg_aux = it.getNumero() + 1;
        // El ordenador elige la mejor pregunta para descubrir el personaje
        preg_ele = ordenador.elegirPregunta();
        // En el siguiente if contenplamos la opción de resolver el juego
        if (preg_ele == preg_aux) {
            cout << "Dime el nombre del personaje: ";
            posible_nombre_personaje = ordenador.resuelvePersonaje();
            cout << posible_nombre_personaje << endl;
            if (player.Resolver(posible_nombre_personaje)) {
                cout << "Enhorabuena acertaste" << endl;
                exit(0);
            } else
                cout << "Lo siento ese no es el personaje" << endl;
        } else {
            // El ordenador tambien intenta descubrir el personaje del jugador
            cout << ordenador.getNombre() << "> " << ordenador.getPreguntas().GetPregunta(preg_ele) << endl;
            /**
             * para que no haya mal interpretación en las respuestas del
             * usuario lo que hacemos es que se responde automaticamente
             * a la pregunta realizada por el ordenador
             */
            respuesta = player.responderPregunta(preg_ele);
            // el jugador (sea humano o maquina) responde a la pregunta
            cout << player.getNombre() << "> " << respuesta << endl << endl;
            // metemos la pregunta en las ya usadas del ordenador
            ordenador.setPreguntaUsada(preg_ele, respuesta);
        }
        // reseteamos la elección del usuario
        preg_ele = -1;
    }
}