package NapakalakiGame;

public class Cultist implements Card{
	private String name;
	private int gainedLevels;
	
	/**
	 * Constructor por defecto
	 * @param name
	 * @param gainedLevels
	 */
	public Cultist(String pName, int pGainedLevels) {
		this.name = pName;
		this.gainedLevels = pGainedLevels;
	}

	/**
	 * @return el parametro nombre
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return el parametro Niveles Ganados
	 */
	public int getGainedLevels() {
		return this.gainedLevels;
	}

	@Override
	public int getBasicValue() {
		return this.gainedLevels;
	}

	@Override
	public int getSpecialValue() {
		return this.getBasicValue()*CultistPlayer.getTotalCultistPlayers();
	}
	
}
