package sm.cdlt.images;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import sm.cdlt.util.BufferedImagePixelIterator;
import sm.cdlt.util.PixelData;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 21-jun-2016
 */
public class SobelOP extends BufferedImageOP {

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
        float[] datamaskX = {-1.0f, -2.0f, -1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 2.0f, 1.0f};
        Kernel maskX = new Kernel(3, 3, datamaskX);
        ConvolveOp gradientX = new ConvolveOp(maskX);
        BufferedImage imgAuxX = gradientX.filter(second, null);
        
        float[] datamaskY = {1.0f, 0.0f, -1.0f, 2.0f, 0.0f, -2.0f, 1.0f, 0.0f, -1.0f};
        Kernel maskY = new Kernel(3, 3, datamaskY);
        ConvolveOp gradientY = new ConvolveOp(maskY);
        BufferedImage imgAuxY = gradientY.filter(second, null);
        
        BufferedImagePixelIterator itX = new BufferedImagePixelIterator(imgAuxX);
        BufferedImagePixelIterator itY = new BufferedImagePixelIterator(imgAuxY);
        PixelData pixelX, pixelY;
        Color color;
        int samX, samY, mag;
        
        while (itX.hasNext()) {
            pixelX = itX.next();
            pixelY = itY.next();
            samX = samY = 0;
            for (int sam = 0; sam < pixelX.getNumSamples(); ++sam) {
                samX += pixelX.getSample()[sam];
                samY += pixelY.getSample()[sam];
            }
            mag = (int) Math.hypot(samX, samY);
            if (mag <= 0) {
                mag = 0;
            }
            if (mag >= 255) {
                mag = 255;
            }
            color = new Color(mag, mag, mag);
            dest.setRGB(pixelX.getCol(), pixelX.getRow(), color.getRGB());
        }
        return dest;
    }
}
