package gui;

import NapakalakiGame.CultistPlayer;
import NapakalakiGame.Monster;
import NapakalakiGame.Napakalaki;
import NapakalakiGame.Player;
import NapakalakiGame.Treasure;
import NapakalakiGame.TreasureKind;

public class NapakalakiView extends javax.swing.JFrame{
	/**
	 * Numero de Serie por defecto
	 */
	private static final long serialVersionUID = 1L;
	private javax.swing.JButton jB_combatir;
    private javax.swing.JButton jB_comprarNivel;
    private javax.swing.JButton jB_descartarseTesoros;
    private javax.swing.JButton jB_equiparse;
    private javax.swing.JButton jB_siguiente;
    private javax.swing.JLabel jL_bonusSectario;
    private javax.swing.JLabel jL_esSectario;
    private javax.swing.JLabel jL_excesoCartas;
    private javax.swing.JTextArea jL_malRollo;
    private javax.swing.JLabel jL_malRolloPendiente;
    private javax.swing.JLabel jL_nivel;
    private javax.swing.JLabel jL_nivelBasico;
    private javax.swing.JLabel jL_nivelCombate;
    private javax.swing.JLabel jL_nivelContraSectarios;
    private javax.swing.JLabel jL_nivelesGanados;
    private javax.swing.JLabel jL_nivelesPerdidos;
    private javax.swing.JLabel jL_nombreJugador;
    private javax.swing.JLabel jL_nombreMonstruo;
    private javax.swing.JLabel jL_ocultosPerdidos;
    private javax.swing.JLabel jL_resultadoCombate;
    private javax.swing.JLabel jL_tesorosGanados;
    private javax.swing.JLabel jL_tipoOcultosPerdidos;
    private javax.swing.JLabel jL_tipoVisiblesPerdidos;
    private javax.swing.JLabel jL_visiblesPerdidos;
    private javax.swing.JPanel jP_imgMonstruo;
    private javax.swing.JPanel jP_jugadores;
    private javax.swing.JPanel jP_malRolloPendiente;
    private javax.swing.JPanel jP_monstruos;
    private javax.swing.JPanel jP_tesorosOcultos;
    private javax.swing.JPanel jP_tesorosVisibles;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;

	private Napakalaki NapakalakiModel;
	private Player elJugadorActual;
	private Monster elMonstruoActual;
	/**
	 * @param Model se le asigna a NapakalakiModel
	 */
	public void setModel(Napakalaki Model) {
		this.NapakalakiModel = Model;
	}
	
	/**
	 * Launch the application.
	 */
	public void Show() {
		this.setVisible(true);
		actualizarJugador();
		actualizarMonstruo();
	}

