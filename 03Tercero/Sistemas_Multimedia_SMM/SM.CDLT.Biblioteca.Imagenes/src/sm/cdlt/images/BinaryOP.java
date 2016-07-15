package sm.cdlt.images;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import sm.cdlt.util.BufferedImageSampleIterator;
import sm.cdlt.util.SampleData;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">{user}</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 20-may-2016
 */
public abstract class BinaryOP extends BufferedImageOP {

    /**
     * Esta es la imagen que se pasa por el constructor
     */
    protected BufferedImage first;

    /**
     * La operación que queremos que se ejecute a las dos imágenes se tiene que
     * poner en este método. Si queremos sumar, restar o multiplicar los valores
     * de los samples.
     *
     * @param s1 [in] será el valor del sample de la primera imagen
     * @param s2 [in] será el valor del sample de la segunda imagen
     * @return valor de la operación realizada.
     */
    public abstract float binaryOp(float s1, float s2);

    /**
     * Constructor de un parámetro para inicializar la operación.
     *
     * @param img [in] Será la primera imagen que intervenga en la acción
     */
    public BinaryOP(BufferedImage img) {
        super();
        this.first = img;
    }

    /**
     * Método que se encarga de aplicar el filtro que hemos escogido
     *
     * @param second [in] esta será la imagen que queremos procesar.
     * @param dest [in] esta será la imagen resultado de la operación.
     * @return tipo BufferedImage con la imagen resultado.
     */
    @Override
    public BufferedImage filter(BufferedImage second, BufferedImage dest) {
        if (second == null) {
            throw new NullPointerException("src image is null");
        }
        if (dest == null) {
            dest = this.createCompatibleDestImage(second, null);
        }
        WritableRaster firstRaster = this.first.getRaster();
        WritableRaster secondRaster = second.getRaster();
        WritableRaster destRaster = dest.getRaster();
        float s1, s2;
        SampleData sample;
        BufferedImageSampleIterator it = new BufferedImageSampleIterator(dest);
        while (it.hasNext()) {
            sample = it.next();
            s1 = firstRaster.getSample(sample.getCol(), sample.getRow(), sample.getBand());
            s2 = secondRaster.getSample(sample.getCol(), sample.getRow(), sample.getBand());
            destRaster.setSample(sample.getCol(), sample.getRow(), sample.getBand(), this.binaryOp(s1, s2));
        }
        return dest;
    }

    /**
     * Método encargado de crear una imagen compatible con la imagen origen.
     * Teniendo solo en cuenta que puede haber solo 2 imágenes.
     *
     * @param second [in] imagen origen desde donde crear la compatible.
     * @param cm [in] ColorModel que queremos que tenga la nueva imagen.
     * @return Tipo bufferedImage con el mismo tamaño y con el ColorModel que
     * hayamos asignado.
     */
    @Override
    public BufferedImage createCompatibleDestImage(BufferedImage second, ColorModel cm) {
        BufferedImage aux;
        if (cm == null) {
            if (this.first.getRaster().getNumBands() < second.getRaster().getNumBands()) {
                cm = this.first.getColorModel();
            } else {
                cm = second.getColorModel();
            }
        }
        int widthInsersection = Math.min(this.first.getWidth(), second.getWidth());
        int heightInsersection = Math.min(this.first.getHeight(), second.getHeight());
        WritableRaster wr = cm.createCompatibleWritableRaster(widthInsersection, heightInsersection);
        return new BufferedImage(cm, wr, cm.isAlphaPremultiplied(), null);
    }
}
