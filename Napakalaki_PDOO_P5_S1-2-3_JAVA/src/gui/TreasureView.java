package gui;

import NapakalakiGame.Treasure;

import java.awt.FlowLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TreasureView extends JPanel {
    private javax.swing.JTextArea jL_nombre = new javax.swing.JTextArea();
    private javax.swing.JLabel jL_bonus = new javax.swing.JLabel();
    private javax.swing.JLabel jL_piezasOro = new javax.swing.JLabel();
    private javax.swing.JLabel jL_tipo = new javax.swing.JLabel();
    
    /* asociación con el tesoro que representa */
    Treasure treasureModel;
    boolean seleccionado = false;

    public TreasureView()  {
		initComponets();
	}
	
	/**
	 * Creacion del Panel
	 */
	public void initComponets(){
		this.setPreferredSize(new java.awt.Dimension(120, 150));
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
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        this.add(jL_nombre);
        this.add(jL_tipo);
        this.add(jL_piezasOro);
        this.add(jL_bonus);
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
	
	/**
	 * @return el parametro seleccionado
	 */
	public boolean isSeleccionado() {
		return seleccionado;
	}
}
