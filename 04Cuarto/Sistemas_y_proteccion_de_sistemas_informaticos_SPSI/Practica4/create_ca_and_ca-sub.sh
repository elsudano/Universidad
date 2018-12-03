#!/bin/bash -x
echo "Generamos la estructura de directorios para la CA Raíz"
mkdir /home/vagrant/CA
cd /home/vagrant/CA
mkdir certs crl newcerts private subordinada
touch index.txt
touch index.txt.attr
echo 1000 > serial
cd ~
ls -lR CA/
echo
echo "Generamos la clave privada de la Autoridad Certificadora Raíz"
openssl genrsa -aes256 -out /home/vagrant/CA/private/ca-key.pem 4096
echo
echo "Generamos el certificado de la Autoridad Certificadora Raíz"
openssl req -config /home/vagrant/openssl_ca.conf -key /home/vagrant/CA/private/ca-key.pem -new -x509 -days 7300 -sha512 -subj "/C=ES/ST=Granada/L=Granada/O=SPSI S.L./CN=www.spsi-server.com/" -out /home/vagrant/CA/certs/ca-cert.pem
echo
echo "Mostramos los valores de la clave privada de la CA"
openssl rsa -noout -text -in /home/vagrant/CA/private/ca-key.pem
echo
echo "Mostramos los valores del Certificado de la CA"
openssl x509 -noout -text -in /home/vagrant/CA/certs/ca-cert.pem
echo
echo "Generamos la estructura de directorios para la CA Subordinada"
cd /home/vagrant/CA/subordinada
mkdir certs crl csr newcerts private
touch index.txt
touch index.txt.attr
echo 1000 > serial 
echo 1000 > crlnumber
cd ~
ls -lR CA/
echo
echo "Generamos la clave privada de la Autoridad Certificadora Subordinada"
openssl genrsa -aes256 -out /home/vagrant/CA/subordinada/private/ca-sub-key.pem 4096
echo
echo "Generamos la solicitud de Certificado para la Autoridad Certificadora Subordinada"
openssl req -config /home/vagrant/openssl_subordinada.conf -new -sha512 -subj "/C=ES/ST=Granada/L=Granada/O=SPSI S.L./CN=www.spsi-server.com/" -out /home/vagrant/CA/subordinada/csr/ca-sub-csr.pem
echo
echo "Generamos el certificado de la Autoridad Certificadora Subordinada a partir de la solicitud creada antes"
openssl ca -config /home/vagrant/openssl_ca.conf -days 3650 -notext -md sha512 -in /home/vagrant/CA/subordinada/csr/ca-sub-csr.pem -out /home/vagrant/CA/subordinada/certs/ca-sub-cert.pem
echo
echo "Mostramos los valores de la clave privada de la CA Subordinada"
openssl rsa -noout -text -in /home/vagrant/CA/subordinada/private/ca-sub-key.pem
echo
echo "Mostramos los valores del Certificado de la CA Subordinada"
openssl x509 -noout -text -in /home/vagrant/CA/subordinada/certs/ca-sub-cert.pem
echo
echo "Mostramos la validez del certificado de la CA Subordinada"
openssl verify -CAfile /home/vagrant/CA/certs/ca-cert.pem /home/vagrant/CA/subordinada/certs/ca-sub-cert.pem
