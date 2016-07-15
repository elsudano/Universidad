/**
  * @file Transformar.cpp
  * @brief Fichero donde se definen las funciones de los programas principales
  *
  * Incluye las definiciones de las dos funciones principales para los programas
  * negativo y desplazar.
  *
  */
#ifndef _IMAGEN_H_
#define _IMAGEN_H_
#include "imagen.h" // Aquí se define el valor de CUAL_COMPILO.
#endif

bool negativo(const char *file_name_in, const char *file_name_out){
  Imagen imagen;
  bool res=true;
  if (LeerImagen(file_name_in,imagen)){
    for(int fil=0;fil<Filas(imagen);fil++)
      for(int col=0;col<Columnas(imagen);col++)
	Set (imagen, fil, col, 255-Get(imagen,fil,col));
    EscribirImagen(file_name_out,imagen);
    Destruir(imagen);
  }else{
    res=false;
  }
  return res;
}

bool desplazar(const int& bits, const char *file_name_in, const char *file_name_out){
  Imagen imagen;
  bool res=true;
  unsigned char tmp;
  if (LeerImagen(file_name_in,imagen)){
    for(int fil=0;fil<Filas(imagen);fil++){
      for(int col=0;col<Columnas(imagen);col++){
	tmp=Get(imagen,fil,col);
	tmp=tmp<<bits;
	Set (imagen, fil, col, tmp);
      }
    }
    EscribirImagen(file_name_out,imagen);
    Destruir(imagen);
  }else{
    res=false;
  }
  return res;
}