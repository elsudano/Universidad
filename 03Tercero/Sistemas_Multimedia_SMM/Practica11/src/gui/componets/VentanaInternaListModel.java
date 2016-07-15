package gui.componets;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">{user}</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 18-may-2016
 */
public class VentanaInternaListModel extends javax.swing.AbstractListModel<String> {

    /**
     * Estas son las cadenas que se muestran en la lista
     */
    private Item[] items;
    /**
     * Estas son las cadenas de los diferentes items.
     */
    private String[] cadenas;

    /**
     * Constructor sin parámetros
     */
    public VentanaInternaListModel() {
        super();
        this.cadenas = new String[0];
        this.items = new Item[0];
    }

    /**
     * Constructor con un parametro que será la lista a mostrar.
     *
     * @param opc [in] Cadenas que queremos mostrar en la lista
     */
    public VentanaInternaListModel(String[] opc) {
        super();
        this.cadenas = opc;
        this.items = new Item[this.cadenas.length];
        for (int i = 0; i < this.cadenas.length; i++) {
            this.items[i] = new Item(this.cadenas[i], i);
        }
    }

    /**
     * Lista de todos los items, que son las parejas de [String,int] que
     * contiene el modelo esta información almacena el título, y la posición de
     * la ventana.
     *
     * @return Array de pares [String,int] con el título y la posición de la
     * ventana.
     */
    public Item[] getItems() {
        return items;
    }

    /**
     * Lista de todas las cadenas que tiene el componente.
     *
     * @return Objeto de tipo String[] con la lista de opciones.
     */
    public String[] getCadenas() {
        return this.cadenas;
    }

    /**
     * Si queremos actualizar la lista de opciones a mostrar.
     *
     * @param opc [in] Cadenas que queremos mostrar en la lista
     */
    public void setCadenas(String[] opc) {
        this.cadenas = opc;
        this.items = new Item[this.cadenas.length];
        for (int i = 0; i < this.cadenas.length; i++) {
            this.items[i] = new Item(this.cadenas[i], i);
        }
    }

    /**
     * Muestra el tamaño de la lista.
     *
     * @return Cantidad de opciones que se muestran
     */
    @Override
    public int getSize() {
        return this.cadenas.length;
    }

    /**
     * Esto devolvera una cadena con el nombre de la ventana elegida.
     *
     * @param i [in] posición de la selección.
     * @return Objeto tipo String con el nombre de la selección.
     */
    @Override
    public String getElementAt(int i) {
        return this.cadenas[i];
    }
}
