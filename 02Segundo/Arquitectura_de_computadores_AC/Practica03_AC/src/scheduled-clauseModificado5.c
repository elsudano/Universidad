/*
 * sheduled-clause.c
 *
 *  Created on: 28/04/2014
 *      Author: Carlos de la Torre
 */

#include <stdio.h>
#include <stdlib.h>
#ifdef _OPENMP
	#include <omp.h>
#else
	#define omp_get_thread_num() 0
#endif
int main(int argc, char **argv) {
	int i, n = 200, chunk, hebras = 1, a[n], suma = 0;

	if (argc < 4) {
		if (argv[1]==NULL){
			fprintf(stderr, "[ERROR]-Falta iteraciones\n");
			exit(-1);
		}else if (argv[2]==NULL){
			fprintf(stderr, "[ERROR]-Falta chunk\n");
			exit(-1);
		}else if (argv[3]==NULL){
			fprintf(stderr, "[ERROR]-Falta numero de hebras\n");
			exit(-1);
		}else{
			printf("[USAGE]-%s [num iteraciones] [num chunk] [num hebras]\n",argv[0]);
		}
	}
	n = atoi(argv[1]);
	if (n > 200)
		n = 200;
	chunk = atoi(argv[2]);
	hebras = atoi(argv[3]);
	printf("Valores de las variables de control antes de la modificacion valen:\n"
			" dyn-var: %s\n"
			" nthreads-var: %s\n"
			" run-sched-var: %s\n",getenv("OMP_DYNAMIC"),getenv("OMP_NUM_THREADS"),getenv("OMP_SCHEDULE"));

	/* Las siguientes cuatro líneas setean las diferentes variables de entorno de usuario
	 * normalmente esto se tendría que hacer desde la consola para que cada usuario
	 * modificase la forma de ejecución del programa a su antojo, pero en esta practica
	 * para no tener que estar continuamente modificando el entorno del usuario
	 * se ha implementado directamente desde el programa.
	 */
	setenv("OMP_DYNAMIC","TRUE",1); // Seteamos a true el ajuste dinámico del nº de threads
	setenv("OMP_NUM_THREADS","4",1); // Seteamos el nº de threads en la siguiente ejecución paralela
	setenv("OMP_NESTED","TRUE",1); // Seteamos a true el paralelismo anidado
	setenv("OMP_SCHEDULE","dynamic",1); // Elegimos como queremos la planificación de bucles

	printf("Valores de las variables de control después de la modificación y fuera del parallel valen:\n"
			" dyn-var: %s\n"
			" nthreads-var: %s\n"
			" run-sched-var: %s\n",getenv("OMP_DYNAMIC"),getenv("OMP_NUM_THREADS"),getenv("OMP_SCHEDULE"));
	for (i = 0; i < n; i++)
		a[i] = i;
#pragma omp parallel num_threads(hebras)
	{
	#pragma omp	for firstprivate(suma) lastprivate(suma) schedule(dynamic,chunk)
		for (i = 0; i < n; i++) {
			suma = suma + a[i];
			if (argv[4]==NULL)
				printf(" La iteración %d la realiza thread %d suma a[%d]=%d suma=%d \n", i, omp_get_thread_num(), i, a[i], suma);
			else if (atoi(argv[4])==1)
				printf("%d %d\n", i, omp_get_thread_num());
		}
	#pragma omp master
		if (omp_in_parallel())
			printf("Valores de las variables de control dentro del parallel:\n"
					" dyn-var: %s\n"
					" nthreads-var: %s\n"
					" thread-limit-var: %s\n"
					" nest-var: %s\n"
					" run-sched-var: %s\n",getenv("OMP_DYNAMIC"),getenv("OMP_NUM_THREADS"),getenv("OMP_THREAD_LIMIT"),getenv("OMP_NESTED"),getenv("OMP_SCHEDULE"));
	}
	printf("Fuera de 'parallel for' suma=%d\n", suma);
	return 0;
}

