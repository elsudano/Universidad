#ifndef LUZ_H
#define LUZ_H

using namespace std;

// Constantes que indican que tipo de luz podemos usar
#define LUZ_FOCO 1
#define LUZ_DIFUSA 2

//*************************************************************************
// clase Luz
// En esta clase tienes que poner la forma en kla que se generan las luces
//
//*************************************************************************
class _luz {
private:
// indicado en R,G,B,A
GLfloat componente_ambiental[4];
// indicado en R,G,B,A
GLfloat componente_difusa[4];
// indicado en R,G,B,A
GLfloat componente_especular[4];
// el cuarto elemento indica si es direccional o puntual
// se pone como un array por que es lo que usan las funciones de OpenGL
GLfloat posicion[4];
// Estos son la posición en grados de un circunferencia imaginaria al
// rededor del eje de coordenadas Y
GLfloat angulo_giro;
// la posición de los 3 ejes X,Y,Z
GLfloat direccion[3];
// exponente de un cono de luz.
GLfloat exponente;
// obturador de luz ángulo de corte de un cono de luz.
GLfloat obturador_luz;
// numero de luz
GLenum num_luz;
// tipo de luz
GLint tipo_luz;
// indica el estado de la luz, las luces por defecto están apagadas
bool encendida_apagada = false;

public:
_luz(int num_luz);
void set_posicion(_vertex4f posicion);
_vertex4f get_posicion();
void set_modo(int modo); // posibilidades Ambiental, Foco
void set_obturador_foco(GLfloat foco);
GLfloat get_obturador_foco();
void interruptor();
};

#if defined(VER1)
#include "ver1/luz.hxx"
#elif defined(VER2)
#include "ver2/luz.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* LUZ_H */
