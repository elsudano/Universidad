<?php
/**
* Esta clase es para crear objetos noticias simples
* Columna			Tipo			Nulo		Comentarios
* NO_id (Primaria)	int(20)			No		
* NO_titulo			char(100)		No			El titular de la Noticia
* NO_descripcion	text			Sí	NULL	Esto es una breve introducción
* NO_link			varchar(255)	Sí	NULL	Este es el enlace de la noticia
* NO_foto			blob			Sí	NULL	
*/
class Noticia{
	
	private $id = '';
	private $titulo = '';
	private $descripcion = '';
	private $link = '';
	private $foto = '';

	// Constructor de la Clase, tiene varios construstores con varios parametros
	// @param p_TIT El Titulo de la Noticia
	// @param p_DESC La descripción de la Noticia
	// @param p_LINK Enlace de la Noticia
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
			$error = 'No se puede crear la Noticia faltan parámetros: <br />';
			$error = $error . '	Usage: $var = new Noticia(\'Titulo\')';
			echo $error;
		}
	}

	//ahora declaro una serie de métodos constructores que aceptan diversos números de parámetros
	public function __construct1($p_TIT){
		$this->__construct2($p_TIT, 'Sin Descripción');
	}

	public function __construct2($p_TIT, $p_DESC){
		$this->__construct3($p_TIT, $p_DESC, 'http://www.google.se');
	}

	public function __construct3($p_TIT, $p_DESC, $p_LINK){
		$this->__construct4($p_TIT, $p_DESC, $p_LINK, 'Sin Foto');
	}
	
	public function __construct4($p_TIT, $p_DESC, $p_LINK, $p_FOTO){
		$this->__construct5($p_TIT, $p_DESC, $p_LINK, $p_FOTO, NULL);
	}
	
	public function __construct5($p_TIT, $p_DESC, $p_LINK, $p_FOTO, $p_ID){
		$this->id = $p_ID;
		$this->titulo = $p_TIT;
		$this->descripcion = $p_DESC;
		$this->link = $p_LINK;
		$this->foto = $p_FOTO;
	}

//----------------------------------------------------------------------------------------------------------------------
	// Muestra el valor de: $id
	// @param no tiene
	// @return devuelve dato tipo int con el ID del árticulo

	public function cualEsId(){
		return $this->id;
	}

	// Muestra el valor de: $titulo
	// @param no tiene
	// @return devuelve dato tipo string con titulo del árticulo

	public function cualEsTitulo(){
		return $this->titulo;
	}

	// Muestra el valor de: $descripcion
	// @param no tiene
	// @return devuelve dato tipo string con el valor de la descripción del articulo

	public function cualEsDescripcion(){
		return $this->descripcion;
	}

	// Muestra el valor de: $link
	// @param no tiene
	// @return devuelve un dato tipo string con el link del articulo

	public function cualEsLink(){
		return $this->link;
	}

	// Muestra el valor de: $foto
	// @param no tiene
	// @return devuelve un dato tipo string (base64) que tiene los datos en bruto de la foto1 del articulo

	public function cualEsFoto(){
		return "data:image/jpeg;base64,".base64_encode($this->foto);
	}

	// Añade un valor a: $foto
	// @param $p_FOTO contiene un binario (base64) con los datos brutos de la foto
	// @return devuelve un dato tipo boolean indicando si la foto del articulo se ha grabado bien

	public function ponerFoto($p_FOTO){
		// todo: tienes que leer la información 
		// tratarla por que viene en base64
	}
}
?>