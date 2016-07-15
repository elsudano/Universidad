/**
  * @file codificar.cpp
  * @brief Fichero con definiciones para ocultar y revelar el mesaje.
  *
  * Permite poder ocultar y revelar el mensaje que queramos
  * siempre teniendo unas cuantas limitaciones.
  * El mensaje tiene que tener un maximo de XX caracteres
  *
  */

#include <iostream>
#include <stdlib.h>
#include "codificar.h"

using namespace std;

void Codificar::pausa(){
  cout << endl << "Por favor presione <Enter> para continuar";
  //cin.get();
  cin.ignore(255, '\n');  // rechazar caracteres introducidos antes de <Enter>
}

int Codificar::longitud(const char cadena[]){
  int i=0;
  while (cadena[i]!='\0')
    i++;
  return i;
}

int Codificar::verfilas(){
  return (*this).filas;
}

int Codificar::vercolumnas(){
  return (*this).columnas;
}

char Codificar::poner_a_0 (char dato){
  return char(dato)&254;
}

char Codificar::poner_a_1 (char dato){
  return char(dato)|1;
}

void Codificar::verbits(char dato){
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

void Codificar::codificar(){
  (*this).filas=0;
  (*this).columnas=0;
  (*this).tipo=IMG_DESCONOCIDO;
}

TipoError Codificar::ocultar() {
  TipoError error=FAULT;
  int mascara=1,count;
  if ((*this).tipo==IMG_PGM){
    for (int ct=0;(*this).datos_msg[ct]!='\0';ct++){
      for (int cb=0;cb<8;cb++){
	mascara=1 << cb;
	char bit=(*this).datos_msg[ct]&mascara;
	if (bit==0){
	  (*this).datos_img[(ct*8)+(7-cb)]=poner_a_0((*this).datos_img[(ct*8)+(7-cb)]);
	}else{
	  (*this).datos_img[(ct*8)+(7-cb)]=poner_a_1((*this).datos_img[(ct*8)+(7-cb)]);
	}
      }
      count=ct;
    }
    
  /**
    * @file codificar.cpp
    * @brief Poner el Final de la Cadena C.
    *
    * Estas lineas es para poner el Final del Mensaje
    * con ellas conseguimos que todos los bits esten
    * a cero de esa manera el valor es un INT en vez
    * de un CHAR y asi es como se finaliza una cadena c.
    */
    count=count+1;
    for (int c0=0;c0<8;c0++){
      (*this).datos_img[(count*8)+c0]=poner_a_0((*this).datos_img[(count*8)+c0]);
    }
    error=OK;
  }else if ((*this).tipo==IMG_PPM){

    error=OK;
  }else{
    cout << "Imagen Desconocida" << endl;
    error=IMG_UNKNOWN;
  }
  
  if ((*this).tipo==IMG_PGM){
    EscribirImagenPGM((*this).new_file_name,(*this).datos_img,(*this).filas,(*this).columnas);
    error=OK;  
  }else if ((*this).tipo==IMG_PPM){
    EscribirImagenPPM((*this).new_file_name,(*this).datos_img,(*this).filas,(*this).columnas);
    error=OK;
  }else{
    cout << "Imagen Desconocida" << endl;
    error=IMG_UNKNOWN;
  }
  return error;
}

TipoError Codificar::revelar(){
  TipoError error=FAULT;
  if ((*this).tipo==IMG_PGM){
    int ultimo_bit,caracter=255;
    for (int ct=0;ct<((*this).filas*(*this).columnas);ct++){
      for (int cb=0;cb<8;cb++){
	ultimo_bit=(*this).datos_img[(ct*8)+cb]&1;
	if (ultimo_bit==0){
	  caracter=caracter << 1;
	}else{
	  caracter=caracter << 1;
	  caracter=poner_a_1(caracter);
	}
      }
      if (caracter!='\0' && ct < MAXMSG)
	(*this).datos_msg[ct]=caracter;
    }
    cout << (*this).datos_msg << endl;
    error=OK;
  }else if ((*this).tipo==IMG_PPM){

    error=OK;
  }else{
    cout << "Imagen Desconocida" << endl;
    error=IMG_UNKNOWN;
  }
  return error;
}

TipoError Codificar::leer_datos(char opcion){
  TipoError error=FAULT;
  //char opcion;
  cout << "________ Mensaje Oculto en una Imágen ________" << endl
       << "----------------------------------------------" << endl << endl
       << "Introduzca la ruta de la imagen a usar: ";
  cin.getline ((*this).file_name,MAXARCHIVO);
  
  //cout << "Que desea hacer (O) ocultar o (R) revelar un mensaje: ";
  //cin >> opcion;
  //cin.get();
  //while (opcion!='o' && opcion!='O' && opcion!='r' && opcion!='R'){
  //  cout << "Opcion Incorrecta, intentelo denuevo." << endl;
  //  cin >> opcion;
  //  cin.get();
  //}
  
  if (opcion=='o' || opcion=='O'){
    cout << "Introduzca la ruta de la nueva imagen: ";
    cin.getline ((*this).new_file_name,MAXARCHIVO);
    cout << endl << "Introduzca el mensaje a ocultar: ";
    cin.getline ((*this).datos_msg,MAXMSG);
    while (longitud((*this).datos_msg)>MAXMSG){
      cout << "Mensaje demasiado largo, introduzca uno mas corto";
      cin.getline ((*this).datos_msg,MAXMSG);
    }
  }else if (opcion=='r' || opcion=='R'){
    cout << "El Mensaje Oculto que hay en la Imagen es: " << endl;
  }else
    cout << "Elija una opcion adecuada para realizar" << endl;
    
  if (longitud((*this).file_name)!=0){
    (*this).tipo=LeerTipoImagen((*this).file_name,(*this).filas,(*this).columnas);
    if ((*this).tipo==IMG_PGM){
      if (LeerImagenPGM ((*this).file_name, (*this).filas, (*this).columnas, (*this).datos_img))
	//cout << (*this).verfilas() << endl << (*this).vercolumnas() << endl;
	error=OK;
      else
	error=NO_READ;
    }else if ((*this).tipo==IMG_PPM){
      if (LeerImagenPPM ((*this).file_name, (*this).filas, (*this).columnas, (*this).datos_img))
	//cout << (*this).verfilas() << endl << (*this).vercolumnas() << endl;
      error=OK;
      else
	error=NO_READ;
    }else{
      error=IMG_UNKNOWN;
      (*this).tipo=IMG_DESCONOCIDO;
      (*this).filas=0;
      (*this).columnas=0;
    }
  }
  return error;
}






