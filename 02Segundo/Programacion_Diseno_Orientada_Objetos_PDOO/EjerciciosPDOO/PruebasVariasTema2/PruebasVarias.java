package PruebasVariasTema2;

import java.util.ArrayList;

public class PruebasVarias {
	private static final PruebasVarias mInstancia = new PruebasVarias();
	// CONSTRUCTOR
	/*
	 * Se pone el constructor en privado para que no se pueda
	 * hacer un new y se tiene que llamar usar getInstancia
	 * para poder usar el objeto en cuestion.
	 */
	private PruebasVarias() {

	}
	
	// METODOS
	/*
	 * Con este metodo en concreto lo que hacemos es que 
	 * cuando lo llamamos ejecutamos el ejercicio en cuestion
	 */
	/**
	 * @author Carlos de la Torre
	 * ejercicio 2 de relación 1 y usado en Prueba2
	 */
	public void prueba1(){
//		public static final int A=1;
//	    static String s="";
//	    private int b=2;
//	    public int c;
	}

	public void prueba2(){
		Prueba obj1=new Prueba();
        Prueba obj2=new Prueba();
        //obj1.A=3;  
        System.out.println(obj1.A);
        //Prueba.A=3;
        Prueba.s="hola";
        obj1.s="hola";
        //obj1.b=0;
	}

	/**
	 * Probando a añadir objetos y valores de tipos básicos dentro de colecciones y arrays
	 * @author Carlos de la Torre
	 */
	public void pruebaCol(){
		//colección no parametrizada, añadiendo valores de tipos básicos y objetos
        ArrayList numeros=new ArrayList();
        numeros.add(7);
        System.out.println(numeros.get(0));
        numeros.add("hola");
        System.out.println(numeros.get(1));
        numeros.add(new Persona("Ana", 19));
        System.out.println(((Persona)numeros.get(2)).nombre); //arreglarlo
        
       //Vector, probando a introducir valores y objetos
        
        int cosas[]={44, 88};
        cosas[0]=22;
        System.out.println(cosas[0]);
        
        Persona p1= new Persona("p1", 19);
        Persona p2= new Persona("p2", 20);
        Persona p3= new Persona("p3", 21);
        Persona personas[]={p1, p2};
        personas[0]=p3;
        System.out.println(((Persona)personas[0]).nombre);
	}
	
    public void pruebaPersona(){
		Persona p1 = new Persona("Juan", 30);
	    Persona p2 = p1;
	    Persona p3 = p1.clone();
	    System.out.println(p1.nombre);
	    p2.nombre = "Luis";
	    p3.nombre = "Pepe";
	    System.out.println(p1.nombre);
	}

	public void pruebaPersona2(){
    	Persona2 p1 = new Persona2("p1", 30);
        System.out.println(p1.nombre);
        System.out.println(p1.getPlanet());
        System.out.println(Persona2.getInfo());
        System.out.println(Persona2.getPlaneta());
	}
	
    public void pruebaEquipo(){
        Persona p8= new Persona("p8",10);
//      Persona p9= new Persona ("p9",20);
//      Equipo e= new Equipo();
//      e.addMiembro(p8);
//      e.addMiembro(p9);
//      System.out.println(e.getMiembro().nombre);
//      e.verMiembros();
	}
	
	/**
	 * @return el parametro mInstancia
	 */
	public static PruebasVarias getInstancia() {
		return mInstancia;
	} 
public static void main(String args[]) {
        

        
        
    }
}
