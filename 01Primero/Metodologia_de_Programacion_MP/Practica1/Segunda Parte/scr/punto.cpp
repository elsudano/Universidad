#include <iostream>
#include <cmath> // sqrt, M_PI
#include "punto.h"

using namespace std;

// Lee un punto en el formato (x,y), incluyendo la lectura de '(' ',' y ')'
Punto LeerPunto()
{
  char c;
  Punto p;
  cin >> c;
  cin >> p.x;
  cin >> c;
  cin >> p.y;
  cin >> c;
  //cout << " El leer punto funciona " << endl;
  return p;
}

// Escribe un punto en formato (x,y), incluyendo la escritura de '(' ',' y ')'
void EscribirPunto (Punto p)
{
  cout << "(" << p.x << "," << p.y << ")";
}

// Devuelve el punto que está entre p1 y p2 (más cercano a igual distancia de p1 y p2)
Punto PuntoMedio (Punto p1, Punto p2)
{
  Punto pm;
  pm.x=(p1.x+p2.x)/2;
  pm.y=(p1.y+p2.y)/2;
  return pm;
}

// Inicializa un punto con dos valores cx cy
void InicializarPunto (Punto &p, double cx, double cy)
{
}

// Devuelve la coordenada X del punto p
double GetX (Punto p)
{
   return p.x;
}

// Devuelve la coordenada Y del punto p
double GetY (Punto p)
{
  return p.y;
}