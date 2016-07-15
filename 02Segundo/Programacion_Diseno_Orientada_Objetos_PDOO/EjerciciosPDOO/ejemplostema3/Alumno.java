/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplostema3;

/**
 *
 * @author ana
 */
class Alumno extends Persona {   
   String  carrera;
   int curso;
   public Alumno(String d,String nom,String carr, int cur){ 
       super(d,nom); 
       this.carrera=carr;
       this.curso=cur;
   }   
   public void estudiar() {
        System.out.println("estudiando"); 
    }
   public void probarAcceso(){ 
       System.out.println(numPersonas);      
   }
   @Override
   public Alumno clone(){
       return this;
   }
}