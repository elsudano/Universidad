package gui.video;

import gui.VentanaPrincipal;
import gui.componets.Item;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.image.BufferedImage;
import javax.swing.event.InternalFrameListener;
import sm.cdlt.util.Utils;
import sm.cdlt.utils.File;
import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.binding.internal.libvlc_marquee_position_e;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.Marquee;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;

enum Estado {
    PLAY,
    PAUSE,
    STOP;
}

/**
 *
 * For contact with me visit https://www.sudano.net or send me a email
 * <a href="mailto:cdelatorre@correo.ugr.es">Carlos de al Torre</a>
 *
 * @author <a href="mailto:cdelatorre@correo.ugr.es">Carlos de al Torre</a>
 * created on 11-jun-2016
 */
public class VentanaInternaVLCPlayer extends javax.swing.JInternalFrame {

    /**
     * Ventana principal que es el padre de la ventana interna.
     */
    private VentanaPrincipal myParent;
    /**
     * Variable que informa si las librerías de VLC se encuentran instaladas en
     * el sistema
     */
    private boolean library_locate;
    /**
     * Componente en donde se podrá reproducir el objeto multimedia
     */
    private EmbeddedMediaPlayerComponent component_video;
    /**
     * Variable que almacena la superficie del componente de vídeo, osea que
     * pone una capa encima del vídeo para realizar diferentes acciones
     * @TODO esto es para añadirlo en la actualización y mejoras.
     */
    //private Canvas surface_component_video;
    /**
     * Variable que almacena la ruta donde se encuentra el fichero multimedia
     * que queremos reproducir
     */
    private File multimedia_file;
    /**
     * Variable que almacena en el estado que se encuentra el fichero
     * multimedia.
     */
    private Estado status;
    /**
     * Este será el tamaño de la ventana
     */
    private java.awt.Dimension tamanio;
    /**
     * Variable que almacena la posición en la lista del objeto.
     */
    private int position;

    /**
     * Creates new form VentanaInternaVLCPlayer only for the Netbeans IDE
     */
    public VentanaInternaVLCPlayer() {
        initComponents();
    }

    /**
     * Constructor con parámetros para darle mayor funcionalidad a la aplicación
     *
     * @param parent [in] Será la ventana padre de la ventana de vídeo.
     * @param d [in] Será la dimensión que tenga la ventana al crearse.
     * @param i [in] Será el objeto que queremos que se muestre en la ventana.
     */
    public VentanaInternaVLCPlayer(VentanaPrincipal parent, java.awt.Dimension d, Item i) {
        this.myParent = parent;
        this.tamanio = d;
        this.position = i.pos;
        initComponents();
        //this.library_locate = new NativeDiscovery().discover();
        if (this.library_locate && VentanaPrincipal.DEBUG) {
            System.out.println(LibVlc.INSTANCE.libvlc_get_version());
        }
        this.multimedia_file = new File(i.path);
        if (this.multimedia_file.getExt().contains("avi")
                || this.multimedia_file.getExt().contains("mpg")
                || this.multimedia_file.getExt().contains("mpeg")
                || this.multimedia_file.getExt().contains("mp4")
                || this.multimedia_file.getExt().contains("mkv")) {
            this.component_video = new EmbeddedMediaPlayerComponent() {
                @Override
                public void mouseWheelMoved(MouseWheelEvent evt) {
                    super.mouseWheelMoved(evt);
                    int volumen = this.getMediaPlayer().getVolume();
                    int count = 0;
                    String cadena = "";
                    while (count < volumen/15) {                        
                        cadena += "-\n";
                        count++;
                    }
                    Marquee marquee = Marquee.marquee()
                            .text(cadena)
                            .size(30)
                            .colour(Color.RED)
                            .timeout(1000)
                            .position(libvlc_marquee_position_e.bottom_right)
                            .opacity(0.8f)
                            .enable();
                    marquee.apply(this.getMediaPlayer());
                    if (evt.getPreciseWheelRotation() < 0 && volumen < 150) {
                        this.getMediaPlayer().setVolume(volumen + 5);
                    } else if (evt.getPreciseWheelRotation() > 0) {
                        this.getMediaPlayer().setVolume(volumen - 5);
                    }
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if (this.getMediaPlayer().isPlaying()) {
                        this.getMediaPlayer().pause();
                    } else {
                        this.getMediaPlayer().play();
                    }
                }

            };
            this.component_video.getMediaPlayer().addMediaPlayerEventListener(new VideoListener());
            this.add(this.component_video, java.awt.BorderLayout.CENTER);
            this.component_video.setVisible(true);
        } else {
            throw new IllegalArgumentException("La extención del fichero no es reconocida");
        }

    }

