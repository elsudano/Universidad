package controler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.input.TouchEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.DialogFXML;
import model.ResizeCanvas;

/**
 * FXML Controller class
 *
 * @author Carlos de al Torre
 */
public class MainController implements Initializable {

    /**
     * Esta es la variable que guarda el lienzo auto-escalable.
     */
    private ResizeCanvas paneLienzo;

    @FXML
    private ToggleButton buttonPunto;
    @FXML
    private ToggleButton buttonLinea;
    @FXML
    private Menu menuArchivo;
    @FXML
    private MenuItem menuItemNuevo;
    @FXML
    private MenuItem menuItemAbrir;
    @FXML
    private MenuItem menuItemGuardar;
    @FXML
    private MenuItem menuItemSalir;
    @FXML
    private ImageView imagePunto;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuEditar;
    @FXML
    private MenuItem menuItemCortar;
    @FXML
    private ImageView imageCortar;
    @FXML
    private MenuItem menuItemCopiar;
    @FXML
    private ImageView imageCopiar;
    @FXML
    private MenuItem menuItemPegar;
    @FXML
    private ImageView imagePegar;
    @FXML
    private ToolBar toolBarUp;
    @FXML
    private ToggleGroup buttonGroup;
    @FXML
    private ToggleButton buttonRectangulo;
    @FXML
    private ImageView imageRectangulo;
    @FXML
    private ToggleButton buttonOval;
    @FXML
    private ImageView imageOval;
    @FXML
    private TextFlow statusBar;
    @FXML
    private CheckMenuItem checkMenuItemPantallaCompleta;
    @FXML
    private MenuItem menuItemAcerca;
    @FXML
    private ImageView imagenAcerca;
    @FXML
    private Menu menuVer;
    @FXML
    private Menu menuAyuda;
    @FXML
    private FlowPane panelLateral;
    @FXML
    private Button buttonRojo;
    @FXML
    private Button buttonVerde;
    @FXML
    private Button buttonAzul;
    @FXML
    private Button buttonBlanco;
    @FXML
    private Button buttonNegro;
    @FXML
    private Button buttonAmarillo;
    @FXML
    private BorderPane mainWindow;
    @FXML
    private ImageView imageLinea;
    @FXML
    private Pane contentLienzo;
    @FXML
    private Label labelTamano;
    @FXML
    private Slider sliderTamano;
    @FXML
    private CheckBox checkboxRelleno;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        assert buttonPunto != null : "fx:id=\"buttonPunto\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonLinea != null : "fx:id=\"buttonLinea\" was not injected: check your FXML file 'main.fxml'.";
        assert menuArchivo != null : "fx:id=\"menuArchivo\" was not injected: check your FXML file 'main.fxml'.";
        assert menuItemNuevo != null : "fx:id=\"menuItemNuevo\" was not injected: check your FXML file 'main.fxml'.";
        assert menuItemAbrir != null : "fx:id=\"menuItemAbrir\" was not injected: check your FXML file 'main.fxml'.";
        assert menuItemGuardar != null : "fx:id=\"menuItemGuardar\" was not injected: check your FXML file 'main.fxml'.";
        assert menuItemSalir != null : "fx:id=\"menuItemSalir\" was not injected: check your FXML file 'main.fxml'.";
        assert imagePunto != null : "fx:id=\"imagePunto\" was not injected: check your FXML file 'main.fxml'.";
        assert menuBar != null : "fx:id=\"menuBar\" was not injected: check your FXML file 'main.fxml'.";
        assert menuEditar != null : "fx:id=\"menuEditar\" was not injected: check your FXML file 'main.fxml'.";
        assert menuItemCortar != null : "fx:id=\"menuItemCortar\" was not injected: check your FXML file 'main.fxml'.";
        assert imageCortar != null : "fx:id=\"imageCortar\" was not injected: check your FXML file 'main.fxml'.";
        assert menuItemCopiar != null : "fx:id=\"menuItemCopiar\" was not injected: check your FXML file 'main.fxml'.";
        assert imageCopiar != null : "fx:id=\"imageCopiar\" was not injected: check your FXML file 'main.fxml'.";
        assert menuItemPegar != null : "fx:id=\"menuItemPegar\" was not injected: check your FXML file 'main.fxml'.";
        assert imagePegar != null : "fx:id=\"imagePegar\" was not injected: check your FXML file 'main.fxml'.";
        assert toolBarUp != null : "fx:id=\"toolBarUp\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonGroup != null : "fx:id=\"buttonGroup\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonRectangulo != null : "fx:id=\"buttonRectangulo\" was not injected: check your FXML file 'main.fxml'.";
        assert imageRectangulo != null : "fx:id=\"imageRectangulo\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonOval != null : "fx:id=\"buttonOval\" was not injected: check your FXML file 'main.fxml'.";
        assert imageOval != null : "fx:id=\"imageOval\" was not injected: check your FXML file 'main.fxml'.";
        assert statusBar != null : "fx:id=\"statusBar\" was not injected: check your FXML file 'main.fxml'.";
        assert checkMenuItemPantallaCompleta != null : "fx:id=\"checkMenuItemPantallaCompleta\" was not injected: check your FXML file 'main.fxml'.";
        assert menuItemAcerca != null : "fx:id=\"menuItemAcerca\" was not injected: check your FXML file 'main.fxml'.";
        assert imagenAcerca != null : "fx:id=\"imagenAcerca\" was not injected: check your FXML file 'main.fxml'.";
        assert menuVer != null : "fx:id=\"menuVer\" was not injected: check your FXML file 'main.fxml'.";
        assert menuAyuda != null : "fx:id=\"menuAyuda\" was not injected: check your FXML file 'main.fxml'.";
        assert panelLateral != null : "fx:id=\"panelLateral\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonRojo != null : "fx:id=\"buttonRojo\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonVerde != null : "fx:id=\"buttonVerde\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonAzul != null : "fx:id=\"buttonAzul\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonBlanco != null : "fx:id=\"buttonBlanco\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonNegro != null : "fx:id=\"buttonNegro\" was not injected: check your FXML file 'main.fxml'.";
        assert buttonAmarillo != null : "fx:id=\"buttonAmarillo\" was not injected: check your FXML file 'main.fxml'.";
        assert mainWindow != null : "fx:id=\"mainWindow\" was not injected: check your FXML file 'main.fxml'.";
        assert imageLinea != null : "fx:id=\"imageLinea\" was not injected: check your FXML file 'main.fxml'.";
        assert contentLienzo != null : "fx:id=\"contentLienzo\" was not injected: check your FXML file 'main.fxml'.";
        assert labelTamano != null : "fx:id=\"labelTamano\" was not injected: check your FXML file 'main.fxml'.";
        assert sliderTamano != null : "fx:id=\"sliderTamano\" was not injected: check your FXML file 'main.fxml'.";
        assert checkboxRelleno != null : "fx:id=\"checkboxRelleno\" was not injected: check your FXML file 'main.fxml'.";

