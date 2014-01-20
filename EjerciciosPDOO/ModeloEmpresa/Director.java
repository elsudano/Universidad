package ModeloEmpresa;

import java.util.ArrayList;
import java.util.List;

class Director extends EmpleadoAsalariado implements Cloneable {
    
    private List<Empleado> subordinados = new ArrayList<Empleado>();
    
    Director(String nom, String d, double s) throws EmpresaException{
        super(nom, d, s);
    }
    
    List<String> obtenerSubordinados(){
        List<String> resultado = new ArrayList<String>();         
        for(Empleado emp:subordinados) { 
            resultado.add(emp.toString());            
        }        
        return resultado;        
    }
    
    void incluirSubordinado(Empleado emp) throws EmpresaException{
        if(emp.tieneDirector()) throw new EmpresaException("Ese empleado ya tiene director");
        if(emp.getClass().getSimpleName().equals("Director"));//pide la clase, coge el nombre y comprueba si es "Director"
        subordinados.add(emp);
        emp.establecerDirector(this);
      }
    // Clone: Copia superficial
//    @Override
//    public Director clone() throws CloneNotSupportedException{
//        return (Director)super.clone();
//    }
    // Clone: Copia en profundidad
//    @Override
//    public Director clone() throws  CloneNotSupportedException{
//        try {
//            Director dir = new Director(this.getNombre(),this.getDni(), this.getSueldo());
//            for (Empleado emp:subordinados){
//                dir.incluirSubordinado((Empleado)emp.clone());
//            }
//            return dir;
//        } catch (EmpresaException ex) {
//            Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//        
//    }
    
    void eliminarSubordinado(Empleado emp) throws EmpresaException{
        if(!subordinados.remove(emp)) throw new EmpresaException ("Empleado no encontrado");
         
    }
    
    void sinSubordinados() throws EmpresaException{
        for(Empleado emp:subordinados){
            emp.sinDirector();
        }
        subordinados.clear();
    }
    
    
}
