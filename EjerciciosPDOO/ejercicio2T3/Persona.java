package ejercicio2T3;

class Persona {
	protected String nombre;

	public Persona(String nom) {
		this.setNombre(nom);
	}

	protected String getNombre() {
		return this.nombre;
	}

	protected void setNombre(String nom) {
		this.nombre = nom;
	}

	public String hablar(){ return "bla bla bla";}
}
