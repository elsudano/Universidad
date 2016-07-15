/* 
 * File:   solucion.h
 * Author: Carlos de la Torre
 *
 * Created on 3 de diciembre de 2015, 12:54
 */

#ifndef __SOLUCION_H__
#define __SOLUCION_H__

#include <stdlib.h>
#include <string>
#include <sstream>
#include <vector>
#include <map>

using namespace std;

/**
 * @brief T.D.A. Solucion\n
 * 
 * Un ejemplo de su uso:
 * @include pasa_test.cpp
 *
 * @author Carlos de la Torre
 * @date Diciembre 2015
 */
class Solucion {
    /**
     * Para poder leer correctamente el tipo de datos que estamos leyendo
     * simplemente es renombrar los tipos de datos.
     */
    typedef unsigned int num;
private:
#if defined(NOSTL)
    /**
     * @page repSolucion Representación del TDA Solucion\n
     * 
     * @section invTSolucion Invariante de la representación
     * 
     * @section faSolucion Función de abstracción
     * 
     */

#elif defined(STL)
    /**
     * @page repSolucion Representación del TDA Solucion\n
     * 
     * @section invTSolucion Invariante de la representación
     * 
     * @section faSolucion Función de abstracción
     * 
     */

    map<Solucion::num, char> misSoluciones;
#endif
public:
    /**
     * @brief Constructor por defecto, crea un conjunto de Soluciones vacio.
     * \verbatim
     *   Solucion S;
     *   cout << S;
     * \endverbatim
     */
    Solucion();

    /**
     * @brief Constructor de Copia.
     * @param s Es el conjunto solución que queremos copiar.
     * @retval Devuelve un conjunto de respuestas con los mismo valores que tiene \as.
     * \verbatim
     *  Solucion S;
     *  Solucion CopiaS(S);
     *  cout << CopiaS;
     * \endverbatim
     */
    Solucion(const Solucion &s);

    /**
     * @brief Destructor por defecto
     * \verbatim
     *   ~Solucion();
     * \endverbatim
     */
    ~Solucion();

    /**
     * @brief Constructor con parametros. Crea un conjunto de soluciones
     * con la primera solución introducida
     * @param s Esta es la solución correcta a la pregunta con numero \anp
     * @param np Es el numero de la pregunta que tenemos la solución.
     * @retval Devuelve un conjunto de Respuestas para seguir introduciendo.
     * \verbatim
     *  Solucion S(5,'A');
     *  cout << S;
     * \endverbatim
     */
    Solucion(num np, char &s);

    /**
     * @brief Operador de Asignación, introduce la respuesta en la
     * siguiente pregunta que corresponda.
     * @param s Es la letra de la respuesta a la pregunta
     * \verbatim
     *   Solucion S(5,'A');
     *   S.Size() < devuelve 1
     *   S='B'; < se inserta en 2
     *   cout << S;
     * \endverbatim
     */
    Solucion &operator=(const char &s);

    /**
     * @brief Operador de Comparación, compara si dos Preguntas son iguales
     * @param s Es el conjunto que queremos comparar
     * @retval Devuelve true si los dos conjuntos son iguales, false en caso contrario
     * \verbatim
     *  Solucion S(4,'B');
     *  Solucion J(3,'C')
     *  if (P == Z)
     *      cout<<"Son iguales";
     *  else
     *      cout<<"Son diferentes";
     * \endverbatim
     */
    bool operator==(const Solucion &s);

    /**
     * @brief Operador de Acceso.
     * @param n Es la posición que ocupa la Solución
     * @retval Devuelve la letra que corresponde a la solución
     * \verbatim
     *   Solucion S(5,'A');
     *   cout << S[5];
     * \endverbatim
     */
    const char operator[](num &n);

    /**
     * @brief Inserta una respuesta en el conjunto de soluciones
     * @param n es un numero entero que corresponde a la pregunta que queremos insertar
     * @retval Devuelve la letra que corresponde a la respuesta correcta
     * \verbatim
     *   Solucion S(5,'A');
     *   S.SetSolucion(6,'C');
     *   cout << S[6];
     * \endverbatim
     */
    void SetSolucion(const num np, const char &s);

    /**
     * @brief Devuelve la respuesta que corresponde a la pregunta de numero n
     * @param n es un numero entero que corresponde a la pregunta que queremos recuperar
     * @retval Devuelve la Respuesta que corresponde a \e n
     * @pre hay que tener en cuenta que no se puede modificar la pregunta resultado
     * \verbatim
     *   Solucion S(5,'A');
     *   S.SetSolucion(6,'C');
     *   cout << S.GetSolucion(6);
     * \endverbatim
     */
    char GetSolucion(const num &n);

    /**
     * @brief Sirve para saber cuantas soluciones tiene el conjunto.
     * @retval Devuelve un numero que indica la cantidad de soluciones del Test
     * \verbatim
     *   Solucion S(5,'A');
     *   S.SetSolucion(6,'C');
     *   cout << S.Size();
     * \endverbatim
     */
    num Size();

