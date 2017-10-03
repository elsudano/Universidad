#ifndef PUNTOS_H
#define PUNTOS_H



#if defined(VER1)
#include "ver1/puntos.hxx"
#elif defined(VER2)
#include "ver2/puntos.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* PUNTOS_H */
