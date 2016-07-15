
#ifndef CIRCULO
#define CIRCULO
#include "punto.h"

struct Circulo {
    Punto centro;
    double radio;
};

Circulo LeerCirculo();
void EscribirCirculo(Circulo c);
double Area (Circulo c);
void InicializarCirculo (Circulo& c, Punto centro, double radio);

#endif

