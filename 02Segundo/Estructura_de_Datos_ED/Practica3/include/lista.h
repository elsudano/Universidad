/* 
 * File:   lista.h
 * Author: Carlos de la Torre
 *
 * Created on 23 de noviembre de 2015, 11:55
 *
 */
#ifndef __Lista__H
#define __Lista__H
/**
 * Incluimos el tipo de dato celda que va a utilizar nuestra Pila
 * con información del elemento mínimo puesto que este tipo de dato se
 * va a utilizar en cualquiera de las implementaciónes que vayamos a usar
 */
#include "basico.h"

using namespace std;

/**
 * @brief T.D.A Lista con cabecera\n
 * Una instancia @e c del tipo de dato abstracto Lista es una secuencia de datos de tipo T\n
 *       m1,m2,....mn\n
 * de manera que m1 es el primer elmento (posicion principio) y mn es el ultimo elemento (posicion final)\n
 * Si @e n=0 diremos que la lista esta vacía\n
 * El espacio requerido para almacenar la n datos de tipo T es O(n).\n
 *
 * @author : Carlos de la Torre
 * 
 * @date: Nobiembre 2015
 */
template <class T>
class Lista {
private:
    /**
     * @page repLISTA Representación del TDA Lista
     * Es un tipo de dato lineal que contiene una secuencia de elementos\n
     * especialmente diseñados para realizar inserciones y borrados y\n
     * para poder consultar hacia cualquier posición.\n
     * @section invLISTA Invariante de la representación
     * El invariante es:
     *  rep.cabecera == dato;\n
     *  rep.ele >= 0;\n
     * @section faLISTA Función de abstracción
     * Un objeto válido @e rep del TDA Pila_min representa al valor\n
     * F() = Resultado(rep.cabecera=T(),rep.ele=0);
     */
    Celda<T> * cabecera; /** Puntero al primer nodo de la lista */
    num ele; /** Número de elementos de la cola */

    /**
     * @brief Método auxiliar que se encarga de copiar una Lista con celdas
     * enlazadas con cabécera en otra Lista.
     * @param l esta será la Lista original que queremos copiar.
     */
    void copiar(const Lista<T> &l);

    /**
     * @brief Método auxiliar que se encarga vaciar la Lista. 
     */
    void limpiar();
public:
    /**
     * @brief Constructor por defecto, crea una Lista vacia
     * @retval Devuelve una Lista que tiene 0 elementos
     * \verbatim
     *   Lista<string> L = new Lista();
     *   cout << L;
     * \endverbatim
     */
    Lista();

    /**
     * @brief Constructor de copia, crea una copia de \e l sin modificar datos
     * @param l Es la Lista original que queremos copiar
     * @retval Devuelve una Lista identica a \e l
     * \verbatim
     *   Lista<string> L = new Lista();
     *   Lista<string> M = new Lista();
     *   Lista<string> L(M); 
     *   cout << L;
     * \endverbatim
     */
    Lista(const Lista<T> &l);

    /**
     * @brief Destructor por defecto.
     * \verbatim
     *   Lista<string> L = new Lista();
     *   cout << L;
     *   ~L;
     * \endverbatim
     */
    ~Lista();

    /**
     * @brief Operador de Asignación, introduce los datos que hay en \e l
     * @param l es una Lista con datos ya dentro para asignarlos al nuevo
     * @retval Devuelve una Lista con los datos de \e l
     * \verbatim
     *   Lista<string> L = new Lista();
     *   L.insertar("dato");
     *   Lista<string> M = L;
     *   cout << Z;
     * \endverbatim
     */
    Lista &operator=(const Lista<T> &l);

    /**
     * @brief Método para comprobar si la Lista contiene datos.
     * @retval Devuelve true si la Lista esta vacia, false en caso contrario.
     * \verbatim
     *  Lista<string> L = new Lista();
     *  L.insertar("Datos");
     *  if (L.vacia())
     *      cout << "Esta vacia";
     *  else
     *      cout << "Contiene datos";
     * \endverbatim
     */
    bool vacia() const;

    /**
     * @brief Muestra el elemento de la Lista que esta en la posición
     * indicada por \e pos pero el valor devuelto no se puede modificar
     * @param pos indica la posición de donde queremos ver el dato
     * \verbatim
     *   Lista<string> L = new Lista();
     *   L.insertar(4, "dato");
     *   cout << L.get(4);
     * \endverbatim
     */
    const T &get(num pos)const;

    /**
     * @brief Muestra el elemento de la Lista que esta en la posición indicada por \e pos
     * @param pos indica la posición de donde queremos ver el dato
     * \verbatim
     *   Lista<string> L = new Lista();
     *   L.insertar(4, "dato");
     *   cout << L.get(4);
     * \endverbatim
     */
    T &get(num pos);

    /**
     * @brief Añade el elemento a la Lista en la posición indicada por \e pos
     * @param pos indica la posición en donde se va a insertar el dato
     * @param v indica el valor del dato que se va a insertar
     * \verbatim
     *   Lista<string> L = new Lista();
     *   L.insertar(0, "dato");
     *   cout << L;
     * \endverbatim
     */
    void set(num pos, const T &v);

    /**
     * @brief Añade el elemento a la Lista en la posición indicada por \e pos
     * @param pos indica la posición en donde se va a insertar el dato
     * @param v indica el valor del dato que se va a insertar
     * \verbatim
     *   Lista<string> L = new Lista();
     *   L.insertar(0, "dato");
     *   cout << L;
     * \endverbatim
     */
    void insertar(num pos, const T &v);


    /**
     * @brief Elimina el elemento de la Lista indicado por \e pos
     * @param pos indica la posición que queremos borrar
     * \verbatim
     *   Lista<string> L = new Lista();
     *   L.insertar(0, "dato");
     *   L.borrar(0);
     *   cout << L;
     * \endverbatim
     */
    void borrar(num pos);

    /**
     * @brief Devuelve el número de elementos de la Lista
     * \verbatim
     *   Lista<string> L = new Lista();
     *   L.insertar("dato");
     *   cout << L.size();
     * \endverbatim
     */
    num size() const;

};
#include "lista.hxx"
#endif