/**
  * @file imagen.h
  * @brief Fichero de cabeceras para definir todas las funciones de nuestro interfaz.
  * 
  * Con este fichero podremos manejar los ficheros de imagenes para leerlos y escribirlos.
  *
  */
#ifndef _IMAGEN_H_
#define _IMAGEN_H_
#include "imagenES.h" // Aquí es donde se encuentran las funciones para manejar las imagenes.

/**
  * @brief Tipo de Dato Imagen.
  * 
  * Con este tipo de dato conseguiremos cargar en memoria
  * los datos de una imagen, aparte de la cantidad de columnas.
  * Hay que tener en cuenta que contiene otro tipo de dato Celda.
  * @param filas Tipo de dato int, parametro de entrada,
  * numero de filas que tiene nuestra imagen.
  * @param columnas Tipo de dato int, parametro de entrada,
  * numero de columnas que tiene nuestra imagen.
  * @param p_vector Tipo de Dato unsigned Char, parametro de entrada,
  * Este es el vector que contiene todos los datos de la imagen.
  * 
  */
struct datos_imagen {
  int filas; ///< numero de filas que tiene nuestra imagen.
  int columnas; ///< numero de columnas que tiene nuestra imagen.
  unsigned char *datos_img; ///< Este es el vector que contiene todos los datos de la imagen.
};

/**
  * @brief Clase Imagen
  * 
  * Con esta clase lo que conseguiremos es poder manipular
  * el tipo de de dato datos_imagen utilizando sus metodos
  * de lectura y escritura de imagenes.
  * 
  * @see Set
  * @see Get
  * @see Filas
  * @see Columnas
  * @see LeerImagen
  * @see EscribirImagen
  * 
  */
class Imagen {
  private:
  datos_imagen img; ///< Este es el paquete de los datos de la imagen en bruto.
  
  /**
    * @brief Crear o reservar la memoria
    * 
    * Con esta funcion crearemos o reservaremos
    * la memoria necesaria para poder almacenar
    * toda la imagen en memoria.
    * @param f Tipo de Dato int, parametro de entrada,
    * cantidad de filas de la imagen.
    * @param c Tipo de Dato int, parametro de entrada,
    * cantidad de columnas de la imagen.
    * 
    */
  void Crear (int f, int c); // Reserva recursos de img

  /**
    * @brief Eliminar toda la memoria dinamica.
    * 
    * Con esta funcion eliminaremos todas
    * las reservas de memoria dinamica.
    * 
    */
  void Limpiar (); // Libera recursos de img

  public:  
  /**
    * @brief Cuantas filas tiene nuestra imagen
    * 
    * Con esta función conseguiremos saber cuantas
    * filas tiene nuestra imagen.
    * 
    * @return int numero de filas
    */
  int Filas (); // Devuelve el número filas de img

  /**
    * @brief Cuantas columnas tiene nuestra imagen
    * 
    * Con esta función conseguiremos saber cuantas
    * columnas tiene nuestra imagen.
    * 
    * @return int numero de columnas
    */
  int Columnas (); // Devuelve el número columnas de img

  /**
    * @brief Setea el valor de img con el valor de v en la posiciones de i,j
    * 
    * Con esta funcion pondremos el valor de v en la
    * posicion que indique i(filas) junto con j(columnas)
    * dentro del vector.
    * @param i Tipo de Dato int, parametro de entrada,
    * cantidad de filas de la imagen.
    * @param j Tipo de Dato int, parametro de entrada,
    * cantidad de columnas de la imagen.
    * @param v Tipo de Dato unsigned char, parametro de entrada,
    * valor que queremos escribir en la imagen.
    * 
    */
  void Set (int i, int j, unsigned char v); // Hace img(i,j)=v

  /**
    * @brief Muestra el valor de img que corresponde a i,j
    * 
    * Con esta funcion veremos cual es el valor
    * que se guarda en img dentro del punto i,j (filas,columnas)
    * @param i Tipo de Dato int, parametro de entrada,
    * cantidad de filas de la imagen.
    * @param j Tipo de Dato int, parametro de entrada,
    * cantidad de columnas de la imagen.
    * @return Tipo de Dato unsigned char,
    * Devuelve el contenido del punto indicado por i,j
    * 
    */
  unsigned char Get (int i, int j); // Devuelve img(i,j)

  /**
    * @brief Leemos la imagen y la metemos en memoria
    * 
    * con esta funcion cargaremos en memoria los datos de la imagen.
    * @param file Tipo de Dato unsigned char[], parametro de entrada,
    * Esta es la cadena en donde se encuentra el fichero de enrada.
    * @return Tipo de Dato bool,
    * Devuelve verdadero si se ha leido correctamente el fichero.
    * 
    */
  bool LeerImagen(const char file[]); // Carga imagen file en img

  /**
    * @brief Grabamos lo que tenemos en memoria a un fichero
    * 
    * con esta funcion pasaremos todo lo que tenemos
    * en memoria a un fichero en disco.
    * @param file Tipo de Dato unsigned char[], parametro de entrada,
    * Esta es la cadena en donde queremos que se grabe el fichero.
    * @return Tipo de Dato bool,
    * Devuelve verdadero si se ha escrito correctamente el fichero.
    * 
    */
  bool EscribirImagen(const char file[]); //Salva img en file
};
#endif