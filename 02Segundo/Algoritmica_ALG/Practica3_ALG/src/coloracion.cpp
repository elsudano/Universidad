/*
 * coloracion.cpp
 *
 * 		Fecha: 03/06/14
 * 		Desarrollado: Carlos de la Torre
 *
 */
#include <iostream>
#include <vector>
#include "../inc/concolor.h"

#define TAM_GRAFO 10 // Esta es la cantidad de nodos que puede tener el gráfo

using namespace std;

// De que colores se pueden pintar los nodos, el último es para saber la cantidad de colores que hay Cantidad = 6;
enum Color { Blanco, Rojo, Verde, Azul, Amarillo, Magenta, Cian, Cantidad};

// Estructura de un nodo
struct miNodo{
	int numero_nodo;
	Color color;
	int cantidad_nodos_adyacentes;
	miNodo *nodos_adyacentes[5];
};

// Creamos el Grafo que queremos colorear
void CrearGrafo(miNodo *grafo[]){
	miNodo *uno, *dos, *tres, *cuatro, *cinco, *seis, *siete, *ocho, *nueve, *diez;

	// Creando los nodos del gráfo
	uno = new miNodo;
	dos = new miNodo;
	tres = new miNodo;
	cuatro = new miNodo;
	cinco = new miNodo;
	seis = new miNodo;
	siete = new miNodo;
	ocho = new miNodo;
	nueve = new miNodo;
	diez = new miNodo;

	// Hasta aquí hémos creado los nodos a partir de
	// aquí es donde cremos los arcos del gráfo
	uno->numero_nodo = 1;
	uno->color = Color(Blanco);
	uno->cantidad_nodos_adyacentes = 3;
	uno->nodos_adyacentes[0] = dos;
	uno->nodos_adyacentes[1] = tres;
	uno->nodos_adyacentes[2] = cuatro;
	dos->numero_nodo = 2;
	dos->color = Color(Blanco);
	dos->cantidad_nodos_adyacentes = 3;
	dos->nodos_adyacentes[0] = uno;
	dos->nodos_adyacentes[1] = cinco;
	dos->nodos_adyacentes[2] = ocho;
	tres->numero_nodo = 3;
	tres->color = Color(Blanco);
	tres->cantidad_nodos_adyacentes = 2;
	tres->nodos_adyacentes[0] = uno;
	tres->nodos_adyacentes[1] = cinco;
	cuatro->numero_nodo = 4;
	cuatro->color = Color(Blanco);
	cuatro->cantidad_nodos_adyacentes = 2;
	cuatro->nodos_adyacentes[0] = uno;
	cuatro->nodos_adyacentes[1] = seis;
	cinco->numero_nodo = 5;
	cinco->color = Color(Blanco);
	cinco->cantidad_nodos_adyacentes = 3;
	cinco->nodos_adyacentes[0] = dos;
	cinco->nodos_adyacentes[1] = tres;
	cinco->nodos_adyacentes[2] = seis;
	seis->numero_nodo = 6;
	seis->color = Color(Blanco);
	seis->cantidad_nodos_adyacentes = 3;
	seis->nodos_adyacentes[0] = cuatro;
	seis->nodos_adyacentes[1] = cinco;
	seis->nodos_adyacentes[2] = siete;
	siete->numero_nodo = 7;
	siete->color = Color(Blanco);
	siete->cantidad_nodos_adyacentes = 4;
	siete->nodos_adyacentes[0] = seis;
	siete->nodos_adyacentes[1] = ocho;
	siete->nodos_adyacentes[2] = nueve;
	siete->nodos_adyacentes[3] = diez;
	ocho->numero_nodo = 8;
	ocho->color = Color(Blanco);
	ocho->cantidad_nodos_adyacentes = 2;
	ocho->nodos_adyacentes[0] = siete;
	ocho->nodos_adyacentes[1] = dos;
	nueve->numero_nodo = 9;
	nueve->color = Color(Blanco);
	nueve->cantidad_nodos_adyacentes = 1;
	nueve->nodos_adyacentes[0] = siete;
	diez->numero_nodo = 10;
	diez->color = Color(Blanco);
	diez->cantidad_nodos_adyacentes = 1;
	diez->nodos_adyacentes[0] = siete;

	// aqui metemos los nodos del gráfo en un array
	// para poder recorrerlos lo ideal seria en una
	// pila para sacarlos mas rapidamente
	grafo[0] = uno;
	grafo[1] = dos;
	grafo[2] = tres;
	grafo[3] = cuatro;
	grafo[4] = cinco;
	grafo[5] = seis;
	grafo[6] = siete;
	grafo[7] = ocho;
	grafo[8] = nueve;
	grafo[9] = diez;
}

