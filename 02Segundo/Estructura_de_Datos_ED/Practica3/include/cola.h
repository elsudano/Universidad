/* 
 * File:   cola.h
 * Author: Carlos de la Torre
 *
 * Created on 22 de noviembre de 2015, 17:33
 *
 */

#ifndef __Cola_H__
#define __Cola_H__

/**
 * Incluimos el tipo de dato celda que va a utilizar nuestra Pila
 * con información del elemento mínimo puesto que este tipo de dato se
 * va a utilizar en cualquiera de las implementaciónes que vayamos a usar
 */
#include "basico.h"

using namespace std;

/**
 *  @brief T.D.A. Cola\n
 * Una instancia @e c del tipo de dato abstracto Cola sobre un dominio @e T es\n
 * una sucesión finita de elementos del mismo con un funcionamiento @e FIFO\n
 * (First In, First Out)
 *
 * @author Carlos de la Torre
 * @date Noviembre 2015
 */
template <class T>
class Cola {
private:
    /**
     * @page repCOLA Representación del TDA Cola
     * En una cola, las operaciones de inserción tienen\n
     * lugar en uno de los extremos, denominado @e final de la cola, mientras que\n
     * el borrado y consulta se lleva a cabo en el otro extremo, denominado\n
     * @e frente de la cola. Una cola de longitud @e n la denotamos\n
     * - <a1,a2,a3,..,an<\n
     * @section invCOLA Invariante de la representación
     * El invariante es:
     *  rep.primera->ele == datos que se almacenaron en primer lugar;\n
     *  rep.ultima->ele == datos que se acaban de almacenar;\n
     *  rep.elem == cantidad de elementos que se han almacenados;\n
     * @section faCOLA Función de abstracción
     * Un objeto válido @e rep del TDA Cola representa al valor\n
     * F() = Resultado(rep.primera->ele="primerdato",rep.ultima->ele="ultimodato",rep.ele>=0);
     */

    Celda<T> * primera; ///< Puntero al primer nodo de la cola.
    Celda<T> * ultima; ///< Puntero al último nodo de la cola.
    num elem; ///< Número de elementos de la cola.

    /**
     * @brief Método auxiliar que se encarga de copiar una Cola en otra Cola
     * con la misma implementación.
     * @param c esta será la Cola original que queremos copiar.
     */
    void copiar(const Cola<T> &c);

    /**
     * @brief Método auxiliar que se encarga vaciar la Cola
     */
    void limpiar();
public:

    /**
     * @brief Constructor por defecto, crea una Cola vacia
     * @retval Devuelve una Cola que tiene 0 elementos
     * \verbatim
     *   Cola<string> C = new Cola();
     *   cout << C;
     * \endverbatim
     */
    Cola();

    /**
     * @brief Constructor de copia, crea una copia de \e c
     * @param c Es la Cola original que queremos copiar
     * @retval Devuelve una Cola identica a \e c
     * \verbatim
     *   Cola<string> C = new Cola();
     *   Cola<string> J = new Cola();
     *   Cola<string> J(C); 
     *   cout << J;
     * \endverbatim
     */
    Cola(const Cola<T> &c);

    /**
     * @brief Destructor por defecto.
     * \verbatim
     *   Cola<string> C = new Cola();
     *   cout << C;
     *   ~C;
     * \endverbatim
     */
    ~Cola();

    /**
     * @brief Operador de Asignación, introduce los datos que hay en \e c
     * @param c es una Cola con datos ya dentro para asignarlos al nuevo
     * @retval Devuelve una Cola con los datos de \e c
     * \verbatim
     *   Cola<string> C = new Cola();
     *   C.poner("dato");
     *   Cola<string> Z = C;
     *   cout << Z;
     * \endverbatim
     */
    Cola<T> &operator=(const Cola<T> &c);
    
    /**
     * @brief Operador de Comparación, introduce los datos que hay en \e c
     * @param c es una Cola con datos que vamos a comparar
     * @retval Devuelve verdadero si las Colas son iguales, falso en caso
     * contrario
     * \verbatim
     *   Cola<string> C = new Cola();
     *   C.poner("dato");
     *   if (Z == C)
     *       cout << "Son Iguales";
     *   else
     *       cout << "Son Diferentes";
     *   cout << Z;
     * \endverbatim
     
     */
    bool &operator==(const Cola<T> &c);

    /**
     * @brief Método para comprobar si la Cola contiene datos.
     * @retval Devuelve true si la Cola esta vacia, false en caso contrario.
     * \verbatim
     *  Cola<string> C = new Cola();
     *  C.poner("Datos");
     *  if (C.vacia())
     *      cout << "Esta vacia";
     *  else
     *      cout << "Contiene datos";
     * \endverbatim
     */
    bool vacia() const;

    /**
     * 
     */
    /**
     * @brief Devuelve el elemento del frente de la Cola
     * @note Esta operación tiene una eficiencia de O(1)
     * @retval Devuelve un elemento del mismo tipo que se especifico en plantilla
     * \verbatim
     *   Cola<string> C = new Cola();
     *   C.poner("dato");
     *   cout << C.frente();
     * \endverbatim
     */
    T &frente();

    /**
     * @brief Devuelve el elemento del frente de la Cola pero constante
     * @note Esta operación tiene una eficiencia de O(1)
     * @retval Devuelve un elemento del mismo tipo que se especifico en plantilla
     * \verbatim
     *   Cola<string> C = new Cola();
     *   C.poner("dato");
     *   cout << C.frente();
     * \endverbatim
     */
    const T &frente() const;

    /**
     * @brief Añade un elemento por el final de la Cola
     * @param c Será el elemento que se añadira al final de la Cola
     * \verbatim
     *   Cola<string> C = new Cola();
     *   C.poner("dato");
     *   cout << C;
     * \endverbatim
     */
    void poner(const T &c);

    /**
     * @brief Elimina el elemento del frente de la Cola
     * \verbatim
     *   Cola<string> C = new Cola();
     *   C.poner("dato");
     *   C.quitar();
     *   cout << C;
     * \endverbatim
     */
    void quitar();

    /**
     * @brief Devuelve el número de elementos de la Cola
     * \verbatim
     *   Cola<string> C = new Cola();
     *   C.poner("dato");
     *   cout << C.size();
     * \endverbatim
     */
    num size() const;
};

#include <cola.hxx>
#endif // __Cola_H__
