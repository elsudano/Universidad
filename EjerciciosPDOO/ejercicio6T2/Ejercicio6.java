package ejercicio6T2;

public class Ejercicio6 {
	private static final Ejercicio6 mInstancia = new Ejercicio6();
	
	private Ejercicio6(){
		
	}
	
	public void ejecutar() {
		Equipo Equipo = new Equipo();
		int contador = 0;

		for (int c = 1; c<10; c++){
			Atleta miAtleta = new Atleta();
			miAtleta.setNombre("Atleta"+c);
			Equipo.anadirAtleta(miAtleta);
		}
		contador = 0;
		while (contador < Equipo.getAtletas().size()){
			System.out.println("Atleta: " + Equipo.getAtletas().get(contador).getNombre() + "\n");
			contador++;
		}
	}

	/**
	 * @return el parametro mInstancia
	 */
	public static Ejercicio6 getInstancia() {
		return mInstancia;
	}
}
