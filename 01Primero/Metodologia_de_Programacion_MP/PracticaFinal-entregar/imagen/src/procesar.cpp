/**
  * @file procesar.cpp
  * @brief Fichero con definiciones para ocultar, revelar y transformar una imagen.
  *
  * Con las funciones que se encuentran en este cpp conseguiremos realizar 
  * todas las funciones necesarias en la imagen.
  *
  */

#include <iostream>
#include <stdlib.h>
#include "procesar.h"

using namespace std;

bool LeerMensaje(char *mensaje, int max){
  char c;
  bool res=true;
  int i=0;
  while(cin.get(c) && i<max-1){
    mensaje[i]=c;
    i++;
  }
  if (!cin.eof())
    res=false;
  mensaje[i+1]='\0';
  return res;
}

int longitud(const char cadena[]){
  int i=0;
  while (cadena[i]!='\0')
    i++;
  return i;
}

char poner_a_0 (char dato){
  return char(dato)&254;
}

char poner_a_1 (char dato){
  return char(dato)|1;
}

void verbits(char dato){
  int valor, mascara;
  for(int i=7;i>=0;i--){
    mascara=1 << i;
    valor=dato&mascara;
    if(valor == 0)
      cout<<"0";
    else
      cout<<"1";
  }
}

bool ocultar(Imagen& imagen,const char *texto){
  bool res=true;
  int mascara=1,fil=0,col=0,bit;
  for (int cuenta_letras=0;cuenta_letras<longitud(texto)-1;cuenta_letras++){
    for (int cuenta_bits=7;cuenta_bits>=0;cuenta_bits--){
      mascara=1 << cuenta_bits;
      bit=texto[cuenta_letras]&mascara;
      if (bit==0)
	imagen.Set(fil,col,char(poner_a_0(imagen.Get(fil,col))));
      else
	imagen.Set(fil,col,char(poner_a_1(imagen.Get(fil,col))));
      col++;
      if (col==imagen.Columnas()){
	fil++;
      	col=0;
      }
    }
  }
  for (int cuenta_bits=0;cuenta_bits<8;cuenta_bits++)
    imagen.Set(fil,col+cuenta_bits,char(poner_a_0(imagen.Get(fil,col))));
  return res;
}

bool revelar(Imagen& imagen, char *texto){
  bool res=true;
  int simbolo=255,cuenta_bits=0,cuenta_letras=0;
  char ultimo_bit;
  for (int fil=0;fil< imagen.Filas();fil++){
    for (int col=0;col< imagen.Columnas();col++){
      ultimo_bit=imagen.Get(fil,col)&1;
      if (ultimo_bit==0)
	simbolo=simbolo << 1;
      else{
	simbolo=simbolo << 1;
	simbolo=poner_a_1(simbolo);
      }
      cuenta_bits++;
      if (cuenta_bits>7){
	cuenta_bits=0;
	texto[cuenta_letras]=char(simbolo);
	cuenta_letras++;
      }
    }
  }
  texto[++cuenta_letras]='\0';
  return res;
}