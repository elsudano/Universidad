#include <iostream>
#include <cassert>
#include <vector>
#include <string>
#include <iomanip>

using namespace std;

// **************************************************************
// *** CLIENTE
// **************************************************************

struct Cliente {
  string nif;
  string nombre, apellidos;
  string direccion;
};

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

// **************************************************************
// *** HORA
// **************************************************************

class Hora {
  private:
    int hora, minuto, segundo;
  public:
    Hora()                          { Set(0,0,0); }
    Hora(int h, int m, int s)       { Set(h,m,s); }
    void Set(int h, int m, int s);
    int GetHora() const             { return hora; }
    int GetMinuto() const           { return minuto; }
    int GetSegundo() const          { return segundo; }
    bool MenorQue(const Hora& otro) const;
    bool MayorQue(const Hora& otro) const;
    bool IgualQue(const Hora& otro) const;
};

void Hora::Set(int h, int m, int s)
{
  assert(h>=0 && m>=0 && s>=0);
  segundo = s%60;
  m += s/60;
  minuto = m%60;
  h += m/60;
  hora = h%24;
}

bool Hora::MenorQue(const Hora& otro) const
{
  bool result;
  if (GetHora()<otro.GetHora())
    result=true;
  else if (GetHora()>otro.GetHora())
    result=false;
  else
    if (GetMinuto()<otro.GetMinuto())
      result=true;
    else if (GetMinuto()>otro.GetMinuto())
      result=false;
    else
      if (GetSegundo()<otro.GetSegundo())
        result=true;
      else
        result=false;
  return result;
}

bool Hora::IgualQue(const Hora& otro) const
{
  return (GetHora()==otro.GetHora() && GetMinuto()==otro.GetMinuto() && GetSegundo()==otro.GetSegundo());
}

bool Hora::MayorQue(const Hora& otro) const
{
  return (!IgualQue(otro) && !MenorQue(otro));
}

void EscribirHora(const Hora& h)
{
  cout << setfill('0');
  cout << setw(2) << h.GetHora() << " " << setw(2) << h.GetMinuto() << " " << setw(2) << h.GetSegundo();
  cout << setfill(' ');
}

Hora LeerHora()
{
  int h, m, s;
  cin >> h >> m >> s;
  Hora hor(h,m,s);
  return hor;
}

// **************************************************************
// *** FECHA
// **************************************************************

class Fecha {

  private:
    // Día Juliano: Número de días transcurridos desde 4713a.C. a las 12:00
    long int diaj;

    void ConvertirADiaJ(int d, int m, int a);
    void ConvertirAFecha(int &d, int &m, int &a) const;

  public:
    Fecha()                     { diaj=0; }
    Fecha(int d, int m, int a);

    // Consultores
    int GetDia()  const { int d,m,a; ConvertirAFecha(d,m,a); return d; }
    int GetMes()  const { int d,m,a; ConvertirAFecha(d,m,a); return m; }
    int GetAnno() const  { int d,m,a; ConvertirAFecha(d,m,a); return a; }
    bool EsBisiesto() const;
    string DiaSemana() const;

    // Modificador
    bool Set(int d, int m, int a);

    // Operaciones aritméticas con fechas
    long int DiasHasta(const Fecha& f2) const   { return diaj - f2.diaj; }
    void RestarDias(int d)         { diaj -= d; }
    void SumarDias(int d)          { diaj += d; }

    // Comparaciones de fechas
    bool IgualQue(const Fecha& f2)    const                  { return diaj==f2.diaj; }
    bool DistintoQue(const Fecha& f2)  const                 { return diaj!=f2.diaj; }
    bool MayorQue(const Fecha& f2)    const                  { return diaj>f2.diaj; }
    bool MenorQue(const Fecha& f2)    const                  { return diaj<f2.diaj; }
    bool MayorIgualQue(const Fecha& f2)  const               { return diaj>=f2.diaj; }
    bool MenorIgualQue(const Fecha& f2)  const               { return diaj<=f2.diaj; }
};


// ******************************************************************
// Conversión d/m/a -> dj
void Fecha::ConvertirADiaJ(int dia, int mes, int anno)
{
  long int a,a2,m2,b;
  if ((mes==1)||(mes==2)) {
    a2 = anno-1;
    m2 = mes+12;
  } else {
    a2 = anno;
    m2 = mes;
  }
  a = a2/100;
  b = 2-a+a/4;
  diaj = (long int)((int)(365.25*a2)+(int)(30.6001*(m2+1))+b+dia+1720994.5+0.5);
  // El último 0.5 se suma porque el día Juliano comienza a las 12:00AM
}

