SHELL   = /bin/bash
DIRS    = EjerciciosPilas EjerciciosListas EjerciciosArboles Examen2014
SRCS    = $(foreach dir,$(DIRS),$(wildcard $(dir)/*.cpp))
OBJS    = $(SRCS:.cpp=.o)
OBJ_DIR = obj
SRC_DIR = src
BIN     = bin
INC     = include
LIB     = lib
RM      = rm -Rf
GCC     = g++
OPTI    = -O2
OBJFLAG = -m32 -g3 -L$(LIB) -I$(INC) -c -Wall
BINFLAG = -m32 -g3 -L$(LIB) -I$(INC) -Wall

.SECONDARY:

all: cuenta multi MenuPrincipal

%.o: %.cpp
	$(GCC) $(OBJFLAG) $^ -o $@

$(LIB)/LibreriaPrincipal.a: $(OBJS) 
	ar -rvs $@ $^
	mv $^ $(OBJ_DIR)

MenuPrincipal: $(SRC_DIR)/MenuPrincipal.o $(SRC_DIR)/menu.o $(LIB)/LibreriaPrincipal.a
	$(GCC) $(BINFLAG) $^ -o $(BIN)/$@
	mv $(SRC_DIR)/MenuPrincipal.o $(SRC_DIR)/menu.o $(OBJ_DIR)

cuenta: $(SRC_DIR)/CuentaCaminos.o
	$(GCC) $(BINFLAG) $^ -o $(BIN)/$@

multi: $(SRC_DIR)/Multi_Interseccion.o
	$(GCC) $(BINFLAG) $^ -o $(BIN)/$@
	
clean:
	$(RM) $(BIN)/* $(SRC_DIR)/*.o $(OBJ_DIR)/*.o $(LIB)/*.a

touch:
	touch $(SRC)/*
	touch $(INC)/*