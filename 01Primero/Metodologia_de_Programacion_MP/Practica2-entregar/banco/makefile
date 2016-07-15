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

$(EJECUTABLE)main1: $(OBJECTS)main1.o $(LIB_DIR)libfinanzas.a
	g++	-g -L$(LIB_DIR) -I$(INC_DIR) -o $(EJECUTABLE)main1 $(OBJECTS)main1.o -lfinanzas

$(EJECUTABLE)main2: $(OBJECTS)main2.o $(LIB_DIR)libfinanzas.a
	g++	-g -L$(LIB_DIR) -I$(INC_DIR) -o $(EJECUTABLE)main2 $(OBJECTS)main2.o -lfinanzas

$(EJECUTABLE)main3: $(OBJECTS)main3.o $(LIB_DIR)libfinanzas.a
	g++	-g -L$(LIB_DIR) -I$(INC_DIR) -o $(EJECUTABLE)main3 $(OBJECTS)main3.o -lfinanzas

$(EJECUTABLE)main4: $(OBJECTS)main4.o $(LIB_DIR)libfinanzas.a
	g++	-g -L$(LIB_DIR) -I$(INC_DIR) -o $(EJECUTABLE)main4 $(OBJECTS)main4.o -lfinanzas

$(OBJECTS)main1.o: $(FUENTES)main1.cpp
	g++	 -I$(INC_DIR) -o $(OBJECTS)main1.o -c $(FUENTES)main1.cpp

$(OBJECTS)main2.o: $(FUENTES)main2.cpp
	g++	 -I$(INC_DIR) -o $(OBJECTS)main2.o -c $(FUENTES)main2.cpp

$(OBJECTS)main3.o: $(FUENTES)main3.cpp
	g++	 -I$(INC_DIR) -o $(OBJECTS)main3.o -c $(FUENTES)main3.cpp

$(OBJECTS)main4.o: $(FUENTES)main4.cpp
	g++	 -I$(INC_DIR) -o $(OBJECTS)main4.o -c $(FUENTES)main4.cpp

$(OBJECTS)cliente.o: $(FUENTES)cliente.cpp
	g++	 -I$(INC_DIR) -o $(OBJECTS)cliente.o -c $(FUENTES)cliente.cpp

$(OBJECTS)fecha.o: $(FUENTES)fecha.cpp
	g++	 -I$(INC_DIR) -o $(OBJECTS)fecha.o -c $(FUENTES)fecha.cpp

$(OBJECTS)hora.o: $(FUENTES)hora.cpp 
	g++	-I$(INC_DIR) -o $(OBJECTS)hora.o -c $(FUENTES)hora.cpp

$(OBJECTS)cuenta.o: $(FUENTES)cuenta.cpp
	g++	-I$(INC_DIR) -o $(OBJECTS)cuenta.o -c $(FUENTES)cuenta.cpp

$(OBJECTS)movimiento.o: $(FUENTES)movimiento.cpp
	g++	 -I$(INC_DIR) -o $(OBJECTS)movimiento.o -c $(FUENTES)movimiento.cpp

$(OBJECTS)banco.o: $(FUENTES)banco.cpp
	g++	-I$(INC_DIR) -o $(OBJECTS)banco.o -c $(FUENTES)banco.cpp

$(LIB_DIR)libfinanzas.a: $(OBJECTS)cliente.o $(OBJECTS)fecha.o $(OBJECTS)hora.o $(OBJECTS)cuenta.o $(OBJECTS)banco.o $(OBJECTS)movimiento.o
	ar	-rvs $(LIB_DIR)libfinanzas.a $(OBJECTS)cliente.o $(OBJECTS)fecha.o $(OBJECTS)hora.o $(OBJECTS)cuenta.o $(OBJECTS)banco.o $(OBJECTS)movimiento.o

rebuild: touch $(EJECUTABLE)main1 $(EJECUTABLE)main2 $(EJECUTABLE)main3 $(EJECUTABLE)main4

limpiar:
	-rm $(OBJECTS)*.o
	-rm *~
	-rm $(FUENTES)*.cpp~
	-rm $(INC_DIR)*.h~
	-rm $(EJECUTABLE)main1 $(EJECUTABLE)main2 $(EJECUTABLE)main3 $(EJECUTABLE)main4
	-rm $(LIB_DIR)libfinanzas.a

touch:
	touch $(FUENTES)cliente.cpp
	touch $(FUENTES)fecha.cpp
	touch $(FUENTES)hora.cpp
	touch $(FUENTES)cuenta.cpp
	touch $(FUENTES)movimiento.cpp
	touch $(FUENTES)banco.cpp

