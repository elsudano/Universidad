/**
  * @file transformacion2.h
  * @brief Fichero de cabeceras para definir todas las funciones de nuestro interfaz.
  * 
  * Con este fichero podremos manejar los ficheros de Transformaciones para leerlos y escribirlos.
  *
  */

#ifndef _TRANSFORMACION2_H_
#define _TRANSFORMACION2_H_
#include "imagen.h" // Aquí es donde se encuentran las funciones para manejar las imagenes.

/**
  * @brief Tipo de fichero
  *
  * Con este tipo de dato le decimos a nuestros programas
  * cual es el tipo de fichero queremos guardar en disco
  *
  * @see Transformacion
  */
enum txt_or_bin {
  NO_DEFINIDO, ///< Tipo no definido
  TXT,         ///< Texto
  BIN          ///< Binario
};

/**
  * @brief Clase Transformacion
  *
  * Con esta clase lo que vamos a tener son las diferentes transformaciones
  * que queremos aplicar a nuestras imagenes.
  *
  * @see Imagen
  */
class Transformacion {
  private:
    int *transformacion; ///< este es el vector donde se guardan todos los numeros de la transformación.
    txt_or_bin tipo; ///< Con este char controlamos si la grabacion en el fichero se hace en modo texto o en modo binario.
  public:
    Transformacion(); ///< Constructor por defecto
    Transformacion(Transformacion &t); ///< Constructor de Copia.
    ~Transformacion(); ///< Destructor
    Transformacion& operator=(const Transformacion &t); ///< Funcion de sobrecarga de operador de Igualdad.
    void Set (int pos,int val); ///< Funcion que asigna trasformacion(posicion)=valor
    int Get (int pos); ///< Funcion que devuelve transformacion(posicion)
    bool LeerTrans(const char file[]); ///< Funcion que carga el fichero en el vector de enteros (*this).transformacion.
    bool EscribirTrans(const char file[]); ///< Funcion que guarda el vector de enteros (*this).transformacion en un fichero.
    txt_or_bin Get_tipo(); ///< Con esta Funcion se puede ver el valor del tipo de fichero que queremos guardar.
    txt_or_bin Set_tipo(const char *valor); ///< Con esta Funcion se Setea el valor del tipo de fichero que queremos guardar.
};
Transformacion operator+(Transformacion &tr1, Transformacion &tr2); ///< Funcion de sobrecargada del operador + externa a la clase para poder sumar transformaciones.
#endif 