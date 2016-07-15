#include <iostream>
#include <cstdlib>
#include <vector>
#include <queue>
#include <cerrno>

/* Las dos macros que vienen a continuación se pueden
 * modificar al compilar el programa usando la bandera
 * -D del compilador g++ ejemplo:
 * g++ -O2 -DLINUX -DCONSOLA_COLOR player.cpp -o player.o
 * y si no queremos usar esto en el make solo tenemos
 * que descomentar las dos lineas siguientes según lo
 * que queramos hacer.
 */
//#define CONSOLA_COLOR// <--- Valor posibles de la constante CONSOLA_COLOR
#define WIN // <---- Valores para compilar el player WIN o LINUX

#ifdef WIN
	#include <ctime>
	#include "player.h"
	#include "environment.h"
	#ifdef CONSOLA_COLOR
		#include "concolor.h"
	#endif
#elif defined LINUX
	#include "../inc/player.h"
	#include "../inc/environment.h"
	#ifdef CONSOLA_COLOR
		#include "../inc/concolor.h"
	#endif
#else
#error Falta definir sistema operativo! Definir g++ -DWIN o g++ -DLINUX
#endif

using namespace std;

const double masinf=9999999999.0, menosinf=-9999999999.0;

// Constructor
Player::Player(int jug){
    jugador_=jug;
}

// Actualiza el estado del juego para el jugador
void Player::Perceive(const Environment &estado){
    actual_=estado;
}

// visualizar el estado del tablero
void Player::verTablero(const Environment &estado){
	/* siempre que tengamos una matriz es mejor que el bucle
	 * externo para recorrerlo sea el de las filas por que
	 * en la memoria se guardan una fila completa detras
	 * de otra fila completa entonces cuando la recorremos
	 * entera solo tenemos que ir avanzando
	 */
#ifdef CONSOLA_COLOR
	cout <<yellowb<< " Jugador evaluado: " << ((estado.JugadorActivo()==1)?2:1) <<normal<< endl;
#else
	cout << " Jugador evaluado: " << ((estado.JugadorActivo()==1)?2:1) << endl;
#endif
	for (int F=0; F < 7; F++){
		cout << " ";
		for (int C=0; C < 7; C++){
			if ((int) estado.See_Casilla(F,C) == 1)
#ifdef CONSOLA_COLOR
				cout <<greenb<< "X ";
#else
				cout << "X ";
#endif

			else if ((int) estado.See_Casilla(F,C) == 2)
#ifdef CONSOLA_COLOR
				cout <<redb<< "O ";
#else
				cout << "O ";
#endif
			else
#ifdef CONSOLA_COLOR
				cout <<whiteb<< "- ";
#else
				cout << "- ";
#endif
		}
#ifdef CONSOLA_COLOR
		cout <<normal<< endl;
#else
		cout << endl;
#endif
	}
	cout << endl;
}

// ------------------- Los tres metodos anteriores no se pueden modificar

// Funcion heuristica (ESTA ES LA QUE TENEIS QUE MODIFICAR)
double Player::Valoracion(const Environment &estado, int jugador){
	double resultado = 0.0;
	int ganador = estado.RevisarTablero();

	if (ganador == jugador)
		resultado = masinf; // Gana el jugador que pide la valoracion
	else if (ganador != 0)
		resultado = menosinf; // Pierde el jugador que pide la valoracion
	else if (estado.Get_Casillas_Libres() == 0)
		resultado = 0; // Hay un empate global y se ha rellenado completamente el tablero
	else
		for (int i = 0; i < 7; i++) // <---- FILAS
			for (int j = 0; j < 7; j++) // <---- COLUMNAS
				if (estado.See_Casilla(i,j)==jugador){ // esta linea sirve solo para evaluar las casillas llenas por fichas
					// 2 casillas horizontales---------------------------------------------------------------------------------------------------
					// * *
					if (j<6 and estado.See_Casilla(i,j) == jugador and estado.See_Casilla(i,j+1) == jugador)
						resultado += 50;
					// 2 casillas verticales---------------------------------------------------------------------------------------------------
					// *
					// *
					if (i<6 and estado.See_Casilla(i,j) == jugador and estado.See_Casilla(i+1,j) == jugador){
						resultado += 40;
						if (j>1 and j<5)
							resultado += 2;
					}
					// 2 casillas verticales columna central-------------------------------------------------------------------------------------
					//    *
					//	  *
					if (i<6 and j==3 and estado.See_Casilla(i,j) == jugador and estado.See_Casilla(i+1,j) == jugador)
						resultado += 50;
					// 2 casillas diagonal derecha---------------------------------------------------------------------------------------------------
					//  *
					// *
					if (i<6 and j<6 and estado.See_Casilla(i,j) == jugador and estado.See_Casilla(i+1,j+1) == jugador)
						resultado += 20;
					// 2 casillas diagonal izquierda---------------------------------------------------------------------------------------------------
					// *
					//  *
					if (i<6 and j<6 and estado.See_Casilla(i,j) == jugador and estado.See_Casilla(i+1,j-1) == jugador)
						resultado += 20;
					// 3 casillas horizontales---------------------------------------------------------------------------------------------------
					// * * *
					if (j<5 and estado.See_Casilla(i,j) == jugador and estado.See_Casilla(i,j+1) == jugador and estado.See_Casilla(i,j+2) == jugador)
						resultado += 70;
					// 3 casillas verticales---------------------------------------------------------------------------------------------------
					// *
					// *
					// *
					if (i<5 and estado.See_Casilla(i,j) == jugador and estado.See_Casilla(i+1,j) == jugador and estado.See_Casilla(i+2,j) == jugador){
						resultado += 70;
						if (j>1 and j<5)
							resultado += 20;
					}
					// 3 casillas diagonal izquierda
					// *
					//  *
					//   *
					if (i<5 and j>1 and estado.See_Casilla(i,j) == jugador and estado.See_Casilla(i+1,j-1) == jugador and estado.See_Casilla(i+2,j-2) == jugador)
						resultado += 40;

					// 3 casillas diagonal derecha
					//  *
					// *
					//*
					if (i<5 and j<5 and estado.See_Casilla(i,j) == jugador and estado.See_Casilla(i+1,j+1) == jugador and estado.See_Casilla(i+2,j+2) == jugador)
						resultado += 40;
					//-----------------------------Situaciones para crear posibles jugadas buenas----------------------
					// 3 casillas juntas en las 3 pocisiones centrales del tablero de cualquier fila
					if (j>0 and j<6 and estado.See_Casilla(i,j) == jugador and estado.See_Casilla(i,j+1) == jugador and estado.See_Casilla(i,j+2) == jugador)
						resultado += 100;
					// intentar conseguir una T
					// * * *
					//   *
					if (j>0 and j<6 and i<6 and estado.See_Casilla(i,j) == jugador and estado.See_Casilla(i+1,j-1) == jugador and estado.See_Casilla(i+1,j) == jugador and estado.See_Casilla(i+1,j+1) == jugador)
						resultado += 400;
				}
	return resultado;
}

