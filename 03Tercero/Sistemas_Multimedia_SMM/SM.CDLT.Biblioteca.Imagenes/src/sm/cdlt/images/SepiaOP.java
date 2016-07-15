package sm.cdlt.images;

import sm.cdlt.util.BufferedImagePixelIterator;
import sm.cdlt.util.PixelData;
import java.awt.image.BufferedImage;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">{user}</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 17-may-2016
 */
public class SepiaOP extends BufferedImageOP {

    public SepiaOP() {
    }

    /**
     * Método que se encarga de aplicar el filtro.
     *
     * @param second [in] Imagen de origen.
     * @param dest [in] Imagen de destino, puede valer null.
     * @return objeto BufferedImage con la imagen original y el filtro aplicado.
     */
    @Override
    public BufferedImage filter(BufferedImage second, BufferedImage dest) {
        if (second == null) {
            throw new NullPointerException("src image is null");
        }
        if (dest == null) {
            dest = createCompatibleDestImage(second, null);
        }
        PixelData pixel;
        float[] sample, dsample = {0, 0, 0};
        for (BufferedImagePixelIterator it = new BufferedImagePixelIterator(second); it.hasNext();) {
            pixel = it.next();
            sample = pixel.getSample();
            dsample[0] = (int) Math.min(255, 0.393 * sample[0] + 0.769 * sample[1] + 0.189 * sample[2]);
            dsample[1] = (int) Math.min(255, 0.349 * sample[0] + 0.686 * sample[1] + 0.168 * sample[2]);
            dsample[2] = (int) Math.min(255, 0.272 * sample[0] + 0.534 * sample[1] + 0.131 * sample[2]);
            dest.getRaster().setPixel(pixel.getCol(), pixel.getRow(), dsample);
        }
        return dest;
    }
}
