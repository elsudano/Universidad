<?php
require_once('base_de_datos.class.inc'); // necesario para la extención y la conexión de la BD
require_once('usuario.class.inc'); // necesario para crear los objetos de tipo usuario

/**
* Esta clase es para manejar a los usuarios
* Columna			Tipo			Nulo	Predeterminado	Comentarios
* US_id (Primaria)	int(20)			No		
* US_nombre			char(20)		Sí		NULL			El nombre real del Usuario
* US_apellidos		varchar(40)		Sí		NULL			os Apellidos del Usuario
* US_nif			bigint(20)		No						El DNI sin la letra del Usuario
* US_contrasena		varchar(100)	No						La contraseña cifrada en SHA1
* US_nivel			enum(			No		invitado		Este es el nivel de acceso que tendra el usuario que entre a nuestra página
					'invitado',
					'usuario',
					'admin')	
* US_validado		tinyint(1)		No		0				Para validar el correo del usuario
* US_email			varchar(50)		No						Hace falta para validar al Usuario y pasar de invitado a usuario
* US_foto			blob			Sí		NULL
*/

class UsuarioPDO extends BaseDeDatos{

	public function __construct($p_TIPO){
		parent::__construct();
		$this->conndb = $this->Conectar($p_TIPO);
	}

	// todo: tienes que hacer que se guarden todos los usuarios de la
	// base de datos pero creando un objeto usuario por cada uno
	// de los que estan almacenados en la tabla osea 
	// recorrer la tabla y crear un new Usuario y guardarlo en 
	// otro array diferente que solo contenga una objetos del tipo
	// Usuario pero con todos los datos ya cargados
	// @param no tiene parametros de entrada
	// @return devuelve un array de php con objetos de tipo Usuario
	// @error si no existen usuarios devuelve un array vacio

	public function TodosLosUsuarios(){
		$array = array(); // Este array lo usamos para el return
		$SQL = "SELECT * FROM USuarios";
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// Y se ejecuta la consulta:
			$sentenciaSQL->execute();
			// Para recorrer los registros recuperados se emplea el método fetchAll:
			foreach ($sentenciaSQL->fetchAll() as $fila) {
				$array[] = new Usuario($fila['US_nombre'],$fila['US_nif'],$fila['US_apellidos'],$fila['US_contrasena'],$fila['US_nivel'],$fila['US_validado'],$fila['US_email'],$fila['US_foto'],$fila['US_id']);
			}
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $array;
	}

//-----------------Para encontrar a un usuario por su ID--------------------------------

	// todo: esto es basicamente lo mismo que el anterior metodo
	// pero en este caso le pasamos un nombre del usuario para buscar
	// solo un usuario
	// @param p_ID es el ID que identifica al usuario
	// @return objeto de tipo Usuario que contiene al usuario definido por su ID
	// @error si no hay usuario devuelve el valor NULL

