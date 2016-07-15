package gui;

import gui.image.VentanaInternaImagen;
import gui.componets.ColorComboBoxEditor;
import gui.componets.ColorComboBoxModel;
import gui.componets.ColorComboBoxRender;
import gui.componets.Item;
import gui.modal.QuestionsColors;
import gui.modal.QuestionsRecordOptions;
import gui.modal.QuestionsRescaleImage;
import gui.modal.QuestionsResizeImage;
import gui.modal.QuestionsSelectImages;
import gui.modal.QuestionsSizeImage;
import gui.others.AcercaDe;
import gui.video.VentanaInternaJMFPlayer;
import gui.video.VentanaInternaVLCPlayer;
import gui.video.VentanaInternaWebcam;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.color.ColorSpace;
import java.awt.color.ICC_ColorSpace;
import java.awt.color.ICC_Profile;
import java.awt.event.ItemEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BandCombineOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.LookupOp;
import java.awt.image.LookupTable;
import java.awt.image.RescaleOp;
import java.awt.image.ShortLookupTable;
import java.awt.image.WritableRaster;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import sm.cdlt.graphics.myDiscontinuity;
import sm.cdlt.graphics.myRectangle;
import sm.cdlt.graphics.myRectangleShape;
import sm.cdlt.images.EqualizationOP;
import sm.cdlt.images.LookupTableProducer;
import sm.cdlt.util.Utils;
import sm.cdlt.images.SepiaOP;
import sm.cdlt.images.SobelOP;
import sm.cdlt.images.TintOP;
import sm.cdlt.images.UmbralizationOP;
import sm.cdlt.images.mySecondEfectOP;
import sm.cdlt.multimedia.ClipPlayer;
import sm.cdlt.multimedia.Multimedia;
import sm.cdlt.multimedia.SoundPlayerRecorder;
import sm.cdlt.multimedia.ExtendedVideoPlayer;
import sm.cdlt.util.File;
import sm.cdlt.util.FileNameExtensionFilter;
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

