#!/bin/bash
echo .
echo .  Para restaurar los datos de una base de datos primero, esa base de datos tiene que existir.
echo .  mysql --host=localhost --user=root --password=contraseña --execute=\"CREATE DATABASE IF NOT EXISTS contactos\"
echo .
mysql --host=localhost --user=root --password=p1tr3l1 --execute="CREATE DATABASE IF NOT EXISTS contactos"
echo .
echo . Ahora que ya sabemos seguro que la base de datos existe tenemos que recuperar los datos
echo . mysql --host=localhost --user=root --password=contraseña --database=\"contactos\" --table=\"datos\" \< /home/usuario/copia_seguridad_db1.sql
echo .
mysql --host=localhost --user=root --password=p1tr3l1 --database="contactos" --table="datos" < /home/usuario/copia_seguridad_db1.sql
echo .
echo . Ahora comprobamos que los datos se han recuperado correctamente
echo . mysql --host=localhost --user=root --password=contraseña --database=\"contactos\" --execute="select * from datos" contactos
echo .
mysql --host=localhost --user=root --password=p1tr3l1 --database="contactos" --execute="select * from datos" contactos
echo . Por ultimo damos la posibilidad de borrar el fichero de copia de seguridad.
echo .
read -p " ¿ quiere borrar copia_seguridad_db1.sql ? " -n 1 -r -t 3
echo
if [[ ! $REPLY =~ ^[YySs]$ ]]
then
    exit 1
fi
rm -f /home/usuario/copia_seguridad_db1.sql
read -p " ¿ Quiere borrar la base de datos contactos ? " -n 1 -r -t 3
echo
if [[ ! $REPLY =~ ^[YySs]$ ]]
then
    exit 1
fi
mysql --host=localhost --user=root --password=p1tr3l1 --execute="DROP DATABASE contactos"