/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import gui.VentanaInterna;
import gui.VentanaPrincipal;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos de al Torre
 */
public class Main {

    /**
     * @param args the command line arguments
     */
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
        VentanaInterna myVi = new VentanaInterna(myApp);
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                myApp.setVisible(true);
                myVi.setLocation(15, 15);
                myVi.setVisible(true);
                myApp.getEscritorio().add(myVi);
                try {
                    myVi.setSelected(true);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
}
