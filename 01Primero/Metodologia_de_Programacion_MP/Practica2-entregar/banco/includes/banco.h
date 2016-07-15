// **************************************************************
// *** BANCO
// **************************************************************
#ifndef _BANCO_
#define _BANCO_

#ifndef _CUENTA_
#define _CUENTA_
#include "cuenta.h"
#endif

#ifndef _CLIENTE_
#define _CLIENTE_
#include "cliente.h"
#endif

using namespace std;

struct Titularidad {
  string nif;
  string ncuenta;
};

class Banco {
  private:
    vector<Cliente> clientes;
    vector<Cuenta> cuentas;
    vector<Titularidad> relacioncc;

    int BuscaCliente(const string& nif) const;
    int BuscaCuenta(const string& ncuenta) const;
    int BuscaPar(const string& nif, const string& ncuenta) const;

  public:

    // Si el cliente no existe lo añade a la lista
    // Si el cliente ya existe ... no lo añade: modifica sus datos
    // Si la cuenta ya existe lo añade como titular
    // Si la cuenta no existe: la crea
    void NuevoCC(const Cliente& cli, const string& ncuenta);

    // Añade nuevo titular a una cuenta
    // Debe verificar que ambos existen
    //void NuevoTitular(string nif, string cuenta);  // Con NuevoCliente sería suficiente?

    // Consultores
    vector<Cliente> Titulares(const string& ncuenta="") const;  // Si =="" entonces listado completo
    Cliente GetCliente(const string& nif) const;

    vector<Cuenta> CuentasCliente(const string& nif="") const;  // Si =="" entonces listado completo
    Cuenta GetCuenta(const string& ncuenta) const;
    bool ExisteCuenta(const string& ncuenta) const;

    // Consultores de una cuenta (no son estrictamente necesarios si tenemos GetCuenta)
    // double Saldo(string ncuenta);

    // Modificadores de cuentas
    void Ingreso (const string& ncuenta, const Hora& h, const Fecha& f, double cant, const string& motivo="");
    void Retirada (const string& ncuenta, const Hora& h, const Fecha& f, double cant, const string& motivo="");


    // bool InsertaCliente(Cliente cli);  // Añade o modifica según si estaba o no
    // void BorraCliente(string nif);     // Integridad referencial ?

    // bool InsertaCuenta() // ???
    // void BorraCuenta(string ncuenta);   // Integridad referencial ?

    // Para solucionar la integridad referencial quizá en lugar de borrar
    // podríamos "desactivar" (clientes y cuentas)

    void CargarDatos();
    void SalvarDatos() const;
};
#endif