/** 
* Material del libro "Java 2 Vol. 2 - Características Avanzadas, C. S. Horstmann y G. Cornell
* http://www.horstmann.com/corejava.html
* ¡Lo tenéis en la biblioteca!
*/ 

package PruebasColecciones;

/**
   @version 1.10 2004-08-02
   @author Cay Horstmann
*/

import java.util.*;

/**
   Listas enlazadas
*/
public class LinkedListTest
{  
   public static void main(String[] args)
   {  
      List<String> a = new LinkedList<String>();
      a.add("Amy");
      a.add("Carl");
      a.add("Erica");

      List<String> b = new LinkedList<String>();
      b.add("Bob");
      b.add("Doug");
      b.add("Frances");
      b.add("Gloria");

      ListIterator<String> aIter = a.listIterator();
      Iterator<String> bIter = b.iterator();

//PARTE 1: Descomenta el System.out y comprueba qué hace este trozo de código
      while (bIter.hasNext())
      {  
         if (aIter.hasNext()) 
                aIter.next();
         aIter.add(bIter.next()); //< ¿qué hace aIter.add(...)?¿podría ponerse a.add(bIter.next())?
      }
      //System.out.println(a);
// ¿Cómo podríamos haber añadido los elementos de b al comienzo/final de la lista a?

// PARTE 2: Descomenta el System.out y comprueba qué hace el siguiente trozo de código
      bIter = b.iterator();
      while (bIter.hasNext())
      {  
         bIter.next(); 
         if (bIter.hasNext())
         {  
            bIter.next(); 
            bIter.remove();
         }
      }
  //    System.out.println(b);

// PARTE 3: Comprueba el funcionamiento del método removeAll
    //System.out.println(a);
    a.removeAll(b);
    //System.out.println(a);
   }
}


