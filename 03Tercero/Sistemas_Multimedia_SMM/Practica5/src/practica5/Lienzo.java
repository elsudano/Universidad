package practica5;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos de al Torre
 */
public class Lienzo extends javax.swing.JPanel {

    Graphics2D g2d;
    private final List<Shape> Shapes = new ArrayList();
    private boolean fill = false;
    private Color myColor = Color.black;
    private float myStroke = 4;

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
        this.g2d = (Graphics2D) g;
        this.g2d.setColor(myColor);
        this.g2d.setStroke(new BasicStroke(myStroke));
        // recorremos la lista y pintamos los objetos
        for (Shape s : Shapes) {
            if (s != null) {
                if (this.fill && !(s instanceof Line2D.Float)) {
                    this.g2d.fill(s);
                } else {
                    this.g2d.draw(s);
                }
            }
        }
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
    public void setSize(float s) {
        this.myStroke = s;
    }

    /**
     * Con este metódo nos devuelve la forma que actualmente estamos manipulando.
     * @return la forma actual
     */
    public Shape getShape() {
        return this.Shapes.get(this.Shapes.size()-1);
    }

    /**
     * Con este metódo asignamos la herramienta que usaremos para pintar en el
     * lienzo.
     *
     * @param f[in] es una cadena que indica que tipo de herramienta usamos.
     * @return Devuelve verdadero si se añade la forma, falso en caso contrario.
     */
    public boolean setShape(Shape f) {
        boolean ret = false;
        if (this.Shapes.add(f)) {
            ret = true;
        }
        return ret;
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

    
    /**
     * Con este metódo seleccionamos la forma que pertenece al punto que le
     * pasamos por parámetros.
     *
     * @param p[in] Point que indica la posición en la que esta el ratón
     * @return devuelve la forma que contiene el punto p
     */
    public Shape getSelectedShape(Point2D p) {
        for (Shape s : Shapes) {
            if (s.contains(p)) {
                return s;
            }
        }
        return null;
    }
    
    /**
     * Este metódo limpia el lienzo y pone los valores por defecto del mismo.
     */
    public void clean(){
        this.Shapes.clear();
        this.myColor = Color.black;
        this.myStroke = 4;
        this.fill = false;
    }
}
