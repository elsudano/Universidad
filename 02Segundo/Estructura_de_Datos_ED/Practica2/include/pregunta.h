/* 
 * File:   pregunta.h
 * Author: Carlos de la Torre
 *
 * Created on 3 de noviembre de 2015, 10:39
 */

#ifndef __PREGUNTA_H
#define	__PREGUNTA_H

#include <string>

using namespace std;

/**
 *  @brief T.D.A. Pregunta\n
 * Una instancia de Pregunta \e contiene en su interior un enunciado
 * junto con sus \e N respuestas opcionales
 * Un ejemplo de su uso:
 * @include pruebagen_test.cpp
 *
 * @author Carlos de la Torre
 * @date Noviembre 2015
 */
class Pregunta {
private:
    /**
     * @page repPregunta Representación del TDA Pregunta\n
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
    typedef string enunciado;
    typedef unsigned int size_type;
    typedef string respuestas;

    size_type cant_respu; /**Cantidad de Respuestas de la pregunta*/
    enunciado Enunciado; /**Enunciado de la Pregunta*/
    respuestas Respuestas; /**Cadena de Respuestas*/

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
     * @brief Constructor de copia, crea una copia de \e P
     * @param \e P Es la pregunta original que queremos copiar
     * @retval Devuelve una pregunta identica a \e P
     * \verbatim
     *   Pregunta P = new Pregunta;
     *   Pregunta J(P); 
     *   cout J;
     * \endverbatim
     */
    Pregunta(const Pregunta &P);
    
    /**
     * @brief Constructor con parámetros
     * @param \e E Es un string que contiene el enunciado de la pregunta
     * @param \e N Es el numero de respuestas que tendrá la pregunta
     * @param \e R Es un string separado por "\n" que contiene todas las respuestas
     * @retval Devuelve un dato tipo Pregunta con los datos que se metén por los
     * parametros de entrados
     * \verbatim
     *   Pregunta P = new Pregunta;
     *   Pregunta J(P); 
     *   cout J;
     * \endverbatim
     */
    Pregunta(Pregunta::enunciado E, Pregunta::size_type N, Pregunta::respuestas R);
    
    /**
     * @brief Destructor por defecto
     * \verbatim
     *   ~Pregunta;
     * \endverbatim
     */
    ~Pregunta();

    /**
     * @brief Operador de Asignación, introduce los datos que hay en la cadena \e P
     * @param Los datos Enunciado asi como sus respuestas se insertan en \e P
     * @retval Devuelve una pregunta con los datos que contiene \e P
     * @pre La entrada tiene el formato:
     * "Enunciado\nNumero de Respuestas\nRespuestaA\nRespuestaN"
     * \verbatim
     *   Pregunta P;
     *   P = "Pregunta\n2\nSi\nNo";
     *   cout<<P;
     * \endverbatim
     */
    Pregunta& operator=(const string &P);
    
    /**
     * @brief Operador de Comparación, compara si dos Preguntas son iguales
     * @param La pregunta que queremos comparar se introduce en \e P
     * @retval Devuelve true si las dos Preguntas son iguales, false en caso contrario
     * \verbatim
     *  Pregunta P = new Pregunta;
     *  P = "Pregunta\n2\nSi\nNo";
     *  Pregunta Z = new Pregunta;
     *  Z = "Pregunta2\n3\nSi\nNo\nTal vez";
     *  if (P == Z)
     *      cout<<"Son iguales";
     *  else
     *      cout<<"Son diferentes";
     * \endverbatim
     */
    bool operator==(const Pregunta &P);
    
    /**
     * @brief Entrada de una Pregunta desde istream
     * @param is stream de entrada
     * @param p Pregunta que recibe el valor
     * @retval La Pregunta leída en p
     * @pre La entrada tiene el formato:
     * "Enunciado\nNumero de Respuestas\nRespuestaA\nRespuestaN"
     * \verbatim
     * Uso:
     *    Pregunta P;
     *    P>>"Pregunta\n2\nSi\nNo";
     *    cout<<P;
     * \endverbatim
     */
    friend istream& operator>>(istream& IS, Pregunta& P);

    /**
     * @brief Salida de una Pregunta por ostream
     * @param os stream de salida
     * @param p Pregunta a escribir
     * @post Se obtiene en \a os la Pregunta completa con sus respuestas formateada
     * \verbatim
     * Uso:
     *   Pregunta P;
     *   P = "Pregunta\n2\nSi\nNo";
     *   cout<<P;
     * \endverbatim
     */
    friend ostream& operator<<(ostream& OS, const Pregunta& P);
};

#if defined(VER2)
#include "pregunta2.hxx"
#elif defined(VER1)
#include "pregunta1.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif

#endif	/* __PREGUNTA_H */

