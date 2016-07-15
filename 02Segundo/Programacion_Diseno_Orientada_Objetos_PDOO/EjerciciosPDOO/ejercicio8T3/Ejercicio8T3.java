package ejercicio8T3;

/**
 * @author Carlos de la Torre
 * 
 */
public class Ejercicio8T3 {
	private static final Ejercicio8T3 mInstancia = new Ejercicio8T3();

	// CONSTRUCTOR
	/*
	 * Se pone el constructor en privado para que no se pueda hacer un new y se
	 * tiene que llamar usar getInstancia para poder usar el objeto en cuestion.
	 */
	private Ejercicio8T3() {

	}

	// METODOS
	/*
	 * Con este metodo en concreto lo que hacemos es que cuando lo llamamos
	 * ejecutamos el ejercicio en cuestion
	 */
	public void ejecutar() {
		Grupo<Persona> miGrupoDeCarpinteros = new Grupo<Persona>();
		Grupo<Musico> miGrupoDeMusica = new Grupo<Musico>();
		
		for (int i = 0; i < 8; i++) {
			miGrupoDeCarpinteros.setMiembro(new Persona("Carpintero", "Hago muebles de madera"));
		}
		miGrupoDeCarpinteros.setLider(new Persona("Banquero", "Dirijo una Carpinteria"));
		
		for (int i = 0; i < 8; i++) {
			miGrupoDeMusica.setMiembro(new Musico("Gitarrista","Toco las cuerdas de la guitarra"));
		}
		miGrupoDeMusica.setLider(new Musico("Cantante", "Toco las cuerdas de mi garganta"));
		
		System.out.println("Mi grupo de Carpinteros tiene estos miembros: \n");
		for (int j = 0;j<miGrupoDeCarpinteros.conjunto.size();j++){
			System.out.println(miGrupoDeCarpinteros.conjunto.get(j).getTipo() + " Que realizan esta actividad: " + miGrupoDeCarpinteros.conjunto.get(j).getQueHago());
		}
		System.out.println("Pero el jefe de todos ellos es: " + miGrupoDeCarpinteros.getLider().getTipo() + " Y realiza esta actividad: " + miGrupoDeMusica.getLider().getQueHago());
		
		System.out.println("Mi grupo de Musicos tiene estos miembros: \n");
		for (int j = 0;j<miGrupoDeMusica.conjunto.size();j++){
			System.out.println(miGrupoDeMusica.conjunto.get(j).getTipo() + " Que realizan esta actividad: " + miGrupoDeMusica.conjunto.get(j).getQueHago());
		}
		System.out.println("Pero el jefe de todos ellos es: " + miGrupoDeMusica.getLider().getTipo() + " Y realiza esta actividad: " + miGrupoDeMusica.getLider().getQueHago());
	}

	/**
	 * @return el parametro mInstancia
	 */
	public static Ejercicio8T3 getInstancia() {
		return mInstancia;
	}
}
