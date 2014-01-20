package menuModoTexto;

class Pair<F,S> {
    private F first;
    private S second;

    public Pair(F first, S second) { 
        this.first = first;
        this.second = second;
    }

    public F getFirst() { return this.first; }
    public S getSecond() { return this.second; }
}

class OpcionItem{
	private Pair<String,Integer> NombreItem;
	
	//CONSTRUCTOR
	public OpcionItem(){
		this.NombreItem = new Pair<String, Integer>("Salir", 0);
	}
	
	public OpcionItem(String pNombre){		
		this.NombreItem = new Pair<String, Integer>(pNombre, 0);
	}
	
	public OpcionItem(String pNombre, int pFuncion){
		this.NombreItem = new Pair<String, Integer>(pNombre, pFuncion);
	}
	
	//METODOS
	public String getNombre(){
		return this.NombreItem.getFirst();
	}
	
	public int getFuncion(){
		return this.NombreItem.getSecond();
	}
	
	public boolean isEmpty(){
		boolean valor = true;
		if (!this.NombreItem.getFirst().contentEquals(""))
			valor = false;
		return valor;
	}
}