#include <iostream>
#include <cassert>
#include <vector>
#include <string>
#include <iomanip>

#include "cliente.h"
#include "hora.h"
#include "fecha.h"
#include "movimiento.h"
#include "cuenta.h"
#include "banco.h"

using namespace std;
/*

Movimiento LeerMovimiento()
{
  Fecha f;
  f=LeerFecha();
  Hora h;
  h=LeerHora();
  double c;
  cin >> c;
  string a;
  getline(cin,a);
  Movimiento mov(h,f,c,a);
  return mov;
}

void EscribirMovimiento(const Movimiento& m)
{
  EscribirFecha(m.GetFecha());
  cout << "    ";
  EscribirHora(m.GetHora());
  cout << "    ";
  cout << setw(7) << m.GetCantidad();
  cout << "    ";
  cout << m.GetAnotacion();
}

void EscribirCuenta(const Cuenta& c)
{
  cout << c.GetNumeroCuenta() << endl;
  cout << c.Saldo() << endl;
  cout << c.NumMovimientos() << endl;
  for (int i=0; i<c.NumMovimientos(); i++) {
    EscribirMovimiento(c.GetMovimiento(i));
    cout << endl;
  }
}

Cuenta LeerCuenta()
{
  string ncuenta;
  getline(cin,ncuenta);
  string suc, ent, dc, cue;
  ent = ncuenta.substr(0,4);
  suc = ncuenta.substr(4,4);
  dc = ncuenta.substr(8,2);
  cue = ncuenta.substr(10,10);
  double saldo;
  cin >> saldo;
  Cuenta cuenta(ent,suc,cue,dc);
  int nmov;
  cin >> nmov;
  for (int i=0; i<nmov; i++) {
    Movimiento mov;
    mov=LeerMovimiento();
    cuenta.Ingreso(mov.GetHora(), mov.GetFecha(), mov.GetCantidad(), mov.GetAnotacion());
  }
  // Tras leer movimientos verificar que el saldo es correcto
  if (cuenta.Saldo()!=saldo)
    cout << "ERROR: El saldo de la cuenta no cuadra" << endl;
  return cuenta;
}
*/