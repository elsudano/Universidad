/*
 * main.cpp
 *
 *  Created on: 11/10/2013
 *      Author: Carlos de la Torre
 */

#include <unistd.h> // incluye ”sleep(....)”
#include <stdlib.h> // incluye ”rand(...)”
#include <iostream>
#include <vector>
#include <pthread.h>
#include <semaphore.h>

using namespace std;

static int contador = 1;
const int num_items = 1000000;		// Cantidad de Datos que vamos a Producir
const unsigned int tam_buff = 10;	// tamaño del buffer en memoria para pasar datos entre hebras

vector<int>  buffer (tam_buff,0);	// buffer en memoria
unsigned int primera_libre = 0;		// guardamos la posición de la siguiente celda libre que hay
unsigned int primera_ocupada = 0;

sem_t puede_producir;		// Declaramos los 3 semaforos que necesitamos
sem_t puede_consumir;		// Dos para los bloqueos de las hebras
sem_t sec_critica;			// y un tercero para gestionar la zona critica

int producir_dato(){
	return contador ++ ;
}

void consumir_dato( int dato ){
	cout << "dato recibido: " << dato << endl;
}

void * productor( void * ){
	for( unsigned int i = 0; i < num_items; i++ ){
		int dato = producir_dato();
		sem_wait(&puede_producir);
		buffer.at(primera_libre)=dato;	// falta: insertar ”dato” en el vector
		if (primera_libre<tam_buff-1){
			primera_libre++;
		}else{
			primera_libre=0;
		}
		sem_post(&puede_consumir);
	}
	return NULL;
}

void * consumidor( void * ){
	for( unsigned int i = 0; i < num_items; i++ ){
		sem_wait(&puede_consumir);
		int dato = buffer.at(primera_ocupada);
		if (primera_ocupada<tam_buff-1){
			primera_ocupada++;
		}else{
			primera_ocupada=0;
		}
		sem_post(&puede_producir);
		consumir_dato( dato );
	}
	return NULL;
}

int main(int argc,char *argv[]){
	pthread_t productora, consumidora;

	sem_init(&puede_producir, 0, 1);
	sem_init(&puede_consumir, 0, 0);

	pthread_create(&productora,NULL,productor,NULL); // hebra productora
	pthread_create(&consumidora,NULL,consumidor,NULL); // hebra consumidora
	pthread_join(productora,NULL);
	pthread_join(consumidora,NULL);
}

