package ejercicio7T3;
/*
 * Esta interfaz se extiende de las otras dos anteriores
 * por lo tanto la clase que implemente esta interfaz
 * devera implementar los metodos que estan incluidos
 * en esta misma y en las dos interfaces de la que extiende
 */
public interface InterfazMultiple extends InterfazA, InterfazB {
	
	public String getCampo();
	
	public void setCampo(String campo);
}
