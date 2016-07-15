<?php
require_once('base_de_datos.class.inc'); // necesario para la extención y la conexión de la BD
require_once('articulo.class.inc'); // necesario para crear los objetos de tipo articulo

/**
* Esta clase es para manejar a los articulos
*
* TABLA:
* Columna			Tipo		Nulo	Predeterminado	Comentarios
* AR_id (Primaria)	int(20)		No		
* AR_denominacion	char(100)	No						Este es el Nombre del Artículo
* AR_descripcion	text		No		NULL 			Esta es una breve descripción del Artículo
* AR_precio			int(10)		No		0				El precio del Artículo
* AR_unidades		int(10)		No		0				Estas son las unidades de artículos
* AR_en_portada		tinyint(1)	No		0				Para saber si sale en la portada
* AR_foto1			blob		Sí		NULL	
* AR_foto2			blob		Sí		NULL	
* AR_foto3			blob		Sí		NULL
*
* TABLA: AR_con_AR
* Columna				Tipo	Nulo
* AR_or_id (Primaria)	int(20)	No
* AR_re_id (Primaria)	int(20)	No
*
* TABLA: AR_con_FA
* Columna				Tipo	Nulo
* AR_id (Primaria)		int(20)	No
* FA_re_id (Primaria)	int(20)	No
*/

class ArticuloDAO extends BaseDeDatos{

	public function __construct($p_TIPO){
		parent::__construct();
		$this->conndb = $this->Conectar($p_TIPO);
	}

	// Con esto recorremos todos los articulos de la BD
	// y los guardamos en un array que devolvemos
	// @param no tiene parametros de entrada
	// @return devuelve un array de php con objetos de tipo Articulo
	// @error si no existen articulos devuelve un array vacio

	public function TodosLosArticulos(){
		$array = array(); // Este array lo usamos para el return
		$SQL = "SELECT * FROM ARticulos";
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// Y se ejecuta la consulta:
			$sentenciaSQL->execute();
			// Para recorrer los registros recuperados se emplea el método fetchAll:
			foreach ($sentenciaSQL->fetchAll() as $fila) {
				$array[] = new Articulo($fila['AR_denominacion'],$fila['AR_precio'],$fila['AR_descripcion'],$fila['AR_unidades'],$fila['AR_en_portada'],$fila['AR_foto1'],$fila['AR_foto2'],$fila['AR_foto3'],$fila['AR_id']);
			}
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $array;
	}

//-----------------Para buscar todos los articulos que tiene que aparecer en la portada--------------------------------

	// Sirve para buscar los articulos de portada
	// @param no tiene parametros de entrada
	// @return devuelve un array de php con objetos de tipo Articulo
	// @error si no existen articulos devuelve un array vacio

	public function ArticulosPortada(){
		$array = array(); // Este array lo usamos para el return
		$SQL = "SELECT * FROM ARticulos WHERE AR_en_portada = TRUE";
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// Y se ejecuta la consulta:
			$sentenciaSQL->execute();
			// Para recorrer los registros recuperados se emplea el método fetchAll:
			foreach ($sentenciaSQL->fetchAll() as $fila) {
				$array[] = new Articulo($fila['AR_denominacion'],$fila['AR_precio'],$fila['AR_descripcion'],$fila['AR_unidades'],$fila['AR_en_portada'],$fila['AR_foto1'],$fila['AR_foto2'],$fila['AR_foto3'],$fila['AR_id']);
			}
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $array;
	}

//----------------------------------Añadir un Artículo a la portada-------------------------------------

	// Esta función se encarga de añadir el articulo seleccionado a la portada de la página
	// @param $p_ARTICULO de tipo Articulo será el Articulo que pondremos en la portada
	// @return devuelve un tipo de dato boolean, TRUE si lo borra correctamente
	// @error devuelve un tipo de dato boolean, FALSE si NO puede borrarlo

	public function AnadirArticuloPortada($p_ARTICULO){
		$resultado = FALSE;
		$SQL = "UPDATE ARticulos SET AR_en_portada = TRUE WHERE AR_id =" . $p_ARTICULO->cualEsId();
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// Y se ejecuta la consulta:
			$resultado = $sentenciaSQL->execute();
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $resultado;
	}

//-----------------Para mostrar todos los articulos relacionados con otro articulo--------------------------------

