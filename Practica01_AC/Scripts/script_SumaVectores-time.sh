#!/bin/bash
if [ "$1" = "local" ]; then
  echo
  echo "Tiempos para el programa secuencial: -------------------------------------"
  for ((N=65536;N<67108865;N=N*2))
  do
    time ./bin/SumaVectoresC $N $2
  done

  echo
  echo "Tiempos para el programa paralelo for: -----------------------------------"
  for ((N=65536;N<67108865;N=N*2))
  do
    time ./bin/SumaVectoresOMPfor $N $2
  done

  echo
  echo "Tiempos para el programa paralelo sections: ------------------------------"
  for ((N=65536;N<67108865;N=N*2))
  do
    time ./bin/SumaVectoresOMPsections $N $2
  done

else
  #Se asigna al trabajo el nombre SumaVectoresC_vdinamicos
  #PBS -N SumaVectoresC_vdinamicos
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
  for ((N=65536;N<67108865;N=N*2))
  do
    time 01_Practica1_OMP/SumaVectoresC $N 1
  done
  
  #Se asigna al trabajo el nombre SumaVectoresC_vlocales
  #PBS -N SumaVectores
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
  for ((N=65536;N<67108865;N=N*2))
  do
    time 01_Practica1_OMP/SumaVectoresOMPfor $N 1
  done

  #Se asigna al trabajo el nombre SumaVectoresC_vglobales
  #PBS -N SumaVectoresC_vglobales
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
  for ((N=65536;N<67108865;N=N*2))
  do
    time 01_Practica1_OMP/SumaVectoresOMPsections $N 1
  done
fi