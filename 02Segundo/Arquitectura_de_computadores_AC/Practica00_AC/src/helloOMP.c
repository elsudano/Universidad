/*
 ============================================================================
 Name        : helloOMP.c
 Author      : Carlos de la Torre
 Version     : alfa
 Copyright   :
 Description : Hello OpenMP World in C
 ============================================================================
 */
#include <stdio.h>
#include <omp.h>

int main(int argc, char *argv[]) {
#pragma omp parallel
	printf("(%d:!!!Hello world!!!)\n", omp_get_thread_num());
	return (0);
}