	// Sirve para mostrar los articulos relacionados que hay con $this
	// @param parametro de entrada $p_ARTICULO de tipo Articulo
	// @return devuelve un array de php con objetos de tipo Articulo
	// @error si no existen articulos devuelve un array vacio

	public function ArticulosRelacionados($p_ARTICULO){
		$array = array(); // Este array lo usamos para el return
		// La tabla que relaciona tiene el nombre de AR_con_AR y los campos son:
		// campo AR_or_id para decir que es el Artículo Origen y el
		// campo AR_re_id para decir que son los articulos relacionados
		$SQL = "SELECT AR_id, AR_denominacion, AR_descripcion, AR_precio, AR_unidades, AR_en_portada, AR_foto1, AR_foto2, AR_foto3
				FROM ARticulos,AR_con_AR
				WHERE AR_id = AR_re_id AND AR_or_id = " . $p_ARTICULO->cualEsId();
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// Y se ejecuta la consulta:
			$sentenciaSQL->execute();
			// Para recorrer los registros recuperados se emplea el método fetchAll:
			foreach ($sentenciaSQL->fetchAll() as $fila) {
				$array[] = new Articulo($fila['AR_denominacion'],$fila['AR_precio'],$fila['AR_descripcion'],$fila['AR_unidades'],$fila['AR_en_portada'],$fila['AR_foto1'],$fila['AR_foto2'],$fila['AR_foto3'],$fila['AR_id']);
			}
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $array;
	}

//---------------------------------Para encontrar un Artículo por su ID---------------------------------------------

	// Podemos buscar un articulo por el ID de la base de datos
	// @param p_ID de tipo INT es el ID del Articulo
	// @return objeto de tipo Articulo que contiene al articulo definido por ID
	// @error si no hay usuario devuelve el valor NULL

	public function ArticuloPorId($p_ID){
		$SQL = "SELECT * FROM ARticulos WHERE AR_id = :id";
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
				$resultado = new Articulo($fila['AR_denominacion'],$fila['AR_precio'],$fila['AR_descripcion'],$fila['AR_unidades'],$fila['AR_en_portada'],$fila['AR_foto1'],$fila['AR_foto2'],$fila['AR_foto3'],$fila['AR_id']);
			}
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $resultado;
	}

//---------------------------------Para encontrar un Artículo por su Familia---------------------------------------------

	// Podemos buscar un articulo por el ID de la base de datos
	// @param p_FAMILIA de tipo Familia es la Familia del Articulo
	// @return objeto de tipo array de Articulos que contiene los articulos que pertenecen a Familia
	// @error si no hay usuario devuelve el valor NULL

	public function ArticulosPorFamilia($p_FAMILIA){
		$array = array(); // Este array lo usamos para el return
		// La tabla que relaciona tiene el nombre de AR_con_FA y los campos son:
		// campo AR_id para el Artículo a buscar y mostrar y el
		// campo FA_re_id para la Familia por la que queremos filtrar
		$SQL = "SELECT AR_id, AR_denominacion, AR_descripcion, AR_precio, AR_unidades, AR_en_portada, AR_foto1, AR_foto2, AR_foto3
				FROM ARticulos,FA_con_AR
				WHERE AR_id = AR_re_id AND FA_id = " . $p_FAMILIA->cualEsId();
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// Y se ejecuta la consulta:
			$sentenciaSQL->execute();
			// Para recorrer los registros recuperados se emplea el método fetchAll:
			foreach ($sentenciaSQL->fetchAll() as $fila) {
				$array[] = new Articulo($fila['AR_denominacion'],$fila['AR_precio'],$fila['AR_descripcion'],$fila['AR_unidades'],$fila['AR_en_portada'],$fila['AR_foto1'],$fila['AR_foto2'],$fila['AR_foto3'],$fila['AR_id']);
			}
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $array;
	}

