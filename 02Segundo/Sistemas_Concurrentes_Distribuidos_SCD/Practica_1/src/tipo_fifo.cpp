/*
 * main.cpp
 *
 *  Created on: 11/10/2013
 *      Author: Carlos de la Torre
 */
#include <iostream>
//#include <vector>
#include <pthread.h>
#include <semaphore.h>
#include "comunes.h"
#include "fun_tiempo.h"

using namespace std;

vector<int>  buffer (tam_buff,0);	// buffer en memoria
unsigned int primera_libre = 0;
unsigned int primera_ocupada = 0;

sem_t puede_producir;				// Declaramos los dos semaforos que necesitamos
sem_t puede_consumir;

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

	struct timespec inicio = ahora();
	pthread_create(&productora,NULL,productor,NULL); // hebra productora
	pthread_create(&consumidora,NULL,consumidor,NULL); // hebra consumidora
	pthread_join(productora,NULL);
	pthread_join(consumidora,NULL);
	struct timespec fin = ahora();
	cout << "tiempo transcurrido == " << duracion( &inicio, &fin ) << " seg." << endl;
}
