/*
  $Id: bintree.h,v 1.2 2006/05/18 10:14:06 mgs Exp $  
*/
/*
  $Log: bintree.h,v $
  Revision 1.2  2006/05/18 10:14:06  mgs
  *** empty log message ***

  Revision 1.1.1.1  2006/05/17 12:25:32  jmbs
  Versi� Inicial

*/

//-*-Mode: C++;-*-

#ifndef __BINTREE_H__
#define __BINTREE_H__

/**
   TDA bintree.

   Representa un árbol binario con nodos etiquetados con datos del tipo T.

   T debe tener definidas las operaciones:

   - T & operator=(const T & e);
   - bool operator!=(const T & e);
   - bool opertaor==(const T & e);

   Son mutables.
   Residen en memoria din�ica.
*/

#include <queue>


template <typename T>
class bintree {
public:
  class node;

  typedef unsigned int size_type;
  
  /**
     @brief Constructor primitivo por defecto.
     
     Crea un árbol nulo.
  */
  bintree();

  /**
     @brief Constructor primitivo.
     
     @param e Etiqueta para la raíz.
     
     Crea un árbol con un nico nodo etiquetado con e.
  */
  bintree(const T & e);

  /**
     @brief Constructor de copia.
     
     @param a árbol que se copia.
     
     Crea un árbol duplicado exacto de a.
  */
  bintree (const bintree<T> & a);

  /**
     @brief Reemplaza el receptor por una copia de subárbol.

     @param a Arbol desde el que se copia.
     @param n nodo raíz del subárbol que se copia.

     El receptor se hace nulo y despu� se le asigna una copia
     del subárbol de a cuya raíz es n.
  */
  void assign_subtree(const bintree<T> & a, node n);
/**
     @brief Reemplaza un subárbol del receptor por una copia de subárbol.
     
     @param pos es un node  en this sobre el que se realiza la copia
     @param a Arbol desde el que se copia, a!=this.
     @param n nodo raíz del subárbol que se copia.

     El nodo receptor se hace nulo y después se le asigna una copia
     del subárbol de a cuya raíz es n.
*/
     void replace_subtree(node pos, const bintree & a, node n);
  /**
     @brief Destructor.

     Destruye el receptor liberando los recursos que ocupaba.
  */
  ~bintree();

  /**
     @brief Operador de asignaci�.
     
     @param a: árbol que se asigna.
     
     Destruye el contenido previo del receptor y le asigna un
     duplicado de a.
  */
  bintree<T> & operator=(const bintree<T> & a);

  /**
     @brief Obtener el nodo raíz.
     
     @return nodo raíz del receptor.
  */
  node root() const;
  
  /**
     @brief Podar el subárbol a la izquierda de un nodo.

     @param n: nodo del receptor. !n.null().
     @param dest: subárbol a la izquierda de n. Es MODIFICADO.

     Desconecta el subárbol a la izquierda de n, que pasa a
     ser un árbol nulo. El subárbol anterior se devuelve sobre
     dest.
  */
  void prune_left(node n, bintree<T> & dest);

  /**
     @brief Podar el subárbol a la derecha de un nodo.

     @param n: nodo del receptor. !n.null().
     @param dest: subárbol a la derecha de n. Es MODIFICADO.

     Desconecta el subárbol a la derecha de n, que pasa a
     ser un árbol nulo. El subárbol anterior se devuelve sobre
     dest.
  */
  void prune_right(node n, bintree<T> & dest);

  /**
     @brief Insertar un nodo como hijo a la izquierda de un nodo.
     
     @param n: nodo del receptor. !n.null().
     @param e: etiqueta del nuevo nodo.
     
     Desconecta y destruye el subárbol a la izquierda de n, inserta
     un nuevo nodo con etiqueta e como hijo a la izquierda
  */
  void insert_left(const bintree<T>::node & n, const T & e);

  /**
     @brief Insertar un árbol como subárbol a la izquierda de un nodo.

     @param n: nodo del receptor. n != nodo_nulo.
     @param rama: subárbol que se inserta. Es MODIFICADO.

     Desconecta y destruye el subárbol a la izquierda de n, le
     asigna el valor de rama como nuevo subárbol a la izquierda
     y rama se hace árbol nulo.
  */
  void insert_left(node n, bintree<T> & rama);

  /**
     @brief Insertar un nodo como hijo a la derecha de un nodo.

     @param n: nodo del receptor. !n.Nulo().
     @param e: etiqueta del nuevo nodo.

     Desconecta y destruye el subárbol a la derecha de n, inserta
     un nuevo nodo con etiqueta e como hijo a la derecha
  */
  void insert_right(node n, const T & e);

  /**
     @brief Insertar un árbol como subárbol a la derecha de un nodo.

     @param n: nodo del receptor. !n.Nulo().
     @param rama: subárbol que se inserta. Es MODIFICADO.

     Desconecta y destruye el subárbol a la izquierda de n, le
     asigna el valor de rama como nuevo subárbol a la derecha
     y rama se hace árbol nulo.
  */
  void insert_right(node n, bintree<T> & rama);

