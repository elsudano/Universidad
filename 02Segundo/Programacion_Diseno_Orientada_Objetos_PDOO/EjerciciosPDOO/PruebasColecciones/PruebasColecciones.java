package PruebasColecciones;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


public class PruebasColecciones {
	private static final PruebasColecciones mInstancia = new PruebasColecciones();
	// CONSTRUCTOR
	/*
	 * Se pone el constructor en privado para que no se pueda
	 * hacer un new y se tiene que llamar usar getInstancia
	 * para poder usar el objeto en cuestion.
	 */
	private PruebasColecciones() {

	}
	
	// METODOS
	/*
	 * Con este metodo en concreto lo que hacemos es que 
	 * cuando lo llamamos ejecutamos el ejercicio en cuestion
	 */
	public void HashSetTest(){
		// EJEMPLO 1: ¿Por qué da error el siguiente trozo de código? ¿cómo lo
		// arreglarías?
		ArrayList<Integer> saco = new ArrayList<Integer>();
		saco.add(1234);

		// EJEMPLO 2: Ejecuta el siguiente trozo de código y comprueba cuál es
		// el resultado
		HashSet<String> conjunto = new HashSet<String>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		};
		conjunto.add("Esto");
		conjunto.add("es");
		conjunto.add("una");
		conjunto.add("prueba");
		conjunto.add("con");
		conjunto.add("una");
		conjunto.add("colección");

		Iterator<String> it = conjunto.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		// EJEMPLO 3: Ahora ejecuta este otro... ¿cuál es la diferencia?
		ArrayList<String> conjunto2 = new ArrayList<String>();
		conjunto2.add("Esto");
		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
		accounts.add(new BankAccount(1001));
		accounts.add(new BankAccount(1015));
		accounts.add(new BankAccount(1729));
		accounts.add(1, new BankAccount(1008));
		accounts.remove(0);

		System.out.println("size=" + accounts.size());
		BankAccount first = accounts.get(0);
		System.out.println("first account number=" + first.getAccountNumber());
		BankAccount last = accounts.get(accounts.size() - 1);
		System.out.println("last account number=" + last.getAccountNumber());
		conjunto2.add("es");
		conjunto2.add("una");
		conjunto2.add("prueba");
		conjunto2.add("con");
		conjunto2.add("una");
		conjunto2.add("colección");

