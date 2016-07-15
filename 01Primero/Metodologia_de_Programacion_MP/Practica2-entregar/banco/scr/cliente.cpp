#include "finanzas.h"

using namespace std;

Cliente LeerCliente() {
  Cliente cli;
  getline(cin,cli.nif);
  getline(cin,cli.nombre);
  getline(cin,cli.apellidos);
  getline(cin,cli.direccion);
  return cli;
}

void EscribirCliente(const Cliente& cli) {
  cout << cli.nif << endl;
  cout << cli.nombre << endl;
  cout << cli.apellidos << endl;
  cout << cli.direccion << endl;
}