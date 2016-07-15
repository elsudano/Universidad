/*
 * monedas.c
 *
 * 		Fecha: 03/06/14
 * 		Desarrollado: Carlos de la Torre
 *
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define DEBUGMODE 1// <-- Valores posibles SI=1 / NO=0

/*
 * Con esto podemos saber el tamaño de una array en C
 */
#define NELEMENTOS(x) (sizeof(x)/sizeof(x[0]))

/*
 * Variable global para saber el nombre del programa
 */
char* nomPrograma;

/*
 * Este es un truco para poder pasar matrices entre
 * funciones en C sin tener que usar punteros
 */
typedef unsigned int matriz[15][9999]; // <-- Definir los tamaños maximos para el monedero y la vuelta

/*
 * Con esto sacamos por pantalla el valor
 * de todas las monedas que tiene el monedero
 */
void imprimeMonedero(int MONEDERO[], int CANTI_MONEDAS){
	int i = 0;
	printf ("Monedero: [");
	for (;i<CANTI_MONEDAS;++i){
		printf ("%d",MONEDERO[i]);
		if (i < CANTI_MONEDAS-1)
			printf (", ");
		else
			printf("]");
	}
	printf("\n");
}

/*
 * Con esto sacamos por pantalla el valor
 * de la tabla que contiene la cantidad de
 * monedas a devolver según cuanto tengamos
 * que devolver
 * Pasamos el monedero por parametros para
 * poder hacer los incrementos de las columnas
 * por el valor de la moneda mas pequeña
 */
void imprimeTabla(int MONEDERO[], matriz TABLA, int FIL, int COL){
	int fil = 0, col = 0;
	printf("\nA Devolver --> ");
	for (col = 0;col<=COL;col+=MONEDERO[0]){ // <-- el incremento es igual a la moneda mas pequeña
		if (MONEDERO[0] > (COL-col)) // <-- El valor de la moneda es mas grande que lo que me queda por recorrer
			printf("%2d ",COL);
		else
			printf("%2d ",col);
	}
	printf("\n________________");
	for (col = 0;col<COL;col+=MONEDERO[0]){
		printf("___");
	}
	printf("_\n");
	for (fil = 0;fil<FIL;fil++){
		printf("Mon. tipo %d -> ",fil+1);
		for (col = 0;col<=COL;col+=MONEDERO[0]){
			if (MONEDERO[0] > (COL-col)) // <-- El valor de la moneda es mas grande que lo que me queda por recorrer
				printf("%2d ",TABLA[fil][COL]);
			else
				printf("%2d ",TABLA[fil][col]);
		}
		printf("\n");
	}
}

/*
 * Esta función controla los posibles errores del programa
 */
void funError(int ERROR){
	switch (ERROR){
	case 1:
		printf(" La cantidad de parametros es incorrecta \n");
		break;
	case 2:
		printf(" La cantidad valores de las monedas es diferente al numero de monedas \n");
		break;
	case 3:
		printf(" No hay una solución optima posible \n");
		break;
	}

	printf(" Uso: %s [Cantidad Monedas] [Valor Monedas separadas por ,] [Euros a devolver]\n\n",nomPrograma);
	printf(" Ejemplo: %s 3 1,2,5 13\n",nomPrograma);
	exit(-ERROR);
}

/*
 * Funcion de minimo en C
 * la he creado yo por que no he encontrado
 * ningúna librería de C que la tuvíera
 */
int min(int VALOR_A, int VALOR_B){
	return (VALOR_A<VALOR_B)?VALOR_A:VALOR_B;
}

/*
 * Con esta función comprobamos cuantas monedas tenemos
 * que usar para devolver el dinero que nos queda
 */
