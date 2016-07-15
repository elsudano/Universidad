// **************************************************************
// *** MOVIMIENTO
// **************************************************************
#ifndef _MOVIMIENTO_
#define _MOVIMIENTO_

#ifndef _HORA_
#define _HORA_
#include "cuenta.h"
#endif

#ifndef _FECHA_
#define _FECHA_
#include "cliente.h"
#endif

using namespace std;

class Movimiento {
  private:
    Hora hora;
    Fecha fecha;
    double cantidad;
    string anotacion;

  public:
    Movimiento()  { cantidad=0; }   // Para poder LeerMovimiento
    Movimiento(Hora h, Fecha f, double c, string a="")
               { fecha=f; hora=h; cantidad=c; anotacion=a; }
    Hora GetHora() const                        { return hora; }
    Fecha GetFecha() const                      { return fecha; }
    double GetCantidad() const                  { return cantidad; }
    string GetAnotacion() const                 { return anotacion; }
};

Movimiento LeerMovimiento();
void EscribirMovimiento(const Movimiento& m);
#endif