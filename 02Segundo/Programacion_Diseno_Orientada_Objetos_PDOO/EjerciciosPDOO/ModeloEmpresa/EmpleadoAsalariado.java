
package ModeloEmpresa;

class EmpleadoAsalariado extends Empleado{
    
    private double sueldo;
    
    EmpleadoAsalariado(String nom, String d, double s) throws EmpresaException{
        super(nom, d);
        this.modificarPagos(s);
    }
    @Override
    public String toString() {
      return  super.toString() + "  Gano : " + sueldo;
    }
    
    double nomina(){
        return this.sueldo- (sueldo*retencion)/100;
    }
    
    @Override
    void modificarPagos (double s) throws EmpresaException{
        if(s < 600) throw new EmpresaException ("Sueldo inferior al minimo");
        
        else
            this.sueldo = s;
    }

    double getSueldo(){
        return this.sueldo;
    }
    
}
