package client;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import utils.utilities;

/**
 *
 * @author usuario
 */
public class client extends Thread {

    private InetAddress destination_address;
    private int destination_port;
    private String protocol;
    private window ventana;

    public client(InetAddress dest, int port, String protocol) throws UnknownHostException {
        if (protocol.isEmpty()) {
            this.protocol = "TCP";
        } else {
            this.protocol = protocol;
        }
        if (dest == null) {
            this.destination_address = InetAddress.getLocalHost();
        } else {
            this.destination_address = dest;
        }
        if (port == 0) {
            this.destination_port = 9999;
        } else {
            this.destination_port = port;
        }
        this.ventana = new window("Cliente " + this.destination_address.getHostName());
    }

    private void tcp() {
        try (Socket socket_tcp = new Socket(this.destination_address, this.destination_port)) {
            BufferedImage image = ImageIO.read(socket_tcp.getInputStream());
            this.ventana.setImage(image);
            socket_tcp.close();
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void udp() {
        int size_package = 256;
        byte[] buf = new byte[size_package];
        buf = "imag".getBytes();
        DatagramPacket pack = new DatagramPacket(buf, buf.length, this.destination_address, this.destination_port);
        
        try (DatagramSocket socket_udp = new DatagramSocket()) {
            socket_udp.send(pack);
            socket_udp.receive(pack); // se bloquea hasta recibir un datagrama
            int num_of_datagrams = utilities.byteArrayToInt(pack.getData());
            byte[] image_data = new byte[num_of_datagrams*size_package];
            ByteArrayInputStream bais = new ByteArrayInputStream(image_data);
            for (int dg = 0; dg < num_of_datagrams; dg++) {
                socket_udp.receive(pack); // se bloquea hasta recibir un datagrama
                bais.read(pack.getData());
            }
            System.out.println(bais.toString());
        } catch (SocketException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Constructor de esta clase
    private void ClienteUDP(String direccionServidor, int puerto) {
        /*
        1. Creamos el socket
        2. Enviamos un mensaje al servidor para pedir la imagen
        3. Recibimos el número de datagramas que se va a recibir
        4. Recibimos y almacenamos la información del servidor
        5. Creamos un stream de bytes ByteArrayInputStream para ir leyendo la información recibida
        6. Leemos el primer dato que es el número de filas
        7. Leemos el segundo dato que es el número de columnas
        8. Copiamos los pixeles recibidos a un objeto imagen
        9. Representamos la imagen
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
