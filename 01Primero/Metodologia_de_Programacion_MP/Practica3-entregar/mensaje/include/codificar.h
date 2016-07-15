/**
  * @file codificar.h
  * @brief Cabecera principal que contiene las funciones de ocultar / revelar
  * 
  * Cabecera principal que contiene las funciones
  * tanto para ocultar el mensaje en una imagen como
  * para revelear el mensaje de la misma.
  *
  */
#ifndef _CODIFICAR_H_
#define _CODIFICAR_H_
#include "imagenES.h"

/**
  * @brief Variable que define cual sera el tamaño maximo para la cadena de la ruta de la imagen
  * 
  * Longitud maxima para la ruta del fichero de imagen.
  */
#define MAXARCHIVO 160

/**
  * @brief Variable que define cual sera el tamaño maximo que podra tener el mensaje que queremos ocultar.
  * Longitud maxima para el mensaje oculto. Hay que tener en cuenta que este valor tiene
  * que ser el valor que resulte de dividir el tamaño maximo de la imagen entre 8 que
  * son la cantidad de bits que tiene un caracter
  */
#define MAXMSG 20000

/**
  * @brief Variable que define cual sera el tamaño maximo de la imagen a leer.
  * Tamaño maximo para el alto y el largo de la imagen a leer.
  */
#define MAXIMG 1024

/**
  * @brief Tipo de Error
  *
  * Declara una serie de constantes para representar los distintos tipos
  * de imágenes que se pueden manejar.
  *
  * @see ocultar
  * @see revelar
  */
enum TipoError {OK,		///< Sin errores
		BIG_STR,	///< La Cadena a codificar es demaciado grande
		NO_END,		///< La cadena cifrada en la imagen no tiene el digito de control
		NO_READ,	///< La imagen no se ha leido correctamente.
		IMG_UNKNOWN,	///< Fichero desconocido.
		FAULT		///< Fallo desconocido.
	       };

/**
  * @brief Codificar
  *
  * Con esta clase Codificar conseguimos pasar los datos necesarios
  * a cada una de las funciones que necesiten las filas, columnas, mensaje y bytes de la imagen.
  *
  * @see ocultar
  * @see revelar
  */
class Codificar {
  private:
    int filas;					///< Las filas
    int columnas;				///< Las columnas
    TipoImagen tipo;					///< Tipo de la Imagen
    //unsigned char datos_img_pgm[MAXIMG*MAXIMG];	///< Los datos en bruto de la Imagen.    
    unsigned char datos_img[MAXIMG*MAXIMG*3];	///< Los datos en bruto de la Imagen.
    char datos_msg[MAXMSG];			///< La cadena que queremos ocultar
    char file_name[MAXARCHIVO];			///< ruta del fichero de imagen.
    char new_file_name[MAXARCHIVO];		///< ruta del fichero de imagen resultante.
    
    /**
      * @brief Funcion que realiza un pausa en el programa
      *
      * Sirve para realizar una pausa en el programa y permite
      * continuar en el mismo cuando se pulsa la tecla return
      */
    void pausa();
    
    /**
      * @brief Longitud de una cadena de C
      *
      * Sirve para poder saber cuantos caracteres tiene una cadena de C
      * @param cadena Parametro de entrada, que deve tener un valor
      * de vector de char.
      * @return Devuelve 0 si la cadena esta vacia y si contiene
      * caracteres devuelve la cantidad de la misma.
      */
    int longitud(const char cadena[]);
    
    /**
      * @brief cantidad de filas que tiene la imagen
      *
      * Teniendo en cuenta que las imagenes son filas y columnas 
      * de pixeles esta funcion devuelve la cantidad de filas en 
      * la que esta dividida una imagen.
      * 
      * @see Codificar
      */
    int verfilas();
    
    /**
      * @brief Cantidad de columnas que tiene la imagen
      *
      * Teniendo en cuenta que las imagenes son filas y columnas 
      * de pixeles esta funcion devuelve la cantidad de columnas en 
      * la que esta dividida una imagen.
      *
      * @see Codificar
      */
    int vercolumnas();
    
    /**
      * @brief Poner el bit de menor peso a 0
      * 
      * @param dato Parametro de entrada, tipo de dato char,
      * Esta funcion sirve para poner el ultimo bit del caracter a 0
      * @see Codificar
      */
    char poner_a_0 (char dato);
    
    /**
      * @brief Poner el bit de menor peso a 1
      *
      * @param dato Parametro de entrada, tipo de dato char,
      * Esta funcion sirve para poner el ultimo bit del caracter a 1
      * @see Codificar
      */
    char poner_a_1 (char dato);
    
    /**
      * @brief Sirve para poder ver el caracter en numero binario
      *
      * Esta función recorre el Bytes del caracter recibido para imprimir por pantalla su valor binario.
      * 
      * @see Codificar
      */
    void verbits(char dato);
    
  public:
    /**
      * @brief Constructor de la Clase Codificar
      * 
      * Con esta funcion creamos el objeto que 
      * nos ayudara a leer los datos a ocultar el 
      * mensaje a escribir la imagen y a revelar el
      * mensaje oculto cuando sea necesario.
      * 
      * @see Codificar
      */
    void codificar();
    
    /**
      * @brief Ocultar el mensaje dentro de la Imagen
      *
      * Con esta función realizaremos el proceso de ocultar
      * el mensaje dentro de la imagen.
      * @return Devuelve el tipo de error que coresponda.
      *
      * @see Codificar
      * @see TipoError
      */
    TipoError ocultar();

    /**
      * @brief Revelar el mensaje que hay dentro de la Imagen
      *
      * @return Devuelve el tipo de error que coresponda.
      *
      * @see Codificar
      * @see TipoError
      */
    TipoError revelar();

    /**
      * @brief Lee los datos por teclado, para ocultar el mensaje.
      *
      * Sirve para poder introducir todos los datos al programa, y que nos devuelva el objeto
      * Imagen con los datos necesarios para poder trabajar con imagenes
      * @param opcion Parametro de entrada, tipo de dato char, sirve para
      * decidir quedatos vamos a pedir al usuario, "O" si es para ocultar
      * el mensaje, o "R" si es para revelar el mensaje
      * @return Devuelve un tipo de dato TipoError.
      * 
      * @see Codificar
      * @see TipoError
      */
    TipoError leer_datos(char opcion);
};

#endif

/* Fin Fichero: codificar.h */