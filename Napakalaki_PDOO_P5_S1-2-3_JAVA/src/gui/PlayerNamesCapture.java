package gui;
import java.util.ArrayList;

public class PlayerNamesCapture extends javax.swing.JDialog {

	/**
	 * Numero de Serie Predeterminado
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<String> names = new ArrayList<String>();
	private javax.swing.JTextField CampoJugador1;
	private javax.swing.JTextField CampoJugador2;
	private javax.swing.JTextField CampoJugador3;
	
	/**
	 * @param owner
	 * @param modal
	 */
	public PlayerNamesCapture(java.awt.Frame parent, boolean modal) {
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
		setIconImage(java.awt.Toolkit
				.getDefaultToolkit()
				.getImage(
						PlayerNamesCapture.class
								.getResource("/javax/swing/plaf/basic/icons/JavaCup16.png")));
		setBounds(100, 100, 230, 216);
		getContentPane().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 5));
		{
			javax.swing.JTextPane TextoDescripcion = new javax.swing.JTextPane();
			TextoDescripcion.setBackground(java.awt.SystemColor.controlHighlight);
			TextoDescripcion.setEditable(false);
			TextoDescripcion.setText("Por favor introduzca aquí\nlos nombres de los jugadores");
			getContentPane().add(TextoDescripcion);
		}
		{
			javax.swing.Box verticalBox = javax.swing.Box.createVerticalBox();
			verticalBox.setBorder(javax.swing.UIManager.getBorder("Menu.border"));
			getContentPane().add(verticalBox);
			{
				javax.swing.Box horizontalBox = javax.swing.Box.createHorizontalBox();
				verticalBox.add(horizontalBox);
				{
					javax.swing.JLabel LabelJugador1 = new javax.swing.JLabel("Jugador 1");
					horizontalBox.add(LabelJugador1);
				}
				{
					java.awt.Component horizontalStrut = javax.swing.Box.createHorizontalStrut(5);
					horizontalBox.add(horizontalStrut);
				}
				{
					CampoJugador1 = new javax.swing.JTextField();
					CampoJugador1.addFocusListener(new java.awt.event.FocusAdapter() {
						@Override
						public void focusGained(java.awt.event.FocusEvent e) {
							CampoJugador1.setText(null);
						}
					});
					CampoJugador1.setText("nombre jugador");
					CampoJugador1.setColumns(10);
					horizontalBox.add(CampoJugador1);
				}
			}
			{
				javax.swing.Box horizontalBox = javax.swing.Box.createHorizontalBox();
				verticalBox.add(horizontalBox);
				{
					javax.swing.JLabel LabelJugador2 = new javax.swing.JLabel("Jugador 2");
					horizontalBox.add(LabelJugador2);
				}
				{
					java.awt.Component horizontalStrut = javax.swing.Box.createHorizontalStrut(5);
					horizontalBox.add(horizontalStrut);
				}
				{
					CampoJugador2 = new javax.swing.JTextField();
					CampoJugador2.addFocusListener(new java.awt.event.FocusAdapter() {
						@Override
						public void focusGained(java.awt.event.FocusEvent e) {
							CampoJugador2.setText(null);
						}
					});
					CampoJugador2.setText("nombre jugador");
					CampoJugador2.setColumns(10);
					horizontalBox.add(CampoJugador2);
				}
			}
			{
				javax.swing.Box horizontalBox = javax.swing.Box.createHorizontalBox();
				verticalBox.add(horizontalBox);
				{
					javax.swing.JLabel LabelJugador3 = new javax.swing.JLabel("Jugador 3");
					horizontalBox.add(LabelJugador3);
				}
				{
					java.awt.Component horizontalStrut = javax.swing.Box.createHorizontalStrut(5);
					horizontalBox.add(horizontalStrut);
				}
				{
					CampoJugador3 = new javax.swing.JTextField();
					CampoJugador3.addFocusListener(new java.awt.event.FocusAdapter() {
						@Override
						public void focusGained(java.awt.event.FocusEvent e) {
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
			java.awt.Component verticalStrut = javax.swing.Box.createVerticalStrut(100);
			getContentPane().add(verticalStrut);
		}
		{
			javax.swing.JPanel buttonPanel = new javax.swing.JPanel();
			buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));
			getContentPane().add(buttonPanel);
			{
				javax.swing.JButton PlayButton = new javax.swing.JButton("Play");
				PlayButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
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
				javax.swing.JButton cancelButton = new javax.swing.JButton("Cancel");
				cancelButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPanel.add(cancelButton);
			}
		}
	}
}
