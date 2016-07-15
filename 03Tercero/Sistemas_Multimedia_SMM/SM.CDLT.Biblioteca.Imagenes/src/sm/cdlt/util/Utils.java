package sm.cdlt.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ByteLookupTable;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.awt.image.DataBufferShort;
import java.awt.image.DataBufferUShort;
import java.awt.image.LookupTable;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">{user}</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 30-abr-2016
 */
public class Utils {

    /**
     * Variables que sirven para posicionar la aplicación en mirad de la
     * pantalla.
     */
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    public static final double SCREEN_WIDTH = SCREEN_SIZE.getWidth();
    public static final double SCREEN_HEIGHT = SCREEN_SIZE.getHeight();

    /**
     * Constructor sin parámetros
     */
    private Utils() {

    }

    /**
     * Creates a new raster that has a <b>copy</b> of the data in
     * <tt>ras</tt>. This is highly optimized for speed. There is no provision
     * for changing any aspect of the SampleModel. However you can specify a new
     * location for the returned raster.
     *
     * This method should be used when you need to change the contents of a
     * Raster that you do not "own" (ie the result of a
     * <tt>getData</tt> call).
     *
     * @param ras The Raster to copy.
     *
     * @param minX The x location for the upper left corner of the returned
     * WritableRaster.
     *
     * @param minY The y location for the upper left corner of the returned
     * WritableRaster.
     *
     * @return A writable copy of <tt>ras</tt>
     */
    public static WritableRaster copyRaster(Raster ras, int minX, int minY) {
        WritableRaster ret = Raster.createWritableRaster(ras.getSampleModel(), new Point(0, 0));
        ret = ret.createWritableChild(
                ras.getMinX() - ras.getSampleModelTranslateX(),
                ras.getMinY() - ras.getSampleModelTranslateY(),
                ras.getWidth(), ras.getHeight(), minX, minY, null);

        // Use System.arraycopy to copy the data between the two...
        DataBuffer srcDB = ras.getDataBuffer();
        DataBuffer retDB = ret.getDataBuffer();
        if (srcDB.getDataType() != retDB.getDataType()) {
            throw new IllegalArgumentException("New DataBuffer doesn't match original");
        }
        int len = srcDB.getSize();
        int banks = srcDB.getNumBanks();
        int[] offsets = srcDB.getOffsets();
        for (int b = 0; b < banks; b++) {
            //System.out.println("byte: " + DataBuffer.TYPE_BYTE + " int: " + DataBuffer.TYPE_INT + " short: " + DataBuffer.TYPE_SHORT + " ushort: " + DataBuffer.TYPE_USHORT);
            switch (srcDB.getDataType()) {
                case DataBuffer.TYPE_BYTE: {
                    DataBufferByte srcDBT = (DataBufferByte) srcDB;
                    DataBufferByte retDBT = (DataBufferByte) retDB;
                    System.arraycopy(srcDBT.getData(b), offsets[b],
                            retDBT.getData(b), offsets[b], len);
                }
                break;
                case DataBuffer.TYPE_INT: {
                    DataBufferInt srcDBT = (DataBufferInt) srcDB;
                    DataBufferInt retDBT = (DataBufferInt) retDB;
                    System.arraycopy(srcDBT.getData(b), offsets[b],
                            retDBT.getData(b), offsets[b], len);
                }
                break;
                case DataBuffer.TYPE_SHORT: {
                    DataBufferShort srcDBT = (DataBufferShort) srcDB;
                    DataBufferShort retDBT = (DataBufferShort) retDB;
                    System.arraycopy(srcDBT.getData(b), offsets[b],
                            retDBT.getData(b), offsets[b], len);
                }
                break;
                case DataBuffer.TYPE_USHORT: {
                    DataBufferUShort srcDBT = (DataBufferUShort) srcDB;
                    DataBufferUShort retDBT = (DataBufferUShort) retDB;
                    System.arraycopy(srcDBT.getData(b), offsets[b],
                            retDBT.getData(b), offsets[b], len);
                }
                break;
            }
        }
        return ret;
    }