  /**
     @brief Hace nulo un árbol.

     Destruye todos los nodos del árbol receptor y lo hace
     un árbol nulo.
  */
  void clear();

  /**
     @brief Obtiene el nmero de nodos.

     @return nmero de nodos del receptor.
  */
  size_type size() const;

  /**
     @brief Comprueba si un árbol est�vac� (es nulo).

     @return true, si el receptor est�vac� (es nulo).
             false, en otro caso.
  */
  bool empty() const;


  /**
     @brief Operador de comparaci� de igualdad.

     @param a: árbol con que se compara el receptor.
     
     @return  true, si el receptor es igual, en estructura y
                 etiquetas a a.
              false, en otro caso.
  */
  bool operator==(const bintree<T> & a) const;

  /**
     @brief Operador de comparaci� de desigualdad.

     @param a: árbol con que se compara el receptor.

     @return  true, si el receptor no es igual, en estructura o
                    etiquetas a a.
              false, en otro caso.
  */
  bool operator!=(const bintree<T> & a) const;

  /**
     Clase iterator para recorrer el árbol en PreOrden
  */
  class preorderiterator {
  public:
    preorderiterator();
    preorderiterator(const preorderiterator & i);
    bool operator!=(const preorderiterator & i) const;
    bool operator==(const preorderiterator & i) const;
    preorderiterator & operator=(const preorderiterator & i);
    T & operator*();
    preorderiterator & operator++();
    preorderiterator & operator++(int){return ++(*this);};;
  private:
    node elnodo;
    preorderiterator(node n);
    friend class bintree<T>;
  };

  preorderiterator beginPreorder();
  preorderiterator endPreorder();

  class const_preorderiterator
  {
  public:
    const_preorderiterator();
    const_preorderiterator(const const_preorderiterator & i);
    bool operator!=(const const_preorderiterator & i) const;
    bool operator==(const const_preorderiterator & i) const;
    const_preorderiterator & operator=(const const_preorderiterator & i);
    const T & operator*() const;
    const_preorderiterator & operator++();
    const_preorderiterator & operator++(int){return ++(*this);};;
  private:
    node elnodo;
    const_preorderiterator(node n);
    friend class bintree<T>;
 };

  const_preorderiterator beginPreorder() const;
  const_preorderiterator endPreorder() const;



  /**
     Clase iterator para recorrer el árbol en Inorden
  */

 class inorderiterator
 {
  public:
    inorderiterator();
    inorderiterator(const inorderiterator & i);
    bool operator!=(const inorderiterator & i) const;
    bool operator==(const inorderiterator & i) const;
    inorderiterator & operator=(const inorderiterator & i);
    T & operator*();
    inorderiterator & operator++();
    inorderiterator & operator++(int){return ++(*this);};;
  private:
    node elnodo;
    inorderiterator(node n);
    friend class bintree<T>;
 };

  inorderiterator beginInorder();
  inorderiterator endInorder();

class const_inorderiterator
 {
  public:
    const_inorderiterator();
    const_inorderiterator(const const_inorderiterator & i);
    bool operator!=(const const_inorderiterator & i) const;
    bool operator==(const const_inorderiterator & i) const;
    const_inorderiterator & operator=(const const_inorderiterator & i);
    const T & operator*() const;
    const_inorderiterator & operator++();
    const_inorderiterator & operator++(int){return ++(*this);};;
  private:
    node elnodo;
    const_inorderiterator(node n);
    friend class bintree<T>;
 };

  const_inorderiterator beginInorder() const;
  const_inorderiterator endInorder() const;


  /**
     Clase iterator para recorrer el árbol en PostOrden
  */

 class postorderiterator
 {
  public:
    postorderiterator();
    postorderiterator(const postorderiterator & i);
    bool operator!=(const postorderiterator & i) const;
    bool operator==(const postorderiterator & i) const;
    postorderiterator & operator=(const postorderiterator & i);
    T & operator*();
    postorderiterator & operator++();
    postorderiterator & operator++(int){return ++(*this);};;
  private:
    node elnodo;
    postorderiterator(node n);
    friend class bintree<T>;
  };

  postorderiterator beginPostorder();
  postorderiterator endPostorder();

class const_postorderiterator
 {
  public:
    const_postorderiterator();
    bool operator!=(const const_postorderiterator & i) const;
    bool operator==(const const_postorderiterator & i) const;
    const T & operator*() const;
    const_postorderiterator & operator=(const const_postorderiterator & i);
    const_postorderiterator & operator++();
    const_postorderiterator & operator++(int){return ++(*this);};;
  private:
    node elnodo;
    const_postorderiterator(node n);
    friend class bintree<T>;
  };
  const_postorderiterator beginPostorder() const;
  const_postorderiterator endPostorder() const;


  /**
     Clase iterator para recorrer el árbol por niveles
  */

