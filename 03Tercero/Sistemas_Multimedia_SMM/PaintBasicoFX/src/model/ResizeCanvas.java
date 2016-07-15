package model;

import java.awt.Point;
import javafx.scene.paint.Color;

/**
 * @Creado el 10-mar-2016
 * @Autor Carlos de la Torre
 * @Blog https://www.sudano.net
 */
public class ResizeCanvas extends javafx.scene.canvas.Canvas {

    /**
     * Esta será la posición donde queremos que comience la forma.
     */
    private final Point myPointBegin;

    /**
     * Esta será la posición donde queremos que termine la forma.
     */
    private final Point myPointEnd;

    /**
     * Este será el color que se use tanto para la linea como para el relleno de
     * la forma que pintemos.
     */
    private Color myColor;

    //private Shape myShape;
    /**
     * Esta es la forma que vamos a pintar, la pongo como una string por que
     * todavia no se muy bien trabajar con las formas de java modificar mas
     * adelante.
     */
    private String myShape;

    /**
     * Este es el tamaño que vamos a tener en las diferentes formas.
     */
    private int mySize;

    /**
     * Con esto indicamos si queremos que la forma este rellena o no.
     */
    private boolean fill;

    /**
     * Constructor de clase que se encarga de inicializar los valores que
     * corresponden para poder pintar formas.
     *
     * @note la figura en custión se deja vacia para que asigne con el metódo
     * que corresponda.
     */
    public ResizeCanvas() {
        super();
        this.myPointBegin = new Point();
        this.myPointEnd = new Point();
        this.mySize = 4;
        this.myColor = Color.BLACK;
        this.fill = false;
        // Hasta que no elegimos una forma no se pinta nada.
        this.myShape = "";
        // Con esto repintamos cuando el tamaño cambia
        widthProperty().addListener(evt -> repaint());
        heightProperty().addListener(evt -> repaint());
    }

    /**
     * Metódo de asignación del punto de inicio de la figura que deseamos
     * pintar.
     *
     * @param x[in] (double) posición x del punto.
     * @param y[in] (double) posición y del punto.
     */
    public void setPointBegin(double x, double y) {
        this.myPointBegin.setLocation(x, y);
    }

    /**
     * Metódo de asignación del punto de fin de la figura que deseamos pintar.
     *
     * @param x[in] (double) posición x del punto.
     * @param y[in] (double) posición y del punto.
     */
    public void setPointEnd(double x, double y) {
        this.myPointEnd.setLocation(x, y);
    }

    /**
     * Metódo de devolución del color que tendrá tanto el relleno de la figura
     * como la linea del contorno de la misma.
     *
     * @return devuelve el color que estamos usando.
     */
    public Color getColor() {
        return this.myColor;
    }

    /**
     * Metódo de asignación del color que tendrá tanto el relleno de la figura
     * como la linea del contorno de la misma.
     *
     * @param c[in] (Color) Este será el color que queremos pintar.
     */
    public void setColor(Color c) {
        this.myColor = c;
    }

    /**
     * Metódo de devolución de la forma que tendrá la figura que estamos
     * pintando.
     *
     * @return devuelve la forma que estamos pintando.
     */
    public String getShape() {
        return this.myShape;
    }

    /**
     * Metódo de asignación de la forma que queremos pintar.
     *
     * @param s[in] (String) Será una cadena que representa a la forma en
     * cuestión.
     */
    public void setShape(String s) {
        this.myShape = s;
    }

    /**
     * Metódo de devolución del tamaño de la forma o del grosor de la linea.
     *
     * @return devuelve el grosor de la linea de la forma.
     */
    public int getSize() {
        return this.mySize;
    }

    /**
     * Metódo de asignación del tamaño de la forma o del grosor de la linea.
     *
     * @param t[in] (int) Será el tamaño que se le de a la forma.
     */
    public void setSize(int t) {
        this.mySize = t;
    }

    /**
     * Metódo que devuelve verdadero si la forma esta rellena, falso en caso
     * contrario
     *
     * @return verdadero si esta rellena falso en caso contrario.
     */
    public boolean isFill() {
        return this.fill;
    }

    /**
     * Metódo de asignación para indicar si la forma debe de ir rellena o no.
     *
     * @param b[in] (boolean) Si queremos rellenar la forma, la pondremos a true
     * en caso contrario a false.
     */
    public void setFill(boolean b) {
        this.fill = b;
    }

    /**
     * Metódo que se encarga de dibujar la forma especificada con los valores
     * que ya tienen las otras variables.
     */
    private void draw() {
        switch (myShape) {
            case "point":
                this.getGraphicsContext2D().setLineWidth(mySize);
                this.getGraphicsContext2D().setStroke(myColor);
                this.getGraphicsContext2D().strokeOval(myPointBegin.x - (mySize / 2), myPointBegin.y - (mySize / 2), mySize, mySize);
                break;
            case "line":
                this.getGraphicsContext2D().setLineWidth(mySize);
                this.getGraphicsContext2D().setStroke(myColor);
                this.getGraphicsContext2D().strokeLine(myPointBegin.x, myPointBegin.y, myPointEnd.x, myPointEnd.y);
                break;
            case "rect":
                if (fill) {
                    this.getGraphicsContext2D().setFill(myColor);
                    this.getGraphicsContext2D().fillRect(myPointBegin.x, myPointBegin.y, myPointEnd.x - myPointBegin.x, myPointEnd.y - myPointBegin.y);
                } else {
                    this.getGraphicsContext2D().setLineWidth(mySize);
                    this.getGraphicsContext2D().setStroke(myColor);
                    this.getGraphicsContext2D().strokeRect(myPointBegin.x, myPointBegin.y, myPointEnd.x - myPointBegin.x, myPointEnd.y - myPointBegin.y);
                }
                break;
            case "oval":
                if (fill) {
                    this.getGraphicsContext2D().setFill(myColor);
                    this.getGraphicsContext2D().fillOval(myPointBegin.x, myPointBegin.y, myPointEnd.x - myPointBegin.x, myPointEnd.y - myPointBegin.y);
                } else {
                    this.getGraphicsContext2D().setLineWidth(mySize);
                    this.getGraphicsContext2D().setStroke(myColor);
                    this.getGraphicsContext2D().strokeOval(myPointBegin.x, myPointBegin.y, myPointEnd.x - myPointBegin.x, myPointEnd.y - myPointBegin.y);
                }
                break;
        }
    }

    /**
     * Metódo que se encarga de vaciar el entorno de dibujado.
     */
    public void clean() {
        this.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
    }

    /**
     * Metódo que se encarga de actualizar el entorno de dibujado.
     */
    public void repaint() {
        this.clean();
        /* Con esto probamos que el tamaño del lienzo cambia * /
        this.getGraphicsContext2D().setStroke(Color.RED);
        this.getGraphicsContext2D().strokeLine(0, 0, this.getWidth(), this.getHeight());
        this.getGraphicsContext2D().strokeLine(0, this.getHeight(), this.getWidth(), 0);
         */
        this.draw();
    }

    /**
     * Sobreescribimos los metódos para asignar la altura preferida.
     *
     * @param width
     * @return devuelve un double que será el tamaño
     */
    @Override
    public double prefHeight(double width) {
        return this.getHeight();
    }

    /**
     * Sobreescribimos los metódos para asignar la anchura preferida.
     *
     * @param height
     * @return devuelve un double que será el tamaño
     */
    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    /**
     * Sobreescribimos los metódos para saber si es rescalable.
     *
     * @return devuelve verdadero si es rescalable y falso en caso contrario.
     */
    @Override
    public boolean isResizable() {
        return true;
    }
}
