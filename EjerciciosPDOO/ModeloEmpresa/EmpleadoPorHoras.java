package ModeloEmpresa;

class EmpleadoPorHoras extends Empleado {

    private double precioHora;
    private double horasTrabajadas;

    EmpleadoPorHoras( String nom, String d,double ph) throws EmpresaException{
        super(nom, d);
        this.modificarPagos(ph);
        this.horasTrabajadas = 0;
    }
    public String toString(){
        return super.toString() + "  precio de la hora : " + this.precioHora;
    }

    double getPrecioHora(){
        return precioHora;
    }

    double getHorasTrabajadas(){
        return horasTrabajadas;
    }

    @Override
    void modificarPagos(double ph) throws EmpresaException{
        if(ph < 6) throw new EmpresaException("Precio de la hora incorrecto");
        else
            this.precioHora = ph;
    }

    void incrementarHoras(double ht){
        this.horasTrabajadas = this.horasTrabajadas + ht;
    }

    void inicializarHoras(){
        this.horasTrabajadas = 0;
    }
    
    @Override
    double nomina(){
        double nomina = this.horasTrabajadas * this.precioHora;
        nomina = nomina -(nomina*retencion)/100;
        this.inicializarHoras();
        return nomina;
    }

}
