#include "puntos.h"

using namespace std;

//*************************************************************************
// clase triángulo
//*************************************************************************
class _triangulos3D: public _puntos3D {
public:
	_triangulos3D();
	void    draw_aristas(float r, float g, float b, int grosor);
	void    draw_solido(float r, float g, float b);
	void    draw_solido_ajedrez(float r1, float g1, float b1, float r2, float g2, float b2);

	vector<_vertex3i> caras;
};
