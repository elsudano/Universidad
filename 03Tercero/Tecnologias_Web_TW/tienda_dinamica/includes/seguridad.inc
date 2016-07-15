<?php
    require_once 'includes/usuarioDAO.class.inc';
    require_once 'includes/config.inc';
    /*
    * author: Carlos de la Torre
    * application: Tienda OnLine con noticias DEMO
    * 
    */


    /**
    * Universal session status
    * @return bool
    */
    function is_session_started() {
        if ( php_sapi_name() !== 'cli' ) {
            if ( version_compare(phpversion(), '5.4.0', '>=') ) {
                return session_status() === PHP_SESSION_ACTIVE ? TRUE : FALSE;
            } else {
                return session_id() === '' ? FALSE : TRUE;
            }
        }
        return FALSE;
    }

    /**
    * Redirigir al usuario en cualquier caso
    * @return none
    */
    function enviarA($url){
        if (headers_sent()):
            die('<script type="text/javascript">window.location.href="' . $url . '";</script>');
        else:
            header('Location: ' . $url);
            die();
        endif;
    }

    /*
     * Esto es para activar o desactivar el modo depuración
     */
    if (DEBUG):
        error_reporting(E_ALL); // Nos aseguramos que si algo falla en la conexión salga el resultado en el navegador
        ini_set('display_errors', 'on');
    else:
        error_reporting(0);
        ini_set('display_errors', 'off');
    endif;

    /**
     * Esto es para poder saber cual es
     * el directorio raiz de la aplicación
     * y después redirigir al usuario
     **/
    $dir = dirname(__FILE__);
        while(!file_exists($dir . "/index.php")){
        $dir = dirname($dir);
    }

    $miManejadorDeUsuarios = new UsuarioPDO(TIPODB);
    
    session_start(); // Iniciamos la Session
    if (empty($_SESSION['logueado'])) {
       $_SESSION['logueado'] = FALSE;
    }
    $miUsuario = NULL; // variable donde se guardan los resultados de las sentencias SQL

    if (isset($_POST['validar'])) { // Cuando se ha pulsado por primera vez el botón se ejecuta el código
        // Definimos $usuario y $contrasena
        $_SESSION['usuario'] = $_POST['usuario'];
        // Convertimos la contraseña al hash de SHA1
        $contrasena = sha1($_POST['contrasena']);
        // y fijamos la variable logueado a FALSE
        $_SESSION['logueado'] = FALSE;
        // la contraseña hay que ponerla entre '' por culpa de los caracteres raros del HASH
        $miUsuario = $miManejadorDeUsuarios->IdentificarUsuario($_SESSION['usuario'],$contrasena);

        if ($miUsuario !== NULL AND $miUsuario->cualEsValidado()) {
            // Inicializamos la Sesión del usuario
            $_SESSION['usuario'] = $miUsuario->cualEsNif();
            // Añadimos el dia y el mes actual para no pasar la contraseña real por session
            $_SESSION['contrasena'] = sha1($contrasena . date('dm')); 
            // y fijamos la variable logueado a TRUE
            $_SESSION['logueado'] = TRUE;
            // Variable global que guarda el nivel de seguridad que tiene el usuario
            $_SESSION['nivel'] = $miUsuario->cualEsNivel();
            // vacío la variable de error para que no me salga impreso
            $_SESSION['error'] = '';
            // Redirigimos al usuario a la página en donde se encontraba
            header("location: ".$_POST['ruta']);
        }elseif ($miUsuario !== NULL){
            $_SESSION['error'] = "El usuario" . $_SESSION['usuario'] . " ya esta dado de alta pero no ha validado su correo";
            header("location: entrar.php"); // redirigimos al usuario
        }else{
            $_SESSION['error'] = "La contrase&ntilde;a facilitada para el usuario: " . $_SESSION['usuario'] . " NO es correcta ";
            enviarA(RAIZ); // redirigimos al usuario
        }
    }elseif(isset($_POST['salir'])){
        // destruimos la session del usuario
        session_destroy();
        enviarA($_POST['ruta']);// redirigimos al usuario
        //header("location: ".$_POST['ruta']);
    }elseif($_SESSION['logueado']){
        $miUsuario = $miManejadorDeUsuarios->GetUsuario($_SESSION['usuario']);

        // solo 1 resultado fila [0], el campo [contrasena], mas el dia mes date(...), comparamos con la session 
        if ( ($miUsuario !== NULL) AND ($_SESSION['contrasena'] !== sha1($miUsuario->cualEsContrasena() . date('dm'))) ) {
            $_SESSION['logueado'] = FALSE;
            // como se ha manipulado el usuario indevidamente mando el mensaje de error
            $_SESSION['error'] = "Se ha cerrado la sesi&oacute;n para prevenir intrusiones";
            enviarA(RAIZ.'entrar.php'); // redirigimos al usuario
        }
    }else{
    	$_SESSION['error'] = "Se ha producido un error desconocido, vuelva a intentarlo";
    	// todo: poner aquí algún mensaje de error por si no valida el usuario
    }
?>
