#ifndef TEXTURA_H
#define TEXTURA_H

// Necesario para la manipulación de las imagenes, usa librerias de ImageMagick
#include "CImg.h"
using namespace cimg_library;

//*************************************************************************
// Clase Textura
// Esta clase se encarga de la manipulación de la textura si se requiere
// la apertura del fichero de imagenes, manipulación del mismo y la
// preparación para que se pueda utilizar con OpenGL.
//
//*************************************************************************
class _textura {
private:
std::vector<unsigned char> data;
GLuint textura_id;
CImg<unsigned char> textura;

public:
_textura();
std::vector<unsigned char> cargar_imagen_a_memoria(const char* fichero, int &ancho, int &alto);
GLuint prepara_textura(int id, int &ancho, int &alto, std::vector<unsigned char> datos);
void limpia_textura();
};

#if defined(VER1)
#include "ver1/textura.hxx"
#elif defined(VER2)
#include "ver2/textura.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* TEXTURA_H */
