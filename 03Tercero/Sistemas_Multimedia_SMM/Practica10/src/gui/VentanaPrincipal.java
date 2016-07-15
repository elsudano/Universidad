package gui;

import gui.componets.ColorComboBoxEditor;
import gui.componets.ColorComboBoxModel;
import gui.componets.ColorComboBoxRender;
import gui.modal.QuestionsRescaleImage;
import gui.modal.QuestionsResizeImage;
import gui.modal.QuestionsSizeImage;
import gui.others.AcercaDe;
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
import java.io.File;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import sm.cdlt.graphics.myRectangle;
import sm.cdlt.graphics.myRectangleShape;
import sm.cdlt.util.Utils;
import sm.image.LookupTableProducer;

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
     * Variable que permite Deshacer y Rehacer una acción.
     */
    public static BufferedImage UNDO, REDO;
    /**
     * Variable que se usa para poder acceder a las traducciones.
     */
    protected static ResourceBundle bundle;
    /**
     * Variable que muestra la ventana activa.
     */
    private VentanaInterna vi;
    /**
     * Variable que se encarga de almacenar una version original de la imagen
     * que estamos modificando para poder realizar un deshacer, y tambien la
     * temporal donde hacemos las modificaciones.
     */
    private BufferedImage imgOrigen, imgTemp;

    /**
     * Creates new form PaintBasico
     */
    public VentanaPrincipal() {
        initComponents();
        myInitComponents();
    }

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
        Color aux_color = this.vi.getLienzo().getColor();
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
     * Este método nos sirve para poder acceder al Escritorio y tener acceso a
     * las ventanas internas por si queremos manupularlas desde otros sitios.
     *
     * @return Devuelve un objeto JDesktopPane con todas las ventanas internas.
     */
    public JDesktopPane getEscritorio() {
        return jDesktopEscritorio;
    }

    /**
     * Con este método lo que conseguimos poner o quitar el menu deshacer
     * operativo para que se pueda llevar a cabo la operación.
     *
     * @param b Si es verdadero se activa la opción deshacer, en caso contrario
     * se desactiva.
     */
    public void setEnabledUndo(boolean b) {
        this.jMenuItemDeshacer.setEnabled(b);
    }

    public JToggleButton getToggleButtonRelleno() {
        return this.jToggleButtonRelleno;
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
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jLabelImageGrosor = new javax.swing.JLabel();
        jSpinnerGrosor = new javax.swing.JSpinner();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jLabelTransparente = new javax.swing.JLabel();
        jSpinnerTransparencia = new javax.swing.JSpinner();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        jToggleButtonRelleno = new javax.swing.JToggleButton();
        jToggleButtonAntiAliasing = new javax.swing.JToggleButton();
        jButtonSalir = new javax.swing.JButton();
        jPanelStatusBar = new javax.swing.JPanel();
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jLabelHerramienta = new javax.swing.JLabel();
        jLabelColor = new javax.swing.JLabel();
        jLabelAccionRaton = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabelMousePos = new javax.swing.JLabel();
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
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
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
        jMenuOpciones = new javax.swing.JMenu();
        jMenuIdioma = new javax.swing.JMenu();
        jRadioButtonMenuItemES = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItemEN = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItemDE = new javax.swing.JRadioButtonMenuItem();
        jMenuAyuda = new javax.swing.JMenu();
        jMenuItemAyuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("interna/Bundle"); // NOI18N
        setTitle(bundle.getString("APP_TITLE")); // NOI18N
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1024, 768));

        jToolBarTools.setMinimumSize(new java.awt.Dimension(550, 36));
        jToolBarTools.setPreferredSize(new java.awt.Dimension(600, 36));

        jButtonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/nuevo.png"))); // NOI18N
        jButtonNuevo.setToolTipText(bundle.getString("NEW_TT")); // NOI18N
        jButtonNuevo.setBorder(null);
        jButtonNuevo.setFocusable(false);
        jButtonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoActionPerformed(evt);
            }
        });
        jToolBarTools.add(jButtonNuevo);

        jButtonAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/abrir.png"))); // NOI18N
        jButtonAbrir.setToolTipText(bundle.getString("OPEN_TT")); // NOI18N
        jButtonAbrir.setBorder(null);
        jButtonAbrir.setFocusable(false);
        jButtonAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbrirActionPerformed(evt);
            }
        });
        jToolBarTools.add(jButtonAbrir);

        jButtonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/guardar.png"))); // NOI18N
        jButtonGuardar.setToolTipText(bundle.getString("SAVE_TT")); // NOI18N
        jButtonGuardar.setBorder(null);
        jButtonGuardar.setFocusable(false);
        jButtonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });
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
        jToggleButtonPintar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonPintarActionPerformed(evt);
            }
        });
        jToolBarTools.add(jToggleButtonPintar);

        buttonGroupOpciones.add(jToggleButtonMover);
        jToggleButtonMover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/mover.png"))); // NOI18N
        jToggleButtonMover.setToolTipText(bundle.getString("MOVE_TT")); // NOI18N
        jToggleButtonMover.setBorder(null);
        jToggleButtonMover.setFocusable(false);
        jToggleButtonMover.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonMover.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonMover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonMoverActionPerformed(evt);
            }
        });
        jToolBarTools.add(jToggleButtonMover);

        buttonGroupOpciones.add(jToggleButtonBorrar);
        jToggleButtonBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/borrar.png"))); // NOI18N
        jToggleButtonBorrar.setToolTipText(bundle.getString("DELETE_TT")); // NOI18N
        jToggleButtonBorrar.setBorder(null);
        jToggleButtonBorrar.setFocusable(false);
        jToggleButtonBorrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonBorrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonBorrarActionPerformed(evt);
            }
        });
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
        jToggleButtonPunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonPuntoActionPerformed(evt);
            }
        });
        jToolBarTools.add(jToggleButtonPunto);

        buttonGroupTools.add(jToggleButtonLinea);
        jToggleButtonLinea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/linea.png"))); // NOI18N
        jToggleButtonLinea.setToolTipText(bundle.getString("LINE_TT")); // NOI18N
        jToggleButtonLinea.setBorder(null);
        jToggleButtonLinea.setFocusable(false);
        jToggleButtonLinea.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonLinea.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonLineaActionPerformed(evt);
            }
        });
        jToolBarTools.add(jToggleButtonLinea);

        buttonGroupTools.add(jToggleButtonRectangulo);
        jToggleButtonRectangulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rectangulo.png"))); // NOI18N
        jToggleButtonRectangulo.setToolTipText(bundle.getString("RECT_TT")); // NOI18N
        jToggleButtonRectangulo.setBorder(null);
        jToggleButtonRectangulo.setFocusable(false);
        jToggleButtonRectangulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonRectangulo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonRectangulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonRectanguloActionPerformed(evt);
            }
        });
        jToolBarTools.add(jToggleButtonRectangulo);

        buttonGroupTools.add(jToggleButtonCirculo);
        jToggleButtonCirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/elipse.png"))); // NOI18N
        jToggleButtonCirculo.setToolTipText(bundle.getString("CIRCLE_TT")); // NOI18N
        jToggleButtonCirculo.setBorder(null);
        jToggleButtonCirculo.setFocusable(false);
        jToggleButtonCirculo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonCirculo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonCirculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonCirculoActionPerformed(evt);
            }
        });
        jToolBarTools.add(jToggleButtonCirculo);
        jToolBarTools.add(jSeparator5);

        jLabelImageColor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelImageColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/color2.png"))); // NOI18N
        jToolBarTools.add(jLabelImageColor);

        jComboBoxColor.setModel(new ColorComboBoxModel());
        jComboBoxColor.setToolTipText(bundle.getString("COLOR_TT")); // NOI18N
        jComboBoxColor.setBorder(null);
        jComboBoxColor.setEditor(new ColorComboBoxEditor());
        jComboBoxColor.setMaximumSize(new java.awt.Dimension(60, 28));
        jComboBoxColor.setMinimumSize(new java.awt.Dimension(28, 28));
        jComboBoxColor.setPreferredSize(new java.awt.Dimension(50, 28));
        jComboBoxColor.setRenderer(new ColorComboBoxRender());
        jComboBoxColor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxColorItemStateChanged(evt);
            }
        });
        jToolBarTools.add(jComboBoxColor);
        jToolBarTools.add(jSeparator6);

        jLabelImageGrosor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelImageGrosor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/grosor.png"))); // NOI18N
        jToolBarTools.add(jLabelImageGrosor);

        jSpinnerGrosor.setToolTipText(bundle.getString("STROKE_TT")); // NOI18N
        jSpinnerGrosor.setMaximumSize(new java.awt.Dimension(60, 28));
        jSpinnerGrosor.setMinimumSize(new java.awt.Dimension(28, 28));
        jSpinnerGrosor.setPreferredSize(new java.awt.Dimension(50, 28));
        jSpinnerGrosor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerGrosorStateChanged(evt);
            }
        });
        jSpinnerGrosor.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jSpinnerGrosorMouseWheelMoved(evt);
            }
        });
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
        jSpinnerTransparencia.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerTransparenciaStateChanged(evt);
            }
        });
        jSpinnerTransparencia.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jSpinnerTransparenciaMouseWheelMoved(evt);
            }
        });
        jToolBarTools.add(jSpinnerTransparencia);
        jToolBarTools.add(jSeparator8);

        jToggleButtonRelleno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/relleno.png"))); // NOI18N
        jToggleButtonRelleno.setToolTipText(bundle.getString("FILL_TT")); // NOI18N
        jToggleButtonRelleno.setBorder(null);
        jToggleButtonRelleno.setFocusable(false);
        jToggleButtonRelleno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonRelleno.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonRelleno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonRellenoActionPerformed(evt);
            }
        });
        jToolBarTools.add(jToggleButtonRelleno);

        jToggleButtonAntiAliasing.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/antialiasing.png"))); // NOI18N
        jToggleButtonAntiAliasing.setToolTipText(bundle.getString("ANTIALIASING_TT")); // NOI18N
        jToggleButtonAntiAliasing.setBorder(null);
        jToggleButtonAntiAliasing.setFocusable(false);
        jToggleButtonAntiAliasing.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jToggleButtonAntiAliasing.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonAntiAliasing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonAntiAliasingActionPerformed(evt);
            }
        });
        jToolBarTools.add(jToggleButtonAntiAliasing);

        jButtonSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/salir.png"))); // NOI18N
        jButtonSalir.setToolTipText(bundle.getString("EXIT")); // NOI18N
        jButtonSalir.setBorder(null);
        jButtonSalir.setFocusable(false);
        jButtonSalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        jToolBarTools.add(jButtonSalir);

        getContentPane().add(jToolBarTools, java.awt.BorderLayout.NORTH);

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
        jLabelMousePos.setMaximumSize(new java.awt.Dimension(220, 25));
        jLabelMousePos.setMinimumSize(new java.awt.Dimension(200, 25));
        jLabelMousePos.setPreferredSize(new java.awt.Dimension(200, 25));
        jPanelStatusBar.add(jLabelMousePos);

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
        jSliderZoom.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderZoomStateChanged(evt);
            }
        });
        jSliderZoom.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jSliderZoomFocusLost(evt);
            }
        });
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
        jSliderRotate.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderRotateStateChanged(evt);
            }
        });
        jSliderRotate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jSliderRotateFocusLost(evt);
            }
        });
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
        jSliderBrigthness.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderBrigthnessStateChanged(evt);
            }
        });
        jSliderBrigthness.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jSliderBrigthnessFocusLost(evt);
            }
        });
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
        jSliderContrast.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderContrastStateChanged(evt);
            }
        });
        jSliderContrast.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jSliderContrastFocusLost(evt);
            }
        });
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
        jComboBoxFiltros.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxFiltrosItemStateChanged(evt);
            }
        });
        jComboBoxFiltros.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jComboBoxFiltrosFocusLost(evt);
            }
        });
        jPanelSplitDerecho.add(jComboBoxFiltros);

        jSplitPaneCenter.setLeftComponent(jPanelSplitDerecho);

        jDesktopEscritorio.setPreferredSize(new java.awt.Dimension(650, 0));
        jSplitPaneCenter.setRightComponent(jDesktopEscritorio);

        getContentPane().add(jSplitPaneCenter, java.awt.BorderLayout.CENTER);

        jMenuBarra.setName("jMenuBarra"); // NOI18N

        jMenuArchivo.setText(bundle.getString("ARCHIVO")); // NOI18N

        jMenuItemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/nuevo.png"))); // NOI18N
        jMenuItemNuevo.setText(bundle.getString("NUEVO")); // NOI18N
        jMenuItemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNuevoActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemNuevo);

        jMenuItemAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/abrir.png"))); // NOI18N
        jMenuItemAbrir.setText(bundle.getString("OPEN")); // NOI18N
        jMenuItemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAbrirActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemAbrir);

        jMenuItemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/guardar.png"))); // NOI18N
        jMenuItemGuardar.setText(bundle.getString("SAVE")); // NOI18N
        jMenuItemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemGuardarActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemGuardar);

        jSeparator2.setMinimumSize(new java.awt.Dimension(10, 10));
        jSeparator2.setName("jSeparator2"); // NOI18N
        jMenuArchivo.add(jSeparator2);

        jMenuItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/salir.png"))); // NOI18N
        jMenuItemSalir.setText(bundle.getString("EXIT")); // NOI18N
        jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalirActionPerformed(evt);
            }
        });
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
        jMenuItemDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDeshacerActionPerformed(evt);
            }
        });
        jMenuEditar.add(jMenuItemDeshacer);

        jMenuItemRehacer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRehacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rehacer.png"))); // NOI18N
        jMenuItemRehacer.setText(bundle.getString("REDO")); // NOI18N
        jMenuItemRehacer.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rehacer.png"))); // NOI18N
        jMenuItemRehacer.setEnabled(false);
        jMenuItemRehacer.setName("jMenuItemRehacer"); // NOI18N
        jMenuItemRehacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRehacerActionPerformed(evt);
            }
        });
        jMenuEditar.add(jMenuItemRehacer);

        jSeparator11.setName("jSeparator11"); // NOI18N
        jMenuEditar.add(jSeparator11);

        jMenuItemCortar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCortar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/cortar.png"))); // NOI18N
        jMenuItemCortar.setText(bundle.getString("CUT")); // NOI18N
        jMenuItemCortar.setName("jMenuItemCortar"); // NOI18N
        jMenuItemCortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCortarActionPerformed(evt);
            }
        });
        jMenuEditar.add(jMenuItemCortar);

        jMenuItemCopiar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemCopiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/copiar.png"))); // NOI18N
        jMenuItemCopiar.setText(bundle.getString("COPY")); // NOI18N
        jMenuItemCopiar.setName("jMenuItemCopiar"); // NOI18N
        jMenuItemCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCopiarActionPerformed(evt);
            }
        });
        jMenuEditar.add(jMenuItemCopiar);

        jMenuItemPegar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemPegar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/pegar.png"))); // NOI18N
        jMenuItemPegar.setText(bundle.getString("PASTE")); // NOI18N
        jMenuItemPegar.setName("jMenuItemPegar"); // NOI18N
        jMenuItemPegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemPegarActionPerformed(evt);
            }
        });
        jMenuEditar.add(jMenuItemPegar);

        jMenuBarra.add(jMenuEditar);

        jMenuVer.setText(bundle.getString("VIEW")); // NOI18N
        jMenuVer.setName("jMenuVer"); // NOI18N

        jCheckBoxMenuItemVerBarraHerramientas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItemVerBarraHerramientas.setSelected(true);
        jCheckBoxMenuItemVerBarraHerramientas.setText(bundle.getString("TOOL_BAR_TEXT")); // NOI18N
        jCheckBoxMenuItemVerBarraHerramientas.setName("jCheckBoxMenuItemVerBarraHerramientas"); // NOI18N
        jCheckBoxMenuItemVerBarraHerramientas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemVerBarraHerramientasActionPerformed(evt);
            }
        });
        jMenuVer.add(jCheckBoxMenuItemVerBarraHerramientas);

        jCheckBoxMenuItemVerBarraUtilidades.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItemVerBarraUtilidades.setSelected(true);
        jCheckBoxMenuItemVerBarraUtilidades.setText(bundle.getString("UTIL_BAR_TEXT")); // NOI18N
        jCheckBoxMenuItemVerBarraUtilidades.setName("jCheckBoxMenuItemVerBarraUtilidades"); // NOI18N
        jCheckBoxMenuItemVerBarraUtilidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemVerBarraUtilidadesActionPerformed(evt);
            }
        });
        jMenuVer.add(jCheckBoxMenuItemVerBarraUtilidades);

        jCheckBoxMenuItemVerBarraEstado.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItemVerBarraEstado.setSelected(true);
        jCheckBoxMenuItemVerBarraEstado.setText(bundle.getString("STATUS_BAT_TEXT")); // NOI18N
        jCheckBoxMenuItemVerBarraEstado.setName("jCheckBoxMenuItemVerBarraEstado"); // NOI18N
        jCheckBoxMenuItemVerBarraEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemVerBarraEstadoActionPerformed(evt);
            }
        });
        jMenuVer.add(jCheckBoxMenuItemVerBarraEstado);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jMenuVer.add(jSeparator1);

        jCheckBoxMenuItemPantallaCompleta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jCheckBoxMenuItemPantallaCompleta.setText(bundle.getString("FULL_SCREEN")); // NOI18N
        jCheckBoxMenuItemPantallaCompleta.setName("jCheckBoxMenuItemPantallaCompleta"); // NOI18N
        jCheckBoxMenuItemPantallaCompleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMenuItemPantallaCompletaActionPerformed(evt);
            }
        });
        jMenuVer.add(jCheckBoxMenuItemPantallaCompleta);

        jMenuBarra.add(jMenuVer);

        jMenuImagen.setText(bundle.getString("IMAGE")); // NOI18N
        jMenuImagen.setName("jMenuImagen"); // NOI18N

        jMIBrightnessContrast.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMIBrightnessContrast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/brillo1.png"))); // NOI18N
        jMIBrightnessContrast.setText(bundle.getString("BRIGHTNESS_CONTRAST")); // NOI18N
        jMIBrightnessContrast.setName("jMIBrightnessContrast"); // NOI18N
        jMIBrightnessContrast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIBrightnessContrastActionPerformed(evt);
            }
        });
        jMenuImagen.add(jMIBrightnessContrast);

        jSeparator9.setName("jSeparator9"); // NOI18N
        jMenuImagen.add(jSeparator9);

        jMenuItemScaleImage.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemScaleImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/reescalar.png"))); // NOI18N
        jMenuItemScaleImage.setText(bundle.getString("SCALE_IMAGE")); // NOI18N
        jMenuItemScaleImage.setName("jMenuItemScaleImage"); // NOI18N
        jMenuItemScaleImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemScaleImageActionPerformed(evt);
            }
        });
        jMenuImagen.add(jMenuItemScaleImage);

        jMenuItemResizeImage.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemResizeImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/redimensionar.png"))); // NOI18N
        jMenuItemResizeImage.setText(bundle.getString("RESIZE_IMAGE")); // NOI18N
        jMenuItemResizeImage.setName("jMenuItemResizeImage"); // NOI18N
        jMenuItemResizeImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemResizeImageActionPerformed(evt);
            }
        });
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
        jMenuItemCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCustomActionPerformed(evt);
            }
        });
        jMenuRotate.add(jMenuItemCustom);

        jMenuItemRotateLeft.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRotateLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rotar_izq.png"))); // NOI18N
        jMenuItemRotateLeft.setText(bundle.getString("ROTATE_LEFT")); // NOI18N
        jMenuItemRotateLeft.setName("jMenuItemRotateLeft"); // NOI18N
        jMenuItemRotateLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRotateLeftActionPerformed(evt);
            }
        });
        jMenuRotate.add(jMenuItemRotateLeft);

        jMenuItemRotateRight.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRotateRight.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rotar_der.png"))); // NOI18N
        jMenuItemRotateRight.setText(bundle.getString("ROTATE_RIGHT")); // NOI18N
        jMenuItemRotateRight.setName("jMenuItemRotateRight"); // NOI18N
        jMenuItemRotateRight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRotateRightActionPerformed(evt);
            }
        });
        jMenuRotate.add(jMenuItemRotateRight);

        jMenuItemRotate180.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRotate180.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rotar180.png"))); // NOI18N
        jMenuItemRotate180.setText(bundle.getString("ROTATE_180")); // NOI18N
        jMenuItemRotate180.setName("jMenuItemRotate180"); // NOI18N
        jMenuItemRotate180.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRotate180ActionPerformed(evt);
            }
        });
        jMenuRotate.add(jMenuItemRotate180);

        jMenuImagen.add(jMenuRotate);

        jMenuItemClone.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemClone.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/duplicar.png"))); // NOI18N
        jMenuItemClone.setText(bundle.getString("CLONE")); // NOI18N
        jMenuItemClone.setName("jMenuItemClone"); // NOI18N
        jMenuItemClone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCloneActionPerformed(evt);
            }
        });
        jMenuImagen.add(jMenuItemClone);

        jMenuItemSenoFunction.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSenoFunction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/seno.png"))); // NOI18N
        jMenuItemSenoFunction.setText(bundle.getString("SENOFUNCTION")); // NOI18N
        jMenuItemSenoFunction.setName("jMenuItemSenoFunction"); // NOI18N
        jMenuItemSenoFunction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSenoFunctionActionPerformed(evt);
            }
        });
        jMenuImagen.add(jMenuItemSenoFunction);

        jSeparator12.setName("jSeparator12"); // NOI18N
        jMenuImagen.add(jSeparator12);

        jMenuItemRescaleOP.setText("RescaleOP");
        jMenuItemRescaleOP.setName("jMenuItemRescaleOP"); // NOI18N
        jMenuItemRescaleOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRescaleOPActionPerformed(evt);
            }
        });
        jMenuImagen.add(jMenuItemRescaleOP);

        jMenuItemConvolveOP.setText("ConvolveOP");
        jMenuItemConvolveOP.setName("jMenuItemConvolveOP"); // NOI18N
        jMenuItemConvolveOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConvolveOPActionPerformed(evt);
            }
        });
        jMenuImagen.add(jMenuItemConvolveOP);

        jMenuItemAffineTransformOp.setText("AffineTransformOp");
        jMenuItemAffineTransformOp.setName("jMenuItemAffineTransformOp"); // NOI18N
        jMenuItemAffineTransformOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAffineTransformOpActionPerformed(evt);
            }
        });
        jMenuImagen.add(jMenuItemAffineTransformOp);

        jMenuItemLookupOp.setText("LookupOp");
        jMenuItemLookupOp.setName("jMenuItemLookupOp"); // NOI18N
        jMenuItemLookupOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemLookupOpActionPerformed(evt);
            }
        });
        jMenuImagen.add(jMenuItemLookupOp);

        jMenuItemBandCombineOp.setText("BandCombineOp");
        jMenuItemBandCombineOp.setName("jMenuItemBandCombineOp"); // NOI18N
        jMenuItemBandCombineOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBandCombineOpActionPerformed(evt);
            }
        });
        jMenuImagen.add(jMenuItemBandCombineOp);

        jMenuItemColorConvertOp.setText("ColorConvertOp");
        jMenuItemColorConvertOp.setName("jMenuItemColorConvertOp"); // NOI18N
        jMenuItemColorConvertOp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemColorConvertOpActionPerformed(evt);
            }
        });
        jMenuImagen.add(jMenuItemColorConvertOp);

        jMenuBarra.add(jMenuImagen);

        jMenuFilters.setText(bundle.getString("FILTERS")); // NOI18N
        jMenuFilters.setName("jMenuFilters"); // NOI18N

        jMenuItemBlurMedio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemBlurMedio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/blur.png"))); // NOI18N
        jMenuItemBlurMedio.setText(bundle.getString("BLUR_MEDIO")); // NOI18N
        jMenuItemBlurMedio.setName("jMenuItemBlurMedio"); // NOI18N
        jMenuItemBlurMedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBlurMedioActionPerformed(evt);
            }
        });
        jMenuFilters.add(jMenuItemBlurMedio);

        jMenuItemBlurBinomial.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemBlurBinomial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/blur2.png"))); // NOI18N
        jMenuItemBlurBinomial.setText(bundle.getString("BLUR_BINOMIAL")); // NOI18N
        jMenuItemBlurBinomial.setName("jMenuItemBlurBinomial"); // NOI18N
        jMenuItemBlurBinomial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBlurBinomialActionPerformed(evt);
            }
        });
        jMenuFilters.add(jMenuItemBlurBinomial);

        jMenuItemFocus.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemFocus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/enfoque.png"))); // NOI18N
        jMenuItemFocus.setText(bundle.getString("FOCUS")); // NOI18N
        jMenuItemFocus.setName("jMenuItemFocus"); // NOI18N
        jMenuItemFocus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFocusActionPerformed(evt);
            }
        });
        jMenuFilters.add(jMenuItemFocus);

        jMenuItemRelief.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemRelief.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/relieve.png"))); // NOI18N
        jMenuItemRelief.setText(bundle.getString("RELIEF")); // NOI18N
        jMenuItemRelief.setName("jMenuItemRelief"); // NOI18N
        jMenuItemRelief.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReliefActionPerformed(evt);
            }
        });
        jMenuFilters.add(jMenuItemRelief);

        jMenuItemBorder.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemBorder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/realzar.png"))); // NOI18N
        jMenuItemBorder.setText(bundle.getString("FRONTIER_DETECT")); // NOI18N
        jMenuItemBorder.setName("jMenuItemBorder"); // NOI18N
        jMenuItemBorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBorderActionPerformed(evt);
            }
        });
        jMenuFilters.add(jMenuItemBorder);

        jMenuItemEmboss.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemEmboss.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/emboss.png"))); // NOI18N
        jMenuItemEmboss.setText(bundle.getString("EMBOSS")); // NOI18N
        jMenuItemEmboss.setName("jMenuItemEmboss"); // NOI18N
        jMenuItemEmboss.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEmbossActionPerformed(evt);
            }
        });
        jMenuFilters.add(jMenuItemEmboss);

        jMenuItemSharpens.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSharpens.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/sharpen.png"))); // NOI18N
        jMenuItemSharpens.setText(bundle.getString("SHARPENS")); // NOI18N
        jMenuItemSharpens.setName("jMenuItemSharpens"); // NOI18N
        jMenuItemSharpens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSharpensActionPerformed(evt);
            }
        });
        jMenuFilters.add(jMenuItemSharpens);

        jMenuItemFrontier.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemFrontier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/frontera.png"))); // NOI18N
        jMenuItemFrontier.setText(bundle.getString("FRONTIER")); // NOI18N
        jMenuItemFrontier.setName("jMenuItemFrontier"); // NOI18N
        jMenuItemFrontier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFrontierActionPerformed(evt);
            }
        });
        jMenuFilters.add(jMenuItemFrontier);

        jMenuItemSuavizado.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSuavizado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/suavizado.png"))); // NOI18N
        jMenuItemSuavizado.setText(bundle.getString("GAUSSIANO")); // NOI18N
        jMenuItemSuavizado.setName("jMenuItemSuavizado"); // NOI18N
        jMenuItemSuavizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSuavizadoActionPerformed(evt);
            }
        });
        jMenuFilters.add(jMenuItemSuavizado);

        jMenuItemNegative.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemNegative.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/negativo.png"))); // NOI18N
        jMenuItemNegative.setText(bundle.getString("NEGATIVO")); // NOI18N
        jMenuItemNegative.setName("jMenuItemNegative"); // NOI18N
        jMenuItemNegative.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNegativeActionPerformed(evt);
            }
        });
        jMenuFilters.add(jMenuItemNegative);

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
        jRadioButtonMenuItemES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItemESActionPerformed(evt);
            }
        });
        jMenuIdioma.add(jRadioButtonMenuItemES);

        jRadioButtonMenuItemEN.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        buttonGroupIdioma.add(jRadioButtonMenuItemEN);
        jRadioButtonMenuItemEN.setText(bundle.getString("ENGLISH")); // NOI18N
        jRadioButtonMenuItemEN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/uk.png"))); // NOI18N
        jRadioButtonMenuItemEN.setName("jRadioButtonMenuItemEN"); // NOI18N
        jRadioButtonMenuItemEN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItemENActionPerformed(evt);
            }
        });
        jMenuIdioma.add(jRadioButtonMenuItemEN);

        jRadioButtonMenuItemDE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        jRadioButtonMenuItemDE.setText(bundle.getString("GERMANY")); // NOI18N
        jRadioButtonMenuItemDE.setName("jRadioButtonMenuItemDE"); // NOI18N
        jRadioButtonMenuItemDE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMenuItemDEActionPerformed(evt);
            }
        });
        jMenuIdioma.add(jRadioButtonMenuItemDE);

        jMenuOpciones.add(jMenuIdioma);

        jMenuBarra.add(jMenuOpciones);

        jMenuAyuda.setText(bundle.getString("HELP")); // NOI18N
        jMenuAyuda.setName("jMenuAyuda"); // NOI18N

        jMenuItemAyuda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/acerca.png"))); // NOI18N
        jMenuItemAyuda.setText(bundle.getString("ABOUT")); // NOI18N
        jMenuItemAyuda.setName("jMenuItemAyuda"); // NOI18N
        jMenuItemAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAyudaActionPerformed(evt);
            }
        });
        jMenuAyuda.add(jMenuItemAyuda);

        jMenuBarra.add(jMenuAyuda);

        setJMenuBar(jMenuBarra);

        pack();
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
        // colocamos la aplicación en el centro
        this.setLocation(Utils.centerOfScreen(this.getWidth(), this.getHeight()));
        // pongo el idioma de la interfaz
        VentanaPrincipal.bundle = ResourceBundle.getBundle("interna/Bundle");
    }// </editor-fold>/ /GEN-END:myInitComponents

    private void jMenuItemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNuevoActionPerformed
        QuestionsSizeImage question = new QuestionsSizeImage(this, true);
        question.setDescriptionDialog("Por favor indique el tamaño de la imagen");
        question.setTextButtonOk("Crear");
        question.setVisible(true);
    }//GEN-LAST:event_jMenuItemNuevoActionPerformed

    private void jMenuItemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAbrirActionPerformed
        VentanaInterna ultima_ventana = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        FileFilter jpg_filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
        FileFilter png_filter = new FileNameExtensionFilter("PNG file", "png");
        FileFilter gif_filter = new FileNameExtensionFilter("GIF file", "gif");
        JFileChooser dlg = new JFileChooser();
        dlg.addChoosableFileFilter(jpg_filter);
        dlg.addChoosableFileFilter(png_filter);
        dlg.addChoosableFileFilter(gif_filter);
        dlg.setAcceptAllFileFilterUsed(false);
        int resp = dlg.showOpenDialog(this);
        if (resp == JFileChooser.APPROVE_OPTION) {
            try {
                File f = dlg.getSelectedFile();
                BufferedImage img = ImageIO.read(f);
                this.vi = new VentanaInterna(this, new Dimension(img.getWidth(), img.getHeight()), img.getType());
                if (ultima_ventana != null) {
                    this.vi.setLocation(ultima_ventana.getLocation().x + 15, ultima_ventana.getLocation().y + 15);
                } else {
                    this.vi.setLocation(15, 15);
                }
                this.vi.getLienzo().setImage(img);
                this.getEscritorio().add(vi);
                this.vi.setTitle(f.getName());
                this.vi.setVisible(true);
            } catch (Exception ex) {
                System.err.println("Error al leer la imagen" + ex);
            }
        }

    }//GEN-LAST:event_jMenuItemAbrirActionPerformed

    private void jMenuItemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGuardarActionPerformed
        int n = JOptionPane.showConfirmDialog(this, "Se perderan todas las formas del Lienzo\ny se guardara la imagen con las formas.", "¿Esta seguro?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null && n == 0) {
            FileFilter jpg_filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
            FileFilter png_filter = new FileNameExtensionFilter("PNG file", "png");
            FileFilter gif_filter = new FileNameExtensionFilter("GIF file", "gif");
            JFileChooser dlg = new JFileChooser();
            int type = this.vi.getLienzo().getImage().getType();
            switch (type) {
                case BufferedImage.TYPE_INT_RGB:
                    dlg.addChoosableFileFilter(jpg_filter);
                    dlg.addChoosableFileFilter(png_filter);
                    dlg.setAcceptAllFileFilterUsed(false);
                    break;
                case BufferedImage.TYPE_INT_ARGB:
                    dlg.addChoosableFileFilter(png_filter);
                    dlg.setAcceptAllFileFilterUsed(false);
                    break;
                case BufferedImage.TYPE_BYTE_INDEXED:
                    dlg.addChoosableFileFilter(gif_filter);
                    dlg.setAcceptAllFileFilterUsed(false);
                    break;
                default:
                    dlg.addChoosableFileFilter(jpg_filter);
                    dlg.addChoosableFileFilter(png_filter);
                    dlg.addChoosableFileFilter(gif_filter);
                    dlg.setAcceptAllFileFilterUsed(true);
                    break;
            }
            int resp = dlg.showSaveDialog(this);
            if (resp == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedImage img = vi.getLienzo().getImage(true);
                    vi.getLienzo().clean();
                    if (img != null) {
                        File f = dlg.getSelectedFile();
                        if (dlg.getFileFilter().equals(jpg_filter)) {
                            ImageIO.write(img, "jpg", f);
                        } else if (dlg.getFileFilter().equals(png_filter)) {
                            ImageIO.write(img, "png", f);
                        } else if (dlg.getFileFilter().equals(gif_filter)) {
                            ImageIO.write(img, "gif", f);
                        }
                        this.vi.setTitle(f.getName());
                    }
                } catch (Exception ex) {
                    System.err.println("Error al guardar la imagen" + ex.getLocalizedMessage());
                }
            }

        }
    }//GEN-LAST:event_jMenuItemGuardarActionPerformed

    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemSalirActionPerformed

    private void jMenuItemDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDeshacerActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (VentanaPrincipal.UNDO != null && this.vi != null) {
            VentanaPrincipal.REDO = this.vi.getLienzo().getImage();
            this.vi.getLienzo().setImage(VentanaPrincipal.UNDO);
            myRectangleShape border_aux = (myRectangle) this.vi.getLienzo().getVisiblePart();
            border_aux.setWidth(VentanaPrincipal.UNDO.getWidth());
            border_aux.setHeigth(VentanaPrincipal.UNDO.getHeight());
            VentanaPrincipal.UNDO = null;
        }
        this.jMenuItemDeshacer.setEnabled(false);
        this.jMenuItemRehacer.setEnabled(true);
    }//GEN-LAST:event_jMenuItemDeshacerActionPerformed

    private void jMenuItemRehacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRehacerActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (VentanaPrincipal.REDO != null && this.vi != null) {
            VentanaPrincipal.UNDO = this.vi.getLienzo().getImage();
            this.vi.getLienzo().setImage(VentanaPrincipal.REDO);
            myRectangleShape border_aux = (myRectangle) this.vi.getLienzo().getVisiblePart();
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

    private void jCheckBoxMenuItemPantallaCompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemPantallaCompletaActionPerformed
        this.jCheckBoxMenuItemVerBarraUtilidades.setSelected(!this.jCheckBoxMenuItemVerBarraUtilidades.isSelected());
        this.jCheckBoxMenuItemVerBarraHerramientas.setSelected(!this.jCheckBoxMenuItemVerBarraHerramientas.isSelected());
        this.jCheckBoxMenuItemVerBarraEstado.setSelected(!this.jCheckBoxMenuItemVerBarraEstado.isSelected());
        this.jPanelSplitDerecho.setVisible(this.jCheckBoxMenuItemVerBarraUtilidades.isSelected());
        this.jToolBarTools.setVisible(this.jCheckBoxMenuItemVerBarraHerramientas.isSelected());
        this.jPanelStatusBar.setVisible(this.jCheckBoxMenuItemVerBarraEstado.isSelected());
        if (this.getExtendedState() == javax.swing.JFrame.NORMAL) {
            this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
            this.setUndecorated(true);
        } else {
            this.setExtendedState(javax.swing.JFrame.NORMAL);
            this.setUndecorated(false);
        }
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
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            BufferedImage img_aux = this.vi.getLienzo().getImage();
            // para el deshacer
            VentanaPrincipal.UNDO = img_aux;
            this.setEnabledUndo(true);
            if (img_aux != null) {
                AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(270.0), img_aux.getWidth() / 2, img_aux.getHeight() / 2); // rotate
                try {
                    // @TODO Esta creada la imagen nueva pero no se guarda el tamaño que yo quiero al rotar
                    AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage img_out = new BufferedImage(img_aux.getHeight(), img_aux.getWidth(), BufferedImage.TYPE_INT_ARGB);
                    atop.filter(img_aux, img_out);
                    this.vi.getLienzo().setImage(img_out);
                    //this.vi.getLienzo().setVisiblePart(new Rectangle2D.Double(0, 0, img_out.getWidth(), img_out.getHeight()));
                } catch (Exception e) {
                    System.err.println("Error rotando la imagen a 90º a la izquierda " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemRotateLeftActionPerformed

    private void jMenuItemRotateRightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRotateRightActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            BufferedImage img_aux = this.vi.getLienzo().getImage();
            // para el deshacer
            VentanaPrincipal.UNDO = img_aux;
            this.setEnabledUndo(true);
            if (img_aux != null) {
                AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(90.0), img_aux.getWidth() / 2, img_aux.getHeight() / 2); // rotate
                try {
                    // cuidado con las variables de imagenes
                    AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    BufferedImage img_out = new BufferedImage(img_aux.getHeight(), img_aux.getWidth(), BufferedImage.TYPE_INT_ARGB);
                    atop.filter(img_aux, img_out);
                    this.vi.getLienzo().setImage(img_out);
                    //this.vi.getLienzo().setVisiblePart(new Rectangle2D.Double(0, 0, img_out.getWidth(), img_out.getHeight()));
                } catch (Exception e) {
                    System.err.println("Error rotando la imagen a 90º a la derecha " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemRotateRightActionPerformed

    private void jMenuItemRotate180ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRotate180ActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            BufferedImage img_aux = this.vi.getLienzo().getImage();
            // para el deshacer
            VentanaPrincipal.UNDO = img_aux;
            this.setEnabledUndo(true);
            if (img_aux != null) {
                AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(180.0), img_aux.getWidth() / 2, img_aux.getHeight() / 2); // rotate
                try {
                    AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    img_aux = atop.filter(img_aux, null);
                    this.vi.getLienzo().setImage(img_aux);
                    //this.vi.getLienzo().setVisiblePart(new Rectangle2D.Double(0, 0, img_aux.getWidth(), img_aux.getHeight()));
                } catch (Exception e) {
                    System.err.println("Error rotando la imagen a 180º " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemRotate180ActionPerformed

    private void jMenuItemCloneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCloneActionPerformed
        // @TODO Este metodo se puede hacer mucho mas corto pero lo hago así
        // para que se entienda cuando se lea el código.
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            BufferedImage img_aux = this.vi.getLienzo().getImage();
            VentanaInterna new_vi = new VentanaInterna(this, new Dimension(img_aux.getWidth(), img_aux.getHeight()), img_aux.getType());
            // @TODO tienes que recorrer el array de formas y añadirlas a la nueva ventana
            new_vi.setLocation(this.vi.getLocation().x + 15, this.vi.getLocation().y + 15);
            new_vi.getLienzo().setImage(img_aux);
            this.jDesktopEscritorio.add(new_vi);
            new_vi.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItemCloneActionPerformed

    private void jMenuItemSenoFunctionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSenoFunctionActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            this.vi.getLienzo().setTypeImage(BufferedImage.TYPE_INT_ARGB);
            BufferedImage img_aux = this.vi.getLienzo().getImage();
            // para el deshacer
            VentanaPrincipal.UNDO = img_aux;
            this.setEnabledUndo(true);
            if (img_aux != null) {
                try {
                    LookupTable seno = Utils.senoFuction(180.0f / 255.0f);
                    LookupOp ltop = new LookupOp(seno, null);
                    this.vi.getLienzo().setImage(ltop.filter(img_aux, null));
                } catch (Exception e) {
                    System.err.println("Error aplicando la función seno " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemSenoFunctionActionPerformed

    private void jMenuItemRescaleOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRescaleOPActionPerformed
        /*
        @TODO
        con este método vamos a subir el brillo de una imagen que tenga
        transparencia, tenemos que hacer un cuadro de dialogo para guardar
        la imagen el cual pregunte si queremos la imagen con transparencia o no
        se hace con un extends de la clase del cuadro de dialogo.
        Usamos la funcion de conversión para las imagenes GIF
         */
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
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
            this.imgOrigen = this.vi.getLienzo().getImage();
            // para el deshacer
            VentanaPrincipal.UNDO = this.imgOrigen;
            this.setEnabledUndo(true);
            this.imgOrigen = Utils.convertImageToType(this.imgOrigen, BufferedImage.TYPE_INT_ARGB);
            if (this.imgOrigen != null) {
                try {
                    // multiplicamos por 1 R,G y B se quedan igual
                    float[] alfas = {1.0F, 1.0F, 1.0F, 1.0F};
                    // sumamos 100 a R, G y B le subimos el brillo
                    float[] betas = {100.0F, 100.0F, 100.0F, 0.0F,};
                    // el ultimo componente de los vectores es la transparencia
                    // que la dejamos igual en todos lados.
                    RescaleOp rop = new RescaleOp(alfas, betas, null);
                    this.vi.getLienzo().setImage(rop.filter(this.imgOrigen, null));
                } catch (IllegalArgumentException e) {
                    System.err.println("Error aplicando filtro rescale " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemRescaleOPActionPerformed

    private void jMenuItemConvolveOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConvolveOPActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            BufferedImage img_aux = this.vi.getLienzo().getImage();
            // para el deshacer
            VentanaPrincipal.UNDO = img_aux;
            this.setEnabledUndo(true);
            if (img_aux != null) {
                try {
                    float[] datamask = {1f, 1f, 1f, 1f, -8f, 1f, 1f, 1f, 1f};
                    Kernel mask = new Kernel(3, 3, datamask);
                    ConvolveOp cop = new ConvolveOp(mask, ConvolveOp.EDGE_NO_OP, null);
                    this.vi.getLienzo().setImage(cop.filter(img_aux, null));
                } catch (IllegalArgumentException e) {
                    System.err.println("Error aplicando filtro convolve " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemConvolveOPActionPerformed

    private void jMenuItemAffineTransformOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAffineTransformOpActionPerformed
        AffineTransform at;
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            BufferedImage img_aux = this.vi.getLienzo().getImage();
            // para el deshacer
            VentanaPrincipal.UNDO = img_aux;
            this.setEnabledUndo(true);
            if (img_aux != null) {
                //at = AffineTransform.getScaleInstance(1.25, 1.25); // resize
                //at = AffineTransform.getRotateInstance(Math.toRadians(180.0), img_aux.getWidth() / 2, img_aux.getHeight() / 2); // rotate
                at = AffineTransform.getShearInstance(0.5, 0.0);
                //at = AffineTransform.getTranslateInstance(20.0, 20.0);
                try {
                    AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    img_aux = atop.filter(img_aux, null);
                    this.vi.getLienzo().setImage(img_aux);
                    //this.vi.getLienzo().setVisiblePart(new Rectangle2D.Double(0, 0, img_aux.getWidth(), img_aux.getHeight()));
                } catch (Exception e) {
                    System.err.println("Error aplicando filtro affine " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemAffineTransformOpActionPerformed

    private void jMenuItemLookupOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemLookupOpActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            BufferedImage img_aux = this.vi.getLienzo().getImage();
            // para el deshacer
            VentanaPrincipal.UNDO = img_aux;
            this.setEnabledUndo(true);
            if (img_aux != null) {
                try {
                    short lt[] = new short[256];
                    for (int i = 0; i < 256; i++) {
                        lt[i] = (short) (255 - i); // Negativo
                    }
                    ShortLookupTable slt = new ShortLookupTable(0, lt);
                    LookupOp lop = new LookupOp(slt, null);
                    img_aux = lop.filter(img_aux, null);
                    this.vi.getLienzo().setImage(img_aux);
                    //this.vi.getLienzo().setVisiblePart(new Rectangle2D.Double(0, 0, img_aux.getWidth(), img_aux.getHeight()));
                } catch (Exception e) {
                    System.err.println("Error al aplicar el Lookup " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemLookupOpActionPerformed

    private void jMenuItemBandCombineOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBandCombineOpActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            BufferedImage img_aux = this.vi.getLienzo().getImage();
            // para el deshacer
            VentanaPrincipal.UNDO = img_aux;
            this.setEnabledUndo(true);
            if (img_aux != null) {
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
                    WritableRaster rasterdest = bcop.filter(img_aux.getRaster(), null);
                    this.vi.getLienzo().setImage(new BufferedImage(img_aux.getColorModel(), rasterdest, false, null));
                } catch (Exception e) {
                    System.err.println("Error al aplicar el BandCombine " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemBandCombineOpActionPerformed

    private void jMenuItemColorConvertOpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemColorConvertOpActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            BufferedImage img_aux = this.vi.getLienzo().getImage();
            // para el deshacer
            VentanaPrincipal.UNDO = img_aux;
            this.setEnabledUndo(true);
            if (img_aux != null) {
                try {
                    ICC_Profile ip = ICC_Profile.getInstance(ColorSpace.CS_GRAY);
                    ColorSpace cs = new ICC_ColorSpace(ip);
                    ColorConvertOp ccop = new ColorConvertOp(cs, null);
                    this.vi.getLienzo().setImage(ccop.filter(img_aux, null));
                } catch (Exception e) {
                    System.err.println("Error al aplicar el ColorConvert " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemColorConvertOpActionPerformed

    private void jMenuItemBlurMedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBlurMedioActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            float[] datamask = {0.11f, 0.11f, 0.11f, 0.11f, 0.2f, 0.11f, 0.11f, 0.11f, 0.11f};
            Kernel mask = new Kernel(3, 3, datamask);
            this.applyFilter(mask);
        }
    }//GEN-LAST:event_jMenuItemBlurMedioActionPerformed

    private void jMenuItemBlurBinomialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBlurBinomialActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            float[] datamask = {0.0625f, 0.125f, 0.0625f, 0.125f, 0.25f, 0.125f, 0.0625f, 0.125f, 0.0625f};
            Kernel mask = new Kernel(3, 3, datamask);
            this.applyFilter(mask);
        }
    }//GEN-LAST:event_jMenuItemBlurBinomialActionPerformed

    private void jMenuItemFocusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFocusActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            float[] datamask = {0f, -1f, 0f, -1f, 5f, -1f, 0f, -1f, 0f};
            Kernel mask = new Kernel(3, 3, datamask);
            this.applyFilter(mask);
        }
    }//GEN-LAST:event_jMenuItemFocusActionPerformed

    private void jMenuItemReliefActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReliefActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            float[] datamask = {-2f, -1f, 0f, -1f, 1f, 1f, 0f, 1f, 2f};
            //float[] datamask = KernelProducer.MASCARA_RELIEVE_3x3;
            Kernel mask = new Kernel(3, 3, datamask);
            this.applyFilter(mask);
        }
    }//GEN-LAST:event_jMenuItemReliefActionPerformed

    private void jMenuItemBorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBorderActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            float[] datamask = new float[]{0f, 0f, 0f, -1f, 1f, 0f, 0f, 0f, 0f};
            Kernel mask = new Kernel(3, 3, datamask);
            this.applyFilter(mask);
        }
    }//GEN-LAST:event_jMenuItemBorderActionPerformed

    private void jMenuItemEmbossActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEmbossActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            float[] datamask = new float[]{-2f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 2f};
            Kernel mask = new Kernel(3, 3, datamask);
            this.applyFilter(mask);
        }
    }//GEN-LAST:event_jMenuItemEmbossActionPerformed

    private void jMenuItemSharpensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSharpensActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            float[] datamask = new float[]{-1f, -1f, -1f, -1f, 9f, -1f, -1f, -1f, -1f,};
            Kernel mask = new Kernel(3, 3, datamask);
            this.applyFilter(mask);
        }
    }//GEN-LAST:event_jMenuItemSharpensActionPerformed

    private void jMenuItemFrontierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFrontierActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            float[] datamask = {1f, 1f, 1f, 1f, -8f, 1f, 1f, 1f, 1f};
            Kernel mask = new Kernel(3, 3, datamask);
            this.applyFilter(mask);
        }
    }//GEN-LAST:event_jMenuItemFrontierActionPerformed

    private void jMenuItemSuavizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSuavizadoActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            float[] datamask = {
                0.0030f, 0.0133f, 0.0219f, 0.0133f, 0.0030f,
                0.0133f, 0.0596f, 0.0983f, 0.0596f, 0.0133f,
                0.0219f, 0.0983f, 0.1621f, 0.0983f, 0.0219f,
                0.0133f, 0.0596f, 0.0983f, 0.0596f, 0.0133f,
                0.0030f, 0.0133f, 0.0219f, 0.0133f, 0.0030f
            };
            Kernel mask = new Kernel(5, 5, datamask);
            this.applyFilter(mask);
        }
    }//GEN-LAST:event_jMenuItemSuavizadoActionPerformed

    private void jMenuItemNegativeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNegativeActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            if (this.imgOrigen == null) {
                this.imgOrigen = this.vi.getLienzo().getImage();
                // para el deshacer
                VentanaPrincipal.UNDO = this.imgOrigen;
                this.setEnabledUndo(true);
            }
            if (this.imgOrigen != null) {
                try {
                    LookupTable lt = LookupTableProducer.negativeFuction();
                    LookupOp lop = new LookupOp(lt, null);
                    this.vi.getLienzo().setImage(lop.filter(this.imgOrigen, null));
                } catch (Exception e) {
                    System.err.println("Error al aplicar el Negativo " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemNegativeActionPerformed

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
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        this.jLabelAccionRaton.setText(bundle.getString("MOUSE"));
        this.vi.getLienzo().setPaint();
    }//GEN-LAST:event_jToggleButtonPintarActionPerformed

    private void jToggleButtonMoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonMoverActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        this.jLabelAccionRaton.setText(bundle.getString("MOVE"));
        this.vi.getLienzo().setMove();
    }//GEN-LAST:event_jToggleButtonMoverActionPerformed

    private void jToggleButtonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonBorrarActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        this.jLabelAccionRaton.setText(bundle.getString("DELETE"));
        this.vi.getLienzo().setDelete();
    }//GEN-LAST:event_jToggleButtonBorrarActionPerformed

    private void jToggleButtonPuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonPuntoActionPerformed
        this.jLabelHerramienta.setText(bundle.getString("TOOL_POINT"));
        this.vi.getLienzo().setTool("point"); //NOI18N
    }//GEN-LAST:event_jToggleButtonPuntoActionPerformed

    private void jToggleButtonLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonLineaActionPerformed
        this.jLabelHerramienta.setText(bundle.getString("TOOL_LINE"));
        this.vi.getLienzo().setTool("line"); //NOI18N
    }//GEN-LAST:event_jToggleButtonLineaActionPerformed

    private void jToggleButtonRectanguloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonRectanguloActionPerformed
        this.jLabelHerramienta.setText(bundle.getString("TOOL_RECT"));
        this.vi.getLienzo().setTool("rect"); //NOI18N
    }//GEN-LAST:event_jToggleButtonRectanguloActionPerformed

    private void jToggleButtonCirculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonCirculoActionPerformed
        this.jLabelHerramienta.setText(bundle.getString("TOOL_CIRCLE"));
        this.vi.getLienzo().setTool("oval"); //NOI18N
    }//GEN-LAST:event_jToggleButtonCirculoActionPerformed

    private void jComboBoxColorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxColorItemStateChanged
        Color aux_color = (Color) this.jComboBoxColor.getSelectedItem();
        this.jLabelColor.setText(bundle.getString("COLOR") + "[R: " + aux_color.getRed() + " G: " + aux_color.getGreen() + " B: " + aux_color.getBlue() + "]");
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        this.vi.getLienzo().setColor((Color) this.jComboBoxColor.getSelectedItem());
    }//GEN-LAST:event_jComboBoxColorItemStateChanged

    private void jSpinnerGrosorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerGrosorStateChanged
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            this.vi.getLienzo().setStroke((int) this.jSpinnerGrosor.getValue() * 1f);
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
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            this.vi.getLienzo().setAlpha((int) this.jSpinnerTransparencia.getValue());
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
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        //this.vi.getLienzo().setFill(this.jToggleButtonRelleno.isSelected());
    }//GEN-LAST:event_jToggleButtonRellenoActionPerformed

    private void jToggleButtonAntiAliasingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonAntiAliasingActionPerformed
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        this.vi.getLienzo().setFlatten(this.jToggleButtonAntiAliasing.isSelected());
    }//GEN-LAST:event_jToggleButtonAntiAliasingActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        this.jMenuItemSalirActionPerformed(evt);
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private void jSliderZoomStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderZoomStateChanged
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            double value_slider = this.jSliderZoom.getValue();
            if (this.imgOrigen == null && value_slider != 0) {
                this.vi.getLienzo().setTypeImage(BufferedImage.TYPE_INT_ARGB);
                this.imgOrigen = this.vi.getLienzo().getImage();
                // para el deshacer tambien
                VentanaPrincipal.UNDO = this.imgOrigen;
                this.setEnabledUndo(true);
            }
            if (this.imgOrigen != null && value_slider != 100) {
                value_slider = value_slider / 100.0F;
                AffineTransform at = AffineTransform.getScaleInstance(value_slider, value_slider); // resize
                try {
                    AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    this.imgTemp = atop.filter(this.imgOrigen, null);
                    this.vi.getLienzo().setImage(this.imgTemp);
                    //this.vi.getLienzo().setVisiblePart(new Rectangle2D.Double(0, 0, this.imgTemp.getWidth(), this.imgTemp.getHeight()));
                } catch (Exception e) {
                    System.err.println("Error aplicando el zoom " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jSliderZoomStateChanged

    private void jSliderZoomFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSliderZoomFocusLost
        if (this.jSliderZoom.getValue() != 100) {
            int n = JOptionPane.showConfirmDialog(this, "¿Quiere aplicar los cambios de zoom realizados?", "Realizar Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            // son 2 botones el si=0, el no=1
            if (n == 1 && this.imgOrigen != null) {
                this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
                this.vi.getLienzo().setImage(this.imgOrigen);
            }
            this.jSliderZoom.setValue(100);
            this.imgOrigen = null;
        }
    }//GEN-LAST:event_jSliderZoomFocusLost

    private void jSliderRotateStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderRotateStateChanged
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            double value_slider = this.jSliderRotate.getValue();
            if (this.imgOrigen == null && value_slider != 0) {
                this.vi.getLienzo().setTypeImage(BufferedImage.TYPE_INT_ARGB);
                this.imgOrigen = this.vi.getLienzo().getImage();
                // para el deshacer tambien
                VentanaPrincipal.UNDO = this.imgOrigen;
                this.setEnabledUndo(true);
            }
            if (this.imgOrigen != null && value_slider != 0) {
                AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(this.jSliderRotate.getValue()), this.imgOrigen.getWidth() / 2, this.imgOrigen.getHeight() / 2); // rotate
                try {
                    AffineTransformOp atop = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
                    // calculo cual es el valor maximo del tamaño de la imagen en este caso la Hipotenusa
                    int value_max = (int) Math.hypot(this.imgOrigen.getWidth() + 0.0f, this.imgOrigen.getHeight() + 0.0f);
                    // creo una imagen con ambos lados de la imagen del tamaño máximo
                    this.imgTemp = new BufferedImage(value_max, value_max, BufferedImage.TYPE_INT_ARGB);
                    // aqui es donde tengo que poner la imagen origen en la imagen destino
                    this.imgTemp.createGraphics().drawImage(this.imgOrigen, atop, (value_max - this.imgOrigen.getWidth()) / 2, (value_max - this.imgOrigen.getHeight()) / 2);
                    this.vi.getLienzo().setImage(this.imgTemp);
                } catch (Exception e) {
                    System.err.println("Error rotando la imagen " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jSliderRotateStateChanged

    private void jSliderRotateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSliderRotateFocusLost
        if (this.jSliderRotate.getValue() != 0) {
            int n = JOptionPane.showConfirmDialog(this, "¿Quiere aplicar los cambios de rotación realizados?", "Realizar Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            // son 2 botones el si=0, el no=1
            if (n == 1 && this.imgOrigen != null) {
                this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
                this.vi.getLienzo().setImage(this.imgOrigen);
            }
            this.jSliderRotate.setValue(0);
            this.imgOrigen = null;
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
         */
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            /*
            El comprobar el 0 es por que cuando aplicamos los cambios el slider
            cambia de valor a 0 y se vuelve a llama
             */
            int value_slider = this.jSliderBrigthness.getValue();
            if (this.imgOrigen == null && value_slider != 0) {
                this.vi.getLienzo().setTypeImage(BufferedImage.TYPE_INT_ARGB);
                this.imgOrigen = this.vi.getLienzo().getImage();
                // para el deshacer tambien
                VentanaPrincipal.UNDO = this.imgOrigen;
                this.setEnabledUndo(true);
            }
            if (this.imgOrigen != null && value_slider != 0) {
                try {
                    // multiplicamos por 1 R,G y B se quedan igual
                    float[] alfas = {1, 1, 1, 1};
                    // sumamos 100 a R, G y B le subimos el brillo el ultimo no lo tocamos
                    float[] betas = {value_slider, value_slider, value_slider, 0.0F,};
                    // el ultimo componente de los vectores es la transparencia
                    // que la dejamos igual en todos lados.
                    RescaleOp rop = new RescaleOp(alfas, betas, null);
                    this.imgTemp = rop.filter(this.imgOrigen, null);
                    this.vi.getLienzo().setImage(this.imgTemp);
                } catch (IllegalArgumentException e) {
                    System.err.println("Error al intentar modificar el brillo " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jSliderBrigthnessStateChanged

    private void jSliderBrigthnessFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSliderBrigthnessFocusLost
        if (this.jSliderBrigthness.getValue() != 0) {
            int n = JOptionPane.showConfirmDialog(this, "¿Quiere aplicar los cambios de brillo realizados?", "Realizar Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            // son 2 botones el si=0, el no=1
            if (n == 1 && this.imgOrigen != null) {
                this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
                this.vi.getLienzo().setImage(this.imgOrigen);
            }
            this.jSliderBrigthness.setValue(0);
            this.imgOrigen = null;
        }
    }//GEN-LAST:event_jSliderBrigthnessFocusLost

    private void jSliderContrastStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderContrastStateChanged
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            /*
            El comprobar el 0 es por que cuando aplicamos los cambios el slider
            cambia de valor a 0 y se vuelve a llamar
            Solo guardo la imagen original 1 sola vez (this.imgOrigen == null)
             */
            int value_slider = this.jSliderContrast.getValue();
            if (this.imgOrigen == null && value_slider != 50) {
                this.vi.getLienzo().setTypeImage(BufferedImage.TYPE_INT_ARGB);
                this.imgOrigen = this.vi.getLienzo().getImage();
                // para el deshacer tambien
                VentanaPrincipal.UNDO = this.imgOrigen;
                this.setEnabledUndo(true);
            }
            if (this.imgOrigen != null && value_slider != 50) {
                try {
                    // finción S
                    LookupTable lt = LookupTableProducer.sFuction(value_slider / 100f, 3.5);
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
                    this.vi.getLienzo().setImage(this.imgTemp);
                } catch (IllegalArgumentException e) {
                    System.err.println("Error al intentar modificar el contraste " + e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jSliderContrastStateChanged

    private void jSliderContrastFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSliderContrastFocusLost
        if (this.jSliderContrast.getValue() != 50) {
            int n = JOptionPane.showConfirmDialog(this, "¿Quiere aplicar los cambios de contraste realizados?", "Realizar Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            // son 2 botones el si=0, el no=1
            if (n == 1 && this.imgOrigen != null) {
                this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
                this.vi.getLienzo().setImage(this.imgOrigen);
            }
            this.jSliderContrast.setValue(50);
            this.imgOrigen = null;
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
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            if (this.imgOrigen == null) {
                this.imgOrigen = this.vi.getLienzo().getImage();
                // para el deshacer
                VentanaPrincipal.UNDO = this.imgOrigen;
                this.setEnabledUndo(true);
            }
            this.imgTemp = Utils.convertImageToType(this.imgOrigen, BufferedImage.TYPE_INT_RGB);
            if (this.imgTemp != null) {
                try {
                    /*
                    Activamos la opción EDGE_NO_OP, de forma que los pixeles del
                    borde (en los que no se puede aplicar la convolución) se
                    copiarán de la original. Si se optase por la opción
                    EDGE_ZERO_FILL, el borde se pondría a cero (ésta es la
                    opción por defecto).
                     */
                    ConvolveOp cop = new ConvolveOp(k, ConvolveOp.EDGE_NO_OP, null);
                    BufferedImage imgDestino = cop.filter(this.imgTemp, null);
                    this.vi.getLienzo().setImage(imgDestino);
                } catch (IllegalArgumentException e) {
                    System.err.println("Error al aplicar el filtro: " + e.getLocalizedMessage());
                }
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
            if (n == 1 && this.imgOrigen != null) {
                this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
                this.vi.getLienzo().setImage(this.imgOrigen);
            }
            this.jComboBoxFiltros.setSelectedIndex(0);
            this.imgOrigen = null;
        }
        this.vi.getLienzo().repaint();
    }//GEN-LAST:event_jComboBoxFiltrosFocusLost

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
    private javax.swing.ButtonGroup buttonGroupTools;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler15;
    private javax.swing.JButton jButtonAbrir;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemPantallaCompleta;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVerBarraEstado;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVerBarraHerramientas;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVerBarraUtilidades;
    protected javax.swing.JComboBox<String> jComboBoxColor;
    private javax.swing.JComboBox<String> jComboBoxFiltros;
    private javax.swing.JDesktopPane jDesktopEscritorio;
    protected javax.swing.JLabel jLabelAccionRaton;
    protected javax.swing.JLabel jLabelColor;
    protected javax.swing.JLabel jLabelHerramienta;
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
    protected javax.swing.JLabel jLabelMousePos;
    private javax.swing.JLabel jLabelTransparente;
    private javax.swing.JMenuItem jMIBrightnessContrast;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenu jMenuAyuda;
    private javax.swing.JMenuBar jMenuBarra;
    private javax.swing.JMenu jMenuEditar;
    private javax.swing.JMenu jMenuFilters;
    private javax.swing.JMenu jMenuIdioma;
    private javax.swing.JMenu jMenuImagen;
    private javax.swing.JMenuItem jMenuItemAbrir;
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
    private javax.swing.JMenuItem jMenuItemEmboss;
    private javax.swing.JMenuItem jMenuItemFocus;
    private javax.swing.JMenuItem jMenuItemFrontier;
    private javax.swing.JMenuItem jMenuItemGuardar;
    private javax.swing.JMenuItem jMenuItemLookupOp;
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
    private javax.swing.JMenuItem jMenuItemSharpens;
    private javax.swing.JMenuItem jMenuItemSuavizado;
    private javax.swing.JMenu jMenuOpciones;
    private javax.swing.JMenu jMenuRotate;
    private javax.swing.JMenu jMenuVer;
    private javax.swing.JPanel jPBrilloContraste;
    private javax.swing.JPanel jPBrilloSlider;
    private javax.swing.JPanel jPanelBrilloContraste;
    private javax.swing.JPanel jPanelPrevio;
    private javax.swing.JPanel jPanelRotar;
    private javax.swing.JPanel jPanelSplitDerecho;
    private javax.swing.JPanel jPanelStatusBar;
    private javax.swing.JPanel jPanelZoom;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemDE;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemEN;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemES;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
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
    protected javax.swing.JSpinner jSpinnerGrosor;
    protected javax.swing.JSpinner jSpinnerTransparencia;
    private javax.swing.JSplitPane jSplitPaneCenter;
    protected javax.swing.JToggleButton jToggleButtonAntiAliasing;
    protected javax.swing.JToggleButton jToggleButtonBorrar;
    protected javax.swing.JToggleButton jToggleButtonCirculo;
    protected javax.swing.JToggleButton jToggleButtonLinea;
    protected javax.swing.JToggleButton jToggleButtonMover;
    protected javax.swing.JToggleButton jToggleButtonPintar;
    protected javax.swing.JToggleButton jToggleButtonPunto;
    protected javax.swing.JToggleButton jToggleButtonRectangulo;
    protected javax.swing.JToggleButton jToggleButtonRelleno;
    private javax.swing.JToolBar jToolBarTools;
    // End of variables declaration//GEN-END:variables
}
