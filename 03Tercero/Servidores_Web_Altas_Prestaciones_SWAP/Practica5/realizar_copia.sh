#!/bin/bash
echo .
echo .  Para poder crear la copia de seguridad tenemos que asegurarnos que no se añaden mas registros mientras la hacemos
echo .  mysql --host=localhost --user=root --password=contraseña --database=\"contactos\" --execute=\"FLUSH TABLES WITH READ LOCK\"
echo .
mysql --host=localhost --user=root --password=p1tr3l1 --database="contactos" --execute="FLUSH TABLES WITH READ LOCK"
echo .
echo . Ahora realizamos la copia de seguridad y la guardamos en un fichero con extención .sql
echo . mysqldump --host=localhost --user=root --password=contraseña contactos datos \> /home/usuario/copia_seguridad_db1.sql
echo .
mysqldump --host=localhost --user=root --password=p1tr3l1 contactos datos > /home/usuario/copia_seguridad_db1.sql
echo .
echo . Una vez que se ha creado el fichero con el volcado de la tabla tenemos que volver su estado normal la base de datos
echo . mysql --host=localhost --user=root --password=contraseña --database=\"contactos\" --execute=\"UNLOCK TABLES\"
echo .
mysql --host=localhost --user=root --password=p1tr3l1 --database="contactos" --execute="UNLOCK TABLES"
echo . Por ultimo damos la posibilidad de copiar la copia al segundo servidor.
echo .
read -p " ¿ quiere copiar el fichero al segundo servidor DB2 ? " -n 1 -r -t 3
echo
if [[ ! $REPLY =~ ^[YySs]$ ]]
then
    exit 1
fi
scp /home/usuario/copia_seguridad_db1.sql root@192.168.50.160:/home/usuario/copia_seguridad_db1.sql
rm -f /home/usuario/copia_seguridad_db1.sql