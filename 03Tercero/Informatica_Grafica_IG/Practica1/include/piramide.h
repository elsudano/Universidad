#ifndef PIRAMIDE_H
#define PIRAMIDE_H

#include "triangulos.h"

//*************************************************************************
// clase piramide
//*************************************************************************
class _piramide : public _triangulos3D {
public:
_piramide(float tam=0.5, float al=0.75);
};

#if defined(VER1)
#include "ver1/piramide.hxx"
#elif defined(VER2)
#include "ver2/piramide.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* PIRAMIDE_H */
