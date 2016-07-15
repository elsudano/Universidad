/* 
 * File:   pregunta.h
 * Author: Carlos de la Torre
 *
 * Created on 3 de noviembre de 2015, 10:39
 */

#ifndef __PREGUNTA_H
#define __PREGUNTA_H

#include <map>
#include <string>
#include <sstream>
#include <locale>
#include <vector>
#include <stdlib.h>

using namespace std;

/**
 *  @brief T.D.A. Pregunta\n
 * Una instancia de Pregunta contiene en su interior un enunciado
 * junto con sus N respuestas opcionales
 * Un ejemplo de su uso:
 * @include pruebagen_test.cpp
 *
 * @author Carlos de la Torre
 * @date Diciembre 2015
 */
class Pregunta {
    /**
     * Para poder leer correctamente el tipo de datos que estamos leyendo
     * simplemente es renombrar los tipos de datos.
     */
    typedef unsigned int num;
private:
    /** Este es el numero de la pregunta */
    num Num_preg; //< Cuidado con esto que no esta implementado en el hxx en la parte de NOSTL

    /** Enunciado de la Pregunta */
    string Enunciado;

#if defined(NOSTL)
    /**
     * @page repPregunta Representación del TDA Pregunta
     * Este tipo de dato se encarga de almacenar un enunciado
     * junto con el numero de respuestas que se han asignado.
     * @section invPregunta Invariante de la representación
     * El invariante es:
     *  rep.Enunciado != "";\n
     *  rep.cant_respu > 0;\n
     *  rep.Respuestas == cadena de n respuestas;\n
     * @section faPregunta Función de abstracción
     * Un objeto válido @e rep del TDA Pregunta representa al valor\n
     * F(rep.Enunciado,rep.cant_respu,rep.Respuestas)
     */

    /** Cantidad de Respuestas de la pregunta*/
    num cant_respu;

    /** Cadena de Respuestas */
    string Respuestas;
#elif defined(STL)

    /**
     * @page repPregunta Representación del TDA Pregunta
     * Este tipo de dato se encarga de almacenar un enunciado
     * junto con el numero de respuestas que se han asignado a dicho enunciado.
     * @section invPregunta Invariante de la representación
     * rep.Num_preg == Numero real de la pregunta != de la posición que ocupa.
     * rep.Enunciado == Es la pregunta.
     * rep.Respuestas == Conjunto de las posibles respuestas
     * @section faPregunta Función de abstracción
     * Pregunta = F(rep.Num_preg,rep.Enunciado,rep.Respuestas[])
     */

    /** Redefinimos un map para que contenga todas las respuesta de la pregunta */
    typedef pair<string, string> respuesta;

    /** Almacen para las Respuestas de la pregunta */
    vector<respuesta> Respuestas;
#endif


public:
    /**
     * @brief Constructor por defecto, crea una pregunta vacia
     * @retval Devuelve una pregunta con enunciado vacio y sin ninguna respuesta
     * @post hay que rellenar la pregunta
     * \verbatim
     *   Pregunta P = new Pregunta;
     *   cout P;
     * \endverbatim
     */
    Pregunta();

    /**
     * @brief Constructor de copia, crea una copia de p
     * @param p Es la pregunta original que queremos copiar
     * @retval Devuelve una pregunta identica a p
     * \verbatim
     *   Pregunta P = new Pregunta;
     *   Pregunta J(P); 
     *   cout << J;
     * \endverbatim
     */
    Pregunta(const Pregunta &p);

    /**
     * @brief Constructor con parámetros, 
     * @param p Es un string que contiene el numero de la pregunta
     * seguido del enunciado junto con las posibles respuestas manteniendo
     * el siguiente formato:\n
     * numero.- Enunciado\\ncantidad de respuestas\\nrespuesta 1\\nrespuesta n\\n \n
     * @retval Devuelve un dato tipo Pregunta.
     * \verbatim
     *   Pregunta P("165.- Esto es una pregunta\\n3\\na) Que puede tener esta respuesta\\nb) O esta otra respuesta\\nc) O ninguna de las 2 anteriores\\n");
     *   cout << P;
     * \endverbatim
     */
    Pregunta(const string &p);

