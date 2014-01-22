package NapakalakiGame;

public interface Card {
	
	/**
	 * Con esto devolvemos los valores basicos de una carta
	 * @return devuelve el valor de la carta.
	 */
	public int getBasicValue();
	
	/**
	 * Con este método se devuelven los valores especiales
	 * de la carta
	 * @return devuleve el valor especial de la carta
	 */
	public int getSpecialValue();

}
