#!/bin/bash
# Titulo:	Ejecutar todos los binarios
# Fecha:	01/06/14
# Autor:	elsudano
# Versión:	1.0
# Descripción:	Sirve para poder comprobar las velocidades de ejecución de todos los binarios construidos
# Opciones: Ninguna
# Uso: ./ejecutar_todos.sh sin parámetros

BINARIOS_FIGURA1=($(ls $(pwd)/bin/figura1*))
BINARIOS_PMM=($(ls $(pwd)/bin/pmm*))
BINARIOS_DAXPY=($(ls $(pwd)/bin/daxpy*))

#echo "Orden de ejecución de binarios"
#for bin in $(ls $(pwd)/bin/)
#do
#  echo $bin
#done
echo "------------Tiempos de DAXPY---------------"
for bin2 in ${BINARIOS_DAXPY[@]}
do
  if [[ $1 -eq "1" ]]; then
    $bin2 9999 11.27 1
  else
    echo $(basename $bin2); $bin2 9999 11.27
  fi
done
echo "--------Tiempos de Figura1--------"
for bin0 in ${BINARIOS_FIGURA1[@]}
do
  if [[ $1 -eq "1" ]]; then
    $bin0 1
  else
    echo $(basename $bin0); $bin0
  fi
done
echo "--Tiempos de Producto de Matriz por Matriz--"
for bin1 in ${BINARIOS_PMM[@]}
do
  if [[ $1 -eq "1" ]]; then
    $bin1 800 1
  else
    echo $(basename $bin1); $bin1 800
  fi
done
