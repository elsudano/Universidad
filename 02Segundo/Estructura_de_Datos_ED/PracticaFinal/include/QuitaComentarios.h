/* 
 * File:   QuitaComentarios.h
 *
 * Created on 12 de diciembre de 2015, 15:21
 */

#ifndef QUITACOMENTARIOS_H
#define QUITACOMENTARIOS_H

using namespace std;

/**
 * @brief Función auxiliar que sirve para quitar los comentarios que hay en los 
 * ficheros de configuración
 * @param in es un flujo de entrada que utilizaremos para quitar lo comentarios
 */

void QuitaComentarios(ifstream &in);

#if defined(VER1)
#include "ver1/QuitarComentarios.hxx"
#elif defined(VER2)
#include "ver2/QuitarComentarios.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* QUITACOMENTARIOS_H */