// Funcion MiniMax para recorrer el árbol de soluciones con la poda Alpha-Beta
double Player::Poda_AlfaBeta(const Environment &estado, int jugador, bool MAXMIN, int profun, double valorPadre){
	Environment tableros[7];
	int total_tableros = estado.GenerateAllMoves(tableros);
	profun = profun-1;
	double valor = 0.0;


	if(estado.JuegoTerminado() or profun == 0){
		valor = Valoracion(estado,jugador);//-Valoracion(estado,(jugador==1)?2:1);
//		if (estado.Get_Casillas_Libres()==30 - profun){
//			cout << " Valor Nodo Final: " << valor << endl;
//			verTableroColor(estado);
//		}
	}else{
		(MAXMIN)?valor=menosinf:valor=masinf; //<--- cambiar esta linea para probar el menos por el mas
		//(MAXMIN)?valor=menosinf:valor=masinf;
		for (int t = 0;t<total_tableros;t++){
			if (MAXMIN){
				valor = max(valor,Poda_AlfaBeta(tableros[t],jugador,!MAXMIN,profun,valor));
//				cout << "Poda Beta!!: Valor: " << valor << " >= valorPadre: " << valorPadre << endl;
//				verTableroColor(tableros[t]);
				if (valor>=valorPadre)
					break;
			}else{
				valor = min(valor,Poda_AlfaBeta(tableros[t],jugador,!MAXMIN,profun,valor));
//				cout << "Poda Alpha!!: Valor: " << valor << " <= valorPadre: " << valorPadre << endl;
//				verTableroColor(tableros[t]);
				if (valor<=valorPadre){
					break;
				}
			}
		}
	}
	return valor;
}

// Invoca el siguiente movimiento del jugador
Environment::ActionType Player::Think(){
#ifdef WIN
	system("cls");
#elif defined LINUX
	system("clear");
#endif
	srand(time(NULL));
    const int PROFUNDIDAD_ALFABETA = 8; // Umbral maximo de profundidad para la poda Alfa_Beta
    Environment::ActionType accion; // acción que se va a devolver
    Environment tableros[7];

    //Numero de acciones posibles en el estado actual, Numero de tableros del estado actual y que movimiento realizar
    int n_posibilidades = actual_.GenerateAllMoves(tableros);
    double valor = 0.0, valorActual = menosinf;
    int fichas_col_mas_vacia = 0, columna_elegida = 0;
    //--------------------- AQUI EMPIEZA LA PARTE A REALIZAR POR EL ALUMNO ------------------------------------------------
    //    Opcion: Poda AlfaBeta
    //    NOTA: La parametrizacion es solo orientativa
#ifdef CONSOLA_COLOR
    cout <<magentab<< " Empieza el analisis de los tableros... " <<normal<< endl << endl;
#else
    cout << " Empieza el analisis de los tableros... " << endl << endl;
#endif

	for (int t=0; t<n_posibilidades; t++){
		valor = Poda_AlfaBeta(tableros[t],jugador_,false,PROFUNDIDAD_ALFABETA,valor);
#ifdef CONSOLA_COLOR
		cout <<greenb<< " Alpha " << valor <<whiteb<< " > " <<redb<< valorActual << " Beta"<<normal<< endl;
#else
		cout << " Alpha " << valor << " > " << valorActual << " Beta"<< endl;
#endif

		verTablero(tableros[t]);
		if (valor>valorActual){
			valorActual = valor;
			accion = static_cast< Environment::ActionType > (tableros[t].Last_Action(jugador_));
		}else if (valorActual == menosinf){
			for (int col = 0; col<7; col++){
				if (fichas_col_mas_vacia > actual_.Get_Ocupacion_Columna(col)){
					fichas_col_mas_vacia = actual_.Get_Ocupacion_Columna(col);
					columna_elegida = col;
				}
			}
			accion = static_cast< Environment::ActionType > (columna_elegida);
		}
	}
#ifdef CONSOLA_COLOR
	cout <<whiteb<< " La ficha se puso en: " << actual_.ActionStr(accion) <<normal<<endl;
	cout <<magentab<< " Termina el analisis de los tableros... " <<normal<< endl;
#else
	cout << " La ficha se puso en: " << actual_.ActionStr(accion) <<endl;
	cout << " Termina el analisis de los tableros... " << endl;
#endif
	//--------------------- AQUI TERMINA LA PARTE A REALIZAR POR EL ALUMNO ------------------------------------------------
  	return accion;
}

