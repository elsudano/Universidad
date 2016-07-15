/* 
 * File:   Jugador.h
 *
 * Created on 12 de diciembre de 2015, 14:34
 */

#ifndef JUGADOR_H
#define JUGADOR_H

#include <iostream>
#include <string>
#include <set>
#include <cmath>
#include "Persona.h"
#include "Personas.h"
#include "Preguntas.h"

using namespace std;
/**
 * @brief T.D.A. Jugador
 * 
 * \b Definición:
 * Una instancia \e a del tipo de dato abstracto Jugador se puede construir
 * con unos datos minimos
 * 
 * - Un dato Nombre que será el nombre del jugador humano que luche contra
 * la maquina.\n
 * - Un objeto Personaje que será el personaje que tiene que ocultar el 
 * jugador para poder ganar.\n
 * 
 * Si no se pone el nombre del jugador por defecto será \e Maquina.\n
 * 
 * Para poder usar el tipo de dato Jugador se debe incluir el fichero
 * <tt>\#include Jugador.h</tt>
 * 
 * @author Carlos de la Torre
 * @date 28/12/15
 */
class Jugador {
    /**
     * @page repJugador Representación del TDA Jugador
     * 
     * Esta clase se creó para definir un nuevo tipo de dato abstracto y así
     * tratar a cada uno de los participantes con los siguientes atributos.
     * - Un dato tipo string para guardar el nombre.
     * - Un dato de tipo Persona para tener el personaje.
     * - Un dato de tipo Personas en el que tendrá los posibles personajes.
     * - Un dato de tipo Preguntas en la que estarán almacenadas las posibles
     * preguntas que se puedan realizar durante el juego.
     *
     * @section invJugador Invariante de la representación
     *
     * - El campo de nombre no podrá estar vacío y será un string.
     * - El campo que pertenece a Persona guardara todo lo necesario para el
     * personaje que tenga que encontrar el contrincante y este tiene que
     * existir como personaje y reunirá características sobre las preguntas
     * que le puedan hacer.
     * 
     * - El valor de las posibles_personajes  será la lista de posibles
     * personajes que haya en el juego y este no estar vacío y enumero máximo
     * de personaje será el indicado por el fichero de los personajes que nos
     * han pasado.
     * - Posibles_preguntas serán las mismas preguntas para todos los personajes
     * y serán n preguntas indicadas en el fichero inicial.
     *
     * @section faJugador Función de abstracción
     *
     * La función de abstracción de la clase Jugador tendrá que cumplir que para
     * cada jugador este tendrá un nombre asignado , un personaje que el
     * contrincante deberá adivinar y una serie de preguntas disponibles para
     * efectuar durante el juego.
     * 
     * fA : rep -> T.D.A. especificación.
     * 
     * Donde j es una instancia de objeto abstracto de tipo “Jugador” que sirve
     * para crear T.D.A.
     * 
     * fA : rep -> j.Nombre(siendo != vacio),
     *             j.personaje(siendo != vacio && ϵ en posible_personajes),
     *             j.posibles_personajes(siendo != vacio),
     *             j.posibles_preguntas(siendo != vacio).
     */
private:
    /**
     * @brief Cada Jugador tendrá un nombre asignado por defecto será Maquina
     */
    string nombre;
    /**
     * @brief El personaje será el que tenga que encontrar el contrincante
     */
    Persona personaje;
    /**
     * @brief Esta será la lista de posibles personajes que haya en el juego
     */
    Personas posibles_personajes;
    /**
     * @brief Lista de las posibles preguntas que podemos hacer para los
     * diferentes personajes
     */
    Preguntas posibles_preguntas;
    /**
     * @brief Esto determina si el jugador será humano o una maquina
     * true indica que es humano, maquina en caso contrario.
     */
    bool tipo;
    /**
     * @brief Sirve para pasar una cadena de caracteres todo a mayusculas
     * @param cadena Esta es la cadena que vamos a convertir
     */
    void toUpper(string &cadena);
public:
    /**
     * @brief Constructor por defecto.
     */
    Jugador();

    /**
     * @brief Constructor con parametros.
     * @param nom Nombre del jugador.
     */
    Jugador(Personas pers, Preguntas preg);

    /**
     * @brief Constructor de Copia
     * @param p Jugador desde donde se copiaran los datos
     */
    Jugador(Jugador &p);

    /**
     * @brief Destructor por defecto.
     */
    ~Jugador();

    /**
     * @brief Con este metodo asignamos un nombre al jugador y lo combertimos
     * en humano ya que por defecto se construye como maquina.
     * @param n Nombre del jugador
     */
    void setNombre(const string &n);
    
    /**
     * @brief Con esto mostramos el nombre del Jugador
     * @return Devuelve la cadena con el nombre
     */
    const string getNombre();

    /**
     * @brief Con este metodo podemos meter el personaje con el que jugara
     * el jugador tanto si es humano como si es maquina
     * @param per Objeto Personaje que será el que se le asigne al jugador
     */
    void setPersona(Persona &per);

    /**
     * @brief Nos muestra cual es el personaje asignado al jugador.
     * @return Devuelve un objeto Persona, con todos los datos.
     */
    Persona getPersona();

    /**
     * @brief Este método se usa para convertir al jugador en Humano puesto que
     * por defecto es una maquina, osea una IA.
     */
    void setHumano();
    
    /**
     * @brief Con esto podemos comprobar si el jugador es humano o no
     * @return Devuelve verdadero si es humano, falso en caso contrario
     */
    bool esHumano();

    /**
     * @brief Sirve para poder decirle al jugador cuales son las preguntas
     * que ya hemos usado para descubrir el personaje.
     * @param n Numero de la pregunta que ya hemos usado.
     * @param r Es la respuesta a la pregunta ya realizada.
     */
    void setPreguntaUsada(int n, string &r);
    
    /**
     * @brief Esta función imprime por pantalla las preguntas que puede hacer
     * el jugador, solo imprime las posibles, descarta las ya realizadas
     */
    Preguntas &getPreguntas();
    
    /**
     * @brief Elige una pregunta de las posibles dentro del que quedan en
     * el conjunto de preguntas teniendo en cuenta la entropia existente
     * con los personajes que quedan.
     * @return Devuelve un entero que corresponde a una pregunta.
     */
    const int elegirPregunta();
    
    /**
     * @brief Responde a la pregunta que corresponde con el numero especificado
     * @param n Es el numero de la pregunta que queremos tener la respuesta
     * @return Devuelve una cadena Si o No según corresponda
     */
    const string responderPregunta(int &n);

    /**
     * @brief Con este metodo buscamos cual es el nombre del personaje que la IA
     * a descubierto como el que esta oculto de su contrincante.
     * @return Devuelve la cadena de texto del nombre del personaje.
     */
    const string resuelvePersonaje();
    
    /**
     * @brief Con esto comparamos si la cadena de entrada, coincide con el 
     * nombre del personaje que tiene asignado el jugador
     * @param perso Es una cadena con el nombre del personaje a descubrir
     * @return Devuelve verdadero si el nombre coincide, falso en caso contrario
     */
    bool Resolver(string &perso);

    /**
     * @brief Operador de Entrada.
     * @param in
     * @param jug
     */
    friend std::istream &operator>>(std::istream &in, const Jugador &jug);

    /**
     * @brief Operador de Salida.
     * @param out
     * @param jug
     */
    friend std::ostream &operator<<(std::ostream &out, const Jugador &jug);
};

#if defined(VER1)
#include "ver1/Jugador.hxx"
#elif defined(VER2)
#include "ver2/Jugador.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* JUGADOR_H */

