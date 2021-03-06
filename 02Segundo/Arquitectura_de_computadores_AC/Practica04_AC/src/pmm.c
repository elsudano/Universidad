/*
 * matrices.c
 *
 *  Created on: 25/05/2014
 *      Author: Carlos de la Torre
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define PRINT_ALL_MIN 15
// Ponemos que los elementos mínimos para que se
// impriman todos los valores de la matriz sea 15
#define DEBUGMODE 0
// con esta definición nos aseguramos que solo
// salgan las cifras de tiempo en cada ejecución
// así de esa manera es mas fácil realizar el
// estudio empírico del programa

int main(int argc, char* argv[]) {
	int f,c,k,N;
	double tr;
	struct timespec t1, t2;
	int boolImprime = 0;

	switch (argc){ // coneste switch nos aseguramos de que la entrada de parametros sea correcta
		case 1:
			printf("Faltan las filas/columnas de la Matrices\n");
			printf("\nUso: %s [numero]\n",argv[0]);
			printf("\nDonde numero es el tamaño de las filas y las columnas de la matrices\n");
			exit(-1);
			break;
		case 2:
			N = atoi(argv[1]); // Este sera el tamaño del vector y de las filas/columnas de la matriz
			break;
		case 3:
			N = atoi(argv[1]); // Este sera el tamaño del vector y de las filas/columnas de la matriz
			if (atoi(argv[2])==1)
				boolImprime = 1;
			break;
		default:
			printf("La cantidad de parametros es incorrecta\n");
			exit(-1);
			break;
	}

	int **M1, **M2, **MR;
	M1 = (int**) malloc(N * sizeof(int*));
	for (f = 0; f < N; f++)
		M1[f] = (int*) malloc(N * sizeof(int));
	M2 = (int**) malloc(N * sizeof(int*));
	for (f = 0; f < N; f++)
		M2[f] = (int*) malloc(N * sizeof(int));
	MR = (int**) malloc(N * sizeof(int*));
	for (f = 0; f < N; f++)
		MR[f] = (int*) malloc(N * sizeof(int));
	if ((M1 == NULL) || (M2 == NULL) || (MR == NULL)) {
		printf("Error en la reserva de espacio para los Vectores o MatrizTri\n");
		exit(-2);
	}

	srand(N);
	//srand(time(NULL)); // esta es la semilla que se usa para los random
	// Inicializamos la Matrices
	for(f = 0; f < N; f++)
		for(c = 0; c < N; c++){
			M1[f][c]=rand()%10;
			M2[f][c]=rand()%10;
		}

// imprimimos la matriz y el vector si el tamaño de N < PRINT_ALL_MIN
	if (N <= PRINT_ALL_MIN && DEBUGMODE!=1){
		printf ("\nEsta es la matriz 1: \n");
		for (f = 0; f < N; f++){
			for (c = 0; c < N; c++){
				printf ("%d ",M1[f][c]);
			}
			printf ("\n");
		}
		printf ("\nEsta es la matriz 2: \n");
		for (f = 0; f < N; f++){
			for (c = 0; c < N; c++){
				printf ("%d ",M2[f][c]);
			}
			printf ("\n");
		}
		printf ("\n");
	}

// Calcular la multiplicación de una matriz por un vector
	clock_gettime(CLOCK_REALTIME, &t1);
	for (f = 0; f < N; ++f) {
	  for (c = 0; c < N; ++c) {
		for (k = 0; k < N; ++k) {
		   MR[f][c] += M1[f][k] * M2[k][c];
		}
	  }
	}
	clock_gettime(CLOCK_REALTIME, &t2);

	// calculamos el tiempo que hemos tardado en calcular la multiplicación
	tr = (double) (t2.tv_sec - t1.tv_sec) + (double) ((t2.tv_nsec - t1.tv_nsec) / (1.e+9));

	// Ahora imprimimos por pantalla los resultados obtenidos segun las restricciones del problema
	if (N <= PRINT_ALL_MIN && DEBUGMODE == 0 && boolImprime == 0){
		printf("Tiempo(seg.):%11.9f\nTamaño Matriz y Vector:%u\n",tr,N);// si queremos imprimir datos completos y N < PRINT_ALL_MIN
		printf ("Este es la matriz resultante: \n");
		for (f = 0; f < N; f++){
			for (c = 0; c < N; c++){
				printf ("%d ",MR[f][c]);
			}
			printf ("\n");
		}
		printf("\n");
	}else if (DEBUGMODE == 1 || boolImprime == 1) // si queremos imprimir unicamente el tiempo de cálculo
		  printf("%11.9f\n",tr);//
	else{ // y si queremos imprimir el tiempo la primera y la ultima multiplicacón
		printf("Tiempo(seg.):%11.9f\n",tr);
		printf("Tamaño Matriz 1, Matriz 2 y Matriz resultante: %u\n",N);
		printf("(M1[0][0]=%d)*(M2[0][0]=%d)=%d\n",M1[0][0],M2[0][0],MR[0][0]);
		printf("(M1[%d][%d]=%d)*(M2[%d][%d]=%d)=%d\n",N-1,N-1,M1[N-1][N-1],N-1,N-1,M2[N-1][N-1],MR[N-1][N-1]);
	}

	for(f=0; f<N; f++){
		free(M1[f]);
		free(M2[f]);
		free(MR[f]);
	}
	free(M1);
	free(M2);
	free(MR);
	return 0;
}


