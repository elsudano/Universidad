package model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Carlos de al Torre
 */
public class PaintBasico extends javafx.application.Application {
    /**
     * 
     * @param stage
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/gui/main.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Paint Básico");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/icon.png")));
        stage.show();
    }

    /**
     * Este es el hilo principal de la aplicación.
     * @param args los argumentos pasado por parámetros
     */
    public static void main(String[] args) {
        // esto lo ponemos para que no se puedan pasar argumentos a la aplicación
        javafx.application.Application.launch(PaintBasico.class, (java.lang.String[])null);
    }
    
}
