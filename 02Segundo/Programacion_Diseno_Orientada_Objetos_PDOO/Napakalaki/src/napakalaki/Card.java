package napakalaki;

/**
 * Interface que sirve para agregar metodos basicos
 * a las diferentes clases del juego, y de esta forma
 * que haya una uniformidad en los diferentes métodos
 * de las clases
 * @author: Carlos de la Torre
 */
public interface Card {
    public String getName();
    public int getBasicValue(); 
    public int getSpecialValue();
}
