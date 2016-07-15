/* 
 * File:   conjuntopreguntas.h
 * Author: Carlos de la Torre
 *
 * Created on 3 de noviembre de 2015, 10:36
 */

#ifndef __CONJUNTOPREGUNTAS_H__
#define __CONJUNTOPREGUNTAS_H__

#include <stdlib.h>
#include <string>
#include <vector>
#include <set>
#include "pregunta.h"

using namespace std;

/**
 * @brief T.D.A. ConjuntoPreguntas\n
 * Una instancia de ConjuntoPreguntas contiene en su interior una cantidad
 * finitas de Preguntas las cuales se pueden usar para un Test
 * Un ejemplo de su uso:
 * @include pruebagen_test.cpp
 *
 * @author Carlos de la Torre
 * @date Diciembre 2015
 */

class ConjuntoPreguntas {
    /**
     * Para poder leer correctamente el tipo de datos que estamos leyendo
     * simplemente es renombrar los tipos de datos.
     */
    typedef unsigned int num;
private:
#if defined(NOSTL)
    /**
     * @page repConjuntoPreguntas Representación del TDA ConjuntoPreguntas.\n
     * Para poder almacenar las diferentes preguntas en memoria se utiliza
     * un vector dinamico el cual almacena objetos de tipo pregunta.
     * @section invConjuntoPreguntas Invariante de la representación
     *  rep.cant_pregu > 0;\n
     *  rep.ConjuntoPreguntas == rep.cant_pregu;\n
     * @section faConjuntoPreguntas Función de abstracción
     * Para crear un conjunto de preguntas simplemente tenemos
     * pasar el texto "bruto" y se agregaran todas las preguntas
     * al conjunto.
     *  F(Lista_de_Preguntas);
     */
    num cant_pregu; /** Cantidad de Preguntas que tiene el conjunto */
    Pregunta * miConjuntoPreguntas; /** Conjunto de Preguntas completo */
    num reservado; /** Tamaño del vector de preguntas */
#elif defined(STL)
    /**
     * @page repConjuntoPreguntas Representación del TDA ConjuntoPreguntas.\n
     * Para poder almacenar las diferentes preguntas en memoria se utiliza
     * un vector dinamico el cual almacena objetos de tipo pregunta.
     * @section invConjuntoPreguntas Invariante de la representación
     *  rep.miConjuntoPreguntas == Conjunto de preguntas > 0
     * @section faConjuntoPreguntas Función de abstracción
     * Para crear un conjunto de preguntas simplemente tenemos
     * pasar el texto "bruto" y se agregaran todas las preguntas
     * al conjunto.
     *  ConjuntoPreguntas == F(Pregunta) * x;
     */
    typedef pair<num, Pregunta> pregunta;
    vector<pregunta> miConjuntoPreguntas;
#endif
public:
    /**
     * @brief Constructor por defecto, crea un conjunto de preguntas vacio.
     * @retval Devuelve una pregunta con enunciado vacio y sin ninguna respuesta
     * @post hay que introducir las preguntas una a una
     * \verbatim
     *   ConjuntoPreguntas CP;
     *   cout CP;
     * \endverbatim
     */
    ConjuntoPreguntas();

    /**
     * @brief Constructor de Copia.
     * @param \bcp Conjunto de Preguntas origen de donde se copiaran los datos.
     * @retval Devuelve un ConjuntoPreguntas con los mismos datos que \ecp.
     * \verbatim
     *   ConjuntoPreguntas P
     *   ConjuntoPreguntas CopiaP(P);
     *   cout << P;
     *   cout << CopiaP;
     * \endverbatim
     */
    ConjuntoPreguntas(const ConjuntoPreguntas &cp);

    /**
     * @brief Destructor por defecto.
     * \verbatim
     *   ~ConjuntoPreguntas;
     * \endverbatim
     */
    ~ConjuntoPreguntas();

    /**
     * @brief Inserta una pregunta en el Conjunto.
     * @param \e P es la pregunta que se va a insertar.
     * \verbatim
     *  Pregunta P;
     *  ConjuntoPreguntas CP;
     *  CP.setPregunta(P);
     *  cout CP;
     * \endverbatim
     */
    void setPregunta(const Pregunta &p);

    /**
     * @brief Pide una pregunta al conjunto.
     * @param n Es un numero entero que corresponde a la pregunta que queremos recuperar.
     * @retval Devuelve la Pregunta que corresponde a n.
     * \verbatim
     *  ConjuntoPreguntas CP;
     *  fin>>CP;
     *  cout CP.getPregunta(3);
     * \endverbatim
     */
    Pregunta getPregunta(const num &n);

    /**
     * @brief Pide una pregunta al conjunto.
     * @param n Es un numero entero que a la posicion que ocupa la pregunta que queremos recuperar.
     * @retval Devuelve la Pregunta que corresponde a la posición n.
     * @pre hay que tener en cuenta que no se puede modificar la pregunta resultado.\n
     * \verbatim
     *  ConjuntoPreguntas CP;
     *  fin>>CP;
     *  cout << CP[3];
     * \endverbatim
     */
    const Pregunta &operator[](num &n);

    /**
     * @brief Muestra la cantidad de preguntas que hay en el conjunto.
     * @retval Devuelve un entero con la cantidad de preguntas.
     * \verbatim
     *   ConjuntoPreguntas CP;
     *   cout CP.Size();
     * \endverbatim
     */
    num Size();

    /**
     * @brief Redimenciona el conjuntoPreguntas para albergar n preguntas mas.
     * @param Cantidad de preguntas que se amplia el conjunto n.
     * \verbatim
     *   ConjuntoPreguntas CP.Redimencionar(5);
     *   cout CP.Size();
     * \endverbatim
     */
    void Redimencionar(const num &n);

