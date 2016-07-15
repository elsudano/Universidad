Practica 5 <img src="basedatos.jpg" alt="Logotipo" width="50px" height="50px">
==========
*Replicando servidores completos de Base de Datos*

### Objetivos
En esta practica lo que se pretende es conseguir crear una copia casi exacta de un servidor de base de datos, pero también se pretende que el servicio no se detenga mientras sucede esto, es decir, que podamos tener la información disponible en todo momento mientras tenemos la seguridad de que nuestros datos están a salvo en otro servidor idéntico.

### Pasos realizados
> * Paso 1 <br />
> La primera parte de la practica es configurar correctamente el servidor MySQL <br />
> * Paso 2 <br />
> Para que sea mas fácil de gestionar la base de datos he instalado Apache y phpMyAdmin para la comprobación de las sentencias ejecutadas desde la consola. <br />
> * Paso 3 <br />
> Para no tener que preocuparme de la configuración de estos dos servicios instalados en los dos servidores, creo una tarea programada en el servidor "copia" DB2 que se encarga de tener sincronizada la configuración, de ambos servicios. <br />
> * Paso 4 <br />
> Creo los scripts necesarios para comprobar que todos los comandos se pueden realizar sin problemas, entre los dos servidores, de esa manera me aseguro tener los datos preparados para poder ejecutarlos a mano. <br />
> * Paso 5 <br />
> . <br />

### Scripts Adicionales
Los siguientes scripts, sirven de ayuda a la gestión de los servidores replicados, se ha procurado crear los scripts necesarios tanto para la creación desde cero de las bases de datos, las tablas y los datos necesarios para la realización de la practica. <br />
 * El primero de todos se encarga de crear la base de datos, la tabla y los registros necesarios para realizar la practica. <br />
 * El segundo se encarga de realizar una copia de seguridad de la base de datos que hemos creado antes en DB1 y copiarlo al segundo servidor. <br />
 * Por último el tercero es el encargado de restaurar esa copia de seguridad en el segundo servidor (DB2) y borrar el fichero de copia. <br />

**config_manual.sh**
```bash
#!/bin/bash
echo .
echo .  Primero creamos la Base de datos
echo .  mysql --host=localhost --user=root --password=contraseña --execute=\"CREATE DATABASE IF NOT EXISTS contactos\"
echo .
echo .
echo . Después miramos las tablas de la base de datos
echo . mysql --host=localhost --user=root --password=contraseña --execute=\"show tables\" --database=\"contactos\"
echo .
echo .
echo . Como vemos no tienen ninguna tabla, así que vamos a crear una que se llame datos
echo . mysql --host=localhost --user=root --password=contraseña --execute=\"create table if not exists datos \(nombre varchar\(100\), tlf int\)\" --database=\"contactos\"
echo .
echo .
echo . Volvemos a comprobar las tablas que tenemos creadas
echo . mysql --host=localhost --user=root --password=contraseña --execute=\"show tables\" --database=\"contactos\"
echo .
echo .
echo . Esta vez si obtenemos resultados positivos
echo .
echo . Ahora vamos a insertar algunos registros para poder realizar las pruebas
echo . mysql --host=localhost --user=root --password=contraseña --execute=\"insert into datos\(nombre,tlf\) values \(\"pepe\",95834987\)\" --database=\"contactos\"
echo . repetiremos esta operación tantas veces como registros queramos crear
echo .
echo . Y después de haber metido los registros miramos si están correctamente insertados
echo . mysql --host=localhost --user=root --password=contraseña --execute=\"select \* from datos\" --database=\"contactos\"
echo . como vemos a continuación tenemos 4 registros insertados
echo .
echo .
echo . Por ultimo vamos a usar el siguiente comando para ver la estructura de la tabla
echo . mysql --host=localhost --user=root --password=contraseña --execute=\"describe datos\" --database=\"contactos\"
echo .
echo . Por ultimo damos la posibilidad de borrar la base de datos
echo .
read -p " ¿ quiere borrar la BD contactos ? " -n 1 -r -t 3
echo
```