    /**
     * Este método nos ayuda a saber cuando una imagen es nueva o se acaba de
     * crear se recorre todos los samples de la imagen y si alguno de ellos es
     * diferentes de 255 se considera que la imagen no es nueva.
     *
     * TODO hay que pulirla por que no consideramos todas las casuísticas de
     * las imágenes.
     *
     * @param img [in] Será la imagen que queremos saber si es nueva
     * @return Verdadero si la imagen es nueva, falso en caso contrario.
     */
    public static boolean isNew(BufferedImage img) {
        boolean isNew = true;
        BufferedImageSampleIterator it = new BufferedImageSampleIterator(img);
        while (it.hasNext()) {
            if (it.next().getValue() != 255) {
                isNew = false;
                break;
            }
        }
        return isNew;
    }

    /**
     * Con este método convertimos el tipo de imagen que tenemos en el parámetro
     * img a otro que nosotros necesitemos, esto lo podemos utilizar para
     * cambiar una imagen GIF, a un formato que no sea indexado, para de esta
     * forma poder aplicar diferentes filtros que no se pueden aplicar a las
     * imágenes no indexadas;
     *
     * Este método no cambia la imagen original, si queremos cambiar el tipo de
     * la imagen original tenemos que usar este método guardar el resultado en
     * una variable y a continuación usar el método setImage() para poner como
     * original la imagen resultado.
     *
     * @param img [in] objeto de tipo BufferedImage que contiene la imagen que
     * queremos convertir de tipo.
     * @param type [in] Es de tipo int e indica el tipo al que vamos a cambiar
     * la imagen.
     * @return Devuelve un objeto BufferedImage con los datos de la imagen y con
     * el tipo cambiado al que le hayamos pasado por parámetros.
     */
    public static BufferedImage convertImageToType(BufferedImage img, int type) {
        BufferedImage imgOut = img;
        if (imgOut != null) {
            imgOut = new BufferedImage(imgOut.getWidth(), imgOut.getHeight(), type);
            Graphics2D myG2D = imgOut.createGraphics();
            myG2D.drawImage(img, 0, 0, null);
        }
        return imgOut;
    }

//    /**
//     * Con este método lo que conseguimos es redimensionar la imagen que le
//     * pasamos por parámetros, teniendo en cuenta que la imagen sigue teniendo
//     * el mismo tamaño que tenia antes pero se crean "huecos" blancos en los
//     * espacios a los que no llega la imagen.
//     *
//     * Hay que tener en cuenta tambien que segun el tipo de imagen que le
//     * pasemos este método rellenara los "huecos" con el color blanco de fondo o
//     * lo dejará transparente tal y como indica el tipo de la imágen.
//     *
//     * @param img [in] objeto de tipo BufferedImage que contiene la imagen a
//     * redimencionar
//     * @param w [in] tipo entero que indica el nuevo ancho de la imagen
//     * @param h [in] tipo entero que indica el nuevo alto de la imagen
//     * @return Devuelve un objeto de tipo BufferedImage con la imagen
//     * redimencionada
//     */
//    public static BufferedImage resizeImage(BufferedImage img, int w, int h) {
//        BufferedImage imgOut = null;
//        Graphics2D myG2D = null;
//        int type = 0;
//        if (img != null) {
//            type = img.getType();
//            imgOut = new BufferedImage(w, h, type);
//            myG2D = imgOut.createGraphics();
//        } else {
//            throw new UnsupportedOperationException("The image is equal to null");
//        }
//        if (w > 0 && h > 0) {
//            if (!img.getColorModel().hasAlpha()) {
//                myG2D.setPaint(Color.WHITE);
//                myG2D.fillRect(0, 0, w, h);
//            }
//            myG2D.drawImage(img, 0, 0, null);
//        } else {
//            throw new UnsupportedOperationException("The size of the width and height are not correct");
//        }
//        return imgOut;
//    }

//    /**
//     * Con este método conseguiremos reescalar la imagen que le pasamos por
//     * parámetros, osea que lo que vamos a realizar es "estirar la imagen" al
//     * nuevo tamaño que se especifique por los parámetros w y h.
//     *
//     * @param img [in] objeto de tipo BufferedImage que contiene la imagen a
//     * reescalar
//     * @param w [in] tipo entero que indica el nuevo ancho de la imagen
//     * @param h [in] tipo entero que indica el nuevo alto de la imagen
//     * @return Devuelve un objeto de tipo BufferedImage con la imagen reescalada
//     */
//    public static BufferedImage rescaleImage(BufferedImage img, int w, int h) {
//        BufferedImage imgOut = null;
//        Graphics2D myG2D = null;
//        int type = 0;
//        if (img != null) {
//            imgOut = new BufferedImage(w, h, img.getType());
//            myG2D = imgOut.createGraphics();
//            type = img.getType();
//        } else {
//            throw new UnsupportedOperationException("The image is equal to null");
//        }
//        if (w > 0 && h > 0) {
//            if (type != BufferedImage.TYPE_4BYTE_ABGR && type != BufferedImage.TYPE_4BYTE_ABGR_PRE && type != BufferedImage.TYPE_INT_ARGB && type != BufferedImage.TYPE_INT_ARGB_PRE) {
//                myG2D.setPaint(Color.WHITE);
//                myG2D.fillRect(0, 0, w, h);
//            }
//            myG2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//            myG2D.drawImage(img, 0, 0, w, h, null);
//        } else {
//            throw new UnsupportedOperationException("The size of the width and height are not correct");
//        }
//        return imgOut;
//    }

