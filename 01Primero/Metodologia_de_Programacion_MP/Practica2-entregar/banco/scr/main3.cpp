// **************************************************************
// *** MAIN 3
// **************************************************************
#include "finanzas.h"

using namespace std;

// Cargar un Banco y probar algunas cosas
int main() {
  Banco banco;

  banco.CargarDatos();

  cout << "***********************" << endl;
  cout << "Existe cuenta 12340443161122334455: " << (banco.ExisteCuenta("12340443161122334455")?"SI":"NO") << endl;
  cout << "Existe cuenta 17268726876838760000: " << (banco.ExisteCuenta("17268726876838760000")?"SI":"NO") << endl;
  cout << endl;

  cout << "***********************" << endl;
  cout << "Titulares de C1: " << endl;
  vector<Cliente> titc;
  titc=banco.Titulares("12340443161122334455");
  for (int i=0; i<(int)titc.size(); i++)
    cout << "   " << titc.at(i).nombre << " " << titc.at(i).apellidos << endl;
  cout << endl;

  cout << "***********************" << endl;
  cout << "Titulares de C2: " << endl;
  titc=banco.Titulares("00043006780000012345");
  for (int i=0; i<(int)titc.size(); i++)
    cout << "   " << titc.at(i).nombre << " " << titc.at(i).apellidos << endl;
  cout << endl;

  cout << "***********************" << endl;
  cout << "Cuentas de cliente 1: " << endl;
  vector<Cuenta> cuent;
  cuent=banco.CuentasCliente("12345678d");
  for (int i=0; i<(int)cuent.size(); i++)
    cout << "   " << cuent.at(i).GetNumeroCuenta() << endl;
  cout << endl;

  cout << "***********************" << endl;
  cout << "Cuentas de cliente 2: " << endl;
  cuent=banco.CuentasCliente("98765432a");
  for (int i=0; i<(int)cuent.size(); i++)
    cout << "   " << cuent.at(i).GetNumeroCuenta() << endl;
  cout << endl;

}