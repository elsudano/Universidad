/*
 * pmm-mejoras.c
 *
 *  Created on: 25/05/2014
 *      Author: Carlos de la Torre
 */
#ifdef MEJORA1
void mejoras(int **M1, int **M2, int **MR, int N){
	int f, c, k;
	int tmpA0=0, tmpA1=0, tmpA2=0, tmpA3=0;
	for (f = 0; f < N; ++f) {
		for (c = 0; c < N; ++c) {
			tmpA0=0; tmpA1=0; tmpA2=0; tmpA3=0;
			for (k = 0; k < N-3; k+=4) {
				tmpA0 += M1[f][k] * M2[k][c];
				tmpA1 += M1[f][k+1] * M2[k+1][c];
				tmpA2 += M1[f][k+2] * M2[k+2][c];
				tmpA3 += M1[f][k+3] * M2[k+3][c];
			}
			MR[f][c] = tmpA0+tmpA1+tmpA2+tmpA3;
		}
	}
}
#elif defined MEJORA2
void mejoras(int **M1, int **M2, int **MR, int N){
	int f, c, k;
	int tmpA0=0, tmpA1=0, tmpA2=0, tmpA3=0;
	int tmpB0=0, tmpB1=0, tmpB2=0, tmpB3=0;
	for (f = 0; f < N; ++f) {
		for (c = 0; c < N-1; c+=2) {
			tmpA0=0; tmpA1=0; tmpA2=0; tmpA3=0;
			for (k = 0; k < N-3; k+=4) {
				tmpA0 += M1[f][k] * M2[k][c];
				tmpA1 += M1[f][k+1] * M2[k+1][c];
				tmpA2 += M1[f][k+2] * M2[k+2][c];
				tmpA3 += M1[f][k+3] * M2[k+3][c];
			}
			MR[f][c] = tmpA0+tmpA1+tmpA2+tmpA3;
			tmpB0=0; tmpB1=0; tmpB2=0; tmpB3=0;
			for (k = 0; k < N-3; k+=4) {
				tmpB0 += M1[f][k] * M2[k][c+1];
				tmpB1 += M1[f][k+1] * M2[k+1][c+1];
				tmpB2 += M1[f][k+2] * M2[k+2][c+1];
				tmpB3 += M1[f][k+3] * M2[k+3][c+1];
			}
			MR[f][c+1] = tmpB0+tmpB1+tmpB2+tmpB3;
		}
	}
}
#else
void mejoras(int **M1, int **M2, int **MR, int N){
	int f, c, k;
	for (f = 0; f < N; ++f) {
		for (c = 0; c < N; ++c) {
			for (k = 0; k < N; ++k) {
				MR[f][c] += M1[f][k] * M2[k][c];
			}
		}
	}
}
#endif
