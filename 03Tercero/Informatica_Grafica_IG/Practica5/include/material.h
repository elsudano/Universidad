#ifndef MATERIAL_H
#define MATERIAL_H

using namespace std;

// Constantes que indican que tipo de material podemos usar
#define PLATA 1
#define OTHER 2

//*************************************************************************
// Clase Material
// Clase que se encarga de manejar los tipos de materiales que puede tener
// un objeto3D, esta clase seguramente vaya muy ligada a la clase luz
//
//*************************************************************************
class _material {
private:
GLfloat ambiental[4];
GLfloat difusa[4];
GLfloat especular[4];
GLfloat brillo;

public:
_material();
void set_ambiental(GLfloat r, GLfloat g, GLfloat b, GLfloat a);
void set_difusa(GLfloat r, GLfloat g, GLfloat b, GLfloat a);
void set_especular(GLfloat r, GLfloat g, GLfloat b, GLfloat a);
void set_brillo(GLfloat b);
GLfloat* get_ambiental();
GLfloat* get_difusa();
GLfloat* get_especular();
GLfloat get_brillo();
void selecionar_material(GLint material);
};

#if defined(VER1)
#include "ver1/material.hxx"
#elif defined(VER2)
#include "ver2/material.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* MATERIAL_H */
