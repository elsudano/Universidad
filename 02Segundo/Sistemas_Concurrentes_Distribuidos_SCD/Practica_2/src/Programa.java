import java.util.Vector;

import hebras.barbero.*;
import hebras.fumadores.*;
import hebras.productor_consumidor.*;
import menuModoTexto.MenuText;
import practica.contador.*;
import practica.lector_escritor.*;
import practica.productor_consumidor.*;
import seminario.seminario2_1.*;
import seminario.seminario2_2.*;

public class Programa {
	private static MenuText miMenu = MenuText.getInstancia();
	/**
	 * @param args especifica los parametros del programa
	 */
	public static void main(String[] args){
		miMenu.newMenuItem("Seminario");
		miMenu.newMenuOption("Seminario", "Programa Siesta", 1);
		miMenu.newMenuOption("Seminario", "Programa Contador", 2);
		miMenu.newMenuItem("Practica");
		miMenu.newMenuOption("Practica", "Contador", 3);
		miMenu.newMenuOption("Practica", "Lector-Escritor", 4);
		miMenu.newMenuOption("Practica", "Productor-Consumidor", 5);
		miMenu.newMenuItem("Hebras");
		miMenu.newMenuOption("Hebras", "Productor-Consumidor fifo", 6);
		miMenu.newMenuOption("Hebras", "Productor-Consumidor lifo", 7);
		miMenu.newMenuOption("Hebras", "Fumadores", 8);
		miMenu.newMenuOption("Hebras", "Barbero", 9);
		miMenu.newMenuItem("Salir", true);
		int opcion = -1;
		while (opcion!=0){
			opcion = miMenu.seleccionarOpcion();
			String[] parametros = {"0","0","0","0","0","0"};
			switch(opcion){
			case 1:
				parametros[0]="1000";
				parametros[1]="";
				try{
					if (parametros[1]!=""){
						System.out.println( "Error: se desea ejecutar primera parte del programa pero falta valor de ’siesta’" );
						System.exit(1);
					}
					long siesta = Long.parseLong(parametros[0]) ;
					TipoHebra obj = new TipoHebra("hebra ’obj’", siesta); // crear hebra
					obj.thr.start(); // lanzar hebra
					Thread.sleep( 100 ); // la hebra principal duerme 1/10 sec.
					obj.thr.join(); // esperar a que termine la hebra
				}catch (InterruptedException e){
					System.out.println ("ha ocurrido una excepcion.");
				}
				break;
			case 2:
				try{
					Contador contH = new Contador(0); // contador usado por la hebras
					HebraContadora[] vc = new HebraContadora[2] ;
					vc[0] = new HebraContadora( "Contador2 ", 2, contH);
					vc[1] = new HebraContadora( "Contador3 ", 3, contH);
					vc[0].thr.start();
					vc[1].thr.start(); // lanza hebras
					vc[0].thr.join();
					vc[1].thr.join(); // espera terminación
					System.out.println("Resultado hebras : "+contH.getvalor());
					long contV = 0 ; // contador de verificacion
					for ( int i = 1 ; i <= 1000 ; i++ ){
						if ( i % 2 == 0 ) contV = contV + 1 ;
						if ( i % 3 == 1 ) contV = contV - 1 ;
					}
					System.out.println("Resultado correcto: " + contV);
				}catch (InterruptedException e){
					System.out.println ("ha ocurrido una excepcion.");
				}
				break;
			case 3:
				parametros[0]="4";
				parametros[1]="10";
				try{
					int num_hebras, num_iters;
					if ( parametros.length != 2 ){
						System.err.println("parámetros: número_de_hebras horas_totales \n  utilizando 4 y 50 por defecto");
						num_hebras = 4;
						num_iters=50;
					}else{
						num_hebras = Integer.parseInt(parametros[0]);
						num_iters  = Integer.parseInt(parametros[1]);
					}
					System.out.println("creando el contador de dias\n");
					Contador_dias contdias = new Contador_dias();
					Usuario[] usuarios = new Usuario[num_hebras];
					for( int i = 0 ; i < usuarios.length; i++){
						usuarios[i] =  new Usuario(contdias, num_iters,"usuario "+i);
						System.out.println("creando la hebra " + (i+1) + " y lanzándola\n");
						usuarios[i].thr.start();
					}
					for(int i = 0; i < usuarios.length; i++)
						usuarios[i].thr.join();
						System.out.println("unidas todas las hebras");
					}catch(Exception e){
						System.err.println("excepción: " + e);
						e.printStackTrace();
				}
				break;
			case 4:
				parametros[0]="3";
				parametros[1]="4";
				parametros[2]="4";
				parametros[3]="5";
				if ( parametros.length != 4 ){
					System.err.println("Uso: num_lectores num_escritores num_iters_lector num_iters_escritor");
			        return ;
			    }// leer parametros, crear vectores, crear monitor 
			    Lector[]   vlec = new Lector[ Integer.parseInt(parametros[0]) ];
			    Escritor[] vesc = new Escritor[ Integer.parseInt(parametros[1]) ];
			    int iter_lector = Integer.parseInt(parametros[2]);
			    int iter_escritor = Integer.parseInt(parametros[3]);
			    MonitorLE monitor = new MonitorLE();
			    // crear hebras
			    for( int i = 0; i < vlec.length; i++) 
			      vlec[i] = new Lector(monitor, iter_lector,"lector"+(i+1));
			    for( int i = 0; i < vesc.length; i++) 
			      vesc[i] = new Escritor(monitor, iter_escritor,"escritor"+(i+1));
			    // lanzar hebras
			    for( int i = 0; i < vlec.length ; i++) vlec[i].thr.start();
			    for( int i = 0; i < vesc.length ; i++) vesc[i].thr.start();
				break;
			case 5:
				parametros[0]="3";
				parametros[1]="4";
				parametros[2]="4";
				parametros[3]="5";
				parametros[4]="3";
				if ( parametros.length != 5 ){
					System.err.println("Uso: ncons nprod tambuf niterp niterc");
					return ;
				}
				// leer parametros, crear vectores y buffer intermedio
				Consumidor[] cons = new Consumidor[Integer.parseInt(parametros[0])] ;
				Productor[]  prod = new Productor[Integer.parseInt(parametros[1])] ;
				Buffer buffer = new Buffer(Integer.parseInt(parametros[2]));
				int iter_cons = Integer.parseInt(parametros[3]);
				int iter_prod = Integer.parseInt(parametros[4]);
				if ( cons.length*iter_cons != prod.length*iter_prod ){
					System.err.println("no coinciden número de items a producir con a cosumir");
					break ;
				}    
				// crear hebras
				for(int i = 0; i < cons.length; i++) 
					cons[i] = new Consumidor(buffer,iter_cons,i) ;
				for(int i = 0; i < prod.length; i++)
					prod[i] = new Productor(buffer,iter_prod,i) ;
				// poner en marcha las hebras
				for(int i = 0; i < prod.length; i++) prod[i].thr.start();
				for(int i = 0; i < cons.length; i++) cons[i].thr.start();
				break;
			case 6:
				parametros[0]="2";
				parametros[1]="2";
				parametros[2]="3";
				parametros[3]="fifo";
				parametros[4]="5";
				parametros[5]="5";
			case 7:
				if (Integer.parseInt(parametros[0])==0){
					parametros[0]="2";
					parametros[1]="2";
					parametros[2]="3";
					parametros[3]="lifo";
					parametros[4]="5";
					parametros[5]="5";
				}
				if ( parametros.length != 6 ){
					System.err.println("Uso: ncons nprod tambuf tipobuf niterp niterc");
					return ;
				}
				// leer parametros, crear vectores y buffer intermedio
				ConsumidorH[] cons1 = new ConsumidorH[Integer.parseInt(parametros[0])] ; //lista de consumidores
				ProductorH[]  prod1 = new ProductorH[Integer.parseInt(parametros[1])] ; // lista de productores
				Object bufferFL = null;
				if (parametros[3].equalsIgnoreCase("fifo")){
					bufferFL = new BufferFifo(Integer.parseInt(parametros[2]));
				}else if (parametros[3].equalsIgnoreCase("lifo")){
					bufferFL = new BufferLifo(Integer.parseInt(parametros[2]));
				}
	
				int iter_cons1 = Integer.parseInt(parametros[4]);
				int iter_prod1 = Integer.parseInt(parametros[5]);
				if ( cons1.length*iter_cons1 != prod1.length*iter_prod1 ){
					System.err.println("no coinciden número de items a producir con a cosumir");
					break ;
				}    
				// crear hebras
				for(int i = 0; i < cons1.length; i++)
					cons1[i] = new ConsumidorH(bufferFL,iter_cons1,i);
				for(int i = 0; i < prod1.length; i++)
					prod1[i] = new ProductorH(bufferFL,iter_prod1,i);
				// poner en marcha las hebras
				for(int i = 0; i < prod1.length; i++)
					prod1[i].thr.start();
				for(int i = 0; i < cons1.length; i++)
					cons1[i].thr.start();
				parametros = null;
				break;
			case 8:
				int nFumadores = 3;
				Estanco estanco = new Estanco();
				Estanquero  estanquero = new Estanquero(estanco);
				estanquero.thr.start();
				Vector<Fumador> fumadores = new Vector<Fumador>();
				for (int w=0;w<nFumadores;w++){
					Fumador fumador = new Fumador(w,estanco);
					fumador.thr.start();
					fumadores.add(fumador);
				}
				int count = 0;
				while (count < 20){
					for (Fumador fum : fumadores)
						System.out.println("El " + fum.miNombre + " esta " + fum.miEstado);
					try {
						Thread.sleep(6000);
					} catch (InterruptedException e) {
						System.out.println("Error en Programa Principal: " + e);
					}
					count++;
				}
				break;
			case 9:
				int nClientes = 5;
				Barberia barberia = new Barberia();
				Barbero  barbero = new Barbero(barberia);
				barbero.thr.start();
				Vector<Cliente> clientes = new Vector<Cliente>();
				for (int w=0;w<nClientes;w++){
					Cliente cliente = new Cliente(barberia,w);
					cliente.thr.start();
					clientes.add(cliente);
				}
				break;
			default:
				System.out.println("opcion no valida");
				System.exit(1);
			}
		}
	}
}