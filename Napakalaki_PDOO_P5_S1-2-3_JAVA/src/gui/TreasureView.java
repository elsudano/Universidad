package gui;

import NapakalakiGame.Treasure;

@SuppressWarnings("serial")
public class TreasureView extends javax.swing.JPanel {
	/* asociación con el tesoro que representa */
	private Treasure treasureModel;
    private javax.swing.JTextArea jL_nombre = new javax.swing.JTextArea();
    private javax.swing.JLabel jL_bonus = new javax.swing.JLabel();
    private javax.swing.JLabel jL_piezasOro = new javax.swing.JLabel();
    private javax.swing.JLabel jL_tipo = new javax.swing.JLabel();

	public TreasureView()  {
		initComponets();
	}
	
	/**
	 * Creacion del Panel
	 */
	public void initComponets(){
		this.setPreferredSize(new java.awt.Dimension(120, 150));
		this.setLayout(new java.awt.GridLayout());
		this.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK));
                   
        jL_nombre.setFocusable(false);
        jL_nombre.setEditable(false);
        jL_nombre.setBackground(java.awt.Color.WHITE);
        jL_nombre.setColumns(15);
        jL_nombre.setFont(new java.awt.Font("Dialog", 3, 11));
        jL_nombre.setOpaque(false);
        jL_nombre.setLineWrap(true);
        jL_nombre.setRows(3);
        jL_nombre.setToolTipText("");
        jL_nombre.setWrapStyleWord(true);
        jL_nombre.setAutoscrolls(false);
        
        jL_bonus.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        
        this.setOpaque(false);
        this.add(jL_nombre, new java.awt.GridLayout(5, 5, 110, -1));
        this.add(jL_tipo, new java.awt.GridLayout(5, 120, 90, -1));
        this.add(jL_piezasOro, new java.awt.GridLayout(5, 132, 90, -1));
        this.add(jL_bonus, new java.awt.GridLayout(20, 132, 90, -1));
	}
	
	/**
	 * Asignar un tesoro a la vista para presentarlo en pantalla
	 * @param t tesoro que se va a pintar en pantalla
	 */
	public void setTreasure (Treasure t) {
		this.treasureModel = t;
		jL_nombre.setText(treasureModel.getName());
        jL_bonus.setText("+" + treasureModel.getBasicValue() + "/+" + treasureModel.getSpecialValue());
        jL_piezasOro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_piezasOro.setText(treasureModel.getGoldCoins() + " oro");
        jL_tipo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jL_tipo.setText("" + treasureModel.getType());
		repaint();
	}

	public void Show() {
		this.setVisible(true);
	}
}
