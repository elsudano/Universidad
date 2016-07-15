#include <time.h> // incluye ”time(....)”
#include <unistd.h> // incluye ”sleep(....)”
#include <stdlib.h> // incluye ”rand(...)”
#include <iostream>
#include <semaphore.h>

using namespace std;

sem_t mutex, f_tabaco,f_papel,f_cerillas, ingredientes_retirados;
const unsigned long num_repartos = 20;

//Función que simula fumar, realmente es una “parada” de un tiempo de 0 a 4 segundos
void fumar(){
	sleep(rand()%5);
}

/*
* Las hebras fumador1,fumador2,fumador3 básicamente realizan lo mismo, hacemos un wait de su
* propio semáforo, despues wait y post del mutex para hacer el cout correctamente y finalmente un
* post del semáforo de los ingredientes indicando que él va ya a fumar. La diferencia es que cada
* fumador el primer wait lo hace de su propio semáforo, el resto es idem.
*/
void* fumador1( void* ){
	while(true){
		sem_wait(&f_tabaco);
		sem_wait(&mutex);
		cout << "\nFuma el que tiene tabaco. ";
		sem_post(&mutex);
		sem_post(&ingredientes_retirados);
		fumar();
	}
	return NULL;
}

void* fumador2( void* ){
	while(true){
		sem_wait(&f_papel);
		sem_wait(&mutex);
		cout << "\nFuma el	que tiene papel. ";
		sem_post(&mutex);
		sem_post(&ingredientes_retirados);
		fumar();
	}
	return NULL;
}

void* fumador3( void* ){
	while(true){
		sem_wait(&f_cerillas);
		sem_wait(&mutex);
		cout << "\nFuma el que tiene cerillas. ";
		sem_post(&mutex);
		sem_post(&ingredientes_retirados);
		fumar();
	}
	return NULL;
}

/*
* La hebra del estanquero realizará num_repartos iteraciones de manera que hace un wait de su
* propio semáforo y después genera un número que puede ser 1,2 ó 3, para indicar aleatoriamente
* el fumador que cogerá ingredientes, dependiendo de dicho número colocará unos ingredientes u
* otros, para cada fumador realizará wait post del mutex para hacer el cout correctamente y un post
* del fumador en concreto.
*/
void* colocar( void* ){
	for( unsigned long i = 0 ; i < num_repartos ; i++ ){
		sem_wait(&ingredientes_retirados);
		int quien = rand()%3 + 1;
		cout << quien;
		if(quien == 1){
			sem_wait(&mutex);
			cout << "\nEstanquero coloca papel y cerillas " << i+1 << "a vez";
			sem_post(&mutex);
			sem_post(&f_tabaco);
		}
		else if(quien == 2){
			sem_wait(&mutex);
			cout << "\nEstanquero coloca tabaco y cerillas " << i+1 << "a vez";
			sem_post(&mutex);
			sem_post(&f_papel);
		}
		else{
			sem_wait(&mutex);
			cout << "\nEstanquero coloca tabaco y papel " << i+1 << "a vez";
			sem_post(&mutex);
			sem_post(&f_cerillas);
		}
	}
	return NULL;
}


int main(){
	srand ( time(NULL) ); // inicializa la semilla aleatoria
	pthread_t fumador_tabaco, fumador_papel, fumador_cerillas, estanquero;
	sem_init( &f_tabaco, 0, 0 );
	sem_init( &f_papel, 0, 0 );
	sem_init( &f_cerillas, 0, 0 );
	sem_init( &ingredientes_retirados, 0, 1 ); // semaforo para colocar ingredientes: inicializado a 1
	sem_init( &mutex, 0, 1 ); // semaforo para EM para cout: inicializado a 1

	cout << "Comienza el reparto:";
	pthread_create( &fumador_tabaco, NULL, fumador1, NULL );
	pthread_create( &fumador_papel, NULL, fumador2, NULL );
	pthread_create( &fumador_cerillas, NULL, fumador3, NULL );
	pthread_create( &estanquero, NULL, colocar, NULL );
	pthread_join( estanquero, NULL );
	sem_wait(&mutex);
	cout << "\n\nFin." << endl ;
	sem_post(&mutex);

	/*
	* Si el número de iteraciones fuese finito, deberiamos usar un pthread_join pero dado que es un
	* bucle true no llegarán a ejecutarse los join.
	*
	* pthread_join( fumador_tabaco, NULL );
	* pthread_join( fumador_papel, NULL );
	* pthread_join( fumador_cerillas, NULL );
	*/
	//Destruimos los semáforos para liberar recursos
	sem_destroy( &f_tabaco );
	sem_destroy( &f_papel );
	sem_destroy( &f_cerillas );
	sem_destroy( &ingredientes_retirados );
	sem_destroy( &mutex );
	return 0;
}