**realizar_copia.sh**
```bash
#!/bin/bash
echo .
echo .  Para poder crear la copia de seguridad tenemos que asegurarnos que no se añaden mas registros mientras la hacemos
echo .  mysql --host=localhost --user=root --password=contraseña --database=\"contactos\" --execute=\"FLUSH TABLES WITH READ LOCK\"
echo .
echo .
echo . Ahora realizamos la copia de seguridad y la guardamos en un fichero con extensión .sql
echo . mysqldump --host=localhost --user=root --password=contraseña contactos datos \> /home/usuario/copia_seguridad_db1.sql
echo .
echo .
echo . Una vez que se ha creado el fichero con el volcado de la tabla tenemos que volver su estado normal la base de datos
echo . mysql --host=localhost --user=root --password=contraseña --database=\"contactos\" --execute=\"UNLOCK TABLES\"
echo .
echo . Por ultimo damos la posibilidad de copiar la copia al segundo servidor.
echo .
read -p " ¿ quiere copiar el fichero al segundo servidor DB2 ? " -n 1 -r -t 3
echo
```
**restaturar_copia.sh**
```bash
#!/bin/bash
echo .
echo .  Para restaurar los datos de una base de datos primero, esa base de datos tiene que existir.
echo .  mysql --host=localhost --user=root --password=contraseña --execute=\"CREATE DATABASE IF NOT EXISTS contactos\"
echo .
echo .
echo . Ahora que ya sabemos seguro que la base de datos existe tenemos que recuperar los datos
echo . mysql --host=localhost --user=root --password=contraseña --database=\"contactos\" --table=\"datos\" \< /home/usuario/copia_seguridad_db1.sql
echo .
echo .
echo . Ahora comprobamos que los datos se han recuperado correctamente
echo . mysql --host=localhost --user=root --password=contraseña --database=\"contactos\" --execute="select * from datos" contactos
echo .
echo . Por ultimo damos la posibilidad de borrar el fichero de copia de seguridad.
echo .
read -p " ¿ quiere borrar copia_seguridad_db1.sql ? " -n 1 -r -t 3
echo
```
### Configuración Manual
Hasta aquí hemos conseguido que la replicación de datos sea consistente pero bastante primitiva, puesto que necesitamos de tareas programadas para que se realice el proceso o bien a un técnico que se encargue de monitorizar la copia de datos entre los dos servidores. <br />
Lo que vamos a intentar ahora es configurar los dos servidores para que actúen como maestro/esclavo, (DB1/DB2), para que la replica de datos sea casi automática. <br />

### Configuración Maestro/Maestro
Ya hemos visto que se pueden hacer copia de los datos entre servidores, de forma rudimentaria, ahora vamos a realizarlo de manera un poco mas profesional y sin tener que programar tareas para que el replicado de datos se lleve a cabo, sino que se guardaran los datos en los dos servidores simultáneamente.<br />
La metodología para configurar de esta manera los servidores es bastante sencilla, lo que se hace es configurar un servidor primero como maestro DB1 y el segundo se configura como esclavo DB2, a continuación se realiza la misma operación pero en el otros sentido, así de esa manera, si cualquiera de los dos servidores se estropeara, bastaría con sustituirlo por una maquina nueva y ponerle la misma IP que el servidor que se ha estropeado, de esa manera, una vez configurado correctamente el nuevo servidor se auto replicarían los datos de un servidor a otro.<br />

