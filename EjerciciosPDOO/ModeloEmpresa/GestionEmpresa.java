package ModeloEmpresa;

 
import java.io.*;
import java.util.*;

public class GestionEmpresa {
     
// variable static que va a guardar los emplados que se vayan dando de alta en la empresa
    private static Map<String,Empleado> empleados = new HashMap<String,Empleado>();

// Método static que nos va a buscar a un empleado por su dni
    public static Empleado buscarEmpleado(String dni)throws EmpresaException {
        if(!empleados.containsKey(dni)) throw new EmpresaException ("No existe el empleado") ;
        return empleados.get(dni);
    }

// definicion de funciones estaticas que manipulen las distintas opciones de los menus

    public static int menuEmpleado() throws IOException{     

//Declaracion de la variable in que va a permitir leer objetos de tipo String desde el teclado
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

// Menu que selecciona el tipo de empleado a contratar.
        System.out.println("==========================================\n"+
            "1. Asalariado"+"\n"+ 
            "2. Por horas"+"\n"+
            "3. Director"+"\n"+
	"=============================================="+"\n");
        return (Integer.parseInt(in.readLine()));
    }

    private static int menuDireccion()throws Exception{

  //Declaracion de la variable in que va a permitir leer objetos de tipo String desde el teclado
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

  // Menu que selecciona las operaciones relacionadas con el Director y sus empleados
        System.out.println("===========================================\n" +
            "1. Subordinar un empleado a un director" + "\n" +
            "2. Desubordinar un empleado a un director" + "\n" +
            "3. Modificar el director de un empleado" + "\n" +
            "4. Promocionar empleado a director" + "\n" +
            "5. Destituir a un Director" + "\n" +
            "6. Ver empleados de un director" + "\n" +
            "OTRO VALOR = Menu principal" + "\n" +
        "====================================================\n");

        return (Integer.parseInt(in.readLine()));
    }

    private static int menuInicial() throws Exception{

  //Declaracion de la variable in que va a permitir leer objetos de tipo String desde el teclado
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

   // Menu principal, donde se contemplan las operaciones generales que se pueden llevar a cabo.
        System.out.println("===========================================\n"+
           "1. Contratar empleados"+"\n"+
           "2. Director y sus empleados"+"\n"+
	   "3. Modificar Sueldo/Precio de hora"+"\n"+
           "4. Incrementar horas trabajadas"+"\n"+
           "5. Calcular nomina de todos los empleados"+"\n"+
	   "6. Despedir empleado"+"\n"+
           "7. Mostrar todos los empleados" +"\n" +     
           "0. Terminar\n"+
          "====================================================\n");

        return(Integer.parseInt(in.readLine()));
    }  
 