//---------------------------------Para dar de Alta un Articulo en la BD---------------------------------------------

	// Podemos buscar un articulo por el ID de la base de datos
	// @param p_DENO de tipo String, obligatorio, Nombre del Artículo
	// @param p_DESC de tipo String, opcional, Descripción del Artículo
	// @param p_PRE de tipo Int, opcional, Precio del Artículo
	// @param p_UNI de tipo Int, opcional, Unidades del Artículo
	// @param p_PORT de tipo Boolean, opcional, Ponerlo en portada o no
	// @param p_FOTO de tipo Binario, opcional, Imagen del Artículo
	// @return objeto de tipo Boolean, informa si el alta ha sido correcta
	// @error si hay error devuelve el valor FALSE

	public function AltaArticulo($p_DENO, $p_DESC, $p_PRE, $p_UNI, $p_PORT, $p_FAM, $p_FOTOS){
		// Con esto genero las variables necesarias para cada una de las FOTOS que
		// son pasadas por parametros en el ARRAY, si no hay fotos no proceso el array
		if ($p_FOTOS['foto']['size'][0] > 0):
			for ($i=1; $i <= count($p_FOTOS['foto']['name']); $i++):
				// compruebo que el tipo de fichero es correcto
				$boolean = preg_match('#^image\/(jpeg|png|gif|tiff|)$#',$p_FOTOS['foto']['type'][$i-1]);
				if ( $boolean AND $p_FOTOS['foto']['error'][$i-1] === 0):
					// aqui se puede poner un gestor de errores en las siguientes versiones
					${'FOTO'.$i} = file_get_contents($p_FOTOS['foto']['tmp_name'][$i-1]);
				endif;
			endfor;
		endif;

		// Ahora creo la sentencia SQL segun la cantidad de FOTOS que hay
		// Según el número de fotos uso el que sea
		// primera parte de la sentencia
		$SQL1 = "INSERT INTO ARticulos (AR_id, AR_denominacion, AR_descripcion, AR_precio, AR_unidades, AR_en_portada";
		if ($p_FOTOS['foto']['size'][0] > 0):
			// meto los campos necesarios según las fotos
			switch (count($p_FOTOS['foto']['name'])) {
				case 1:
					$SQL1 = $SQL1 . ", AR_foto1";
					break;
				case 2:
					$SQL1 = $SQL1 . ", AR_foto1, AR_foto2";
					break;
				case 3:
					$SQL1 = $SQL1 . ", AR_foto1, AR_foto2, AR_foto3";
					break;
			}
		endif;
		// segunda parte de la sentencia
		$SQL1 = $SQL1 . ") VALUES (NULL, :denominacion, :descripcion, :precio, :unidades, :portada";
		if ($p_FOTOS['foto']['size'][0] > 0):
			// meto los valores de los campos según las fotos
			switch (count($p_FOTOS['foto']['name'])) {
				case 1:
					$SQL1 = $SQL1 . ", :foto1";
					break;
				case 2:
					$SQL1 = $SQL1 . ", :foto1, :foto2";
					break;
				case 3:
					$SQL1 = $SQL1 . ", :foto1, :foto2, :foto3";
					break;
			}
		endif;
		// cierro la sentencia, esta de esa manera por que así
		// puedo meter articulos sin ninguna foto
		$SQL1 = $SQL1 . ")";

		$SQL2 = "SELECT AR_id FROM ARticulos WHERE AR_denominacion = :denominacion";
		$SQL3 = "INSERT INTO FA_con_AR (FA_id, AR_re_id) VALUES (:familia, :articulo)";
		$resultado = NULL;
		try{
			$this->conndb->beginTransaction();
			$sentenciaSQL = $this->conndb->prepare($SQL1);
			// Seguidamente se asignan valores a los marcadores de parámetros
			$sentenciaSQL->bindValue(":denominacion", $p_DENO, PDO::PARAM_STR);
			$sentenciaSQL->bindValue(":descripcion", $p_DESC, PDO::PARAM_STR);
			$sentenciaSQL->bindValue(":precio", $p_PRE, PDO::PARAM_INT);
			$sentenciaSQL->bindValue(":unidades", $p_UNI, PDO::PARAM_INT);
			$sentenciaSQL->bindValue(":portada", FALSE, PDO::PARAM_BOOL);
			// cuando paso el valor por parametros no se por que no acepta
			// el valor booleano asi que lo pongo de esa manera por defecto es false
			if ($p_PORT)
				$sentenciaSQL->bindValue(":portada", TRUE, PDO::PARAM_BOOL);
			// con esto genero la SQL según el numero de fotos
			if ($p_FOTOS['foto']['size'][0] > 0):
				switch (count($p_FOTOS['foto']['name'])) {
				case 1:
					$sentenciaSQL->bindValue(":foto1", $FOTO1, PDO::PARAM_LOB);
					break;
				case 2:
					$sentenciaSQL->bindValue(":foto1", $FOTO1, PDO::PARAM_LOB);
					$sentenciaSQL->bindValue(":foto2", $FOTO2, PDO::PARAM_LOB);
					break;
				case 3:
					$sentenciaSQL->bindValue(":foto1", $FOTO1, PDO::PARAM_LOB);
					$sentenciaSQL->bindValue(":foto2", $FOTO2, PDO::PARAM_LOB);
					$sentenciaSQL->bindValue(":foto3", $FOTO3, PDO::PARAM_LOB);
					break;
				}
			endif;
			// Y se ejecuta el alta del articulo
			$sentenciaSQL->execute();
			// Se prepara la sentencia de busqueda para ver cual es el ID del nuevo artículo
			$sentenciaSQL = $this->conndb->prepare($SQL2);
			// Seguidamente se asignan valores a los marcadores de parámetros
			$sentenciaSQL->bindValue(":denominacion", $p_DENO, PDO::PARAM_STR);
			// Se ejecuta la busqueda
			$sentenciaSQL->execute();
			// Guardo el valor en una variable para usarla
			$ID_articulo_nuevo = $sentenciaSQL->fetchColumn();
			// Preparo la ultima sentencia
			$sentenciaSQL = $this->conndb->prepare($SQL3);
			// Seguidamente se asignan valores a los marcadores de parámetros
			$sentenciaSQL->bindValue(":articulo", $ID_articulo_nuevo, PDO::PARAM_INT);
			$sentenciaSQL->bindValue(":familia", $p_FAM, PDO::PARAM_INT);
			// ejecuto la ultima inserción
			$sentenciaSQL->execute();
			// realizo el commit
			$resultado = $this->conndb->commit();
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
			$this->conndb->rollback();
		}
		return $resultado;
	}

