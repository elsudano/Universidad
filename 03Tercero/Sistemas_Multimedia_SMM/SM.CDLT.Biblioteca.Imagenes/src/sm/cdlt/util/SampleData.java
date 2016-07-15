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
public class SampleData {

    private float value;
    private final int band;
    private final int row;
    private final int col;

    /**
     * Constructor del sample.
     *
     * @param col [in] columna de donde se encuentra el sample.
     * @param row [in] fila de donde se encuentra el sample.
     * @param band [in] banda en la que se encuentra el sample.
     * @param value [in] valor del sample.
     */
    public SampleData(int col, int row, int band, float value) {
        this.value = value;
        this.band = band;
        this.row = row;
        this.col = col;
    }

    /**
     * Devuelve la columna que esta asociada al sample.
     *
     * @return entero con el valor de la columna al que pertenece el sample.
     */
    public int getCol() {
        return (int) col;
    }

    /**
     * Devuelve la fila que esta asociada al sample.
     *
     * @return entero con el valor de la fila al que pertenece el sample.
     */
    public int getRow() {
        return row;
    }

    /**
     * Devuelve el valor con la banda en donde se encuentra el sample.
     *
     * @return devuelve el numero de la banda en donde se encuentra el sample.
     */
    public int getBand() {
        return band;
    }

    /**
     * Devuelve el valor del sample.
     *
     * @return float con el valor del sample.
     */
    public float getValue() {
        return value;
    }

    /**
     * Asignamos el valor del sample.
     *
     * @param v [in] float valor que se asignará al sample.
     */
    public void setValue(float v) {
        this.value = v;
    }

}
