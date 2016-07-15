/*
 * figura1-mejora-memoria.c
 *
 *  Created on: 01/06/2014
 *      Author: Carlos de la Torre
 */

#ifdef MEJORA_MEM
void reservaMemoria(int *datos, int *tempA, int *tempB, int N){
	// dos temporales para poder alinear la memoría
	// *tempA, *tempB;
	// el 63 es el valor del tamaño de las lineas de cache del procesador
	datos = (int*) malloc(sizeof(int) * N + 63);
	// los 63 es tamaño linea de cache
	tempA = (int *)(((int)datos+63)&~(63));
	// los 8192 son 32KB de tamaño cache nivel 1
	// por 2 memorias que tiene mi procesador
	// dividido entre 8 vias se quedan 8192
	tempB = (int *)((((int)datos+63)&~(63))+8192+64);
}
#else
void reservaMemoria(int *datos, int *tempA, int *tempB, int N){
	/* aquí se hace la reserva de memoria normal con
	 * malloc y se devuelve como puntero
	 */
	int *reserva = (int*) malloc(sizeof(int) * N);
	*datos = *reserva;
}
#endif