	public function UsuarioPorId($p_ID){
		$SQL = "SELECT * FROM USuarios WHERE US_id = :id";
		$sentenciaSQL = $this->conndb->prepare($SQL);
		$resultado = NULL;
		try{
			// Seguidamente se asignan valores a los marcadores de parámetros
			$sentenciaSQL->bindValue(":id", $p_ID, PDO::PARAM_INT);
			// Y se ejecuta la consulta:
			$sentenciaSQL->execute();
			// como se supone que solo hay un usuario lo guardo y lo creo como objeto
			$fila = $sentenciaSQL->fetch();
			// compruebo que el resultado tiene datos y creo el usuario resultante
			if ($fila) {
				$resultado = new Usuario($fila['US_nombre'],$fila['US_nif'],$fila['US_apellidos'],$fila['US_contrasena'],$fila['US_nivel'],$fila['US_validado'],$fila['US_email'],$fila['US_foto'],$fila['US_id']);
			}
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $resultado;
	}

//-----------------Para encontrar a un usuario por su NIF--------------------------------

	// todo: esto es basicamente lo mismo que el anterior metodo
	// pero en este caso le pasamos un nombre del usuario para buscar
	// solo un usuario
	// @param p_NIF es el NIF que identifica al usuario
	// @return objeto de tipo usuario que contiene al usuario definido por su NIF
	// @error si no hay usuario devuelve el valor NULL

	public function GetUsuario($p_NIF){
		$SQL = "SELECT * FROM USuarios WHERE US_nif = :nif";
		$sentenciaSQL = $this->conndb->prepare($SQL);
		$resultado = NULL;
		try{
			// Seguidamente se asignan valores a los marcadores de parámetros
			$sentenciaSQL->bindValue(":nif", $p_NIF, PDO::PARAM_STR);
			// Y se ejecuta la consulta:
			$sentenciaSQL->execute();
			// como se supone que solo hay un usuario lo guardo y lo creo como objeto
			$fila = $sentenciaSQL->fetch();
			// compruebo que el resultado tiene datos y creo el usuario resultante
			if ($fila) {
				$resultado = new Usuario($fila['US_nombre'],$fila['US_nif'],$fila['US_apellidos'],$fila['US_contrasena'],$fila['US_nivel'],$fila['US_validado'],$fila['US_email'],$fila['US_foto'],$fila['US_id']);
			}
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $resultado;
	}

//-----------------Para encontrar a un usuario por su nombre y contraseña--------------------------------

	// Esto es basicamente lo mismo que el anterior metodo
	// pero usamos el nombre y la contraseña del usuario para identificarlo
	// @param p_USUARIO es el nombre del usuario
	// @param p_CONTRASENA es la contraseña del
	// @return objeto de tipo usuario que contiene al usuario definido nombre y contraseña
	// @error si no hay usuario devuelve el valor NULL

	public function IdentificarUsuario($p_USUARIO,$p_CONTRASENA){
		$SQL = "SELECT * FROM USuarios WHERE US_nombre = :nombre AND US_contrasena = :contrasena";
		$sentenciaSQL = $this->conndb->prepare($SQL);
		$resultado = NULL;
		try{
			// Seguidamente se asignan valores a los marcadores de parámetros
			$sentenciaSQL->bindValue(":nombre", $p_USUARIO, PDO::PARAM_STR);
			$sentenciaSQL->bindValue(":contrasena", $p_CONTRASENA, PDO::PARAM_STR);
			// Y se ejecuta la consulta:
			$sentenciaSQL->execute();
			// como se supone que solo hay un usuario lo guardo y lo creo como objeto
			$fila = $sentenciaSQL->fetch();
			// compruebo que el resultado tiene datos y creo el usuario resultante
			if ($fila) {
				$resultado = new Usuario($fila['US_nombre'],$fila['US_nif'],$fila['US_apellidos'],$fila['US_contrasena'],$fila['US_nivel'],$fila['US_validado'],$fila['US_email'],$fila['US_foto'],$fila['US_id']);
			}
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $resultado;
	}
//-----------------Para dar de alta usuario con diferentes parámetros--------------------------------
	
	// Esta funcion se encarga de llenar los datos del usuario
	// a la tabla correstondiente y comprobar que todos los datos del
	// usuario están correctos.
	// [] parámetros opcionales
	// @param $nom de tipo string será el nombre del usuario
	// @[param] $ape de tipo string serán los apellidos del usuario
	// @param $nif de tipo int será el NIF del usuario
	// @[param] $pass de tipo string será la contraseña del usuario
	// @[param] $email de tipo string será el correo del usuario
	// @[param] $foto de tipo binario será la foto del usuario
	// @return devuelve un tipo de dato boolean, TRUE si lo creado correctamente
	// @error devuelve un tipo de dato boolean, FALSE si NO puede crearlo

	public function AltaDeUsuario($p_NOMBRE, $p_APELLIDOS = 'no los puso', $p_NIF, $p_PASS = 'pordefecto', $p_NIVEL = 'invitado', $p_VALIDADO,$p_EMAIL = 'nocorreo@nodominio.com', $p_FOTO){
		$resultado = FALSE;
		$foto = NULL;
		//cifrado de la contraseña
		$contrasena = sha1($p_PASS);
		//Como solo guardo el NIF le quito la letra
		$nif = substr($p_NIF,0,8);
		// compruebo que viene alguna foto si es así la proceso
		if ($p_FOTO['size'] > 0):
			$boolean = preg_match('#^image\/(jpeg|png|gif|tiff|)$#',$p_FOTO['type']);
			if ( $boolean AND $p_FOTO['error']=== 0):
				// aqui se puede poner un gestor de errores en las siguientes versiones
				$foto = file_get_contents($p_FOTO['tmp_name']);
			endif;
		endif;

		$SQL = "INSERT INTO USuarios (US_id, US_nombre, US_apellidos, US_nif, US_contrasena, US_nivel, US_validado, US_email, US_foto)
				VALUES (NULL, :nombre, :apellidos, :nif, :contrasena, :nivel, :validado, :email, :foto);";
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// Seguidamente se asignan valores a los marcadores de parámetros
			$sentenciaSQL->bindValue(":nombre", $p_NOMBRE, PDO::PARAM_STR);
			$sentenciaSQL->bindValue(":apellidos", $p_APELLIDOS, PDO::PARAM_STR);
			$sentenciaSQL->bindValue(":nif", $nif, PDO::PARAM_INT);
			$sentenciaSQL->bindValue(":contrasena", $contrasena, PDO::PARAM_STR);
			$sentenciaSQL->bindValue(":nivel", $p_NIVEL, PDO::PARAM_STR);
			$sentenciaSQL->bindValue(":validado", FALSE, PDO::PARAM_BOOL);
			if ($p_VALIDADO)
				$sentenciaSQL->bindValue(":validado", TRUE, PDO::PARAM_BOOL);
			$sentenciaSQL->bindValue(":email", $p_EMAIL, PDO::PARAM_STR);
			$sentenciaSQL->bindValue(":foto", $foto, PDO::PARAM_LOB);
			// Y se ejecuta la consulta:
			$resultado = $sentenciaSQL->execute();
		}catch(PDOException $e){
			switch ($e->getCode()):
			 	case 23000:
			 		$resultado = 'Los datos de usuarios ya estan dados de alta en la BD';
			 		break;
			 	default:
			 		echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage() . ' ' . $e->getCode();
			 		break;
			 endswitch;
		}
		return $resultado;
	}

//---------------------------Cambia el estado de un usuarrio para validarse-------------------------------------

	// Esta función se encarga activar al usuario para que pueda validarse en la página
	// @param $p_USUARIO de tipo Usuario será el Usuario que Activaremos
	// @param $p_ESTADO de tipo Boolean será el estado en el que se encuentra para validarse
	// @return devuelve un tipo de dato boolean, TRUE si lo activa correctamente
	// @error devuelve un tipo de dato boolean, FALSE si NO puede activarlo

	public function CambiaEstado($p_USUARIO, $p_ESTADO, $p_NIVEL){
		$resultado = FALSE;
		$SQL = "UPDATE USuarios SET
				US_validado = :estado,
				US_nivel = :nivel
				WHERE US_id = :id";
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// Seguidamente se asignan valores a los marcadores de parámetros
			$sentenciaSQL->bindValue(":id", $p_USUARIO->cualEsId(), PDO::PARAM_INT);
			$sentenciaSQL->bindValue(":nivel", $p_NIVEL, PDO::PARAM_STR);
			$sentenciaSQL->bindValue(":estado", FALSE, PDO::PARAM_BOOL);
			// cambio el estado solo cuando es TRUE por defecto esta desactivado
			if ($p_ESTADO):
				$sentenciaSQL->bindValue(":estado", TRUE, PDO::PARAM_BOOL);
				$resultado = TRUE;
			endif;
			// Y se ejecuta la consulta:
			$sentenciaSQL->execute();
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $resultado;
	}

//----------------------------Para modificar al usuario con diferentes parámetros---------------------------------------

	// Esta función se encarga modificar los datos del usuario
	// [] parámetros opcionales
	// @[param] $nom de tipo string será el nombre del usuario
	// @[param] $ape de tipo string serán los apellidos del usuario
	// @[param] $nif de tipo int será el NIF del usuario
	// @[param] $pass de tipo string será la contraseña del usuario
	// @[param] $email de tipo string será el correo del usuario
	// @[param] $foto de tipo binario será la foto del usuario
	// @return devuelve un tipo de dato boolean, TRUE si lo modifica correctamente
	// @error devuelve un tipo de dato boolean, FALSE si NO puede crearlo

	public function ModificarUsuario($p_NOMBRE, $p_APELLIDOS = '', $p_NIF, $p_PASS = '', $p_NIVEL = '', $p_VALIDADO,$p_EMAIL = '', $p_FOTO = ''){
		// todo: es practicamente igual que el alta pero cambiando la SQL
		// tener en cuenta que hay que pasarle un objeto usuario ??
	}


//---------------------------------------Para borrar al usuario--------------------------------------------------------

	// Esta función se encarga de borrar los datos del usuario
	// que se pasa por parametro de la base de datos
	// @param $p_USUARIO de tipo Usuario será el usuario que borremos
	// @return devuelve un tipo de dato boolean, TRUE si lo borra correctamente
	// @error devuelve un tipo de dato boolean, FALSE si NO puede borrarlo

	public function BorrarUsuario($p_USUARIO){
		$resultado = FALSE;
		$SQL = "DELETE FROM USuarios WHERE US_id = :id";
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// Seguidamente se asignan valores a los marcadores de parámetros
			$sentenciaSQL->bindValue(":id", $p_USUARIO->cualEsId(), PDO::PARAM_INT);
			// Y se ejecuta la consulta:
			$resultado = $sentenciaSQL->execute();
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $resultado;
	}

//----------------------------------------------------------------------------------------------------------------------
}

?>