package sm.cdlt.graphics;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Polygon;
import java.awt.RadialGradientPaint;
import java.awt.geom.Rectangle2D;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 31-may-2016
 */
public class myPolygon extends myExtraInfo {

    /**
     * Variable que se encarga de almacenar el tipo de degradado. el valor 0
     * marca que sera un color solido.
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
     * Variable que almacena en que estado de dibujo se encuentra la forma
     */
    private boolean status;
    /**
     * lista de valores de x de todos los puntos de la forma
     */
    private int[] xpoints;
    /**
     * lista de valores de y de todos los puntos de la forma
     */
    private int[] ypoints;
    /**
     * Coordenada x de la esquina superior izquierda derecha del limite de la forma
     */
    private double min_x;
    /**
     * Coordenada y de la esquinas superior izquierda del limite de la forma
     */
    private double min_y;
    /**
     * Coordenada x de la esquina inferior derecha del limite de la forma
     */
    private double max_x;
    /**
     * Coordenada y de la esquina inferior derecha del limite de la forma
     */
    private double max_y;

    /**
     * Constructor por defecto con un solo punto de inicio.
     *
     * @param p [in] punto en donde comienza a dibujarse el polígono
     */
    public myPolygon(geoPoint p) {
        super(p);
        this.max_x = this.max_y = 0;
        this.status = false;
        this.option_fill = 0;
        this.fill_colors = new Color[myExtraInfo.MAX_COLORS_FILL];
        this.fill_spaces = new float[myExtraInfo.MAX_COLORS_FILL];
        this.xpoints = new int[this.list_of_points.size()];
        this.ypoints = new int[this.list_of_points.size()];
    }

    /**
     * Constructor ampliado con el que podemos dar valores básicos al polígono.
     *
     * @param p [in] Punto de inicio.
     * @param c [in] Color de la linea.
     * @param s [in] Ancho de la linea.
     */
    public myPolygon(geoPoint p, Color c, float s) {
        super(p, c, s);
        this.max_x = this.max_y = 0;
        this.status = false;
        this.option_fill = 0;
        this.fill_colors = new Color[myExtraInfo.MAX_COLORS_FILL];
        this.fill_spaces = new float[myExtraInfo.MAX_COLORS_FILL];
        this.xpoints = new int[this.list_of_points.size()];
        this.ypoints = new int[this.list_of_points.size()];
    }

    /**
     * Con este método añadimos un nuevo vértice al polígono.
     *
     * @param p [in] Punto que se añade.
     */
    public void setPoint(geoPoint p) {
        int count = 0;
        this.list_of_points.add(p);
        /*
        tengo que poner la construcción de los array aquí, por que cada punto
        que se añade tengo que volver a reservar memoria para los arrays
         */
        this.xpoints = new int[this.list_of_points.size()];
        this.ypoints = new int[this.list_of_points.size()];
        for (geoPoint point : this.list_of_points) {
            this.xpoints[count] = (int) point.getPosX();
            this.ypoints[count] = (int) point.getPosY();
            if (point.getPosX() < this.min_x) {
                this.min_x = point.getPosX();
            }
            if (point.getPosY() < this.min_y) {
                this.min_y = point.getPosY();
            }
            if (point.getPosX() > this.max_x) {
                this.max_x = point.getPosX();
            }
            if (point.getPosY() > this.max_y) {
                this.max_y = point.getPosY();
            }
            count++;
        }
        this.bounding_rect.update(new geoPoint(this.max_x, this.max_y));
    }

    /**
     * Con este método añadimos un nuevo vértice al polígono.
     *
     * @param x [in] esta es la posición en el eje x del punto.
     * @param y [in] esta es la posición en el eje y del punto.
     */
    public void setPoint(double x, double y) {
        int count = 0;
        this.list_of_points.add(new geoPoint(x, y));
        /*
        tengo que poner la construcción de los array aquí, por que cada punto
        que se añade tengo que volver a reservar memoria para los arrays
         */
        this.xpoints = new int[this.list_of_points.size()];
        this.ypoints = new int[this.list_of_points.size()];
        for (geoPoint point : this.list_of_points) {
            this.xpoints[count] = (int) point.getPosX();
            this.ypoints[count] = (int) point.getPosY();
            if (point.getPosX() < this.min_x) {
                this.min_x = point.getPosX();
            }
            if (point.getPosY() < this.min_y) {
                this.min_y = point.getPosY();
            }
            if (point.getPosX() > this.max_x) {
                this.max_x = point.getPosX();
            }
            if (point.getPosY() > this.max_y) {
                this.max_y = point.getPosY();
            }
            count++;
        }
        this.bounding_rect.update(new geoPoint(this.max_x, this.max_y));
    }

