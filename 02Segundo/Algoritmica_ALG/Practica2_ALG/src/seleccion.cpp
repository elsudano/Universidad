/*
 * Algoritmo Selección
 * 		Autor: Carlos de la Torre
 * 		Fecha: 07/05/14
 */
#include <vector>
#include <iostream>
#include <iomanip>
#include <string>
#include <cstdlib>
#include <ctime>
#include "../inc/concolor.h"

using namespace std;

#define TAM_MIN_VEC 9
// con esto definimos que el tamaño mínimo
// del vector para que tenga buenos resultados
// la selección de la mediana
#define DEBUGMODE 1
// con esta definición nos aseguramos que solo
// salgan las cifras de tiempo en cada ejecución
// así de esa manera es mas fácil realizar el
// estudio empírico del programa

void intercambia (vector<int> &lista, int posi, int posd){
	int temporal;
    temporal= lista.at(posi);
    lista.at(posi)=lista.at(posd);
    lista.at(posd)=temporal;
}

int Pivote(vector<int> &lista,int limite_izq,int limite_der){
    int tmp, respuesta, pivote;

    pivote = lista.at(limite_izq);
    tmp = limite_izq;
    respuesta = limite_der;

    for(;lista.at(tmp)>=pivote and tmp<limite_der;tmp++);
    if (DEBUGMODE){
    	cout <<redb<< " ----------------------------------------------------" << endl;
    	cout << " Posición del puntero de la Izquierda" <<whiteb<< endl;
    	cout <<green<< " Valor del pivote: " << pivote << ", Posición del pivote: " << limite_izq <<whiteb<< endl << " ";
    	for (unsigned int i=0; i<lista.size(); i++) {
			if (i==tmp)
				cout <<redb<< lista.at(i) <<whiteb;
			else
				cout << lista.at(i);

			if(i<lista.size()-1)
				cout << ",";
			else
				cout << endl;
		}
		cout << " En este momento el valor del puntero izquierdo es mas pequeño o igual que el valor del pivote" << endl;
    	cout <<redb<< " ----------------------------------------------------" <<whiteb<< endl;
    }
    for(;lista.at(respuesta)<=pivote and respuesta>limite_izq;respuesta--);
    if (DEBUGMODE){
    	cout <<blueb<< " ----------------------------------------------------" << endl;
    	cout << " Posición del puntero de la Derecha" <<whiteb<< endl;
    	cout <<green<< " Valor del pivote: " << pivote << ", Posición del pivote: " << limite_izq <<whiteb<< endl << " ";
    	for (unsigned int i=0; i<lista.size(); i++) {
			if (i==respuesta)
				cout <<blueb<< lista.at(i) <<whiteb;
			else
				cout << lista.at(i);

			if(i<lista.size()-1)
				cout << ",";
			else
				cout << endl;
		}
		cout << " En este momento el valor del puntero derecho es mas grande o igual que el valor del pivote" << endl;
    	cout <<blueb<< " ----------------------------------------------------" <<whiteb<< endl;
    }
    while (tmp<respuesta){
    	if (DEBUGMODE){
			cout <<yellowb<< " ----------------------------------------------------" << endl;
			cout << " Posicionados los dos punteros si el izquierdo es mas pequeño " << endl;
			cout << " que el derecho se intercambian los valores y se sigue recorriendo" << endl;
			cout << " el vector hasta que se crucen los punteros" <<whiteb<< endl << " ";
			for (unsigned int i=0; i<lista.size(); i++) {
				if (i==tmp)
					cout <<redb<< lista.at(i) <<whiteb;
				else if (i==respuesta)
					cout <<blueb<< lista.at(i) <<whiteb;
				else
					cout << lista.at(i);

				if(i<lista.size()-1)
					cout << ",";
				else
					cout <<green<< " Antés" <<whiteb<< endl << " ";
			}
    	}
    	intercambia(lista,tmp,respuesta);
    	if (DEBUGMODE){
			for (unsigned int i=0; i<lista.size(); i++) {
				if (i==tmp)
					cout <<yellowb<< lista.at(i) <<whiteb;
				else if (i==respuesta)
					cout <<yellowb<< lista.at(i) <<whiteb;
				else
					cout << lista.at(i);

				if(i<lista.size()-1)
					cout << ",";
				else
					cout <<green<< " Despúes" << endl;
			}
			cout <<yellowb<< " ----------------------------------------------------" <<whiteb<< endl;
		}
    	for(;lista.at(tmp)>=pivote;tmp++);
    	if (DEBUGMODE){
			cout <<redb<< " ----------------------------------------------------" << endl;
			cout << " Seguimos recorriendo el vector, posición del puntero de la Izquierda" <<whiteb<< endl;
			cout <<green<< " Valor del pivote: " << pivote << ", Posición del pivote: " << limite_izq <<whiteb<< endl << " ";
			for (unsigned int i=0; i<lista.size(); i++) {
				if (i==tmp)
					cout <<redb<< lista.at(i) <<whiteb;
				else
					cout << lista.at(i);

				if(i<lista.size()-1)
					cout << ",";
				else
					cout << endl;
			}
			cout << " En este momento el valor del puntero izquierdo es mas pequeño o igual que el valor del pivote" << endl;
			cout <<redb<< " ----------------------------------------------------" <<whiteb<< endl;
		}
    	for(;lista.at(respuesta)<=pivote;respuesta--);
        if (DEBUGMODE){
        	cout <<blueb<< " ----------------------------------------------------" << endl;
        	cout << " Seguimos recorriendo el vector, posición del puntero de la Derecha" <<whiteb<< endl;
        	cout <<green<< " Valor del pivote: " << pivote << ", Posición del pivote: " << limite_izq <<whiteb<< endl << " ";
        	for (unsigned int i=0; i<lista.size(); i++) {
    			if (i==respuesta)
    				cout <<blueb<< lista.at(i) <<whiteb;
    			else
    				cout << lista.at(i);

    			if(i<lista.size()-1)
    				cout << ",";
    			else
    				cout << endl;
    		}
    		cout << " En este momento el valor del puntero derecho es mas grande o igual que el valor del pivote" << endl;
        	cout <<blueb<< " ----------------------------------------------------" <<whiteb<< endl;
        }
    }
    if (DEBUGMODE){
		cout <<yellowb<< " ----------------------------------------------------" << endl;
		cout << " Como los punteros se han cruzado lo que se hace" << endl;
		cout << " es intercambiar la posición del pivote actual" << endl;
		cout << " por la posición del puntero de la derecha" <<whiteb<< endl;
		cout <<green<< " Valor del pivote: " << pivote << ", Posición del pivote: " << limite_izq <<whiteb<< endl << " ";
		for (unsigned int i=0; i<lista.size(); i++) {
			if (i==tmp)
				cout <<redb<< lista.at(i) <<whiteb;
			else if (i==respuesta)
				cout <<blueb<< lista.at(i) <<whiteb;
			else
				cout << lista.at(i);

			if(i<lista.size()-1)
				cout << ",";
			else
				cout <<green<< " Antés" <<whiteb<< endl << " ";
		}
	}
    intercambia(lista,limite_izq,respuesta);
    if (DEBUGMODE){
		for (unsigned int i=0; i<lista.size(); i++) {
			if (i==limite_izq)
				cout <<yellowb<< lista.at(i) <<whiteb;
			else if (i==respuesta)
				cout <<yellowb<< lista.at(i) <<whiteb;
			else
				cout << lista.at(i);

			if(i<lista.size()-1)
				cout << ",";
			else
				cout <<green<< " Despúes" <<whiteb<< endl;
		}
		cout <<yellowb<< " ----------------------------------------------------" <<whiteb<< endl;
	}
	return respuesta;
}

