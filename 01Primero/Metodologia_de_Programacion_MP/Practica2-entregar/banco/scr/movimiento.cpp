#include "finanzas.h"

using namespace std;

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
