/**
  * @file imagen3.cpp
  * @brief Fichero de definiciones para insertar la implementación deseada
  * 
  * Incluye la definición correspondiente dependiendo de la selección en imagen.h
  *
  */

void Crear (Imagen& img, int f, int c){
  img.filas=f;
  img.columnas=c;
  img.p_vector=new unsigned char *[f];
  img.p_vector[0]=new unsigned char[f*c];
  for (int a=1;a<f;a++)
    img.p_vector[a]=img.p_vector[a-1]+c;
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
  delete img.p_vector[0];
  delete img.p_vector;
}

bool LeerImagen(const char file[], Imagen& img){
  bool res=true;
  int fil,col;
  if (LeerTipoImagen(file, fil, col)==1){
    Crear(img,fil,col);
    LeerImagenPGM (file, img.filas, img.columnas, *img.p_vector);
  }else
    res=false;
  return res;
}

bool EscribirImagen(const char file[], const Imagen& img){
  bool res;
  if (EscribirImagenPGM (file, *img.p_vector, img.filas, img.columnas))
    res=true;
  else
    res=false;
  return res;
}
