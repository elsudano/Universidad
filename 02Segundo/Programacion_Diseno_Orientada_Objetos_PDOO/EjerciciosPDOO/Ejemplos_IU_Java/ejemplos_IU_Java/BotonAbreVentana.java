/*
 * Ejemplo con dos ventantas. El botón de la primera abre la segunda y 
 * permite también cerrar la primera si se desea. 
 * En la segunda ventana hay otro botón que abre ventanas de confirmación
 */
package ejemplos_IU_Java;


import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author mjfortiz
 */
public class BotonAbreVentana {
    
//para la ventana primera
    
JFrame f1 ;
Container cont1;
JLabel label1;
JButton boton1;

//para la ventana segunda

JFrame f2 ;
Container cont2;
JLabel label2;
JButton boton2;


private void accionBoton1(){    //acción asociada al botón 1: que abra la ventana 2
ActionListener al = new ActionListener() {
            
            @Override
            public void actionPerformed( ActionEvent evento ) {
                
                f2.setVisible(true);
                //f1.dispose();  //cierra la ventana 1 después de abrir la ventana 2
            }
        };
        boton1.addActionListener( al );
        
}

private void accionBoton2(){    //acción asociada al botón 2: que abra una ventana para preguntar
ActionListener al = new ActionListener() {
            
    @Override
    public void actionPerformed( ActionEvent evento ) {
                
        int res = JOptionPane.showConfirmDialog( f2,"¿Te gusta el verano?",
            "Ventana para preguntar",JOptionPane.YES_NO_OPTION );
        String respuesta = null;
     
        if( res == JOptionPane.YES_OPTION ) 
            respuesta = "Sí";
        else
            respuesta = "No";
        
        JOptionPane.showMessageDialog( f2, //en base a la respuesta se abre otra ventana de confirmación
            "Tu respuesta es: "+respuesta, 
            "Ventana para Confirmación", 
            JOptionPane.WARNING_MESSAGE );
        
            }
        };
        boton2.addActionListener( al );
        
}

BotonAbreVentana(){
f1 = new JFrame("Ventana 1");
cont1= f1.getContentPane();
label1 = new JLabel("Tocar el botón para abrir una ventana nueva");
boton1 = new JButton("Abrir otra ventana");
cont1.setLayout(new FlowLayout());        
cont1.add(label1);
cont1.add(boton1);
this.accionBoton1();
f1.setSize( 300,300);
f1.setLocationRelativeTo(null);
f1.setVisible( true );

f2 = new JFrame("Ventana 2");
cont2= f2.getContentPane();
label2 = new JLabel("soy la Ventana 2");
cont2.add(label2);
boton2 = new JButton("Abrir un dialog");
cont2.add(boton2);
this.accionBoton2();
f2.setSize(200,200);
}

public static void main (String args[]){
  
BotonAbreVentana pru= new BotonAbreVentana();




}
    
}

