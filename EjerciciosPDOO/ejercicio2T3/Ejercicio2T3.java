package ejercicio2T3;

import java.util.ArrayList;

/**
 * @author Carlos de la Torre
 *
 */
public class Ejercicio2T3 {
	private static final Ejercicio2T3 mInstancia = new Ejercicio2T3();
	
	// CONSTRUCTOR
	/*
	 * Se pone el constructor en privado para que no se pueda
	 * hacer un new y se tiene que llamar usar getInstancia
	 * para poder usar el objeto en cuestion.
	 */
	private Ejercicio2T3(){
		
	}
	
	// METODOS
	/*
	 * Con este metodo en concreto lo que hacemos es que 
	 * cuando lo llamamos ejecutamos el ejercicio en cuestion
	 */
	public void ejecutar() {
		//Creamos los dispositivos
		String[] Dispositivos = {"Equipo_de_Sobremesa","Portátil","SmartPhone","Galaxy_Note"};
		// Creamos el nuevo alumno de informática
		Alumno_Informatica yoMismo = new Alumno_Informatica("Carlos", "Informática", 2, Dispositivos);
		// Creamos tambien un alumno normal para ver las diferencias
		Alumno esOtro = new Alumno("Pedro", "Bellas_Artes", 5);
		// y le preguntamos a ver si de verdad estudia
		yoMismo.estudiar();
		// y ahora le preguntamos a pedro si estudia
		esOtro.estudiar();
		//Ahora le preguntamos cuales son todos sus dispositivos
		String[] miDis = yoMismo.getDispositivos();
		int count = 0;
		System.out.println("Tengo: " + yoMismo.getDispositivos().length + " dispositivos");
		for (String tipo : miDis){
			System.out.println(" de los cuales el: " + count + " es: " + tipo + " y");
			count++;
		}
		//Ahora le quitamos el primer dispositivo por que es el que menos usa
		removeElements(miDis, 0);
		yoMismo.setDispositivos(miDis);
		System.out.println("\nMis Dispositivos actuales son: " + yoMismo.getDispositivos().length);
		for (String tipo : miDis){
			System.out.println(" y " + tipo);
		}
	}

	/**
	 * @return el parametro mInstancia
	 */
	public static Ejercicio2T3 getInstancia() {
		return mInstancia;
	}
	
	/*
	 * funcion rescatada sirve para eliminar elementos desde un Array de Strings
	 */
	private String[] removeElements(String[] input, int pos) {
	    ArrayList<String> result = new ArrayList<>(input.length-2);
	    for(String item : input)
	            result.add(item);
	    result.remove(pos);
	    return result.toArray(input);
	}
}
