/* 
 * File:   test.h
 * Author: usuario
 *
 * Created on 3 de noviembre de 2015, 10:37
 */

#ifndef __TEST_H
#define	__TEST_H

#include <time.h> 
#include <stdlib.h>
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
 * @date Noviembre 2015
 */
class Test{
private:
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
    typedef unsigned int size_type;
    size_type cant_pregu; /** Cantidad de Preguntas que contiene el Test*/
    size_type reservado; /** Tamaño de reserva para el vector de Preguntas */
    Pregunta * preguntasTest; /** Conjunto de preguntas aleatorio */
    
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
    Test(const Test& T);
    
    /**
     * @brief Destructor por defecto
     * \verbatim
     *   ~Test();
     * \endverbatim
     */
    ~Test();
    
    /**
     * @brief Constructor con parametros. para crear un nuevo 
     * @param CP Este es el conjunto de preguntas de donde se elijen
     * @param np Es la cantidad de preguntas que queremos que tenga el Test
     * @retval Devuelve un Test con sus preguntas y respuestas
     * \verbatim
     *  ConjuntoPreguntas CP;
     *  Test T(CP,5);
     *  cout<<T;
     * \endverbatim
     */
    Test(ConjuntoPreguntas &CP, size_type np);
    
    /**
     * @brief Sirve para saber cuantas preguntas tiene el Test.
     * @retval Devuelve un numero que indica la cantidad de preguntas del Test
     * \verbatim
     *  ConjuntoPreguntas CP;
     *  Test T(CP,5);
     *  cout<<T.Size();
     * \endverbatim
     */
    size_type Size();
    
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
    friend ostream& operator<<(ostream& os, const Test& t);

};

#if defined(VER2)
  #include "test2.hxx"
#elif defined(VER1)
  #include "test1.hxx"
#else
  #error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif

#endif	/* __TEST_H */