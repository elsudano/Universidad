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

void imprime_arbol_con_formato(string nom, ArbolGeneral<int> &ab){
    ArbolGeneral<int>::iter_preorden it_tree = ab.begin();
    cout << "Arbol " << nom << ": " << ab << endl;
    cout << "Etiquetas ordenadas de " << nom << ": ";
    for (; it_tree != ab.end(); ++it_tree)
        cout << (*it_tree) << " ";
    cout << (*it_tree) << endl;
}

int main(int argc, char *argv[]) {
    fstream f,fdp,resultado;
    f.open (argv[1], fstream::in | fstream::out);
    if (!f.is_open()) {
        cout << "No puedo abrir el fichero: " << argv[1]<< endl;
        exit(1);
    }
    fdp.open (argv[2], fstream::in | fstream::out);
    if (!fdp.is_open()) {
        cout << "No puedo abrir el fichero: " << argv[2]<< endl;
        exit(1);
    }
    ArbolGeneral<int> ab1, ab2, ab3;
    cout << "Voy a leer los dos arboles" << endl << endl;
    f>>ab1;
    fdp>>ab2;
    f.close();
    fdp.close();
    ArbolGeneral<int>::iter_preorden it_tree = ab1.begin();
    /******************************************************************************/
    cout << "He leido los dos arboles correctamente" << endl;
    imprime_arbol_con_formato("AB1",ab1);
    imprime_arbol_con_formato("AB2",ab2);
    cout << endl;
    /************Copia de los arboles************/
    cout << "Pruebo que se pueden copiar los arboles del AB2 al AB3" << endl;
    ab3 = ab2;
    imprime_arbol_con_formato("AB3",ab3);
    cout << endl;
    /**************************************************************************/
    cout << "Voy a poner el AB2 como subarbol de AB1 en el nodo 5 (cuando tiene hijos)" << endl;
    it_tree = ab1.begin();
    for (; (*it_tree) != 5; ++it_tree);
    cout << "Encuentro el nodo: " << (*it_tree) << endl;
    ab1.asignar_subarbol(ab2, it_tree.GetNodo());
    cout << "Arbol AB1 despúes de asignar subarbol AB2: " << endl;
    imprime_arbol_con_formato("AB1",ab1);
    cout << endl;
    /**************************************************************************/
    cout << "Voy a poner el AB3 como subarbol de AB1 en el nodo 10 (cuando NO tiene hijos)" << endl;
    it_tree = ab1.begin();
    for (; (*it_tree) != 10; ++it_tree);
    cout << "Encuentro el nodo: " << (*it_tree) << endl;
    /****Asignamos Subarbol*****/
    ab1.asignar_subarbol(ab3, it_tree.GetNodo());
    cout << "Arbol AB1 despúes de asignar subarbol AB3: " << endl;
    imprime_arbol_con_formato("AB1",ab1);
    cout << endl;
    /**************************************************************************/
    cout << "Voy a podar el subarbol izquierdo del nodo 6 del árbol AB1" << endl;
    /****Podamos Subarbol Izquierdo*****/
    it_tree = ab1.begin();
    for (; (*it_tree) != 6; ++it_tree);
    ab1.podar_hijomasizquierda(it_tree.GetNodo(), ab2);
    imprime_arbol_con_formato("AB1",ab1);
    cout << "Este es el árbol que hemos podado" << endl;
    imprime_arbol_con_formato("AB2",ab2);
    cout << endl;
    /**************************************************************************/
    cout << "Voy a podar el subarbol derecho del nodo 7 del árbol AB1" << endl;
    /****Podamos Subarbol Derecho*****/
    it_tree = ab1.begin();
    for (; (*it_tree) != 7; ++it_tree);
    ab1.podar_hermanoderecha(it_tree.GetNodo(), ab3);
    imprime_arbol_con_formato("AB1",ab1);
    cout << "Este es el árbol que hemos podado" << endl;
    imprime_arbol_con_formato("AB3",ab3);
    cout << endl;
    /**************************************************************************/
    cout << "Insertamos en el nodo 200 del árbol AB1 el árbol AB3 (cuando tiene hijos)" << endl;
    /****Insertamos Subarbol en nodo HijoIzquierdo*****/
    it_tree = ab1.begin();
    for (; (*it_tree) != 200; ++it_tree);
    ab1.insertar_hijomasizquierda(it_tree.GetNodo(),ab3);
    imprime_arbol_con_formato("AB1",ab1);
    cout << endl;
    /**************************************************************************/
    cout << "Insertamos en el nodo 9 del árbol AB1 el árbol AB2 (cuando NO tiene hijos)" << endl;
    /****Insertamos Subarbol en nodo HermanoDerecha*****/
    it_tree = ab1.begin();
    for (; (*it_tree) != 9; ++it_tree);
    ab1.insertar_hermanoderecha(it_tree.GetNodo(),ab2);
    imprime_arbol_con_formato("AB1",ab1);
    cout << endl;
    /**************************************************************************/
    cout << "Por ultimo guardamos en el fichero: resultado.tree el árbol AB1" << endl;
    /****Guardamos en disco el arbol*****/
    string contenido;
    resultado.open (argv[3], fstream::in | fstream::out);
    if (!resultado.is_open()) {
        cout << "No puedo abrir el fichero: " << argv[3]<< endl;
        exit(1);
    }
    resultado<<ab1;
    resultado.seekp(0);
    getline(resultado,contenido);
    cout << contenido << endl;
    resultado.close();
}