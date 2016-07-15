package napakalaki;

/**
 * Clase de Jugador sectario esto es un tipo especial de jugador, con unos
 * atributos diferentes y reglas especiales
 *
 * @author: Carlos de la Torre
 */
public class Cultist implements Card {

    /**
     * @brief Nombre de la carta del tipo sectario.
     */
    private String name;
    /**
     * @brief Niveles que se ganan con la carta de tipo sectario.
     */
    private int gainedLevels;

    /**
     * Constructor por parametros, con el cual se da nombre y el numero de
     * niveles del objeto.
     *
     * @param name nombre del objeto.
     * @param gainedLevels niveles que tiene el objeto para usar.
     */
    public Cultist(String name, int gainedLevels) {
        this.name = name;
        this.gainedLevels = gainedLevels;
    }

    /**
     * Devuelve el nombre del objeto.
     *
     * @return devuelve una cadena de carateres con el nombre del objeto
     */
    @Override
    public String getName() {
        return this.name;
    }
    
    /**
     * Devuelve el valor del parametro GainedLevels.
     * 
     * @return devuelve un entero con el valor del parametro.
     */
    public int getGainedLevels(){
        return this.gainedLevels;
    }
    
    /**
     * Devuelve el nivel del objeto.
     *
     * @return devuelve un numero entero con el nivel del objeto
     */
    @Override
    public int getBasicValue() {
        return this.gainedLevels;
    }

    /**
     * Devuelve el nivel del objeto.
     *
     * @return devuelve un numero entero con el nivel del objeto multiplicado
     * por la cantidad de jugadores sectarios que hay en la partida
     */
    @Override
    public int getSpecialValue() {
        return getBasicValue() * CultistPlayer.getTotalCultistPlayers();
    }
    
    /**
     * Imprime por pantalla los datos del objeto
     *
     * @return Cadena de caracteres con los valores del objeto formateado
     */
    @Override
    public String toString() {
        String formateado = 
        "Nombre: " + this.getName()
        +"\nNivel de Combate: " + this.getBasicValue()
        + "\n";
        return formateado;
    }
}
