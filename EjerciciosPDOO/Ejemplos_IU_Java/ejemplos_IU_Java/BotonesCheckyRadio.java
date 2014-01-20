/*
 * Ejemplo que ilustra cómo trabajar con botones Check y Radio y con grupos de botones
 * Cada grupo de botones está en un panel diferente dentro del JFrame
 */
package ejemplos_IU_Java;

/**
 *
 * @author mjfortiz
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class BotonesCheckyRadio extends JPanel {

  String ids[] = {"Lunes","Martes","Miércoles",
        "Jueves","Viernes","Sabado","Domingo"};
    
 
  JFrame ventana; 
  JPanel panelCheck; //primer panel de la ventana
  JPanel panelRadio; //segundo panel de la ventana
  JTextField texto = new JTextField( 20 ); //campo de texto en la ventana
   

    private  JPanel creaPanelBotonesCheck() {
        ButtonGroup botonesCheck = new ButtonGroup();
        JPanel panel1 = new JPanel();
        panel1.setBorder( new TitledBorder( "botones JCheckBox" ) );
        for(int i=0; i<ids.length;i++ )
        {   JCheckBox boton= new JCheckBox(ids[i]);
            boton.addActionListener(this.asociaAccionCheck());
            botonesCheck.add( boton );
            panel1.add( boton );
        }
        return panel1;
    }
        
     private   JPanel creaPanelBotonesRadio(){
        ButtonGroup botonesCheck = new ButtonGroup();
        JPanel panel2 = new JPanel();
        panel2.setBorder( new TitledBorder( "botones RadioButton" ) );
        for(int i=0; i<ids.length;i++ )
        {   JRadioButton boton= new JRadioButton(ids[i]);
            boton.addActionListener(this.asociaAccionRadio());
            botonesCheck.add( boton );
            panel2.add( boton );
        }
       panel2.setBackground(Color.PINK); //para poner un fondo de color que se vea
       panel2.setOpaque(true);
       return panel2;
     }
     private  ActionListener asociaAccionCheck(){
        ActionListener al = new ActionListener() {
            
            @Override
            public void actionPerformed( ActionEvent evento ) {
                String nombre = ( (JCheckBox)evento.getSource()).getText();
                texto.setText( nombre );
            }
        };
        return al;
        
     }
     
     private  ActionListener asociaAccionRadio(){
        ActionListener al = new ActionListener() {
            
            @Override
            public void actionPerformed( ActionEvent evento ) {
                String nombre = ( (JRadioButton)evento.getSource()).getText();
                texto.setText( nombre );
                //posible mejora: mostrar historial de pulsaciones en el texto
            }
        };
        return al;
        
     }
public BotonesCheckyRadio() {
    panelCheck=this.creaPanelBotonesCheck();
    panelRadio=this.creaPanelBotonesRadio();
    ventana = new JFrame("Probando botones check y radio");
    ventana.getContentPane().add( panelCheck,BorderLayout.NORTH );
    ventana.getContentPane().add( texto,BorderLayout.CENTER );
    ventana.getContentPane().add( panelRadio,BorderLayout.SOUTH );
    ventana.setSize( 350,200 );
    ventana.setVisible( true );
}

    public static void main( String args[] ) {
        BotonesCheckyRadio panelCompuesto = new BotonesCheckyRadio();   
    }
}
    

