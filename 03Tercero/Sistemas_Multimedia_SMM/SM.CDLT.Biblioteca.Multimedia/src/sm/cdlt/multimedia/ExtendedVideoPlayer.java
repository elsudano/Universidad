package sm.cdlt.multimedia;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.Buffer;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 29-may-2016
 */
public class ExtendedVideoPlayer extends Multimedia implements Player {

    /**
     * Variable que almacena el player completo
     */
    protected javax.media.Player player;
    /**
     * Variable que se encarga de crear el objeto para la captura de imágenes.
     */
    protected FrameGrabbingControl frameGrabber;
    /**
     * Variable que almacena la parte que muestra el vídeo en pantalla.
     */
    protected Component visual_component;
    /**
     * Variable que almacena los controles del vídeo
     */
    protected Component control_component;

    /**
     * Constructor por defecto, para el player.
     *
     * @param f [in] Tipo fichero con los datos que queremos reproducir.
     */
    private ExtendedVideoPlayer(File f) {
        super(f);
        try {
            MediaLocator media_locator = new MediaLocator("file:" + f.getAbsolutePath());
            this.player = Manager.createRealizedPlayer(media_locator);
            this.visual_component = player.getVisualComponent();
            this.control_component = player.getControlPanelComponent();
            this.frameGrabber = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
        } catch (IOException ex) {
            Logger.getLogger(ExtendedVideoPlayer.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Problemas con la lectura/escritura del fichero: " + ex);
        } catch (NoPlayerException ex) {
            Logger.getLogger(ExtendedVideoPlayer.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("El reproductor no se encuentra disponible: " + ex);
        } catch (CannotRealizeException ex) {
            Logger.getLogger(ExtendedVideoPlayer.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("No se puede realizar la Exepcion???: " + ex);
        }
    }

    /**
     * Método getInstance que nos sirve para pedir una instancia del player
     * desde cualquier lugar de la aplicación, siendo este siempre el mismo.
     *
     * @param f [in] Fichero con el path completo de los datos a reproducir.
     * @return tipo VideoPlayer con todo lo necesario para poder reproducir.
     */
    public static ExtendedVideoPlayer getInstance(File f) {
        ExtendedVideoPlayer v = new ExtendedVideoPlayer(f);
        if (v.player != null) {
            return v;
        } else {
            return null;
        }
    }

    /**
     * Método getInstance que nos sirve para pedir una instancia del player
     * desde cualquier lugar de la aplicación, siendo este siempre el mismo.
     *
     * @param p [in] Cadena de texto con el path completo del fichero a
     * reproducir.
     * @return tipo VideoPlayer con todo lo necesario para poder reproducir.
     */
    public static ExtendedVideoPlayer getInstance(String p) {
        ExtendedVideoPlayer v = new ExtendedVideoPlayer(new File(p));
        if (v.player != null) {
            return v;
        } else {
            return null;
        }
    }

    /**
     * Con este método podemos obtener el componente visual donde se reproduce
     * el fichero multimedia.
     *
     * @return devuelve un componente donde se reproduce el fichero multimedia.
     */
    public Component getVisual() {
        return this.visual_component;
    }

    /**
     * Con este método podemos obtener el componente de controles donde
     * pondremos manejar la reproducción del fichero multimedia.
     *
     * @return devuelve el componente con los controles para manipular el
     * fichero multimedia.
     */
    public Component getControl() {
        return this.control_component;
    }

    /**
     * Método con el cual podemos tomar una captura de lo que se esta viendo en
     * el vídeo.
     *
     * @return objeto BufferedImage con la imagen capturada.
     */
    public BufferedImage takePhoto() {
        Buffer buf = this.frameGrabber.grabFrame();
        // Convertir un buffer en una imagen para luego pasarlo a un bufferimage.
        Image img = (new BufferToImage((VideoFormat) buf.getFormat()).createImage(buf));
        return (BufferedImage)img;
    }

    /**
     * Método que se encarga de poner a reproducir el fichero multimedia que
     * contenga el player.
     */
    @Override
    public void play() {
        this.player.start();
    }

    /**
     * Método que se encarga de detener la reproducción del fichero multimedia
     * que contenga el player.
     */
    @Override
    public void stop() {
        this.player.stop();
    }

    /**
     * Método que nos informa si el fichero multimedia esta en reproducción o
     * detenido.
     *
     * @return verdadero si esta en reproducción, falso en caso contrario.
     */
    public boolean isWorking() {
        //@TODO To change body of generated methods, choose Tools | Templates.
        throw new UnsupportedOperationException("Not implemented yet. Method isWorking to VideoPlayer");
    }
}
