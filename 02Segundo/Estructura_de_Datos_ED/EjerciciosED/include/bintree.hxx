//-*-Mode: C++;-*-

/*
  $Id: bintree.template,v 1.4 2006/05/18 10:31:17 mgs Exp $  
*/
/*
  $Log: bintree.template,v $
  Revision 1.4  2006/05/18 10:31:17  mgs
  *** empty log message ***

  Revision 1.3  2006/05/18 10:22:10  jmbs
  Corregida funci� bintree<T>::copy

  Revision 1.2  2006/05/18 10:14:06  mgs
  *** empty log message ***

  Revision 1.1.1.1  2006/05/17 12:25:32  jmbs
  Versi� Inicial

*/
  

/*
************************************************************ 
* Implementaci�
************************************************************
*/

/*
  Funci� de Abstracci�:
  ----------------------
  Dado el objeto del tipo rep r, r = {laraiz}, el objeto
  abstracto al que representa es:
  a) Arbol nulo, si r.laraiz.null().
  b) Arbol con un nico nodo de etiqueta *(r.laraiz)
     si r.laraiz.left().null() y r.laraiz.dcha().null()

  c)                    *(r.laraiz)
                         /       \
                        /         \
      Arbol(r.laraiz.left())    Arbol(r.laraiz.right())


  Invariante de Representaci�:
  ----------------------------
  Si !r.laraiz.null(),
  -   r.laraiz.parent().null().

  Para cualquier nodo n del �bol:
  Si !n.left().null()
      n.left().parent() == n;
  Si !n.right().null()
      n.right().parent() == n;

*/


#include <cassert>


/*____________________________________________________________ */
/*____________________________________________________________ */
//               FUNCIONES PRIVADAS
/*____________________________________________________________ */
/*____________________________________________________________ */

template <typename T>
void bintree<T>::destroy(typename bintree<T>::node n)
{
  if (!n.null()) {
    destroy(n.left());
    destroy(n.right());
    n.remove();
  }
}

/*____________________________________________________________ */

template <typename T>
void bintree<T>::copy(typename bintree<T>::node & dest, const typename bintree<T>::node & orig)
{
  if (orig.null())
    dest = typename bintree<T>::node();
  else {
      dest = node(*orig);
      typename bintree<T>::node aux(dest.left());
      copy (aux, orig.left());
      dest.left(aux);
      if (!dest.left().null())
        dest.left().parent(dest);
      aux = dest.right();
      copy (aux, orig.right());
      dest.right(aux);
      if (!dest.right().null())
	dest.right().parent(dest);
    }
}

/*____________________________________________________________ */

template <typename T>
int bintree<T>::count(typename bintree<T>::node n) const
{
  if (n.null())
    return 0;
  else
    return 1 + count(n.left()) + count(n.right());
}

/*____________________________________________________________ */

template <typename T>
bool bintree<T>::equals(typename bintree<T>::node n1, typename bintree<T>::node n2) const
{
  if (n1.null() && n2.null())
    return true;
  if (n1.null() || n2.null())
    return false;
  if (*n1 != *n2)
    return false;
  if (!equals(n1.left(),n2.left()))
    return false;
  if (!equals(n1.right(),n2.right()))
    return false;
  return true;
}


/*____________________________________________________________ */
/*____________________________________________________________ */
//               FUNCIONES PUBLICAS
/*____________________________________________________________ */
/*____________________________________________________________ */


template <typename T>
inline 
bintree<T>::bintree()
  : num_nodos(0)
{
}

/*____________________________________________________________ */

template <typename T>
inline
bintree<T>::bintree(const T & e) 
  : laraiz(e), num_nodos(1)
{
}

/*____________________________________________________________ */

template <typename T>
inline
bintree<T>::bintree(const bintree<T> & a)
{
  copy(laraiz, a.laraiz);
  if (!laraiz.null())
    laraiz.parent(typename bintree<T>::node());
  num_nodos = a.num_nodos;
}

/*____________________________________________________________ */

template <typename T>
void bintree<T>::assign_subtree(const bintree<T> & a,
				typename bintree<T>::node n)
{
  if (&a != this) {
    destroy(laraiz);
    num_nodos = count(n);
    copy(laraiz, n);
    if (!laraiz.null())
      laraiz.parent(typename bintree<T>::node());
  } else {  // Reemplazar el receptor por un sub�bol suyo.
    if (laraiz != n) {
      typename bintree<T>::node nod(laraiz);
      num_nodos = count(n);
      laraiz = n;
      if (!n.null()) {
        typename bintree<T>::node aux(n.parent());
        if (n.parent().left() == n)
          aux.left(typename bintree<T>::node());
        else
          aux.right(typename bintree<T>::node());
      }
      destroy(nod);
      laraiz.parent(typename bintree<T>::node());
    }
  }
}


 template <typename T>
