#ifndef DORAEMON_H
#define DORAEMON_H

#include "objeto3D.h"

//*************************************************************************
// clase Rotación
//*************************************************************************
class _doraemon : public _objeto3D {
private:
int mode_of_paint;
int size_of_dot;
int size_of_line;

public:
_doraemon();
void cabeza();
void cuerpo();
void antebrazoderecho();
void brazoderecho();
void antebrazoizquierdo();
void brazoizquierdo();
void troncoinferior();
void dibuja(float r, float g,float b, char nombre_fichero[]);
void componer(int modo);
};

#if defined(VER1)
#include "ver1/composicion.hxx"
#elif defined(VER2)
#include "ver2/composicion.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* DORAEMON_H */
