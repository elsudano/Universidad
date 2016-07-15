/* 
 * File:   test.h
 * Author: usuario
 *
 * Created on 3 de noviembre de 2015, 10:37
 */

#ifndef __TEST_H
#define __TEST_H

#include <time.h> 
#include <stdlib.h>
#include <set>
#include "pregunta.h"
#include "conjuntopreguntas.h"

using namespace std;

/**
 * @brief T.D.A. Test\n
 * Con esta clase de datos conseguiremos un conjunto de preguntas
 * aleatorio, que se generará desde una base de datos de preguntas
 * que se le pasa al Test indicando la cantidad de preguntas a generar.
 * Un ejemplo de su uso:
 * @include pruebagen_test.cpp
 *
 * @author Carlos de la Torre
 * @date Diciembre 2015
 */
class Test {
    /**
     * Para poder leer correctamente el tipo de datos que estamos leyendo
     * simplemente es renombrar los tipos de datos.
     */
    typedef unsigned int num;
private:
#if defined(NOSTL)
    /**
     * @page repTest Representación del TDA Test\n
     * Este objeto tendrá un conjunto de preguntas aleatorio
     * las cuales se seleccionaran desde el conjunto de preguntas
     * original.
     * @section invTest Invariante de la representación
     *  rep.cant_pregu > 0;\n
     *  rep.preguntasTest == rep.cant_pregu;\n
     * @section faTest Función de abstracción
     * Para poder obtener un test valido hace falta que se cumpla:\n
     * F(ConjuntoPreguntas,Numero_preguntas)
     */

    num cant_pregu; /** Cantidad de Preguntas que contiene el Test*/
    num reservado; /** Tamaño de reserva para el vector de Preguntas */
    Pregunta * preguntasTest; /** Conjunto de preguntas aleatorio */

#elif defined(STL)
    /**
     * @page repTest Representación del TDA Test\n
     * Este objeto tendrá un conjunto de preguntas aleatorio
     * las cuales se seleccionaran desde el conjunto de preguntas
     * original.
     * @section invTest Invariante de la representación
     *  rep.preguntasTest == Conjunto de Preguntas Size() > 0;\n
     * @section faTest Función de abstracción
     * Para poder obtener un test valido hace falta que se cumpla:\n
     * Test == F(ConjuntoPreguntas[>0])
     */

    vector<Pregunta> preguntasTest;
#endif


public:
    /**
     * @brief Constructor por defecto, crea un Test vacio sin preguntas.
     * @post hay que introducir las preguntas una a una
     * \verbatim
     *   Test T;
     *   cout<<T;
     * \endverbatim
     */
    Test();

    /**
     * @brief Constructor de Copia. Copia el Objeto T a un nuevo sitio de memoria
     * @param El dato de entrada T es un Test con las preguntas ya introducidas
     * @retval Devuelve un Test con las mismas preguntas que tiene el origen T
     * \verbatim
     *  Test T;
     *  Test CopiaT(T);
     *  cout<<CopiaT;
     * \endverbatim
     */
    Test(const Test &t);

    /**
     * @brief Destructor por defecto
     * \verbatim
     *   ~Test();
     * \endverbatim
     */
    ~Test();

    /**
     * @brief Constructor con parametros. para crear un nuevo 
     * @param cp Este es el conjunto de preguntas de donde se elijen
     * @param np Es la cantidad de preguntas que queremos que tenga el Test
     * @retval Devuelve un Test con sus preguntas y respuestas
     * \verbatim
     *  ConjuntoPreguntas CP;
     *  Test T(CP,5);
     *  cout<<T;
     * \endverbatim
     */
    Test(ConjuntoPreguntas &cp, num np);

    /**
     * @brief Constructor con parametros. para crear un nuevo Test
     * que no tiene preguntas repetidas
     * @param cp Este es el conjunto de preguntas de donde se elijen
     * @param np Es la cantidad de preguntas que queremos que tenga el Test
     * @param nr Es un listado de preguntas que ya se han realizado para no repetir
     * @retval Devuelve un Test con sus preguntas y respuestas
     * \verbatim

     * \endverbatim
     */
    Test(ConjuntoPreguntas &cp, int np, set<int> &nr);

    /**
     * @brief Sirve para saber cuantas preguntas tiene el Test.
     * @retval Devuelve un numero que indica la cantidad de preguntas del Test
     * \verbatim
     *  ConjuntoPreguntas CP;
     *  Test T(CP,5);
     *  cout<<T.Size();
     * \endverbatim
     */
    num Size();

