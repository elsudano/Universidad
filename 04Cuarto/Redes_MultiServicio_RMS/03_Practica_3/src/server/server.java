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
import utils.utilities;

/**
 *
 * @author usuario
 */
public class server extends Thread {

    private Object id_socket;
    private String protocol;
    private Webcam webcam;
    private int size_package;

    /* Constructor para el servidor TCP */
    public server(Socket id_socket) {
        this.id_socket = id_socket;
        this.protocol = "TCP";
        this.webcam = Webcam.getDefault();
        //this.webcam.setViewSize(new Dimension(640, 480));
    }

    /* Constructor para el servidor UDP */
    public server(DatagramSocket id_socket, int size_package) {
        this.id_socket = id_socket;
        this.protocol = "UDP";
        this.size_package = size_package;
        this.webcam = Webcam.getDefault();
        //this.webcam.setViewSize(new Dimension(640, 480));
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

    private void udp(int size_package) {
        try (DatagramSocket socket_udp = (DatagramSocket) this.id_socket) {
            DatagramPacket pack = new DatagramPacket(new byte[size_package], size_package);
            // while true
            this.webcam.open();
            BufferedImage image = this.webcam.getImage();
            this.webcam.close();
            int ancho = image.getWidth();
            int alto = image.getHeight();
            ByteArrayOutputStream baos = new ByteArrayOutputStream(ancho * alto);
            for (int w = 0; w < ancho; w++) {
                for (int h = 0; h < alto; h++) {
                    baos.write(image.getRGB(w, h));
                }
            }
            byte[] image_data = baos.toByteArray();
            int num_of_datagrams = image_data.length / size_package;
            int rest = image_data.length % size_package;
            System.out.println("Bytes a enviar: " + image_data.length + ", Datagramas: " + num_of_datagrams + ", Alto: " + alto + ", Ancho: " + ancho);
            baos.flush();
            baos.close();
            socket_udp.receive(pack);
            /**
             * las cuatro lineas de a continuación se encargan de crear el
             * primer paquete UDP que indica el numero de paquetes que se
             * enviarán primero se pone la dirección despúes se pone el puerto
             * despúes se divide la longitud de datos de la imágen por el tamaño
             * del paquete se convierte ese numero en un array de bytes y se
             * envia el paquete
             */
            pack.setAddress(pack.getAddress());
            pack.setPort(pack.getPort());
            byte[] buf = utilities.intsToArrayByte(num_of_datagrams, alto, ancho);
            pack.setData(buf);
            socket_udp.send(pack);
            buf = new byte[size_package];
            /* 
            Aquí hay que montar algo para cuando tenemos un resto de la imagen
            osea si tenemos la variables rest mayor que 0 recorrer hasta el 
            resto en el ultimo datagrama
             */
            for (int dg = 0; dg <= num_of_datagrams; dg++) {
                if (rest > 0 && dg == num_of_datagrams) {
                    pack = new DatagramPacket(new byte[rest], rest, pack.getAddress(), pack.getPort());
                    buf = new byte[rest];
                    for (int b = 0; b < rest; b++) {
                        buf[b] = image_data[b + (dg * size_package)];
                    }
                } else if (dg == num_of_datagrams-1){
                    for (int b = 0; b < size_package; b++) {
                        buf[b] = image_data[b + (dg * size_package)];
                    }
                }
                pack.setAddress(pack.getAddress());
                pack.setPort(pack.getPort());
                pack.setData(buf);
                socket_udp.send(pack);
                server.sleep(200);
            }
            // while true
        } catch (SocketException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | InterruptedException ex) {
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
            this.udp(this.size_package);
        } else {
            this.interrupt();
        }
    }
}
