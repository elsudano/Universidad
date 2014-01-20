
//  *************************************  Modelo

package cuestionarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;


/**
 *
 * @author mjfortiz
 */
public class Cuestionario {
    
    //ArrayList<String> preguntas;
    //ArrayList<String> respuestas;
    HashMap<String,String> preguntasRespuestas;
    int aciertos;
    int fallos;
    
  public Cuestionario(){
    //preguntas= new ArrayList();
    //respuestas= new ArrayList();
    preguntasRespuestas= new HashMap();
    aciertos=2;
    fallos=0;
    }
   
      
    void addPreguntaRespuesta(String pre, String res){
        //preguntas.add(pre);
        //respuestas.add(res);
        preguntasRespuestas.put(pre,res);
    }
      
    public String getPregunta(){  //obtener preguntas aleatorias
        Random r = new Random();
        //int pos = r.nextInt(preguntas.size()); 
        //return (String)preguntas.get(pos);
        int pos = r.nextInt(preguntasRespuestas.size());
        Set miSet =preguntasRespuestas.keySet();
        //for ()
        return (String)preguntasRespuestas.get(pos);
    }
   
    public String resultados(){ //mostrar aciertos y fallos
       //return "Aciertos: "+ Integer.toString(aciertos)+ "  Fallos: "+ Integer.toString(fallos);
       return "Aciertos: "+ Integer.toString(aciertos)+ "  Fallos: "+ Integer.toString(fallos);
    }

    public ArrayList<String> todasRespuestas(){
        ArrayList<String> respuestas = new ArrayList<String>();
    	for (String resp : preguntasRespuestas.values()){
    		respuestas.add(resp);
    	}
        return respuestas;
    }
   
   public Boolean comprobar(String p, String r){ // verificar si la respuesta es adecuada a la pregunta
        if (((String)preguntasRespuestas.get(p)).equals(r)){
            aciertos*=2;
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
        this.addPreguntaRespuesta("Aprobaremos Sistemas Operativos", "Si");
        this.addPreguntaRespuesta("Esta se una pregunta de Ejemplo", "Si");
   }
    
   
}
