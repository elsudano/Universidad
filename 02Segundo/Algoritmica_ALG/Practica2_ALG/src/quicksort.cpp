/*
 * Algoritmo QuickSort
 * 		Autor: Carlos de la Torre
 * 		Fecha: 07/05/14
 */
#include <vector>
#include <iostream>
#include <iomanip>
#include <string>
#include <cstdlib>
#include <ctime>

using namespace std;

#define TAM_MIN_VEC 9
// con esto definimos que el tamaño mínimo
// del vector para que tenga buenos resultados
// la selección de la mediana
#define DEBUGMODE 0
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

void Pivote(vector<int> &lista,int limite_izq,int limite_der){
    int izq,der,pivote;

    izq = limite_izq;
    der = limite_der;
    pivote = lista.at((izq+der)/2);

    do{
        while(lista.at(izq)<pivote && izq<limite_der)
        	izq++;
        while(pivote<lista.at(der) && der>limite_izq)
        	der--;
        if(izq <= der){
        	intercambia(lista,izq,der);
            izq++;
            der--;
        }
    }while(izq <= der);

	if(limite_izq<der)
		Pivote(lista,limite_izq,der);
	if(limite_der>izq)
		Pivote(lista,izq,limite_der);
}

bool quicksort(vector<int> &lista){
	bool respuesta=false;
	if (lista.size() > TAM_MIN_VEC){
		respuesta = true;
		Pivote(lista,0,lista.size()-1);
	}else
		cout << "el Vector es muy pequeño" << endl;
	return respuesta;
}

int main(int argc, const char * argv[]){
	struct timespec t1, t2;
	double tresultado;
    int num=0;
    if (argv[1]==NULL){
    	cout << " Cuantos numeros quiere generar: " << endl;
    	cin >> num;
    }else
    	num=atoi(argv[1]);

    // creamos el vector segun el tamaño introducido
    vector<int> lista(num);

    // plantamos la semilla para los aleatorios
    srand(num);

    // inicializamos el vector con los datos
    for (int i=0; i<num; i++){
    	lista.at(i)=rand()%100;
    }

    // imprimimos la lista sin ordenar
    if (!DEBUGMODE){
		cout << "Lista Desordenada " << endl;
		for (unsigned int i=0; i<lista.size(); i++) {
			cout << lista.at(i);
			if(i<lista.size()-1)
				cout << ",";
			else
				cout << endl;
		}
		cout << endl;
    }

    // Medición del tiempo de ejecución del algorítmo quickSort();
    clock_gettime(CLOCK_REALTIME, &t1);
    bool ordenado = quicksort(lista);
    clock_gettime(CLOCK_REALTIME, &t2);
    tresultado = (double) (t2.tv_sec - t1.tv_sec) + (double) ((t2.tv_nsec - t1.tv_nsec) / (1.e+9));

    // si hémos ordenado la lista la mostramos
    if (ordenado){
    	if (!DEBUGMODE){
    			cout << "Lista Modificada " << endl;
    			for (unsigned int i=0; i<lista.size(); i++) {
    				cout << lista.at(i);
    				if(i<lista.size()-1)
    					cout << ",";
    				else
    					cout << endl;
    			}
    			cout << endl;
    			cout << "Valor del elemento seleccionado: " << lista.at((lista.size()-1)/2) << endl;
    			cout << "Tiempo empleado en la ordenación: " << setiosflags(ios::fixed) << setprecision(9) << tresultado << endl;
    	    }else{
    	    	cout << "Valor del elemento seleccionado: " << lista.at((lista.size()-1)/2) << endl;
    	    	cout << setiosflags(ios::fixed) << setprecision(9) << tresultado << endl;
    	    }
    }else
    	cout << "Error al ordenar el vector " << endl;
    return 0;
}
