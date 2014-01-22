package NapakalakiGame;

public class Treasure implements Card {
	private String mName;
	private int mGoldCoins;
	private int mMinBonus;
	private int mMaxBonus;
	public TreasureKind mType;

	// CONSTRUCTOR
	/**
	 * Constructor por defecto
	 * @param pN Nombre del Tesoro
	 * @param pG Cantidad de monedas de Oro
	 * @param pMin numero minimo de Bonus
	 * @param pMax numero maximo de Bonus
	 * @param pT el tipo de Tesoro que conseguimos
	 */
	public Treasure(String pN, int pG, int pMin, int pMax, TreasureKind pT) {
		this.mName=pN;
		this.mGoldCoins=pG;
		this.mMinBonus=pMin;
		this.mMaxBonus=pMax;
		this.mType=pT;
	}

	/**
	 * @return el nombre del Tesoro
	 */
	public String getName() {
		return this.mName;
	}

	/**
	 * @return la cantidad de monedas de oro que conseguimos
	 */
	public int getGoldCoins() {
		return this.mGoldCoins;
	}

	/**
	 * @return el minimo de bonus que conseguimos
	 */
	public int getMinBonus() {
		return this.mMinBonus;
	}

	/**
	 * @return el maximo de bonus que conseguimos
	 */
	public int getMaxBonus() {
		return this.mMaxBonus;
	}

	/**
	 * @return el tesoro que conseguimos
	 */
	public TreasureKind getType() {
		return this.mType;
	}

	@Override
	public int getBasicValue() {
		return this.mMinBonus;
	}

	@Override
	public int getSpecialValue() {
		return this.mMaxBonus;
	}
}