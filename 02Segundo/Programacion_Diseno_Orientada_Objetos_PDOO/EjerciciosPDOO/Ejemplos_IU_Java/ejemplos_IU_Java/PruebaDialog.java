/*
 * A diferencia de JFrame, JDialog es una ventana que no puede redimensionarse pero se basa en un JFrame
 */
package ejemplos_IU_Java;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author mjfortiz
 */
public class PruebaDialog extends JDialog {

    static JLabel mensaje;
    

    public PruebaDialog(JFrame unFrame, boolean modo) {
        super(unFrame, modo);
        mensaje = new JLabel();
        mensaje.setText("texto dentro de la ventana");
        
//        this.addWindowListener(new WindowAdapter() {
//
//                    @Override
//                    public void windowClosing(WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
    }
        
        
  public static void main( String args[] ) {
        
        JFrame ventana = new JFrame( "Probando JDialog" );
      
        PruebaDialog pru= new PruebaDialog(ventana, true);
        
        //probar estos y luego los tres siguientes para ver la diferencia
        pru.getContentPane().add( mensaje );
        pru.setSize(500,300); 
        pru.setVisible(true);
      
      
//        ventana.getContentPane().add( mensaje );
//        ventana.setSize(500,300);
//        ventana.setVisible(true);

    }
}

    
    

