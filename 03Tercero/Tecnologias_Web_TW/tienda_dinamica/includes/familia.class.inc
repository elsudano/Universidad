<?php
/**
* Esta clase es para crear objetos familia simples
*
* Comentarios de la tabla: Esta tabla son las familias de articulos
* Columna			Tipo			Nulo		Comentarios
* FA_id (Primaria)	int(20)			No		
* FA_denominacion	varchar(30)		No			Solo puede haber una Familia con ese nombre
* FA_descripcion	varchar(255)	Sí	NULL	Breve descripción de lo que contiene la familia
* FA_color			varchar(8)		No	#000000	Color representado por RGB #223344
* FA_foto			blob			Sí	NULL	Esta es la imagen que sale en la cabecera de las familias
*
*/
class Familia{
	
	private $id = 0;
	private $denominacion = '';
	private $descripcion = '';
	private $color = '';
	private $foto = '';

	// Constructor de la Clase, tiene varios construstores con varios parametros
	// @param p_DENO El nombre de la Familia
	// @param p_DESC La descripción de la familia
	// @param p_COLOR El color que le corresponde en el menu
	// @param p_FOTO La foto de cabecera
	// @param p_ID Si queremos que tenga un ID en particular

	public function __construct(){
		//obtengo un array con los parámetros enviados a la función
		$params = func_get_args();
		//saco el número de parámetros que estoy recibiendo
		$num_params = func_num_args();
		//cada constructor de un número dado de parámtros tendrá un nombre de función
		//atendiendo al siguiente modelo __construct1() __construct2()...
		$funcion_constructor ='__construct'.$num_params;
		//compruebo si hay un constructor con ese número de parámetros
		if (method_exists($this,$funcion_constructor)){
			//si existía esa función, la invoco, reenviando los parámetros que recibí en el constructor original
			call_user_func_array(array($this,$funcion_constructor),$params);
		}else{
			$error = 'No se puede crear la Familia faltan parámetros: <br />';
			$error = $error . '	Usage: $var = new Familia(\'Denominaci&oacute;n\')';
			echo $error;
		}
	}

	//ahora declaro una serie de métodos constructores que aceptan diversos números de parámetros
	public function __construct1($p_DENO){
		$this->__construct2($p_DENO, 'Sin Descripción');
	}

	public function __construct2($p_DENO, $p_DESC){
		$this->__construct3($p_DENO, $p_DESC, '#000000');
	}

	public function __construct3($p_DENO, $p_DESC, $p_COLOR){
		$this->__construct4($p_DENO, $p_DESC, $p_COLOR, 'Sin Imagen');
	}
	
	public function __construct4($p_DENO, $p_DESC, $p_COLOR, $p_FOTO){
		$this->__construct5($p_DENO, $p_DESC, $p_COLOR, $p_FOTO, NULL);
	}
	
	public function __construct5($p_DENO, $p_DESC, $p_COLOR, $p_FOTO, $p_ID){
		$this->id = $p_ID;
		$this->denominacion = $p_DENO;
		$this->descripcion = $p_DESC;
		$this->color = $p_COLOR;
		$this->foto = $p_FOTO;
	}


//----------------------------------------------------------------------------------------------------------------------
	// Muestra el valor de: $id
	// @param no tiene
	// @return devuelve dato tipo int con el ID del árticulo

	public function cualEsId(){
		return $this->id;
	}

	// Muestra el valor de: $denominacion
	// @param no tiene
	// @return devuelve dato tipo string con la denominación del árticulo

	public function cualEsDenominacion(){
		return $this->denominacion;
	}

	// Muestra el valor de: $descripcion
	// @param no tiene
	// @return devuelve dato tipo string con el valor de la descripción del articulo

	public function cualEsDescripcion(){
		return $this->descripcion;
	}

	// Muestra el valor de: $precio
	// @param no tiene
	// @return devuelve un dato tipo int con el precio del articulo

	public function cualEsColor(){
		return $this->color;
	}

	// Muestra el valor de: $foto
	// @param no tiene
	// @return devuelve un dato tipo string que tiene el SRC= de la imágen

	public function cualEsFoto(){
		return "data:image/jpeg;base64,".base64_encode($this->foto);
	}
}
?>