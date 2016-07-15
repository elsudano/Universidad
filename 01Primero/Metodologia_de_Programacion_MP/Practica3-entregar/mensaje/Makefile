# ********** Variables de MakeFiles **********
SRC = src
INC = include
OBJ = obj
BIN = bin
LIB = lib
CXX = g++
CPPFLAGS = -Wall -g  -L$(LIB) -I$(INC) -c
BINFLAGS = -Wall -g  -L$(LIB) -I$(INC) -o

# ************ Generación de documentación ******************
documentacion:
	doxygen doc/doxys/Doxyfile

# ********** Compilación de ejecutables **********
$(BIN)/ocultar: $(OBJ)/ocultar.o $(LIB)/libmodulos.a
	$(CXX) $(BINFLAGS) $(BIN)/ocultar $(OBJ)/ocultar.o -lmodulos
	
$(BIN)/revelar: $(OBJ)/revelar.o $(LIB)/libmodulos.a
	$(CXX) $(BINFLAGS) $(BIN)/revelar $(OBJ)/revelar.o -lmodulos

# ************ Compilación de módulos ************
$(OBJ)/imagenES.o : $(SRC)/imagenES.cpp $(INC)/imagenES.h
	$(CXX) $(CPPFLAGS) $(SRC)/imagenES.cpp -o $(OBJ)/imagenES.o
	
$(OBJ)/codificar.o : $(SRC)/codificar.cpp $(INC)/codificar.h
	$(CXX) $(CPPFLAGS) $(SRC)/codificar.cpp -o $(OBJ)/codificar.o

$(OBJ)/ocultar.o : $(SRC)/ocultar.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/ocultar.cpp -o $(OBJ)/ocultar.o

$(OBJ)/revelar.o : $(SRC)/revelar.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/revelar.cpp -o $(OBJ)/revelar.o

# ********** Creación de Librerias **********
$(LIB)/libmodulos.a: $(OBJ)/imagenES.o $(OBJ)/codificar.o
	ar	-rvs $(LIB)/libmodulos.a $(OBJ)/imagenES.o $(OBJ)/codificar.o

# ************ Limpieza ************
clean :
	-rm $(OBJ)/* $(SRC)/*~ $(INC)/*~ ./*~ $(LIB)/*

mrproper : clean
	-rm $(BIN)/* doc/html/*
