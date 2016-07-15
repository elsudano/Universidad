/*
 * nucleo.c
 *
 *  Created on: 25/05/2014
 *      Author: Carlos de la Torre
 */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define FOREXT 40000
#define FORINT 5000

struct {
        int a;
        int b;
}  s[5000];

int main(int argc, char* argv[]) {
	int ii,i,X1,X2;
	double tr;
	struct timespec t1, t2;

	int *R = (int*) malloc(FOREXT * sizeof(int));
	if (R == NULL) {
		printf("Error en la reserva de espacio para los Vector o Matriz\n");
		exit(-2);
	}

	clock_gettime(CLOCK_REALTIME, &t1);
	for (ii = 1; ii <= FOREXT; ii++) {
		for (i = 0; i < FORINT; i++)
			X1 = 2 * s[i].a + ii;
		for (i = 0; i < FORINT; i++)
			X2 = 3 * s[i].b - ii;

		if (X1<X2)
			R[ii] = X1;
		else
			R[ii] = X2;
	}
	clock_gettime(CLOCK_REALTIME, &t2);
	// calculamos el tiempo que hemos tardado en calcular la multiplicación
	tr = (double) (t2.tv_sec - t1.tv_sec) + (double) ((t2.tv_nsec - t1.tv_nsec) / (1.e+9));

	if (argc == 2 && atoi(argv[1])==1)
		printf("%11.9f\n",tr);
	else
		printf("Tiempo(seg.):%11.9f\n",tr);
	return 0;
}
