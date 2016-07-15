package sm.cdlt.images;

import sm.cdlt.util.BufferedImagePixelIterator;
import sm.cdlt.util.ColorSpaceYCbCr;
import sm.cdlt.util.PixelData;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">{user}</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 17-may-2016
 */
public class myFirstEfectOP extends BufferedImageOP {

    public myFirstEfectOP() {
        super();
    }

    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        if (src == null) {
            throw new NullPointerException("src image is null");
        }
        /*
        Para poder realizar mi efecto es necesario que la imagen destino siempre
        sea del tipo de espacio de color YCbCr, por lo tanto lo que hago es que 
        si o si creo una imagen destino ignorando la que entra por parámetro.
         */
        ColorSpace cs = new ColorSpaceYCbCr();
        ColorModel cm = new ComponentColorModel(cs, false, false, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        dest = createCompatibleDestImage(src, cm);
        int[] pixel = new int[3];
        for (int i = 0; i < src.getWidth(); i++) {
            for (int j = 0; j < src.getHeight(); j++) {
                src.getRaster().getPixel(i, j, pixel);
                dest.getRaster().getPixel(i, j, pixel);
            }

        }
//        PixelData pixel;
//        for (BufferedImagePixelIterator it = new BufferedImagePixelIterator(src); it.hasNext();) {
//            pixel = it.next();
//            //pixel.setSample(cs.toRGB(pixel.getSample()));
//            dest.getRaster().setPixel(pixel.getCol(), pixel.getRow(), pixel.getSample());
//        }
        return dest;
    }

    @Override
    public BufferedImage createCompatibleDestImage(BufferedImage second, ColorModel cm) {
        if (second == null) {
            throw new NullPointerException("The first parameter cannot null value");
        }
        if (cm == null) {
            cm = second.getColorModel();
        }

        WritableRaster wr = cm.createCompatibleWritableRaster(second.getWidth(), second.getHeight());
        BufferedImage aux = new BufferedImage(cm, wr, cm.isAlphaPremultiplied(), null);
        if (cm.getColorSpace() instanceof ColorSpaceYCbCr) {
            BufferedImagePixelIterator it_orig = new BufferedImagePixelIterator(second);
            BufferedImagePixelIterator it_dest = new BufferedImagePixelIterator(aux);
            ColorSpaceYCbCr cs;
            PixelData pixel_orig, pixel_dest;
            int[] pi = new int[3];
            float[] pf = new float[3];
            while (it_orig.hasNext()) {
                pixel_orig = it_orig.next();
                pixel_dest = it_dest.next();
                pi[0] = (int)pixel_orig.getSample()[0];
                pi[1] = (int)pixel_orig.getSample()[1];
                pi[2] = (int)pixel_orig.getSample()[2];
                cs = (ColorSpaceYCbCr) cm.getColorSpace();
                pi = cs.fromRGB(pi);
                pf[0] = pi[0];
                pf[1] = pi[1];
                pf[2] = pi[2];
                pixel_dest.setSample(pf);
            }
        }
        return aux;
    }

}
