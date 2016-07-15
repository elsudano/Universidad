package sm.cdlt.multimedia;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.AudioSystem;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.DataLine;
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
 * created on 27-may-2016
 */
public class SoundPlayer extends Multimedia implements Player {

    /**
     * Tamaño del buffer de lectura de datos
     */
    private static final int EXTERNAL_BUFFER_SIZE = 128000;
    /**
     * Esta es la variable que almacena la linea de entrada
     */
    private DataLine line;

    /**
     * Constructor de un parámetro tipo java.io.File
     *
     * @param f [in] el fichero que se va a reproducir.
     */
    public SoundPlayer(final java.io.File f) {
        super(f);
        this.lineListener = null;
        this.clipTime = 0L;
        this.line = null;
    }

    /**
     * Constructor para el clip, solo necesita el path del fichero de entrada.
     *
     * @param p [in] Tipo String con el path del fichero que vamos a tratar.
     */
    public SoundPlayer(final String p) {
        super(p);
        this.lineListener = null;
        this.clipTime = 0L;
        this.line = null;
    }

    /**
     * Método que nos sirve para poner a reproducir el audio.
     */
    @Override
    public void play() {
        final Thread thread = new Thread() {
            @Override
            public void run() {
                SoundPlayer.this.startPlay();
            }
        };
        thread.start();
    }

    /**
     * Método que sirve para parar la reproducción del audio.
     */
    @Override
    public void stop() {
        if (this.line != null) {
            this.line.stop();
            this.line.close();
        }
    }

    /**
     * Con esto podemos saber si se esta escuchando el fichero multimedia.
     *
     * @return verdadero si se esta escuchando, falso en caso contrario.
     */
    public boolean isWorking() {
        return this.line.isRunning();
    }

    /**
     * Con este método dejamos de recibir los datos que nos llegan a la linea
     * pero no almacenamos el punto en el que ha dejado de recibir datos
     * simplemente ya no esta a la "escucha" Osea se para pero no se cierra.
     */
    public void pause() {
        if (this.line != null) {
            this.line.stop();
        }
    }

    /**
     * Con este método volvemos a capturar los datos que llegan a la linea. Como
     * no esta cerrada podemos volver a recibir.
     */
    public void resume() {
        if (this.line != null) {
            this.line.start();
        }
    }

    /**
     * Modificado por: <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la
     * Torre</a>
     * 
     * Método que se encarga de gestionar la creación de todo lo necesario para
     * poder lanzar la reproducción en una hebra.
     */
    private void startPlay() {
        AudioInputStream audioIS = null;
        AudioFormat audioF;
        DataLine.Info info;
        SourceDataLine sourceDL = null;
        int numBytesRead = 0;
        final byte[] bufferData = new byte[SoundPlayer.EXTERNAL_BUFFER_SIZE];

        try {
            audioIS = AudioSystem.getAudioInputStream(this.multimediaFile);
            audioF = audioIS.getFormat();
            info = new DataLine.Info(SourceDataLine.class, audioF);
            (this.line = (sourceDL = (SourceDataLine) AudioSystem.getLine(info))).addLineListener(this.lineListener);
            sourceDL.open(audioF);
            sourceDL.start();
        } catch (IOException ex) {
            Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Problemas con la lectura/escritura del fichero: " + ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("La linea de salida no esta disponible: " + ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("El tipo de fichero no esta soportado: " + ex);
        }
        while (numBytesRead != -1) {
            try {
                if (audioIS != null && sourceDL != null) {
                    numBytesRead = audioIS.read(bufferData, 0, bufferData.length);
                    if (numBytesRead < 0) {
                        continue;
                    }
                    sourceDL.write(bufferData, 0, numBytesRead);
                }
            } catch (Exception ex) {
                Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Error al leer los datos: " + ex);
            }
        }
        sourceDL.drain();
        sourceDL.close();
    }
}
