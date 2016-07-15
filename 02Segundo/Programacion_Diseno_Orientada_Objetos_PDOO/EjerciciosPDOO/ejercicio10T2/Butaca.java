package ejercicio10T2;

public class Butaca {
	private int aFila;
	private int aNumero;
	
	public Butaca(){
		this.aFila=0;
		this.aNumero=0;
	}

	/**
	 * @return el parametro aFila
	 */
	public int getaFila() {
		return aFila;
	}

	/**
	 * @param aFila se le asigna a aFila
	 */
	public void setaFila(int aFila) {
		this.aFila = aFila;
	}

	/**
	 * @return el parametro aNumero
	 */
	public int getaNumero() {
		return aNumero;
	}

	/**
	 * @param aNumero se le asigna a aNumero
	 */
	public void setaNumero(int aNumero) {
		this.aNumero = aNumero;
	}
}