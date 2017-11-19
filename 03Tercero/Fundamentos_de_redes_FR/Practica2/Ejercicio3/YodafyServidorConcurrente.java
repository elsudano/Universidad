package YodafyServidorConcurrente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
public class YodafyServidorConcurrente {

	public static void main(String[] args) {
	
		// Puerto de escucha
		int port=8989;
		// Cadena de texto con los datos del cliente
                String mensajes;
		// declaramos la variable del servidor
                ServerSocket socketServidor = null;
                // declaramos la variable para el servicio
                Socket socketServicio = null;
		try {
			// Abrimos el socket en modo pasivo, escuchando el en puerto indicado por "port"
			//////////////////////////////////////////////////
			socketServidor = new ServerSocket(port);
			//////////////////////////////////////////////////
			
			// Mientras ... siempre!
			do {
				// Aceptamos una nueva conexión con accept()
				/////////////////////////////////////////////////
				socketServicio = socketServidor.accept();
				//////////////////////////////////////////////////
				
				// Creamos un objeto de la clase ProcesadorYodafy, pasándole como 
				// argumento el nuevo socket, para que realice el procesamiento
				// Este esquema permite que se puedan usar hebras más fácilmente.
				ProcesadorYodafy procesador=new ProcesadorYodafy(socketServicio);
				procesador.start();
				
			} while (true);
			
		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}