int seleccion(vector<int> &lista, int pos){
	int pivote = 0;
	int puntero_izq = 0;
	int puntero_der = lista.size()-1;
	while (pivote!=pos){
		pivote = Pivote(lista,puntero_izq,puntero_der);
		if (pos < pivote){
		    if (DEBUGMODE){
		    	cout <<magentab<< "----------------------------------------------------" <<whiteb<< endl;
				cout << " El valor que ha devuelto la funcion pivote() es: " << pivote << endl;
				cout << " esta es la posición actual que tiene el pivote cuyo valor es: " << lista.at(pivote) << endl;
			}
			puntero_der = pivote-1;
			if (DEBUGMODE){
				cout << " Como la posición que buscamos es menor que la posición devuelta" << endl;
				cout << " lo que se hace a continuación es partir el vector en 2" << endl;
				cout << " de tal forma que la posición devuelta menos una unidad" << endl;
				cout << " será el limite superior quedando el limite en: " << puntero_der << endl;
				cout << " que tiene el valor: "<< lista.at(puntero_der) << endl << " ";
				for (unsigned int i=0; i<lista.size(); i++) {
					if (i==puntero_der)
						cout <<greenb<< lista.at(i) <<whiteb;
					else
						cout << lista.at(i);

					if(i<lista.size()-1)
						cout << ",";
					else
						cout << endl;
				}
				cout <<magentab<< " ----------------------------------------------------" <<whiteb<< endl;
			}
		}else if (pos > pivote){
			if (DEBUGMODE){
				cout <<magentab<< " ----------------------------------------------------" <<whiteb<< endl;
				cout << " El valor que ha devuelto la funcion pivote() es: " << pivote << endl;
				cout << " esta es la posición actual que tiene el pivote cuyo valor es: " << lista.at(pivote) << endl;
			}
			puntero_izq = pivote+1;
			if (DEBUGMODE){
				cout << " Como la posición que buscamos es mayor que la posición devuelta" << endl;
				cout << " lo que se hace a continuación es partir el vector en 2" << endl;
				cout << " de tal forma que el la posición devuelta mas una unidad" << endl;
				cout << " será el limite inferior quedando el limite en: " << puntero_izq << endl;
				cout << " que tiene el valor: "<< lista.at(puntero_izq) << endl << " ";
				for (unsigned int i=0; i<lista.size(); i++) {
					if (i==puntero_izq)
						cout <<greenb<< lista.at(i) <<whiteb;
					else
						cout << lista.at(i);

					if(i<lista.size()-1)
						cout << ",";
					else
						cout << endl;
				}
				cout <<magentab<< " ----------------------------------------------------" <<whiteb<< endl;
			}
		}
	}
	return lista.at(pivote);
}

