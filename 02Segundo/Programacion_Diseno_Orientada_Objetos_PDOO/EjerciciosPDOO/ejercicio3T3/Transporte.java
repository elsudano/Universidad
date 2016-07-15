package ejercicio3T3;

/**
 * @author Carlos
 * 
 */
public abstract class Transporte {
	protected String marca;
	
	/**
	 * Esta es la marca que tiene nuestro transporte
	 * @param marc La marca que tiene nuestro Transporte Actual
	 */
	protected String getMarca() {
		return this.marca;
	}
	
	/**
	 * Esta es la marca que tiene nuestro transporte
	 * @param marc La marca que queremos pornerle a nuestro Trasnporte
	 */
	protected void setMarca(String marc) {
		this.marca = marc;
	}

	/**
	 * Podemos hacer una ruta turistica con este metódo
	 * @param origen Esta es la cidad de partida
	 * @param destino Esta es la Ciudad de llegada
	 * @return tipo String
	 */
	public abstract String hacerRuta(String origen, String destino);
}
