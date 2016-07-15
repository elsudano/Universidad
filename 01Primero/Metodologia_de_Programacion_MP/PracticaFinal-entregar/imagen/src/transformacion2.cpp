/**
  * @file Transformacion2.cpp
  * @brief Fichero de definiciones para insertar la implementación deseada
  *
  * Permite modificar la implementación mediante un número del 1 a 2
  * Modificando la variable CUAL_COMPILO podremos compilar
  * de que manera usaremos nuestra representacion a la hora 
  * de tratar la imagen de entrada del programa.
  *
  */

#include <string.h>
#include <fstream>
#include "transformacion2.h"

using namespace std;

Transformacion::Transformacion(){
  transformacion = new int[256];
  for (int count=0;count<256;count++)
    (*this).transformacion[count]=0;
  (*this).tipo=NO_DEFINIDO;
}

Transformacion::Transformacion(Transformacion &t){
  tipo=t.tipo;
  transformacion=new int[256];
  for (int count=0;count<256;count++)
    transformacion[count]=t.transformacion[count];
}

Transformacion::~Transformacion(){
  delete[] transformacion;
  //delete tipo;
}

Transformacion& Transformacion::operator=(const Transformacion &t){
  if (&t!=this){
    delete[] this->transformacion;
    this->tipo=t.tipo;
    this->transformacion=new int[256];
    for (int count=0;count<256;count++)
      this->transformacion[count]=t.transformacion[count];
  }
  return *this;
}

void Transformacion::Set (int pos,int val){
  (*this).transformacion[pos]=val;
}

int Transformacion::Get (int pos){
  return (*this).transformacion[pos];
}

bool Transformacion::LeerTrans(const char file[]){
  bool res=true;
  char tmp;
  ifstream fichero(file,ios::in|ios::binary);
  if (fichero){
    char cad_magica[8];
    fichero.read(reinterpret_cast<char*>(cad_magica),8);
    cad_magica[8]='\0';
    while (tmp!='\n')
      tmp=fichero.get();
    if (!strcmp(cad_magica,"MP-TRF-T")){
      for (int valor=0;!fichero.eof();valor++)
	fichero >> (*this).transformacion[valor];
    }else if (!strcmp(cad_magica,"MP-TRF-B")){
      fichero.read(reinterpret_cast<char*>((*this).transformacion),sizeof(int)*256);
    }else
      res=false;
  }else
    res=false;
  return res;
}

bool Transformacion::EscribirTrans(const char file[]){
  bool res=true;
  ofstream fichero(file,ios::out|ios::binary);
  if (fichero){
    if ((*this).tipo==TXT){
      char cad_magica[]={'M','P','-','T','R','F','-','T',' ','2','5','6','\0'};
      fichero << cad_magica << endl;
      for (int valor=0;valor<256;valor++)
	fichero << Get(valor) << endl;
    }else if ((*this).tipo==BIN){
      char cad_magica[]={'M','P','-','T','R','F','-','B',' ','2','5','6','\0'};
      fichero << cad_magica << endl;
      fichero.write(reinterpret_cast<char*>((*this).transformacion),sizeof(int)*256);
    }else
      res=false;
  }else
    res=false;
  fichero.close();
  return res;
}

txt_or_bin Transformacion::Get_tipo(){
  txt_or_bin res=NO_DEFINIDO;
  if ((*this).tipo==TXT)
    res=TXT;
  else if ((*this).tipo==BIN)
    res=BIN;
  else
    res=NO_DEFINIDO;
  return res;
}

txt_or_bin Transformacion::Set_tipo(const char *valor){
  if (!strcmp(valor,"t") || !strcmp(valor,"T"))
    (*this).tipo=TXT;
  else if (!strcmp(valor,"b") || !strcmp(valor,"B"))
    (*this).tipo=BIN;
  else
    (*this).tipo=NO_DEFINIDO;
  return (*this).tipo;
}

Transformacion operator+(Transformacion &tr1, Transformacion &tr2){
  Transformacion trans_aux;
  for (int count=0;count<256;count++)
    trans_aux.Set(count,tr2.Get(tr1.Get(count)));
  return trans_aux;
}
