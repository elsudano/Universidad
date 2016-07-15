package PruebasVariasTema2;



/**
 *
 * @author PDOO
 *  probando el acceso a variables de instancia y clase, 
 * pregunta 4 del cuestionario 2
 */
public class Persona2 {
    String nombre;
    int edad;
    static String Planeta="Tierra";
    
    Persona2(String n, int e) {
        this.nombre=n;
        this.edad=e;
    }
    
    static String getPlaneta(){
        return Persona2.Planeta; //probar con this
        
    }
    
     String getPlanet(){
        return Persona2.Planeta; //probar con this
        
    }
    static String getInfo(){
        //return Persona2.nombre; Descomentar para probar
        Persona2 p4= new Persona2("p4", 4);
        return p4.nombre;  //
    }
        
 
     
     public static void main(String argsString[]) {

        Persona2 p1 = new Persona2("p1", 30);
        System.out.println(p1.nombre);
        System.out.println(p1.getPlanet());
        System.out.println(Persona2.getInfo());
        System.out.println(Persona2.getPlaneta());
}
}