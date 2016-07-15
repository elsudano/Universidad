#!/bin/bash
#Se asigna al trabajo el nombre cpuInfo
#PBS -N cpuInfo
#Se asigna al trabajo la cola ac
#PBS -q ac
#Se imprime información del trabajo usando variables de entorno de PBS
echo "Id. usuario del trabajo: $PBS_O_LOGNAME"
echo "Entorno de trabajo: $PBS_O_WORKDIR"
echo "Id. del trabajo: $PBS_JOBID"
echo "Nombre del trabajo especificado por usuario: $PBS_JOBNAME"
echo "Nodo que ejecuta qsub: $PBS_O_HOST"
echo "Cola: $PBS_QUEUE"
echo "Nodos asignados al trabajo:"
cat $PBS_NODEFILE
#Se fija a 12 el nº de threads máximo (tantos como cores en un nodo)
export OMP_THREAD_LIMIT=12
echo "Nº de threads inicial: $OMP_THREAD_LIMIT"
#Se ejecuta HelloOMP, que está en el directorio en el que se ha ejecutado qsub
cat /proc/cpuinfo > ~/00_Practica0_Hello/cpuinfo_remoto.txt