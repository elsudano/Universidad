package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;
import java.io.File;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import sm.cdlt.util.Utils;

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
     * Variables que sirven para posicionar la aplicación en mirad de la
     * pantalla.
     */
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final double width = screenSize.getWidth();
    private final double height = screenSize.getHeight();

    /**
     * Creates new form PaintBasico
     */
    public VentanaPrincipal() {
        initComponents();
        myInitComponents();
    }

    private void changeLocaleUI() {
        this.jCheckBoxMenuItemPantallaCompleta.setText(bundle.getString("FULL_SCREEN"));
        this.jCheckBoxMenuItemVerBarraEstado.setText(bundle.getString("STATUS_BAT_TEXT"));
        this.jCheckBoxMenuItemVerBarraHerramientas.setText(bundle.getString("TOOL_BAR_TEXT"));
        this.jCheckBoxMenuItemVerBarraUtilidades.setText(bundle.getString("UTIL_BAR_TEXT"));
        this.jLabelAccionRaton.setText(bundle.getString("MOUSE"));
        Color aux_color = this.vi.getLienzo().getColor();
        this.jLabelColor.setText(bundle.getString("COLOR")
                + "[R: " + aux_color.getRed()
                + " G: " + aux_color.getGreen()
                + " B: " + aux_color.getBlue()
                + "]");
        this.jLabelHerramienta.setText(bundle.getString("TOOL_POINT"));
        this.jMenuArchivo.setText(bundle.getString("FILE"));
        this.jMenuAyuda.setText(bundle.getString("HELP"));
        this.jMenuEditar.setText(bundle.getString("EDIT"));
        this.jMenuIdioma.setText(bundle.getString("LANGUAGE"));
        this.jMenuItemAbrir.setText(bundle.getString("OPEN"));
        this.jMenuItemAyuda.setText(bundle.getString("ABOUT"));
        this.jMenuItemCopiar.setText(bundle.getString("COPY"));
        this.jMenuItemCortar.setText(bundle.getString("CUT"));
        this.jMenuItemGuardar.setText(bundle.getString("SAVE"));
        this.jMenuItemNuevo.setText(bundle.getString("NUEVO"));
        this.jMenuItemPegar.setText(bundle.getString("PASTE"));
        this.jMenuItemSalir.setText(bundle.getString("EXIT"));
        this.jMenuOpciones.setText(bundle.getString("OPTIONS"));
        this.jMenuVer.setText(bundle.getString("VIEW"));
        this.jRadioButtonMenuItemEN.setText(bundle.getString("ENGLISH"));
        this.jRadioButtonMenuItemES.setText(bundle.getString("SPANISH"));
        this.jLabelMousePos.setText(bundle.getString("MOUSE_POS"));
        this.jButtonNuevo.setToolTipText(bundle.getString("NEW_TT"));
        this.jButtonAbrir.setToolTipText(bundle.getString("OPEN_TT"));
        this.jButtonGuardar.setToolTipText(bundle.getString("SAVE_TT"));
        this.jToggleButtonPintar.setToolTipText(bundle.getString("PAINT_TT"));
        this.jToggleButtonMover.setToolTipText(bundle.getString("MOVE_TT"));
        this.jToggleButtonPunto.setToolTipText(bundle.getString("POINT_TT"));
        this.jToggleButtonLinea.setToolTipText(bundle.getString("LINE_TT"));
        this.jToggleButtonRectangulo.setToolTipText(bundle.getString("RECT_TT"));
        this.jToggleButtonCirculo.setToolTipText(bundle.getString("CIRCLE_TT"));
        this.jComboBoxColor.setToolTipText(bundle.getString("COLOR_TT"));
        this.jSpinnerGrosor.setToolTipText(bundle.getString("STROKE_TT"));
        this.jSpinnerTransparencia.setToolTipText(bundle.getString("TRANS_TT"));
        this.jToggleButtonRelleno.setToolTipText(bundle.getString("FILL_TT"));
        this.jToggleButtonAntiAliasing.setToolTipText(bundle.getString("ANTIALIASING_TT"));
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

    public JToggleButton getToggleButtonRelleno() {
        return this.jToggleButtonRelleno;
    }

    public JToggleButton getToggleButtonRectangulo() {
        return this.jToggleButtonRectangulo;
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
        jLabelImageRotateLeft1 = new javax.swing.JLabel();
        jSliderZoom = new javax.swing.JSlider();
        jLabelImageRotateLeft2 = new javax.swing.JLabel();
        jPanelRotar = new javax.swing.JPanel();
        jLabelImageRotateLeft = new javax.swing.JLabel();
        jSliderRotate = new javax.swing.JSlider();
        jLabelImageRotateRigth = new javax.swing.JLabel();
        lienzo2DImage1 = new sm.cdlt.ui.Lienzo2DImage();
        jPanelBrilloContraste = new javax.swing.JPanel();
        jPBrilloSlider = new javax.swing.JPanel();
        jLabelImageBrigthness = new javax.swing.JLabel();
        jSliderBrigthness = new javax.swing.JSlider();
        jLabelImageBrigthness1 = new javax.swing.JLabel();
        jPBrilloContraste = new javax.swing.JPanel();
        jLabelImageBrigthness2 = new javax.swing.JLabel();
        jSliderContrast = new javax.swing.JSlider();
        jLabelImageBrigthness3 = new javax.swing.JLabel();
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
        jMenuItemBrightness = new javax.swing.JMenuItem();
        jMenuItemContrast = new javax.swing.JMenuItem();
        jMenuItemRescaleOP = new javax.swing.JMenuItem();
        jMenuItemConvolveOP = new javax.swing.JMenuItem();
        jMenuFilters = new javax.swing.JMenu();
        jMenuItemBlurMedio = new javax.swing.JMenuItem();
        jMenuItemBlurBinomial = new javax.swing.JMenuItem();
        jMenuItemFocus = new javax.swing.JMenuItem();
        jMenuItemRelief = new javax.swing.JMenuItem();
        jMenuItemBorder = new javax.swing.JMenuItem();
        jMenuItemRepujado = new javax.swing.JMenuItem();
        jMenuItemFrontier = new javax.swing.JMenuItem();
        jMenuItemSuavizado = new javax.swing.JMenuItem();
        jMenuOpciones = new javax.swing.JMenu();
        jMenuIdioma = new javax.swing.JMenu();
        jRadioButtonMenuItemES = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItemEN = new javax.swing.JRadioButtonMenuItem();
        jMenuAyuda = new javax.swing.JMenu();
        jMenuItemAyuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("interna/Bundle"); // NOI18N
        setTitle(bundle.getString("APP_TITLE")); // NOI18N
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1024, 768));

        jToolBarTools.setToolTipText(bundle.getString("TOOLS_FOR_USE")); // NOI18N
        jToolBarTools.setMinimumSize(new java.awt.Dimension(550, 36));
        jToolBarTools.setName("Herramientas"); // NOI18N
        jToolBarTools.setPreferredSize(new java.awt.Dimension(600, 36));

        jButtonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/nuevo.png"))); // NOI18N
        jButtonNuevo.setToolTipText(bundle.getString("NEW_TT")); // NOI18N
        jButtonNuevo.setBorder(null);
        jButtonNuevo.setFocusable(false);
        jButtonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonNuevo.setName("jButtonNuevo"); // NOI18N
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
        jButtonAbrir.setName("jButtonAbrir"); // NOI18N
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
        jButtonGuardar.setName("jButtonGuardar"); // NOI18N
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
        jToggleButtonPintar.setName("jToggleButtonPintar"); // NOI18N
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
        jToggleButtonMover.setName("jToggleButtonMover"); // NOI18N
        jToggleButtonMover.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonMover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonMoverActionPerformed(evt);
            }
        });
        jToolBarTools.add(jToggleButtonMover);

        buttonGroupOpciones.add(jToggleButtonBorrar);
        jToggleButtonBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/borrar.png"))); // NOI18N
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
        jToggleButtonPunto.setName("jToggleButtonPunto"); // NOI18N
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
        jToggleButtonLinea.setName("jToggleButtonLinea"); // NOI18N
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
        jToggleButtonRectangulo.setName("jToggleButtonRectangulo"); // NOI18N
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
        jToggleButtonCirculo.setName("jToggleButtonCirculo"); // NOI18N
        jToggleButtonCirculo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToggleButtonCirculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonCirculoActionPerformed(evt);
            }
        });
        jToolBarTools.add(jToggleButtonCirculo);
        jToolBarTools.add(jSeparator5);

        jLabelImageColor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelImageColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/color1.png"))); // NOI18N
        jLabelImageColor.setName("jLabelImageColor"); // NOI18N
        jToolBarTools.add(jLabelImageColor);

        jComboBoxColor.setModel(new ColorComboBoxModel());
        jComboBoxColor.setToolTipText(bundle.getString("COLOR_TT")); // NOI18N
        jComboBoxColor.setBorder(null);
        jComboBoxColor.setEditor(new ColorComboBoxEditor());
        jComboBoxColor.setMaximumSize(new java.awt.Dimension(60, 28));
        jComboBoxColor.setMinimumSize(new java.awt.Dimension(28, 28));
        jComboBoxColor.setName("jComboBoxColor"); // NOI18N
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
        jLabelImageGrosor.setName("jLabelImageGrosor"); // NOI18N
        jToolBarTools.add(jLabelImageGrosor);

        jSpinnerGrosor.setToolTipText(bundle.getString("STROKE_TT")); // NOI18N
        jSpinnerGrosor.setMaximumSize(new java.awt.Dimension(60, 28));
        jSpinnerGrosor.setMinimumSize(new java.awt.Dimension(28, 28));
        jSpinnerGrosor.setName("jSpinnerGrosor"); // NOI18N
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
        jLabelTransparente.setName("jLabelTransparente"); // NOI18N
        jToolBarTools.add(jLabelTransparente);

        jSpinnerTransparencia.setToolTipText(bundle.getString("TRANS_TT")); // NOI18N
        jSpinnerTransparencia.setMaximumSize(new java.awt.Dimension(60, 28));
        jSpinnerTransparencia.setMinimumSize(new java.awt.Dimension(28, 28));
        jSpinnerTransparencia.setName("jSpinnerTransparencia"); // NOI18N
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
        jToggleButtonRelleno.setName("jToggleButtonRelleno"); // NOI18N
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
        jToggleButtonAntiAliasing.setName("jToggleButtonAntiAliasing"); // NOI18N
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
        jButtonSalir.setName("jButtonSalir"); // NOI18N
        jButtonSalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        jToolBarTools.add(jButtonSalir);

        getContentPane().add(jToolBarTools, java.awt.BorderLayout.NORTH);

        jPanelStatusBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanelStatusBar.setName("jPanelStatusBar"); // NOI18N
        jPanelStatusBar.setLayout(new javax.swing.BoxLayout(jPanelStatusBar, javax.swing.BoxLayout.LINE_AXIS));

        filler15.setName("filler15"); // NOI18N
        jPanelStatusBar.add(filler15);

        jLabelHerramienta.setText(bundle.getString("TOOL_POINT")); // NOI18N
        jLabelHerramienta.setMaximumSize(new java.awt.Dimension(250, 25));
        jLabelHerramienta.setMinimumSize(new java.awt.Dimension(150, 25));
        jLabelHerramienta.setName("jLabelHerramienta"); // NOI18N
        jLabelHerramienta.setPreferredSize(new java.awt.Dimension(225, 25));
        jPanelStatusBar.add(jLabelHerramienta);

        jLabelColor.setText(bundle.getString("COLOR")); // NOI18N
        jLabelColor.setMaximumSize(new java.awt.Dimension(200, 25));
        jLabelColor.setMinimumSize(new java.awt.Dimension(200, 25));
        jLabelColor.setName("jLabelColor"); // NOI18N
        jLabelColor.setPreferredSize(new java.awt.Dimension(200, 25));
        jPanelStatusBar.add(jLabelColor);

        jLabelAccionRaton.setText(bundle.getString("MOUSE")); // NOI18N
        jLabelAccionRaton.setMaximumSize(new java.awt.Dimension(200, 25));
        jLabelAccionRaton.setMinimumSize(new java.awt.Dimension(200, 25));
        jLabelAccionRaton.setName("jLabelAccionRaton"); // NOI18N
        jLabelAccionRaton.setPreferredSize(new java.awt.Dimension(200, 25));
        jPanelStatusBar.add(jLabelAccionRaton);

        filler1.setName("filler1"); // NOI18N
        jPanelStatusBar.add(filler1);

        jLabelMousePos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMousePos.setText(bundle.getString("MOUSE_POS")); // NOI18N
        jLabelMousePos.setMaximumSize(new java.awt.Dimension(220, 25));
        jLabelMousePos.setMinimumSize(new java.awt.Dimension(200, 25));
        jLabelMousePos.setName("jLabelMousePos"); // NOI18N
        jLabelMousePos.setPreferredSize(new java.awt.Dimension(200, 25));
        jPanelStatusBar.add(jLabelMousePos);

        getContentPane().add(jPanelStatusBar, java.awt.BorderLayout.SOUTH);

        jSplitPaneCenter.setName("jSplitPaneCenter"); // NOI18N

        jPanelSplitDerecho.setMinimumSize(new java.awt.Dimension(200, 0));
        jPanelSplitDerecho.setName("jPanelSplitDerecho"); // NOI18N
        jPanelSplitDerecho.setPreferredSize(new java.awt.Dimension(200, 0));
        jPanelSplitDerecho.setLayout(new javax.swing.BoxLayout(jPanelSplitDerecho, javax.swing.BoxLayout.PAGE_AXIS));

        jPanelPrevio.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("VentanaPrincipal.jPanelPrevio.border.title"))); // NOI18N
        jPanelPrevio.setMaximumSize(new java.awt.Dimension(32767, 200));
        jPanelPrevio.setName("jPanelPrevio"); // NOI18N
        jPanelPrevio.setPreferredSize(new java.awt.Dimension(14, 200));
        jPanelPrevio.setLayout(new java.awt.BorderLayout());

        jPanelZoom.setLayout(new javax.swing.BoxLayout(jPanelZoom, javax.swing.BoxLayout.PAGE_AXIS));

        jLabelImageRotateLeft1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageRotateLeft1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/zoom_up.png"))); // NOI18N
        jLabelImageRotateLeft1.setAlignmentX(0.5F);
        jLabelImageRotateLeft1.setName("jLabelImageRotateLeft1"); // NOI18N
        jPanelZoom.add(jLabelImageRotateLeft1);

        jSliderZoom.setOrientation(javax.swing.JSlider.VERTICAL);
        jPanelZoom.add(jSliderZoom);

        jLabelImageRotateLeft2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageRotateLeft2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/zoom_down.png"))); // NOI18N
        jLabelImageRotateLeft2.setAlignmentX(0.5F);
        jLabelImageRotateLeft2.setName("jLabelImageRotateLeft2"); // NOI18N
        jPanelZoom.add(jLabelImageRotateLeft2);

        jPanelPrevio.add(jPanelZoom, java.awt.BorderLayout.EAST);

        jPanelRotar.setLayout(new javax.swing.BoxLayout(jPanelRotar, javax.swing.BoxLayout.LINE_AXIS));

        jLabelImageRotateLeft.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageRotateLeft.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rotar_izq.png"))); // NOI18N
        jLabelImageRotateLeft.setName("jLabelImageRotateLeft"); // NOI18N
        jPanelRotar.add(jLabelImageRotateLeft);

        jSliderRotate.setName("jSliderRotate"); // NOI18N
        jPanelRotar.add(jSliderRotate);

        jLabelImageRotateRigth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageRotateRigth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/rotar_der.png"))); // NOI18N
        jLabelImageRotateRigth.setName("jLabelImageRotateRigth"); // NOI18N
        jPanelRotar.add(jLabelImageRotateRigth);

        jPanelPrevio.add(jPanelRotar, java.awt.BorderLayout.SOUTH);

        lienzo2DImage1.setName("lienzo2DImage1"); // NOI18N
        jPanelPrevio.add(lienzo2DImage1, java.awt.BorderLayout.CENTER);

        jPanelSplitDerecho.add(jPanelPrevio);

        jPanelBrilloContraste.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("VentanaPrincipal.jPanelBrilloContraste.border.title"))); // NOI18N
        jPanelBrilloContraste.setMaximumSize(new java.awt.Dimension(32767, 100));
        jPanelBrilloContraste.setMinimumSize(new java.awt.Dimension(0, 100));
        jPanelBrilloContraste.setName("jPanelBrilloContraste"); // NOI18N
        jPanelBrilloContraste.setPreferredSize(new java.awt.Dimension(0, 100));
        jPanelBrilloContraste.setLayout(new javax.swing.BoxLayout(jPanelBrilloContraste, javax.swing.BoxLayout.PAGE_AXIS));

        jPBrilloSlider.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPBrilloSlider.setName("jPBrilloSlider"); // NOI18N
        jPBrilloSlider.setLayout(new javax.swing.BoxLayout(jPBrilloSlider, javax.swing.BoxLayout.LINE_AXIS));

        jLabelImageBrigthness.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageBrigthness.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/brillo1.png"))); // NOI18N
        jLabelImageBrigthness.setName("jLabelImageBrigthness"); // NOI18N
        jPBrilloSlider.add(jLabelImageBrigthness);

        jSliderBrigthness.setMinimum(-100);
        jSliderBrigthness.setToolTipText(bundle.getString("BRIGHTNESS_TT")); // NOI18N
        jSliderBrigthness.setValue(0);
        jSliderBrigthness.setName("jSliderBrigthness"); // NOI18N
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

        jLabelImageBrigthness1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageBrigthness1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/brillo3.png"))); // NOI18N
        jLabelImageBrigthness1.setName("jLabelImageBrigthness1"); // NOI18N
        jPBrilloSlider.add(jLabelImageBrigthness1);

        jPanelBrilloContraste.add(jPBrilloSlider);

        jPBrilloContraste.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPBrilloContraste.setName("jPBrilloContraste"); // NOI18N
        jPBrilloContraste.setLayout(new javax.swing.BoxLayout(jPBrilloContraste, javax.swing.BoxLayout.LINE_AXIS));

        jLabelImageBrigthness2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageBrigthness2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/contraste2.png"))); // NOI18N
        jLabelImageBrigthness2.setName("jLabelImageBrigthness2"); // NOI18N
        jPBrilloContraste.add(jLabelImageBrigthness2);

        jSliderContrast.setName("jSliderContrast"); // NOI18N
        jPBrilloContraste.add(jSliderContrast);

        jLabelImageBrigthness3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelImageBrigthness3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/contraste1.png"))); // NOI18N
        jLabelImageBrigthness3.setName("jLabelImageBrigthness3"); // NOI18N
        jPBrilloContraste.add(jLabelImageBrigthness3);

        jPanelBrilloContraste.add(jPBrilloContraste);

        jPanelSplitDerecho.add(jPanelBrilloContraste);

        jComboBoxFiltros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar...", "Blur Medio", "Blur Binomial", "Enfoque", "Relieve", "Realzar Bordes", "Emboss", "Sharpens", "Frontera Laplaciano", "Suavisado Gaussiano" }));
        jComboBoxFiltros.setMaximumSize(new java.awt.Dimension(32767, 36));
        jComboBoxFiltros.setMinimumSize(new java.awt.Dimension(65, 36));
        jComboBoxFiltros.setName("jComboBoxFiltros"); // NOI18N
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
        jMenuArchivo.setName("jMenuArchivo"); // NOI18N

        jMenuItemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/nuevo.png"))); // NOI18N
        jMenuItemNuevo.setText(bundle.getString("NUEVO")); // NOI18N
        jMenuItemNuevo.setName("jMenuItemNuevo"); // NOI18N
        jMenuItemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemNuevoActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemNuevo);

        jMenuItemAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/abrir.png"))); // NOI18N
        jMenuItemAbrir.setText(bundle.getString("OPEN")); // NOI18N
        jMenuItemAbrir.setName("jMenuItemAbrir"); // NOI18N
        jMenuItemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAbrirActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemAbrir);

        jMenuItemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/guardar.png"))); // NOI18N
        jMenuItemGuardar.setText(bundle.getString("SAVE")); // NOI18N
        jMenuItemGuardar.setName("jMenuItemGuardar"); // NOI18N
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
        jMenuItemSalir.setName("jMenuItemSalir"); // NOI18N
        jMenuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSalirActionPerformed(evt);
            }
        });
        jMenuArchivo.add(jMenuItemSalir);

        jMenuBarra.add(jMenuArchivo);

        jMenuEditar.setText(bundle.getString("EDIT")); // NOI18N
        jMenuEditar.setName("jMenuEditar"); // NOI18N

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

        jMenuItemBrightness.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/brillo1.png"))); // NOI18N
        jMenuItemBrightness.setText(bundle.getString("BRIGHTNESS")); // NOI18N
        jMenuItemBrightness.setName("jMenuItemBrightness"); // NOI18N
        jMenuImagen.add(jMenuItemBrightness);

        jMenuItemContrast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/tono.png"))); // NOI18N
        jMenuItemContrast.setText(bundle.getString("CONSTRAST")); // NOI18N
        jMenuItemContrast.setName("jMenuItemContrast"); // NOI18N
        jMenuImagen.add(jMenuItemContrast);

        jMenuItemRescaleOP.setText(bundle.getString("VentanaPrincipal.jMenuItemRescaleOP.text")); // NOI18N
        jMenuItemRescaleOP.setName("jMenuItemRescaleOP"); // NOI18N
        jMenuItemRescaleOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRescaleOPActionPerformed(evt);
            }
        });
        jMenuImagen.add(jMenuItemRescaleOP);

        jMenuItemConvolveOP.setText(bundle.getString("VentanaPrincipal.jMenuItemConvolveOP.text")); // NOI18N
        jMenuItemConvolveOP.setName("jMenuItemConvolveOP"); // NOI18N
        jMenuItemConvolveOP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConvolveOPActionPerformed(evt);
            }
        });
        jMenuImagen.add(jMenuItemConvolveOP);

        jMenuBarra.add(jMenuImagen);

        jMenuFilters.setText(bundle.getString("FILTERS")); // NOI18N
        jMenuFilters.setName("jMenuFilters"); // NOI18N

        jMenuItemBlurMedio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/blur.png"))); // NOI18N
        jMenuItemBlurMedio.setText(bundle.getString("BLUR_MEDIO")); // NOI18N
        jMenuItemBlurMedio.setName("jMenuItemBlurMedio"); // NOI18N
        jMenuItemBlurMedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBlurMedioActionPerformed(evt);
            }
        });
        jMenuFilters.add(jMenuItemBlurMedio);

        jMenuItemBlurBinomial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/blur2.png"))); // NOI18N
        jMenuItemBlurBinomial.setText(bundle.getString("BLUR_BINOMIAL")); // NOI18N
        jMenuItemBlurBinomial.setName("jMenuItemBlurBinomial"); // NOI18N
        jMenuItemBlurBinomial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBlurBinomialActionPerformed(evt);
            }
        });
        jMenuFilters.add(jMenuItemBlurBinomial);

        jMenuItemFocus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/enfoque.png"))); // NOI18N
        jMenuItemFocus.setText(bundle.getString("FOCUS")); // NOI18N
        jMenuItemFocus.setName("jMenuItemFocus"); // NOI18N
        jMenuItemFocus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFocusActionPerformed(evt);
            }
        });
        jMenuFilters.add(jMenuItemFocus);

        jMenuItemRelief.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/relieve.png"))); // NOI18N
        jMenuItemRelief.setText(bundle.getString("RELIEF")); // NOI18N
        jMenuItemRelief.setName("jMenuItemRelief"); // NOI18N
        jMenuItemRelief.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReliefActionPerformed(evt);
            }
        });
        jMenuFilters.add(jMenuItemRelief);

        jMenuItemBorder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/realzar.png"))); // NOI18N
        jMenuItemBorder.setText(bundle.getString("FRONTIER_DETECT")); // NOI18N
        jMenuItemBorder.setName("jMenuItemBorder"); // NOI18N
        jMenuFilters.add(jMenuItemBorder);

        jMenuItemRepujado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/relieve.png"))); // NOI18N
        jMenuItemRepujado.setText(bundle.getString("REPUJADO")); // NOI18N
        jMenuItemRepujado.setName("jMenuItemRepujado"); // NOI18N
        jMenuFilters.add(jMenuItemRepujado);

        jMenuItemFrontier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/frontera.png"))); // NOI18N
        jMenuItemFrontier.setText(bundle.getString("FRONTIER")); // NOI18N
        jMenuItemFrontier.setName("jMenuItemFrontier"); // NOI18N
        jMenuItemFrontier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFrontierActionPerformed(evt);
            }
        });
        jMenuFilters.add(jMenuItemFrontier);

        jMenuItemSuavizado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/suavizado.png"))); // NOI18N
        jMenuItemSuavizado.setText(bundle.getString("VentanaPrincipal.jMenuItemSuavizado.text")); // NOI18N
        jMenuItemSuavizado.setName("jMenuItemSuavizado"); // NOI18N
        jMenuItemSuavizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSuavizadoActionPerformed(evt);
            }
        });
        jMenuFilters.add(jMenuItemSuavizado);

        jMenuBarra.add(jMenuFilters);

        jMenuOpciones.setText(bundle.getString("OPTIONS")); // NOI18N
        jMenuOpciones.setName("jMenuOpciones"); // NOI18N

        jMenuIdioma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/lenguaje.png"))); // NOI18N
        jMenuIdioma.setText(bundle.getString("LANGUAGE")); // NOI18N
        jMenuIdioma.setName("jMenuIdioma"); // NOI18N

        jRadioButtonMenuItemES.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
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

        jRadioButtonMenuItemEN.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
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
    // <editor-fold defaultstate="collapsed" desc="My Initialized Code">//GEN-BEGIN:myInitComponents
    private void myInitComponents() {
        // pongo aquí los valores de los sppiner para setearlos al principio.
        this.jSpinnerGrosor.setValue(VentanaPrincipal.MIN_VALUE_SPPINER);
        this.jSpinnerTransparencia.setValue(VentanaPrincipal.MAX_VALUE_SPPINER);
        // colocamos la aplicación en el centro
        this.setLocation((int)this.width / 2 - (int)this.getWidth() / 2, (int)this.height / 2 - (int)this.getHeight() / 2);
        // pongo el idioma de la interfaz
        VentanaPrincipal.bundle = ResourceBundle.getBundle("interna/Bundle");
    }// </editor-fold>//GEN-END:myInitComponents

    private void jMenuItemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemNuevoActionPerformed
        Questions question = new Questions(this, true);
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
            FileFilter jpg_filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
            FileFilter png_filter = new FileNameExtensionFilter("PNG file", "png");
            FileFilter gif_filter = new FileNameExtensionFilter("GIF file", "gif");
            JFileChooser dlg = new JFileChooser();
            switch (this.vi.getLienzo().getImage(true).getType()) {
                case BufferedImage.TYPE_INT_RGB:
                    dlg.addChoosableFileFilter(jpg_filter);
                    dlg.addChoosableFileFilter(png_filter);
                    break;
                case BufferedImage.TYPE_INT_ARGB:
                    dlg.addChoosableFileFilter(png_filter);
                    break;
                case BufferedImage.TYPE_BYTE_INDEXED:
                    dlg.addChoosableFileFilter(gif_filter);
                    break;
                default:
                    break;
            }
            dlg.setAcceptAllFileFilterUsed(false);
            int resp = dlg.showSaveDialog(this);
            if (resp == JFileChooser.APPROVE_OPTION) {
                try {
                    BufferedImage img = vi.getLienzo().getImage(true);
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
                    System.err.println("Error al guardar la imagen");
                }
            }

        }
    }//GEN-LAST:event_jMenuItemGuardarActionPerformed

    private void jMenuItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItemSalirActionPerformed

    private void jMenuItemCortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCortarActionPerformed
        // @TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemCortarActionPerformed

    private void jMenuItemCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCopiarActionPerformed
        // @TODO add your handling code here:
    }//GEN-LAST:event_jMenuItemCopiarActionPerformed

    private void jMenuItemPegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemPegarActionPerformed
        // @TODO add your handling code here:
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

    private void jRadioButtonMenuItemESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItemESActionPerformed
        VentanaPrincipal.bundle = ResourceBundle.getBundle("interna/Bundle");
        this.changeLocaleUI();
    }//GEN-LAST:event_jRadioButtonMenuItemESActionPerformed

    private void jRadioButtonMenuItemENActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMenuItemENActionPerformed
        VentanaPrincipal.bundle = ResourceBundle.getBundle("interna/Bundle_en_US");
        this.changeLocaleUI();
    }//GEN-LAST:event_jRadioButtonMenuItemENActionPerformed

    private void jMenuItemAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAyudaActionPerformed
        AcercaDe myAcerca = new AcercaDe(this, true);
        myAcerca.setVisible(true);
    }//GEN-LAST:event_jMenuItemAyudaActionPerformed

    private void jButtonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoActionPerformed
        this.jMenuItemNuevoActionPerformed(evt);
    }//GEN-LAST:event_jButtonNuevoActionPerformed

    private void jButtonAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbrirActionPerformed
        this.jMenuItemAbrirActionPerformed(evt);
    }//GEN-LAST:event_jButtonAbrirActionPerformed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        this.jMenuItemGuardarActionPerformed(evt);
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        this.jMenuItemSalirActionPerformed(evt);
    }//GEN-LAST:event_jButtonSalirActionPerformed

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
        
    }//GEN-LAST:event_jToggleButtonRellenoActionPerformed

    private void jToggleButtonAntiAliasingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonAntiAliasingActionPerformed
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        this.vi.getLienzo().setFlatten(this.jToggleButtonAntiAliasing.isSelected());
    }//GEN-LAST:event_jToggleButtonAntiAliasingActionPerformed

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
            this.imgOrigen = this.vi.getLienzo().getImage(true);
            BufferedImage img_aux = Utils.convertImageToType(this.imgOrigen, BufferedImage.TYPE_INT_RGB);
            if (img_aux != null) {
                try {
                    /*
                    Activamos la opción EDGE_NO_OP, de forma que los pixeles del
                    borde (en los que no se puede aplicar la convolución) se
                    copiarán de la original. Si se optase por la opción
                    EDGE_ZERO_FILL, el borde se pondría a cero (ésta es la
                    opción por defecto).
                     */
                    ConvolveOp cop = new ConvolveOp(k, ConvolveOp.EDGE_NO_OP, null);
                    BufferedImage imgDestino = cop.filter(img_aux, null);
                    this.vi.getLienzo().setImage(imgDestino);
                    this.vi.getLienzo().repaint();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }

    private void jComboBoxFiltrosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxFiltrosItemStateChanged
        // este if es por que al cambiar de estado se mandan dos casos
        // el que estaba seleccionado y el que se ha seleccionado
        // por eso se discrimina el caso del que estaba seleccionado.
        if (evt.getStateChange() == ItemEvent.SELECTED && this.jComboBoxFiltros.getSelectedIndex() != 0) {
            float[] datamask;
            Kernel mask = null;
            switch ((String) evt.getItem()) {
                case "Blur Medio":
                    datamask = new float[]{0.11f, 0.11f, 0.11f, 0.11f, 0.2f, 0.11f, 0.11f, 0.11f, 0.11f};
                    mask = new Kernel(3, 3, datamask);
                    break;
                case "Blur Binomial":
                    datamask = new float[]{0.0625f, 0.125f, 0.0625f, 0.125f, 0.25f, 0.125f, 0.0625f, 0.125f, 0.0625f};
                    mask = new Kernel(3, 3, datamask);
                    break;
                case "Enfoque":
                    datamask = new float[]{0f, -1f, 0f, -1f, 5f, -1f, 0f, -1f, 0f};
                    mask = new Kernel(3, 3, datamask);
                    break;
                case "Relieve":
                    datamask = new float[]{-2f, -1f, 0f, -1f, 1f, 1f, 0f, 1f, 2f};
                    ;
                    mask = new Kernel(3, 3, datamask);
                    break;
                case "Realzar Bordes":
                    datamask = new float[]{0f, 0f, 0f, -1f, 1f, 0f, 0f, 0f, 0f};
                    mask = new Kernel(3, 3, datamask);
                    break;
                case "Emboss":
                    datamask = new float[]{-2f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 2f};
                    mask = new Kernel(3, 3, datamask);
                    break;
                case "Sharpens":
                    datamask = new float[]{-1f, -1f, -1f, -1f, 9f, -1f, -1f, -1f, -1f,};
                    mask = new Kernel(3, 3, datamask);
                    break;
                case "Frontera Laplaciano":
                    datamask = new float[]{1f, 1f, 1f, 1f, -8f, 1f, 1f, 1f, 1f};
                    mask = new Kernel(3, 3, datamask);
                    break;
                case "Suavisado":
                    datamask = new float[]{
                        0.0030f, 0.0133f, 0.0219f, 0.0133f, 0.0030f,
                        0.0133f, 0.0596f, 0.0983f, 0.0596f, 0.0133f,
                        0.0219f, 0.0983f, 0.1621f, 0.0983f, 0.0219f,
                        0.0133f, 0.0596f, 0.0983f, 0.0596f, 0.0133f,
                        0.0030f, 0.0133f, 0.0219f, 0.0133f, 0.0030f
                    };
                    mask = new Kernel(5, 5, datamask);
                    break;
            }
            // aplicamos el filtro
            this.applyFilter(mask);
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
            if (this.imgOrigen == null && this.jSliderBrigthness.getValue() != 0) {
                this.imgOrigen = Utils.convertImageToType(this.imgOrigen, BufferedImage.TYPE_INT_ARGB);
            }
            this.imgTemp = Utils.convertImageToType(this.imgOrigen, BufferedImage.TYPE_INT_ARGB);
            if (this.imgTemp != null && this.jSliderBrigthness.getValue() != 0) {
                try {
                    // multiplicamos por 1 R,G y B se quedan igual
                    int alfa = 1;
                    float[] alfas = {alfa, alfa, alfa, alfa};
                    // sumamos 100 a R, G y B le subimos el brillo el ultimo
                    // no lo tocamos
                    int beta = this.jSliderBrigthness.getValue();
                    float[] betas = {beta, beta, beta, 0.0F,};
                    // el ultimo componente de los vectores es la transparencia
                    // que la dejamos igual en todos lados.
                    RescaleOp rop = new RescaleOp(alfas, betas, null);
                    BufferedImage imgDestino = rop.filter(this.imgOrigen, null);
                    this.vi.getLienzo().setImage(imgDestino);
                    this.vi.getLienzo().repaint();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jSliderBrigthnessStateChanged

    private void jSliderBrigthnessFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jSliderBrigthnessFocusLost
        int n = JOptionPane.showConfirmDialog(this, "¿Quiere aplicar los cambios de brillo realizados?", "Realizar Cambios", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        // son 2 botones el si=0, el no=1
        if (n == 1 && this.imgOrigen != null) {
            this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
            this.vi.getLienzo().setImage(this.imgOrigen);
        }
        this.jSliderBrigthness.setValue(0);
        this.imgOrigen = null;
        this.vi.getLienzo().repaint();
    }//GEN-LAST:event_jSliderBrigthnessFocusLost

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
            this.imgOrigen = this.vi.getLienzo().getImage(true);
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
                    BufferedImage imgDestino = rop.filter(this.imgOrigen, null);
                    this.vi.getLienzo().setImage(imgDestino);
                    this.vi.getLienzo().repaint();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemRescaleOPActionPerformed

    private void jMenuItemConvolveOPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConvolveOPActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            BufferedImage img_aux = this.vi.getLienzo().getImage(true);
            if (img_aux != null) {
                try {
                    float[] datamask = {1f, 1f, 1f, 1f, -8f, 1f, 1f, 1f, 1f};
                    Kernel mask = new Kernel(3, 3, datamask);
                    ConvolveOp cop = new ConvolveOp(mask, ConvolveOp.EDGE_NO_OP, null);
                    BufferedImage imgDestino = cop.filter(img_aux, null);
                    this.vi.getLienzo().setImage(imgDestino);
                    this.vi.getLienzo().repaint();
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getLocalizedMessage());
                }
            }
        }
    }//GEN-LAST:event_jMenuItemConvolveOPActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel jLabelImageBrigthness;
    private javax.swing.JLabel jLabelImageBrigthness1;
    private javax.swing.JLabel jLabelImageBrigthness2;
    private javax.swing.JLabel jLabelImageBrigthness3;
    private javax.swing.JLabel jLabelImageColor;
    private javax.swing.JLabel jLabelImageGrosor;
    private javax.swing.JLabel jLabelImageRotateLeft;
    private javax.swing.JLabel jLabelImageRotateLeft1;
    private javax.swing.JLabel jLabelImageRotateLeft2;
    private javax.swing.JLabel jLabelImageRotateRigth;
    protected javax.swing.JLabel jLabelMousePos;
    private javax.swing.JLabel jLabelTransparente;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenu jMenuAyuda;
    private javax.swing.JMenuBar jMenuBarra;
    private javax.swing.JMenu jMenuEditar;
    private javax.swing.JMenu jMenuFilters;
    private javax.swing.JMenu jMenuIdioma;
    private javax.swing.JMenu jMenuImagen;
    private javax.swing.JMenuItem jMenuItemAbrir;
    private javax.swing.JMenuItem jMenuItemAyuda;
    private javax.swing.JMenuItem jMenuItemBlurBinomial;
    private javax.swing.JMenuItem jMenuItemBlurMedio;
    private javax.swing.JMenuItem jMenuItemBorder;
    private javax.swing.JMenuItem jMenuItemBrightness;
    private javax.swing.JMenuItem jMenuItemContrast;
    private javax.swing.JMenuItem jMenuItemConvolveOP;
    private javax.swing.JMenuItem jMenuItemCopiar;
    private javax.swing.JMenuItem jMenuItemCortar;
    private javax.swing.JMenuItem jMenuItemFocus;
    private javax.swing.JMenuItem jMenuItemFrontier;
    private javax.swing.JMenuItem jMenuItemGuardar;
    private javax.swing.JMenuItem jMenuItemNuevo;
    private javax.swing.JMenuItem jMenuItemPegar;
    private javax.swing.JMenuItem jMenuItemRelief;
    private javax.swing.JMenuItem jMenuItemRepujado;
    private javax.swing.JMenuItem jMenuItemRescaleOP;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenuItem jMenuItemSuavizado;
    private javax.swing.JMenu jMenuOpciones;
    private javax.swing.JMenu jMenuVer;
    private javax.swing.JPanel jPBrilloContraste;
    private javax.swing.JPanel jPBrilloSlider;
    private javax.swing.JPanel jPanelBrilloContraste;
    private javax.swing.JPanel jPanelPrevio;
    private javax.swing.JPanel jPanelRotar;
    private javax.swing.JPanel jPanelSplitDerecho;
    private javax.swing.JPanel jPanelStatusBar;
    private javax.swing.JPanel jPanelZoom;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemEN;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemES;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
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
    private sm.cdlt.ui.Lienzo2DImage lienzo2DImage1;
    // End of variables declaration//GEN-END:variables
}
