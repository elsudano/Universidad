/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cuestionarios_cambioModelo;

import java.util.ArrayList;

/**
 *
 * @author mjfortiz
 */
public interface InterfazVistaCuestionarioCM {

public void setControlador(ControlCuestionarioCM c);
public void arranca(); // comienza la visualización

public String getRespuesta();// 

public void escribeComprobar(String s);

public void escribePregunta(String s);

public void escribePuntos(String s);

public void repetir(String s);

public ArrayList<String> nuevaPregunta();


}
