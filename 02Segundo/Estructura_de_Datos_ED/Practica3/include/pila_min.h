/* 
 * File:   pila_min.h
 * Author: Carlos de la Torre
 *
 * Created on 22 de noviembre de 2015, 9:40
 */

#ifndef PILA_MIN_H
#define PILA_MIN_H
/*
 * Quitar esta linea para poder decidir que versión compilar desde el
 * Makefile simplemente usando el modificar -D, ponemos cualquiera de
 * las tres opciones (VD, COLA, LISTA) y se compilara de una manera o de otra.
 */
/** #define TIPO_VD */

#if defined(TIPO_VD)
#include "vd.h"
#elif defined(TIPO_LISTA)
#include "lista.h"
#elif defined(TIPO_COLA)
#include "cola.h"
#endif

/**
 * @brief T.D.A. Pila_min\n
 * Una instancia de Pila_min se encarga de gestionar un contenedor de tipo pila\n
 * pero con la ventaja de que podemos consultar cual es el elemento de menor\n
 * valor con una eficiencia de O(1)\n
 * Un ejemplo de su uso:\n
 * @include usopilas_min.cpp
 *
 * @author Carlos de la Torre
 * @date Noviembre 2015
 */
template <class T>
class Pila_min {
private:
#if defined(TIPO_VD)
    VD<Elemento<T> > datos_pila_min;
#elif defined(TIPO_LISTA)
    Lista<Elemento<T> > datos_pila_min;
#elif defined(TIPO_COLA)
    Cola<Elemento<T> > datos_pila_min;
#endif
public:
    /**
     * @brief Constructor por defecto, crea una Pila_min vacia
     * @retval Devuelve una Pila que tiene 0 elementos y su minimo es 0
     * @post después de esto se pueden añadir elémentos a la Pila
     * \verbatim
     *   Pila_min<string> P = new Pila_min();
     *   cout << P;
     * \endverbatim
     */
    Pila_min();
    
    /**
     * @brief Constructor de copia, crea una copia de \e pm
     * @param pm Es la Pila original que queremos copiar
     * @retval Devuelve una Pila identica a \e pm
     * \verbatim
     *   Pila_min<string> P = new Pila_min();
     *   Pila_min J(P); 
     *   cout << J;
     * \endverbatim
     */
    Pila_min(const Pila_min<T> &pm);
    
    /**
     * @brief Destructor por defecto.
     * \verbatim
     *   Pila_min<string> P = new Pila_min(); 
     *   cout << P;
     *   ~P;
     * \endverbatim
     */
    ~Pila_min();
    
    /**
     * @brief Operador de Asignación, introduce los datos que hay en \e da
     * @param da es del tipo de datos que hayamos especificado
     * @retval Devuelve una Pila con el dato introducido en el tope
     * \verbatim
     *   Pila_min<string> P;
     *   P = "Dato";
     *   cout << P;
     * \endverbatim
     */
    Pila_min<T> &operator=(const Pila_min<T> &pm);
    
    /**
     * @brief Operador de Comparación, compara si dos Pilas son iguales
     * @param pm es la Pila que queremos comparar
     * @retval Devuelve true si las dos Preguntas son iguales, false en caso contrario
     * \verbatim
     *  Pila_min<string> P = new Pila_min();
     *  P = "Datos";
     *  Pila_min<string> Z = new Pila_min();
     *  Z = "SinDatos";
     *  if (P == Z)
     *      cout << "Son iguales";
     *  else
     *      cout << "Son diferentes";
     * \endverbatim
     */
    bool operator==(const Pila_min<T> &pm);
    
    /**
     * @brief Operador de Consulta, muestra el tope de la Pila
     * @retval Devuelve un valor del tipo definido en la pila
     * \verbatim
     *  Pila_min<string> P = new Pila_min();
     *  P = "Datos";
     *  cout << P.tope();
     * \endverbatim
     */
    T tope();
    
    /**
     * @brief Método de Consulta, muestra cual es el valor minimo de la Pila
     * @retval Devuelve un valor del tipo definido en la pila
     * \verbatim
     *  Pila_min<string> P = new Pila_min();
     *  P = "Datos";
     *  cout << P.tope();
     * \endverbatim
     */
    T minimo();
    
    /**
     * @brief Método de Asignación, introduce el dato dentro de la Pila
     * @param dato es el dato que queremos alojar en el tope de la Pila
     * \verbatim
     *  Pila_min<string> P = new Pila_min();
     *  P.poner("Datos");
     *  cout << P.tope();
     * \endverbatim
     */
    void poner(T dato);
    
    /**
     * @brief Método para Borrar el dato que se encuentra en el tope de la Pila
     * \verbatim
     *  Pila_min<string> P = new Pila_min();
     *  P.quitar();
     *  cout << P.tope();
     * \endverbatim
     */
    void quitar();
    
    /**
     * @brief Método para comprobar si la Pila contiene datos.
     * @retval Devuelve true si la Pila esta vacia, false en caso contrario.
     * \verbatim
     *  Pila_min<string> P = new Pila_min();
     *  P.poner("Datos");
     *  if (P.vacia())
     *      cout << "Esta vacia";
     *  else
     *      cout << "Contiene datos";
     * \endverbatim
     */
    bool vacia()const;
    
    /**
     * @brief Método consultar el tamaño de la Pila
     * \verbatim
     *  Pila_min<string> P = new Pila_min();
     *  cout << P.size();
     * \endverbatim
     */
    num size()const;
    
    /**
     * @brief Entrada de una Pregunta desde istream
     * @param is stream de entrada
     * @param pm Pila que recibe el valor
     * @retval Devuelve la Pila con el dato introducido
     * @pre Solo se puede introducir un dato a la vez
     * \verbatim
     * Uso:
     *    Pila_min<string> P;
     *    P >> "Dato";
     *    cout << P;
     * \endverbatim
     */
    friend istream &operator>>(istream &is, Pila_min<T> &pm);
    
    /**
     * @brief Imprime los datos de la Pila por la salida estandar ostream
     * @param os stream de salida
     * @param pm Pila que tiene los datos que queremos imprimir
     * \verbatim
     * Uso:
     *    Pila_min<string> P;
     *    P.poner("Datos");
     *    cout << P;
     * \endverbatim
     */
    friend ostream &operator<<(ostream &os, const Pila_min<T> &pm);
};

#if defined(TIPO_VD)
#include "pila_min_vd.hxx"
#elif defined(TIPO_LISTA)
#include "pila_min_lista.hxx"
#elif defined(TIPO_COLA)
#include "pila_min_cola.hxx"
#else
#error !!! HAY QUE INDICAR LA VERSION DE LA REPRESENTACION A COMPILAR VD, LISTA o COLA ¡¡¡
#error para indicar cual queremos compilar usaremos el parametro -D [VD,LISTA,COLA] del gcc
#endif

#endif /* PILA_MIN_H */

