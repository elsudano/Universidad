###############################################################################
# Interprete par la ejecución del Script
SHELL = /bin/bash

###############################################################################
# Variable que guarda los ficheros que hay que compilar
SRC_DIR = src

###############################################################################
# Extraemos los nombres de los ficheros para los binarios
BIN_DIR = bin

###############################################################################
# Aqui es donde estan los binarios compilados con las opciones de depuración
DEBUG_DIR = debug

###############################################################################
# Extraemos los nombres de los ficheros para los binarios
# asegurate que no tienes ninguna definicion en el codigo
# osea que no se declara "#define STL" 
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
DEBUG = -g3 -O0 -Wall
ARCH = -m32

###############################################################################
# Opciones de configuración para compilar cualquier programa
GCCDEBUGFLAG = $(ARCH) $(DEBUG) -D$(VER) -L$(LIB) -I$(INC) -c
GCCFLAG = -D$(VER) -L$(LIB) -I$(INC) -c -O3

###############################################################################
# Opciones de configuración para compilar cualquier programa al binario
BINDEBUGFLAGS = $(ARCH) $(DEBUG) -L$(LIB) -I$(INC)
BINFLAGS = -L$(LIB) -I$(INC) -O3

.PHONY: all
.SECONDARY:
.PRECIOUS: $(DAT)

all: clean makedir prueba test QuienEsQuien suficiente minimo

$(OBJ)/%.o: $(SRC_DIR)/%.cpp
	$(GCC) $(GCCDEBUGFLAG) $^ -o $(DEBUG_DIR)/$@
	$(GCC) $(GCCFLAG) $^ -o $(BIN_DIR)/$@
	
%: $(OBJ)/%.o
	$(GCC) $(BINDEBUGFLAGS) $(DEBUG_DIR)/$^ -o $(DEBUG_DIR)/$@
	$(GCC) $(BINFLAGS) $(BIN_DIR)/$^ -o $(BIN_DIR)/$@

docu:
	doxygen ./doc/doxys/dox_config
	
makedir:
	mkdir ./$(DEBUG_DIR)/$(OBJ)
	mkdir ./$(BIN_DIR)/$(OBJ)

clean:
	$(RM) $(LIB)/* $(DEBUG_DIR)/* $(BIN_DIR)/* doc/html/* doc/latex/*

touch:
	touch $(SRC_DIR)/*
	touch $(INC)/*
