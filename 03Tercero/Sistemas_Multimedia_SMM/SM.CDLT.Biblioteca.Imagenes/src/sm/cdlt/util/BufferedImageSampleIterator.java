package sm.cdlt.util;

import java.awt.image.BufferedImage;
import java.util.Iterator;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">{user}</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 10-may-2016
 */
public class BufferedImageSampleIterator implements Iterator<SampleData> {

    private int rows;
    private int cols;
    private int bands;
    private int size;
    private int count;
    private BufferedImage myImage;
    
    /**
     * Constructor por defecto del iterador.
     *
     * @param img Imagen sobre la que vamos a iterar.
     */
    public BufferedImageSampleIterator(BufferedImage img) {
        if (img != null) {
            this.count = 0;
            this.myImage = img;
            this.rows = this.myImage.getRaster().getHeight();
            this.cols = this.myImage.getRaster().getWidth();
            this.bands = this.myImage.getRaster().getNumBands();
            this.size = this.rows * this.cols * this.bands;
        } else {
            throw new UnsupportedOperationException("Not valid null value in parameter img");
        }
    }

    /**
     * Comprobamos si tenemos mas datos para iterar.
     *
     * @return Devuelve verdadero si hay datos para iterar, falso en caso
     * contrario.
     */
    @Override
    public boolean hasNext() {
        return this.count < this.size;
    }

    /**
     * Devuelve el siguiente Sample de la imagen donde estamos iterando.
     * 
     * @return SampleData con los datos del sample de la imagen.
     */
    @Override
    public SampleData next() {
        int row, col, band;
        if (this.count >= this.size) {
            throw new UnsupportedOperationException("Is the end of image");
        }
        row = this.count / (this.cols * this.bands);
        col = this.count / this.bands % this.cols;
        band = this.count % this.bands;
        ++this.count;
        return new SampleData(col, row, band, this.myImage.getRaster().getSample(col, row, band));
    }

    /**
     * Not Implemented
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not implemented yet. Operation not supported");
    }
}