    /**
     * Método que nos permite decidir si la figura ha terminado de pintarse o
     * no.
     *
     * @param s [in] estado en el que se encuentra la forma.
     */
    public void endPaint(boolean s) {
        this.status = s;
    }

    /**
     * Método que nos permite saber si es el ultimo punto a pintar.
     *
     * @return verdadero si es el ultimo punto, falso en caso contrario.
     */
    public boolean isLastPoint() {
        return this.status;
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
     * @TODO poner en funcionamiento.
     */
//    public Object getFill() {
//        Object aux = null;
//        if (this.fill_color != null) {
//            aux = this.fill_color;
//        }
//        if (this.fill_gradient != null) {
//            aux = this.fill_gradient;
//        }
//        return aux;
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
     * Con este método asignamos las propiedades que tendrá el relleno de la
     * forma.
     *
     * @param t [in] numero entero que indica el tipo de degradado
     * @param f [in] colección de números que indica cual sera la porción que
     * ocupa cada color en el degradado
     * @param c [in] colección de colores que serán los que compongan el
     * degradado.
     */
    public void setFill(int t, float[] f, Color[] c) {
        this.option_fill = t;
        this.fill_colors = c;
        this.fill_spaces = f;
    }

    /**
     * Método que se usa para actualizar los diferentes puntos del polígono.
     *
     * @param p [in] este será el punto que se actualizara.
     */
    public void updatePolygon(geoPoint p) {
        //@TODO To change body of generated methods, choose Tools | Templates.
        throw new UnsupportedOperationException("Not implemented yet. Method updatePolygon to myPolygon");
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
        int count = 0;
        pfijox = p.getPosX() - ((Polygon) this.myForma).getBounds().getX();
        pfijoy = p.getPosY() - ((Polygon) this.myForma).getBounds().getY();
        for (geoPoint point : this.list_of_points) {
            point.traslate(pfijox, pfijoy, 0);
            this.xpoints[count] = (int) point.getPosX();
            this.ypoints[count] = (int) point.getPosY();
            count++;
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
        return new geoPoint(((Polygon) this.myForma).getBounds().getX(), ((Polygon) this.myForma).getBounds().getY());
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
        this.myForma = new Polygon(this.xpoints, this.ypoints, this.list_of_points.size());
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
        if (this.option_fill > 0) {
            switch (this.option_fill) {
                case 1: // solo color solido
                    g2d.setPaint(this.fill_colors[0]);
                    g2d.fill(this.myForma);
                    break;
                case 2: // LinearGradient
                    g2d.setPaint(new LinearGradientPaint(
                            (float) this.list_of_points.get(0).getPosX(),
                            (float) this.list_of_points.get(0).getPosY(),
                            // no pueden ser el mismo punto
                            (float) this.list_of_points.get(this.list_of_points.size() - 1).getPosX() + 0.1f,
                            (float) this.list_of_points.get(this.list_of_points.size() - 1).getPosY() + 0.1f,
                            this.fill_spaces, this.fill_colors));
                    g2d.fill(this.myForma);
                    break;
                case 3: // RadialGradient
                    g2d.setPaint(new RadialGradientPaint(
                            (float) ((Rectangle2D) this.myForma).getCenterX(),
                            (float) ((Rectangle2D) this.myForma).getCenterY(),
                            (float) Math.abs(this.list_of_points.get(0).getPosX() - this.list_of_points.get(this.list_of_points.size() - 1).getPosX()),
                            this.fill_spaces, this.fill_colors));
                    g2d.fill(this.myForma);
                    break;
            }
        }
        if (this.border_color != null) {
            g2d.setPaint(this.border_color);
            g2d.draw(this.myForma);
        }
        if (this.showBounding) {
            this.bounding_rect.drawShapeIn(g2d);
        }
    }
}
