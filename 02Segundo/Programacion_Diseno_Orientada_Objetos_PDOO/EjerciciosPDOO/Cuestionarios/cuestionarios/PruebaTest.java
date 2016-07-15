/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cuestionarios;

/**
 *
 * @author mjfortiz
 */
public class PruebaTest {
    
    
public static void main(String[] args) {
    
    // el modelo:
Cuestionario modelo =new Cuestionario();
modelo.insertaDatos();

// la vista:
InterfazVistaCuestionario vista =new VentanaCuestionario();

// y el control:
ControlCuestionario control = new ControlCuestionario(vista, modelo);

// configura la vista
vista.setControlador(control);

// y arranca la interfaz (vista):
vista.arranca();

}
}

