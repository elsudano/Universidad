/*
 * Examen1.h
 *
 *  Created on: 17/02/2014
 *      Author: Carlos de la Torre
 */

#ifndef EXAMEN1_H_
#define EXAMEN1_H_
#include <vector>
#include <stack>
#include <queue>

using namespace std;

enum palo {
	espadas,bastos,copas,oros
};

struct Carta{
	int num;
	int palo;
};

template <typename T>
class MontonCartas {
private:
	T miContenedor;
	int tam;

public:
	MontonCartas();
	void Barajar();
	Carta CogerCarta();
	void EliminarCarta();
	void InsertarCarta(Carta c);
	int Size();

};

#define CUAL_COMPILO 1

#if CUAL_COMPILO==1
  #include "Examen1Vector.hxx"
#elif CUAL_COMPILO==2
  #include "Examen1Lista.hxx"
#elif CUAL_COMPILO==3
  #include "Examen1Pila.hxx"
#elif CUAL_COMPILO==4
  #include "Examen1Cola.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION
#error compila como: g++ -D version -o ejecutable principal.ccp
#endif

class Examen1{
private:
	/**
	 * @brief Contenedor de Monton de Cartas de diferentes tipos para el ejercicio
	 */
	MontonCartas<stack<Carta> > miMontonDeCartas;

public:


	/**
	 * @brief Esta es la ejecucion del programa
	 */
	void ejecutar();
};

#endif /* EXAMEN1_H_ */
