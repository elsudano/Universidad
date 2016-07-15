#ifndef PLAYER_H
#define PLAYER_H

#include "environment.h"

class Player{
    public:
      Player(int jug);
      Environment::ActionType Think();
      void Perceive(const Environment &estado);
      void verTablero(const Environment &estado);
      void verTableroColor(const Environment &estado);
      double Poda_AlfaBeta(const Environment &estado, int jugador, bool MAXMIN, int profun, double alpha, double beta);
      double Valoracion(const Environment &estado, int jugador);

    private:
      int jugador_;
      Environment actual_;
};
#endif
