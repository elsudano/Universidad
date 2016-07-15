package sm.cdlt.graphics;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 06-abr-2016
 */
public abstract class myRectangleShape extends myExtraInfo {

    /**
     * Variable que se encarga de almacenar el tipo de degradado.
     * el valor 0 marca que sera un color solido.
     */
    protected int option_fill;
    /**
     * Variable que se encarga de almacenar la lista de colores que componen el
     * relleno de la forma
     */
    protected Color[] fill_colors;
    /**
     * Variable que almacena la distancia que hay entre los colores que componen
     * el degradado de la forma, el valor 0.0 indica que no hay separación.
     */
    protected float[] fill_spaces;
    
    /**
     * Constructor de un parámetro que se encarga de crear una forma
     * cuadrilátero.
     *
     * @param p [in] Objeto de tipo geoPoint que indica la posición del punto
     * superior izquierdo de donde comienza la forma.
     */
    public myRectangleShape(geoPoint p) {
        super(p);
        this.option_fill = 0;
        this.fill_colors = new Color[myExtraInfo.MAX_COLORS_FILL];
        this.fill_spaces = new float[myExtraInfo.MAX_COLORS_FILL];
    }

    /**
     * Constructor de 3 parámetros que se encarga de poner el punto de comienzo
     * de la forma junto con su color y el grosor de la linea que ocupa su
     * perímetro.
     *
     * @param p [in] Objeto de tipo geoPoint que indica la posición del punto
     * superior izquierdo de donde comienza la forma.
     * @param c [in] Objeto de tipo Color que indica el color de la linea del
     * perímetro.
     * @param s [in] Entero que indica el grosor de la linea del perímetro.
     */
    public myRectangleShape(geoPoint p, Color c, float s) {
        super(p, c, s);
        this.option_fill = 0;
        this.fill_colors = new Color[myExtraInfo.MAX_COLORS_FILL];
        this.fill_spaces = new float[myExtraInfo.MAX_COLORS_FILL];
    }

    /**
     * Devuelve el el objeto con el relleno que tendrá la forma, si es un
     * relleno de color solido devuelve un objeto de tipo Color, y si es un
     * relleno de tipo degradado devuelve un objeto de tipo
     * MultipleGradientPaint.
     *
     * Para saber de que tipo es se puede utilizar la sentencia "instanceof"
     *
     * @return Devuelve un objeto de tipo Color con el color.
     * @TODO hacer que funcione
     */
//    public Object getFill() {
//        return this.fill;
//    }

    /**
     * Con este método asignamos el color que tendrá el relleno de la forma.
     *
     * @param c [in] Objeto tipo Color con el color.
     */
    public void setFill(Color c) {
        this.option_fill = 1;
        this.fill_colors[0] = c;
        this.fill_spaces[0] = 0f;
    }

    /**
     * Con este método asignamos las propiedades que tendrá el relleno de la forma.
     * 
     * @param t [in] numero entero que indica el tipo de degradado 
     * @param f [in] colección de números que indica cual sera la porción que
     * ocupa cada color en el degradado
     * @param c [in] colección de colores que serán los que compongan el degradado.
     */
    public void setFill(int t, float[] f, Color[] c) {
        this.option_fill = t;
        this.fill_colors = c;
        this.fill_spaces = f;
    }

    /**
     * Este método nos devuelve el ancho que tiene nuestra forma.
     *
     * @return de tipo double para tener mas presición.
     */
    public double getWidth() {
        return ((Rectangle2D) this.myForma).getWidth();
    }

    /**
     * Con este método podemos asignar el ancho de nuestra forma rectangular.
     *
     * @param w [in] de tipo double para mas presición.
     */
    public void setWidth(double w) {
        ((Rectangle2D) this.myForma).setFrame(this.myForma.getBounds2D().getX(), this.myForma.getBounds2D().getY(), w, this.myForma.getBounds2D().getHeight());
    }

    /**
     * Este método nos devuelve el alto que tiene nuestra forma rectangular.
     *
     * @return de tipo double para tener mas presición.
     */
    public double getHeigth() {
        return ((Rectangle2D) this.myForma).getHeight();
    }

    /**
     * Con este método podemos asignar el alto de nuestra forma rectangular.
     *
     * @param h [in] de tipo double para mas presición.
     */
    public void setHeigth(double h) {
        ((Rectangle2D) this.myForma).setFrame(this.myForma.getBounds2D().getX(), this.myForma.getBounds2D().getY(), this.myForma.getBounds2D().getWidth(), h);
    }

    /**
     * Método que devuelve un RectangularShape nativo de JAVA para poder usarlo
     * directamente en el paint.
     *
     * @return Objeto RectangularShape que sirve de contenedor de la forma.
     */
    public RectangularShape getBounds() {
        return (RectangularShape) this.myForma;
    }
}