    /**
     * @brief Clase iterador, sirve para movernos por los elementos de nuestro TDA.
     * @retval Devuelve un dato iterador para usarlo.
     * \verbatim
     * 
     * \endverbatim
     */
    class iterator {
    private:
        vector<pregunta>::iterator it;
    public:

        /** 
         * @brief Operador de post incremento del iterador, para la
         * clase ConjuntoPreguntas.
         * @retval Devuelve el siguiente elemento del contenedor despues 
         * de ejecutar el codigo que sea necesario.
         * \verbatin
         * 
         * \endverbatin
         */
        iterator &operator++();

        /** 
         * @brief Operador de post decremento del iterador, para la
         * clase ConjuntoPreguntas.
         * @retval Devuelve el elemento anterior del contenedor después 
         * de ejecutar el codigo que sea necesario.
         * \verbatin
         * 
         * \endverbatin
         */
        iterator &operator--();

        /** 
         * @brief Operador de acceso del iterador, para la clase ConjuntoPreguntas.
         * @retval Devuelve el valor del elemento en donde se encuentre el iterador.
         * \verbatin
         * 
         * \endverbatin
         */
        pregunta &operator*();

        /** 
         * @brief Operador de comparación, para la clase ConjuntoPreguntas.
         * @retval Devuelve true si la posición de los iteradores es la misma,
         * false en caso contrario.
         * \verbatin
         * 
         * \endverbatin
         */
        bool operator==(const iterator &i) const;

        /** 
         * @brief Operador de diferencia, para la clase ConjuntoPreguntas.
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
         * por los diferentes elementos.
         */
        friend class ConjuntoPreguntas;
    };

    /**
     * @brief Clase cost_iterador, sirve para movernos por los elementos de
     * nuestro TDA constante.
     * @retval Devuelve un dato iterador para usarlo.
     * \verbatim
     * 
     * \endverbatim
     */
    class const_iterator {
    private:
        vector<pregunta>::const_iterator it;
    public:
        /** 
         * @brief Operador de post incremento del iterador, para la
         * clase ConjuntoPreguntas.
         * @retval Devuelve el siguiente elemento del contenedor despues 
         * de ejecutar el codigo que sea necesario.
         * \verbatin
         * 
         * \endverbatin
         */
        const_iterator &operator++();

        /** 
         * @brief Operador de post decremento del iterador, para la
         * clase ConjuntoPreguntas.
         * @retval Devuelve el elemento anterior del contenedor después 
         * de ejecutar el codigo que sea necesario.
         * \verbatin
         * 
         * \endverbatin
         */
        const_iterator &operator--();

        /** 
         * @brief Operador de acceso del iterador, para la clase ConjuntoPreguntas.
         * @retval Devuelve el valor del elemento en donde se encuentre el iterador
         * \verbatin
         * 
         * \endverbatin
         */
        pregunta operator*();

        /** 
         * @brief Operador de comparación, para la clase ConjuntoPreguntas.
         * @retval Devuelve true si la posición de los iteradores es la misma,
         * false en caso contrario.
         * \verbatin
         * 
         * \endverbatin
         */
        bool operator==(const const_iterator &i) const;

        /** 
         * @brief Operador de diferencia, para la clase ConjuntoPreguntas.
         * @retval Devuelve true si la posición de los iteradores son diferentes,
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
        friend class ConjuntoPreguntas;
    };

    /**
     * @brief Metodo de acceso al iterador del comienzo.
     * @retval Devuelve un dato iterador para usarlo.
     * \verbatim
     * 
     * \endverbatim
     */
    iterator begin();

    /**
     * @brief Metodo de acceso al iterador del comienzo constante.
     * @retval Devuelve un dato iterador para usarlo.
     * \verbatim
     * 
     * \endverbatim
     */
    const_iterator begin() const;

    /**
     * @brief Metodo de acceso al iterador del final.
     * @retval Devuelve un dato iterador para usarlo.
     * \verbatim
     * 
     * \endverbatim
     */
    iterator end();

    /**
     * @brief Metodo de acceso al iterador del final constante.
     * @retval Devuelve un dato iterador para usarlo.
     * \verbatim
     * 
     * \endverbatim
     */
    const_iterator end() const;

    /**
     * @brief Entrada de un ConjuntoPreguntas desde istream
     * @param is stream de entrada
     * @param cp Conjunto de Preguntas que recibe el valor
     * @retval Devuelve el conjunto de Preguntas con las preguntas en su interior
     * @pre La entrada tiene el formato:
     * "Numero.- Enunciado\b \\n
     *Numero de Respuestas\b \\n
     *RespuestaA\b \\n
     *RespuestaN"\b \\n \n
     * \verbatim
     * Uso:
     *  ifstream fin(argv[1]);
     *  ConjuntoPreguntas CP;
     *  fin>>CP;
     *  cout<<CP;
     * \endverbatim
     */
    friend istream &operator>>(istream &is, ConjuntoPreguntas &cp);

    /**
     * @brief Salida de una Pregunta por ostream
     * @param os stream de salida
     * @param cp Pregunta a escribir
     * @post Se obtiene en \b os el ConjuntoPreguntas completo con sus respuestas formateada
     * \verbatim
     * Uso:
     *  ConjuntoPreguntas CP;
     *  fin>>CP
     *  cout<<CP;
     * \endverbatim
     */
    friend ostream &operator<<(ostream &os, const ConjuntoPreguntas &cp);
};

#if defined(STL)
#include "conjuntopreguntas2.hxx"
#elif defined(NOSTL)
#include "conjuntopreguntas1.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif

#endif /* __CONJUNTOPREGUNTAS_H__ */

