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
# Directorio donde se guardan los ficheros .o
OBJ = obj

###############################################################################
# Variables que no cambian para cualquier compilación
# Includes Propios
INC_P = include
# Includes del sistema
INC_S = /usr/include
# Librerias propias
LIB_P = lib
# Librerias del sistema
LIB_S = /usr/lib64
# Librerias a usar.
LIBS = -lglut -lGLU -lGL -lX11 -lXext -lc -lm
# Extención de los ficheros fuente
EXT = cpp # pueden ser cpp, cc
RM = rm -Rf
GCC = g++
DEBUG = -g3 -O0 -Wall
ARCH = #-m32

###############################################################################
# Opciones de configuración para compilar cualquier programa
GCCDEBUGFLAG = $(ARCH) $(DEBUG) -D$(VER) -L$(LIB_S) -L$(LIB_P) $(LIBS) -I$(INC_S) -I$(INC_P) -c
GCCFLAG = -D$(VER) -L$(LIB_S) -L$(LIB_P) $(LIBS) -I$(INC_S) -I$(INC_P) -c -O3

###############################################################################
# Opciones de configuración para compilar cualquier programa al binario
BINDEBUGFLAGS = $(ARCH) $(DEBUG) -L$(LIB_S) -L$(LIB_P) $(LIBS) -I$(INC_S) -I$(INC_P)
BINFLAGS = -L$(LIB_S) -L$(LIB_P) $(LIBS) -I$(INC_S) -I$(INC_P) -O3

.PHONY: all
.SECONDARY:

all: clean makedir practica3

$(OBJ)/%.o: $(SRC_DIR)/%.$(EXT)
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
	# $(RM) $(LIB_P)/* $(DEBUG_DIR)/* $(BIN_DIR)/* doc/html/* doc/latex/*
	$(RM) $(INC_P)/*.gch $(INC_P)/ver1/*.gch $(INC_P)/ver2/*.gch $(DEBUG_DIR)/* $(BIN_DIR)/* doc/html/* doc/latex/*

touch:
	touch $(SRC_DIR)/*
	touch $(INC_P)/*
