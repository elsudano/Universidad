package sm.cdlt.graphics;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 06-abr-2016 @since 0.1
 */
public abstract class myShape {

    /**
     * Variable que guardara cualquier tipo de forma.
     */
    protected Shape myForma;
    /**
     * Lista de puntos que contiene la forma, se considera que cualquier forma
     * esta construida a base de puntos.
     */
    protected ArrayList<geoPoint> list_of_points;
    /**
     * Estas serán las modificaciones de render que se apliquen a la forma.
     */
    protected RenderingHints render;
    /**
     * Nos indica el tipo de discontinuidad que tiene la linea.
     */
    protected myDiscontinuity discontinuity_type;
    /**
     * Variable que almacena el estado para mostrar los limites de la figura
     */
    protected boolean showBounding;
    /**
     * Variable que almacena el boundig
     */
    protected myBounding bounding_rect;

    /**
     * Constructor sin parámetros.
     */
    public myShape() {
        this.list_of_points = new ArrayList<geoPoint>();
        this.discontinuity_type = myDiscontinuity.NO_DISC;
        this.showBounding = false;
        this.bounding_rect = new myBounding(new geoPoint(), 0, 0);
    }

    /**
     * Constructor de una forma con un parámetro, este constructor se puede usar
     * para crear y la forma y después ir pasándole diferentes mensajes hasta
     * conseguir que la forma final deseada.
     *
     * @param p [in] Objeto de tipo geoPoint que añade un punto a la forma.
     */
    public myShape(geoPoint p) {
        this.list_of_points = new ArrayList<geoPoint>();
        this.list_of_points.add(p);
        this.discontinuity_type = myDiscontinuity.NO_DISC;
        this.showBounding = false;
        this.bounding_rect = new myBounding(p, 0, 0);
    }

    /**
     * Este método devuelve la lista completa de puntos por los que esta formado
     * la forma.
     *
     * @return Devuelve una lista de objetos geoPoint.
     */
    public ArrayList getPoints() {
        return this.list_of_points;
    }

    /**
     * Con este método activamos el alisado, esto significa que se dibuja con
     * antialiasing.
     *
     * @param f [in] Si es verdadero se activa, se desactiva en caso contrario.
     */
    public void setFlatten(boolean f) {
        this.render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        if (f) {
            this.render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
    }

    /**
     * Con este método podemos comprobar si el antialiasing esta activado.
     *
     * @return Verdadero si el antialiasing esta activado falso en caso
     * contrario.
     */
    public boolean getFlatten() {
        boolean aux = false;
        if (this.render.containsValue(RenderingHints.VALUE_ANTIALIAS_ON)) {
            aux = true;
        }
        return aux;
    }

    /**
     * Devuelve el estado que se encuentra la discontinuidad de la linea.
     *
     * @return Devuelve verdadero si la linea tiene discontinuidad, falso en
     * caso contrario.
     */
    public boolean isDiscontinuity() {
        return this.discontinuity_type != myDiscontinuity.NO_DISC;
    }

    /**
     * Devuelve el tipo de discontinuidad que tiene la linea en caso de tenerla.
     *
     * @return Devuelve un objeto myDiscontinuity con el enumerado que
     * corresponda.
     */
    public myDiscontinuity getDiscontinuityType() {
        myDiscontinuity aux = myDiscontinuity.NO_DISC;
        if (this.discontinuity_type != myDiscontinuity.NO_DISC) {
            aux = this.discontinuity_type;
        }
        return aux;
    }

    /**
     * Asignamos una discontinuidad al contorno de la forma.
     *
     * @param dt [in] Objeto de tipo myDiscontinuity que indica cual es.
     */
    public void setDiscontinuityType(myDiscontinuity dt) {
        this.discontinuity_type = dt;
    }

    /**
     * Este método se encarga de cambiar la posición de la forma, al punto que
     * se le pase como parámetro.
     *
     * @param p [in] Objeto de tipo geoPoint que indica la nueva posición de la
     * forma.
     */
    public abstract void setLocation(geoPoint p);

    /**
     * Este método nos permite ver en que punto se encuentra la forma.
     *
     * @return Devuelve el punto en donde esta la forma.
     */
    public abstract geoPoint getLocation();

    /**
     * Este método nos informa si forma contiene el punto que le pasamos por
     * parámetros, osea que podemos saber si el punto que le pasamos por
     * parámetro esta dentro de la forma.
     *
     * @param p [in] Objeto de tipo geoPoint.
     *
     * @return Devuelve verdadero si el punto esta dentro de la forma, falso en
     * caso contrario
     */
    public abstract boolean contains(geoPoint p);

    /**
     * Este método nos sirve para mostrar un rectángulo que rodea a la figura y
     * muestra cuales son los limites de la misma, también da la posibilidad de
     * modificar los parámetros de dicha figura
     *
     * @param s [in] muestra el limite de la figura con verdadero, falso en caso
     * contrario
     */
    public void showBounding(boolean s) {
        this.showBounding = s;
    }

    /**
     * Este método se encarga de dibujar la forma actual, en le objeto que se le
     * pasa por parámetros.
     *
     * @param g2d Objeto de tipo Graphics2D donde se pintará la forma.
     *
     * De momento se pasa un objeto Graphics2D pero se puede cambiar la interfaz
     * mas adelante para que acepte otros objetos.
     */
    public abstract void drawShapeIn(Graphics2D g2d);
}
