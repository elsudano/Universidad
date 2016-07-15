package napakalaki;

import gui.Dice;
import test.GameTester;
import gui.NapakalakiView;

public class EjemploMain {

    public static void main(String[] args) {
        NapakalakiView vista = new NapakalakiView();
        Napakalaki game = Napakalaki.getInstance();
        game.setVista(vista);
        Dice.createInstance(vista);
        GameTester test = GameTester.getInstance();
        
        // Poner el numero de jugadores con el que se quiera probar
        test.play(game, vista, 2);
        game = null;
        vista = null;
    }
}
