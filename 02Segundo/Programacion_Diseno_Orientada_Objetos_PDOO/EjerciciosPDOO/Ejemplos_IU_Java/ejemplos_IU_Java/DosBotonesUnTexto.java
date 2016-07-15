/*
 * Ejemplo de ventana con dos botones y un texto que cambia segúne el botón pulsado
 */
package ejemplos_IU_Java;

/**
 *
 * @author mjfortiz
 */
  
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class DosBotonesUnTexto extends JPanel {
    JLabel label= new JLabel("Probando botones");
    JButton boton1 = new JButton( "boton 1" );
    JButton boton2 = new JButton( "boton 2" );
    JTextField texto = new JTextField( 20 );
    

    public DosBotonesUnTexto() {
      
        add(label);
        ActionListener al = new ActionListener() {
            
            @Override
            public void actionPerformed( ActionEvent evento ) {
                String nombre = ( (JButton)evento.getSource()).getText();
                texto.setText( nombre+" Pulsado" );
            }
        };
        boton1.addActionListener( al );
        add( boton1 );
        boton2.addActionListener( al );
        add( boton2 );
        add( texto );
        
        //posibles mejoras: asociar imágenes o iconos a los botones
    }

    public static void main( String args[] ) {
        JFrame ventana = new JFrame( "Probando botones y texto" );

        DosBotonesUnTexto dbut= new DosBotonesUnTexto();
        dbut.setBorder(new MatteBorder(5,5,60,60,Color.red));  //para ver como se pone borde a un panel
        ventana.getContentPane().add( dbut );
       
        ventana.setSize( 500,300);
       
        ventana.setVisible( true );
    }
}
    

