
//  *************************************  Modelo

package cuestionarios_combo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/**
 *
 * @author mjfortiz
 */
public class Cuestionario {
    
    ArrayList<String> preguntas= new ArrayList();
    ArrayList<String> respuestas= new ArrayList();
    HashMap<String,String> preguntasRespuestas= new HashMap();
    int aciertos=0;
    int fallos=0;
   
      
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
    
    
    public ArrayList<String> getRespuestas(){
        return respuestas;
    }

    public String resultados(){ //mostrar aciertos y fallos
       return "Aciertos: "+ Integer.toString(aciertos)+ "  Fallos: "+ Integer.toString(fallos);
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
