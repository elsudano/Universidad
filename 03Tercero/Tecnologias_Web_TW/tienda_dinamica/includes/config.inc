<?php
/**
* Este fichero es de configuración general de la página
*/

/*
* establecemos cual es la zona horaria de la página
* lo hacemos a traves de programación y no de la
* configuración del servidor para que sea exportable
* a cualquier servidor
*/
date_default_timezone_set("Europe/Madrid");

// Con esto definimos el entorno de Desarrollo
define('DEBUG',TRUE);

// Con esto definimos el servidor para todos los objetos
define('SERVIDOR_DB','localhost');

// Con esto definimos la base de datos para todos los objetos
define('BASE_DATOS','miTienda');

// Con esto definimos el usuario de conexión a la BD para todos los objetos
define('USUARIO','usedb');

// Con esto definimos la contraseña de la BD para todos los objetos
define('CONTRASENA','contrasena');

// Con esto definimos el tipo de Base de datos al que nos conectamos
// !!!Cuidado¡¡¡ Todavia no esta del todo implementado pero las posibilidades son las siguientes:
// pg_sql: Base de datos PostgreSQL
// my_sql: Base de datos MySQL pero sin orientación a Objetos
// my_sql_pdo: Base de datos MySQL con Orientación a Objetos
// ms_sql: Base de datos MS SQL Server
define('TIPODB','my_sql_pdo');

// Esta es la dirección Raiz de mi pagina se usa para redirigir al usuario en caso de error
define('RAIZ','http://sudano.dtdns.net/tienda/');

// Porcentaje del precio que se resta para calcular el precio antiguo
define('POR_PRE_ANT',0.2); // 20%

// Ponemos el tipo de caracter que queremos que muestre la moneda
define('TIPO_MONEDA','<i class="fa fa-eur"></i>');

// Cuantas son las unidades que se consideran pocas par el cartel de disponibilidad
define('UNIDADES_MINIMAS',10);

// Con esto controlamos cúal es el máximo de noticias relacionados que se pueden ver en la página del artículo
define('MAX_NOTI_RELA',2);

// Con esto controlamos cúal es el máximo de articulos relacionados que se pueden ver en la página del artículo
define('MAX_ARTI_RELA',3);

// Con esto controlamos cúal es el el tamaño maximo para las imagenes expresado en bytes
define('TAMANO_MAXIMO_FOTO',300000);

?>