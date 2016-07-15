/**
 * 
 */
package ejercicio2T3;

/**
 * @author usuario
 *
 */
public class Alumno_Informatica extends Alumno {
	
	private String[] dispositivos;
	
	/**
	 * @param nom Es el nombre del Alumno
	 * @param carr Es la carrera que esta estudiando
	 * @param cur Es el curso que esta estudiando
	 * @param dis Son los dispositivos que usa el alumno para estudiar
	 */
	public Alumno_Informatica(String nom, String carr, int cur, String[] dis) {
		super(nom, carr, cur);
		setDispositivos(dis);
	}

	/**
	 * @return el parametro dispositivos
	 */
	public String[] getDispositivos() {
		return dispositivos;
	}

	/**
	 * @param dispositivos se le asigna a dispositivos
	 */
	public void setDispositivos(String[] dispositivos) {
		this.dispositivos = dispositivos;
	}
	
	public void estudiar() {
		System.out.println("Igual que los demás tambien estoy estudiando");
		System.out.println("Pero yo lo hago con mi Dispositivo preferido: " + dispositivos[dispositivos.length-1] + "\n");
	}

}
