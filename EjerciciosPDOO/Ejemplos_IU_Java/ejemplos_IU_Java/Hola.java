/* ejemplo inicial típico de ventana que muestra una etiqueta de saludo.
 * Los demás ejemplos están basados en el tutorial siguiente:
 * http://www.fismat.umich.mx/computacion/notas/parte14/cap14-7.html
 * Se han añadido las gestiones de eventos que en la mayoría de los ejemplos base del
 * tutorial no estaban.
 */
package ejemplos_IU_Java;

/**
 *
 * @author mjfortiz
 */
    import javax.swing.*;

public class Hola extends JFrame {
    
    Hola(){
        JLabel hola = new JLabel( "¡Hola Mundo!" );
        

        getContentPane().add(hola);
        setSize( 200,100);
        setLocationRelativeTo(null); //centra en la pantalla del ordenador
        setVisible( true );
    }
    
public static void main( String argv[] ) {
        new Hola();
    }
    
    
    
}