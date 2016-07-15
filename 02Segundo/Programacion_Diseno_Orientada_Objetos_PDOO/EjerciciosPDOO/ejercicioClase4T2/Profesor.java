package ejercicioClase4T2;

public class Profesor extends Persona {
	String asignatura;
	int experiencia;
	
	public void hablar(){
		System.out.println("Esto es lo que dice el Profesor");
		super.hablar(); // llamamos al metodo hablar de la superclase
	}
	
	public Profesor(String n, String d, String a, int e) {
		super(n,d); // llamamos al constructor de la Superclase
		asignatura=a;
		experiencia=e;
	}
	
	public String hablar(String texto){
		return texto;
	}
}
