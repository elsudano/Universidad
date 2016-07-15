/*
 * pmtv-OpenMP.c
 *
 *  Created on: 04/05/2014
 *      Author: Carlos de la Torre
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#ifdef _OPENMP
	#include <omp.h> // biblioteca para programas paralelos
#else
	#define omp_get_thread_num() 0
#endif
#define PRINT_ALL_MIN 15
// Ponemos que los elementos mínimos para que se
// impriman todos los valores de la matriz sea 15
#define NELEMENTOS(x)  (sizeof(x) / sizeof(x[0]))
// Con esto lo que hacemos es saber cual es el numero
// de elementos de cualquier vector de C solo tenemos
// que poner donde pone x el nombre del vector
#define DEBUGMODE 1
// con esta definición nos aseguramos que solo
// salgan las cifras de tiempo en cada ejecución
// así de esa manera es mas fácil realizar el
// estudio empírico del programa

void error(char* param[]){
	printf("\n [USAGE]-%s [num iteraciones] [planificación] [num chunk] [num hebras]\n"
			" para mas información %s --help\n\n", param[0],param[0]);
	if (param[1]==NULL){
		fprintf(stderr, " [ERROR]-Falta iteraciones\n");
		exit(-1);
	}else if (param[2]==NULL){
		fprintf(stderr, " [ERROR]-Falta modo de planificación: static, dynamic, guided\n");
		exit(-1);
	}else if (param[3]==NULL){
		fprintf(stderr, " [ERROR]-Falta chunk\n");
		exit(-1);
	}
//		}else if (argv[4]==NULL){
//			fprintf(stderr, " [ERROR]-Falta numero de hebras\n");
//			exit(-1);
//		}
	exit(-1);
}

int main(int argc, char* argv[]) {
	int f,c,N;
	char planificacion[10], chunk[2]="", hebras[2], tmp[6], help[6]="--help";
	char statics[9]="static", dynamic[10]="dynamic", guided[9]="guided";
	double tr, t1, t2;

	if (argc!=2 && argc!=3 && argc!=4 && argc!=5){
		error(argv);
	}else if (argc==2){
		if (getenv("OMP_SCHEDULE")!=NULL){
			N = atoi(argv[1]); // Este sera el tamaño del vector y de las filas/columnas de la matriz
			snprintf(hebras, sizeof(int), "%d", omp_get_num_procs());
			if (!strncmp(dynamic,getenv("OMP_SCHEDULE"),7))
				setenv("OMP_DYNAMIC","TRUE",1);
			strcpy(planificacion,getenv("OMP_SCHEDULE"));
		}else{
			strcpy(tmp,argv[1]);
			if (!strncmp(tmp,help,6)){
				printf("\n [USAGE]-%s [num iteraciones] [planificación] [num chunk] [num hebras]\n\n"
						" Tambien se puede utilizar la variable de entorno\n"
						" OMP_SCHEDULE para modificar la planificación\n\n"
						" Ejemplos:\n"
						" export OMP_SCHEDULE=\"static,4\"\n"
						" %s 10  la cantidad de hebras se asignará según el número de cores\n\n O\n"
						" %s 10 dynamic 4 4\n\n",argv[0],argv[0],argv[0]);
				exit(-1);
			}else
				error(argv);
		}
	}else if (argc==3){
		N = atoi(argv[1]); // Este sera el tamaño del vector y de las filas/columnas de la matriz
		strcpy(planificacion,argv[2]); // Esto es para poder capturar el texto desde consola
		sprintf(hebras,"%d",12);
	}else if (argc==4){
		N = atoi(argv[1]); // Este sera el tamaño del vector y de las filas/columnas de la matriz
		strcpy(planificacion,argv[2]); // Esto es para poder capturar el texto desde consola
		strcpy(chunk,argv[3]); // Cual es el chunk del programa
		sprintf(hebras,"%d",omp_get_num_procs());
	}else if (argc==5){
		N = atoi(argv[1]); // Este sera el tamaño del vector y de las filas/columnas de la matriz
		strcpy(planificacion,argv[2]); // Esto es para poder capturar el texto desde consola
		strcpy(chunk,argv[3]); // Cual es el chunk del programa
		strcpy(hebras,argv[4]);
	}

	/* He elegido esta manera de asignar los valores al programa por que en OMP
	 * en la escala de prioridad esta es la segunda opción osea que la unica
	 * manera de poder cambiar la planificación del programa sería editando
	 * el codigo y utilizando un if.
	 */
	if (!strcmp(statics,planificacion)){ // ponemos ! por que si las cadenas son iguales el valor es 0
		if (strcmp(chunk,"")){
			strcat(statics,",");
			strcat(statics,chunk);
		}
		setenv("OMP_SCHEDULE",statics,1); // Elegimos como queremos la planificación de bucles
	}else if (!strcmp(dynamic,planificacion)){
		if (strcmp(chunk,"")){
			strcat(dynamic,",");
			strcat(dynamic,chunk);
		}
		setenv("OMP_SCHEDULE",dynamic,1); // Elegimos como queremos la planificación de bucles
		setenv("OMP_DYNAMIC","TRUE",1); // Seteamos a true el ajuste dinámico del nº de threads
	}else if (!strcmp(guided,planificacion)){
		if (strcmp(chunk,"")){
			strcat(guided,",");
			strcat(guided,chunk);
		}
		setenv("OMP_SCHEDULE",guided,1); // Elegimos como queremos la planificación de bucles
	}
	setenv("OMP_NUM_THREADS",hebras,1); // Seteamos el nº de threads en la siguiente ejecución paralela


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
#pragma omp parallel for schedule(runtime) private(f,c) shared(MatrizTri,vector)// Inicializamos la Matriz y el vector
	for(f = 0; f < N; f++){
		for(c = 0; c < N; c++){
			if(f > c) // <---- Cambiando el sentido del simbolo la matriz es superior o inferior
				MatrizTri[f][c]=rand()%10;
			else
				MatrizTri[f][c]=0;
		}
		vector[f] = rand()%10;
	}

