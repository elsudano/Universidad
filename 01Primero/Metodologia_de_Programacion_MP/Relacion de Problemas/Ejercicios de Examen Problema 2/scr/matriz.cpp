#include <iostream>
#include <fstream>
#include "matriz.h"

using namespace std;

Matriz::Matriz(){
  (*this).filas=0;
  (*this).columnas=0;
  (*this).datos=new double[0];
  (*this).datos[0]=0;
}

Matriz::Matriz(int f, int c){
  (*this).filas=f;
  (*this).columnas=c;
  (*this).datos=new double[(*this).filas*(*this).columnas];
  for (int i=0;i<(*this).filas*(*this).columnas;i++)
    (*this).datos[i]=1;
}

Matriz::Matriz(const Matriz &m){
  this->filas=m.filas;
  this->columnas=m.columnas;
  this->datos=new double[(*this).filas*(*this).columnas];
  for (int i=0;i<(*this).filas*(*this).columnas;i++)
    (*this).datos[i]=m.datos[i];
}

Matriz& Matriz::operator=(const Matriz &m){
  if (&m!=this){
    delete[] this->datos;
    this->filas=m.filas;
    this->columnas=m.columnas;
    this->datos=new double[(*this).filas*(*this).columnas];
    for (int i=0;i<(*this).filas*(*this).columnas;i++)
      (*this).datos[i]=m.datos[i];
  }
  return *this;
}

void Matriz::Set(int f, int c, double d){
  (*this).datos[(f*(*this).columnas)+c]=d;
}

double Matriz::Get(int f, int c) const{
  if ((*this).filas>0 && (*this).columnas>0)
    return (*this).datos[(f*(*this).columnas)+c];
}

int Matriz::Leer(const char *nombre){
  return 1;
}

int Matriz::Escribir(const char *nombre) const{
  return 1;
}