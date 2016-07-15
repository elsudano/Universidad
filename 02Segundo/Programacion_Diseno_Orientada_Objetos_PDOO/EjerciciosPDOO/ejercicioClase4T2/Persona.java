package ejercicioClase4T2;

public class Persona {
	protected String nombre;
	protected String dni;
	
	public void hablar(){
		System.out.println("hola");
	}
	
	public Persona(String n, String d) {
		this.nombre = n;
		this.dni = d;
	}
	
	protected String getNombre(){
		return this.nombre;
	}
}