// imprimimos la matriz y el vector si el tamaño de N < PRINT_ALL_MIN
	if (N <= PRINT_ALL_MIN && DEBUGMODE!=1){
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

	t1 = omp_get_wtime(); // Calcular la multiplicación de una matriz por un vector
#pragma omp parallel shared(MatrizTri,vector,Vresultado)
	{
#pragma omp for schedule(runtime) private(f,c)
		for (f = 0; f < N; f++){
			for (c = 0; c < N; c++)
				Vresultado[f] += MatrizTri[f][c]*vector[c];
		}
#pragma omp master
		if (omp_in_parallel())
			printf("Valores de las variables de control dentro del parallel:\n"
					" dyn-var: %s\n"
					" nthreads-var: %s\n"
					" thread-limit-var: %s\n"
					" nest-var: %s\n"
					" run-sched-var: %s\n",getenv("OMP_DYNAMIC"),getenv("OMP_NUM_THREADS"), getenv("OMP_THREAD_LIMIT"),getenv("OMP_NESTED"),getenv("OMP_SCHEDULE"));
	}
	t2 = omp_get_wtime();
	tr = t2 - t1; // Calculo el tiempo que he tardado en multiplicarlo

	// Ahora imprimimos por pantalla los resultados obtenidos segun las restricciones del problema
	if (N <= PRINT_ALL_MIN){
		// si queremos imprimir datos completos y N < PRINT_ALL_MIN
		printf("Tiempo(seg.):%11.9f\nTamaño Matriz y Vector:%u\n",tr,N);
		printf ("Este es el vector resultante: \n");
		printf("{");
		for (f = 0; f < N; f++){
			if (f==N-1)
				printf ("VR[%d]=%d",f,Vresultado[f]);
			else
				printf ("VR[%d]=%d, ",f,Vresultado[f]);
		}
		printf("}\n");
	}else if (DEBUGMODE==1) // si queremos imprimir unicamente el tiempo de cálculo
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
