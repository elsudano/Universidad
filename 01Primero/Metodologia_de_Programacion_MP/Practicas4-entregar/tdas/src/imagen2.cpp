/**
  * @file imagen2.cpp
  * @brief Fichero de definiciones para insertar la implementación deseada
  * 
  * Incluye la definición correspondiente dependiendo de la selección en imagen.h
  *
  */

void Crear (Imagen& img, int f, int c){
  img.filas=f;
  img.columnas=c;
  img.p_vector=new unsigned char *[f];
  for(int a=0;a<f;a++){
    unsigned char *fila=new unsigned char [c];
    img.p_vector[a]=fila;
  }
}

int Filas (const Imagen& img){
  return img.filas;
}

int Columnas (const Imagen& img){
  return img.columnas;
}

void Set (Imagen& img, int i, int j, unsigned char v){
  img.p_vector[i][j]=v;
}

unsigned char Get (const Imagen& img, int i, int j){
  return img.p_vector[i][j];
}

void Destruir (Imagen& img){
  for(int a=0;a<img.filas;a++){
    delete[] img.p_vector[a];
  }
  delete[] img.p_vector;
}

bool LeerImagen(const char file[], Imagen& img){
  bool res=true;
  int fil,col;
  if (LeerTipoImagen(file, fil, col)==1){
    img.filas=fil;
    img.columnas=col;
    unsigned char *aux=new unsigned char [img.filas*img.columnas];
    Crear(img,img.filas,img.columnas);
    LeerImagenPGM (file, img.filas, img.columnas, aux);
    for(int f=0;f<img.filas;f++)
      for(int c=0;c<img.columnas;c++)
	Set(img,f,c,aux[(f*img.columnas)+c]);
    delete aux;
  }else
    res=false;
  return res;
}

bool EscribirImagen(const char file[], const Imagen& img){
  bool res;
  unsigned char *aux=new unsigned char [img.filas*img.columnas];
  for(int f=0;f<img.filas;f++)
    for(int c=0;c<img.columnas;c++)
      aux[(f*img.columnas)+c]=Get(img,f,c);
  if (EscribirImagenPGM (file, aux, img.filas, img.columnas)){
    res=true;
    delete aux;
  }else
    res=false;
  return res;
}
