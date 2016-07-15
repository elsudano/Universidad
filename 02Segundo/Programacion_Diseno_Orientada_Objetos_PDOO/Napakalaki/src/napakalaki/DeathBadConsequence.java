package napakalaki;

/**
 * 
 * @author Carlos de la Torre
 */
public class DeathBadConsequence extends NumericBadConsequence {

    /**
     * Constructor con parámetros, inicializa los valores del objeto con
     * parámetros de entrada:
     *
     * @param text numero entero que indica cantidad de tesoros
     * @param death indica si el mal rollo conlleva muerte.
     */
    public DeathBadConsequence(String text, boolean death) {
        super(text, Player.NIVEL_MAXIMO, death, MAXTREASURES, MAXTREASURES);
    }
}
