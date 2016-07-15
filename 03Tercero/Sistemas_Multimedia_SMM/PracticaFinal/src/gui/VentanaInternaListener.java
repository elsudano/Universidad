package gui;

import gui.image.VentanaInternaImagen;
import gui.video.VentanaInternaJMFPlayer;
import gui.video.VentanaInternaVLCPlayer;
import gui.video.VentanaInternaWebcam;
import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de la Torre</a>
 * created on 13-jun-2016
 *
 * Clase encargada de gestionar los diferentes eventos en la s diferentes
 * ventanas que tenemos en la aplicación, así pues, con esta clase conseguimos
 * que según la ventana que estemos usando en cada momento realicemos una acción
 * diferente según el evento que realice el usuario.
 *
 * Gestionamos la apertura, la maximización, la minimización, la activación
 * antes del cierre y el cierre de las ventanas de imágenes
 * (VentanaInternaImagen), cámara web (VentanaInternaWebCam), reproductor basado
 * en JMF (VentanaInternaJMFPlayer), reproductor basado en VLC
 * (VentanaInternaVLCPlayer) y la de acciones con conjuntos de imágenes
 * (VentanaInternaBlend).
 */
public class VentanaInternaListener extends javax.swing.event.InternalFrameAdapter {

    /**
     * Variable que almacena cual sera la ventana en la que se aplicaran las
     * diferentes acciones después de recibir los eventos.
     */
    private VentanaPrincipal myParent;
    /**
     * Variable que almacena la ventana interna con la que se esta trabajando
     * actualmente.
     */
    private JInternalFrame vi;

