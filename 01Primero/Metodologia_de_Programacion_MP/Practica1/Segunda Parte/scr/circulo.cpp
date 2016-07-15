#include <iostream>
#include <cmath> // sqrt, M_PI
#include "circulo.h"

using namespace std;

// Lee círculo en formato radio-centro, incluyendo '-' y leyendo "centro" con LeerPunto
Circulo LeerCirculo()
{
  Circulo circulo;
  char c;
  cin >> circulo.radio;
  cin >> c;
  circulo.centro=LeerPunto();
  return circulo;
}

// Escribe círculo en formato radio-centro,  incluyendo '-' y escribiendo "centro" con EscribirPunto
void EscribirCirculo(Circulo c)
{
  cout << c.radio << "-";
  EscribirPunto(c.centro);
}

// Inicializa circulo c con centro y radio
void InicializarCirculo (Circulo& c, Punto centro, double radio)
{
  c.radio=radio;
  c.centro=centro;
}

// Devuelve el área del círculo c
double Area (Circulo c)
{
  return M_PI*(pow(c.radio,2));
}