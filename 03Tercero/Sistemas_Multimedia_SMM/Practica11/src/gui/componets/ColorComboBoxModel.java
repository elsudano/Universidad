package gui.componets;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de al Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de al Torre</a>
 * created on 17-abr-2016
 */
public class ColorComboBoxModel extends AbstractListModel implements ComboBoxModel {

    /**
     * Esta es la lista de colores por defecto que queremos que salga en el combobox.
     */
    Color[] colors = {Color.BLACK, Color.WHITE, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW};
    /**
     * Lista que tendrá los "cuadraditos" con los colores.
     */
    private final ArrayList<Color> myColors = new ArrayList<Color>();
    /**
     * Este será el color que se muestre y se escoja en el combobox.
     */
    private Color color;

    /**
     * Constructor sin parámetros.
     */
    public ColorComboBoxModel() {
        myColors.addAll(Arrays.asList(colors));
    }

    /**
     * Nos servirá para poder añadir mas colores al combobox.
     * 
     * @param c [in] Será el color que queremos añadir.
     */
    public void addElement(Color c) {
        this.myColors.add(c);
    }

    /**
     * Este método devuelve el tamaño que tiene nuestro modelo.
     *
     * @return entero con la cantidad de opciones.
     */
    @Override
    public int getSize() {
        return this.myColors.size();
    }

    /**
     * Con este método devolvemos el JLabel que marca el parametro de indice.
     *
     * @param index este será el indice que este seleccionado en el combobox.
     * @return Objeto de tipo Jlabel que tiene de fondo el color especificado.
     */
    @Override
    public Object getElementAt(int index) {
        return (Color) this.myColors.get(index);
    }

    /**
     * Con este método estamos asignando a la variable de clase el valor
     * seleccionado en el combobox.
     *
     * @param anItem Objeto seleccionado.
     */
    @Override
    public void setSelectedItem(Object anItem) {
        this.color = (Color) anItem;
    }

    /**
     * Con este método devolvemos el JLabel con el color seleccionado.
     *
     * @return Devolvemos el objeto seleccionado, sea el que sea.
     */
    @Override
    public Object getSelectedItem() {
        return this.color;
    }
}
