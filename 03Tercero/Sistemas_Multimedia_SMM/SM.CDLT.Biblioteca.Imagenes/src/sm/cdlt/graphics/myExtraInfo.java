package sm.cdlt.graphics;

import java.awt.Color;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 15-abr-2016 @since 0.1
 */
public abstract class myExtraInfo extends myShape {

    /**
     * Variable estática que indica la cantidad de colores que se pueden
     * utilizar para generar el degradado del relleno de las formas.
     */
    public static int MAX_COLORS_FILL = 4;
    /**
     * Este será el grosor de la linea.
     */
    protected float line_width;
    /**
     * Este será el color que tendrá la linea del contorno de la forma.
     */
    protected Color border_color;
    /**
     * Este es el valor de la transparencia.
     */
    protected int alpha;

    /**
     * Constructor sin parámetros, crear un punto en la posición: X=0,Y=0,Z=0 de
     * color negro y con 1px de ancho
     */
    public myExtraInfo() {
        super();
        this.line_width = 1;
        this.border_color = Color.BLACK;
        this.alpha = 100;
    }

    /**
     * Creamos el objeto pasándole solo el punto de inicio de la forma.
     * 
     * @param p [in] geoPoint de inicio de la forma. 
     */
    public myExtraInfo(geoPoint p) {
        super(p);
        this.line_width = 1;
        this.border_color = Color.BLACK;
        this.alpha = 100;
    }

    /**
     * Creamos el objeto pasándole todos los parámetros.
     * 
     * @param p [in] geoPoint de inicio de la forma.
     * @param c [in] Color que tendrá la forma.
     * @param s [in] Ancho de la linea que tendrá la forma.
     */
    public myExtraInfo(geoPoint p, Color c, float s) {
        super(p);
        this.border_color = c;
        this.line_width = s;
        this.alpha = 100;
    }

    /**
     * Devuelve el tamaño del grosor de la linea, que tendrá el gráfico.
     *
     * @return Devuelve un entero con el tamaño del grosor de la linea.
     */
    public float getLineWidth(){
        return this.line_width;
    }

    /**
     * Con este método podemos asignar el grosor que tendrá el gráfico.
     *
     * @param lw [in] Es un entero que indica el numero de px de grosor.
     */
    public void setLineWidth(float lw){
        this.line_width = lw;
    }

    /**
     * Devuelve el color del perímetro de la forma.
     *
     * @return Devuelve un objeto de tipo Color con el color.
     */
    public Color getBorderColor(){
        return this.border_color;
    }

    /**
     * Con este método asignamos el color que tendrá el perímetro de la forma.
     *
     * @param c [in] Objeto tipo Color con el color.
     */
    public void setBorderColor(Color c){
        if (c != null) {
            this.border_color = c;
        }
    }
    
    /**
     * Con este método podemos ver el valor que tiene la transparencia del
     * gráfico.
     *
     * @return Devuelve un valor Float con el valor de la transparencia.
     */
    public int getAlpha(){
        return this.alpha;
    }

    /**
     * Con este método podemos poner la transparencia del gráfico.
     *
     * @param a [in] es un valor float que indica el nivel de transparencia.
     */
    public void setAlpha(int a){
        if (a >= 0 && a <= 100) {
            this.alpha = a;
        }
    }
}
