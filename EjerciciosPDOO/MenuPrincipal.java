import ModeloEmpresa.EmpresaException;
import ModeloEmpresa.PruebaClone;
import PruebaVisibilidad.UnPaquete.PruebaBicicleta;
import PruebasColecciones.PruebasColecciones;
import PruebasVariasTema2.PruebasVarias;
import ejemplostema3.EjemplosTema3;
import ejemplostema3.interfaces.Interfaces;
import ejercicio10T2.Ejercicio10;
import ejercicio11T2.Ejercicio11;
import ejercicio19T3.Ejercicio19T3;
import ejercicio24T2.Ejercicio24;
import ejercicio2T3.Ejercicio2T3;
import ejercicio3T3.Ejercicio3T3;
import ejercicio6T2.Ejercicio6;
import ejercicio8T2.Ejercicio8;
import ejercicio8T3.Ejercicio8T3;
import ejercicioClase1T2.EjercicioClase1;
import ejercicioClase2T2.EjercicioClase2;
import ejercicioClase4T2.EjercicioClase4;
import menuModoTexto.MenuTexto;

public class MenuPrincipal {
	private static final MenuTexto miMenu = MenuTexto.getInstancia();
	
	public static void main(String[] args) throws EmpresaException, CloneNotSupportedException, InterruptedException {
		
		miMenu.nuevoMenuItem("Ejercicios Tema 2 Leccion 1-2");
		miMenu.nuevoMenuOption("Ejercicios Tema 2 Leccion 1-2", "Ejercicio 6", 1);
		miMenu.nuevoMenuOption("Ejercicios Tema 2 Leccion 1-2", "Ejercicio 8", 2);
		miMenu.nuevoMenuOption("Ejercicios Tema 2 Leccion 1-2", "Ejercicio 10", 3);
		miMenu.nuevoMenuOption("Ejercicios Tema 2 Leccion 1-2", "Ejercicio 11", 4);
		miMenu.nuevoMenuOption("Ejercicios Tema 2 Leccion 1-2", "Pruebas Visibilidad", 14);
		miMenu.nuevoMenuOption("Ejercicios Tema 2 Leccion 1-2", "Pruebas Varias", 13);
		miMenu.nuevoMenuItem("Ejercicios Tema 2 Leccion 3-4");
		miMenu.nuevoMenuOption("Ejercicios Tema 2 Leccion 3-4", "Ejercicio 24", 7);
		miMenu.nuevoMenuOption("Ejercicios Tema 2 Leccion 3-4", "Ejercicio 12", 8);
		miMenu.nuevoMenuOption("Ejercicios Tema 2 Leccion 3-4", "Modelo Empresa", 11);
		miMenu.nuevoMenuOption("Ejercicios Tema 2 Leccion 3-4", "Pruebas Colecciones", 12);
		miMenu.nuevoMenuItem("Ejercicios Tema 3");
		miMenu.nuevoMenuOption("Ejercicios Tema 3", "Ejemplo del Tema3", 15);
		miMenu.nuevoMenuOption("Ejercicios Tema 3", "Ejemplo de Interfaz", 16);
		miMenu.nuevoMenuOption("Ejercicios Tema 3", "Ejercicio 2", 17);
		miMenu.nuevoMenuOption("Ejercicios Tema 3", "Ejercicio 3", 18);
		miMenu.nuevoMenuOption("Ejercicios Tema 3", "Ejercicio 8", 19);
		miMenu.nuevoMenuOption("Ejercicios Tema 3", "Ejercicio 19", 20);
		miMenu.nuevoMenuOption("Ejercicios Tema 3", "Modelo Empresa", 0);
		miMenu.nuevoMenuOption("Ejercicios Tema 3", "Pruebas Colecciones", 0);
		miMenu.nuevoMenuItem("Ejercicios de Clase Tema2");
		miMenu.nuevoMenuOption("Ejercicios de Clase Tema2", "EjercicioClase1 dia ", 5);
		miMenu.nuevoMenuOption("Ejercicios de Clase Tema2", "EjercicioClase2 dia ", 6);
		miMenu.nuevoMenuOption("Ejercicios de Clase Tema2", "EjercicioClase3 dia ", 9);
		miMenu.nuevoMenuOption("Ejercicios de Clase Tema2", "EjercicioClase4 dia 5/12/13", 10);
		miMenu.nuevoMenuItem("Ejercicios de Clase Tema3");
		miMenu.nuevoMenuOption("Ejercicios de Clase Tema3", "EjercicioClase1 dia ", 0);
		miMenu.nuevoMenuOption("Ejercicios de Clase Tema3", "EjercicioClase2 dia ", 0);
		miMenu.nuevoMenuOption("Ejercicios de Clase Tema3", "EjercicioClase3 dia ", 0);
		miMenu.nuevoMenuOption("Ejercicios de Clase Tema3", "EjercicioClase4 dia 5/12/13", 0);
		miMenu.nuevoMenuItem("Salir", true);
		int opcion = -1;
		while (opcion!=0){
			opcion = miMenu.seleccionarOpcion();
			switch(opcion){
			case 1:
				Ejercicio6 miEjercicio6 = Ejercicio6.getInstancia();
				miEjercicio6.ejecutar();
				break;
			case 2:
				Ejercicio8 miEjercicio8 = Ejercicio8.getInstancia();
				miEjercicio8.ejecutar();
				break;
			case 3:
				Ejercicio10 miEjercicio10 = Ejercicio10.getInstancia();
				miEjercicio10.ejecutar();
				break;
			case 4:
				Ejercicio11 miEjercicio11 = Ejercicio11.getInstancia();
				miEjercicio11.ejecutar();
				break;
			case 5:
				EjercicioClase1 miEjercicio1 = EjercicioClase1.getInstancia();
				miEjercicio1.ejecutar();
				break;
			case 6:
				EjercicioClase2 miEjercicio2 = EjercicioClase2.getInstancia();
				miEjercicio2.ejecutar();
				break;
			case 7:
				Ejercicio24 miEjercicio3 = Ejercicio24.getInstancia();
				miEjercicio3.ejecutar();
			case 8:
				int[] x=new int[10];
				int d[] = {3};
				System.out.println(x.getClass());
				System.out.println(x.getClass().equals(d.getClass()));
				if (x.getClass()==d.getClass()) System.out.println("Entro en el if");
				else System.out.println("No entro en el if");
				break;
			case 9:
//				EjercicioClase3 miEjercicioClase3 = EjercicioClase3.getInstancia();
//				miEjercicioClase3.ejecutar();
				break;
			case 10:
				EjercicioClase4 miEjercicioClase4 = EjercicioClase4.getInstancia();
				miEjercicioClase4.ejecutar();
				break;
			case 11:
				PruebaClone miEjercicio4 = PruebaClone.getInstancia();
				miEjercicio4.ejecutar();
				break;
			case 12:
				PruebasColecciones miEjercicio5 = PruebasColecciones.getInstancia();
				miEjercicio5.HashSetTest();
				Thread.sleep(5000);
				miEjercicio5.ArrayListTest();
				Thread.sleep(5000);
				miEjercicio5.LinkedListTest();
				Thread.sleep(5000);
				miEjercicio5.MapTest();
				Thread.sleep(5000);
				miEjercicio5.ShuffleTest();
				Thread.sleep(5000);
				miEjercicio5.TreeSetTest();
				break;
			case 13:
				PruebasVarias miEjercicio7 = PruebasVarias.getInstancia();
				miEjercicio7.prueba1();
				miEjercicio7.prueba2();
				miEjercicio7.pruebaCol();
				miEjercicio7.pruebaEquipo();
				miEjercicio7.pruebaPersona();
				miEjercicio7.pruebaPersona2();
				break;
			case 14:
				PruebaBicicleta miEjercicio9 = PruebaBicicleta.getInstancia();
				miEjercicio9.ejecutar();
				miEjercicio9.ejecutar2();
				break;
			case 15:
				EjemplosTema3 miEjercicio12 = EjemplosTema3.getInstancia();
				miEjercicio12.ejecutar();
				break;
			case 16:
				Interfaces miEjercicio13 = Interfaces.getInstancia();
				miEjercicio13.ejecutar();
				break;
			case 17:
				Ejercicio2T3 miEjercicio2T3 = Ejercicio2T3.getInstancia();
				miEjercicio2T3.ejecutar();
				break;
			case 18:
				Ejercicio3T3 miEjercicio3T3 = Ejercicio3T3.getInstancia();
				miEjercicio3T3.ejecutar();
				break;
			case 19:
				Ejercicio8T3 miEjercicio8T3 = Ejercicio8T3.getInstancia();
				miEjercicio8T3.ejecutar();
				break;
			case 20:
				Ejercicio19T3 miEjercicio19T3 = Ejercicio19T3.getInstancia();
				miEjercicio19T3.ejecutar();
				break;
			default:
				System.out.println("opcion no valida");
			}
		}
	}
}
