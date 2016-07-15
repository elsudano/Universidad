#include "finanzas.h"

using namespace std;

void Cuenta::CalcularDigitosControl()
{
  int suma1 = (entidad.at(0)-'0') * 4 +
              (entidad.at(1)-'0') * 8 +
              (entidad.at(2)-'0') * 5 +
              (entidad.at(3)-'0') * 10 +
              (sucursal.at(0)-'0') * 9 +
              (sucursal.at(1)-'0') * 7 +
              (sucursal.at(2)-'0') * 3 +
              (sucursal.at(3)-'0') * 6;
  int dig1 = 11 - (suma1%11);
  if (dig1==11)
    dig1 = 0;
  else if (dig1==10)
    dig1 = 1;
  int suma2 = (cuenta.at(0)-'0') * 1 +
              (cuenta.at(1)-'0') * 2 +
              (cuenta.at(2)-'0') * 4 +
              (cuenta.at(3)-'0') * 8 +
              (cuenta.at(4)-'0') * 5 +
              (cuenta.at(5)-'0') * 10 +
              (cuenta.at(6)-'0') * 9 +
              (cuenta.at(7)-'0') * 7 +
              (cuenta.at(8)-'0') * 3 +
              (cuenta.at(9)-'0') * 6;
  int dig2 = 11 - (suma2%11);
  if (dig2==11)
    dig2 = 0;
  else if (dig2==10)
    dig2 = 1;
  dcontrol.clear();
  dcontrol += dig1+'0';
  dcontrol += (char)(dig2+'0');
}

Cuenta::Cuenta(const string& ent,const  string& suc,const  string& cue,const  string& ctr)
{
  CrearCuenta(ent,suc,cue,ctr);
}

Cuenta::Cuenta(const string& ncuenta)
{
  assert(ncuenta.size()==20);
  CrearCuenta(ncuenta.substr(0,4),ncuenta.substr(4,4),ncuenta.substr(10,10),ncuenta.substr(8,2));
}

void Cuenta::CrearCuenta(string ent, string suc, string cue, const string& ctr)
{
  // Rellenar con ceros a la izquierda
  if (suc.size()<4) {
    suc = string(4-suc.size(),'0') + suc;
  }
  if (ent.size()<4) {
    ent = string(4-ent.size(),'0') + ent;
  }
  if (cue.size()<10) {
    cue = string(10-cue.size(),'0') + cue;
  }
  entidad = ent;
  sucursal = suc;
  cuenta = cue;
  CalcularDigitosControl();
  if (dcontrol!=ctr) {
    cout << "ERROR: Se ha intentado crear una cuenta no vï¿½lida" << endl;
    entidad="";
    sucursal="";
    cuenta="";
    dcontrol="";   // Para indicar que no es válida
  }
  saldo = 0;
}

void Cuenta::Ingreso (const Hora& h, const Fecha& f, double cant, const string& motivo)
{
  assert(saldo+cant>=0);
  Movimiento mov(h,f,cant,motivo);
  movimientos.push_back(mov);
  saldo += cant;
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