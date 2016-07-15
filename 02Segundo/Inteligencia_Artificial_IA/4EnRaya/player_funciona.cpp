#include <iostream>
#include <cstdlib>
#include <vector>
#include <queue>
#include <math.h>
#include "player.h"
#include "environment.h"
#include "concolor.h"

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
void Player::verTableroColor(const Environment &estado){
	/* siempre que tengamos una matriz es mejor que el bucle
	 * externo para recorrerlo sea el de las filas por que
	 * en la memoria se guardan una fila completa detras
	 * de otra fila completa entonces cuando la recorremos
	 * entera solo tenemos que ir avanzando
	 */
//	cout <<magenta<< " Ultima accion aplicada: " << estado.Last_Action(jugador_) <<normal<< endl;
//	cout <<magenta<< " Ultimo Jugador: " << estado.JugadorActivo() <<normal<< endl;
	for (int F=0; F < sqrt(49); F++){
		cout << " ";
		for (int C=0; C < sqrt(49); C++){
			if ((int) estado.See_Casilla(F,C) == 1)
				cout <<greenb<< "X ";
			else if ((int) estado.See_Casilla(F,C) == 2)
				cout <<redb<< "O ";
			else
				cout <<whiteb<< "- ";
		}
		cout <<normal<< endl;
	}
	cout << endl;
}

// visualizar el estado del tablero
void Player::verTablero(const Environment &estado){
	/* siempre que tengamos una matriz es mejor que el bucle
	 * externo para recorrerlo sea el de las filas por que
	 * en la memoria se guardan una fila completa detras
	 * de otra fila completa entonces cuando la recorremos
	 * entera solo tenemos que ir avanzando
	 */
	for (int F=0; F < sqrt(49); F++){
		for (int C=0; C < sqrt(49); C++){
			cout << (int) estado.See_Casilla(F,C) << " ";
		}
		cout << endl;
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
					if (i<6 and estado.See_Casilla(i,j) == jugador and estado.See_Casilla(i+1,j) == jugador)
						resultado += 50;
					// 2 casillas diagonal izquierda---------------------------------------------------------------------------------------------------
					//  *
					// *
					if (i<6 and j<6 and estado.See_Casilla(i,j) == jugador and estado.See_Casilla(i+1,j+1) == jugador)
						resultado += 50;
					// 2 casillas diagonal derecha---------------------------------------------------------------------------------------------------
					// *
					//  *
					if (i<6 and j<6 and estado.See_Casilla(i,j+1) == jugador and estado.See_Casilla(i+1,j) == jugador)
						resultado += 50;
					// 3 casillas horizontales---------------------------------------------------------------------------------------------------
					// * * *
					if (j<5 and estado.See_Casilla(i,j) == jugador and estado.See_Casilla(i,j+1) == jugador and estado.See_Casilla(i,j+2) == jugador)
						resultado += 75;
					// 3 casillas verticales---------------------------------------------------------------------------------------------------
					// *
					// *
					// *
					if (i<5 and estado.See_Casilla(i,j) == jugador and estado.See_Casilla(i+1,j) == jugador and estado.See_Casilla(i+2,j) == jugador)
						resultado += 75;
					// 3 casillas diagonal izquierda
					//  *
					// *
					//*
					if (i<5 and j<5 and estado.See_Casilla(i,j) == jugador and estado.See_Casilla(i+1,j+1) == jugador and estado.See_Casilla(i+2,j+2) == jugador)
						resultado += 75;
					// 3 casillas diagonal derecha
					// *
					//  *
					//   *
					if (i<5 and j<5 and estado.See_Casilla(i,j+2) == jugador and estado.See_Casilla(i+1,j+1) == jugador and estado.See_Casilla(i+2,j) == jugador)
						resultado += 75;
					//-----------------------------Situaciones para crear posibles jugadas buenas----------------------
					if (j>0 and j<6 and estado.See_Casilla(i,j) == jugador and estado.See_Casilla(i,j+1) == jugador and estado.See_Casilla(i,j+2) == jugador)
						resultado += 150;
				}
	return resultado;
}

