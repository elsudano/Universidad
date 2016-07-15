package sm.cdlt.images;

import java.awt.image.BufferedImage;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.ShortLookupTable;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 12-jul-2016
 */
public class EqualizationOP extends BufferedImageOP {

    /**
     * Variable global que indica que la ecualización se realiza en todas las
     * bandas
     */
    private static final int ALL_BANDS = -1;
    /**
     * Variable que almacena la banda a la que se quiere ecualizar
     */
    private int band;

    /**
     * Constructor por defecto, ecualiza todas las bandas
     */
    public EqualizationOP() {
        super();
        this.band = EqualizationOP.ALL_BANDS;
    }

    /**
     * Constructor con parámetro que permite asignar la banda que queremos
     * ecualizar
     *
     * @param b [in] Número de la banda que queremos ecualizar
     */
    public EqualizationOP(final int b) {
        super();
        if (b < 0) {
            throw new IllegalArgumentException("Band index must be positive");
        }
        this.band = b;
    }

    @Override
    public BufferedImage filter(BufferedImage second, BufferedImage dest) {
        if (second == null) {
            throw new NullPointerException("src image is null");
        }
        if (this.band >= second.getRaster().getNumBands()) {
            throw new IndexOutOfBoundsException("Band index out of bounds");
        }
        if (dest == null) {
            dest = this.createCompatibleDestImage(second, null);
        }
        Histogram histo = new Histogram(second);
        double[][] ch = histo.getCumulativeHistogram();
        short[][] ltm = new short[histo.getNumBands()][histo.getNumBins()];
        double maxSampleValue = histo.getNumBins() - 1;
        for (int band = 0; band < ch.length; ++band) {
            for (int bin = 0; bin < ch[band].length; ++bin) {
                if (this.band == -1) {
                    ltm[band][bin] = (short) Math.floor(ch[band][bin] * maxSampleValue);
                } else if (band == this.band) {
                    ltm[band][bin] = (short) Math.floor(ch[band][bin] * maxSampleValue);
                } else {
                    ltm[band][bin] = (short) bin;
                }
            }
        }
        LookupTable lt = new ShortLookupTable(0, ltm);
        LookupOp lop = new LookupOp(lt, null);
        lop.filter(second, dest);
        return dest;
    }

}
