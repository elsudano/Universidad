package model;

import controler.MainController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Carlos de al Torre
 */
public class DialogFXML {

    /**
     * Variable que almacena el titulo de la ventana.
     */
    private String titulo;
    /**
     * Variable que almacena la ruta del icono de la ventana.
     */
    private String icono;
    /**
     * Variable que almacena la ruta del fichero de la vista.
     */
    private String fxml;

    /**
     * Constructor con solo la vista.
     *
     * @param f[in] String donde se encuentra la vista.
     */
    public DialogFXML(String f) {
        this.fxml = f;
    }

    /**
     * Constructor con la vista y el título.
     *
     * @param f[in] String donde se encuentra la vista.
     * @param t[in] String cuál es el título de la ventana
     */
    public DialogFXML(String f, String t) {
        this.fxml = f;
        this.titulo = t;
    }

    /**
     * Constructor con la vista el título y el icono de la ventana.
     *
     * @param f[in] String donde se encuentra la vista.
     * @param t[in] String cuál es el título de la ventana.
     * @param i[in] String donde se encuentra el icono.
     */
    public DialogFXML(String f, String t, String i) {
        this.fxml = f;
        this.titulo = t;
        this.icono = i;
    }

    /**
     * Metodo que mostrará la ventana con la forma que se ha especificado en el
     * FXML.
     */
    public void show() {
        try {
            // cargamos la vista que queremos mostrar
            Parent root = FXMLLoader.load(getClass().getResource(this.fxml));
            // creamos la escena que vamos a mostrar
            Scene scene = new Scene(root);
            // creamos el escenario donde vamos a poner la escena
            Stage stage = new Stage();
            // le damos un nombre al escenario
            if (!this.titulo.isEmpty()) {
                stage.setTitle(this.titulo);
            }
            // le ponemos el icono a la ventana
            if (!this.icono.isEmpty()) {
                stage.getIcons().add(new Image(getClass().getResourceAsStream(this.icono)));
            }
            // ponemos los controles en la escena
            stage.setScene(scene);
            // ponemos la ventana para que no se pueda maximizar
            stage.setResizable(false);
            // lo mostramos
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
