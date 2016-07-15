#include "finanzas.h"

using namespace std;

int Banco::BuscaCliente(const string& nif) const
{
  int pos=-1;
  for (int i=0; i<(int)clientes.size() && pos==-1; i++)
    if (clientes.at(i).nif == nif)
      pos = i;
  return pos;
}

int Banco::BuscaCuenta(const string& ncuenta) const
{
  int pos=-1;
  for (int i=0; i<(int)cuentas.size() && pos==-1; i++)
    if (cuentas.at(i).GetNumeroCuenta() == ncuenta)
      pos = i;
  return pos;
}

int Banco::BuscaPar(const string& nif, const string& ncuenta) const
{
  int pos=-1;
  for (int i=0; i<(int)relacioncc.size() && pos==-1; i++)
    if (relacioncc.at(i).nif == nif && relacioncc.at(i).ncuenta == ncuenta)
      pos = i;
  return pos;
}

void Banco::NuevoCC(const Cliente& cli, const string& ncuenta)
{
  Cuenta cue(ncuenta);
  if (cue.Valida()) {
    // Registramos datos de nueva cuenta
    int pos = BuscaCuenta(ncuenta);
    if (pos==-1)   // Si no existe la cuenta la creamos
      cuentas.push_back(cue);

    // Registramos datos de cliente
    pos = BuscaCliente(cli.nif);
    if (pos!=-1) {
      // Actualizar datos
      clientes.at(pos).nombre = cli.nombre;
      clientes.at(pos).apellidos = cli.apellidos;
      clientes.at(pos).direccion = cli.direccion;
      //cout << "El cliente " << cli.nif << " ha sido actualizado" << endl;
    } else {
      // Añadir cliente
      clientes.push_back(cli);
    }

    // Actuar sobre tabla de relaciones
    pos = BuscaPar(cli.nif,ncuenta);
    if (pos==-1) {
      // Si no está ya como titular, lo añadimos
      Titularidad t;
      t.nif = cli.nif;
      t.ncuenta = ncuenta;
      relacioncc.push_back(t);
    }
  }
}

vector<Cliente> Banco::Titulares(const string& ncuenta) const
{
  vector<Cliente> res;
  if (ncuenta=="")
    res=clientes;  // Devolver todas
  else {
    for (int i=0; i<(int)relacioncc.size(); i++) {
      if (relacioncc.at(i).ncuenta==ncuenta) {
        res.push_back(clientes.at(BuscaCliente(relacioncc.at(i).nif)));
      }
    }
  }
  return res;
}

Cliente Banco::GetCliente(const string& nif) const
{
  Cliente cli;
  int pos = BuscaCliente(nif);
  if (pos!=-1)
    cli = clientes.at(pos);
  return cli;
}

vector<Cuenta> Banco::CuentasCliente(const string& nif) const
{
  vector<Cuenta> res;
  if (nif=="")
    res = cuentas;  // Devolver todas
  else
    for (int i=0; i<(int)relacioncc.size(); i++) {
      if (relacioncc.at(i).nif==nif) {
        res.push_back(cuentas.at(BuscaCuenta(relacioncc.at(i).ncuenta)));
      }
    }
  return res;
}

Cuenta Banco::GetCuenta(const string& ncuenta) const
{
  Cuenta cuen;
  int pos = BuscaCuenta(ncuenta);
  if (pos!=-1)
    cuen = cuentas.at(pos);
  return cuen;
}

bool Banco::ExisteCuenta(const string& ncuenta) const
{
  return (BuscaCuenta(ncuenta)!=-1);
}

void Banco::Ingreso(const string& ncuenta, const Hora& h, const Fecha& f, double cant, const string& motivo)
{
  int pos = BuscaCuenta(ncuenta);
  if (pos==-1) {
    cout << "ERROR: Se ha intentado hacer un movimiento en una cuenta inexistente" << endl;
  } else {
    cuentas.at(pos).Ingreso(h,f,cant,motivo);
  }
}

void Banco::Retirada(const string& ncuenta, const Hora& h, const Fecha& f, double cant, const string& motivo)
{
  Ingreso(ncuenta,h,f,-cant,motivo);
}

void Banco::SalvarDatos() const
{
  cout << clientes.size() << endl;
  for (unsigned int i=0; i<clientes.size(); i++)
    EscribirCliente(clientes.at(i));

  cout << cuentas.size() << endl;
  for (unsigned int i=0; i<cuentas.size(); i++)
    EscribirCuenta(cuentas.at(i));

  cout << relacioncc.size() << endl;
  for (unsigned int i=0; i<relacioncc.size(); i++) {
    cout << relacioncc.at(i).nif << endl;
    cout << relacioncc.at(i).ncuenta << endl;
  }
}

void Banco::CargarDatos()
{
  clientes.clear();
  cuentas.clear();
  relacioncc.clear();

  int nclient;
  cin >> nclient;
  cin.get();
  for (int i=0; i<nclient; i++) {
    Cliente cli;
    cli=LeerCliente();
    clientes.push_back(cli);
  }

  int ncuent;
  cin >> ncuent;
  cin.get();
  for (int i=0; i<ncuent; i++) {
    Cuenta cuen;
    cuen=LeerCuenta();
    cuentas.push_back(cuen);
  }

  int nrel;
  cin >> nrel;
  cin.get();
  for (int i=0; i<nrel; i++) {
    Titularidad t;
    getline(cin,t.nif);
    getline(cin,t.ncuenta);
    relacioncc.push_back(t);
  }
}