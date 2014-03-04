package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import NapakalakiGame.Monster;

public class MonsterView extends JPanel {

	private static final long serialVersionUID = 1L;
	private Monster monster;
	private Image monsterImage;

	public MonsterView() {
		initComponets();
	}

	/**
	 * Creacion del Panel
	 */
	public void initComponets() {
		this.setPreferredSize(new java.awt.Dimension(120, 150));
		this.setBorder(javax.swing.BorderFactory.createLineBorder(Color.BLACK));
		this.setOpaque(false);
	}

	public void setMonster(Monster mon) {
		this.monster = mon;
		try {
			monsterImage = new ImageIcon(getClass().getClassLoader().getResource("resources/Monsters/" + this.monster.getName()+ ".png")).getImage();
			this.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,"La imagen resources/Monstruos/"+ this.monster.getName() + ".png no está disponible.\n"
									+ "Contactar con los programadores para subsanar el fallo: "
									+ e.getMessage(), "Error!!!",JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
	/* Sobreescritura del método paint de JPanel para dibujar la imagen del monstruo */
    @Override
    public void paint(Graphics g)
    {
        if (monsterImage != null)
            g.drawImage(monsterImage,0,0,null);
        super.paint(g);
    }
}
