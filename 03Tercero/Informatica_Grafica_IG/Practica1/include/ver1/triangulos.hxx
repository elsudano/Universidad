#ifndef TRIANGULOS_H
#define TRIANGULOS_H



#if defined(VER1)
#include "ver1/triangulos.hxx"
#elif defined(VER2)
#include "ver2/triangulos.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* TRIANGULOS_H */
