package sm.cdlt.images;

import java.awt.image.ByteLookupTable;
import java.awt.image.LookupTable;

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
 * created on 25-may-2016
 */
public class LookupTableProducer {

    public static final int TYPE_NEGATIVE = 0;
    public static final int TYPE_SFUNCION = 1;
    public static final int TYPE_LOGARITHM = 2;
    public static final int TYPE_POWER = 3;
    public static final int TYPE_ROOT = 4;
    public static final int TYPE_GAMMA_CORRECTION = 5;
    public static final int MAX_LEVEL = 255;
    public static final double DEFAULT_M_SFUNCTION = 128.0;
    public static final double DEFAULT_E_SFUNCTION = 4.0;
    public static final double DEFAULT_POWER = 2.0;
    public static final double DEFAULT_ROOT = 3.0;
    public static final double DEFAULT_A_GAMMA = 1.0;
    public static final double DEFAULT_GAMMA = 0.4;
    protected static final boolean ECHO = false;

    /**
     * Constructor por defecto que genera un objeto LookupTable especifico según
     * el parámetro de entrada, posibles opciones: TYPE_NEGATIVE negativo de la
     * imagen. TYPE_SFUNCION función S. TYPE_LOGARITHM función logaritmo.
     * TYPE_POWER función potencia. TYPE_ROOT función raíz cuadrada.
     * TYPE_GAMMA_CORRECTION función gamma.
     *
     * @param type [in] entero que indica que tipo de LookupTable queremos.
     * @return objeto LookupTable con la forma necesaria.
     */
    public static LookupTable createLookupTable(final int type) {
        switch (type) {
            case 0: {
                return negativeFuction();
            }
            case 1: {
                return sFuction(128.0, 4.0);
            }
            case 2: {
                return logarithmFuction();
            }
            case 3: {
                return powerFuction(2.0);
            }
            case 4: {
                return rootFuction(3.0);
            }
            case 5: {
                return gammaCorrection(1.0, 0.4);
            }
            default: {
                return null;
            }
        }
    }

    /**
     * Genera un objeto de tipo LookupTable que contiene la función negativo.
     *
     * @return objeto LookupTable con la función especifica.
     */
    public static LookupTable negativeFuction() {
        byte[] lt = new byte[256];
        for (int l = 0; l <= 255; ++l) {
            lt[l] = (byte) (255 - l);
        }
        ByteLookupTable slt = new ByteLookupTable(0, lt);
        return slt;
    }

    /**
     * Genera un objeto de tipo LookupTable que contiene la función S.
     *
     * @param m [in] 
     * @param e [in] 
     * @return objeto LookupTable con la función especifica.
     */
    public static LookupTable sFuction(double m, double e) {
        double K = 255.0 / (1.0 / (1.0 + Math.pow(m / 255.0, e)));
        byte[] lt = new byte[256];
        lt[0] = 0;
        for (int l = 1; l <= 255; ++l) {
            lt[l] = (byte) (K * (1.0 / (1.0 + Math.pow(m / l, e))));
        }
        ByteLookupTable slt = new ByteLookupTable(0, lt);
        return slt;
    }

    /**
     * Genera un objeto de tipo LookupTable que contiene la función logaritmo.
     *
     * @return objeto LookupTable con la función especifica.
     */
    public static LookupTable logarithmFuction() {
        double K = 255.0 / Math.log(256.0);
        byte[] lt = new byte[256];
        for (int l = 0; l <= 255; ++l) {
            lt[l] = (byte) (K * Math.log(1.0 + l));
        }
        ByteLookupTable slt = new ByteLookupTable(0, lt);
        return slt;
    }

    /**
     * Genera un objeto de tipo LookupTable que contiene la función potencia.
     *
     * @param n [in] el entero del exponente.
     * @return objeto LookupTable con la función especifica.
     */
    public static LookupTable powerFuction(double n) {
        double K = 255.0 / Math.pow(255.0, n);
        byte[] lt = new byte[256];
        for (int l = 0; l <= 255; ++l) {
            lt[l] = (byte) (K * Math.pow(l, n));
        }
        ByteLookupTable slt = new ByteLookupTable(0, lt);
        return slt;
    }

    /**
     * Genera un objeto de tipo LookupTable que contiene la función raíz.
     *
     * @param n [in] el entero de la raíz
     * @return objeto LookupTable con la función especifica.
     */
    public static LookupTable rootFuction(double n) {
        return powerFuction(1.0 / n);
    }

    /**
     * Genera un objeto de tipo LookupTable que contiene la función gamma.
     *
     * @param cteA [in] Constante de 
     * @param gamma [in] Valor de gamma que hay que aplicar.
     * @return objeto LookupTable con la función especifica.
     */
    public static LookupTable gammaCorrection(double cteA, double gamma) {
        if (gamma <= 0.0) {
            return null;
        }
        double K = 255.0 / (cteA * Math.pow(255.0, gamma));
        byte[] lt = new byte[256];
        for (int l = 0; l <= 255; ++l) {
            lt[l] = (byte) (K * cteA * Math.pow(l, gamma));
        }
        ByteLookupTable slt = new ByteLookupTable(0, lt);
        return slt;
    }

    /**
     * Método que nos permite ver por pantalla el contenido del objeto.
     *
     * @param lt [in] objeto LookupTable que queremos observar.
     */
    protected static void toString(LookupTable lt) {
        byte[][] aux = ((ByteLookupTable) lt).getTable();
        for (int l = 0; l <= 255; ++l) {
            System.out.print(Byte.toUnsignedInt(aux[0][l]) + " ");
        }
        System.out.println();
    }
}
