package ejemplos_IU_Java;
/**
 * Ejemplo que muestra el uso de las barras de progreso y barras de
 * desplazamiento.
 * Desplazando la barra de deslizamiento que aparece en la zona inferior, 
 * la barra de progreso de la parte media se desplazará de forma semejante 
 * y en la misma proporción. En la parte inferior se visualiza el texto con la
 * cantidad seleccionada en la barra
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

public class BarraDes extends JPanel {
    JProgressBar barraProg = new JProgressBar(); //barra de progreso, azúl
    JSlider barraSlid = new JSlider( JSlider.HORIZONTAL,0,100,60 ); //barra que se desliza
     JTextField texto= new  JTextField();

    public BarraDes() {
        setLayout( new GridLayout(3,1) );
        add( barraProg );
        barraSlid.setValue( 0 );
        barraSlid.setPaintTicks( true );
        barraSlid.setMinorTickSpacing( 5 );
        barraSlid.setLabelTable( barraSlid.createStandardLabels( 20 ) );
        barraSlid.setPaintLabels( true );
        barraSlid.setBorder( new TitledBorder("Para desplazar") );
        barraSlid.addChangeListener( new ChangeListener() {
            public void stateChanged( ChangeEvent evt ) {
                barraProg.setValue(barraSlid.getValue() );
                texto.setText(Integer.toString(barraSlid.getValue()));
            }
        } );
        add( barraSlid );
        add( texto );
        //posibles cambios: cambiar formato de presentación de las barras
    }


    public static void main( String args[] ) {
        JFrame frame = new JFrame( "Barras de progreso y deslizable" );
        frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
        frame.getContentPane().add( new BarraDes(),BorderLayout.CENTER );
        frame.pack();
        frame.setSize( 300,300 );
        frame.setVisible( true );
    }
}
