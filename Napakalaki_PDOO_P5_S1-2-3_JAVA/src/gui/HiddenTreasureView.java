package gui;
import java.awt.Color;
import javax.swing.BorderFactory;

public class HiddenTreasureView extends TreasureView {

	private static final long serialVersionUID = 1L;
	public HiddenTreasureView() {
		super();
		addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
            	if (seleccionado){
            		HiddenTreasureView.this.setBorder(BorderFactory.createLineBorder(Color.black));
            		HiddenTreasureView.this.setEnabled(true);
            		seleccionado = false;
            	}else{
            		HiddenTreasureView.this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.red));
            		HiddenTreasureView.this.setEnabled(false);
					seleccionado = true;
            	}
			}
		});
	}

}