int Cambio(int MONEDERO[], int CANTI_MONEDAS, int DEVOLVER){
	// Este es el indice que uso para los for de recorrido
	int idx = 0;
	/* Este vector guarda la cantidad de monedas que hemos usado
	   según sea del primer tipo de monedas o del segundo tipo
	   asi sucesisamente la posición es el tipo de monedas
	   y el valor es la cantidad de veces que la hemos usado
	   según la vuelta que necesitamos dar en cada iteración */
	int monedas_usadas[CANTI_MONEDAS];
	/* Esta variable la uso para no tener que modificar
	   la cantidad de monedas que me llegan a la función */
	int auxCantiMonedas = CANTI_MONEDAS;
	/* Con este for inicializo todo el vector de monedas
	   usadas a 0 de esa forma cada vez que llamo a cambio
	  me aseguro de la veracidad de la cantidad de monedas
	  se supone que el for deberia llegar hasta el tamaño
	  total de monedas_usadas menos 1 que es el último
	  elemento de monedas_usadas */
	for (idx = 0;idx<CANTI_MONEDAS;idx++)
		monedas_usadas[idx] = 0; // <-- inicializo el vector a 0

	/* Me aseguro que la variable que voy a devolver esté a 0 */
	int cantidad_monedas = 0;

	/* El condicional que viene a continuación contempla los
	   dos casos base de la función de recurrencía y también
	   el caso general de la misma */
	if (DEVOLVER==0)
		cantidad_monedas = 0;
	else if (DEVOLVER<0 || auxCantiMonedas<=0)
		cantidad_monedas = 999999; // <-- mas infinito
	else{
		while (DEVOLVER>0 && auxCantiMonedas > 0){ // <-- Mientras tenga que devolver y no este en la ultima moneda
			if (DEVOLVER >= MONEDERO[auxCantiMonedas-1]){ // <-- Si el valor de la moneda es mas pequeño de lo que tengo que devolver
				DEVOLVER -= MONEDERO[auxCantiMonedas-1]; // <-- resto el valor de la moneda a lo que me queda por devolver
				monedas_usadas[auxCantiMonedas-1]++; // <-- Y añado una moneda de ese tipo a la solución
			}else // <-- Si el valor de la moneda es mas grande que lo que me queda por devolver
				auxCantiMonedas--; // <-- cojo la siguiente moneda mas pequeña
		}
	/* Este for se encarga de recorrer el vector de monedas
	   usadas para contarlas y acumularlas en cantidad_monedas
	   que es la variable que se devuélve */
		for (idx=0;idx<=CANTI_MONEDAS-1;idx++){
			cantidad_monedas += monedas_usadas[idx];
		}
	}
	return cantidad_monedas;
}

/*
 * Este es el menu principal
 */
