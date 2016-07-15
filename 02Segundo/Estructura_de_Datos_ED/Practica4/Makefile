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
# asegurate que no tienes ninguna definicion en el codigo
# osea que no se declara "#define STL" 
VER = STL

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
DEBUG = -g -O0 -Wall
ARCH = -m32

###############################################################################
# Opciones de configuración para compilar cualquier programa
GCCFLAG = $(ARCH) $(DEBUG) -D$(VER) -L$(LIB) -I$(INC) -c
GCCFLAG_FINAL = -L$(LIB) -I$(INC) -c -O3

###############################################################################
# Opciones de configuración para compilar cualquier programa al binario
BINFLAGS = $(ARCH) $(DEBUG) -L$(LIB) -I$(INC)
BINFLAGS_FINAL = -L$(LIB) -I$(INC) -O3

.PHONY: all
.SECONDARY:
.PRECIOUS: $(DAT)

all: clean prueba pasa_test repasa


$(OBJ)/%.o: $(SRC)/%.cpp
	$(GCC) $(GCCFLAG) $^ -o $@

%: $(OBJ)/%.o
	$(GCC) $(BINFLAGS) $^ -o $(BIN_DIR)/$@

docu:
	doxygen ./doc/doxys/dox_config

clean:
	$(RM) $(LIB)/* $(OBJ)/*.o*
	
mrproper : clean
	$(RM) $(BIN_DIR)/* doc/html/* doc/latex/*

touch:
	touch $(SRC)/*
	touch $(INC)/*
