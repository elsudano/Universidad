/**
  * @file imagen4.cpp
  * @brief Fichero de definiciones para insertar la implementación deseada
  * 
  * Incluye la definición correspondiente dependiendo de la selección en imagen.h
  *
  */

void Crear (Imagen& img, int f, int c){
  img.columnas=c;
  img.celda=0;
  for (int cf=0;cf<=f;cf++){
    Celda *aux;
    aux=new Celda;
    (*aux).sig=img.celda;
    (*aux).p_vector=new unsigned char [c];
    img.celda=aux;
  }  
}

int Filas (const Imagen& img){
  int count=0;
  Celda *celdaaux;
  celdaaux=img.celda;
  while (celdaaux -> sig!=0){
    count++;
    celdaaux=celdaaux -> sig;
  }
  return count;
}

int Columnas (const Imagen& img){
  return img.columnas;
}

void Set (Imagen& img, int i, int j, unsigned char v){
  int count=0;
  Celda *celdaaux;
  celdaaux=img.celda;
  while (img.celda -> sig!=0){
    if (count==i)
      img.celda -> p_vector[j]=v;
    count++;
    img.celda=img.celda -> sig;
  }
  img.celda=celdaaux;
}

unsigned char Get (const Imagen& img, int i, int j){
  int count=0;
  unsigned char res='0';
  Celda *celdaaux;
  celdaaux=img.celda;
  while (celdaaux -> sig!=0){
    if (count==i)
      res=celdaaux -> p_vector[j];
    count++;
    celdaaux=celdaaux -> sig;
  }
  return res;
}

void Destruir (Imagen& img){
  while (img.celda -> sig!=0){
    Celda *celdaaux;
    celdaaux=img.celda;
    img.celda=img.celda -> sig;
    delete celdaaux;
  }
}

bool LeerImagen(const char file[], Imagen& img){
  bool res=true;
  int fil,col;
  if (LeerTipoImagen(file, fil, col)==1){
    unsigned char *aux=new unsigned char [fil*col];
    Crear(img,fil,col);
    LeerImagenPGM (file, fil, col, aux);
    for (int cfil=0;cfil<fil;cfil++)
      for (int ccol=0;ccol<col;ccol++)
	Set (img,cfil,ccol,aux[(cfil*col)+ccol]);
    delete aux;
  }else
    res=false;
  return res;
}

bool EscribirImagen(const char file[], const Imagen& img){
  bool res=true;
  int fil,col;
  fil=Filas(img);
  col=Columnas(img);
  unsigned char *aux=new unsigned char [fil*col];
  for (int cfil=0;cfil<fil;cfil++)
    for (int ccol=0;ccol<col;ccol++)
      aux[(cfil*col)+ccol]=Get (img,cfil,ccol);
  if (EscribirImagenPGM (file, aux, Filas(img), Columnas(img)))
    delete aux;
  else
    res=false;
  return res;
}
