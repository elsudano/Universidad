package ClienteServidorEntradas;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        // mensaje que pretendemos enviar
        String buferEnvio;
        // respuesta del servidor, ¿que hacemos con ella?
        String buferRecepcion;
        // Nombre del host donde se ejecuta el servidor:
        String host = "localhost";
        // Puerto en el que espera el servidor:
        int port = 8989;
        // Socket para la conexión TCP
        Socket socketServicio = null;
        
        try {
            // Creamos un socket que se conecte a "hist" y "port":
            socketServicio = new Socket(host, port);
            // esto lo usamos para enviar datos al servidor en modo texto
            PrintWriter outPrinter = new PrintWriter(socketServicio.getOutputStream(), true);
            // esto lo usamos para leer datos desde el servidor en modo texto
            BufferedReader inReader = new BufferedReader(new InputStreamReader(socketServicio.getInputStream()));
            // Leemos la respuesta del servidor con las peliculas
            buferRecepcion = inReader.readLine();
            // Si queremos enviar una cadena de caracteres por un OutputStream
            // hay que pasarla primero a un array de bytes:
            buferEnvio = eleccion(buferRecepcion);
            // Enviamos el array por el outputStream;
            outPrinter.print(buferEnvio);
            // Aunque le indiquemos a TCP que queremos enviar varios arrays de bytes
            // sólo los enviará efectivamente cuando considere que tiene suficientes
            // datos que enviar... Podemos usar "flush()" para obligar a TCP a que no
            // espere para hacer el envío:
            outPrinter.flush();
            // Una vez terminado el servicio, cerramos el socket (automáticamente se
            // cierran el inpuStream  y el outputStream)
            outPrinter.close();
            inReader.close();

            // Excepciones:
        } catch (UnknownHostException e) {
            System.err.println("Error: Nombre de host no encontrado.");
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al abrir el socket.");
        }
    }

    private static String eleccion(String datos) {
        // cadena que se va a enviar
        String envio;
        // Esto es para la entrada de teclado
        Scanner teclado = new Scanner(System.in);
        //lista de peliculas
        String[] peliculas = datos.split(";");
        // pintaos la lista
        for (int i = 0; i < peliculas.length; i++) {
            System.out.print(i+".- "+peliculas[i]+"\n");
        }
        System.out.print("El 9 repite la lista\n");
        // elejimos la pelicula
        System.out.print("Por favor elija que pelicula quiere ver\n");
        envio = teclado.nextLine();

        return envio;
    }
}
