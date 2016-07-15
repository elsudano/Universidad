/* 
 * File:   vd.h
 * Author: Carlos de la Torre
 *
 * Created on 22 de noviembre de 2015, 13:40
 *
 */
#ifndef __VD__H
#define __VD__H

/**
 * Incluimos el tipo de dato celda que va a utilizar nuestra Pila
 * con información del elemento mínimo puesto que este tipo de dato se
 * va a utilizar en cualquiera de las implementaciónes que vayamos a usar
 */
#include "basico.h"

using namespace std;

/**
 * @brief T.D.A VD\n
 * Una instancia @e c del tipo de dato abstracto VD es una secuencia de datos de tipo T\n
 *       m1,m2,....mn\n
 * de manera que m1 es el primer elmento (posicion principio) y mn es el ultimo elemento (posicion final)\n
 * El espacio requerido para almacenar la n datos de tipo T es O(n).\n
 *
 * @author : Carlos de la Torre
 * 
 * @date: Noviembre 2015
 */

template <class T>
class VD {
private:
    /**
     * @page repVD Representación del TDA Vector Dinamico
     * Este tipo de datos Vector Dinamico se encarga de almacenar los datos en\n
     * la memoria uno detras de otro, para recorrerlos simplemente hay que\n
     * ir avanzando en una unidad y podremos ver todos los elementos.\n
     * @section invVD Invariante de la representación
     * El invariante es:
     *  rep.datos<T> == datos almacenados;\n
     *  rep.ele >= 0;\n
     *  rep.tam >= rep.ele;\n
     * @section faVD Función de abstracción
     * Un objeto válido @e rep del TDA Pregunta representa al valor\n
     * F() = Resultado(rep.datos="",rep.ele=0,rep.tam=1);
     */
    T * datos_vd; /** Vector de Elementos que contienen Información y Mínimo. */
    num ele; /** Cantidad de elementos que tiene la pila. */
    num tam; /** Auxiliar que reserva el tamaño del vector. */

    /**
     * @brief Método auxiliar que se utiliza para redimencionar el tamaño del Vector.
     * @param tam es el numero de unidades que aumentaremos o disminuiremos el Vector.
     */
    void resize(num tam);

    /**
     * @brief Método auxiliar que se encarga de copiar un vector a otro
     * de vector dinámico.
     * @param v esta será el vector original que queremos copiar.
     */
    void copiar(const VD<T> &v);

    /**
     * @brief Método auxiliar que se encarga vaciar el Vector
     */
    void limpiar();
public:
    /**
     * @brief Constructor por defecto, crea un Vector vacio
     * @param tam parametro que indica la cantidad de memoria a reservar 
     * @retval Devuelve un Vector que tiene 0 elementos y su minimo es 0
     * \verbatim
     *   VD<string> V = new VD();
     *   cout << V;
     * \endverbatim
     */
    VD(num tam = MEM_MIN);


    /**
     * @brief Constructor de copia, crea una copia de \e v
     * @param \e v Es el Vector original que queremos copiar
     * @retval Devuelve un Vector identicaoa \e v
     * \verbatim
     *   VD<string> V = new VD();
     *   VD J(V); 
     *   cout << J;
     * \endverbatim
     */
    VD(const VD<T> &v);

    /**
     * @brief Destructor por defecto.
     * \verbatim
     *   VD<string> V = new VD();
     *   cout << V;
     *   ~V;
     * \endverbatim
     */
    ~VD();

    /**
     * @brief Operador de Asignación, introduce los datos que hay en \e v
     * @param v es un vector con datos ya dentro para asignarlos al nuevo
     * @retval Devuelve un vector con los datos de \e v
     * \verbatim
     *   VD<string> V = new VD();
     *   V.poner("dato");
     *   VD<string> Z = V;
     *   cout << Z;
     * \endverbatim
     */
    VD<T> &operator=(const VD<T> &v);

    /**
     * @brief Devuelve el elemento de la posicion pos
     * @param pos posición a la que queremos acceder.
     * @retval devuelve un valor de tipo T con el dato de la posición
     * \verbatim
     *   VD<string> V = new VD();
     *   V.poner("dato");
     *   cout << V.get(0);
     * \endverbatim
     */
    T get(num pos) const;

    /**
     * @brief Devuelve el elemento de la posicion pos
     * @note con esta NO podemos modificar el dato que nos devuelve.
     * @param pos posición a la que queremos acceder.
     * @retval devuelve un valor de tipo T con el dato de la posición
     * \verbatim
     *   VD<string> V = new VD();
     *   V.poner("dato");
     *   cout << V[0];
     * \endverbatim
     */
    const T &operator[](num pos) const;

    /**
     * @brief Devuelve el elemento de la posicion pos
     * @note con esta si podemos modificar el dato que nos devuelve.
     * @param pos posición a la que queremos acceder.
     * @retval devuelve un valor de tipo T con el dato de la posición
     * \verbatim
     *   VD<string> V = new VD();
     *   V.poner("dato");
     *   cout << V[0];
     * \endverbatim
     */
    T &operator[](num pos);

    /**
     * @brief Devuelve el número de elementos almacenadas
     * \verbatim
     *   VD<string> V = new VD();
     *   V.poner("dato");
     *   cout << V.size();
     * \endverbatim
     */
    num size() const;

    /**
     * @brief Elimina el elemento en la posicion pos
     * @note El numero de elementos n se decrementa en uno. Eficiencia O(n)
     * \verbatim
     *   VD<string> V = new VD();
     *   V.poner("dato");
     *   cout << V.borrar(5);
     * \endverbatim
     */
    void borrar(num pos);

    /**
     * @brief Inserta un nuevo elemento en un posicion
     * @param nuevo: elemento a insertar
     * @param pos: posicion donde se inserta
     * \verbatim
     *   VD<string> V = new VD();
     *   V.poner("dato");
     *   cout << V.insertar(3);
     * \endverbatim
     */
    void insertar(const T &nuevo, num pos);
};
#include "vd.hxx"
#endif