void bintree<T>::replace_subtree(typename bintree<T>::node pos, const bintree<T>& a,      typename bintree<T>::node n)
{
   if (&a != this) {
     if (pos == laraiz) {  // pos es la raiz
       destroy(laraiz);
       copy(laraiz, n);
       if (!laraiz.null())
	 laraiz.parent(typename bintree<T>::node());
     } else {  // Pos no esta en la raiz
       typename bintree<T>::node padre = pos.parent(), aux;
       if (padre.left()==pos) { // Es hijo izquierda
	  destroy(padre.left());
	  copy(aux, n);
	  num_nodos+= count(aux);
	  padre.left(aux);
	  aux.parent(padre);
       }
       else { // Es hijo derecha
	 destroy(padre.right());
	 copy(aux, n);
	 num_nodos+= count(aux);
	 padre.right(aux);
	 aux.parent(padre);
       }
     }
   }
}




/*____________________________________________________________ */

template <typename T>
inline 
bintree<T>::~bintree()
{
  destroy(laraiz);
}

/*____________________________________________________________ */

template <typename T>
inline
bintree<T> & 
bintree<T>::operator=(const bintree<T> & a)
{
  if (&a != this) {
    destroy(laraiz);
    num_nodos = a.num_nodos;
    copy(laraiz, a.laraiz);
    if (!laraiz.null())
      laraiz.parent(typename bintree<T>::node());
  }
  return *this;
}

/*____________________________________________________________ */

template <typename T>
inline 
typename bintree<T>::node bintree<T>::root() const
{
  return laraiz;
}

/*____________________________________________________________ */

template <typename T>
inline 
void bintree<T>::prune_left(typename bintree<T>::node n,
			    bintree<T> & dest)
{
  assert(!n.null());

  destroy(dest.laraiz);
  num_nodos = num_nodos - dest.num_nodos;
  dest.laraiz = n.left();
  if (!dest.laraiz.null())
    dest.laraiz.parent(typename bintree<T>::node());
  n.left(typename bintree<T>::node());
}


/*____________________________________________________________ */

template <typename T>
inline
void bintree<T>::prune_right(typename bintree<T>::node n,
			     bintree<T> & dest)
{
  assert(!n.null());

  destroy(dest.laraiz);
  dest.num_nodos = count(n.right());
  num_nodos = num_nodos - dest.num_nodos ;
  dest.laraiz = n.right();
  if (!dest.laraiz.null())
    dest.laraiz.parent(typename bintree<T>::node());
  n.right(typename bintree<T>::node());
}

/*____________________________________________________________ */
template <typename T>
inline 
void bintree<T>::insert_left(const typename bintree<T>::node & n, const T & e)
{
  bintree<T> aux(e);
  insert_left(n, aux);
}

/*____________________________________________________________ */
template <typename T>
inline
void bintree<T>::insert_left(typename bintree<T>::node n,
			     bintree<T> & rama)
{
  assert(!n.null());

  num_nodos = num_nodos - count(n.left()) + rama.num_nodos;
  typename bintree<T>::node aux(n.left());
  destroy(aux);
  n.left(rama.laraiz);
  if (!n.left().null()) 
    n.left().parent(n);
  rama.laraiz = typename bintree<T>::node();
}

/*____________________________________________________________ */
template <typename T>
inline
void bintree<T>::insert_right(typename bintree<T>::node n, const T & e)
{
  bintree<T> aux(e);
  insert_right(n, aux);
}
/*____________________________________________________________ */

template <typename T>
inline
void bintree<T>::insert_right(typename bintree<T>::node n, 
			      bintree<T> & rama)
{
  assert(!n.null());
  
  num_nodos = num_nodos - count(n.right()) + rama.num_nodos;
  typename bintree<T>::node aux(n.right());
  destroy(aux);
  n.right(rama.laraiz);
  if (!n.right().null())
    n.right().parent(n);
  rama.laraiz = typename bintree<T>::node();
}

/*____________________________________________________________ */

template <typename T>
void bintree<T>::clear()
{
  destroy(laraiz);
  num_nodos = 0;
  laraiz = typename bintree<T>::node();
}

