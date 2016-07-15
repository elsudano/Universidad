<?php
require_once('base_de_datos.class.inc'); // necesario para la extención y la conexión de la BD
require_once('familia.class.inc'); // necesario para crear los objetos de tipo familia

/**
* Esta clase es para manejar a las Familias
* Columna			Tipo			Nulo		Comentarios
* FA_id (Primaria)	int(20)			No		
* FA_denominacion	varchar(30)		No			Solo puede haber una Familia con ese nombre
* FA_descripcion	varchar(255)	Sí	NULL	Breve descripción de lo que contiene la familia
* FA_color			varchar(8)		No	#000000	Color representado por RGB #223344
* FA_foto			blob			Sí	NULL	Esta es la imagen que sale en la cabecera de las familias
*/

class FamiliaDAO extends BaseDeDatos{

	public function __construct($p_TIPO){
		parent::__construct();
		$this->conndb = $this->Conectar($p_TIPO);
	}

	// Con esto recorremos todos las Familias de la BD
	// y los guardamos en un array que devolvemos
	// @param no tiene parametros de entrada
	// @return devuelve un array de php con TODOS los objetos de tipo Familia
	// @error si no existen articulos devuelve un array vacio

	public function TodosLasFamilias(){
		$array = array(); // Este array lo usamos para el return
		$SQL = "SELECT * FROM FAmilias";
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// Y se ejecuta la consulta:
			$sentenciaSQL->execute();
			// Para recorrer los registros recuperados se emplea el método fetchAll:
			foreach ($sentenciaSQL->fetchAll() as $fila) {
				$array[] = new Familia($fila['FA_denominacion'],$fila['FA_descripcion'],$fila['FA_color'],$fila['FA_foto'],$fila['FA_id']);
			}
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $array;
	}

//---------------------------------Para encontrar una Familia por su ID---------------------------------------------

	// Podemos buscar una familia por el ID de la base de datos
	// @param p_ID de tipo INT es el ID de la Familia
	// @return objeto de tipo Familia que contiene la familia definida por ID
	// @error si no hay usuario devuelve el valor NULL

	public function FamiliaPorId($p_ID){
		$SQL = "SELECT * FROM FAmilias WHERE FA_id = :id";
		$sentenciaSQL = $this->conndb->prepare($SQL);
		$resultado = NULL;
		try{
			// Seguidamente se asignan valores a los marcadores de parámetros
			$sentenciaSQL->bindValue(":id", $p_ID, PDO::PARAM_INT);
			// Y se ejecuta la consulta:
			$sentenciaSQL->execute();
			// como se supone que solo hay un articulo lo guardo y lo creo como objeto
			$fila = $sentenciaSQL->fetch();
			// compruebo que el resultado tiene datos y creo el usuario resultante
			if ($fila) {
				$resultado = new Familia($fila['FA_denominacion'],$fila['FA_descripcion'],$fila['FA_color'],$fila['FA_foto'],$fila['FA_id']);
			}
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $resultado;
	}

//---------------------------------Para dar de Alta una Familia en la BD---------------------------------------------

	// Podemos buscar un articulo por el ID de la base de datos
	// @param p_DENO de tipo String, obligatorio, Nombre del Artículo
	// @param p_DESC de tipo String, opcional, Descripción del Artículo
	// @param p_PRE de tipo Int, opcional, Precio del Artículo
	// @param p_UNI de tipo Int, opcional, Unidades del Artículo
	// @param p_PORT de tipo Boolean, opcional, Ponerlo en portada o no
	// @param p_FOTO de tipo Binario, opcional, Imagen del Artículo
	// @return objeto de tipo Boolean, informa si el alta ha sido correcta
	// @error si hay error devuelve el valor FALSE

	public function AltaFamilia($p_DENO, $p_DESC, $p_COLOR, $p_FOTO = "Sin Foto"){
		$SQL = "INSERT INTO FAmilias (FA_id, FA_denominacion, FA_descripcion, FA_color, FA_foto) VALUES
				(NULL, :denominacion, :descripcion, :color, :foto)";
		$resultado = NULL;
		try{
			$sentenciaSQL = $this->conndb->prepare($SQL);
			// Seguidamente se asignan valores a los marcadores de parámetros
			$sentenciaSQL->bindValue(":denominacion", $p_DENO, PDO::PARAM_STR);
			$sentenciaSQL->bindValue(":descripcion", $p_DESC, PDO::PARAM_STR);
			$sentenciaSQL->bindValue(":color", $p_COLOR, PDO::PARAM_STR);
			$sentenciaSQL->bindValue(":foto", $p_FOTO, PDO::PARAM_LOB);
			// Y se ejecuta el alta del articulo
			$resultado = $sentenciaSQL->execute();
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $resultado;
	}
//---------------------------------------Para borrar Un Articulo--------------------------------------------------------

	// Esta función se encarga de borrar los datos del articulo
	// que se pasa por parametro de la base de datos
	// @param $p_ARTICULO de tipo Articulo será el Articulo que borremos
	// @return devuelve un tipo de dato boolean, TRUE si lo borra correctamente
	// @error devuelve un tipo de dato boolean, FALSE si NO puede borrarlo

	public function BajaDeArticulo($p_ARTICULO){
		$resultado = FALSE;
		$SQL = "DELETE FROM ARticulos WHERE AR_id =" . $p_ARTICULO->cualEsId();
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// Y se ejecuta la consulta:
			$resultado = $sentenciaSQL->execute();
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $resultado;
	}

}

?>