#include "finanzas.h"

using namespace std;

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
