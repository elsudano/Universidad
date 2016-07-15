// **************************************************************
// *** FECHA
// **************************************************************
#ifndef _FECHA_
#define _FECHA_

using namespace std;

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

    // Modificado#include <cassert>r
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

Fecha LeerFecha();
void EscribirFecha(Fecha f);
#endif