/*
 * reduction-clause.c
 *
 *  Created on: 09/04/2014
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
	int i, n = 20, a[n], suma = 10;
	if (argc < 2) {
		fprintf(stderr,"Falta iteraciones\n");
		exit(-1);
	}
	n = atoi(argv[1]);
	if (n > 20) {
		n = 20;
		printf("n=%d\n", n);
	}
	for (i = 0; i < n; i++)
		a[i] = i;
#pragma omp parallel sections reduction(+:suma) private(i)
	{
	#pragma omp section
		for (i = 0; i < n/4; i++)
			suma += a[i];
	#pragma omp section
		for (i = n/4; i < n/2; i++)
			suma += a[i];
	#pragma omp section
		for (i = n/2; i < n/2+n/4; i++)
			suma += a[i];
	#pragma omp section
		for (i = n/2+n/4; i < n; i++)
			suma += a[i];
	}
	printf("Tras 'parallel' suma=%d\n", suma);
return 0;
}
