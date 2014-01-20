/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cuestionarios_cambioModelo;

/**
 *
 * @author mjfortiz
 */
public class PruebaTestCM {
    
    
public static void main(String[] args) {
    
    // el modelo:
CuestionarioCM modelo =new CuestionarioCM();
modelo.insertaDatos();

// la vista:
InterfazVistaCuestionarioCM vista =new VentanaCuestionarioCM();

// y el control:
ControlCuestionarioCM control = new ControlCuestionarioCM(vista, modelo);

// configura la vista
vista.setControlador(control);

// y arranca la interfaz (vista):
vista.arranca();

}
}

