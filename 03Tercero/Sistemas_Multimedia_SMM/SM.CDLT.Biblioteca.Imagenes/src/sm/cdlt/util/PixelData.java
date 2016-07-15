package sm.cdlt.util;

/**
 * Esta clase ha sido creada por el profesor:
 * <a href="mailto:jesus@decsai.ugr.es">Jesús Chamorro Martínez</a>
 * para la Asignatura (SMM) que imparte en la Universidad de Granada
 * <a href="http://etsiit.ugr.es/">ETSIIT</a>
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 10-may-2016
 */
public class PixelData {

    private final int row;
    private final int col;
    private final int numSamples;
    private float[] sample;

    /**
     * Constructor del pixel.
     * @param row [in] fila de donde esta el pixel.
     * @param col [in] columna de donde esta el pixel.
     * @param sample [in] datos de los valores del pixel. 
     */
    public PixelData(int row, int col, float[] sample) {
        super();
        this.sample = null;
        this.row = row;
        this.col = col;
        this.sample = sample;
        this.numSamples = ((sample != null) ? sample.length : 0);
    }

    /**
     * Devuelve la fila de la imagen en la que se encuentra el pixel.
     *
     * @return numero entero indicando la fila.
     */
    public int getRow() {
        return row;
    }

    /**
     * Devuelve la columna de la imagen en la que se encuentra el pixel.
     *
     * @return numero entero indicando la columna.
     */
    public int getCol() {
        return col;
    }

    /**
     * Devuelve la cantidad de bandas que contiene el pixel.
     *
     * @return numero entero indicando la cantidad de bandas del pixel.
     */
    public int getNumSamples() {
        return numSamples;
    }

    /**
     * Devuelve el sample completo del pixel.
     *
     * @return array con los datos del sample.
     */
    public float[] getSample() {
        return sample;
    }

    /**
     * Asigna el sample que se le pasa por parámetros a la imagen.
     *
     * @param s array con los valores del sample.
     */
    public void setSample(float[] s) {
        this.sample = s;
    }

}
