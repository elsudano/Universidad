package gui;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import napakalaki.CultistPlayer;
import napakalaki.Napakalaki;
import napakalaki.Player;
import napakalaki.Treasure;

/**
 *
 * @author Carlos de al Torre
 */
public class PlayerView extends javax.swing.JPanel {

    /**
     * Este será el modelo de la aplicacion es donde están las funcionalidades
     * del programa
     */
    Napakalaki napakalakiModel;
    /**
     * Este es el jugador que contiene todos los datos que se mostrarán en la
     * vista.
     */
    Player playerModel;

    /**
     * Con está función auxiliar lo que hacemos es ocultar soló los botones del
     * panel del jugador la usamos para cambiar su visibilidad según en la fase
     * que se encuentre el jugdor.
     */
    /**
     * Con esta función auxiliar conseguimos poner la interfaz con todos los
     * botones según corresponda en cada fase del juego.
     *
     * @param f será el numero que indica en que fase se encuentra el jugador.
     */
    protected void setFase(int f) {
        switch (f) {
            case 1:
                // Equiparse, Descartarse, Descartarse de todo
                this.jBEquiparTesoro.setEnabled(true);
                this.jBDescartarTesoro.setEnabled(true);
                this.jBDescartarTodos.setEnabled(true);
                // con esto habilitamos o deshabilitamos el boton para robar si corresponde
                this.jBRobarTesoro.setEnabled(this.playerModel.canISteal());
                break;
            case 2:
                // Ninguno habilitado
                this.jBEquiparTesoro.setEnabled(false);
                this.jBDescartarTesoro.setEnabled(false);
                this.jBDescartarTodos.setEnabled(false);
                this.jBRobarTesoro.setEnabled(false);
                break;
            case 3:
                // Descartarse, Descartarse de todo
                this.jBEquiparTesoro.setEnabled(false);
                this.jBDescartarTesoro.setEnabled(true);
                this.jBDescartarTodos.setEnabled(true);
                this.jBRobarTesoro.setEnabled(this.playerModel.canISteal());
                break;
        }
        repaint();
        revalidate();
    }

    void CambiarEstadoBotones() {

    }

    /**
     * Creamos un Objeto de tipo Jugador para la vista.
     */
    public PlayerView() {
        initComponents();
        this.jLBonusBasico.setVisible(false);
        this.jLBonusEspecial.setVisible(false);
        this.jTBonusBasico.setVisible(false);
        this.jTBonusEspecial.setVisible(false);
        this.pendingBadConsequenceView.setVisible(false);
    }

    /**
     * Con este metodo lo que hacemos es que asignamos el controlador a la vista
     * del jugador asi de esa manera podemos pasarle mensajes desde el jugador y
     * realizar las operaciones pertinentes.
     *
     * @param napa esta es una instancia del controlador del juego.
     */
    void setNapakalaki(Napakalaki napa) {
        this.napakalakiModel = napa;
        this.setPlayer(this.napakalakiModel.getCurrentPlayer());
        repaint();
        revalidate();
    }

    /**
     * Función auxiliar con la que conseguimos que se puedan mostrar los
     * diferentes tesoros en el panel de tesoros que corresponda, basicamente es
     * para actualizar la lista de tesoros.
     *
     * @param aPanel Este es el panel que queremos actualizar.
     * @param aList Esta es la lista de tesoros que vamos a mostrar en la vista.
     */
    private void fillTreasurePanel(javax.swing.JPanel aPanel, ArrayList<Treasure> aList) {
        // Se elimina la información antigua, si la hubiera
        aPanel.removeAll();
        // Se recorre la lista de tesoros construyendo y añadiendo sus vistas
        // al aPanel
        for (Treasure t : aList) {
            TreasureView aTreasureView = new TreasureView();
            aTreasureView.setTreasure(t);
            aTreasureView.setVisible(true);
            aPanel.add(aTreasureView);
        }
        // Se fuerza la actualización visual del panel
        aPanel.repaint();
        aPanel.revalidate();
    }

    /**
     * Función auxiliar que nos devuelve un array de los tesoros que hay
     * seleccionados en el panel que nosotros elegimos.
     *
     * @param aPanel este será el panel de tesoros seleccionado.
     * @return devuelve un array de tesoros con los que hemos seleccionado
     */
    private ArrayList<Treasure> getSelectedTreasures(javax.swing.JPanel aPanel) {
        // Se recorren los tesoros que contiene el panel,
        // almacenando en un vector aquellos que están seleccionados.
        // Finalmente se devuelve dicho vector.
        TreasureView tv;
        ArrayList<Treasure> output = new ArrayList();
        for (Component c : aPanel.getComponents()) {
            tv = (TreasureView) c;
            if (tv.isSelected()) {
                output.add(tv.getTreasure());
            }
        }
        return output;
    }

