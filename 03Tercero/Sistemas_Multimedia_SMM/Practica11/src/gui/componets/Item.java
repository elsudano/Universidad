package gui.componets;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">{user}</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 19-may-2016
 * 
 * Clase publica para crear componentes de Item que contienen el nombre de la
 * ventana y la posición de la misma dentro del array de ventanas del escritorio
 * de la ventana principal.
 */
public class Item {

    /**
     * Este es el título del item.
     */
    public String title;
    /**
     * Esta es la posición del item.
     */
    public int pos;
    /**
     * Indica si el elemento esta seleccionado
     */
    private boolean selected;

    /**
     * Para crear un nuevo item.
     *
     * @param t [in] será el titulo del item.
     * @param p [in] será la posición del item.
     */
    public Item(String t, int p) {
        this.title = t;
        this.pos = p;
    }

    /**
     * Método que nos indica si el elemento esta seleccionado.
     *
     * @return Verdadero si esta seleccionado, falso en caso contrario.
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Método con el que podemos poner un elemento como seleccionado o no.
     *
     * @param s [in] De tipo boolean indica el estado.
     */
    public void setSelected(boolean s) {
        this.selected = s;
    }
}
