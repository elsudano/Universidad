package sm.cdlt.multimedia;

import javax.sound.sampled.LineListener;
import sm.cdlt.utils.File;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 25-may-2016
 */
public class Multimedia {

    /**
     * Si queremos tener algún manejador concreto.
     */
    protected LineListener lineListener;
    /**
     * Almacena el fichero que estamos tratando.
     */
    protected File multimediaFile;
    /**
     * Almacena el tiempo que tiene el sonido que estamos tratando.
     */
    protected long clipTime;

    /**
     * Constructor por defecto con un parámetro.
     *
     * @param f [in] fichero el cual queremos tratar.
     */
    public Multimedia(final java.io.File f) {
        this.multimediaFile = new File(f.getAbsolutePath());
    }

    /**
     * Constructor por defecto con un parámetro, que nos permite usar una string.
     *
     * @param f [in] fichero el cual queremos tratar.
     */
    public Multimedia(final String f) {
        this.multimediaFile = new File(f);
    }
    
    /**
     * Nos devuelve el fichero que estamos tratando.
     *
     * @return Tipo File con el fichero tratado.
     */
    public File getSoundFile() {
        return this.multimediaFile;
    }

    /**
     * Método que se encarga de mostrar quien es el que se encarga de gestionar
     * los eventos del objeto.
     *
     * @return tipo LineListener quien se encarga de manejar los eventos.
     */
    public LineListener getListener() {
        return this.lineListener;
    }

    /**
     * Desarrollado por: <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la
     * Torre</a>
     *
     * Método que se encarga de asignar la clase que se encargará de gestionar
     * los eventos de los diferentes objetos.
     *
     * @param l [in] tipo LineListener que se encarga de escuchar los eventos.
     */
    public void setListener(LineListener l) {
        this.lineListener = l;
    }

    /**
     * Desarrollado por: <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la
     * Torre</a>
     *
     * Método que se encarga de extraer la cadena que corresponde al nombre del
     * fichero SOLAMENTE sin la extensión.
     *
     * @return tipo String que SOLO muestra el nombre del fichero sin la
     * extensión.
     */
    public String getName() {
        return this.multimediaFile.getFriendlyName();
    }

    /**
     * Desarrollado por: <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la
     * Torre</a>
     *
     * Método que se encarga de extraer la cadena que corresponde a la extensión
     * del fichero SOLAMENTE sin el nombre.
     *
     * @return tipo String que SOLO muestra la extensión del fichero.
     */
    public String getExt() {
        return this.multimediaFile.getExt();
    }
}
