package sm.cdlt.util;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.Iterator;

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
public class BufferedImagePixelIterator implements Iterator<PixelData> {

    private final Raster raster;
    private final int rows;
    private final int cols;
    private final int size;
    private int cont;
    private final float[] pixelComp;

    /**
     * Constructor por defecto del iterador.
     *
     * @param img Imagen sobre la que vamos a iterar.
     */
    public BufferedImagePixelIterator(final BufferedImage img) {
        super();
        this.cont = 0;
        this.pixelComp = null;
        this.raster = img.getRaster();
        this.rows = this.raster.getHeight();
        this.cols = this.raster.getWidth();
        this.size = this.rows * this.cols;
    }

    /**
     * Comprobamos si tenemos mas datos para iterar.
     *
     * @return Devuelve verdadero si hay datos para iterar, falso en caso
     * contrario.
     */
    @Override
    public boolean hasNext() {
        return this.cont < this.size;
    }

    /**
     * Devuelve el siguiente Pixel de la imagen donde estamos iterando.
     * 
     * @return PixelData con los datos del pixel de la imagen.
     */
    @Override
    public PixelData next() {
        if (this.cont >= this.size) {
            throw new UnsupportedOperationException("No more samples");
        }
        final int row = this.cont / this.cols;
        final int col = this.cont % this.cols;
        ++this.cont;
        return new PixelData(row, col, this.raster.getPixel(col, row, this.pixelComp));
    }

    /**
     * Not Implemented
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Remove operation not supported");
    }
}
