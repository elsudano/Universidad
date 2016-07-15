package practica.contador;

import monitor.AbstractMonitor;

public class Contador_dias extends AbstractMonitor
{ private int num_horas = 0, num_dias = 0;
  public void nueva_hora() 
  { enter() ;
    num_horas++;
    if ( num_horas == 24 )
    { num_dias++;
      num_horas=0;
    }
    leave() ; 
  }
  public int obtener_dia( )
  { enter() ;
    int valor=num_dias;
    leave() ; 
    return valor;
  }
}
