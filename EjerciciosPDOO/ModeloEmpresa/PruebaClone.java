/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ModeloEmpresa;
/**
 *
 * @author Carlos de la Torre
 */
public class PruebaClone {
	private static final PruebaClone mInstancia = new PruebaClone();
	// CONSTRUCTOR
	/*
	 * Se pone el constructor en privado para que no se pueda
	 * hacer un new y se tiene que llamar usar getInstancia
	 * para poder usar el objeto en cuestion.
	 */
	private PruebaClone() {

	}
	
	// METODOS
	/*
	 * Con este metodo en concreto lo que hacemos es que 
	 * cuando lo llamamos ejecutamos el ejercicio en cuestion
	 */
	public void ejecutar() throws EmpresaException, CloneNotSupportedException{
		Director dir = new Director("pepe","pppp",2000);
        EmpleadoAsalariado ea = new EmpleadoAsalariado("maria","mmm",3000);
        EmpleadoPorHoras eh = new EmpleadoPorHoras("juan","jjj",30);
        
        //Incluir empleados al director
        dir.incluirSubordinado(ea);
        dir.incluirSubordinado(eh);
        
        System.out.println("Ver los empleados del director ------");
        for(String datosEmp:dir.obtenerSubordinados())
            System.out.println("....." +datosEmp);
        
        // Clonar el director
        Director dir1 = (Director)dir.clone();
        System.out.println("Hemos clonado el director");
        System.out.println("Ver los emplados del director clonado------");
        for(String datosEmp:dir1.obtenerSubordinados())
            System.out.println("<<<<<<" + datosEmp);
        
        // Eliminamos un emplado del primer director
        dir.eliminarSubordinado(eh);
        System.out.println("Eliminamos un empledo del primer director-----");
        
        System.out.println("Ver los empleados del primer director------");
        for(String datosEmp:dir.obtenerSubordinados())
            System.out.println("-----" + datosEmp);
        System.out.println("Ver los empleados del director clonado -----");
        for(String datosEmp:dir1.obtenerSubordinados())
            System.out.println("++++++"+datosEmp);
	}

	/**
	 * @return el parametro mInstancia
	 */
	public static PruebaClone getInstancia() {
		return mInstancia;
	}    
}
