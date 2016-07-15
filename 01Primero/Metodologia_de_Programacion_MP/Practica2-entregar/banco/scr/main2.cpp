// **************************************************************
// *** MAIN 2
// **************************************************************

#include "finanzas.h"

using namespace std;

// Cargar un banco y hacer un listado de clientes
int main() {
  Banco banco;

  banco.CargarDatos();

  vector<Cliente> cli;
  cli = banco.Titulares();
  cout << "********************" << endl;
  cout << "Clientes del banco: " << endl;
  for (int i=0; i<(int)cli.size(); i++) {
    cout << "  NIF : " << cli.at(i).nif << endl;
    cout << "    Nombre    : " << cli.at(i).apellidos << ", " << cli.at(i).nombre << endl;
    cout << "    Dirección : " << cli.at(i).direccion << endl;
    vector<Cuenta> cuentas;
    cuentas = banco.CuentasCliente(cli.at(i).nif);
    cout << "    Cuentas asociadas:" << endl;
    for (int j=0; j<(int)cuentas.size(); j++) {
      cout << "       NC   : " << cuentas.at(j).GetNumeroCuenta() << endl;
      cout << "       Saldo: " << cuentas.at(j).Saldo() << endl;
    }
  }

}