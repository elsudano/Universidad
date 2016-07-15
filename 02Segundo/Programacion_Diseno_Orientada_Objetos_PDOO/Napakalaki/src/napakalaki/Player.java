package napakalaki;

import java.util.ArrayList;
import java.util.Random;
import gui.Dice;
import java.util.Objects;

/**
 * Clase de Jugador que controla todo lo relacionado con el tesoros que posee,
 * tanto ocultos como visibles nombre, o si ha terminado la partida para el.
 *
 * @author: Carlos de la Torre
 */
public class Player {

    /**
     * Indica si el jugador esta Muerto.
     */
    private boolean dead;
    /**
     * Este es el nombre del Jugador.
     */
    private String name;
    /**
     * Nivel de combate del Jugador.
     */
    private int level;
    /**
     * Este sera un enemigo directo del jugador.
     */
    protected Player enemy;
    /**
     * Tesoros ocultos que tiene el Jugador.
     */
    private ArrayList<Treasure> hiddenTreasures;
    /**
     * Tesoros visibles que tiene el Jugador.
     */
    private ArrayList<Treasure> visibleTreasures;
    /**
     * Mal rollo mendiente de cumplir por el jugador.
     */
    private BadConsequence pendingBadConsequence;
    /**
     * Variable que indica .....
     */
    private boolean canISteal;
    /**
     * Indica si el Jugador es un Sectario.
     */
    protected boolean isCultistPlayer;
    /**
     * Constante que indica el nivel minimo de un Jugador.
     */
    private static final int NIVEL_MINIMO = 1;
    /**
     * Constante que indica el nivel máximo de un Jugador.
     */
    static final int NIVEL_MAXIMO = 10;
    /**
     * Constante que indica el numero máximo de tesoros ocultos de un Jugador;
     * Se pone publico puesto que desde la interfaz grafica tambien lo consultamos.
     */
    public static final int TESOROS_OCULTOS_MAXIMO = 4;

    /**
     * Constructor de Clase, inicializa los valores del objeto con parámetros de
     * entrada:
     *
     * @param name este es el nombre del jugador
     */
    public Player(String name) {
        this.name = name;
        this.dead = true;
        this.level = 1;
        this.hiddenTreasures = new ArrayList();
        this.visibleTreasures = new ArrayList();
        this.pendingBadConsequence = new NumericBadConsequence("", 0, this.dead, 0, 0);
        this.canISteal = true;
        this.isCultistPlayer = false;
    }

    /**
     * Constructor de copia (en profundidad) de Jugador, copia un objeto de tipo
     * jugador, a otra variable en el mismo estado en el que se encuentra el
     * original.
     *
     * @param p objeto de tipo Jugador que será el origen.
     */
    public Player(Player p) {
        this.name = p.name;
        this.dead = p.dead;
        this.level = p.level;
        // es este paso no hace falta hacer copia en profundidad 
        // puesto que el jugador se convierte pero sigue teneiendo el mismo 
        // enemigo por eso solo copiamos su referencia.
        this.enemy = p.enemy;
        this.hiddenTreasures = new ArrayList<>();
        for (Treasure t : p.hiddenTreasures){
            this.hiddenTreasures.add(new Treasure(t));
        }
        this.visibleTreasures = new ArrayList<>();
        for (Treasure t : p.visibleTreasures){
            this.visibleTreasures.add(new Treasure(t));
        }
        if (p.pendingBadConsequence instanceof NumericBadConsequence) {
            this.pendingBadConsequence = new NumericBadConsequence((NumericBadConsequence) p.pendingBadConsequence);
        } else if (p.pendingBadConsequence instanceof SpecificBadConsequence) {
            this.pendingBadConsequence = new SpecificBadConsequence((SpecificBadConsequence) p.pendingBadConsequence);
        }
        this.canISteal = p.canISteal;
        this.isCultistPlayer = p.isCultistPlayer;
    }

    /**
     * Metodo auxiliar que pone el parametro de muerte a falso para que el
     * jugador vuelva a la vida.
     */
    private void bringToLife() {
        this.dead = false;
    }

