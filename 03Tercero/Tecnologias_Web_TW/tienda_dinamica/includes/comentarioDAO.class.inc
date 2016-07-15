<?php
require_once('base_de_datos.class.inc'); // necesario para la extención y la conexión de la BD
require_once('comentario.class.inc'); // necesario para crear los objetos de tipo comentario
/**
* Esta clase es para manejar los Comentarios de los Artículos
* Columna			Tipo		Nulo	Comentarios
* CO_id (Primaria)	int(20)		No		
* CO_comentario		longtext	No		Este es el comentario que puso el usuario
* CO_fecha			date		No		Esta es la fecha cuando se grabo el comentario
* CO_hora			time		No		Esta es la hora de cuando se grabo
*/
class ComentarioDAO extends BaseDeDatos{

	public function __construct($p_TIPO){
		parent::__construct();
		$this->conndb = $this->Conectar($p_TIPO);
	}

	// Con esto recorremos todos los Comentarios de la BD
	// y los guardamos en un array que devolvemos
	// @param no tiene parametros de entrada
	// @return devuelve un array de php con TODOS los objetos de tipo Comentario
	// @error si no existen articulos devuelve un array vacio

	public function TodosLosComentarios(){
		$array = array(); // Este array lo usamos para el return
		$SQL = "SELECT * FROM COmentarios";
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// Y se ejecuta la consulta:
			$sentenciaSQL->execute();
			// Para recorrer los registros recuperados se emplea el método fetchAll:
			foreach ($sentenciaSQL->fetchAll() as $fila) {
				$array[] = new Comentario($fila['CO_comentario'],$fila['CO_id_usuario'],$fila['CO_id_articulo'],$fila['CO_fecha'],$fila['CO_hora'], $fila['CO_id']);
			}
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $array;
	}

//-----------------Para encontrar a un Comentario por su ID--------------------------------

	// Podemos buscar una fila por el ID de la base de datos
	// @param $p_ID es el ID del Comentario
	// @return objeto de tipo Comentario que contiene el comentario definido por ID
	// @error si no hay usuario devuelve el valor NULL

