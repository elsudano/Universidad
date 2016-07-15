<?php
/**
* Esta clase es para crear objetos usuarios simples
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
class Usuario{
	
	private $id = '';
	private $nombre = '';
	private $apellidos = '';
	private $nif = '';
	private $contrasena = '';
	private $nivel = '';
	private $email = '';
	private $foto = '';
	private $validado = 0;
	private $error = '';

	// Constructor de la Clase, tiene varios construstores con varios parametros
	// @param p_NOMBRE El nombre del Usuario
	// @param p_NIF El DNI sin la Letra
	// @param p_APELLIDOS Los Apellidos
	// @param p_CONTRASENA La contraseña en // combrobar si esta en SHA1
	// @param p_NIVEL Nivel de acceso
	// @param p_EMAIL Email del Usuario
	// @param p_FOTO Una foto identificativa
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
			$this->error = 'No se puede crear el usuario faltan parámetros: <br />';
			$this->error = $this->error . '	Usage: $var = new Usuario(\'Nombre\',\'77778888\')';
		}
	}

	//ahora declaro una serie de métodos constructores que aceptan diversos números de parámetros
	public function __construct2($p_NOMBRE, $p_NIF){
		$this->__construct3($p_NOMBRE, $p_NIF, 'Sin Apellidos');
	}
	
	public function __construct3($p_NOMBRE, $p_NIF, $p_APELLIDOS){
		$this->__construct4($p_NOMBRE, $p_NIF, $p_APELLIDOS, 'Sin Contraseña');
	}
	
	public function __construct4($p_NOMBRE, $p_NIF, $p_APELLIDOS, $p_CONTRASENA){
		$this->__construct5($p_NOMBRE, $p_NIF, $p_APELLIDOS, $p_CONTRASENA,'invitado');
	}

	public function __construct5($p_NOMBRE, $p_NIF, $p_APELLIDOS, $p_CONTRASENA, $p_NIVEL){
		$this->__construct6($p_NOMBRE, $p_NIF, $p_APELLIDOS, $p_CONTRASENA, $p_NIVEL, TRUE);
	}

	public function __construct6($p_NOMBRE, $p_NIF, $p_APELLIDOS, $p_CONTRASENA, $p_NIVEL, $p_OK){
		$this->__construct7($p_NOMBRE, $p_NIF, $p_APELLIDOS, $p_CONTRASENA, $p_NIVEL, $p_OK, 'correo@dominio.com');
	}

	public function __construct7($p_NOMBRE, $p_NIF, $p_APELLIDOS, $p_CONTRASENA, $p_NIVEL, $p_OK, $p_EMAIL){
		$this->__construct8($p_NOMBRE, $p_NIF, $p_APELLIDOS, $p_CONTRASENA, $p_NIVEL, $p_OK, $p_EMAIL, 'Sin Foto');
	}

	public function __construct8($p_NOMBRE, $p_NIF, $p_APELLIDOS, $p_CONTRASENA, $p_NIVEL, $p_OK, $p_EMAIL, $p_FOTO){
		$this->__construct9($p_NOMBRE, $p_NIF, $p_APELLIDOS, $p_CONTRASENA, $p_NIVEL, $p_OK, $p_EMAIL, $p_FOTO, NULL);
	}

	public function __construct9($p_NOMBRE, $p_NIF, $p_APELLIDOS, $p_CONTRASENA, $p_NIVEL, $p_OK, $p_EMAIL, $p_FOTO, $p_ID){
		$this->id = $p_ID;
		$this->nombre = $p_NOMBRE;
		$this->apellidos = $p_APELLIDOS;
		$this->nif = $p_NIF;
		$this->contrasena = $p_CONTRASENA;
		$this->nivel = $p_NIVEL;
		$this->validado = $p_OK;
		$this->email = $p_EMAIL;
		$this->foto = $p_FOTO;
	}

//----------------------------------------------------------------------------------------------------------------------

	// Muestra el valor de: $id
	// @param no tiene
	// @return devuelve dato tipo string con el valor del nombre de usuario

	public function cualEsId(){
		return $this->id;
	}

	// Muestra el valor de: $nombre
	// @param no tiene
	// @return devuelve dato tipo string con el valor del nombre de usuario

	public function cualEsNombre(){
		return $this->nombre;
	}

	// Muestra el valor de: $apellidos
	// @param no tiene
	// @return devuelve dato tipo string con el valor de los apellidos de usuario

	public function cualEsApellidos(){
		return $this->apellidos;
	}

	// Muestra el valor de: $nif
	// @param no tiene
	// @return devuelve un dato tipo int que contiene el nif del usuario

	public function cualEsNif(){
		return $this->nif;
	}

	// Muestra el valor de: $contrasena
	// @param no tiene
	// @return devuelve un dato tipo string que contiene la contraseña de usuario

	public function cualEsContrasena(){
		return $this->contrasena;
	}

	// Muestra el valor de: $nivel
	// @param no tiene
	// @return devuelve dato tipo string con el valor del nivel de acceso del usuario

	public function cualEsNivel(){
		return $this->nivel;
	}

	// Muestra el valor de: $validado
	// @param no tiene
	// @return devuelve dato tipo boolean para saber si se ha verificado el correo

	public function cualEsValidado(){
		return $this->validado;
	}

	// Muestra el valor de: $email
	// @param no tiene
	// @return devuelve un dato de tipo string que tiene el correo del usuario

	public function cualEsEmail(){
		return $this->email;
	}

	// Muestra el valor de: $foto
	// @param no tiene
	// @return devuelve un dato tipo binario (base64) que tiene los datos en bruto de la foto de usuario

	public function cualEsFoto(){
		$resultado = NULL;
		if ($this->foto !== NULL)
			$resultado = "data:image/jpeg;base64,".base64_encode($this->foto);
		return $resultado;
	}

	// Muestra el valor de: $error
	// @param no tiene
	// @return devuelve un dato tipo string que muestra una descripción del último error
	
	public function ultimoError(){
		return $this->error;
	}
}
?>