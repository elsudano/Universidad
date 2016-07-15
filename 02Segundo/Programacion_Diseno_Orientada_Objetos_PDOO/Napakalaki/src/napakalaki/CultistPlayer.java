package napakalaki;

import java.util.ArrayList;
import java.util.Random;

/**
 * Clase de Jugador Sectario se extiende de Jugador por lo tanto tiene las
 * mismas funciones que Jugador pero se añaden funcionalidades para la parte de
 * Sectarios
 *
 * @author: Carlos de la Torre
 */
public class CultistPlayer extends Player {

    /*
     * Variable global que indica la cantidad de jugadores sectarios del juego
     */
    private static int totalCultistPlayers = 0;
    /*
     * Variable de la carta que pertenece al jugador
     */
    private Cultist myCultistCard;

    /**
     * Construtor por defecto, se genera un jugador sectario.
     * @param player jugador desde donde se convierte
     * @param card carta que se le asigna al sectario
     */
    public CultistPlayer(Player player, Cultist card) {
        super(player);
        myCultistCard = card;
        incrementCultists();
        super.isCultistPlayer = true;
    }

    /**
     * Incrementa en una unidad la cantidad de jugadores sectarios del
     * juego.
     */
    private void incrementCultists() {
        totalCultistPlayers++;
    }

    /**
     * Devuelve el valor de combate que tiene el jugador sectario, tiene
     * la diferencia con un jugador normal que el valor que devuelve es un valor
     * calculado de la siguiente manera: [Valor del jugador]+[20% Valor del
     * Jugador]+[(niveles de la carta sectario) * (cantidad de jugadores
     * sectarios del juego)].
     * @return nivel de combate calculado según parametros del juego
     */
    @Override
    protected int getCombatLevel() {
        return super.getCombatLevel() + ((super.getCombatLevel() * 20) / 100) + myCultistCard.getSpecialValue();
    }

    /**
     * Pregunta al jugador si es posible convertirse en un jugador
     * sectario en este caso siempre tendremos que decir que no por que ya somos
     * un jugador sectario y no tenemos posibilidad de cambiar de estado.
     * @return false
     */
    @Override
    protected boolean shouldConvert() {
        return false;
    }

    /**
     * Nos indica cual es el nivel de combate de nuestro oponente
     * teniendo en cuenta las reglas del juego.
     * @param m este sera el monstruo con el que nos enfretamos
     * @return devielve un numero con el nivel de combate
     */
    @Override
    protected int getOponentLevel(Monster m) {
        return m.getSpecialValue();
    }

    /**
     * Nos muestra cual será la cantida de monedas de oro que tenemos
     * con todos los tesoros que hay en parametro de entrada.
     * @param treasures lista de tesoros con los que queremos calcular las monedas
     * @return devuelve un numero que esrá el total de monedas que tenemos
     */
    public int computeGoldCoinsValue(ArrayList<Treasure> treasures) {
        int goldCoins = 0;
        for (Treasure t : treasures)
            goldCoins += 2 * t.getGoldCoins();
        return goldCoins / 1000;
    }

    /**
     * Devuelve la cantidad de jugadores sectarios que se encuentran
     * activos en la partida actual
     * @return devuelve un numero con la cantidad de juegadores sectarios
     */
    public static int getTotalCultistPlayers() {
        return totalCultistPlayers;
    }

    /**
     * Devuelve la carta del jugador sectario tiene asignada
     * @return devuelve un objeto de tipo Cultist
     */
    public Cultist getMyCultistCard() {
        return this.myCultistCard;
    }

    /**
     * Devuelve true si el jugador actual es un jugador sectario,
     * false en caso contrario
     * @return devuelve true si es un jugador sectario
     */
    @Override
    public boolean isCultistPlayer() {
        return super.isCultistPlayer();
    }

    /**
     * Método auxiliar que devuelve true si el jugador tiene tesoros para ser
     * robados por otro jugador y false en caso contrario.
     *
     * @return devuelve verdadero si hay tesoros para robar en caso contrario
     * devuelve falso.
     */
    private boolean canYouGiveMeaATreasure() {
        /**
         * Como solo se puede devolver un tesoro visible del jugador solo se
         * comprueba el tamaño del array de visibles
         */
        return !super.getVisibleTreasures().isEmpty();
    }

    /**
     * Método auxiliar que sirve para pedir un tesoro al azar de la lista de
     * cartas de tesoros VISIBLES y asignarselo al juegador actual
     *
     * @return objeto de tipo tesoro
     */
    private Treasure giveMeATreasure() {
        Random generator = new Random();
        int num_tesoro = generator.nextInt(super.getHiddenTreasures().size());
        Treasure myTesoro = super.getVisibleTreasures().get(num_tesoro);
        /**
         * Cuidado con esta función por que se especifica que solo se devuelve
         * un tesoro de la lista de ocultos del enemigo pero no se especifica
         * que se tenga que quitar del array puesto que puede servir para mas
         * cosas a parte de para robar un tesoro.
         */
        //super.getVisibleTreasures().remove(myTesoro);
        return myTesoro;
    }

    /**
     * Imprime por pantalla el objeto CultisPlayer actual
     * @return devuelve formateado para la pantalla el jugador actual
     */
    @Override
    public String toString() {
        return super.toString() + "\nJugador sectario: "
                + "\n\tBonus basico: " + myCultistCard.getBasicValue()
                + " | Bonus especial: " + myCultistCard.getSpecialValue();
    }
}
