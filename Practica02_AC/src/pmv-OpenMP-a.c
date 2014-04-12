/*
 * pmv-OpenMp-a.c
 *
 *  Created on: 12/04/2014
 *      Author: Carlos de la Torre
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <omp.h> // biblioteca para programas paralelos
#define PRINT_ALL_MIN 15
// Ponemos que los elementos mínimos para que se
// impriman todos los valores de la matriz sea 15

int main(int argc, char* argv[]) {
	int i,j,k,N,TIME;
	double tr;
	// estas son las variables que sirven para medir el tiempo
	double t1, t2;
	switch (argc){ // coneste switch nos aseguramos de que la entrada de parametros sea correcta
		case 1:
			printf("Faltan las filas/columnas de la Matriz, y el tamaño del vector\n");
			printf("\nUso: %s [numero] [0/1]\n",argv[0]);
			printf("\nDonde numero es el tamaño de las filas y las columnas de la matriz y el tamaño del vector\n");
			printf("y el 0 o el 1 especifica si queremos solo los tiempos (1) o no\n");
			exit(-1);
			break;
		case 2:
			N = atoi(argv[1]); // Este sera el tamaño del vector y de las filas/columnas de la matriz
			TIME = 0;
			break;
		case 3:
			N = atoi(argv[1]); // Este sera el tamaño del vector y de las filas/columnas de la matriz
			TIME = atoi(argv[2]); // si tiene un valor de 0 se imprime toda la info si tiene un valor de 1 se imprime solo el tiempo
			break;
		default:
			printf("La cantidad de parametros es incorrecta\n");
			exit(-1);
			break;
	}

	int vector[N],Matriz[N][N];
	int Vresultado[N*N];

	srand(time(NULL)); // esta es la semilla que se usa para los random

#pragma omp parallel for private(i,j)// Inicializamos la Matriz y el vector
	for (i = 0; i < N; i++){
		for (j = 0; j < N; j++){
			Matriz[i][j] = 2;
		}
		vector[i] = 4;
	}

// imprimimos la matriz y el vector si el tamaño de N es menor de PRINT_ALL_MIN
	if (N <= PRINT_ALL_MIN && TIME!=1){
		printf ("\nEsta es la matriz: \n");
		for (i = 0; i < N; i++){
			for (j = 0; j < N; j++){
				printf ("%d ",Matriz[i][j]);
			}
			printf ("\n");
		}
		printf ("\nEste es el vector: \n");
		for (i = 0; i < N; i++)
			printf ("%d ",vector[i]);
		printf("\n\n");
	}

	t1 = omp_get_wtime(); // Calcular la multiplicación de una matriz por un vector

	double acumulador=0;
	for (k = 0; k < N; k++){
		acumulador += vector[k];
	}

#pragma omp parallel private (j)
	{
	#pragma omp for
		for (i = 0; i < N; i++){
			for (j = 0; j < N; j++){
				Vresultado[j+i*N] = Matriz[i][j]*acumulador;
			}
		}
	}
	t2 = omp_get_wtime();
	tr = t2 - t1; // Calculo el tiempo que he tardado en multiplicarlo

	// Ahora imprimimos por pantalla los resultados obtenidos segun las restricciones del problema
	if (N <= PRINT_ALL_MIN){
		printf("Tiempo(seg.):%11.9f\nTamaño Matriz y Vector:%u\n",tr,N);// si queremos imprimir datos completos y N < PRINT_ALL_MIN
		printf ("Este es el vector resultante: \n");
		printf("{");
		for (i = 0; i < N*N; i++){
			printf ("VR[%d]=%d, ",i,Vresultado[i]);
		}
		printf("}\n");
	}else if (TIME==1) // si queremos imprimir unicamente el tiempo de cálculo
		  printf("%11.9f\n",tr);//
	else{ // y si queremos imprimir el tiempo la primera y la ultima multiplicacón
		printf("Tiempo(seg.):%11.9f\n",tr);
		printf("Tamaño Matriz y Vector:%u\n",N);
		printf("Suma del vector completo: %d\n",vector[0]*N);
		printf("(Matriz[0][0]=%d)*%d=%d\n",Matriz[0][0],vector[0]*N,Matriz[0][0]*vector[0]*N);
		printf("(Matriz[%d][%d]=%d)*%d=%d\n",N-1,N-1,Matriz[N-1][N-1],vector[N-1]*N,Matriz[N-1][N-1]*vector[N-1]*N);
	}
	return 0;
}
