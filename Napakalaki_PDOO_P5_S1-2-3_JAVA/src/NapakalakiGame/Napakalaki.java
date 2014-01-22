package NapakalakiGame;

import java.util.ArrayList;

public class Napakalaki {
	private static final Napakalaki mInstance = new Napakalaki();
	public Monster mCurrentMonster;
	private CardDealer mDealer;
	public Player mCurrentPlayer;
	public ArrayList<Player> mPlayers;

	// CONSTRUCTOR
	/**
	 * 
	 */
	private Napakalaki() {
		this.mCurrentMonster = null;
		this.mDealer = CardDealer.getInstance();
		this.mCurrentPlayer = null;
		this.mPlayers = new ArrayList<Player>();
	}

	/**
	 * @param pNames
	 */
	private void initPlayers(ArrayList<String> pNames) {
		for (String nombre : pNames){
			Player jugador = new Player(nombre);
			this.mPlayers.add(jugador);
		}
		this.mCurrentPlayer = this.nextPlayer();
	}

	/**
	 * @return
	 */
	private Player nextPlayer() {
		int siguiente_jugador = 0;
		for (Player jugador : mPlayers){
			if (jugador==this.mCurrentPlayer)
				siguiente_jugador = mPlayers.indexOf(jugador);
			if (siguiente_jugador==this.mPlayers.lastIndexOf(jugador))
				siguiente_jugador=0;
		}
		return mPlayers.get(siguiente_jugador);
	}

	/**
	 * @return una instancia unica del juego
	 */
	public static Napakalaki getInstance() {
		return mInstance;
	}

	/**
	 * @return Devuelve el resultado del combate
	 */
	public CombatResult developCombat() {
		return this.mCurrentPlayer.combat(this.mCurrentMonster);
	}

	/**
	 * @param pTreasures
	 */
	public void discardVisibleTreasures(ArrayList<Treasure> pTreasures) {
		for (Treasure tesoro : pTreasures){
			mCurrentPlayer.discardVisibleTreasure(tesoro);
			mDealer.giveTreasureBack(tesoro);
		}
	}

	/**
	 * @param pTreasures
	 */
	public void discardHiddenTreasures(ArrayList<Treasure> pTreasures) {
		for (Treasure tesoro : pTreasures){
			mCurrentPlayer.discardVisibleTreasure(tesoro);
			mDealer.giveTreasureBack(tesoro);
		}
	}

	/**
	 * @param pTreasures
	 */
	public void makeTreasuresVisible(ArrayList<Treasure> pTesoros) {
		for (Treasure tesoro : pTesoros){
			if (this.canMakeTreasureVisible(tesoro)){
				this.mCurrentPlayer.canMakeTreasureVisible(tesoro);
			}
		}
	}

	/**
	 * 
	 */
	public boolean buyLevels(ArrayList<Treasure> pVisible, ArrayList<Treasure> pHidden) {
		return this.mCurrentPlayer.buyLevels(pVisible, pHidden);
	}

	/**
	 * @param pPlayers
	 */
	public void initGame(ArrayList<String> pPlayers) {
		this.initPlayers(pPlayers);
		this.mDealer.initCards();
		this.nextTurn();		
	}

	/**
	 * @return el jugador actual
	 */
	public Player getCurrentPlayer() {
		return this.mCurrentPlayer;
	}

	/**
	 * @return el monstruo actual
	 */
	public Monster getCurrentMonster() {
		return this.mCurrentMonster;
	}

	/**
	 * @param pT
	 * @return
	 */
	public boolean canMakeTreasureVisible(Treasure pT) {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return
	 */
	public boolean nextTurn() {
		boolean stateOK = this.nextTurnAllowed();
		if (stateOK){
			this.mCurrentMonster = this.mDealer.nextMonster();
			this.mCurrentPlayer = this.nextPlayer();
			boolean dead = mCurrentPlayer.isDead();
			if (dead){
				mCurrentPlayer.initTreasures();
			}
		};
		return stateOK;
	}

	/**
	 * 
	 */
	public boolean nextTurnAllowed() {
		return this.mCurrentPlayer.validState();
	}

	/**
	 * @param pResult
	 * @return
	 */
	public boolean endOfGame(CombatResult pResult) {
		boolean valor = false;
		if (pResult==CombatResult.WinAndWinGame)
			valor = true;
		return valor;
	}

	/**
	 * @return el parametro mDealer
	 */
	public CardDealer getDealer() {
		return mDealer;
	}
}