package ClienteServidorEntradas;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//
// author: Carlos de la Torre
//
public class Servidor {

    public static void main(String[] args) {

        // Puerto de escucha
        int port = 8989;
        // Cadena de texto con los datos del cliente
        ArrayList<String> listaPeliculas = new ArrayList<String>();
        // declaramos la variable del protocolo TCP para el servidor
        ServerSocket socketServidor = null;
        // declaramos la variable para el servicio
        Socket socketServicio = null;
        
        listaPeliculas.add("El caso burne");
        listaPeliculas.add("El hombre araña");
        listaPeliculas.add("La mosca");
        listaPeliculas.add("Los tres cerditos");
        
        try {
            // Abrimos el socket en modo pasivo, escuchando el en puerto indicado por "port"
            socketServidor = new ServerSocket(port);

            do {
                // Aceptamos una nueva conexión con accept()
                socketServicio = socketServidor.accept();

                // Creamos un objeto de la clase Acciones, pasándole como 
                // argumento el nuevo socket, para que realice el procesamiento
                // Este esquema permite que se puedan usar hebras más fácilmente.
                Acciones procesador = new Acciones(socketServicio,listaPeliculas);
                procesador.start();

            } while (true);

        } catch (IOException e) {
            System.err.println("Error al escuchar en el puerto " + port);
        }
    }
}
