#!/bin/bash

function pausa(){
   read -p "		Pulse la tecla [ENTER] para continuar"
}

function progreso (){
  printf "\r%79s\r"
}

function inicio_ejercicio(){
  clear
  printf "\n\r"
  printf "		------------------------------------------------------------\n\r"
  printf "		|                                                          |\n\r"
  printf "		| Comenzamos el Ejercicio $1                                |\n\r"
  printf "		|                                                          |\n\r"
  printf "		------------------------------------------------------------\n\r"
}
function fin_ejercicio(){
  printf "\n\r"
  printf "		------------------------------------------------------------\n\r"
  printf "		|                                                          |\n\r"
  printf "		| Aquí se termina el ejercicio $1                           |\n\r"
  printf "		|                                                          |\n\r"
  printf "		------------------------------------------------------------\n\r"
}

function pregunta(){
  printf "		¿Es tu directorio HOME? si/no\n\r"
  pwd
  PREGUNTA=no
  while [ "$PREGUNTA" != si ]; do
    read PREGUNTA
    if [ "$PREGUNTA" = no ]; then
    cd /
    cd ~
    printf "		¿Y ahora? si/no\n\r"
    pwd
    fi
  done
}

function crea_objetos(){
  cd /
  cd ~

  mkdir ug1
  touch pg1
  ls *ug1* -l
  ls *pg1* -l
  cd ug1
  mkdir ee51vn
  touch ma51ik
  ls -l
  cd ee51vn
  mkdir docs
  mkdir pics
  ls -l

}

function borra_objetos(){
  cd /
  cd ~
  rm pg1
  cd ug1
  rm ma51ik
  cd ee51vn
  rmdir docs
  rmdir pics
  cd ..
  rmdir ee51vn
  cd ..
  rmdir ug1
}

function inicializa(){
  printf "		¿Desea inicializar el entorno?\n\r"
  read PREGUNTA
  if [ "$PREGUNTA" = si ]; then
    cd /
    cd ~
    rm ug1 -r
    rm pg1
    rm FS -r
    rm FP -r
  fi
}

function mover(){
  cd /
  cd ~/ug1/ee51vn/
  mv pics ~/ug1/imagenes
  cp docs ~/ug1/docs -r
}

printf "\n\r"
printf "		------------------------------------------------------------\n\r"
printf "		| Con este script que se llama $0 intentaremos    |\n\r"
printf "		| realizar la primera practica de la asignatura de         |\n\r"
printf "		| Fundamentos del Software. Que consiste en crear un árbol  |\n\r"
printf "		| de directorios o carpetas dentro de nuestro 'HOME'.      |\n\r"
printf "		|                                                          |\n\r"
printf "		| Lo primero que vamos a realizar sera ir paso a paso      |\n\r"
printf "		| creando el árbol que se pide en dicha practica.          |\n\r"
printf "		|                                                          |\n\r"
printf "		| Nota: si no es la primera vez que ejecuta este GUION     |\n\r"
printf "		| por favor inicialize el entorno		           |\n\r"
printf "		------------------------------------------------------------\n\r"
inicializa
#pausa
printf "\n\r"
printf "		Primero comprobamos que estamos en el directorio correcto\n\r"
pregunta
printf "\n\r"
printf "		------------------------------------------------------------\n\r"
printf "		|                                                          |\n\r"
printf "		| Ahora vamos a crear todos los directorios                |\n\r"
printf "		|                                                          |\n\r"
printf "		------------------------------------------------------------\n\r"
pausa
if [ -d ~/ug1 ]; then
  borra_objetos
fi
crea_objetos
fin_ejercicio 1
pausa
inicio_ejercicio 2
pregunta
printf "\n\r"
printf "		------------------------------------------------------------\n\r"
printf "		|                                                          |\n\r"
printf "		| Ahora vamos a mover y copiar directorios                 |\n\r"
printf "		|                                                          |\n\r"
printf "		------------------------------------------------------------\n\r"
pausa
mover
fin_ejercicio 2
pausa
inicio_ejercicio 3
pregunta
printf "\n\r"
printf "		------------------------------------------------------------\n\r"
printf "		|                                                          |\n\r"
printf "		| Vamos a ver cuales son los ficheros de mi directorio     |\n\r"
printf "		|                                                          |\n\r"
printf "		------------------------------------------------------------\n\r"
pausa
ls -l
cd ug1
touch pg1
ls -l
fin_ejercicio 3
pausa
inicio_ejercicio 4
pregunta
printf "\n\r"
printf "		------------------------------------------------------------\n\r"
printf "		|                                                          |\n\r"
printf "		| a) Obtener en nombre de camino absoluto (pathname	   |\n\r"
printf "		|    absoluto) de tu directorio home. ¿Es el mismo que	   |\n\r"
printf "		|    el de tu compañero/a?				   |\n\r"
printf "		|                                                          |\n\r"
printf "		|  b) Crear un directorio para cada asignatura en la que   |\n\r"
printf "		|     se van a realizar prácticas de laboratorio y, dentro |\n\r"
printf "		|     de cada directorio, nuevos directorios para cada una |\n\r"
printf "		|     de las prácticas realizadas hasta el momento.        |\n\r"
printf "		|                                                          |\n\r"
printf "		| c) Dentro del directorio de la asignatura fundamentos    |\n\r"
printf "		|    del software (llamado FS) y dentro del directorio     |\n\r"
printf "		|    creado para esta sesión, copiar los archivos host y   |\n\r"
printf "		|    passwd que se encuentran dentro del directorio /etc.  |\n\r"
printf "		|                                                          |\n\r"
printf "		| d) Mostrar el contenido de cada uno de los archivos.     |\n\r"
printf "		|                                                          |\n\r"
printf "		------------------------------------------------------------\n\r"
pausa
printf "		Respuesta A\n\r"
cd /
cd ~
pwd
pausa
printf "		Respuesta B\n\r"
cd /
cd ~
mkdir FS
mkdir FP
ls -l
cd FS
mkdir practica1
mkdir practica2
ls -l
cd ..
cd FP
mkdir practica1
mkdir practica2
ls -l
pausa
printf "		Respuesta C\n\r"
cd /
cd ~
cd FS
cd practica2
cp /etc/hosts ~/FS/practica2/hosts
cp /etc/passwd ~/FS/practica2/passwd
pausa
pregunta
printf "		Respuesta D\n\r"
cd /
cd ~/FS/practica2
cat hosts
pausa
cat passwd
pausa
fin_ejercicio 4
pausa
inicio_ejercicio 5a
pregunta
cd /
cd bin
ls ????
fin_ejercicio 5a
pausa
inicio_ejercicio 5b
pregunta
cd /
cd bin
ls [df]* -R
fin_ejercicio 5b
pausa
inicio_ejercicio 5c
pregunta
cd /
cd bin

fin_ejercicio 5c
pausa
inicio_ejercicio 5d
pregunta
cd /
cd bin

fin_ejercicio 5d
pausa
inicio_ejercicio 6
pregunta
cd /
cd ~
ls term*[gz,zip]

fin_ejercicio 6
pausa
