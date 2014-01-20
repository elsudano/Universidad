
package diagramaresueltojava;

import java.util.ArrayList;


public class Empresa {

    private String nombre;
    private String direccion;
    private ArrayList<Empleado> empleados;
    
    Empresa(String nombre, String direccion){
        this.nombre = nombre;
        this.direccion = direccion;
        this.empleados = new ArrayList<Empleado>();
    }
    
    void despedirEmpleado(String dni){
        Empleado emp = this.buscarEmpleado(dni);
        if(emp.esDirector())
            emp.sinSubordinados();
        else{
            if(emp.tieneDirector())
                emp.eliminarSubordinacion();
        }
        empleados.remove(emp);
    }
    
    //Este método está sin implementar
    Empleado buscarEmpleado(String dni){
        return null;
    }

}
