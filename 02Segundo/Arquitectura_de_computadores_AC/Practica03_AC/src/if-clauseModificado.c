/*
 * if-clauseModificado.c
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
	int i, hebras = 1, n = 20, tid;
	int a[n], suma = 0, sumalocal;
	if (argc < 3) {
		if (argv[1]==NULL){
			printf("[USAGE]-%s [num iteracciones] [num hebras]\n",argv[0]);
			fprintf(stderr, "[ERROR]-Falta iteraciones\n");
			exit(-1);
		}else{
			printf("[USAGE]-%s [num iteracciones] [num hebras]\n",argv[0]);
			fprintf(stderr, "[ERROR]-Falta numero de hebras\n");
			exit(-1);
		}
	}
	n = atoi(argv[1]);
	hebras = atoi(argv[2]);
	if (n > 20)
		n = 20;
	for (i = 0; i < n; i++) {
		a[i] = i;
	}
#pragma omp parallel num_threads(hebras) private(sumalocal,tid) shared(a,suma,n)
	{
		printf ("Esto es lo que tiene omp_num_threads(): %d\n",omp_get_num_threads());
		sumalocal = 0;
		tid = omp_get_thread_num();
	#pragma omp for private(i) schedule(static) nowait
		for (i = 0; i < n; i++) {
			sumalocal += a[i];
			printf(" thread %d suma de a[%d]=%d sumalocal=%d \n", tid, i, a[i], sumalocal);
		}
	#pragma omp atomic
		suma += sumalocal;
	#pragma omp barrier
	#pragma omp master
		printf(" thread master=%d imprime suma=%d\n", tid, suma);
	}
	return 0;
}
