package gui;

import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Carlos de al Torre
 */
public class PlayerNamesCapture extends javax.swing.JDialog {

    /**
     * Estos son los nombres de los jugadores que se capturan y se pasan al
     * controlador del juego para poder usarlos.
     */
    private final ArrayList<String> names = new ArrayList<>();

    /**
     * Función auxiliar que realiza la acción de comenzar el juego, ponemos aquí
     * el comienzo del juego asi de esa manera podemos llamar a comenzar desde
     * cualquier botón u evento que necesitemos.
     */
    private void Comenzar() {
        names.add(jT_Jugador1.getText());
        names.add(jT_Jugador2.getText());
        if (jCB_4Jugadores.isSelected()) {
            if (!jT_Jugador3.getText().equals("...")) {
                names.add(jT_Jugador3.getText());
            }
            names.add(jT_Jugador4.getText());
            if (!jT_Jugador3.getText().equals("...")) {
                names.add(jT_Jugador3.getText());
            }
        }
        //this.sonido("");
        this.dispose();
    }

    /**
     * Función auxiliar que se usa para poder leer el fichero de configuración
     * de los diferentes mazos de cartas cuando se usa el juego en un fichero
     * compilado .jar.
     *
     * @param recurso esta es una cadena de texto que será donde se encuentra el
     * fichero
     * @return devuelve un objeto File con el fichero que le hemos pasado por
     * parametros
     */
    private File ficheroExterno(String recurso) {
        File fichero = null;
        URL res = getClass().getResource(recurso);
        if (res.toString().startsWith("jar:")) {
            try {
                InputStream input = getClass().getResourceAsStream(recurso);
                fichero = File.createTempFile("tempfile", ".tmp");
                OutputStream out = new FileOutputStream(fichero);
                int read;
                byte[] bytes = new byte[1024];

                while ((read = input.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                fichero.deleteOnExit();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            //this will probably work in your IDE, but not from a JAR
            fichero = new File(res.getFile());
        }

        if (fichero != null && !fichero.exists()) {
            throw new RuntimeException("Error: File " + fichero + " not found!");
        }
        return fichero;
    }

    /**
     * Este es el manejador de sonidos de la interfaz gráfica, simplemente se le
     * decimos que sonido queremos reproducir en cada momento y listo.
     *
     * @param sonido El nombre del sonido que queremos que reproduzca
     */
    private void sonido(String sonido) {
        AudioInputStream clipNameAIS;
        Clip clipNameClip;
        String fichero = "/resources/comenzar.wav";

        if (!sonido.isEmpty()) {
            fichero = "/resources/" + sonido + ".wav";
        }
        //File file = this.ficheroExterno(fichero);
        try {
            clipNameAIS = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(fichero));
            clipNameClip = AudioSystem.getClip();
            clipNameClip.open(clipNameAIS);
            //Action where .wav is played
            clipNameClip.setFramePosition(0);
            clipNameClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(PlayerNamesCapture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Creates new form PlayerNamesCapture
     *
     * @param parent Ventana padre del cuadro de dialogo.
     * @param modal parametro para especificar si es modal.
     */
    public PlayerNamesCapture(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        // Con esto conseguimos que siempre aparezca en el centro de la pantalla
        int ancho = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2 - this.getWidth() / 2;
        int alto = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2 - this.getHeight() / 2;
        setBounds(ancho, alto, this.getWidth(), this.getHeight());
        // con esto hacemos que el foco se ponga en el primer campo de escritura
        this.jT_Jugador1.requestFocus();
        // con esto ponemos el 3er y 4to jugador no visible al mostrar el dialogo
        jL_Jugador3.setVisible(false);
        jT_Jugador3.setVisible(false);
        jL_Jugador4.setVisible(false);
        jT_Jugador4.setVisible(false);
    }

    /**
     * Función que nos permite recuperar los datos que ha metido el usuario en
     * los campos del formulario.
     *
     * @return Devuelve un Array con los nombres que se han introducido.
     */
    public ArrayList<String> getNames() {
        this.setVisible(true);
        return names;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCB_4Jugadores = new javax.swing.JCheckBox();
        jL_Jugador1 = new javax.swing.JLabel();
        jT_Jugador1 = new javax.swing.JTextField();
        jL_Jugador2 = new javax.swing.JLabel();
        jT_Jugador2 = new javax.swing.JTextField();
        jL_Jugador3 = new javax.swing.JLabel();
        jT_Jugador3 = new javax.swing.JTextField();
        jL_Jugador4 = new javax.swing.JLabel();
        jT_Jugador4 = new javax.swing.JTextField();
        jB_Cancelar = new javax.swing.JButton();
        jB_Comenzar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("¿Cuales son los Nombres?");
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(272, 240));
        setName("PlayerNamesCapture"); // NOI18N
        setPreferredSize(new java.awt.Dimension(400, 270));

        jCB_4Jugadores.setText("Cuatro Jugadores");
        jCB_4Jugadores.setToolTipText("Si lo marcas pueden jugar 4 Jugadores");
        jCB_4Jugadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB_4JugadoresActionPerformed(evt);
            }
        });

        jL_Jugador1.setLabelFor(jL_Jugador1);
        jL_Jugador1.setText("Jugador1");

        jT_Jugador1.setText("...");
        jT_Jugador1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jT_Jugador1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jT_Jugador1FocusLost(evt);
            }
        });
        jT_Jugador1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jT_Jugador1ActionPerformed(evt);
            }
        });

        jL_Jugador2.setLabelFor(jL_Jugador2);
        jL_Jugador2.setText("Jugador2");

        jT_Jugador2.setText("...");
        jT_Jugador2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jT_Jugador2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jT_Jugador2FocusLost(evt);
            }
        });
        jT_Jugador2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jT_Jugador2ActionPerformed(evt);
            }
        });

        jL_Jugador3.setLabelFor(jL_Jugador3);
        jL_Jugador3.setText("Jugador3");

        jT_Jugador3.setText("...");
        jT_Jugador3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jT_Jugador3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jT_Jugador3FocusLost(evt);
            }
        });
        jT_Jugador3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jT_Jugador3ActionPerformed(evt);
            }
        });

        jL_Jugador4.setLabelFor(jT_Jugador4);
        jL_Jugador4.setText("Jugador4");

        jT_Jugador4.setText("...");
        jT_Jugador4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jT_Jugador4FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jT_Jugador4FocusLost(evt);
            }
        });
        jT_Jugador4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jT_Jugador4ActionPerformed(evt);
            }
        });

        jB_Cancelar.setText("Cancelar");
        jB_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_CancelarActionPerformed(evt);
            }
        });

        jB_Comenzar.setText("Comenzar");
        jB_Comenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_ComenzarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCB_4Jugadores)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jB_Cancelar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                            .addComponent(jB_Comenzar))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jL_Jugador4)
                                .addComponent(jL_Jugador3)
                                .addComponent(jL_Jugador2)
                                .addComponent(jL_Jugador1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jT_Jugador1)
                                .addComponent(jT_Jugador2)
                                .addComponent(jT_Jugador3)
                                .addComponent(jT_Jugador4)))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jCB_4Jugadores)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_Jugador1)
                    .addComponent(jT_Jugador1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_Jugador2)
                    .addComponent(jT_Jugador2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_Jugador3)
                    .addComponent(jT_Jugador3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jL_Jugador4)
                    .addComponent(jT_Jugador4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jB_Cancelar)
                    .addComponent(jB_Comenzar))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCB_4JugadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB_4JugadoresActionPerformed
        if (jCB_4Jugadores.isSelected()) {
            jL_Jugador3.setVisible(true);
            jT_Jugador3.setVisible(true);
            jL_Jugador4.setVisible(true);
            jT_Jugador4.setVisible(true);
        } else {
            jL_Jugador3.setVisible(false);
            jT_Jugador3.setVisible(false);
            jL_Jugador4.setVisible(false);
            jT_Jugador4.setVisible(false);
        }
    }//GEN-LAST:event_jCB_4JugadoresActionPerformed

    private void jT_Jugador1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_Jugador1FocusGained
        if (jT_Jugador1.getText().equals("...")) {
            jT_Jugador1.setText("");
        } else {
            jT_Jugador1.selectAll();
        }
    }//GEN-LAST:event_jT_Jugador1FocusGained

    private void jT_Jugador2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_Jugador2FocusGained
        if (jT_Jugador2.getText().equals("...")) {
            jT_Jugador2.setText("");
        } else {
            jT_Jugador2.selectAll();
        }
    }//GEN-LAST:event_jT_Jugador2FocusGained

    private void jT_Jugador3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_Jugador3FocusGained
        if (jT_Jugador3.getText().equals("...")) {
            jT_Jugador3.setText("");
        } else {
            jT_Jugador3.selectAll();
        }
    }//GEN-LAST:event_jT_Jugador3FocusGained

    private void jT_Jugador4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_Jugador4FocusGained
        if (jT_Jugador4.getText().equals("...")) {
            jT_Jugador4.setText("");
        } else {
            jT_Jugador4.selectAll();
        }
    }//GEN-LAST:event_jT_Jugador4FocusGained

    private void jB_ComenzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_ComenzarActionPerformed
        this.Comenzar();
    }//GEN-LAST:event_jB_ComenzarActionPerformed

    private void jB_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_CancelarActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jB_CancelarActionPerformed

    private void jT_Jugador1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_Jugador1FocusLost
        if (jT_Jugador1.getText().equals("")) {
            jT_Jugador1.setText("...");
        }
    }//GEN-LAST:event_jT_Jugador1FocusLost

    private void jT_Jugador2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_Jugador2FocusLost
        if (jT_Jugador2.getText().equals("")) {
            jT_Jugador2.setText("...");
        }
    }//GEN-LAST:event_jT_Jugador2FocusLost

    private void jT_Jugador3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_Jugador3FocusLost
        if (jT_Jugador3.getText().equals("")) {
            jT_Jugador3.setText("...");
        }
    }//GEN-LAST:event_jT_Jugador3FocusLost

    private void jT_Jugador4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jT_Jugador4FocusLost
        if (jT_Jugador4.getText().equals("")) {
            jT_Jugador4.setText("...");
        }
    }//GEN-LAST:event_jT_Jugador4FocusLost

    private void jT_Jugador1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jT_Jugador1ActionPerformed
        this.Comenzar();
    }//GEN-LAST:event_jT_Jugador1ActionPerformed

    private void jT_Jugador2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jT_Jugador2ActionPerformed
        this.Comenzar();
    }//GEN-LAST:event_jT_Jugador2ActionPerformed

    private void jT_Jugador3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jT_Jugador3ActionPerformed
        this.Comenzar();
    }//GEN-LAST:event_jT_Jugador3ActionPerformed

    private void jT_Jugador4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jT_Jugador4ActionPerformed
        this.Comenzar();
    }//GEN-LAST:event_jT_Jugador4ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB_Cancelar;
    private javax.swing.JButton jB_Comenzar;
    private javax.swing.JCheckBox jCB_4Jugadores;
    private javax.swing.JLabel jL_Jugador1;
    private javax.swing.JLabel jL_Jugador2;
    private javax.swing.JLabel jL_Jugador3;
    private javax.swing.JLabel jL_Jugador4;
    private javax.swing.JTextField jT_Jugador1;
    private javax.swing.JTextField jT_Jugador2;
    private javax.swing.JTextField jT_Jugador3;
    private javax.swing.JTextField jT_Jugador4;
    // End of variables declaration//GEN-END:variables
}