 class leveliterator
 {
  public:
    leveliterator();
    leveliterator(const leveliterator & i);
    bool operator!=(const leveliterator & i) const;
    bool operator==(const leveliterator & i) const;
    leveliterator & operator=(const leveliterator & i)
    {cola_Nodos= i.cola_Nodos; return *this;};
    T & operator*();
    leveliterator & operator++();
    leveliterator & operator++(int){return ++(*this);};;
  private:
    std::queue<node> cola_Nodos;
    leveliterator(node n);
    friend class bintree<T>;
  };

  leveliterator beginLevel();
  leveliterator endLevel();

class const_leveliterator
 {
  public:
    const_leveliterator();
    bool operator!=(const const_leveliterator & i) const;
    bool operator==(const const_leveliterator & i) const;
    const_leveliterator & operator=(const const_leveliterator & i) {cola_Nodos= i.cola_Nodos; return *this;};
    const T & operator*() const;
    const_leveliterator & operator++();
    const_leveliterator& operator++(int){return ++(*this);};;
  private:
    std::queue<node> cola_Nodos;
    const_leveliterator(node n);
    friend class bintree<T>;
  };

  const_leveliterator beginLevel() const;
  const_leveliterator endLevel() const ;


private:

  // Funciones auxiliares
  /**
     @brief Destruir subárbol.

     @param n: nodo raíz del subárbol que se destruye.
     @doc:
     Destruye el subárbol cuya raíz es n.
  */
  void destroy(bintree<T>::node n);
  
  
  /**
     @brief Copia subárbol.

     @param dest: nodo sobre el que se copia. dest.null().
            Es MODIFICADO.
     @param orig: raíz del subárbol que se copia.

     @doc
     Destruye el subárbol con raíz en dest. Sobre �te realiza
     un duplicado del subárbol con raíz en orig.
  */
  void copy(node & dest, const node &orig);
  
  
  /**
     @brief Cuenta el nmero de nodos.
    
     @param n: raíz del subárbol a contar.

     @return devuelve el nmero de nodos del subárbol que
             tiene n como raíz.

     Cuenta el nmero de nodos en el subárbol cuuya raíz es n.
  */
  int count(node n) const;
    
  /**
     @brief Comparaci� de igualdad.

     @param n1: raiz del primer subárbol.
     @param n2: raiz del segundo subárbol.

     @return true, si los dos subárboles son iguales, en
                   estructura y etiquetas.
             false, en otro caso.
  */
  bool equals(node n1, node n2) const;

  // Representaci�
  node laraiz;
  size_type num_nodos;

  
  /** 
      TDA nodo.
      Modela los nodos del árbol binario.
  */
  
  class nodewrapper {
  public:
    nodewrapper();
    nodewrapper(const T & e);

    T etiqueta;
    node pad;
    node izda;
    node dcha;
  };
  
public:

  class node {
  public:
    /**
       @brief Constructor primitivo
    */
    node();
      
    /**
       @brief Constructor primitivo
       @param e: Etiqueta del nodo
    */
    node(const T & e);

    /**
       @brief Constructor de copia
       @param n: Nodo que se copia
    */
    node(const node & n);
      
    /**
       @brief Determina si el nodo es nulo
    */
    bool null() const;
      
    /**
       @brief Devuelve el padre del nodo receptor
       @pre !null()
    */
    node parent() const;
      
    /**
       @brief Devuelve el hizo izquierdo del nodo receptor
       @pre !null()
    */
    node left() const;
      
    /**
       @brief Devuelve el hizo izquierdo del nodo receptor
       @pre !null()
    */
    node right() const;
      
    /**
       @brief Devuelve la etiqueta del nodo
       @pre Si se usa como consultor, !null()
    */
    T & operator*();
      
    /**
       @brief Devuelve la etiqueta del nodo
       @pre !null()
      */
    const T & operator*() const;
      
    /**
       @brief Operador de asignaci�
       @param n: el nodo a asignar
    */
    node & operator=(const node & n);
      
    /**
       @brief Operador de comparaci� de igualdad
       @param n: el nodo con el que se compara
    */
    bool operator==(const node & n) const;
      
    /**
       @brief Operador de comparaci� de desigualdad
       @param n: el nodo con el que se compara
    */
    bool operator!=(const node & n) const;
      
  private:
    // Las siguientes funciones son privadas para uso exclusivo en bintree
    friend class bintree<T>;

     /**
       @brief Elimina el nodo actual
       @pre !null() y es una hoja
    */
    void remove();

    /**
       @brief Coloca el nodo padre de un nodo
       @param n El nodo que ser~ padre del receptor. No nulo.
    */
    inline void parent(node n);

    /**
       @brief Coloca el nodo hijo izquierda de un nodo
       @param n El nodo que ser~ hijo izquierdo del receptor. No nulo
    */
    inline void left(node n);
    
    /**
       @brief Coloca el nodo hijo derecho de un nodo
       @param n El nodo que ser~ hijo derecho del receptor No nulo
    */
    inline void right(node n);


    nodewrapper * elnodo;
  };
};

#include "bintree.hxx"
#include "node.hxx"


#endif




