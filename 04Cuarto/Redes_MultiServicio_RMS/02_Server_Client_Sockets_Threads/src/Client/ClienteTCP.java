package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class ClienteTCP extends Thread {

    @Override
    public void run() {
        int count = 0;
        try (Socket s = new Socket("localhost", 9999)) {
            ClienteTCP.sleep(2000);
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            while (count < 5) {
                System.out.println("Hora del Cliente: " + s.getLocalPort() + " = " + in.readLine());
                count += 1;
            }
            out.println("exit");
        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
