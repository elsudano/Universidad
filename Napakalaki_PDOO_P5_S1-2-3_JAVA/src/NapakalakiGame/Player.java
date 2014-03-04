package NapakalakiGame;

import java.util.ArrayList;

public class Player {
	private boolean mDead;
	private String mName;
	private int mLevel;
	public BadStuff mPendingBadStuff;
	public ArrayList<Treasure> mHiddenTreasures;
	public ArrayList<Treasure> mVisibleTreasures;

	/**
	 * Constructor por defecto de la Clase
	 * @param pName Este sera el nombre que tendra el jugador
	 */
	public Player(String pName) {
		this.mDead = true;
		this.mName = pName;
		this.mLevel = 1;
		this.mPendingBadStuff = new BadStuff("", true);
		this.mHiddenTreasures = new ArrayList<Treasure>();
		this.mVisibleTreasures = new ArrayList<Treasure>();
	}

	/**
	 * Constructor de copia
	 * @param pPlayer este sera el Jugador desde donde se copiaran los datos
	 */
	public Player(Player pPlayer) {
		ArrayList<Treasure> tempA = new ArrayList<Treasure>();
		ArrayList<Treasure> tempB = new ArrayList<Treasure>();
		this.mDead = pPlayer.isDead();
		this.mName = pPlayer.getName();
		this.mLevel = pPlayer.getLevels();
		this.mPendingBadStuff.clone(pPlayer.mPendingBadStuff);
		for (Treasure tesoro : pPlayer.mVisibleTreasures){
			tempA.add(tesoro);
		}
		this.mVisibleTreasures = tempA;
		for (Treasure tesoro : pPlayer.mHiddenTreasures){
			tempB.add(tesoro);
		}
		this.mHiddenTreasures = tempB;
	}
	
	/**
	 * Metodo que sive para saber si el jugador esta muerto
	 * @return devuelve true si esta muerto y false en caso contrario
	 */
	public boolean isDead() {
		return this.mDead;
	}

	/**
	 * Devuelve el nivel de Convate que tiene el jugador
	 * @return es un entero con el nivel de combate calculado del jugador
	 */
	public int getCombatLevel() {
		int valor = 0;
		boolean collar = false;
		for (Treasure tesoro : this.mVisibleTreasures){
			if (tesoro.getType()==TreasureKind.NECKLACE)
				collar = true;
		}
		for (Treasure tesoro : this.mVisibleTreasures){
			// se supone que todos los visibles estan equipados
			if (collar)
				valor += tesoro.getMaxBonus();
			else
				valor += tesoro.getMinBonus();
		}
		return this.mLevel += valor; 
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
		return this.mLevel += valor;
	}
	
	/**
	 * Método que se encarga de mirar si
	 * el jugador se puede convertir a sectario
	 * @return devuelve true si se puede convertir
	 * y false en caso contrario
	 */
	public boolean shouldConvert(){
		boolean valor = false;
		Dice dado = Dice.getInstance();
		int numero = dado.nextNumber();
		if (numero==6)
			valor = true;
		return valor;
	}
	
	/**
	 * Devuelve un array con los Tesoros ocultos del Jugador
	 * @return arrayList con los tesoros ocultos del jugador
	 */
	public ArrayList<Treasure> getHiddenTreasures() {
		return this.mHiddenTreasures;
	}

	/**
	 * Devuelve un array con los Tesoros visibles del Jugador
	 * @return arrayList con los tesoros visibles del jugador
	 */
	public ArrayList<Treasure> getVisibleTreasures() {
		return this.mVisibleTreasures;
	}

	/**
	 * Método que sirve para revivir al jugador
	 * supongo que usando algún tipo de mágia 
	 */
	private void bringToLife() {
		this.mDead = false;
	}
	
	/**
	 * Incrementa el valor del nivel del jugador tantas unidades como indique pI
	 * @param pI parametro entero de entrada que suma el valor al nivel del jugador
	 */
	private void incrementLevels(int pI) {
		this.mLevel += pI;
	}
	
	/**
	 * Decrementa el valor del nivel del jugador tantas unidades como indique pI
	 * @param pI parametro entero de entrada que resta el valor al nivel del jugador
	 */
	private void decrementLevels(int pI) {
		this.mLevel -= pI;
	}
	
	/**
	 * Método que sirve para asignar un malRollo al jugador
	 * @param pB
	 */
	private void setPendingBadStuff(BadStuff pB) {
		this.mPendingBadStuff = pB;
	}
	
	/**
	 * Método que sirve para descartar el collar que da puntos extras
	 */
	private void discardNecklaceIfVisible() {
		CardDealer dealer = CardDealer.getInstance();
		for (Treasure tesoro : this.mVisibleTreasures){
			if (tesoro.getType()==TreasureKind.NECKLACE){
				dealer.giveTreasureBack(tesoro);
			}
		}
	}
	
