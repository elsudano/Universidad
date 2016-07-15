<?php
require_once('config.inc'); // Parámetros de configuración para la base de datos
/**
* Esta clase es para manejar las diferentes bases de datos
*/
abstract class BaseDeDatos {
    
    private $servidor = '';
    private $puerto = '';
    private $base_de_datos = '';
    private $usuario = '';
    private $contrasena = '';
    private $tipo_db = '';
    private $conndb = NULL;
    private $estaConectado = FALSE;
    private $esquema ='dbo'; // de momento lo ponemos aquí
    private $charset = 'utf8'; // pero poner el set para ambas
    private $error = '';
    
    public function __construct($serv = SERVIDOR_DB, $db = BASE_DATOS, $usu = USUARIO, $pass = CONTRASENA){
        $this->servidor = $serv;
        $this->base_de_datos = $db;
        $this->usuario = $usu;
        $this->contrasena = $pass;
    }
    
    
    // Muestra la cadena del servidor
    // @param no tiene
    // @return devuelve un tipo string con la cadena del servidor
    // @error devuelve un tipo string vacio

    protected function cualEsServidor(){
        return $this->servidor;
    }
    
    // Muestra la cadena del puerto almacenado para la conexión
    // @param no tiene
    // @return devuelve un tipo string con la cadena del puerto
    // @error devuelve un tipo string vacio

    protected function cualEsPuerto(){
        return $this->puerto;
    }

    // Especifica el puerto de conexión
    // @param de tipo int especifica el numero de puerto para la conexión
    // @return no devuelve nada
    // @error si el puerto no esta entre 1025 y 65535 pone el puerto a vacio

    protected function elPuertoES($port){
        if (1025 <= $port and  $port <= 65535)
            $this->puerto = $port;
        else{
            $this->puerto = '';
        }
    }
    
    // Muestra la base_de_datos a la que conectamos
    // @param no tiene
    // @return devuelve un tipo string con la base de datos
    // @error devuelve un tipo string vacio

    protected function cualEsBaseDatos(){
        return $this->base_de_datos;
    }
    
    // Muestra cual es el tipo de la base de datos al que estamos conectados
    // @param no tiene
    // @return devuelve un tipo string con el tipo de la base de datos
    // @error devuelve un tipo string vacio

    protected function cualEsTipoDB(){
        return $this->tipo_db;
    }

    // Muestra si esta conectada la base de datos
    // @param no tiene
    // @return devuelve un tipo boolean, true si esta conectada
    // @error devuelve un tipo boolean, false si NO esta conectada

    public function estaConectado(){
        return $this->estaConectado;
    }
    
    // Muestra cual es el estado del ultimo error
    // @param no tiene
    // @return devuelve un tipo string con el mensaje de error generado
    // @error devuelve un tipo string vacio ni no hay errores en la conexión

    public function ultimoError(){
        return $this->error;
    }

    // realizar la conexión indicando el tipo de base de datos
    // @param de tipo string, especifica cual es el tipo de base de datos a conectar
    // @return devuelve un tipo conndb que contiene la conexión
    // @error devuelve un tipo string vacio, y pone un mensaje de error recuperable
    // mediante la función ultimoError()

    protected function Conectar($tipo){
        if ($tipo == ''){
            $this->error = "No se ha definido al tipo de base de datos que se quiere conectar";
        }else{
            $this->tipo_db = $tipo;
            switch ($this->tipo_db) {
                case "pg_sql":
                    if ($this->puerto == ''){
                        $this->puerto = '5432';
                    }
                    $this->conndb = pg_pconnect('host=' . $this->servidor . ' port=' . $this->puerto . ' dbname=' . $this->base_de_datos . ' user=' . $this->usuario . ' password=' . $this->contrasena) or die ("Fallo al intentar conectar con la base de datos" . pg_last_error($this->conndb));
                    if ($this->conndb){
                        $this->estaConectado = TRUE;
                    }else{
                        $this->error = 'No he podido conectar con el servidor de Base de datos de PostgreSQL';
                    }
                    break;
                case "my_sql":
                    if ($this->puerto == ''){
                        $this->puerto = '3306';
                    }
                    $this->conndb = mysqli_connect($this->servidor, $this->usuario, $this->contrasena, $this->base_de_datos);
                    if ($this->conndb){
                        $this->estaConectado = TRUE;
                    }else{
                        $this->error = 'No he podido conectar con el servidor de Base de datos de MySQL';
                    }
                    break;
                case "my_sql_pdo": // esta forma es por si la conexión se hace por DSN
                    //echo '"mysql:host='. $this->servidor .';dbname='. $this->base_de_datos .';charset='. $this->charset .' Usuario: '. $this->usuario .' Contraseña:'. $this->contrasena .'"<br />';
                    try{
                        $this->conndb = new PDO('mysql:host='.$this->servidor.';dbname='.$this->base_de_datos.';charset='.$this->charset, $this->usuario, $this->contrasena);
                        //echo 'mysql:host='.$this->servidor.';dbname='.$this->base_de_datos.';charset='.$this->charset . ' Usuario ' . $this->usuario . ' contrasena ' . $this->contrasena;
                        // Se permite a PHP que mantenga la conexión MySQL abierta para
                        // que se emplee en otras partes de la aplicación.
                        $this->conndb->setAttribute(PDO::ATTR_PERSISTENT, true); 
                        $this->conndb->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
                        $this->estaConectado = TRUE;
                    }catch (PDOException  $e){
                        die('No me puedo conectar a la base de datos: '. $e->getMessage());
                    }
                    break;
                case "ms_sql":
                    if ($this->puerto == ''){
                        $this->puerto = '1433';
                    }
                    $this->conndb = mssql_connect($this->servidor . ":" . $this->puerto . "\ " . $this->base_de_datos , $this->usuario , $this->contrasena) or die ("Fallo al intentar conectar con la base de datos" . mssql_get_last_message($this->conndb));
                    if ($this->conndb){
                        $this->estaConectado = TRUE;
                    }else{
                        $this->error = 'No he podido conectar con el servidor de Base de datos de Microsoft';
                    }
                    break;
                default:
                    $this->error = 'El tipo de base de datos no es manejable por la clase, opciones: my_sql, my_sql_pdo, pg_sql, ms_sql';
                    break;
            }
        }
        return $this->conndb;
    }

    // Limpia toda la memoria cuando no se usa el objeto
    // @param no tiene
    // @return nada
    // @error ninguno

    public function __destruct() {
        // Cerramos la Conexión
        switch ($this->tipo_db) {
            case "pg_sql":
                pg_close($this->conndb);
                break;
            case "my_sql":
                mysqli_close($this->conndb);
                break;
            case "ms_sql":
                mssql_close($this->conndb);
                break;
            default:
                break;
        }
        unset($this->servidor);
        unset($this->puerto);
        unset($this->base_de_datos);
        unset($this->esquema);
        unset($this->usuario);
        unset($this->contrasena);
        unset($this->conndb);
        unset($this->estaConectado);
        unset($this->tipo_db);
        unset($this->charset);
        unset($this->error);
    }
}
?>