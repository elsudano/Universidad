#ifndef CUBO_H
#define CUBO_H



#if defined(VER1)
#include "ver1/cubo.hxx"
#elif defined(VER2)
#include "ver2/cubo.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* CUBO_H */
