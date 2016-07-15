Practica 2 <img src="rsync.jpg" alt="Logotipo" width="50px" height="50px">
==========

*Configurando RSync y SSH sin contraseñas*

### Objetivos
En Esta practica se pretende configurar dos servidores web para que tengan la misma configuración en su servidor apache, y también en las paginas que sirven a sus clientes

### Comandos usados
* Primero nos validamos como superusuario <br />
<pre><code>[usuario@WEB1 /]$ su<br />
Contraseña: <br />
[root@WEB1 /]# <br /></code></pre>

* Para generar las claves necesarias RSA <br />
<pre><code>[root@WEB1 /]# ssh-keygen -b 2048 -t rsa <br />
Generating public/private rsa key pair. <br />
Enter file in which to save the key (/root/.ssh/id_rsa):<br />
Enter passphrase (empty for no passphrase): <br />
Enter same passphrase again: <br />
Your identification has been saved in /root/.ssh/id_rsa. <br />
Your public key has been saved in /root/.ssh/id_rsa.pub. <br />
The key fingerprint is: <br />
c4:40:28:f5:92:04:f4:06:27:d5:d3:e2:f4:48:6d:f5 root@WEB1 <br /></code></pre>

* Copiamos la clave publica al segundo servidor <br />
<pre><code>[root@WEB1 /]# ssh-copy-id root@<WEB2> *ponemos su numbre DNS* <br />
root@WEB2's password  <br />
Number of key(s) added: 1 <br /></code></pre>

* Probamos que rsync puede copiar los ficheros sin que tengamos que introducir ninguna contraseña <br />
<pre><code>[root@WEB1 /]# rsync -avz -e ssh root@<WEB2>:/var/www/html/ /var/www/html/ <br />
receiving incremental file list <br />
sent 262 bytes  received 55856 bytes  112236.00 bytes/sec <br />
total size is 33977792  speedup is 605.47 <br /></code></pre>

* Por ultimo creamos la tarea correspondiente para la copia remota, hay que tener en cuenta que el proceso de copia será desde el servidor remoto hacia el servidor que ejecuta la llamada <br />
<pre><code>SHELL=/bin/bash <br />
PATH=/sbin:/bin:/usr/sbin:/usr/bin <br />
MAILTO=root <br />
# For details see man 4 crontabs <br />
# Example of job definition: <br />
# .---------------- minute (0 - 59) <br />
# |  .------------- hour (0 - 23) <br />
# |  |  .---------- day of month (1 - 31) <br />
# |  |  |  .------- month (1 - 12) OR jan,feb,mar,apr ... <br />
# |  |  |  |  .---- day of week (0 - 6) (Sunday=0 or 7) OR sun,mon,tue,wed,thu,fri,sat <br />
# |  |  |  |  | <br />
# *  *  *  *  * user-name  command to be executed <br />
 00 18 08 03 00 root       rsync -avz -e ssh root@192.168.50.148:/var/www/html/ /var/www/html/ </code></pre>

### Conclusiones
Esta claro que los comando que se han explicado en el párrafo anterior también son aplicables al segundo servidor, por supuesto hay que tener claro como queremos que se repliquen los datos, puesto que si tenemos uno como maestro, y otro como esclavo, tenemos que tener en cuenta esa topología, ahora bien si queremos que ambos servidores sean maestros, tendremos que reproducir todos los comandos incluso los del cron. 
<!--
![Imagen de Prueba](/resources/prueba.jpg = 100x50)
-->