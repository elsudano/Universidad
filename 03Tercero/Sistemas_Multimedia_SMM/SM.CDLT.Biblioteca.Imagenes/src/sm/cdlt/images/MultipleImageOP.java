package sm.cdlt.images;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Arrays;
import sm.cdlt.util.BufferedImageSampleIterator;
import sm.cdlt.util.SampleData;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 19-may-2016
 */
public abstract class MultipleImageOP extends BufferedImageOP {

    /**
     * Imagen de destino, se usa para ir almacenando la información del cálculo
     * de la operación y presentar la imagen resultante.
     */
    protected BufferedImage image_out;

    /**
     * Operación que se encarga de calcular dos samples que se le pasan por
     * parámetros. Ejemplo: Si queremos sumar N imágenes lo que tenemos que
     * hacer es considerar el sample s1 como el sample de origen y el s2 como el
     * sample de destino así pues podemos sumar s1 y s2 y el resultado que
     * obtengamos lo dividimos por N (imágenes) así lo que obtenemos es una suma
     * ponderada. Se supone que la imagen de destino siempre será mas grande que
     * cualquiera de las imágenes de origen, por ese motivo se pone primero la
     * de destino por que si queremos realizar la operación de resta es mas
     * fácil de esta forma.
     *
     * @param s1 [in] tipo float valor de la imagen de destino.
     * @param s2 [in] tipo float valor de la imagen (N) de origen
     * @return tipo double será el valor resultante de la operación.
     */
//    public abstract float sampleOperation(float s1, float s2);

    /**
     * Operación que se encarga de calcular dos samples que se le pasan por
     * parámetros. Ejemplo: Si queremos sumar N imágenes lo que tenemos que
     * hacer es considerar el sample s1 como el sample de origen y el s2 como el
     * sample de destino así pues podemos sumar s1 y s2 y el resultado que
     * obtengamos lo dividimos por N (imágenes) así lo que obtenemos es una suma
     * ponderada. Se supone que la imagen de destino siempre será mas grande que
     * cualquiera de las imágenes de origen, por ese motivo se pone primero la
     * de destino por que si queremos realizar la operación de resta es mas
     * fácil de esta forma, el tercer parámetro son permite asignar un alpha
     * al sample que estamos procesando.
     *
     * @param s1 [in] tipo float valor de la imagen de destino.
     * @param s2 [in] tipo float valor de la imagen (N) de origen
     * @param a [in] tipo float valor de alpha del sample.
     * @return tipo double será el valor resultante de la operación.
     */
    public abstract float sampleOperation(float s1, float s2, float a);
    
    /**
     * Constructor sin parámetros, básicamente se encarga de poner la primera
     * imagen que será la de destino, osea que será el resultado de la
     * operación.
     */
    public MultipleImageOP() {
        super();
        this.image_out = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
    }

    /**
     * Como este método solo admite operaciones con solo una imagen, para
     * realizar operaciones con múltiples imágenes utilizamos el método applyOP
     *
     * @param second nothing
     * @param dest nothing
     * @return nothing
     */
    @Override
    public BufferedImage filter(BufferedImage second, BufferedImage dest) {
        throw new UnsupportedOperationException("This operation is only for single image, please use applyOP method for multiple images");
    }

    /**
     * Método encargado de crear una imagen compatible con la imagen origen.
     * Por supuesto teniendo en cuenta la casuística de las N imágenes.
     * 
     * @param second [in] imagen origen desde donde crear la compatible.
     * @param cm [in] ColorModel que queremos que tenga la nueva imagen.
     * @return Tipo bufferedImage con el mismo tamaño y con el ColorModel que hayamos asignado.
     */
    @Override
    public BufferedImage createCompatibleDestImage(BufferedImage second, ColorModel cm) {
        if (cm == null) {
            if (this.image_out.getRaster().getNumBands() > second.getRaster().getNumBands()) {
                cm = this.image_out.getColorModel();
            } else {
                cm = second.getColorModel();
            }
        }
        int width = Math.max(this.image_out.getWidth(), second.getWidth());
        int height = Math.max(this.image_out.getHeight(), second.getHeight());
        WritableRaster wr = cm.createCompatibleWritableRaster(width, height);
        this.image_out = new BufferedImage(cm, wr, cm.isAlphaPremultiplied(), null);
        return this.image_out;
    }