/**
 * Clase principal para la interfaz gráfica del programa, contiene todos los
 * controles necesarios, para llevar a cabo todas las operaciones del modelo.
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de al Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de al Torre</a>
 * created on 10-abr-2016
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Variable de depuración
     */
    public static final boolean DEBUG = false;
    /**
     * Valor mínimo que pueden tomar los campos sppiners.
     */
    public static final int MIN_VALUE_SPPINER = 4;
    /**
     * Valor máximo que pueden tomar los campos sppiners.
     */
    public static final int MAX_VALUE_SPPINER = 99;
    /**
     * Variable que controla la pantalla completa
     */
    private boolean FULLSCREEN = false;
    /**
     * Variable que permite Deshacer y Rehacer una acción.
     */
    public static BufferedImage UNDO, REDO;
    /**
     * Variable que se usa para poder acceder a las traducciones.
     */
    private static ResourceBundle bundle;
    /**
     * Guarda el directorio actual en donde abrimos o guardamos los ficheros
     */
    private String dir_actual;
    /**
     * variable que guarda la opción de grabación que ha elegido el usuario
     */
    private String optionRecording;
    /**
     * Sonido actual que esta listo para reproducirse, es de tipo Object por que
     * se utiliza la librería de vlcj y para poder usar la JMF también con la
     * misma variable hace falta ponerla así.
     */
    private Object multimedia_actual;
    /**
     * Contenedor de ficheros multimedia
     *
     * //@TODO IMPORTANTE quitar el public
     */
    public ArrayList<Item> multimedia_files;
    /**
     * Manejador que se encarga de los sonidos.
     */
    private SoundListener mySoundListener;
    /**
     * Modelo de datos para la lista de sonidos
     */
    private DefaultListModel<String> list_model = new DefaultListModel<String>();
    /**
     * Contador de ventanas sin título. Se podrían contar las ventanas del
     * escritorio pero así es mas rápido. Cuando se cierra una ventana sin
     * titulo hay que ver que se hace
     */
    public static int COUNT_VI = 1;
    /**
     * Son los filtros para los cuadros de dialogo de abrir y guardar
     */
    private final FileNameExtensionFilter[] filters = new FileNameExtensionFilter[14];
    private final int ALL_FORMAT_IMAGE = 0;
    private final int JPEG_FILE = 1;
    private final int PNG_FILE = 2;
    private final int GIF_FILE = 3;
    private final int ALL_FORMAT_SOUND = 4;
    private final int WAV_FILE = 5;
    private final int AU_FILE = 6;
    private final int AIFF_FILE = 7;
    private final int MP3_FILE = 8;
    private final int ALL_FORMAT_VIDEO = 9;
    private final int AVI_FILE = 10;
    private final int MPG_FILE = 11;
    private final int MP4_FILE = 12;
    private final int MKV_FILE = 13;
    /**
     * Variable que muestra la ventana activa.
     */
    private JInternalFrame vi;
    /**
     * Manejador que se encarga de ventanas internas de la aplicación.
     */
    private VentanaInternaListener myVIListener;
    /**
     * Variable que se encarga de almacenar una versión original de la imagen
     * que estamos modificando para poder realizar un deshacer, y también la
     * temporal donde hacemos las modificaciones.
     */
    private BufferedImage imgOrigen, imgTemp;
    /**
     * Tiempo total de los ficheros de audio
     */
    private long total_time;

    /**
     * Creates new form PaintBasico
     */
    public VentanaPrincipal() {
        initComponents();
        myInitComponents();
    }

    /**
     * Método que nos sirve para poder poner el idioma a la interfaz de manera
     * manual, normalmente JAVA lee las propiedades del sistema para asignar un
     * idioma a la aplicación, pero en este caso lo asignamos nosotros.
     */
    private void changeLocaleUI() {
        this.jButtonNuevo.setToolTipText(bundle.getString("NEW_TT"));
        this.jButtonAbrir.setToolTipText(bundle.getString("OPEN_TT"));
        this.jButtonGuardar.setToolTipText(bundle.getString("SAVE_TT"));
        this.jButtonSalir.setToolTipText(bundle.getString("EXIT"));
        this.jCheckBoxMenuItemPantallaCompleta.setText(bundle.getString("FULL_SCREEN"));
        this.jCheckBoxMenuItemVerBarraEstado.setText(bundle.getString("STATUS_BAT_TEXT"));
        this.jCheckBoxMenuItemVerBarraHerramientas.setText(bundle.getString("TOOL_BAR_TEXT"));
        this.jCheckBoxMenuItemVerBarraUtilidades.setText(bundle.getString("UTIL_BAR_TEXT"));
        this.jComboBoxColor.setToolTipText(bundle.getString("COLOR_TT"));
        this.jComboBoxFiltros.setToolTipText(bundle.getString("FILTER_TT"));
        this.jLabelAccionRaton.setText(bundle.getString("MOUSE"));
        if (this.vi instanceof VentanaInternaImagen) {
            Color aux_color = ((VentanaInternaImagen) this.vi).getLienzo().getColor();
            this.jLabelColor.setText(bundle.getString("COLOR")
                    + "[R: " + aux_color.getRed()
                    + " G: " + aux_color.getGreen()
                    + " B: " + aux_color.getBlue()
                    + "]");
        }
        this.jLabelHerramienta.setText(bundle.getString("TOOL_POINT"));
        this.jLabelMousePos.setText(bundle.getString("MOUSE_POS"));
        this.jMIBrightnessContrast.setText(bundle.getString("BRIGHTNESS_CONTRAST"));
        this.jMenuArchivo.setText(bundle.getString("FILE"));
        this.jMenuAyuda.setText(bundle.getString("HELP"));
        this.jMenuEditar.setText(bundle.getString("EDIT"));
        this.jMenuFilters.setText(bundle.getString("FILTERS"));
        this.jMenuIdioma.setText(bundle.getString("LANGUAGE"));
        this.jMenuImagen.setText(bundle.getString("IMAGE"));
        this.jMenuItemAbrir.setText(bundle.getString("OPEN"));
        this.jMenuItemAyuda.setText(bundle.getString("ABOUT"));
        this.jMenuItemBlurBinomial.setText(bundle.getString("BLUR_BINOMIAL"));
        this.jMenuItemBlurMedio.setText(bundle.getString("BLUR_BINOMIAL"));
        this.jMenuItemBorder.setText(bundle.getString("FRONTIER_DETECT"));
        this.jMenuItemClone.setText(bundle.getString("CLONE"));
        this.jMenuItemCopiar.setText(bundle.getString("COPY"));
        this.jMenuItemCortar.setText(bundle.getString("CUT"));
        this.jMenuItemCustom.setText(bundle.getString("CUSTOM"));
        this.jMenuItemDeshacer.setText(bundle.getString("UNDO"));
        this.jMenuItemEmboss.setText(bundle.getString("EMBOSS"));
        this.jMenuItemFocus.setText(bundle.getString("FOCUS"));
        this.jMenuItemFrontier.setText(bundle.getString("FRONTIER"));
        this.jMenuItemGuardar.setText(bundle.getString("SAVE"));
        this.jMenuItemNegative.setText(bundle.getString("NEGATIVO"));
        this.jMenuItemNuevo.setText(bundle.getString("NUEVO"));
        this.jMenuItemPegar.setText(bundle.getString("PASTE"));
        this.jMenuItemRehacer.setText(bundle.getString("REDO"));
        this.jMenuItemRelief.setText(bundle.getString("RELIEF"));
        this.jMenuItemResizeImage.setText(bundle.getString("RESIZE_IMAGE"));
        this.jMenuItemRotate180.setText(bundle.getString("ROTATE_180"));
        this.jMenuItemRotateLeft.setText(bundle.getString("ROTATE_LEFT"));
        this.jMenuItemRotateRight.setText(bundle.getString("ROTATE_RIGHT"));
        this.jMenuItemSalir.setText(bundle.getString("EXIT"));
        this.jMenuItemScaleImage.setText(bundle.getString("SCALE_IMAGE"));
        this.jMenuItemSenoFunction.setText(bundle.getString("SENOFUNCTION"));
        this.jMenuItemSharpens.setText(bundle.getString("SHARPENS"));
        this.jMenuItemSuavizado.setText(bundle.getString("GAUSSIANO"));
        this.jMenuOpciones.setText(bundle.getString("OPTIONS"));
        this.jMenuRotate.setText(bundle.getString("ROTATE"));
        this.jMenuVer.setText(bundle.getString("VIEW"));
        this.jRadioButtonMenuItemEN.setText(bundle.getString("ENGLISH"));
        this.jRadioButtonMenuItemES.setText(bundle.getString("SPANISH"));
        this.jSliderBrigthness.setToolTipText(bundle.getString("BRIGHTNESS_TT"));
        this.jSliderContrast.setToolTipText(bundle.getString("CONTRAST_TT"));
        this.jSliderRotate.setToolTipText(bundle.getString("ROTATE_TT"));
        this.jSliderZoom.setToolTipText(bundle.getString("ZOOM_TT"));
        this.jSpinnerGrosorLinea.setToolTipText(bundle.getString("STROKE_TT"));
        this.jSpinnerTransparencia.setToolTipText(bundle.getString("TRANS_TT"));
        this.jToggleButtonAntiAliasing.setToolTipText(bundle.getString("ANTIALIASING_TT"));
        this.jToggleButtonBorrar.setToolTipText(bundle.getString("DELETE_TT"));
        this.jToggleButtonCirculo.setToolTipText(bundle.getString("CIRCLE_TT"));
        this.jToggleButtonLinea.setToolTipText(bundle.getString("LINE_TT"));
        this.jToggleButtonMover.setToolTipText(bundle.getString("MOVE_TT"));
        this.jToggleButtonPintar.setToolTipText(bundle.getString("PAINT_TT"));
        this.jToggleButtonPunto.setToolTipText(bundle.getString("POINT_TT"));
        this.jToggleButtonRectangulo.setToolTipText(bundle.getString("RECT_TT"));
        this.jToggleButtonRelleno.setToolTipText(bundle.getString("FILL_TT"));
        // casos especiales
        this.jPanelPrevio.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("PREVIEW")));
        this.jPanelBrilloContraste.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("BRIGHTNESS_CONTRAST")));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupTools = new javax.swing.ButtonGroup();
        buttonGroupIdioma = new javax.swing.ButtonGroup();
        buttonGroupOpciones = new javax.swing.ButtonGroup();
        buttonGroupSound = new javax.swing.ButtonGroup();
        buttonGroupSoundOptions = new javax.swing.ButtonGroup();
        jPanelContentIcons = new javax.swing.JPanel();
        jPanelContentBars = new javax.swing.JPanel();
        jToolBarTools = new javax.swing.JToolBar();
        jButtonNuevo = new javax.swing.JButton();
        jButtonAbrir = new javax.swing.JButton();
        jButtonGuardar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jToggleButtonPintar = new javax.swing.JToggleButton();
        jToggleButtonMover = new javax.swing.JToggleButton();
        jToggleButtonBorrar = new javax.swing.JToggleButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jToggleButtonPunto = new javax.swing.JToggleButton();
        jToggleButtonLinea = new javax.swing.JToggleButton();
        jToggleButtonQuadCurve = new javax.swing.JToggleButton();
        jToggleButtonCubicCurve = new javax.swing.JToggleButton();
        jToggleButtonRectangulo = new javax.swing.JToggleButton();
        jToggleButtonRectanguloRound = new javax.swing.JToggleButton();
        jToggleButtonCirculo = new javax.swing.JToggleButton();
        jToggleButtonPolygon = new javax.swing.JToggleButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jLabelImageColor = new javax.swing.JLabel();
        jComboBoxColor = new javax.swing.JComboBox<>();
        jSeparator16 = new javax.swing.JToolBar.Separator();
        jLabelImageTipo = new javax.swing.JLabel();
        jComboBoxTipoLinea = new javax.swing.JComboBox<>();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jLabelImageGrosor = new javax.swing.JLabel();
        jSpinnerGrosorLinea = new javax.swing.JSpinner();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jLabelTransparente = new javax.swing.JLabel();
        jSpinnerTransparencia = new javax.swing.JSpinner();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        jToggleButtonRelleno = new javax.swing.JToggleButton();
        jToggleButtonAntiAliasing = new javax.swing.JToggleButton();
        jToolBarSound = new javax.swing.JToolBar();
        jToggleButtonPlay = new javax.swing.JToggleButton();
        jToggleButtonStop = new javax.swing.JToggleButton();
        jSeparator14 = new javax.swing.JToolBar.Separator();
        jToggleButtonShow = new javax.swing.JToggleButton();
        jButtonWebcam = new javax.swing.JButton();
        jButtonTakePhoto = new javax.swing.JButton();
        jSeparator15 = new javax.swing.JToolBar.Separator();
        jToggleButtonRecord = new javax.swing.JToggleButton();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(6, 0), new java.awt.Dimension(6, 0), new java.awt.Dimension(6, 32767));
        jLabelElapsedTimeRecord = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 25), new java.awt.Dimension(0, 25), new java.awt.Dimension(0, 32767));
        jButtonSalir = new javax.swing.JButton();
        jPanelStatusBar = new javax.swing.JPanel();
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jLabelHerramienta = new javax.swing.JLabel();
        jLabelColor = new javax.swing.JLabel();
        jLabelAccionRaton = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabelMousePos = new javax.swing.JLabel();
        jLabelMouseColorPos = new javax.swing.JLabel();
        jLabelMouseViewColorPos = new javax.swing.JLabel();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 5));
        jSplitPaneCenter = new javax.swing.JSplitPane();
        jPanelSplitDerecho = new javax.swing.JPanel();
        jPanelHistogram = new javax.swing.JPanel();
        jPanelPrevio = new javax.swing.JPanel();
        jPanelZoom = new javax.swing.JPanel();
        jLabelImageUpZoom = new javax.swing.JLabel();
        jSliderZoom = new javax.swing.JSlider();
        jLabelImageDownZoom = new javax.swing.JLabel();
        jPanelRotar = new javax.swing.JPanel();
        jLabelImageRotateLeft = new javax.swing.JLabel();
        jSliderRotate = new javax.swing.JSlider();
        jLabelImageRotateRight = new javax.swing.JLabel();
        jPanelBrilloContraste = new javax.swing.JPanel();
        jPBrilloSlider = new javax.swing.JPanel();
        jLabelImageDownBrigthness = new javax.swing.JLabel();
        jSliderBrigthness = new javax.swing.JSlider();
        jLabelImageUpBrigthness = new javax.swing.JLabel();
        jPBrilloContraste = new javax.swing.JPanel();
        jLabelImageDownContrast = new javax.swing.JLabel();
        jSliderContrast = new javax.swing.JSlider();
        jLabelImageUpContrast = new javax.swing.JLabel();
        jPanelUmbral = new javax.swing.JPanel();
        jPanelUmbralSlider = new javax.swing.JPanel();
        jLabelImageDownUmbral = new javax.swing.JLabel();
        jSliderUmbral = new javax.swing.JSlider();
        jLabelImageUpUmbral = new javax.swing.JLabel();
        jButtonColorUmbral = new javax.swing.JButton();
        jComboBoxFiltros = new javax.swing.JComboBox<>();
        jDesktopEscritorio = new javax.swing.JDesktopPane();
        jPanelOeste = new javax.swing.JPanel();
        jPanelNorte = new javax.swing.JPanel();
        jScrollPanelSounds = new javax.swing.JScrollPane();
        jListSounds = new javax.swing.JList<>();
        jPanelProgressBar = new javax.swing.JPanel();
        jLabelElapsedTime = new javax.swing.JLabel();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(32767, 0));
        jProgressBarMultimedia = new javax.swing.JProgressBar();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(32767, 0));
        jLabelTotalTime = new javax.swing.JLabel();
        jPanelSur = new javax.swing.JPanel();
        jPanelOptions = new javax.swing.JPanel();
        jRadioButtonRepeatNone = new javax.swing.JRadioButton();
        jRadioButtonRepeatSound = new javax.swing.JRadioButton();
        jRadioButtonRepeatAll = new javax.swing.JRadioButton();
        jCheckBoxOpenPanel = new javax.swing.JCheckBox();
        jMenuBarra = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        jMenuItemNuevo = new javax.swing.JMenuItem();
        jMenuItemAbrir = new javax.swing.JMenuItem();
        jMenuItemGuardar = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSalir = new javax.swing.JMenuItem();
        jMenuEditar = new javax.swing.JMenu();
        jMenuItemDeshacer = new javax.swing.JMenuItem();
        jMenuItemRehacer = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        jMenuItemCortar = new javax.swing.JMenuItem();
        jMenuItemCopiar = new javax.swing.JMenuItem();
        jMenuItemPegar = new javax.swing.JMenuItem();
        jMenuVer = new javax.swing.JMenu();
        jCheckBoxMenuItemVerBarraHerramientas = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemVerBarraUtilidades = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemVerBarraEstado = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemVerBarraSonido = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItemPanelSonido = new javax.swing.JCheckBoxMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jCheckBoxMenuItemPantallaCompleta = new javax.swing.JCheckBoxMenuItem();
        jMenuPruebas = new javax.swing.JMenu();
        jMenuItemRescaleOP = new javax.swing.JMenuItem();
        jMenuItemConvolveOP = new javax.swing.JMenuItem();
        jMenuItemAffineTransformOp = new javax.swing.JMenuItem();
        jMenuItemLookupOp = new javax.swing.JMenuItem();
        jMenuItemBandCombineOp = new javax.swing.JMenuItem();
        jMenuItemColorConvertOp = new javax.swing.JMenuItem();
        jMenuItemVLCPlayer = new javax.swing.JMenuItem();
        jMenuImagen = new javax.swing.JMenu();
        jMIBrightnessContrast = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        jMenuItemConvertBlackWhite = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItemEqualization = new javax.swing.JMenuItem();
        jSeparator17 = new javax.swing.JPopupMenu.Separator();
        jMenuItemScaleImage = new javax.swing.JMenuItem();
        jMenuItemResizeImage = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jMenuRotate = new javax.swing.JMenu();
        jMenuItemCustom = new javax.swing.JMenuItem();
        jMenuItemRotateLeft = new javax.swing.JMenuItem();
        jMenuItemRotateRight = new javax.swing.JMenuItem();
        jMenuItemRotate180 = new javax.swing.JMenuItem();
        jMenuItemClone = new javax.swing.JMenuItem();
        jMenuItemSenoFunction = new javax.swing.JMenuItem();
        jMenuItemTintar = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        jMenuItemAdd = new javax.swing.JMenuItem();
        jMenuItemSubstract = new javax.swing.JMenuItem();
        jMenuItemMultiply = new javax.swing.JMenuItem();
        jMenuFilters = new javax.swing.JMenu();
        jMenuItemBlurMedio = new javax.swing.JMenuItem();
        jMenuItemBlurBinomial = new javax.swing.JMenuItem();
        jMenuItemFocus = new javax.swing.JMenuItem();
        jMenuItemRelief = new javax.swing.JMenuItem();
        jMenuItemBorder = new javax.swing.JMenuItem();
        jMenuItemEmboss = new javax.swing.JMenuItem();
        jMenuItemSharpens = new javax.swing.JMenuItem();
        jMenuItemFrontier = new javax.swing.JMenuItem();
        jMenuItemSuavizado = new javax.swing.JMenuItem();
        jMenuItemNegative = new javax.swing.JMenuItem();
        jMenuItemSepia = new javax.swing.JMenuItem();
        jMenuItemSolve = new javax.swing.JMenuItem();
        jMenuItemEfecto = new javax.swing.JMenuItem();
        jMenuOpciones = new javax.swing.JMenu();
        jMenuIdioma = new javax.swing.JMenu();
        jRadioButtonMenuItemES = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItemEN = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItemDE = new javax.swing.JRadioButtonMenuItem();
        jCheckBoxMenuItemSelectLibrary = new javax.swing.JCheckBoxMenuItem();
        jMenuAyuda = new javax.swing.JMenu();
        jMenuItemAyuda = new javax.swing.JMenuItem();

        FormListener formListener = new FormListener();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("internalization/Bundle"); // NOI18N
        setTitle(bundle.getString("APP_TITLE")); // NOI18N
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setName("Form"); // NOI18N

        jPanelContentIcons.setName("jPanelContentIcons"); // NOI18N
        jPanelContentIcons.setLayout(new java.awt.BorderLayout());

        jPanelContentBars.setName("jPanelContentBars"); // NOI18N
        jPanelContentBars.setLayout(new javax.swing.BoxLayout(jPanelContentBars, javax.swing.BoxLayout.LINE_AXIS));

        jToolBarTools.setMaximumSize(new java.awt.Dimension(800, 36));
        jToolBarTools.setMinimumSize(new java.awt.Dimension(800, 24));
        jToolBarTools.setPreferredSize(new java.awt.Dimension(800, 24));

        jButtonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/nuevo.png"))); // NOI18N
        jButtonNuevo.setToolTipText(bundle.getString("NEW_TT")); // NOI18N
        jButtonNuevo.setBorder(null);
        jButtonNuevo.setFocusable(false);
        jButtonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNuevo.addActionListener(formListener);
        jToolBarTools.add(jButtonNuevo);

        jButtonAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/abrir.png"))); // NOI18N
        jButtonAbrir.setToolTipText(bundle.getString("OPEN_TT")); // NOI18N
        jButtonAbrir.setBorder(null);
        jButtonAbrir.setFocusable(false);
        jButtonAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAbrir.addActionListener(formListener);
        jToolBarTools.add(jButtonAbrir);

        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/guardar.png"))); // NOI18N
        jButtonGuardar.setToolTipText(bundle.getString("SAVE_TT")); // NOI18N
        jButtonGuardar.setBorder(null);
        jButtonGuardar.setFocusable(false);
        jButtonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGuardar.addActionListener(formListener);
        jToolBarTools.add(jButtonGuardar);
        jToolBarTools.add(jSeparator3);

        buttonGroupOpciones.add(jToggleButtonPintar);
        jToggleButtonPintar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/pintar.png"))); // NOI18N
        jToggleButtonPintar.setSelected(true);
        jToggleButtonPintar.setToolTipText(bundle.getString("PAINT_TT")); // NOI18N
        jToggleButtonPintar.setBorder(null);
        jToggleButtonPintar.setFocusable(false);
        jToggleButtonPintar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonPintar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonPintar.addActionListener(formListener);
        jToolBarTools.add(jToggleButtonPintar);

        buttonGroupOpciones.add(jToggleButtonMover);
        jToggleButtonMover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/mover.png"))); // NOI18N
        jToggleButtonMover.setToolTipText(bundle.getString("MOVE_TT")); // NOI18N
        jToggleButtonMover.setBorder(null);
        jToggleButtonMover.setFocusable(false);
        jToggleButtonMover.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonMover.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonMover.addActionListener(formListener);
        jToolBarTools.add(jToggleButtonMover);

        buttonGroupOpciones.add(jToggleButtonBorrar);
        jToggleButtonBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/borrar.png"))); // NOI18N
        jToggleButtonBorrar.setToolTipText(bundle.getString("DELETE_TT")); // NOI18N
        jToggleButtonBorrar.setBorder(null);
        jToggleButtonBorrar.setFocusable(false);
        jToggleButtonBorrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonBorrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonBorrar.addActionListener(formListener);
        jToolBarTools.add(jToggleButtonBorrar);
        jToolBarTools.add(jSeparator4);

        buttonGroupTools.add(jToggleButtonPunto);
        jToggleButtonPunto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/punto.png"))); // NOI18N
        jToggleButtonPunto.setSelected(true);
        jToggleButtonPunto.setToolTipText(bundle.getString("POINT_TT")); // NOI18N
        jToggleButtonPunto.setBorder(null);
        jToggleButtonPunto.setFocusable(false);
        jToggleButtonPunto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonPunto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonPunto.addActionListener(formListener);
        jToolBarTools.add(jToggleButtonPunto);

        buttonGroupTools.add(jToggleButtonLinea);
        jToggleButtonLinea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/linea.png"))); // NOI18N
        jToggleButtonLinea.setToolTipText(bundle.getString("LINE_TT")); // NOI18N
        jToggleButtonLinea.setBorder(null);
        jToggleButtonLinea.setFocusable(false);
        jToggleButtonLinea.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonLinea.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonLinea.addActionListener(formListener);
        jToolBarTools.add(jToggleButtonLinea);

        buttonGroupTools.add(jToggleButtonQuadCurve);
        jToggleButtonQuadCurve.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/poly_linea1.png"))); // NOI18N
        jToggleButtonQuadCurve.setToolTipText(bundle.getString("LINE_CURVE_TT")); // NOI18N
        jToggleButtonQuadCurve.setBorder(null);
        jToggleButtonQuadCurve.setFocusable(false);
        jToggleButtonQuadCurve.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonQuadCurve.setName("jToggleButtonQuadCurve"); // NOI18N
        jToggleButtonQuadCurve.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonQuadCurve.addActionListener(formListener);
        jToolBarTools.add(jToggleButtonQuadCurve);

        buttonGroupTools.add(jToggleButtonCubicCurve);
        jToggleButtonCubicCurve.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/poly_linea.png"))); // NOI18N
        jToggleButtonCubicCurve.setToolTipText(bundle.getString("LINE_CURVE2_TT")); // NOI18N
        jToggleButtonCubicCurve.setBorder(null);
        jToggleButtonCubicCurve.setFocusable(false);
        jToggleButtonCubicCurve.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonCubicCurve.setName("jToggleButtonCubicCurve"); // NOI18N
        jToggleButtonCubicCurve.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonCubicCurve.addActionListener(formListener);
        jToolBarTools.add(jToggleButtonCubicCurve);

        buttonGroupTools.add(jToggleButtonRectangulo);
        jToggleButtonRectangulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rectangulo.png"))); // NOI18N
        jToggleButtonRectangulo.setToolTipText(bundle.getString("RECT_TT")); // NOI18N
        jToggleButtonRectangulo.setBorder(null);
        jToggleButtonRectangulo.setFocusable(false);
        jToggleButtonRectangulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonRectangulo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonRectangulo.addActionListener(formListener);
        jToolBarTools.add(jToggleButtonRectangulo);

        buttonGroupTools.add(jToggleButtonRectanguloRound);
        jToggleButtonRectanguloRound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rectangulo_redondo.png"))); // NOI18N
        jToggleButtonRectanguloRound.setToolTipText(bundle.getString("RECT_ROUND_TT")); // NOI18N
        jToggleButtonRectanguloRound.setBorder(null);
        jToggleButtonRectanguloRound.setFocusable(false);
        jToggleButtonRectanguloRound.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonRectanguloRound.setName("jToggleButtonRectanguloRound"); // NOI18N
        jToggleButtonRectanguloRound.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonRectanguloRound.addActionListener(formListener);
        jToolBarTools.add(jToggleButtonRectanguloRound);

        buttonGroupTools.add(jToggleButtonCirculo);
        jToggleButtonCirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/elipse.png"))); // NOI18N
        jToggleButtonCirculo.setToolTipText(bundle.getString("CIRCLE_TT")); // NOI18N
        jToggleButtonCirculo.setBorder(null);
        jToggleButtonCirculo.setFocusable(false);
        jToggleButtonCirculo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonCirculo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonCirculo.addActionListener(formListener);
        jToolBarTools.add(jToggleButtonCirculo);

        buttonGroupTools.add(jToggleButtonPolygon);
        jToggleButtonPolygon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/poligono.png"))); // NOI18N
        jToggleButtonPolygon.setToolTipText(bundle.getString("POLY_TT")); // NOI18N
        jToggleButtonPolygon.setBorder(null);
        jToggleButtonPolygon.setFocusable(false);
        jToggleButtonPolygon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonPolygon.setName("jToggleButtonPolygon"); // NOI18N
        jToggleButtonPolygon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonPolygon.addActionListener(formListener);
        jToolBarTools.add(jToggleButtonPolygon);
        jToolBarTools.add(jSeparator5);

        jLabelImageColor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelImageColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/color2.png"))); // NOI18N
        jToolBarTools.add(jLabelImageColor);

        jComboBoxColor.setModel(new ColorComboBoxModel());
        jComboBoxColor.setToolTipText(bundle.getString("COLOR_TT")); // NOI18N
        jComboBoxColor.setBorder(null);
        jComboBoxColor.setEditor(new ColorComboBoxEditor());
        jComboBoxColor.setMaximumSize(new java.awt.Dimension(40, 28));
        jComboBoxColor.setMinimumSize(new java.awt.Dimension(28, 28));
        jComboBoxColor.setPreferredSize(new java.awt.Dimension(40, 28));
        jComboBoxColor.setRenderer(new ColorComboBoxRender());
        jComboBoxColor.addItemListener(formListener);
        jToolBarTools.add(jComboBoxColor);

        jSeparator16.setName("jSeparator16"); // NOI18N
        jToolBarTools.add(jSeparator16);

        jLabelImageTipo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelImageTipo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/tipo_linea.png"))); // NOI18N
        jLabelImageTipo.setName("jLabelImageTipo"); // NOI18N
        jToolBarTools.add(jLabelImageTipo);

        jComboBoxTipoLinea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NO_DISC", "DASH_BLANK", "DASH_POINT", "POINT_BLANK" }));
        jComboBoxTipoLinea.setToolTipText(bundle.getString("DISCON_TT")); // NOI18N
        jComboBoxTipoLinea.setMaximumSize(new java.awt.Dimension(50, 32767));
        jComboBoxTipoLinea.setMinimumSize(new java.awt.Dimension(50, 26));
        jComboBoxTipoLinea.setPreferredSize(new java.awt.Dimension(50, 26));
        jComboBoxTipoLinea.addItemListener(formListener);
        jToolBarTools.add(jComboBoxTipoLinea);
        jToolBarTools.add(jSeparator6);

        jLabelImageGrosor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelImageGrosor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/grosor.png"))); // NOI18N
        jToolBarTools.add(jLabelImageGrosor);

        jSpinnerGrosorLinea.setToolTipText(bundle.getString("STROKE_TT")); // NOI18N
        jSpinnerGrosorLinea.setMaximumSize(new java.awt.Dimension(60, 28));
        jSpinnerGrosorLinea.setMinimumSize(new java.awt.Dimension(28, 28));
        jSpinnerGrosorLinea.setPreferredSize(new java.awt.Dimension(50, 28));
        jSpinnerGrosorLinea.addChangeListener(formListener);
        jSpinnerGrosorLinea.addMouseWheelListener(formListener);
        jToolBarTools.add(jSpinnerGrosorLinea);
        jToolBarTools.add(jSeparator7);

        jLabelTransparente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTransparente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/transparente.png"))); // NOI18N
        jLabelTransparente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToolBarTools.add(jLabelTransparente);

        jSpinnerTransparencia.setToolTipText(bundle.getString("TRANS_TT")); // NOI18N
        jSpinnerTransparencia.setMaximumSize(new java.awt.Dimension(60, 28));
        jSpinnerTransparencia.setMinimumSize(new java.awt.Dimension(28, 28));
        jSpinnerTransparencia.setPreferredSize(new java.awt.Dimension(50, 28));
        jSpinnerTransparencia.addChangeListener(formListener);
        jSpinnerTransparencia.addMouseWheelListener(formListener);
        jToolBarTools.add(jSpinnerTransparencia);
        jToolBarTools.add(jSeparator8);

        jToggleButtonRelleno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/relleno.png"))); // NOI18N
        jToggleButtonRelleno.setToolTipText(bundle.getString("FILL_TT")); // NOI18N
        jToggleButtonRelleno.setBorder(null);
        jToggleButtonRelleno.setFocusable(false);
        jToggleButtonRelleno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonRelleno.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonRelleno.addActionListener(formListener);
        jToolBarTools.add(jToggleButtonRelleno);

        jToggleButtonAntiAliasing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/antialiasing.png"))); // NOI18N
        jToggleButtonAntiAliasing.setToolTipText(bundle.getString("ANTIALIASING_TT")); // NOI18N
        jToggleButtonAntiAliasing.setBorder(null);
        jToggleButtonAntiAliasing.setFocusable(false);
        jToggleButtonAntiAliasing.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonAntiAliasing.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonAntiAliasing.addActionListener(formListener);
        jToolBarTools.add(jToggleButtonAntiAliasing);

        jPanelContentBars.add(jToolBarTools);

        jToolBarSound.setRollover(true);

        buttonGroupSound.add(jToggleButtonPlay);
        jToggleButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/reproducir.png"))); // NOI18N
        jToggleButtonPlay.setToolTipText(bundle.getString("PLAY_TT")); // NOI18N
        jToggleButtonPlay.setBorder(null);
        jToggleButtonPlay.setFocusable(false);
        jToggleButtonPlay.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonPlay.setName("jToggleButtonPlay"); // NOI18N
        jToggleButtonPlay.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonPlay.addActionListener(formListener);
        jToolBarSound.add(jToggleButtonPlay);

        buttonGroupSound.add(jToggleButtonStop);
        jToggleButtonStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/parar.png"))); // NOI18N
        jToggleButtonStop.setSelected(true);
        jToggleButtonStop.setToolTipText(bundle.getString("STOP_TT")); // NOI18N
        jToggleButtonStop.setBorder(null);
        jToggleButtonStop.setFocusable(false);
        jToggleButtonStop.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonStop.setName("jToggleButtonStop"); // NOI18N
        jToggleButtonStop.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonStop.addActionListener(formListener);
        jToolBarSound.add(jToggleButtonStop);

        jSeparator14.setName("jSeparator14"); // NOI18N
        jToolBarSound.add(jSeparator14);

        jToggleButtonShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/alpha_up.png"))); // NOI18N
        jToggleButtonShow.setToolTipText(bundle.getString("SHOW_MULTIMEDIA_TT")); // NOI18N
        jToggleButtonShow.setBorder(null);
        jToggleButtonShow.setFocusable(false);
        jToggleButtonShow.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonShow.setName("jToggleButtonShow"); // NOI18N
        jToggleButtonShow.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonShow.addActionListener(formListener);
        jToolBarSound.add(jToggleButtonShow);

        jButtonWebcam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/webcam.png"))); // NOI18N
        jButtonWebcam.setToolTipText(bundle.getString("WEBCAM_TT")); // NOI18N
        jButtonWebcam.setBorder(null);
        jButtonWebcam.setFocusable(false);
        jButtonWebcam.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonWebcam.setName("jButtonWebcam"); // NOI18N
        jButtonWebcam.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonWebcam.addActionListener(formListener);
        jToolBarSound.add(jButtonWebcam);

        jButtonTakePhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/foto.png"))); // NOI18N
        jButtonTakePhoto.setToolTipText(bundle.getString("SNAPSHOP_TT")); // NOI18N
        jButtonTakePhoto.setBorder(null);
        jButtonTakePhoto.setEnabled(false);
        jButtonTakePhoto.setFocusable(false);
        jButtonTakePhoto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonTakePhoto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonTakePhoto.addActionListener(formListener);
        jToolBarSound.add(jButtonTakePhoto);

        jSeparator15.setName("jSeparator15"); // NOI18N
        jToolBarSound.add(jSeparator15);

        jToggleButtonRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/grabar.png"))); // NOI18N
        jToggleButtonRecord.setToolTipText(bundle.getString("RECORD_TT")); // NOI18N
        jToggleButtonRecord.setBorder(null);
        jToggleButtonRecord.setFocusable(false);
        jToggleButtonRecord.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonRecord.setName("jToggleButtonRecord"); // NOI18N
        jToggleButtonRecord.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonRecord.addActionListener(formListener);
        jToolBarSound.add(jToggleButtonRecord);

        filler4.setName("filler4"); // NOI18N
        jToolBarSound.add(filler4);

        jLabelElapsedTimeRecord.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelElapsedTimeRecord.setText("00:00");
        jLabelElapsedTimeRecord.setMaximumSize(new java.awt.Dimension(50, 12));
        jLabelElapsedTimeRecord.setMinimumSize(new java.awt.Dimension(40, 12));
        jLabelElapsedTimeRecord.setName("jLabelElapsedTimeRecord"); // NOI18N
        jLabelElapsedTimeRecord.setPreferredSize(new java.awt.Dimension(40, 12));
        jToolBarSound.add(jLabelElapsedTimeRecord);

        jPanelContentBars.add(jToolBarSound);

        jPanelContentIcons.add(jPanelContentBars, java.awt.BorderLayout.WEST);

        filler2.setFocusable(false);
        filler2.setName("filler2"); // NOI18N
        jPanelContentIcons.add(filler2, java.awt.BorderLayout.CENTER);

        filler3.setName("filler3"); // NOI18N
        jPanelContentIcons.add(filler3, java.awt.BorderLayout.CENTER);

        jButtonSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/salir.png"))); // NOI18N
        jButtonSalir.setToolTipText(bundle.getString("EXIT")); // NOI18N
        jButtonSalir.setBorder(null);
        jButtonSalir.setFocusable(false);
        jButtonSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSalir.setMaximumSize(new java.awt.Dimension(46, 14));
        jButtonSalir.setMinimumSize(new java.awt.Dimension(25, 14));
        jButtonSalir.setPreferredSize(new java.awt.Dimension(25, 14));
        jButtonSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSalir.addActionListener(formListener);
        jPanelContentIcons.add(jButtonSalir, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanelContentIcons, java.awt.BorderLayout.NORTH);

        jPanelStatusBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanelStatusBar.setLayout(new javax.swing.BoxLayout(jPanelStatusBar, javax.swing.BoxLayout.LINE_AXIS));

        filler15.setName("filler15"); // NOI18N
        jPanelStatusBar.add(filler15);

        jLabelHerramienta.setText(bundle.getString("TOOL_POINT")); // NOI18N
        jLabelHerramienta.setMaximumSize(new java.awt.Dimension(250, 25));
        jLabelHerramienta.setMinimumSize(new java.awt.Dimension(150, 25));
        jLabelHerramienta.setPreferredSize(new java.awt.Dimension(225, 25));
        jPanelStatusBar.add(jLabelHerramienta);

        jLabelColor.setText(bundle.getString("COLOR")); // NOI18N
        jLabelColor.setMaximumSize(new java.awt.Dimension(200, 25));
        jLabelColor.setMinimumSize(new java.awt.Dimension(200, 25));
        jLabelColor.setPreferredSize(new java.awt.Dimension(200, 25));
        jPanelStatusBar.add(jLabelColor);

        jLabelAccionRaton.setText(bundle.getString("MOUSE")); // NOI18N
        jLabelAccionRaton.setMaximumSize(new java.awt.Dimension(200, 25));
        jLabelAccionRaton.setMinimumSize(new java.awt.Dimension(200, 25));
        jLabelAccionRaton.setPreferredSize(new java.awt.Dimension(200, 25));
        jPanelStatusBar.add(jLabelAccionRaton);

        filler1.setName("filler1"); // NOI18N
        jPanelStatusBar.add(filler1);

        jLabelMousePos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMousePos.setText(bundle.getString("MOUSE_POS")); // NOI18N
        jLabelMousePos.setMaximumSize(new java.awt.Dimension(200, 25));
        jLabelMousePos.setMinimumSize(new java.awt.Dimension(160, 25));
        jLabelMousePos.setPreferredSize(new java.awt.Dimension(160, 25));
        jPanelStatusBar.add(jLabelMousePos);

        jLabelMouseColorPos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMouseColorPos.setText(bundle.getString("COLOR_POS")); // NOI18N
        jLabelMouseColorPos.setMaximumSize(new java.awt.Dimension(240, 25));
        jLabelMouseColorPos.setMinimumSize(new java.awt.Dimension(200, 25));
        jLabelMouseColorPos.setPreferredSize(new java.awt.Dimension(220, 25));
        jPanelStatusBar.add(jLabelMouseColorPos);

        jLabelMouseViewColorPos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMouseViewColorPos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jLabelMouseViewColorPos.setMaximumSize(new java.awt.Dimension(20, 20));
        jLabelMouseViewColorPos.setMinimumSize(new java.awt.Dimension(20, 20));
        jLabelMouseViewColorPos.setName("jLabelMouseViewColorPos"); // NOI18N
        jLabelMouseViewColorPos.setOpaque(true);
        jLabelMouseViewColorPos.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanelStatusBar.add(jLabelMouseViewColorPos);

        filler8.setName("filler8"); // NOI18N
        jPanelStatusBar.add(filler8);

        getContentPane().add(jPanelStatusBar, java.awt.BorderLayout.SOUTH);

        jSplitPaneCenter.setName("jSplitPaneCenter"); // NOI18N

        jPanelSplitDerecho.setMinimumSize(new java.awt.Dimension(200, 0));
        jPanelSplitDerecho.setPreferredSize(new java.awt.Dimension(200, 0));
        jPanelSplitDerecho.setLayout(new javax.swing.BoxLayout(jPanelSplitDerecho, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelHistogram.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("HISTOGRAMA"))); // NOI18N
        jPanelHistogram.setMaximumSize(new java.awt.Dimension(32767, 200));
        jPanelHistogram.setName("jPanelHistogram"); // NOI18N
        jPanelHistogram.setPreferredSize(new java.awt.Dimension(20, 200));
        jPanelHistogram.setLayout(new java.awt.BorderLayout());
        jPanelSplitDerecho.add(jPanelHistogram);

        jPanelPrevio.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("PREVIEW"))); // NOI18N
        jPanelPrevio.setMaximumSize(new java.awt.Dimension(32767, 200));
        jPanelPrevio.setPreferredSize(new java.awt.Dimension(14, 200));
        jPanelPrevio.setLayout(new java.awt.BorderLayout());

        jPanelZoom.setLayout(new javax.swing.BoxLayout(jPanelZoom, javax.swing.BoxLayout.PAGE_AXIS));

        jLabelImageUpZoom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageUpZoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/zoom_up.png"))); // NOI18N
        jLabelImageUpZoom.setAlignmentX(0.5F);
        jPanelZoom.add(jLabelImageUpZoom);

        jSliderZoom.setMaximum(200);
        jSliderZoom.setOrientation(javax.swing.JSlider.VERTICAL);
        jSliderZoom.setToolTipText(bundle.getString("ZOOM_TT")); // NOI18N
        jSliderZoom.setValue(100);
        jSliderZoom.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        jSliderZoom.addChangeListener(formListener);
        jSliderZoom.addFocusListener(formListener);
        jPanelZoom.add(jSliderZoom);

        jLabelImageDownZoom.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageDownZoom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/zoom_down.png"))); // NOI18N
        jLabelImageDownZoom.setAlignmentX(0.5F);
        jPanelZoom.add(jLabelImageDownZoom);

        jPanelPrevio.add(jPanelZoom, java.awt.BorderLayout.EAST);

        jPanelRotar.setLayout(new javax.swing.BoxLayout(jPanelRotar, javax.swing.BoxLayout.LINE_AXIS));

        jLabelImageRotateLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageRotateLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rotar_izq.png"))); // NOI18N
        jPanelRotar.add(jLabelImageRotateLeft);

        jSliderRotate.setMaximum(180);
        jSliderRotate.setMinimum(-180);
        jSliderRotate.setToolTipText(bundle.getString("ROTATE_TT")); // NOI18N
        jSliderRotate.setValue(0);
        jSliderRotate.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        jSliderRotate.addChangeListener(formListener);
        jSliderRotate.addFocusListener(formListener);
        jPanelRotar.add(jSliderRotate);

        jLabelImageRotateRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageRotateRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rotar_der.png"))); // NOI18N
        jPanelRotar.add(jLabelImageRotateRight);

        jPanelPrevio.add(jPanelRotar, java.awt.BorderLayout.SOUTH);

        jPanelSplitDerecho.add(jPanelPrevio);

        jPanelBrilloContraste.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("BRIGHTNESS_CONTRAST"))); // NOI18N
        jPanelBrilloContraste.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanelBrilloContraste.setLayout(new javax.swing.BoxLayout(jPanelBrilloContraste, javax.swing.BoxLayout.PAGE_AXIS));

        jPBrilloSlider.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPBrilloSlider.setName("jPBrilloSlider"); // NOI18N
        jPBrilloSlider.setLayout(new javax.swing.BoxLayout(jPBrilloSlider, javax.swing.BoxLayout.LINE_AXIS));

        jLabelImageDownBrigthness.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageDownBrigthness.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/brillo1.png"))); // NOI18N
        jPBrilloSlider.add(jLabelImageDownBrigthness);

        jSliderBrigthness.setMinimum(-100);
        jSliderBrigthness.setToolTipText(bundle.getString("BRIGHTNESS_TT")); // NOI18N
        jSliderBrigthness.setValue(0);
        jSliderBrigthness.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        jSliderBrigthness.addChangeListener(formListener);
        jSliderBrigthness.addFocusListener(formListener);
        jPBrilloSlider.add(jSliderBrigthness);

        jLabelImageUpBrigthness.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageUpBrigthness.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/brillo3.png"))); // NOI18N
        jPBrilloSlider.add(jLabelImageUpBrigthness);

        jPanelBrilloContraste.add(jPBrilloSlider);

        jPBrilloContraste.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPBrilloContraste.setName("jPBrilloContraste"); // NOI18N
        jPBrilloContraste.setLayout(new javax.swing.BoxLayout(jPBrilloContraste, javax.swing.BoxLayout.LINE_AXIS));

        jLabelImageDownContrast.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageDownContrast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/contraste2.png"))); // NOI18N
        jPBrilloContraste.add(jLabelImageDownContrast);

        jSliderContrast.setMaximum(128);
        jSliderContrast.setMinimum(-128);
        jSliderContrast.setToolTipText(bundle.getString("CONTRAST_TT")); // NOI18N
        jSliderContrast.setValue(0);
        jSliderContrast.setBorder(javax.swing.BorderFactory.createEmptyBorder(2, 2, 2, 2));
        jSliderContrast.addChangeListener(formListener);
        jSliderContrast.addFocusListener(formListener);
        jPBrilloContraste.add(jSliderContrast);

        jLabelImageUpContrast.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageUpContrast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/contraste1.png"))); // NOI18N
        jPBrilloContraste.add(jLabelImageUpContrast);

        jPanelBrilloContraste.add(jPBrilloContraste);

        jPanelSplitDerecho.add(jPanelBrilloContraste);

        jPanelUmbral.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("UMBRAL_PANEL"))); // NOI18N
        jPanelUmbral.setName("jPanelUmbral"); // NOI18N
        jPanelUmbral.setLayout(new javax.swing.BoxLayout(jPanelUmbral, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelUmbralSlider.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanelUmbralSlider.setName("jPanelUmbralSlider"); // NOI18N
        jPanelUmbralSlider.setLayout(new javax.swing.BoxLayout(jPanelUmbralSlider, javax.swing.BoxLayout.LINE_AXIS));

        jLabelImageDownUmbral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageDownUmbral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/menos.png"))); // NOI18N
        jLabelImageDownUmbral.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        jLabelImageDownUmbral.setName("jLabelImageDownUmbral"); // NOI18N
        jPanelUmbralSlider.add(jLabelImageDownUmbral);

        jSliderUmbral.setToolTipText(bundle.getString("UMBRAL_TT")); // NOI18N
        jSliderUmbral.setName("jSliderUmbral"); // NOI18N
        jSliderUmbral.addChangeListener(formListener);
        jSliderUmbral.addFocusListener(formListener);
        jPanelUmbralSlider.add(jSliderUmbral);

        jLabelImageUpUmbral.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageUpUmbral.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/mas.png"))); // NOI18N
        jLabelImageUpUmbral.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        jLabelImageUpUmbral.setName("jLabelImageUpUmbral"); // NOI18N
        jPanelUmbralSlider.add(jLabelImageUpUmbral);

        jButtonColorUmbral.setMaximumSize(new java.awt.Dimension(30, 30));
        jButtonColorUmbral.setMinimumSize(new java.awt.Dimension(30, 30));
        jButtonColorUmbral.setName("jButtonColorUmbral"); // NOI18N
        jButtonColorUmbral.setPreferredSize(new java.awt.Dimension(30, 30));
        jButtonColorUmbral.addActionListener(formListener);
        jPanelUmbralSlider.add(jButtonColorUmbral);

        jPanelUmbral.add(jPanelUmbralSlider);

        jPanelSplitDerecho.add(jPanelUmbral);

        jComboBoxFiltros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "Blur Medio", "Blur Binomial", "Enfoque", "Relieve", "Realzar Bordes", "Emboss", "Sharpens", "Frontera Laplaciano", "Suavisado Gaussiano", "Negativo", "Sepia", "Sobel", "Personalizado" }));
        jComboBoxFiltros.setToolTipText(bundle.getString("FILTER_TT")); // NOI18N
        jComboBoxFiltros.setMaximumSize(new java.awt.Dimension(32767, 36));
        jComboBoxFiltros.setMinimumSize(new java.awt.Dimension(65, 36));
        jComboBoxFiltros.setPreferredSize(new java.awt.Dimension(65, 36));
        jComboBoxFiltros.addItemListener(formListener);
        jComboBoxFiltros.addFocusListener(formListener);
        jPanelSplitDerecho.add(jComboBoxFiltros);

        jSplitPaneCenter.setLeftComponent(jPanelSplitDerecho);

        jDesktopEscritorio.setPreferredSize(new java.awt.Dimension(650, 0));
        jSplitPaneCenter.setRightComponent(jDesktopEscritorio);

        getContentPane().add(jSplitPaneCenter, java.awt.BorderLayout.CENTER);

        jPanelOeste.setName("jPanelOeste"); // NOI18N
        jPanelOeste.setPreferredSize(new java.awt.Dimension(200, 262));
        jPanelOeste.setLayout(new java.awt.BorderLayout(10, 10));

        jPanelNorte.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanelNorte.setName("jPanelNorte"); // NOI18N
        jPanelNorte.setLayout(new javax.swing.BoxLayout(jPanelNorte, javax.swing.BoxLayout.PAGE_AXIS));

        jScrollPanelSounds.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("SOUND_LIST"))); // NOI18N
        jScrollPanelSounds.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPanelSounds.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPanelSounds.setMaximumSize(new java.awt.Dimension(250, 250));
        jScrollPanelSounds.setName("jScrollPanelSounds"); // NOI18N

        jListSounds.setModel(this.list_model);
        jListSounds.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListSounds.setToolTipText(bundle.getString("SOUNDLIST_TT")); // NOI18N
        jListSounds.setMaximumSize(new java.awt.Dimension(36, 75));
        jListSounds.setName("jListSounds"); // NOI18N
        jListSounds.setPreferredSize(new java.awt.Dimension(36, 36));
        jListSounds.addMouseListener(formListener);
        jScrollPanelSounds.setViewportView(jListSounds);

        jPanelNorte.add(jScrollPanelSounds);

        jPanelProgressBar.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanelProgressBar.setName("jPanelProgressBar"); // NOI18N
        jPanelProgressBar.setLayout(new javax.swing.BoxLayout(jPanelProgressBar, javax.swing.BoxLayout.LINE_AXIS));

        jLabelElapsedTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelElapsedTime.setText("00:00");
        jLabelElapsedTime.setMaximumSize(new java.awt.Dimension(50, 12));
        jLabelElapsedTime.setMinimumSize(new java.awt.Dimension(40, 12));
        jLabelElapsedTime.setName("jLabelElapsedTime"); // NOI18N
        jLabelElapsedTime.setPreferredSize(new java.awt.Dimension(40, 12));
        jPanelProgressBar.add(jLabelElapsedTime);

        filler5.setName("filler5"); // NOI18N
        jPanelProgressBar.add(filler5);

        jProgressBarMultimedia.setName("jProgressBarMultimedia"); // NOI18N
        jPanelProgressBar.add(jProgressBarMultimedia);

        filler6.setName("filler6"); // NOI18N
        jPanelProgressBar.add(filler6);

        jLabelTotalTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTotalTime.setText("00:00");
        jLabelTotalTime.setMaximumSize(new java.awt.Dimension(50, 12));
        jLabelTotalTime.setMinimumSize(new java.awt.Dimension(40, 12));
        jLabelTotalTime.setName("jLabelTotalTime"); // NOI18N
        jLabelTotalTime.setPreferredSize(new java.awt.Dimension(40, 12));
        jPanelProgressBar.add(jLabelTotalTime);

        jPanelNorte.add(jPanelProgressBar);

        jPanelOeste.add(jPanelNorte, java.awt.BorderLayout.NORTH);

        jPanelSur.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanelSur.setName("jPanelSur"); // NOI18N
        jPanelSur.setLayout(new javax.swing.BoxLayout(jPanelSur, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelOptions.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanelOptions.setName("jPanelOptions"); // NOI18N
        jPanelOptions.setLayout(new java.awt.GridLayout(4, 0));

        buttonGroupSoundOptions.add(jRadioButtonRepeatNone);
        jRadioButtonRepeatNone.setSelected(true);
        jRadioButtonRepeatNone.setText(bundle.getString("REPEAT_NONE")); // NOI18N
        jRadioButtonRepeatNone.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jRadioButtonRepeatNone.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jRadioButtonRepeatNone.setName("jRadioButtonRepeat"); // NOI18N
        jPanelOptions.add(jRadioButtonRepeatNone);

        buttonGroupSoundOptions.add(jRadioButtonRepeatSound);
        jRadioButtonRepeatSound.setText(bundle.getString("REPEAT_SOUND")); // NOI18N
        jRadioButtonRepeatSound.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jRadioButtonRepeatSound.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jRadioButtonRepeatSound.setName("jRadioButtonRepeat"); // NOI18N
        jPanelOptions.add(jRadioButtonRepeatSound);

        buttonGroupSoundOptions.add(jRadioButtonRepeatAll);
        jRadioButtonRepeatAll.setText(bundle.getString("REPEAT_ALL")); // NOI18N
        jRadioButtonRepeatAll.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jRadioButtonRepeatAll.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jRadioButtonRepeatAll.setName("jRadioButtonRepeat"); // NOI18N
        jPanelOptions.add(jRadioButtonRepeatAll);

        jCheckBoxOpenPanel.setSelected(true);
        jCheckBoxOpenPanel.setText(bundle.getString("CHECK_OPEN_PANEL")); // NOI18N
        jCheckBoxOpenPanel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jCheckBoxOpenPanel.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jCheckBoxOpenPanel.setName("jCheckBoxOpenPanel"); // NOI18N
        jPanelOptions.add(jCheckBoxOpenPanel);

        jPanelSur.add(jPanelOptions);

        jPanelOeste.add(jPanelSur, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanelOeste, java.awt.BorderLayout.LINE_END);

        jMenuBarra.setName("jMenuBarra"); // NOI18N

        jMenuArchivo.setText(bundle.getString("ARCHIVO")); // NOI18N

        jMenuItemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/nuevo.png"))); // NOI18N
        jMenuItemNuevo.setText(bundle.getString("NUEVO")); // NOI18N
        jMenuItemNuevo.addActionListener(formListener);
        jMenuArchivo.add(jMenuItemNuevo);

        jMenuItemAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/abrir.png"))); // NOI18N
        jMenuItemAbrir.setText(bundle.getString("OPEN")); // NOI18N
        jMenuItemAbrir.addActionListener(formListener);
        jMenuArchivo.add(jMenuItemAbrir);

        jMenuItemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/guardar.png"))); // NOI18N
        jMenuItemGuardar.setText(bundle.getString("SAVE")); // NOI18N
        jMenuItemGuardar.addActionListener(formListener);
        jMenuArchivo.add(jMenuItemGuardar);

        jSeparator2.setMinimumSize(new java.awt.Dimension(10, 10));
        jSeparator2.setName("jSeparator2"); // NOI18N
        jMenuArchivo.add(jSeparator2);

        jMenuItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/salir.png"))); // NOI18N
        jMenuItemSalir.setText(bundle.getString("EXIT")); // NOI18N
        jMenuItemSalir.addActionListener(formListener);
        jMenuArchivo.add(jMenuItemSalir);

        jMenuBarra.add(jMenuArchivo);

        jMenuEditar.setText(bundle.getString("EDIT")); // NOI18N
        jMenuEditar.setName("jMenuEditar"); // NOI18N

        jMenuItemDeshacer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemDeshacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/deshacer.png"))); // NOI18N
        jMenuItemDeshacer.setText(bundle.getString("UNDO")); // NOI18N
        jMenuItemDeshacer.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/res/deshacer.png"))); // NOI18N
        jMenuItemDeshacer.setEnabled(false);
        jMenuItemDeshacer.setName("jMenuItemDeshacer"); // NOI18N
        jMenuItemDeshacer.addActionListener(formListener);
        jMenuEditar.add(jMenuItemDeshacer);

        jMenuItemRehacer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRehacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rehacer.png"))); // NOI18N
        jMenuItemRehacer.setText(bundle.getString("REDO")); // NOI18N
        jMenuItemRehacer.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rehacer.png"))); // NOI18N
        jMenuItemRehacer.setEnabled(false);
        jMenuItemRehacer.setName("jMenuItemRehacer"); // NOI18N
        jMenuItemRehacer.addActionListener(formListener);
        jMenuEditar.add(jMenuItemRehacer);

        jSeparator11.setName("jSeparator11"); // NOI18N
        jMenuEditar.add(jSeparator11);

        jMenuItemCortar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCortar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/cortar.png"))); // NOI18N
        jMenuItemCortar.setText(bundle.getString("CUT")); // NOI18N
        jMenuItemCortar.setName("jMenuItemCortar"); // NOI18N
        jMenuItemCortar.addActionListener(formListener);
        jMenuEditar.add(jMenuItemCortar);

        jMenuItemCopiar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCopiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/copiar.png"))); // NOI18N
        jMenuItemCopiar.setText(bundle.getString("COPY")); // NOI18N
        jMenuItemCopiar.setName("jMenuItemCopiar"); // NOI18N
        jMenuItemCopiar.addActionListener(formListener);
        jMenuEditar.add(jMenuItemCopiar);

        jMenuItemPegar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemPegar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/pegar.png"))); // NOI18N
        jMenuItemPegar.setText(bundle.getString("PASTE")); // NOI18N
        jMenuItemPegar.setName("jMenuItemPegar"); // NOI18N
        jMenuItemPegar.addActionListener(formListener);
        jMenuEditar.add(jMenuItemPegar);

        jMenuBarra.add(jMenuEditar);

        jMenuVer.setText(bundle.getString("VIEW")); // NOI18N
        jMenuVer.setName("jMenuVer"); // NOI18N

        jCheckBoxMenuItemVerBarraHerramientas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItemVerBarraHerramientas.setSelected(true);
        jCheckBoxMenuItemVerBarraHerramientas.setText(bundle.getString("TOOL_BAR_TEXT")); // NOI18N
        jCheckBoxMenuItemVerBarraHerramientas.setName("jCheckBoxMenuItemVerBarraHerramientas"); // NOI18N
        jCheckBoxMenuItemVerBarraHerramientas.addActionListener(formListener);
        jMenuVer.add(jCheckBoxMenuItemVerBarraHerramientas);

        jCheckBoxMenuItemVerBarraUtilidades.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItemVerBarraUtilidades.setSelected(true);
        jCheckBoxMenuItemVerBarraUtilidades.setText(bundle.getString("UTIL_BAR_TEXT")); // NOI18N
        jCheckBoxMenuItemVerBarraUtilidades.setName("jCheckBoxMenuItemVerBarraUtilidades"); // NOI18N
        jCheckBoxMenuItemVerBarraUtilidades.addActionListener(formListener);
        jMenuVer.add(jCheckBoxMenuItemVerBarraUtilidades);

        jCheckBoxMenuItemVerBarraEstado.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItemVerBarraEstado.setSelected(true);
        jCheckBoxMenuItemVerBarraEstado.setText(bundle.getString("STATUS_BAR_TEXT")); // NOI18N
        jCheckBoxMenuItemVerBarraEstado.setName("jCheckBoxMenuItemVerBarraEstado"); // NOI18N
        jCheckBoxMenuItemVerBarraEstado.addActionListener(formListener);
        jMenuVer.add(jCheckBoxMenuItemVerBarraEstado);

        jCheckBoxMenuItemVerBarraSonido.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItemVerBarraSonido.setSelected(true);
        jCheckBoxMenuItemVerBarraSonido.setText(bundle.getString("SOUND_BAR")); // NOI18N
        jCheckBoxMenuItemVerBarraSonido.setName("jCheckBoxMenuItemVerBarraSonido"); // NOI18N
        jCheckBoxMenuItemVerBarraSonido.addActionListener(formListener);
        jMenuVer.add(jCheckBoxMenuItemVerBarraSonido);

        jCheckBoxMenuItemPanelSonido.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItemPanelSonido.setText(bundle.getString("SOUND_PANEL")); // NOI18N
        jCheckBoxMenuItemPanelSonido.setName("jCheckBoxMenuItemPanelSonido"); // NOI18N
        jCheckBoxMenuItemPanelSonido.addActionListener(formListener);
        jMenuVer.add(jCheckBoxMenuItemPanelSonido);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jMenuVer.add(jSeparator1);

        jCheckBoxMenuItemPantallaCompleta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItemPantallaCompleta.setText(bundle.getString("FULL_SCREEN")); // NOI18N
        jCheckBoxMenuItemPantallaCompleta.setName("jCheckBoxMenuItemPantallaCompleta"); // NOI18N
        jCheckBoxMenuItemPantallaCompleta.addActionListener(formListener);
        jMenuVer.add(jCheckBoxMenuItemPantallaCompleta);

        jMenuBarra.add(jMenuVer);

        jMenuPruebas.setText("PRUEBAS");
        jMenuPruebas.setName("jMenuPruebas"); // NOI18N

        jMenuItemRescaleOP.setText("RescaleOP");
        jMenuItemRescaleOP.setName("jMenuItemRescaleOP"); // NOI18N
        jMenuItemRescaleOP.addActionListener(formListener);
        jMenuPruebas.add(jMenuItemRescaleOP);

        jMenuItemConvolveOP.setText("ConvolveOP");
        jMenuItemConvolveOP.setName("jMenuItemConvolveOP"); // NOI18N
        jMenuItemConvolveOP.addActionListener(formListener);
        jMenuPruebas.add(jMenuItemConvolveOP);

        jMenuItemAffineTransformOp.setText("AffineTransformOp");
        jMenuItemAffineTransformOp.setName("jMenuItemAffineTransformOp"); // NOI18N
        jMenuItemAffineTransformOp.addActionListener(formListener);
        jMenuPruebas.add(jMenuItemAffineTransformOp);

        jMenuItemLookupOp.setText("LookupOp");
        jMenuItemLookupOp.setName("jMenuItemLookupOp"); // NOI18N
        jMenuItemLookupOp.addActionListener(formListener);
        jMenuPruebas.add(jMenuItemLookupOp);

        jMenuItemBandCombineOp.setText("BandCombineOp");
        jMenuItemBandCombineOp.setName("jMenuItemBandCombineOp"); // NOI18N
        jMenuItemBandCombineOp.addActionListener(formListener);
        jMenuPruebas.add(jMenuItemBandCombineOp);

        jMenuItemColorConvertOp.setText("ColorConvertOp");
        jMenuItemColorConvertOp.setName("jMenuItemColorConvertOp"); // NOI18N
        jMenuItemColorConvertOp.addActionListener(formListener);
        jMenuPruebas.add(jMenuItemColorConvertOp);

        jMenuItemVLCPlayer.setText("Reproductor VLCJ");
        jMenuItemVLCPlayer.setName("jMenuItemVLCPlayer"); // NOI18N
        jMenuItemVLCPlayer.addActionListener(formListener);
        jMenuPruebas.add(jMenuItemVLCPlayer);

        jMenuBarra.add(jMenuPruebas);

        jMenuImagen.setText(bundle.getString("IMAGE")); // NOI18N
        jMenuImagen.setName("jMenuImagen"); // NOI18N

        jMIBrightnessContrast.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMIBrightnessContrast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/brillo1.png"))); // NOI18N
        jMIBrightnessContrast.setText(bundle.getString("BRIGHTNESS_CONTRAST")); // NOI18N
        jMIBrightnessContrast.setEnabled(false);
        jMIBrightnessContrast.setName("jMIBrightnessContrast"); // NOI18N
        jMIBrightnessContrast.addActionListener(formListener);
        jMenuImagen.add(jMIBrightnessContrast);

        jSeparator13.setName("jSeparator13"); // NOI18N
        jMenuImagen.add(jSeparator13);

        jMenuItemConvertBlackWhite.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemConvertBlackWhite.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/blanco_negro.png"))); // NOI18N
        jMenuItemConvertBlackWhite.setText(bundle.getString("CONVERT_BW")); // NOI18N
        jMenuItemConvertBlackWhite.setName("jMenuItemConvertBlackWhite"); // NOI18N
        jMenuItemConvertBlackWhite.addActionListener(formListener);
        jMenuImagen.add(jMenuItemConvertBlackWhite);

        jSeparator9.setName("jSeparator9"); // NOI18N
        jMenuImagen.add(jSeparator9);

        jMenuItemEqualization.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemEqualization.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/ajustes.png"))); // NOI18N
        jMenuItemEqualization.setText(bundle.getString("EQUALIZATION")); // NOI18N
        jMenuItemEqualization.setName("jMenuItemEqualization"); // NOI18N
        jMenuItemEqualization.addActionListener(formListener);
        jMenuImagen.add(jMenuItemEqualization);

        jSeparator17.setName("jSeparator17"); // NOI18N
        jMenuImagen.add(jSeparator17);

        jMenuItemScaleImage.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemScaleImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/reescalar.png"))); // NOI18N
        jMenuItemScaleImage.setText(bundle.getString("SCALE_IMAGE")); // NOI18N
        jMenuItemScaleImage.setName("jMenuItemScaleImage"); // NOI18N
        jMenuItemScaleImage.addActionListener(formListener);
        jMenuImagen.add(jMenuItemScaleImage);

        jMenuItemResizeImage.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemResizeImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/redimensionar.png"))); // NOI18N
        jMenuItemResizeImage.setText(bundle.getString("RESIZE_IMAGE")); // NOI18N
        jMenuItemResizeImage.setName("jMenuItemResizeImage"); // NOI18N
        jMenuItemResizeImage.addActionListener(formListener);
        jMenuImagen.add(jMenuItemResizeImage);

        jSeparator10.setName("jSeparator10"); // NOI18N
        jMenuImagen.add(jSeparator10);

        jMenuRotate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rotar.png"))); // NOI18N
        jMenuRotate.setText(bundle.getString("ROTATE")); // NOI18N
        jMenuRotate.setName("jMenuRotate"); // NOI18N

        jMenuItemCustom.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCustom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rotar.png"))); // NOI18N
        jMenuItemCustom.setText(bundle.getString("CUSTOM")); // NOI18N
        jMenuItemCustom.setEnabled(false);
        jMenuItemCustom.setName("jMenuItemCustom"); // NOI18N
        jMenuItemCustom.addActionListener(formListener);
        jMenuRotate.add(jMenuItemCustom);

        jMenuItemRotateLeft.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRotateLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rotar_izq.png"))); // NOI18N
        jMenuItemRotateLeft.setText(bundle.getString("ROTATE_LEFT")); // NOI18N
        jMenuItemRotateLeft.setName("jMenuItemRotateLeft"); // NOI18N
        jMenuItemRotateLeft.addActionListener(formListener);
        jMenuRotate.add(jMenuItemRotateLeft);

        jMenuItemRotateRight.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRotateRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rotar_der.png"))); // NOI18N
        jMenuItemRotateRight.setText(bundle.getString("ROTATE_RIGHT")); // NOI18N
        jMenuItemRotateRight.setName("jMenuItemRotateRight"); // NOI18N
        jMenuItemRotateRight.addActionListener(formListener);
        jMenuRotate.add(jMenuItemRotateRight);

        jMenuItemRotate180.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRotate180.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rotar180.png"))); // NOI18N
        jMenuItemRotate180.setText(bundle.getString("ROTATE_180")); // NOI18N
        jMenuItemRotate180.setName("jMenuItemRotate180"); // NOI18N
        jMenuItemRotate180.addActionListener(formListener);
        jMenuRotate.add(jMenuItemRotate180);

        jMenuImagen.add(jMenuRotate);

        jMenuItemClone.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemClone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/duplicar.png"))); // NOI18N
        jMenuItemClone.setText(bundle.getString("CLONE")); // NOI18N
        jMenuItemClone.setName("jMenuItemClone"); // NOI18N
        jMenuItemClone.addActionListener(formListener);
        jMenuImagen.add(jMenuItemClone);

        jMenuItemSenoFunction.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSenoFunction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/seno.png"))); // NOI18N
        jMenuItemSenoFunction.setText(bundle.getString("SENOFUNCTION")); // NOI18N
        jMenuItemSenoFunction.setName("jMenuItemSenoFunction"); // NOI18N
        jMenuItemSenoFunction.addActionListener(formListener);
        jMenuImagen.add(jMenuItemSenoFunction);

        jMenuItemTintar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemTintar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/tintar.png"))); // NOI18N
        jMenuItemTintar.setText(bundle.getString("TINTAR")); // NOI18N
        jMenuItemTintar.setName("jMenuItemTintar"); // NOI18N
        jMenuItemTintar.addActionListener(formListener);
        jMenuImagen.add(jMenuItemTintar);

        jSeparator12.setName("jSeparator12"); // NOI18N
        jMenuImagen.add(jSeparator12);

        jMenuItemAdd.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/suma_imagenes.png"))); // NOI18N
        jMenuItemAdd.setText(bundle.getString("ADD_IMAGES")); // NOI18N
        jMenuItemAdd.setName("jMenuItemAdd"); // NOI18N
        jMenuItemAdd.addActionListener(formListener);
        jMenuImagen.add(jMenuItemAdd);

        jMenuItemSubstract.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSubstract.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/resta_imagenes.png"))); // NOI18N
        jMenuItemSubstract.setText(bundle.getString("SUBTRACTION_IMAGES")); // NOI18N
        jMenuItemSubstract.setName("jMenuItemSubstract"); // NOI18N
        jMenuItemSubstract.addActionListener(formListener);
        jMenuImagen.add(jMenuItemSubstract);

        jMenuItemMultiply.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemMultiply.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/multiplicacion_imagenes.png"))); // NOI18N
        jMenuItemMultiply.setText(bundle.getString("MULTIPLY_IMAGES")); // NOI18N
        jMenuItemMultiply.setName("jMenuItemMultiply"); // NOI18N
        jMenuItemMultiply.addActionListener(formListener);
        jMenuImagen.add(jMenuItemMultiply);

        jMenuBarra.add(jMenuImagen);

        jMenuFilters.setText(bundle.getString("FILTERS")); // NOI18N
        jMenuFilters.setName("jMenuFilters"); // NOI18N

        jMenuItemBlurMedio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemBlurMedio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/blur.png"))); // NOI18N
        jMenuItemBlurMedio.setText(bundle.getString("BLUR_MEDIO")); // NOI18N
        jMenuItemBlurMedio.setName("jMenuItemBlurMedio"); // NOI18N
        jMenuItemBlurMedio.addActionListener(formListener);
        jMenuFilters.add(jMenuItemBlurMedio);

        jMenuItemBlurBinomial.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemBlurBinomial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/blur2.png"))); // NOI18N
        jMenuItemBlurBinomial.setText(bundle.getString("BLUR_BINOMIAL")); // NOI18N
        jMenuItemBlurBinomial.setName("jMenuItemBlurBinomial"); // NOI18N
        jMenuItemBlurBinomial.addActionListener(formListener);
        jMenuFilters.add(jMenuItemBlurBinomial);

        jMenuItemFocus.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemFocus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/enfoque.png"))); // NOI18N
        jMenuItemFocus.setText(bundle.getString("FOCUS")); // NOI18N
        jMenuItemFocus.setName("jMenuItemFocus"); // NOI18N
        jMenuItemFocus.addActionListener(formListener);
        jMenuFilters.add(jMenuItemFocus);

        jMenuItemRelief.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRelief.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/relieve.png"))); // NOI18N
        jMenuItemRelief.setText(bundle.getString("RELIEF")); // NOI18N
        jMenuItemRelief.setName("jMenuItemRelief"); // NOI18N
        jMenuItemRelief.addActionListener(formListener);
        jMenuFilters.add(jMenuItemRelief);

        jMenuItemBorder.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemBorder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/realzar.png"))); // NOI18N
        jMenuItemBorder.setText(bundle.getString("FRONTIER_DETECT")); // NOI18N
        jMenuItemBorder.setName("jMenuItemBorder"); // NOI18N
        jMenuItemBorder.addActionListener(formListener);
        jMenuFilters.add(jMenuItemBorder);

        jMenuItemEmboss.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemEmboss.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/emboss.png"))); // NOI18N
        jMenuItemEmboss.setText(bundle.getString("EMBOSS")); // NOI18N
        jMenuItemEmboss.setName("jMenuItemEmboss"); // NOI18N
        jMenuItemEmboss.addActionListener(formListener);
        jMenuFilters.add(jMenuItemEmboss);

        jMenuItemSharpens.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSharpens.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/sharpen.png"))); // NOI18N
        jMenuItemSharpens.setText(bundle.getString("SHARPENS")); // NOI18N
        jMenuItemSharpens.setName("jMenuItemSharpens"); // NOI18N
        jMenuItemSharpens.addActionListener(formListener);
        jMenuFilters.add(jMenuItemSharpens);

        jMenuItemFrontier.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemFrontier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/frontera.png"))); // NOI18N
        jMenuItemFrontier.setText(bundle.getString("FRONTIER")); // NOI18N
        jMenuItemFrontier.setName("jMenuItemFrontier"); // NOI18N
        jMenuItemFrontier.addActionListener(formListener);
        jMenuFilters.add(jMenuItemFrontier);

        jMenuItemSuavizado.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSuavizado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/suavizado.png"))); // NOI18N
        jMenuItemSuavizado.setText(bundle.getString("GAUSSIANO")); // NOI18N
        jMenuItemSuavizado.setName("jMenuItemSuavizado"); // NOI18N
        jMenuItemSuavizado.addActionListener(formListener);
        jMenuFilters.add(jMenuItemSuavizado);

        jMenuItemNegative.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemNegative.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/negativo.png"))); // NOI18N
        jMenuItemNegative.setText(bundle.getString("NEGATIVO")); // NOI18N
        jMenuItemNegative.setName("jMenuItemNegative"); // NOI18N
        jMenuItemNegative.addActionListener(formListener);
        jMenuFilters.add(jMenuItemNegative);

        jMenuItemSepia.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSepia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/sepia.png"))); // NOI18N
        jMenuItemSepia.setText(bundle.getString("SEPIA")); // NOI18N
        jMenuItemSepia.setName("jMenuItemSepia"); // NOI18N
        jMenuItemSepia.addActionListener(formListener);
        jMenuFilters.add(jMenuItemSepia);

        jMenuItemSolve.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/frontera.png"))); // NOI18N
        jMenuItemSolve.setText(bundle.getString("SOBEL")); // NOI18N
        jMenuItemSolve.setName("jMenuItemSolve"); // NOI18N
        jMenuItemSolve.addActionListener(formListener);
        jMenuFilters.add(jMenuItemSolve);

        jMenuItemEfecto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemEfecto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/personalizado.png"))); // NOI18N
        jMenuItemEfecto.setText(bundle.getString("EFECTO")); // NOI18N
        jMenuItemEfecto.setName("jMenuItemEfecto"); // NOI18N
        jMenuItemEfecto.addActionListener(formListener);
        jMenuFilters.add(jMenuItemEfecto);

        jMenuBarra.add(jMenuFilters);

        jMenuOpciones.setText(bundle.getString("OPTIONS")); // NOI18N
        jMenuOpciones.setName("jMenuOpciones"); // NOI18N

        jMenuIdioma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/lenguaje.png"))); // NOI18N
        jMenuIdioma.setText(bundle.getString("LANGUAGE")); // NOI18N
        jMenuIdioma.setName("jMenuIdioma"); // NOI18N

        jRadioButtonMenuItemES.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        buttonGroupIdioma.add(jRadioButtonMenuItemES);
        jRadioButtonMenuItemES.setSelected(true);
        jRadioButtonMenuItemES.setText(bundle.getString("SPANISH")); // NOI18N
        jRadioButtonMenuItemES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/espania.png"))); // NOI18N
        jRadioButtonMenuItemES.setName("jRadioButtonMenuItemES"); // NOI18N
        jRadioButtonMenuItemES.addActionListener(formListener);
        jMenuIdioma.add(jRadioButtonMenuItemES);

        jRadioButtonMenuItemEN.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        buttonGroupIdioma.add(jRadioButtonMenuItemEN);
        jRadioButtonMenuItemEN.setText(bundle.getString("ENGLISH")); // NOI18N
        jRadioButtonMenuItemEN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/uk.png"))); // NOI18N
        jRadioButtonMenuItemEN.setName("jRadioButtonMenuItemEN"); // NOI18N
        jRadioButtonMenuItemEN.addActionListener(formListener);
        jMenuIdioma.add(jRadioButtonMenuItemEN);

        jRadioButtonMenuItemDE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        jRadioButtonMenuItemDE.setText(bundle.getString("GERMANY")); // NOI18N
        jRadioButtonMenuItemDE.setEnabled(false);
        jRadioButtonMenuItemDE.setName("jRadioButtonMenuItemDE"); // NOI18N
        jRadioButtonMenuItemDE.addActionListener(formListener);
        jMenuIdioma.add(jRadioButtonMenuItemDE);

        jMenuOpciones.add(jMenuIdioma);

        jCheckBoxMenuItemSelectLibrary.setText(bundle.getString("SELECT_LIBRARY")); // NOI18N
        jCheckBoxMenuItemSelectLibrary.setToolTipText(bundle.getString("SELECT_LIBRARY_TT")); // NOI18N
        jCheckBoxMenuItemSelectLibrary.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/library.png"))); // NOI18N
        jCheckBoxMenuItemSelectLibrary.setName("jCheckBoxMenuItemSelectLibrary"); // NOI18N
        jMenuOpciones.add(jCheckBoxMenuItemSelectLibrary);

        jMenuBarra.add(jMenuOpciones);

        jMenuAyuda.setText(bundle.getString("HELP")); // NOI18N
        jMenuAyuda.setName("jMenuAyuda"); // NOI18N

        jMenuItemAyuda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/acerca.png"))); // NOI18N
        jMenuItemAyuda.setText(bundle.getString("ABOUT")); // NOI18N
        jMenuItemAyuda.setName("jMenuItemAyuda"); // NOI18N
        jMenuItemAyuda.addActionListener(formListener);
        jMenuAyuda.add(jMenuItemAyuda);

        jMenuBarra.add(jMenuAyuda);

        setJMenuBar(jMenuBarra);

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.FocusListener, java.awt.event.ItemListener, java.awt.event.MouseListener, java.awt.event.MouseWheelListener, javax.swing.event.ChangeListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == jButtonNuevo) {
                VentanaPrincipal.this.jButtonNuevoActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonAbrir) {
                VentanaPrincipal.this.jButtonAbrirActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonGuardar) {
                VentanaPrincipal.this.jButtonGuardarActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonPintar) {
                VentanaPrincipal.this.jToggleButtonPintarActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonMover) {
                VentanaPrincipal.this.jToggleButtonMoverActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonBorrar) {
                VentanaPrincipal.this.jToggleButtonBorrarActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonPunto) {
                VentanaPrincipal.this.jToggleButtonPuntoActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonLinea) {
                VentanaPrincipal.this.jToggleButtonLineaActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonQuadCurve) {
                VentanaPrincipal.this.jToggleButtonQuadCurveActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonCubicCurve) {
                VentanaPrincipal.this.jToggleButtonCubicCurveActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonRectangulo) {
                VentanaPrincipal.this.jToggleButtonRectanguloActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonRectanguloRound) {
                VentanaPrincipal.this.jToggleButtonRectanguloRoundActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonCirculo) {
                VentanaPrincipal.this.jToggleButtonCirculoActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonPolygon) {
                VentanaPrincipal.this.jToggleButtonPolygonActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonRelleno) {
                VentanaPrincipal.this.jToggleButtonRellenoActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonAntiAliasing) {
                VentanaPrincipal.this.jToggleButtonAntiAliasingActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonPlay) {
                VentanaPrincipal.this.jToggleButtonPlayActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonStop) {
                VentanaPrincipal.this.jToggleButtonStopActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonShow) {
                VentanaPrincipal.this.jToggleButtonShowActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonWebcam) {
                VentanaPrincipal.this.jButtonWebcamActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonTakePhoto) {
                VentanaPrincipal.this.jButtonTakePhotoActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonRecord) {
                VentanaPrincipal.this.jToggleButtonRecordActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonSalir) {
                VentanaPrincipal.this.jButtonSalirActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonColorUmbral) {
                VentanaPrincipal.this.jButtonColorUmbralActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemNuevo) {
                VentanaPrincipal.this.jMenuItemNuevoActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemAbrir) {
                VentanaPrincipal.this.jMenuItemAbrirActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemGuardar) {
                VentanaPrincipal.this.jMenuItemGuardarActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemSalir) {
                VentanaPrincipal.this.jMenuItemSalirActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemDeshacer) {
                VentanaPrincipal.this.jMenuItemDeshacerActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemRehacer) {
                VentanaPrincipal.this.jMenuItemRehacerActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemCortar) {
                VentanaPrincipal.this.jMenuItemCortarActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemCopiar) {
                VentanaPrincipal.this.jMenuItemCopiarActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemPegar) {
                VentanaPrincipal.this.jMenuItemPegarActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemVerBarraHerramientas) {
                VentanaPrincipal.this.jCheckBoxMenuItemVerBarraHerramientasActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemVerBarraUtilidades) {
                VentanaPrincipal.this.jCheckBoxMenuItemVerBarraUtilidadesActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemVerBarraEstado) {
                VentanaPrincipal.this.jCheckBoxMenuItemVerBarraEstadoActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemVerBarraSonido) {
                VentanaPrincipal.this.jCheckBoxMenuItemVerBarraSonidoActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemPanelSonido) {
                VentanaPrincipal.this.jCheckBoxMenuItemPanelSonidoActionPerformed(evt);
            }
            else if (evt.getSource() == jCheckBoxMenuItemPantallaCompleta) {
                VentanaPrincipal.this.jCheckBoxMenuItemPantallaCompletaActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemRescaleOP) {
                VentanaPrincipal.this.jMenuItemRescaleOPActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemConvolveOP) {
                VentanaPrincipal.this.jMenuItemConvolveOPActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemAffineTransformOp) {
                VentanaPrincipal.this.jMenuItemAffineTransformOpActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemLookupOp) {
                VentanaPrincipal.this.jMenuItemLookupOpActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemBandCombineOp) {
                VentanaPrincipal.this.jMenuItemBandCombineOpActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemColorConvertOp) {
                VentanaPrincipal.this.jMenuItemColorConvertOpActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemVLCPlayer) {
                VentanaPrincipal.this.jMenuItemVLCPlayerActionPerformed(evt);
            }
            else if (evt.getSource() == jMIBrightnessContrast) {
                VentanaPrincipal.this.jMIBrightnessContrastActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemConvertBlackWhite) {
                VentanaPrincipal.this.jMenuItemConvertBlackWhiteActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemEqualization) {
                VentanaPrincipal.this.jMenuItemEqualizationActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemScaleImage) {
                VentanaPrincipal.this.jMenuItemScaleImageActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemResizeImage) {
                VentanaPrincipal.this.jMenuItemResizeImageActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemCustom) {
                VentanaPrincipal.this.jMenuItemCustomActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemRotateLeft) {
                VentanaPrincipal.this.jMenuItemRotateLeftActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemRotateRight) {
                VentanaPrincipal.this.jMenuItemRotateRightActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemRotate180) {
                VentanaPrincipal.this.jMenuItemRotate180ActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemClone) {
                VentanaPrincipal.this.jMenuItemCloneActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemSenoFunction) {
                VentanaPrincipal.this.jMenuItemSenoFunctionActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemTintar) {
                VentanaPrincipal.this.jMenuItemTintarActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemAdd) {
                VentanaPrincipal.this.jMenuItemAddActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemSubstract) {
                VentanaPrincipal.this.jMenuItemSubstractActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemMultiply) {
                VentanaPrincipal.this.jMenuItemMultiplyActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemBlurMedio) {
                VentanaPrincipal.this.jMenuItemBlurMedioActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemBlurBinomial) {
                VentanaPrincipal.this.jMenuItemBlurBinomialActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemFocus) {
                VentanaPrincipal.this.jMenuItemFocusActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemRelief) {
                VentanaPrincipal.this.jMenuItemReliefActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemBorder) {
                VentanaPrincipal.this.jMenuItemBorderActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemEmboss) {
                VentanaPrincipal.this.jMenuItemEmbossActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemSharpens) {
                VentanaPrincipal.this.jMenuItemSharpensActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemFrontier) {
                VentanaPrincipal.this.jMenuItemFrontierActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemSuavizado) {
                VentanaPrincipal.this.jMenuItemSuavizadoActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemNegative) {
                VentanaPrincipal.this.jMenuItemNegativeActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemSepia) {
                VentanaPrincipal.this.jMenuItemSepiaActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemSolve) {
                VentanaPrincipal.this.jMenuItemSolveActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemEfecto) {
                VentanaPrincipal.this.jMenuItemEfectoActionPerformed(evt);
            }
            else if (evt.getSource() == jRadioButtonMenuItemES) {
                VentanaPrincipal.this.jRadioButtonMenuItemESActionPerformed(evt);
            }
            else if (evt.getSource() == jRadioButtonMenuItemEN) {
                VentanaPrincipal.this.jRadioButtonMenuItemENActionPerformed(evt);
            }
            else if (evt.getSource() == jRadioButtonMenuItemDE) {
                VentanaPrincipal.this.jRadioButtonMenuItemDEActionPerformed(evt);
            }
            else if (evt.getSource() == jMenuItemAyuda) {
                VentanaPrincipal.this.jMenuItemAyudaActionPerformed(evt);
            }
        }

        public void focusGained(java.awt.event.FocusEvent evt) {
        }

        public void focusLost(java.awt.event.FocusEvent evt) {
            if (evt.getSource() == jSliderZoom) {
                VentanaPrincipal.this.jSliderZoomFocusLost(evt);
            }
            else if (evt.getSource() == jSliderRotate) {
                VentanaPrincipal.this.jSliderRotateFocusLost(evt);
            }
            else if (evt.getSource() == jSliderBrigthness) {
                VentanaPrincipal.this.jSliderBrigthnessFocusLost(evt);
            }
            else if (evt.getSource() == jSliderContrast) {
                VentanaPrincipal.this.jSliderContrastFocusLost(evt);
            }
            else if (evt.getSource() == jSliderUmbral) {
                VentanaPrincipal.this.jSliderUmbralFocusLost(evt);
            }
            else if (evt.getSource() == jComboBoxFiltros) {
                VentanaPrincipal.this.jComboBoxFiltrosFocusLost(evt);
            }
        }

        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            if (evt.getSource() == jComboBoxColor) {
                VentanaPrincipal.this.jComboBoxColorItemStateChanged(evt);
            }
            else if (evt.getSource() == jComboBoxTipoLinea) {
                VentanaPrincipal.this.jComboBoxTipoLineaItemStateChanged(evt);
            }
            else if (evt.getSource() == jComboBoxFiltros) {
                VentanaPrincipal.this.jComboBoxFiltrosItemStateChanged(evt);
            }
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == jListSounds) {
                VentanaPrincipal.this.jListSoundsMousePressed(evt);
            }
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
        }

        public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
            if (evt.getSource() == jSpinnerGrosorLinea) {
                VentanaPrincipal.this.jSpinnerGrosorLineaMouseWheelMoved(evt);
            }
            else if (evt.getSource() == jSpinnerTransparencia) {
                VentanaPrincipal.this.jSpinnerTransparenciaMouseWheelMoved(evt);
            }
        }

        public void stateChanged(javax.swing.event.ChangeEvent evt) {
            if (evt.getSource() == jSpinnerGrosorLinea) {
                VentanaPrincipal.this.jSpinnerGrosorLineaStateChanged(evt);
            }
            else if (evt.getSource() == jSpinnerTransparencia) {
                VentanaPrincipal.this.jSpinnerTransparenciaStateChanged(evt);
            }
            else if (evt.getSource() == jSliderZoom) {
                VentanaPrincipal.this.jSliderZoomStateChanged(evt);
            }
            else if (evt.getSource() == jSliderRotate) {
                VentanaPrincipal.this.jSliderRotateStateChanged(evt);
            }
            else if (evt.getSource() == jSliderBrigthness) {
                VentanaPrincipal.this.jSliderBrigthnessStateChanged(evt);
            }
            else if (evt.getSource() == jSliderContrast) {
                VentanaPrincipal.this.jSliderContrastStateChanged(evt);
            }
            else if (evt.getSource() == jSliderUmbral) {
                VentanaPrincipal.this.jSliderUmbralStateChanged(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    /**
     * This code only was touch with another editor for not touch much
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="My Initialized Code">//GEN-BEGIN:myInitComponents
    private void myInitComponents() {
        // pongo aquí los valores de los sppiner para setearlos al principio.
        this.jSpinnerGrosorLinea.setValue(VentanaPrincipal.MIN_VALUE_SPPINER);
        this.jSpinnerTransparencia.setValue(VentanaPrincipal.MAX_VALUE_SPPINER);
        // añadimos los filtros para los cuadros de dialogo de abrir y guardar
        this.filters[this.ALL_FORMAT_IMAGE] = new FileNameExtensionFilter("Todas las Imagenes", "jpg", "jpeg", "png", "gif", "JPG", "JPEG", "PNG", "GIF");
        this.filters[this.JPEG_FILE] = new FileNameExtensionFilter("Joint Photographic Experts Group (JPEG)", "jpg", "jpeg", "JPG", "JPEG"); // NOI18N
        this.filters[this.PNG_FILE] = new FileNameExtensionFilter("Portable Network Graphics (PNG)", "png", "PNG"); // NOI18N
        this.filters[this.GIF_FILE] = new FileNameExtensionFilter("Graphics Interchange Format (GIF)", "gif", "GIF"); // NOI18N
        this.filters[this.ALL_FORMAT_SOUND] = new FileNameExtensionFilter("Todas los Sonidos", "wav", "au", "aiff", "mp3", "WAV", "AU", "AIFF", "MP3"); // NOI18N
        this.filters[this.WAV_FILE] = new FileNameExtensionFilter("Waveform Audio Format (WAV)", "wav", "WAV"); // NOI18N
        this.filters[this.AU_FILE] = new FileNameExtensionFilter("Audio File Format (AU)", "au", "AU"); // NOI18N
        this.filters[this.AIFF_FILE] = new FileNameExtensionFilter("Audio Interchange File Format (AIFF)", "aiff", "AIFF"); // NOI18N
        this.filters[this.MP3_FILE] = new FileNameExtensionFilter("MPEG-2 Audio Layer III (MP3)", "mp3", "MP3"); // NOI18N
        this.filters[this.ALL_FORMAT_VIDEO] = new FileNameExtensionFilter("Todas los Videos", "avi", "mpg", "mpeg", "mp4", "mkv", "AVI", "MPG", "MPEG", "MP4", "MKV"); // NOI18N
        this.filters[this.AVI_FILE] = new FileNameExtensionFilter("Audio Video Interleave (AVI)", "avi", "AVI"); // NOI18N
        this.filters[this.MPG_FILE] = new FileNameExtensionFilter("Moving Picture Experts Group (MPEG)", "mpg", "mpeg", "MPG", "MPEG"); // NOI18N
        this.filters[this.MP4_FILE] = new FileNameExtensionFilter("MP4 file format version 2 (MP4)", "mp4", "MP4"); // NOI18N
        this.filters[this.MKV_FILE] = new FileNameExtensionFilter("Matroska Multimedia Container (MKV)", "mkv", "MKV"); // NOI18N
        // panel de sonido no visible
        this.jPanelOeste.setVisible(false);
        // colocamos la aplicación en el centro
        this.setLocation(Utils.centerOfScreen(this.getWidth(), this.getHeight()));
        // para almacenar los ficheros multimedia
        this.multimedia_files = new ArrayList<Item>();
        // pongo el idioma de la interfaz
        VentanaPrincipal.bundle = ResourceBundle.getBundle("internalization/Bundle"); // NOI18N
        // creo el manejador para el sonido
        this.mySoundListener = new SoundListener();
        // creo el manejador para las ventanas internas.
        this.myVIListener = new VentanaInternaListener(this);
        // para que no se vea el menu de pruebas solo en debugger
        this.jMenuPruebas.setVisible(VentanaPrincipal.DEBUG);
        // para que no se vea los controles de repetición
        this.jPanelOptions.setVisible(VentanaPrincipal.DEBUG);
    }// </editor-fold>//GEN-END:myInitComponents

    /**
     * Clase encargada de manejar los eventos de sonido.
     */
    private class SoundListener extends MediaPlayerEventAdapter implements LineListener {

        /**
         * Variable que almacena el hilo que manipula la barra de progreso del
         * tiempo escuchado en una canción
         */
        private Thread thead_progress_bar;

        /**
         * Con esta función nos aseguramos si el usuario esta usando los
         * controles de la interfaz para seleccionar la siguiente canción de
         * manera automática.
         *
         * @TODO hay que repasarlo por que parece que los hilos de ejecución no
         * son lo mismo para los mp3 como para los wav
         */
        private void next() {
            /* Esto lo hago para cuando el clip llege al final sin que el usuario lo pare manualmente */
            int actual = VentanaPrincipal.this.jListSounds.getSelectedIndex();
            int ultimo = VentanaPrincipal.this.jListSounds.getLastVisibleIndex();
            if (VentanaPrincipal.this.jRadioButtonRepeatAll.isSelected()) {
                if (actual != -1 && actual < ultimo) {
                    VentanaPrincipal.this.jListSounds.setSelectedIndex(actual + 1);
                    if (VentanaPrincipal.this.multimedia_actual instanceof ClipPlayer) {
                        ((ClipPlayer) VentanaPrincipal.this.multimedia_actual).play();
                    } else if (VentanaPrincipal.this.multimedia_actual instanceof AudioMediaPlayerComponent) {
                        ((AudioMediaPlayerComponent) VentanaPrincipal.this.multimedia_actual).getMediaPlayer().play();
                    }
                } else if (actual == ultimo) {
                    VentanaPrincipal.this.jListSounds.setSelectedIndex(0);
                    if (VentanaPrincipal.this.multimedia_actual instanceof ClipPlayer) {
                        ((ClipPlayer) VentanaPrincipal.this.multimedia_actual).play();
                    } else if (VentanaPrincipal.this.multimedia_actual instanceof AudioMediaPlayerComponent) {
                        ((AudioMediaPlayerComponent) VentanaPrincipal.this.multimedia_actual).getMediaPlayer().play();
                    }
                }
            }
            if (VentanaPrincipal.this.jRadioButtonRepeatSound.isSelected()) {
                if (VentanaPrincipal.this.multimedia_actual instanceof ClipPlayer) {
                    ((ClipPlayer) VentanaPrincipal.this.multimedia_actual).play();
                } else if (VentanaPrincipal.this.multimedia_actual instanceof AudioMediaPlayerComponent) {
                    ((AudioMediaPlayerComponent) VentanaPrincipal.this.multimedia_actual).getMediaPlayer().play();
                }
            }
        }

        @Override
        public void update(LineEvent evt) {
            if (evt.getType() == LineEvent.Type.OPEN) {
                /* miramos el tamaño del fichero */
                long file_size_bytes = ((Multimedia) VentanaPrincipal.this.multimedia_actual).getSoundFile().length();
                /* dividimos por el tamaño del frame, para saber cuantos frames tenemos */
                long frames = file_size_bytes / ((ClipPlayer) VentanaPrincipal.this.multimedia_actual).getClip().getFormat().getFrameSize();
                /* almacenamos el tiempo en segundos del clip */
                VentanaPrincipal.this.total_time = (long) (frames / ((ClipPlayer) VentanaPrincipal.this.multimedia_actual).getClip().getFormat().getFrameRate());
                /* Formateamos el resultado para ponerlo en el campo que corresponde */
                VentanaPrincipal.this.jLabelTotalTime.setText(String.format("%02d:%02d", VentanaPrincipal.this.total_time / 60L, VentanaPrincipal.this.total_time % 60L));
            }
            if (evt.getType() == LineEvent.Type.START) {
                if (VentanaPrincipal.this.optionRecording != null) {
                    switch (VentanaPrincipal.this.optionRecording) {
                        case "sound":
                            VentanaPrincipal.this.jToggleButtonRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/parar.png"))); // NOI18N
                            break;
                        case "video":
                            VentanaPrincipal.this.jToggleButtonRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/parar.png"))); // NOI18N
                            // @TODO aqui seguramente tengas que lanzar la ventana de grabación
                            break;
                    }
                    /* Si estoy grabando no puedo hacer nada mas que eso con el sonido. */
                    VentanaPrincipal.this.jToggleButtonPlay.setEnabled(false);
                    VentanaPrincipal.this.jToggleButtonStop.setEnabled(false);
                    VentanaPrincipal.this.jToggleButtonShow.setEnabled(false);
                    VentanaPrincipal.this.jPanelOeste.setVisible(false);
                } else {
                    this.thead_progress_bar = new Thread(() -> {
                        while (((ClipPlayer) VentanaPrincipal.this.multimedia_actual).isWorking()) {
                            /* compruebo cual es el frame en el que estoy y lo divido por el frameRate para calcular en el segundo de la canción donde me encuentro*/
                            long position = (long) (((ClipPlayer) VentanaPrincipal.this.multimedia_actual).getClip().getLongFramePosition() / ((ClipPlayer) VentanaPrincipal.this.multimedia_actual).getClip().getFormat().getFrameRate());
                            /* Formateo el texto para ponerlo en el campo correspondiente, calculo los minutos y los segundos */
                            VentanaPrincipal.this.jLabelElapsedTime.setText(String.format("%02d:%02d", position / 60L, position % 60L));
                            /* calculo el porcentaje que le corresponde a la barra de progreso */
                            VentanaPrincipal.this.jProgressBarMultimedia.setValue((int) ((position * 100) / VentanaPrincipal.this.total_time));
                        }
                    });
                    this.thead_progress_bar.start();
                    VentanaPrincipal.this.jToggleButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/pause.png"))); // NOI18N
                    VentanaPrincipal.this.jToggleButtonPlay.setSelected(true);
                }
            }
            if (evt.getType() == LineEvent.Type.STOP) {
                if (VentanaPrincipal.this.optionRecording != null) {
                    switch (VentanaPrincipal.this.optionRecording) {
                        case "sound":
                            VentanaPrincipal.this.optionRecording = null;
                            /* restauro lo que antes he deshabilitado */
                            VentanaPrincipal.this.jToggleButtonPlay.setEnabled(true);
                            VentanaPrincipal.this.jToggleButtonStop.setEnabled(true);
                            VentanaPrincipal.this.jToggleButtonShow.setEnabled(true);
                            VentanaPrincipal.this.jPanelOeste.setVisible(true);
                            VentanaPrincipal.this.jToggleButtonRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/grabar.png"))); // NOI18N
                            break;
                        case "video":
                            VentanaPrincipal.this.jToggleButtonRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/parar.png"))); // NOI18N
                            // @TODO aquí tendras que restaurar todos los botones y lanzar un cuadro de dialogo para guardar lo grabado
                            break;
                    }
                } else if (((ClipPlayer) VentanaPrincipal.this.multimedia_actual).inPause()) {
                    VentanaPrincipal.this.jToggleButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/reproducir.png"))); // NOI18N
                    VentanaPrincipal.this.jToggleButtonStop.setSelected(true);
                    try {
                        this.thead_progress_bar.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SoundListener.class.getName()).log(Level.SEVERE, null, ex);
                        System.err.println("Error al detener el hilo de actualización de progress bar para wav: " + ex);
                    }
                } else {
                    VentanaPrincipal.this.jLabelElapsedTime.setText("00:00");
                    VentanaPrincipal.this.jProgressBarMultimedia.setValue(0);
                    VentanaPrincipal.this.total_time = 1;
                    VentanaPrincipal.this.jToggleButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/reproducir.png"))); // NOI18N
                    VentanaPrincipal.this.jToggleButtonStop.setSelected(true);
                    ((ClipPlayer) VentanaPrincipal.this.multimedia_actual).stop();
                    this.next();
                }
            }
            if (evt.getType() == LineEvent.Type.CLOSE) {
                this.thead_progress_bar.interrupt();
            }
        }

        @Override
        public void opening(MediaPlayer mediaPlayer) {
            super.opening(mediaPlayer);
        }

        @Override
        public void playing(MediaPlayer mediaPlayer) {
            super.playing(mediaPlayer);
            VentanaPrincipal.this.total_time = (long) (mediaPlayer.getLength() * 0.001f);
            this.thead_progress_bar = new Thread(() -> {
                while (mediaPlayer.isPlaying()) {
                    float position = (mediaPlayer.getTime() * 0.001f);
                    /* Formateo el texto para ponerlo en el campo correspondiente, calculo los minutos y los segundos */
                    VentanaPrincipal.this.jLabelElapsedTime.setText(String.format("%02d:%02d", (int) position / 60, (int) position % 60));
                    /* calculo el porcentaje que le corresponde a la barra de progreso */
                    VentanaPrincipal.this.jProgressBarMultimedia.setValue((int) ((position * 100) / VentanaPrincipal.this.total_time));
                }
            });
            this.thead_progress_bar.start();
            VentanaPrincipal.this.jToggleButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/pause.png"))); // NOI18N
            VentanaPrincipal.this.jToggleButtonPlay.setSelected(true);
            /* Formateo el texto para ponerlo en el campo correspondiente, cálculo los minutos y los segundos */
            VentanaPrincipal.this.jLabelTotalTime.setText(String.format("%02d:%02d", VentanaPrincipal.this.total_time / 60, VentanaPrincipal.this.total_time % 60));
        }

        @Override
        public void paused(MediaPlayer mediaPlayer) {
            super.paused(mediaPlayer);
            VentanaPrincipal.this.jToggleButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/reproducir.png"))); // NOI18N
            VentanaPrincipal.this.jToggleButtonStop.setSelected(true);
            try {
                this.thead_progress_bar.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(SoundListener.class.getName()).log(Level.SEVERE, null, ex);
                System.err.println("Error al detener el hilo de actualización de progress bar para mp3: " + ex);
            }
        }

        @Override
        public void finished(MediaPlayer mediaPlayer) {
            super.finished(mediaPlayer);
            this.next();
            VentanaPrincipal.this.jLabelElapsedTime.setText("00:00");
            VentanaPrincipal.this.jProgressBarMultimedia.setValue(0);
            VentanaPrincipal.this.total_time = 1;
            VentanaPrincipal.this.jToggleButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/reproducir.png"))); // NOI18N
            VentanaPrincipal.this.jToggleButtonStop.setSelected(true);
            this.thead_progress_bar.interrupt();
        }

        @Override
        public void muted(MediaPlayer mediaPlayer, boolean muted) {
            super.muted(mediaPlayer, muted);
        }

        @Override
        public void error(MediaPlayer mediaPlayer) {
            super.error(mediaPlayer);
        }
    }

    /**
     * Este método nos sirve para poder acceder al Escritorio y tener acceso a
     * las ventanas internas por si queremos manipularlas desde otros sitios.
     *
     * @return Devuelve un objeto JDesktopPane con todas las ventanas internas.
     */
    public JDesktopPane getEscritorio() {
        return jDesktopEscritorio;
    }

    /**
     * Con este método lo que conseguimos poner o quitar el menú deshacer
     * operativo para que se pueda llevar a cabo la operación.
     *
     * @param b Si es verdadero se activa la opción deshacer, en caso contrario
     * se desactiva.
     */
    public void setEnabledUndo(boolean b) {
        this.jMenuItemDeshacer.setEnabled(b);
    }

    /**
     * Con este método añadiremos un panel en la parte derecha de la ventana.
     *
     * @param p [in] El panel que queremos añadir
     */
    public void setRightPanel(javax.swing.JPanel p) {
        this.add(p, java.awt.BorderLayout.WEST);
    }

    /**
     * Para poder traducir la aplicación es necesario entrar en los recursos de
     * todos los textos.
     *
     * @return de tipo ResourceBundle contiene todas las cadenas de texto de la
     * aplicación.
     */
    public static ResourceBundle getBundle() {
        return bundle;
    }

    public JSpinner getSpinnerGrosor() {
        return this.jSpinnerGrosorLinea;
    }

    public JSpinner getSpinnerTransparencia() {
        return this.jSpinnerTransparencia;
    }

    public JToggleButton getToggleButtonAntiAliasing() {
        return this.jToggleButtonAntiAliasing;
    }

    public JToggleButton getToggleButtonBorrar() {
        return this.jToggleButtonBorrar;
    }

    public JToggleButton getToggleButtonCirculo() {
        return this.jToggleButtonCirculo;
    }

    public JToggleButton getToggleButtonLinea() {
        return this.jToggleButtonLinea;
    }

    public JToggleButton getToggleButtonMover() {
        return this.jToggleButtonMover;
    }

    public JToggleButton getToggleButtonPintar() {
        return this.jToggleButtonPintar;
    }

    public JToggleButton getToggleButtonPunto() {
        return this.jToggleButtonPunto;
    }

    public JToggleButton getToggleButtonRectangulo() {
        return this.jToggleButtonRectangulo;
    }

    public JToggleButton getToggleButtonRelleno() {
        return this.jToggleButtonRelleno;
    }

    public JToggleButton getToggleButtonStop() {
        return this.jToggleButtonStop;
    }

    public JToggleButton getToggleButtonRecord() {
        return this.jToggleButtonRecord;
    }

    public JLabel getLabelAccionRaton() {
        return this.jLabelAccionRaton;
    }

    public JLabel getLabelColor() {
        return this.jLabelColor;
    }

    public JLabel getLabelHerramienta() {
        return this.jLabelHerramienta;
    }

    public JLabel getLabelMousePos() {
        return this.jLabelMousePos;
    }

    public JLabel getLabelMouseColorPos() {
        return this.jLabelMouseColorPos;
    }

    public JLabel getLabelMouseViewColorPos() {
        return this.jLabelMouseViewColorPos;
    }

    public JComboBox<String> getComboBoxColor() {
        return this.jComboBoxColor;
    }

    public JComboBox<String> getComboBoxFiltros() {
        return this.jComboBoxFiltros;
    }

    public JList getListSounds() {
        return this.jListSounds;
    }

    public JButton getButtonTakePhoto() {
        return this.jButtonTakePhoto;
    }

    public JButton getButtonWebCam() {
        return this.jButtonWebcam;
    }

    public void setOptionRecording(String o) {
        switch (o) {
            case "sound":
            case "video":
                this.optionRecording = o;
                break;
            default:
                throw new IllegalArgumentException("La cadena " + o + " no es valida como parámetro");
        }
    }

    private void jMenuItemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNuevoActionPerformed
        QuestionsSizeImage question = new QuestionsSizeImage(this, true);
        question.setDescriptionDialog("Por favor indique el tamaño de la imagen");
        question.setTextButtonOk("Crear");
        VentanaPrincipal.COUNT_VI++;
        question.setVisible(true);
    }//GEN-LAST:event_jMenuItemNuevoActionPerformed

    private void jMenuItemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAbrirActionPerformed
        JInternalFrame ventana_abierta = this.jDesktopEscritorio.getSelectedFrame();
        if (ventana_abierta instanceof VentanaInternaImagen) {
            ventana_abierta = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
        }
        if (ventana_abierta instanceof VentanaInternaJMFPlayer) {
            ventana_abierta = (VentanaInternaJMFPlayer) this.jDesktopEscritorio.getSelectedFrame();
        }
        if (ventana_abierta instanceof VentanaInternaWebcam) {
            ventana_abierta = (VentanaInternaWebcam) this.jDesktopEscritorio.getSelectedFrame();
        }
        JFileChooser dlg;
        if (this.dir_actual == null) {
            dlg = new JFileChooser();
        } else {
            dlg = new JFileChooser(this.dir_actual);
        }
        for (FileNameExtensionFilter filter : this.filters) {
            dlg.addChoosableFileFilter(filter);
        }
        // para quitar la opción de ver todos las extenciones de ficheros
        dlg.setAcceptAllFileFilterUsed(false);
        // seleccionar varios ficheros al mismo tiempo
        dlg.setMultiSelectionEnabled(true);
        // mostramos el cuadro de dialogo
        if (dlg.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            java.io.File[] file_list_aux = dlg.getSelectedFiles();
            // Recorremos lo ficheros para ver de que tipo son y abrirlos
            for (int i = 1; i <= file_list_aux.length; i++) {
                // @TODO Mejora muy considerable para buscar las extensiones de los ficheros
                File faux = new File(file_list_aux[i-1].getAbsolutePath());
                // se puede hacer mejor pero para esta aplicación me vale
                this.dir_actual = faux.getPath();
                try {
                    if (this.filters[this.ALL_FORMAT_IMAGE].contains(faux.getExt())) {
                        BufferedImage img = ImageIO.read(faux);
                        this.vi = new VentanaInternaImagen(this, new Dimension(img.getWidth(), img.getHeight()), img.getType());
                        ((VentanaInternaImagen) this.vi).setListener(this.myVIListener);
                        if (ventana_abierta != null) {
                            this.vi.setLocation(ventana_abierta.getLocation().x + (i * 15), ventana_abierta.getLocation().y + (i * 15));
                        } else {
                            this.vi.setLocation(15, 15);
                        }
                        ((VentanaInternaImagen) this.vi).getLienzo().setImage(img);
                        this.getEscritorio().add(this.vi);
                        this.vi.setTitle(file_list_aux[i-1].getName());
                        this.vi.setVisible(true);
                    } else if (this.filters[this.ALL_FORMAT_SOUND].contains(faux.getExt()) || this.filters[this.ALL_FORMAT_VIDEO].contains(faux.getExt())) {
                        this.multimedia_files.add(new Item(faux.getFriendlyName(), faux.getExt(), faux.getPath(), this.multimedia_files.size()));
                        this.list_model.addElement(faux.getFriendlyName());
                        boolean aux = this.jCheckBoxOpenPanel.isSelected();
                        this.jCheckBoxMenuItemPanelSonido.setSelected(aux);
                        this.jToggleButtonShow.setSelected(aux);
                        this.jPanelOeste.setVisible(aux);
                    }
                } catch (FileNotFoundException ex) {
                    System.err.println("El Fichero " + file_list_aux[i-1].getName() + " no existe " + ex);
                } catch (IOException ex) {
                    System.err.println("No se pudo crear el fichero " + file_list_aux[i-1].getName() + " " + ex);
                } catch (NullPointerException ex) {
                    System.err.println("No se permite el valor null en el fichero " + file_list_aux[i-1].getName() + " " + ex);
                } catch (Exception ex) {
                    System.err.println("Error desconocido con el fichero " + file_list_aux[i-1].getName() + " " + ex);
                }
            }
        }
    }//GEN-LAST:event_jMenuItemAbrirActionPerformed

    private void jMenuItemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGuardarActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "Se perderan todas las formas del Lienzo\ny se guardara la imagen con las formas.", "¿Esta seguro?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && n == 0) {
            JFileChooser dlg = new JFileChooser();
            if (this.vi instanceof VentanaInternaImagen) {
                this.vi = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
                int type = ((VentanaInternaImagen) this.vi).getLienzo().getImage().getType();
                switch (type) {
                    case BufferedImage.TYPE_INT_RGB:
                        dlg.addChoosableFileFilter(this.filters[this.JPEG_FILE]);
                        dlg.addChoosableFileFilter(this.filters[this.PNG_FILE]);
                        dlg.setAcceptAllFileFilterUsed(false);
                        break;
                    case BufferedImage.TYPE_INT_ARGB:
                        dlg.addChoosableFileFilter(this.filters[this.PNG_FILE]);
                        dlg.setAcceptAllFileFilterUsed(false);
                        break;
                    case BufferedImage.TYPE_BYTE_INDEXED:
                        dlg.addChoosableFileFilter(this.filters[this.GIF_FILE]);
                        dlg.setAcceptAllFileFilterUsed(false);
                        break;
                    default:
                        dlg.addChoosableFileFilter(this.filters[this.JPEG_FILE]);
                        dlg.addChoosableFileFilter(this.filters[this.PNG_FILE]);
                        dlg.addChoosableFileFilter(this.filters[this.GIF_FILE]);
                        dlg.setAcceptAllFileFilterUsed(true);
                        break;
                }
            } else if (this.vi instanceof VentanaInternaJMFPlayer || this.vi instanceof VentanaInternaWebcam) {
                this.vi = (VentanaInternaJMFPlayer) this.jDesktopEscritorio.getSelectedFrame();
                dlg.addChoosableFileFilter(this.filters[this.AVI_FILE]);
                dlg.addChoosableFileFilter(this.filters[this.MPG_FILE]);
                dlg.addChoosableFileFilter(this.filters[this.MP4_FILE]);
                dlg.setAcceptAllFileFilterUsed(false);
            }
            if (dlg.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File f = new File(dlg.getSelectedFile().getAbsolutePath());
                try {
                    if (this.vi instanceof VentanaInternaImagen) {
                        this.vi = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
                        BufferedImage img = ((VentanaInternaImagen) this.vi).getLienzo().getImage(true);
                        ((VentanaInternaImagen) this.vi).getLienzo().clean();
                        if (img != null) {
                            if (dlg.getFileFilter().equals(this.filters[this.JPEG_FILE])) {
                                ImageIO.write(img, "jpg", f);
                            } else if (dlg.getFileFilter().equals(this.filters[this.PNG_FILE])) {
                                ImageIO.write(img, "png", f);
                            } else if (dlg.getFileFilter().equals(this.filters[this.GIF_FILE])) {
                                ImageIO.write(img, "gif", f);
                            }
                        }
//                    }else if (this.vi instanceof VentanaInternaSound) {
//                        this.vi_sound = (VentanaInternaSound) this.jDesktopEscritorio.getSelectedFrame();
//                        /*
//                        Aqui tienes que crear la variable para poder guardar el fichero
//                         */
//                        BufferedImage sound = new BufferedImage(1, 1, 3);
//                        if (sound != null) {
//                            if (dlg.getFileFilter().equals(this.filters[this.WAV_FILE])) {
//                                ImageIO.write(sound, "wav", f);
//                            } else if (dlg.getFileFilter().equals(this.filters[this.AU_FILE])) {
//                                ImageIO.write(sound, "au", f);
//                            } else if (dlg.getFileFilter().equals(this.filters[this.AIFF_FILE])) {
//                                ImageIO.write(sound, "aiff", f);
//                            }
//                        }
                    } else if (this.vi instanceof VentanaInternaJMFPlayer) {
                        /* Aqui tienes que crear la variable para poder guardar el fichero */
                    } else if (this.vi instanceof VentanaInternaWebcam) {
                        /* Aqui tienes que crear la variable para poder guardar el fichero */
                    }
                    this.vi.setTitle(f.getName());
                } catch (Exception ex) {
                    System.err.println("Error al guardar la imagen " + ex);
                }
            }
            VentanaPrincipal.COUNT_VI--;
        }
    }//GEN-LAST:event_jMenuItemGuardarActionPerformed

    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemSalirActionPerformed

    private void jMenuItemDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeshacerActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (VentanaPrincipal.UNDO != null && this.vi != null && this.vi instanceof VentanaInternaImagen) {
            VentanaPrincipal.REDO = ((VentanaInternaImagen) this.vi).getLienzo().getImage();
            ((VentanaInternaImagen) this.vi).getLienzo().setImage(VentanaPrincipal.UNDO);
            myRectangleShape border_aux = (myRectangle) ((VentanaInternaImagen) this.vi).getLienzo().getVisiblePart();
            border_aux.setWidth(VentanaPrincipal.UNDO.getWidth());
            border_aux.setHeigth(VentanaPrincipal.UNDO.getHeight());
            VentanaPrincipal.UNDO = null;
            this.jMenuItemDeshacer.setEnabled(false);
            this.jMenuItemRehacer.setEnabled(true);
        }
    }//GEN-LAST:event_jMenuItemDeshacerActionPerformed

    private void jMenuItemRehacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRehacerActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (VentanaPrincipal.REDO != null && this.vi != null && this.vi instanceof VentanaInternaImagen) {
            VentanaPrincipal.UNDO = ((VentanaInternaImagen) this.vi).getLienzo().getImage();
            ((VentanaInternaImagen) this.vi).getLienzo().setImage(VentanaPrincipal.REDO);
            myRectangleShape border_aux = (myRectangle) ((VentanaInternaImagen) this.vi).getLienzo().getVisiblePart();
            border_aux.setWidth(VentanaPrincipal.REDO.getWidth());
            border_aux.setHeigth(VentanaPrincipal.REDO.getHeight());
            VentanaPrincipal.REDO = null;
            this.jMenuItemDeshacer.setEnabled(true);
            this.jMenuItemRehacer.setEnabled(false);
        }
    }//GEN-LAST:event_jMenuItemRehacerActionPerformed

    private void jMenuItemCortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCortarActionPerformed
        //@TODO falta implementarlo
        throw new UnsupportedOperationException("This method not implemented: jMenuItemCortarActionPerformed in VentanaPrincipal.java");
    }//GEN-LAST:event_jMenuItemCortarActionPerformed

    private void jMenuItemCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCopiarActionPerformed
        //@TODO falta implementarlo
        throw new UnsupportedOperationException("This method not implemented: jMenuItemCopiarActionPerformed in VentanaPrincipal.java");
    }//GEN-LAST:event_jMenuItemCopiarActionPerformed

    private void jMenuItemPegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPegarActionPerformed
        //@TODO falta implementarlo
        throw new UnsupportedOperationException("This method not implemented: jMenuItemPegarActionPerformed in VentanaPrincipal.java");
    }//GEN-LAST:event_jMenuItemPegarActionPerformed

    private void jCheckBoxMenuItemVerBarraHerramientasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemVerBarraHerramientasActionPerformed
        this.jToolBarTools.setVisible(this.jCheckBoxMenuItemVerBarraHerramientas.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemVerBarraHerramientasActionPerformed

    private void jCheckBoxMenuItemVerBarraUtilidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemVerBarraUtilidadesActionPerformed
        this.jPanelSplitDerecho.setVisible(this.jCheckBoxMenuItemVerBarraUtilidades.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemVerBarraUtilidadesActionPerformed

    private void jCheckBoxMenuItemVerBarraEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemVerBarraEstadoActionPerformed
        this.jPanelStatusBar.setVisible(this.jCheckBoxMenuItemVerBarraEstado.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemVerBarraEstadoActionPerformed

    private void jCheckBoxMenuItemVerBarraSonidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemVerBarraSonidoActionPerformed
        this.jToolBarSound.setVisible(this.jCheckBoxMenuItemVerBarraSonido.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemVerBarraSonidoActionPerformed

    private void jCheckBoxMenuItemPanelSonidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemPanelSonidoActionPerformed
        this.jPanelOeste.setVisible(this.jCheckBoxMenuItemPanelSonido.isSelected());
        this.jToggleButtonShow.setSelected(this.jCheckBoxMenuItemPanelSonido.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemPanelSonidoActionPerformed

    private void jCheckBoxMenuItemPantallaCompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemPantallaCompletaActionPerformed
        this.jCheckBoxMenuItemVerBarraUtilidades.setSelected(this.FULLSCREEN);
        this.jCheckBoxMenuItemVerBarraHerramientas.setSelected(this.FULLSCREEN);
        this.jCheckBoxMenuItemVerBarraEstado.setSelected(this.FULLSCREEN);
        this.jCheckBoxMenuItemVerBarraSonido.setSelected(this.FULLSCREEN);
        this.jCheckBoxMenuItemPanelSonido.setSelected(false);
        this.jPanelSplitDerecho.setVisible(this.FULLSCREEN);
        this.jToolBarTools.setVisible(this.FULLSCREEN);
        this.jPanelStatusBar.setVisible(this.FULLSCREEN);
        this.jToolBarSound.setVisible(this.FULLSCREEN);
        this.jPanelOeste.setVisible(false);
        if (this.getExtendedState() == javax.swing.JFrame.NORMAL) {
            this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
            //this.setUndecorated(true);
        } else {
            this.setExtendedState(javax.swing.JFrame.NORMAL);
            //this.setUndecorated(false);
        }
        this.FULLSCREEN = !this.FULLSCREEN;
    }//GEN-LAST:event_jCheckBoxMenuItemPantallaCompletaActionPerformed

    private void jMIBrightnessContrastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIBrightnessContrastActionPerformed
        // @TODO Implementar el brillo una ventanita que salga los controles
        throw new UnsupportedOperationException("This method not implemented: jMIBrightnessContrastActionPerformed in VentanaPrincipal.java");
    }//GEN-LAST:event_jMIBrightnessContrastActionPerformed

    private void jMenuItemConvertBlackWhiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConvertBlackWhiteActionPerformed
        if (this.canApply(BufferedImage.TYPE_INT_RGB)) {
            try {
                ICC_Profile ip = ICC_Profile.getInstance(ColorSpace.CS_GRAY);
                ColorSpace cs = new ICC_ColorSpace(ip);
                ColorConvertOp ccop = new ColorConvertOp(cs, null);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(ccop.filter(this.imgTemp, null));
                this.imgOrigen = this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception e) {
                System.err.println("Error al aplicar el ColorConvert " + e.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_jMenuItemConvertBlackWhiteActionPerformed

    private void jMenuItemResizeImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemResizeImageActionPerformed
        QuestionsResizeImage question = new QuestionsResizeImage(this, true);
        question.setDescriptionDialog("Por favor indique el nuevo tamaño");
        question.setTextButtonOk("Redimensionar");
        question.setVisible(true);
    }//GEN-LAST:event_jMenuItemResizeImageActionPerformed

    private void jMenuItemEqualizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEqualizationActionPerformed
        if (this.canApply(BufferedImage.TYPE_INT_ARGB)) {
            try {
                EqualizationOP eqop = new EqualizationOP();
                this.imgTemp = eqop.filter(this.imgOrigen, null);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgTemp);
                this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (HeadlessException ex) {
                System.err.println("Error aplicando la función de ecualizar imágen " + ex);
            }
        }
    }//GEN-LAST:event_jMenuItemEqualizationActionPerformed
    
    private void jMenuItemScaleImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemScaleImageActionPerformed
        QuestionsRescaleImage question = new QuestionsRescaleImage(this, true);
        question.setDescriptionDialog("Por favor indique el nuevo tamaño");
        question.setTextButtonOk("Reescalar");
        question.setVisible(true);
    }//GEN-LAST:event_jMenuItemScaleImageActionPerformed

    private void jMenuItemCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCustomActionPerformed
        // @TODO tienes que implementar un cuadro de dialogo que te permita mostrar propiedades para rotación
        throw new UnsupportedOperationException("This method not implemented: jMenuItemCustomActionPerformed in VentanaPrincipal.java");
    }//GEN-LAST:event_jMenuItemCustomActionPerformed

    private void jMenuItemRotateLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRotateLeftActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            if (this.imgOrigen == null) {
                this.imgOrigen = ((VentanaInternaImagen) this.vi).getLienzo().getImage();
                // para el deshacer
                VentanaPrincipal.UNDO = this.imgOrigen;
                this.setEnabledUndo(true);
                ((VentanaInternaImagen) this.vi).getLienzo().setTypeImage(BufferedImage.TYPE_INT_ARGB);
            }
            if (this.imgOrigen != null) {
                AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(270.0f), this.imgOrigen.getWidth() / 2, this.imgOrigen.getHeight() / 2); // rotate
                try {
                    AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    this.imgTemp = new BufferedImage(this.imgOrigen.getHeight(), this.imgOrigen.getWidth(), BufferedImage.TYPE_INT_ARGB);
                    this.imgTemp.createGraphics().drawImage(this.imgOrigen, atop, 0, 0);
                    ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgTemp);
                    this.imgOrigen = this.imgTemp = null;
                } catch (Exception e) {
                    System.err.println("Error rotando la imagen a 90º a la izquierda " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemRotateLeftActionPerformed

    private void jMenuItemRotateRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRotateRightActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            if (this.imgOrigen == null) {
                this.imgOrigen = ((VentanaInternaImagen) this.vi).getLienzo().getImage();
                // para el deshacer
                VentanaPrincipal.UNDO = this.imgOrigen;
                this.setEnabledUndo(true);
                ((VentanaInternaImagen) this.vi).getLienzo().setTypeImage(BufferedImage.TYPE_INT_ARGB);
            }
            if (this.imgOrigen != null) {
                AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90.0f), this.imgOrigen.getWidth() / 2, this.imgOrigen.getHeight() / 2); // rotate
                try {
                    AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    this.imgTemp = new BufferedImage(this.imgOrigen.getHeight(), this.imgOrigen.getWidth(), BufferedImage.TYPE_INT_ARGB);
                    this.imgTemp.createGraphics().drawImage(this.imgOrigen, atop, 0, 0);
                    ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgTemp);
                    this.imgOrigen = this.imgTemp = null;
                } catch (Exception e) {
                    System.err.println("Error rotando la imagen a 90º a la derecha " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemRotateRightActionPerformed

    private void jMenuItemRotate180ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRotate180ActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            if (this.imgOrigen == null) {
                ((VentanaInternaImagen) this.vi).getLienzo().setTypeImage(BufferedImage.TYPE_INT_ARGB);
                this.imgOrigen = ((VentanaInternaImagen) this.vi).getLienzo().getImage();
                // para el deshacer
                VentanaPrincipal.UNDO = this.imgOrigen;
                this.setEnabledUndo(true);
            }
            if (this.imgOrigen != null) {
                AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(180.0f), this.imgOrigen.getWidth() / 2, this.imgOrigen.getHeight() / 2); // rotate
                try {
                    AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    this.imgTemp = new BufferedImage(this.imgOrigen.getWidth(), this.imgOrigen.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    this.imgTemp.createGraphics().drawImage(this.imgOrigen, atop, 0, 0);
                    ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgTemp);
                    this.imgOrigen = null;
                } catch (Exception e) {
                    System.err.println("Error rotando la imagen a 180º " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemRotate180ActionPerformed

    private void jMenuItemCloneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCloneActionPerformed
        // @TODO Este metodo se puede hacer mucho mas corto pero lo hago así
        // para que se entienda cuando se lea el código.
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            BufferedImage img_aux = ((VentanaInternaImagen) this.vi).getLienzo().getImage();
            VentanaInternaImagen new_vi = new VentanaInternaImagen(this, new Dimension(img_aux.getWidth(), img_aux.getHeight()), img_aux.getType());
            new_vi.setListener(this.myVIListener);
            // @TODO tienes que recorrer el array de formas y añadirlas a la nueva ventana
            new_vi.setLocation(((VentanaInternaImagen) this.vi).getLocation().x + 15, ((VentanaInternaImagen) this.vi).getLocation().y + 15);
            new_vi.getLienzo().setImage(img_aux);
            this.jDesktopEscritorio.add(new_vi);
            VentanaPrincipal.COUNT_VI++;
            new_vi.setTitle("(Sin Título " + VentanaPrincipal.COUNT_VI + ")");
            new_vi.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItemCloneActionPerformed

    /**
     * Con este método quitamos muchas lineas de código por que siempre
     * comprobamos lo mismo si esta correcta la ventana interna y si la imagen
     * original es igual a null para no tener que poner lo mismo en todo los
     * métodos,
     *
     * Hay que destacar que la imagen original se almacena sin modificaciones
     * ninguna, que la imagen temporal se almacena modificando el tipo de imagen
     * de la original a la que se pasa por parámetros, y que para que funcionen
     * todos los métodos se tiene que poner a null la imagen original una vez
     * terminado el proceso de retoque en el caso de las opciones del menú se
     * pone a null cuando finalice el método en caso de los slider se pone a
     * null cuando el slider pierde el foco.
     *
     * @param t [in] Tipo al que queremos convertir la imgTemp para realizar la
     * operación, si usamos -1 dejamos el tipo de la imagen sin cambiar.
     * @return si se puede aplicar el efecto a la imagen original devuelve
     * verdadero, falso en caso contrario.
     */
    private boolean canApply(int t) {
        boolean aux = false;
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            if (this.imgOrigen == null) {
                this.imgOrigen = ((VentanaInternaImagen) this.vi).getLienzo().getImage();
            }
            if (t != -1) {
                ((VentanaInternaImagen) this.vi).getLienzo().setTypeImage(t);
            }
            this.imgTemp = ((VentanaInternaImagen) this.vi).getLienzo().getImage();
            // para el deshacer
            VentanaPrincipal.UNDO = this.imgTemp;
        }
        if (this.imgTemp != null && this.vi instanceof VentanaInternaImagen) {
            aux = true;
        }
        return aux;
    }

    private void jMenuItemSenoFunctionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSenoFunctionActionPerformed
        if (this.canApply(BufferedImage.TYPE_INT_ARGB)) {
            try {
                LookupTable seno = Utils.senoFuction(180.0f / 255.0f);
                LookupOp ltop = new LookupOp(seno, null);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(ltop.filter(this.imgTemp, null));
                this.imgOrigen = this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception ex) {
                System.err.println("Error aplicando la función seno " + ex);
            }
        }
    }//GEN-LAST:event_jMenuItemSenoFunctionActionPerformed

    private void jMenuItemTintarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTintarActionPerformed
        if (this.canApply(BufferedImage.TYPE_INT_ARGB)) {
            try {
                TintOP tint;
                if (((VentanaInternaImagen) this.vi).getLienzo().withFill()) {
                    tint = new TintOP(((VentanaInternaImagen) this.vi).getLienzo().getFillColor(), 50);
                    ((VentanaInternaImagen) this.vi).getLienzo().setImage(tint.filter(this.imgTemp, null));
                } else {
                    JOptionPane.showConfirmDialog(this, "Por favor elija un color de fondo\n antes de usar este filtro", "Advertencia", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
                }
                this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (HeadlessException ex) {
                System.err.println("Error aplicando la función de tintar imágen " + ex);
            }
        }
    }//GEN-LAST:event_jMenuItemTintarActionPerformed

    private void jMenuItemRescaleOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRescaleOPActionPerformed
        /*
        La función matematica que se aplica con esta operacion de RescaleOP
        es la siguiente:
            g(x,y) = alfa * f(x,y) + beta
        donde x e y son las coordenadas del pixel al que queremos aplicar la
        función, asi pues si multiplicamos por 1 (alfa=1.0F) y le sumamos 0
        (beta=0.0F) la función g(x,y) será igual que f(x,y).

        Como particularidad decir que las imagenes con transparencia tiene
        4 canales R, G, B y A don de A es el alpha de la transparencia.

        Para estos casos tenemos que pasar un vector con los valores para cada
        uno de los canales R, G, B, y A (float[] alfas = {1.0F,1.0F,1.0F,1.0F};)
         */
        if (this.canApply(BufferedImage.TYPE_INT_ARGB)) {
            try {
                // multiplicamos por 1 R,G y B se quedan igual
                float[] alfas = {1.0F, 1.0F, 1.0F, 1.0F};
                // sumamos 100 a R, G y B le subimos el brillo
                float[] betas = {100.0F, 100.0F, 100.0F, 0.0F,};
                // el ultimo componente de los vectores es la transparencia
                // que la dejamos igual en todos lados.
                RescaleOp rop = new RescaleOp(alfas, betas, null);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(rop.filter(this.imgTemp, null));
                this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (IllegalArgumentException ex) {
                System.err.println("Error aplicando filtro rescale " + ex);
            }
        }
    }//GEN-LAST:event_jMenuItemRescaleOPActionPerformed

    private void jMenuItemConvolveOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConvolveOPActionPerformed
        if (this.canApply(BufferedImage.TYPE_INT_RGB)) {
            try {
                /*
                    Activamos la opción EDGE_NO_OP, de forma que los pixeles del
                    borde (en los que no se puede aplicar la convolución) se
                    copiarán de la original. Si se optase por la opción
                    EDGE_ZERO_FILL, el borde se pondría a cero (ésta es la
                    opción por defecto).
                 */
                float[] datamask = {1f, 1f, 1f, 1f, -8f, 1f, 1f, 1f, 1f};
                Kernel mask = new Kernel(3, 3, datamask);
                ConvolveOp cop = new ConvolveOp(mask, ConvolveOp.EDGE_NO_OP, null);
                this.imgTemp = cop.filter(this.imgTemp, null);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgTemp);
                this.imgOrigen = this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (IllegalArgumentException ex) {
                System.err.println("Error al aplicar el convolve: " + ex);
            }
        }
    }//GEN-LAST:event_jMenuItemConvolveOPActionPerformed

    private void jMenuItemAffineTransformOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAffineTransformOpActionPerformed
        if (this.canApply(BufferedImage.TYPE_INT_ARGB)) {
            //AffineTransform at = AffineTransform.getScaleInstance(1.25, 1.25); // resize
            //AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(180.0), img_aux.getWidth() / 2, img_aux.getHeight() / 2); // rotate
            AffineTransform at = AffineTransform.getShearInstance(0.5, 0.0);
            //AffineTransform at = AffineTransform.getTranslateInstance(20.0, 20.0);
            try {
                AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                this.imgTemp = atop.filter(this.imgTemp, null);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgTemp);
                this.imgOrigen = this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception ex) {
                System.err.println("Error aplicando filtro affine " + ex);
            }
        }
    }//GEN-LAST:event_jMenuItemAffineTransformOpActionPerformed

    private void jMenuItemLookupOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLookupOpActionPerformed
        if (this.canApply(BufferedImage.TYPE_INT_ARGB)) {
            try {
                short lt[] = new short[256];
                for (int i = 0; i < 256; i++) {
                    lt[i] = (short) (255 - i); // Negativo
                }
                ShortLookupTable slt = new ShortLookupTable(0, lt);
                LookupOp lop = new LookupOp(slt, null);
                this.imgTemp = lop.filter(this.imgTemp, null);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgTemp);
                this.imgOrigen = this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception ex) {
                System.err.println("Error al aplicar el Lookup " + ex);
            }
        }
    }//GEN-LAST:event_jMenuItemLookupOpActionPerformed

    private void jMenuItemBandCombineOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBandCombineOpActionPerformed
        if (this.canApply(BufferedImage.TYPE_INT_ARGB)) {
            this.imgOrigen = ((VentanaInternaImagen) this.vi).getLienzo().getImage();
            try {
                /*
                    Con este tipo de conbinación podemos cambiar el color verde
                    por el azul osea donde esta uno ponemos el otro.
                 */
                float[][] m = {
                    {1.0F, 0.0F, 0.0F, 0.0F},
                    {0.0F, 0.0F, 1.0F, 0.0F},
                    {0.0F, 1.0F, 0.0F, 0.0F}
                };
                BandCombineOp bcop = new BandCombineOp(m, null);
                WritableRaster rasterdest = bcop.filter(this.imgOrigen.getRaster(), null);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(new BufferedImage(this.imgOrigen.getColorModel(), rasterdest, false, null));
                this.imgOrigen = this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception ex) {
                System.err.println("Error al aplicar el BandCombine " + ex);
            }
            this.imgOrigen = null;
        }
    }//GEN-LAST:event_jMenuItemBandCombineOpActionPerformed

    private void jMenuItemColorConvertOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemColorConvertOpActionPerformed
        if (this.canApply(BufferedImage.TYPE_INT_RGB)) {
            try {
                ICC_Profile ip = ICC_Profile.getInstance(ColorSpace.CS_GRAY);
                ColorSpace cs = new ICC_ColorSpace(ip);
                ColorConvertOp ccop = new ColorConvertOp(cs, null);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(ccop.filter(this.imgTemp, null));
                this.imgOrigen = this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception ex) {
                System.err.println("Error al aplicar el ColorConvert " + ex);
            }
        }
    }//GEN-LAST:event_jMenuItemColorConvertOpActionPerformed

    private void jMenuItemVLCPlayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVLCPlayerActionPerformed
        VentanaInternaVLCPlayer reproductorVLC = new VentanaInternaVLCPlayer();
        reproductorVLC.setListener(this.myVIListener);
        this.jDesktopEscritorio.add(reproductorVLC);
        reproductorVLC.setVisible(true);
    }//GEN-LAST:event_jMenuItemVLCPlayerActionPerformed

    private void jMenuItemBlurMedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBlurMedioActionPerformed
        float[] datamask = {0.11f, 0.11f, 0.11f, 0.11f, 0.2f, 0.11f, 0.11f, 0.11f, 0.11f};
        Kernel mask = new Kernel(3, 3, datamask);
        this.applyFilter(mask);
    }//GEN-LAST:event_jMenuItemBlurMedioActionPerformed

    private void jMenuItemBlurBinomialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBlurBinomialActionPerformed
        float[] datamask = {0.0625f, 0.125f, 0.0625f, 0.125f, 0.25f, 0.125f, 0.0625f, 0.125f, 0.0625f};
        Kernel mask = new Kernel(3, 3, datamask);
        this.applyFilter(mask);
    }//GEN-LAST:event_jMenuItemBlurBinomialActionPerformed

    private void jMenuItemFocusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFocusActionPerformed
        float[] datamask = {0f, -1f, 0f, -1f, 5f, -1f, 0f, -1f, 0f};
        Kernel mask = new Kernel(3, 3, datamask);
        this.applyFilter(mask);
    }//GEN-LAST:event_jMenuItemFocusActionPerformed

    private void jMenuItemReliefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReliefActionPerformed
        float[] datamask = {-2f, -1f, 0f, -1f, 1f, 1f, 0f, 1f, 2f};
        //float[] datamask = KernelProducer.MASCARA_RELIEVE_3x3;
        Kernel mask = new Kernel(3, 3, datamask);
        this.applyFilter(mask);
    }//GEN-LAST:event_jMenuItemReliefActionPerformed

    private void jMenuItemBorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBorderActionPerformed
        float[] datamask = new float[]{0f, 0f, 0f, -1f, 1f, 0f, 0f, 0f, 0f};
        Kernel mask = new Kernel(3, 3, datamask);
        this.applyFilter(mask);
    }//GEN-LAST:event_jMenuItemBorderActionPerformed

    private void jMenuItemEmbossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEmbossActionPerformed
        float[] datamask = new float[]{-2f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 2f};
        Kernel mask = new Kernel(3, 3, datamask);
        this.applyFilter(mask);
    }//GEN-LAST:event_jMenuItemEmbossActionPerformed

    private void jMenuItemSharpensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSharpensActionPerformed
        float[] datamask = new float[]{-1f, -1f, -1f, -1f, 9f, -1f, -1f, -1f, -1f,};
        Kernel mask = new Kernel(3, 3, datamask);
        this.applyFilter(mask);
    }//GEN-LAST:event_jMenuItemSharpensActionPerformed

    private void jMenuItemFrontierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFrontierActionPerformed
        float[] datamask = {1f, 1f, 1f, 1f, -8f, 1f, 1f, 1f, 1f};
        Kernel mask = new Kernel(3, 3, datamask);
        this.applyFilter(mask);
    }//GEN-LAST:event_jMenuItemFrontierActionPerformed

    private void jMenuItemSuavizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSuavizadoActionPerformed
        float[] datamask = {
            0.0030f, 0.0133f, 0.0219f, 0.0133f, 0.0030f,
            0.0133f, 0.0596f, 0.0983f, 0.0596f, 0.0133f,
            0.0219f, 0.0983f, 0.1621f, 0.0983f, 0.0219f,
            0.0133f, 0.0596f, 0.0983f, 0.0596f, 0.0133f,
            0.0030f, 0.0133f, 0.0219f, 0.0133f, 0.0030f
        };
        Kernel mask = new Kernel(5, 5, datamask);
        this.applyFilter(mask);
    }//GEN-LAST:event_jMenuItemSuavizadoActionPerformed

    private void jMenuItemNegativeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNegativeActionPerformed
        if (this.canApply(BufferedImage.TYPE_INT_ARGB)) {
            try {
                LookupTable lt = LookupTableProducer.negativeFuction();
                LookupOp lop = new LookupOp(lt, null);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(lop.filter(this.imgTemp, null));
                this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception ex) {
                System.err.println("Error al aplicar el Negativo " + ex);
            }
        }
    }//GEN-LAST:event_jMenuItemNegativeActionPerformed

    private void jMenuItemSepiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSepiaActionPerformed
        if (this.canApply(BufferedImage.TYPE_INT_RGB)) {
            try {
                SepiaOP sepop = new SepiaOP();
                this.imgTemp = sepop.filter(this.imgTemp, null);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgTemp);
                this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception ex) {
                System.err.println("Error al aplicar el Sepia " + ex);
            }
        }
    }//GEN-LAST:event_jMenuItemSepiaActionPerformed

    private void jMenuItemSolveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSolveActionPerformed
        if (this.canApply(BufferedImage.TYPE_INT_RGB)) {
            try {
                SobelOP solop = new SobelOP();
                this.imgTemp = solop.filter(this.imgTemp, null);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgTemp);
                this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception ex) {
                System.err.println("Error al aplicar el filtro Solve " + ex);
            }
        }
    }//GEN-LAST:event_jMenuItemSolveActionPerformed

    private void jMenuItemEfectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEfectoActionPerformed
        if (this.canApply(BufferedImage.TYPE_INT_RGB)) {
            try {
                mySecondEfectOP myop = new mySecondEfectOP();
                myop.onliShadow();
                this.imgTemp = myop.filter(this.imgTemp, null);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgTemp);
                this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception ex) {
                System.err.println("Error al aplicar el Mi propio efecto " + ex);
            }
        }
    }//GEN-LAST:event_jMenuItemEfectoActionPerformed

    private void jMenuItemAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAddActionPerformed
        QuestionsSelectImages question = new QuestionsSelectImages(this, true);
        question.setDescriptionDialog("Por favor seleccione las imagenes a mezclar");
        question.setTextButtonOk("Seleccionar");
        question.setOperation(QuestionsSelectImages.ADD);
        question.setVisible(true);
    }//GEN-LAST:event_jMenuItemAddActionPerformed

    private void jMenuItemSubstractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSubstractActionPerformed
        QuestionsSelectImages question = new QuestionsSelectImages(this, true);
        question.setDescriptionDialog("Por favor seleccione las imagenes a mezclar");
        question.setTextButtonOk("Seleccionar");
        question.setOperation(QuestionsSelectImages.SUBSTRACTION);
        question.setVisible(true);
    }//GEN-LAST:event_jMenuItemSubstractActionPerformed

    private void jMenuItemMultiplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemMultiplyActionPerformed
        QuestionsSelectImages question = new QuestionsSelectImages(this, true);
        question.setDescriptionDialog("Por favor seleccione las imagenes a mezclar");
        question.setTextButtonOk("Seleccionar");
        question.setOperation(QuestionsSelectImages.MULTIPLY);
        question.setVisible(true);
    }//GEN-LAST:event_jMenuItemMultiplyActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        this.jMenuItemNuevoActionPerformed(evt);
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbrirActionPerformed
        this.jMenuItemAbrirActionPerformed(evt);
    }//GEN-LAST:event_jButtonAbrirActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        this.jMenuItemGuardarActionPerformed(evt);
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jToggleButtonPintarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonPintarActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            this.jLabelAccionRaton.setText(bundle.getString("MOUSE"));
            ((VentanaInternaImagen) this.vi).getLienzo().setPaint();
        }
    }//GEN-LAST:event_jToggleButtonPintarActionPerformed

    private void jToggleButtonMoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonMoverActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            this.jLabelAccionRaton.setText(bundle.getString("MOVE"));
            ((VentanaInternaImagen) this.vi).getLienzo().setMove();
        }
    }//GEN-LAST:event_jToggleButtonMoverActionPerformed

    private void jToggleButtonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonBorrarActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            this.jLabelAccionRaton.setText(bundle.getString("DELETE"));
            ((VentanaInternaImagen) this.vi).getLienzo().setDelete();
        }
    }//GEN-LAST:event_jToggleButtonBorrarActionPerformed

    private void jToggleButtonPuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonPuntoActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            this.jLabelHerramienta.setText(bundle.getString("TOOL_POINT"));
            ((VentanaInternaImagen) this.vi).getLienzo().setTool("point"); //NOI18N
        }
    }//GEN-LAST:event_jToggleButtonPuntoActionPerformed

    private void jToggleButtonLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonLineaActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            this.jLabelHerramienta.setText(bundle.getString("TOOL_LINE"));
            ((VentanaInternaImagen) this.vi).getLienzo().setTool("line"); //NOI18N
        }
    }//GEN-LAST:event_jToggleButtonLineaActionPerformed

    private void jToggleButtonCubicCurveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonCubicCurveActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            this.jLabelHerramienta.setText(bundle.getString("TOOL_CUBIC"));
            ((VentanaInternaImagen) this.vi).getLienzo().setTool("cubic"); //NOI18N
        }
    }//GEN-LAST:event_jToggleButtonCubicCurveActionPerformed

    private void jToggleButtonQuadCurveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonQuadCurveActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            this.jLabelHerramienta.setText(bundle.getString("TOOL_QUAD"));
            ((VentanaInternaImagen) this.vi).getLienzo().setTool("quad"); //NOI18N
        }
    }//GEN-LAST:event_jToggleButtonQuadCurveActionPerformed

    private void jToggleButtonRectanguloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonRectanguloActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            this.jLabelHerramienta.setText(bundle.getString("TOOL_RECT"));
            ((VentanaInternaImagen) this.vi).getLienzo().setTool("rect"); //NOI18N
        }
    }//GEN-LAST:event_jToggleButtonRectanguloActionPerformed

    private void jToggleButtonRectanguloRoundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonRectanguloRoundActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            this.jLabelHerramienta.setText(bundle.getString("TOOL_RECT_ROUND"));
            ((VentanaInternaImagen) this.vi).getLienzo().setTool("rect_round"); //NOI18N
        }
    }//GEN-LAST:event_jToggleButtonRectanguloRoundActionPerformed

    private void jToggleButtonCirculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonCirculoActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            this.jLabelHerramienta.setText(bundle.getString("TOOL_CIRCLE"));
            ((VentanaInternaImagen) this.vi).getLienzo().setTool("oval"); //NOI18N
        }
    }//GEN-LAST:event_jToggleButtonCirculoActionPerformed

    private void jToggleButtonPolygonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonPolygonActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            this.jLabelHerramienta.setText(bundle.getString("TOOL_POLY"));
            ((VentanaInternaImagen) this.vi).getLienzo().setTool("polygon"); //NOI18N
        }
    }//GEN-LAST:event_jToggleButtonPolygonActionPerformed

    private void jComboBoxColorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxColorItemStateChanged
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            Color aux_color = (Color) this.jComboBoxColor.getSelectedItem();
            this.jLabelColor.setText(bundle.getString("COLOR") + "[R: " + aux_color.getRed() + " G: " + aux_color.getGreen() + " B: " + aux_color.getBlue() + "]");
            ((VentanaInternaImagen) this.vi).getLienzo().setColor((Color) this.jComboBoxColor.getSelectedItem());
        }
    }//GEN-LAST:event_jComboBoxColorItemStateChanged

    private void jComboBoxTipoLineaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTipoLineaItemStateChanged
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            myDiscontinuity aux_dis = myDiscontinuity.valueOf((String) this.jComboBoxTipoLinea.getSelectedItem());
            ((VentanaInternaImagen) this.vi).getLienzo().setDiscontinuity(aux_dis);
        }
    }//GEN-LAST:event_jComboBoxTipoLineaItemStateChanged

    private void jSpinnerGrosorLineaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerGrosorLineaStateChanged
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            ((VentanaInternaImagen) this.vi).getLienzo().setStroke((int) this.jSpinnerGrosorLinea.getValue() * 1f);
        }
    }//GEN-LAST:event_jSpinnerGrosorLineaStateChanged

    private void jSpinnerGrosorLineaMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jSpinnerGrosorLineaMouseWheelMoved
        if (evt.getPreciseWheelRotation() < 0) {
            if ((int) this.jSpinnerGrosorLinea.getValue() < VentanaPrincipal.MAX_VALUE_SPPINER) {
                this.jSpinnerGrosorLinea.setValue((int) this.jSpinnerGrosorLinea.getValue() + 7);
            }
        } else if ((int) this.jSpinnerGrosorLinea.getValue() > VentanaPrincipal.MIN_VALUE_SPPINER) {
            this.jSpinnerGrosorLinea.setValue((int) this.jSpinnerGrosorLinea.getValue() - 7);
        }
    }//GEN-LAST:event_jSpinnerGrosorLineaMouseWheelMoved

    private void jSpinnerTransparenciaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerTransparenciaStateChanged
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            ((VentanaInternaImagen) this.vi).getLienzo().setAlpha((int) this.jSpinnerTransparencia.getValue());
        }
    }//GEN-LAST:event_jSpinnerTransparenciaStateChanged

    private void jSpinnerTransparenciaMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jSpinnerTransparenciaMouseWheelMoved
        if (evt.getPreciseWheelRotation() < 0) {
            if ((int) this.jSpinnerTransparencia.getValue() < VentanaPrincipal.MAX_VALUE_SPPINER) {
                this.jSpinnerTransparencia.setValue((int) this.jSpinnerTransparencia.getValue() + 7);
            }
        } else if ((int) this.jSpinnerTransparencia.getValue() > VentanaPrincipal.MIN_VALUE_SPPINER) {
            this.jSpinnerTransparencia.setValue((int) this.jSpinnerTransparencia.getValue() - 7);
        }
    }//GEN-LAST:event_jSpinnerTransparenciaMouseWheelMoved

    private void jToggleButtonRellenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonRellenoActionPerformed
        QuestionsColors question = new QuestionsColors(this, true);
        question.setDescriptionDialog("Por favor elija las opciones que quiera");
        question.setTextButtonOk("Elegir Relleno");
        question.setVisible(true);
    }//GEN-LAST:event_jToggleButtonRellenoActionPerformed

    private void jToggleButtonAntiAliasingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonAntiAliasingActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && this.vi instanceof VentanaInternaImagen) {
            ((VentanaInternaImagen) this.vi).getLienzo().setFlatten(this.jToggleButtonAntiAliasing.isSelected());
        }
    }//GEN-LAST:event_jToggleButtonAntiAliasingActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        this.jMenuItemSalirActionPerformed(evt);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jSliderZoomStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderZoomStateChanged
        double value_slider = this.jSliderZoom.getValue();
        if (this.canApply(BufferedImage.TYPE_INT_ARGB) && value_slider != 100) {
            this.setEnabledUndo(true);
            value_slider = value_slider / 100.0F;
            AffineTransform at = AffineTransform.getScaleInstance(value_slider, value_slider); // resize
            try {
                AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                this.imgTemp = atop.filter(this.imgOrigen, null);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgTemp);
            } catch (Exception ex) {
                System.err.println("Error aplicando el zoom " + ex);
            }
        }
    }//GEN-LAST:event_jSliderZoomStateChanged

    private void jSliderZoomFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSliderZoomFocusLost
        if (this.jDesktopEscritorio.getSelectedFrame() instanceof VentanaInternaImagen) {
            if (this.jSliderZoom.getValue() != 100) {
                int n = JOptionPane.showConfirmDialog(this, "¿Quiere aplicar los cambios de zoom realizados?", "Realizar Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                // son 2 botones el si=0, el no=1
                if (n == 1 && this.imgOrigen != null) {
                    this.vi = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
                    ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgOrigen);
                }
                this.imgOrigen = null;
                this.jSliderZoom.setValue(100);
            }
        }
    }//GEN-LAST:event_jSliderZoomFocusLost

    private void jSliderRotateStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderRotateStateChanged
        double value_slider = this.jSliderRotate.getValue();
        if (this.canApply(BufferedImage.TYPE_INT_ARGB) && value_slider != 0) {
            this.setEnabledUndo(true);
            AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(this.jSliderRotate.getValue()), this.imgOrigen.getWidth() / 2, this.imgOrigen.getHeight() / 2); // rotate
            try {
                AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                // calculo cual es el valor maximo del tamaño de la imagen en este caso la Hipotenusa
                int value_max = (int) Math.hypot(this.imgOrigen.getWidth() + 0.0f, this.imgOrigen.getHeight() + 0.0f);
                // creo una imagen con ambos lados de la imagen del tamaño máximo
                this.imgTemp = new BufferedImage(value_max, value_max, BufferedImage.TYPE_INT_ARGB);
                // aqui es donde tengo que poner la imagen origen en la imagen destino
                this.imgTemp.createGraphics().drawImage(this.imgOrigen, atop, (value_max - this.imgOrigen.getWidth()) / 2, (value_max - this.imgOrigen.getHeight()) / 2);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgTemp);
            } catch (Exception ex) {
                System.err.println("Error rotando la imagen " + ex);
            }
        }
    }//GEN-LAST:event_jSliderRotateStateChanged

    private void jSliderRotateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSliderRotateFocusLost
        if (this.jDesktopEscritorio.getSelectedFrame() instanceof VentanaInternaImagen) {
            if (this.jSliderRotate.getValue() != 0) {
                int n = JOptionPane.showConfirmDialog(this, "¿Quiere aplicar los cambios de rotación realizados?", "Realizar Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                // son 2 botones el si=0, el no=1
                if (n == 1 && this.imgOrigen != null) {
                    this.vi = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
                    ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgOrigen);
                }
                this.imgOrigen = null;
                this.jSliderRotate.setValue(0);
            }
        }
    }//GEN-LAST:event_jSliderRotateFocusLost

    private void jSliderBrigthnessStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderBrigthnessStateChanged
        /*
        La función matematica que se aplica con esta operacion de RescaleOP
        es la siguiente:
            g(x,y) = alfa * f(x,y) + beta
        donde x e y son las coordenadas del pixel al que queremos aplicar la
        función, asi pues si multiplicamos por 1 (alfa=1.0F) y le sumamos 0
        (beta=0.0F) la función g(x,y) será igual que f(x,y).

        Como particularidad decir que las imagenes con transparencia tiene
        4 canales R, G, B y A don de A es el alpha de la transparencia.

        Para estos casos tenemos que pasar un vector con los valores para cada
        uno de los canales R, G, B, y A (float[] alfas = {1.0F,1.0F,1.0F,1.0F};)

        El comprobar el 0 es por que cuando aplicamos los cambios el slider
        cambia de valor a 0 y se vuelve a llama
         */
        int value_slider = this.jSliderBrigthness.getValue();
        if (this.canApply(BufferedImage.TYPE_INT_ARGB) && value_slider != 0) {
            if (this.imgOrigen == null) {
                this.imgOrigen = ((VentanaInternaImagen) this.vi).getLienzo().getImage();
            }
            this.imgTemp = Utils.convertImageToType(this.imgOrigen, BufferedImage.TYPE_INT_ARGB);
            this.setEnabledUndo(true);
            try {
                // multiplicamos por 1 R,G y B se quedan igual
                float[] alfas = {1, 1, 1, 1};
                // sumamos 100 a R, G y B le subimos el brillo el ultimo no lo tocamos
                float[] betas = {value_slider, value_slider, value_slider, 0.0F,};
                // el ultimo componente de los vectores es la transparencia
                // que la dejamos igual en todos lados.
                RescaleOp rop = new RescaleOp(alfas, betas, null);
                this.imgTemp = rop.filter(this.imgTemp, null);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgTemp);
            } catch (IllegalArgumentException ex) {
                System.err.println("Error al intentar modificar el brillo " + ex);
            }
        }
    }//GEN-LAST:event_jSliderBrigthnessStateChanged

    private void jSliderBrigthnessFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSliderBrigthnessFocusLost
        if (this.jDesktopEscritorio.getSelectedFrame() instanceof VentanaInternaImagen) {
            if (this.jSliderBrigthness.getValue() != 0) {
                int n = JOptionPane.showConfirmDialog(this, "¿Quiere aplicar los cambios de brillo realizados?", "Realizar Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                // son 2 botones el si=0, el no=1
                if (n == 1 && this.imgOrigen != null) {
                    this.vi = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
                    ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgOrigen);
                }
                this.imgOrigen = null;
                this.jSliderBrigthness.setValue(0);
            }
        }
    }//GEN-LAST:event_jSliderBrigthnessFocusLost

    private void jSliderContrastStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderContrastStateChanged
        /*
            El comprobar el 0 es por que cuando aplicamos los cambios el slider
            cambia de valor a 0 y se vuelve a llamar
            Solo guardo la imagen original 1 sola vez (this.imgOrigen == null)
         */
        int value_slider = this.jSliderContrast.getValue();
        if (this.canApply(BufferedImage.TYPE_INT_ARGB) && value_slider != 0) {
            this.setEnabledUndo(true);
            try {
                // finción S
                LookupTable lt = LookupTableProducer.sFuction(value_slider * 1.0f, 4f);
                // función potencia
                // LookupTable lt = LookupTableProducer.powerFuction(value_slider / 100.0f);
                // función raiz
                // LookupTable lt = LookupTableProducer.rootFuction(value_slider / 100.0f);
                // función logaritmica
                // LookupTable lt = LookupTableProducer.logarithmFuction();
                // función gamma
                // LookupTable lt = LookupTableProducer.gammaCorrection(value_slider, 2);
                LookupOp lop = new LookupOp(lt, null);
                this.imgTemp = lop.filter(this.imgOrigen, null);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgTemp);
            } catch (IllegalArgumentException ex) {
                System.err.println("Error al intentar modificar el contraste " + ex);
            }
        }
    }//GEN-LAST:event_jSliderContrastStateChanged

    private void jSliderContrastFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSliderContrastFocusLost
        if (this.jDesktopEscritorio.getSelectedFrame() instanceof VentanaInternaImagen) {
            if (this.jSliderContrast.getValue() != 0) {
                int n = JOptionPane.showConfirmDialog(this, "¿Quiere aplicar los cambios de contraste realizados?", "Realizar Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                // son 2 botones el si=0, el no=1
                if (n == 1 && this.imgOrigen != null) {
                    this.vi = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
                    ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgOrigen);
                }
                this.imgOrigen = null;
                this.jSliderContrast.setValue(0);
            }
        }
    }//GEN-LAST:event_jSliderContrastFocusLost

    private void jSliderUmbralStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderUmbralStateChanged
        int value_slider = this.jSliderUmbral.getValue();
        if (value_slider != 50 && this.canApply(-1)) {
            this.setEnabledUndo(true);
            try {
                UmbralizationOP uop;
                if (((VentanaInternaImagen) this.vi).getLienzo().getFillColor() == null) {
                    uop = new UmbralizationOP(value_slider);
                } else {
                    uop = new UmbralizationOP(((VentanaInternaImagen) this.vi).getLienzo().getFillColor(), value_slider);
                }
                this.imgTemp = uop.filter(this.imgOrigen, null);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgTemp);
            } catch (IllegalArgumentException ex) {
                System.err.println("Error al intentar aplicar la umbralización " + ex);
            }
        }
    }//GEN-LAST:event_jSliderUmbralStateChanged

    private void jSliderUmbralFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSliderUmbralFocusLost
        if (this.jDesktopEscritorio.getSelectedFrame() instanceof VentanaInternaImagen) {
            if (this.jSliderUmbral.getValue() != 50) {
                int n = JOptionPane.showConfirmDialog(this, "¿Quiere aplicar los cambios de Umbralización realizados?", "Realizar Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                // son 2 botones el si=0, el no=1
                if (n == 1 && this.imgOrigen != null) {
                    this.vi = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
                    ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgOrigen);
                }
                this.imgOrigen = null;
                this.jSliderUmbral.setValue(50);
            }
        }
    }//GEN-LAST:event_jSliderUmbralFocusLost

    private void jButtonColorUmbralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonColorUmbralActionPerformed
        if (this.jDesktopEscritorio.getSelectedFrame() instanceof VentanaInternaImagen) {
            this.vi = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
            Color colordlg = JColorChooser.showDialog(this, "Elige color:", Color.BLACK);
            ((VentanaInternaImagen) this.vi).getLienzo().setFillColor(colordlg);
            ((javax.swing.JButton) evt.getSource()).setBackground(colordlg);
        }
    }//GEN-LAST:event_jButtonColorUmbralActionPerformed

    /**
     * Con este método aplicamos un filtro de imagen concreto a la imagen que se
     * pasa por parámetros, el k es la matriz de datos que utiliza ConvolveOP
     * para aplicar el filtro en particular.
     *
     * @param img [in] Datos de la imagen que queremos aplicar el filtro.
     * @param k [in] Matriz de float con los parámetros del filtro.
     */
    private void applyFilter(Kernel k) {
        if (this.canApply(BufferedImage.TYPE_INT_RGB)) {
            this.imgOrigen = ((VentanaInternaImagen) this.vi).getLienzo().getImage();
            try {
                /*
                Activamos la opción EDGE_NO_OP, de forma que los pixeles del
                borde (en los que no se puede aplicar la convolución) se
                copiarán de la original. Si se optase por la opción
                EDGE_ZERO_FILL, el borde se pondría a cero (ésta es la
                opción por defecto).
                 */
                ConvolveOp cop = new ConvolveOp(k, ConvolveOp.EDGE_NO_OP, null);
                this.imgTemp = cop.filter(this.imgOrigen, null);
                ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgTemp);
                this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (IllegalArgumentException e) {
                System.err.println("Error al aplicar el filtro: " + e.getLocalizedMessage());
            }
        }
    }

    private void jComboBoxFiltrosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxFiltrosItemStateChanged
        /*
        este if es por que al cambiar de estado se mandan dos casos
        el que estaba seleccionado y el que se ha seleccionado
        por eso se discrimina el caso del que estaba seleccionado.
         */
        if (evt.getStateChange() == ItemEvent.SELECTED && this.jComboBoxFiltros.getSelectedIndex() != 0) {
            switch ((String) evt.getItem()) {
                case "Blur Medio":
                    this.jMenuItemBlurMedioActionPerformed(null);
                    break;
                case "Blur Binomial":
                    this.jMenuItemBlurBinomialActionPerformed(null);
                    break;
                case "Enfoque":
                    this.jMenuItemFocusActionPerformed(null);
                    break;
                case "Relieve":
                    this.jMenuItemReliefActionPerformed(null);
                    break;
                case "Realzar Bordes":
                    this.jMenuItemBorderActionPerformed(null);
                    break;
                case "Emboss":
                    this.jMenuItemEmbossActionPerformed(null);
                    break;
                case "Sharpens":
                    this.jMenuItemSharpensActionPerformed(null);
                    break;
                case "Frontera Laplaciano":
                    this.jMenuItemFrontierActionPerformed(null);
                    break;
                case "Suavisado Gaussiano":
                    this.jMenuItemSuavizadoActionPerformed(null);
                    break;
                case "Negativo":
                    this.jMenuItemNegativeActionPerformed(null);
                    break;
                case "Sepia":
                    this.jMenuItemSepiaActionPerformed(null);
                    break;
                case "Sobel":
                    this.jMenuItemSolveActionPerformed(null);
                    break;
                case "Personalizado":
                    this.jMenuItemEfectoActionPerformed(null);
                    break;
            }
        }
    }//GEN-LAST:event_jComboBoxFiltrosItemStateChanged

    private void jComboBoxFiltrosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxFiltrosFocusLost
        if (this.jDesktopEscritorio.getSelectedFrame() instanceof VentanaInternaImagen) {
            if (this.jComboBoxFiltros.getSelectedIndex() != 0) {
                int n = JOptionPane.showConfirmDialog(this, "¿Queiere aplicar el filtro que acaba de elegir?", "Realizar Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                // son 2 botones el si=0, el no=1
                if (n == 1 && this.imgOrigen != null) {
                    this.vi = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
                    ((VentanaInternaImagen) this.vi).getLienzo().setImage(this.imgOrigen);
                }
                this.imgOrigen = null;
                this.jComboBoxFiltros.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_jComboBoxFiltrosFocusLost

    private void jToggleButtonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonPlayActionPerformed
        /*
        @TODO tienes que saber que es lo que se va a reproducir mirando la
        lista de reproducción, segun sea un audio o un video tendras que
        abrir una ventana o no.
        Si es un audio solo reproduces y cambias los botones
        Si es un video abres ventana,reproduces, y controlas el tiempo de
        reproducción en las barras de progreso.
         */
        int id = this.jListSounds.getSelectedIndex();
        // cuidado con el orden del if
        if (id >= 0 && this.multimedia_actual instanceof ClipPlayer && ((ClipPlayer) this.multimedia_actual).isWorking()) {
            /* @TODO Arreglar esto para que acepte mas extensiones */
            ((ClipPlayer) this.multimedia_actual).pause();
            VentanaPrincipal.this.jToggleButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/reproducir.png"))); // NOI18N
        } else if (id >= 0 && this.multimedia_actual instanceof ClipPlayer) {
            /* @TODO Arreglar esto para que acepte mas extensiones */
            ((ClipPlayer) this.multimedia_actual).play();
            VentanaPrincipal.this.jToggleButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/pause.png"))); // NOI18N
        } else if (id >= 0 && this.multimedia_actual instanceof AudioMediaPlayerComponent && ((AudioMediaPlayerComponent) this.multimedia_actual).getMediaPlayer().isPlaying()) {
            ((AudioMediaPlayerComponent) this.multimedia_actual).getMediaPlayer().pause();
            VentanaPrincipal.this.jToggleButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/reproducir.png"))); // NOI18N
        } else if (id >= 0 && this.multimedia_actual instanceof AudioMediaPlayerComponent) {
            ((AudioMediaPlayerComponent) this.multimedia_actual).getMediaPlayer().play();
            VentanaPrincipal.this.jToggleButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/pause.png"))); // NOI18N
        } else {
            JOptionPane.showConfirmDialog(this, "Por favor seleccione lo que\nquiere reproducir o hagá\ndoble click encima", "Seleccionar Multimedia", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
            this.jToggleButtonStop.setSelected(true);
        }
    }//GEN-LAST:event_jToggleButtonPlayActionPerformed

    private void jToggleButtonStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonStopActionPerformed
        if (this.multimedia_actual != null) {
            if (this.multimedia_actual instanceof ClipPlayer) {
                ((ClipPlayer) this.multimedia_actual).stop();
            }
            if (this.multimedia_actual instanceof AudioMediaPlayerComponent) {
                ((AudioMediaPlayerComponent) this.multimedia_actual).getMediaPlayer().stop();
                VentanaPrincipal.this.jToggleButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/reproducir.png"))); // NOI18N
            }
            // como he terminado con el fichero lo pongo a null
            this.multimedia_actual = null;
            this.jListSounds.clearSelection();
        }
    }//GEN-LAST:event_jToggleButtonStopActionPerformed

    private void jToggleButtonRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonRecordActionPerformed
        /* numeros aleatorios entre 0 y 10 para el nombre del fichero */
        int max = 10;
        int min = 0;
        Random random = new Random();
        int num = random.nextInt(max - min + 1) + min;
        /*
        Como no quiero que se pueda hacer otra cosa con el sonido o el video
        mientras estoy grabando, pues paro cualquier reproducción que haya en
        progreso.
         */
        if (this.multimedia_actual != null && this.optionRecording == null) {
            this.jRadioButtonRepeatNone.setSelected(true);
            ((ClipPlayer) this.multimedia_actual).stop();
        }
        if (this.optionRecording != null) {
            ((SoundPlayerRecorder) this.multimedia_actual).stop();
            JFileChooser dlg = new JFileChooser();
            dlg.addChoosableFileFilter(this.filters[this.WAV_FILE]);
            dlg.addChoosableFileFilter(this.filters[this.AU_FILE]);
            dlg.addChoosableFileFilter(this.filters[this.AIFF_FILE]);
            dlg.setAcceptAllFileFilterUsed(false);
            if (dlg.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    File destino = new File(dlg.getSelectedFile().getAbsolutePath());
                    Files.move(((Multimedia) this.multimedia_actual).getSoundFile().toPath(), destino.toPath(), REPLACE_EXISTING);
                    this.multimedia_files.add(new Item(destino.getFriendlyName(), destino.getExt(), destino.getPath(), this.multimedia_files.size()));
                    this.list_model.addElement(destino.getFriendlyName());
                } catch (IOException ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    System.err.println("Error al guardar el fichero: " + ex);
                    ((Multimedia) this.multimedia_actual).getSoundFile().delete();
                }
            } else {
                ((Multimedia) this.multimedia_actual).getSoundFile().delete();
            }
            // como he terminado con el fichero lo pongo a null
            this.multimedia_actual = null;
        } else {
            QuestionsRecordOptions question = new QuestionsRecordOptions(this, true);
            question.setDescriptionDialog("Elija una opción: ");
            question.setTextButtonOk("Grabar");
            question.setVisible(true);
            if (this.optionRecording != null && this.optionRecording.equals("sound")) {
                try {
                    this.multimedia_actual = new SoundPlayerRecorder(java.io.File.createTempFile("tempfile", String.format("%02d", num)));
                    ((Multimedia) this.multimedia_actual).setListener(this.mySoundListener);
                    ((SoundPlayerRecorder) this.multimedia_actual).record();
                    //System.err.println(this.multimedia_actual.getSoundFile().getAbsolutePath());
                } catch (IOException ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    System.err.println("Error al crear el fichero temporal: " + ex);
                }
            } else if (this.optionRecording != null && this.optionRecording.equals("video")) {
                // @TODO poner todo el codigo que haga falta para grabación de video
            }
        }
    }//GEN-LAST:event_jToggleButtonRecordActionPerformed

    private void jToggleButtonShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonShowActionPerformed
        this.jPanelOeste.setVisible(this.jToggleButtonShow.isSelected());
        this.jCheckBoxMenuItemPanelSonido.setSelected(this.jToggleButtonShow.isSelected());
    }//GEN-LAST:event_jToggleButtonShowActionPerformed

    private void jButtonWebcamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonWebcamActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        // creo la ventana de WebCam
        VentanaInternaWebcam vi_webcam = new VentanaInternaWebcam();
        vi_webcam.setListener(this.myVIListener);
        // la posiciono
        if (this.vi != null) {
            vi_webcam.setLocation(this.vi.getLocation().x + 15, this.vi.getLocation().y + 15);
        } else {
            vi_webcam.setLocation(15, 15);
        }
        // la añado al escritorio
        this.getEscritorio().add(vi_webcam);
        vi_webcam.setVisible(true);
    }//GEN-LAST:event_jButtonWebcamActionPerformed

    private void jButtonTakePhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTakePhotoActionPerformed
        this.vi = this.jDesktopEscritorio.getSelectedFrame();
        // compruebo si es una ventana de webcam
        // la ventana imagen no la tengo en cuenta
        BufferedImage aux = null;
        if (this.vi instanceof VentanaInternaJMFPlayer) {
            // capturo la foto
            aux = ((VentanaInternaJMFPlayer) this.vi).takePhoto();
        } else if (this.vi instanceof VentanaInternaVLCPlayer) {
            // capturo la foto
            aux = ((VentanaInternaVLCPlayer) this.vi).takePhoto();
        } else if (this.vi instanceof VentanaInternaWebcam) {
            // capturo la foto
            aux = ((VentanaInternaWebcam) this.vi).takePhoto();
        }
        // creo una ventana nueva para la foto
        this.vi = new VentanaInternaImagen(this, new Dimension(aux.getWidth(), aux.getHeight()), BufferedImage.TYPE_INT_ARGB);
        ((VentanaInternaImagen) this.vi).setListener(this.myVIListener);
        // pongo la imagen en la ventana
        ((VentanaInternaImagen) this.vi).getLienzo().setImage(aux);
        // sumo una ventana al contador
        VentanaPrincipal.COUNT_VI++;
        // Le pongo el título
        this.vi.setTitle("(Sin Título " + VentanaPrincipal.COUNT_VI + ")");
        // la pongo en el escritorio
        this.jDesktopEscritorio.add(this.vi);
        // la muestro
        this.vi.setVisible(true);
    }//GEN-LAST:event_jButtonTakePhotoActionPerformed

    private void jListSoundsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListSoundsMousePressed
        if (VentanaPrincipal.DEBUG) {
            System.out.println("pulsamos en la lista");
        }
        if (this.multimedia_actual != null) {
            if (this.multimedia_actual instanceof ClipPlayer && ((ClipPlayer) this.multimedia_actual).isWorking()) {
                ((ClipPlayer) this.multimedia_actual).stop();
            }
            if (this.multimedia_actual instanceof ExtendedVideoPlayer && ((ExtendedVideoPlayer) this.multimedia_actual).isWorking()) {
                ((ExtendedVideoPlayer) this.multimedia_actual).stop();
            }
            if (this.multimedia_actual instanceof AudioMediaPlayerComponent && ((AudioMediaPlayerComponent) this.multimedia_actual).getMediaPlayer().isPlaying()) {
                ((AudioMediaPlayerComponent) this.multimedia_actual).getMediaPlayer().release();
            }
        }
        if (this.jListSounds.getSelectedIndex() != -1) {
            Item item = this.multimedia_files.get(this.jListSounds.getSelectedIndex());
            if (item.ext.contains("wav") || item.ext.contains("au") || item.ext.contains("aiff")) {
                this.multimedia_actual = new ClipPlayer(this.multimedia_files.get(this.jListSounds.getSelectedIndex()).path);
                ((Multimedia) this.multimedia_actual).setListener(this.mySoundListener);
                /* según lo elijo lo pongo a reproducir*/
                ((ClipPlayer) this.multimedia_actual).play();
            } else if (item.ext.contains("mp3")) {
                this.multimedia_actual = new AudioMediaPlayerComponent();
                ((AudioMediaPlayerComponent) this.multimedia_actual).getMediaPlayer().addMediaPlayerEventListener(this.mySoundListener);
                ((AudioMediaPlayerComponent) this.multimedia_actual).getMediaPlayer().playMedia(item.path);
            } else {
                if (this.jCheckBoxMenuItemSelectLibrary.isSelected()) {
                    this.vi = new VentanaInternaJMFPlayer(this, new Dimension(640, 480), item);
                    ((VentanaInternaJMFPlayer) this.vi).setListener(this.myVIListener);
                } else {
                    this.vi = new VentanaInternaVLCPlayer(this, new Dimension(640, 480), item);
                    ((VentanaInternaVLCPlayer) this.vi).setListener(this.myVIListener);
                    ((VentanaInternaVLCPlayer) this.vi).setMultimediaFile(item.path);
                }
                this.jDesktopEscritorio.add(this.vi);
                this.vi.setVisible(true);
            }
        }
    }//GEN-LAST:event_jListSoundsMousePressed

    private void jRadioButtonMenuItemESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItemESActionPerformed
        VentanaPrincipal.bundle = ResourceBundle.getBundle("interna/Bundle");
        this.changeLocaleUI();
    }//GEN-LAST:event_jRadioButtonMenuItemESActionPerformed

    private void jRadioButtonMenuItemENActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItemENActionPerformed
        VentanaPrincipal.bundle = ResourceBundle.getBundle("interna/Bundle_en_US");
        this.changeLocaleUI();
    }//GEN-LAST:event_jRadioButtonMenuItemENActionPerformed

    private void jRadioButtonMenuItemDEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItemDEActionPerformed
        VentanaPrincipal.bundle = ResourceBundle.getBundle("interna/Bundle_de_DE");
        this.changeLocaleUI();
    }//GEN-LAST:event_jRadioButtonMenuItemDEActionPerformed

    private void jMenuItemAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAyudaActionPerformed
        AcercaDe myAcerca = new AcercaDe(this, true);
        myAcerca.setVisible(true);
    }//GEN-LAST:event_jMenuItemAyudaActionPerformed

    // @TODO Para realizar el preview en el lienzo 2D de la ventana principal
    // lo que tenemos que hacer es utilizar la misma imagen que la del lienzo
    // original pero utilizando otro constructor que podemos pasarle el tamaño
    // del area en donde queremos pintar la imagen, sin cambiar el tamaño
    // real de la imagen y sin usar una interpolación bilineal.

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupIdioma;
    private javax.swing.ButtonGroup buttonGroupOpciones;
    private javax.swing.ButtonGroup buttonGroupSound;
    private javax.swing.ButtonGroup buttonGroupSoundOptions;
    private javax.swing.ButtonGroup buttonGroupTools;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler8;
    private javax.swing.JButton jButtonAbrir;
    private javax.swing.JButton jButtonColorUmbral;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonTakePhoto;
    private javax.swing.JButton jButtonWebcam;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemPanelSonido;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemPantallaCompleta;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemSelectLibrary;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVerBarraEstado;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVerBarraHerramientas;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVerBarraSonido;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVerBarraUtilidades;
    private javax.swing.JCheckBox jCheckBoxOpenPanel;
    private javax.swing.JComboBox<String> jComboBoxColor;
    private javax.swing.JComboBox<String> jComboBoxFiltros;
    private javax.swing.JComboBox<String> jComboBoxTipoLinea;
    private javax.swing.JDesktopPane jDesktopEscritorio;
    private javax.swing.JLabel jLabelAccionRaton;
    private javax.swing.JLabel jLabelColor;
    private javax.swing.JLabel jLabelElapsedTime;
    private javax.swing.JLabel jLabelElapsedTimeRecord;
    private javax.swing.JLabel jLabelHerramienta;
    private javax.swing.JLabel jLabelImageColor;
    private javax.swing.JLabel jLabelImageDownBrigthness;
    private javax.swing.JLabel jLabelImageDownContrast;
    private javax.swing.JLabel jLabelImageDownUmbral;
    private javax.swing.JLabel jLabelImageDownZoom;
    private javax.swing.JLabel jLabelImageGrosor;
    private javax.swing.JLabel jLabelImageRotateLeft;
    private javax.swing.JLabel jLabelImageRotateRight;
    private javax.swing.JLabel jLabelImageTipo;
    private javax.swing.JLabel jLabelImageUpBrigthness;
    private javax.swing.JLabel jLabelImageUpContrast;
    private javax.swing.JLabel jLabelImageUpUmbral;
    private javax.swing.JLabel jLabelImageUpZoom;
    private javax.swing.JLabel jLabelMouseColorPos;
    private javax.swing.JLabel jLabelMousePos;
    private javax.swing.JLabel jLabelMouseViewColorPos;
    private javax.swing.JLabel jLabelTotalTime;
    private javax.swing.JLabel jLabelTransparente;
    private javax.swing.JList<String> jListSounds;
    private javax.swing.JMenuItem jMIBrightnessContrast;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenu jMenuAyuda;
    private javax.swing.JMenuBar jMenuBarra;
    private javax.swing.JMenu jMenuEditar;
    private javax.swing.JMenu jMenuFilters;
    private javax.swing.JMenu jMenuIdioma;
    private javax.swing.JMenu jMenuImagen;
    private javax.swing.JMenuItem jMenuItemAbrir;
    private javax.swing.JMenuItem jMenuItemAdd;
    private javax.swing.JMenuItem jMenuItemAffineTransformOp;
    private javax.swing.JMenuItem jMenuItemAyuda;
    private javax.swing.JMenuItem jMenuItemBandCombineOp;
    private javax.swing.JMenuItem jMenuItemBlurBinomial;
    private javax.swing.JMenuItem jMenuItemBlurMedio;
    private javax.swing.JMenuItem jMenuItemBorder;
    private javax.swing.JMenuItem jMenuItemClone;
    private javax.swing.JMenuItem jMenuItemColorConvertOp;
    private javax.swing.JMenuItem jMenuItemConvertBlackWhite;
    private javax.swing.JMenuItem jMenuItemConvolveOP;
    private javax.swing.JMenuItem jMenuItemCopiar;
    private javax.swing.JMenuItem jMenuItemCortar;
    private javax.swing.JMenuItem jMenuItemCustom;
    private javax.swing.JMenuItem jMenuItemDeshacer;
    private javax.swing.JMenuItem jMenuItemEfecto;
    private javax.swing.JMenuItem jMenuItemEmboss;
    private javax.swing.JMenuItem jMenuItemEqualization;
    private javax.swing.JMenuItem jMenuItemFocus;
    private javax.swing.JMenuItem jMenuItemFrontier;
    private javax.swing.JMenuItem jMenuItemGuardar;
    private javax.swing.JMenuItem jMenuItemLookupOp;
    private javax.swing.JMenuItem jMenuItemMultiply;
    private javax.swing.JMenuItem jMenuItemNegative;
    private javax.swing.JMenuItem jMenuItemNuevo;
    private javax.swing.JMenuItem jMenuItemPegar;
    private javax.swing.JMenuItem jMenuItemRehacer;
    private javax.swing.JMenuItem jMenuItemRelief;
    private javax.swing.JMenuItem jMenuItemRescaleOP;
    private javax.swing.JMenuItem jMenuItemResizeImage;
    private javax.swing.JMenuItem jMenuItemRotate180;
    private javax.swing.JMenuItem jMenuItemRotateLeft;
    private javax.swing.JMenuItem jMenuItemRotateRight;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenuItem jMenuItemScaleImage;
    private javax.swing.JMenuItem jMenuItemSenoFunction;
    private javax.swing.JMenuItem jMenuItemSepia;
    private javax.swing.JMenuItem jMenuItemSharpens;
    private javax.swing.JMenuItem jMenuItemSolve;
    private javax.swing.JMenuItem jMenuItemSuavizado;
    private javax.swing.JMenuItem jMenuItemSubstract;
    private javax.swing.JMenuItem jMenuItemTintar;
    private javax.swing.JMenuItem jMenuItemVLCPlayer;
    private javax.swing.JMenu jMenuOpciones;
    private javax.swing.JMenu jMenuPruebas;
    private javax.swing.JMenu jMenuRotate;
    private javax.swing.JMenu jMenuVer;
    private javax.swing.JPanel jPBrilloContraste;
    private javax.swing.JPanel jPBrilloSlider;
    private javax.swing.JPanel jPanelBrilloContraste;
    private javax.swing.JPanel jPanelContentBars;
    private javax.swing.JPanel jPanelContentIcons;
    private javax.swing.JPanel jPanelHistogram;
    private javax.swing.JPanel jPanelNorte;
    private javax.swing.JPanel jPanelOeste;
    private javax.swing.JPanel jPanelOptions;
    private javax.swing.JPanel jPanelPrevio;
    private javax.swing.JPanel jPanelProgressBar;
    private javax.swing.JPanel jPanelRotar;
    private javax.swing.JPanel jPanelSplitDerecho;
    private javax.swing.JPanel jPanelStatusBar;
    private javax.swing.JPanel jPanelSur;
    private javax.swing.JPanel jPanelUmbral;
    private javax.swing.JPanel jPanelUmbralSlider;
    private javax.swing.JPanel jPanelZoom;
    private javax.swing.JProgressBar jProgressBarMultimedia;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemDE;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemEN;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemES;
    private javax.swing.JRadioButton jRadioButtonRepeatAll;
    private javax.swing.JRadioButton jRadioButtonRepeatNone;
    private javax.swing.JRadioButton jRadioButtonRepeatSound;
    private javax.swing.JScrollPane jScrollPanelSounds;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JToolBar.Separator jSeparator14;
    private javax.swing.JToolBar.Separator jSeparator15;
    private javax.swing.JToolBar.Separator jSeparator16;
    private javax.swing.JPopupMenu.Separator jSeparator17;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JSlider jSliderBrigthness;
    private javax.swing.JSlider jSliderContrast;
    private javax.swing.JSlider jSliderRotate;
    private javax.swing.JSlider jSliderUmbral;
    private javax.swing.JSlider jSliderZoom;
    private javax.swing.JSpinner jSpinnerGrosorLinea;
    private javax.swing.JSpinner jSpinnerTransparencia;
    private javax.swing.JSplitPane jSplitPaneCenter;
    private javax.swing.JToggleButton jToggleButtonAntiAliasing;
    private javax.swing.JToggleButton jToggleButtonBorrar;
    private javax.swing.JToggleButton jToggleButtonCirculo;
    private javax.swing.JToggleButton jToggleButtonCubicCurve;
    private javax.swing.JToggleButton jToggleButtonLinea;
    private javax.swing.JToggleButton jToggleButtonMover;
    private javax.swing.JToggleButton jToggleButtonPintar;
    private javax.swing.JToggleButton jToggleButtonPlay;
    private javax.swing.JToggleButton jToggleButtonPolygon;
    private javax.swing.JToggleButton jToggleButtonPunto;
    private javax.swing.JToggleButton jToggleButtonQuadCurve;
    private javax.swing.JToggleButton jToggleButtonRecord;
    private javax.swing.JToggleButton jToggleButtonRectangulo;
    private javax.swing.JToggleButton jToggleButtonRectanguloRound;
    private javax.swing.JToggleButton jToggleButtonRelleno;
    private javax.swing.JToggleButton jToggleButtonShow;
    private javax.swing.JToggleButton jToggleButtonStop;
    private javax.swing.JToolBar jToolBarSound;
    private javax.swing.JToolBar jToolBarTools;
    // End of variables declaration//GEN-END:variables
}
