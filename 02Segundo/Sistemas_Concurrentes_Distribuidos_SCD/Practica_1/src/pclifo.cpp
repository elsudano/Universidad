#include <iostream>
#include <pthread.h>
#include <semaphore.h>
#include <vector>

using namespace std;

const int num_items = 1000000;			// Cantidad de Datos que vamos a Producir
const unsigned int tam_buff = 10;	// tamaño del buffer en memoria para pasar datos entre hebras

vector<int>  buffer (tam_buff,0);	// buffer en memoria
int primera_libre = 0;				// guardamos la posición de la siguiente celda libre que hay

sem_t puede_producir;		// Declaramos los 3 semaforos que necesitamos
sem_t puede_consumir;		// Dos para los bloqueos de las hebras
sem_t sec_critica;			// y un tercero para gestionar la zona critica

int producir_dato(){
	static int contador = 1;
	return contador ++ ;
}

void consumir_dato( int dato ){
	cout << "dato recibido: " << dato << endl;
}

void * productor( void * ){
	for( unsigned int i = 0; i < num_items; i++ ){
		int dato = producir_dato();
		sem_wait(&puede_producir);
		sem_wait(&sec_critica);
		buffer.at(primera_libre)=dato;
		primera_libre++;
		sem_post(&sec_critica);
		sem_post(&puede_consumir);
	}
	return NULL;
}

void * consumidor( void * ){
	for( unsigned int i = 0; i < num_items; i++ ){
		sem_wait(&puede_consumir);				// si esta a 0 se pone en suspendido el consumidor a la espera de que el productor lo ponga a 1
		sem_wait(&sec_critica);					// si esta a 0 se pone en suspendido por que alguien esta usando el trozo de codigo, esperamos a que se ponga a 1
		primera_libre--;
		int dato = buffer.at(primera_libre);
		sem_post(&sec_critica);					// como ya hemos ejecutado nuestro codigo lo ponemos a 1 para que otro pueda ejecutarlo
		sem_post(&puede_producir);				// como ya hemos consumido el dato pues le damos permiso al semaforo de produccion para que nos de uno nuevo
		consumir_dato( dato );
	}
	return NULL;
}

int main(int argc,char *argv[]){
	pthread_t productora, consumidora;

	sem_init(&puede_producir, 0, 1);		// este lo ponemos a 1 para que el productor cree un dato
	sem_init(&puede_consumir, 0, 0);		// este lo inicializamos a 0 para que no consuma un vacio
	sem_init(&sec_critica, 0, 1);			// y este lo ponemos a 1 para que el que llegue primero ejecute la zona critica

	pthread_create(&productora,NULL,productor,NULL); 	// hebra productora
	pthread_create(&consumidora, NULL,consumidor,NULL); // hebra consumidora
	pthread_join(productora,NULL);						// Para que la hebra principal no termine antes
	pthread_join(consumidora,NULL);						// que las hebras Prod. y Consu. ponemos los JOIN
}
