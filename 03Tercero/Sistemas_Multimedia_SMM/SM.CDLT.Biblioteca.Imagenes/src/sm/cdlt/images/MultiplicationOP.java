package sm.cdlt.images;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 22-may-2016
 */
public class MultiplicationOP extends MultipleImageOP {

    /**
     * Método encargado de cumplir la operación con un factor de alpha asignado
     * por parámetros. El parámetro alpha esta en la clase BufferedImageOP.
     *
     * @param s1 [in] valor del primer sample.
     * @param s2 [in] valor del segundo sample.
     * @param a [in] valor de alpha para los samples.
     * @return tipo float valor del resultado de la multiplicación con un
     * porcentaje de alpha para los 2 samples.
     */
    @Override
    public float sampleOperation(float s1, float s2, float a) {
        float value = ((a * s2) * ((1.0f - a)*s1));
        return this.truncateValue(value, 0, 255);
    }

}
