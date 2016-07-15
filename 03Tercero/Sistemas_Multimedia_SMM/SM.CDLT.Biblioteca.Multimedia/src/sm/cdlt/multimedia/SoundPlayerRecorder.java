package sm.cdlt.multimedia;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
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
public class SoundPlayerRecorder extends Multimedia implements Player, Recorder {

    /**
     * Codificación que tendrá el fichero
     */
    private static final AudioFormat.Encoding DEFAULT_CODING;
    /**
     * Numero de muestras del fichero
     */
    private static final float DEFAULT_SAMPLE_RATE = 44100.0f;
    /**
     * Numero de bits del fichero
     */
    private static final int DEFAULT_SAMPLE_SIZE = 16;
    /**
     * Numero de canales del fichero (2 stereo, 1 mono)
     */
    private static final int DEFAULT_NUMCHANELS = 2;
    /**
     * Numero de muestras por segundo en el frame
     */
    private static final float DEFAULT_FRAME_RATE = 44100.0f;
    /**
     * Numero de bits por frame
     */
    private static final int DEFAULT_FRAME_SIZE = 4;
    /**
     * Indica de que forma se almacenara una sola muestra, verdadero en BIG
     * ENDIAN falso en LITTLE ENDIAN
     */
    private static final boolean DEFAULT_BIGENDIAN = false;
    /**
     * Tipo de fichero por defecto
     */
    private static final AudioFileFormat.Type DEFAULT_FILE_FORMAT;
    /**
     * Tamaño del buffer de lectura de datos
     */
    private static final int EXTERNAL_BUFFER_SIZE = 128000;
    /**
     * Linea que servirá para grabar el sonido.
     */
    private DataLine line;

    /**
     * Constructor de un parámetro.
     *
     * @param f [in] fichero en el que se guardará o leerá el sonido.
     */
    public SoundPlayerRecorder(final java.io.File f) {
        super(f);
        this.line = null;
    }

    /**
     * Desarrollado por: <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la
     * Torre</a>
     *
     * Constructor para el clip, solo necesita el path del fichero de entrada.
     *
     * @param p [in] Tipo String con el path del fichero que vamos a tratar.
     */
    public SoundPlayerRecorder(final String p) {
        super(p);
        this.line = null;
    }

    /**
     * Método encargado de poner a reproducir el fichero.
     */
    @Override
    public void play() {
        final Thread thread = new Thread() {
            @Override
            public void run() {
                SoundPlayerRecorder.this.startPlay();
            }
        };
        thread.start();
    }

    /**
     * Método encargado de poner a grabar al sistema.
     */
    @Override
    public void record() {
        final Thread thread = new Thread() {
            @Override
            public void run() {
                SoundPlayerRecorder.this.startRecord();
            }
        };
        thread.start();
    }

    /**
     * Método encargado de parar el proceso actual.
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
        boolean aux = false;
        if (this.line != null) {
            aux = this.line.isRunning();
        }
        return aux;
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
        int nBytesRead = 0;
        final byte[] abData = new byte[SoundPlayerRecorder.EXTERNAL_BUFFER_SIZE];
        try {
            audioIS = AudioSystem.getAudioInputStream(this.multimediaFile);
            audioF = audioIS.getFormat();
            info = new DataLine.Info(SourceDataLine.class, audioF);
            (this.line = (sourceDL = (SourceDataLine) AudioSystem.getLine(info))).addLineListener(this.lineListener);
            sourceDL.open(audioF);
            sourceDL.start();
        } catch (IOException ex) {
            Logger.getLogger(SoundPlayerRecorder.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Problemas con la lectura/escritura del fichero: " + ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(SoundPlayerRecorder.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("La linea de salida no esta disponible: " + ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(SoundPlayerRecorder.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("El tipo de fichero no esta soportado: " + ex);
        }
        while (nBytesRead != -1) {
            try {
                if (audioIS != null && sourceDL != null) {
                    nBytesRead = audioIS.read(abData, 0, abData.length);
                    if (nBytesRead < 0) {
                        continue;
                    }
                    sourceDL.write(abData, 0, nBytesRead);
                }
            } catch (Exception ex) {
                Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Error al leer los datos: " + ex);
            }
        }
        sourceDL.drain();
        sourceDL.close();
    }

    /**
     * Modificado por: <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la
     * Torre</a>
     *
     * Método que se encarga de gestionar la creación de todo lo necesario para
     * poder lanzar la grabación en una hebra.
     */
    private void startRecord() {
        final AudioFormat audioF = new AudioFormat(
                SoundPlayerRecorder.DEFAULT_CODING,
                SoundPlayerRecorder.DEFAULT_SAMPLE_RATE,
                SoundPlayerRecorder.DEFAULT_SAMPLE_SIZE,
                SoundPlayerRecorder.DEFAULT_NUMCHANELS,
                SoundPlayerRecorder.DEFAULT_FRAME_SIZE,
                SoundPlayerRecorder.DEFAULT_FRAME_RATE,
                SoundPlayerRecorder.DEFAULT_BIGENDIAN
        );
        final DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioF);
        AudioInputStream audioIS;
        AudioFileFormat.Type targetT;
        try {
            this.line = (TargetDataLine) AudioSystem.getLine(info);
            this.line.addLineListener(this.lineListener);
            ((TargetDataLine) this.line).open(audioF);
            ((TargetDataLine) this.line).start();
            audioIS = new AudioInputStream((TargetDataLine) this.line);
            targetT = SoundPlayerRecorder.DEFAULT_FILE_FORMAT;
            AudioSystem.write(audioIS, targetT, this.multimediaFile);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("La linea no esta disponible para recibir datos: " + ex);
        } catch (IOException ex) {
            Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Error al intentar guardar el fichero: " + ex);
        }
    }

    static {
        DEFAULT_CODING = AudioFormat.Encoding.PCM_SIGNED;
        DEFAULT_FILE_FORMAT = AudioFileFormat.Type.WAVE;
    }
}
