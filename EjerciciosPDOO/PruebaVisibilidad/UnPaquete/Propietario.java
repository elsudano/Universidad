package PruebaVisibilidad.UnPaquete;

  
public class Propietario {
    
    private String nombre;
    private String dni;
    private Bicicleta miBicicleta;
    
    public Propietario(String nom, String dni, Bicicleta bici){
        this.setNombre(nom);
        this.setDni(dni);
        this.setBicicleta(bici);
    }
    
     public void setNombre(String nom){     
         nombre = nom;    
     }

    public void setDni(String dni) {
        this.dni=dni;
    }

    public void setBicicleta(Bicicleta bici) {
       miBicicleta = bici;
    }
    public void salirEnBici(double km){
         //miBicicleta.incrementarKilometros(km);
    }
    public double cuantoskilometros(){
        return miBicicleta.kilometrosRecorridos;    
    }
    public int ruedasDeMiBici(){
        // return miBicicleta.NUMERORUEDAS;
        return Bicicleta.NUMERORUEDAS;
        //return Bicicleta.getNumeroRuedas(); // método de instancia
    }
}
