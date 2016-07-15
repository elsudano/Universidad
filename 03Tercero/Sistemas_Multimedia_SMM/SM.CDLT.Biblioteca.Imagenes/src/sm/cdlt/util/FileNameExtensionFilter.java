package sm.cdlt.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 25-may-2016
 */
public class FileNameExtensionFilter extends javax.swing.filechooser.FileFilter {

    // Description of this filter.
    private final String description;
    // Known extensions.
    private final String[] extensions;
    // para buscar mas rapidamente las extensiones
    private final Set<String> exts;

    /**
     * Creates a {@code FileNameExtensionFilter} with the specified description
     * and file name extensions. The returned {@code FileNameExtensionFilter}
     * will accept all directories and any file with a file name extension
     * contained in {@code extensions}.
     *
     * @param description textual description for the filter, may be
     * {@code null}
     * @param extensions the accepted file name extensions
     * @throws IllegalArgumentException if extensions is {@code null}, empty,
     * contains {@code null}, or contains an empty string
     * @see #accept
     */
    public FileNameExtensionFilter(String description, String... extensions) {
        if (extensions == null || extensions.length == 0) {
            throw new IllegalArgumentException("Extensions must be non-null and not empty");
        }
        this.description = description;
        this.extensions = new String[extensions.length];
        this.exts = new HashSet<String>();
        for (int i = 0; i < extensions.length; i++) {
            if (extensions[i] == null || extensions[i].length() == 0) {
                throw new IllegalArgumentException("Each extension must be non-null and not empty");
            }
            this.extensions[i] = extensions[i];
            this.exts.addAll(Arrays.asList(this.extensions[i].toLowerCase(java.util.Locale.ENGLISH)));
        }
    }

    /**
     * Tests the specified file, returning true if the file is accepted, false
     * otherwise. True is returned if the extension matches one of the file name
     * extensions of this {@code FileFilter}, or the file is a directory.
     *
     * @param f the {@code File} to test
     * @return true if the file is to be accepted, false otherwise
     */
    @Override
    public boolean accept(java.io.File f) {
        if (f != null) {
            if (f.isDirectory()) {
                return true;
            }
            // NOTE: we tested implementations using Maps, binary search
            // on a sorted list and this implementation. All implementations
            // provided roughly the same speed, most likely because of
            // overhead associated with java.io.File. Therefor we've stuck
            // with the simple lightweight approach.
            String fileName = f.getName();
            int i = fileName.lastIndexOf('.');
            if (i > 0 && i < fileName.length() - 1) {
                String desiredExtension = fileName.substring(i + 1).toLowerCase(java.util.Locale.ENGLISH);
                for (String extension : this.extensions) {
                    if (desiredExtension.equals(extension.toLowerCase(java.util.Locale.ENGLISH))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Comprueba si una extensión que se le pasa por parametros esta en el filtro.
     * 
     * @param s [in] String con la extensión que queremos comprobar.
     * @return verdadero si la extensión existe.
     */
    public boolean contains(String s){
        return this.exts.contains(s);
    }
    
    /**
     * The description of this filter. For example: "JPG and GIF Images."
     *
     * @return the description of this filter
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the set of file name extensions files are tested against.
     *
     * @return the set of file name extensions files are tested against
     */
    public String[] getExtensions() {
        String[] result = new String[this.extensions.length];
        System.arraycopy(this.extensions, 0, result, 0, this.extensions.length);
        return result;
    }

    /**
     * Returns the set of file name extensions files in lowercase
     *
     * @return the set of file name extensions files in lowercase
     */
    public String[] getExtLowerCase() {
        String[] result = new String[this.extensions.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = this.extensions[i].toLowerCase(java.util.Locale.ENGLISH);
        }
        return result;
    }
}
