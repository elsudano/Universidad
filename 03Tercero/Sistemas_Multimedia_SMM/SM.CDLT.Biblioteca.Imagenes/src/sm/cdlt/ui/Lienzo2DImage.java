package sm.cdlt.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import sm.cdlt.graphics.geoPoint;
import sm.cdlt.graphics.myDiscontinuity;
import sm.cdlt.graphics.myRectangle;
import sm.cdlt.graphics.myRectangleShape;
import sm.cdlt.util.Utils;

/**
 * Esta clase se encarga todo lo referente a las imágenes, pero teniendo en
 * cuenta las diferentes formas que se pueden dibujar encima de las imágenes.
 * 
 * Precisamente por eso se hace una herencia desde Lienzo2D.
 * 
 * This class For contact with me visit https://www.sudano.net or send me a
 * email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 16-abr-2016
 */
public class Lienzo2DImage extends Lienzo2D {

    /**
     * Variable que se encarga de almacenar la imagen que hay en el lienzo.
     */
    private BufferedImage myImage;
    /**
     * Variables que almacenan el ancho y alto del lienzo (imagen).
     */
    private double pos_image_width, pos_image_heigth;
    /**
     * Constante que indica cual es el tamaño del borde discontinuo que delimita
     * a la imagen que estamos dibujando o modificando.
     */
    public static final float WIDTH_BORDER_IMAGE = 1.0f;

    /**
     * Constructor sin parámetros para el netbeans. NO USAR!!!
     */
    public Lienzo2DImage() {
        super();
        // pintamos el borde
        this.clip_border = true;
        // posicionamos el comienzo de la imagen
        this.pos_image_width = this.pos_image_heigth = 0;
        // ponemos una imagen de cualquier tamaño, para crear los componentes
        this.myImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        initComponents();
    }

    /**
     * Creamos un lienzo con una imagen (vacía) para pintar en ella, del tamaño
     * que se específica por el "PreferredSize".
     *
     * @param d [in] Objeto de tipo Dimension que nos indica el tamaño de la
     * imagen
     */
    public Lienzo2DImage(Dimension d) {
        super();
        // pintamos el borde
        this.clip_border = true;
        // posicionamos el comienzo de la imagen
        this.pos_image_width = this.pos_image_heigth = 0;
        // punto de origen para pintar la imagen
        this.point_to_paint = new geoPoint(this.pos_image_width, this.pos_image_heigth);
        // ponemos una imagen de cualquier tamaño, con offset para el borde discontinuo
        this.myImage = new BufferedImage((int) d.getWidth(), (int) d.getHeight(), BufferedImage.TYPE_INT_RGB);
        // dibujo el fondo blanco del lienzo por que es una imagen nueva
        new myRectangle(this.point_to_paint, d.getWidth(), d.getHeight(), Color.BLACK, 0).drawShapeIn(this.myImage.createGraphics());
        // esta será la zona visible y el borde de la imagen
        this.myVisiblePart = new myRectangle(this.point_to_paint, d.getWidth(), d.getHeight(), Color.BLACK, Lienzo2DImage.WIDTH_BORDER_IMAGE);
        // ponemos el tipo de discontinuidad
        this.myVisiblePart.setDiscontinuityType(myDiscontinuity.BORDER_IMAGE);
        // inicializamos los componentes
        initComponents();
    }

