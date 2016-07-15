// **************************************************************
// *** HORA
// **************************************************************
#ifndef _HORA_
#define _HORA_

using namespace std;

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

void EscribirHora(const Hora& h);
Hora LeerHora();
#endif