
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
        /* Modo en el que se lanza el programa como cliente o servidor*/
        String mode = "CLIENT";

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
        if (args.length > 0 && args.length < 5) {
            if (args.length > 0)
                mode = args[0];
            if (args.length > 1)
                host = args[1];
            if (args.length > 2)
                port = Integer.parseInt(args[2]);
            if (args.length > 3)
                protocol = args[3];
        }
        /* Dirección donde se encuentra el servidor */
        InetAddress address_server = InetAddress.getByName(host);
        
        /* Número de clientes que quieto lanzar para comprobar que funciona */
        int clients = 5;

        /* Comprobamos el modo de ejecución */
        switch (mode.toUpperCase()) {
            case "CLIENT": {
                for (int i = 0; i < clients; i++) {
                    new client(address_server, port, protocol).start();
                    Thread.sleep(2000);
                }
                break;
            }
            case "SERVER": {
                switch (protocol.toUpperCase()) {
                    case "TCP": {
                        ServerSocket listener_server = new ServerSocket(port);
                        while (true) {
                            new server(listener_server.accept()).start();
                        }
                    }
                    case "UDP": {
                        DatagramSocket listener_server = new DatagramSocket(port);
                        //while (true) {
                            new server(listener_server).start();
                        //}
                    }
                    default:
                        break;
                }
                break;
            }
        }
    }
}
