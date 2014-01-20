/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cuestionarios_combo;

/**
 *
 * @author mjfortiz
 */
public class PruebaTest_combo {
    
    
public static void main(String[] args) {
    
    // el modelo:
Cuestionario modelo =new Cuestionario();
modelo.insertaDatos();

// la vista:
InterfazVistaCuestionario_combo vista =new VentanaCuestionario_combo();

// y el control:
ControlCuestionario_combo control = new ControlCuestionario_combo(vista, modelo);

// configura la vista
vista.setControlador(control);

// y arranca la interfaz (vista):
vista.arranca();

}
}

