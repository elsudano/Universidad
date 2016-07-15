/* 
 * File:   conjuntopreguntas.h
 * Author: Carlos de la Torre
 *
 * Created on 3 de noviembre de 2015, 10:36
 */

#ifndef __CONJUNTOPREGUNTAS_H
#define	__CONJUNTOPREGUNTAS_H

#include <stdlib.h>
#include <string>
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
 * @date Noviembre 2015
 */

class ConjuntoPreguntas{
private:
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
    typedef unsigned int size_type;
    size_type cant_pregu;/** Cantidad de Preguntas que tiene el conjunto */
    Pregunta * miConjuntoPreguntas;/** Conjunto de Preguntas completo */
    size_type reservado;/** Tamaño del vector de preguntas */

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
     * @param Conjunto de Preguntas origen de donde se copiaran los datos.
     * @retval Devuelve un ConjuntoPreguntas con los mismos datos que \e CP
     * \verbatim
     *   ConjuntoPreguntas P
     *   ConjuntoPreguntas CopiaP(P);
     *   cout << P;
     *   cout << CopiaP;
     * \endverbatim
     */
    ConjuntoPreguntas(const ConjuntoPreguntas &CP);
    
    /**
     * @brief Destructor por defecto
     * \verbatim
     *   ~ConjuntoPreguntas;
     * \endverbatim
     */
    ~ConjuntoPreguntas();
    
    /**
     * @brief Inserta una pregunta en el Conjunto
     * @param \e P es la pregunta que se va a insertar
     * \verbatim
     *  Pregunta P;
     *  ConjuntoPreguntas CP;
     *  CP.setPregunta(P);
     *  cout CP;
     * \endverbatim
     */
    void setPregunta(const Pregunta P);
    
    /**
     * @brief Pide una pregunta al conjunto 
     * @param \e N es un numero entero que corresponde a la pregunta que queremos recuperar
     * @retval Devuelve la Pregunta que corresponde a \e N
     * \verbatim
     *  ConjuntoPreguntas CP;
     *  fin>>CP;
     *  cout CP.getPregunta(3);
     * \endverbatim
     */
    Pregunta getPregunta(const size_type &N);
    
    /**
     * @brief Pide una pregunta al conjunto 
     * @param \e N es un numero entero que corresponde a la pregunta que queremos recuperar
     * @retval Devuelve la Pregunta que corresponde a \e N
     * @pre hay que tener en cuenta que no se puede modificar la pregunta resultado
     * \verbatim
     *  ConjuntoPreguntas CP;
     *  fin>>CP;
     *  cout CP[3];
     * \endverbatim
     */
    const Pregunta &operator[](size_type& N);
    
    /**
     * @brief Muestra la cantidad de preguntas que hay en el conjunto
     * @retval Devuelve un entero con la cantidad de preguntas
     * \verbatim
     *   ConjuntoPreguntas CP;
     *   cout CP.Size();
     * \endverbatim
     */
    size_type Size();
    
    /**
     * @brief Redimenciona el conjuntoPreguntas para albergar N preguntas mas
     * @param Cantidad de preguntas que se amplia el conjunto \e N
     * \verbatim
     *   ConjuntoPreguntas CP.Redimencionar(5);
     *   cout CP.Size();
     * \endverbatim
     */
    void Redimencionar(const size_type &N);
    
    /**
     * @brief Entrada de un ConjuntoPreguntas desde istream
     * @param IS stream de entrada
     * @param CP Conjunto de Preguntas que recibe el valor
     * @retval Devuelve el conjunto de Preguntas con las preguntas en su interior
     * @pre La entrada tiene el formato:
     * "Enunciado
     * Numero de Respuestas
     * RespuestaA
     * RespuestaN"
     * \verbatim
     * Uso:
     *  ifstream fin(argv[1]);
     *  ConjuntoPreguntas CP;
     *  fin>>CP;
     *  cout<<CP;
     * \endverbatim
     */
    friend istream& operator>>(istream& IS, ConjuntoPreguntas& CP);
    
    /**
     * @brief Salida de una Pregunta por ostream
     * @param OS stream de salida
     * @param CP Pregunta a escribir
     * @post Se obtiene en \a OS el ConjuntoPreguntas completo con sus respuestas formateada
     * \verbatim
     * Uso:
     *  ConjuntoPreguntas CP;
     *  fin>>CP
     *  cout<<CP;
     * \endverbatim
     */
    friend ostream& operator<<(ostream& OS, const ConjuntoPreguntas& CP);
};

#if defined(VER2)
  #include "conjuntopreguntas2.hxx"
#elif defined(VER1)
  #include "conjuntopreguntas1.hxx"
#else
  #error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif

#endif	/* __CONJUNTOPREGUNTAS_H */

