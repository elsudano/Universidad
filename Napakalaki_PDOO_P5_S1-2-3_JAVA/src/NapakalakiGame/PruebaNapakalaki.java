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

/**
 * @author Carlos de la Torre
 *
 */
public class PruebaNapakalaki {
	private static Napakalaki mNapakalaki = Napakalaki.getInstance();
	private static NapakalakiView mNapakalakiView = new NapakalakiView();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Dice.createInstance(mNapakalakiView);
		mNapakalakiView.setModel(mNapakalaki);
		PlayerNamesCapture namesCaptures = new PlayerNamesCapture(mNapakalakiView, true);
		mNapakalaki.initGame(namesCaptures.getNames());
		mNapakalakiView.Show();
	}
}
