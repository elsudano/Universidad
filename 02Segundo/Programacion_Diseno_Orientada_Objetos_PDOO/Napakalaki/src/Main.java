

import gui.Dice;
import gui.NapakalakiView;
import gui.PlayerNamesCapture;
import napakalaki.Napakalaki;

public class Main {

    public static void main(String[] args) {
        /**
         * Creamos el objeto que contendra la vista del programa.
         */
        NapakalakiView napakalakiView = new NapakalakiView();
        /**
         * Creamos el controlador que gestionara la logica del programa.
         */
        Napakalaki game = Napakalaki.getInstance();
        /**
         * Creamos la unica instancia del dado que habrá en el programa
         * esto lo hacemos aquí por que es el único sitio que se inicializa
         * el dado.
         */
        Dice.createInstance (napakalakiView);
        /**
         * Creamos un objeto que será el que capture el nombre de los jugadores
         * tiene que ir en este orden para que funcione correctamente.
         */
        PlayerNamesCapture namesCapture = new PlayerNamesCapture(napakalakiView, true);
        /**
         * Creamos un array de Strings que será el que contenga todos los nombres
         * y lo inicializamos con los datos que capturamos desde el objeto anterior
         * si nos damos cuenta lo que devuelve el metodo getName() del objeto
         * anterior es un array de Strings con los nombres.
         * 
         * esta variable se puede odviar, puesto que la función se puede llamar
         * mientras se esta iniciando el juego.
         */
        //ArrayList<String> names = namesCapture.getNames();
        /**
         * Ahora inicializamos el juego con los datos de los jugadores que ya
         * hemos capturado con anterioridad.
         */
        game.initGame(namesCapture.getNames());
        /**
         * Asignamos el controlador a la vista, hay que tener en cuenta que el
         * controlador tiene que estar inicializado para que contenga los datos
         * que queremos pasarle a la vista, ya que si no lo hacemos en este
         * orden habrá datos que necesite la vista y que estén a NULL.
         */
        napakalakiView.setNapakalaki(game);
        /**
         * Le decimos a la ventana de la vista que se muestre en la pantalla,
         * una vez que ya lo tenemos todo inicializado enseñamos la interfaz.
         */
        napakalakiView.setVisible (true);
    }
}
