/* 
 * File:   Personas.h
 *
 * Created on 22 de diciembre de 2015, 15:16
 */

#ifndef PERSONAS_H
#define PERSONAS_H

#include <string>
#include <map>
#include "Persona.h"

using namespace std;

/**
 * @brief T.D.A. Personas
 * 
 * \b Definición:
 * Una instancia \e a del tipo de dato abstracto Personas se puede construir
 * con unos datos minimos
 * 
 * - Un dato Int que será el numero de la Persona que se añade al conjunto\n
 * Este número será el que la identifique en el árbol.\n
 * - Un objeto Persona que será el dato que se almacene en el conjuto para\n
 * despúes poder acceder a sus datos.\n
 * 
 * Para poder usar el tipo de dato Personas se debe incluir el fichero
 * <tt>\#include Personas.h</tt>
 * 
 * @author Carlos de la Torre
 * @date 28/12/15
 */
class Personas {
    /**
     * @page repPersonas Representación del TDA Personas
     * Esta clase se creó para definir un nuevo tipo de dato abstracto con el
     * que tendremos una base de datos de personas por el que decidimos usa
     * un map.
     * - Un map de primer elemento un entero y de segundo elemento un TDA de
     * Persona.
     * - Esta clase tiene un iterador implementado para poder manejar mejor
     * los elementos del map.
     *
     * @section invPersonas Invariante de la representación
     *
     * El primer elemento del map no podrá ser negativo y el segundo elemento
     * será objeto del TDA Persona. (En un map no pueden existir
     * elementos repetidos.)
     *
     * @section faPersonas Función de abstracción
     *
     * La función de abstracción para un map es tan sencillo como acceder a los
     * datos de cada uno de los elementos de acaba objeto del map.
     * 
     * fA : rep -> T.D.A. especificación.
     * 
     * Donde pe es una instancia de objeto abstracto de tipo “Personas” que 
     * sirve para crear T.D.A.
     * 
     * fA : rep -> pe.first (siendo >=0…n), pe.second(Persona != vacio)
     */
private:
    map<int, Persona> bd_personas;
public:
    /**
     * @brief Constructor por defecto
     * @note Este constructor esta vacio por que no se hace reserva de memoria\n
     * manualmente si no que se crea el contenedor al crear el objeto.
     */
    Personas();

    /**
     * @brief Constructor con parametros.
     * @param p Persona que se va a agregar al conjunto.
     */
    Personas(const int &n, const Persona &p);

    /**
     * @brief Constructor de Copia
     * @param p este es el conjunto de persona que se quiere copiar
     */
    Personas(const Personas &p);

    /**
     * @brief Destructor por defecto
     * @note Cuando se destruye el objeto se borra todos los datos que \n
     * contiene el map de la zona privada
     */
    ~Personas();

    /**
     * @brief Permite añadir una persona al conjunto.
     * @param p Objeto persona que se añade.
     */
    void SetPersona(const int &n, const Persona &p);


    /**
     * @brief Muestra la persona que queremos.
     * @param n Es el identificador de la persona que queremos mostrar.
     * @return Devuelve un objeto persona con los datos.
     */
    Persona &GetPersona(const int &n);

    /**
     * @brief Esto es para eliminar la persona que corresponde al numero
     * @param n Numero entero que corresponde a la persona.
     */
    void DelPersona(const int &n);
    
    /**
     * @brief Si queremos saber cual es la cantidad de personajes que tenemos
     * @return Devuelve un numero entero positivo que será la cantidad de personajes
     */
    unsigned int Size();

    /**
     * @brief Operador de Entrada.
     * @param in Es el flujo de entrada donde están los datos en bruto.
     * @param pers Es el objeto que recibe los datos para almacenarlos.
     */
    friend std::istream &operator>>(std::istream &in, Personas &pers);

    /**
     * @brief Operador de Salida.
     * @param out Es el flujo de salida donde se muestran los datos formateados.
     * @param pers Es el objeto que contiene los datos que queremos mostrar.
     */
    friend std::ostream &operator<<(std::ostream &out, const Personas &pers);

    /** 
     * @brief T.D.A. Iterador de Personas
     */
    class iterator {
    private:
        map<int, Persona>::iterator it;
    public:
        /**
         * @brief Constructor por defecto
         * @note En realidad este constructor no hace nada puesto que en\n
         * un iterador como este no se tiene que construir nada.
         */
        iterator();

        /**
         * @brief Obtiene el valor del dato
         * @return Devuelve el dato que contiene la posición actual.
         */
        const Persona &operator*();

        /**
         * @brief Obtiene un iterador al siguiente dato
         * @return Devuelve el elemento que esta despúes de la posición actual.
         */
        iterator &operator++();

        /**
         * @brief Obtiene un iterador al dato anterior
         * @return Devuelve el elemento que esta antes de la posición actual.
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

        friend class Personas;
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
#include "ver1/Personas.hxx"
#elif defined(VER2)
#include "ver2/Personas.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* PERSONAS_H */

