/*
 * daxpy.c
 *
 *  Created on: 25/05/2014
 *      Author: Carlos de la Torre
 */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

//#define RAND_MAX 32768
#define DEBUGMODE 0 // <<--- Esto es para que se imprima el valor de todos los elementos del vector

int main(int argc, char* argv[]) {
	int i,N, boolImprime = 0;
	double tr,a;
	struct timespec t1, t2;
	switch (argc){ // coneste switch nos aseguramos de que la entrada de parametros sea correcta
			case 1:
				printf("Intruduzca el valor de N y del Factor\n");
				printf("\nUso: %s [N] [Factor]\n",argv[0]);
				printf("\nDonde N es el numero de iteraciones del programa\n");
				printf("Es el numero por el que se multiplicara\n");
				exit(-1);
				break;
			case 3:
				N = atoi(argv[1]); // Estas son la cantidad de Iteraciones
				a = atoi(argv[2]); // Este es el valor del Factor
				break;
			case 4:
				N = atoi(argv[1]); // Estas son la cantidad de Iteraciones
				a = atoi(argv[2]); // Este es el valor del Factor
				if (atoi(argv[3])==1)
					boolImprime = 1;
				break;
			default:
				printf("La cantidad de parametros es incorrecta\n");
				exit(-1);
				break;
		}
	if (N > 0 && a != 0.0){
		double *x = (int*) malloc(sizeof(int) * N);
		double *y = (int*) malloc(sizeof(int) * N);
		if ((y == NULL) || (x == NULL)) {
			printf("Error en la reserva de espacio para los Vector o Matriz\n");
			exit(-2);
		}
		srand(time(NULL)); // esta es la semilla que se usa para los random
		// Inicializamos los arrays
		for (i=0;i<N-3;i+=4){
			x[i] = ((double)rand()/(double)RAND_MAX);
			y[i] = ((double)rand()/(double)RAND_MAX);
			x[i+1] = ((double)rand()/(double)RAND_MAX);
			y[i+1] = ((double)rand()/(double)RAND_MAX);
			x[i+2] = ((double)rand()/(double)RAND_MAX);
			y[i+2] = ((double)rand()/(double)RAND_MAX);
			x[i+3] = ((double)rand()/(double)RAND_MAX);
			y[i+3] = ((double)rand()/(double)RAND_MAX);
		}

		clock_gettime(CLOCK_REALTIME, &t1);
		for (i=1;i<=N;i++)
			y[i] = a*x[i] + y[i];
		clock_gettime(CLOCK_REALTIME, &t2);
		tr = (double) (t2.tv_sec - t1.tv_sec) + (double) ((t2.tv_nsec - t1.tv_nsec) / (1.e+9));
		if (DEBUGMODE==1)
			for (i=1;i<=N;i++)
				printf("Valor y[i]=%11.9f\n",y[i]);
		if (argc == 4 && boolImprime == 1)
			printf("%11.9f\n",tr);
		else{
			printf("Tiempo(seg.):%11.9f\n",tr);
		}
	}else
		printf("N tiene que ser mayor que 0 y alpha debe de ser diferente de 0.0\n");
	return 0;
}