    /**
     * Consultor que indica cual es el nivel de combate de un jugador, este
     * metodo se suele utilizar mucho para compararlo con el nivel de combate de
     * un jugador, para llevar a cabo los enfrentamientos, Devuelve el nivel de
     * combate del jugador, que viene dado por su nivel más los bonus que le
     * proporcionan los tesoros que tenga equipados.
     *
     * @return devuelve un entero con el nivel de combate del Jugador
     */
    protected int getCombatLevel() {
        int cont = this.level;
//        boolean collar = false;
//        de momento se tiene que hacer asi cuando se explique el collar se separaran las bonus
        for (Treasure tesoro : visibleTreasures) {
            cont += tesoro.getBasicValue() + tesoro.getMaxBonus();
        }
//        for (Treasure t : visibleTreasures) {
//            if (t.getType().equals(TreasureKind.NECKLACE)) {
//                collar = true;
//            }
//        }
//        if (collar) {
//            for (Treasure t1 : visibleTreasures) {
//                cont += t1.getSpecialValue();
//            }
//        } else {
//            for (Treasure t2 : visibleTreasures) {
//                cont += t2.getBasicValue();
//            }
//        }
        return cont;
    }

    /**
     * Metodo Auxiliar que añade una cantidad de nivel al usuario según el
     * parámetro de entrada
     *
     * @param i cantidad que se quiere añadir al nivel de combate del jugador
     */
    private void incrementLevels(int i) {
        this.level += i;
        if (this.level > NIVEL_MAXIMO) {
            this.level = NIVEL_MAXIMO;
        }
    }

    /**
     * Metodo Auxiliar que quita una cantidad de nivel al usuario según el
     * parámetro de entrada
     *
     * @param i cantidad que se quiere quitar al nivel de combate del jugador
     */
    private void decrementLevels(int i) {
        this.level -= i;
        if (this.level < NIVEL_MINIMO) {
            this.level = NIVEL_MINIMO;
        }
    }

    /**
     * Metódo auxiliar para asignar un mal rollo al jugador, se le asigna el mal
     * rollo de entrada a un estado pendiente y después se calcula cual es su
     * repercución
     *
     * @param bc será el mal rollo que se aplicara al jugador.
     */
    private void setPendingBadConsequence(BadConsequence b) {
        this.pendingBadConsequence = b;
    }

    /**
     * Método auxiliar que procede a matar al jugador que no tiene ningún tesoro
     * entre los visibles y los ocultos, por lo tanto no puede seguir jugando.
     */
    private void dieIfNoTreasures() {
        if (this.hiddenTreasures.isEmpty() && this.visibleTreasures.isEmpty()) {
            this.dead = true;
        }
    }

    /**
     * Método auxuiliar que comprueba la cantidad de monedas que tiene el
     * Jugador entre todos los tesoros que tiene acumulados
     *
     * @param treasures colección de tesoros desde los que se van a calcular la
     * cantidad de monedas
     * @return devuelve un valor entero con la cantidad de monedas que tiene el
     * jugador
     */
    private int computeGoldCoinsValue(ArrayList<Treasure> treasures) {
        int cont = 0;
        for (Treasure t : treasures) {
            cont += t.getGoldCoins();
        }
        // es posible comprar un nivel con 1.000 piezas de oro.
        cont /= 1000; // Redondeado hacia abajo 8.9 = 8
        return cont;
    }

    /**
     * Método auxiliar que indica si se pueden comprar mas niveles de combate
     *
     * @param I es un entero con el numero de niveles de combate que se quieren
     * comprar
     * @return devuelve verdadero si se puede comprar los niveles y falso en
     * caso contrario
     */
    private boolean canIBuyLevels(int I) {
        return ((this.level + I) < 10);
    }

    /**
     * Método auxiliar que sirve para aplicar el precio que tiemne un monstruo a
     * un jugador cuando este ultimo gana el combate contra el monstruo
     *
     * @param m es el monstruo el cuál contiene las ganancias del jugador
     */
    private void applyPrize(Monster m) {
        int nlevels = m.getLevelsGained();
        this.incrementLevels(nlevels);
        int nTreasures = m.getTreasuresGained();
        if (nTreasures > 0) {
            for (int i = 0; i < nTreasures; i++) {
                Treasure treasure = Napakalaki.getInstance().getDealer().nextTreasure();
                hiddenTreasures.add(treasure);
            }
        }

    }

