// **************************************************************
// *** CUENTA
// **************************************************************
#ifndef _CUENTA_
#define _CUENTA_

using namespace std;

class Cuenta {
  private:
    string entidad;
    string sucursal;
    string cuenta;
    string dcontrol;
    double saldo;
    vector<Movimiento> movimientos;

    void CalcularDigitosControl();
    void CrearCuenta(string ent, string suc, string cue, const string& ctr);

  public:
    Cuenta() { saldo=0; }   // Para poder LeerCuenta
    // Al crear la cuenta se pone saldo a cero ... asi evitamos problemas despues.
    // Si podemos crear cuenta con un saldo inicial distinto de cero, al volcar
    // los datos a cout y luego leerlos de cin deberiamos guardar tambien este
    // dato para que todo cuadre --> solución: no ponerlo aquí
    Cuenta(const string& ent,const  string& suc,const  string& cue,const  string& ctr);
    Cuenta(const string& ncuenta);
    string GetNumeroCuenta() const   { return entidad+sucursal+dcontrol+cuenta; }

    bool Valida() const  { return dcontrol!=""; }

    double Saldo() const  { return saldo; }
    void Ingreso (const Hora& h, const Fecha& f, double cant, const string& motivo="");
    void Retirada(const Hora& h, const Fecha& f, double cant, const string& motivo="") {
                    assert(saldo>=cant);
                    Ingreso(h,f,-cant,motivo);
                  }

    int NumMovimientos() const   { return (int)movimientos.size(); }
    Movimiento GetMovimiento(int n) const { assert(n>=0 && n<(int)movimientos.size());
                                       return movimientos.at(n); }
    //vector<Movimiento> GetUltimosMovimientos(int num=10);
};

Cuenta LeerCuenta();
void EscribirCuenta(const Cuenta& c);
#endif