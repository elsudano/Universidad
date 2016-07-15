package menuModoTexto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;

class MenuItem {
	private String NombreMenu;
	private boolean Funcion;
	private Scanner entrada = new Scanner(System.in);
	private ArrayList<OpcionItem> Opciones;
	
	//CONSTRUCTOR
	public MenuItem(){
		 this.NombreMenu = "item";
		 this.Funcion = false;
		 this.Opciones = new ArrayList<OpcionItem>();
	}
	
	public MenuItem(String pNombre){
		 this.NombreMenu = pNombre;
		 this.Funcion = false;
		 this.Opciones = new ArrayList<OpcionItem>();
	}
	
	public MenuItem(String pNombre, boolean pFuncion){
		 this.NombreMenu = pNombre;
		 this.Funcion = pFuncion;
		 this.Opciones = new ArrayList<OpcionItem>();
	}
	
	private void mostrarItems(){
		try{
			String os = System.getProperty("os.name");
			if (os.contains("Windows"))
				Runtime.getRuntime().exec("cls");
			else
				Runtime.getRuntime().exec("clear");
		}catch(IOException e){
			e.printStackTrace();
		}
		if (!Opciones.isEmpty()){
			for (OpcionItem opcion : Opciones)
				System.out.println(Opciones.indexOf(opcion)+1+".\t"+opcion.getNombre());
		}else
			throw new EmptyStackException();
	}

	private void separator(){
		OpcionItem mOpcion = new OpcionItem("-------------------");
		this.Opciones.add(mOpcion);
	}

	//METODOS
	public void setName(String pNombre){
		this.NombreMenu = pNombre;
	}

	public void newOpcionItem(String pNombre){
		OpcionItem item = new OpcionItem(pNombre);
		this.Opciones.add(item);
	}
	
	public void newOpcionItem(String pNombre, int pFuncion){
		OpcionItem item = new OpcionItem(pNombre,pFuncion);
		this.Opciones.add(item);
	}
	
	public void newOpcionItem(int pPos, String pNombre){
		if (Opciones.size()<pPos){
			for (int c=Opciones.size();c<pPos;c++)
				this.separator();
		}
		OpcionItem item = new OpcionItem(pNombre);
		this.Opciones.add(pPos, item);
	}
	
	public void newOpcionItem(int pPos, String pNombre, int pFuncion){
		if (Opciones.size()<pPos){
			for (int c=Opciones.size();c<pPos;c++)
				this.separator();
		}
		OpcionItem item = new OpcionItem(pNombre,pFuncion);
		this.Opciones.add(pPos, item);
	}
	
	public OpcionItem getOpcionItem(int pPos){
		return this.Opciones.get(pPos);
	}
	
	public String getName(){
		return this.NombreMenu;
	}

	public boolean getFuncion(){
		return this.Funcion;
	}

	public int size() {
		return Opciones.size();
	}

	public boolean isEmpty(){
		return this.Opciones.isEmpty();
	}
	
	public int seleccionarOpcion(){
		this.mostrarItems();
		int opcion = entrada.nextInt();
		entrada.nextLine();
		return Opciones.get(opcion-1).getFuncion();
	}
}