package ClienteServidorEntradas;

//
// author: Carlos de la Torre
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

//
// Como esta clase extiende de la clase Thread, y el procesamiento lo hacemos en el método "run()",
// El servidor de convierte en concurrente por que puede procesar varias peticiones al mismo tiempo. 
//
public class Acciones extends Thread {

    // Referencia a un socket para enviar/recibir las peticiones/respuestas
    private final Socket socketServicio;
    // Para que la respuesta sea siempre diferente, usamos un generador de números aleatorios.
    private final Random random;
    // lista de peliculas pasadas desde el servidor
    private final ArrayList<String> lista;

    // Constructor que tiene como parámetro una referencia al socket abierto en por otra clase
    public Acciones(Socket socketServicio, ArrayList<String> lista) {
        this.socketServicio = socketServicio;
        this.random = new Random();
        this.lista = lista;
    }

    // Aquí es donde se realiza el procesamiento realmente:
    @Override
    public void run() {

        // Estos serán los datos que recojemos desde el cliete
        String datosRecibidos;
        // Cadena de datos para enviar la respuesta al cliente
        String datosEnviar;

        try {
            // Obtiene los flujos de escritura/lectura
            PrintWriter outPrinter = new PrintWriter(socketServicio.getOutputStream(), true);
            BufferedReader inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
            // Enviamos la lista de peliculas en formato de texto por eso elegimos el 0
            outPrinter.println(respuesta(9));
            outPrinter.flush();
            // Quedamos a la espera de que responda el cliente
            datosRecibidos = inReader.readLine();
            // Creamos un String a partir de un array de bytes de tamaño "bytesRecibidos":
            int peticion = Integer.parseInt(datosRecibidos);
            // Convertimos el String de respuesta en una array de bytes:
            datosEnviar = respuesta(peticion);
            // enviamos la respuesta al cliente
            outPrinter.println(datosEnviar);
            outPrinter.flush();
            if (this.lista.isEmpty()){
                this.interrupt();
            }

        } catch (IOException e) {
            System.err.println("Error al obtener los flujo de entrada/salida.");
        }
    }

    private String respuesta(int peticion) {
        String resultado = "";
        if (peticion == 9) {
            for (int pelicula = 0; pelicula < this.lista.size(); pelicula++) {
                resultado = resultado + this.lista.get(pelicula) + ";";
            }
        } else {
            resultado = "La pelicula elegida es: " + this.lista.get(peticion) + ";";
            System.out.print("Ya no hay entradas para: " + this.lista.get(peticion) + "\n");
            this.lista.remove(peticion);
        }
        return resultado;
    }
}
