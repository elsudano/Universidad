#ifndef REVOLUCION_H
#define REVOLUCION_H

#include "objeto3D.h"

//*************************************************************************
// clase Rotación
//*************************************************************************
class _revolucion : public _objeto3D {
private:
vector<_vertex3f> puntos_perfil;
void caras_objeto(int cv, int po);
void tapas_objeto(int cv, int po);

public:
_revolucion();
void leer_objeto(char *archivo);
bool in_use();
void revolucion(int caras, bool con_tapas);
void print_puntos_perfil();
};

#if defined(VER1)
#include "ver1/revolucion.hxx"
#elif defined(VER2)
#include "ver2/revolucion.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* REVOLUCION_H */