/*____________________________________________________________ */

template <typename T>
inline 
typename bintree<T>::size_type bintree<T>::size() const
{
  return num_nodos;
}

/*____________________________________________________________ */

template <typename T>
inline 
bool bintree<T>::empty() const
{
  return laraiz == typename bintree<T>::node();
}

/*____________________________________________________________ */

template <typename T>
inline 
bool bintree<T>::operator==(const bintree<T> & a) const
{
  return equals(laraiz, a.laraiz);
}

/*____________________________________________________________ */

template <typename T>
inline bool bintree<T>::operator!=(const bintree<T> & a) const
{
  return !(*this == a);  
}

/*
  ************************************************************
  Iteradores
  ************************************************************
  */


/*
  Iterador para recorrido en Preorder
*/

template <typename T>
inline 
bintree<T>::preorderiterator::preorderiterator()
{
  elnodo = typename bintree<T>::node();
}

template <typename T>
inline 
bintree<T>::preorderiterator::preorderiterator(
                 const bintree<T>::preorderiterator & i) 
  : elnodo(i.elnodo)
{
}


template <typename T>
inline 
bintree<T>::preorderiterator::preorderiterator(typename bintree<T>::node n) 
  : elnodo(n)
{
}

#include <iostream>
using namespace std;

template <typename T>
inline 
bool bintree<T>::preorderiterator::operator!=(
        const bintree<T>::preorderiterator & i) const
{
  //cout << elnodo << " " << i.elnodo << endl;
  return elnodo != i.elnodo;
}


template <typename T>
inline 
bool bintree<T>::preorderiterator::operator==(
              const bintree<T>::preorderiterator & i) const
{

  //cout << elnodo << " " << i.elnodo << endl;
  return elnodo == i.elnodo;
}


template <typename T>
inline 
T & bintree<T>::preorderiterator::operator*()
{
  return *elnodo;
}

template <typename T>
typename  bintree<T>::preorderiterator & bintree<T>::preorderiterator::operator=(const bintree<T>::preorderiterator & i)
{
  elnodo = i.elnodo;
  return *this;

}

template <typename T>
typename bintree<T>::preorderiterator &
bintree<T>::preorderiterator::operator++()
{
  if (elnodo.null())
    return *this;

  if (!elnodo.left().null())
    elnodo = elnodo.left();
  else if (!elnodo.right().null())
      elnodo = elnodo.right();
  else {
    while ((!elnodo.parent().null()) &&
	       (elnodo.parent().right() == elnodo ||
	        elnodo.parent().right().null()))
      elnodo = elnodo.parent();
    if (elnodo.parent().null())
      elnodo = typename bintree<T>::node();
    else
      elnodo = elnodo.parent().right();
  }

  return *this;
}


template <typename T>
inline 
typename bintree<T>::preorderiterator
bintree<T>::beginPreorder()
{
  return preorderiterator(laraiz);
}

template <typename T>
inline 
typename bintree<T>::preorderiterator
bintree<T>::endPreorder()
{
  return preorderiterator(typename bintree<T>::node());
}

/*____________________________________________________________ */

/*
  Iterador para recorrido en Inorder
*/


template <typename T>
inline 
bintree<T>::inorderiterator::inorderiterator()
{
}


template <typename T>
inline bintree<T>::inorderiterator::inorderiterator(
  bintree<T>::node n)
  : elnodo(n)
{
}

template <typename T>
inline bool bintree<T>::inorderiterator::operator!=(
const typename bintree<T>::inorderiterator & i) const
{
  return elnodo != i.elnodo;
}


template <typename T>
inline bool bintree<T>::inorderiterator::operator==(
const typename bintree<T>::inorderiterator & i) const
{
  return elnodo == i.elnodo;
}

template <typename T>
typename  bintree<T>::inorderiterator & bintree<T>::inorderiterator::operator=(const bintree<T>::inorderiterator & i)
{
  elnodo = i.elnodo;
  return *this;
}

template <typename T>
inline T & bintree<T>::inorderiterator::operator*()
{
  return *elnodo;
}


template <typename T>
typename bintree<T>::inorderiterator &
bintree<T>::inorderiterator::operator++()
{
  if (elnodo.null())
    return *this;

  if (!elnodo.right().null()) {
      elnodo = elnodo.right();
      while (!elnodo.left().null())
        elnodo = elnodo.left();
  }
  else {
      while (!elnodo.parent().null() &&
	         elnodo.parent().right() == elnodo)
        elnodo = elnodo.parent();
      // Si (padre de elnodo es nodo_nulo), hemos terminado
      // En caso contrario, el siguiente node es el padre
      elnodo = elnodo.parent();
    }
  return *this;
}

