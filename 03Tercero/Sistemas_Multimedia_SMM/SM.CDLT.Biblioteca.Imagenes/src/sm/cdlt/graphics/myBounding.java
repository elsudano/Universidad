package sm.cdlt.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 11-jul-2016
 */
public class myBounding {

    private final double ancla_size = 7;
    /**
     * Rectángulo que indica los limites de la forma
     */
    private Rectangle2D.Double bounding;
    /**
     * Lista de puntos en las cuatro esquinas del rectángulo
     */
    private Ellipse2D.Double[] point_list;
    /**
     * Posición X de la esquina superior izquierda
     */
    private double x;
    /**
     * Posición Y de la esquina superior izquierda
     */
    private double y;

    /**
     * Constructor por defecto
     *
     * @param p [in] Punto de origen de la esquina superior izquierda de la
     * figura
     * @param w [in] Ancho de la figura que queremos enseñar el bounding
     * @param h [in] Alto de la figura que queremos enseñar el bounding
     */
    public myBounding(geoPoint p, double w, double h) {
        this.x = p.getPosX() - 5;
        this.y = p.getPosY() - 5;
        this.bounding = new Rectangle2D.Double(this.x, this.y, w + 10, h + 10);
        this.point_list = new Ellipse2D.Double[4];
        this.point_list[0] = new Ellipse2D.Double(this.x - this.ancla_size / 2, this.y - this.ancla_size / 2, this.ancla_size, this.ancla_size);
        this.point_list[1] = new Ellipse2D.Double(this.x + this.bounding.getWidth() - this.ancla_size / 2, this.y - this.ancla_size / 2, this.ancla_size, this.ancla_size);
        this.point_list[2] = new Ellipse2D.Double(this.x - this.ancla_size / 2, this.y + this.bounding.getHeight() - this.ancla_size / 2, this.ancla_size, this.ancla_size);
        this.point_list[3] = new Ellipse2D.Double(this.x + this.bounding.getWidth() - this.ancla_size / 2, this.y + this.bounding.getHeight() - this.ancla_size / 2, this.ancla_size, this.ancla_size);
    }

    /**
     * Actualizamos la posición donde se encuentra el bounding
     *
     * @param p [in] Punto de origen de la esquina superior izquierda de la
     * figura
     */
    public void update(geoPoint p) {
        this.bounding.setFrameFromDiagonal(this.x, this.y, p.getPosX() + 5, p.getPosY() + 5);
        this.point_list[0].setFrame(this.x - this.ancla_size / 2, this.y - this.ancla_size / 2, this.ancla_size, this.ancla_size);
        this.point_list[1].setFrame(this.x + this.bounding.getWidth() - this.ancla_size / 2, this.y - this.ancla_size / 2, this.ancla_size, this.ancla_size);
        this.point_list[2].setFrame(this.x - this.ancla_size / 2, this.y + this.bounding.getHeight() - this.ancla_size / 2, this.ancla_size, this.ancla_size);
        this.point_list[3].setFrame(this.x + this.bounding.getWidth() - this.ancla_size / 2, this.y + this.bounding.getHeight() - this.ancla_size / 2, this.ancla_size, this.ancla_size);
    }

    /**
     * Actualizamos la posición donde se encuentra el bounding
     *
     * @param r [in] rectángulo que contiene a la forma
     */
    public void update(Rectangle2D r) {
        this.bounding.setFrameFromDiagonal(this.x, this.y, r.getWidth() + 10, r.getHeight() + 10);
        this.point_list[0].setFrame(this.x - this.ancla_size / 2, this.y - this.ancla_size / 2, this.ancla_size, this.ancla_size);
        this.point_list[1].setFrame(this.x + this.bounding.getWidth() - this.ancla_size / 2, this.y - this.ancla_size / 2, this.ancla_size, this.ancla_size);
        this.point_list[2].setFrame(this.x - this.ancla_size / 2, this.y + this.bounding.getHeight() - this.ancla_size / 2, this.ancla_size, this.ancla_size);
        this.point_list[3].setFrame(this.x + this.bounding.getWidth() - this.ancla_size / 2, this.y + this.bounding.getHeight() - this.ancla_size / 2, this.ancla_size, this.ancla_size);
    }

    /**
     * Colocar en la nueva posición el recuadro que muestra los limites
     *
     * @param p [in] punto de la esquina superior izquierda de la figura.
     */
    public void setLocation(geoPoint p) {
        this.x = p.getPosX() - 5;
        this.y = p.getPosY() - 5;
        this.bounding.setFrameFromDiagonal(this.x, this.y, this.x + this.bounding.getWidth(), this.y + this.bounding.getHeight());
        this.point_list[0].setFrame(this.x - this.ancla_size / 2, this.y - this.ancla_size / 2, this.ancla_size, this.ancla_size);
        this.point_list[1].setFrame(this.x + this.bounding.getWidth() - this.ancla_size / 2, this.y - this.ancla_size / 2, this.ancla_size, this.ancla_size);
        this.point_list[2].setFrame(this.x - this.ancla_size / 2, this.y + this.bounding.getHeight() - this.ancla_size / 2, this.ancla_size, this.ancla_size);
        this.point_list[3].setFrame(this.x + this.bounding.getWidth() - this.ancla_size / 2, this.y + this.bounding.getHeight() - this.ancla_size / 2, this.ancla_size, this.ancla_size);
    }

        /**
     * Colocar en la nueva posición el recuadro que muestra los limites
     *
     * @param x [in] Coordenada x del punto de la esquina superior izquierda
     * @param y [in] Coordenada y del punto de la esquina superior izquierda
     */
    public void setLocation(double x, double y) {
        this.x = x - 5;
        this.y = y - 5;
        this.bounding.setFrameFromDiagonal(this.x, this.y, this.x + this.bounding.getWidth(), this.y + this.bounding.getHeight());
        this.point_list[0].setFrame(this.x - this.ancla_size / 2, this.y - this.ancla_size / 2, this.ancla_size, this.ancla_size);
        this.point_list[1].setFrame(this.x + this.bounding.getWidth() - this.ancla_size / 2, this.y - this.ancla_size / 2, this.ancla_size, this.ancla_size);
        this.point_list[2].setFrame(this.x - this.ancla_size / 2, this.y + this.bounding.getHeight() - this.ancla_size / 2, this.ancla_size, this.ancla_size);
        this.point_list[3].setFrame(this.x + this.bounding.getWidth() - this.ancla_size / 2, this.y + this.bounding.getHeight() - this.ancla_size / 2, this.ancla_size, this.ancla_size);
    }
    
    /**
     * Para que el bounding se auto dibuje en el Graphics2D
     *
     * @param g2d [in] donde queremos dibujar el bounding
     */
    public void drawShapeIn(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1.0f, new float[]{5.0f, 2.0f}, 0));
        g2d.draw(this.bounding);
        g2d.setStroke(new BasicStroke(10));
        for (Ellipse2D.Double point : point_list) {
            g2d.fill(point);
        }
    }
}