    /**
     * Constructor por defecto con un parámetro que indica la ventana padre.
     *
     * @param p [in] Ventana principal donde se aplicaran lo cambios.
     */
    public VentanaInternaListener(VentanaPrincipal p) {
        this.myParent = p;
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        super.internalFrameOpened(e);
        this.vi = e.getInternalFrame();
        if (this.vi instanceof VentanaInternaImagen) {

        } else if (this.vi instanceof VentanaInternaJMFPlayer) {

        } else if (this.vi instanceof VentanaInternaVLCPlayer) {

        } else if (this.vi instanceof VentanaInternaWebcam) {
            this.myParent.getButtonWebCam().setEnabled(false);
        }
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
        super.internalFrameActivated(e);
        this.vi = e.getInternalFrame();
        if (this.vi instanceof VentanaInternaImagen) {
            // el grosor de la linea
            this.myParent.getSpinnerGrosor().setValue((int) ((VentanaInternaImagen) this.vi).getLienzo().getStroke());
            // aquí ponemos si pintamos o movemos
            String aux_option = ((VentanaInternaImagen) this.vi).getLienzo().whatOptionSelected();
            switch (aux_option) {
                case "paint":
                    this.myParent.getToggleButtonPintar().setSelected(true);
                    this.myParent.getLabelAccionRaton().setText(VentanaPrincipal.getBundle().getString("MOUSE"));
                    break;
                case "move":
                    this.myParent.getToggleButtonMover().setSelected(true);
                    this.myParent.getLabelAccionRaton().setText(VentanaPrincipal.getBundle().getString("MOVE"));
                    break;
                case "delete":
                    this.myParent.getToggleButtonBorrar().setSelected(true);
                    this.myParent.getLabelAccionRaton().setText(VentanaPrincipal.getBundle().getString("DELETE"));
                    break;
                default:
                    break;
            }
            // si esta relleno
            if (((VentanaInternaImagen) this.vi).getLienzo().withFill()) {
                this.myParent.getToggleButtonRelleno().setSelected(true);
            } else {
                this.myParent.getToggleButtonRelleno().setSelected(false);
            }

            // si aplicamos alisado
            this.myParent.getToggleButtonAntiAliasing().setSelected(((VentanaInternaImagen) this.vi).getLienzo().getFlatten());
            // si tiene transparencia
            this.myParent.getSpinnerTransparencia().setValue(((VentanaInternaImagen) this.vi).getLienzo().getAlpha());

            // el color que esta elegido
            if (((VentanaInternaImagen) this.vi).getLienzo().getColor().equals(Color.BLACK)) {
                this.myParent.getComboBoxColor().setSelectedItem(Color.BLACK);
            } else if (((VentanaInternaImagen) this.vi).getLienzo().getColor().equals(Color.WHITE)) {
                this.myParent.getComboBoxColor().setSelectedItem(Color.WHITE);
            } else if (((VentanaInternaImagen) this.vi).getLienzo().getColor().equals(Color.RED)) {
                this.myParent.getComboBoxColor().setSelectedItem(Color.RED);
            } else if (((VentanaInternaImagen) this.vi).getLienzo().getColor().equals(Color.GREEN)) {
                this.myParent.getComboBoxColor().setSelectedItem(Color.GREEN);
            } else if (((VentanaInternaImagen) this.vi).getLienzo().getColor().equals(Color.BLUE)) {
                this.myParent.getComboBoxColor().setSelectedItem(Color.BLUE);
            } else if (((VentanaInternaImagen) this.vi).getLienzo().getColor().equals(Color.YELLOW)) {
                this.myParent.getComboBoxColor().setSelectedItem(Color.YELLOW);
            }
            this.myParent.getLabelColor().setText(VentanaPrincipal.getBundle().getString("COLOR")
                    + "[R: " + ((Color) this.myParent.getComboBoxColor().getSelectedItem()).getRed()
                    + " G: " + ((Color) this.myParent.getComboBoxColor().getSelectedItem()).getGreen()
                    + " B: " + ((Color) this.myParent.getComboBoxColor().getSelectedItem()).getBlue() + "]");
            // la herramienta que esta elegida
            switch (((VentanaInternaImagen) this.vi).getLienzo().getTool()) {
                case "point":
                    this.myParent.getToggleButtonPunto().setSelected(true);
                    this.myParent.getLabelHerramienta().setText(VentanaPrincipal.getBundle().getString("TOOL_POINT"));
                    break;
                case "line":
                    this.myParent.getToggleButtonLinea().setSelected(true);
                    this.myParent.getLabelHerramienta().setText(VentanaPrincipal.getBundle().getString("TOOL_LINE"));
                    break;
                case "rect":
                    this.myParent.getToggleButtonRectangulo().setSelected(true);
                    this.myParent.getLabelHerramienta().setText(VentanaPrincipal.getBundle().getString("TOOL_RECT"));
                    break;
                case "oval":
                    this.myParent.getToggleButtonCirculo().setSelected(true);
                    this.myParent.getLabelHerramienta().setText(VentanaPrincipal.getBundle().getString("TOOL_CIRCLE"));
                    break;
            }
        } else if (this.vi instanceof VentanaInternaJMFPlayer) {
            this.myParent.getButtonTakePhoto().setEnabled(true);
        } else if (this.vi instanceof VentanaInternaVLCPlayer) {
            this.myParent.getButtonTakePhoto().setEnabled(true);
        } else if (this.vi instanceof VentanaInternaWebcam) {
            this.myParent.getButtonTakePhoto().setEnabled(true);
        }
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
        super.internalFrameDeactivated(e);
        this.vi = e.getInternalFrame();
        if (this.vi instanceof VentanaInternaImagen) {

        } else if (this.vi instanceof VentanaInternaJMFPlayer) {
            this.myParent.getButtonTakePhoto().setEnabled(false);
        } else if (this.vi instanceof VentanaInternaVLCPlayer) {
            this.myParent.getButtonTakePhoto().setEnabled(false);
        } else if (this.vi instanceof VentanaInternaWebcam) {
            this.myParent.getButtonTakePhoto().setEnabled(false);
        }
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
        super.internalFrameClosing(e);
        this.vi = e.getInternalFrame();
        int idx, n = JOptionPane.showConfirmDialog(this.vi, "¿Seguro que quiere cerrar la ventana?", "Cerrar Ventana", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        // son 2 botones el si=0, el no=1
        if (n == 0) {
            if (this.vi instanceof VentanaInternaImagen) {

            } else if (this.vi instanceof VentanaInternaJMFPlayer) {
                ((VentanaInternaJMFPlayer) this.vi).stop();
                /*
                @TODO Como quitar el objeto del array por que cuando quitamos
                uno cualquiera ya no tenemos las mismas posiciones que al principio
                */
//                JList aux = this.myParent.getListSounds();
//                idx = ((VentanaInternaJMFPlayer) this.vi).getPositionList();
//                ((DefaultListModel) aux.getModel()).remove(idx);
//                this.myParent.multimedia_files.remove(idx);
            } else if (this.vi instanceof VentanaInternaVLCPlayer) {
                ((VentanaInternaVLCPlayer) this.vi).release();
//                JList aux = this.myParent.getListSounds();
//                idx = ((VentanaInternaVLCPlayer) this.vi).getPositionList();
//                ((DefaultListModel) aux.getModel()).remove(idx);
//                this.myParent.multimedia_files.remove(idx);
            } else if (this.vi instanceof VentanaInternaWebcam) {
                ((VentanaInternaWebcam) this.vi).stop();
                this.myParent.getButtonTakePhoto().setEnabled(false);
                this.myParent.getButtonWebCam().setEnabled(true);
            }
            this.vi.dispose();
        }
        if (VentanaPrincipal.DEBUG) {
            System.out.println("Se ha cerrado la ventana interna de tipo: " + e.getClass().getName());
        }
    }

}