 // Método static main con todo el código para gestionar la empresa.   
 public static void main(String args[]) throws Exception {

 //Declaracion de la variable in que va a permitir leer objetos de tipo String desde el teclado
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
 // Declaración de todas las variables que se necesitan.
    int opcion = 0;
    String nombre;
    String dniDir;
    String dniEmp;
    //String sph;
    double sueldo;  
    Empleado emp,dir;

    do{
        // Captura de excepciones
        try{
            opcion = menuInicial();
                switch(opcion){
                    case 1: //Contratar empleado
                        // Lectura de nombre y dni de empleado
                        System.out.println("Introduzca el nombre");
                        nombre = in.readLine();
                        System.out.println("Introduce el dni");
                        dniEmp = in.readLine();                       
                        System.out.println("Introduce el sueldo o precio de la hora");                       
                        sueldo = Double.parseDouble(in.readLine());
                        opcion = menuEmpleado();
                        switch  (opcion) {
                             case 1:
                                 empleados.put(dniEmp,new EmpleadoAsalariado(nombre,dniEmp,sueldo));
                             break;
                             case 2:
                                 empleados.put(dniEmp,new EmpleadoPorHoras(nombre,dniEmp,sueldo));
                             break;
                             case 3:
                                 empleados.put(dniEmp,new Director(nombre, dniEmp, sueldo));
                             break;
                        } 
                    break;

                    case 2: // gestion de todas las operaciones relacionadas con el Director
                        opcion = menuDireccion();
                        switch (opcion){
                            case 1:
                            // Subordinar un Empleado a un Director
                                System.out.println("Introduce el dni del Director");
                                dniDir = in.readLine();
                                System.out.println("Introduce el dni del empleado");
                                dniEmp = in.readLine();
                                dir = buscarEmpleado(dniDir);
                                emp = buscarEmpleado(dniEmp);
                                ((Director)dir).incluirSubordinado(emp);
                            break;

                            case 2:
                            // Desubordinar un Empleado a un Director
                                System.out.println("Introduce el dni del empleado");
                                dniEmp = in.readLine();
                                emp = buscarEmpleado(dniEmp);
                                emp.eliminarSubordinacion();
                            break;

                            case 3:
                            // Cambiar el Director de un Empleado
                                System.out.println("Introduce el dni del nuevo Director");
                                dniDir = in.readLine();
                                System.out.println("Introduce el dni del empleado");
                                dir = buscarEmpleado(dniDir);
                                dniEmp = in.readLine();
                                emp= buscarEmpleado(dniEmp);
                                if(emp.tieneDirector())
                                    emp.eliminarSubordinacion();
                                ((Director)dir).incluirSubordinado(emp);
                            break;

                            case 4:
                            // Promocionar empleado asalariado a director
                                System.out.println("Introduce el dni del empleado");
                                dniEmp = in.readLine();
                                emp = buscarEmpleado(dniEmp);
                                if(emp.tieneDirector())
                                    emp.eliminarSubordinacion();    
                                String nom = emp.getNombre();
                                sueldo = ((EmpleadoAsalariado)emp).getSueldo();
                                empleados.remove(dniEmp);
                                empleados.put(dniEmp, new Director(dniEmp, nom, sueldo));
                            break;

                            case 5:
                            // Destituir a un director y pasarlo a empleado asalariado.
                                System.out.println("Introduce el dni del Director");
                                dniDir = in.readLine();
                                System.out.println("Introduce el sueldo");
                                sueldo = Double.parseDouble(in.readLine());
                                dir = buscarEmpleado(dniDir);
                                nom = dir.getNombre();
                                ((Director)dir).sinSubordinados();
                                empleados.remove(dniDir);
                                empleados.put(dniDir, new EmpleadoAsalariado(dniDir, nom, sueldo));
                            break;

                            case 6:
                            // Mostrar los empleados de un Director
                                System.out.println("Introduce el dni del Director");
                                dniDir = in.readLine();
                                dir = buscarEmpleado(dniDir);                                
                                List<String> resultado = ((Director) dir).obtenerSubordinados();
                                for(String datosSubor:resultado){ 
                                    System.out.println(datosSubor);
                                } 
                            break;
                        }
                    break;

                    case 3:  
                     //Modificar el sueldo o el precio de la hora de los empleados 
                        System.out.println("Introduce el dni del empleado");
                        dniEmp = in.readLine();
                        emp = buscarEmpleado(dniEmp);
                        System.out.println("Introduce el nuevo sueldo/precio-hora/comision");
                        
                        sueldo = Double.parseDouble(in.readLine());
                        emp.modificarPagos(sueldo);
                    break;

                    case 4:  //Incrementar las horas trabajadas
                        
                        System.out.println("Introduce el dni del empleado");
                        dniEmp = in.readLine();
                        emp=buscarEmpleado(dniEmp);
                        System.out.println("Introduce el incremento de horas");
                             
                        sueldo = Double.parseDouble(in.readLine());
                        ((EmpleadoPorHoras)emp).incrementarHoras(sueldo);
                             
                    break;

                    case 5:
                    // Tarea: mostrar la nomina de todos los empleados de la empresa.
                        for(Empleado empleado:empleados.values()){
                            System.out.println("El empleado " + empleado.getNombre()+ "gana " +empleado.nomina());
                        }
                    break;

                    case 6:
                    // Despedir a un empleado
                        System.out.println("Introduce el dni del empleado");
                        dniEmp = in.readLine();
                        emp = buscarEmpleado(dniEmp);
                        if(emp.getClass().getSimpleName().equals("Director"))
                            ((Director)emp).sinSubordinados();
                        else if(emp.tieneDirector())
                            emp.eliminarSubordinacion();
                        empleados.remove(emp.getDni());
                    break;
                    case 7:
                    // Mostrar todos los empleados de la empresa
                         for (Map.Entry<String, Empleado> empleadoEntry : empleados.entrySet()) {                           
                           System.out.println(empleadoEntry.getValue().toString());                            
                        }
                    break;
                    case 0:
                        default: System.out.println("Opcion no valida");
                    break;
                }           
        // Si se ha producido una excepción  de este tipo se ejecuta este catch
        } catch (Exception ex) {
            System.err.println(ex);}  
        
    }while (opcion != 0);
  }
} 
