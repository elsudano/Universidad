package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class ServidorConcurrenteTCP extends Thread {

    Socket id;

    public ServidorConcurrenteTCP(Socket s) {
        id = s;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(this.id.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(this.id.getInputStream()));
            while (!this.id.isClosed()) {
                ServidorConcurrenteTCP.sleep((long) (Math.random()*3000));
                int horas = LocalDateTime.now().getHour();
                int minutos = LocalDateTime.now().getMinute();
                int segundos = LocalDateTime.now().getSecond();
                out.println(horas + ":" + minutos + ":" + segundos);
                if (in.ready()) {
                    out.close();
                    in.close();
                    this.id.close();
                }
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ServidorConcurrenteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
