package sm.cdlt.graphics;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.QuadCurve2D;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 02-jun-2016
 */
public class myQuadCurve extends myCurveShape {

    /**
     * Constante que indica cual es el punto inicial de la curva.
     */
    private final int BEGIN_POINT = 0;
    /**
     * Constante que indica cual es el punto final de la curva.
     */
    private final int END_POINT = 1;
    /**
     * Constante que indica cual es el punto inicial de la curva.
     */
    private final int FIRST_CONTROL_POINT = 2;
    /**
     * Variable que almacena en que estado de dibujo se encuentra la forma
     */
    private int status = 0;

    /**
     * Constructor por defecto con solo un punto de anclaje.
     *
     * @param p [in] el comienzo y el final están en el mismo punto.
     */
    public myQuadCurve(geoPoint p) {
        super(p, p);
        this.list_of_points.add(p);
        this.list_of_points.add(p);
        this.status = this.END_POINT;
    }

    /**
     * Constructor por defecto con 2 puntos de anclaje.
     *
     * @param pb [in] Punto de comienzo.
     * @param pe [in] Punto de final.
     */
    public myQuadCurve(geoPoint pb, geoPoint pe) {
        super(pb, pe);
        this.list_of_points.add(pb);
        this.list_of_points.add(pe);
        this.status = this.FIRST_CONTROL_POINT;
    }

    /**
     * Constructor ampliado con valores de diferentes propiedades de la forma.
     *
     * @param pb [in] Punto de comienzo.
     * @param pe [in] Punto de final.
     * @param c [in] Color de la linea.
     * @param s [in] Ancho de la linea.
     */
    public myQuadCurve(geoPoint pb, geoPoint pe, Color c, float s) {
        super(pb, pe, c, s);
        this.list_of_points.add(pb);
        this.list_of_points.add(pe);
        this.status = this.FIRST_CONTROL_POINT;
    }

    /**
     * Método que sirve para actualizar los puntos de la curva, podemos ampliar
     * la curva o modificar los puntos de control, para que tenga otra forma,
     * Este método se tiene que encargar de mirar en el estado que se encuentra
     * la forma para modificar el punto que corresponda en cada momento.
     *
     * @param p [in] geoPoint que será el nuevo punto de la curva.
     */
    public void updateCurve(geoPoint p) {
        if (this.status == this.END_POINT) {
            this.list_of_points.set(this.END_POINT, p);
            this.bounding_rect.update(p);
        } else if (this.status == this.FIRST_CONTROL_POINT) {
            this.list_of_points.set(this.FIRST_CONTROL_POINT, p);
        } else {
            this.list_of_points.set(this.BEGIN_POINT, p);
        }
    }

    /**
     * Podemos consultar en el estado de dibujo que se encuentra la forma.
     *
     * @return devuelve un valor entero con estado en el que se encuentra.
     *
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Método que nos permite asignar el estado en el que queremos poner a
     * nuestra forma cuando la estamos dibujando.
     *
     * @param s [in] entero que nos indica el estado que queremos la forma.
     *
     * 0 == principio de la forma. 1 == final de la forma. 2 == primer punto de
     * control. 3 == segundo punto de control.
     */
    public void setStatus(int s) {
        this.status = s;
    }

    /**
     * Este método se encarga de cambiar la posición del primer punto de la
     * forma, al punto que se le pase como parámetro.
     *
     * @param p [in] Objeto de tipo geoPoint que indica la nueva posición.
     */
    @Override
    public void setLocation(geoPoint p) {
        double pfijox, pfijoy;
        pfijox = p.getPosX() - ((QuadCurve2D)this.myForma).getBounds().getX();
        pfijoy = p.getPosY() - ((QuadCurve2D)this.myForma).getBounds().getY();
        for (geoPoint point : this.list_of_points) {
            point.traslate(pfijox, pfijoy, 0);
        }
        this.bounding_rect.setLocation(p);
    }

    /**
     * Con este método podemos saber donde se encuentra el punto de inicio de la
     * linea.
     *
     * @return Devuelve un objeto de tipo myPoint con la posición del primer
     * punto de la linea.
     */
    @Override
    public geoPoint getLocation() {
        return new geoPoint(this.myForma.getBounds().getX(), this.myForma.getBounds().getY());
    }

    /**
     * Con este método podemos saber si el punto que pasamos por parámetros se
     * encuentra dentro de la forma.
     *
     * @param p [in] Este es el punto que queremos comprobar si esta en los
     * limites.
     * @return Devuelve verdadero si esta dentro de los limites, falso encaso
     * contrario.
     */
    @Override
    public boolean contains(geoPoint p) {
        return this.myForma.contains(p.getPosX(), p.getPosY());
    }

    /**
     * Con este método la forma se pinta ella sola en el objeto Graphics2D que
     * se pasa por parámetros.
     *
     * @param g2d [in] objeto en donde se pintará la forma.
     */
    @Override
    public void drawShapeIn(Graphics2D g2d) {
        double x1, y1, ctrlx1, ctrly1, x2, y2;
        x1 = this.list_of_points.get(this.BEGIN_POINT).getPosX();
        y1 = this.list_of_points.get(this.BEGIN_POINT).getPosY();
        ctrlx1 = this.list_of_points.get(this.FIRST_CONTROL_POINT).getPosX();
        ctrly1 = this.list_of_points.get(this.FIRST_CONTROL_POINT).getPosY();
        x2 = this.list_of_points.get(this.END_POINT).getPosX();
        y2 = this.list_of_points.get(this.END_POINT).getPosY();
        this.myForma = new QuadCurve2D.Double(x1, y1, ctrlx1, ctrly1, x2, y2);
        if (this.border_color != null) {
            g2d.setColor(this.border_color);
        }
        if (this.discontinuity_type != myDiscontinuity.NO_DISC) {
            float[] pattern;
            switch (this.discontinuity_type) {
                case DASH_BLANK:
                    pattern = new float[]{10.0f, 4.0f};
                    g2d.setStroke(new BasicStroke(this.line_width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, pattern, 0));
                    break;
                case POINT_BLANK:
                    pattern = new float[]{2.0f, 3.0f};
                    g2d.setStroke(new BasicStroke(this.line_width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, pattern, 0));
                    break;
                case DASH_POINT:
                    pattern = new float[]{10.0f, 4.0f, 2.0f, 4.0f};
                    g2d.setStroke(new BasicStroke(this.line_width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, pattern, 0));
                    break;
            }
        } else {
            g2d.setStroke(new BasicStroke(this.line_width));
        }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.alpha / 100f));
        if (this.render != null) {
            g2d.setRenderingHints(this.render);
        }
        g2d.draw(this.myForma);
        
        if (this.showBounding){
            this.bounding_rect.drawShapeIn(g2d);
        }
    }

}
