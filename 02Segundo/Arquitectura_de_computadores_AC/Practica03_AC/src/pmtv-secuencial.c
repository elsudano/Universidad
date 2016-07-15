/*
 * pmtv-secuencial.c
 *
 *  Created on: 04/05/2014
 *      Author: Carlos de la Torre
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define PRINT_ALL_MIN 15
// Ponemos que los elementos mínimos para que se
// impriman todos los valores de la matriz sea 15

int main(int argc, char* argv[]) {
	int f,c,N,TIME;
	double tr;
	struct timespec t1, t2;

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

	int *vector, *Vresultado;
	int **MatrizTri;
	MatrizTri = (int**) malloc(N * sizeof(int*));
	for (f = 0; f < N; f++)
		MatrizTri[f] = (int*) malloc(N * sizeof(int));
	vector = (int*) malloc(N * sizeof(int)); //si no hay espacio suficiente malloc devuelve NULL
	Vresultado = (int*) malloc(N * sizeof(int));
	if ((MatrizTri == NULL) || (vector == NULL) || (Vresultado == NULL)) {
		printf("Error en la reserva de espacio para los Vectores o MatrizTri\n");
		exit(-2);
	}

	srand(time(NULL)); // esta es la semilla que se usa para los random
	// Inicializamos la Matriz y el vector
	for(f = 0; f < N; f++){
		for(c = 0; c < N; c++){
			if(f > c) // <---- Cambiando el sentido del simbolo la matriz es superior o inferior
				//MatrizTri[f][c]=rand()%10;
				MatrizTri[f][c]=4;
			else
				MatrizTri[f][c]=0;
		}
		vector[f] = 2;
	}

// imprimimos la matriz y el vector si el tamaño de N < PRINT_ALL_MIN
	if (N <= PRINT_ALL_MIN && TIME!=1){
		printf ("\nEsta es la matriz: \n");
		for (f = 0; f < N; f++){
			for (c = 0; c < N; c++){
				printf ("%d ",MatrizTri[f][c]);
			}
			printf ("\n");
		}
		printf ("\nEste es el vector: \n");
		for (f = 0; f < N; f++)
			printf ("%d ",vector[f]);
		printf("\n\n");
	}

// Calcular la multiplicación de una matriz por un vector
	clock_gettime(CLOCK_REALTIME, &t1);

	for (f = 0; f < N; f++)
		for (c = 0; c < N; c++)
				Vresultado[f] += MatrizTri[f][c] * vector[c];

	clock_gettime(CLOCK_REALTIME, &t2);

	// calculamos el tiempo que hemos tardado en calcular la multiplicación
	tr = (double) (t2.tv_sec - t1.tv_sec) + (double) ((t2.tv_nsec - t1.tv_nsec) / (1.e+9));

	// Ahora imprimimos por pantalla los resultados obtenidos segun las restricciones del problema
	if (N <= PRINT_ALL_MIN){
		printf("Tiempo(seg.):%11.9f\nTamaño Matriz y Vector:%u\n",tr,N);// si queremos imprimir datos completos y N < PRINT_ALL_MIN
		printf ("Este es el vector resultante: \n");
		printf("{");
		for (f = 0; f < N; f++){
			if (f==N-1)
				printf ("VR[%d]=%d",f,Vresultado[f]);
			else
				printf ("VR[%d]=%d, ",f,Vresultado[f]);
		}
		printf("}\n");
	}else if (TIME==1) // si queremos imprimir unicamente el tiempo de cálculo
		  printf("%11.9f\n",tr);//
	else{ // y si queremos imprimir el tiempo la primera y la ultima multiplicacón
		printf("Tiempo(seg.):%11.9f\n",tr);
		printf("Tamaño Matriz y Vector:%u\n",N);
		printf("(Matriz[0][0]=%d)*(Vector[0]=%d)=%d\n",MatrizTri[0][0],vector[0],MatrizTri[0][0]*vector[0]);
		printf("(Matriz[%d][%d]=%d)*(Vector[%d]=%d)=%d\n",N-1,N-1,MatrizTri[N-1][N-1],N-1,vector[N-1],MatrizTri[N-1][N-1]*vector[N-1]);
	}
	free(vector);
	free(Vresultado);
	for(f=0; f<N; f++)
		free(MatrizTri[f]);
	free(MatrizTri);
	return 0;
}


