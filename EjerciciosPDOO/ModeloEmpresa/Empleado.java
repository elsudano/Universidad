package ModeloEmpresa;

abstract class Empleado implements Cloneable{
    static double retencion = 20;
    private String nombre;
    private String dni;
    private Director miDirector;

    Empleado(String nom, String d){
        this.nombre = nom;
        this.dni = d;
        miDirector = null;
    }
    @Override
    public Empleado clone() throws CloneNotSupportedException{
        return (Empleado)super.clone();
    
    }
    public String toString(){
     return " dni : " + this.dni + ";  nombre : " + this.nombre;
    }

    String getNombre(){
        return this.nombre;
    }

    String getDni(){
        return this.dni;
    }

    void setDni(String d){
        this.dni = d;
    }

    void setNombre(String nom){
        this.nombre = nom;
    }

    abstract double nomina();

    void establecerDirector(Director dir){
        this.miDirector = dir;
    }

    void sinDirector() throws EmpresaException{
        miDirector = null; 
    }
    

    void eliminarSubordinacion() throws EmpresaException{
        miDirector.eliminarSubordinado(this);
        this.sinDirector();
    }

    boolean tieneDirector(){
        if(miDirector != null)
            return true;
        else
            return false;
    }

    abstract void modificarPagos(double sueldo) throws EmpresaException;
}