    /**
     * @brief Clase iterador, sirve para movernos por los elementos de nuestro TDA
     * @retval Devuelve un dato iterador para usarlo
     */
    class iterator {
    private:
        vector<Pregunta>::iterator it;
    public:
        /** 
         * @brief Operador de post incremento del iterador, para la clase Test.
         * @retval Devuelve el siguiente elemento del contenedor despues 
         * de ejecutar el codigo que sea necesario.
         * \verbatin
         * 
         * \endverbatin
         */
        iterator &operator++();

        /** 
         * @brief Operador de post decremento del iterador, para la clase Test.
         * @retval Devuelve el elemento anterior del contenedor después 
         * de ejecutar el codigo que sea necesario.
         * \verbatin
         * 
         * \endverbatin
         */
        iterator &operator--();

        /** 
         * @brief Operador de acceso del iterador, para la clase Test.
         * @retval Devuelve el valor del elemento en donde se encuentre el iterador
         * \verbatin
         * 
         * \endverbatin
         */
        Pregunta &operator*();

        /** 
         * @brief Operador de comparación, para la clase Test.
         * @retval Devuelve true si la posición de los iteradores es la misma,
         * false en caso contrario.
         * \verbatin
         * 
         * \endverbatin
         */
        bool operator==(const iterator &i) const;

        /** 
         * @brief Operador de diferencia, para la clase Test.
         * @retval Devuelve true si la posición de los iteradores son diferentes,
         * false en caso contrario.
         * \verbatin
         * 
         * \endverbatin
         */
        bool operator!=(const iterator &i) const;

        /*
         * Esta linea sirve para indicar que esta clase iterador tiene
         * acceso a los datos privados de Conjunto preguntas para movernos
         * por los diferentes elementos
         */
        friend class Test;
    };

    /**
     * @brief Clase iterador, sirve para movernos por los elementos de nuestro TDA
     * @retval Devuelve un dato iterador constante para usarlo 
     */
    class const_iterator {
    private:
        vector<Pregunta>::const_iterator it;
    public:
        /** 
         * @brief Operador de post incremento del iterador constante, para la clase Test.
         * @retval Devuelve el siguiente elemento del contenedor despues 
         * de ejecutar el codigo que sea necesario sin que se pueda modificar.
         * \verbatin
         * 
         * \endverbatin
         */
        const_iterator &operator++();

        /** 
         * @brief Operador de post decremento del iterador constante, para la clase Test.
         * @retval Devuelve el elemento anterior del contenedor después 
         * de ejecutar el codigo que sea necesario sin que se pueda modificar.
         * \verbatin
         * 
         * \endverbatin
         */
        const_iterator &operator--();

        /** 
         * @brief Operador de acceso del iterador constante, para la clase Test.
         * @retval Devuelve el valor del elemento en donde se encuentre el iterador constante
         * \verbatin
         * 
         * \endverbatin
         */
        Pregunta operator*() const;

        /** 
         * @brief Operador de comparación, para la clase Test.
         * @retval Devuelve true si la posición de los iteradores es la misma,
         * false en caso contrario.
         * \verbatin
         * 
         * \endverbatin
         */
        bool operator==(const const_iterator &i) const;

        /** 
         * @brief Operador de diferencia, para la clase Test.
         * @retval Devuelve true si la posición de los iteradores constantes son diferentes,
         * false en caso contrario.
         * \verbatin
         * 
         * \endverbatin
         */
        bool operator!=(const const_iterator &i) const;

        /*
         * Esta linea sirve para indicar que esta clase iterador tiene
         * acceso a los datos privados de Conjunto preguntas para movernos
         * por los diferentes elementos
         */
        friend class Test;
    };

    /**
     * @brief Metodo de acceso al iterador del comienzo
     * @retval Devuelve un dato iterador para usarlo
     * \verbatim
     * 
     * \endverbatim
     */
    iterator begin();

    /**
     * @brief Metodo de acceso al iterador del comienzo constante
     * @retval Devuelve un dato iterador para usarlo
     * \verbatim
     * 
     * \endverbatim
     */
    const_iterator begin() const;

    /**
     * @brief Metodo de acceso al iterador del final
     * @retval Devuelve un dato iterador para usarlo
     * \verbatim
     * 
     * \endverbatim
     */
    iterator end();

    /**
     * @brief Metodo de acceso al iterador del final constante
     * @retval Devuelve un dato iterador para usarlo
     * \verbatim
     * 
     * \endverbatim
     */
    const_iterator end() const;

    /**
     * @brief Salida de un Test por ostream
     * @param os stream de salida
     * @param t Test a escribir
     * @post Se obtiene en \a os el Test completo sin las respuestas
     * \verbatim
     *   ConjuntoPreguntas CP;
     *   cout CP;
     * \endverbatim
     */
    friend ostream &operator<<(ostream &os, const Test &t);

};

#if defined(STL)
#include "test2.hxx"
#elif defined(NOSTL)
#include "test1.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif

#endif /* __TEST_H */