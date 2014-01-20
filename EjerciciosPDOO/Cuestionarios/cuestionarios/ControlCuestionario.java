// * *************************************** Control

package cuestionarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author mjfortiz
 */
public class ControlCuestionario implements ActionListener {

private InterfazVistaCuestionario vista;
private Cuestionario modelo;
private String pregunta;

public ControlCuestionario(InterfazVistaCuestionario vista, Cuestionario modelo){
this.vista= vista;
this.modelo= modelo;
pregunta = modelo.getPregunta();
vista.escribePregunta(pregunta);
}

    @Override
public void actionPerformed(ActionEvent evento){
String respuesta = vista.getRespuesta();
if(evento.getActionCommand().equals(InterfazVistaCuestionario.COMPROBAR)) {
    if (modelo.comprobar(pregunta,respuesta))
        vista.escribeComprobar("Muy bien" );
    else vista.escribeComprobar("No, otra vez");}
else 
    if(evento.getActionCommand().equals(InterfazVistaCuestionario.VERPUNTOS)) {
    vista.escribePuntos(modelo.resultados());}
}
}