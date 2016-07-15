package ejercicio8T2;

public class Ejercicio8 {
	private static final Ejercicio8 mInstancia = new Ejercicio8();
	
	public void ejecutar() {
		Kursus CursoAleman = new Kursus();
		CursoAleman.setAntalStuderende(5);
		CursoAleman.setNavn("Ni Idea de lo que dice");
		System.out.println(CursoAleman.getNavn()+" "+CursoAleman.getAntalStuderende());
	}

	/**
	 * @return el parametro mInstancia
	 */
	public static Ejercicio8 getInstancia() {
		return mInstancia;
	}
}