    /**
     * Esta función se encarga de aplicar una operación elegida sobre un array
     * de N samples así pues, lo que se hará realmente es realizar la operación
     * pertinente por cada uno de los samples de todas las imágenes ponderando
     * el resultado.
     *
     * @param origs [in] de tipo ArrayList tiene las imágenes a combinar.
     * @param alphas [in] de tipo float contendrá un array con los valores de
     * alpha para cada una de las imágenes, con esto conseguimos darle mas
     * peso a la imagen que nosotros queremos, puede tomar el valor null
     * para asignar un valor por defecto.
     * @param dest [in] de tipo BufferedImage será la imagen resultado.
     * @return Tipo BufferedImage con la imagen resultado de la operación.
     */
    public BufferedImage applyOP(ArrayList<BufferedImage> origs, float[] alphas, BufferedImage dest) {
        /* Como el valor del array es nulo pongo un valor ponderado a los alphas de las imagenes */
        if (alphas == null){
            alphas = new float[origs.size()];
            Arrays.fill(alphas, 1.0f/origs.size());
        }
        if (origs.size() < 2) {
            throw new IllegalArgumentException("the size of array is minor of two, this class is for multiple images");
        }
        BufferedImage image;
        if (dest == null) {
            for (int i = 0; i < origs.size(); i++) {
                image = origs.get(i);
                dest = this.createCompatibleDestImage(image, null);
            }
        }
        this.image_out = dest;
        /* Las medidas de la imagen final son las que tiene el lienzo */
        SampleData sample;
        BufferedImageSampleIterator samples_it = new BufferedImageSampleIterator(this.image_out);
        WritableRaster orig_raster, dest_raster = this.image_out.getRaster();
        float s1, s2;
        int ini_x, ini_y, fin_x, fin_y;
        /*
        Ahora recorro todos los samples imagen por imagen, lo hago de esta forma
        por que mas eficiente, y necesito hacer solo una pasada.
         */
        while (samples_it.hasNext()) {
            sample = samples_it.next();
            /*
            La imagen destino tiene transparencia me aseguro que tenga el alpha a 255
            */
            if (sample.getBand() == 3) {
                dest_raster.setSample(sample.getCol(), sample.getRow(), sample.getBand(), 255);
            }
            for (int i = 0; i < origs.size(); i++) {
                image = origs.get(i);
                orig_raster = image.getRaster();
                /*
                Desde donde comienza hasta donde termina la imagen que estoy
                procesando en cada instante, de esta forma puedo pintar todas
                las imagenes en el centro de la imagen destino
                */
                ini_x = (int) (this.image_out.getWidth() / 2 - image.getWidth() / 2);
                ini_y = (int) (this.image_out.getHeight() / 2 - image.getHeight() / 2);
                fin_x = image.getWidth() + ini_x;
                fin_y = image.getHeight() + ini_y;
                s1 = dest_raster.getSample(sample.getCol(), sample.getRow(), sample.getBand());
                dest_raster.setSample(sample.getCol(), sample.getRow(), sample.getBand(), s1);
                if (sample.getCol() >= ini_x
                        && sample.getRow() >= ini_y
                        && sample.getCol() < fin_x
                        && sample.getRow() < fin_y
                        && sample.getBand() < orig_raster.getNumBands()) {
                    // se restan las posiciones para que las imagenes salgan centradas
                    s2 = orig_raster.getSample(sample.getCol() - ini_x, sample.getRow() - ini_y, sample.getBand());
                    dest_raster.setSample(sample.getCol(), sample.getRow(), sample.getBand(), this.sampleOperation(s1, s2, alphas[i]));
                }
            }
        }
        return this.image_out;
    }

    /**
     * Con esta función truncamos el valor del sample a un valor dentro de los
     * validos.
     *
     * @param value [in] de tipo float valor del sample.
     * @param min [in] de tipo entero valor mínimo del rango.
     * @param max [in] de tipo entero valor máximo del rango.
     * @return valor entero normalizado para el sample.
     */
    public int truncateValue(float value, int min, int max) {
        int aux = (int) value;
        if (value <= min) {
            aux = min;
        } else if (value >= max) {
            aux = max;
        }
        return aux;
    }
}
