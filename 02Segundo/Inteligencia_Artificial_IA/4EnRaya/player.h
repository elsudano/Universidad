#ifndef PLAYER_H
#define PLAYER_H

#ifdef WIN
	#include "environment.h"
#elif defined LINUX
	#include "../inc/environment.h"
#else
#error Falta definir sistema operativo! Definir g++ -DWIN o g++ -DLINUX
#endif

class Player{
    public:
      Player(int jug);
      Environment::ActionType Think();
      void Perceive(const Environment &estado);
      void verTablero(const Environment &estado);
      double Poda_AlfaBeta(const Environment &estado, int jugador, bool MAXMIN, int profun, double valorPadre);
      double Valoracion(const Environment &estado, int jugador);

    private:
      int jugador_;
      Environment actual_;
};
#endif
