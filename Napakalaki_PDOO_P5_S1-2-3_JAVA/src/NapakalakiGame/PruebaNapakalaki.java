/**
 * Clase Principal del Proyecto
 * que sería como si fuera la clase controlador del proyecto
 * seria la que soporta todas las llamadas que se realizan
 * por parte del usuario y la que realiza las llamadas hacia
 * las otras clase para que se ejecute el programa.
 */

package NapakalakiGame;

import gui.NapakalakiView;
import gui.PlayerNamesCapture;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author Carlos de la Torre
 *
 */
public class PruebaNapakalaki {
	// Definición de la variable in que nos va a permitir leer String desde teclado.
	private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));	   
	/*
	 * Ejemplos de lectura desde teclado con in.
	 * String s = in.readLine();//Lectura de un String
	 * double d= Double.parseDouble(in.readLine());// lectura de un duoble
	 * int i = Integer.parseInt(in.readLine());// lectura de un int
	 * Estado estado = Estado.valueOf(in.readLine()); // lectura de un enum
	 */
	private static Napakalaki mNapakalaki = Napakalaki.getInstance();
	private static NapakalakiView mNapakalakiView = new NapakalakiView();

	/**
	 * Con esta función podemos escribir por pantalla
	 * el objeto monstruo que le pasamos por 
	 * parametros pero con las cabeceras de los 
	 * campos.
	 * @param miMonstruo es el objeto monstruo que queremos imprimir por pantalla 
	 */
	private static void mostrarMonstruoFormateado(Monster miMonstruo){
		System.out.println("Nombre del Monstruo: " + miMonstruo.getName());
		System.out.println("Nivel de Combate contra Monstruo: " + miMonstruo.getBasicValue());
		if (miMonstruo.getLevelChangeAgainstCultistPlayer()>0)
			System.out.println("Nivel de Combate contra Sectario: " + miMonstruo.getSpecialValue());
		Prize precioMonstruo = miMonstruo.mPrize;
		System.out.println("Tesoros que podemos ganar: " + precioMonstruo.getTreasures());
		System.out.println("Cantidad de niveles que conseguimos por ganar: " + precioMonstruo.getLevels());
		BadStuff MalRolloMonstruo = miMonstruo.mBadStuff;
		System.out.println("Texto MR: " + MalRolloMonstruo.getText());
		System.out.println("Niveles de MR que perdemos: " + MalRolloMonstruo.getLevels());
		System.out.println("String: " + MalRolloMonstruo.toString() + "\n");
	}

	/**
	 * Con esta función podemos escribir por pantalla
	 * el objeto tesoro que le pasamos por 
	 * parametros pero con las cabeceras de los 
	 * campos.
	 * @param miTesoro es el objeto tesoro que queremos imprimir por pantalla 
	 */
	private static void mostrarTesoroFormateado(Treasure miTesoro){
		System.out.println("Nombre del Tesoro: " + miTesoro.getName());
		System.out.println("Cantidad de Monedas de Oro: " + miTesoro.getGoldCoins());
		System.out.println("Cantidad Minima de Bonus: " + miTesoro.getMinBonus());
		System.out.println("Cantidad Maxima de Bonus: " + miTesoro.getMaxBonus());
		System.out.println("Tipo de Tesoro que conseguimos: " + miTesoro.mType + "\n");
	}
	
	/**
	 * Con esta función podemos escribir por pantalla
	 * el objeto Cultist que le pasamos por 
	 * parametros pero con las cabeceras de los 
	 * campos.
	 * @param miCultistPlayer es el objeto tesoro que queremos imprimir por pantalla 
	 */
	private static void mostrarSectarioFormateado(Cultist miCultistCard){
		System.out.println("Nombre de la Carta: " + miCultistCard.getName());
		System.out.println("Niveles de la Carta: " + miCultistCard.getGainedLevels() + "\n");
	}
	
	/**
	 * Esta es una funcion auxiliar que nos sirve
	 * para mostrar un submenu en la consola
	 */
	private static void submenu(){
		int opcion2=0;
		String eleccion2 = "";
		try {
			System.out.println("1. Solamente los que tengan un nivel de Combate mayor que 10\n"+
					"2. Que en el Mal Rollo se pierdan niveles\n"+
					"3. Que en el Buen Rollo se ganen mas de 1 nivel\n"+
					"4. Que en el Mal Rollo pierdan un determinado tesoro\n");
			eleccion2 = in.readLine();
			if (eleccion2=="") throw new Exception("Introduzca un numero del 1 al 5 para continuar");
			opcion2 = Integer.parseInt(eleccion2); // lectura de un int.
			switch(opcion2){
				case 1:
					for (Monster monstruo : mNapakalaki.getDealer().mUnusedMonsters){
						if (monstruo.getLevel()>10)
							mostrarMonstruoFormateado(monstruo);
					}
					break;
				case 2:
					for (Monster monstruo : mNapakalaki.getDealer().mUnusedMonsters){
						if (monstruo.mBadStuff.getLevels()!=0)
							mostrarMonstruoFormateado(monstruo);
					}
					break;
				case 3:
					for (Monster monstruo : mNapakalaki.getDealer().mUnusedMonsters){
						if (monstruo.mPrize.getLevels()>1)
							mostrarMonstruoFormateado(monstruo);
					}
					break;
				case 4:
					for (Monster monstruo : mNapakalaki.getDealer().mUnusedMonsters){
						if (!monstruo.mBadStuff.mSpecificHiddenTreasures.isEmpty() && !monstruo.mBadStuff.mSpecificVisibleTreasures.isEmpty())
							mostrarMonstruoFormateado(monstruo);
					}
					break;
			}
		}catch(Exception ex){ // captura de la excepción
			System.err.println(ex);
	    }
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int opcion=0; //variables que nos controlan las opciónes de menú.
		String eleccion="";
		Monster monstruo = null;
		do{ //do-while que controlara todo el menú
			try{// tratamiento de las excepciones
				//mostrar menú(usando System.out.println(...)) con las siguientes opciones
				
				System.out.println("1. Crear Objeto 'Prize',asignar valores, mostrar valores\n"+
						"2. Crear el Objeto Mal Rollo y llenar sus datos\n"+
						"3. Crear el Objeto Monstruo y llenar sus datos\n"+
						"4. Mostrar Monstruos pero...\n"+
						"5. Mostrar todos los Monstruos\n"+
						"6. Mostrar todos los Tesoros\n"+
						"7. Mostrar todos los Sectarios\n"+
						"8. Probar Aplicación\n"+
						"9. Probar Aplicación entorno gráfico\n"+
						"\n"+
						"0. Terminar Aplicación");
				//eleccion = in.readLine();
				eleccion = "9"; // elige por defecto la opcion 9 sin quitar el menu
				if (eleccion=="") throw new Exception("Introduzca un numero del 1 al 9 para continuar");
				opcion = Integer.parseInt(eleccion); // lectura de un int.
				if (opcion<0 || opcion>9) throw new Exception("La opción elegida no es valida");
				// Montar un switch con todas las opciones de menú.
				switch(opcion){
					case 1:
						System.out.println("Creando objeto 'Prize'\n");
						System.out.println("Introduzca el numero de Tesoros\n"
								+ "pulse enter y luego el numero para el\n"
								+ "nivel pulse enter para terminar\n");
						Prize precioMonstruo = new Prize(Integer.parseInt(in.readLine()),Integer.parseInt(in.readLine()));
						System.out.println("Objeto Creado\n");
						System.out.println("Valor de los tesoros del objeto creado: " + precioMonstruo.getTreasures() + "\n");
						System.out.println("Valor del nivel del objeto creado: " + precioMonstruo.getLevels() + "\n");
						System.out.println("probando metodo toString:\n" + precioMonstruo.toString());
						break;
					case 2:
						System.out.println("Creando objeto 'MalRollo'\n");
						System.out.println("Introduzca el concepto del Mal Rollo\n"
								+ "pulse enter y luego el numero para los niveles\n"
								+ "pulse enter y luego el numero para los Tesoros Visibles\n"
								+ "pulse enter y luego el numero para los Tesoros Ocultos\n"
								+ "pulse enter para terminar\n");
						BadStuff mRollo = new BadStuff(in.readLine(),Integer.parseInt(in.readLine()),Integer.parseInt(in.readLine()),Integer.parseInt(in.readLine()));
						System.out.println("Objeto 1 Creado\n");
						System.out.println("Valor del Mal Rollo del objeto creado: " + mRollo.getText() + "\n");
						System.out.println("Valor de los niveles del objeto creado: " + mRollo.getLevels() + "\n");
						System.out.println("probando metodo toString:\n" + mRollo.toString());
						break;
					case 3:
						System.out.println("Creando objeto 'Monstruo'\n");
						System.out.println("Introduzca el numero de Tesoros\n"
								+ "pulse enter y luego el numero para el nivel\n"
								+ "pulse enter y introduzca el concepto del Mal Rollo\n"
								+ "pulse enter y luego el numero para los niveles\n"
								+ "pulse enter y luego el numero para los Tesoros Visibles\n"
								+ "pulse enter y luego el numero para los Tesoros Ocultos\n"
								+ "pulse enter y el nombre del monstruo\n"
								+ "pulse enter y y por ultimo el valor del nivel del monstruo\n"
								+ "pulse enter para terminar\n");
						Prize precioMonstruo1 = new Prize(Integer.parseInt(in.readLine()),Integer.parseInt(in.readLine()));
						BadStuff MalRolloMonstruo = new BadStuff(in.readLine(),Integer.parseInt(in.readLine()),Integer.parseInt(in.readLine()),Integer.parseInt(in.readLine()));
						monstruo = new Monster(in.readLine(),Integer.parseInt(in.readLine()),MalRolloMonstruo,precioMonstruo1,0);
						System.out.println("Objeto monstruo Creado\n");
						System.out.println("Valor del nivel de conbate del mostruo creado: " + monstruo.getLevel() + "\n");
						System.out.println("Valor del nombre del monstruo creado: " + monstruo.getName() + "\n");
						System.out.println("probando metodo toString:\n" + monstruo.toString());
						break;
					case 4:
						submenu();
						break;
					case 5:
						for (Monster m : mNapakalaki.getDealer().mUnusedMonsters){
							mostrarMonstruoFormateado(m);
						}
						break;
					case 6:
						for (Treasure t : mNapakalaki.getDealer().mUnusedTreasures){
							mostrarTesoroFormateado(t);
						}
						break;
					case 7:
						for (Cultist s : mNapakalaki.getDealer().mUnusedCultists){
							mostrarSectarioFormateado(s);
						}
						break;
					case 8:
						ArrayList<String> Jugadores = new ArrayList<>(2);
						System.out.println("Para iniciar el Juego introduzca los nombres de 2 Jugadores:\n");
						System.out.println("Jugador1:");
						Jugadores.add(in.readLine());
						System.out.println("Jugador2:");
						Jugadores.add(in.readLine());
						Dice.createInstance(mNapakalakiView);
						mNapakalaki.initGame(Jugadores);
						mNapakalakiView.setModel(mNapakalaki);
						mNapakalakiView.Show();
						break;
					case 9:
						Dice.createInstance(mNapakalakiView);
						mNapakalakiView.setModel(mNapakalaki);
						PlayerNamesCapture namesCaptures = new PlayerNamesCapture(mNapakalakiView, true);
						mNapakalaki.initGame(namesCaptures.getNames());
						mNapakalakiView.Show();
						
						break;
				}
			}catch(Exception ex){ // captura de la excepción
				System.err.println(ex);
	        } 
		}while(opcion !=0);    
		System.out.println("Saliendo de la Aplicación");
	} // de main(...)
}