//---------------------------------Para Actualizar un Articulo en la BD---------------------------------------------

	// Podemos buscar un articulo por el ID de la base de datos
	// @param p_ARTICULO de tipo Articulo, obligatorio, El articulo que vamos a actualizar
	// @param p_DENO de tipo String, opcional, Nombre del Artículo
	// @param p_PRE de tipo Int, opcional, Precio del Artículo
	// @param p_UNI de tipo Int, opcional, Unidades del Artículo
	// @param p_PORT de tipo Boolean, opcional, Ponerlo en portada o no
	// @param p_FOTOS de tipo Array, opcional, Contiene los datos de las imagenes del usuario
	// @return objeto de tipo Boolean, informa si el alta ha sido correcta
	// @error si hay error devuelve el valor FALSE

	public function ActualizarArticulo($p_ARTICULO, $p_DENO, $p_DESC, $p_PRE, $p_UNI, $p_PORT = FALSE, $p_FAM, $p_FOTOS){
		// Con esto genero las variables necesarias para cada una de las FOTOS que
		// son pasadas por parametros en el ARRAY
		for ($i=1; $i <= count($p_FOTOS['foto']['name']); $i++):
			// compruebo que el tipo de fichero es correcto
			$boolean = preg_match('#^image\/(jpeg|png|gif|tiff|)$#',$p_FOTOS['foto']['type'][$i-1]);
			if ( $boolean AND $p_FOTOS['foto']['error'][$i-1] === 0):
				// aqui se puede poner un gestor de errores en las siguientes versiones
				${'FOTO'.$i} = file_get_contents($p_FOTOS['foto']['tmp_name'][$i-1]);
			endif;
		endfor;

		// Ahora creo la sentencia SQL segun la cantidad de FOTOS que hay
		// Según el número de fotos uso el que sea
		$SQL1 = "UPDATE ARticulos SET";
		switch (count($p_FOTOS['foto']['name'])) {
			case 1:
				$SQL1 = $SQL1 . " AR_foto1 = :foto1,";
				break;
			case 2:
				$SQL1 = $SQL1 . " AR_foto1 = :foto1, AR_foto2 = :foto2,";
				break;
			case 3:
				$SQL1 = $SQL1 . " AR_foto1 = :foto1, AR_foto2 = :foto2, AR_foto3 = :foto3,";
				break;
		}
		$SQL1 = $SQL1 . " AR_denominacion = :denominacion,
				AR_descripcion = :descripcion,
				AR_precio = :precio,
				AR_unidades = :unidades,
				AR_en_portada = :portada WHERE AR_id =" . $p_ARTICULO->cualEsId();

		$resultado = NULL;
		try{
			$this->conndb->beginTransaction();
			// preparo la sentencia de update para el articulo
			$sentenciaSQL = $this->conndb->prepare($SQL1);
			// Seguidamente se asignan valores a los marcadores de parámetros
			$sentenciaSQL->bindValue(":denominacion", $p_DENO, PDO::PARAM_STR);
			$sentenciaSQL->bindValue(":descripcion", $p_DESC, PDO::PARAM_STR);
			$sentenciaSQL->bindValue(":precio", $p_PRE, PDO::PARAM_INT);
			$sentenciaSQL->bindValue(":unidades", $p_UNI, PDO::PARAM_INT);
			$sentenciaSQL->bindValue(":portada", FALSE, PDO::PARAM_BOOL);
			// cuando paso el valor por parametros no se por que no acepta
			// el valor booleano asi que lo pongo de esa manera por defecto es false
			if ($p_PORT)
				$sentenciaSQL->bindValue(":portada", TRUE, PDO::PARAM_BOOL);
			// con esto genero la SQL según el numero de fotos
			switch (count($p_FOTOS['foto']['name'])) {
			case 1:
				$sentenciaSQL->bindValue(":foto1", $FOTO1, PDO::PARAM_LOB);
				break;
			case 2:
				$sentenciaSQL->bindValue(":foto1", $FOTO1, PDO::PARAM_LOB);
				$sentenciaSQL->bindValue(":foto2", $FOTO2, PDO::PARAM_LOB);
				break;
			case 3:
				$sentenciaSQL->bindValue(":foto1", $FOTO1, PDO::PARAM_LOB);
				$sentenciaSQL->bindValue(":foto2", $FOTO2, PDO::PARAM_LOB);
				$sentenciaSQL->bindValue(":foto3", $FOTO3, PDO::PARAM_LOB);
				break;
			}
			// ejecuto la actualización
			$sentenciaSQL->execute();
			// realizo el commit
			$resultado = $this->conndb->commit();
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
			$this->conndb->rollback();
		}
		return $resultado;
	}

