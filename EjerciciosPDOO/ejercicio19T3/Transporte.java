package ejercicio19T3;

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
	
	/**
	 * Con este metodo podemos decirle a nuestro transporte
	 * que es lo que queremos llevar
	 */
	public abstract void llevarCosas();
	
	/**
	 * Con este metódo podemos decirle a nuestro transporte
	 * que se detenga en el acto.
	 */
	public void parar(){
		;
	}
}
