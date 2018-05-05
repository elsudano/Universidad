/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author usuario
 */
class ServidorConcurrenteTCP extends Thread {

    Socket id;

    public ServidorConcurrenteTCP(Socket s) {
        id = s;
    }

    @Override
    public void run() {
        try {
            PrintWriter out = new PrintWriter(this.id.getOutputStream(), true);
            while (true) {
                int horas = LocalDateTime.now().getHour();
                int minutos = LocalDateTime.now().getMinute();
                int segundos = LocalDateTime.now().getSecond();
                out.println(horas + ":" + minutos + ":" + segundos);
                Thread.sleep(2000);
            }
        } catch (IOException | InterruptedException e) {
        }
    }

    public static void main(String args[]) throws IOException {
        ServerSocket ss = new ServerSocket(9999);
        while (true) {
            new ServidorConcurrenteTCP(ss.accept()).start();
        }
    }
}
