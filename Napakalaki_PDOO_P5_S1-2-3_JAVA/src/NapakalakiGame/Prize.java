package NapakalakiGame;

public class Prize {
	private int mTreasures;
	private int mLevels;

	//	CONSTRUCTOR
	/**
	 * Cuando creamos este objeto tenemos que asignar
	 * cuantos tesoros y cuanto es el nivel que tiene.
	 * @param pT
	 * @param pI
	 */
	public Prize(int pT, int pI) {
		this.mTreasures = pT;
		this.mLevels = pI;
	}

	/**
	 * @return la cantida de tesoros que tiene el precio
	 */
	public int getTreasures() {
		return this.mTreasures;
	}

	/**
	 * @return la cantidad de nivel que tiene el precio
	 */ 
	public int getLevels() {
		return this.mLevels;
	}
}