/*
 * Fumadores.cpp
 *
 *  Created on: 21/10/2013
 *      Author: Carlos de la Torre
 */

#include <fun_tiempo.h> // incluye ”time(....)”
#include <unistd.h> // incluye ”sleep(....)”
#include <stdlib.h> // incluye ”rand(...)”
#include <iostream>
#include <pthread.h>
#include <semaphore.h>
#include <vector>

using namespace std;

sem_t sem_estanco;
sem_t sem_fumador1;
sem_t sem_fumador2;
sem_t sem_fumador3;
sem_t sec_critica;

// VARIABLES DE EJECUCIÓN
bool PararDeFumar = false;
unsigned long num_fumador = 3; // con esto podemos regular el numero de Fumadores
int cajetillas = 15; // estas son las cajetillas que se pueden producir por fumador
vector<int>  Almacen (cajetillas*num_fumador,0);	// buffer en memoria
int primera_libre = 0;
// función que simula la acción de fumar
// como un retardo aleatorio de la hebra
void fumar(int num_fumador){
	sleep((rand()%5)*2);
	sem_wait(&sec_critica);
	Almacen.at(primera_libre)=num_fumador;
	primera_libre++;
	sem_post(&sec_critica);
}

// Esta sería la función que se encargaria de
// producir los elementos para que los fumadores
// puedan fumar
void estanco(){
	cout << "Quiere cerrar el estanco?? y/n" << endl;
}

void * quiereFumar( void * num_void){
	int num = (unsigned long) num_void ;
	while (!PararDeFumar)
		fumar(num);
	return NULL;
}

void * quiereVender( void * ){
	char c;
	while (c!='y'){
		estanco();
		cin >> c;
	}
	PararDeFumar=true;
	return NULL;
}

int main(int argc,char *argv[]){
	srand ( time(NULL) ); // inicializa la semilla aleatoria
	pthread_t id_hebra[num_fumador];

	sem_init(&sem_estanco, 0, 1);
	sem_init(&sem_fumador1, 0, 0);
	sem_init(&sem_fumador2, 0, 0);
	sem_init(&sem_fumador3, 0, 0);
	sem_init(&sec_critica, 0, 1);

	struct timespec inicio = ahora(); //con esto regulamos el tiempo transcurrido del programa completo

	pthread_create( &(id_hebra[0]), NULL, quiereVender,NULL ); // Esta sera la hebra del estanquero

	for( unsigned long i = 1 ; i <= num_fumador ; i++)// crear y lanzar num_fumador, cada una ejecuta ”quiereFumar()”
		pthread_create( &(id_hebra[i]), NULL, quiereFumar,(void *) i );

	for( unsigned long i = 1 ; i <= num_fumador ; i++){
		pthread_join( id_hebra[i],NULL);// esperar (join) a que termine cada hebra
	}

	pthread_join( id_hebra[0],NULL); // El estanquero es el ultimo en cerrar el estanco
	struct timespec fin = ahora();
	cout << "Datos del Almacen == ";
	for (unsigned int j=0 ; j<Almacen.size()-1 ; j++){
		cout << Almacen[j] << ",";
	}
	cout << endl;
	cout << "tiempo transcurrido == " << duracion( &inicio, &fin ) << " seg." << endl;
}

