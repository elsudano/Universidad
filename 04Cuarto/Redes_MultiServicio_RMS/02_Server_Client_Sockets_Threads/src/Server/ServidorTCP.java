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
import java.net.UnknownHostException;

/**
 *
 * @author usuario
 */
public class ServidorTCP {

    public static void main(String args[]) throws UnknownHostException, IOException {
        try (ServerSocket ss = new ServerSocket(9999); Socket s = ss.accept()) {
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            out.println("Bienvenido a la asignatura RMS");
        } // espero a que llegue un cliente
    }
}
