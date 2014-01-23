package gui;

import javax.swing.ImageIcon;

import NapakalakiGame.Cultist;
import NapakalakiGame.CultistPlayer;

@SuppressWarnings("serial")
public class CultistView extends javax.swing.JPanel {
	/* asociación con el tesoro que representa */
	private Cultist CultistModel;
    private javax.swing.JLabel jL_nombre = new javax.swing.JLabel();
	private java.awt.Image laImagenCultist;
    private javax.swing.JLabel jL_bonus = new javax.swing.JLabel();
    private javax.swing.JLabel jL_texto = new javax.swing.JLabel();

	public CultistView()  {
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
        jL_nombre.setBackground(java.awt.Color.WHITE);
        jL_nombre.setFont(new java.awt.Font("Dialog", 3, 11));
        jL_nombre.setOpaque(false);
        jL_nombre.setToolTipText("");
        jL_nombre.setAutoscrolls(false);
        
        jL_bonus.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        
        this.setOpaque(false);
        this.add(jL_nombre, new java.awt.GridLayout(5, 5, 110, -1));
        this.add(jL_bonus, new java.awt.GridLayout(20, 132, 90, -1));
        this.add(jL_texto, new java.awt.GridLayout(5, 120, 90, -1));
	}
	
	/**
	 * Asignar un tesoro a la vista para presentarlo en pantalla
	 * @param t tesoro que se va a pintar en pantalla
	 */
	public void setCultist (Cultist c) {
		this.CultistModel = c;
		jL_nombre.setText(CultistModel.getName());
		jL_bonus.setText("+" + CultistModel.getGainedLevels()
                + " por cada Sectario en juego -> " + CultistPlayer.getTotalCultistPlayers());
		this.laImagenCultist = (new ImageIcon(getClass().getClassLoader().getResource("/resources/Cultist/"+this.CultistModel.getName()+".png"))).getImage();
		repaint();
	}
}
