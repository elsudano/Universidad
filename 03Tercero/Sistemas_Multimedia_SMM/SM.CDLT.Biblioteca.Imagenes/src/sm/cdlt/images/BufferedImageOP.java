package sm.cdlt.images;

import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

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
 * created on 17-may-2016
 */
public abstract class BufferedImageOP implements BufferedImageOp {

    /**
     * Método que se encarga de aplicar el filtro que hemos escogido
     * 
     * @param second [in] esta será la imagen que queremos procesar.
     * @param dest [in] esta será la imagen resultado de la operación.
     * @return tipo BufferedImage con la imagen resultado.
     */
    @Override
    public abstract BufferedImage filter(BufferedImage second, BufferedImage dest);

    /**
     * Método que devuelve el Rectangle2D que contiene a la imagen.
     * @param img [in] Imagen a la que queremos contener.
     * @return Tipo Rectangle2D que sera el tamaño de la imagen.
     */
    @Override
    public Rectangle2D getBounds2D(BufferedImage img) {
        return img.getRaster().getBounds();
    }

    /**
     * Método encargado de crear una imagen compatible con la imagen origen.
     * 
     * @param second [in] imagen origen desde donde crear la compatible.
     * @param cm [in] ColorModel que queremos que tenga la nueva imagen.
     * @return Tipo bufferedImage con el mismo tamaño y con el ColorModel que hayamos asignado.
     */
    @Override
    public BufferedImage createCompatibleDestImage(BufferedImage second, ColorModel cm) {
        if (cm == null) {
            cm = second.getColorModel();
        }
        WritableRaster wr = cm.createCompatibleWritableRaster(second.getWidth(), second.getHeight());
        return new BufferedImage(cm, wr, cm.isAlphaPremultiplied(), null);
    }

    /**
     * Este método se encarga de devolver el punto que se encuentra en la
     * esquina superior izquierda de la imagen para poder posicionarla en
     * donde queramos.
     * 
     * @param p1 [in]
     * @param p2 [in]
     * @return objeto tipo Point2D nativo de java que indica la posición
     * superior izquierda de la forma
     */
    @Override
    public Point2D getPoint2D(Point2D p1, Point2D p2) {
        if (p2 == null) {
            p2 = (Point2D) p1.clone();
        } else {
            p2.setLocation(p1);
        }
        return p2;
    }

    /**
     * En este tipo de operaciones no vamos a utilizar ningún RenderingHits.
     * @return null.
     */
    @Override
    public RenderingHints getRenderingHints() {
        return null;
    }
}
