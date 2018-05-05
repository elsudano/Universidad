/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
class ClienteTCP extends Thread {

    @Override
    public void run() {
        int count = 0;
        try (Socket s = new Socket("localhost", 9999)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            while (count < 20) {
                System.out.println(in.readLine());
                count += 1;
            }
        } catch (IOException ex) {
            Logger.getLogger(ClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) throws InterruptedException {
        int clients = 3;
        for (int i = 0; i < clients; i++) {
            new ClienteTCP().start();
            Thread.sleep((long) (Math.random()*1000));
        }
    }
}