int main(int argc, const char * argv[]){
	struct timespec t1, t2;
	double tresultado;
    int num,MORE;
    num=MORE=0;

    if (argv[1]==NULL){
    	cout << " Cuantos numeros quiere generar: " << endl;
    	cin >> num;
    }else
    	num=atoi(argv[1]);

    if (argv[2]==NULL){
		cout << " Quiere mostrar los vectores 0/1: " << endl;
		cin >> MORE;
	}else
		MORE=atoi(argv[2]);

    // creamos el vector según el tamaño introducido
    vector<int> lista(num);

    // plantamos la semilla para los aleatorios
    srand(num);

    // inicializamos el vector con los datos
    if (DEBUGMODE){
		lista.at(0)=4;
		lista.at(1)=1;
		lista.at(2)=9;
		lista.at(3)=7;
		lista.at(4)=3;
		lista.at(5)=2;
		lista.at(6)=6;
		lista.at(7)=8;
		lista.at(8)=5;
    }else{
		for (int i=0; i<num; i++){
			lista.at(i)=rand()%100;
		}
    }

    // imprimimos la lista sin ordenar
    if (DEBUGMODE or MORE){
		cout <<whiteb<< " Lista Desordenada " << endl << " ";
		for (unsigned int i=0; i<lista.size(); i++) {
			cout << lista.at(i);
			if(i<lista.size()-1)
				cout << ",";
			else
				cout << endl;
		}
		cout <<white<< endl;
    }
    // Medición del tiempo de ejecución del algorítmo quickSort();
    clock_gettime(CLOCK_REALTIME, &t1);
    int ordenado = seleccion(lista,lista.size()/2);
    clock_gettime(CLOCK_REALTIME, &t2);
    tresultado = (double) (t2.tv_sec - t1.tv_sec) + (double) ((t2.tv_nsec - t1.tv_nsec) / (1.e+9));

    // si hémos ordenado la lista la mostramos
    if (DEBUGMODE or MORE){
		cout <<whiteb<< " Lista Modificada " << endl << " ";
		for (unsigned int i=0; i<lista.size(); i++) {
			cout << lista.at(i);
			if(i<lista.size()-1)
				cout << ",";
			else
				cout << endl;
		}
		cout <<whiteb<< endl;
		cout << " Valor del elemento seleccionado: " << ordenado << endl;
		cout << " Tiempo empleado en la ordenación: " << setiosflags(ios::fixed) << setprecision(9) << tresultado <<normal<< endl;
    }else{
    	cout <<whiteb<< " Valor del elemento seleccionado: " << ordenado << endl;
    	cout << setiosflags(ios::fixed) << setprecision(9) << tresultado <<normal<< endl;
    }
    return 0;
}
