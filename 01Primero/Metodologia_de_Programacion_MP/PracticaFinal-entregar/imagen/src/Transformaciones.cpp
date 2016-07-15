/**
  * @file Transformaciones.cpp
  * @brief Fichero de definiciones para las diferentes Transformaciones.
  * 
  * Estos son los algoritmos de transformacion que vamos a
  * implementar en los diferentes programas.
  *
  */

#include "Transformaciones.h"

using namespace std;

Transformacion negativo(){
  Transformacion trans;
  for (int count=0;count<256;count++)
    trans.Set(count,255-count);
  return trans;
}

Transformacion desplazar(int bits){
  Transformacion trans;
  for (int count=0;count<256;count++)
    trans.Set(count,char(count<<bits));
  return trans;
}

Transformacion umbralizar(int cantidad){
  Transformacion trans;
  for (int count=0;count<256;count++){
    if (count<=cantidad)
      trans.Set(count,0);
    else
      trans.Set(count,255);
  }
  return trans;
}

Transformacion brillo(int valor){
  Transformacion trans;
  for (int count=0;count<256;count++){
    if (count+valor<0)
      trans.Set(count,0);
    else if (count+valor>255)
      trans.Set(count,255);
    else
      trans.Set(count,count+valor);
  }
  return trans;
}