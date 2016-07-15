# Nombre archivo: makefile
# Uso: make
# Descripción: Mantiene todas las dependencias entre los módulos y la biblioteca
# 	       que utiliza el programa.

# Variable que indica el directorio en donde se encuentran los archivos de cabecera
INC_DIR= ./includes/

# Variable que indica el directorio en donde se encuentran las librerias
LIB_DIR= ./lib/

# Variable que indica el directorio en donde se encuentran los objetos
OBJECTS= ./objetos/

# Variable que indica el directorio en donde se encuentran los objetos
FUENTES= ./scr/

# Variable que indica el directorio en donde se encuentra el Binario
EJECUTABLE= ./bin/

$(EJECUTABLE)central: $(OBJECTS)central.o $(LIB_DIR)libformas.a
	g++	-g -L$(LIB_DIR) -I$(INC_DIR) -o $(EJECUTABLE)central $(OBJECTS)central.o -lformas

$(OBJECTS)punto.o: $(FUENTES)punto.cpp
	g++	 -I$(INC_DIR) -o $(OBJECTS)punto.o -c $(FUENTES)punto.cpp

$(OBJECTS)circulo.o: $(FUENTES)circulo.cpp
	g++	 -I$(INC_DIR) -o $(OBJECTS)circulo.o -c $(FUENTES)circulo.cpp

$(OBJECTS)central.o: $(FUENTES)central.cpp 
	g++	-I$(INC_DIR) -o $(OBJECTS)central.o -c $(FUENTES)central.cpp

$(LIB_DIR)libformas.a: $(OBJECTS)punto.o $(OBJECTS)circulo.o
	ar	-rvs $(LIB_DIR)libformas.a $(OBJECTS)punto.o $(OBJECTS)circulo.o

rebuild: touch $(EJECUTABLE)central

limpiar:
	-rm $(OBJECTS)*.o
	-rm *~
	-rm $(EJECUTABLE)central
	-rm $(LIB_DIR)libformas.a

touch:
	touch $(FUENTES)central.cpp
	touch $(FUENTES)punto.cpp
	touch $(FUENTES)circulo.cpp

