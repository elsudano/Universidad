#ifndef PUNTO
#define PUNTO

struct Punto {
    double x,y;
};

Punto LeerPunto();
void EscribirPunto (Punto p);
Punto PuntoMedio (Punto p1, Punto p2);
void InicializarPunto (Punto &p, double cx, double cy);
double GetX (Punto p);
double GetY (Punto p);

#endif