**Fichero de configuración en DB1**
```bash
[usuario@DB1 /]# cat /etc/my.cnf
# bind-address          = 127.0.0.1
server-id               = 1 # identificador del servidor
report_host             = 192.168.50.159 # ip del servidor cuando actúa como esclavo
log_bin                 = /var/log/mariadb/mariadb-bin 
log_bin_index           = /var/log/mariadb/mariadb-bin.index
relay_log               = /var/log/mariadb/relay-bin
relay_log_index         = /var/log/mariadb/relay-bin.index
replicate-do-db         = contactos # solo se replicará esta base de datos
...
[usuario@DB1 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="create user 'replicauser'@'%' identified by 'contraseña'"
[usuario@DB1 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="grant replication slave on *.* to 'replicauser'@'%'"
```
Aúnque no hace falta tambien utilizaremos los comandos para bloquear la lectura y escritura de las tablas mientras hacemos la siguiente operación para evitar que podamos perder algún dato, mientras terminamos de configurarlo todo.
```bash
[usuario@DB1 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="flush privileges"
[usuario@DB1 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="flush tables"
[usuario@DB1 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="flush tables with read lock"
[usuario@DB1 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="show master status"
+--------------------+----------+--------------+------------------+
| File               | Position | Binlog_Do_DB | Binlog_Ignore_DB |
+--------------------+----------+--------------+------------------+
| mariadb-bin.000001 |      245 |              |                  |
+--------------------+----------+--------------+------------------+ 
```
Bien hasta aquí la primera parte de la configuración del primer servidor maestro, ahora tenemos que continuar con la primera parte del segundo servidor maestro DB2 y para ello cambiaremos la configuración tal y como se muestra a continuación.<br />