// Colorear el grafo dado
void ColorearGrafo(miNodo *grafo[]){
	int NodosSinPintar = TAM_GRAFO;
	int color;
	bool color_valido;

	while(NodosSinPintar > 0){
		for(unsigned int num_nodo = 0; num_nodo < TAM_GRAFO; num_nodo++){ // Recorro todos los Nodos del Grafo
			if (grafo[num_nodo]->cantidad_nodos_adyacentes > 0){ // compruebo si el nodo tiene nodos adyacentes
				for (int num_nodo_adya = 0; num_nodo_adya < grafo[num_nodo]->cantidad_nodos_adyacentes;++num_nodo_adya){// recorremos todos los nodos adyacentes
					if(grafo[num_nodo]->nodos_adyacentes[num_nodo_adya]->color == Color(Blanco)){// comprobamos si el nodo adyacente no lo hemos pintado
						color = Color(Rojo); // como no hemos pintado el nodo adyacente ponemos el color al primero que se puede usar
						color_valido = false; // y esta variable auxiliar nos dirá si podemos usar el color temporal que hémos escogido
						if (grafo[num_nodo]->nodos_adyacentes[num_nodo_adya]->cantidad_nodos_adyacentes > 0){
							// con esto lo que hacemos es visitar los nodos adyacentes del nodo adyacente que estamos coloreando
							// osea que alguno de estos nodos será el nodo désde donde venimos
							for (int num_nodo_adya2 = 0; num_nodo_adya2 < grafo[num_nodo]->nodos_adyacentes[num_nodo_adya]->cantidad_nodos_adyacentes;++num_nodo_adya2){// recorremos los nodos adyacentes
								if (grafo[num_nodo]->nodos_adyacentes[num_nodo_adya]->nodos_adyacentes[num_nodo_adya2]->color != static_cast<Color>(color))
									// y en este if es donde comprobamos si el color que hemos elegido podemos usarlo para pintar el nodo adyacente
									color_valido = true;
								else
									// si no pudieramos usar el color actualmente asignado por que ya hay un nodo adyacente con ese color elegimos el siguiente
									color++;
							}
						}
						if (color_valido) // como ya sabemos que tenemos el color correcto para colorear el nodo adyacente lo coloreamos
							grafo[num_nodo]->nodos_adyacentes[num_nodo_adya]->color = static_cast<Color>(color);//pintamos el nodo de otro color que el nodo padre
						NodosSinPintar--; // quitamos un nodo del vector sin colorear
					}
				}
			}else{ // si no tuviera nodos adyacentes seria un grafo con un solo nodo
				if (grafo[num_nodo]->color == Color(Blanco))
					grafo[num_nodo]->color = static_cast<Color>(color);
				NodosSinPintar--; // quitamos un nodo del vector sin colorear
			}
		}
	}
}

// Funcion para imprimir por pantalla el Grafo dado
void ImprimirGrafo(miNodo *grafo[]){
	for(unsigned int i = 0; i < TAM_GRAFO; i++){
		if (grafo[i]->color == Blanco)
			cout<<whiteb;
		if (grafo[i]->color == Rojo)
			cout<<redb;
		if (grafo[i]->color == Verde)
			cout<<greenb;
		if (grafo[i]->color == Azul)
			cout<<blueb;
		if (grafo[i]->color == Amarillo)
			cout<<yellowb;
		if (grafo[i]->color == Magenta)
			cout<<magentab;
		if (grafo[i]->color == Cian)
			cout<<cyanb;
		cout << " " << grafo[i]->numero_nodo;
		cout<<normal;
	cout <<endl;
	}
}

int main(int argc, const char * argv[]){
	miNodo *elGrafo[TAM_GRAFO];
	CrearGrafo(elGrafo);
	ColorearGrafo(elGrafo);
	ImprimirGrafo(elGrafo);
	return 0;
}