    /**
     * @brief Clase iterador, sirve para movernos por los elementos de nuestro TDA
     * @retval Devuelve un dato iterador para usarlo
     */
    class iterator {
    private:
        map<num, char>::iterator it;
    public:
        /** 
         * @brief Operador de post incremento del iterador, para la clase Solucion.
         * @retval Devuelve el siguiente elemento del contenedor despues 
         * de ejecutar el codigo que sea necesario.
         * \verbatin
         * 
         * \endverbatin
         */
        iterator &operator++();

        /** 
         * @brief Operador de post decremento del iterador, para la clase Solucion.
         * @retval Devuelve el elemento anterior del contenedor después 
         * de ejecutar el codigo que sea necesario.
         * \verbatin
         * 
         * \endverbatin
         */
        iterator &operator--();

        /** 
         * @brief Operador de acceso del iterador, para la clase Solucion.
         * @retval Devuelve el valor del elemento en donde se encuentre el iterador
         * \verbatin
         * 
         * \endverbatin
         */
        pair<num, char> operator*();

        /** 
         * @brief Operador de comparación, para la clase Solucion.
         * @retval Devuelve true si la posición de los iteradores es la misma,
         * false en caso contrario.
         * \verbatin
         * 
         * \endverbatin
         */
        bool operator==(const iterator &i) const;

        /** 
         * @brief Operador de diferencia, para la clase Solucion.
         * @retval Devuelve true si la posición de los iteradores son diferentes,
         * false en caso contrario.
         * \verbatin
         * 
         * \endverbatin
         */
        bool operator!=(const iterator &i) const;

        /**
         * @brief esta linea sirve para indicar que esta clase iterador tiene
         * acceso a los datos privados de Conjunto preguntas para movernos
         * por los diferentes elementos
         */
        friend class Solucion;
    };

    /**
     * @brief Clase iterador, sirve para movernos por los elementos de nuestro TDA
     * @retval Devuelve un dato iterador constante para usarlo 
     */
    class const_iterator {
    private:
        map<num, char>::const_iterator it;
    public:
        /** 
         * @brief Operador de post incremento del iterador constante, para la clase Solucion.
         * @retval Devuelve el siguiente elemento del contenedor despues 
         * de ejecutar el codigo que sea necesario sin que se pueda modificar.
         * \verbatin
         * 
         * \endverbatin
         */
        const_iterator &operator++();

        /** 
         * @brief Operador de post decremento del iterador constante, para la clase Solucion.
         * @retval Devuelve el elemento anterior del contenedor después 
         * de ejecutar el codigo que sea necesario sin que se pueda modificar.
         * \verbatin
         * 
         * \endverbatin
         */
        const_iterator &operator--();

        /** 
         * @brief Operador de acceso del iterador constante, para la clase Solucion.
         * @retval Devuelve el valor del elemento en donde se encuentre el iterador constante
         * \verbatin
         * 
         * \endverbatin
         */
        pair<num, char> operator*() const;

        /** 
         * @brief Operador de comparación, para la clase Solucion.
         * @retval Devuelve true si la posición de los iteradores es la misma,
         * false en caso contrario.
         * \verbatin
         * 
         * \endverbatin
         */
        bool operator==(const const_iterator &i) const;

        /** 
         * @brief Operador de diferencia, para la clase Solucion.
         * @retval Devuelve true si la posición de los iteradores constantes son diferentes,
         * false en caso contrario.
         * \verbatin
         * 
         * \endverbatin
         */
        bool operator!=(const const_iterator &i) const;

        /**
         * @brief esta linea sirve para indicar que esta clase iterador tiene
         * acceso a los datos privados de Conjunto preguntas para movernos
         * por los diferentes elementos
         */
        friend class Solucion;
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
     * @brief Entrada de un conjunto de Soluciones desde istream
     * @param is stream de entrada
     * @param s Conjunto de Soluciones que recibe el valor
     * @retval Devuelve el conjunto de Soluciones
     * @pre La entrada tiene el formato:
     * "[Numero].- [Letra de Solucion]"
     * \verbatim
     * Uso:
     *  ifstream fin(argv[1]);
     *  Solucion S;
     *  fin>>S;
     *  cout << S;
     * \endverbatim
     */
    friend istream &operator>>(istream &is, const Solucion &s);

    /**
     * @brief Salida de un conjunto de Soluciones por ostream
     * @param os stream de salida
     * @param t Conjunto Solucion a escribir
     * @post Se obtiene en \a os el conjunto completo de soluciones
     * \verbatim
     *   Solucion S;
     *   cout << S;
     * \endverbatim
     */
    friend ostream &operator<<(ostream &os, const Solucion &s);
};

#if defined(STL)
#include "solucion2.hxx"
#elif defined(NOSTL)
#include "solucion1.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* __SOLUCION_H__ */