**Fichero de configuración en DB2**
<pre><code>
[usuario@DB2 /]# cat /etc/my.cnf
# bind-address          = 127.0.0.1
server-id               = <b>2</b> # identificador del servidor
report_host             = <b>192.168.50.160</b> # ip del servidor cuando actúa como esclavo
log_bin                 = /var/log/mariadb/mariadb-bin 
log_bin_index           = /var/log/mariadb/mariadb-bin.index
relay_log               = /var/log/mariadb/relay-bin
relay_log_index         = /var/log/mariadb/relay-bin.index
replicate-do-db         = contactos # solo se replicará esta base de datos
...
[usuario@DB2 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="create user 'replicauser'@'%' identified by 'contraseña'"
[usuario@DB2 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="grant replication slave on *.* to 'replicauser'@'%'"
[usuario@DB2 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="flush privileges"
[usuario@DB2 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="flush tables"
[usuario@DB2 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="flush tables with read lock"
[usuario@DB2 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="show master status"
+--------------------+----------+--------------+------------------+
| File               | Position | Binlog_Do_DB | Binlog_Ignore_DB |
+--------------------+----------+--------------+------------------+
| <b>mariadb-bin.000002</b> |      245 |              |                  |
+--------------------+----------+--------------+------------------+
</code></pre>
Si nos fijamos en este punto nos damos cuenta que hemos cambiado dos parametros en la configuración y el servidor a cambiado el nombre del fichero de logs binarios, ahora bien como en este servidor ya si podemos utilizar el otro como servidor esclavo, seguimos con la configuración.
<pre><code>
[usuario@DB2 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="stop slave"
[usuario@DB2 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="change master to master_host='<b>192.168.50.159</b>', MASTER_USER='replicauser', MASTER_PASSWORD='contraseña', MASTER_LOG_FILE='mariadb-bin.000001', MASTER_LOG_POS=245"
[usuario@DB2 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="start slave"
[usuario@DB2 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="unlock tables"
</code></pre>
Y Ahora como el servidor maestro DB2 ya está listo seguiremos con la configuración del primero, para configurarlo como esclavo también
<pre><code>
[usuario@DB1 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="stop slave"
[usuario@DB1 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="change master to master_host='<b>192.168.50.160</b>', MASTER_USER='replicauser', MASTER_PASSWORD='contraseña', MASTER_LOG_FILE='<b>mariadb-bin.000002</b>', MASTER_LOG_POS=245"
[usuario@DB1 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="start slave"
[usuario@DB1 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="unlock tables"
</code></pre>
Y para terminar de comprobar que esta todo correctamente configurado podemos utilizar el siguiente comando en ambos servidores.
```bash
mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="show slave status\G"
```
El resultado tendrá que ser algo parecido a esto en ambos servidores

```bash
               Slave_IO_State: Waiting for master to send event
                  Master_Host: 192.168.50.159
                  Master_User: replicauser
                  Master_Port: 3306
                Connect_Retry: 60
              Master_Log_File: mariadb-bin.000004
          Read_Master_Log_Pos: 245
               Relay_Log_File: relay-bin.000007
                Relay_Log_Pos: 531
        Relay_Master_Log_File: mariadb-bin.000004
             Slave_IO_Running: Yes
            Slave_SQL_Running: Yes
              Replicate_Do_DB: contactos
          Replicate_Ignore_DB: 
           Replicate_Do_Table: 
       Replicate_Ignore_Table: 
      Replicate_Wild_Do_Table: 
  Replicate_Wild_Ignore_Table: 
                   Last_Errno: 0
                   Last_Error: 
                 Skip_Counter: 0
          Exec_Master_Log_Pos: 245
              Relay_Log_Space: 1105
              Until_Condition: None
               Until_Log_File: 
                Until_Log_Pos: 0
           Master_SSL_Allowed: No
           Master_SSL_CA_File: 
           Master_SSL_CA_Path: 
              Master_SSL_Cert: 
            Master_SSL_Cipher: 
               Master_SSL_Key: 
        Seconds_Behind_Master: 0
Master_SSL_Verify_Server_Cert: No
                Last_IO_Errno: 0
                Last_IO_Error: 
               Last_SQL_Errno: 0
               Last_SQL_Error: 
  Replicate_Ignore_Server_Ids: 
             Master_Server_Id: 1
```
Con esto damos por finalizada la configuración en ambos servidores, y si queremos comprobar que la replicación funciona correctamente lo único que tenemos que hacer es crear un nuevo registro en la tabla datos de nuestra base de datos contactos y ver como, ejecutando la sentencia en un solo servidor podemos ver los datos en ambos servidores.

```bash
[usuario@DB2 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="insert into datos(nombre,tlf) values ("Prueba",958147258)"
[usuario@DB2 /]# mysql --host=localhost --user=root --password=contraseña --database="contactos" --execute="select * from datos"
+---------+-----------+
| nombre  | tlf       |
+---------+-----------+
| pepe    | 958123456 |
| juan    | 958654321 |
| carlos  | 958987654 |
| pedro   | 958456789 |
| Prueba1 | 958147258 |
+---------+-----------+
```

### Bibliografía
> Página web de MariaDB: https://mariadb.com/kb/en/mariadb/replication-cluster-multi-master/ <br />
> Comandos mas usados: https://mariadb.com/kb/en/mariadb/replication-commands/ <br />
> Parametros de configuración de replica: https://mariadb.com/kb/en/mariadb/replication-and-binary-log-server-system-variables/ <br />

### Conclusiones
Bueno la primera parte de la practica, se supone que es mas fácil por que es algo que ya hemos visto en las anteriores, aun así hay que tener cuidado a la hora de utilizar los comandos de copia entre servidores para clonar las configuraciones de ambos por que, si no vamos con cuidado podemos copiar la configuración mala, en el servidor con la configuración buena.<br />
Aparte de eso es bastante sencillo poder crear una copia del servidor completo por si al maestro le sucediera algo. Después la segunda parte de la practica se complica un poco mas, pero esta claro que es la configuración que tenemos que realizar para cuando tenemos servidores en producción, puesto que de esta forma no bloqueamos la inserción de datos mientras estamos realizando una copia de seguridad en el otro servidor, pero claro que esto también tiene la pega, de que si nos equivocamos, y borramos registros sin querer hacerlo, también se borran en el otros servidor, por lo tanto hay que tener claro que aunque tengamos, replicados los datos para posibles contingencias frente a fallos, también hay que realizar copias de seguridad programadas.