    /**
     * Método auxiliar que se encarga de aplicar el mal rollo que se pasa por
     * parametro al jugador.
     *
     * @param m objeto de tipo monstruo que contiene los datos a aplicar el mal
     * rollo al jugador
     */
    private void applyBadConsequence(Monster m) {
        int nlevels = m.getBadConsequence().getLevels();
        this.decrementLevels(nlevels);
        BadConsequence pendingBad = m.getBadConsequence().adjustToFitTreasureList(this.visibleTreasures, this.hiddenTreasures);
        this.setPendingBadConsequence(pendingBad);
    }

    /**
     * Método auxiliar que se encarga de convertir un tesoro que se pasa por
     * parametros en un tesoro visble, o lo que es lo mismo que pase a se usado.
     *
     * @param t tesoro que se va a marcar como visible.
     * @return si el tesoro se pone visible devuelve true, en caso contrario
     * false.
     */
    private boolean canMakeTreasureVisible(Treasure t) {
        boolean puede = true;
        int cont = 0;
        switch (t.getType().toString()) {
            case "1 Mano":
                for (Treasure tesoro : visibleTreasures) {
                    if (tesoro.getType().equals(TreasureKind.ONEHAND)) {
                        cont++;
                    }
                    if (tesoro.getType().equals(TreasureKind.BOTHHANDS)) {
                        puede = false;
                    }
                }
                if (cont >= 2) {
                    puede = false;
                }
                break;
            case "2 Manos":
                for (Treasure tesoro : visibleTreasures) {
                    if (tesoro.getType().equals(TreasureKind.ONEHAND)) {
                        cont++;
                    }
                    if (tesoro.getType().equals(TreasureKind.BOTHHANDS)) {
                        puede = false;
                    }
                }
                if (cont > 0) {
                    puede = false;
                }
                break;
            default:
                for (Treasure tesoro : visibleTreasures) {
                    if (tesoro.getType().equals(t.getType())) {
                        puede = false;
                    }
                }
        }
        return puede;
    }

    /**
     * Método auxiliar que se encarga de comprobar si los tesoros que se pasan
     * por parametro se encuentra entre los que tiene el jugador.
     *
     * @param tKind tipo de tesoro que se compara con uno de los que tiene el
     * jugador
     * @return devuelve la cantidad de tesoros visibles que tiene el jugador
     */
    private int howManyVisibleTreasures(TreasureKind tKind) {
        int cont = 0;
        for (int i = 0; i < visibleTreasures.size(); i++) {
            if (visibleTreasures.get(i).getType().equals(tKind)) {
                cont++;
            }
        }
        return cont;
    }

    /**
     * Método auxiliar que sirve para pedir un tesoro al azar de la lista de
     * cartas de tesoros ocultos y asignarselo al juegador actual
     *
     * @return objeto de tipo tesoro
     */
    private Treasure giveMeATreasure() {
        Random generator = new Random();
        int num_tesoro = generator.nextInt(this.hiddenTreasures.size());
        Treasure myTesoro = this.hiddenTreasures.get(num_tesoro);
        /**
         * Cuidado con esta función por que se especifica que solo se devuelve
         * un tesoro de la lista de ocultos del enemigo pero no se especifica
         * que se tenga que quitar del array puesto que puede servir para mas
         * cosas a parte de para robar un tesoro.
         */
        //this.hiddenTreasures.remove(myTesoro);
        return myTesoro;
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
         * Como solo se puede devolver un tesoro oculto del jugador solo se
         * comprueba el tamaño del array de ocultos
         */
        return !this.hiddenTreasures.isEmpty();
    }

    /**
     * Método auxiliar que cambia el atributo canISteal a false cuando el
     * jugador roba un tesoro.
     */
    private void haveStolen() {
        this.canISteal = false;
    }

    /**
     * Método que se encarga de descartar el collar visible de la lista de
     * tesoros visibles una vez que se utiliza.
     */
    public void dicardNecklaceIfVisible() {
        for (Treasure t : visibleTreasures) {
            if (t.getType().equals(TreasureKind.NECKLACE)) {
                Napakalaki.getInstance().getDealer().giveTreasureBack(t);
            }
        }

    }