	public function ComentarioPorId($p_ID){
		$SQL = "SELECT * FROM COmentarios WHERE CO_id = :id";
		$sentenciaSQL = $this->conndb->prepare($SQL);
		$resultado = NULL;
		try{
			// Seguidamente se asignan valores a los marcadores de parámetros
			$sentenciaSQL->bindValue(":id", $p_ID, PDO::PARAM_INT);
			// Y se ejecuta la consulta:
			$sentenciaSQL->execute();
			// como se supone que solo hay un fila lo guardo y lo creo como objeto
			$fila = $sentenciaSQL->fetch();
			// compruebo que el resultado tiene datos y creo el usuario resultante
			if ($fila) {
				$resultado = new Comentario($fila['CO_comentario'],$fila['CO_id_usuario'],$fila['CO_id_articulo'],$fila['CO_fecha'],$fila['CO_hora'], $fila['CO_id']);
			}
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $resultado;
	}

//-----------------Para buscar todos los Comentarios que pertenecen a un Artículo--------------------------------

	// Sirve para mostrar los comentarios que los usuarios hacen de un Artículo
	// @param parametro de entrada $p_ARTICULO de tipo Articulo
	// @return devuelve un array de php con objetos de tipo Comentario
	// @error si no existen articulos devuelve un array vacio

	public function ComentariosPorArticulo($p_ARTICULO){
		$array = array(); // Este array lo usamos para el return
		$SQL = "SELECT CO_id, CO_comentario, CO_id_usuario, CO_id_articulo, CO_fecha, CO_hora
				FROM COmentarios,ARticulos
				WHERE CO_id_articulo = AR_id AND AR_id = " . $p_ARTICULO->cualEsId();
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// Y se ejecuta la consulta:
			$sentenciaSQL->execute();
			// Para recorrer los registros recuperados se emplea el método fetchAll:
			foreach ($sentenciaSQL->fetchAll() as $fila) {
				$array[] = new Comentario($fila['CO_comentario'],$fila['CO_id_usuario'],$fila['CO_id_articulo'],$fila['CO_fecha'],$fila['CO_hora'], $fila['CO_id']);
			}
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $array;
	}

//-----------------Para buscar todos los Comentarios que pertenecen a un Usuario--------------------------------

	// Sirve para mostrar los comentarios que un usuario en concreto ha realizado
	// @param parametro de entrada $p_USUARIO de tipo Usuario
	// @return devuelve un array de php con objetos de tipo Comentario
	// @error si no existen articulos devuelve un array vacio

	public function ComentariosPorUsuario($p_USUARIO){
		$array = array(); // Este array lo usamos para el return
		$SQL = "SELECT CO_id, CO_comentario, CO_id_usuario, CO_id_articulo, CO_fecha, CO_hora
				FROM COmentarios,USuarios
				WHERE CO_id_usuario = US_id AND US_id = " . $p_USUARIO->cualEsId();
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// Y se ejecuta la consulta:
			$sentenciaSQL->execute();
			// Para recorrer los registros recuperados se emplea el método fetchAll:
			foreach ($sentenciaSQL->fetchAll() as $fila) {
				$array[] = new Comentario($fila['CO_comentario'],$fila['CO_id_usuario'],$fila['CO_id_articulo'],$fila['CO_fecha'],$fila['CO_hora'], $fila['CO_id']);
			}
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $array;
	}

//---------------------------------Para dar añadir un comentario a un articulo---------------------------------------------

	// Añadimos una relación etre un noticia y un articulo
	// @param p_USUARIO de tipo Usuario, obligatorio, El usuario que realiza el comentario
	// @param p_ARTICULO de tipo Artículo, obligatorio, Articulo al que se va a relacionar la noticia
	// @param p_COMENTARIO de tipo String, obligatorio, El comentario del artículo
	// @return objeto de tipo Boolean, informa si el alta ha sido correcta
	// @error si hay error devuelve el valor FALSE

	public function AnadirComentario($p_USUARIO, $p_ARTICULO, $p_COMENTARIO){
		$SQL = "INSERT INTO COmentarios (CO_comentario, CO_id_articulo, CO_id_usuario, CO_fecha, CO_hora)
				VALUES (:comentario, :articulo, :usuario, :fecha, :hora)";
		$resultado = NULL;
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// auque se puede poner directamente usarmos esto para los parametros
			$sentenciaSQL->bindValue(":comentario", $p_COMENTARIO, PDO::PARAM_STR);
			$sentenciaSQL->bindValue(":articulo", $p_ARTICULO->cualEsId(), PDO::PARAM_INT);
			$sentenciaSQL->bindValue(":usuario", $p_USUARIO->cualEsId(), PDO::PARAM_INT);
			$sentenciaSQL->bindValue(":fecha", date('Y-m-d'), PDO::PARAM_STR);
			$sentenciaSQL->bindValue(":hora", date('G:i:s'), PDO::PARAM_STR);
			// Y se ejecuta el alta del noticia
			$resultado = $sentenciaSQL->execute();

		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $resultado;
	}

//------------------------------------Para Quitar Comentarios de un Articulo ---------------------------------------------------

	// Esta función se encarga de quitar las noticias relacionadas con un articulo
	// @param $p_COMENTARIO de tipo Comentario será el Comentario que quitaremos
	// @return devuelve un tipo de dato boolean, TRUE si lo borra correctamente
	// @error devuelve un tipo de dato boolean, FALSE si NO puede borrarlo

	public function BorrarComentario($p_COMENTARIO){
		$resultado = FALSE;
		$SQL = "DELETE FROM COmentarios WHERE CO_id = :id";
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			$sentenciaSQL->bindValue(":id", $p_COMENTARIO->cualEsId(), PDO::PARAM_INT);
			// Y se ejecuta la consulta:
			$resultado = $sentenciaSQL->execute();
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}

		return $resultado;
	}
}

?>