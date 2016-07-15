package sm.cdlt.multimedia;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;

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
public class SoundRecorder extends Multimedia implements Recorder {

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
     * Linea que servirá para grabar el sonido.
     */
    private DataLine line;

    /**
     * Constructor de un parámetro.
     *
     * @param f [in] fichero en el que se guardara la grabación.
     */
    public SoundRecorder(final java.io.File f) {
        super(f);
        this.lineListener = null;
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
    public SoundRecorder(final String p) {
        super(p);
        this.lineListener = null;
        this.line = null;
    }

    @Override
    public void record() {
        final Thread thread = new Thread() {
            @Override
            public void run() {
                SoundRecorder.this.startRecord();
            }
        };
        thread.start();
    }

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
     * poder lanzar la grabación en una hebra.
     */
    private void startRecord() {
        final AudioFormat audioF = new AudioFormat(
                SoundRecorder.DEFAULT_CODING,
                SoundRecorder.DEFAULT_SAMPLE_RATE,
                SoundRecorder.DEFAULT_SAMPLE_SIZE,
                SoundRecorder.DEFAULT_NUMCHANELS,
                SoundRecorder.DEFAULT_FRAME_SIZE,
                SoundRecorder.DEFAULT_FRAME_RATE,
                SoundRecorder.DEFAULT_BIGENDIAN
        );
        final DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioF);
        TargetDataLine targetDL = null;
        final AudioInputStream audioIS = new AudioInputStream(targetDL);
        final AudioFileFormat.Type targetT = SoundRecorder.DEFAULT_FILE_FORMAT;

        try {
            (this.line = (targetDL = (TargetDataLine) AudioSystem.getLine(info))).addLineListener(this.lineListener);
            targetDL.open(audioF);
            targetDL.start();
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
