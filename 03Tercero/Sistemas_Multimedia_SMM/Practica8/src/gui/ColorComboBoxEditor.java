package gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxEditor;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de al Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de al Torre</a>
 * created on 17-abr-2016
 */
public class ColorComboBoxEditor extends BasicComboBoxEditor {

    private final JPanel content;
    private final JLabel label_color;
    private final JLabel text;
    private Color color;

    public ColorComboBoxEditor() {
        content = new JPanel();
        label_color = new JLabel();
        text = new JLabel();
        color = Color.BLACK;

        content.setMaximumSize(new java.awt.Dimension(120, 20));
        content.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 1, 1));

        label_color.setBackground(color);
        label_color.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        label_color.setMaximumSize(new java.awt.Dimension(20, 20));
        label_color.setOpaque(true);
        label_color.setPreferredSize(new java.awt.Dimension(20, 20));
        content.add(label_color);

//        text.setText(color.toString());
//        text.setMaximumSize(new java.awt.Dimension(90, 20));
//        text.setMinimumSize(new java.awt.Dimension(90, 20));
//        text.setPreferredSize(new java.awt.Dimension(90, 20));
//        content.add(text);
    }

    @Override
    public Component getEditorComponent() {
        return this.content;
    }

    @Override
    public Object getItem() {
        return this.color;
    }

    @Override
    public void setItem(Object item) {
        if (item == null) {
            return;
        }
        this.color = (Color) item;
        this.label_color.setBackground((Color) item);
        this.text.setText(((Color) item).toString());
    }
}