//---------------------------------Para dar añadir un articulo relacionado---------------------------------------------

	// Añadimos una relación etre un articulo y otro
	// @param p_ARTICULO_ORI de tipo Artículo, obligatorio, Articulo al que se va a relacionar el otro
	// @param p_ARTICULO_DEST de tipo Artículo, obligatorio, Artículo que se relaciona con el anterior
	// @return objeto de tipo Boolean, informa si el alta ha sido correcta
	// @error si hay error devuelve el valor FALSE

	public function AnadirArticuloRelacionado($p_ARTICULO_ORI, $p_ARTICULO_DEST){
		$SQL = "INSERT INTO AR_con_AR (AR_or_id, AR_re_id) VALUES (:id1, :id2)";
		$resultado = NULL;
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// auque se puede poner directamente usarmos esto para los parametros
			$sentenciaSQL->bindValue(":id1", $p_ARTICULO_ORI->cualEsId(), PDO::PARAM_INT);
			$sentenciaSQL->bindValue(":id2", $p_ARTICULO_DEST->cualEsId(), PDO::PARAM_INT);
			// Y se ejecuta el alta del articulo
			$resultado = $sentenciaSQL->execute();

		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $resultado;
	}

//----------------------------------Quitar un foto del articulo-------------------------------------

	// Esta función se encarga de quitar la foto del articulo
	// @param $p_ARTICULO de tipo Articulo será el Articulo que pondremos en la portada
	// @param $p_FOTO de tipo INT será el numero de la foto que queremos quitar (1,2,3)
	// @return devuelve un tipo de dato boolean, TRUE si lo borra correctamente
	// @error devuelve un tipo de dato boolean, FALSE si NO puede borrarlo

	public function BorrarFotoArticulo($p_ARTICULO, $p_FOTO){
		$resultado = FALSE;
		$SQL = "UPDATE ARticulos SET";
		switch ($p_FOTO) {
			case 1:
				$SQL = $SQL . " AR_foto1 = :foto";
				break;
			case 2:
				$SQL = $SQL . " AR_foto2 = :foto";
				break;
			case 3:
				$SQL = $SQL . " AR_foto3 = :foto";
				break;
		}
		$SQL = $SQL . " WHERE AR_id = :id";
		try{
			// preparo la sentencia SQL
			$sentenciaSQL = $this->conndb->prepare($SQL);
			// auque se puede poner directamente usarmos esto para los parametros
			$sentenciaSQL->bindValue(":id", $p_ARTICULO->cualEsId(), PDO::PARAM_INT);
			$sentenciaSQL->bindValue(":foto", NULL, PDO::PARAM_INT);
			// Y se ejecuta la consulta:
			$resultado = $sentenciaSQL->execute();
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $resultado;
	}

//----------------------------------Quitar un Artículo a la portada-------------------------------------

	// Esta función se encarga de añadir el articulo seleccionado a la portada de la página
	// @param $p_ARTICULO de tipo Articulo será el Articulo que pondremos en la portada
	// @return devuelve un tipo de dato boolean, TRUE si lo borra correctamente
	// @error devuelve un tipo de dato boolean, FALSE si NO puede borrarlo

	public function QuitarArticuloPortada($p_ARTICULO){
		$resultado = FALSE;
		$SQL = "UPDATE ARticulos SET AR_en_portada = FALSE WHERE AR_id = :id";
		try{
			// preparo la sentencia SQL
			$sentenciaSQL = $this->conndb->prepare($SQL);
			// auque se puede poner directamente usarmos esto para los parametros
			$sentenciaSQL->bindValue(":id", $p_ARTICULO->cualEsId(), PDO::PARAM_INT);
			// Y se ejecuta la consulta:
			$resultado = $sentenciaSQL->execute();
		}catch(PDOException $e){
			echo 'Fichero: ' . basename(__FILE__) . ', Función: ' . __FUNCTION__ . ': ' . $e->getMessage();
		}
		return $resultado;
	}

//---------------------------------------Para Quitar articulos relacionados--------------------------------------------------------

	// Esta función se encarga de quitarlos articulos relacionados a otro articulo
	// @param $p_ARTICULO de tipo Articulo será el Artículo del cuál quitaremos el relacionado
	// @param $p_ARTICULO_RELA de tipo Artículo será el Articulo que quitaremos
	// @return devuelve un tipo de dato boolean, TRUE si lo borra correctamente
	// @error devuelve un tipo de dato boolean, FALSE si NO puede borrarlo

	public function QuitarArticuloRelacionado($p_ARTICULO, $p_ARTICULO_RELA){
		$resultado = FALSE;
		$SQL = "DELETE FROM AR_con_AR WHERE AR_or_id = :articulo AND AR_re_id = :articulo_relacionado";
		$sentenciaSQL = $this->conndb->prepare($SQL);
		try{
			// Seguidamente se asignan valores a los marcadores de parámetros
			$sentenciaSQL->bindValue(":articulo", $p_ARTICULO->cualEsId(), PDO::PARAM_INT);
			$sentenciaSQL->bindValue(":articulo_relacionado", $p_ARTICULO_RELA->cualEsId(), PDO::PARAM_INT);
			// Y se ejecuta la consulta:
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

	public function BorrarArticulo($p_ARTICULO){
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

//----------------------------------------------------------------------------------------------------------------------
}

?>