// Funcion MiniMax para recorrer el árbol de soluciones con la poda Alpha-Beta
double Player::Poda_AlfaBeta(const Environment &estado, int jugador, bool MAXMIN, int profun, double alpha, double beta){
//	Environment tableros[7];
//	int total_tableros = estado.GenerateAllMoves(tableros);
	bool aplicables[7];
	int total_tableros = estado.possible_actions(aplicables);
	int movimiento = -1;
	double valor = 0.0;


	if(estado.JuegoTerminado() or profun == 0){
		valor = Valoracion(estado,jugador);
//		cout << " Numero de Movimientos Posibles:" << total_tableros << " Valor Nodo Final: " << valor << endl;
//		verTableroColor(estado);
		return valor;
	}
    if (MAXMIN){
        for (int t = 0;t<total_tableros;t++){
            Environment tablero = estado.GenerateNextMove(movimiento);
            alpha = max(alpha,Poda_AlfaBeta(tablero,tablero.JugadorActivo(),!MAXMIN,profun-1,alpha,beta));
            //alpha = max(alpha,Poda_AlfaBeta(tableros[t],tableros[t].JugadorActivo(),!MAXMIN,profun-1,alpha,beta));
            //alpha = max(alpha,Poda_AlfaBeta(tableros[t],jugador,!MAXMIN,profun-1,alpha,beta));
            if (alpha>=beta){
//                cout << "Poda beta!!: alpha: " << alpha << " >= beta: " << beta << endl;
//                verTableroColor(tablero);
                return beta;
            }
        }
        return alpha;
    }else{
        for (int t = 0;t<total_tableros;t++){
            Environment tablero = estado.GenerateNextMove(movimiento);
            beta = min(beta,Poda_AlfaBeta(tablero,tablero.JugadorActivo(),!MAXMIN,profun-1,alpha,beta));
            //beta = min(beta,Poda_AlfaBeta(tableros[t],tableros[t].JugadorActivo(),!MAXMIN,profun-1,alpha,beta));
            //beta = min(beta,Poda_AlfaBeta(tableros[t],jugador,!MAXMIN,profun-1,alpha,beta));
            if (beta<=alpha){
//                cout << "Poda alpha!!: alpha: " << alpha << " <= beta: " << beta << endl;
//                verTableroColor(tablero);
                return alpha;
            }
        }
        return beta;
    }
}

// Invoca el siguiente movimiento del jugador
Environment::ActionType Player::Think(){
    const int PROFUNDIDAD_ALFABETA = 8; // Umbral maximo de profundidad para la poda Alfa_Beta
    Environment::ActionType accion; // acción que se va a devolver
    Environment tableros[7];
    //bool aplicables[7]; // Vector bool usado para obtener las acciones que son aplicables en el estado actual. La interpretacion es
                            // aplicables[0]==true si PUT1 es aplicable
                            // aplicables[1]==true si PUT2 es aplicable
                            // aplicables[2]==true si PUT3 es aplicable
                            // aplicables[3]==true si PUT4 es aplicable
                            // aplicables[4]==true si PUT5 es aplicable
                            // aplicables[5]==true si PUT6 es aplicable
                            // aplicables[6]==true si PUT7 es aplicable

    //Numero de acciones posibles en el estado actual, Numero de tableros del estado actual y que movimiento realizar
    int n_posibilidades, movimiento = -1;
    n_posibilidades = actual_.GenerateAllMoves(tableros);
    //actual_.possible_actions(aplicables); // Obtengo las acciones aplicables al estado actual en "aplicables"

    double alpha, beta, valor = 0.0; // Cotas de la poda AlfaBeta
    alpha = menosinf;
    beta = masinf;
    //--------------------- AQUI EMPIEZA LA PARTE A REALIZAR POR EL ALUMNO ------------------------------------------------
    //    Opcion: Poda AlfaBeta
    //    NOTA: La parametrizacion es solo orientativa
	for (int t=0; t<n_posibilidades; t++){
		//Environment tablero = actual_.GenerateNextMove(movimiento);
		valor = Poda_AlfaBeta(tableros[t],jugador_,false,PROFUNDIDAD_ALFABETA,alpha,beta);
		if (valor>alpha){
			alpha = valor;
			accion = static_cast< Environment::ActionType > (tableros[t].Last_Action(jugador_));
		}
	}
    if (actual_.Get_Casillas_Libres()<21){
              cout <<greenb<< " MiniMax: " << valor << " Alpha: " << alpha << " Beta: " << beta <<normal<< endl;
              verTableroColor(actual_);
    }
	//--------------------- AQUI TERMINA LA PARTE A REALIZAR POR EL ALUMNO ------------------------------------------------
    return accion;
}

