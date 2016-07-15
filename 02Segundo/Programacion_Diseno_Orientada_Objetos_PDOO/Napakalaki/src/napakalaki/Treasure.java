package napakalaki;

/**
 * Clase Tesoro, se encarga gestionar todos los datos que contienen los tesoros
 * que proporcionan las ventajas al jugador para poder enfrentarse a los
 * monstruos
 *
 * @author: Carlos de la Torre
 */
public class Treasure implements Card {

    /**
     * Variable con el nombre del tesoro.
     */
    private String name;
    /**
     * Variable con la cantidad de monedas de oro que tiene el tesoro.
     */
    private int goldCoins;
    /**
     * Variable del valor minimo del bonus del tesoro.
     */
    private int minBonus;
    /**
     * Variable del valor maximo del bonus del tesoro.
     */
    private int maxBonus;
    /**
     * Variable con el tipo de Tesoroa que se almacena.
     */
    private TreasureKind type;

    /**
     * Constructor con parametros, se encarga de inicializar el tesoro con todos
     * los datos
     *
     * @param n nombre del tesoro
     * @param g cantidad de monedas de oro que tiene el tesoro
     * @param min bonus minimo que tiene el tesoro
     * @param max bonus maximo que tiene el tesoro
     * @param t tipo de tesoro que tiene dentro
     */
    public Treasure(String n, int g, int min, int max, TreasureKind t) {
        this.name = n;
        this.goldCoins = g;
        this.minBonus = min;
        this.maxBonus = max;
        this.type = t;
    }

    /**
     * Constructor de copia (en profundidad), que simplemente copia todos los
     * atributos del objeto en otro lugar de la memoria.
     * @param t El objeto de tipo Treasure que queremos copiar.
     */
    public Treasure(Treasure t){
        this.name = t.name;
        this.minBonus = t.minBonus;
        this.maxBonus = t.maxBonus;
        this.goldCoins = t.goldCoins;
        this.type = t.type;
    }
    
    /**
     * Consultor de la cantidad de monedas que tiene el tesoro.
     *
     * @return numero entero con la cantidad de monedas de oro.
     */
    public int getGoldCoins() {
        return this.goldCoins;
    }

    /**
     * Consultor del tipo de tesoro que tiene dentro
     *
     * @return devuelve un objeto de tipo TreasureKind del tipo que corresponde
     */
    public TreasureKind getType() {
        return this.type;
    }

    /**
     * Consultor del valor minimo de bonus que se consigue con el tesoro
     *
     * @return numero entero con el valor minimo que tiene el tesoro
     */
    public int getMinBonus() {
        return this.minBonus;
    }

    /**
     * Consultor del valor maximo de bonus que se consigue con el tesoro
     *
     * @return numero entero con el valor maximo que tiene el tesoro
     */
    public int getMaxBonus() {
        return this.maxBonus;
    }

    /**
     * Consultor del nombre del tesoro
     *
     * @return cadena de caracteres con el valor del nombre del tesoro
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Consultor del atributo de bonus minimo
     * @return devuelve un entero con el nivel minimo que proporciona el tesoro
     */
    @Override
    public int getBasicValue() {
        return this.getMinBonus();
    }

    /**
     * Consultor del atributo de bonus máximo
     * @return devuelve un entero con el nivel máximo que proporciona el tesoro
     */
    @Override
    public int getSpecialValue() {
        return this.getMaxBonus();
    }

    @Override
    public String toString() {
        return "Nombre tesoro: " + this.name + " (" + this.type.toString() + ") "
        + "\nBonus mínimo = " + this.minBonus
        + "\nBonus máximo = " + this.maxBonus
        + "\nPiezas de Oro = " + this.goldCoins;
    }
}
