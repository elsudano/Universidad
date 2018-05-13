package server;

import com.github.sarxos.webcam.Webcam;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author usuario
 */
public class server extends Thread {

    private Object id_socket;
    private String protocol;
    private Webcam webcam;

    /* Constructor para el servidor TCP */
    public server(Socket id_socket) {
        this.id_socket = id_socket;
        this.protocol = "TCP";
        this.webcam = Webcam.getDefault();
        //this.webcam.setViewSize(new Dimension(640, 480));
    }

    /* Constructor para el servidor UDP */
    public server(DatagramSocket id_socket) {
        this.id_socket = id_socket;
        this.protocol = "UDP";
        this.webcam = Webcam.getDefault();
        this.webcam.setViewSize(new Dimension(640, 480));
    }

    private void tcp() {
        try (Socket socket_tcp = (Socket) this.id_socket) {
            this.webcam.open();
            BufferedImage image = this.webcam.getImage();
            this.webcam.close();
            ByteArrayOutputStream baos = new ByteArrayOutputStream(image.getHeight() * image.getWidth() * image.getData().getNumBands());
            ImageIO.write(image, "PNG", baos);
            baos.writeTo(socket_tcp.getOutputStream());
            baos.flush();
            baos.close();
        } catch (IOException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void udp() {
        try (DatagramSocket socket_udp = (DatagramSocket) this.id_socket) {
            DatagramPacket pack = new DatagramPacket(new byte[256], 256);
            while (!socket_udp.isClosed()) {
                socket_udp.receive(pack); // se bloquea hasta que recibe un datagrama
                pack.setAddress(pack.getAddress());
                pack.setPort(pack.getPort());
                System.out.println(pack.getData());
                /*
                if () {
                    /* Aqui es donde tienes qeu poner la condicion de parada para el servidor *
                    in.close();
                    socket_udp.close();
                }
                 */
                socket_udp.send(pack);
            }
        } catch (SocketException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

// Constructor de esta clase
    private void ServidorUDP(int puerto) {
        /*
        1. Creamos el socket
        2. Esperamos un mensaje
        3. Almacenamos dir. IP y puerto en el datagrama a enviar
        4. Abrimos la cámara y capturamos una imagen
        5. Copiamos la imagen a un stream (ByteArrayOutputStream) Para ello, añadimos primero el número de filas (getHeight),
        luego el número de columnas (getWidth) y por último los pixeles RGB (getRGB)
        6. Almacenamos en un array de bytes la información a transmitir
        7. Transmitimos el número de datagramas que se van a enviar
        8. Transmitimos los datagramas que contienen los datos de la imagen (se recomienda usar la función sleep entre cada envío) 
         */
    }

    @Override
    public void run() {
        if (this.protocol.contains("TCP")) {
            this.tcp();
        } else if (this.protocol.contains("UDP")) {
            this.udp();
        } else {
            this.interrupt();
        }
    }
}
