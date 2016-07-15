package practica.lector_escritor;

import monitor.AbstractMonitor;
import monitor.Condition;

public class MonitorLE extends AbstractMonitor 
{ 
  private int num_lectores = 0 ;
  private boolean escribiendo = false ;
  private Condition lectura   = makeCondition();
  private Condition escritura = makeCondition();

  public void inicio_lectura() 
  { enter();
    if (escribiendo) lectura.await();
    num_lectores++; 
    lectura.signal(); 
    leave(); 
  }
  public void fin_lectura() 
  { enter();
    num_lectores--; 
    if (num_lectores==0) escritura.signal(); 
    leave(); 
  }
  public void inicio_escritura() 
  { enter();
    if (num_lectores>0 || escribiendo) escritura.await();
    escribiendo=true;
    leave(); 
  }
  public void fin_escritura() // prio. lect
  { enter();
    escribiendo=false;
    if (lectura.isEmpty()) escritura.signal();
    else lectura.signal(); 
    leave(); 
  }
} // fin clase monitor "Lect\_Esc"
