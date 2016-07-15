/*
 * nucleo_mejora_1_C.c
 *
 *  Created on: 25/05/2014
 *      Author: Carlos de la Torre
 */
struct {
        int a;
        int b;
}  s[5000];

#ifdef MEJORA1
void mejoras(int *datos, int N_ext, int N_int) {
	/* Modificaciones para ganar tiempo en la ejecución
	 * ------------------------------------------------
	 * En esta primera mejora quitamos el segundo for interno
	 * que se encarga de realizar las operaciones
	 */
	int ii,i,X1=0,X2=0;

	// pongo a 0 todo el vector
	for (i=0;i<N_ext;++i)
		datos[i] = 0;

	for (ii = 1; ii <= N_ext; ++ii) {
		for (i = 0; i < N_int; ++i){
			X1 = 2 * s[i].a + ii;
			X2 = 3 * s[i].b - ii;
		}

		if (X1<X2)
			datos[ii] = X1;
		else
			datos[ii] = X2;
	}
}
#elif defined MEJORA2
void mejoras(int *datos, int N_ext, int N_int){
	/* Modificaciones para ganar tiempo en la ejecución
	 * ------------------------------------------------
	 * En esta segunda optimización lo que hemos hecho ha
	 * sido desenrrollar el único bucle interno que quedaba
	 * hemos dividido la cantidad de iteraciones en 4
	 * y hemos puesto mas lineas de asignación
	 */
	int ii,i,X1=0,X2=0;

	int n = N_int/4;
	for (ii = 1; ii <= N_ext; ++ii) {
		for (i = 0; i < n-3; i+=4){
			X1 = 2 * s[i].a + ii;
			X2 = 3 * s[i].b - ii;
			X1 += 2 * s[i+1].a + ii;
			X2 += 3 * s[i+1].b - ii;
			X1 += 2 * s[i+2].a + ii;
			X2 += 3 * s[i+2].b - ii;
			X1 += 2 * s[i+3].a + ii;
			X2 += 3 * s[i+3].b - ii;
		}

		if (X1<X2)
			datos[ii] = X1;
		else
			datos[ii] = X2;
	}
}
#elif defined MEJORA3
void mejoras(int *datos, int N_ext, int N_int){
	/* Modificaciones para ganar tiempo en la ejecución
	 * ------------------------------------------------
	 * En esta tercera optimizacion lo que he hecho
	 * es quitar las multiplicaciones por 2 y
	 * sustituirlas por desplazamientos de bits
	 */
	int ii,i,X1=0,X2=0;

	int n = N_int/4;
	for (ii = 1; ii <= N_ext; ++ii) {
		for (i = 0; i < n-3; i+=4){
			X1 = s[i].a + ii;
			X1 = X1 << 1;
			X2 = 3 * s[i].b - ii;
			X1 += s[i+1].a + ii;
			X1 = X1 << 1;
			X2 += 3 * s[i+1].b - ii;
			X1 += s[i+2].a + ii;
			X1 = X1 << 1;
			X2 += 3 * s[i+2].b - ii;
			X1 += s[i+3].a + ii;
			X1 = X1 << 1;
			X2 += 3 * s[i+3].b - ii;
		}

		if (X1<X2)
			datos[ii] = X1;
		else
			datos[ii] = X2;
	}
}
#else
void mejoras(int *datos, int N_ext, int N_int) {
	/* Modificaciones para ganar tiempo en la ejecución
	 * ------------------------------------------------
	 * En esta opcion no hemos mejorado el codigo
	 */
	int ii,i,X1=0,X2=0;

	for (ii = 1; ii <= N_ext; ii++) {
		for (i = 0; i < N_int; i++)
			X1 = 2 * s[i].a + ii;
		for (i = 0; i < N_int; i++)
			X2 = 3 * s[i].b - ii;

		if (X1<X2)
			datos[ii] = X1;
		else
			datos[ii] = X2;
	}
}
#endif
