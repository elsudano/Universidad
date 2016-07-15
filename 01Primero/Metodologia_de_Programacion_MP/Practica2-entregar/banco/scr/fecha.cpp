#include "finanzas.h"

using namespace std;

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
