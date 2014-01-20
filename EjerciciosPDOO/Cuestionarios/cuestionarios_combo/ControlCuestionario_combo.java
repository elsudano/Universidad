// * *************************************** Control

package cuestionarios_combo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author mjfortiz
 */
public class ControlCuestionario_combo implements ActionListener {

private InterfazVistaCuestionario_combo vista;
private Cuestionario modelo;
private String pregunta;

public ControlCuestionario_combo(InterfazVistaCuestionario_combo vista, Cuestionario modelo){
    this.vista= vista;
    this.modelo= modelo;
    pregunta = modelo.getPregunta();
    vista.escribePregunta(pregunta);
    vista.escribeRespuestas(modelo.getRespuestas());
}
    @Override
public void actionPerformed(ActionEvent evento){
    String respuesta = vista.getRespuesta();
    if(evento.getActionCommand().equals(InterfazVistaCuestionario_combo.COMPROBAR)) {
        if (modelo.comprobar(pregunta,respuesta))
            vista.escribeComprobar("Muy bien" );
        else vista.escribeComprobar("No, otra vez");
    vista.escribePuntos(modelo.resultados());}
    //else 
       // if(evento.getActionCommand().equals(InterfazVistaCuestionario_combo.VERPUNTOS)) {
    //}
}
}