#include "finanzas.h"

using namespace std;

// **************************************************************
// *** MAIN 1
// **************************************************************

// Crear un Banco y mostrar datos
int main() {
  Banco banco;
  Cliente cli1, cli2;
  string ncuenta1="12340443161122334455";
  string ncuenta2="00043006780000012345";
  Hora hora1(10,30,27);
  Hora hora2(12,15,52);
  Hora hora3(9,1,10);
  Hora hora4(10,47,34);
  Hora hora5(17,21,22);
  Hora hora6(13,45,8);
  Hora hora7(14,31,00);
  Hora hora8(8,5,7);
  Hora hora9(19,23,32);
  Fecha fecha1(2,3,2010);
  Fecha fecha2(6,3,2010);
  Fecha fecha3(17,5,2010);
  Fecha fecha4(12,7,2010);
  Fecha fecha5(24,9,2010);
  Fecha fecha6(24,12,2010);
  Fecha fecha7(3,1,2011);
  Fecha fecha8(9,1,2011);

  // Cliente 1
  cli1.nif = "12345678d";
  cli1.nombre = "Antonio";
  cli1.apellidos = "Perez Gutierrez";
  cli1.direccion = "C/ del Percebe, 13";

  // Cliente 2
  cli2.nif = "98765432a";
  cli2.nombre = "Juan";
  cli2.apellidos = "Garcia Garcia";
  cli2.direccion = "C/ de Abajo, 10";

  banco.NuevoCC(cli1,ncuenta1);
  banco.NuevoCC(cli2,ncuenta2);
  banco.NuevoCC(cli2,ncuenta1);
  banco.Ingreso(ncuenta1,hora1,fecha1,2000,"Primera imposición");
  banco.Ingreso(ncuenta1,hora2,fecha1,1000,"Mi primer sueldo");
  banco.Ingreso(ncuenta2,hora3,fecha2,10000,"Primera imposición");
  banco.Retirada(ncuenta1,hora4,fecha3,500,"Para las compras de navidad");
  banco.Retirada(ncuenta1,hora5,fecha4,100,"Para pagar recibos");
  banco.Ingreso(ncuenta1,hora6,fecha5,700,"Un trabajillo extra");
  banco.Retirada(ncuenta2,hora7,fecha6,2500,"Vámonos de compras");
  banco.Ingreso(ncuenta2,hora8,fecha7,5700,"Me ha tocado la lotería");
  banco.Ingreso(ncuenta1,hora9,fecha8,150,"Otro ingreso");

  banco.SalvarDatos();
}