    /**
     * Método que asigna que Nivel queremos ponerle al Jugador.
     *
     * @param n numero de nivel que vamos a asignar al jugador
     */
    public void setLevel(int n) {
        this.level = n;
    }

    /**
     * Método que se encarga de matar al jugador y volverlo a colocar en el
     * estado inicial de la partida asi de esa manera empezara desde el
     * principio.
     */
    public void die() {
        this.setLevel(1);

        for (Treasure treasure : visibleTreasures) {
            Napakalaki.getInstance().getDealer().giveTreasureBack(treasure);
        }
        visibleTreasures.clear();
        for (Treasure treasure : hiddenTreasures) {
            Napakalaki.getInstance().getDealer().giveTreasureBack(treasure);
        }
        hiddenTreasures.clear();
        this.dieIfNoTreasures();
    }

    /**
     * Devuelve true si el jugador está muerto, false en caso contrario.
     *
     * @return devuelve verdadero si el jugador esta muerto, falso en caso
     * contrario.
     */
    public boolean isDead() {
        return this.dead;
    }

    /**
     * Consultor de todos los tesoros ocultos que tiene el Jugador.
     *
     * @return devuelve una colección de tesoros de tipo ArrayList
     */
    public ArrayList<Treasure> getHiddenTreasures() {
        return this.hiddenTreasures;
    }

    /**
     * Consultor de todos los tesoros visibles que tiene el Jugador.
     *
     * @return devuelve una colección de tesoros de tipo ArrayList
     */
    public ArrayList<Treasure> getVisibleTreasures() {
        return this.visibleTreasures;
    }

    /**
     * Realiza los calculos necesarios para que se lleve a cabo un combate entre
     * el jugador y el monstruo que se recibe por la entrada del parametro m.
     *
     * @param m objeto de tipo monstruo con el cual se batira en duelo en
     * jugador.
     * @return devuelve un tipo CombatResult con el resultado calculado del
     * combate.
     */
    public CombatResult combat(Monster m) {
        CombatResult cr;
        int myLevel = this.getCombatLevel();
        int monsterLevel = this.getOponentLevel(m);
        if (myLevel > monsterLevel) {
            this.applyPrize(m);
            if (this.getLevels() >= NIVEL_MAXIMO) {
                cr = CombatResult.WINGAME;
            } else {
                cr = CombatResult.WIN;
            }
        } else {
            this.applyBadConsequence(m);
            if (this.shouldConvert()) {
                cr = CombatResult.LOSEANDCONVERT;
            } else {
                cr = CombatResult.LOSE;
            }
        }
        return cr;
    }

    /**
     * Método que sirve para convertir el objeto de tipo tesoro t en uno de los
     * que están en los visibles.
     *
     * @param t objeto de tipo tesoro que se agregara a los visibles.
     */
    public void makeTreasureVisible(Treasure t) {
        boolean canI = this.canMakeTreasureVisible(t);
        if (canI) {
            this.visibleTreasures.add(t);
            this.hiddenTreasures.remove(t);
        }
    }

    /**
     * Cuando un tesoro se usa se tiene que descartar de la colección a la que
     * pertenezca, es por eso que este metodo se usua para descartar los tesoros
     * que ya se han usuado y que esten en los visibles.
     *
     * @param t este será el tesoro que vamos a quitar de la colección.
     */
    public void discardVisibleTreasure(Treasure t) {
        this.visibleTreasures.remove(t);
        if ((pendingBadConsequence != null) && (!pendingBadConsequence.isEmpty())) {
            this.pendingBadConsequence.substractVisibleTreasure(t);
        }
        this.dieIfNoTreasures();
    }

    /**
     * Cuando un tesoro se usa se tiene que descartar de la colección a la que
     * pertenezca, es por eso que este metodo se usua para descartar los tesoros
     * que ya se han usuado y que esten en los ocultos.
     *
     * @param t este será el tesoro que vamos a quitar de la colección.
     */
    public void discardHiddenTreasure(Treasure t) {
        this.hiddenTreasures.remove(t);
        if ((pendingBadConsequence != null) && (!pendingBadConsequence.isEmpty())) {
            this.pendingBadConsequence.substractHiddenTreasure(t);
        }
        this.dieIfNoTreasures();
    }

