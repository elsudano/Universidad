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
   This program demonstrates the use of a map with key type
   String and value type Employee.
*/
public class MapTest
{  
   public static void main(String[] args)
   {  
      Map<String, Employee> staff = new HashMap<String, Employee>();
      staff.put("144-25-5464", new Employee("Amy Lee"));
      staff.put("567-24-2546", new Employee("Harry Hacker"));
      staff.put("157-62-7935", new Employee("Gary Cooper"));
      staff.put("456-62-5527", new Employee("Francesca Cruz"));

      System.out.println(staff); // < ¿Qué hace? ¿cómo?

      staff.remove("567-24-2546");
      staff.put("456-62-5527", new Employee("Francesca Miller"));
      //Comprueba cuál es el resultado de estas dos instrucciones descomentando
      // el System.out
//      System.out.println(staff);

//      System.out.println(staff.get("157-62-7935")); //< ¿qué imprime?

      //¿Qué hace este bucle for?¿para qué podría interesar usarlo en lugar de 
      //System.out.println(staff);?
      for (Map.Entry<String, Employee> entry : staff.entrySet())
      {  
         String key = entry.getKey();
         Employee value = entry.getValue();
         System.out.println("key=" + key + ", value=" + value);
      }
   }
}

/**
   A minimalist employee class for testing purposes.
*/
class Employee
{ 
    
   private String name;
   private double salary;
   /**
      Constructs an employee with $0 salary.
      @param n the employee name
   */
   public Employee(String n)
   {  
      name = n;
      salary = 0;
   }

   public String toString()
   {  
      return "[name=" + name + ", salary=" + salary + "]";
   }

}
