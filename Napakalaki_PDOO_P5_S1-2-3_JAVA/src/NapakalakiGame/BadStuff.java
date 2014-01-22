package NapakalakiGame;

import java.util.ArrayList;

public class BadStuff {
	private String mText;
	private int mLevels;
	private int mNVisibleTreasures;
	private int mNHiddenTreasures;
	private boolean mDeath;
	public ArrayList<TreasureKind> mSpecificHiddenTreasures = new ArrayList<TreasureKind>();
	public ArrayList<TreasureKind> mSpecificVisibleTreasures = new ArrayList<TreasureKind>();

	
	//	CONSTRUCTORES
	/**
	 * @param pT
	 * @param pDeath
	 */
	public BadStuff(String pT, boolean pDeath) {
		this.mText = pT;
		this.mDeath = pDeath;
		this.mLevels = 0;
		this.mNVisibleTreasures = 0;
		this.mNHiddenTreasures = 0;
	}
	
	/**
	 * @param pT
	 * @param pI
	 * @param pNVisible
	 * @param pNHidden
	 */
	public BadStuff(String pT, int pI, int pNVisible, int pNHidden) {
		this.mText = pT;
		this.mLevels = pI;
		this.mLevels = 0;
		this.mNVisibleTreasures = pNVisible;
		this.mNHiddenTreasures = pNHidden;
	}
	
	/**
	 * @param pT
	 * @param pI
	 * @param pV
	 * @param pH
	 */
	public BadStuff(String pT, int pI, ArrayList<TreasureKind> pV, ArrayList<TreasureKind> pH) {
		this.mText = pT;
		this.mLevels = pI;
		this.mLevels = 0;
		this.mNVisibleTreasures = 0;
		this.mNHiddenTreasures = 0;
		this.mSpecificHiddenTreasures = pV;
		this.mSpecificVisibleTreasures = pH;
	}

	/**
	 * @return 
	 */
	public boolean isEmpty() {
		boolean valor = false;
		if (this.mLevels==0 && this.mNVisibleTreasures==0 && this.mNHiddenTreasures==0)
			valor = true;
		return valor;
	}
	
	/**
	 * @return el parámetro text
	 */
	public String getText() {
		return mText;
	}

	/**
	 * @return
	 */
	public int getLevels() {
		return this.mLevels;
	}

	/**
	 * @return
	 */
	public int getNVisibleTreasures() {
		return this.mNVisibleTreasures;
	}

	/**
	 * @return
	 */
	public int getNHiddenTreasures() {
		return this.mNHiddenTreasures;
	}

	/**
	 * @param pT
	 */
	public void substractVisibleTreasures(Treasure pT) {
		Napakalaki.getInstance().getDealer().giveTreasureBack(pT);
	}

	/**
	 * @param pT
	 */
	public void substractHiddenTreasure(Treasure pT) {
		Napakalaki.getInstance().getDealer().giveTreasureBack(pT);
	}

	/**
	 * @param pV
	 * @param pH
	 * @return
	 */
	public BadStuff adjustToFitTreasureList(ArrayList<Treasure> pV, ArrayList<Treasure> pH) {
		ArrayList<TreasureKind> aux = new ArrayList<TreasureKind>();
		// Primero los Visibles
		for (Treasure t : pV){
			for (TreasureKind tk : this.mSpecificVisibleTreasures){
				if (t.getType()==tk)
					aux.add(tk);
			}
		}
		this.mSpecificVisibleTreasures = aux;
		// Despúes los Ocultos
		for (Treasure t : pH){
			for (TreasureKind tk : this.mSpecificHiddenTreasures){
				if (t.getType()==tk)
					aux.add(tk);
			}
		}
		this.mSpecificHiddenTreasures = aux;
		return this;
	}

	/**
	 * @return devuelve el estado del parametro muerte
	 */
	public boolean myBadStuffIsDeath() {
		return this.mDeath;
	}
	
	/**
	 * @return un mal rollo nuevo del propio malrollo
	 */
	public BadStuff clone(BadStuff pMalRollo) {
		ArrayList<TreasureKind> tempA = new ArrayList<TreasureKind>();
		ArrayList<TreasureKind> tempB = new ArrayList<TreasureKind>();
		BadStuff temp = new BadStuff(pMalRollo.getText(), pMalRollo.getLevels(), pMalRollo.getNVisibleTreasures(), pMalRollo.getNHiddenTreasures());
		for (TreasureKind tesoro : pMalRollo.mSpecificVisibleTreasures){
			tempA.add(tesoro);
		}
		temp.mSpecificVisibleTreasures = tempA;
		for (TreasureKind tesoro : pMalRollo.mSpecificHiddenTreasures){
			tempB.add(tesoro);
		}
		temp.mSpecificHiddenTreasures = tempB;
		return temp;
	}
}