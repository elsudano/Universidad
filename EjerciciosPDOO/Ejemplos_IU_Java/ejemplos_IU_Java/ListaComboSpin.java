/*
 * Ejemplo de ventana con tres listas: JCombo, JList y JSpinner, con texto que muestra
 * la selección de la lista. Probarla para ver diferencias entre ellas.
 */
package ejemplos_IU_Java;

/**
 *
 * @author mjfortiz
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

    
public class ListaComboSpin extends JPanel {

    String idsLista[] = {
        "Lunes","Martes","Miercoles",
        "Jueves","Viernes"};
    
    String idsSpin[] = {
        "Aries","Libra","Leo",
        "Tauro","Sagitario","Piscis"};
    
    JTextField texto = new JTextField( 5 ); //campo de texto
   
    JList lista = new JList( idsLista ); //lista de elementos, se pueden seleccionar varios
    
    JComboBox combo = new JComboBox(); //lista desplegabe, se selecciona uno
      
    SpinnerListModel modeloSpin = new SpinnerListModel( idsSpin );//lista special, Spinner y su modelo de datos
    JSpinner spin= new JSpinner( modeloSpin );
        
       
      
    public ListaComboSpin() {//panel con tres listas      
                        
        for ( int i=0; i < 100; i++ )  //se crean los elementos del combo
            combo.addItem( Integer.toString( i ) );
               
        //Asociar funcionalidad a la selección de las listas
        
        ActionListener listenerCombo= new ActionListener() {
             @Override
            public void actionPerformed( ActionEvent evento ) {
                texto.setText( combo.getSelectedItem().toString() );
        }
        };
        
         ListSelectionListener listenerLista = new ListSelectionListener() {
            @Override
            public void valueChanged( ListSelectionEvent evento ) {
                texto.setText( lista.getSelectedValue().toString()); 
            }
        };
         
         ChangeListener listenerSpin= new ChangeListener() {
            @Override
            public void stateChanged( ChangeEvent evento ) {
                texto.setText( spin.getValue().toString()); 
            }
        };
        
        
        combo.addActionListener( listenerCombo );
        add( combo );  // aquí se añade el combo
        
        lista.addListSelectionListener(listenerLista );
        add( new JScrollPane( lista ) ); //aquí se añade la lista con un scroll
        
        spin.setValue( "Leo" );// valor por defecto en el que se sitúa la interfaz
        spin.setPreferredSize(new Dimension(70,24)); //tamaño en pantalla
        spin.addChangeListener(listenerSpin);
        add( spin ); //aquí se añade la lista de tipo spin
        
        add( texto ); // aquí se añade el texto
        
    }


    public static void main( String args[] ) {
        ListaComboSpin prueba = new ListaComboSpin();
        JFrame ventana = new JFrame();
        ventana.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        ventana.getContentPane().add( prueba,BorderLayout.CENTER );
        ventana.pack();
        ventana.setSize( 300,300);
        ventana.setTitle( "Probando lista, combo y spin" );
        ventana.setVisible( true );
    }
}

