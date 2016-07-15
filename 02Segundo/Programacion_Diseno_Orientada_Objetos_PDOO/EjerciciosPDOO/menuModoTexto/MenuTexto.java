package menuModoTexto;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;

public class MenuTexto {
	private static final MenuTexto miBarMenu = new MenuTexto();
	private Scanner entrada = new Scanner(System.in);
	private ArrayList<MenuItem> Menus;
	
	//CONSTRUCTOR
	private MenuTexto(){
		this.Menus = new ArrayList<MenuItem>();
	}
	
	public static MenuTexto getInstancia(){
		return miBarMenu;
	}
	
	private void mostrarMenus(){
		if (!Menus.isEmpty()){
			for (MenuItem menu : Menus)
				System.out.println(Menus.indexOf(menu)+". "+menu.getName());
		}else
			throw new EmptyStackException();
	}
	
	private int buscarMenu(String pMenu){
		int mPos = 0;
		for (MenuItem mMenu : Menus){
			if (mMenu.getName().contentEquals(pMenu))
				mPos = Menus.indexOf(mMenu);
		}
		return mPos;
	}

	public void nuevoMenuItem(String pNombre){
		MenuItem mMenu = new MenuItem(pNombre);
		this.Menus.add(mMenu);
	}
	
	public void nuevoMenuItem(String pNombre, boolean pFuncion){
		MenuItem mMenu = new MenuItem(pNombre,pFuncion);
		this.Menus.add(mMenu);
	}
	
	public void nuevoMenuOption(String pMenu, String pNombre, int pFuncion){
		MenuItem mMenu = this.Menus.get(buscarMenu(pMenu));
		mMenu.newOpcionItem(pNombre,pFuncion);
	}
	
	public void nuevoMenuOption(String pMenu, int pPos, String pNombre, int pFuncion){
		MenuItem mMenu = this.Menus.get(buscarMenu(pMenu));
		mMenu.newOpcionItem(pPos-1, pNombre, pFuncion);
	}

	public void listarMenuCompleto(){
		if (!Menus.isEmpty()){
			for (MenuItem menu : Menus){
				System.out.println(Menus.indexOf(menu)+1+". "+menu.getName());
				for (int c=0;c<menu.size();c++)
					System.out.println(c+1+".\t"+menu.getOpcionItem(c).getNombre());
			}
		}else
			throw new EmptyStackException();
	}
	
	public int seleccionarOpcion(){
		this.mostrarMenus();
		int opcion = entrada.nextInt();
		entrada.nextLine();
		if (opcion>this.Menus.size()-1)
			System.exit(1);
		if (this.Menus.get(opcion).getFuncion())
			System.exit(1);	
		return Menus.get(opcion).seleccionarOpcion();
	}
}
