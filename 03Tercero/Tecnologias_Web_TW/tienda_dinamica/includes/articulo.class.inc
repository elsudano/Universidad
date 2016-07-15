<?php

/**
* Esta clase es para crear objetos articulos simples
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
*/

class Articulo{
	
	private $id = '';
	private $denominacion = '';
	private $descripcion = '';
	private $precio = '';
	private $unidades = '';
	private $en_portada = '';
	private $foto1 = '';
	private $foto2 = '';
	private $foto3 = '';

	// Constructor de la Clase, tiene varios construstores con varios parametros
	// @param p_DENO El nombre del Articulo
	// @param p_PRECIO Cuanto vale
	// @param p_DESC La descripción del Artículo
	// @param p_CANTI Cuantas unidades tenemos del Artículo
	// @param p_PORTA Sale en la Portada si o no
	// @param p_FOTO La primera foto del Artículo
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
			$error = $error . '	Usage: $var = new Articulo(\'Denominación\')';
			echo $error;
		}
	}

	//ahora declaro una serie de métodos constructores que aceptan diversos números de parámetros
	public function __construct1($p_DENO){
		$this->__construct2($p_DENO, 0);
	}

	public function __construct2($p_DENO, $p_PRECIO){
		$this->__construct3($p_DENO, $p_PRECIO, 'Sin Descripción');
	}

	public function __construct3($p_DENO, $p_PRECIO, $p_DESC){
		$this->__construct4($p_DENO, $p_PRECIO, $p_DESC, 0);
	}
	
	public function __construct4($p_DENO, $p_PRECIO, $p_DESC, $p_CANTI){
		$this->__construct5($p_DENO, $p_PRECIO, $p_DESC, $p_CANTI, 0);
	}
	
	public function __construct5($p_DENO, $p_PRECIO, $p_DESC, $p_CANTI, $p_PORTA){
		$this->__construct6($p_DENO, $p_PRECIO, $p_DESC, $p_CANTI, $p_PORTA, NULL);
	}

	public function __construct6($p_DENO, $p_PRECIO, $p_DESC, $p_CANTI, $p_PORTA, $p_FOTO1){
		$this->__construct7($p_DENO, $p_PRECIO, $p_DESC, $p_CANTI, $p_PORTA, $p_FOTO1, NULL);
	}

	public function __construct7($p_DENO, $p_PRECIO, $p_DESC, $p_CANTI, $p_PORTA, $p_FOTO1, $p_FOTO2){
		$this->__construct8($p_DENO, $p_PRECIO, $p_DESC, $p_CANTI, $p_PORTA, $p_FOTO1, $p_FOTO2, NULL);
	}

	public function __construct8($p_DENO, $p_PRECIO, $p_DESC, $p_CANTI, $p_PORTA, $p_FOTO1, $p_FOTO2, $p_FOTO3){
		$this->__construct9($p_DENO, $p_PRECIO, $p_DESC, $p_CANTI, $p_PORTA, $p_FOTO1, $p_FOTO2, $p_FOTO3, NULL);
	}

	public function __construct9($p_DENO, $p_PRECIO, $p_DESC, $p_CANTI, $p_PORTA, $p_FOTO1, $p_FOTO2, $p_FOTO3, $p_ID){
		$this->id = $p_ID;
		$this->denominacion = $p_DENO;
		$this->descripcion = $p_DESC;
		$this->precio = $p_PRECIO;
		$this->unidades = $p_CANTI;
		$this->en_portada = $p_PORTA;
		$this->foto1 = $p_FOTO1;
		$this->foto2 = $p_FOTO2;
		$this->foto3 = $p_FOTO3;
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

	public function cualEsPrecio(){
		return $this->precio;
	}

	// Muestra el valor de: $unidades
	// @param no tiene
	// @return devuelve dato tipo int con la cantidad de unidades que hay en stock

	public function cualEsUnidades(){
		return $this->unidades;
	}

	// Muestra el valor de: $en_portada
	// @param no tiene
	// @return devuelve un dato de tipo boolean que informa si tiene que salir en la portada o no

	public function estaEnPortada(){
		return $this->en_portada;
	}

	// Muestra el valor de: $foto1
	// @param no tiene
	// @return devuelve un dato tipo string (base64) que tiene los datos en bruto de la foto1 del articulo

	public function cualEsFoto1(){
		$resultado = NULL;
		if ($this->foto1 !== NULL)
			$resultado = "data:image/jpeg;base64,".base64_encode($this->foto1);
		return $resultado;
	}

	// Muestra el valor de: $foto2
	// @param no tiene
	// @return devuelve un dato tipo string (base64) que tiene los datos en bruto de la foto2 del articulo

	public function cualEsFoto2(){
		$resultado = NULL;
		if ($this->foto2 !== NULL)
			$resultado = "data:image/jpeg;base64,".base64_encode($this->foto2);
		return $resultado;
	}

	// Muestra el valor de: $foto3
	// @param no tiene
	// @return devuelve un dato tipo string (base64) que tiene los datos en bruto de la foto3 del articulo

	public function cualEsFoto3(){
		$resultado = NULL;
		if ($this->foto3 !== NULL)
			$resultado = "data:image/jpeg;base64,".base64_encode($this->foto3);
		return $resultado;
	}

	// Añade un valor a: $foto1
	// @param $p_FOTO contiene un binario (base64) con los datos brutos de la foto
	// @return devuelve un dato tipo boolean indicando si la foto1 del articulo se ha grabado bien

	public function ponerFoto1($p_FOTO){
		// todo: tienes que leer la información 
		// tratarla por que viene en base64
	}

	// Añade un valor a: $foto2
	// @param $p_FOTO contiene un binario (base64) con los datos brutos de la foto
	// @return devuelve un dato tipo boolean indicando si la foto2 del articulo se ha grabado bien

	public function ponerFoto2($p_FOTO){
		// todo: tienes que leer la información 
		// tratarla por que viene en base64
	}

	// Añade un valor a: $foto3
	// @param $p_FOTO contiene un binario (base64) con los datos brutos de la foto
	// @return devuelve un dato tipo boolean indicando si la foto3 del articulo se ha grabado bien

	public function ponerFoto3($p_FOTO){
		// todo: tienes que leer la información 
		// tratarla por que viene en base64
	}
}
?>