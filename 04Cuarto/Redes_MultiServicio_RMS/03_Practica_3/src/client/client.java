package client;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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
    private int size_package;

    public client(InetAddress dest, int port) throws UnknownHostException {
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
        this.protocol = "TCP";
        this.ventana = new window("Cliente " + this.destination_address.getHostName());
    }

    public client(InetAddress dest, int port, int size_package) throws UnknownHostException {
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
        if (size_package == 0) {
            this.size_package = 256;
        } else {
            this.size_package = size_package;
        }
        this.protocol = "UDP";
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

    private void udp(int size_package) {
        DatagramPacket pack = new DatagramPacket(new byte[size_package], size_package, this.destination_address, this.destination_port);
        try (DatagramSocket socket_udp = new DatagramSocket()) {
            socket_udp.send(pack);
            socket_udp.receive(pack); // se bloquea hasta recibir un datagrama
            byte[] buf = pack.getData();
            utilities.enteros mis_enteros = utilities.arrayByteToInts(buf);
            int rest = (mis_enteros.alto * mis_enteros.ancho * 4) - (size_package * mis_enteros.num_of_datagrams);
            ByteArrayOutputStream baos = new ByteArrayOutputStream(mis_enteros.num_of_datagrams * size_package + rest);
            BufferedImage image = new BufferedImage(mis_enteros.ancho, mis_enteros.alto, BufferedImage.TYPE_INT_RGB);
            buf = new byte[size_package];
            for (int dg = 0; dg <= mis_enteros.num_of_datagrams; dg++) {
                /*
                Esto se hace para controlar cuando tenemos paquetes de tamaño menor que el especificado,
                de esta manera podemos modificar el tamaño del paquete en cliente y servidor, al mismo
                tiempo para que se transmita la información mas rapido.
                 */
                if (dg == mis_enteros.num_of_datagrams) {
                    pack = new DatagramPacket(new byte[rest], rest);
                    socket_udp.receive(pack); // se bloquea hasta recibir un datagrama
                    baos.write(pack.getData());
                } else {
                    socket_udp.receive(pack); // se bloquea hasta recibir un datagrama
                    baos.write(pack.getData());
                }
            }
            byte[] image_data_byte = baos.toByteArray();
            int[] imagen_data_int = new int[image_data_byte.length / 4];
            int c = 0;
            buf = new byte[4];
            for (int i = 0; i < image_data_byte.length; i += 4) {
                buf[0] = image_data_byte[i];
                buf[1] = image_data_byte[i + 1];
                buf[2] = image_data_byte[i + 2];
                buf[3] = image_data_byte[i + 3];
                imagen_data_int[c] = utilities.byteArrayToInt(buf);
                c++;
            }
            System.out.println("Bytes recibidos: " + image_data_byte.length + ", Nums Enteros: " + imagen_data_int.length + ", Datagramas: " + mis_enteros.num_of_datagrams + ", Alto: " + mis_enteros.alto + ", Ancho: " + mis_enteros.ancho + ", Resto: " + rest);
            image.setRGB(0, 0, mis_enteros.ancho, mis_enteros.alto, imagen_data_int, 0, mis_enteros.ancho);
            this.ventana.setImage(image);
        } catch (SocketException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        if (this.protocol.contains("TCP")) {
            this.tcp();
        } else if (this.protocol.contains("UDP")) {
            this.udp(this.size_package);
        } else {
            this.interrupt();
        }
    }
}
