<?php
require_once('base_de_datos.class.inc'); // necesario para la extención y la conexión de la BD
require_once('noticia.class.inc'); // necesario para crear los objetos de tipo noticia

/**
* Esta clase es para manejar a las noticias
* TABLA: NOticias
* Columna			Tipo			Nulo		Comentarios
* NO_id (Primaria)	int(20)			No		
* NO_titulo			char(100)		No			El titular de la Noticia
* NO_descripcion	text			Sí	NULL	Esto es una breve introducción
* NO_link			varchar(255)	Sí	NULL	Este es el enlace de la noticia
* NO_foto			blob			Sí	NULL
* 
* TABLA: AR_con_NO
* Columna				Tipo	Nulo	Comentarios
* AR_id (Primaria)		int(20)	No		Estos son los IDs de los articulos
* NO_re_id (Primaria)	int(20)	No		Estos son los IDs de las Noticias
*/

class NoticiaDAO extends BaseDeDatos{

	public function __construct($p_TIPO){
		parent::__construct();
		$this->conndb = $this->Conectar($p_TIPO);
	}

	// Con esto recorremos todos las Noticias de la BD
	// y los guardamos en un array que devolvemos
	// @param no tiene parametros de entrada
	// @return devuelve un array de php con objetos de tipo Noticia
	// @error si no existen articulos devuelve un array vacio

	public function TodasLasNoticias(){
		$array = array(); // Este array lo usamos para el return
		$SQL = "SELECT * FROM NOticias";
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// Y se ejecuta la consulta:
			$sentenciaSQL->execute();
			// Para recorrer los registros recuperados se emplea el método fetchAll:
			foreach ($sentenciaSQL->fetchAll() as $fila) {
				$array[] = new Noticia($fila['NO_titulo'],$fila['NO_descripcion'],$fila['NO_link'],$fila['NO_foto'],$fila['NO_id']);
			}
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $array;
	}

//-----------------Para mostrar todas las noticias relacionadas con un articulo--------------------------------

	// Sirve para mostrar las noticias relacionados que hay con $this
	// @param parametro de entrada $p_ARTICULO de tipo Articulo
	// @return devuelve un array de php con objetos de tipo Noticas
	// @error si no existen articulos devuelve un array vacio

	public function NoticiasRelacionadas($p_ARTICULO){
		$array = array(); // Este array lo usamos para el return
		// La tabla que relaciona tiene el nombre de
		// campo AR_or_id para decir que es el Artículo Origen y el
		// campo AR_re_id para decir que son los articulos relacionados
		$SQL = "SELECT NO_id, NO_titulo, NO_descripcion, NO_link, NO_foto
				FROM NOticias,AR_con_NO
				WHERE NO_id = NO_re_id AND AR_id = " . $p_ARTICULO->cualEsId();
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// Y se ejecuta la consulta:
			$sentenciaSQL->execute();
			// Para recorrer los registros recuperados se emplea el método fetchAll:
			foreach ($sentenciaSQL->fetchAll() as $fila) {
				$array[] = new Noticia($fila['NO_titulo'],$fila['NO_descripcion'],$fila['NO_link'],$fila['NO_foto'],$fila['NO_id']);
			}
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $array;
	}

//-----------------Para encontrar la Noticia por su ID--------------------------------

	// Podemos buscar una noticia por el ID de la base de datos
	// @param $p_ID es el ID del Noticia
	// @return objeto de tipo Noticia que contiene al noticia definido por ID
	// @error si no hay usuario devuelve el valor NULL

