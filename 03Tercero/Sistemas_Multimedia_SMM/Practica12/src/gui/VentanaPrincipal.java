package gui;

import gui.image.VentanaInternaImagen;
import gui.componets.ColorComboBoxEditor;
import gui.componets.ColorComboBoxModel;
import gui.componets.ColorComboBoxRender;
import gui.componets.Item;
import gui.modal.QuestionsRecordOptions;
import gui.modal.QuestionsRescaleImage;
import gui.modal.QuestionsResizeImage;
import gui.modal.QuestionsSelectImages;
import gui.modal.QuestionsSizeImage;
import gui.others.AcercaDe;
import gui.video.VentanaInternaVideo;
import gui.video.VentanaInternaWebcam;
import java.awt.Color;
import java.awt.Dimension;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import sm.cdlt.graphics.myRectangle;
import sm.cdlt.graphics.myRectangleShape;
import sm.cdlt.images.LookupTableProducer;
import sm.cdlt.util.Utils;
import sm.cdlt.images.myEfectOP;
import sm.cdlt.images.SepiaOP;
import sm.cdlt.multimedia.ClipPlayer;
import sm.cdlt.multimedia.Multimedia;
import sm.cdlt.multimedia.SoundPlayerRecorder;
import sm.cdlt.util.File;
import sm.cdlt.util.FileNameExtensionFilter;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de al Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de al Torre</a>
 * created on 10-abr-2016
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Valor mínimo que pueden tomar los campos sppiners.
     */
    public static final int MIN_VALUE_SPPINER = 4;
    /**
     * Valor mínimo que pueden tomar los campos sppiners.
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
     * Sonido actual que esta listo para reproducirse
     */
    private Multimedia multimedia_actual;
    /**
     * Contenedor de ficheros multimedia
     */
    private ArrayList<Item> multimedia_files;
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
    private final FileNameExtensionFilter[] filters = new FileNameExtensionFilter[12];
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
    private final int MPG_FILE = 10;
    private final int MP4_FILE = 11;
    /**
     * Variable que muestra la ventana activa.
     */
    private JInternalFrame vi;
    /**
     * Variable que almacena una internal frame cuando es de imagen
     */
    private VentanaInternaImagen vi_image;
    /**
     * Variable que almacena una internal frame cuando es de vídeo
     */
    private VentanaInternaVideo vi_video;
    /**
     * Variable que almacena una internal frame cuando es de webcam
     */
    private VentanaInternaWebcam vi_webcam;
    /**
     * Variable que se encarga de almacenar una versión original de la imagen
     * que estamos modificando para poder realizar un deshacer, y también la
     * temporal donde hacemos las modificaciones.
     */
    private BufferedImage imgOrigen, imgTemp;
    /**
     * Controla si realizamos un doble click.
     */
    private boolean DoubleClick = false;
    /**
     * Controla el tiempo para considerar el doble click.
     */
    private final long TimeDoubleClick = 500;

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
        Color aux_color = this.vi_image.getLienzo().getColor();
        this.jLabelColor.setText(bundle.getString("COLOR")
                + "[R: " + aux_color.getRed()
                + " G: " + aux_color.getGreen()
                + " B: " + aux_color.getBlue()
                + "]");
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
        this.jSpinnerGrosor.setToolTipText(bundle.getString("STROKE_TT"));
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
        jToggleButtonRectangulo = new javax.swing.JToggleButton();
        jToggleButtonCirculo = new javax.swing.JToggleButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jLabelImageColor = new javax.swing.JLabel();
        jComboBoxColor = new javax.swing.JComboBox<>();
        jSeparator16 = new javax.swing.JToolBar.Separator();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jLabelImageGrosor = new javax.swing.JLabel();
        jSpinnerGrosor = new javax.swing.JSpinner();
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
        jButtonTakePhoto = new javax.swing.JButton();
        jSeparator15 = new javax.swing.JToolBar.Separator();
        jToggleButtonRecord = new javax.swing.JToggleButton();
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(6, 0), new java.awt.Dimension(6, 0), new java.awt.Dimension(6, 32767));
        jLabelElapsedTime1 = new javax.swing.JLabel();
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
        jPanelPrevio = new javax.swing.JPanel();
        jPanelZoom = new javax.swing.JPanel();
        jLabelImageUpZoom = new javax.swing.JLabel();
        jSliderZoom = new javax.swing.JSlider();
        jLabelImageDownZoom = new javax.swing.JLabel();
        jPanelRotar = new javax.swing.JPanel();
        jLabelImageRotateLeft = new javax.swing.JLabel();
        jSliderRotate = new javax.swing.JSlider();
        jLabelImageRotateRight = new javax.swing.JLabel();
        Lienzo2DImagePreview = new sm.cdlt.ui.Lienzo2DImage();
        jPanelBrilloContraste = new javax.swing.JPanel();
        jPBrilloSlider = new javax.swing.JPanel();
        jLabelImageDownBrigthness = new javax.swing.JLabel();
        jSliderBrigthness = new javax.swing.JSlider();
        jLabelImageUpBrigthness = new javax.swing.JLabel();
        jPBrilloContraste = new javax.swing.JPanel();
        jLabelImageDownContrast = new javax.swing.JLabel();
        jSliderContrast = new javax.swing.JSlider();
        jLabelImageUpContrast = new javax.swing.JLabel();
        jComboBoxFiltros = new javax.swing.JComboBox<>();
        jDesktopEscritorio = new javax.swing.JDesktopPane();
        jPanelOeste = new javax.swing.JPanel();
        jPanelNorte = new javax.swing.JPanel();
        jScrollPanelSounds = new javax.swing.JScrollPane();
        jListSounds = new javax.swing.JList<>();
        jPanelProgressBar = new javax.swing.JPanel();
        jLabelElapsedTime = new javax.swing.JLabel();
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(5, 0), new java.awt.Dimension(5, 0), new java.awt.Dimension(32767, 0));
        jProgressBar1 = new javax.swing.JProgressBar();
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
        jMenuImagen = new javax.swing.JMenu();
        jMIBrightnessContrast = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
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
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        jMenuItemRescaleOP = new javax.swing.JMenuItem();
        jMenuItemConvolveOP = new javax.swing.JMenuItem();
        jMenuItemAffineTransformOp = new javax.swing.JMenuItem();
        jMenuItemLookupOp = new javax.swing.JMenuItem();
        jMenuItemBandCombineOp = new javax.swing.JMenuItem();
        jMenuItemColorConvertOp = new javax.swing.JMenuItem();
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
        jMenuItemEfecto = new javax.swing.JMenuItem();
        jMenuOpciones = new javax.swing.JMenu();
        jMenuIdioma = new javax.swing.JMenu();
        jRadioButtonMenuItemES = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItemEN = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItemDE = new javax.swing.JRadioButtonMenuItem();
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

        jToolBarTools.setMaximumSize(new java.awt.Dimension(575, 36));
        jToolBarTools.setMinimumSize(new java.awt.Dimension(575, 24));
        jToolBarTools.setPreferredSize(new java.awt.Dimension(575, 24));

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

        buttonGroupTools.add(jToggleButtonRectangulo);
        jToggleButtonRectangulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rectangulo.png"))); // NOI18N
        jToggleButtonRectangulo.setToolTipText(bundle.getString("RECT_TT")); // NOI18N
        jToggleButtonRectangulo.setBorder(null);
        jToggleButtonRectangulo.setFocusable(false);
        jToggleButtonRectangulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonRectangulo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonRectangulo.addActionListener(formListener);
        jToolBarTools.add(jToggleButtonRectangulo);

        buttonGroupTools.add(jToggleButtonCirculo);
        jToggleButtonCirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/elipse.png"))); // NOI18N
        jToggleButtonCirculo.setToolTipText(bundle.getString("CIRCLE_TT")); // NOI18N
        jToggleButtonCirculo.setBorder(null);
        jToggleButtonCirculo.setFocusable(false);
        jToggleButtonCirculo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonCirculo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonCirculo.addActionListener(formListener);
        jToolBarTools.add(jToggleButtonCirculo);
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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.setName("jComboBox1"); // NOI18N
        jToolBarTools.add(jComboBox1);
        jToolBarTools.add(jSeparator6);

        jLabelImageGrosor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelImageGrosor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/grosor.png"))); // NOI18N
        jToolBarTools.add(jLabelImageGrosor);

        jSpinnerGrosor.setToolTipText(bundle.getString("STROKE_TT")); // NOI18N
        jSpinnerGrosor.setMaximumSize(new java.awt.Dimension(60, 28));
        jSpinnerGrosor.setMinimumSize(new java.awt.Dimension(28, 28));
        jSpinnerGrosor.setPreferredSize(new java.awt.Dimension(50, 28));
        jSpinnerGrosor.addChangeListener(formListener);
        jSpinnerGrosor.addMouseWheelListener(formListener);
        jToolBarTools.add(jSpinnerGrosor);
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
        jToolBarSound.setName("jToolBarSound"); // NOI18N

        buttonGroupSound.add(jToggleButtonPlay);
        jToggleButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/reproducir.png"))); // NOI18N
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
        jToggleButtonShow.setBorder(null);
        jToggleButtonShow.setFocusable(false);
        jToggleButtonShow.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonShow.setName("jToggleButtonShow"); // NOI18N
        jToggleButtonShow.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonShow.addActionListener(formListener);
        jToolBarSound.add(jToggleButtonShow);

        jButtonTakePhoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/foto.png"))); // NOI18N
        jButtonTakePhoto.setBorder(null);
        jButtonTakePhoto.setFocusable(false);
        jButtonTakePhoto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonTakePhoto.setName("jButtonTakePhoto"); // NOI18N
        jButtonTakePhoto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonTakePhoto.addActionListener(formListener);
        jToolBarSound.add(jButtonTakePhoto);

        jSeparator15.setName("jSeparator15"); // NOI18N
        jToolBarSound.add(jSeparator15);

        jToggleButtonRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/grabar.png"))); // NOI18N
        jToggleButtonRecord.setBorder(null);
        jToggleButtonRecord.setFocusable(false);
        jToggleButtonRecord.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonRecord.setName("jToggleButtonRecord"); // NOI18N
        jToggleButtonRecord.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonRecord.addActionListener(formListener);
        jToolBarSound.add(jToggleButtonRecord);

        filler4.setName("filler4"); // NOI18N
        jToolBarSound.add(filler4);

        jLabelElapsedTime1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelElapsedTime1.setText("00:00");
        jLabelElapsedTime1.setMaximumSize(new java.awt.Dimension(50, 12));
        jLabelElapsedTime1.setMinimumSize(new java.awt.Dimension(40, 12));
        jLabelElapsedTime1.setName("jLabelElapsedTime1"); // NOI18N
        jLabelElapsedTime1.setPreferredSize(new java.awt.Dimension(40, 12));
        jToolBarSound.add(jLabelElapsedTime1);

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
        jSliderRotate.addChangeListener(formListener);
        jSliderRotate.addFocusListener(formListener);
        jPanelRotar.add(jSliderRotate);

        jLabelImageRotateRight.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageRotateRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rotar_der.png"))); // NOI18N
        jPanelRotar.add(jLabelImageRotateRight);

        jPanelPrevio.add(jPanelRotar, java.awt.BorderLayout.SOUTH);
        jPanelPrevio.add(Lienzo2DImagePreview, java.awt.BorderLayout.CENTER);

        jPanelSplitDerecho.add(jPanelPrevio);

        jPanelBrilloContraste.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("BRIGHTNESS_CONTRAST"))); // NOI18N
        jPanelBrilloContraste.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanelBrilloContraste.setMinimumSize(new java.awt.Dimension(0, 100));
        jPanelBrilloContraste.setPreferredSize(new java.awt.Dimension(0, 100));
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

        jSliderContrast.setMinimum(1);
        jSliderContrast.setToolTipText(bundle.getString("CONTRAST_TT")); // NOI18N
        jSliderContrast.addChangeListener(formListener);
        jSliderContrast.addFocusListener(formListener);
        jPBrilloContraste.add(jSliderContrast);

        jLabelImageUpContrast.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageUpContrast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/contraste1.png"))); // NOI18N
        jPBrilloContraste.add(jLabelImageUpContrast);

        jPanelBrilloContraste.add(jPBrilloContraste);

        jPanelSplitDerecho.add(jPanelBrilloContraste);

        jComboBoxFiltros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "Blur Medio", "Blur Binomial", "Enfoque", "Relieve", "Realzar Bordes", "Emboss", "Sharpens", "Frontera Laplaciano", "Suavisado Gaussiano", "Negativo" }));
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
        jListSounds.setMaximumSize(new java.awt.Dimension(36, 75));
        jListSounds.setName("jListSounds"); // NOI18N
        jListSounds.setPreferredSize(new java.awt.Dimension(36, 36));
        jListSounds.addMouseListener(formListener);
        jListSounds.addListSelectionListener(formListener);
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

        jProgressBar1.setName("jProgressBar1"); // NOI18N
        jPanelProgressBar.add(jProgressBar1);

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

        jMenuImagen.setText(bundle.getString("IMAGE")); // NOI18N
        jMenuImagen.setName("jMenuImagen"); // NOI18N

        jMIBrightnessContrast.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMIBrightnessContrast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/brillo1.png"))); // NOI18N
        jMIBrightnessContrast.setText(bundle.getString("BRIGHTNESS_CONTRAST")); // NOI18N
        jMIBrightnessContrast.setName("jMIBrightnessContrast"); // NOI18N
        jMIBrightnessContrast.addActionListener(formListener);
        jMenuImagen.add(jMIBrightnessContrast);

        jSeparator9.setName("jSeparator9"); // NOI18N
        jMenuImagen.add(jSeparator9);

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

        jSeparator13.setName("jSeparator13"); // NOI18N
        jMenuImagen.add(jSeparator13);

        jMenuItemRescaleOP.setText("RescaleOP");
        jMenuItemRescaleOP.setName("jMenuItemRescaleOP"); // NOI18N
        jMenuItemRescaleOP.addActionListener(formListener);
        jMenuImagen.add(jMenuItemRescaleOP);

        jMenuItemConvolveOP.setText("ConvolveOP");
        jMenuItemConvolveOP.setName("jMenuItemConvolveOP"); // NOI18N
        jMenuItemConvolveOP.addActionListener(formListener);
        jMenuImagen.add(jMenuItemConvolveOP);

        jMenuItemAffineTransformOp.setText("AffineTransformOp");
        jMenuItemAffineTransformOp.setName("jMenuItemAffineTransformOp"); // NOI18N
        jMenuItemAffineTransformOp.addActionListener(formListener);
        jMenuImagen.add(jMenuItemAffineTransformOp);

        jMenuItemLookupOp.setText("LookupOp");
        jMenuItemLookupOp.setName("jMenuItemLookupOp"); // NOI18N
        jMenuItemLookupOp.addActionListener(formListener);
        jMenuImagen.add(jMenuItemLookupOp);

        jMenuItemBandCombineOp.setText("BandCombineOp");
        jMenuItemBandCombineOp.setName("jMenuItemBandCombineOp"); // NOI18N
        jMenuItemBandCombineOp.addActionListener(formListener);
        jMenuImagen.add(jMenuItemBandCombineOp);

        jMenuItemColorConvertOp.setText("ColorConvertOp");
        jMenuItemColorConvertOp.setName("jMenuItemColorConvertOp"); // NOI18N
        jMenuItemColorConvertOp.addActionListener(formListener);
        jMenuImagen.add(jMenuItemColorConvertOp);

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
        jRadioButtonMenuItemDE.setName("jRadioButtonMenuItemDE"); // NOI18N
        jRadioButtonMenuItemDE.addActionListener(formListener);
        jMenuIdioma.add(jRadioButtonMenuItemDE);

        jMenuOpciones.add(jMenuIdioma);

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

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.FocusListener, java.awt.event.ItemListener, java.awt.event.MouseListener, java.awt.event.MouseWheelListener, javax.swing.event.ChangeListener, javax.swing.event.ListSelectionListener {
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
            else if (evt.getSource() == jToggleButtonRectangulo) {
                VentanaPrincipal.this.jToggleButtonRectanguloActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonCirculo) {
                VentanaPrincipal.this.jToggleButtonCirculoActionPerformed(evt);
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
            else if (evt.getSource() == jButtonTakePhoto) {
                VentanaPrincipal.this.jButtonTakePhotoActionPerformed(evt);
            }
            else if (evt.getSource() == jToggleButtonRecord) {
                VentanaPrincipal.this.jToggleButtonRecordActionPerformed(evt);
            }
            else if (evt.getSource() == jButtonSalir) {
                VentanaPrincipal.this.jButtonSalirActionPerformed(evt);
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
            else if (evt.getSource() == jMIBrightnessContrast) {
                VentanaPrincipal.this.jMIBrightnessContrastActionPerformed(evt);
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
            else if (evt.getSource() == jComboBoxFiltros) {
                VentanaPrincipal.this.jComboBoxFiltrosFocusLost(evt);
            }
        }

        public void itemStateChanged(java.awt.event.ItemEvent evt) {
            if (evt.getSource() == jComboBoxColor) {
                VentanaPrincipal.this.jComboBoxColorItemStateChanged(evt);
            }
            else if (evt.getSource() == jComboBoxFiltros) {
                VentanaPrincipal.this.jComboBoxFiltrosItemStateChanged(evt);
            }
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == jListSounds) {
                VentanaPrincipal.this.jListSoundsMouseClicked(evt);
            }
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
            if (evt.getSource() == jSpinnerGrosor) {
                VentanaPrincipal.this.jSpinnerGrosorMouseWheelMoved(evt);
            }
            else if (evt.getSource() == jSpinnerTransparencia) {
                VentanaPrincipal.this.jSpinnerTransparenciaMouseWheelMoved(evt);
            }
        }

        public void stateChanged(javax.swing.event.ChangeEvent evt) {
            if (evt.getSource() == jSpinnerGrosor) {
                VentanaPrincipal.this.jSpinnerGrosorStateChanged(evt);
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
        }

        public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
            if (evt.getSource() == jListSounds) {
                VentanaPrincipal.this.jListSoundsValueChanged(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    /**
     * This code only was touch with another editor for not touch much
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="My Initialized Code">/ /GEN-BEGIN:myInitComponents
    private void myInitComponents() {
        // pongo aquí los valores de los sppiner para setearlos al principio.
        this.jSpinnerGrosor.setValue(VentanaPrincipal.MIN_VALUE_SPPINER);
        this.jSpinnerTransparencia.setValue(VentanaPrincipal.MAX_VALUE_SPPINER);
        // añadimos los filtros para los cuadros de dialogo de abrir y guardar
        this.filters[this.ALL_FORMAT_IMAGE] = new FileNameExtensionFilter("Todas las Imagenes", "jpg", "jpeg", "png", "gif");
        this.filters[this.JPEG_FILE] = new FileNameExtensionFilter("Joint Photographic Experts Group (JPEG)", "jpg", "jpeg"); // NOI18N
        this.filters[this.PNG_FILE] = new FileNameExtensionFilter("Portable Network Graphics (PNG)", "png"); // NOI18N
        this.filters[this.GIF_FILE] = new FileNameExtensionFilter("Graphics Interchange Format (GIF)", "gif"); // NOI18N
        this.filters[this.ALL_FORMAT_SOUND] = new FileNameExtensionFilter("Todas los Sonidos", "wav", "au", "aiff", "mp3"); // NOI18N
        this.filters[this.WAV_FILE] = new FileNameExtensionFilter("Waveform Audio Format (WAV)", "wav"); // NOI18N
        this.filters[this.AU_FILE] = new FileNameExtensionFilter("Audio File Format (AU)", "au"); // NOI18N
        this.filters[this.AIFF_FILE] = new FileNameExtensionFilter("Audio Interchange File Format (AIFF)", "aiff"); // NOI18N
        this.filters[this.MP3_FILE] = new FileNameExtensionFilter("MPEG-2 Audio Layer III (MP3)", "mp3"); // NOI18N
        this.filters[this.ALL_FORMAT_VIDEO] = new FileNameExtensionFilter("Todas los Videos", "mpg", "mpeg", "mp4"); // NOI18N
        this.filters[this.MPG_FILE] = new FileNameExtensionFilter("Moving Picture Experts Group (MPEG)", "mpg", "mpeg"); // NOI18N
        this.filters[this.MP4_FILE] = new FileNameExtensionFilter("MP4 file format version 2 (MP4)", "mp4"); // NOI18N
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
    }// </editor-fold>/ /GEN-END:myInitComponents

    /**
     * Clase encargada de manejar los eventos de sonido.
     */
    private class SoundListener extends Object implements LineListener {

        @Override
        public void update(LineEvent evt) {
            if (evt.getType() == LineEvent.Type.OPEN) {
                // @TODO tienes que poner las cosas que se hacen cuando un sonido se abre
            }
            if (evt.getType() == LineEvent.Type.START) {
                /*
                @TODO como el evento se ha producido, pongo el estado del objeto a escuchando
                pongo la imagen del icono del bóton de play a pause y lo pongo como pulsado
                 */
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
                    VentanaPrincipal.this.jToggleButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/pause.png"))); // NOI18N
                    VentanaPrincipal.this.jToggleButtonPlay.setSelected(true);
                    /* Para actualizar la barra de progreso */
                    new Thread(() -> {
                        while (((ClipPlayer) VentanaPrincipal.this.multimedia_actual).isWorking()) {
                            SoundListener.this.progressBarPlayed();
                        }
                    }).start();
                }
            }
            if (evt.getType() == LineEvent.Type.STOP) {
                /*
                @TODO Como el evento se ha producido, pongo el objeto a estado parado
                pongo la imagen del icono del bóton de pause a play y lo pongo como no pulsado
                 */
                if (VentanaPrincipal.this.optionRecording != null) {
                    VentanaPrincipal.this.optionRecording = null;
                    VentanaPrincipal.this.jToggleButtonRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/grabar.png"))); // NOI18N
                    /* restauro lo que antes he deshabilitado */
                    VentanaPrincipal.this.jToggleButtonPlay.setEnabled(true);
                    VentanaPrincipal.this.jToggleButtonStop.setEnabled(true);
                    VentanaPrincipal.this.jToggleButtonShow.setEnabled(true);
                    VentanaPrincipal.this.jPanelOeste.setVisible(true);
                } else {
                    // cuidado con el orden de las instrucciones
                    VentanaPrincipal.this.jToggleButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/reproducir.png"))); // NOI18N
                    VentanaPrincipal.this.jToggleButtonStop.setSelected(true);
                    ((ClipPlayer) VentanaPrincipal.this.multimedia_actual).stop();
                    int actual = VentanaPrincipal.this.jListSounds.getSelectedIndex();
                    int ultimo = VentanaPrincipal.this.jListSounds.getLastVisibleIndex();
                    if (VentanaPrincipal.this.jRadioButtonRepeatAll.isSelected()) {
                        if (actual != -1 && actual < ultimo) {
                            VentanaPrincipal.this.jListSounds.setSelectedIndex(actual + 1);
                            ((ClipPlayer) VentanaPrincipal.this.multimedia_actual).play();
                        } else if (actual == ultimo) {
                            VentanaPrincipal.this.jListSounds.setSelectedIndex(0);
                            ((ClipPlayer) VentanaPrincipal.this.multimedia_actual).play();
                        }
                    }
                    if (VentanaPrincipal.this.jRadioButtonRepeatSound.isSelected()) {
                        ((ClipPlayer) VentanaPrincipal.this.multimedia_actual).play();
                    }
                }
            }
            if (evt.getType() == LineEvent.Type.CLOSE) {
                // @TODO tienes que poner las cosas que se hacen cuando un sonido se cierra
                VentanaPrincipal.this.getToggleButtonStop().setSelected(true);
            }
        }

        private void progressBarPlayed() {
            if (VentanaPrincipal.this.multimedia_actual != null) {
                final long posicion = ((ClipPlayer) VentanaPrincipal.this.multimedia_actual).getClip().getLongFramePosition();
                final float frameRate = ((ClipPlayer) VentanaPrincipal.this.multimedia_actual).getClip().getFormat().getFrameRate();
                final long tActual = (long) (posicion / frameRate);
                final long min = tActual / 60L;
                final long seg = tActual % 60L;
                final String s = String.format("%02d:%02d", min, seg);
                VentanaPrincipal.this.jLabelElapsedTime.setText(s);
            }
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
        return this.jSpinnerGrosor;
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
//
//    public JToggleButton getToggleButtonPlay() {
//        return this.jToggleButtonPlay;
//    }

    public JToggleButton getToggleButtonStop() {
        return this.jToggleButtonStop;
    }
//
//    public JToggleButton getToggleButtonRecord() {
//        return this.jToggleButtonRecord;
//    }

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
        VentanaInternaImagen ultima_ventana = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
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
        dlg.showOpenDialog(this);
        // Recorremos lo ficheros para ver de que tipo son y abrirlos
        if (JFileChooser.APPROVE_OPTION == 0) {
            java.io.File[] file_list_aux = dlg.getSelectedFiles();
            for (int i = 0; i < file_list_aux.length; i++) {
                // @TODO Mejora muy considerable para buscar las extensiones de los ficheros
                File faux = new File(file_list_aux[i].getAbsolutePath());
                // se puede hacer mejor pero para esta aplicación me vale
                this.dir_actual = faux.getPath();
                try {
                    if (this.filters[this.ALL_FORMAT_IMAGE].contains(faux.getExt())) {
                        BufferedImage img = ImageIO.read(faux);
                        this.vi_image = new VentanaInternaImagen(this, new Dimension(img.getWidth(), img.getHeight()), img.getType());
                        if (ultima_ventana != null) {
                            this.vi_image.setLocation(ultima_ventana.getLocation().x + (i * 15), ultima_ventana.getLocation().y + (i * 15));
                        } else {
                            this.vi_image.setLocation(15, 15);
                        }
                        this.vi_image.getLienzo().setImage(img);
                        this.getEscritorio().add(vi_image);
                        this.vi_image.setTitle(file_list_aux[i].getName());
                        this.vi_image.setVisible(true);
                    } else if (this.filters[this.ALL_FORMAT_SOUND].contains(faux.getExt())) {
                        this.multimedia_files.add(new Item(faux.getFriendlyName(), faux.getExt(), faux.getPath(), this.multimedia_files.size()));
                        this.list_model.addElement(faux.getFriendlyName());
                        boolean aux = this.jCheckBoxOpenPanel.isSelected();
                        this.jCheckBoxMenuItemPanelSonido.setSelected(aux);
                        this.jToggleButtonShow.setSelected(aux);
                        this.jPanelOeste.setVisible(aux);
                    } else if (this.filters[this.ALL_FORMAT_VIDEO].contains(faux.getExt())) {
                        BufferedImage vid = ImageIO.read(file_list_aux[i]);
                    }
                } catch (FileNotFoundException ex) {
                    System.err.println("El Fichero " + file_list_aux[i].getName() + " no existe " + ex);
                } catch (IOException ex) {
                    System.err.println("No se pudo crear el fichero " + file_list_aux[i].getName() + " " + ex);
                } catch (NullPointerException ex) {
                    System.err.println("No se permite el valor null en el fichero " + file_list_aux[i].getName() + " " + ex);
                } catch (Exception ex) {
                    System.err.println("Error desconocido con el fichero " + file_list_aux[i].getName() + " " + ex);
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
                this.vi_image = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
                int type = this.vi_image.getLienzo().getImage().getType();
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
            } else if (this.vi instanceof VentanaInternaVideo || this.vi instanceof VentanaInternaWebcam) {
                this.vi_video = (VentanaInternaVideo) this.jDesktopEscritorio.getSelectedFrame();
                dlg.addChoosableFileFilter(this.filters[this.MPG_FILE]);
                dlg.addChoosableFileFilter(this.filters[this.MP4_FILE]);
                dlg.setAcceptAllFileFilterUsed(false);
            }
            int resp = dlg.showSaveDialog(this);
            File f = new File(dlg.getSelectedFile().getAbsolutePath());
            if (resp == JFileChooser.APPROVE_OPTION) {
                try {
                    if (this.vi instanceof VentanaInternaImagen) {
                        this.vi_image = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
                        BufferedImage img = this.vi_image.getLienzo().getImage(true);
                        this.vi_image.getLienzo().clean();
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
                    } else if (this.vi instanceof VentanaInternaVideo || this.vi instanceof VentanaInternaWebcam) {
                        this.vi_video = (VentanaInternaVideo) this.jDesktopEscritorio.getSelectedFrame();
                        /*
                        Aqui tienes que crear la variable para poder guardar el fichero
                         */
//                        BufferedImage video = new BufferedImage(1, 1, 3);
//                        if (video != null) {
//                            if (dlg.getFileFilter().equals(this.filters[this.MPG_FILE])) {
//                                ImageIO.write(video, "mpg", f);
//                            } else if (dlg.getFileFilter().equals(this.filters[this.MP4_FILE])) {
//                                ImageIO.write(video, "mp4", f);
//                            }
//                        }
                    }
                    this.vi.setTitle(f.getName());
                } catch (Exception ex) {
                    System.err.println("Error al guardar la imagen" + ex.getLocalizedMessage());
                }
            }
            VentanaPrincipal.COUNT_VI--;
        }
    }//GEN-LAST:event_jMenuItemGuardarActionPerformed

    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemSalirActionPerformed

    private void jMenuItemDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeshacerActionPerformed
        this.vi_image = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
        if (VentanaPrincipal.UNDO != null && this.vi_image != null) {
            VentanaPrincipal.REDO = this.vi_image.getLienzo().getImage();
            this.vi_image.getLienzo().setImage(VentanaPrincipal.UNDO);
            myRectangleShape border_aux = (myRectangle) this.vi_image.getLienzo().getVisiblePart();
            border_aux.setWidth(VentanaPrincipal.UNDO.getWidth());
            border_aux.setHeigth(VentanaPrincipal.UNDO.getHeight());
            VentanaPrincipal.UNDO = null;
        }
        this.jMenuItemDeshacer.setEnabled(false);
        this.jMenuItemRehacer.setEnabled(true);
    }//GEN-LAST:event_jMenuItemDeshacerActionPerformed

    private void jMenuItemRehacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRehacerActionPerformed
        this.vi_image = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
        if (VentanaPrincipal.REDO != null && this.vi_image != null) {
            VentanaPrincipal.UNDO = this.vi_image.getLienzo().getImage();
            this.vi_image.getLienzo().setImage(VentanaPrincipal.REDO);
            myRectangleShape border_aux = (myRectangle) this.vi_image.getLienzo().getVisiblePart();
            border_aux.setWidth(VentanaPrincipal.REDO.getWidth());
            border_aux.setHeigth(VentanaPrincipal.REDO.getHeight());
            VentanaPrincipal.REDO = null;
        }
        this.jMenuItemDeshacer.setEnabled(true);
        this.jMenuItemRehacer.setEnabled(false);
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

    private void jMenuItemResizeImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemResizeImageActionPerformed
        QuestionsResizeImage question = new QuestionsResizeImage(this, true);
        question.setDescriptionDialog("Por favor indique el nuevo tamaño");
        question.setTextButtonOk("Redimensionar");
        question.setVisible(true);
    }//GEN-LAST:event_jMenuItemResizeImageActionPerformed

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
        this.vi_image = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi_image != null) {
            if (this.imgOrigen == null) {
                this.imgOrigen = this.vi_image.getLienzo().getImage();
                // para el deshacer
                VentanaPrincipal.UNDO = this.imgOrigen;
                this.setEnabledUndo(true);
                this.vi_image.getLienzo().setTypeImage(BufferedImage.TYPE_INT_ARGB);
            }
            if (this.imgOrigen != null) {
                AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(270.0f), this.imgOrigen.getWidth() / 2, this.imgOrigen.getHeight() / 2); // rotate
                try {
                    AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    this.imgTemp = new BufferedImage(this.imgOrigen.getHeight(), this.imgOrigen.getWidth(), BufferedImage.TYPE_INT_ARGB);
                    this.imgTemp.createGraphics().drawImage(this.imgOrigen, atop, 0, 0);
                    this.vi_image.getLienzo().setImage(this.imgTemp);
                    this.imgOrigen = this.imgTemp = null;
                } catch (Exception e) {
                    System.err.println("Error rotando la imagen a 90º a la izquierda " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemRotateLeftActionPerformed

    private void jMenuItemRotateRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRotateRightActionPerformed
        this.vi_image = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi_image != null) {
            if (this.imgOrigen == null) {
                this.imgOrigen = this.vi_image.getLienzo().getImage();
                // para el deshacer
                VentanaPrincipal.UNDO = this.imgOrigen;
                this.setEnabledUndo(true);
                this.vi_image.getLienzo().setTypeImage(BufferedImage.TYPE_INT_ARGB);
            }
            if (this.imgOrigen != null) {
                AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90.0f), this.imgOrigen.getWidth() / 2, this.imgOrigen.getHeight() / 2); // rotate
                try {
                    AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    this.imgTemp = new BufferedImage(this.imgOrigen.getHeight(), this.imgOrigen.getWidth(), BufferedImage.TYPE_INT_ARGB);
                    this.imgTemp.createGraphics().drawImage(this.imgOrigen, atop, 0, 0);
                    this.vi_image.getLienzo().setImage(this.imgTemp);
                    this.imgOrigen = this.imgTemp = null;
                } catch (Exception e) {
                    System.err.println("Error rotando la imagen a 90º a la derecha " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemRotateRightActionPerformed

    private void jMenuItemRotate180ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRotate180ActionPerformed
        this.vi_image = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi_image != null) {
            if (this.imgOrigen == null) {
                this.vi_image.getLienzo().setTypeImage(BufferedImage.TYPE_INT_ARGB);
                this.imgOrigen = this.vi_image.getLienzo().getImage();
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
                    this.vi_image.getLienzo().setImage(this.imgTemp);
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
        this.vi_image = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi_image != null) {
            BufferedImage img_aux = this.vi_image.getLienzo().getImage();
            VentanaInternaImagen new_vi = new VentanaInternaImagen(this, new Dimension(img_aux.getWidth(), img_aux.getHeight()), img_aux.getType());
            // @TODO tienes que recorrer el array de formas y añadirlas a la nueva ventana
            new_vi.setLocation(this.vi_image.getLocation().x + 15, this.vi_image.getLocation().y + 15);
            new_vi.getLienzo().setImage(img_aux);
            this.jDesktopEscritorio.add(new_vi);
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
     * operación
     * @return si se puede aplicar el efecto a la imagen original devuelve
     * verdadero, falso en caso contrario.
     */
    private boolean canApply(int t) {
        boolean aux = false;
        this.vi_image = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi_image != null) {
            if (this.imgOrigen == null) {
                this.imgOrigen = this.vi_image.getLienzo().getImage();
            }
            this.vi_image.getLienzo().setTypeImage(t);
            this.imgTemp = this.vi_image.getLienzo().getImage();
            // para el deshacer
            VentanaPrincipal.UNDO = this.imgTemp;
        }
        if (this.imgTemp != null) {
            aux = true;
        }
        return aux;
    }

    private void jMenuItemSenoFunctionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSenoFunctionActionPerformed
        if (this.canApply(BufferedImage.TYPE_INT_ARGB)) {
            try {
                LookupTable seno = Utils.senoFuction(180.0f / 255.0f);
                LookupOp ltop = new LookupOp(seno, null);
                this.vi_image.getLienzo().setImage(ltop.filter(this.imgTemp, null));
                this.imgOrigen = this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception e) {
                System.err.println("Error aplicando la función seno " + e.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_jMenuItemSenoFunctionActionPerformed

    private void jMenuItemTintarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTintarActionPerformed
        if (this.canApply(BufferedImage.TYPE_INT_ARGB)) {
            try {
                LookupTable seno = Utils.senoFuction(180.0f / 255.0f);
                LookupOp ltop = new LookupOp(seno, null);
                this.vi_image.getLienzo().setImage(ltop.filter(this.imgTemp, null));
                this.imgOrigen = this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception e) {
                System.err.println("Error aplicando la función seno " + e.getLocalizedMessage());
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
                this.vi_image.getLienzo().setImage(rop.filter(this.imgTemp, null));
                this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (IllegalArgumentException e) {
                System.err.println("Error aplicando filtro rescale " + e.getLocalizedMessage());
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
                this.vi_image.getLienzo().setImage(this.imgTemp);
                this.imgOrigen = this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (IllegalArgumentException e) {
                System.err.println("Error al aplicar el convolve: " + e.getLocalizedMessage());
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
                this.vi_image.getLienzo().setImage(this.imgTemp);
                this.imgOrigen = this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception e) {
                System.err.println("Error aplicando filtro affine " + e.getLocalizedMessage());
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
                this.vi_image.getLienzo().setImage(this.imgTemp);
                this.imgOrigen = this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception e) {
                System.err.println("Error al aplicar el Lookup " + e.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_jMenuItemLookupOpActionPerformed

    private void jMenuItemBandCombineOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBandCombineOpActionPerformed
        if (this.canApply(BufferedImage.TYPE_INT_ARGB)) {
            this.imgOrigen = this.vi_image.getLienzo().getImage();
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
                this.vi_image.getLienzo().setImage(new BufferedImage(this.imgOrigen.getColorModel(), rasterdest, false, null));
                this.imgOrigen = this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception e) {
                System.err.println("Error al aplicar el BandCombine " + e.getLocalizedMessage());
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
                this.vi_image.getLienzo().setImage(ccop.filter(this.imgTemp, null));
                this.imgOrigen = this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception e) {
                System.err.println("Error al aplicar el ColorConvert " + e.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_jMenuItemColorConvertOpActionPerformed

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
                this.vi_image.getLienzo().setImage(lop.filter(this.imgTemp, null));
                this.imgOrigen = this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception e) {
                System.err.println("Error al aplicar el Negativo " + e.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_jMenuItemNegativeActionPerformed

    private void jMenuItemSepiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSepiaActionPerformed
        if (this.canApply(BufferedImage.TYPE_INT_RGB)) {
            try {
                SepiaOP sepop = new SepiaOP();
                this.imgTemp = sepop.filter(this.imgTemp, null);
                this.vi_image.getLienzo().setImage(this.imgTemp);
                this.imgOrigen = this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception e) {
                System.err.println("Error al aplicar el Sepia " + e.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_jMenuItemSepiaActionPerformed

    private void jMenuItemEfectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEfectoActionPerformed
        if (this.canApply(BufferedImage.TYPE_INT_RGB)) {
            try {
                myEfectOP myop = new myEfectOP();
                this.imgTemp = myop.filter(this.imgTemp, null);
                this.vi_image.getLienzo().setImage(this.imgTemp);
                this.imgOrigen = this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (Exception e) {
                System.err.println("Error al aplicar el Mi propio efecto " + e.getLocalizedMessage());
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
        this.vi_image = (VentanaInternaImagen) jDesktopEscritorio.getSelectedFrame();
        this.jLabelAccionRaton.setText(bundle.getString("MOUSE"));
        this.vi_image.getLienzo().setPaint();
    }//GEN-LAST:event_jToggleButtonPintarActionPerformed

    private void jToggleButtonMoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonMoverActionPerformed
        this.vi_image = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
        this.jLabelAccionRaton.setText(bundle.getString("MOVE"));
        this.vi_image.getLienzo().setMove();
    }//GEN-LAST:event_jToggleButtonMoverActionPerformed

    private void jToggleButtonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonBorrarActionPerformed
        this.vi_image = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
        this.jLabelAccionRaton.setText(bundle.getString("DELETE"));
        this.vi_image.getLienzo().setDelete();
    }//GEN-LAST:event_jToggleButtonBorrarActionPerformed

    private void jToggleButtonPuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonPuntoActionPerformed
        this.jLabelHerramienta.setText(bundle.getString("TOOL_POINT"));
        this.vi_image.getLienzo().setTool("point"); //NOI18N
    }//GEN-LAST:event_jToggleButtonPuntoActionPerformed

    private void jToggleButtonLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonLineaActionPerformed
        this.jLabelHerramienta.setText(bundle.getString("TOOL_LINE"));
        this.vi_image.getLienzo().setTool("line"); //NOI18N
    }//GEN-LAST:event_jToggleButtonLineaActionPerformed

    private void jToggleButtonRectanguloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonRectanguloActionPerformed
        this.jLabelHerramienta.setText(bundle.getString("TOOL_RECT"));
        this.vi_image.getLienzo().setTool("rect"); //NOI18N
    }//GEN-LAST:event_jToggleButtonRectanguloActionPerformed

    private void jToggleButtonCirculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonCirculoActionPerformed
        this.jLabelHerramienta.setText(bundle.getString("TOOL_CIRCLE"));
        this.vi_image.getLienzo().setTool("oval"); //NOI18N
    }//GEN-LAST:event_jToggleButtonCirculoActionPerformed

    private void jComboBoxColorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxColorItemStateChanged
        Color aux_color = (Color) this.jComboBoxColor.getSelectedItem();
        this.jLabelColor.setText(bundle.getString("COLOR") + "[R: " + aux_color.getRed() + " G: " + aux_color.getGreen() + " B: " + aux_color.getBlue() + "]");
        this.vi_image = (VentanaInternaImagen) jDesktopEscritorio.getSelectedFrame();
        this.vi_image.getLienzo().setColor((Color) this.jComboBoxColor.getSelectedItem());
    }//GEN-LAST:event_jComboBoxColorItemStateChanged

    private void jSpinnerGrosorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerGrosorStateChanged
        this.vi_image = (VentanaInternaImagen) jDesktopEscritorio.getSelectedFrame();
        if (this.vi_image != null) {
            this.vi_image.getLienzo().setStroke((int) this.jSpinnerGrosor.getValue() * 1f);
        }
    }//GEN-LAST:event_jSpinnerGrosorStateChanged

    private void jSpinnerGrosorMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jSpinnerGrosorMouseWheelMoved
        if (evt.getPreciseWheelRotation() < 0) {
            if ((int) this.jSpinnerGrosor.getValue() < VentanaPrincipal.MAX_VALUE_SPPINER) {
                this.jSpinnerGrosor.setValue((int) this.jSpinnerGrosor.getValue() + 7);
            }
        } else if ((int) this.jSpinnerGrosor.getValue() > VentanaPrincipal.MIN_VALUE_SPPINER) {
            this.jSpinnerGrosor.setValue((int) this.jSpinnerGrosor.getValue() - 7);
        }
    }//GEN-LAST:event_jSpinnerGrosorMouseWheelMoved

    private void jSpinnerTransparenciaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerTransparenciaStateChanged
        this.vi_image = (VentanaInternaImagen) jDesktopEscritorio.getSelectedFrame();
        if (this.vi_image != null) {
            this.vi_image.getLienzo().setAlpha((int) this.jSpinnerTransparencia.getValue());
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
        this.vi_image = (VentanaInternaImagen) jDesktopEscritorio.getSelectedFrame();
        //this.vi_image.getLienzo().setFill(this.jToggleButtonRelleno.isSelected());
    }//GEN-LAST:event_jToggleButtonRellenoActionPerformed

    private void jToggleButtonAntiAliasingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonAntiAliasingActionPerformed
        this.vi_image = (VentanaInternaImagen) jDesktopEscritorio.getSelectedFrame();
        this.vi_image.getLienzo().setFlatten(this.jToggleButtonAntiAliasing.isSelected());
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
                this.vi_image.getLienzo().setImage(this.imgTemp);
            } catch (Exception e) {
                System.err.println("Error aplicando el zoom " + e.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_jSliderZoomStateChanged

    private void jSliderZoomFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSliderZoomFocusLost
        if (this.jSliderZoom.getValue() != 100) {
            int n = JOptionPane.showConfirmDialog(this, "¿Quiere aplicar los cambios de zoom realizados?", "Realizar Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            // son 2 botones el si=0, el no=1
            if (n == 1) {
                this.vi_image = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
                this.vi_image.getLienzo().setImage(this.imgOrigen);
            }
            this.imgOrigen = null;
            this.jSliderZoom.setValue(100);
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
                this.vi_image.getLienzo().setImage(this.imgTemp);
            } catch (Exception e) {
                System.err.println("Error rotando la imagen " + e.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_jSliderRotateStateChanged

    private void jSliderRotateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSliderRotateFocusLost
        if (this.jSliderRotate.getValue() != 0) {
            int n = JOptionPane.showConfirmDialog(this, "¿Quiere aplicar los cambios de rotación realizados?", "Realizar Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            // son 2 botones el si=0, el no=1
            if (n == 1) {
                this.vi_image = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
                this.vi_image.getLienzo().setImage(this.imgOrigen);
            }
            this.imgOrigen = null;
            this.jSliderRotate.setValue(0);
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
                this.imgOrigen = this.vi_image.getLienzo().getImage();
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
                this.vi_image.getLienzo().setImage(this.imgTemp);
            } catch (IllegalArgumentException e) {
                System.err.println("Error al intentar modificar el brillo " + e.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_jSliderBrigthnessStateChanged

    private void jSliderBrigthnessFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSliderBrigthnessFocusLost
        if (this.jSliderBrigthness.getValue() != 0) {
            int n = JOptionPane.showConfirmDialog(this, "¿Quiere aplicar los cambios de brillo realizados?", "Realizar Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            // son 2 botones el si=0, el no=1
            if (n == 1) {
                this.vi_image = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
                this.vi_image.getLienzo().setImage(this.imgOrigen);
            }
            this.imgOrigen = null;
            this.jSliderBrigthness.setValue(0);
        }
    }//GEN-LAST:event_jSliderBrigthnessFocusLost

    private void jSliderContrastStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderContrastStateChanged
        /*
            El comprobar el 0 es por que cuando aplicamos los cambios el slider
            cambia de valor a 0 y se vuelve a llamar
            Solo guardo la imagen original 1 sola vez (this.imgOrigen == null)
         */
        int value_slider = this.jSliderContrast.getValue();
        if (this.canApply(BufferedImage.TYPE_INT_ARGB) && value_slider != 50) {
            if (this.imgOrigen == null) {
                this.imgOrigen = this.vi_image.getLienzo().getImage();
            }
            //this.imgTemp = Utils.convertImageToType(this.imgOrigen, BufferedImage.TYPE_INT_ARGB);
            this.setEnabledUndo(true);
            try {
                // finción S
                LookupTable lt = LookupTableProducer.sFuction(value_slider / 100f, 3.5f);
                // función potencia
                // LookupTable lt = LookupTableProducer.powerFuction(value_slider / 100.0F);
                // función raiz
                // LookupTable lt = LookupTableProducer.rootFuction(value_slider);
                // función logaritmica
                // LookupTable lt = LookupTableProducer.logarithmFuction();
                // función gamma
                // LookupTable lt = LookupTableProducer.gammaCorrection(value_slider, 2);
                LookupOp lop = new LookupOp(lt, null);
                this.imgTemp = lop.filter(this.imgOrigen, null);
                this.vi_image.getLienzo().setImage(this.imgTemp);
            } catch (IllegalArgumentException e) {
                System.err.println("Error al intentar modificar el contraste " + e.getLocalizedMessage());
            }
        }
    }//GEN-LAST:event_jSliderContrastStateChanged

    private void jSliderContrastFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSliderContrastFocusLost
        if (this.jSliderContrast.getValue() != 50) {
            int n = JOptionPane.showConfirmDialog(this, "¿Quiere aplicar los cambios de contraste realizados?", "Realizar Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            // son 2 botones el si=0, el no=1
            if (n == 1) {
                this.vi_image = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
                this.vi_image.getLienzo().setImage(this.imgOrigen);
            }
            this.imgOrigen = null;
            this.jSliderContrast.setValue(50);
        }
    }//GEN-LAST:event_jSliderContrastFocusLost

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
            this.imgOrigen = this.vi_image.getLienzo().getImage();
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
                this.vi_image.getLienzo().setImage(this.imgTemp);
                this.imgTemp = null;
                this.setEnabledUndo(true);
            } catch (IllegalArgumentException e) {
                System.err.println("Error al aplicar el filtro: " + e.getLocalizedMessage());
            }
        }
    }

    private void jComboBoxFiltrosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxFiltrosItemStateChanged
        // este if es por que al cambiar de estado se mandan dos casos
        // el que estaba seleccionado y el que se ha seleccionado
        // por eso se discrimina el caso del que estaba seleccionado.
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
            }
        }
    }//GEN-LAST:event_jComboBoxFiltrosItemStateChanged

    private void jComboBoxFiltrosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBoxFiltrosFocusLost
        if (this.jComboBoxFiltros.getSelectedIndex() != 0) {
            int n = JOptionPane.showConfirmDialog(this, "¿Queiere aplicar el filtro que acaba de elegir?", "Realizar Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            // son 2 botones el si=0, el no=1
            if (n == 1) {
                this.vi_image = (VentanaInternaImagen) this.jDesktopEscritorio.getSelectedFrame();
                this.vi_image.getLienzo().setImage(this.imgOrigen);
            }
            this.imgOrigen = null;
            this.jComboBoxFiltros.setSelectedIndex(0);
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
        if (id >= 0 && ((ClipPlayer) this.multimedia_actual).isWorking()) {
            ((ClipPlayer) this.multimedia_actual).pause();
        } else if (id >= 0) {
            ((ClipPlayer) this.multimedia_actual).play();
        } else {
            JOptionPane.showConfirmDialog(this, "Por favor seleccione lo que\nquiere reproducir o hagá\ndoble click encima", "Seleccionar Multimedia", JOptionPane.CLOSED_OPTION, JOptionPane.INFORMATION_MESSAGE);
            this.jToggleButtonStop.setSelected(true);
        }
    }//GEN-LAST:event_jToggleButtonPlayActionPerformed

    private void jToggleButtonStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonStopActionPerformed
        /*
        @TODO Paras y cierras la linea en el caso de audio,
        Paras el video cierras la ventana en caso de video
        Paras la grabación y sacas el dialogo para poder guardar el fichero.
         */
        if (this.multimedia_actual != null) {
            ((ClipPlayer) this.multimedia_actual).stop();
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
            /*
            @TODO tienes que poner el cuadro de dialogo para guardar el fichero
            lo que tienes que hacer es pedir al usuario cual es el fichero de destino
            y despues mover el fichero guardado en el lugar correcto.
             */
            JFileChooser dlg = new JFileChooser();
            dlg.addChoosableFileFilter(this.filters[this.WAV_FILE]);
            dlg.addChoosableFileFilter(this.filters[this.AU_FILE]);
            dlg.addChoosableFileFilter(this.filters[this.AIFF_FILE]);
            dlg.setAcceptAllFileFilterUsed(false);
            if (dlg.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                try {
                    File destino = new File(dlg.getSelectedFile().getAbsolutePath());
                    Files.move(this.multimedia_actual.getSoundFile().toPath(), destino.toPath(), REPLACE_EXISTING);
                    this.multimedia_files.add(new Item(destino.getFriendlyName(), destino.getExt(), destino.getPath(), this.multimedia_files.size()));
                    this.list_model.addElement(destino.getFriendlyName());
                } catch (IOException ex) {
                    Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    System.err.println("Error al guardar el fichero: " + ex);
                    this.multimedia_actual.getSoundFile().delete();
                }
            } else {
                this.multimedia_actual.getSoundFile().delete();
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
                    this.multimedia_actual.setListener(this.mySoundListener);
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

    private void jButtonTakePhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTakePhotoActionPerformed
        /*
        @TODO Tomas una fotografia de la reproducción de video actual
        abres una ventana interna de imagenes y la pones en esa ventana.
         */
    }//GEN-LAST:event_jButtonTakePhotoActionPerformed

    private void jListSoundsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jListSoundsValueChanged
        /*
        @TODO cuando cambien la seleccion del fichero tienes que parar el fichero
        si hay alguno sonando despúes tienes que leer la posición de la selección
        y con esa posición buscas el Clip en el array de clips que hay,
        Creas un objeto ClipPlayer y lo almacenas en una variable global para reproducir.
         */
        if (this.multimedia_actual != null && ((ClipPlayer) this.multimedia_actual).isWorking()) {
            ((ClipPlayer) this.multimedia_actual).stop();
        }
        if (this.jListSounds.getSelectedIndex() != -1) {
            this.multimedia_actual = new ClipPlayer(this.multimedia_files.get(this.jListSounds.getSelectedIndex()).path);
            this.multimedia_actual.setListener(this.mySoundListener);
        }
    }//GEN-LAST:event_jListSoundsValueChanged

    /**
     * Este método lo utilizamos para realizar una simulación del doble click
     *
     * @param evt [in] variables del evento de entrada.
     */
    private void jListSoundsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListSoundsMouseClicked
        if (this.DoubleClick && this.multimedia_actual != null) {
            ((ClipPlayer) this.multimedia_actual).play();
            this.DoubleClick = false;
        } else {
            this.DoubleClick = true;
            Timer t = new Timer("doubleclickTimer", false);
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    DoubleClick = false;
                }
            }, this.TimeDoubleClick);
        }
    }//GEN-LAST:event_jListSoundsMouseClicked

    private void jListSoundsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListSoundsMousePressed
        /*
        @TODO Cuando se pulse encima de la lista y no haya nada seleccionado
        que no se marque nada.
         */
        //this.jListSounds.clearSelection();
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
    private sm.cdlt.ui.Lienzo2DImage Lienzo2DImagePreview;
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
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonTakePhoto;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemPanelSonido;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemPantallaCompleta;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVerBarraEstado;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVerBarraHerramientas;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVerBarraSonido;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVerBarraUtilidades;
    private javax.swing.JCheckBox jCheckBoxOpenPanel;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxColor;
    private javax.swing.JComboBox<String> jComboBoxFiltros;
    private javax.swing.JDesktopPane jDesktopEscritorio;
    private javax.swing.JLabel jLabelAccionRaton;
    private javax.swing.JLabel jLabelColor;
    private javax.swing.JLabel jLabelElapsedTime;
    private javax.swing.JLabel jLabelElapsedTime1;
    private javax.swing.JLabel jLabelHerramienta;
    private javax.swing.JLabel jLabelImageColor;
    private javax.swing.JLabel jLabelImageDownBrigthness;
    private javax.swing.JLabel jLabelImageDownContrast;
    private javax.swing.JLabel jLabelImageDownZoom;
    private javax.swing.JLabel jLabelImageGrosor;
    private javax.swing.JLabel jLabelImageRotateLeft;
    private javax.swing.JLabel jLabelImageRotateRight;
    private javax.swing.JLabel jLabelImageUpBrigthness;
    private javax.swing.JLabel jLabelImageUpContrast;
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
    private javax.swing.JMenuItem jMenuItemConvolveOP;
    private javax.swing.JMenuItem jMenuItemCopiar;
    private javax.swing.JMenuItem jMenuItemCortar;
    private javax.swing.JMenuItem jMenuItemCustom;
    private javax.swing.JMenuItem jMenuItemDeshacer;
    private javax.swing.JMenuItem jMenuItemEfecto;
    private javax.swing.JMenuItem jMenuItemEmboss;
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
    private javax.swing.JMenuItem jMenuItemSuavizado;
    private javax.swing.JMenuItem jMenuItemSubstract;
    private javax.swing.JMenuItem jMenuItemTintar;
    private javax.swing.JMenu jMenuOpciones;
    private javax.swing.JMenu jMenuRotate;
    private javax.swing.JMenu jMenuVer;
    private javax.swing.JPanel jPBrilloContraste;
    private javax.swing.JPanel jPBrilloSlider;
    private javax.swing.JPanel jPanelBrilloContraste;
    private javax.swing.JPanel jPanelContentBars;
    private javax.swing.JPanel jPanelContentIcons;
    private javax.swing.JPanel jPanelNorte;
    private javax.swing.JPanel jPanelOeste;
    private javax.swing.JPanel jPanelOptions;
    private javax.swing.JPanel jPanelPrevio;
    private javax.swing.JPanel jPanelProgressBar;
    private javax.swing.JPanel jPanelRotar;
    private javax.swing.JPanel jPanelSplitDerecho;
    private javax.swing.JPanel jPanelStatusBar;
    private javax.swing.JPanel jPanelSur;
    private javax.swing.JPanel jPanelZoom;
    private javax.swing.JProgressBar jProgressBar1;
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
    private javax.swing.JSlider jSliderZoom;
    private javax.swing.JSpinner jSpinnerGrosor;
    private javax.swing.JSpinner jSpinnerTransparencia;
    private javax.swing.JSplitPane jSplitPaneCenter;
    private javax.swing.JToggleButton jToggleButtonAntiAliasing;
    private javax.swing.JToggleButton jToggleButtonBorrar;
    private javax.swing.JToggleButton jToggleButtonCirculo;
    private javax.swing.JToggleButton jToggleButtonLinea;
    private javax.swing.JToggleButton jToggleButtonMover;
    private javax.swing.JToggleButton jToggleButtonPintar;
    private javax.swing.JToggleButton jToggleButtonPlay;
    private javax.swing.JToggleButton jToggleButtonPunto;
    private javax.swing.JToggleButton jToggleButtonRecord;
    private javax.swing.JToggleButton jToggleButtonRectangulo;
    private javax.swing.JToggleButton jToggleButtonRelleno;
    private javax.swing.JToggleButton jToggleButtonShow;
    private javax.swing.JToggleButton jToggleButtonStop;
    private javax.swing.JToolBar jToolBarSound;
    private javax.swing.JToolBar jToolBarTools;
    // End of variables declaration//GEN-END:variables
}
