###############################################################################
# Interprete par la ejecución del Script
SHELL = /bin/bash

###############################################################################
# Variable que guarda los ficheros que hay que compilar
SRC = src

###############################################################################
# Extraemos los nombres de los ficheros para los binarios
BIN_DIR = bin

###############################################################################
# Extraemos los nombres de los ficheros para los binarios
VER = VER1

###############################################################################
# Nos Aseguramos de que exista un fichero .DAT para cada binario
DAT = datos

###############################################################################
# Directorio donde se guardan los ficheros .o
OBJ = obj

###############################################################################
# Variables que no cambian para cualquier compilación
INC = include
LIB = lib
RM = rm -Rf
GCC = g++

###############################################################################
# Opciones de configuración para compilar cualquier programa
GCCFLAG= -m32 -g -D$(VER) -L$(LIB) -I$(INC) -O0 -c #-Wall

###############################################################################
# Opciones de configuración para compilar cualquier programa al binario
BINFLAGS = -m32 -g -L$(LIB) -I$(INC) -O0 #-Wall

.PHONY: all
.SECONDARY:
.PRECIOUS: $(DAT)

all: clean pruebagen_test


$(OBJ)/%.o: $(SRC)/%.cpp
	$(GCC) $(GCCFLAG) $^ -o $@

%: $(OBJ)/%.o
	$(GCC) $(BINFLAGS) $^ -o $(BIN_DIR)/$@

docu:
	doxygen dox_config

clean:
	$(RM) $(LIB)/* $(OBJ)/*.o
	
mrproper : clean
	$(RM) $(BIN_DIR)/* doc/html/* doc/latex/*

touch:
	touch $(SRC)/*
	touch $(INC)/*