    /**
     * Con este método podemos darle el fichero que queremos que se reproduzca
     * en la ventana.
     *
     * @param f [in] Tipo File que contiene el fichero que se reproducirá en la
     * ventana.
     */
    public void setMultimediaFile(File f) {
        this.multimedia_file = f;
        this.status = Estado.STOP;
    }

    /**
     * Con este método podemos decirle al reproductor donde se encuentra el
     * fichero que queremos reproducir en la ventana.
     *
     * @param f [in] cadena de texto que contiene la ruta (path) donde se
     * encuentra el fichero a reproducir.
     */
    public void setMultimediaFile(String f) {
        this.multimedia_file = new File(f);
        this.status = Estado.STOP;
    }

    /**
     * Método que detiene la reproducción del archivo y lo cierra.
     */
    public void release() {
        this.component_video.getMediaPlayer().release();
    }

    /**
     * Método que asigna un manejador al objeto.
     *
     * @param l [in] Será el manejador que controle los eventos del objeto.
     */
    public void setListener(InternalFrameListener l) {
        this.addInternalFrameListener(l);
    }

    /**
     * Método con el cual podemos tomar una captura de lo que se esta viendo en
     * el vídeo.
     *
     * @return objeto BufferedImage con la imagen capturada.
     */
    public BufferedImage takePhoto() {
        return Utils.convertImageToType(this.component_video.getMediaPlayer().getSnapshot(), BufferedImage.TYPE_INT_RGB);
    }

    /**
     * Con este método podemos saber cual es la posición que ocupa el objeto en
     * la lista de reproducción para poder quitarlo de ella.
     *
     * @return entero que indica la posición del elemento en la lista multimedia.
     */
    public int getPositionList() {
        return this.position;
    }

    /**
     * Clase encargada de gestionar los eventos del objeto reproductor de la
     * ventana.
     */
    private class VideoListener extends MediaPlayerEventAdapter {

        @Override
        public void playing(MediaPlayer mediaPlayer) {
            super.playing(mediaPlayer);
            VentanaInternaVLCPlayer.this.jToggleButtonPlay.setSelected(true);
        }

        @Override
        public void paused(MediaPlayer mediaPlayer) {
            super.paused(mediaPlayer);
            VentanaInternaVLCPlayer.this.jToggleButtonPause.setSelected(true);
        }

        @Override
        public void forward(MediaPlayer mediaPlayer) {
            super.forward(mediaPlayer);
        }

        @Override
        public void positionChanged(MediaPlayer mediaPlayer, float newPosition) {
            super.positionChanged(mediaPlayer, newPosition);
            mediaPlayer.getTime();
            /*
            @TODO aqui capturas la posición para poder gestionar los eventos
            de la barra de progreso
            */
        }

        @Override
        public void finished(MediaPlayer mediaPlayer) {
            super.finished(mediaPlayer);
            VentanaInternaVLCPlayer.this.jToggleButtonStop.setSelected(true);
        }

        @Override
        public void error(MediaPlayer mediaPlayer) {
            super.error(mediaPlayer);
        }

        @Override
        public void muted(MediaPlayer mediaPlayer, boolean muted) {
            super.muted(mediaPlayer, muted);
            VentanaInternaVLCPlayer.this.jToggleButtonSilencio.setSelected(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupButtons = new javax.swing.ButtonGroup();
        jPanelContentControls = new javax.swing.JPanel();
        jPanelButtons = new javax.swing.JPanel();
        jButtonRewind = new javax.swing.JButton();
        jToggleButtonPlay = new javax.swing.JToggleButton();
        jToggleButtonPause = new javax.swing.JToggleButton();
        jToggleButtonStop = new javax.swing.JToggleButton();
        jButtonForward = new javax.swing.JButton();
        jPanelSlider = new javax.swing.JPanel();
        jProgressBar = new javax.swing.JProgressBar();
        jPanelExtra = new javax.swing.JPanel();
        jButtonVolumenDOWN = new javax.swing.JButton();
        jToggleButtonSilencio = new javax.swing.JToggleButton();
        jButtonVolumeUP = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Reproductor VLCj");
        setMinimumSize(this.tamanio);
        setPreferredSize(this.tamanio);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanelContentControls.setLayout(new java.awt.BorderLayout(2, 2));

        jPanelButtons.setLayout(new javax.swing.BoxLayout(jPanelButtons, javax.swing.BoxLayout.LINE_AXIS));

        jButtonRewind.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/retrocede.png"))); // NOI18N
        jButtonRewind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRewindActionPerformed(evt);
            }
        });
        jPanelButtons.add(jButtonRewind);

