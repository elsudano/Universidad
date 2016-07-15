#include "mpi.h"
#include <time.h>
#include <stdlib.h>
#include <stdlib.h> 	// incluye ”rand(...)” y ”srand”
#include <unistd.h> 	// incluye ”sleep(....)” y ”usleep(...)”
#include <iostream>
using namespace std;

/* Función Filosofo */
void Filosofo(int id, int nprocesos){	// Código proceso Filosofo
	int izq = (id + 1) % nprocesos;
	int der = (id-1 + nprocesos) % nprocesos;

	while(1){

		//Si uno de los filosofos coge los tenedores al reves que el resto se evitara interbloqueo
		if(id == 0){
		  	//Solicita tenedor derecho
            MPI_Ssend( &id, 1, MPI_INT,der,der, MPI_COMM_WORLD);
          	cout << "Filósofo " << id << " solicita tenedor derecho ... " << der << endl << flush;
                  
          	//Solicita tenedor izquierdo
            MPI_Ssend( &id, 1, MPI_INT,izq,izq, MPI_COMM_WORLD);
          	cout << "Filósofo " << id << " coge tenedor izquierdo ... " << izq << endl << flush;
		}
		else{
		  	//Solicita tenedor izquierdo
	      	MPI_Ssend( &id, 1, MPI_INT,izq,izq, MPI_COMM_WORLD);
          	cout << "Filósofo " << id << " solicita tenedor izq ... " << izq << endl << flush;
            
            //Solicita tenedor derecho      
           	MPI_Ssend( &id, 1, MPI_INT,der,der, MPI_COMM_WORLD);
            cout << "Filósofo " << id << " coge tenedor der ... " << der << endl << flush;
        }
		cout << "Filósofo: " << id << " COMIENDO " << endl << flush;
		sleep( (rand() % 5) + 1);	// Comiendo
	
		// Suelta el tenedor izquierdo
		cout << "Filósofo " << id << " suelta tenedor izq ... " << izq << endl << flush;
		MPI_Ssend( &id, 1, MPI_INT,izq,id, MPI_COMM_WORLD );
              
		// Suelta el tenedor derecho		
		cout << "Filósofo " << id << " suelta tenedor der ... " << der << endl << flush;
		MPI_Ssend( &id, 1, MPI_INT,der,id, MPI_COMM_WORLD );

		cout << "Filósofo " << id << " PENSANDO " << endl << flush;
		sleep( (rand() % 5) + 1);	// Pensando

	}
}

//_____________________________________________________________________________________________________________

/* Función Tenedor */
void Tenedor(int id, int nprocesos){	// Código proceso Tenedor
	int Filo;
	MPI_Status status;
	
	while(1){
		// Espera una petición desde cualquier filosofo vecino ...
		// Recibe la petición del filosofo ...
		MPI_Recv(&Filo, 1, MPI_INT, MPI_ANY_SOURCE, id, MPI_COMM_WORLD, &status);
		cout << "Tenedor " << id << " recibe petición del Filósofo" << Filo << endl << flush;

		// Espera a que el filosofo suelte el tenedor ...
		MPI_Recv(&Filo, 1, MPI_INT, MPI_ANY_SOURCE, Filo, MPI_COMM_WORLD, &status);
		cout << "Tenedor " << id << " recibe petición del Filósofo" << Filo << endl << flush;
	}
}

//______________________________________________________________________________________________________________

/* Programa principal */
int main(int argc, char **argv){
	int rank, size;

	srand(time(0));	// Inicializa la semilla aleatoria

	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD, &size);

	if(size != 10){
		if(rank == 0){
			cout << "El número de procesos debe ser 10" << endl << flush;
		}
		MPI_Finalize();
		return 0;
	}

	if( (rank % 2) == 0){
		Filosofo(rank,size);	// Los pares son Filosofos
	}
	else{
		Tenedor(rank,size);	// Los impares som Tenedores
	}

	MPI_Finalize();

	return 0;
}
