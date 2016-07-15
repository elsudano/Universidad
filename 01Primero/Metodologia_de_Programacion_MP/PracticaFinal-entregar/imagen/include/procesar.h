/**
  * @file procesar.h
  * @brief Fichero de cabecera que implementa todos los demas
  * 
  * Con este fichero conseguimos que todos los demas fichero esten centralizados
  * de esta manera bastara con incluir este fichero en la cabecera de nuestros
  * programas para poder utilizar toda su funcionalidad.
  *
  */
#ifndef _PROCESAR_H_
#define _PROCESAR_H_
#include "imagen.h" ///< Aquí es donde se encuentran las funciones para manejar las imagenes.
#include "transformacion.h" ///< Esta es la Clase que se encarga de las Transformaciones.
/**
  * @brief Leer el mensaje desde la consola para guardarlo en memoria.
  *
  * Con esta funcion leeremos el mensaje y lo guardaremos
  * en un vector de unsigned char para darselo a la siguiente funcion listo.
  * @param mensaje Parametro de entrada, tipo de dato char[],
  * este es el nombre del fichero de imagen en donde queremos cifrar nuestro mensaje.
  * @param max Parametro de entrada, tipo de int,
  * Este es el tamaño maximo que se podra leer para guardar en el vector.
  * @return Devuelve verdadero o falso segun tenga exito o no.
  *
  */
bool LeerMensaje(char *mensaje, int max);

/**
  * @brief Ocultar el mensaje dentro de la Imagen
  *
  * Con esta funcion conseguimos poner a valor 1 el ultimo bit (bit de menor peso)
  * del caracter que le pasemos a la funcion.
  * @param dato Parametro de entrada, tipo de dato char,
  * @return Devuelve el caracter ya modificado.
  *
  */
char poner_a_1 (char dato);

/**
  * @brief Ocultar el mensaje dentro de la Imagen
  *
  * Con esta funcion conseguimos poner a valor 0 el ultimo bit (bit de menor peso)
  * del caracter que le pasemos a la funcion.
  * @param dato Parametro de entrada, tipo de dato char,
  * @return Devuelve el caracter ya modificado.
  *
  */
char poner_a_0 (char dato);

/**
  * @brief Ocultar el mensaje dentro de la Imagen
  *
  * Con esta función realizaremos el proceso de ocultar
  * el mensaje dentro de la imagen.
  * @param imagen Parametro de entrada, tipo de dato Imagen,
  * Este es un objeto imagen con los datos necesarios para su procesado
  * @param texto Parametro de entrada, tipo de dato *char,
  * Este es el mensaje que queremos ocultar en nuestra Imagen.
  * @return Devuelve verdadero o falso segun tenga exito o no.
  *
  */
bool ocultar(Imagen& imagen,const char *texto);

/**
  * @brief Revelar el mensaje que hay dentro de la Imagen
  *
  * Con esta función realizaremos el proceso de revelar
  * el mensaje que esta dentro de la imagen.
  * @param imagen Parametro de entrada, tipo de dato Imagen.
  * Este es el objeto Imagen donde esta oculto nuestro mensaje.
  * @param texto Parametro de salida, tipo de dato *char,
  * este es el mensaje que esta dentro de la imagen.
  * @return Devuelve verdadero o falso segun tenga exito o no.
  *
  */
bool revelar(Imagen& imagen, char texto[]);

#endif 
