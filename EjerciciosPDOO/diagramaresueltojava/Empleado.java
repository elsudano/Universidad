package diagramaresueltojava;

import java.util.ArrayList;


public class Empleado {
    
    private String dni;
    private String nombre;
    private Empleado director;
    private ArrayList<Empleado> subordinados;
    
    Empleado(String dni, String nombre, Empleado director){
        this.dni = dni;
        this.nombre = nombre;
        this.director = director;
        this.subordinados = new ArrayList<Empleado>();
    }
    
    void sinSubordinados(){
        for(Empleado empSubor: subordinados){
            empSubor.sinDirector();
        }
    }
    
    void sinDirector(){} //Sin implementar
    boolean esDirector(){return false;} //Sin implementar
    boolean tieneDirector(){return false;} //Sin implementar
    void eliminarSubordinacion(){} //Sin implementar
}
