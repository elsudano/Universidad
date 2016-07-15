package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JToggleButton;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

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
     * Variables que sirven para posicionar la aplicación en mirad de la
     * pantalla.
     */
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double width = screenSize.getWidth();
    private double height = screenSize.getHeight();

    /**
     * Creates new form PaintBasico
     */
    public VentanaPrincipal() {
        initComponents();
        myInitComponents();
    }

    private void changeLocaleUI() {
        this.jCheckBoxAlisar.setText(bundle.getString("FLATTEN"));
        this.jCheckBoxEditar.setText(bundle.getString("CHECKBOX_EDIT"));
        this.jCheckBoxMenuItemPantallaCompleta.setText(bundle.getString("FULL_SCREEN"));
        this.jCheckBoxMenuItemVerBarraEstado.setText(bundle.getString("STATUS_BAT_TEXT"));
        this.jCheckBoxMenuItemVerBarraHerramientas.setText(bundle.getString("TOOL_BAR_TEXT"));
        this.jCheckBoxMenuItemVerBarraUtilidades.setText(bundle.getString("UTIL_BAR_TEXT"));
        this.jCheckBoxRelleno.setText(bundle.getString("FILL"));
        this.jCheckBoxTransparencia.setText(bundle.getString("TRANSPARENCY"));
        this.jLabelAccionRaton.setText(bundle.getString("MOUSE"));
        Color aux_color = this.vi.getLienzo().getColor();
        this.jLabelColor.setText(bundle.getString("COLOR")
                + "[R: " + aux_color.getRed()
                + " G: " + aux_color.getGreen()
                + " B: " + aux_color.getBlue()
                + "]");
        this.jLabelColors.setText(bundle.getString("COLORS"));
        this.jLabelEstilo.setText(bundle.getString("STYLE"));
        this.jLabelGrosor.setText(bundle.getString("STROKE_VALUE_LABEL"));
        this.jLabelGrosorEtiqueta.setText(bundle.getString("GROSOR:"));
        this.jLabelHerramienta.setText(bundle.getString("TOOL_POINT"));
        this.jLabelOpciones.setText(bundle.getString("OPTIONS"));
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
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jToggleButtonPunto = new javax.swing.JToggleButton();
        jToggleButtonLinea = new javax.swing.JToggleButton();
        jToggleButtonRectangulo = new javax.swing.JToggleButton();
        jToggleButtonCirculo = new javax.swing.JToggleButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jLabelImageColor = new javax.swing.JLabel();
        jComboBoxColor = new javax.swing.JComboBox<>();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jLabelImageGrosor = new javax.swing.JLabel();
        jSpinnerGrosor = new javax.swing.JSpinner();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jLabelTransparente = new javax.swing.JLabel();
        jSpinnerTransparencia = new javax.swing.JSpinner();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jToggleButtonRelleno = new javax.swing.JToggleButton();
        jToggleButtonAntiAliasing = new javax.swing.JToggleButton();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0), new java.awt.Dimension(30, 0));
        jButtonSalir = new javax.swing.JButton();
        jPanelOptions = new javax.swing.JPanel();
        jToolBarOptions = new javax.swing.JToolBar();
        jPanelColorContents = new javax.swing.JPanel();
        jLabelColors = new javax.swing.JLabel();
        jPanelColors = new javax.swing.JPanel();
        jButtonNegro = new javax.swing.JButton();
        jButtonBlanco = new javax.swing.JButton();
        jButtonRojo = new javax.swing.JButton();
        jButtonVerde = new javax.swing.JButton();
        jButtonAzul = new javax.swing.JButton();
        jButtonAmarillo = new javax.swing.JButton();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0));
        jPanelSlidersContents = new javax.swing.JPanel();
        jLabelEstilo = new javax.swing.JLabel();
        jPanelSliders = new javax.swing.JPanel();
        jLabelGrosorEtiqueta = new javax.swing.JLabel();
        jLabelGrosor = new javax.swing.JLabel();
        jSliderGrosor = new javax.swing.JSlider();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0), new java.awt.Dimension(15, 0));
        jPanelCheckBoxContents = new javax.swing.JPanel();
        jLabelOpciones = new javax.swing.JLabel();
        jPanelCheckBoxs = new javax.swing.JPanel();
        jCheckBoxRelleno = new javax.swing.JCheckBox();
        jCheckBoxTransparencia = new javax.swing.JCheckBox();
        jCheckBoxAlisar = new javax.swing.JCheckBox();
        jCheckBoxEditar = new javax.swing.JCheckBox();
        jPanelStatusBar = new javax.swing.JPanel();
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jLabelHerramienta = new javax.swing.JLabel();
        jLabelColor = new javax.swing.JLabel();
        jLabelAccionRaton = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        jLabelMousePos = new javax.swing.JLabel();
        jDesktopEscritorio = new javax.swing.JDesktopPane();
        jMenuBarra = new javax.swing.JMenuBar();
        jMenuArchivo = new javax.swing.JMenu();
        jMenuItemNuevo = new javax.swing.JMenuItem();
        jMenuItemAbrir = new javax.swing.JMenuItem();
        jMenuItemGuardar = new javax.swing.JMenuItem();
        jSeparator1MArchivo = new javax.swing.JPopupMenu.Separator();
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
        jMenuOpciones = new javax.swing.JMenu();
        jMenuIdioma = new javax.swing.JMenu();
        jRadioButtonMenuItemES = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItemEN = new javax.swing.JRadioButtonMenuItem();
        jMenuAyuda = new javax.swing.JMenu();
        jMenuItemAyuda = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("interna/Bundle"); // NOI18N
        setTitle(bundle.getString("APP_TITLE")); // NOI18N
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName("Form"); // NOI18N

        jToolBarTools.setToolTipText(bundle.getString("TOOLS_FOR_USE")); // NOI18N
        jToolBarTools.setMinimumSize(new java.awt.Dimension(150, 32));
        jToolBarTools.setName("Herramientas"); // NOI18N
        jToolBarTools.setPreferredSize(new java.awt.Dimension(500, 32));

        jButtonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/nuevo.png"))); // NOI18N
        jButtonNuevo.setToolTipText(bundle.getString("NEW_TT")); // NOI18N
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

        jSeparator3.setName("jSeparator3"); // NOI18N
        jToolBarTools.add(jSeparator3);

        buttonGroupOpciones.add(jToggleButtonPintar);
        jToggleButtonPintar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/pintar.png"))); // NOI18N
        jToggleButtonPintar.setSelected(true);
        jToggleButtonPintar.setToolTipText(bundle.getString("PAINT_TT")); // NOI18N
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

        jSeparator2.setName("jSeparator2"); // NOI18N
        jToolBarTools.add(jSeparator2);

        buttonGroupTools.add(jToggleButtonPunto);
        jToggleButtonPunto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/punto.png"))); // NOI18N
        jToggleButtonPunto.setSelected(true);
        jToggleButtonPunto.setToolTipText(bundle.getString("POINT_TT")); // NOI18N
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
        jToggleButtonCirculo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/circulo.png"))); // NOI18N
        jToggleButtonCirculo.setToolTipText(bundle.getString("CIRCLE_TT")); // NOI18N
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

        jSeparator4.setName("jSeparator4"); // NOI18N
        jToolBarTools.add(jSeparator4);

        jLabelImageColor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelImageColor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/color.png"))); // NOI18N
        jLabelImageColor.setMaximumSize(new java.awt.Dimension(28, 28));
        jLabelImageColor.setMinimumSize(new java.awt.Dimension(28, 28));
        jLabelImageColor.setName("jLabelImageColor"); // NOI18N
        jLabelImageColor.setPreferredSize(new java.awt.Dimension(28, 28));
        jToolBarTools.add(jLabelImageColor);

        jComboBoxColor.setModel(new ColorComboBoxModel());
        jComboBoxColor.setToolTipText(bundle.getString("COLOR_TT")); // NOI18N
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

        jSeparator5.setName("jSeparator5"); // NOI18N
        jToolBarTools.add(jSeparator5);

        jLabelImageGrosor.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelImageGrosor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/grosor.png"))); // NOI18N
        jLabelImageGrosor.setMaximumSize(new java.awt.Dimension(28, 28));
        jLabelImageGrosor.setMinimumSize(new java.awt.Dimension(28, 28));
        jLabelImageGrosor.setName("jLabelImageGrosor"); // NOI18N
        jLabelImageGrosor.setPreferredSize(new java.awt.Dimension(28, 28));
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

        jSeparator7.setName("jSeparator7"); // NOI18N
        jToolBarTools.add(jSeparator7);

        jLabelTransparente.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelTransparente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/transparente.png"))); // NOI18N
        jLabelTransparente.setMaximumSize(new java.awt.Dimension(28, 28));
        jLabelTransparente.setMinimumSize(new java.awt.Dimension(28, 28));
        jLabelTransparente.setName("jLabelTransparente"); // NOI18N
        jLabelTransparente.setPreferredSize(new java.awt.Dimension(28, 28));
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

        jSeparator6.setName("jSeparator6"); // NOI18N
        jToolBarTools.add(jSeparator6);

        jToggleButtonRelleno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/relleno.png"))); // NOI18N
        jToggleButtonRelleno.setToolTipText(bundle.getString("FILL_TT")); // NOI18N
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

        filler3.setName("filler3"); // NOI18N
        jToolBarTools.add(filler3);

        jButtonSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/salir.png"))); // NOI18N
        jButtonSalir.setToolTipText(bundle.getString("EXIT")); // NOI18N
        jButtonSalir.setName("jButtonSalir"); // NOI18N
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });
        jToolBarTools.add(jButtonSalir);

        getContentPane().add(jToolBarTools, java.awt.BorderLayout.NORTH);

        jPanelOptions.setName("jPanelOptions"); // NOI18N
        jPanelOptions.setLayout(new java.awt.BorderLayout());

        jToolBarOptions.setRollover(true);
        jToolBarOptions.setMinimumSize(new java.awt.Dimension(700, 95));
        jToolBarOptions.setName("jToolBarOptions"); // NOI18N
        jToolBarOptions.setPreferredSize(new java.awt.Dimension(700, 95));

        jPanelColorContents.setAlignmentX(0.0F);
        jPanelColorContents.setAlignmentY(0.0F);
        jPanelColorContents.setFocusable(false);
        jPanelColorContents.setName("jPanelColorContents"); // NOI18N
        jPanelColorContents.setOpaque(false);
        jPanelColorContents.setLayout(new javax.swing.BoxLayout(jPanelColorContents, javax.swing.BoxLayout.Y_AXIS));

        jLabelColors.setText(bundle.getString("COLORS")); // NOI18N
        jLabelColors.setName("jLabelColors"); // NOI18N
        jPanelColorContents.add(jLabelColors);

        jPanelColors.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanelColors.setAlignmentX(0.0F);
        jPanelColors.setAlignmentY(0.0F);
        jPanelColors.setEnabled(false);
        jPanelColors.setFocusable(false);
        jPanelColors.setMaximumSize(new java.awt.Dimension(100, 60));
        jPanelColors.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanelColors.setName("null");
        jPanelColors.setOpaque(false);
        jPanelColors.setPreferredSize(new java.awt.Dimension(100, 60));
        jPanelColors.setLayout(new java.awt.GridLayout(2, 3, 5, 5));

        jButtonNegro.setBackground(new java.awt.Color(0, 0, 0));
        jButtonNegro.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonNegro.setBorderPainted(false);
        jButtonNegro.setMaximumSize(new java.awt.Dimension(20, 20));
        jButtonNegro.setMinimumSize(new java.awt.Dimension(20, 20));
        jButtonNegro.setName("jButtonNegro"); // NOI18N
        jButtonNegro.setPreferredSize(new java.awt.Dimension(20, 20));
        jButtonNegro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNegroActionPerformed(evt);
            }
        });
        jPanelColors.add(jButtonNegro);

        jButtonBlanco.setBackground(new java.awt.Color(255, 255, 255));
        jButtonBlanco.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonBlanco.setBorderPainted(false);
        jButtonBlanco.setMaximumSize(new java.awt.Dimension(20, 20));
        jButtonBlanco.setMinimumSize(new java.awt.Dimension(20, 20));
        jButtonBlanco.setName("jButtonBlanco"); // NOI18N
        jButtonBlanco.setPreferredSize(new java.awt.Dimension(20, 20));
        jButtonBlanco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBlancoActionPerformed(evt);
            }
        });
        jPanelColors.add(jButtonBlanco);

        jButtonRojo.setBackground(new java.awt.Color(255, 0, 0));
        jButtonRojo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonRojo.setBorderPainted(false);
        jButtonRojo.setMaximumSize(new java.awt.Dimension(20, 20));
        jButtonRojo.setMinimumSize(new java.awt.Dimension(20, 20));
        jButtonRojo.setName("jButtonRojo"); // NOI18N
        jButtonRojo.setPreferredSize(new java.awt.Dimension(20, 20));
        jButtonRojo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRojoActionPerformed(evt);
            }
        });
        jPanelColors.add(jButtonRojo);

        jButtonVerde.setBackground(new java.awt.Color(0, 255, 0));
        jButtonVerde.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonVerde.setBorderPainted(false);
        jButtonVerde.setMaximumSize(new java.awt.Dimension(20, 20));
        jButtonVerde.setMinimumSize(new java.awt.Dimension(20, 20));
        jButtonVerde.setName("jButtonVerde"); // NOI18N
        jButtonVerde.setPreferredSize(new java.awt.Dimension(20, 20));
        jButtonVerde.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVerdeActionPerformed(evt);
            }
        });
        jPanelColors.add(jButtonVerde);

        jButtonAzul.setBackground(new java.awt.Color(0, 0, 255));
        jButtonAzul.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAzul.setBorderPainted(false);
        jButtonAzul.setMaximumSize(new java.awt.Dimension(20, 20));
        jButtonAzul.setMinimumSize(new java.awt.Dimension(20, 20));
        jButtonAzul.setName("jButtonAzul"); // NOI18N
        jButtonAzul.setPreferredSize(new java.awt.Dimension(20, 20));
        jButtonAzul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAzulActionPerformed(evt);
            }
        });
        jPanelColors.add(jButtonAzul);

        jButtonAmarillo.setBackground(new java.awt.Color(255, 255, 0));
        jButtonAmarillo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jButtonAmarillo.setBorderPainted(false);
        jButtonAmarillo.setMaximumSize(new java.awt.Dimension(20, 20));
        jButtonAmarillo.setMinimumSize(new java.awt.Dimension(20, 20));
        jButtonAmarillo.setName("jButtonAmarillo"); // NOI18N
        jButtonAmarillo.setPreferredSize(new java.awt.Dimension(20, 20));
        jButtonAmarillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAmarilloActionPerformed(evt);
            }
        });
        jPanelColors.add(jButtonAmarillo);

        jPanelColorContents.add(jPanelColors);
        jPanelColors.getAccessibleContext().setAccessibleName("null");

        jToolBarOptions.add(jPanelColorContents);

        filler12.setName("filler12"); // NOI18N
        jToolBarOptions.add(filler12);

        jPanelSlidersContents.setAlignmentX(0.0F);
        jPanelSlidersContents.setAlignmentY(0.0F);
        jPanelSlidersContents.setEnabled(false);
        jPanelSlidersContents.setFocusable(false);
        jPanelSlidersContents.setMaximumSize(new java.awt.Dimension(115, 75));
        jPanelSlidersContents.setMinimumSize(new java.awt.Dimension(115, 75));
        jPanelSlidersContents.setName("jPanelSlidersContents"); // NOI18N
        jPanelSlidersContents.setOpaque(false);
        jPanelSlidersContents.setPreferredSize(new java.awt.Dimension(115, 75));
        jPanelSlidersContents.setLayout(new javax.swing.BoxLayout(jPanelSlidersContents, javax.swing.BoxLayout.Y_AXIS));

        jLabelEstilo.setText(bundle.getString("STYLE")); // NOI18N
        jLabelEstilo.setName("jLabelEstilo"); // NOI18N
        jPanelSlidersContents.add(jLabelEstilo);

        jPanelSliders.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanelSliders.setAlignmentX(0.0F);
        jPanelSliders.setAlignmentY(0.0F);
        jPanelSliders.setEnabled(false);
        jPanelSliders.setFocusable(false);
        jPanelSliders.setMaximumSize(new java.awt.Dimension(115, 60));
        jPanelSliders.setMinimumSize(new java.awt.Dimension(100, 60));
        jPanelSliders.setName("jPanelSliders"); // NOI18N
        jPanelSliders.setOpaque(false);
        jPanelSliders.setPreferredSize(new java.awt.Dimension(100, 60));

        jLabelGrosorEtiqueta.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelGrosorEtiqueta.setText(bundle.getString("GROSOR:")); // NOI18N
        jLabelGrosorEtiqueta.setName("jLabelGrosorEtiqueta"); // NOI18N
        jLabelGrosorEtiqueta.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jLabelGrosorEtiquetaMouseWheelMoved(evt);
            }
        });
        jPanelSliders.add(jLabelGrosorEtiqueta);

        jLabelGrosor.setText(bundle.getString("STROKE_VALUE_LABEL")); // NOI18N
        jLabelGrosor.setMaximumSize(new java.awt.Dimension(25, 15));
        jLabelGrosor.setMinimumSize(new java.awt.Dimension(25, 15));
        jLabelGrosor.setName("jLabelGrosor"); // NOI18N
        jLabelGrosor.setPreferredSize(new java.awt.Dimension(25, 15));
        jLabelGrosor.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jLabelGrosorMouseWheelMoved(evt);
            }
        });
        jPanelSliders.add(jLabelGrosor);

        jSliderGrosor.setMinimum(4);
        jSliderGrosor.setMinorTickSpacing(1);
        jSliderGrosor.setPaintLabels(true);
        jSliderGrosor.setToolTipText(bundle.getString("TOOLTIPS_GROSOR")); // NOI18N
        jSliderGrosor.setValue(4);
        jSliderGrosor.setAlignmentX(0.0F);
        jSliderGrosor.setAlignmentY(0.0F);
        jSliderGrosor.setMaximumSize(null);
        jSliderGrosor.setMinimumSize(null);
        jSliderGrosor.setName("jSliderGrosor"); // NOI18N
        jSliderGrosor.setPreferredSize(new java.awt.Dimension(100, 20));
        jSliderGrosor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSliderGrosorStateChanged(evt);
            }
        });
        jSliderGrosor.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jSliderGrosorMouseWheelMoved(evt);
            }
        });
        jPanelSliders.add(jSliderGrosor);

        jPanelSlidersContents.add(jPanelSliders);

        jToolBarOptions.add(jPanelSlidersContents);

        filler9.setName("filler9"); // NOI18N
        jToolBarOptions.add(filler9);

        jPanelCheckBoxContents.setAlignmentY(0.0F);
        jPanelCheckBoxContents.setFocusable(false);
        jPanelCheckBoxContents.setName("jPanelCheckBoxContents"); // NOI18N
        jPanelCheckBoxContents.setOpaque(false);
        jPanelCheckBoxContents.setLayout(new javax.swing.BoxLayout(jPanelCheckBoxContents, javax.swing.BoxLayout.Y_AXIS));

        jLabelOpciones.setText(bundle.getString("OPTIONS")); // NOI18N
        jLabelOpciones.setName("jLabelOpciones"); // NOI18N
        jPanelCheckBoxContents.add(jLabelOpciones);

        jPanelCheckBoxs.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanelCheckBoxs.setAlignmentX(0.0F);
        jPanelCheckBoxs.setAlignmentY(0.0F);
        jPanelCheckBoxs.setEnabled(false);
        jPanelCheckBoxs.setFocusable(false);
        jPanelCheckBoxs.setMaximumSize(new java.awt.Dimension(270, 60));
        jPanelCheckBoxs.setMinimumSize(new java.awt.Dimension(270, 60));
        jPanelCheckBoxs.setName("jPanelCheckBoxs"); // NOI18N
        jPanelCheckBoxs.setOpaque(false);
        jPanelCheckBoxs.setPreferredSize(new java.awt.Dimension(270, 60));
        jPanelCheckBoxs.setLayout(new java.awt.GridLayout(2, 2));

        jCheckBoxRelleno.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jCheckBoxRelleno.setText(bundle.getString("FILL")); // NOI18N
        jCheckBoxRelleno.setName("jCheckBoxRelleno"); // NOI18N
        jCheckBoxRelleno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxRellenoActionPerformed(evt);
            }
        });
        jPanelCheckBoxs.add(jCheckBoxRelleno);

        jCheckBoxTransparencia.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jCheckBoxTransparencia.setText(bundle.getString("TRANSPARENCY")); // NOI18N
        jCheckBoxTransparencia.setName("jCheckBoxTransparencia"); // NOI18N
        jCheckBoxTransparencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxTransparenciaActionPerformed(evt);
            }
        });
        jPanelCheckBoxs.add(jCheckBoxTransparencia);

        jCheckBoxAlisar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jCheckBoxAlisar.setText(bundle.getString("FLATTEN")); // NOI18N
        jCheckBoxAlisar.setName("jCheckBoxAlisar"); // NOI18N
        jCheckBoxAlisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxAlisarActionPerformed(evt);
            }
        });
        jPanelCheckBoxs.add(jCheckBoxAlisar);

        jCheckBoxEditar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jCheckBoxEditar.setText(bundle.getString("CHECKBOX_EDIT")); // NOI18N
        jCheckBoxEditar.setName("jCheckBoxEditar"); // NOI18N
        jPanelCheckBoxs.add(jCheckBoxEditar);

        jPanelCheckBoxContents.add(jPanelCheckBoxs);

        jToolBarOptions.add(jPanelCheckBoxContents);

        jPanelOptions.add(jToolBarOptions, java.awt.BorderLayout.CENTER);

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

        jPanelOptions.add(jPanelStatusBar, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanelOptions, java.awt.BorderLayout.SOUTH);

        jDesktopEscritorio.setName("jDesktopEscritorio"); // NOI18N
        getContentPane().add(jDesktopEscritorio, java.awt.BorderLayout.CENTER);

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

        jSeparator1MArchivo.setMinimumSize(new java.awt.Dimension(10, 10));
        jSeparator1MArchivo.setName("jSeparator1MArchivo"); // NOI18N
        jMenuArchivo.add(jSeparator1MArchivo);

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

        jMenuOpciones.setText(bundle.getString("OPTIONS")); // NOI18N
        jMenuOpciones.setName("jMenuOpciones"); // NOI18N

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
        // esta barra la quito por ahora para usarla mas adelante en las practicas finales
        this.jToolBarOptions.setVisible(false);
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
                this.vi = new VentanaInterna(this, new Dimension(250, 250));
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
                System.err.println("Error al leer la imagen");
            }
        }

    }//GEN-LAST:event_jMenuItemAbrirActionPerformed

    private void jMenuItemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemGuardarActionPerformed
        this.vi = (VentanaInterna) this.jDesktopEscritorio.getSelectedFrame();
        if (this.vi != null) {
            FileFilter jpg_filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg");
            FileFilter png_filter = new FileNameExtensionFilter("PNG file", "png");
            FileFilter gif_filter = new FileNameExtensionFilter("GIF file", "gif");
            JFileChooser dlg = new JFileChooser();
            dlg.addChoosableFileFilter(jpg_filter);
            dlg.addChoosableFileFilter(png_filter);
            dlg.addChoosableFileFilter(gif_filter);
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
        this.jToolBarOptions.setVisible(this.jCheckBoxMenuItemVerBarraUtilidades.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemVerBarraUtilidadesActionPerformed

    private void jCheckBoxMenuItemVerBarraEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemVerBarraEstadoActionPerformed
        this.jPanelStatusBar.setVisible(this.jCheckBoxMenuItemVerBarraEstado.isSelected());
    }//GEN-LAST:event_jCheckBoxMenuItemVerBarraEstadoActionPerformed

    private void jCheckBoxMenuItemPantallaCompletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMenuItemPantallaCompletaActionPerformed
        this.jCheckBoxMenuItemVerBarraUtilidades.setSelected(!this.jCheckBoxMenuItemVerBarraUtilidades.isSelected());
        this.jCheckBoxMenuItemVerBarraHerramientas.setSelected(!this.jCheckBoxMenuItemVerBarraHerramientas.isSelected());
        this.jCheckBoxMenuItemVerBarraEstado.setSelected(!this.jCheckBoxMenuItemVerBarraEstado.isSelected());
        this.jToolBarOptions.setVisible(this.jCheckBoxMenuItemVerBarraUtilidades.isSelected());
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
                this.jSpinnerGrosor.setValue((int) this.jSpinnerGrosor.getValue() + 1);
            }
        } else if ((int) this.jSpinnerGrosor.getValue() > VentanaPrincipal.MIN_VALUE_SPPINER) {
            this.jSpinnerGrosor.setValue((int) this.jSpinnerGrosor.getValue() - 1);
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
                this.jSpinnerTransparencia.setValue((int) this.jSpinnerTransparencia.getValue() + 1);
            }
        } else if ((int) this.jSpinnerTransparencia.getValue() > VentanaPrincipal.MIN_VALUE_SPPINER) {
            this.jSpinnerTransparencia.setValue((int) this.jSpinnerTransparencia.getValue() - 1);
        }
    }//GEN-LAST:event_jSpinnerTransparenciaMouseWheelMoved

    private void jToggleButtonRellenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonRellenoActionPerformed
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        // poner aquí para rellenar
    }//GEN-LAST:event_jToggleButtonRellenoActionPerformed

    private void jToggleButtonAntiAliasingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonAntiAliasingActionPerformed
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        this.vi.getLienzo().setFlatten(this.jToggleButtonAntiAliasing.isSelected());
    }//GEN-LAST:event_jToggleButtonAntiAliasingActionPerformed

    private void jButtonNegroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNegroActionPerformed
        this.jLabelColor.setText(bundle.getString("COLOR_BLACK"));
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        this.vi.getLienzo().setColor(Color.black);
    }//GEN-LAST:event_jButtonNegroActionPerformed

    private void jButtonBlancoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBlancoActionPerformed
        this.jLabelColor.setText(bundle.getString("COLOR_WITHE"));
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        this.vi.getLienzo().setColor(Color.white);
    }//GEN-LAST:event_jButtonBlancoActionPerformed

    private void jButtonRojoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRojoActionPerformed
        this.jLabelColor.setText(bundle.getString("COLOR_RED"));
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        this.vi.getLienzo().setColor(Color.red);
    }//GEN-LAST:event_jButtonRojoActionPerformed

    private void jButtonVerdeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVerdeActionPerformed
        this.jLabelColor.setText(bundle.getString("COLOR_GREEN"));
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        this.vi.getLienzo().setColor(Color.green);
    }//GEN-LAST:event_jButtonVerdeActionPerformed

    private void jButtonAzulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAzulActionPerformed
        this.jLabelColor.setText(bundle.getString("COLOR_BLUE"));
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        this.vi.getLienzo().setColor(Color.blue);
    }//GEN-LAST:event_jButtonAzulActionPerformed

    private void jButtonAmarilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAmarilloActionPerformed
        this.jLabelColor.setText(bundle.getString("COLOR_YELLOW"));
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        this.vi.getLienzo().setColor(Color.yellow);
    }//GEN-LAST:event_jButtonAmarilloActionPerformed

    private void jLabelGrosorEtiquetaMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jLabelGrosorEtiquetaMouseWheelMoved
        if (evt.getPreciseWheelRotation() < 0) {
            this.jSliderGrosor.setValue(this.jSliderGrosor.getValue() + 1);
            //javax.swing.JOptionPane.showMessageDialog(null, "La ruleta va hacia arriba");
        } else {
            this.jSliderGrosor.setValue(this.jSliderGrosor.getValue() - 1);
            //javax.swing.JOptionPane.showMessageDialog(null, "La ruleta va hacia abajo");
        }
    }//GEN-LAST:event_jLabelGrosorEtiquetaMouseWheelMoved

    private void jLabelGrosorMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jLabelGrosorMouseWheelMoved
        if (evt.getPreciseWheelRotation() < 0) {
            this.jSliderGrosor.setValue(this.jSliderGrosor.getValue() + 1);
            //javax.swing.JOptionPane.showMessageDialog(null, "La ruleta va hacia arriba");
        } else {
            this.jSliderGrosor.setValue(this.jSliderGrosor.getValue() - 1);
            //javax.swing.JOptionPane.showMessageDialog(null, "La ruleta va hacia abajo");
        }
    }//GEN-LAST:event_jLabelGrosorMouseWheelMoved

    private void jSliderGrosorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSliderGrosorStateChanged
        this.jLabelGrosor.setText("" + this.jSliderGrosor.getValue()); //NOI18N
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        this.vi.getLienzo().setStroke(this.jSliderGrosor.getValue());
    }//GEN-LAST:event_jSliderGrosorStateChanged

    private void jSliderGrosorMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jSliderGrosorMouseWheelMoved
        if (evt.getPreciseWheelRotation() < 0) {
            this.jSliderGrosor.setValue(this.jSliderGrosor.getValue() + 1);
            //javax.swing.JOptionPane.showMessageDialog(null, "La ruleta va hacia arriba");
        } else {
            this.jSliderGrosor.setValue(this.jSliderGrosor.getValue() - 1);
            //javax.swing.JOptionPane.showMessageDialog(null, "La ruleta va hacia abajo");
        }
    }//GEN-LAST:event_jSliderGrosorMouseWheelMoved

    private void jCheckBoxRellenoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxRellenoActionPerformed
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        // poner aquí para rellenar
    }//GEN-LAST:event_jCheckBoxRellenoActionPerformed

    private void jCheckBoxTransparenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxTransparenciaActionPerformed
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        if (this.jCheckBoxTransparencia.isSelected()) {
            this.vi.getLienzo().setAlpha(50);
        } else {
            this.vi.getLienzo().setAlpha(100);
        }
    }//GEN-LAST:event_jCheckBoxTransparenciaActionPerformed

    private void jCheckBoxAlisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxAlisarActionPerformed
        this.vi = (VentanaInterna) jDesktopEscritorio.getSelectedFrame();
        this.vi.getLienzo().setFlatten(this.jCheckBoxAlisar.isSelected());
    }//GEN-LAST:event_jCheckBoxAlisarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupIdioma;
    private javax.swing.ButtonGroup buttonGroupOpciones;
    private javax.swing.ButtonGroup buttonGroupTools;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JButton jButtonAbrir;
    private javax.swing.JButton jButtonAmarillo;
    private javax.swing.JButton jButtonAzul;
    private javax.swing.JButton jButtonBlanco;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonNegro;
    private javax.swing.JButton jButtonNuevo;
    private javax.swing.JButton jButtonRojo;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JButton jButtonVerde;
    private javax.swing.JCheckBox jCheckBoxAlisar;
    private javax.swing.JCheckBox jCheckBoxEditar;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemPantallaCompleta;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVerBarraEstado;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVerBarraHerramientas;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItemVerBarraUtilidades;
    private javax.swing.JCheckBox jCheckBoxRelleno;
    private javax.swing.JCheckBox jCheckBoxTransparencia;
    protected javax.swing.JComboBox<String> jComboBoxColor;
    private javax.swing.JDesktopPane jDesktopEscritorio;
    protected javax.swing.JLabel jLabelAccionRaton;
    protected javax.swing.JLabel jLabelColor;
    private javax.swing.JLabel jLabelColors;
    private javax.swing.JLabel jLabelEstilo;
    private javax.swing.JLabel jLabelGrosor;
    private javax.swing.JLabel jLabelGrosorEtiqueta;
    protected javax.swing.JLabel jLabelHerramienta;
    private javax.swing.JLabel jLabelImageColor;
    private javax.swing.JLabel jLabelImageGrosor;
    protected javax.swing.JLabel jLabelMousePos;
    private javax.swing.JLabel jLabelOpciones;
    private javax.swing.JLabel jLabelTransparente;
    private javax.swing.JMenu jMenuArchivo;
    private javax.swing.JMenu jMenuAyuda;
    private javax.swing.JMenuBar jMenuBarra;
    private javax.swing.JMenu jMenuEditar;
    private javax.swing.JMenu jMenuIdioma;
    private javax.swing.JMenuItem jMenuItemAbrir;
    private javax.swing.JMenuItem jMenuItemAyuda;
    private javax.swing.JMenuItem jMenuItemCopiar;
    private javax.swing.JMenuItem jMenuItemCortar;
    private javax.swing.JMenuItem jMenuItemGuardar;
    private javax.swing.JMenuItem jMenuItemNuevo;
    private javax.swing.JMenuItem jMenuItemPegar;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenu jMenuOpciones;
    private javax.swing.JMenu jMenuVer;
    private javax.swing.JPanel jPanelCheckBoxContents;
    private javax.swing.JPanel jPanelCheckBoxs;
    private javax.swing.JPanel jPanelColorContents;
    private javax.swing.JPanel jPanelColors;
    private javax.swing.JPanel jPanelOptions;
    private javax.swing.JPanel jPanelSliders;
    private javax.swing.JPanel jPanelSlidersContents;
    private javax.swing.JPanel jPanelStatusBar;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemEN;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItemES;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator1MArchivo;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JSlider jSliderGrosor;
    protected javax.swing.JSpinner jSpinnerGrosor;
    protected javax.swing.JSpinner jSpinnerTransparencia;
    protected javax.swing.JToggleButton jToggleButtonAntiAliasing;
    protected javax.swing.JToggleButton jToggleButtonCirculo;
    protected javax.swing.JToggleButton jToggleButtonLinea;
    protected javax.swing.JToggleButton jToggleButtonMover;
    protected javax.swing.JToggleButton jToggleButtonPintar;
    protected javax.swing.JToggleButton jToggleButtonPunto;
    protected javax.swing.JToggleButton jToggleButtonRectangulo;
    protected javax.swing.JToggleButton jToggleButtonRelleno;
    private javax.swing.JToolBar jToolBarOptions;
    private javax.swing.JToolBar jToolBarTools;
    // End of variables declaration//GEN-END:variables
}
