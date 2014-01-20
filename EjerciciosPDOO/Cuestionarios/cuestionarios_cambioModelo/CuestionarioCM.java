
//  *************************************  Modelo

package cuestionarios_cambioModelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/**
 *
 * @author mjfortiz
 */
public class CuestionarioCM {
    
    ArrayList<String> preguntas;
    ArrayList<String> respuestas;
    HashMap<String,String> preguntasRespuestas;
    int aciertos;
    int fallos;
    
  public CuestionarioCM(){
    preguntas= new ArrayList();
    respuestas= new ArrayList();
    preguntasRespuestas= new HashMap();
    aciertos=0;
    fallos=0;
    }
   
      
    void addPreguntaRespuesta(String pre, String res){
        preguntas.add(pre);
        respuestas.add(res);
        preguntasRespuestas.put(pre,res);
    }
      
    public String getPregunta(){  //obtener preguntas aleatorias
        Random r = new Random();
        int pos = r.nextInt(preguntas.size()); 
        return (String)preguntas.get(pos);
    }
    
    public String resultados(){ //mostrar aciertos y fallos
       return "Aciertos: "+ Integer.toString(aciertos)+ "  Fallos: "+ Integer.toString(fallos);
    }

    public ArrayList<String> getRespuestas(){
        return respuestas;
    }
   
   public Boolean comprobar(String p, String r){ // verificar si la respuesta es adecuada a la pregunta
        if (((String)preguntasRespuestas.get(p)).equals(r)){
            aciertos++;
            return true;
        }
            
        else { 
            fallos++;
            return false;
        }
   }
   
   public void insertaDatos(){
        this.addPreguntaRespuesta("Capital de España", "Madrid");
        this.addPreguntaRespuesta("Cuanto es 4 x 5", "20");
        this.addPreguntaRespuesta("Pintor cubista Malagueño ", "Picasso");
   }
    
   
}
