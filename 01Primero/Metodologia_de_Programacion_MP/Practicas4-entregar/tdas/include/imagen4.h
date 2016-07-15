/**
  * @file imagen4.h
  * @brief Fichero de cabeceras para definir todas las funciones de nuestro interfaz.
  * 
  * Incluye la definición correspondiente dependiendo de la selección en imagen.h
  *
  */

#ifndef _IMAGENES_H_
#define _IMAGENES_H_
#include "imagenES.h" // Aquí es donde se encuentran las funciones para manejar las imagenes.
#endif

/**
  * @brief Tipo de Dato Celda.
  * 
  * Con este tipo de dato guardaremos una fila de 
  * @param sig Tipo de Dato Celda, parametro de entrada,
  * Posicion del puntero de la siguiente celda.
  * @param p_vector Tipo de Dato Unsigned Char, parametro de entrada,
  * Esto es el buffer donde se guarda los datos de la Imagen.
  * 
  */
struct Celda {
  Celda *sig; ///< Posicion del puntero de la siguiente celda.
  unsigned char *p_vector; ///< Esto es el buffer donde se guarda los datos de la Imagen.
};

/**
  * @brief Tipo de Dato Imagen.
  * 
  * Con este tipo de dato conseguiremos cargar en memoria
  * los datos de una imagen, aparte de la cantidad de columnas.
  * Hay que tener en cuenta que contiene otro tipo de dato Celda.
  * @param columnas Tipo de dato int, parametro de entrada,
  * numero de columnas que tiene nuestra imagen.
  * @param celda Tipo de Dato Celda, parametro de entrada,
  * 
  */
struct Imagen {
  int columnas; ///< Esta son la cantidad de columnas que tiene nuestra imagen.
  Celda *celda; ///< Esta es la celda completa donde se encuentran los datos.
};

/**
  * @brief Crear o reservar la memoria
  * 
  * Con esta funcion crearemos o reservaremos
  * la memoria necesaria para poder almacenar
  * toda la imagen en memoria.
  * @param img Tipo de Dato Imagen, parametro de entrada,
  * aqui es donde se van a guardar los datos de la imagen.
  * @param f Tipo de Dato int, parametro de entrada,
  * cantidad de filas de la imagen.
  * @param c Tipo de Dato int, parametro de entrada,
  * cantidad de columnas de la imagen.
  * 
  */
void Crear (Imagen& img, int f, int c); // Reserva recursos

/**
  * @brief Cuantas filas tiene nuestra imagen
  * 
  * Con esta función conseguiremos saber cuantas
  * filas tiene nuestra imagen.
  * 
  * @return int numero de filas
  */
int Filas (const Imagen& img); // Devuelve el número filas de m

/**
  * @brief Cuantas columnas tiene nuestra imagen
  * 
  * Con esta función conseguiremos saber cuantas
  * columnas tiene nuestra imagen.
  * 
  * @return int numero de columnas
  */
int Columnas (const Imagen& img); // Devuelve el número columnas de m

/**
  * @brief Setea el valor de img con el valor de v en la posiciones de i,j
  * 
  * Con esta funcion pondremos el valor de v en la
  * posicion que indique i(filas) junto con j(columnas)
  * dentro de img.
  * @param img Tipo de Dato Imagen, parametro de entrada,
  * aqui es donde se van a guardar los datos de la imagen.
  * @param i Tipo de Dato int, parametro de entrada,
  * cantidad de filas de la imagen.
  * @param j Tipo de Dato int, parametro de entrada,
  * cantidad de columnas de la imagen.
  * @param v Tipo de Dato unsigned char, parametro de entrada,
  * valor que queremos escribir en la imagen.
  * 
  */
void Set (Imagen& img, int i, int j, unsigned char v); // Hace img(i,j)=v

/**
  * @brief Muestra el valor de img que corresponde a i,j
  * 
  * Con esta funcion veremos cual es el valor
  * que se guarda en img dentro del punto i,j (filas,columnas)
  * @param img Tipo de Dato Imagen, parametro de entrada,
  * aqui es donde se van a guardar los datos de la imagen.
  * @param i Tipo de Dato int, parametro de entrada,
  * cantidad de filas de la imagen.
  * @param j Tipo de Dato int, parametro de entrada,
  * cantidad de columnas de la imagen.
  * @return Tipo de Dato unsigned char,
  * Devuelve el contenido del punto indicado por i,j
  * 
  */
unsigned char Get (const Imagen& img, int i, int j); // Devuelve img(i,j)

/**
  * @brief Eliminar toda la memoria dinamica.
  * 
  * Con esta funcion eliminaremos todas
  * las reservas de memoria dinamica.
  * @param img Tipo de Dato Imagen, parametro de entrada,
  * aqui es donde se van a guardar los datos de la imagen.
  * 
  */
void Destruir (Imagen& img); // Libera recursos de m

/**
  * @brief Leemos la imagen y la metemos en memoria
  * 
  * con esta funcion cargaremos en memoria los datos de la imagen.
  * @param img Tipo de Dato Imagen, parametro de entrada,
  * aqui es donde se van a guardar los datos de la imagen.
  * @param file Tipo de Dato unsigned char[], parametro de entrada,
  * Esta es la cadena en donde se encuentra el fichero de enrada.
  * @return Tipo de Dato bool,
  * Devuelve verdadero si se ha leido correctamente el fichero.
  * 
  */
bool LeerImagen(const char file[], Imagen& img); // Carga imagen file en img

/**
  * @brief Grabamos lo que tenemos en memoria a un fichero
  * 
  * con esta funcion pasaremos todo lo que tenemos
  * en memoria a un fichero en disco.
  * @param img Tipo de Dato Imagen, parametro de entrada,
  * aqui es donde se van a guardar los datos de la imagen.
  * @param file Tipo de Dato unsigned char[], parametro de entrada,
  * Esta es la cadena en donde queremos que se grabe el fichero.
  * @return Tipo de Dato bool,
  * Devuelve verdadero si se ha escrito correctamente el fichero.
  * 
  */
bool EscribirImagen(const char file[], const Imagen& img); //Salva img en file