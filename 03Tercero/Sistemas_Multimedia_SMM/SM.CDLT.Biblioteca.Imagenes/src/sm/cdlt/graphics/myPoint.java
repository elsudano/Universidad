package sm.cdlt.graphics;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de al Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de al Torre</a>
 * created on 14-abr-2016
 */
public class myPoint extends myShape {

    /**
     * Este será el tamaño del punto
     */
    private static final int POINT_WIDTH = 10;
    /**
     * Este será el color que tendrá la linea.
     */
    private Color color;
    /**
     * Este es el valor de la transparencia.
     */
    private int alpha;

    /**
     * Constructor sin parámetros, crea un punto con los valores por defecto
     * para después pasarle mensajes de la posición en donde debe pintarse.
     *
     * Posición X=0,Y=0. Color por defecto negro. Tamaño por defecto 1 px.
     */
    public myPoint() {
        super();
        this.color = Color.BLACK;
        this.alpha = 100;
    }

    /**
     * Constructor con parámetros, crea un punto de 2 dimensiones.
     *
     * @param c [in] Color del que será el punto.
     * @param p [in] Posición en donde se colocara el punto en el Lienzo.
     */
    public myPoint(Color c, geoPoint p) {
        super(p);
        this.color = c;
        this.alpha = 100;
    }

    /**
     * Con este método podemos ver cual es el color del punto.
     *
     * @return Objeto de tipo color que muestra el color del punto.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Con este método podemos asignar el color del punto.
     *
     * @param c [in] Objeto de tipo color que indica cual es.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * Con este método podemos ver cual es la transparencia que tiene el punto.
     *
     * @return Objeto de tipo float que muestra un valor entre:
     * 0.0 (transparente) y 1.0 (opaco)
     */
    public float getAlpha() {
        return alpha;
    }

    /**
     * Con este método podemos asignar cual será la transparencia del punto
     *
     * @param a [in] Numero float (0.0 - 1.0) que indica la transparencia.
     */
    public void setAlpha(int a) {
        if (a >= 0 && a <= 100) {
            this.alpha = a;
        }
    }

    /**
     * Este método nos da la posición en el eje de coordenadas del punto.
     * @return Devuelve un objeto geoPoint con la posición.
     */
    @Override
    public geoPoint getLocation() {
        return this.list_of_points.get(0);
    }

    /**
     * Asigna la posición en el eje de coordenadas del punto.
     * @param p [in] Objeto de tipo geoPoint con la posición.
     */
    @Override
    public void setLocation(geoPoint p) {
        this.list_of_points.add(0, p);
        this.bounding_rect.setLocation(p);
    }

    /**
     * Asigna la posición en el eje de coordenadas del punto.
     * @param x [in] posición en el eje x
     * @param y [in] posición en el eje y
     */
    public void setLocation(double x, double y) {
        this.list_of_points.add(0, new geoPoint(x, y));
    }
    
    /**
     * Comprueba si el punto que se pasa por parámetros esta dentro del punto.
     * 
     * @param p [in] Punto que sirve de referencia para saber si esta tocándose.
     * 
     * @return Devuelve el verdadero si están tocándose, falso en caso contrario.
     */
    @Override
    public boolean contains(geoPoint p) {
        boolean aux = false;
        if (((this.list_of_points.get(0).getPosX() - myPoint.POINT_WIDTH / 2) < p.getPosX() && p.getPosX() < (this.list_of_points.get(0).getPosX() + myPoint.POINT_WIDTH / 2))
                && ((this.list_of_points.get(0).getPosY() - myPoint.POINT_WIDTH / 2) < p.getPosY() && p.getPosY() < (this.list_of_points.get(0).getPosY() + myPoint.POINT_WIDTH / 2))) {
            aux = true;
        }
        return aux;
    }

    /**
     * Este método permite al objeto punto pintarse dentro del objeto Graphics
     * que se le pasa por parámetros.
     * 
     * @param g2d [in] El objeto donde se pintará el objeto myPoint
     */
    @Override
    public void drawShapeIn(Graphics2D g2d) {
        Ellipse2D paint_point = new Ellipse2D.Float((int) this.list_of_points.get(0).getPosX() - myPoint.POINT_WIDTH / 2, (int) this.list_of_points.get(0).getPosY() - myPoint.POINT_WIDTH / 2, myPoint.POINT_WIDTH, myPoint.POINT_WIDTH);
        g2d.setColor(this.color);
        g2d.setStroke(new BasicStroke(0));
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.alpha/100f));
        g2d.setRenderingHints(this.render);
        g2d.fill(paint_point);
        if (this.showBounding){
            this.bounding_rect.drawShapeIn(g2d);
        }
    }
}