template <typename T>
typename bintree<T>::inorderiterator
bintree<T>::beginInorder()
{
  node n(laraiz);

  if (!n.null())
    while (!n.left().null())
      n = n.left();
  return inorderiterator(n);
}


template <typename T>
inline 
typename bintree<T>::inorderiterator
bintree<T>::endInorder()
{
  return inorderiterator(typename bintree<T>::node());
}

/*____________________________________________________________ */

/*
  Iterador para recorrido en Postorder
*/


template <typename T>
inline 
bintree<T>::postorderiterator::postorderiterator()
{
}

template <typename T>
inline 
bintree<T>::postorderiterator::postorderiterator(
  typename bintree<T>::node n)
  : elnodo(n)
{
}

template <typename T>
inline 
bool bintree<T>::postorderiterator::operator!=(
const bintree<T>::postorderiterator & i) const
{
  return elnodo != i.elnodo;
}

template <typename T>
inline 
bool bintree<T>::postorderiterator::operator==(
const bintree<T>::postorderiterator & i) const
{
  return elnodo == i.elnodo;
}


template <typename T>
inline 
T & bintree<T>::postorderiterator::operator*()
{
  return *elnodo;
}


template <typename T>
typename bintree<T>::postorderiterator &
bintree<T>::postorderiterator::operator++()
{
  if (elnodo.null())
    return *this;

  if (elnodo.parent().null())
    elnodo = typename bintree<T>::node();
  else
    if (elnodo.parent().left() == elnodo) {
      if (!elnodo.parent().right().null()) {
        elnodo = elnodo.parent().right();
        do {
	         while (!elnodo.left().null())
            elnodo = elnodo.left();
          if (!elnodo.right().null())
            elnodo = elnodo.right();
        } while ( !elnodo.left().null() ||
                  !elnodo.right().null());
      }
      else
        elnodo = elnodo.parent();
    }
    else // elnodo es hijo a la derecha de su padre
      elnodo = elnodo.parent();

  return *this;
}

template <typename T>
typename  bintree<T>::postorderiterator & bintree<T>::postorderiterator::operator=(const bintree<T>::postorderiterator & i)
{
  elnodo = i.elnodo;
  return *this;
}


template <typename T>
inline 
typename bintree<T>::postorderiterator bintree<T>::beginPostorder()
{
  node n(laraiz);
  
  if (!n.null()) 
    do {
      while (!n.left().null())
        n = n.left();
      if (!n.right().null())
        n = n.right();
    } while (!n.left().null() || !n.right().null());

  return postorderiterator(n);
}

template <typename T>
inline 
typename bintree<T>::postorderiterator
bintree<T>::endPostorder()
{
  return postorderiterator(typename bintree<T>::node());
}

/*____________________________________________________________ */

/*
  Iterador para recorrido por Niveles
*/

template <typename T>
inline 
bintree<T>::leveliterator::leveliterator()
{
}

template <typename T>
inline bintree<T>::leveliterator::leveliterator(
  typename bintree<T>::node n)
{
  cola_Nodos.push(n);

}
template <typename T>
inline bool bintree<T>::leveliterator::operator!=(
const bintree<T>::leveliterator & i) const
{
  if (cola_Nodos.empty() && i.cola_Nodos.empty())
    return false;
  if (cola_Nodos.empty() || i.cola_Nodos.empty())
    return true;
  if (cola_Nodos.front() != i.cola_Nodos.front())
    return false;
  if (cola_Nodos.size() != i.cola_Nodos.size())
    return false;
  return (cola_Nodos == i.cola_Nodos);
}

template <typename T>
inline 
bool bintree<T>::leveliterator::operator==(
const bintree<T>::leveliterator & i) const
{
  return  !(*this != i);
}

template <typename T>
inline 
T & bintree<T>::leveliterator::operator*()
{
  return *cola_Nodos.front();
}


template <typename T>
typename bintree<T>::leveliterator &
bintree<T>::leveliterator::operator++()
{
  if (!cola_Nodos.empty()) {
    typename bintree<T>::node n = cola_Nodos.front();
    cola_Nodos.pop();
    if (!n.left().null())
      cola_Nodos.push(n.left());
    if (!n.right().null())
      cola_Nodos.push(n.right());
  }

  return *this;
}