    /**
     * Con esto asignamos el jugador correspondiente a la parte de la fista del
     * jugador con todos sus datos; Tiene visibilidad de paquete por que solo
     * quiero que se pueda usar desde las vistas no desde el controlador.
     *
     * @param aPlayer este es el objeto jugador del modelo.
     */
    void setPlayer(Player aPlayer) {
        this.playerModel = aPlayer;
        this.jTNombreJugador.setText(this.playerModel.getName());
        this.jTEnemigo.setText(this.playerModel.getEnemy().getName());
        this.jTNivelJugador.setText("" + this.playerModel.getLevels());
        // Si el jugador es un cultisplayer mostramos mas campos
        if (this.playerModel instanceof CultistPlayer) {
            this.jCBJugadorSectario.setSelected(true);
            this.jLBonusBasico.setVisible(true);
            this.jLBonusEspecial.setVisible(true);
            this.jTBonusBasico.setVisible(true);
            this.jTBonusEspecial.setVisible(true);
            this.jTBonusBasico.setText("" + ((CultistPlayer) this.playerModel).getMyCultistCard().getBasicValue());
            this.jTBonusEspecial.setText("" + ((CultistPlayer) this.playerModel).getMyCultistCard().getSpecialValue());
        } else {
            this.jCBJugadorSectario.setSelected(false);
            this.jLBonusBasico.setVisible(false);
            this.jLBonusEspecial.setVisible(false);
            this.jTBonusBasico.setVisible(false);
            this.jTBonusEspecial.setVisible(false);
        }
        // con esto mostramos el pendingBadConsequence si contiene algo
        if (!this.playerModel.getPendingBadConsequence().isEmpty()) {
            this.pendingBadConsequenceView.setPendingBadConsequence(this.playerModel.getPendingBadConsequence());
            this.pendingBadConsequenceView.setVisible(true);
        } else {
            this.pendingBadConsequenceView.setVisible(false);
        }
        this.fillTreasurePanel(jPTesorosVisibles, this.playerModel.getVisibleTreasures());
        this.fillTreasurePanel(jPTesorosOcultos, this.playerModel.getHiddenTreasures());
        repaint();
        revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPTesorosOcultos = new javax.swing.JPanel();
        jPTesorosVisibles = new javax.swing.JPanel();
        jPDatosJugador = new javax.swing.JPanel();
        jLNombreJugador = new javax.swing.JLabel();
        jLEnemigo = new javax.swing.JLabel();
        jTNombreJugador = new javax.swing.JTextField();
        jTEnemigo = new javax.swing.JTextField();
        jLNivelJugador = new javax.swing.JLabel();
        jTNivelJugador = new javax.swing.JTextField();
        jLJugadorSectario = new javax.swing.JLabel();
        jCBJugadorSectario = new javax.swing.JCheckBox();
        jLBonusBasico = new javax.swing.JLabel();
        jTBonusBasico = new javax.swing.JTextField();
        jLBonusEspecial = new javax.swing.JLabel();
        jTBonusEspecial = new javax.swing.JTextField();
        jBEquiparTesoro = new javax.swing.JButton();
        jBDescartarTesoro = new javax.swing.JButton();
        jBRobarTesoro = new javax.swing.JButton();
        jBDescartarTodos = new javax.swing.JButton();
        pendingBadConsequenceView = new gui.PendingBadConsequenceView();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Espacio del Jugador"));

        jPTesorosOcultos.setBorder(javax.swing.BorderFactory.createTitledBorder("Tesoros Ocultos"));
        jPTesorosOcultos.setToolTipText("Estos son los tesoros que NO tiene equipados el jugador");
        jPTesorosOcultos.setAutoscrolls(true);
        jPTesorosOcultos.setMinimumSize(new java.awt.Dimension(645, 230));
        jPTesorosOcultos.setName("jPTesorosOcultos"); // NOI18N
        jPTesorosOcultos.setPreferredSize(new java.awt.Dimension(645, 230));

        jPTesorosVisibles.setBorder(javax.swing.BorderFactory.createTitledBorder("Tesoros Visibles"));
        jPTesorosVisibles.setToolTipText("Estos son los tesoros que tiene equipados el jugador");
        jPTesorosVisibles.setMaximumSize(new java.awt.Dimension(645, 230));
        jPTesorosVisibles.setMinimumSize(new java.awt.Dimension(645, 230));
        jPTesorosVisibles.setName("jPTesorosVisibles"); // NOI18N
        jPTesorosVisibles.setPreferredSize(new java.awt.Dimension(645, 230));

        jPDatosJugador.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del Jugador"));
        jPDatosJugador.setMinimumSize(new java.awt.Dimension(222, 230));
        jPDatosJugador.setPreferredSize(new java.awt.Dimension(222, 230));

        jLNombreJugador.setLabelFor(jTNombreJugador);
        jLNombreJugador.setText("Nombre:");
        jLNombreJugador.setName("jLNombreJugador"); // NOI18N

        jLEnemigo.setLabelFor(jTEnemigo);
        jLEnemigo.setText("Enemigo:");
        jLEnemigo.setToolTipText("");
        jLEnemigo.setName("jLEnemigo"); // NOI18N

        jTNombreJugador.setEditable(false);
        jTNombreJugador.setText("jTNombreJugador");
        jTNombreJugador.setBorder(null);

        jTEnemigo.setEditable(false);
        jTEnemigo.setText("jTEnemigo");
        jTEnemigo.setBorder(null);

        jLNivelJugador.setLabelFor(jTNivelJugador);
        jLNivelJugador.setText("Nivel:");
        jLNivelJugador.setToolTipText("");
        jLNivelJugador.setName("jLNivelJugador"); // NOI18N

        jTNivelJugador.setEditable(false);
        jTNivelJugador.setText("jTNivelJugador");
        jTNivelJugador.setBorder(null);

        jLJugadorSectario.setLabelFor(jCBJugadorSectario);
        jLJugadorSectario.setText("Jugador Sectario:");
        jLJugadorSectario.setToolTipText("");
        jLJugadorSectario.setName("jLJugadorSectario"); // NOI18N

        jCBJugadorSectario.setEnabled(false);
        jCBJugadorSectario.setName("jCBJugadorSectario"); // NOI18N

        jLBonusBasico.setLabelFor(jTBonusBasico);
        jLBonusBasico.setText("Bonus Basico:");
        jLBonusBasico.setName("jLBonusBasico"); // NOI18N

        jTBonusBasico.setEditable(false);
        jTBonusBasico.setText("jTBonusBasico");
        jTBonusBasico.setBorder(null);

        jLBonusEspecial.setLabelFor(jTBonusEspecial);
        jLBonusEspecial.setText("Bonus Especial:");
        jLBonusEspecial.setName("jLBonus"); // NOI18N

        jTBonusEspecial.setEditable(false);
        jTBonusEspecial.setText("jTBonusEspecial");
        jTBonusEspecial.setBorder(null);

        javax.swing.GroupLayout jPDatosJugadorLayout = new javax.swing.GroupLayout(jPDatosJugador);
        jPDatosJugador.setLayout(jPDatosJugadorLayout);
        jPDatosJugadorLayout.setHorizontalGroup(
            jPDatosJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDatosJugadorLayout.createSequentialGroup()
                .addComponent(jLJugadorSectario)
                .addGap(18, 18, 18)
                .addComponent(jCBJugadorSectario)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPDatosJugadorLayout.createSequentialGroup()
                .addGroup(jPDatosJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLNombreJugador)
                    .addComponent(jLEnemigo)
                    .addComponent(jLNivelJugador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDatosJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTEnemigo, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTNivelJugador)
                    .addComponent(jTNombreJugador)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPDatosJugadorLayout.createSequentialGroup()
                .addGroup(jPDatosJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLBonusBasico)
                    .addComponent(jLBonusEspecial))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPDatosJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTBonusEspecial, javax.swing.GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE)
                    .addComponent(jTBonusBasico, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)))
        );
        jPDatosJugadorLayout.setVerticalGroup(
            jPDatosJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPDatosJugadorLayout.createSequentialGroup()
                .addGroup(jPDatosJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNombreJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNombreJugador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDatosJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTEnemigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLEnemigo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDatosJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTNivelJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLNivelJugador))
                .addGap(18, 18, 18)
                .addGroup(jPDatosJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLJugadorSectario)
                    .addComponent(jCBJugadorSectario))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDatosJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLBonusBasico)
                    .addComponent(jTBonusBasico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPDatosJugadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLBonusEspecial)
                    .addComponent(jTBonusEspecial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jBEquiparTesoro.setText("Equipar Tesoro");
        jBEquiparTesoro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEquiparTesoroActionPerformed(evt);
            }
        });

        jBDescartarTesoro.setText("Descartar Tesoro");
        jBDescartarTesoro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDescartarTesoroActionPerformed(evt);
            }
        });

        jBRobarTesoro.setText("Robar Tesoro");
        jBRobarTesoro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBRobarTesoroActionPerformed(evt);
            }
        });

        jBDescartarTodos.setText("Descartar Todos");
        jBDescartarTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDescartarTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jBEquiparTesoro)
                .addGap(18, 18, 18)
                .addComponent(jBDescartarTesoro)
                .addGap(18, 18, 18)
                .addComponent(jBDescartarTodos)
                .addGap(18, 18, 18)
                .addComponent(jBRobarTesoro)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPDatosJugador, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(pendingBadConsequenceView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPTesorosVisibles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPTesorosOcultos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPDatosJugador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPTesorosVisibles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPTesorosOcultos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pendingBadConsequenceView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBEquiparTesoro)
                    .addComponent(jBDescartarTesoro)
                    .addComponent(jBRobarTesoro)
                    .addComponent(jBDescartarTodos))
                .addGap(2, 2, 2))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jBEquiparTesoroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEquiparTesoroActionPerformed
        // recopilamos información de la interfaz
        ArrayList<Treasure> OcultosSeleccionados = getSelectedTreasures(this.jPTesorosOcultos);
        // se la pasamos al modelo para que haga las operaciones
        this.napakalakiModel.makeTreasuresVisible(OcultosSeleccionados);
        // volvemos a pintar la interfaz la parte del jugador
        this.setPlayer(this.napakalakiModel.getCurrentPlayer());
    }//GEN-LAST:event_jBEquiparTesoroActionPerformed

    private void jBDescartarTesoroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDescartarTesoroActionPerformed
        ArrayList<Treasure> VisiblesSeleccionados = getSelectedTreasures(this.jPTesorosVisibles);
        ArrayList<Treasure> OcultosSeleccionados = getSelectedTreasures(this.jPTesorosOcultos);
        if (!VisiblesSeleccionados.isEmpty()) {
            this.napakalakiModel.discardVisibleTreasures(VisiblesSeleccionados);
        }
        if (!OcultosSeleccionados.isEmpty()) {
            this.napakalakiModel.discardHiddenTreasures(OcultosSeleccionados);
        }
        this.setPlayer(this.napakalakiModel.getCurrentPlayer());
    }//GEN-LAST:event_jBDescartarTesoroActionPerformed

    private void jBDescartarTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDescartarTodosActionPerformed
        this.playerModel.discardAllTreasures();
        this.setPlayer(this.napakalakiModel.getCurrentPlayer());
    }//GEN-LAST:event_jBDescartarTodosActionPerformed

    private void jBRobarTesoroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBRobarTesoroActionPerformed
        if (this.playerModel.canISteal()) {
            this.playerModel.stealTreasure();
        }else{
            JOptionPane.showMessageDialog(null, "No se ha podido robar ningún tesoro");
        }
        this.setPlayer(this.napakalakiModel.getCurrentPlayer());
    }//GEN-LAST:event_jBRobarTesoroActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBDescartarTesoro;
    private javax.swing.JButton jBDescartarTodos;
    private javax.swing.JButton jBEquiparTesoro;
    private javax.swing.JButton jBRobarTesoro;
    private javax.swing.JCheckBox jCBJugadorSectario;
    private javax.swing.JLabel jLBonusBasico;
    private javax.swing.JLabel jLBonusEspecial;
    private javax.swing.JLabel jLEnemigo;
    private javax.swing.JLabel jLJugadorSectario;
    private javax.swing.JLabel jLNivelJugador;
    private javax.swing.JLabel jLNombreJugador;
    private javax.swing.JPanel jPDatosJugador;
    private javax.swing.JPanel jPTesorosOcultos;
    private javax.swing.JPanel jPTesorosVisibles;
    private javax.swing.JTextField jTBonusBasico;
    private javax.swing.JTextField jTBonusEspecial;
    private javax.swing.JTextField jTEnemigo;
    private javax.swing.JTextField jTNivelJugador;
    private javax.swing.JTextField jTNombreJugador;
    private gui.PendingBadConsequenceView pendingBadConsequenceView;
    // End of variables declaration//GEN-END:variables
}
