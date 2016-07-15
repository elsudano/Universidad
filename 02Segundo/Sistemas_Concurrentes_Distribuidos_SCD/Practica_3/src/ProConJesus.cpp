/*
	5 Producer / 4 Consumer using MPI
	JESUS ANGEL GONZALEZ NOVEZ
*/
#include <mpi.h>
#include <iostream>
#include <cmath>
#include <unistd.h>
#include <time.h>
#include <cstdlib>
using namespace std;
#define Productor0 0
#define Productor1 1
#define Productor2 2
#define Productor3 3
#define Productor4 4
#define Consumidor0 5
#define Consumidor1 6
#define Consumidor2 7
#define Consumidor3 8
#define Buffer 9
#define ITERS 20
const int TAM=10;

//Producer
void productor(int rank){
	for (unsigned int i=rank;i<ITERS;i+=5){
	//	cout<< "Productor " << rank << " produce valor "<<i<<endl<<flush;
		MPI_Ssend(&i,1,MPI_INT,Buffer, 0, MPI_COMM_WORLD);
	}
	//cout << "Acabado Productor() " << rank << endl;
}

//Consumer
void consumidor(int rank){
	int value,peticion=1;
	float raiz;
	MPI_Status status;
	for (unsigned int i=0;i<ITERS/4;i++){
		MPI_Ssend(&peticion,1, MPI_INT, Buffer, 1, MPI_COMM_WORLD);
		MPI_Recv(&value,1, MPI_INT, Buffer, 2, MPI_COMM_WORLD,&status );
		cout<< "Consumidor " << rank-5 << " recibe valor "<<value<<" de Buffer "<<endl<<flush;
		raiz=sqrt(value);
	}
	//cout << "Acabado Consumidor() " << rank << endl;
}
void buffer(){
	int value[TAM], peticion, pos=0,etiqueta,producidos=0;
	MPI_Status status;
	for (unsigned int i=0;i<ITERS*2;i++){
		if(pos == 0)
			etiqueta = 0;
		else
			if(pos == TAM)
				etiqueta = 1;
			else
				if(producidos<20)
					etiqueta = rand()%2;
				else
					etiqueta = 1;
		MPI_Recv(&peticion,1,MPI_INT,MPI_ANY_SOURCE,etiqueta,MPI_COMM_WORLD,&status);
		if(etiqueta == 0){
			value[pos] = peticion;
			pos++;
			producidos++;
		}else{
			MPI_Ssend(&value[pos-1],1,MPI_INT,status.MPI_SOURCE,2,MPI_COMM_WORLD);
			pos--;
		}
	}
	cout << "Acabado Buffer" << endl;
}

int main(int argc, char *argv[]) {
	int rank,size;
	srand(time(0));
	MPI_Init( &argc, &argv );
	MPI_Comm_rank( MPI_COMM_WORLD, &rank );
	MPI_Comm_size( MPI_COMM_WORLD, &size );
	if(size!=10){
		cout << "Uso: mpirun -np 10 nombre_binario" << endl;
		MPI_Finalize();
	}else{
		if(rank>=Productor0 && rank<=Productor4)
			productor(rank);
		else
			if(rank>=Consumidor0 && rank<=Consumidor3)
				consumidor(rank);
			else
				buffer();
		MPI_Finalize( );
	}
	return 0;
}
