#!/bin/bash 
# Ejercicio 2 del la sesión 6
printf "Este Script sirve para buscar todos los\nficheros de un directorio y diferenciar entre Directorios, Enlaces Simbolicos o Archivos Normales\n"
printf "Por favor introduzca el directorio a buscar "
read -a DIRECTORIO

for datos in $(ls -a $DIRECTORIO)
do
  if `test -d $DIRECTORIO/$datos && echo true || echo false` ; then
    printf '\e[1;34m%-6s\e[m\n\r' "$DIRECTORIO/$datos Directorio"
  fi
  if `test -h "$DIRECTORIO/$datos" && echo true || echo false` ; then
    printf '\e[1;31m%-6s\e[m\n\r' "$DIRECTORIO/$datos Enlace Simbolico"
  elif `test -f "$DIRECTORIO/$datos" && echo true || echo false` ; then
    printf '\e[1;33m%-5s\e[m\n\r' "$DIRECTORIO/$datos Archivo"
    # Aquí no se pone nada por que no se contempla esta posibilidad
  fi
done

# Ejercicio 5 del la sesión 6
while :
do
  clear
  read -p "Por favor introduzca un valor entre 1 y 10: " valor
  let valor=$valor+0
  if [ $valor -ge 1 ] && [ $valor -le 10 ]
  then
    printf "Numero correcto\n"
    sleep 3
    break
  else
    printf "Introduzca un valor correcto\n"
    sleep 3
  fi
done


# Ejercicio 1 del la sesión 6
printf "Este Script sirve para buscar todos los\nficheros de un directorio dado\ncuyo tamaño es menor que el dado por teclado\n"
if `test -f archivosSizN.txt && echo true || echo false`; then
  rm archivosSizN.txt
fi
read -p "Por favor introduzca el directorio a buscar " directorio
if `test -d $directorio && echo true || echo false` ; then
  read -p "Por favor introduzca el tamaño mínimo en bytes para el fichero " tamano
  if expr $tamano : '-\?[0-9]\+$' ; then
    for datos in $(ls -a $directorio)
    do
      if `test -d $directorio/$datos && echo false || echo true` ; then
	if `test -h $directorio/$datos && echo false || echo true` ; then
	  find $directorio/$datos -size -$tamano'c' >> archivosSizN.txt
	fi
      fi
    done
    printf "Estos son los ficheros que tienen un tamaño menor o igual al indicado y que se encuentran en le directorio $directorio\n"
    cat archivosSizN.txt
  else
    printf "no ha introducido un entero\n"
  fi
else
  printf "El directorio introducido es incorrecto o no existe\n"
fi

# Ejercicio 9 del la sesión 6
#!/bin/bash
if (($#==3)) && (($2>=1)) && (($2<=99));then
  
fi
printf
printf "Usage: $0 <directorio> <cantidad(1-99)> <basename>"