    /**
     * Método que se encarga de cambiar todos los tesoros que tiene el jugador
     * en su poder tanto los visibles como los ocultos por mas nivel de combate
     *
     * @param visible colección de tesoros visibles que quiere intercambiar el
     * jugador
     * @param hidden colección de tesoros ocultos que quiere intercambiar el
     * jugador
     * @return devuelve verdadero si con los tesoros que tiene el jugador se
     * pueden conseguir mas niveles de combate, falso en caso contrario
     */
    public boolean buyLevels(ArrayList<Treasure> visible, ArrayList<Treasure> hidden) {
        int levelsMayBought = this.computeGoldCoinsValue(visible);
        levelsMayBought += this.computeGoldCoinsValue(hidden);
        boolean canI = this.canIBuyLevels(levelsMayBought);
        if (canI) {
            this.incrementLevels(levelsMayBought);
        }
        visibleTreasures.removeAll(visible);
        hiddenTreasures.removeAll(hidden);

        for (Treasure t : visible) {
            Napakalaki.getInstance().getDealer().giveTreasureBack(t);
        }
        for (Treasure t : hidden) {
            Napakalaki.getInstance().getDealer().giveTreasureBack(t);
        }
        return canI;
    }

    /**
     * Método que sirve para poder validar el estado correcto de un jugador,
     * osea que despues de hacer todas las operaciones este tenga un estado
     * valido
     *
     * @return devuelve true si no tenemos ningún mal rollo pendiente de asignar
     * y si el tamaño de los tesoros ocultos es menor que
     * Player.TESOROS_OCULTOS_MAXIMO
     */
    public boolean validState() {
        return (Player.TESOROS_OCULTOS_MAXIMO >= this.hiddenTreasures.size() && this.pendingBadConsequence.isEmpty());
    }

    /**
     * Metodo que se usa para asignar los tesoros que le corresponden a cada
     * jugador al principio de la partida.
     */
    public void initTreasures() {
        CardDealer dealer = Napakalaki.getInstance().getDealer();
        Dice dice = Dice.getInstance();
        this.bringToLife();
        Treasure treasure = dealer.nextTreasure();
        this.hiddenTreasures.add(treasure);
        int number = dice.nextNumber("Según el número tendremos:", "1 = 1 Tesoro, 2...5 = 2 Tesoros, 6 = 3 Tesoros");
        if (number > 1) {
            treasure = dealer.nextTreasure();
            this.hiddenTreasures.add(treasure);
        }
        if (number == 6) {
            treasure = dealer.nextTreasure();
            this.hiddenTreasures.add(treasure);
        }

    }

    /**
     * Método que sirve para robar un tesoro a otro jugador, se encarga de
     * comprobar que es posible y de robarlo en caso afirmativo de lo contrario
     * devuelve null
     *
     * @return devuelve un tesoro que se ha robado o null en caso de no poder
     * robar ninguno
     */
    public Treasure stealTreasure() {
// Esta manera de implementarlo no necesita de variables auxiliares
// pero se implementa de la segunda versión por que es lo que pone en el Guión
//        Treasure treasure = null;
//        if (this.canISteal())
//            if (this.enemy.canYouGiveMeaATreasure()){
//                treasure = this.enemy.giveMeATreasure();
//                this.hiddenTreasures.add(treasure);
//                this.haveStolen();                  
//            }
//        return treasure;
        boolean canI = this.canISteal(), canYou = false;
        Treasure treasure = null;
        if (canI) {
            canYou = this.enemy.canYouGiveMeaATreasure();
        }
        if (canYou) {
            treasure = this.enemy.giveMeATreasure();
            this.hiddenTreasures.add(treasure);
            this.haveStolen();
        }
        return treasure;
    }

