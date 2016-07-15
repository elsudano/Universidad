#!/bin/bash
if [ "$1" = "local" ]; then
  echo
  echo "Tiempos para el programa paralelo paralilizo las filas: --------------------------------"
  for val in 100 1000 10000
  do
    ./bin/pmv-OpenMP-a $val $2
  done

  echo
  echo "Tiempos para el programa paralelo paralelizo las columnas ------------------------------"
  for val in 100 1000 10000
  do
    ./bin/pmv-OpenMP-b $val $2
  done
  
  echo
  echo "Tiempos para el programa paralelo uso reductions ---------------------------------------"
  for val in 100 1000 10000
  do
    ./bin/pmv-OpenMP-reduction $val $2
  done
  
  echo
  echo "Tiempos para el programa secuencial: -------------------------------------"
#  for val in 100 1000 10000
#  do
#    ./bin/pmv-OpenMP-a $val $2
#  done

else
  #Se asigna al trabajo el nombre SumaVectoresC_vlocales
  #PBS -N pmv-OpenMP-a
  #Se asigna al trabajo la cola ac
  #PBS -q ac
  #Se imprime información del trabajo usando variables de entorno de PBS
  echo "Id. usuario del trabajo: $PBS_O_LOGNAME"
  echo "Id. del trabajo: $PBS_JOBID"
  echo "Nombre del trabajo especificado por usuario: $PBS_JOBNAME"
  echo "Nodo que ejecuta qsub: $PBS_O_HOST"
  echo "Directorio en el que se ha ejecutado qsub: $PBS_O_WORKDIR"
  echo "Cola: $PBS_QUEUE"
  echo "Nodos asignados al trabajo:"
  cat $PBS_NODEFILE
  #Se ejecuta SumaVectorC, que está en el directorio en el que se ha ejecutado qsub,
  #para N potencia de 2 desde 2^16 a 2^26
  for val in 100 1000 10000
  do
    ./02_Practica2_OMP/pmv-OpenMP-a $val
  done

  #Se asigna al trabajo el nombre SumaVectoresC_vglobales
  #PBS -N pmv-OpenMP-b
  #Se asigna al trabajo la cola ac
  #PBS -q ac
  #Se imprime información del trabajo usando variables de entorno de PBS
  echo "Id. usuario del trabajo: $PBS_O_LOGNAME"
  echo "Id. del trabajo: $PBS_JOBID"
  echo "Nombre del trabajo especificado por usuario: $PBS_JOBNAME"
  echo "Nodo que ejecuta qsub: $PBS_O_HOST"
  echo "Directorio en el que se ha ejecutado qsub: $PBS_O_WORKDIR"
  echo "Cola: $PBS_QUEUE"
  echo "Nodos asignados al trabajo:"
  cat $PBS_NODEFILE
  #Se ejecuta SumaVectorC, que está en el directorio en el que se ha ejecutado qsub,
  #para N potencia de 2 desde 2^16 a 2^26
  for val in 100 1000 10000
  do
    ./02_Practica2_OMP/pmv-OpenMP-b $val
  done
  
    #Se asigna al trabajo el nombre SumaVectoresC_vdinamicos
  #PBS -N pmv-OpenMP-reduction
  #Se asigna al trabajo la cola ac
  #PBS -q ac
  #Se imprime información del trabajo usando variables de entorno de PBS
  echo "Id. usuario del trabajo: $PBS_O_LOGNAME"
  echo "Id. del trabajo: $PBS_JOBID"
  echo "Nombre del trabajo especificado por usuario: $PBS_JOBNAME"
  echo "Nodo que ejecuta qsub: $PBS_O_HOST"
  echo "Directorio en el que se ha ejecutado qsub: $PBS_O_WORKDIR"
  echo "Cola: $PBS_QUEUE"
  echo "Nodos asignados al trabajo:"
  cat $PBS_NODEFILE
  #Se ejecuta SumaVectorC, que está en el directorio en el que se ha ejecutado qsub,
  #para N potencia de 2 desde 2^16 a 2^26
  for val in 100 1000 10000
  do
    ./02_Practica2_OMP/pmv-OpenMP-reduction $val
  done
  
    #Se asigna al trabajo el nombre SumaVectoresC_vdinamicos
  #PBS -N pmv-secuencial
  #Se asigna al trabajo la cola ac
  #PBS -q ac
  #Se imprime información del trabajo usando variables de entorno de PBS
  echo "Id. usuario del trabajo: $PBS_O_LOGNAME"
  echo "Id. del trabajo: $PBS_JOBID"
  echo "Nombre del trabajo especificado por usuario: $PBS_JOBNAME"
  echo "Nodo que ejecuta qsub: $PBS_O_HOST"
  echo "Directorio en el que se ha ejecutado qsub: $PBS_O_WORKDIR"
  echo "Cola: $PBS_QUEUE"
  echo "Nodos asignados al trabajo:"
  cat $PBS_NODEFILE
  #Se ejecuta SumaVectorC, que está en el directorio en el que se ha ejecutado qsub,
  #para N potencia de 2 desde 2^16 a 2^26
#  for val in 100 1000 10000
#  do
#    ./02_Practica2_OMP/pmv-secuencial $val
#  done
fi