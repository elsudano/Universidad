// **************************************************************
// *** MAIN 4
// **************************************************************
#include "finanzas.h"

using namespace std;

// Crear 2 cuentas y mostrarlas
int main() {
  Hora hora(10,00,00);
  Fecha fecha(17,12,2010);

  Cuenta c1("1234", "443", "1122334455", "16");
  c1.Ingreso(hora,fecha,1000,"Primer ingreso");
  c1.Ingreso(hora,fecha,500,"Segundo ingreso");
  c1.Retirada(hora,fecha,800,"Primera retirada");
  c1.Ingreso(hora,fecha,2500,"Tercer ingreso");
  c1.Retirada(hora,fecha,1200,"Segunda retirada");
  EscribirCuenta(c1);

  Cuenta c2("4", "3006", "12345", "78");
  c2.Ingreso(hora,fecha,1500,"Primer ingreso");
  c2.Retirada(hora,fecha,800,"Primera retirada");
  c2.Ingreso(hora,fecha,500,"Segundo ingreso");
  c2.Retirada(hora,fecha,300,"Primera retirada");
  c2.Ingreso(hora,fecha,100,"Tercer ingreso");
  c2.Retirada(hora,fecha,200,"Segunda retirada");
  c2.Retirada(hora,fecha,250,"Tercera retirada");
  EscribirCuenta(c2);
}