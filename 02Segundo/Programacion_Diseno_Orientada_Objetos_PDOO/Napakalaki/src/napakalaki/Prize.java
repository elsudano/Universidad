package napakalaki;

/**
 * Clase de buen rollo del monstruo, esta clase indica lo que gana un jugador
 * cuando gana un enfrentamiento contra un monstruo.
 *
 * @author: Carlos de la Torre
 */
public class Prize {

    /**
     * Variable con la cantidad de tesoros que se pueden ganar con este precio.
     */
    private int treasures;
    /**
     * Variable con la cantidad de niveles que se pueden ganar con este precio.
     */
    private int level;

    /**
     * Constructor de Clase, inicializa los valores del objeto con parámetros de
     * entrada:
     *
     * @param t numero entero que indica cantidad de tesoros
     * @param l numero que indica la cantidad de niveles
     */
    public Prize(int t, int l) {
        this.treasures = t;
        this.level = l;
    }

    /**
     * Devuelve la cantidad de tesoros que tiene el buen rollo
     *
     * @return numero entero que muestra la cantidad de tesoros
     */
    public int getTreasures() {
        return this.treasures;
    }

    /**
     * Devuelve la cantidad de niveles que contiene el buen rollo del monstruo
     *
     * @return numero entero que muestra la cantidad de niveles
     */
    public int getLevels() {
        return this.level;
    }

    /**
     * Generá una cadena la cual contiene los datos formateados para imprimirlos
     * por pantalla
     *
     * @return string que contiene los datos del objeto con cabeceras.
     */
    @Override
    public String toString() {
        return "Numero de Tesoros: " + Integer.toString(this.treasures) + ", " + "Numero de Niveles: " + Integer.toString(this.level);
    }
}
