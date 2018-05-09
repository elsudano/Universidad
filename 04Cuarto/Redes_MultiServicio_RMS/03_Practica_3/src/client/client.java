package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class client extends Thread {

    private InetAddress destination_address;
    private int destination_port;
    private String protocol;

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
    }

    private void send(Object data) {

    }

    private Object recive() {
        Object response = null;

        return response;
    }

    private void tcp() {
        long stop = System.currentTimeMillis() + 5000;
        try (Socket socket_tcp = new Socket(this.destination_address, this.destination_port)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket_tcp.getInputStream()));
            PrintWriter out = new PrintWriter(socket_tcp.getOutputStream(), true);
            String data = "";
            while (true) {
                if (System.currentTimeMillis() > stop) { /* Este if es donde tenes que poner la condicion de parada */
                    out.println("exit");
                    break;
                } else {
                    /* Aqui es donde tienes que poner la recepcion de las imagenes */
                    client.sleep(700); /* es para poder lanzar el servidor despues del cliente */                    
                    System.out.println("Client: " + socket_tcp.getLocalPort() + " sigue vivo");
                    out.println("imagen");
                    data = in.readLine(); /* se bloquea esperando al servidor */
                    System.out.println("Client: data recive: " + data);
                }
            }
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void udp(String data) {
        long stop = System.currentTimeMillis() + 5000;
        byte[] buf = new byte[256];

        try (DatagramSocket socket_udp = new DatagramSocket()) {
            DatagramPacket pack = new DatagramPacket(buf, buf.length, this.destination_address, this.destination_port);
            while (true) {
                client.sleep(700);
                /* Aqui es donde tienes que poner la recepcion de las imagenes */
                buf = data.getBytes();
                pack.setData(buf);
                socket_udp.send(pack);
                if (System.currentTimeMillis() > stop) {
                    /* Este if es donde tenes que poner la condicion de parada */
                }
                socket_udp.receive(pack); // se bloquea hasta recibir un datagrama
                System.out.write(pack.getData());
            }
        } catch (SocketException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | InterruptedException ex) {
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
            this.udp("Esto es una prueba de udp");
        } else {
            this.interrupt();
        }
    }
}
