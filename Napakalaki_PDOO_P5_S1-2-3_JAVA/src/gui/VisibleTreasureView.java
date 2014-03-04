package gui;

import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;

public class VisibleTreasureView extends TreasureView {

	private static final long serialVersionUID = 1L;
	public VisibleTreasureView() {
		super();
        addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent evt) {
            	if (!seleccionado){
            		VisibleTreasureView.this.setBorder(BorderFactory.createLineBorder(Color.black));
            		VisibleTreasureView.this.setEnabled(true);
            		seleccionado = true;
            	}else{
					VisibleTreasureView.this.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.red));
					VisibleTreasureView.this.setEnabled(false);
					seleccionado = false;
            	}
            }
        });
	}
}
