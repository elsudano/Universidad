#ifndef OBJETO3D_H
#define OBJETO3D_H

#include "triangulos3D.h"
#include "leer_fichero_ply.h"

//*************************************************************************
// clase Rotación
//*************************************************************************
class _objeto3D : public _triangulos3D {
private:

public:
_objeto3D();
void leer_objeto(char *archivo);
void escribir_objeto(char *archivo);
bool in_use();
void clear();
};

#if defined(VER1)
#include "ver1/objeto3D.hxx"
#elif defined(VER2)
#include "ver2/objeto3D.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* OBJETO3D_H */