int main(int argc, const char *argv[]){
	int misMonedas[atoi(argv[1])];// <-- Cantidad de Monedas disponibles
	nomPrograma = argv[0];
	char* parametro2 = argv[2];
	//int misMonedas[] = {1,2,5,9};
	char *charMoneda = malloc(2);
	int CD = 0, M = 0, V = 0;
	int idx = 0; // <-- Indice de cualquier for

	if (argc == 4){
		charMoneda = strtok(parametro2,",");
		while (charMoneda){
			misMonedas[idx] = atoi(charMoneda);
			idx++;
			charMoneda = strtok(NULL,",");
		}
		if (idx!=NELEMENTOS(misMonedas))
			funError(2);
		CD = atoi(argv[3]);
	}else if (argc < 4)
		funError(1);

	matriz tablaDinamica;
	// OJO: aquí hay un "fallo" y es que solo se pueden tener
	// monederos de 7 monedas y solo se puede devolver hasta 40 €
	// Se define en la parte superior del programa.

	/*
	 * Este es el nucleo del algoritmo:
	 * -El 1er for recorre la cantidad que tenemos que devolver désde 0 ya que también se contempla la posibilidad de no devolver nada
	 * -El 2do for recorre la cantidad de monedas, notese que este no recorre hasta <= si no que se detiene en el ultimo elemento del array
	 * -El condicional se encarga de llenar las posiciones 0 en los elementos que no tenemos que devolver dinero en otro caso ver memoria
	 * para poder entender por que es necesario un par de diagramas para entenderlo
	 * Comentarios para entender el código:
	 * -Variable misMonedas[]: Este sería el monedero del ejercicio
	 * -Variable tablaDinamica: Tabla dinamica que almacenas la cantidad de monedas a devolver en cada caso
	 * -Variable CD: esta es la cantidad que le tenemos que devolver al cliente.
	 * -Variable CM: esta es la cantidad de monedas optima que se tienen que devolver al final.
	 * -Variable M: estas son la cantidad de monedas disponibles que tendrá la función Cambio() en cada momento.
	 * -Variable V: este sera el valor que tendrá que devolver Cambio() en cada iteración
	 * -Variable C1 y C2: Son variables auxiliare para hacer el codigo mas legible
	 */
	unsigned int C1, C2;
	while (V<=CD){
		for (M = 0;M<NELEMENTOS(misMonedas);M++){
			if(V==0){
				tablaDinamica[M][V] = 0;
			}else if (CD-V >= misMonedas[0]){ // antes de pasarme de la cantidad que tengo que devolver hago otra cosa
				C1 = Cambio(misMonedas,M+1,V); // <-- Comprobamos el cambio con una moneda mas de otro tipo
				C2 = 1+Cambio(misMonedas,M,V-misMonedas[M]); // <-- Comprobamos el cambio con las mismas monedas pero con menos a devolver
				tablaDinamica[M][V] = min(C1,C2); // <-- Comprobamos con cual de las dos situaciones damos menos monedas
			}else{
				int R = CD - (misMonedas[M] * tablaDinamica[M][V]);
			}
		}
		V+=misMonedas[0];
	}
//	for (V = 0;V<=CD;V+=misMonedas[0]){ // aqui es donde tengo que poner un if para ver si me paso de lo que voy a devolver
//		for (M = 0;M<NELEMENTOS(misMonedas);M++){
//			if(V==0){
//				tablaDinamica[M][V] = 0;
//			}else{
//				C1 = Cambio(misMonedas,M+1,V); // <-- Comprobamos el cambio con una moneda mas de otro tipo
//				C2 = 1+Cambio(misMonedas,M,V-misMonedas[M]); // <-- Comprobamos el cambio con las mismas monedas pero con menos a devolver
//				tablaDinamica[M][V] = min(C1,C2); // <-- Comprobamos con cual de las dos situaciones damos menos monedas
//			}
//		}
//	}

	/*
	 * Aquí presentamos los datos que tenemos hasta el momento
	 */
	printf ("Cantidad a devolver: %d €\n",CD);
	/*
	 * Como en C no hay un length o size, hay que estar
	 * continuamente pasando el tamaño tando del vector
	 * como los de la matriz
	 */
	imprimeMonedero(misMonedas,NELEMENTOS(misMonedas));
	if (DEBUGMODE  == 1)
		imprimeTabla(misMonedas,tablaDinamica,NELEMENTOS(misMonedas),CD);

	/*
	 * Las variables aux1, aux2 y tamSol son para que el codigo sea mas fácil de leer
	 * esta parte es donde se recompone la solución a partir de los datos que hemos
	 * rellenado en la tabla
	 * Tanto esta parte como la anterior se podrian meter en una función pero al tener
	 * que pasar tantos parámetros he decidido dejarlo en el main
	 */
	int aux1 = NELEMENTOS(misMonedas)-1; // <-- Le pongo -1 por que es el ultimo elemento del array
	int aux2 = CD;
	int solucion[NELEMENTOS(misMonedas)];// <-- Array Solución

	for (idx = 0; idx<NELEMENTOS(solucion);idx++) // <-- Inicializo solución a 0
		solucion[idx] = 0;

	while (aux1 >= 0 && aux2 >= 0){
		if (aux1 > 0 && tablaDinamica[aux1][aux2]==tablaDinamica[aux1-1][aux2]) // <-- El orden de las comprobaciones IMPORTANTE
			aux1 -= 1;
		else{
			solucion[aux1] += 1;
			aux2 = aux2 - misMonedas[aux1];
		}
	}

	printf ("\nSolucion: \n");
	for (aux1 = 0 ;aux1<NELEMENTOS(solucion);aux1++){ // <-- Como tengo que llegar al final de los elementos uso <=
		printf("%2d Monedas de %d €\n",solucion[aux1],misMonedas[aux1]);
	}

	return 0;
}
