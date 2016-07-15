package gui.componets;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">{user}</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 19-may-2016
 * 
 * Clase publica para crear componentes de ítem que contienen el nombre de la
 * ventana y la posición de la misma dentro del array de ventanas del escritorio
 * de la ventana principal.
 */
public class Item {

    /**
     * Este es el nombre del ítem de sonido.
     */
    public String name;
    /**
     * Esta es la extensión del ítem de sonido.
     */
    public String ext;
    /**
     * Esta es la ruta completa del ítem de sonido.
     */
    public String path;
    /**
     * Esta es la posición del ítem de sonido.
     */
    public int pos;

    /**
     * Para crear un nuevo ítem multimedia.
     *
     * @param n [in] será el nombre del ítem de sonido.
     * @param e [in] será la extensión del fichero de sonido.
     * @param r [in] será la ruta completa del fichero de sonido.
     * @param p [in] será la posición del ítem de sonido.
     */
    public Item(String n, String e, String r, int p) {
        this.name = n;
        this.ext = e;
        this.path = r;
        this.pos = p;
    }
}