		Iterator<String> it2 = conjunto2.iterator();
		while (it2.hasNext()) {
			System.out.println(it2.next());
		}
	}
	
    // EJEMPLO 4: Ejecuta el método main en ArrayListTester.java con el depurador,
    // ¿qué ocurre en cada paso?
	public void ArrayListTest(){
		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
		accounts.add(new BankAccount(1001));
		accounts.add(new BankAccount(1015));
		accounts.add(new BankAccount(1729));
		accounts.add(1, new BankAccount(1008));
		accounts.remove(0);
		
		System.out.println("size=" + accounts.size());
		BankAccount first = accounts.get(0);
		System.out.println("first account number=" + first.getAccountNumber());
		BankAccount last = accounts.get(accounts.size() - 1);
		System.out.println("last account number=" + last.getAccountNumber());
	}

    // EJEMPLO 5: Ejecuta LinkedList.java y responde a las preguntas que allí se proponen
	public void LinkedListTest(){
		List<String> a = new LinkedList<String>();
		a.add("Amy");
		a.add("Carl");
		a.add("Erica");

		List<String> b = new LinkedList<String>();
		b.add("Bob");
		b.add("Doug");
		b.add("Frances");
		b.add("Gloria");

		ListIterator<String> aIter = a.listIterator();
		Iterator<String> bIter = b.iterator();

		// PARTE 1: Descomenta el System.out y comprueba qué hace este trozo de
		// código
		while (bIter.hasNext()) {
			if (aIter.hasNext())
				aIter.next();
			aIter.add(bIter.next()); // < ¿qué hace aIter.add(...)?¿podría
										// ponerse a.add(bIter.next())?
		}
		// System.out.println(a);
		// ¿Cómo podríamos haber añadido los elementos de b al comienzo/final de
		// la lista a?

		// PARTE 2: Descomenta el System.out y comprueba qué hace el siguiente
		// trozo de código
		bIter = b.iterator();
		while (bIter.hasNext()) {
			bIter.next();
			if (bIter.hasNext()) {
				bIter.next();
				bIter.remove();
			}
		}
		// System.out.println(b);

		// PARTE 3: Comprueba el funcionamiento del método removeAll
		// System.out.println(a);
		a.removeAll(b);
		// System.out.println(a);
	}
	
    // EJEMPLO 6: Comprueba el funcionamiento de las tablas hash ejecutando MapTest.java, responde a las preguntas que se proponen
	public void MapTest(){
		Map<String, Employee> staff = new HashMap<String, Employee>();
		staff.put("144-25-5464", new Employee("Amy Lee"));
		staff.put("567-24-2546", new Employee("Harry Hacker"));
		staff.put("157-62-7935", new Employee("Gary Cooper"));
		staff.put("456-62-5527", new Employee("Francesca Cruz"));

		System.out.println(staff); // < ¿Qué hace? ¿cómo?

		staff.remove("567-24-2546");
		staff.put("456-62-5527", new Employee("Francesca Miller"));
		// Comprueba cuál es el resultado de estas dos instrucciones
		// descomentando
		// el System.out
		// System.out.println(staff);

		// System.out.println(staff.get("157-62-7935")); //< ¿qué imprime?

		// ¿Qué hace este bucle for?¿para qué podría interesar usarlo en lugar
		// de
		// System.out.println(staff);?
		for (Map.Entry<String, Employee> entry : staff.entrySet()) {
			String key = entry.getKey();
			Employee value = entry.getValue();
			System.out.println("key=" + key + ", value=" + value);
		}
	}
	
    // EJEMPLO 7: Comprueba el uso de los métodos de ordenación de Collections ejecutando ShuffleTest.java
	public void ShuffleTest(){
		List<Integer> numbers = new ArrayList<Integer>();
		for (int i = 1; i <= 49; i++)
			numbers.add(i);
		Collections.shuffle(numbers);
		List<Integer> winningCombination = numbers.subList(0, 6);
		Collections.sort(winningCombination);
		System.out.println(winningCombination);
	}
	
    // EJEMPLO 8: Comprueba el uso de los comparadores ejecutando TreeSetTest.java
	public void TreeSetTest(){
		SortedSet<Item> parts = new TreeSet<Item>();
		parts.add(new Item("Toaster", 1234));
		parts.add(new Item("Widget", 4562));
		parts.add(new Item("Modem", 9912));
		System.out.println(parts);

		SortedSet<Item> sortByDescription = new TreeSet<Item>(
				new Comparator<Item>() {
					public int compare(Item a, Item b) {
						String descrA = a.getDescription();
						String descrB = b.getDescription();
						return descrA.compareTo(descrB);
					}
				});

		sortByDescription.addAll(parts);
		System.out.println(sortByDescription);
	}
	
	/**
	 * @return el parametro mInstancia
	 */
	public static PruebasColecciones getInstancia() {
		return mInstancia;
	}  
}


/**
 *An item with a description and a part number.
 */
class Item implements Comparable<Item> {
	/**
	 * Constructs an item.
	 * 
	 * @param aDescription the item's description
	 * @param aPartNumber the item's part number
	 */
	public Item(String aDescription, int aPartNumber) {
		description = aDescription;
		partNumber = aPartNumber;
	}

	/**
	 * Gets the description of this item.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	public String toString() {
		return "[descripion=" + description + ", partNumber=" + partNumber + "]";
	}

	public boolean equals(Object otherObject) {
		if (this == otherObject)
			return true;
		if (otherObject == null)
			return false;
		if (getClass() != otherObject.getClass())
			return false;
		Item other = (Item) otherObject;
		return description.equals(other.description)
				&& partNumber == other.partNumber;
	}

	public int hashCode() {
		return 13 * description.hashCode() + 17 * partNumber;
	}

	public int compareTo(Item other) {
		return partNumber - other.partNumber;
	}

	private String description;
	private int partNumber;
}
