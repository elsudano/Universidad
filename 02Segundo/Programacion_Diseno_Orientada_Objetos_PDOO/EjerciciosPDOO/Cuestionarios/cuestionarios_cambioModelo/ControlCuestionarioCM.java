// * *************************************** Control

package cuestionarios_cambioModelo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author mjfortiz
 */
public class ControlCuestionarioCM implements ActionListener {

private InterfazVistaCuestionarioCM vista;
private CuestionarioCM modelo;
private String pregunta;

public ControlCuestionarioCM(InterfazVistaCuestionarioCM vista, CuestionarioCM modelo){
this.vista= vista;
this.modelo= modelo;
pregunta = modelo.getPregunta();
vista.escribePregunta(pregunta);
}

    @Override
public void actionPerformed(ActionEvent evento){
String respuesta = vista.getRespuesta();
if(evento.getActionCommand().equals("COMPROBAR")) {
    if (modelo.comprobar(pregunta,respuesta))
        vista.escribeComprobar("Muy bien" );
    else vista.escribeComprobar("No, otra vez");}
else 
    if(evento.getActionCommand().equals("VERPUNTOS")) 
    vista.escribePuntos(modelo.resultados());
    else
        if(evento.getActionCommand().equals("REPETIR"))
        { pregunta=modelo.getPregunta();
            vista.repetir(pregunta);}
        else   
            if(evento.getActionCommand().equals("NUEVA"))
            {   ArrayList<String> np= vista.nuevaPregunta();
                modelo.addPreguntaRespuesta(np.get(0), np.get(1));
            }       
                        
                        ;
}
}