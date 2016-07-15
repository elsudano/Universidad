#!/bin/bash
# Titulo:	nombre
# Fecha:	27/10/2011
# Autor:	Carlos
# Versión:	1.0
# Descripción:	Imprime por pantalla el parametro que le pasemos
# Opciones: Ninguna
# Uso: nombre parametro
if [ $1 != '']; then
  printf "Hola, que tal estas $1 $2 $3\n"
else 
  printf "Por favor introduzca 3 nombres \n"
fi;

echo "Hemos recibido $# parámetros"
while (( $# )) 
do
case $1 in
  -h )
      echo "Aquí va la ayuda"
      ;;
  -e )
      echo "Opción -e "
      ;;
esac

shift
done
