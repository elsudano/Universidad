/*
 * MenuPrincipal.cpp
 *
 *  Created on: 19/11/2013
 *      Author: Carlos de la Torre
 */

#include "menu.h"
#include "examen1.h"
#include "examen2.h"
#include "ejercicioPilas1.h"
#include "ejercicioPilas2.h"
#include "ejercicioPilas3.h"
#include "ejercicioPilas4.h"
#include "ejercicioPilas5.h"
#include "ejercicioPilas6.h"
#include "ejercicioPilas7.h"
#include "ejercicioPilas8.h"
#include "ejercicioPilas9.h"
#include "ejercicioPilas10.h"
#include "ejercicioPilas11.h"
#include "ejercicioPilas12.h"
#include "ejercicioPilas13.h"
#include "ejercicioListas1.h"
#include "ejercicioListas2.h"
#include "ejercicioListas3.h"
#include "ejercicioListas4.h"
#include "ejercicioListas5.h"
#include "ejercicioListas6.h"
#include "ejercicioListas7.h"
#include "ejercicioListas8.h"
#include "ejercicioListas9.h"
#include "ejercicioListas10.h"
#include "ejercicioListas11.h"
#include "ejercicioListas12.h"
#include "ejercicioArboles1.h"
#include "ejercicioArboles10.h"

