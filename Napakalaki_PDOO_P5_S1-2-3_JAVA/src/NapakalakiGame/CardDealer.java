package NapakalakiGame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class CardDealer {
	private static final CardDealer mInstance = new CardDealer();
	FileReader fr = null;
	private BufferedReader br = null;
	private String [] columnas = null;
	private String fila=null;
	public ArrayList<Monster> mUnusedMonsters;
	public ArrayList<Monster> mUsedMonsters;
	public ArrayList<Treasure> mUsedTreasures;
	public ArrayList<Treasure> mUnusedTreasures;
	public ArrayList<Cultist> mUsedCultists;
	public ArrayList<Cultist> mUnusedCultists;
    
	private CardDealer() {
		this.mUnusedMonsters = new ArrayList<Monster>();
		this.mUsedMonsters = new ArrayList<Monster>();
		this.mUsedTreasures = new ArrayList<Treasure>();
		this.mUnusedTreasures = new ArrayList<Treasure>();
		this.mUsedCultists = new ArrayList<Cultist>();
		this.mUnusedCultists = new ArrayList<Cultist>();
	}

	private void initTreasureCardDeck() {
		try {
		// Apertura del fichero y creacion de BufferedReader para poder
		// hacer una lectura comoda (disponer del metodo readLine()).
		fr = new FileReader(getClass().getResource("/resources/base_datos_tesoros.txt").getPath());
		br = new BufferedReader(fr);
		while((fila=br.readLine())!=null){
			columnas = fila.split(",");
			mUnusedTreasures.add(new Treasure(columnas[0], Integer.parseInt((columnas[4])), Integer.parseInt(columnas[2]), Integer.parseInt(columnas[3]), TreasureKind.valueOf(columnas[1].toUpperCase())));
		}
		this.shuffleTreasures();
		fr.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private void initMonsterCardDeck() {
		BadStuff malrollo = null;
		try {
		// Apertura del fichero y creacion de BufferedReader para poder
		// hacer una lectura comoda (disponer del metodo readLine()).
		
		fr = new FileReader(getClass().getResource("/resources/base_datos_monstruos.txt").getPath());
		br = new BufferedReader(fr);
		while((fila=br.readLine())!=null){
			columnas = fila.split(",");
			if (columnas[10] == "muerte"){
				malrollo = new BadStuff(columnas[4], true);
			}else if (!columnas[8].isEmpty() || !columnas[9].isEmpty()){
				ArrayList<TreasureKind> THidden = leeTesoros(columnas[8]);
				ArrayList<TreasureKind> TVisible = leeTesoros(columnas[9]);
				malrollo = new BadStuff(columnas[4], Integer.parseInt(columnas[5]), THidden, TVisible);
			}else{
				malrollo = new BadStuff(columnas[4], Integer.parseInt(columnas[5]), Integer.parseInt(columnas[6]), Integer.parseInt(columnas[7]));
			}
			Prize precio = new Prize(Integer.parseInt(columnas[2]), Integer.parseInt(columnas[3]));
			mUnusedMonsters.add(new Monster(columnas[0], Integer.parseInt(columnas[1]), malrollo, precio, Integer.parseInt(columnas[11])));
		}
		this.shuffleMonsters();
		fr.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Con este metodo lo que hacemos es cargar en el
	 * programa todas las cartas necesarias para convertir
	 * a un jugador en sectario.
	 */
	private void initCultistCardDeck() {
		try {
		// Apertura del fichero y creacion de BufferedReader para poder
		// hacer una lectura comoda (disponer del metodo readLine()).

		fr = new FileReader(getClass().getResource("/resources/base_datos_sectarios.txt").getPath());
		br = new BufferedReader(fr);
		while((fila=br.readLine())!=null){
			columnas = fila.split(",");
			mUnusedCultists.add(new Cultist(columnas[0], Integer.parseInt(columnas[1])));
		}
		this.shuffleCultists();
		fr.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Con este metodo lo que hacemos es convertir una
	 * cadena de texto separada por "," en un
	 * ArrayList<TreasureKind> de esa manera podemos
	 * tener los objetos que realmente necesitamos
	 * en el ArrayList devuelto.
	 * @param es una cadena de texto separada por comas con los objetos
	 * @return Es un ArrayList<TreasureKind> que contiene los objetos
	 */
	private ArrayList<TreasureKind> leeTesoros(String datos){
		String[] objTesoro = datos.toUpperCase().split("-");
		ArrayList<TreasureKind> Tesoros = new ArrayList<TreasureKind>();
		int c=0;
		while (objTesoro.length>0 && c<objTesoro.length){
			if (!objTesoro[c].contentEquals(""))
				Tesoros.add(TreasureKind.valueOf(objTesoro[c]));
			c++;
		}
		return Tesoros;
	}

	/**
	 * Con este metodo barajamos las cartas del monton
	 * de los Tesoros
	 */
	private void shuffleTreasures() {
		Collections.shuffle(mUnusedTreasures);
	}

	/**
	 * Con este metodo barajamos las cartas del monton
	 * de los monstruos
	 */
	private void shuffleMonsters() {
		Collections.shuffle(mUnusedMonsters);
	}
	
	/**
	 * Con este metodo barajamos las cartas del monton
	 * de los Sectarios
	 */
	private void shuffleCultists() {
		Collections.shuffle(mUnusedCultists);
	}
	/**
	 * @return la instancia del CardDealer
	 */
	public static CardDealer getInstance() {
		return mInstance;
	}

	/**
	 * @return el siguiente Tesoro
	 */
	public Treasure nextTreasure() {
		Treasure tesoro = this.mUnusedTreasures.get(0);
		this.mUnusedTreasures.remove(0);
		if (this.mUnusedTreasures.isEmpty()){
			this.mUnusedTreasures = this.mUsedTreasures;
			this.shuffleTreasures();
			this.mUsedTreasures.clear();
		}
		return tesoro;		
	}

	/**
	 * @return el siguiente Monstruo
	 */
	public Monster nextMonster() {
		Monster monstruo = this.mUnusedMonsters.get(0);
		this.mUnusedMonsters.remove(0);
		if (this.mUnusedMonsters.isEmpty()){
			this.mUnusedMonsters = this.mUsedMonsters;
			this.shuffleTreasures();
			this.mUsedMonsters.clear();
		}
		return monstruo;
	}
	
	/**
	 * @return el siguiente Carta del Sectario
	 */
	public Cultist nextCultists() {
		Cultist cultist = this.mUnusedCultists.get(0);
		this.mUnusedCultists.remove(0);
		if (this.mUnusedCultists.isEmpty()){
			this.mUnusedCultists = this.mUsedCultists;
			this.shuffleCultists();
			this.mUnusedCultists.clear();
		}
		return cultist;
	}
	
	/**
	 * @param pT
	 */
	public void giveTreasureBack(Treasure pT) {
		this.mUsedTreasures.add(pT);
	}

	/**
	 * @param mM
	 */
	public void giveMonsterBack(Monster mM) {
		this.mUsedMonsters.add(mM);
	}

	/**
	 * 
	 */
	public void initCards() {
		this.initMonsterCardDeck();
		this.initTreasureCardDeck();
		this.initCultistCardDeck();
	}

	/**
	 * @return el parametro mUsedMonsters
	 */
	public ArrayList<Monster> getUsedMonsters() {
		return mUsedMonsters;
	}
	
	/**
	 * @return el parametro mUsedMonsters
	 */
	public ArrayList<Treasure> getUsedTreasures() {
		return mUsedTreasures;
	}
}