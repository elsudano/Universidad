<?php
/**
* Esta clase es para crear objetos comentarios simples
* Columna			Tipo		Nulo	Comentarios
* CO_id (Primaria)	int(20)		No		
* CO_comentario		longtext	No		Este es el comentario que puso el usuario
* CO_fecha			date		No		Esta es la fecha cuando se grabo el comentario
* CO_hora			time		No		Esta es la hora de cuando se grabo
* 
*/
class Comentario{
	
	private $id = '';
	private $comentario = '';
	private $usuario = '';
	private $articulo = '';
	private $fecha = '';
	private $hora = '';

	// Constructor de la Clase, tiene varios construstores con varios parametros
	// @param p_COME El comentario
	// @param p_FECHA La fecha de la entrada
	// @param p_HORA La hora de la entrada
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
			$error = 'No se puede crear el Articulo faltan parámetros: <br />';
			$error = $error . '	Usage: $var = new Articulo(\'Denominaci&oacute;n\',\'Precio\')';
			echo $error;
		}
	}

	//ahora declaro una serie de métodos constructores que aceptan diversos números de parámetros
	public function __construct3($p_COME, $p_USUA, $p_ARTI){
		$this->__construct4($p_COME, $p_USUA, $p_ARTI, date('Y-m-d'));
	}

	public function __construct4($p_COME, $p_USUA, $p_ARTI, $p_FECHA){
		$this->__construct5($p_COME, $p_USUA, $p_ARTI, $p_FECHA, date('G:i:s'));
	}

	public function __construct5($p_COME, $p_USUA, $p_ARTI, $p_FECHA, $p_HORA){
		$this->__construct6($p_COME, $p_USUA, $p_ARTI, $p_FECHA, $p_HORA, NULL);
	}

	public function __construct6($p_COME, $p_USUA, $p_ARTI, $p_FECHA, $p_HORA, $p_ID){
		$this->id = $p_ID;
		$this->comentario = $p_COME;
		$this->usuario = $p_USUA;
		$this->articulo = $p_ARTI;
		$this->fecha = $p_FECHA;
		$this->hora = $p_HORA;
	}

//----------------------------------------------------------------------------------------------------------------------
	// Muestra el valor de: $id
	// @param no tiene
	// @return devuelve dato tipo int con el ID del árticulo

	public function cualEsId(){
		return $this->id;
	}

	// Muestra el valor de: $comentario
	// @param no tiene
	// @return devuelve dato tipo string con el comentario del artículo

	public function cualEsComentario(){
		return $this->comentario;
	}

	// Muestra el valor de: $usuario
	// @param no tiene
	// @return devuelve dato tipo Int con el ID del Usuario

	public function cualEsUsuario(){
		return $this->usuario;
	}

	// Muestra el valor de: $articulo
	// @param no tiene
	// @return devuelve dato tipo ID con el ID del artículo

	public function cualEsArticulo(){
		return $this->articulo;
	}

	// Muestra el valor de: $fecha
	// @param no tiene
	// @return devuelve dato tipo Fecha con la fecha del comentario

	public function cualEsFecha(){
		return $this->fecha;
	}

	// Muestra el valor de: $hora
	// @param no tiene
	// @return devuelve dato tipo Hora con la hora del comentario

	public function cualEsHora(){
		return $this->hora;
	}
}
?>