	/**
	 * Create the application.
	 */
	public NapakalakiView() {
		initComponents();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initComponents() {

        jP_monstruos = new javax.swing.JPanel();
        jL_nombreMonstruo = new javax.swing.JLabel();
        jL_nivelesGanados = new javax.swing.JLabel();
        jL_tesorosGanados = new javax.swing.JLabel();
        jL_nivel = new javax.swing.JLabel();
        jL_nivelContraSectarios = new javax.swing.JLabel();
        jL_nivelesPerdidos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jL_malRollo = new javax.swing.JTextArea();
        jP_imgMonstruo = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jL_resultadoCombate = new javax.swing.JLabel();
        jP_jugadores = new javax.swing.JPanel();
        jL_nombreJugador = new javax.swing.JLabel();
        jP_tesorosVisibles = new javax.swing.JPanel();
        jP_tesorosOcultos = new javax.swing.JPanel();
        jL_esSectario = new javax.swing.JLabel();
        jL_nivelCombate = new javax.swing.JLabel();
        jL_bonusSectario = new javax.swing.JLabel();
        jL_malRolloPendiente = new javax.swing.JLabel();
        jP_malRolloPendiente = new javax.swing.JPanel();
        jL_ocultosPerdidos = new javax.swing.JLabel();
        jL_tipoOcultosPerdidos = new javax.swing.JLabel();
        jL_visiblesPerdidos = new javax.swing.JLabel();
        jL_tipoVisiblesPerdidos = new javax.swing.JLabel();
        jL_nivelBasico = new javax.swing.JLabel();
        jL_excesoCartas = new javax.swing.JLabel();
        jB_equiparse = new javax.swing.JButton();
        jB_comprarNivel = new javax.swing.JButton();
        jB_descartarseTesoros = new javax.swing.JButton();
        jB_combatir = new javax.swing.JButton();
        jB_siguiente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Napakalaki");
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(2147483647, 701));
        setResizable(false);

        jP_monstruos.setBorder(javax.swing.BorderFactory.createTitledBorder("Territorio del Terrible Monstruo"));
        jP_monstruos.setPreferredSize(new java.awt.Dimension(904, 180));

        jL_nombreMonstruo.setText("Nombre Monstruo");

        jL_nivelesGanados.setText("Niveles Ganados");

        jL_tesorosGanados.setText("Tesoros Ganados");

        jL_nivel.setText("Nivel");

        jL_nivelContraSectarios.setText("Nivel contra Sectarios");

        jL_nivelesPerdidos.setText("Niveles Perdidos");

        jL_malRollo.setEditable(false);
        jL_malRollo.setBackground(new java.awt.Color(238, 238, 238));
        jL_malRollo.setColumns(20);
        jL_malRollo.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jL_malRollo.setLineWrap(true);
        jL_malRollo.setRows(4);
        jL_malRollo.setText("Mal Rollo del Monstruo");
        jL_malRollo.setToolTipText("");
        jL_malRollo.setWrapStyleWord(true);
        jL_malRollo.setAutoscrolls(false);
        jScrollPane1.setViewportView(jL_malRollo);

        jP_imgMonstruo.setMaximumSize(new java.awt.Dimension(120, 155));
        jP_imgMonstruo.setMinimumSize(new java.awt.Dimension(120, 150));
        jP_imgMonstruo.setPreferredSize(new java.awt.Dimension(120, 155));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultado del Combate"));

        jL_resultadoCombate.setText("Resultado del Combate");
        jL_resultadoCombate.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(160, Short.MAX_VALUE)
                .addComponent(jL_resultadoCombate, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jL_resultadoCombate)
                .addContainerGap())
        );

        javax.swing.GroupLayout jP_monstruosLayout = new javax.swing.GroupLayout(jP_monstruos);
        jP_monstruos.setLayout(jP_monstruosLayout);
        jP_monstruosLayout.setHorizontalGroup(
            jP_monstruosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_monstruosLayout.createSequentialGroup()
                .addGroup(jP_monstruosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_monstruosLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jP_monstruosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jL_nombreMonstruo, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(jL_nivelesGanados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jL_tesorosGanados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(75, 75, 75)
                        .addGroup(jP_monstruosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jL_nivel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jL_nivelContraSectarios, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                            .addComponent(jL_nivelesPerdidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jP_monstruosLayout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jP_imgMonstruo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jP_monstruosLayout.setVerticalGroup(
            jP_monstruosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_monstruosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP_monstruosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jP_monstruosLayout.createSequentialGroup()
                        .addGroup(jP_monstruosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jL_nombreMonstruo)
                            .addComponent(jL_nivel))
                        .addGap(7, 7, 7)
                        .addGroup(jP_monstruosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jL_nivelesGanados)
                            .addComponent(jL_nivelContraSectarios))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jP_monstruosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jL_nivelesPerdidos)
                            .addComponent(jL_tesorosGanados))))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_monstruosLayout.createSequentialGroup()
                .addComponent(jP_imgMonstruo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jP_jugadores.setBorder(javax.swing.BorderFactory.createTitledBorder("Parcelita de los débiles jugadores"));

        jL_nombreJugador.setText("Nombre Jugador");

        jP_tesorosVisibles.setBorder(javax.swing.BorderFactory.createTitledBorder("Equipo"));

        jP_tesorosOcultos.setBorder(javax.swing.BorderFactory.createTitledBorder("Cartas Ocultas"));

        jL_esSectario.setText("Humano");

        jL_nivelCombate.setText("Nivel de Combate");

        jL_bonusSectario.setText("Bonus Sectario");

        jP_malRolloPendiente.setBorder(javax.swing.BorderFactory.createTitledBorder("MalRollo Pendiente"));

        jL_ocultosPerdidos.setText("Nº Ocultos a perder:");

        jL_tipoOcultosPerdidos.setText("Tipo:");

        jL_visiblesPerdidos.setText("Nº Visibles a perder");

        jL_tipoVisiblesPerdidos.setText("Tipo:");

        javax.swing.GroupLayout jP_malRolloPendienteLayout = new javax.swing.GroupLayout(jP_malRolloPendiente);
        jP_malRolloPendiente.setLayout(jP_malRolloPendienteLayout);
        jP_malRolloPendienteLayout.setHorizontalGroup(
            jP_malRolloPendienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_malRolloPendienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP_malRolloPendienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jL_ocultosPerdidos, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jP_malRolloPendienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jL_tipoOcultosPerdidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jL_visiblesPerdidos, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                        .addComponent(jL_tipoVisiblesPerdidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jP_malRolloPendienteLayout.setVerticalGroup(
            jP_malRolloPendienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_malRolloPendienteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jL_ocultosPerdidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jL_tipoOcultosPerdidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jL_visiblesPerdidos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jL_tipoVisiblesPerdidos)
                .addContainerGap())
        );

        jL_nivelBasico.setText("Nivel Básico");

        jL_excesoCartas.setText("");

        javax.swing.GroupLayout jP_jugadoresLayout = new javax.swing.GroupLayout(jP_jugadores);
        jP_jugadores.setLayout(jP_jugadoresLayout);
        jP_jugadoresLayout.setHorizontalGroup(
            jP_jugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_jugadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jP_jugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jP_tesorosVisibles, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                    .addComponent(jP_tesorosOcultos, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE))
                .addGroup(jP_jugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_jugadoresLayout.createSequentialGroup()
                        .addGroup(jP_jugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jP_jugadoresLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jL_malRolloPendiente))
                            .addGroup(jP_jugadoresLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jP_jugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jL_nivelCombate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jL_esSectario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jL_nivelBasico, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jL_nombreJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jL_excesoCartas, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jL_bonusSectario, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 12, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jP_jugadoresLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jP_malRolloPendiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jP_jugadoresLayout.setVerticalGroup(
            jP_jugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jP_jugadoresLayout.createSequentialGroup()
                .addGroup(jP_jugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jP_jugadoresLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jL_nombreJugador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jL_nivelBasico)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jL_nivelCombate)
                        .addGap(12, 12, 12)
                        .addComponent(jL_malRolloPendiente)
                        .addGap(18, 18, 18)
                        .addComponent(jL_esSectario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jL_bonusSectario)
                        .addGap(18, 18, 18)
                        .addComponent(jL_excesoCartas))
                    .addGroup(jP_jugadoresLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jP_tesorosVisibles, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(jP_jugadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jP_tesorosOcultos, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jP_malRolloPendiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jB_equiparse.setText("Equiparse");
        jB_equiparse.setEnabled(false);
        jB_equiparse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });

        jB_comprarNivel.setText("Comprar Nivel");
        jB_comprarNivel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });

        jB_descartarseTesoros.setText("Descartarse Tesoros");
        jB_descartarseTesoros.setEnabled(false);
        jB_descartarseTesoros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });

        jB_combatir.setText("¡COMBATIR!");
        jB_combatir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	Treasure miTesoro = NapakalakiModel.getCurrentPlayer().getHiddenTreasures().get(0);
            	TreasureView miVistaTesoro = new TreasureView();
        		miVistaTesoro.setTreasure(miTesoro);
        		miVistaTesoro.Show();
            }
        });

        jB_siguiente.setText("Siguiente");
        jB_siguiente.setEnabled(false);
        jB_siguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jB_equiparse)
                        .addGap(18, 18, 18)
                        .addComponent(jB_comprarNivel)
                        .addGap(18, 18, 18)
                        .addComponent(jB_descartarseTesoros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jB_combatir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jB_siguiente))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jP_jugadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jP_monstruos, javax.swing.GroupLayout.DEFAULT_SIZE, 971, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jP_monstruos, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jP_jugadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jB_siguiente)
                    .addComponent(jB_combatir)
                    .addComponent(jB_descartarseTesoros)
                    .addComponent(jB_comprarNivel)
                    .addComponent(jB_equiparse))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        repaint();
        pack();
    }

	private void actualizarJugador(){
        //TreasureView miUITesoro;
        elJugadorActual = NapakalakiModel.getCurrentPlayer();

        jL_nombreJugador.setText("" + elJugadorActual.getName());
        jL_nivelBasico.setText("Nivel Básico: " + elJugadorActual.getLevels());
        jL_nivelCombate.setText("Nivel de combate: " + elJugadorActual.getCombatLevel());


        if (elJugadorActual instanceof CultistPlayer){
            jL_esSectario.setText("Sectario");
            jL_bonusSectario.setText("+" + ((CultistPlayer)elJugadorActual).getMyCultistCard().getBasicValue()
                                      + " por cada Sectario en juego -> " + CultistPlayer.getTotalCultistPlayers());
        }else{
            jL_esSectario.setText("Humano");
            jL_bonusSectario.setText("");
        }

//        for (TreasureView tg : tesorosVisiblesAlimpiar)
//            jP_tesorosVisibles.remove(tg);
//        tesorosVisiblesAlimpiar.clear();

//        for (Tesoro t : jugadorActivo.obtenerTesorosVisibles()){
//            unTesoroGrafico = new TesoroGraficoVisible(t);
//            jP_tesorosVisibles.add (unTesoroGrafico);
//            tesorosVisiblesAlimpiar.add(unTesoroGrafico);
//        }
//        for (TesoroGrafico tg : tesorosOcultosAlimpiar)
//            jP_tesorosOcultos.remove(tg);
//        tesorosOcultosAlimpiar.clear();
//
//        for (Tesoro t : jugadorActivo.obtenerTesorosOcultos()){
//            unTesoroGrafico = new TesoroGraficoOculto(t);
//            jP_tesorosOcultos.add(unTesoroGrafico);
//            tesorosOcultosAlimpiar.add(unTesoroGrafico);
//        }

//        tesorosVisiblesSeleccionados.clear();
//        tesorosOcultosSeleccionados.clear();

        
     // Actualizamos malRolloPendiente
        if(elJugadorActual.getPendingBadStuff().getNHiddenTreasures()==0 && elJugadorActual.getPendingBadStuff().mSpecificHiddenTreasures.isEmpty()){
            jL_ocultosPerdidos.setText("");
            jL_tipoOcultosPerdidos.setText("");
        }else{
            jL_ocultosPerdidos.setText("Nº ocultos perdidos: " + elJugadorActual.getPendingBadStuff().getNHiddenTreasures());
            String f = new String();
            f += "Tipos: ";
            for (TreasureKind t: elJugadorActual.getPendingBadStuff().mSpecificHiddenTreasures)
                f += t + " | ";
            jL_tipoOcultosPerdidos.setText(f);
        }
        
        if(elJugadorActual.getPendingBadStuff().getNVisibleTreasures()==0 && elJugadorActual.getPendingBadStuff().mSpecificVisibleTreasures.isEmpty()){
            jL_visiblesPerdidos.setText("");
            jL_tipoVisiblesPerdidos.setText("");

        }else{
            jL_visiblesPerdidos.setText("Nº visibles perdidos: " + elJugadorActual.getPendingBadStuff().getNVisibleTreasures());
            String f = "Tipos: ";
            for (TreasureKind t: elJugadorActual.getPendingBadStuff().mSpecificVisibleTreasures)
                f += t + " | ";
            jL_tipoVisiblesPerdidos.setText(f);
        }
        repaint();
        pack();
    }
	
	private void actualizarMonstruo(){
		// if (imagenMonstruo != null)
		// jP_imgMonstruo.remove(imagenMonstruo);
		elMonstruoActual = NapakalakiModel.getCurrentMonster();
		// imagenMonstruo = new MonstruoGrafico(monstruoEnJuego);
		jL_nombreMonstruo.setText(elMonstruoActual.getName());
		jL_nivelesGanados.setText("Niveles Ganados: " + elMonstruoActual.getPrize().getLevels());
		jL_tesorosGanados.setText("Tesoros Ganados: " + elMonstruoActual.getPrize().getTreasures());
		jL_nivel.setText("Nivel: " + elMonstruoActual.getBasicValue());
		jL_nivelContraSectarios.setText("Nivel contra Sectarios: " + elMonstruoActual.getSpecialValue());
		jL_nivelesPerdidos.setText("Niveles perdidos: " + elMonstruoActual.getLevel());
		jL_resultadoCombate.setText("");
		jL_malRollo.setText(elMonstruoActual.getBadStuff().getText());

		// jP_imgMonstruo.add(imagenMonstruo);
		pack();
		repaint();
	}

}
