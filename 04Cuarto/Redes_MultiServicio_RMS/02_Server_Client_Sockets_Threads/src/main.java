
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class main {

    public static void main(String args[]) {
        ServerSocket ss;
        int clients = 5;
        try {
            ss = new ServerSocket(9999);
            for (int i = 0; i < clients; i++) {
                new Client.ClienteTCP().start();
                new Server.ServidorConcurrenteTCP(ss.accept()).start();
            }
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
