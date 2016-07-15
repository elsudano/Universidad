package napakalaki;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Clase de Mal rollo se encarga de incluir las ociones negativas del juego así
 * cuando se pierde un combate.
 *
 * @author: Carlos de la Torre
 */
public abstract class BadConsequence {

    /**
     * Cantidad máxima de tesoros del mal rollo
     */
    static final int MAXTREASURES = 10;
    /**
     * Texto que indica cual es el mal rollo
     */
    private String text;
    /**
     * Niveles del Mal rollo
     */
    private int levels;
    /**
     * Si el mal rollo al aplicarlo al jugador hace que este muera
     */
    private boolean death;

    /**
     * Constructor de Clase, inicializa los valores del objeto con parámetros de
     * entrada:
     */
    public BadConsequence() {
        this.text = "";
        this.levels = 0;
    }

    /**
     * Constructor con parámetros, inicializa los valores del objeto con
     * parámetros de entrada:
     *
     * @param text numero entero que indica cantidad de tesoros
     * @param levels numero que indica la cantidad de niveles
     * @param death indica si el mal rollo conlleva muerte.
     */
    public BadConsequence(String text, int levels, boolean death) {
        this.text = text;
        this.levels = levels;
        this.death = death;
    }

    /**
     * Devuelve el motivo del mal rollo
     *
     * @return Cadena de caracteres con el nombre del mal rollo
     */
    public String getText() {
        return this.text;
    }

    /**
     * Devuelve el un numero entero con el nivel del mostruo
     *
     * @return nuemro entero con la cantidad de niveles que se pierde
     */
    public int getLevels() {
        return this.levels;
    }

    /**
     * Devuelve el parámetro de muerte
     *
     * @return verdadero o falso si esta muerto o no
     */
    public boolean getDeath() {
        return this.death;
    }

    /**
     * Comprobamos si mi mal rollo tiene muerte y por lo tanto el final de la
     * partida
     *
     * @return verdadero o falso según sea muerte o no.
     */
    public boolean myBadConsequenceIsDeath() {
        return this.death;
        //return this.text.contains("muerto");
    }

    /**
     * Podemos preguntar si el mal rollo esta lleno o esta vacio osea si los
     * tesoros visibles y los ocultos estan vacios.
     *
     * @return true si no hay tesoros almacenados en el mal rollo.
     */
    abstract public boolean isEmpty();

    /**
     * Quitamos un tesoro visible
     *
     * @param treasure es el tesoro que queremos quitar
     */
    abstract public void substractVisibleTreasure(Treasure treasure);

    /**
     * Quitamos un tesoro oculto
     *
     * @param treasure es el tesoro que queremos quitar
     */
    abstract public void substractHiddenTreasure(Treasure treasure);

    /**
     * Metodo para actualizar los datos del mal rollo una vez que se ha aplicado
     * las reglas del juego.
     *
     * @param tVisible array con los tesoros visibles
     * @param tHidden array con los tesoros ocultos
     * @return devolvemos el mismo mal rollo pero con los datos actualizados
     */
    abstract public BadConsequence adjustToFitTreasureList(ArrayList<Treasure> tVisible, ArrayList<Treasure> tHidden);

    /**
     * @TODO Tienes que hacer que este método compruebe si los datos que vienen
     * en los dos array se pueden quitar todos ellos de los datos que tiene el
     * jugador. Osea que si tenemos una mano, un zapato. Podamos quitarselo al
     * jugador. No podemos modificar el this por que modificamos la carta
     * original del mazo. Aparte de eso tenemos que crear un if para saber si
     * los array vienen vacios pues tenemos que quitar el numero de tesoros que
     * se especifican en las variables nVisibleTreasures y nHiddenTreasures del
     * objeto This
     */
    /**
     * Función de comparación entre dos jugadores, esto se sobreescribe por que
     * queremos comparar el estado del objeto jugador, si no lo sobreescribimos
     * solo comparamos su
     *
     * @param bc este es el objeto badconsequence que vamos a comparar.
     * @return devuelve verdadero si son iguales y falso en caso contrario.
     */
    @Override
    abstract public boolean equals(Object bc);

    /**
     * Esta función se crea para asegurarnos de que miramos los datos de los
     * diferentes objetos cuando los comparamos.
     *
     * @return devuelve un numero entero según una función hash.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.text);
        hash = 73 * hash + this.levels;
        hash = 73 * hash + (this.death ? 1 : 0);
        return hash;
    }

    /**
     * Devuelve una cadena de caracteres con todos los valores que contiene el
     * mal rollo, lo imprime por pantalla formateado
     *
     * @return Cadena de caracteres con los valores del mal rollo formateado
     */
    @Override
    public String toString() {
        return "Texto: " + this.text + ", "
                + "\n\tNiveles: " + Integer.toString(this.levels) + ", "
                + "\n\tMuerte: " + Boolean.toString(this.death);
    }
}