        buttonGroupButtons.add(jToggleButtonPlay);
        jToggleButtonPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/reproducir.png"))); // NOI18N
        jToggleButtonPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonPlayActionPerformed(evt);
            }
        });
        jPanelButtons.add(jToggleButtonPlay);

        buttonGroupButtons.add(jToggleButtonPause);
        jToggleButtonPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/pause.png"))); // NOI18N
        jToggleButtonPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonPauseActionPerformed(evt);
            }
        });
        jPanelButtons.add(jToggleButtonPause);

        buttonGroupButtons.add(jToggleButtonStop);
        jToggleButtonStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/parar.png"))); // NOI18N
        jToggleButtonStop.setSelected(true);
        jToggleButtonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonStopActionPerformed(evt);
            }
        });
        jPanelButtons.add(jToggleButtonStop);

        jButtonForward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/avanza.png"))); // NOI18N
        jButtonForward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonForwardActionPerformed(evt);
            }
        });
        jPanelButtons.add(jButtonForward);

        jPanelContentControls.add(jPanelButtons, java.awt.BorderLayout.WEST);

        jPanelSlider.setLayout(new java.awt.BorderLayout());
        jPanelSlider.add(jProgressBar, java.awt.BorderLayout.CENTER);

        jPanelContentControls.add(jPanelSlider, java.awt.BorderLayout.CENTER);

        jPanelExtra.setLayout(new javax.swing.BoxLayout(jPanelExtra, javax.swing.BoxLayout.LINE_AXIS));

        jButtonVolumenDOWN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/volumen_down.png"))); // NOI18N
        jButtonVolumenDOWN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolumenDOWNActionPerformed(evt);
            }
        });
        jPanelExtra.add(jButtonVolumenDOWN);

        jToggleButtonSilencio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/volumen_mute.png"))); // NOI18N
        jToggleButtonSilencio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonSilencioActionPerformed(evt);
            }
        });
        jPanelExtra.add(jToggleButtonSilencio);

        jButtonVolumeUP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/volumen_up.png"))); // NOI18N
        jButtonVolumeUP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVolumeUPActionPerformed(evt);
            }
        });
        jPanelExtra.add(jButtonVolumeUP);

        jPanelContentControls.add(jPanelExtra, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanelContentControls, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRewindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRewindActionPerformed
        this.component_video.getMediaPlayer().skip(-10000);
    }//GEN-LAST:event_jButtonRewindActionPerformed

    private void jToggleButtonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonPlayActionPerformed
        this.component_video.getMediaPlayer().play();
        this.status = Estado.PLAY;
    }//GEN-LAST:event_jToggleButtonPlayActionPerformed

    private void jToggleButtonPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonPauseActionPerformed
        this.component_video.getMediaPlayer().pause();
        this.status = Estado.PAUSE;
    }//GEN-LAST:event_jToggleButtonPauseActionPerformed

    private void jToggleButtonStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonStopActionPerformed
        this.component_video.getMediaPlayer().stop();
        this.status = Estado.STOP;
    }//GEN-LAST:event_jToggleButtonStopActionPerformed

    private void jButtonForwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonForwardActionPerformed
        this.component_video.getMediaPlayer().skip(10000);
    }//GEN-LAST:event_jButtonForwardActionPerformed

    private void jButtonVolumeUPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolumeUPActionPerformed
        this.component_video.getMediaPlayer().setVolume(this.component_video.getMediaPlayer().getVolume() + 15);
    }//GEN-LAST:event_jButtonVolumeUPActionPerformed

    private void jToggleButtonSilencioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonSilencioActionPerformed
        this.component_video.getMediaPlayer().mute(this.jToggleButtonSilencio.isSelected());
    }//GEN-LAST:event_jToggleButtonSilencioActionPerformed

    private void jButtonVolumenDOWNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVolumenDOWNActionPerformed
        this.component_video.getMediaPlayer().setVolume(this.component_video.getMediaPlayer().getVolume() - 15);
    }//GEN-LAST:event_jButtonVolumenDOWNActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        this.component_video.getMediaPlayer().playMedia(this.multimedia_file.getAbsolutePath());
        this.component_video.getMediaPlayer().stop();
        this.status = Estado.STOP;
    }//GEN-LAST:event_formInternalFrameOpened

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupButtons;
    private javax.swing.JButton jButtonForward;
    private javax.swing.JButton jButtonRewind;
    private javax.swing.JButton jButtonVolumeUP;
    private javax.swing.JButton jButtonVolumenDOWN;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JPanel jPanelContentControls;
    private javax.swing.JPanel jPanelExtra;
    private javax.swing.JPanel jPanelSlider;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JToggleButton jToggleButtonPause;
    private javax.swing.JToggleButton jToggleButtonPlay;
    private javax.swing.JToggleButton jToggleButtonSilencio;
    private javax.swing.JToggleButton jToggleButtonStop;
    // End of variables declaration//GEN-END:variables
}
