/**
  * @file imagen.cpp
  * @brief Fichero de definiciones para insertar la implementación deseada
  * 
  * Incluye la definición correspondiente del fichero imagen.h
  *
  */

#include "imagen.h"

using namespace std;

void Imagen::Crear (int f, int c){
  (*this).img.datos_img=new unsigned char[f*c];
  (*this).img.filas=f;
  (*this).img.columnas=c;
}

void Imagen::Limpiar (){
  delete (*this).img.datos_img;  
}

int Imagen::Filas (){
  return (*this).img.filas;
}

int Imagen::Columnas (){
  return (*this).img.columnas;
}

void Imagen::Set (int i, int j, unsigned char v){
  (*this).img.datos_img[(i*(*this).img.columnas)+j]=v;
}

unsigned char Imagen::Get (int i, int j){
  return (*this).img.datos_img[(i*(*this).img.columnas)+j];
}

bool Imagen::LeerImagen(const char file[]){
  bool res=true;
  int fil,col;
  if (LeerTipoImagen(file, fil, col)==IMG_PGM){
    Crear(fil,col);
    LeerImagenPGM (file, (*this).img.filas, (*this).img.columnas, (*this).img.datos_img);
  }else
    res=false;
  return res;
}

bool Imagen::EscribirImagen(const char file[]){
  bool res;
  if (EscribirImagenPGM (file, (*this).img.datos_img, (*this).img.filas, (*this).img.columnas)){
    Limpiar();
    res=true;
  }
  else
    res=false;
  return res;
}
