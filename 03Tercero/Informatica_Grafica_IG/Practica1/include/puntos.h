//**************************************************************************
// Práctica 1 usando objetos
//**************************************************************************

#include <vector>
#include <GL/gl.h>
#include "vertex.h"
#include <stdlib.h>

using namespace std;

const float AXIS_SIZE=5000;

//*************************************************************************
// clase punto
//*************************************************************************
class _puntos3D {
public:
	vector<_vertex3f> vertices;

	_puntos3D();
	void    draw_puntos(float r, float g, float b, int grosor);
};