	public function NoticiaPorId($p_ID){
		$SQL = "SELECT * FROM NOticias WHERE NO_id = :id";
		$sentenciaSQL = $this->conndb->prepare($SQL);
		$resultado = NULL;
		try{
			// Seguidamente se asignan valores a los marcadores de parámetros
			$sentenciaSQL->bindValue(":id", $p_ID, PDO::PARAM_INT);
			// Y se ejecuta la consulta:
			$sentenciaSQL->execute();
			// como se supone que solo hay un noticia lo guardo y lo creo como objeto
			$fila = $sentenciaSQL->fetch();
			// compruebo que el resultado tiene datos y creo el usuario resultante
			if ($fila) {
				$resultado = new Noticia($fila['NO_titulo'],$fila['NO_descripcion'],$fila['NO_link'],$fila['NO_foto'],$fila['NO_id']);
			}
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $resultado;
	}

//---------------------------------Para dar añadir una noticia relacionada---------------------------------------------

	// Añadimos una relación etre un noticia y un articulo
	// 2 parametros
	// 		@param p_ARTICULO de tipo Artículo, obligatorio, Articulo al que se va a relacionar la noticia
	// 		@param p_NOTICIA de tipo Noticia, obligatorio, Noticia que se añadira al artículo
	// 4 parametros
	// 		@param p_ARTICULO de tipo Artículo, obligatorio, Articulo al que se va a relacionar la noticia
	// 		@param p_TITULO de tipo String, obligatorio, cadena que indica el titulo de la noticia
	// 		@param p_DESCRIPCION de tipo String, opcional, El desarrollo de la noticia
	// 		@param p_LINK de tipo String, obligatorio, el link de acceso a la noticia extendida
	// 		@param p_FOTO de tipo Base64, opcional, los datos en bruto de la foto de la noticia
	// @return objeto de tipo Boolean, informa si el alta ha sido correcta
	// @error si hay error devuelve el valor FALSE

	public function AnadirNoticiaRelacionada(){
		$resultado = TRUE;
		//obtengo un array con los parámetros enviados a la función
		$params = func_get_args();
		//saco el número de parámetros que estoy recibiendo
		$num_params = func_num_args();
		//cada constructor de un número dado de parámtros tendrá un nombre de función
		//atendiendo al siguiente modelo __construct1() __construct2()...
		$funcion_constructor ='AnadirNoticiaRelacionada'.$num_params;
		//compruebo si hay un constructor con ese número de parámetros
		if (method_exists($this,$funcion_constructor)){
			//si existía esa función, la invoco, reenviando los parámetros que recibí en el constructor original
			call_user_func_array(array($this,$funcion_constructor),$params);
		}else{
			$error = 'El metódo AnadirNoticiaRelacionada() admite o 2 o 5 parametros: <br />';
			$error = $error . '	  AnadirNoticiaRelacionada(ARTICULO, NOTICIA) <br/>';
			$error = $error . '	  AnadirNoticiaRelacionada(ARTICULO, \'titulo\', \'descripcion\', \'link\', \'foto\')';
			echo $error;
			$resultado = FALSE;
		}
		return $resultado;
	}

	// Añadimos una relación etre un noticia y un articulo
	// @param p_ARTICULO de tipo Artículo, obligatorio, Articulo al que se va a relacionar la noticia
	// @param p_NOTICIA de tipo Noticia, obligatorio, Noticia que se añadira al artículo
	// @return objeto de tipo Boolean, informa si el alta ha sido correcta
	// @error si hay error devuelve el valor FALSE

	public function AnadirNoticiaRelacionada2($p_ARTICULO, $p_NOTICIA){
		$SQL = "INSERT INTO AR_con_NO (AR_id, NO_re_id) VALUES (:articulo, :noticia)";
		$resultado = NULL;
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// auque se puede poner directamente usarmos esto para los parametros
			$sentenciaSQL->bindValue(":articulo", $p_ARTICULO->cualEsId(), PDO::PARAM_INT);
			$sentenciaSQL->bindValue(":noticia", $p_NOTICIA->cualEsId(), PDO::PARAM_INT);
			// Y se ejecuta el alta del noticia
			$resultado = $sentenciaSQL->execute();

		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $resultado;
	}

	// Añadimos una relación etre un noticia y un articulo
	// @param p_ARTICULO de tipo Artículo, obligatorio, Articulo al que se va a relacionar la noticia
	// @param p_TITULO de tipo String, obligatorio, cadena que indica el titulo de la noticia
	// @param p_DESCRIPCION de tipo String, opcional, El desarrollo de la noticia
	// @param p_LINK de tipo String, obligatorio, el link de acceso a la noticia extendida
	// @param p_FOTO de tipo Base64, opcional, los datos en bruto de la foto de la noticia
	// @return objeto de tipo Boolean, informa si el alta ha sido correcta
	// @error si hay error devuelve el valor FALSE

	public function AnadirNoticiaRelacionada5($p_ARTICULO, $p_TITULO, $p_DESCRIPCION, $p_LINK, $p_FOTO){
		$SQL1 = "INSERT INTO NOticias (NO_titulo, NO_descripcion, NO_link, NO_foto)
				VALUES (:titulo, :descripcion, :link, :foto)";
		$SQL2 = "SELECT NO_id FROM NOticias WHERE NO_titulo = :titulo";
		$SQL3 = "INSERT INTO AR_con_NO (AR_id, NO_re_id) VALUES (:articulo, :noticia)";
		$resultado = NULL;
		try{
			$this->conndb->beginTransaction();
			// con esto insertamos la noticia
			$sentenciaSQL = $this->conndb->prepare($SQL1);
			// auque se puede poner directamente usarmos esto para los parametros
			$sentenciaSQL->bindValue(":titulo", $p_TITULO, PDO::PARAM_INT);
			$sentenciaSQL->bindValue(":descripcion", $p_DESCRIPCION, PDO::PARAM_INT);
			$sentenciaSQL->bindValue(":link", $p_LINK, PDO::PARAM_INT);
			$sentenciaSQL->bindValue(":foto", $p_FOTO, PDO::PARAM_LOB);
			// Y se ejecuta el alta del noticia
			$sentenciaSQL->execute();
			// Se prepara la sentencia de busqueda para ver cual es el ID del nuevo artículo
			$sentenciaSQL = $this->conndb->prepare($SQL2);
			// Seguidamente se asignan valores a los marcadores de parámetros
			$sentenciaSQL->bindValue(":titulo", $p_TITULO, PDO::PARAM_STR);
			// Se ejecuta la busqueda
			$sentenciaSQL->execute();
			// Guardo el valor en una variable para usarla
			$ID_noticia_nueva = $sentenciaSQL->fetchColumn();
			// Preparo la ultima sentencia
			$sentenciaSQL = $this->conndb->prepare($SQL3);
			// Seguidamente se asignan valores a los marcadores de parámetros
			$sentenciaSQL->bindValue(":articulo", $p_ARTICULO->cualEsId(), PDO::PARAM_INT);
			$sentenciaSQL->bindValue(":noticia", $ID_noticia_nueva, PDO::PARAM_INT);
			// ejecuto la última para la relación
			$sentenciaSQL->execute();
			// realizo el commit
			$resultado = $this->conndb->commit();
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
			$this->conndb->rollback();
		}
		return $resultado;
	}
//---------------------------------------Para Quitar articulos relacionados--------------------------------------------------------

	// Esta función se encarga de quitar las noticias relacionadas con un articulo
	// @param $p_ARTICULO de tipo Articulo será el articulo al cual se le quite la noticia
	// @param $p_NOTICIA de tipo Noticia será la noticia que quitaremos al Artículo
	// @return devuelve un tipo de dato boolean, TRUE si lo borra correctamente
	// @error devuelve un tipo de dato boolean, FALSE si NO puede borrarlo

	public function QuitarNoticiaRelacionada($p_ARTICULO, $p_NOTICIA){
		$resultado = FALSE;
		$SQL = "DELETE FROM AR_con_NO WHERE AR_id = :articulo AND NO_re_id = :noticia_relacionada";
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// Seguidamente se asignan valores a los marcadores de parámetros
			$sentenciaSQL->bindValue(":articulo", $p_ARTICULO->cualEsId(), PDO::PARAM_INT);
			$sentenciaSQL->bindValue(":noticia_relacionada", $p_NOTICIA->cualEsId(), PDO::PARAM_INT);
			// Y se ejecuta la consulta:
			$resultado = $sentenciaSQL->execute();
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $resultado;
	}

//---------------------------------------Para borrar Un Noticia--------------------------------------------------------

	// Esta función se encarga de borrar los datos del noticia
	// que se pasa por parametro de la base de datos
	// @param $p_NOTICIA de tipo Noticia será el Noticia que borremos
	// @return devuelve un tipo de dato boolean, TRUE si lo borra correctamente
	// @error devuelve un tipo de dato boolean, FALSE si NO puede borrarlo

	public function BorrarNoticia($p_NOTICIA){
		$resultado = FALSE;
		$SQL = "DELETE FROM NOticia WHERE NO_id =" . $p_NOTICIA->cualEsId();
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
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