package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import utils.utilities;

/**
 *
 * @author usuario
 */
public class window extends JFrame {

    private static final class PanelImage extends JPanel {

        private static final long serialVersionUID = 1L;

        private BufferedImage image;
        private Dimension size;
        private int offset = 0;

        public PanelImage(BufferedImage image) {
            super();
            this.image = image;
            this.size = new Dimension(this.image.getWidth(), this.image.getHeight());
            this.setPreferredSize(this.size);
            this.setVisible(true);
            this.repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.drawImage(this.image, 0, 0, null);
        }
    }

    /* Los siguientes componentes se pondrán en la ventana */
    public static boolean TAKEPHOTO = false;
    private final int WIDTH_SIZE = 300;
    private final int HEIGHT_SIZE = 300;
    private final JButton take_picture;
    private final JButton exit;
    private final ActionListener formListener;

    /* Constructor de la ventana */
    public window(String title) throws HeadlessException {
        super(title);
        this.take_picture = new JButton("Solicitar Imágen");
        this.exit = new JButton("Salir");
        this.formListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource() == take_picture) {
                    window.TAKEPHOTO = true;
                    System.out.println("Take Picture");
                }else if (evt.getSource() == exit){
                    System.exit(0);
                }
                //throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        initWindow();
    }

    /* Inicializamos la ventana del cliente */
    private void initWindow() {
        this.setContentPane(new JPanel());
        this.take_picture.addActionListener(formListener);
        this.exit.addActionListener(formListener);
        this.take_picture.setLocation(this.WIDTH_SIZE/2, this.HEIGHT_SIZE/2);
        this.add(this.take_picture);
        this.add(this.exit);
        this.setLocation(utilities.centerOfScreen(this.WIDTH_SIZE, this.HEIGHT_SIZE));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }

    /* Ponemos la imagen en la ventana */
    public void setImage(BufferedImage image) {
        this.setContentPane(new PanelImage(image));
        this.setLocation(utilities.centerOfScreen(image.getWidth(), image.getHeight()));
        this.pack();
    }
}
