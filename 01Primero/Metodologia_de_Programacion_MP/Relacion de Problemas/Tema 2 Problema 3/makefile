# Nombre archivo: makefile
# Uso: make
# Descripción: Mantiene todas las dependencias entre los módulos y la biblioteca
# 	       que utiliza el programa.

# Variable que indica el directorio en donde se encuentran los archivos de cabecera
INC_DIR= ./includes/

# Variable que indica el directorio en donde se encuentran las librerias
LIB_DIR= ./lib/

# Variable que indica el directorio en donde se encuentran los objetos
OBJECTS= ./obj/

# Variable que indica el directorio en donde se encuentran los Archivos Fuente
FUENTES= ./scr/

# Variable que indica el directorio en donde se encuentra el Binario
EJECUTABLE= ./bin/

$(EJECUTABLE)main: $(OBJECTS)main.o
	g++	-g -L$(LIB_DIR) -I$(INC_DIR) -o $(EJECUTABLE)main $(OBJECTS)main.o

$(OBJECTS)main.o: $(FUENTES)main.cpp
	g++	 -I$(INC_DIR) -o $(OBJECTS)main.o -c $(FUENTES)main.cpp

#$(LIB_DIR)libfunciones.a: $(OBJECTS)funciones.o
#	ar	-rvs $(LIB_DIR)libfinanzas.a $(OBJECTS)funciones.o

rebuild: touch $(EJECUTABLE)main

limpiar:
	-rm $(OBJECTS)*.o
	-rm *~
	-rm $(FUENTES)*.cpp~
	-rm $(INC_DIR)*.h~
	-rm $(EJECUTABLE)main
	-rm $(LIB_DIR)libfinanzas.a

touch:
	touch $(FUENTES)main.cpp
