package ejercicioClase4T2;

public class EjercicioClase4 {
	private static final EjercicioClase4 mInstancia = new EjercicioClase4();
	// CONSTRUCTOR
	/*
	 * Se pone el constructor en privado para que no se pueda
	 * hacer un new y se tiene que llamar usar getInstancia
	 * para poder usar el objeto en cuestion.
	 */
	private EjercicioClase4() {

	}
	
	// METODOS
	/*
	 * Con este metodo en concreto lo que hacemos es que 
	 * cuando lo llamamos ejecutamos el ejercicio en cuestion
	 */
	public void ejecutar() {
		Persona p1 = new Persona("Pepito", "12345");
		p1.hablar();
		System.out.println(p1.getNombre());
		Profesor profe = new Profesor("Don Juan", "7777", "PDO", 37);
		profe.hablar();
		System.out.println(profe.getNombre());
		
		p1 = new Persona("Pepito", "12345");
		p1 = new Profesor("Don Pepe", "88888", "PDO", 30); // Esto esta Bien por que se extiende de Persona
		
//		Profesor profe;
//		profe = new Persona("Pepito", "12345"); // Esto esta Mal Por que no se puede heredar
		
		((Profesor) p1).hablar("Adios");
		p1.hablar();
		System.out.println(p1.getNombre());
		profe = new Profesor("Don Juan", "7777", "PDO", 37);
		profe.hablar();
		System.out.println(profe.getNombre());
		System.out.println(profe.hablar("Buenos Dias"));
	}

	/**
	 * @return el parametro mInstancia
	 */
	public static EjercicioClase4 getInstancia() {
		return mInstancia;
	}
}
