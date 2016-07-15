/* 
 * File:   Preguntas.h
 *
 * Created on 12 de diciembre de 2015, 15:21
 */

#ifndef PREGUNTAS_H
#define PREGUNTAS_H

#include <map>

/**
 * @brief T.D.A. Preguntas
 * 
 * \b Definición:
 * Una instancia \e a del tipo de dato abstracto Preguntas se puede construir
 * con unos datos minimos
 * 
 * - Un dato String que será la pregunta que queremos almacenar en nuestro conjunto.\n
 * - Un dato Int que será el numero de la pregunta por que no necesariamente\n
 * tiene que tener un orden especificado.\n
 * 
 * Para poder usar el tipo de dato Preguntas se debe incluir el fichero
 * <tt>\#include Preguntas.h</tt>
 * 
 * @author Carlos de la Torre
 * @date 28/12/15
 */

class Preguntas {
    /**
     * @page repPreguntas Representación del TDA Preguntas
     * 
     * Esta clase es una TDA para almacenar las preguntas leídas de fichero.
     * Para almacenar las preguntas utilizaremos un map donde el primer campo
     * será el id de la pregunta y el segundo campo un string para la pregunta.
     * Esta clase tiene un iterador implementado para poder manejar mejor los
     * elementos del map.
     *
     * @section invPreguntas Invariante de la representación
     *
     * Al ser un map de entero el primer campo no puede tener ningún otro valor
     * por debajo de 0 o superior al número de preguntas.(En un map no puede
     * existir elementos repetidos.)y como segundo campo será la pregunta
     * correspondiente a dicho id.
     *
     * @section faPreguntas Función de abstracción
     *
     * La función de abstracción para esta clase solo es necesario tener en
     * cuenta los acceso a los datos ya que en ningún momento de modificarían
     * las preguntas.
     * 
     * fA : rep -> T.D.A. especificación.
     * 
     * Donde pre es una instancia de objeto abstracto de tipo “Preguntas” que
     * sirve para crear T.D.A.
     * 
     * fA : rep -> pre.first(siendo >=0…n), p.second(Pregunta != vacío)
     */
private:
    map<int, string> bd_preguntas;

    /**
     * @brief Prepara la pregunta para que se pueda imprimir por pantalla
     * @param p es un pair con los datos de la pregunta
     * @return Devuelve una cadena con el numero y la pregunta
     */
    string imprime(const pair<int, string> &p);
public:
    /**
     * @brief Constructor por defecto.
     */
    Preguntas();

    /**
     * @brief Constructor por parametros.
     * @param n Numero de la pregunta.
     * @param p Cadena con la pregunta. 
     */
    Preguntas(int n, string &p);

    /**
     * @brief Constructor de copia.
     * @param preguntas Conjunto de preguntas desde el que se va a copiar.
     */
    Preguntas(Preguntas &preguntas);

    /**
     * @brief Destructor por defecto.
     */
    ~Preguntas();

    /**
     * @brief Método de inserción de preguntas.
     * @param n Numero de la pregunta.
     * @param p Cadena con la pregunta. 
     */
    void SetPregunta(int n, string &p);
    
    /**
     * @brief Busca una pregunta identificada con \e n y la muestra.
     * @param n Numero de la pregunta que queremos mostrar.
     * @return Devuelve una cadena de caracteres con la pregunta.
     */
    string GetPregunta(int n);
    
    /**
     * @brief Con esta función borramos la pregunta del conjunto, se usa sobre
     * todo para eliminar las preguntas ya realizadas.
     * @param n Numero entero de la pregunta que queremos borrar.
     */
    void DelPregunta(int n);
    
    /**
     * @brief Muestra la cantidad de preguntas que tenemos en el conjunto.
     * @return Devuelve un int con el numero de preguntas.
     */
    int NumPreguntas();

    /**
     * @brief Operador de Entrada.
     * @param in
     * @param pre
     */
    friend std::istream &operator>>(std::istream &in, Preguntas &pre);

    /**
     * @brief Operador de Salida.
     * @param out
     * @param pre
     */
    friend std::ostream &operator<<(std::ostream &out, const Preguntas &pre);

    /** 
     * @brief T.D.A. Iterador de Preguntas 
     */
    class iterator {
    private:
        map<int, string>::iterator it;
    public:
        /**
         * @brief Constructor por defecto
         */
        iterator();

        /**
         * @brief Obtiene el valor del dato
         */
         const string &operator*();

         /**
          * @brief Este metodo es para poder comprobar el numero de la
          * pregunta y seguir cumpliendo lo que se pide en la practica
          * @return Devuelve un numero entero con el numero de la pregunta.
          */
         const int &getNumero();
         
        /**
         * @brief Obtiene un iterador al siguiente dato
         */
        iterator &operator++();

        /**
         * @brief Obtiene un iterador al dato anterior
         */
        iterator &operator--();

        /**
         * @brief Compara dos iteradores
         * @param i: iterador con el con que se comparación
         * @return true si los dos iteradores son iguales. False en caso contrario
         */
        bool operator==(const iterator &i);

        /**
         * @brief Compara dos iteradores
         * @param i: iterador con el con que se comparación
         * @return true si los dos iteradores son diferentes. False en caso contrario
         */
        bool operator!=(const iterator &i);

        friend class Preguntas;
    };

    /**
     * @brief Devuelve el iterador al primer elemento.
     * @return Devuelve un iterador al primer elemento.
     */
    iterator begin();

    /**
     * @brief Devuelve el iterador al ultimo elemento mas 1.
     * @return Devuelve un iterador al ultimo elemento mas 1.
     */
    iterator end();
};
#if defined(VER1)
#include "ver1/Preguntas.hxx"
#elif defined(VER2)
#include "ver2/Preguntas.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* PREGUNTAS_H */

