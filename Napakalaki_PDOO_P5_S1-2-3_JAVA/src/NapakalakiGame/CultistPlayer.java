package NapakalakiGame;

import java.util.ArrayList;

public class CultistPlayer extends Player{
	private static int totalCultistPlayers = 0;
	@SuppressWarnings("unused")
	private Cultist myCultistCard;
	
	/**
	 * Constructor por defecto
	 * @param pPlayer este es el Jugador que se va a convertir en sectario
	 * @param pCultist y este sera el resultado de convertirse en sectario
	 */
	public CultistPlayer(Player pPlayer, Cultist pCultist) {
		super(pPlayer);
		this.myCultistCard = new Cultist(pPlayer.getName(), pPlayer.getLevels());
		CultistPlayer.totalCultistPlayers += 1;
	}
	
	/**
	 * Método que devuelve el nivel que tiene el
	 * jugador Sectario.
	 */
	@Override
	public int getCombatLevel(){
		return 0;
	}
	
	/**
	 * Método que se encarga de mirar si
	 * el jugador se puede convertir a sectario
	 * @return devuelve true si se puede convertir
	 * y false en caso contrario
	 */
	@Override
	public boolean shouldConvert(){
		return false;
	}
	
	/**
	 * Con este método podemos ver cual es el
	 * nivel que tiene nuestro oponente
	 * @param pMonster párametro de entrada del tipo monster
	 * que será el monstruo al que se le calcule el nivel.
	 * @return devuelve un valor entero con el nivel del
	 * monstruo que pasamos por párametros
	 */
	public int getOponentLevel(Monster pMonster){
		int valor = 0;
//		boolean collar = false;
//		for (Treasure tesoro : this.mVisibleTreasures){
//			if (tesoro.getType()==TreasureKind.NECKLACE)
//				collar = true;
//		}
//		for (Treasure tesoro : this.mVisibleTreasures){
//			// se supone que todos los visibles estan equipados
//			if (collar)
//				valor += tesoro.getMaxBonus();
//			else
//				valor += tesoro.getMinBonus();
//		}
		return valor;
	}
	
	/**
	 * Métodos que sirve para comprar niveles
	 * @param pTesoros Es un array con los tesoros que quiere cambiar
	 * el jugador para poder comprar niveles
	 * @return un entero con la cantidad de niveles que se pueden
	 * con la cantidad de dinero que tiene el jugador
	 */
	@SuppressWarnings("unused")
	private int computeGoldCoinsValue(ArrayList<Treasure> pTesoros) {
		int valor = 0;
		for (Treasure tesoro : pTesoros){
			valor += tesoro.getGoldCoins();
		}
		return valor/1000;
		// aquí devuelve la cantidad de niveles que se pueden
		// comprar al ser un entero se trunca el resultado
	}
	
	/**
	 * Método que devuelve la cantidad de Jugadores
	 * Sectarios que hay en el juego
	 * @return devuelve un entero con la cantidad
	 * de jugadores sectarios que hay en la partida
	 */
	public static int getTotalCultistPlayers() {
		return totalCultistPlayers;
	}
}
