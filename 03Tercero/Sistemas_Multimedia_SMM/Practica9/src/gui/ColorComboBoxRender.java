package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de al Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de al Torre</a>
 * created on 17-abr-2016
 */
public class ColorComboBoxRender extends JPanel implements ListCellRenderer {

    private final JLabel label_color;
    private final JLabel text;
    private Color color;

    public ColorComboBoxRender() {
        label_color = new JLabel();
        text = new JLabel();
        color = Color.BLACK;

        this.setMaximumSize(new java.awt.Dimension(120, 20));
        this.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 1, 1));

        label_color.setBackground(color);
        label_color.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        label_color.setMaximumSize(new java.awt.Dimension(20, 20));
        label_color.setOpaque(true);
        label_color.setPreferredSize(new java.awt.Dimension(20, 20));
        this.add(label_color);

//        text.setText(color.toString());
//        text.setMaximumSize(new java.awt.Dimension(90, 20));
//        text.setMinimumSize(new java.awt.Dimension(90, 20));
//        text.setPreferredSize(new java.awt.Dimension(90, 20));
//        this.add(text);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        this.color = (Color) value;

        // ponemos el fondo del label del color que hemos elejido
        this.label_color.setBackground(this.color);

        return this;
    }

}
