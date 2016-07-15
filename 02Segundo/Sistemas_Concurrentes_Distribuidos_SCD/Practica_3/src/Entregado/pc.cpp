/*
 * ProductorConsumidor.cpp
 *
 *  Created on: 09/01/2014
 *      Author: Carlos de la Torre
 */
#include <mpi.h>
#include <iostream>
#include <cmath>
#include <unistd.h>
using namespace std;

#define Productor0 0
#define Productor1 1
#define Productor2 2
#define Productor3 3
#define Productor4 4
#define Buffer 5
#define Consumidor0 6
#define Consumidor1 7
#define Consumidor2 8
#define Consumidor3 9

#define TAGProductor 0
#define TAGConsimidor 1
#define TAGBuffer 2

#define DATOS_PRODUCIDOS 200
const int TAM=5;

//Productor
void productor(int rank){
	for (unsigned int i=rank;i<DATOS_PRODUCIDOS;i+=5){
		MPI_Ssend(&i, 1, MPI_INT, Buffer, TAGProductor, MPI_COMM_WORLD);
	}
}

//Consumidor
void consumidor(int rank){
	int value,peticion=1;
	float raiz;
	MPI_Status status;
	for (unsigned int i=0;i<DATOS_PRODUCIDOS/4;i++){
		MPI_Ssend(&peticion,1, MPI_INT, Buffer, TAGConsimidor, MPI_COMM_WORLD);
		MPI_Recv(&value,1, MPI_INT, Buffer, TAGBuffer, MPI_COMM_WORLD,&status );
		cout<< "Consumidor " << rank-6 << " recibe valor "<<value<<" de Buffer "<<endl<<flush;
		raiz=sqrt(value);
	}
}

//Buffer
void buffer(){
	int value[TAM], peticion, posicion=0, tipo_etiqueta;
	MPI_Status status;
	for (unsigned int i=0;i<DATOS_PRODUCIDOS*2;i++){
		if(posicion == 0)
			tipo_etiqueta = TAGProductor;
		else if(posicion == TAM)
			tipo_etiqueta = TAGConsimidor;
		else
			tipo_etiqueta = MPI_ANY_TAG;

		MPI_Recv(&peticion,1,MPI_INT,MPI_ANY_SOURCE,tipo_etiqueta,MPI_COMM_WORLD,&status);

		if(status.MPI_TAG == TAGProductor){
			value[posicion] = peticion;
			posicion++;
		}

		if(status.MPI_TAG == TAGConsimidor){
			MPI_Ssend(&value[posicion-1],1,MPI_INT,status.MPI_SOURCE, TAGBuffer, MPI_COMM_WORLD);
			posicion--;
		}
	}
}

int main(int argc, char *argv[]) {
	int rank,size;
	MPI_Init( &argc, &argv );
	MPI_Comm_rank( MPI_COMM_WORLD, &rank );
	MPI_Comm_size( MPI_COMM_WORLD, &size );
	if(size!=10){
		cout << "Uso: mpirun -np 10 " << argv[0] << endl;
		MPI_Finalize();
	}else{
		if(rank>=Productor0 && rank<=Productor4)
			productor(rank);
		else if(rank>=Consumidor0 && rank<=Consumidor3)
			consumidor(rank);
		else
			buffer();
		MPI_Finalize( );
	}
	return 0;
}
