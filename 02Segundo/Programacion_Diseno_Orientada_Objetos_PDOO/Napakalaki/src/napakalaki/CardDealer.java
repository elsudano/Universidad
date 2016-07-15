package napakalaki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase principal del juego, contiene las instancias principales para el
 * desarrollo de una partida.
 *
 * @author: Carlos de la Torre
 */
public class CardDealer {

    /**
     * Instance es la unica instancia del mazo de cartas que hay en toda la
     * aplicacion de esta manera no se pueden repetir mazos de cartas, esto
     * sirve para las clases singleton.
     */
    private static final CardDealer INSTANCE = new CardDealer();
    /**
     * Estas variables son para el gestor de ficheros, se usan para poder leer
     * los ficheros de textos necesarios para cargar todos los monstruos,
     * tesoros y demas cartas en los mazos correspondientes.
     */
    private FileReader fr = null;
    /**
     * Estas variables son para el gestor de ficheros, se usan para poder leer
     * los ficheros de textos necesarios para cargar todos los monstruos,
     * tesoros y demas cartas en los mazos correspondientes.
     */
    private BufferedReader br = null;
    /**
     * Estas variables se usan para cuando se realizan las lecturas de los
     * ficheros de texto se puedan guardar en un modelo de datos tipo matriz.
     */
    private String[] columnas = null;
    /**
     * Estas variables se usan para cuando se realizan las lecturas de los
     * ficheros de texto se puedan guardar en un modelo de datos tipo matriz.
     */
    private String fila = null;
    /**
     * Este es el mazo de cartas que corresponde a los monstruos que NO se están
     * usando en la partida.
     */
    public ArrayList<Monster> mUnusedMonsters;
    /**
     * Este es el mazo de cartas que corresponde a los monstruos que se están
     * usando en la partida.
     */
    public ArrayList<Monster> mUsedMonsters;
    /**
     * Este es el mazo de cartas que corresponde a los tesoros que NO se están
     * usando en la partida.
     */
    public ArrayList<Treasure> mUnusedTreasures;
    /**
     * Este es el mazo de cartas que corresponde a los tesoros que se están
     * usando en la partida.
     */
    public ArrayList<Treasure> mUsedTreasures;
    /**
     * Este es el mazo de cartas que corresponde a los sectarios que NO se están
     * usando en la partida.
     */
    public ArrayList<Cultist> mUnusedCultists;
    /**
     * Este es el mazo de cartas que corresponde a los sectarios que se están
     * usando en la partida.
     */
    public ArrayList<Cultist> mUsedCultists;

    /**
     * Es el constructor por defecto, pero está privado por que vamos a usar la
     * clase de manera que sea una clase singleton, osea que solo habrá una
     * instancia de esta clase en todo el juego.
     */
    private CardDealer() {
        this.mUnusedMonsters = new ArrayList();
        this.mUsedMonsters = new ArrayList();
        this.mUsedTreasures = new ArrayList();
        this.mUnusedTreasures = new ArrayList();
        this.mUsedCultists = new ArrayList();
        this.mUnusedCultists = new ArrayList();
    }

