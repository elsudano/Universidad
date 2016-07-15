package sm.cdlt.graphics;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 06-abr-2016
 */
public class myLine extends myExtraInfo {

    /**
     * Punto auxiliar para trabajar con el en los métodos.
     */
    private final Point2D point_java_aux = new Point2D.Double();
    /**
     * Constante que indica cual es el punto inicial de la linea.
     */
    public static final int BEGIN_POINT = 0;
    /**
     * Constante que indica cual es el punto final de la linea.
     */
    public static final int END_POINT = 1;
    /**
     * Este sería el tipo de final que tendrá la linea.
     */
    private int line_ends;

    /**
     * Constructor sin parámetros en este caso, se crea la linea para poder
     * mandarle después los mensajes pertinentes de donde están sus puntos.
     */
    public myLine() {
        super();
        this.line_ends = 0;
        this.discontinuity_type = myDiscontinuity.NO_DISC;
        this.myForma = new Line2D.Double();
    }

    /**
     * Constructor con 2 parámetros para indicar el principio y el final de la
     * linea, los demás parámetros son lo que hay por defecto.
     *
     * @param pb [in] Punto en donde comienza la linea.
     * @param pe [in] Punto en donde termina la linea.
     */
    public myLine(geoPoint pb, geoPoint pe) {
        super(pb);// mete el punto en la posición 0 siempre el primero
        this.list_of_points.add(pe);
        this.line_ends = 0;
        this.discontinuity_type = myDiscontinuity.NO_DISC;
        this.myForma = new Line2D.Double(pb.getPosX(), pb.getPosY(), pe.getPosX(), pe.getPosY());
    }

    /**
     * Constructor con 4 parametros para indicar el principio y el final de la
     * linea,aparte tambien podemos especificar cual es su color y el grosor de
     * la misma.
     *
     * @param pb [in] Punto de inicio de la linea.
     * @param pe [in] Punto de fin de la linea.
     * @param lw [in] Grosor de la linea.
     * @param c [in] Color de la linea.
     */
    public myLine(geoPoint pb, geoPoint pe, int lw, Color c) {
        super(pb); // el alpha tambien se inicializa en el super
        this.list_of_points.add(pe);
        this.line_width = lw;
        this.border_color = c;
        this.line_ends = 0;
        this.myForma = new Line2D.Double(pb.getPosX(), pb.getPosY(), pe.getPosX(), pe.getPosY());
    }

    /**
     * Devuelve el tipo de finalización que tiene la linea.
     *
     * @return Devuelve un entero con el tipo de finalización que tiene la
     * linea.
     */
    public int getLineEnds() {
        return line_ends;
    }

    /**
     * Con este método podemos indicar cual es el tipo de final que tendrá la
     * linea en sus extremos.
     *
     * @param le [in] Es un entero que indica cual es la forma del final.
     */
    public void setLineEnds(int le) {
        this.line_ends = le;
    }

    /**
     * Este método nos sirve para actualizar los puntos de inicio y de fin.
     *
     * @param n [in] De tipo entero, elegimos el punto que queremos modificar, 0
     * para el punto inicial y 1 para el final.
     *
     * @param p [in] De tipo myPoint, será el nuevo punto que asignaremos en la
     * posición n.
     */
    public void updateLine(int n, geoPoint p) {
        if (n == 0 || n == 1) {
            this.list_of_points.set(n, p);
            this.bounding_rect.update(p);
        } else {
            // @TODO lanzar exception
            throw new UnsupportedOperationException("Number of point out of range. Method updateLine of myLine");
        }
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
        return new geoPoint(((Line2D)this.myForma).getBounds().getX(), ((Line2D)this.myForma).getBounds().getY());
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
        pfijox = p.getPosX() - ((Line2D)this.myForma).getBounds().getX();
        pfijoy = p.getPosY() - ((Line2D)this.myForma).getBounds().getY();
        this.list_of_points.get(myLine.BEGIN_POINT).traslate(pfijox, pfijoy, 0);
        this.list_of_points.get(myLine.END_POINT).traslate(pfijox, pfijoy, 0);
        this.bounding_rect.setLocation(p);
    }

    /**
     * Con este método podemos saber si el punto que pasamos por parámetros se
     * encuentra dentro de la linea, vamos podemos saber si el punto esta
     * tocando la linea.
     *
     * @param p [in] Este es el punto que queremos comprobar si esta en los
     * limites.
     * @return Devuelve verdadero si esta dentro de los limites, falso encaso
     * contrario.
     */
    @Override
    public boolean contains(geoPoint p) {
// este es mi metodo para encontrar la linea si puedes perfeccionalo
// tendrias que calcular la región que pasa la linea partiendo de los 2 puntos
//        boolean aux = false;
//        if (((this.list_of_points.get(0).getPosX() - this.accuracy_touch) < p.getPosX() && p.getPosX() < (this.list_of_points.get(0).getPosX() + this.accuracy_touch))
//                && ((this.list_of_points.get(0).getPosY() - this.accuracy_touch) < p.getPosY() && p.getPosY() < (this.list_of_points.get(0).getPosY() + this.accuracy_touch))
//                || ((this.list_of_points.get(1).getPosX() - this.accuracy_touch) < p.getPosX() && p.getPosX() < (this.list_of_points.get(1).getPosX() + this.accuracy_touch))
//                && ((this.list_of_points.get(1).getPosY() - this.accuracy_touch) < p.getPosY() && p.getPosY() < (this.list_of_points.get(1).getPosY() + this.accuracy_touch))) {
//            aux = true;
//        }
//        return aux;
        this.point_java_aux.setLocation(p.getPosX(), p.getPosY());
        return ((Line2D) this.myForma).ptLineDist(point_java_aux) <= this.line_width;
    }

    /**
     * Con este método la forma se pinta ella sola en el objeto Graphics2D que
     * se pasa por parámetros.
     *
     * @param g2d [in] objeto en donde se pintará la linea.
     */
    @Override
    public void drawShapeIn(Graphics2D g2d) {
        geoPoint p1, p2;
        p1 = this.list_of_points.get(myLine.BEGIN_POINT);
        p2 = this.list_of_points.get(myLine.END_POINT);
        ((Line2D) this.myForma).setLine(p1.getPosX(), p1.getPosY(), p2.getPosX(), p2.getPosY());
        g2d.setColor(this.border_color);
        if (this.discontinuity_type != myDiscontinuity.NO_DISC) {
            float[] pattern;
            switch (this.discontinuity_type) {
                case DASH_BLANK:
                    pattern = new float[]{10.0f, 4.0f};
                    g2d.setStroke(new BasicStroke(this.line_width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, pattern, 0));
                    break;
                case POINT_BLANK:
                    pattern = new float[]{2.0f, 3.0f};
                    g2d.setStroke(new BasicStroke(this.line_width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, pattern, 0));
                    break;
                case DASH_POINT:
                    pattern = new float[]{10.0f, 4.0f, 2.0f, 4.0f};
                    g2d.setStroke(new BasicStroke(this.line_width, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, pattern, 0));
                    break;
            }
        } else {
            g2d.setStroke(new BasicStroke(this.line_width));
        }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.alpha / 100f));
        g2d.setRenderingHints(this.render);
        g2d.draw(this.myForma);
        
        if (this.showBounding){
            this.bounding_rect.drawShapeIn(g2d);
        }
    }
}
