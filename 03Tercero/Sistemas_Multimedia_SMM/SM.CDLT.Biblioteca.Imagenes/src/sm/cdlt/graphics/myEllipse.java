package sm.cdlt.graphics;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.geom.Ellipse2D;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 15-abr-2016
 */
public class myEllipse extends myRectangleShape {

    /**
     * Este es el punto de origen de la forma.
     */
    private static final int BEGIN_POINT = 0;
    /**
     * Este es el punto final de la forma.
     */
    private static final int END_POINT = 1;

    /**
     * Constructor de un parámetro que se encarga de crear una forma elíptica.
     *
     * @param p1 [in] Objeto de tipo geoPoint que indica la posición del punto
     * superior izquierdo de donde comienza la forma.
     * @param p2 [in] Objeto de tipo geoPoint que indica la posición del punto
     * inferior derecho de donde termina la forma.
     */
    public myEllipse(geoPoint p1, geoPoint p2) {
        super(p1);
        this.list_of_points.add(p2);
        this.myForma = new Ellipse2D.Double(p1.getPosX(), p1.getPosY(), Math.abs(p1.getPosX() - p2.getPosX()), Math.abs(p1.getPosY() - p2.getPosY()));
    }

    public myEllipse(geoPoint p, double w, double h) {
        super(p);
        this.list_of_points.add(new geoPoint(p.getPosX() + w, p.getPosY() + h));
        this.myForma = new Ellipse2D.Double(p.getPosX(), p.getPosY(), w, h);
    }

    public myEllipse(geoPoint p, double w, double h, Color c, int s) {
        super(p, c, s);
        this.list_of_points.add(new geoPoint(p.getPosX() + w, p.getPosY() + h));
        this.myForma = new Ellipse2D.Double(p.getPosX(), p.getPosY(), w, h);
    }

    /**
     * Método que sirve para actualizar los puntos de la forma
     *
     * @param p [in] geoPoint que será el nuevo punto de la forma.
     */
    public void updateRectangle(geoPoint p) {
        this.list_of_points.set(END_POINT, p);
        ((Ellipse2D) this.myForma).setFrameFromDiagonal(
                this.list_of_points.get(myEllipse.BEGIN_POINT).getPosX(),
                this.list_of_points.get(myEllipse.BEGIN_POINT).getPosY(),
                p.getPosX(), p.getPosY());
        this.bounding_rect.update(p);
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
        pfijox = p.getPosX() - ((Ellipse2D) this.myForma).getBounds().getX();
        pfijoy = p.getPosY() - ((Ellipse2D) this.myForma).getBounds().getY();
        for (geoPoint point : this.list_of_points) {
            point.traslate(pfijox, pfijoy, 0);
        }
        ((Ellipse2D) this.myForma).setFrameFromDiagonal(
                p.getPosX(),
                p.getPosY(),
                p.getPosX() + this.myForma.getBounds2D().getWidth(),
                p.getPosY() + this.myForma.getBounds2D().getHeight());
        this.bounding_rect.setLocation(p);
    }

    /**
     * Con este método podemos saber donde se encuentra el punto de inicio de la
     * linea.
     *
     * @return Devuelve un objeto de tipo myPoint con la posición del primer
     * punto de la forma.
     */
    @Override
    public geoPoint getLocation() {
        return new geoPoint(this.myForma.getBounds2D().getMinX(), this.myForma.getBounds2D().getMinY());
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
        if (this.border_color != null) {
            g2d.setColor(this.border_color);
        }
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
        if (this.render != null) {
            g2d.setRenderingHints(this.render);
        }
        if (this.option_fill > 0) {
            switch (this.option_fill) {
                case 1: // solo color solido
                    g2d.setPaint(this.fill_colors[0]);
                    g2d.fill(this.myForma);
                    break;
                case 2: // LinearGradient
                    g2d.setPaint(new LinearGradientPaint(
                            (float) this.list_of_points.get(BEGIN_POINT).getPosX(),
                            (float) this.list_of_points.get(BEGIN_POINT).getPosY(),
                            // no pueden ser el mismo punto cuando se crea.
                            (float) this.list_of_points.get(END_POINT).getPosX() + 0.1f,
                            (float) this.list_of_points.get(END_POINT).getPosY() + 0.1f,
                            this.fill_spaces, this.fill_colors));
                    g2d.fill(this.myForma);
                    break;
                case 3: // RadialGradient
                    g2d.setPaint(new RadialGradientPaint(
                            (float) ((Ellipse2D) this.myForma).getCenterX(),
                            (float) ((Ellipse2D) this.myForma).getCenterY(),
                            (float) Math.abs(this.list_of_points.get(BEGIN_POINT).getPosX() - this.list_of_points.get(END_POINT).getPosX()),
                            this.fill_spaces, this.fill_colors));
                    g2d.fill(this.myForma);
                    break;
            }
        }
        if (this.border_color != null) {
            g2d.setPaint(this.border_color);
            g2d.draw(this.myForma);
        }
        if (this.showBounding){
            this.bounding_rect.drawShapeIn(g2d);
        }
    }
}