    /**
     * Creamos un lienzo con una imagen (vacía) para pintar en ella, del tamaño
     * que se específica por el parámetro d, y el tipo de imagen que queremos
     * con el parámetro t.
     *
     * @param d [in] Objeto de tipo Dimension que nos indica el tamaño de la
     * imagen.
     * @param t [in] Tipo entero (Por defecto = 0, BufferedImage.TYPE_INT_RGB)
     * que especifica el tipo de imagen que queremos crear.
     */
    public Lienzo2DImage(Dimension d, int t) {
        super();
        // pintamos el borde
        this.clip_border = true;
        // posicionamos el comienzo de la imagen
        this.pos_image_width = this.pos_image_heigth = 0;
        // punto de origen para pintar la imagen
        this.point_to_paint = new geoPoint(this.pos_image_width, this.pos_image_heigth);
        // esta será la zona visible y el borde de la imagen
        this.myVisiblePart = new myRectangle(this.point_to_paint, d.getWidth(), d.getHeight(), Color.BLACK, Lienzo2DImage.WIDTH_BORDER_IMAGE);
        // ponemos el tipo de discontinuidad
        this.myVisiblePart.setDiscontinuityType(myDiscontinuity.BORDER_IMAGE);
        // si no queremos especificar ningún tipo se pone por defecto uno.
        this.myImage = new BufferedImage((int) d.getWidth(), (int) d.getHeight(), t);
        // según tenga o no transparencia ponemos el fondo o no
        if (!this.myImage.getColorModel().hasAlpha()) {
            myRectangle fondo = new myRectangle(this.point_to_paint, this.myImage.getWidth(), this.myImage.getHeight(), Color.WHITE, 0);
            fondo.setFill(Color.WHITE);
            fondo.drawShapeIn(this.myImage.createGraphics());
        }
        // inicializamos los componentes
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new Dimension(this.myImage.getWidth()+10, this.myImage.getHeight()+10));
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Con este método convertimos todo lo que tengamos en el lienzo en un
     * BufferedImage y después lo devolvemos con las modificaciones ya
     * realizadas.
     *
     * @return Objeto de tipo BufferedImage con los datos en bruto de la imagen.
     */
    public BufferedImage getImage() {
        return this.myImage;
    }

    /**
     * Con este método podemos ver la imagen que esta en el lienzo, junto con
     * las formas que hay en ella (true) o simplemente la imagen sin las formas
     * (false).
     *
     * @param dv de tipo boolean si es verdadero se guardan las formas, en caso
     * contrario solo se guarda la imagen.
     *
     * @return Devuelve un BufferedImage con los datos solo de la imagen o la
     * imagen con las formas según el parámetro de entrada.
     */
    public BufferedImage getImage(boolean dv) {
        if (dv) {
            BufferedImage imageAux = new BufferedImage(this.myImage.getWidth(), this.myImage.getHeight(), this.myImage.getType());
            // le quito los bordes discontinuos
            this.clip_border = !this.clip_border;
            // la imagen ya esta pintada por que se ha ejecutado el paintComponet ahora pinto las formas
            this.g2d = imageAux.createGraphics();
            this.paint(this.g2d);
            // le pongo los bordes discontinuos
            this.clip_border = !this.clip_border;

            this.myImage = imageAux;
        }
        return this.myImage;
    }

    /**
     * Con este método podemos asignar una imagen al un lienzo.
     *
     * @param img [in] De tipo BufferedImage son los datos en bruto de la
     * imagen.
     */
    public void setImage(BufferedImage img) {
        this.myImage = img;
        this.pos_image_width = (this.getParent().getWidth() - this.myImage.getWidth()) / 2;
        this.pos_image_heigth = (this.getParent().getHeight() - this.myImage.getHeight()) / 2;
        this.point_to_paint = new geoPoint(this.pos_image_width, this.pos_image_heigth);
        this.setPreferredSize(new Dimension(this.myImage.getWidth(), this.myImage.getHeight()));
        this.repaint();
    }

    /**
     * Con este método podemos cambiar el tipo de la imagen que tenemos en el
     * lienzo este método nos será útil por si queremos aplicar diferentes
     * filtros a la imagen que solo son permitidos por algunos tipos de imagen.
     *
     * @param ti [in] tipo al que queremos convertir la imagen.
     */
    public void setTypeImage(int ti) {
        this.myImage = Utils.convertImageToType(this.myImage, ti);
    }