template <typename T>
inline typename bintree<T>::leveliterator
bintree<T>::beginLevel()
{
  if (!root().null())
    return leveliterator(laraiz);
  else
    return leveliterator();
}

template <typename T>
inline typename bintree<T>::leveliterator
bintree<T>::endLevel()
{
  return leveliterator();
}

/*
  ************************************************************
  Iteradores constantes
  ************************************************************
  */


/*
  Iterador cosntante para recorrido en Preorder
*/

template <typename T>
inline 
bintree<T>::const_preorderiterator::const_preorderiterator()
{
}


template <typename T>
inline 
bintree<T>::const_preorderiterator::const_preorderiterator(
    typename bintree<T>::node n) 
  : elnodo(n)
{
}

template <typename T>
inline 
bool bintree<T>::const_preorderiterator::operator!=(
     const bintree<T>::const_preorderiterator & i) const
{
  return elnodo != i.elnodo;
}


template <typename T>
inline 
bool bintree<T>::const_preorderiterator::operator==(
   const bintree<T>::const_preorderiterator & i) const
{
  return elnodo == i.elnodo;
}

template <typename T>
typename  bintree<T>::const_preorderiterator & bintree<T>::const_preorderiterator::operator=(const bintree<T>::const_preorderiterator & i)
{
  elnodo = i.elnodo;
  return *this;
}


template <typename T>
inline 
const T & bintree<T>::const_preorderiterator::operator*() const
{
  return *elnodo;
}


template <typename T>
typename bintree<T>::const_preorderiterator &
bintree<T>::const_preorderiterator::operator++()
{
  if (elnodo.null())
    return *this;

  if (!elnodo.left().null())
    elnodo = elnodo.left();
  else if (!elnodo.right().null())
      elnodo = elnodo.right();
  else {
    while ((!elnodo.parent().null()) &&
	   (elnodo.parent().right() == elnodo 
	    || elnodo.parent().right().null()))
         elnodo = elnodo.parent();
    if (elnodo.parent().null())
      elnodo = typename bintree<T>::node();
    else
      elnodo = elnodo.parent().right();
  }

  return *this;
}


template <typename T>
inline 
typename bintree<T>::const_preorderiterator
bintree<T>::beginPreorder() const
{
  return const_preorderiterator(laraiz);
}

template <typename T>
inline 
typename bintree<T>::const_preorderiterator
bintree<T>::endPreorder() const
{
  return const_preorderiterator(typename bintree<T>::node());
}

/*____________________________________________________________ */

/*
  Iterador constante para recorrido en Inorder
*/


template <typename T>
inline 
bintree<T>::const_inorderiterator::const_inorderiterator()
{
  elnodo = typename bintree<T>::node();
}


template <typename T>
inline 
bintree<T>::const_inorderiterator::const_inorderiterator(
  typename bintree<T>::node n)
  : elnodo(n)
{
}

template <typename T>
inline 
bool bintree<T>::const_inorderiterator::operator!=(
const bintree<T>::const_inorderiterator & i) const
{
  return elnodo != i.elnodo;
}

template <typename T>
typename  bintree<T>::const_inorderiterator & bintree<T>::const_inorderiterator::operator=(const bintree<T>::const_inorderiterator & i)
{
  elnodo = i.elnodo;
  return *this;
}

template <typename T>
inline 
bool bintree<T>::const_inorderiterator::operator==(
const bintree<T>::const_inorderiterator & i) const
{
  return elnodo == i.elnodo;
}


template <typename T>
inline 
const T & bintree<T>::const_inorderiterator::operator*() const
{
  return *elnodo;
}


template <typename T>
typename bintree<T>::const_inorderiterator &
bintree<T>::const_inorderiterator::operator++()
{
  if (elnodo.null())
    return *this;

  if (!elnodo.right().null()) {
    elnodo = elnodo.right();
    while (!elnodo.left().null())
      elnodo = elnodo.left();
  }
  else {
      while (!elnodo.parent().null() &&
	            elnodo.parent().right() == elnodo)
        elnodo = elnodo.parent();
      // Si (el padre de elnodo es nodo_nulo), hemos terminado
      // En caso contrario, el siguiente Nodo es el padre
      elnodo = elnodo.parent();
    }
  return *this;
}

template <typename T>
inline 
typename bintree<T>::const_inorderiterator
bintree<T>::beginInorder() const
{
  node n(laraiz);

  if (!n.null())
    while (!n.left().null())
      n = n.left();
  return const_inorderiterator(n);
}


