package sm.cdlt.util;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 25-may-2016
 */
public class File extends java.io.File {

    /**
     * Variable que almacena la extensión del fichero.
     */
    private String ext;
    /**
     * Variable que almacena solo el nombre del fichero.
     */
    private String frienlyName;

    /**
     * Constructor con la cadena del fichero
     *
     * @param pathname [in] de tipo String sera la ruta y el nombre del fichero
     * en forma de cadena de texto
     */
    public File(String pathname) {
        super(pathname);
        int i = this.getName().lastIndexOf('.');
        if (i > 0) {
            this.ext = this.getName().substring(i + 1).toLowerCase();
            this.frienlyName = this.getName().substring(0, i).toLowerCase();
        }
    }

    /**
     * Constructor con la dirección URI del fichero.
     *
     * @param uri [in] Tipo URI con la ruta del fichero.
     */
    public File(java.net.URI uri) {
        super(uri);
        int i = this.getName().lastIndexOf('.');
        if (i > 0) {
            this.ext = this.getName().substring(i + 1).toLowerCase();
            this.frienlyName = this.getName().substring(0, i).toLowerCase();
        }
    }

    /**
     * Devolvemos el fichero.
     * 
     * @return Tipo File con el fichero.
     */
    public File getFile(){
        return this;
    }
    
    /**
     * Simplemente devuelve una cadena con SOLO el nombre del fichero.
     *
     * @return String con el nombre del fichero.
     */
    public String getFriendlyName() {
        return this.frienlyName;
    }

    /**
     * Simplemente devuelve una cadena con la extensión del fichero.
     *
     * @return String con la extensión del fichero.
     */
    public String getExt() {
        return this.ext;
    }

}
