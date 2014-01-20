package ejercicio2T3;

class Alumno extends Persona {
	public String carrera;
	public int curso;

	public Alumno(String nom, String carr, int cur) {
		super(nom);
		carrera = carr;
		curso = cur;
	}

	public void estudiar() { System.out.println("estudiando\n"); }
}
