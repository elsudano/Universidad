#include <iostream>
#include <cmath> // sqrt, M_PI
#include "punto.h"
#include "circulo.h"

using namespace std;

// Devuelve del centro de c
Punto Centro (Circulo c)
{
  return c.centro;
}

// Devuelve la distancia euclídea entre p1 y p2
double Distancia (Punto p1, Punto p2)
{
  return sqrt(pow(p1.x-p2.x,2)+pow(p1.y-p2.y,2));
}

int main()
{
  Circulo c1,c2;

  do {
   cout << "Introduzca un circulo en formato radio-(x,y): ";
   c1= LeerCirculo();
   cout << "Introduzca otro circulo: ";
   c2= LeerCirculo();
  } while (Distancia(Centro(c1),Centro(c2))==0);

  Circulo res;

  InicializarCirculo(res,PuntoMedio(Centro(c1),Centro(c2)),
                     Distancia(Centro(c1),Centro(c2))/2);
  cout << "El círculo que pasa por los dos centros es: ";
  EscribirCirculo(res);
  cout << endl;
}