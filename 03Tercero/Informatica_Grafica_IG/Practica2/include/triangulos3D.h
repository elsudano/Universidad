#ifndef TRIANGULOS3D_H
#define TRIANGULOS3D_H

#include "puntos3D.h"

using namespace std;

//*************************************************************************
// clase triángulo
//*************************************************************************
class _triangulos3D : public _puntos3D {
public:
vector<_vertex3i> caras;

_triangulos3D();
void    draw_aristas(float r, float g, float b, int grosor);
void    draw_solido(float r, float g, float b);
void    draw_solido_colores();
void    draw_solido_ajedrez(float r1, float g1, float b1, float r2, float g2, float b2);
};

#if defined(VER1)
#include "ver1/triangulos3D.hxx"
#elif defined(VER2)
#include "ver2/triangulos3D.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* TRIANGULOS3D_H */