    /**
     * Con este método podemos poner cualquier objeto que tenga el método
     * setLocation(), justamente en el centro de la pantalla, así pues, si
     * nosotros le pasamos el alto y el ancho del objeto en cuestión este método
     * nos calcula el punto donde debería estar la esquina superior izquierda de
     * dicho objeto para que saliera en el centro de la pantalla.
     *
     * @param w [in] Este será el ancho que tendrá el objeto que queremos
     * centrar
     * @param h [in] Este será el alto que tendrá el objeto que queremos centrar
     * @return Objeto de tipo Point con las coordenadas de la esquina superior
     * izquierda.
     */
    public static Point centerOfScreen(double w, double h) {
        int pos_x, pos_y;
        pos_x = pos_y = 0;
        if (w > 0 && h > 0) {
            pos_x = (int) (Utils.SCREEN_WIDTH / 2 - w / 2);
            pos_y = (int) (Utils.SCREEN_HEIGHT / 2 - h / 2);
        } else {
            // @TODO Tienes que poner una exception que sea correcta
            throw new UnsupportedOperationException("The size of the width and height are not correct");
        }
        return new Point(pos_x, pos_y);
    }

    /**
     * Con este método lo que vamos a hacer es crear una función seno que nos
     * devuelve el objeto LookupTable correspondiente a dicha función y así
     * poder utilizarla para un método de LookupOp.
     *
     * @param w [in] Será el valor angular de la función para calcular su
     * resultado
     * @return Objeto de tipo LookupTable con los datos para la operación
     * LookupOp
     */
    public static LookupTable senoFuction(double w) {
        double K = 255.0; //valor de normalización
        byte[] lt = new byte[256];
        double seno;
        double absoluto;
        //f(x)=|sin(w·x)|
        for (int count = 0; count <= 255; ++count) {
            seno = Math.sin(Math.toRadians(w * count));
            absoluto = Math.abs(seno);
            lt[count] = (byte) (K * absoluto);
        }
        return new ByteLookupTable(0, lt);
    }
    
    /**
     * Con este método lo que vamos a hacer es crear una función coseno que nos
     * devuelve el objeto LookupTable correspondiente a dicha función y así
     * poder utilizarla para un método de LookupOp.
     *
     * @param w [in] Será el valor angular de la función para calcular su
     * resultado
     * @return Objeto de tipo LookupTable con los datos para la operación
     * LookupOp
     */
    public static LookupTable cosenoFuction(double w) {
        double K = 255.0; //valor de normalización
        byte[] lt = new byte[256];
        double coseno;
        double absoluto;
        //f(x)=|sin(w·x)|
        for (int count = 0; count <= 255; ++count) {
            coseno = Math.cos(Math.toRadians(w * count));
            absoluto = Math.abs(coseno);
            lt[count] = (byte) (K * absoluto);
        }
        return new ByteLookupTable(0, lt);
    }
}
