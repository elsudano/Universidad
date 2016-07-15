/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cuestionarios_combo;

import java.util.ArrayList;

/**
 *
 * @author mjfortiz
 */
public interface InterfazVistaCuestionario_combo {

public void setControlador(ControlCuestionario_combo c);
public void arranca(); // comienza la visualización

public String getRespuesta();// 

public void escribeComprobar(String s);

public void escribePregunta(String s);

public void escribePuntos(String s);

public void escribeRespuestas(ArrayList<String> r);


//texto con la conversión
// Constantes que definen las posibles operaciones:
public static final String COMPROBAR = "COMPROBAR";
//public static final String VERPUNTOS = "VERPUNTOS";


}
