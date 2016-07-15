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

void mejoras(int *datos, int N_ext, int N_int);

int main(int argc, char* argv[]) {
	/* Modificaciones para ganar tiempo en la ejecución
	 * ------------------------------------------------
	 * Lo primero que hemos hecho es quitar uno de los bucles internos
	 * después he hecho un desenrrollado de bucles
	 * después he alineado el vector donde se guardan los
	 */
	int j;
	double tr;
	struct timespec t1, t2;

#ifdef MEJORA_MEM
	const int LINEA_CACHE = 64;
	const int TAM_BITS = 8192;
	// dos temporales para poder alinear la memoría
	// *tempA, *tempB;
	// el 63 es el valor del tamaño de las lineas de cache del procesador
	int *MEM = (int*) malloc(sizeof(int) * FOREXT + LINEA_CACHE-1);
	// los 63 es tamaño linea de cache
	int *tempA = (int *)(((int)MEM+LINEA_CACHE-1)&~(LINEA_CACHE-1));
	// los 8192 son 32KB de tamaño cache nivel 1
	// por 2 memorias que tiene mi procesador
	// dividido entre 8 vias se quedan 8192
	int *tempB = (int *)((((int)MEM+LINEA_CACHE-1)&~(LINEA_CACHE-1))+TAM_BITS+LINEA_CACHE);
	if ((MEM == NULL) || (tempA == NULL) || (tempB == NULL)) {
		printf("Error en la reserva de espacio para los Vector o Matriz\n");
		exit(-2);
	}
#else
	/* aquí se hace la reserva de memoria normal con
	 * malloc y se devuelve como puntero
	 */
	int *MEM = (int*) malloc(sizeof(int) * FOREXT);
	if (MEM == NULL) {
		printf("Error en la reserva de espacio para los Vector o Matriz\n");
		exit(-2);
	}
#endif



// 	pongo a 0 todo el vector esto solo se usa con malloc
//	la funcion calloc es lo mismo que malloc pero llena
//	la memoria reservada con 0s
	for (j=0;j<FOREXT;++j)
		MEM[j] = 0;

	clock_gettime(CLOCK_REALTIME, &t1);
	mejoras(MEM,FOREXT,FORINT);  // esta función se encuentra en los diferentes ficheros de mejoras
	clock_gettime(CLOCK_REALTIME, &t2);
	// calculamos el tiempo que hemos tardado en calcular la multiplicación
	tr = (double) (t2.tv_sec - t1.tv_sec) + (double) ((t2.tv_nsec - t1.tv_nsec) / (1.e+9));
	if (argc == 2 && atoi(argv[1])==1)
		printf("%11.9f\n",tr);
	else
		printf("Tiempo(seg.):%11.9f\n",tr);

	free(MEM);
	return 0;
}
