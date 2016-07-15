/* 
 * File:   Persona.h
 *
 * Created on 24 de diciembre de 2015, 11:48
 */

#ifndef PERSONA_H
#define PERSONA_H

using namespace std;

/**
 * @brief T.D.A. Persona
 * 
 * \b Definición:
 * Una instancia \e a del tipo de dato abstracto Persona se puede construir
 * con los siguientes datos.
 * 
 * - Un dato Nombre que será el nombre del personaje al que representa.
 * - Un dato Path_photo que sera una cadena que contenga la ruta donde se\n
 * encuentra la imagen del personaje.
 * - Un dato Codigo que es una cadena de 0s y 1s que indican las respuestas\n
 * que se dan a las diferentes preguntas, 0 = NO, 1 = SI.
 * 
 * Para poder construir un objeto Persona valido como minimo hace falta un \e Nombre.
 * 
 * Para poder usar el tipo de dato Persona se debe incluir el fichero
 * <tt>\#include Persona.h</tt>
 * 
 * @author Carlos de la Torre
 * @date 28/12/15
 */
class Persona {
    /**
     * @page repPersona Representación del TDA Persona
     * 
     * Esta clase se creó para definir un nuevo tipo de dato abstracto y así
     * tratar a cada uno de los participantes con los siguientes atributos.
     * - Un dato de valor entero “num”para poder diferenciarlo de otro jugador.
     * - Un dato Nombre que será el nombre del personaje al que representa con
     * un string.
     * - Un dato Path_photo que será una cadena de string que contenga la ruta
     * donde se encuentra la imagen del personaje.
     * - Un dato Código que es una cadena de string de 0s y 1s que indican las
     * respuestas  que se dan a las diferentes preguntas, 0 = NO, 1 = SI.
     *
     * @section invPersona Invariante de la representación
     *
     * El campo num solo podrá recibir valores positivos hasta n personajes.
     * - El dato “Nombre” será una cadena de caracteres para la identificación
     * del jugador.
     * - El dato “Path_photo” será una cadena de caracteres donde son indicara
     * la ruta de la ubicación de su imagen.
     * - El dato código será un string compuesto de 0 y 1 para la identificación
     * de camino realizado a través de las preguntas.
     *
     * @section faPersona Función de abstracción
     *
     * La función de abstracción de esta clase es sencilla puesto que suponiendo
     * que p es un objeto de tipo Persona podemos mostrar el contenido de sus
     * campo sin ningún problema.
     * 
     * fA : rep -> T.D.A. especificación.
     * 
     * Donde p es una instancia de objeto abstracto de tipo “Persona” que sirve
     * para crear T.D.A.
     * 
     * fA : rep -> p.num, p.Nombre, p.Path_photo, p.Codigo
     */
private:
    int num;
    string nombre;
    string path_photo;
    string codigo;
public:
    /**
     * @brief Constructor por defecto.
     * @note Al construir por defecto se crea un objeto con el nombre\n
     * \e Maquina y los demas datos con cadenas vacias.\n
     */
    Persona();
    
    /**
     * @brief Constructor por parametros.
     * @param nom Nombre de la persona.
     * @param path Ruta donde se encuentra la imagen de la persona.
     */
    Persona(int num, string nom, string path);
    
    /**
     * @brief Constructor por parametros.
     * @param nom Nombre de la persona.
     * @param path Ruta donde se encuentra la imagen de la persona.
     * @param cod Codigo con la secuencia de las respuestas correctas.
     */
    Persona(int num, string nom, string path, string cod);
    
    /**
     * @brief Constructor de Copia.
     * @param p Persona desde la que se va a copiar la información
     */
    Persona(const Persona &p);
    
    /**
     * @brief Destructor por defecto.
     * @note Cuando se destruye el objeto se eliminan todas las cadenas.
     */
    ~Persona();
    
    /**
     * @brief Sirve para poder saber cual es el numero de la persona
     * @return Devuelve el numero entero asignado a la persona.
     */
    int GetNumero()const;
    
    /**
     * @brief Muestra el nombre que corresponde a la persona en donde estamos.
     * @return Devuelve una cadena de caracteres con el nombre.
     */
    string GetNombre()const;
    
    /**
     * @brief Muestra la ruta donde se encuentra la foto de la persona
     * @return Devuelve una cadena de caracteres con la ruta de la imagen.
     */
    string GetPath()const;
    
    /**
     * @brief Muestra el codigo de la secuencia de respuestas para llegar a la persona.
     * @return Devuelve una cadena de caracteres con la secuencia de respuestas.
     */
    string GetCodigo()const;
    
    /**
     * @brief Permite introducir el codigo de respuestas correctas codificadas
     * como 0s y 1s, de tal manera que el 0 es un no y el 1 es un si, de esta
     * manera podemos saber cuales son las respuestas correctas para esa persona
     * @param cod Cadena de caracteres 0 y 1s
     */
    void SetCodigoRespuestas(string &cod);
    
    /**
     * @brief Operador de Salida.
     * @param out Es el flujo de salida donde se muestran los datos formateados.
     * @param per Es el objeto que contiene los datos que queremos mostrar.
     */
    friend std::ostream &operator<<(std::ostream &out, const Persona &per);
};
#if defined(VER1)
#include "ver1/Persona.hxx"
#elif defined(VER2)
#include "ver2/Persona.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VER1 o VER2
#endif
#endif /* PERSONA_H */

