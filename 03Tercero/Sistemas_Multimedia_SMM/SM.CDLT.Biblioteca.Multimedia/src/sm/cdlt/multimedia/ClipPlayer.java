package sm.cdlt.multimedia;

import javax.sound.sampled.Control;
import javax.sound.sampled.Line;
import javax.sound.sampled.AudioSystem;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Esta clase ha sido creada por el profesor:
 * <a href="mailto:jesus@decsai.ugr.es">Jesús Chamorro Martínez</a>
 * para la Asignatura (SMM) que imparte en la Universidad de Granada
 * <a href="http://etsiit.ugr.es/">ETSIIT</a>
 *
 * Ha sido ampliada para poder cubrir las necesidades de acceso a diferentes
 * parámetros, que no estaban contempladas en un principio.
 *
 * Para diferenciar los métodos que se han añadido en las descripciones de los
 * mismo se utilizara una reseña al desarrollador que lo implemento.
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 25-may-2016
 */
public class ClipPlayer extends Multimedia implements Player {

    /**
     * Será el sonido en cuestión.
     */
    private Clip sound;
    /**
     * Variable que almacena el estado de reproducción del objeto
     */
    private boolean inPause;

    /**
     * Constructor para el clip, solo necesita el fichero de entrada.
     *
     * @param f [in] Tipo File con el fichero que vamos a tratar.
     */
    public ClipPlayer(final java.io.File f) {
        super(f);
        this.lineListener = null;
        this.clipTime = 0L;
        this.inPause = false;
        this.sound = null;
    }

    /**
     * Desarrollado por: <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
     *
     * Constructor para el clip, solo necesita el path del fichero de entrada.
     *
     * @param p [in] Tipo String con el path del fichero que vamos a tratar.
     */
    public ClipPlayer(final String p) {
        super(p);
        try {
            this.lineListener = null;
            this.clipTime = 0L;
            this.sound = AudioSystem.getClip(null);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(ClipPlayer.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("ClipPlayer constructor: " + ex);
        }
    }

    /**
     * Método con el que ponemos a escuchar el sonido.
     */
    @Override
    public void play() {
        try {
            if (this.sound != null && this.clipTime != 0L) {
                this.sound.setMicrosecondPosition(this.clipTime);
                this.sound.start();
            } else {
                this.sound.addLineListener(this.getListener());
                this.sound.open(AudioSystem.getAudioInputStream(this.multimediaFile.getFile()));
                this.sound.start();
            }
            this.inPause = false;
        } catch (IOException ex) {
            Logger.getLogger(ClipPlayer.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Problemas con la lectura/escritura del fichero: " + ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(ClipPlayer.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("La linea de salida no esta disponible: " + ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(ClipPlayer.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("El tipo de fichero no esta soportado: " + ex);
        }
    }

    /**
     * Método con el cual paramos y cerramos el sonido.
     */
    @Override
    public void stop() {
        if (this.sound != null) {
            this.clipTime = 0L;
            this.inPause = false;
            this.sound.close();
        }
    }

    /**
     * Método con el cual SOLO paramos el sonido.
     */
    public void pause() {
        if (this.sound != null) {
            this.sound.stop();
            this.inPause = true;
            this.clipTime = this.sound.getMicrosecondPosition();
        }
    }

    /**
     * Con esto podemos saber si se esta escuchando el fichero multimedia.
     *
     * @return verdadero si se esta escuchando, falso en caso contrario.
     */
    public boolean isWorking() {
        return this.sound.isRunning();
    }
    
    /**
     * Con esto podemos saber si el objeto multimedia esta o no en pausa.
     *
     * @return verdadero si se esta en pausa, falso en caso contrario.
     */
    public boolean inPause() {
        return this.inPause;
    }

    /**
     * Método para obtener el clip de sonido que estamos tratando.
     *
     * @return Tipo Clip con el sonido actual.
     */
    public Clip getClip() {
        return this.sound;
    }

    /**
     * Muestra información relevante en la consola sobre el sonido actual.
     */
    public void printInfo() {
        final Line.Info infoLine = this.sound.getLineInfo();
        System.out.println("Informaci\u00f3n de l\u00ednea: " + infoLine.toString());
        final Control[] c = this.sound.getControls();
        System.out.println("Controles de la l\u00ednea: ");
        for (int i = 0; i < c.length; ++i) {
            System.out.println("   [" + i + "] " + c[i].getType() + " : " + c[i]);
        }
        System.out.println();
    }
}
