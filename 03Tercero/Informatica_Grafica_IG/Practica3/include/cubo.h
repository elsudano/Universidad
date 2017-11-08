#ifndef CUBO_H
#define CUBO_H

#include "objeto3D.h"

//*************************************************************************
// clase cubo
//*************************************************************************
class _cubo : public _objeto3D {
public:
_cubo(float tam=0.5);
};

#if defined(VER1)
#include "ver1/cubo.hxx"
#elif defined(VER2)
#include "ver2/cubo.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* CUBO_H */
