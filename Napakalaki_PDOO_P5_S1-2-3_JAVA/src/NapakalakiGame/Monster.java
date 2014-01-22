package NapakalakiGame;

public class Monster implements Card {
	private String mName;
	private int mCombatLevel;
	public BadStuff mBadStuff;
	public Prize mPrize;
	private int levelChangeAgainstCultistPlayer;

	// CONSTRUCTORES
	/**
	 * @param name
	 * @param combatLevel
	 * @param precioMonstruo
	 * @param mrMonstruo
	 */
	public Monster(String pN, int pI, BadStuff pB, Prize pP, int pLevChaAgaCulPla) {
		this.mName = pN;
		this.mCombatLevel = pI;
		this.mBadStuff = pB;
		this.mPrize = pP;
		this.levelChangeAgainstCultistPlayer = pLevChaAgaCulPla;
	}
	
	/**
	 * @return el nombre del monstruo
	 */
	public String getName() {
		return this.mName;
	}
	
	/**
	 * @return el nivel de combate del Monstruo
	 */
	public int getLevel() {
		return this.mCombatLevel;
	}
	
	/**
	 * @return el mal rollo del Monstruo
	 */
	public BadStuff getBadStuff() {
		return this.mBadStuff;
	}
	
	/**
	 * @return 
	 */
	public int getLevelGained() {
		throw new UnsupportedOperationException();
	}

	/**
	 * @return
	 */
	public int getTreasuresGained() {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * @return devuelve el valor que tenga el mal rollo
	 */
	public boolean kills(){
		return this.mBadStuff.myBadStuffIsDeath();
	}

	public Prize getPrize() {
		return this.mPrize;
	}

	@Override
	public int getBasicValue() {
		return this.mCombatLevel;
	}

	@Override
	public int getSpecialValue() {
		return this.mCombatLevel + this.levelChangeAgainstCultistPlayer;
	}

	/**
	 * @return el parametro levelChangeAgainstCultistPlayer
	 */
	public int getLevelChangeAgainstCultistPlayer() {
		return levelChangeAgainstCultistPlayer;
	}

}