/**
  * @file circulomedio.cpp
  * @brief Calcula círculo con centro en medio de dos círculos y radio la mitad de la distancia
  * @author MP
  * @warning Módulo no definitivo (creado para ser modificado)
  *
  * Un ejemplo de ejecución es:
  *   Introduzca un circulo en formato radio-(x,y): 3-(0,0)
  *   Introduzca otro circulo: 4-(5,0)
  *   El círculo que pasa por los dos centros es: 2.5-(2.5,0)
  */

#include <iostream>
#include <cmath> // sqrt, M_PI
using namespace std;

// Definimos tipo Punto y Circulo ___________________________________________

struct Punto {
    double x,y;
};

struct Circulo {
    Punto centro;
    double radio;
};

// Funciones para manejar puntos y circulos __________________________________

// Lee un punto en el formato (x,y), incluyendo la lectura de '(' ',' y ')'
Punto LeerPunto()
{
}

// Escribe un punto en formato (x,y), incluyendo la escritura de '(' ',' y ')'
void EscribirPunto (Punto p)
{
}

// Inicializa un punto con dos valores cx cy
void InicializarPunto (Punto &p, double cx, double cy)
{
}

// Devuelve la coordenada X del punto p
double GetX (Punto p)
{
}

// Devuelve la coordenada Y del punto p
double GetY (Punto p)
{
}

// Devuelve la distancia euclídea entre p1 y p2
double Distancia (Punto p1, Punto p2)
{
}

// Devuelve el punto que está entre p1 y p2 (más cercano a igual distancia de p1 y p2)
Punto PuntoMedio (Punto p1, Punto p2)
{
}


// Lee círculo en formato radio-centro, incluyendo '-' y leyendo "centro" con LeerPunto
Circulo LeerCirculo()
{
}

// Escribe círculo en formato radio-centro,  incluyendo '-' y escribiendo "centro" con EscribirPunto
void EscribirCirculo(Circulo c)
{
}

// Inicializa circulo c con centro y radio
void InicializarCirculo (Circulo& c, Punto centro, double radio)
{
}

// Devuelve del centro de c
Punto Centro (Circulo c)
{
}

// Devuelve el radio de c
double Radio (Circulo c)
{
}

// Devuelve el área del círculo c
double Area (Circulo c)
{
}

// Devuelve si p está en el interior del círculo c (distancia al centro menor que el radio)
bool Interior (Punto p, Circulo c)
{
}

// Devuelve la distancia de c1 a c2 (distancia entre los centros restándole los radios)
double Distancia (Circulo c1, Circulo c2)
{
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

/* Fin: circulomedio.cpp */