    /**
     * Este método se encarga de devolver el valor del pixel en el lugar en el
     * que se encuentra el ratón.
     *
     * @param p[in] posición de la cual quiero saber el color.
     * @return tipo Array de enteros con el contenido de un pixel.
     *
     * @since nótese que el array contiene tantos componentes como tenga la
     * imagen que estamos observando.
     */
    public int[] getColorInPos(geoPoint p) {
        int[] res = new int[this.myImage.getColorModel().getNumComponents()];
        if (p.getPosX() > this.pos_image_width
                && p.getPosY() > this.pos_image_heigth
                && p.getPosX() < this.pos_image_width + this.myImage.getWidth()
                && p.getPosY() < this.pos_image_heigth + this.myImage.getHeight()) {
            this.myImage.getRaster().getPixel((int) (p.getPosX() - this.pos_image_width), (int) (p.getPosY() - this.pos_image_heigth), res);
        }
        return res;
    }

    /**
     * Con este método lo que conseguimos es redimensionar la imagen que le
     * pasamos por parámetros, teniendo en cuenta que la imagen sigue teniendo
     * el mismo tamaño que tenia antes pero se crean "huecos" blancos en los
     * espacios a los que no llega la imagen.
     *
     * Hay que tener en cuenta también que según el tipo de imagen que le
     * pasemos este método rellenara los "huecos" con el color blanco de fondo o
     * lo dejará transparente tal y como indica el tipo de la imagen.
     *
     * @param w [in] tipo entero que indica el nuevo ancho de la imagen
     * @param h [in] tipo entero que indica el nuevo alto de la imagen
     * @param trans [in] tipo boleano indica si queremos transparencia o no.
     */
    public void resizeImage(int w, int h, boolean trans) {
        if (this.myImage != null && w > 0 && h > 0) {
            BufferedImage imageAux;
            // le pongo el fondo blanco si o no
            if (trans) {
                imageAux = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            } else {
                imageAux = new BufferedImage(w, h, this.myImage.getType());
                myRectangle aux = new myRectangle(new geoPoint(), w, h, Color.WHITE, 0);
                aux.setFill(Color.WHITE);
                aux.drawShapeIn(imageAux.createGraphics());
            }
            // pinto la imagen sin importar la posición
            imageAux.createGraphics().drawImage(this.myImage, 0, 0, this);
            this.myImage = imageAux;
        } else {
            throw new UnsupportedOperationException("The size of the width and height are not correct or image equal null");
        }
        repaint();
    }

    /**
     * Con este método conseguiremos reescalar la imagen que le pasamos por
     * parámetros, osea que lo que vamos a realizar es "estirar la imagen" al
     * nuevo tamaño que se especifique por los parámetros w y h.
     *
     * @param w [in] tipo entero que indica el nuevo ancho de la imagen
     * @param h [in] tipo entero que indica el nuevo alto de la imagen
     */
    public void rescaleImage(int w, int h) {
        if (this.myImage != null && w > 0 && h > 0) {
            BufferedImage imageAux = new BufferedImage(w, h, this.myImage.getType());
            this.g2d = imageAux.createGraphics();
            this.g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            this.g2d.drawImage(this.myImage, 0, 0, w, h, this);
            this.myImage = imageAux;
        } else {
            throw new UnsupportedOperationException("The size of the width and height are not correct or image equal null");
        }
        repaint();
    }

    /**
     * Método sobrecargado que se encarga de pintar las imágenes en el lienzo.
     *
     * @param g [in] Esta será la zona gráfica donde se presentaran las
     * imágenes.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (this.myImage != null) {
            g.drawImage(this.myImage, (int) this.point_to_paint.getPosX(), (int) this.point_to_paint.getPosY(), this);
            // el lienzo2D no tiene por que tener un rectangulo que le marque el borde
            // por eso el borde de la imagen solo se pinta en el lienzo2Dimagen
            if (this.myVisiblePart != null) {
                myRectangleShape border_aux = (myRectangle) this.myVisiblePart;
                border_aux.setWidth(this.myImage.getWidth());
                border_aux.setHeigth(this.myImage.getHeight());
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
