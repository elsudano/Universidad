package sm.cdlt.images;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.cdlt.util.BufferedImagePixelIterator;
import sm.cdlt.util.PixelData;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">{user}</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 20-jun-2016
 */
public class UmbralizationOP extends BufferedImageOP {

    /**
     * Variable que almacena el color de umbralización.
     */
    private Color color;
    /**
     * Variable con el porcentaje que se aplicara la umbralización.
     */
    private int percent;

    /**
     * Constructor con parámetros que nos sirve para asignar el valor básico
     * para la umbralización, el porcentaje con el que se actuará.
     *
     * @param p [in] Porcentaje que se aplicará.
     */
    public UmbralizationOP(int p) {
        this.percent = p;
    }

    /**
     * Constructor con parámetros que nos sirve para asignar los valores básicos
     * para la umbralización, el Color y el porcentaje con el que se actuará.
     *
     * @param c [in] Color que queremos umbralizar.
     * @param p [in] Porcentaje que se aplicará.
     */
    public UmbralizationOP(Color c, int p) {
        this.color = c;
        this.percent = p;
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
        int numBand = second.getColorModel().getNumComponents();
        WritableRaster destRaster = dest.getRaster();
        BufferedImagePixelIterator it = new BufferedImagePixelIterator(second);
        PixelData pixel;
        while (it.hasNext()) {
            pixel = it.next();
            if (numBand < 3) { // blanco y negro
                int greyLevel = 0;
                for (int sam = 0; sam < pixel.getNumSamples(); ++sam) {
                    greyLevel += pixel.getSample()[sam];
                }
                greyLevel /= pixel.getNumSamples();
                if (greyLevel < this.percent) {
                    dest.setRGB(pixel.getCol(), pixel.getRow(), Color.black.getRGB());
                } else if (this.color != null) { // color elegido
                    destRaster.setPixel(pixel.getCol(), pixel.getRow(), pixel.getSample());
                } else { // color original
                    dest.setRGB(pixel.getCol(), pixel.getRow(), Color.white.getRGB());
                }
            } else { // color
                float[] centerColor;
                if (this.color != null) { // color elegido
                    centerColor = this.color.getColorComponents(null);
                    destRaster.setPixel(pixel.getCol(), pixel.getRow(), pixel.getSample());
                } else { // color original
                    centerColor = Color.white.getColorComponents(null);
                    dest.setRGB(pixel.getCol(), pixel.getRow(), Color.white.getRGB());
                }
                for (int sam = 0; sam < pixel.getNumSamples(); ++sam) {
                    final int distC = (int) Math.abs(centerColor[sam] * 255.0f - pixel.getSample()[sam]);
                    if (distC > this.percent) {
                        dest.setRGB(pixel.getCol(), pixel.getRow(), Color.black.getRGB());
                        break;
                    }
                }
            }
        }
        return dest;
    }
}
