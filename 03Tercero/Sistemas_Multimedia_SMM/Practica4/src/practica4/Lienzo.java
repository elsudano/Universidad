package practica4;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Carlos de al Torre
 */
public class Lienzo extends javax.swing.JPanel {

    Graphics2D g2d;
    private String myShape = "point";
    private boolean fill = false;
    private Point myPointBegin;
    private Point myPointEnd;
    private Color myColor = Color.black;
    private int myStroke = 4;

    /**
     * Sobreescribimos el metódo de pintado para poder realizar las formas que
     * nosotros queremos en este panel en concreto que será el que consideramos
     * un lienzo donde pintar.
     *
     * @param g este es un objeto de gráficos que será el que contenga las
     * formas.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g2d = (Graphics2D) g;
        g2d.setColor(myColor);
        if (myShape.equals("point") && this.myPointBegin != null) {
            g2d.fillOval(myPointBegin.x - (myStroke / 2), myPointBegin.y - (myStroke / 2), myStroke, myStroke);
        } else if (myShape.equals("line") && this.myPointBegin != null && this.myPointEnd != null){
            g2d.setStroke(new BasicStroke(myStroke));
            g2d.drawLine(myPointBegin.x, myPointBegin.y, myPointEnd.x, myPointEnd.y);
        } else if (myShape.equals("rect") && this.myPointBegin != null && this.myPointEnd != null) {
            if (fill) {
                g2d.fillRect(myPointBegin.x, myPointBegin.y, myPointEnd.x - myPointBegin.x, myPointEnd.y - myPointBegin.y);
            } else {
                g2d.setStroke(new BasicStroke(myStroke));
                g2d.drawRect(myPointBegin.x, myPointBegin.y, myPointEnd.x - myPointBegin.x, myPointEnd.y - myPointBegin.y);
            }
        } else if (myShape.equals("oval") && this.myPointBegin != null && this.myPointEnd != null) {
            if (fill) {
                g2d.fillOval(myPointBegin.x, myPointBegin.y, myPointEnd.x - myPointBegin.x, myPointEnd.y - myPointBegin.y);
            } else {
                g2d.setStroke(new BasicStroke(myStroke));
                g2d.drawOval(myPointBegin.x, myPointBegin.y, myPointEnd.x - myPointBegin.x, myPointEnd.y - myPointBegin.y);
            }
        }
    }

    /**
     * Con este metodo creamos un punto dentro del lienzo con las
     * especificaciones que se pasan por lo parametros.
     *
     * @param p[in] Punto posición x e y donde estara el punto
     */
    public void setBegin(Point p) {
        this.myPointBegin = p;
    }

    /**
     * Con este metódo asignamos cual será el punto de finalización para crear
     * la forma.
     *
     * @param p[in] Punto posición x e y donde estara el final de la forma.
     */
    public void setEnd(Point p) {
        this.myPointEnd = p;
    }

    /**
     * Con este metódo asignamos el color que queremos para la forma.
     *
     * @param c[in] el comor especificado para la linea y el relleno de la
     * forma.
     */
    public void setColor(Color c) {
        this.myColor = c;
    }

    /**
     * Con este metódo asignamos el tamaño que tendrá la forma ya sea el ancho
     * de la linea o el tamaño del punto.
     *
     * @param s[in] numero entero que indica el tamaño que tendra la forma.
     */
    public void setSize(int s) {
        this.myStroke = s;
    }

    /**
     * Con este metódo asignamos la herramienta que usaremos para pintar en el
     * lienzo.
     *
     * @param t[in] es una cadena que indica que tipo de herramienta usamos.
     */
    public void setTool(String t) {
        this.myShape = t;
    }

    /**
     * Con este metódo asignamos un color de relleno a la forma que vamos a
     * dibujar de tal forma que se rellena si es verdadero y falso en caso
     * contrario.
     *
     * @param b[in] true si se rellena la forma, false en caso contrario
     */
    public void setFill(boolean b) {
        this.fill = b;
    }
}
