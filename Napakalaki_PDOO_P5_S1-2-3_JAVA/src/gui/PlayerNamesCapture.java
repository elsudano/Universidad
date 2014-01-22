package gui;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.UIManager;
import javax.swing.JTextPane;

import java.awt.SystemColor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PlayerNamesCapture extends JDialog {

	/**
	 * Numero de Serie Predeterminado
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> names = new ArrayList<String>();
	private JTextField CampoJugador1;
	private JTextField CampoJugador2;
	private JTextField CampoJugador3;

	/**
	 * @param owner
	 * @param modal
	 */
	public PlayerNamesCapture(Frame parent, boolean modal) {
		super(parent, modal);
		initComponets();
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				System.exit(0);
			}
		});
	}

	/**
	 * 
	 * @return devuelve un array con los nombres de los jugadores
	 */
	public ArrayList<String> getNames() {
		this.setVisible(true);
		return names;
	}

	/**
	 * Create the dialog.
	 */
	private void initComponets() {
		setTitle("Quien Juega...");
		setIconImage(Toolkit
				.getDefaultToolkit()
				.getImage(
						PlayerNamesCapture.class
								.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		setBounds(100, 100, 230, 216);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		{
			JTextPane TextoDescripcion = new JTextPane();
			TextoDescripcion.setBackground(SystemColor.controlHighlight);
			TextoDescripcion.setEditable(false);
			TextoDescripcion.setText("Por favor introduzca aquí\nlos nombres de los jugadores");
			getContentPane().add(TextoDescripcion);
		}
		{
			Box verticalBox = Box.createVerticalBox();
			verticalBox.setBorder(UIManager.getBorder("Menu.border"));
			getContentPane().add(verticalBox);
			{
				Box horizontalBox = Box.createHorizontalBox();
				verticalBox.add(horizontalBox);
				{
					JLabel LabelJugador1 = new JLabel("Jugador 1");
					horizontalBox.add(LabelJugador1);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(5);
					horizontalBox.add(horizontalStrut);
				}
				{
					CampoJugador1 = new JTextField();
					CampoJugador1.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent e) {
							CampoJugador1.setText(null);
						}
					});
					CampoJugador1.setText("nombre jugador");
					CampoJugador1.setColumns(10);
					horizontalBox.add(CampoJugador1);
				}
			}
			{
				Box horizontalBox = Box.createHorizontalBox();
				verticalBox.add(horizontalBox);
				{
					JLabel LabelJugador2 = new JLabel("Jugador 2");
					horizontalBox.add(LabelJugador2);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(5);
					horizontalBox.add(horizontalStrut);
				}
				{
					CampoJugador2 = new JTextField();
					CampoJugador2.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent e) {
							CampoJugador2.setText(null);
						}
					});
					CampoJugador2.setText("nombre jugador");
					CampoJugador2.setColumns(10);
					horizontalBox.add(CampoJugador2);
				}
			}
			{
				Box horizontalBox = Box.createHorizontalBox();
				verticalBox.add(horizontalBox);
				{
					JLabel LabelJugador3 = new JLabel("Jugador 3");
					horizontalBox.add(LabelJugador3);
				}
				{
					Component horizontalStrut = Box.createHorizontalStrut(5);
					horizontalBox.add(horizontalStrut);
				}
				{
					CampoJugador3 = new JTextField();
					CampoJugador3.addFocusListener(new FocusAdapter() {
						@Override
						public void focusGained(FocusEvent e) {
							CampoJugador3.setText(null);
						}
					});
					CampoJugador3.setText("nombre jugador");
					CampoJugador3.setColumns(10);
					horizontalBox.add(CampoJugador3);
				}
			}
		}
		{
			Component verticalStrut = Box.createVerticalStrut(100);
			getContentPane().add(verticalStrut);
		}
		{
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPanel);
			{
				JButton PlayButton = new JButton("Play");
				PlayButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						names.add(CampoJugador1.getText());
						names.add(CampoJugador2.getText());
						names.add(CampoJugador3.getText());
						dispose();
					}
				});
				PlayButton.setActionCommand("Play");
				buttonPanel.add(PlayButton);
				getRootPane().setDefaultButton(PlayButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPanel.add(cancelButton);
			}
		}
	}
}