    /**
     * Función auxiliar que se usa para poder leer el fichero de configuración
     * de los diferentes mazos de cartas cuando se usa el juego en un fichero
     * compilado .jar.
     *
     * @param recurso esta es una cadena de texto que será donde se encuentra el
     * fichero
     * @return devuelve un objeto File con el fichero que le hemos pasado por
     * parametros
     */
    private File ficheroExterno(String recurso) {
        File fichero = null;
        URL res = getClass().getResource(recurso);
        if (res.toString().startsWith("jar:")) {
            try {
                InputStream input = getClass().getResourceAsStream(recurso);
                fichero = File.createTempFile("tempfile", ".tmp");
                OutputStream out = new FileOutputStream(fichero);
                int read;
                byte[] bytes = new byte[1024];

                while ((read = input.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                fichero.deleteOnExit();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            //this will probably work in your IDE, but not from a JAR
            fichero = new File(res.getFile());
        }

        if (fichero != null && !fichero.exists()) {
            throw new RuntimeException("Error: File " + fichero + " not found!");
        }
        return fichero;
    }

    /**
     * Inicializa el monton de cartas de Tesoros para que se puedan usar en el
     * juego las carga desde un fichero de texto y las baraja.
     */
    private void initTreasureCardDeck() {
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            //String fichero = getClass().getResource("/resources/base_datos_tesoros.txt").getPath(); // < para Linux desde el IDE
            //String fichero = "C:\\Users\\pc\\Desktop\\Napakalaki\\src\\resources\\base_datos_tesoros.txt"; // < para Windows
            String fichero = "/resources/base_datos_tesoros.txt"; // < para Linux desde fichero JAR
            File file = this.ficheroExterno(fichero); // Creamos el fichero
            fr = new FileReader(file); // Creamos el manejador de ficheros
            br = new BufferedReader(fr); // Creamos el buffer de lectura para el fichero
            br.readLine(); // lo usamos para leer las cabeceras de las columnas 
            while ((fila = br.readLine()) != null) {
                columnas = fila.split(",");
                mUnusedTreasures.add(new Treasure(columnas[0], Integer.parseInt((columnas[4])), Integer.parseInt(columnas[2]), Integer.parseInt(columnas[3]), TreasureKind.valueOf(columnas[1].toUpperCase())));
            }
            this.shuffleTreasures();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Inicializa el monton de cartas de Monstruos para que se puedan usar en el
     * juego las carga desde un fichero de texto y las baraja.
     */
    private void initMonsterCardDeck() {
        try {
            BadConsequence malrollo = null;
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            //String fichero = getClass().getResource("/resources/base_datos_monstruos.txt").getPath(); // < para Linux desde el IDE
            //String fichero = "C:\\Users\\pc\\Desktop\\Napakalaki\\src\\resources\\base_datos_monstruos.txt"; // < para Windows
            String fichero = "/resources/base_datos_monstruos.txt"; // < para Linux desde fichero JAR
            File file = this.ficheroExterno(fichero); // Creamos el fichero
            fr = new FileReader(file); // Creamos el manejador de ficheros
            br = new BufferedReader(fr); // Creamos el buffer de lectura para el fichero
            br.readLine(); // lo usamos para leer las cabeceras de las columnas 
            while ((fila = br.readLine()) != null) {
                columnas = fila.split(",");
                if (columnas[10].equals("true")) {
                    malrollo = new DeathBadConsequence(columnas[4], true);
                } else if (!columnas[8].isEmpty() || !columnas[9].isEmpty()) {
                    ArrayList<TreasureKind> THidden = this.leeTesoros(columnas[8]);
                    ArrayList<TreasureKind> TVisible = this.leeTesoros(columnas[9]);
                    malrollo = new SpecificBadConsequence(columnas[4], Integer.parseInt(columnas[5]), false, THidden, TVisible);
                } else {
                    malrollo = new NumericBadConsequence(columnas[4], Integer.parseInt(columnas[5]), false, Integer.parseInt(columnas[6]), Integer.parseInt(columnas[7]));
                }
                Prize precio = new Prize(Integer.parseInt(columnas[2]), Integer.parseInt(columnas[3]));
                mUnusedMonsters.add(new Monster(columnas[0], Integer.parseInt(columnas[1]), malrollo, precio, Integer.parseInt(columnas[11])));
            }
            this.shuffleMonsters();
            fr.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Con este metodo lo que hacemos es cargar en el programa todas las cartas
     * necesarias para convertir a un jugador en sectario.
     */
    private void initCultistCardDeck() {
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            //String fichero = getClass().getResource("/resources/base_datos_sectarios.txt").getPath(); // < para Linux desde el IDE
            //String fichero = "C:\\Users\\pc\\Desktop\\Napakalaki\\src\\resources\\base_datos_sectarios.txt"; // < para Windows
            String fichero = "/resources/base_datos_sectarios.txt"; // < para Linux desde fichero JAR
            File file = this.ficheroExterno(fichero); // Creamos el fichero
            fr = new FileReader(file); // Creamos el manejador de ficheros
            br = new BufferedReader(fr); // Creamos el buffer de lectura para el fichero
            br.readLine(); // lo usamos para leer las cabeceras de las columnas 
            while ((fila = br.readLine()) != null) {
                columnas = fila.split(",");
                mUnusedCultists.add(new Cultist(columnas[0], Integer.parseInt(columnas[1])));
            }
            this.shuffleCultists();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Con este metodo lo que hacemos es convertir una cadena de texto separada
     * por "," en un ArrayList<TreasureKind> de esa manera podemos tener los
     * objetos que realmente necesitamos en el ArrayList devuelto.
     *
     * @param datos una cadena de texto separada por comas con los objetos
     * @return Es un ArrayList<TreasureKind> que contiene los objetos
     */
    private ArrayList<TreasureKind> leeTesoros(String datos) {
        String[] objTesoro = datos.toUpperCase().split("-");
        ArrayList<TreasureKind> Tesoros = new ArrayList<>();

        for (String miTesoro : objTesoro) {
            if (!miTesoro.isEmpty()) {
                Tesoros.add(TreasureKind.valueOf(miTesoro));
            }
        }
        return Tesoros;
    }

    /**
     * Con este metodo barajamos las cartas del monton de los Tesoros.
     */
    private void shuffleTreasures() {
        Collections.shuffle(this.mUnusedTreasures);
    }

    /**
     * Con este metodo barajamos las cartas del monton de los monstruos.
     */
    private void shuffleMonsters() {
        Collections.shuffle(this.mUnusedMonsters);
    }

    /**
     * Con este metodo barajamos las cartas del monton de los Sectarios.
     */
    private void shuffleCultists() {
        Collections.shuffle(this.mUnusedCultists);
    }

    /**
     * Metodo que sirve para poder obtener la unica instancia del manejador de
     * cartas que tendrá el juego.
     *
     * @return la instancia del CardDealer
     */
    public static CardDealer getInstance() {
        return INSTANCE;
    }

    /**
     * Con este metodo conseguiremos que nos devuelvan el siguiente tesoro que
     * no se este usando.
     *
     * @return el siguiente Tesoro
     */
    public Treasure nextTreasure() {
        if (this.mUnusedTreasures.isEmpty()) {
            this.mUnusedTreasures = new ArrayList<>(this.mUsedTreasures);
            this.shuffleTreasures();
            this.mUsedTreasures.clear();
        }
        Treasure tesoro = this.mUnusedTreasures.get(0);
        this.mUnusedTreasures.remove(0);
        return tesoro;
    }

    /**
     * Con este metodo conseguiremos que nos devuelvan el siguiente monstruo que
     * no se este usando.
     *
     * @return el siguiente Monstruo
     */
    public Monster nextMonster() {
        if (this.mUnusedMonsters.isEmpty()) {
            this.mUnusedMonsters = new ArrayList<>(this.mUsedMonsters);
            this.shuffleMonsters();
            this.mUsedMonsters.clear();
        }
        Monster monstruo = this.mUnusedMonsters.get(0);
        this.mUnusedMonsters.remove(0);
        return monstruo;
    }

    /**
     * Con este metodo conseguiremos que nos devuelvan el siguiente player
     * Sectario que no se este usando.
     *
     * @return el siguiente Carta del Sectario
     */
    public Cultist nextCultists() {
        if (this.mUnusedCultists.isEmpty()) {
            this.mUnusedCultists = new ArrayList<>(this.mUsedCultists);
            this.shuffleCultists();
            this.mUsedCultists.clear();
        }
        Cultist cultist = this.mUnusedCultists.get(0);
        this.mUnusedCultists.remove(0);
        return cultist;
    }

    /**
     * Con este metodo conseguiremos insertar en los tesoros usuados el tesoro
     * cuyo parámetro sea pT
     *
     * @param t de tipo Treasure es el tesoro que vamos a usar
     */
    public void giveTreasureBack(Treasure t) {
        this.mUsedTreasures.add(t);
    }

    /**
     * Con este metodo conseguiremos insertar en los tesoros usuados el tesoro
     * cuyo parámetro sea pT
     *
     * @param m de tipo Monstruo es el monstruo que vamos a usar
     */
    public void giveMonsterBack(Monster m) {
        this.mUsedMonsters.add(m);
    }

    /**
     * Sirve para poder construir la bases de datos de cartas y barajarlas para
     * el juego
     */
    public void initCards() {
        this.initMonsterCardDeck();
        this.initTreasureCardDeck();
        this.initCultistCardDeck();
    }

    /**
     * Devuelve el array completo de monstruos que estamos usando.
     *
     * @return el parametro mUsedMonsters
     */
    public ArrayList<Monster> getUsedMonsters() {
        return this.mUsedMonsters;
    }

    /**
     * Devuelve el array completo de los Treasures que estamos usuando.
     *
     * @return el parametro mUsedTreasures
     */
    public ArrayList<Treasure> getUsedTreasures() {
        return this.mUsedTreasures;
    }
}
