/*
 * Ejemplo que muestra cómo hacer menús enlazados, partiendo de los textos que deben aparecer en ellos
 *Se muestra ejemplo de un menú cuyos elementos son checkbox.
 */
package ejemplos_IU_Java;

/**
 *
 * @author mjfortiz
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VariosMenus extends JPanel {
        
    JTextField txt = new JTextField( 10 );
    
        
    ActionListener listenerMenu = new ActionListener() {
        @Override
        public void actionPerformed( ActionEvent evt ) {
            txt.setText( ((JMenuItem)evt.getSource()).getText() );
        }
    };
    
   
    
    public Object menuEdicion[][] = {
        
        { "Edición"},
        // Nombre, habilitado (BT) o no (BF)
        { "Cortar",true},
        { "Copiar",false},
        { "Pegar",false},
        { null}, // Separador
        { "Seleccionar Todo",true},
    };
    
    public Object menuOpciones[][] = {
       
        { "Opciones"},
        // Nombre,  habilitado o no
        { "Opción 1",true},
        { "Opción 2",true},
    };
    
  
    public JMenuBar creaMenuBarra() {
        JMenuBar barraMenu = new JMenuBar();
        barraMenu.add(creaMenu(menuEdicion, "items"));
        barraMenu.add(creaMenu(menuOpciones, "box"));
      
        return barraMenu;
    }


   public JMenu creaMenu( Object[][] menuX, String tipo ) {
        
        JMenu menuV = new JMenu();
        
        menuV.setText( (String)menuX[0][0] );
              
        for ( int i=1; i < menuX.length; i++ ) {
            if ( menuX[i][0] == null )
                menuV.add( new JSeparator() );
            else
                menuV.add( creaMenuItem( menuX[i],tipo ) );
        }
        return( menuV );
    }


   public JMenuItem creaMenuItem( Object[] elemento, String tipo ) {
        JMenuItem e = null;
        
        if ( tipo == "items" )
            e = new JMenuItem();
        else if ( tipo == "box" )
            e = new JCheckBoxMenuItem();
        
        //posible mejora: añadir otros tipos de items de menú
        e.setText( (String)elemento[0] );
        
        e.addActionListener( listenerMenu);
        e.setEnabled( ((Boolean)elemento[1]) );
       
        return( e );
    }



    public static void main(String args[]) {
        VariosMenus m= new VariosMenus();
        m.setLayout( new BorderLayout() );
        m.add( m.creaMenuBarra(),BorderLayout.NORTH );
        
        JPanel p = new JPanel();
        p.setLayout( new BorderLayout() );
        p.add( m.txt,BorderLayout.NORTH );
        
        m.add( p,BorderLayout.CENTER );
        
        JFrame ventana = new JFrame();
        ventana.setDefaultCloseOperation( ventana.EXIT_ON_CLOSE );
        ventana.getContentPane().add( m ,BorderLayout.CENTER );
        ventana.setSize( 320,200 );
        ventana.setTitle( "Pruebas de varios menús" );
        ventana.setVisible( true );
    }
}
