#ifndef REVOLUCION_X_H
#define REVOLUCION_X_H

#include "objeto3D.h"

//*************************************************************************
// clase Rotación
//*************************************************************************
class _revolucion_x : public _objeto3D {
private:
vector<_vertex3f> puntos_perfil;
void caras_objeto(int cv, int po);
void tapas_objeto(int cv, int po);

public:
_revolucion_x();
void leer_objeto(char *archivo);
bool in_use();
void revolucion(int caras, bool con_tapas);
void print_puntos_perfil();
};

#if defined(VER1)
#include "ver1/revolucion_x.hxx"
#elif defined(VER2)
#include "ver2/revolucion_x.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* REVOLUCION_X_H */
