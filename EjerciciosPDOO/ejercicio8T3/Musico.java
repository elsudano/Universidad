package ejercicio8T3;

public class Musico {
	private String tipo;
	private String queHago;
	
	/**
	 * @param tipo
	 * @param queHago
	 */
	public Musico(String tipo, String queHago) {
		this.tipo = tipo;
		this.queHago = queHago;
	}

	/**
	 * @return el parametro tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo se le asigna a tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return el parametro queHago
	 */
	public String getQueHago() {
		return queHago;
	}

	/**
	 * @param queHago se le asigna a queHago
	 */
	public void setQueHago(String queHago) {
		this.queHago = queHago;
	}
}