        sliderTamano.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                paneLienzo.setSize(newValue.intValue());
                actualizaStatus();
            }
        });

        // creamos el lienzo auto-scalable
        this.paneLienzo = new ResizeCanvas();
        // lo añadimos dentro de la escena
        this.contentLienzo.getChildren().add(paneLienzo);
        // le damos el tamaño que tienen el contenedor del lienzo
        this.paneLienzo.widthProperty().bind(contentLienzo.widthProperty());
        this.paneLienzo.heightProperty().bind(contentLienzo.heightProperty());
        // rescalar el paneLienzo al tamaño del lienzo
        this.paneLienzo.resize(contentLienzo.getWidth(), contentLienzo.getHeight());
    }

    /**
     * Metódo que se encarga de actualizar los datos de la barra de estado,
     * siempre que se realice algún cambio.
     */
    private void actualizaStatus() {
        statusBar.getChildren().clear();
        Text t;
        if (paneLienzo.isFill()) {
            t = new Text("Herramienta: " + paneLienzo.getShape() + " Color: " + paneLienzo.getColor().toString() + " Tamaño: " + paneLienzo.getSize() + " Está Relleno: SI");
        } else {
            t = new Text("Herramienta: " + paneLienzo.getShape() + " Color: " + paneLienzo.getColor().toString() + " Tamaño: " + paneLienzo.getSize() + " Está Relleno: NO");
        }
        statusBar.getChildren().add(t);
    }

    /**
     * Metódo que se ejecuta cuando realizamos alguna acción encima del menu
     * Archivo/nuevo.
     *
     * @param event contiene el evento que se ha producido en el menu.
     */
    @FXML
    private void OnActionMenuItemNuevo(ActionEvent event) {
        this.paneLienzo.clean();
        actualizaStatus();
    }

    /**
     * Metódo que se ejecuta cuando realizamos alguna acción encima del menu
     * Archivo/abrir.
     *
     * @param event contiene el evento que se ha producido en el menu.
     */
    @FXML
    private void OnActionMenuItemAbrir(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir un Fichero: ");
        fileChooser.showOpenDialog(mainWindow.getScene().getWindow());
    }

    /**
     * Metódo que se ejecuta cuando realizamos alguna acción encima del menu
     * Archivo/guardar.
     *
     * @param event contiene el evento que se ha producido en el menu.
     */
    @FXML
    private void OnActionMenuItemGuardar(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar un Fichero: ");
        fileChooser.showSaveDialog(mainWindow.getScene().getWindow());
    }

    /**
     * Metódo que se ejecuta cuando realizamos alguna acción encima del menu
     * Archivo/salir.
     *
     * @param event contiene el evento que se ha producido en el menu.
     */
    @FXML
    private void OnActionMenuItemSalir(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Metódo que se encarga de mostrar el Acerca de de la aplicación
     *
     * @param event contiene el evento que se ha producido en el menu.
     */
    @FXML
    private void OnActionMenuItemAcerca(ActionEvent event) {
        DialogFXML myDialogo = new DialogFXML("/gui/acercade.fxml", "Acerca de...", "/resources/icon.png");
        myDialogo.show();
    }

    /**
     * Metódo que se ejecuta cuando realizamos alguna acción encima del botón de
     * la herramienta para pintar puntos.
     *
     * @param event contiene el evento que se ha producido en el botón.
     */
    @FXML
    private void OnActionToggleButtonPunto(ActionEvent event) {
        this.paneLienzo.setShape("point");
        actualizaStatus();
    }

    /**
     * Metódo que se ejecuta cuando realizamos alguna acción encima del botón de
     * la herramienta para pintar lineas.
     *
     * @param event contiene el evento que se ha producido en el botón.
     */
    @FXML
    private void OnActionToggleButtonLinea(ActionEvent event) {
        this.paneLienzo.setShape("line");
        actualizaStatus();
    }

    /**
     * Metódo que se ejecuta cuando realizamos alguna acción encima del botón de
     * la herramienta para pintar rectangulos.
     *
     * @param event contiene el evento que se ha producido en el botón.
     */
    @FXML
    private void OnActionToggleButtonRectangulo(ActionEvent event) {
        this.paneLienzo.setShape("rect");
        actualizaStatus();
    }

    /**
     * Metódo que se ejecuta cuando realizamos alguna acción encima del botón de
     * la herramienta para pintar circulos.
     *
     * @param event contiene el evento que se ha producido en el botón.
     */
    @FXML
    private void OnActionToggleButtonOval(ActionEvent event) {
        this.paneLienzo.setShape("oval");
        actualizaStatus();
    }

    /**
     * Metódo cuando pulsamos sobre el botón que tiene el color rojo
     *
     * @param event contiene el evento que se ha producido en el botón.
     */
    @FXML
    private void OnActionButtonRojo(ActionEvent event) {
        this.paneLienzo.setColor(Color.RED);
        actualizaStatus();
    }

    /**
     * Metódo cuando pulsamos sobre el botón que tiene el color verde
     *
     * @param event contiene el evento que se ha producido en el botón.
     */
    @FXML
    private void OnActionButtonVerde(ActionEvent event) {
        this.paneLienzo.setColor(Color.GREEN);
        actualizaStatus();
    }

    /**
     * Metódo cuando pulsamos sobre el botón que tiene el color azul
     *
     * @param event contiene el evento que se ha producido en el botón.
     */
    @FXML
    private void OnActionButtonAzul(ActionEvent event) {
        this.paneLienzo.setColor(Color.BLUE);
        actualizaStatus();
    }

    /**
     * Metódo cuando pulsamos sobre el botón que tiene el color blanco
     *
     * @param event contiene el evento que se ha producido en el botón.
     */
    @FXML
    private void OnActionButtonBlanco(ActionEvent event) {
        this.paneLienzo.setColor(Color.WHITE);
        actualizaStatus();
    }

    /**
     * Metódo cuando pulsamos sobre el botón que tiene el color negro
     *
     * @param event contiene el evento que se ha producido en el botón.
     */
    @FXML
    private void OnActionButtonNegro(ActionEvent event) {
        this.paneLienzo.setColor(Color.BLACK);
        actualizaStatus();
    }

    /**
     * Metódo cuando pulsamos sobre el botón que tiene el color Amarillo
     *
     * @param event contiene el evento que se ha producido en el botón.
     */
    @FXML
    private void OnActionButtonAmarillo(ActionEvent event) {
        this.paneLienzo.setColor(Color.YELLOW);
        actualizaStatus();
    }

    /**
     * Metódo que se ejecuta cuando soltamos el botón del ratón encima del
     * Lienzo de pintura.
     *
     * @note Utilizo estos metodos por que todavia no se construir manejadores
     *
     * @param event contiene el evento que se ha producido en el ratón.
     */
    @FXML
    private void OnMousePressedPaneLienzo(MouseEvent event) {
        this.paneLienzo.setPointBegin(event.getX(), event.getY());
        this.paneLienzo.repaint();
//        Alert alert = new Alert(AlertType.INFORMATION);
//        alert.setTitle("Dialogo de Información");
//        alert.setHeaderText("Por Favor mira la información del dialogo...");
//        alert.setContentText("Con esto comprobamos que la pulsación se realiza correctamente");
//        alert.showAndWait();
    }

    /**
     * Metódo que se ejecuta cuando pulsamos el botón del ratón encima del
     * Lienzo de pintura.
     *
     * @note Utilizo estos metodos por que todavia no se construir manejadores
     *
     * @param event contiene el evento que se ha producido en el ratón.
     */
    @FXML
    private void OnMouseReleasedPaneLienzo(MouseEvent event) {
        this.paneLienzo.setPointEnd(event.getX(), event.getY());
        this.paneLienzo.repaint();
    }

    /**
     * Metódo que se ejecuta cuando arrastramos el ratón con el botón pulsado
     * encima del panelLienzo de pintura.
     *
     * @note Utilizo estos metodos por que todavia no se construir manejadores
     * @param event contiene el evento que se ha producido en el ratón.
     */
    @FXML
    private void OnMouseDraggedPaneLienzo(MouseEvent event) {
        this.paneLienzo.setPointEnd(event.getX(), event.getY());
        this.paneLienzo.repaint();
    }

    /**
     * Metódo que se ejecuta cuando usamos el menu de pantalla completa.
     *
     * @param event contiene el evento que se ha producido en el ratón.
     */
    @FXML
    private void OnActionMenuItemPantallaCompleta(ActionEvent event) {
        Stage stage = (Stage) this.mainWindow.getScene().getWindow();
        stage.setFullScreen(!stage.isFullScreen());
    }

    @FXML
    private void OnActionCheckBoxRelleno(ActionEvent event) {
        this.paneLienzo.setFill(this.checkboxRelleno.isSelected());
        actualizaStatus();
    }

    /**
     * Metódo que se ejecuta tanto cuando usamos la ruleta del ratón encima del
     * slider del tamaño como encima de la etiqueta del tamaño.
     *
     * @param event contiene el evento que se ha producido en el ratón.
     */
    @FXML
    private void OnScrollSliderTamano(ScrollEvent event) {
        //TODO
        actualizaStatus();
    }
}
