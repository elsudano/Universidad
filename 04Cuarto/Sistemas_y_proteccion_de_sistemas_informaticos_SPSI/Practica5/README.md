# Practica 5 Puzle BlockChain

### Descripción

Esta es una pequeña aplicación que se utiliza par resolver la 5 practica de la
asignatura SPSI de la universidad de Granada UGR se ha implementado en Python 
para poder aprovechar otro proyecto que ya tenía en marcha sobre interfaz gráfica
en este lenguaje 

[Skeleton App](https://github.com/elsudano/skeleton_python)

### Requisitos

Para poder utilizar la aplicación es necesario tener instalado el paquete de tkinter
para Python cuidado que hay varias versiones según la versión de Python que tengamos
instalado, la aplicación esta preparada para Python 3.7 en adelante y se esta
intentando hacer retrocompatible con Python 2.7

Para instalar el paquete Tkinter/tkinter dependiendo de la versión de Python tenemos
que ejecutar lo siguiente en Fedora 29:

```bash
sudo dnf install tkinter -y
```
Una vez que tenemos el paquete de Tkinter necesitamos instalar las dependencias del
programa, para eso ejecutamos en la raíz del proyecto y desde la consola lo siguiente:

```bash
pip3.7 -r install requirements.txt
```
Cuidado!!! que si tenemos un entorno virtual tenemos que usar primero:

```bash
source path_entorno_virtual/bin/activate
```

### Como ejecutar

Para poder ejecutar simplemente ponemos en la linea de comandos en el
directorio raíz de la aplicación:

```bash
python3.7 mayn.py
```

### Posibles mejoras

Cuando empezamos a calcular iterativamente los bloques podríamos utilizar hebras
para que los cálculos fueran mas rápidos.