// ******************************************************************
// Conversión dj -> d/m/a
void Fecha::ConvertirAFecha(int &dd, int &m, int &a) const
{
  long int d;
  if (diaj<2299161)
    d = diaj;
  else {
    long int e = (long int)((diaj-1867216.25)/36524.25);
    d = diaj + 1 + e - e/4;
  }
  long int f = d + 1524;
  long int g = (long int)((f-122.1)/365.25);
  long int h = (long int)(g*365.25);
  long int i = (long int)((f-h)/30.6001);
  dd = f-h-(long int)(i*30.6001);
  m = (i<14) ? (i-1) : (i-13);
  long int l = (m>2) ? (g-4716) : (g-4715);
  a = (l>0) ? l : -l+1;
}

// ******************************************************************
Fecha::Fecha(int d, int m, int a)
{
  ConvertirADiaJ(d,m,a);
  int dd,mm,aa;
  ConvertirAFecha(dd,mm,aa);
  if (dd!=d || mm!=m || aa!=a) {
    cout << "   >>> Cuidado: Se ha creado un objeto con fecha incorrecta <<<" << endl;
  }
}

// ******************************************************************
bool Fecha::EsBisiesto() const
{
  int dd,mm,aa;
  ConvertirAFecha(dd,mm,aa);
  return ((aa%4==0) && ((aa%100!=0)||(aa%400==0)));
}

// ******************************************************************
// Para calcular el día de la semana:
//   http://www.albaiges.com/cronologia/calculodiasemana.htm
string Fecha::DiaSemana() const
{
  int dd,mm,aa;
  ConvertirAFecha(dd,mm,aa);
  int cteds;
  switch (mm) {
    case (1) :
    case (10) : cteds=0;
               break;
    case (2) :
    case (3) :
    case (11) : cteds=3;
               break;
    case (4) :
    case (7) : cteds=6;
               break;
    case (5) : cteds=1;
               break;
    case (6) : cteds=4;
               break;
    case (8) : cteds=2;
               break;
    case (9) :
    case (12) : cteds=5;
               break;
  }
  int d = (dd + cteds + aa%100 + (aa%100)/4 + (aa<2000?0:6)) % 7;
  switch (d) {
    case (0) : return string("Domingo");
    case (1) : return string("Lunes");
    case (2) : return string("Martes");
    case (3) : return string("Miércoles");
    case (4) : return string("Jueves");
    case (5) : return string("Viernes");
    default : return string("Sábado");
  }
}

// ******************************************************************
bool Fecha::Set(int d, int m, int a)
{
  ConvertirADiaJ(d,m,a);
  int dd,mm,aa;
  ConvertirAFecha(dd,mm,aa);
  return (dd==d && mm==m && aa==a);
}

// ******************************************************************
Fecha LeerFecha()
{
  int d, m, a;
  cin >> d >> m >> a;
  Fecha f(d,m,a);
  return f;
}

// ******************************************************************
void EscribirFecha(Fecha f)
{
  cout << setfill('0');
  cout << setw(2) << f.GetDia() << " " << setw(2) << f.GetMes() << " " << setw(4) << f.GetAnno();
  cout << setfill(' ');
}

// **************************************************************
// *** MOVIMIENTO
// **************************************************************

class Movimiento {
  private:
    Hora hora;
    Fecha fecha;
    double cantidad;
    string anotacion;

  public:
    Movimiento()  { cantidad=0; }   // Para poder LeerMovimiento
    Movimiento(Hora h, Fecha f, double c, string a="")
               { fecha=f; hora=h; cantidad=c; anotacion=a; }
    Hora GetHora() const                        { return hora; }
    Fecha GetFecha() const                      { return fecha; }
    double GetCantidad() const                  { return cantidad; }
    string GetAnotacion() const                 { return anotacion; }
};

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

// **************************************************************
// *** CUENTA
// **************************************************************

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

// **************************************************************
// *** BANCO
// **************************************************************

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

// **************************************************************
// *** MAIN 1
// **************************************************************
/*
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

// **************************************************************
// *** MAIN 2
// **************************************************************

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
*/
// **************************************************************
// *** MAIN 3
// **************************************************************

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
/*
// **************************************************************
// *** MAIN 4
// **************************************************************

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
*/