import gui.VentanaInterna;
import gui.VentanaPrincipal;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de al Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de al Torre</a>
 * created on 10-abr-2016
 */
public class Main {

    @SuppressWarnings("Convert2Lambda")
    public static void main(String args[]) {
        /* Set the Nimbus look and feel
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        VentanaPrincipal myApp = new VentanaPrincipal();
        // tamaño de la imagen al iniciar la aplicacion
        Dimension dim = new Dimension(250, 250);
        // creamos la ventana interna para colocarla donde queremos
        VentanaInterna myVi = new VentanaInterna(myApp, dim, BufferedImage.TYPE_INT_RGB);
        myVi.setTitle("(Sin Título 1)");
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // pongo la aplicación visible
                myApp.setVisible(true);
                // situo la primera ventana en el sitio que quiero
                myVi.setLocation(15, 15);
                // pongo la ventana interna visible
                myVi.setVisible(true);
                // añado la ventana al escritorio
                myApp.getEscritorio().add(myVi);
                try {
                    // selecciona la ventana interna que he puesto visible como seleccionada
                    myVi.setSelected(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}