int main(int argc, char *argv[]) {
	menuTexto misMenus;
	misMenus.addMenu("Ejercicios de Pilas y Colas", 1);
	misMenus.addSubMenu("Ejercicios de Pilas y Colas", "Ejercicio 1", 1);
	misMenus.addSubMenu("Ejercicios de Pilas y Colas", "Ejercicio 2", 2);
	misMenus.addSubMenu("Ejercicios de Pilas y Colas", "Ejercicio 3", 3);
	misMenus.addSubMenu("Ejercicios de Pilas y Colas", "Ejercicio 4", 4);
	misMenus.addSubMenu("Ejercicios de Pilas y Colas", "Ejercicio 5", 5);
	misMenus.addSubMenu("Ejercicios de Pilas y Colas", "Ejercicio 6", 6);
	misMenus.addSubMenu("Ejercicios de Pilas y Colas", "Ejercicio 7", 7);
	misMenus.addSubMenu("Ejercicios de Pilas y Colas", "Ejercicio 8", 8);
	misMenus.addSubMenu("Ejercicios de Pilas y Colas", "Ejercicio 9", 9);
	misMenus.addSubMenu("Ejercicios de Pilas y Colas", "Ejercicio 10", 10);
	misMenus.addSubMenu("Ejercicios de Pilas y Colas", "Ejercicio 11", 11);
	misMenus.addSubMenu("Ejercicios de Pilas y Colas", "Ejercicio 12", 12);
	misMenus.addSubMenu("Ejercicios de Pilas y Colas", "Ejercicio 13", 13);
	misMenus.addMenu("Ejercicios de Listas", 2);
	misMenus.addSubMenu("Ejercicios de Listas", "Ejercicio 1", 14);
	misMenus.addSubMenu("Ejercicios de Listas", "Ejercicio 2", 15);
	misMenus.addSubMenu("Ejercicios de Listas", "Ejercicio 3", 16);
	misMenus.addSubMenu("Ejercicios de Listas", "Ejercicio 4", 17);
	misMenus.addSubMenu("Ejercicios de Listas", "Ejercicio 5", 18);
	misMenus.addSubMenu("Ejercicios de Listas", "Ejercicio 6", 19);
	misMenus.addSubMenu("Ejercicios de Listas", "Ejercicio 7", 20);
	misMenus.addSubMenu("Ejercicios de Listas", "Ejercicio 8", 21);
	misMenus.addSubMenu("Ejercicios de Listas", "Ejercicio 9", 22);
	misMenus.addSubMenu("Ejercicios de Listas", "Ejercicio 10", 23);
	misMenus.addSubMenu("Ejercicios de Listas", "Ejercicio 11", 24);
	misMenus.addSubMenu("Ejercicios de Listas", "Ejercicio 12", 25);
	misMenus.addMenu("Ejercicios de Arboles", 3);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 1", 26);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 2", 27);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 3", 28);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 4", 29);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 5", 30);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 6", 31);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 7", 32);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 8", 33);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 9", 34);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 10", 35);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 11", 36);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 12", 37);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 13", 38);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 14", 39);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 15", 40);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 16", 41);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 17", 42);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 18", 43);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 19", 44);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 20", 45);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 21", 46);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 22", 47);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 23", 48);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 24", 49);
	misMenus.addSubMenu("Ejercicios de Arboles", "Ejercicio 25", 50);
	misMenus.addMenu("Examen", 4);
	misMenus.addSubMenu("Examen", "Ejercicio 1", 51);

	int opcion=-1;
	do {
		cout << "Por favor elija una opción: " << endl;
		opcion = misMenus.seleccionar();
		switch (opcion) {
		case 1:
			cout
				<< "Construye una función a la que se se le pase una pila P de tipo T y dos elementos x,y de tipo"
				<< endl
				<< "T y que modifique la pila de forma que todas las veces que aparezca x se substituya por y"
				<< endl
				<< "(quedando la pila en el mismo estado en el que estaba anteriormente). Para este ejercicio no"
				<< endl << "se podrán utilizar iteradores." << endl;
			ejercicio1().ejecutar();
			cout << endl;
			break;
		case 2:
			cout
				<< "Implementa un función para determinar si una expresión contenida en un string tiene una"
				<< endl
				<< "configuración de paréntesis correcta. Debe tener un orden lineal."
				<< endl;
			ejercicio2().ejecutar();
			cout << endl;
			break;
		case 3:
			cout
				<< "Dada una matriz que representa un laberinto, construir una función que determine si se puede"
				<< endl
				<< "llegar desde la entrada hasta la salida. Esta matriz tendrá una ’E’ en la entrada, una ’S’ en la"
				<< endl
				<< "salida, un ’0’ en las casillas por las que se pueda pasar y un ’1’ en las que no. No se puede ir"
				<< endl << "en diagonal." << endl << endl;
			ejecutar_ejercicio3();
			cout << endl;
			break;
		case 4:
			cout
				<< "Implementa el TDA pila usando dos colas. ¿Qué orden de eficiencia tienen las operaciones push y pop?"
				<< endl;
			ejercicio4().ejecutar();
			cout << endl;
			break;
		case 5:
			cout
				<< "Implementa el TDA pila usando dos colas. ¿Qué orden de efíciencia tienen las operaciones push y pop?"
				<< endl << endl;
			ejercicio5().ejecutar();
			cout << endl;
			break;
		case 6:
			cout
				<< "Se llama expresión en postfijo a una expresión matemática en la que cada operación aparece"
				<< endl
				<< "con sus dos operandos seguidos por el operador. Por ejemplo: 2 3 + 5 * Escribe un programa"
				<< endl << "que evalue una expresión en postfijo" << endl
				<< endl << "ab^c*d/e + donde a = 5, b = 3, c = d = 2, e = 9"
				<< endl
				<< "abcde+*+ donde a = 12, b = 4, c = 7, d = 5, e = 2"
				<< endl
				<< "ab+cd*+e* donde a = 2, b = 6, c = 3, d = 5, e = 9"
				<< endl;
			ejercicio6().ejecutar();
			cout << endl;
			break;
		case 7:
			cout
				<< "Implementa una función insertar(P, pos, x) que inserte un elemento en una pila P en la posición"
				<< endl
				<< "pos. La pila debe quedar como estaba antes de insertar el elemento (salvo por el nuevo elemento)"
				<< endl;
			ejercicio7().ejecutar();
			cout << endl;
			break;
		case 8:
			cout
				<< "Implementa una función insertar(Q, pos, x) que inserte un elemento en una cola Q en la"
				<< endl
				<< "posición pos. La cola debe quedar como estaba antes de insertar el elemento (salvo por el nuevo elemento)"
				<< endl;
			ejercicio8().ejecutar();
			cout << endl;
			break;
		case 9:
			cout
					<< "Un tipo ventana es un tipo de dato que permite insertar un elemento, mover derecha, mover"
					<< endl
					<< "izquierda, borrar elemento y que se implementa usando dos pilas. Implementa ese tipo de dato"
					<< endl << "con las operaciones comentadas." << endl;
			ejercicio9().ejecutar();
			cout << endl;
			break;
		case 10:
			cout
				<< "Usando una pila y una cola, implementa una función que compruebe si un string es un palíndromo."
				<< endl << endl;
			ejercicio10().ejecutar();
			cout << endl;
			break;
		case 11:
			cout
				<< "Implementa una cola con prioridad de un tipo struct con (apellidos, nombre, prioridad) de"
				<< endl
				<< "forma que los datos salgan de acuerdo a ese tercer campo prioridad."
				<< endl << endl;
			ejercicio11().ejecutar();
			cout << endl;
			break;
		case 12:
			cout
				<< "Implementa una cola con prioridad que contenga strings y de la que salgan primero las cadenas"
				<< endl
				<< "de caracteres más largas y que en caso de igualdad salgan por orden alfabético."
				<< endl << endl;
			ejercicio12().ejecutar();
			cout << endl;
			break;
		case 13:
			cout
				<< "Implementa una cola con prioridad que contenga strings y de la que salgan primero las cadenas"
				<< endl
				<< "de caracteres que tengan más vocales y que en caso de igualdad salgan por orden alfabético."
				<< endl << endl;
			ejercicio13().ejecutar();
			cout << endl;
			break;
		case 14:
			cout
				<< "Implementa una función que, dada una lista l de tipo T y un elemento x de tipo T,"
				<< endl
				<< "elimine el elemento x de todas las posiciones en las que aparezca en l."
				<< endl << endl;
			ejercicioListas1().ejecutar();
			cout << endl;
			break;
		case 15:
			cout
				<< "Implementa una función elimina_duplicados(list<T> &l) que elimine los elementos"
				<< endl
				<< "duplicados (sin ordenar previamente la lista)."
				<< endl << endl;
			ejercicioListas2().ejecutar();
			cout << endl;
			break;
		case 16:
			cout
				<< "Implementa una función que dada una lista l devuelva un lista que tenga los"
				<< endl
				<< "elementos de l pero en orden inverso."
				<< endl << endl;
			ejercicioListas3().ejecutar();
			cout << endl;
			break;
		case 17:
			cout
				<< "Resuelve el problema anterior pero sobre una unica lista pasada por referencia."
				<< endl << endl;
			ejercicioListas4().ejecutar();
			cout << endl;
			break;
		case 18:
			cout
				<< "Implementa una función list<T> mezclar(const list<T> &l1, const list<T> &l2) que dadas"
				<< endl
				<< "dos listas ordenadas l1 y l2 devuelva una lista ordenada mezclando las dos listas."
				<< endl << endl;
			ejercicioListas5().ejecutar();
			cout << endl;
			break;
		case 19:
			cout
				<< "Implementa una función a la que se le pase una lista de enteros y un entero x de manera que"
				<< endl
				<< "cada vez que aparezca en una posición ese valor, se inserte x-1 en la posición siguiente"
				<< endl << endl;
			ejercicioListas6().ejecutar();
			cout << endl;
			break;
		case 20:
			cout
				<< "Implementa una función bool contenida(const list<T> &l1, const list<T> &l2) a la que se le pasen dos listas"
				<< endl
				<< "y compruebe si la lista l1 está contenida en l2 (si los elementos aparecen en la otra y en el mismo orden)"
				<< endl << endl;
			ejercicioListas7().ejecutar();
			cout << endl;
			break;
		case 21:
			cout
				<< "Tenemos dos listas: una, l, que contiene n de elmentos y otra, li, que contiene una serie de posiciones de la lista"
				<< endl
				<< "anterior (valores de la clase iterador). Construye una función a la que se le pasen esas dos listas y devuelva otra"
				<< endl
				<< "que contenga los elementos de l indicados por las posiciones de la lista li."
				<< endl << endl;
			ejercicioListas8().ejecutar();
			cout << endl;
			break;
		case 22:
			cout
				<< "Un vector disperso es un vector en el que la inmensa mayoría de sus elementos son nulos. Para representar ese"
				<< endl
				<< "tipo de vectores se suele utilizar como representación un lista:"
				<< endl << endl;
			ejercicioListas9().ejecutar();
			cout << endl;
			break;
		case 23:
			cout
				<< "Una variante del vector del disperso es aquella en la que se puede defínir cual es el valor nulo. Modifíca la"
				<< endl
				<< "clase anterior de forma que se pueda defínir cual es el valor nulo al crear un objeto. Implementa también cambiar_nulo(const T &n)"
				<< endl << endl;
			ejercicioListas10().ejecutar();
			cout << endl;
			break;
		case 24:
			cout
				<< "Una forma efíciente de guardar secuencias de valores iguales consiste en guardar cada uno de los valores seguido" << endl
				<< "del número de veces que aparece en la secuencia. Por ejemplo," << endl
				<< "<1,1,1,2,2,2,2,2,2,7,7,7,7,7,12,1,1,5,5>" << endl << "< (1,3), (2,6), (7,5), (12,1), (1,2), (5,2) >" << endl
				<< "Implementa las funciones:"	<< endl	<< "list<pair<T, int> > comprimir(const list<T> &l)+" << endl
				<< "list<T> descomprimir(const list<pair<T, int> > &lc)" << endl << "que permitan convertir entre ambas representaciones."
				<< endl << endl;
			ejercicioListas11().ejecutar();
			cout << endl;
			break;
		case 25:
			cout
				<< "Implementa la clase vector dinámico usando como representación el tipo list. ¿Qué orden de efíciencia tiene cada operación?"
				<< endl << endl;
			ejercicioListas12().ejecutar();
			cout << endl;
			break;
		case 26:
			cout
				<< "Este es el ejercicio 1 de arboles"
				<< endl << endl;
			ejercicioArboles1().ejecutar();
			cout << endl;
			break;
		case 51:
			cout << "Ejercicio Nº 1 de Examen Febrero 2014:" << endl;
			examen1().ejecutar();
			cout << endl;
			break;
		case 52:
			cout << "Ejercicio Nº 2 de Examen Febrero 2014: " << endl;
			examen2().ejecutar();
			cout << endl;
			break;
		default:
			exit(1);
		}
	} while (opcion != 0);
}

