/*
 * ProductorConsumidor.cpp
 *
 *  Created on: 09/01/2014
 *      Author: Carlos de la Torre
 */
#include <stdlib.h>
#include <iostream>
#include <math.h>
#include <mpi.h>

using namespace std;

#define Productor 0
#define Buffer 1
#define Consumidor 2
#define ITERS 20
#define TAM 5

void productor() {
	for (unsigned int i = 0; i < ITERS; i++) {
		cout << "Productor produce valor " << i << endl << flush;
		MPI_Ssend(&i, 1, MPI_INT, Buffer, 0, MPI_COMM_WORLD);
	}
}

void consumidor() {
	double value; int peticion = 1;
	float raiz;
	MPI_Status status;
	for (unsigned int i = 0; i < ITERS; i++) {
		MPI_Ssend(&peticion, 1, MPI_INT, Buffer, 0, MPI_COMM_WORLD);
		MPI_Recv(&value, 1, MPI_INT, Buffer, 0, MPI_COMM_WORLD, &status);
		cout << "Consumidor recibe valor " << value << " de Buffer " << endl << flush;
		raiz = sqrt(value);
	}
	cout << raiz << endl << flush;
}

void buffer() {
	int value[TAM], peticion, pos = 0, rama;
	MPI_Status status;
	for (unsigned int i = 0; i < ITERS * 2; i++) {
		if (pos == 0)
			rama = 0;			//El consumidor no puede consumir

		else if (pos == TAM)
			rama = 1; 			// El productor no puede producir
		else { 					//Ambas guardas son ciertas
			MPI_Probe(MPI_ANY_SOURCE, MPI_ANY_TAG, MPI_COMM_WORLD, &status);
			if (status.MPI_SOURCE == Productor)
				rama = 0;
			else
				rama = 1;
		}

		switch (rama) {
		case 0:
			MPI_Recv(&value[pos], 1, MPI_INT, Productor, 0, MPI_COMM_WORLD,&status);
			cout << "Buffer recibe " << value[pos] << " de Prod. " << endl << flush;
			pos++;
			break;
		case 1:
			MPI_Recv(&peticion, 1, MPI_INT, Consumidor, 0, MPI_COMM_WORLD,&status);
			MPI_Ssend(&value[pos-1],1,MPI_INT,Consumidor, 0, MPI_COMM_WORLD);
			cout << "Buffer envia " << value[pos - 1] << " a Cons." << endl << flush;
			pos--;
			break;
		}
	}
}


int main(int argc, char *argv[]) {
	int rank, size;
	MPI_Init(&argc, &argv);
	MPI_Comm_size(MPI_COMM_WORLD, &size);
	MPI_Comm_rank(MPI_COMM_WORLD, &rank);
	if (rank == Productor)
		productor();
	else if (rank == Buffer)
		buffer();
	else
		consumidor();
	MPI_Finalize();
	return 0;
}


