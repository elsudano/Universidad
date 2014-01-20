package PruebaVisibilidad.UnPaquete;

public class Bicicleta {
    
    //Atributos de ámbito de clase
    private static int numeroBicicletas = 0;
    public static final int NUMERORUEDAS = 2; // CONSTANTE
    
    // Atributos de ámbito de instancia
    public final int MARCHAS; // PUBLICO
    public String color;
    double kilometrosRecorridos= 0; // INICIALIZACION
    private final int NUMEROSERIE; // CONSTANTE
    
    
    // Métodos de ámbito de clase: Consultores
    // OJO:  ¿Tienen ambos ámbito de clase?
    public static int getNumeroBicicletas(){
        // color="blanco";
        return numeroBicicletas;
    }
    public int getNumeroRuedas(){
        return NUMERORUEDAS;
    }
    
    // Métodos de ámbito de clase: Modificadores
    //   ¿POR QUÉ ES PRIVADO?
    private static void incrementarNumeroBicicletas(){
         numeroBicicletas++;
    } 
    
    // Constructor por defecto
    public Bicicleta(){
     	MARCHAS=0;
	color="gris";        
        NUMEROSERIE = numeroBicicletas++;              
    }
    // Construtor
    Bicicleta(int marcha, String color){
        this.MARCHAS = marcha;
        this.color = color;
        NUMEROSERIE = numeroBicicletas++;          
    }
   
    // Consultores básicos
    public String getColor()  {
	  return color;
    }    
    public int getMarchas() {
          return MARCHAS;
    }    
    public int getNumeroSerie(){
        return NUMEROSERIE;
    }
    double getKilometros(){
        return kilometrosRecorridos;
    }
    
    // Modificadores básicos
    public void setColor(String unColor) {
	  color= unColor;
          
    }
    private void incrementarKilometros(double km){
        kilometrosRecorridos+=km;
    }
    
    // Método main() de ámbito de clase
    public static void main(String args[]){
	
        //Construcción de objetos de la clase Bicicletas.
        Bicicleta bici= new Bicicleta();
	System.out.println("color de la bicicleta = " + bici.getColor());
        System.out.println("Numero de bicicletas construidas = " + Bicicleta.numeroBicicletas);
        
        Bicicleta miBici = new Bicicleta(5,"rojo");
        System.out.println("color de la bicicleta = " + miBici.getColor());
        System.out.println("Numero de bicicletas construidas = " + Bicicleta.numeroBicicletas);
        

    }
    
}
