#!/bin/bash -x

# Variables del Script:
# Es necesario que las rutas de los diferentes directorios sean rutas absolutas

# Variables de la CA:
CONFCA = /home/vagrant/openssl_ca.conf
PATHCA = /home/vagrant/CA
CERTSCA = $PATHCA/certs
PRIVCA = $PATHCA/private
NEWCERTCA = $PATHCA/newcerts
CRLCA = $PATHCA/crl

# Variables de la CA subordinada:
CONFCASUB = /home/vagrant/openssl_subordinada.conf
PATHCASUB = /home/vagrant/CA-SUB
CERTSCASUB = $PATHCASUB/certs
PRIVCASUB = $PATHCASUB/private
NEWCERTSCASUB = $PATHCASUB/newcerts
CRLCASUB = $PATHCASUB/crl
CSRCASUB = $PATHCASUB/csr

# Variables del Cliente:
PATHCLI = /home/vagrant/cliente
CERTSCLI = $PATHCLI/certs
PRIVCLI = $PATHCLI/private
CSRCLI = $PATHCLI/csr

echo "Generamos la estructura de directorios para la CA Raíz"
mkdir -p $CERTSCA $PRIVCA $NEWCERTCA $CRLCA
touch $PATHCA/index.txt
touch $PATHCA/index.txt.attr
echo 1000 > $PATHCA/serial
cd ~
ls -R
echo
echo "Generamos la clave privada de la Autoridad Certificadora Raíz"
openssl genrsa -aes256 -out $PRIVCA/ca-key.pem 4096
echo
echo "Generamos el certificado de la Autoridad Certificadora Raíz"
openssl req -config $CONFCA -key $PRIVCA/ca-key.pem -new -x509 -days 7300 -sha512 -subj "/C=ES/ST=Granada/L=Granada/O=SPSI S.L./CN=www.spsi-server.com/" -out $CERTSCA/ca-cert.pem
echo
echo "Mostramos los valores de la clave privada de la CA"
openssl rsa -noout -text -in $PRIVCA/ca-key.pem
echo
echo "Mostramos los valores del Certificado de la CA"
openssl x509 -noout -text -in $CERTSCA/ca-cert.pem
read -t10 -n1 -r -p 'Pulse una tecla o espere 10 segundos...'
echo
echo "Generamos la estructura de directorios para la CA Subordinada"
mkdir -p $CERTSCASUB $PRIVCASUB $NEWCERTSCASUB $CRLCASUB $CSRCASUB
touch $PATHCASUB/index.txt
touch $PATHCASUB/index.txt.attr
echo 1000 > $PATHCASUB/serial 
echo 1000 > $PATHCASUB/crlnumber
cd ~
ls -R
echo
echo "Generamos la clave privada de la Autoridad Certificadora Subordinada"
openssl genrsa -aes256 -out $PRIVCASUB/ca-sub-key.pem 4096
echo
echo "Generamos la solicitud de Certificado para la Autoridad Certificadora Subordinada"
openssl req -config $CONFCASUB -new -sha512 -subj "/C=ES/ST=Granada/L=Granada/O=SPSI S.L./CN=www.spsi-server.com/" -keyout $PRIVCASUB/ca-sub-key.pem -out $CSRCASUB/ca-sub-csr.pem
echo
echo "Generamos el certificado de la Autoridad Certificadora Subordinada a partir de la solicitud creada antes"
openssl ca -config $CONFCA -days 3650 -notext -md sha512 -in $CSRCASUB/ca-sub-csr.pem -out $CERTSCASUB/ca-sub-cert.pem
echo
echo "Mostramos los valores de la clave privada de la CA Subordinada"
openssl rsa -noout -text -in $PRIVCASUB/ca-sub-key.pem
echo
echo "Mostramos los valores del Certificado de la CA Subordinada"
openssl x509 -noout -text -in $CERTSCASUB/ca-sub-cert.pem
echo
echo "Mostramos la validez del certificado de la CA Subordinada"
openssl verify -CAfile $CERTSCA/ca-cert.pem $CERTSCASUB/ca-sub-cert.pem
echo
echo "Creamos la estructura de directorios simulando a un cliente de la CA subordinada"
mkdir -p $CERTSCLI $PRIVCLI $CSRCLI
echo
echo "Generamos una solicitud de certificado que sea de un cliente diferente a nuestra CA"
openssl req -newkey 4096 -keyout $PRIVCLI/almeria-sub-key.pem -out $CSRCLI/almeria-sub-csr.pem -subj "/C=ES/ST=Almeria/L=Almeria/O=MiServer/CN=www.server.com/"
echo
echo "Mandamos la solicitud a nuestra CA subordinada para que la firme"
cp /home/vagrant/cliente/csr/almeria-sub-csr.pem $CSRCASUB/almeria-sub-csr.pem
echo
echo "Con la solicitud generada en el punto anterior generamos el ceritificado"
openssl ca -config $CONFCASUB -days 3650 -notext -md sha512 -in $CSRCASUB/almeria-sub-csr.pem -out $CERTSCASUB/almeria-sub-cert.pem
echo
echo "Mandamos al cliente el certificado generado, para su uso"
cp $CERTSCASUB/almeria-sub-cert.pem $CERTSCLI/almeria-sub-cert.pem
echo
echo "Mostramos todos los ficheros generados en los diferentes directorios"
cd ~
ls -R