template <typename T>
inline 
typename bintree<T>::const_inorderiterator
bintree<T>::endInorder() const
{
  return const_inorderiterator(typename bintree<T>::node());
}

/*____________________________________________________________ */

/*
  Iterador constante para recorrido en Postorder
*/


template <typename T>
inline 
bintree<T>::const_postorderiterator::const_postorderiterator()
{
  elnodo = typename bintree<T>::node();
}


template <typename T>
inline 
bintree<T>::const_postorderiterator::const_postorderiterator(
  typename bintree<T>::node n)
  : elnodo(n)
{
}
template <typename T>
typename  bintree<T>::const_postorderiterator & bintree<T>::const_postorderiterator::operator=(const bintree<T>::const_postorderiterator & i)
{
  elnodo = i.elnodo;
  return *this;
}


template <typename T>
inline 
bool bintree<T>::const_postorderiterator::operator!=(
const bintree<T>::const_postorderiterator & i) const
{
  return elnodo != i.elnodo;
}


template <typename T>
inline 
bool bintree<T>::const_postorderiterator::operator==(
const bintree<T>::const_postorderiterator & i) const
{
  return elnodo == i.elnodo;
}


template <typename T>
inline 
const T & bintree<T>::const_postorderiterator::operator*() const
{
  return *elnodo;
}


template <typename T>
typename bintree<T>::const_postorderiterator &
bintree<T>::const_postorderiterator::operator++()
{
  if (elnodo.null())
    return *this;

  if (elnodo.parent().null())
    elnodo = typename bintree<T>::node();
  else
    if (elnodo.parent().left() == elnodo) {
      if (!elnodo.parent().right().null()) {
        elnodo = elnodo.parent().right();
        do {
	         while (!elnodo.left().null())
	           elnodo = elnodo.left();
	         if (!elnodo.right().null())
	           elnodo = elnodo.right();
	       } while ( !elnodo.left().null() ||
		             !elnodo.right().null());
      }
      else
        elnodo = elnodo.parent();
   }
  else // elnodo es hijo a la derecha de su padre
      elnodo = elnodo.parent();

  return *this;
}


template <typename T>
inline 
typename bintree<T>::const_postorderiterator
bintree<T>::beginPostorder() const
{
  node n = root();

  if (!n.null())
    do {
      while (!n.left().null())
        n = n.left();
      if (!n.right().null())
        n = n.right();
    } while (!n.left().null() || !n.right().null());

  return const_postorderiterator(n);
}

template <typename T>
inline 
typename bintree<T>::const_postorderiterator
bintree<T>::endPostorder() const
{
  return const_postorderiterator(typename bintree<T>::node());
}

/*____________________________________________________________ */

/*
  Iterador cosntante para recorrido por Niveles
*/


template <typename T>
inline 
bintree<T>::const_leveliterator::const_leveliterator()
{
}


template <typename T>
inline 
bintree<T>::const_leveliterator::const_leveliterator(
  typename bintree<T>::node n)
{
  cola_Nodos.push(n);
}


template <typename T>
inline 
bool bintree<T>::const_leveliterator::operator!=(
const bintree<T>::const_leveliterator & i) const
{
  if (cola_Nodos.empty() && i.cola_Nodos.empty())
    return false;
  if (cola_Nodos.empty() || i.cola_Nodos.empty())
    return true;
  return cola_Nodos.front() != i.cola_Nodos.front();
}


template <typename T>
inline 
bool bintree<T>::const_leveliterator::operator==(
const bintree<T>::const_leveliterator & i) const
{
  return !(*this != i);
}


template <typename T>
inline const T & bintree<T>::const_leveliterator::operator*() const
{
  return *cola_Nodos.front();
}


template <typename T>
typename bintree<T>::const_leveliterator &
bintree<T>::const_leveliterator::operator++()
{
  if (!cola_Nodos.empty()) {
    typename bintree<T>::node n = cola_Nodos.front();
    cola_Nodos.pop();
    if (!n.left().null())
      cola_Nodos.push(n.left());
    if (!n.right().null())
      cola_Nodos.push(n.right());
  }

  return *this;
}


template <typename T>
inline 
typename bintree<T>::const_leveliterator
bintree<T>::beginLevel() const
{
  if (!root().null())
    return const_leveliterator(laraiz);
  else
    return const_leveliterator();
}

template <typename T>
inline 
typename bintree<T>::const_leveliterator
bintree<T>::endLevel() const
{
  return const_leveliterator();
}


