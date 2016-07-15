/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cuestionarios;

/**
 *
 * @author mjfortiz
 */
public interface InterfazVistaCuestionario {

public void setControlador(ControlCuestionario c);
public void arranca(); // comienza la visualización

public String getRespuesta();// 

public void escribeComprobar(String s);

public void escribePregunta(String s);

public void escribePuntos(String s);


//texto con la conversión
// Constantes que definen las posibles operaciones:
public static final String COMPROBAR = "COMPROBACIÓN";
public static final String VERPUNTOS = "PUNTUACIÓN";


}
