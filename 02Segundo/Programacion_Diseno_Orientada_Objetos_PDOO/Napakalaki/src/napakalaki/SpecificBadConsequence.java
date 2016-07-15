package napakalaki;

import java.util.ArrayList;
import java.util.Objects;

/**
 * 
 * @author Carlos de la Torre
 */
public class SpecificBadConsequence extends BadConsequence {

    /**
     * Tesoros ocultos que se pierden si se aplica el mal rollo
     */
    private final ArrayList<TreasureKind> specificHiddenTreasures = new ArrayList();
    /**
     * Tesoros visibles que se pierden si se aplica el mal rollo
     */
    private final ArrayList<TreasureKind> specificVisibleTreasures = new ArrayList();

    /**
     * Constructor de Clase, inicializa los valores del objeto con parámetros de
     * entrada:
     */
    public SpecificBadConsequence() {
        super();
    }

    /**
     * Constructor con parámetros, inicializa los valores del objeto con
     * parámetros de entrada:
     *
     * @param text numero entero que indica cantidad de tesoros
     * @param levels numero que indica la cantidad de niveles
     * @param death indica si el mal rollo conlleva muerte.
     * @param tVisible array con los tesoros visibles
     * @param tHidden array con los tesoros ocultos
     */
    public SpecificBadConsequence(String text, int levels, boolean death, ArrayList<TreasureKind> tVisible, ArrayList<TreasureKind> tHidden) {
        super(text, levels, death);
        this.specificVisibleTreasures.addAll(tVisible);
        this.specificHiddenTreasures.addAll(tHidden);
    }

    /**
     * Constructor de copia (en profundidad) del objeto tipo
     * SpecificBadConsequence
     *
     * @param bc Objeto del tipo SpecificBadConsequence que queremos copiar.
     */
    public SpecificBadConsequence(SpecificBadConsequence bc) {
        super(bc.getText(), bc.getLevels(), bc.getDeath());
        for (TreasureKind tk : bc.specificVisibleTreasures) {
            this.specificVisibleTreasures.add(tk);
        }
        for (TreasureKind tk : bc.specificHiddenTreasures) {
            this.specificHiddenTreasures.add(tk);
        }

    }

    /**
     * Devuelve un array con todos los tesoros ocultos que contiene el mal rollo
     *
     * @return Array con todos los tesoros ocultos
     */
    public ArrayList<TreasureKind> getSpecificHiddenTreasures() {
        return this.specificHiddenTreasures;
    }

    /**
     * Devuelve un array con todos los tesoros visibles que contiene el mal
     * rollo
     *
     * @return Array con todos los tesoros visibles
     */
    public ArrayList<TreasureKind> getSpecificVisibleTreasures() {
        return this.specificVisibleTreasures;
    }

    /**
     * Metodo para actualizar los datos del mal rollo una vez que se ha aplicado
     * las reglas del juego.
     *
     * @param tVisible array con los tesoros visibles
     * @param tHidden array con los tesoros ocultos
     * @return devolvemos el mismo mal rollo pero con los datos actualizados
     */
    @Override
    public SpecificBadConsequence adjustToFitTreasureList(ArrayList<Treasure> tVisible, ArrayList<Treasure> tHidden) {
        SpecificBadConsequence bs;
        ArrayList<TreasureKind> auxv = new ArrayList<>();
        ArrayList<TreasureKind> auxh = new ArrayList<>();
        ArrayList<TreasureKind> copiaMonsterVisibleTreasures = new ArrayList<>(this.specificVisibleTreasures);
        ArrayList<TreasureKind> copiaMonsterHiddenTreasures = new ArrayList<>(this.specificHiddenTreasures);
        if (!tVisible.isEmpty() || !tHidden.isEmpty()) {
            //Visible
            for (Treasure tparam : tVisible) {
                for (int i = 0; i < copiaMonsterVisibleTreasures.size(); i++) {
                    if (tparam.getType() == copiaMonsterVisibleTreasures.get(i)) {
                        auxv.add(copiaMonsterVisibleTreasures.get(i));
                        copiaMonsterVisibleTreasures.remove(copiaMonsterVisibleTreasures.get(i));
                        i = 0;
                        break;
                    }
                }
            }

            //Hidden
            for (Treasure tparam : tHidden) {
                for (int i = 0; i < copiaMonsterHiddenTreasures.size(); i++) {
                    if (tparam.getType() == copiaMonsterHiddenTreasures.get(i)) {
                        auxh.add(copiaMonsterHiddenTreasures.get(i));
                        copiaMonsterHiddenTreasures.remove(copiaMonsterHiddenTreasures.get(i));
                        i = 0;
                        break;
                    }
                }
            }
            bs = new SpecificBadConsequence(this.getText(), this.getLevels(), this.getDeath(), auxv, auxh);
        } else {
            bs = new SpecificBadConsequence(this.getText(), this.getLevels(), this.getDeath(), auxv, auxh);
        }
        return bs;
    }

    /**
     * Quitamos un tesoro visible
     *
     * @param treasure es el tesoro que queremos quitar
     */
    @Override
    public void substractVisibleTreasure(Treasure treasure) {
        this.specificVisibleTreasures.remove(treasure.getType());
//        if (this.nVisibleTreasures > 0) {
//            this.nVisibleTreasures--;
//        }
    }

    /**
     * Quitamos un tesoro oculto
     *
     * @param treasure es el tesoro que queremos quitar
     */
    @Override
    public void substractHiddenTreasure(Treasure treasure) {
        this.specificHiddenTreasures.remove(treasure.getType());
//        if (this.nHiddenTreasures > 0) {
//            this.nHiddenTreasures--;
//        }
    }

    /**
     * Podemos preguntar si el mal rollo esta lleno o esta vacio osea si los
     * tesoros visibles y los ocultos estan vacios.
     *
     * @return true si no hay tesoros almacenados en el mal rollo.
     */
    @Override
    public boolean isEmpty() {
        return (this.specificHiddenTreasures.isEmpty() && this.specificVisibleTreasures.isEmpty());
    }

    /**
     * Comparamos dos objetos de tipo SpecificBadConsequence y decidimos si son
     * iguales según los parametros del objeto que queremos comparar; Es una
     * sebrescritura del operador de comparación.
     *
     * @param sbc Es un objeto de tipo SpecificBadConsequence que comparamos.
     * @return devuelve true si son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object sbc) {
        boolean aux = false;
        if (sbc instanceof SpecificBadConsequence) {
            if (((SpecificBadConsequence) sbc).getText().equals(this.getText())
                    && ((SpecificBadConsequence) sbc).getLevels() == this.getLevels()
                    && ((SpecificBadConsequence) sbc).getDeath() == this.getDeath()
                    && ((SpecificBadConsequence) sbc).specificVisibleTreasures.equals(this.specificVisibleTreasures)
                    && ((SpecificBadConsequence) sbc).specificHiddenTreasures.equals(this.specificHiddenTreasures)) {
                aux = true;
            }
        }
        return aux;
    }

    /**
     * Esta función se crea para asegurarnos de que miramos los datos de los
     * diferentes objetos cuando los comparamos.
     *
     * @return devuelve un numero entero según una función hash.
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.specificHiddenTreasures);
        hash = 67 * hash + Objects.hashCode(this.specificVisibleTreasures);
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
        return super.toString() + "";
    }
}
