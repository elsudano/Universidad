package api;

import java.awt.Color;

/**
 *
 * @author Carlos de al Torre
 */
public interface Context {
/*
    /**
     * Propiedad de color de la Linea.
     * /
    Color color = Color.black;
    /**
     * Propiedad del tamaño del grosor del contorno de la figura.
     * /
    int stroke = 4;
    /**
     * Propiedad de si la figura esta rellena o no.
     * /
    boolean fill = false;
    /**
     * Propiedad de si la figura tiene un estilo en concreto en alguna de sus
     * propiedades.
     * /
    Style style = new Style();
*/    
    /**
     * Devuelve el color que tiene la forma.
     *
     * @return el valor del color
     */
    public Color getColor();

    /**
     * Asigna el valor del color con el que se pinta la forma.
     *
     * @param color nuevo valor para el color.
     */
    public void setColor(Color color);

    /**
     * Devuelve el tamaño del grosor del contorno de la forma.
     *
     * @return el grosor del contorno.
     */
    public int getStroke();

    /**
     * Asigna el tamaño del grosor del contorno de la forma.
     *
     * @param stroke nuevo tamaño para el contorno de la forma.
     */
    public void setStroke(int stroke);

    /**
     * Devuelve si la forma esta rellena o no.
     *
     * @return verdadero si la forma esta rellena, falso en caso contrario.
     */
    public boolean isFill();

    /**
     * Asigna verdadero o falso para indicar si queremos la forma relleno o no.
     *
     * @param fill nuevo valor de rellono de la forma.
     */
    public void setFill(boolean fill);

    /**
     * Devuelve el estilo que tiene la forma.
     *
     * @return el estilo que tiene la forma.
     */
    public Style getStyle();

    /**
     * Asigna el valor del estilo que queremos dar a la forma.
     *
     * @param style nuevo valor para el estilo que queremos asignar a la forma.
     */
    public void setStyle(Style style);
}
