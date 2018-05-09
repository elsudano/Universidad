
import client.client;
import server.server;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

/**
 *
 * @author usuario
 */
public class main {

    public static void main(String args[]) throws UnknownHostException, IOException, InterruptedException {
        /* Dirección donde esta escuchando el servidor y el cliente */
        String host = "localhost";
        
        /* Puerto donde esta escuchando el servidor */
        int port = 9999;

        /*
        Protocolo por el cual queremos que se comuniquen el 
        servidor y el cliente
         */
        String protocol = "TCP";

        /* si queremos pasar los parametros desde consola */
        if (args.length == 3) {
            host = args[0];
            port = Integer.parseInt(args[1]);
            protocol = args[2];
        }
        /* Dirección donde se encuentra el servidor */
        InetAddress address_server = InetAddress.getByName(host);
        
        /* Número de clientes que quieto lanzar para comprobar que funciona */
        int clients = 5;

        /* Abrimos el puerto del servidor */
        switch (protocol) {
            case "TCP": {
                ServerSocket listener_server = new ServerSocket(port);
                for (int i = 0; i < clients; i++) {
                    new client(address_server, port, protocol).start();
                }
                while (true){
                    new server(listener_server.accept()).start();
                }
            }
            case "UDP": {
                DatagramSocket listener_server = new DatagramSocket(port);
                for (int i = 0; i < clients; i++) {
                    new client(address_server, port, protocol).start();
                }
                while (true) {                    
                    new server(listener_server).start();
                }
            }
            default:
                break;
        }
    }
}
