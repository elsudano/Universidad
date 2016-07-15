/* 
 * File:   basico.h
 * Author: Carlos de la Torre
 *
 * Created on 22 de noviembre de 2015, 13:16
 */

#ifndef BASICO_H
#define BASICO_H

/**
 * El iostream se incluye para poder tener los fujos de entrada y salida
 * que son necesarios para poder imprimir por pantalla los diferentes 
 * datos que almacenan nuestro T.D.A.
 */
#include <iostream>

/**
 * Redefinición de un tipo de dato básico unsigned int, por otro nombre que 
 * sea mas fácil de recordar, la redifinición se hace dentro de la clase
 * para que se pueda llamar desde otros módulos.
 */
typedef unsigned int num;

/**
 * Esta variable se utiliza para tener un minimo de memoria cuando se crea
 * el vector dinamico y no tener que escribirlo diractemente en el codigo.
 */
const num MEM_MIN = 10;

/**
 * @page repElemento Representación del TDA Elemento
 * Este elemento se encarga de contener el dato y el minimo asociado, esto es 
 * parecido al tipo de dato pair() de la S.T.L.
 * @section invElemento Invariante de la representación
 * El invariante es:
 *  rep.dato == dato almacenado;\n
 *  rep.min == valor mas pequeño almacenado en el contenedor ;\n
 * @section faElemento Función de abstracción
 * Un objeto válido @e rep del TDA Elemento representa al valor\n
 * F() = Resultado(rep.dato=T(),rep.min<=T());
 */
template <class T>
struct Elemento {
    T dato; ///< Dato almacenado en el Tope
    T min; ///< Valor del dato mas pequeño

    /**
     * @brief Constructor por defecto
     */
    Elemento() {
        this->dato = T();
        this->min = T();
    }

    /**
     * @brief Constructor con parámetros
     * @param d Dato almacenado en el Tope
     * @param m Valor del dato mas pequeño
     */
    Elemento(const T &d, T &m) {
        this->dato = d;
        this->min = m;
    }
};

/**
 * @page repCelda Representación del TDA Celda
 * Celda de almacenamiento 
 * @section invCelda Invariante de la representación
 * El invariante es:
 *  rep.ele == dato almacenado;\n
 *  rep.sig apunta al siguiente dato;\n
 *  rep.ant apunta al dato anterior;\n
 *  rep.sig = rep.ant = 0, esta vacia\n
 * @section faCelda Función de abstracción
 * Un objeto válido @e rep del TDA Celda representa al valor\n
 * F() = Resultado(rep.ele="",rep.sig=0,rep.ant=0);
 */
template <class T>
struct Celda {
    T ele; ///< Elemento que contiene la información y el mínimo
    Celda * sig; ///< Puntero al siguiente nodo.
    Celda * ant; ///< Puntero al nodo anterior.

    /**
     * @brief Constructor por defecto
     */
    Celda() {
        this->sig = this->ant = 0;
    }

    /**
     * @brief Constructor con parámetros.
     * @param elem Elemento que contiene la información y el mínimo.
     * @param sigui Puntero al siguiente nodo.
     * @param ante Puntero al nodo anterior.
     */
    Celda(T elem, Celda *sigui, Celda *ante) {
        this->ele = elem;
        this->sig = sigui;
        this->ant = ante;
    }
};

#endif /* BASICO_H */