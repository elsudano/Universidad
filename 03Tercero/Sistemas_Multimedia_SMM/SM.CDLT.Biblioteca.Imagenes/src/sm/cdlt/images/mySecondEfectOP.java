package sm.cdlt.images;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BandCombineOp;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 21-jun-2016
 */
public class mySecondEfectOP extends BufferedImageOP {

    private int size;
    private float transparency;
    private boolean shadowOnly;
    private Color shadowColor;

    /**
     * Crea una sombra por defecto, cuyos valores son: Tamaño 5 pixeles, 50% de
     * transparencia y de color negro.
     */
    public mySecondEfectOP() {
        this.size = 5;
        this.transparency = 0.5f;
        this.shadowOnly = false;
        this.shadowColor = Color.BLACK;
    }

    /**
     * Crea una sombra a medida, según los parámetros que le pasamos.
     *
     * @param s [in] tamaño en pixeles de la sombra.
     * @param t [in] opacidad de la sombra.
     * @param c [in] Color que queremos la sombra.
     */
    public mySecondEfectOP(int s, float t, Color c) {
        this.size = s;
        this.transparency = t;
        this.shadowOnly = false;
        this.shadowColor = c;
    }

    /**
     * Podemos mostrar solamente la sombra cuando apliquemos el filtro.
     */
    public void onliShadow() {
        this.shadowOnly = true;
    }

    @Override
    public BufferedImage filter(BufferedImage second, BufferedImage dest) {
        if (second == null) {
            throw new NullPointerException("src image is null");
        }
        if (dest == null) {
            dest = createCompatibleDestImage(second, null);
        }
        // Creamos una mascara para el canal alpha en imagenes de 4 canales
        float[][] extractAlpha = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, this.transparency}
        };
        BufferedImage shadow = new BufferedImage(second.getWidth(), second.getHeight(), BufferedImage.TYPE_INT_ARGB);
        new BandCombineOp(extractAlpha, null).filter(second.getRaster(), shadow.getRaster());
        float[] datamask = {
            0.0030f, 0.0133f, 0.0219f, 0.0133f, 0.0030f,
            0.0133f, 0.0596f, 0.0983f, 0.0596f, 0.0133f,
            0.0219f, 0.0983f, 0.1621f, 0.0983f, 0.0219f,
            0.0133f, 0.0596f, 0.0983f, 0.0596f, 0.0133f,
            0.0030f, 0.0133f, 0.0219f, 0.0133f, 0.0030f
        };
        Kernel mask = new Kernel(5, 5, datamask);
        ConvolveOp gaussian = new ConvolveOp(mask, ConvolveOp.EDGE_NO_OP, null);
        shadow = gaussian.filter(shadow, null);
        Graphics2D g2d = dest.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.transparency));
        int distance = size / 2;
        g2d.drawRenderedImage(shadow, AffineTransform.getTranslateInstance(distance, distance));
        if (!shadowOnly) {
            g2d.setComposite(AlphaComposite.SrcOver);
            g2d.drawRenderedImage(second, null);
        }
        g2d.dispose();
        return dest;
    }

}
