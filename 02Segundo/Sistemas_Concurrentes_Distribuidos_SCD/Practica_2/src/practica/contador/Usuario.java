package practica.contador;

public class Usuario implements Runnable   
{ 
  private Contador_dias cont ;   // monitor contador de dias compartido
  private int           nveces ; // numero de llamadas a 'nueva_hora'
  public Thread                thr ;    // objeto Thread encapsulado
  
  public Usuario( Contador_dias p_cont, int p_nveces, String nombre )      
  { cont   = p_cont;
    nveces = p_nveces;
    thr    = new Thread(this,nombre);
  }
  
  public void run() 
  { for( int i = 0; i < nveces; i++ ) 
    { System.out.println("Hebra "+thr.getName()+": incrementa hora");
      cont.nueva_hora();
      System.out.println("Hebra "+thr.getName()+": .... dia "+cont.obtener_dia());
    }
  }
}

