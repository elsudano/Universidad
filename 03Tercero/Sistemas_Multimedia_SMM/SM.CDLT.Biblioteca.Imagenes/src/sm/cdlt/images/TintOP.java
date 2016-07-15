package sm.cdlt.images;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.cdlt.util.BufferedImageSampleIterator;
import sm.cdlt.util.SampleData;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 23-may-2016
 */
public class TintOP extends BufferedImageOP {

    /**
     * Este será el color con el que tintaremos la imagen.
     */
    private Color color_tinte;
    /**
     * Este sera el porcentaje del color que con el que se tintará la imagen.
     */
    private float porcentaje;

    public TintOP(Color c, float p) {
        super();
        if (c == null) {
            throw new IllegalArgumentException("Color cannot be null");
        }
        this.color_tinte = c;
        p = p * 0.01f;
        if (p < 0.0f) {
            p = 0.0f;
        } else if (p > 1.0f) {
            p = 1.0f;
        }
        this.porcentaje = p;
    }

    /**
     * Con este método asignamos el color con el que queremos tintar la Imagen.
     *
     * @param c [in] Color con el que se tintará la imagen.
     */
    public void setColor(Color c) {
        this.color_tinte = c;
    }

    /**
     * Con este método ponemos el porcentaje de tintado de la imagen.
     *
     * @param p [in] porcentaje en el que se tintara la imagen.
     */
    public void setPorcentaje(float p) {
        this.porcentaje = p;
    }

    @Override
    public BufferedImage filter(BufferedImage second, BufferedImage dest) {
        if (second == null) {
            throw new NullPointerException("Origen image cannot be null");
        }
        if (dest == null) {
            dest = this.createCompatibleDestImage(second, null);
        }
        WritableRaster raster = dest.getRaster();
        float[] color_sample = this.color_tinte.getColorComponents(null);
        BufferedImageSampleIterator it = new BufferedImageSampleIterator(second);
        while (it.hasNext()) {
            SampleData sample = it.next();
            /* La imagen destino tiene transparencia me aseguro que tenga el alpha a 255 */
            if (sample.getBand() == 3) {
                raster.setSample(sample.getCol(), sample.getRow(), 3, 255);
            }
            if (sample.getBand() < 3) {
                int value = (int) (sample.getValue() * (1.0f - this.porcentaje) + 255.0f * color_sample[sample.getBand()] * this.porcentaje);
                raster.setSample(sample.getCol(), sample.getRow(), sample.getBand(), value);
            }
        }
        return dest;
    }

}