	/**
	 * Método que mata al jugador si no tiene mas tésoros
	 */
	private void dieIfNoTreasures() {
		if (this.mVisibleTreasures.isEmpty() && this.mHiddenTreasures.isEmpty())
			this.mDead = true;
	}
	
	/**
	 * Métodos que sirve para comprar niveles
	 * @param pTesoros Es un array con los tesoros que quiere cambiar
	 * el jugador para poder comprar niveles
	 * @return un entero con la cantidad de niveles que se pueden
	 * con la cantidad de dinero que tiene el jugador
	 */
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
	 * Comprueba segun las reglas del juego si el
	 * jugador puede comprar niveles
	 * @param pI es un numero decimal que muestra la
	 * cantidad de niveles que queremos comprar
	 * @return Devuelve true si podemos comprar niveles
	 * y false en caso contrario
	 */
	private boolean canIBuyLevels(float pI) {
		boolean valor = true;
		if (this.mLevel+pI>=10)
			valor = false;
		return valor;
	}
	
	/**
	 * Método que modifica la vida del jugador a muerte
	 */
	public void die() {
		this.mLevel = 1;
		Treasure treasure;
		CardDealer dealer = CardDealer.getInstance();
		for (int i=0;i<this.mVisibleTreasures.size();i++){
			treasure = mVisibleTreasures.get(i);
			dealer.giveTreasureBack(treasure);
		}
		mVisibleTreasures.clear();
		
		for (int i=0;i<this.mVisibleTreasures.size();i++){
			treasure = mHiddenTreasures.get(i);
			dealer.giveTreasureBack(treasure);
		}
		mHiddenTreasures.clear();
		this.dieIfNoTreasures();
	}

	/**
	 * 
	 * @param pM
	 */
	public void applyPrize(Monster pM) {
		@SuppressWarnings("unused")
		int monsterLevels = pM.getLevelGained();
		this.incrementLevels(this.mLevel);
		int nTreasures = pM.getTreasuresGained();
		if (nTreasures>0){
			CardDealer dealer = Napakalaki.getInstance().getDealer();
			for (int g=0;g<pM.getPrize().getTreasures();g++){
				Treasure treasure = dealer.nextTreasure();
				this.mHiddenTreasures.add(treasure);
			}
		}
	}
	
	/**
	 * Método que sirve para simular el combate 
	 * entre el player y el monstruo
	 * @param pM párametro de entrada del tipo Monster
	 * @return devuelve el resultado del combate
	 */
	public CombatResult combat(Monster pM) {
		int myLevel = this.getLevels();
		int monsterLevel = pM.getLevel();
		Dice dado = Dice.getInstance();
		CombatResult resultado = null;
		if (myLevel>monsterLevel){
			this.applyPrize(pM);
			if (myLevel>=10)
				resultado = CombatResult.WinAndWinGame;
			else
				resultado = CombatResult.Win;
		}
		if (myLevel<=monsterLevel){
			int escape = dado.nextNumber();
			if (escape<5){
				boolean amIDead = pM.kills();
				BadStuff bad;
				if (amIDead){
					resultado = CombatResult.LoseAndDie;
					this.die();
				}else{
					resultado = CombatResult.Lose;
					bad = pM.getBadStuff();
					this.applyBadStuff(bad);
				}
			}
		}else
			resultado = CombatResult.LoseAndEscape;
		this.discardNecklaceIfVisible();
		
		return resultado;
	}

// Si el nivel de jugador > nivel del monstruo {
//	 Se actualiza el nivel y tesoros del jugador y se invoca al operación
//	 applyPrize (Diagrama: applyPrize)
//	 Devuelve Win si el jugador no gana la partida (nivel del jugador <10).
//	 	Devuelve WinAndWinGame si el jugador gana la partida.
//	 }
//	 Si el nivel del jugador es <= nivel del monstruo {
//	 Se lanza el dado
//	 Si sale 5 ó 6, {
//		 No pasa nada y se devuelve LooseAndEscape
//	 } en otro caso {
//		 Se analiza en qué consiste el mal rollo
//	 Si el jugador muere {
//		 Se invoca a la operación die
//		 (Diagrama: die)
//		 Se devuelve LoseAndDie
//	 } en otro caso {
//		 Se invoca a la operación applyBadStuff.
//		 (Diagrama: applyBadStuff)
//		 Se devuelve Lose
//	 }
// }
	/**
	 * Método que aplica el malRollo que tiene que cumplir
	 * el Jugador como castigo
	 * @param pBad párametro de entrada del tipo malRollo
	 */
	public void applyBadStuff(BadStuff pBad) {
		int nLevels = pBad.getLevels();
		decrementLevels(nLevels);
		BadStuff pendingBag = pBad.adjustToFitTreasureList(this.mVisibleTreasures, this.mHiddenTreasures);
		setPendingBadStuff(pendingBag);
	}
	
