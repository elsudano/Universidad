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
#include <string>

using namespace std;

// VARIABLES DE EJECUCIÓN///////////////////////////////////////////////////////////
unsigned long num_fumadores = 3; // con esto podemos regular el numero de Fumadores
int ingredientes = 10; // estas son las cajetillas que se pueden producir por fumador
// VARIABLES DE EJECUCIÓN///////////////////////////////////////////////////////////

// aunque el semaforo sea este la hebra es id_hebra[0]
sem_t sem_estanquero;
// lo convertimos a entero y le sumamos 1 [1,2,3]
// las hebras de los fumadores serian id_hebra[1,2,3]
// y los semaforos de los mismo serian sem_fumadores[1,2,3]
int num_semaforos = (int) num_fumadores + 1;
sem_t sem_fumadores[4];
// este es el semaforo de la seccion critica
sem_t sec_critica;

void * quiereFumar(void * num_void) {
	int num_hebra = (unsigned long) num_void;
	int contador = 1;
	while (true) {
		int seg = rand() % 60;
		sem_wait(&sem_fumadores[num_hebra]);
		sem_wait(&sec_critica);
		cout << "El fumador que esta fumando es el que le faltaba el ingrediente: "	<< num_hebra << endl
			 << "Tiempo Aleatorio de: " << seg << " seg. y es la " << contador << " vez que fumo" << endl
			 << endl;
		contador++;
		sem_post(&sec_critica);
		sem_post(&sem_estanquero);
		sleep(seg); // proceso gordo
		sem_wait(&sec_critica);
		cout << "El fumador: " << num_hebra << " ha terminado" << endl << endl;
		sem_post(&sec_critica);
	}
	return NULL;
}

void * quiereVender(void *) {
	int aleatorio;
	string pregunta;
	while (pregunta != "n") {
		for (int c = 1; c < ingredientes + 1; c++) {
			aleatorio = rand() % 3 + 1;
			sem_wait(&sem_estanquero); // ponemos en wait al estanquero
			sem_wait(&sec_critica);
			cout << "El estanquero coloca el producto nº " << aleatorio << " encima del mostrador, la " << c << " vez" << endl << endl;
			sem_post(&sec_critica);
			sem_post(&sem_fumadores[aleatorio]); // hacemos que fume el fumador correspondiente
		}
		cout << "¿Quiere repartir otros " << ingredientes << " ingredientes? y/n" << endl << endl;
		cin >> pregunta;
	}

	sem_destroy(&sem_fumadores[1]);
	return NULL;
}

int main(int argc, char *argv[]) {
	srand(time(NULL)); // inicializa la semilla aleatoria
	struct timespec inicio = ahora(); //con esto regulamos el tiempo transcurrido del programa completo

	pthread_t id_hebra[num_fumadores]; //estas son las hebras de los fumadores

	sem_init(&sem_estanquero, 0, 1); // este es el semaforo del estanquero
	sem_init(&sec_critica, 0, 1);

	pthread_create(&(id_hebra[0]), NULL, quiereVender, NULL); // Esta sera la hebra del estanquero

	for (unsigned long i = 1; i <= num_fumadores; i++) { // crear y lanzar num_fumador, cada una ejecuta ”quiereFumar()”
		pthread_create(&(id_hebra[i]), NULL, quiereFumar, (void *) i);
		sem_init(&sem_fumadores[(int) i], 0, 0);
	}

	pthread_join(id_hebra[0], NULL); // El estanquero es el ultimo en cerrar el estanco
	sem_destroy(&sem_estanquero);
	sem_destroy(&sec_critica);
	struct timespec fin = ahora();
	cout << "\ntiempo transcurrido == " << duracion(&inicio, &fin) << " seg."
			<< endl;
}

