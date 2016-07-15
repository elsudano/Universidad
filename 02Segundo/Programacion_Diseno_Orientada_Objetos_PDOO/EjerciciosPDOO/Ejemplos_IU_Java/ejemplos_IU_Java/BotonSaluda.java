/*
 * Ejemplo con un botón que saluda cuando se pulsa
 */
package ejemplos_IU_Java;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author mjfortiz
 */
public class BotonSaluda {
 

public static void main (String args[]){

JFrame ventana = new JFrame("Ventana para saludar");
JPanel cont= (JPanel)ventana.getContentPane();
final JLabel label = new JLabel("Aquí iría el saludo");
JButton boton= new JButton("saludar");
cont.setLayout(new FlowLayout());        
cont.add(boton);
ActionListener aclis = new ActionListener() {
            
            @Override
            public void actionPerformed( ActionEvent evento ) {
                label.setText("Buenas Tardes Amigos");
                
            }
        };
boton.addActionListener( aclis );
cont.add(label);
ventana.setSize( 300,100);
ventana.setLocationRelativeTo(null);
ventana.setVisible( true );


}
    
}