    /**
     * @brief Destructor por defecto
     * \verbatim
     *   ~Pregunta;
     * \endverbatim
     */
    ~Pregunta();

    /**
     * @brief Muestra cual es el numero real de la pregunta.
     * \verbatim
     *   Pregunta P = new Pregunta();
     *   cout << P.GetNumero();
     * \endverbatim
     */
    num GetNumero() const;

    /**
     * @brief Muestra cual es la respuesta que esta en la posición pos
     * @param pos Posición de la respuesta que queremos ver
     * \verbatim
     *   Pregunta P("165.- Esto es una pregunta\\n3\\na) Que puede tener esta respuesta\\nb) O esta otra respuesta\\nc) O ninguna de las 2 anteriores\\n");
     *   cout << P.GetAnswer(2);
     * \endverbatim
     */
    string GetAnswer(num pos) const;

    /**
     * @brief Operador de Asignación, introduce los datos que hay en la cadena p
     * @param p Un tipo de dato Pregunta
     * @retval Devuelve una pregunta con los datos que contiene p
     * \verbatim
     *   Pregunta P("165.- Esto es una pregunta\\n3\\na) Que puede tener esta respuesta\\nb) O esta otra respuesta\\nc) O ninguna de las 2 anteriores\\n");
     *   Pregunta J = P;
     *   cout << J;
     * \endverbatim
     */
    Pregunta &operator=(const Pregunta &p);

    /**
     * @brief Operador de Comparación, compara si dos Preguntas son iguales
     * @param La pregunta que queremos comparar se introduce en \e P
     * @retval Devuelve true si las dos Preguntas son iguales, false en caso contrario
     * \verbatim
     *  Pregunta P("165.- Esto es una pregunta\\n3\\na) Que puede tener esta respuesta\\nb) O esta otra respuesta\\nc) O ninguna de las 2 anteriores\\n");
     *  Pregunta Z("165.- Esto es una pregunta\\n2\\na) Que puede tener esta respuesta\\nb) O ninguna respuesta\\n");
     *  if (P == Z)
     *      cout<<"Son iguales";
     *  else
     *      cout<<"Son diferentes";
     * \endverbatim
     */
    bool operator==(const Pregunta &p);

    /**
     * @brief Entrada de una Pregunta desde istream
     * @param is stream de entrada
     * @param p Pregunta que recibe el valor
     * @retval La Pregunta leída en p
     * @pre La entrada tiene el formato:
     * "Enunciado\nNumero de Respuestas\nRespuestaA\nRespuestaN"
     * \verbatim
     * Uso:
     *    Pregunta P();
     *    P>>"165.- Esto es una pregunta\\n3\\na) Que puede tener esta respuesta\\nb) O esta otra respuesta\\nc) O ninguna de las 2 anteriores\\n";
     *    cout<<P;
     * \endverbatim
     */
    friend istream &operator>>(istream &is, Pregunta &p);

    /**
     * @brief Salida de una Pregunta por ostream
     * @param os stream de salida
     * @param p Pregunta a escribir
     * @post Se obtiene en \a os la Pregunta completa con sus respuestas formateada
     * \verbatim
     * Uso:
     *   Pregunta P("165.- Esto es una pregunta\\n3\\na) Que puede tener esta respuesta\\nb) O esta otra respuesta\\nc) O ninguna de las 2 anteriores\\n");
     *   cout<<P;
     * \endverbatim
     */
    friend ostream &operator<<(ostream &os, const Pregunta &p);
};
#if defined(STL)
#include "pregunta2.hxx"
#elif defined(NOSTL)
#include "pregunta1.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif

#endif /* __PREGUNTA_H */