    /**
     * Metodo que se usa para asignar un enemigo al jugador actual.
     *
     * @param enemy objeto de tipo jugador que será asignado al Jugador actual
     */
    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }

    /**
     * Devuelve el valor de la variable canISteal, que indica si el jugador ha
     * robado antes o no un tesoro a su enemigo.
     *
     * @return devuelve verdadero si el jugador a robado un tesoro en caso
     * contrario devuelve falso.
     */
    public boolean canISteal() {
        return this.canISteal;
    }

    /**
     * Metodo que sirve para descartarse de todos los tesoros que tiene el
     * jugador, tanto los visibles como los ocultos.
     */
    public void discardAllTreasures() {
        ArrayList<Treasure> auxv = new ArrayList(visibleTreasures);
        ArrayList<Treasure> auxh = new ArrayList(hiddenTreasures);
        for (Treasure tesoro : auxv) {
            this.discardVisibleTreasure(tesoro);
        }
        for (Treasure tesoro : auxh) {
            this.discardHiddenTreasure(tesoro);
        }
    }

    /**
     * Sirve para comprobar si existen tesoros visibles.
     *
     * @return devuelve verdadero si el jugador tiene tesoros visibles, falso en
     * caso contrario
     */
    public boolean hasVisibleTreasures() {
        return (this.visibleTreasures.size() > 0);
    }

    /**
     * Consultor de la cantidad de nivel de combate que tiene el jugador
     *
     * @return devuelve un entero que va desde 1, (nivel mas bajo), hasta 10,
     * (nivel mas alto)
     */
    public int getLevels() {
        return this.level;
    }

    /**
     * Consultor del nombre del jugador.
     *
     * @return devuelve una cadena de caracteres con el nombre del Jugador
     */
    public String getName() {
        return this.name;
    }

    protected int getOponentLevel(Monster m) {
        return m.getBasicValue();
    }

    protected boolean shouldConvert() {
        Dice dado = Dice.getInstance();
        return (dado.nextNumber("Has perdido....", "Si sacas un 1 te conviertes") == 1);
    }

    public BadConsequence getPendingBadConsequence() {
        return this.pendingBadConsequence;
    }

    protected boolean isCultistPlayer() {
        return isCultistPlayer;
    }

    public Player getEnemy() {
        return this.enemy;
    }

    /**
     * Devuelve el contenido del jugador impreso por la pantalla de manera
     * formateada
     *
     * @return cadena de caracteres con el contenido del jugador
     */
    @Override
    public String toString() {
        String formateado = this.name;
        formateado += "\nNivel del Jugador:" + this.getLevels();
        formateado += "\nEnemigo del Jugador:" + this.enemy.getName();
        formateado += "\t\nTesoros Ocultos:";
        for (Treasure tesoro : hiddenTreasures) {
            formateado += tesoro.getName() + " | ";
        }
        formateado += "\t\nTesoros Visibles:";
        for (Treasure tesoro : visibleTreasures) {
            formateado += tesoro.getName() + " | ";
        }
        if (pendingBadConsequence != null) {
            formateado += "\t\nMal rollo Pendiente: " + pendingBadConsequence.toString();
            formateado += "\t\nNivel de Combate: " + level + "\n";
            formateado += "\t\nEsta Muerto: " + dead;
        }
        return formateado;
    }

    /**
     * Función de comparación entre dos jugadores, esto se sobreescribe por que
     * queremos comparar el estado del objeto jugador, si no lo sobreescribimos
     * solo comparamos su referencia.
     * @param player tipo de objeto player con el que vamos a comparar.
     * @return devuelve verdadero si son iguales y falso en caso contrario.
     */
    @Override
    public boolean equals(Object player) {
        boolean aux = false;
        if (player instanceof Player) {
            if (((Player) player).name.equals(this.name)
                    && ((Player) player).dead == this.dead
                    && ((Player) player).level == this.level
                    && ((Player) player).hiddenTreasures.equals(this.hiddenTreasures)
                    && ((Player) player).visibleTreasures.equals(this.visibleTreasures)
                    && ((Player) player).pendingBadConsequence.equals(this.pendingBadConsequence)
                    && ((Player) player).canISteal == this.canISteal
                    && ((Player) player).isCultistPlayer == this.isCultistPlayer) {
                aux = true;
            }
        }
        return aux;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.dead ? 1 : 0);
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + this.level;
        hash = 71 * hash + Objects.hashCode(this.hiddenTreasures);
        hash = 71 * hash + Objects.hashCode(this.visibleTreasures);
        hash = 71 * hash + Objects.hashCode(this.pendingBadConsequence);
        hash = 71 * hash + (this.canISteal ? 1 : 0);
        hash = 71 * hash + (this.isCultistPlayer ? 1 : 0);
        return hash;
    }
}
