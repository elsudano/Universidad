#!/bin/bash
if ( test -n "$1" ) then 
  test_fx=`test -f $1 && test -x $1 && echo true || echo false`
  test_L=`test -L $1 && echo true || echo false`

  if ($test_fx) then
    echo "En este fichero tiene permisos de ejecución y es un fichero plano"
  else
    echo "En este fichero NO tiene permisos de ejecución y NO es un fichero plano"
  fi

  if ($test_L) then
    echo "Este fichero es un enlace simbólico"
  else
    echo "Este fichero NO es un enlace simbólico"
  fi

#---------------------------------------------------------------------------------------
  if (test "$1" = "-h") then
    echo "Este Script sirve para saber si los días que quedan para fin de año son divisibles por 5 y solo se ha de poner $0"
  else
     if (( ((365-$(date +%j))%5)==0 )); then
      echo "Los días que faltan para fin de año son divisibles por 5"
    else
      echo "Los días que faltan para fin de año NO son divisibles por 5"
    fi
  fi
#---------------------------------------------------------------------------------------
  #!/bin/bash
  if (( $(cat /etc/passwd | grep -c "$1:")>0 )); then
    if (($(ls -l /bin/ls | grep -c "$1 $1"))); then
      echo "El usuario es propietario del fichero"
      if (($(ls -l /bin/ls | grep -c "r.."))); then
	echo "El usuario tiene permisos de lectura en el fichero"
      else
	echo "El usuario NO tiene permisos de lectura en el fichero"
      fi
    else
      echo "El usuario NO es propietario del fichero"
      if (($(ls -l /bin/ls | grep -c "r..\."))); then
	echo "El usuario tiene permisos de lectura en el fichero"
      else
	echo "El usuario NO tiene permisos de lectura en el fichero"
      fi
    fi
  else
    echo "$1 NO existe como usuario en el sistema"
  fi
#---------------------------------------------------------------------------------------
  #!/bin/bash
echo $1|wc -m
  if (($(echo $1|wc -m)-1==1)); then
    if (($(echo $1|grep -c [A-Z]))); then
      echo "Es una letra mayúscula"
    elif (($(echo $1|grep -c [a-z]))); then
      echo "Es una letra minúscula"
    else
      echo "El caracter introducido no es ninguna letra"
    fi
  else
    echo "Solo se permite un caracter"
  fi
else
  echo "ponga un parametro"
fi