	/**
	 * Método que convierte un Tesoro oculto en visible
	 * osea que lo pone como si lo fueramos a usar
	 * @param pT párametro de entrada del tipo Tesoro
	 * @return devuelve verdadero si el tesoro puede usarse
	 * o false en caso contrario.
	 */
	public boolean makeTreasureVisible(Treasure pT) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Método que convierte un Tesoro oculto en visible
	 * osea que lo pone como si lo fueramos a usar
	 * @param pT párametro de entrada del tipo Tesoro
	 * @return devuelve verdadero si el tesoro puede usarse
	 * o false en caso contrario.
	 */
	public boolean canMakeTreasureVisible(Treasure pT) {
		boolean valor = true;
		for (Treasure tesoro : this.mVisibleTreasures){
			if (tesoro.getType()==pT.getType())
				valor = false;
		}
		return valor;
	}
	
	/**
	 * Método que descarta un tesoro visible cuando ya ha sido usado
	 * @param pT párametro de entrada del tipo tesoro
	 * que será el tesoro que descartaremos.
	 */
	public void discardVisibleTreasure(Treasure pT) {
		this.mVisibleTreasures.remove(pT);
		if (this.mPendingBadStuff!=null && !mPendingBadStuff.isEmpty()){
			mPendingBadStuff.substractVisibleTreasures(pT);
		}
		this.dieIfNoTreasures();
	}
	
	/**
	 * Método que descarta un tesoro oculto cuando ya ha sido usado
	 * @param pT párametro de entrada del tipo tesoro
	 * que será el tesoro que descartaremos.
	 */
	public void discardHiddenTreasure(Treasure pT) {
		this.mHiddenTreasures.remove(pT);
		if (this.mPendingBadStuff!=null && !mPendingBadStuff.isEmpty()){
			mPendingBadStuff.substractHiddenTreasure(pT);
		}
		this.dieIfNoTreasures();
	}
	
	/**
	 * 
	 * @param pVisible
	 * @param pHidden
	 * @return
	 */
	public boolean buyLevels(ArrayList<Treasure> pVisible, ArrayList<Treasure> pHidden) {
		int levelsMayBought = computeGoldCoinsValue(pVisible);
		levelsMayBought += computeGoldCoinsValue(pHidden);
		boolean canI = canIBuyLevels(levelsMayBought);
		if (canI) incrementLevels(levelsMayBought);
		CardDealer dealer = CardDealer.getInstance();
		for (int i=1;i<pVisible.size();i++){
			pVisible.remove(i);
			dealer.giveTreasureBack(pVisible.get(i));
		}
		for (int i=1;i<pHidden.size();i++){
			pVisible.remove(i);
			dealer.giveTreasureBack(pHidden.get(i));
		}
		return canI;
	}
	
	/**
	 * 
	 * @return devuelve verdaddero si el jugador esta
	 * correctamente inicializado y false en caso contrario
	 */
	public boolean validState() {
		boolean valor = false;
		if (this.mPendingBadStuff.isEmpty() && this.mHiddenTreasures.size()<=4)
			valor = true;
		return valor;
	}
	
	/**
	 * Método que inicializar la cantidad de tesoros que
	 * tendrá el jugador al comienzo de la partida
	 */
	public void initTreasures() {
		CardDealer dealer = Napakalaki.getInstance().getDealer();
		Dice dice = Dice.getInstance();
		this.bringToLife();
		Treasure treasure = dealer.nextTreasure();
		this.mHiddenTreasures.add(treasure);
		int number = dice.nextNumber();
		if (number>1){
			treasure = dealer.nextTreasure();
			this.mHiddenTreasures.add(treasure);
		}
		if (number==6){
			treasure = dealer.nextTreasure();
			this.mHiddenTreasures.add(treasure);
		}
		
	}
	
	/**
	 * 
	 * @return devuelve true si el jugador tiene
	 * tesoros visibles y false en caso contrario
	 */
	public boolean hasVisibleTreasures() {
		boolean valor = true;
		if (this.mVisibleTreasures.isEmpty())
			valor = false;
		return valor;
	}
	
	/**
	 * 
	 * @return devuelve la cantidad de niveles que tiene el jugador
	 */
	public int getLevels() {
		return mLevel;
	}
	
	/**
	 * 
 	 * @return devuelve el nombre del jugador
	 */
	public String getName() {
		return mName;
	}

	/**
	 * @return el parametro mPendingBadStuff
	 */
	public BadStuff getPendingBadStuff() {
		return mPendingBadStuff;
	}
}