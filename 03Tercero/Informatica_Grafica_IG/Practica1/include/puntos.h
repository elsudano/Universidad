#ifndef PUNTOS_H
#define PUNTOS_H

//**************************************************************************
// Práctica 1 usando objetos
//**************************************************************************

#include <vector>
#include <GL/gl.h>
#include "vertex.h"
#include <stdlib.h>

using namespace std;

//*************************************************************************
// clase punto
//*************************************************************************
class _puntos3D {
public:
vector<_vertex3f> vertices;

_puntos3D();
void   draw_puntos(float r, float g, float b, int grosor);
};

#if defined(VER1)
#include "ver1/puntos.hxx"
#elif defined(VER2)
#include "ver2/puntos.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* PUNTOS_H */
