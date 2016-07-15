/*
 * Filosofos con Camarero
 *
 *  Created on: 09/01/2014
 *      Author: Carlos de la Torre
 */
#include <mpi.h>
#include <time.h>
#include <stdlib.h>
#include <unistd.h> 	
#include <iostream>
using namespace std;

#define Camarero 10
#define TAGSentarse 0
#define TAGLevantarse 1
#define TAGCogeTenedor 2
#define TAGSueltaTenedor 3

/* Función Filosofo */
void Filosofo(int rank, int size){
	int peticion;
	int izq = rank + 1;
	int der;
	// Con este condicional lo que hacemos es seleccionar
	// los tenedores para el primer Filosofo
	if(rank == 0)
		der = 9;
	else
		der = rank - 1;

	while(true){
		cout << "Filosofo: " << rank << " PENSANDO " << endl << flush;
		sleep((rand()%3)+rank);
			
		//cout << "Filosofo " << rank << " SENTANDO " << endl << flush;
		MPI_Ssend( &peticion, 1, MPI_INT,Camarero,TAGSentarse, MPI_COMM_WORLD );	
	
	 	MPI_Ssend( &rank, 1, MPI_INT,izq,TAGCogeTenedor, MPI_COMM_WORLD);
	 	//cout << "Filosofo" << rank << " solicita tenedor izquierdo "  << izq << endl << flush;
            
	 	MPI_Ssend( &rank, 1, MPI_INT,der,TAGCogeTenedor, MPI_COMM_WORLD);
	 	//cout << "Filosofo" << rank << " solicita tenedor derecho " << der << endl << flush;

		cout << "Filosofo: " << rank << " COMIENDO " << endl << flush;
		sleep((rand()%3)+rank);

		MPI_Ssend( &rank, 1, MPI_INT,izq,TAGSueltaTenedor, MPI_COMM_WORLD );
		//cout << "Filosofo " << rank << " suelta tenedor izq  " << izq << endl << flush;

		MPI_Ssend( &rank, 1, MPI_INT,der,TAGSueltaTenedor, MPI_COMM_WORLD );
		//cout << "Filosofo " << rank << " suelta tenedor der  " << der << endl << flush;
		
		MPI_Ssend( &peticion, 1, MPI_INT,Camarero,TAGLevantarse, MPI_COMM_WORLD );
		//cout << "Filosofo " << rank << " LEVANTANDO " << endl << flush;
	}
}


/* Función Tenedor */
void Tenedor(int rank, int size){
	int Filo;
	MPI_Status status;
	while(true){
		MPI_Recv(&Filo, 1, MPI_INT, MPI_ANY_SOURCE, TAGCogeTenedor, MPI_COMM_WORLD, &status);
		//cout << "El Filosofo" << Filo << " suelta el tenedor izquierdo" << rank << endl << flush;
		MPI_Recv(&Filo, 1, MPI_INT, MPI_ANY_SOURCE, TAGSueltaTenedor, MPI_COMM_WORLD, &status);
		//cout << "El Filosofo" << Filo << " suelta el tenedor derecho" << rank << endl << flush;
	}
}


/* Función Camarero */
void camarero(){	
	int peticion,tipo_etiqueta;
	int sentados=0;
	MPI_Status status;
	while(true){
		cout << "Clientes sentados: " << sentados << endl << flush;
		if(sentados <= 4)
			tipo_etiqueta = TAGSentarse;
		else
			tipo_etiqueta = TAGLevantarse;

		MPI_Recv(&peticion,1,MPI_INT,MPI_ANY_SOURCE,tipo_etiqueta,MPI_COMM_WORLD,&status);

		if(tipo_etiqueta == TAGSentarse)
			sentados++;
		else
			sentados--;
	}
}

/* Programa principal */
int main(int argc, char **argv){
	int rank, size;
	srand(time(0));
	MPI_Init(&argc, &argv);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	MPI_Comm_size(MPI_COMM_WORLD, &size);
	if(size != 11){
		cout << "Uso: mpirun -np 11 " << argv[0] << endl;
		MPI_Finalize();
		return 0;
	}
	if( ((rank % 2) == 0) && (rank != Camarero)){
		Filosofo(rank,size);	// Los pares son Filosofos
	}else if(rank == Camarero)
		camarero(); 			// El camarero es el 10
	else
		Tenedor(rank,size);		// Los impares som Tenedores
	
	MPI_Finalize();
	return 0;
}
