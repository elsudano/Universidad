#ifndef DORAEMON_H
#define DORAEMON_H

#include "objeto3D.h"

//*************************************************************************
// clase Rotación
//*************************************************************************
class _doraemon : public _objeto3D {
private:

public:
_doraemon();
};

#if defined(VER1)
#include "ver1/doraemon.hxx"
#elif defined(VER2)
#include "ver2/doraemon.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* DORAEMON_H */
