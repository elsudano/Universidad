package sm.cdlt.images;

import java.awt.image.BufferedImage;
import sm.cdlt.util.BufferedImageSampleIterator;
import sm.cdlt.util.SampleData;

/**
 * Esta clase ha sido creada por el profesor:
 * <a href="mailto:jesus@decsai.ugr.es">Jesús Chamorro Martínez</a>
 * para la Asignatura (SMM) que imparte en la Universidad de Granada
 * <a href="http://etsiit.ugr.es/">ETSIIT</a>
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:jesus@decsai.ugr.es">Jesús Chamorro Martínez</a>
 * created on 12-jul-2016
 */
public class Histogram {

    private int[][] h;
    private int numBands;
    private int maxSampleValue;
    private int numSamplesBand;

    public Histogram(BufferedImage img) {
        super();
        if (img == null) {
            throw new IllegalArgumentException("Image cannot be null");
        }
        this.numBands = img.getRaster().getNumBands();
        this.maxSampleValue = (int) Math.pow(2.0, img.getSampleModel().getSampleSize(0)) - 1;
        this.h = new int[this.numBands][this.maxSampleValue + 1];
        this.numSamplesBand = img.getWidth() * img.getHeight();
        BufferedImageSampleIterator it = new BufferedImageSampleIterator(img);
        while (it.hasNext()) {
            SampleData sample = it.next();
            int[] array = this.h[sample.getBand()];
            int value = (int) sample.getValue();
            ++array[value];
        }
    }

    public int getValue(int bin, int band) {
        return this.h[band][bin];
    }

    public int getNumBins() {
        return this.maxSampleValue + 1;
    }

    public int getNumBands() {
        return this.numBands;
    }

    public int[][] getConts() {
        return this.h.clone();
    }

    public int[] getConts(int band) {
        if (band >= this.numBands) {
            throw new IllegalArgumentException("Band index out of bounds");
        }
        return this.h[band].clone();
    }

    public double[][] getNormalizedHistogram() {
        double[][] nh = new double[this.numBands][this.maxSampleValue + 1];
        for (int band = 0; band < this.h.length; ++band) {
            for (int bin = 0; bin < this.h[band].length; ++bin) {
                nh[band][bin] = this.h[band][bin] / this.numSamplesBand;
            }
        }
        return nh;
    }

    public double[] getNormalizedHistogram(int band) {
        if (band >= this.numBands) {
            throw new IllegalArgumentException("Band index out of bounds");
        }
        double[] nh = new double[this.maxSampleValue + 1];
        for (int bin = 0; bin < this.h[band].length; ++bin) {
            nh[bin] = this.h[band][bin] / this.numSamplesBand;
        }
        return nh;
    }

    public double[][] getCumulativeHistogram() {
        double[][] ch = this.getNormalizedHistogram();
        for (int band = 0; band < ch.length; ++band) {
            for (int bin = 1; bin < ch[band].length; ++bin) {
                ch[band][bin] += ch[band][bin - 1];
            }
        }
        return ch;
    }

    public double[] getCumulativeHistogram(final int band) {
        if (band >= this.numBands) {
            throw new IllegalArgumentException("Band index out of bounds");
        }
        double[] ch = this.getNormalizedHistogram(band);
        for (int bin = 1; bin < ch.length; ++bin) {
            ch[bin] += ch[bin - 1];
        }
        return ch;
    }
}
