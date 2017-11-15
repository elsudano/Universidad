#ifndef COMPOSICION_H
#define COMPOSICION_H

#include "objeto3D.h"

//*************************************************************************
// clase Rotación
//*************************************************************************
class _composicion{
private:
const float max_giro = 45.0;
const float min_giro = -45.0;
const float max_elev = 20.0;
const float min_elev = -5.0;

int size_of_dot;
int size_of_line;
float r,g,b;
char fichero[40];
_objeto3D *parte_cuerpo;
float giro_cintura,eleva_hombro,eleva_brazo;

void cabeza(int mode);
void cuerpo(int mode);
void antebrazoderecho(int mode);
void brazoderecho(int mode);
void antebrazoizquierdo(int mode);
void brazoizquierdo(int mode);
void troncoinferior(int mode);
void dibuja(int mode);

public:
_composicion();
void componer(int mode);
void set_giro_cintura(float grados);
float get_giro_cintura();
void set_eleva_hombro(float cm);
float get_eleva_hombro();
void set_eleva_brazo(float cm);
float get_eleva_brazo();
};

#if defined(VER1)
#include "ver1/composicion.hxx"
#elif defined(VER2)
#include "ver2/composicion.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* COMPOSICION_H */
