package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class server extends Thread {

    private Object id_socket;
    private String protocol;

    /* Constructor para el servidor TCP */
    public server(Socket id_socket) {
        this.id_socket = id_socket;
        this.protocol = "TCP";
    }

    /* Constructor para el servidor UDP */
    public server(DatagramSocket id_socket) {
        this.id_socket = id_socket;
        this.protocol = "UDP";
    }

    private void send(Object data) {

    }

    private Object recive() {
        Object response = null;

        return response;
    }

    private void tcp() {
        try (Socket socket_tcp = (Socket) this.id_socket) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket_tcp.getInputStream()));
            PrintWriter out = new PrintWriter(socket_tcp.getOutputStream(), true);
            String data = "";
            while (!socket_tcp.isClosed()) {
                if (data.contains("imagen")) {
                    out.println("Response by server to: " + socket_tcp.getPort() + " client");
                    /* Aqui es donde tenes que poner las imagenes de la camara para enviar */
                } else if (data.contains("exit")) {
                    in.close();
                    socket_tcp.close();
                    break;
                }
                data = in.readLine(); /* se bloquea esperando al cliente */
                System.out.println("Server: " + this.getName() + ":" + socket_tcp.getLocalPort() + " sigue vivo");
                System.out.println("Server: data recive: " + data);
            }
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
