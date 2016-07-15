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
$(BIN)/negativo: $(OBJ)/negativo.o $(LIB)/libimagen.a
	$(CXX) $(BINFLAGS) $(BIN)/negativo $(OBJ)/negativo.o -limagen
	
$(BIN)/desplazar: $(OBJ)/desplazar.o $(LIB)/libimagen.a
	$(CXX) $(BINFLAGS) $(BIN)/desplazar $(OBJ)/desplazar.o -limagen

$(BIN)/prueba: $(OBJ)/prueba.o $(LIB)/libimagen.a
	$(CXX) $(BINFLAGS) $(BIN)/prueba $(OBJ)/prueba.o -limagen

rebuild: touch $(BIN)/negativo $(BIN)/desplazar

# ************ Compilación de módulos ************
$(OBJ)/negativo.o: $(SRC)/negativo.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/negativo.cpp -o $(OBJ)/negativo.o

$(OBJ)/desplazar.o: $(SRC)/desplazar.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/desplazar.cpp -o $(OBJ)/desplazar.o

$(OBJ)/prueba.o: $(SRC)/prueba.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/prueba.cpp -o $(OBJ)/prueba.o

$(OBJ)/imagenES.o: $(SRC)/imagenES.cpp $(INC)/imagenES.h
	$(CXX) $(CPPFLAGS) $(SRC)/imagenES.cpp -o $(OBJ)/imagenES.o
	
$(OBJ)/imagen.o: $(SRC)/imagen.cpp $(INC)/imagen.h
	$(CXX) $(CPPFLAGS) $(SRC)/imagen.cpp -o $(OBJ)/imagen.o

$(OBJ)/transformar.o: $(SRC)/transformar.cpp $(INC)/transformar.h
	$(CXX) $(CPPFLAGS) $(SRC)/transformar.cpp -o $(OBJ)/transformar.o

# ********** Creación de Librerias **********
$(LIB)/libimagen.a: $(OBJ)/imagenES.o $(OBJ)/imagen.o $(OBJ)/transformar.o
	ar	-rvs $(LIB)/libimagen.a $(OBJ)/imagenES.o $(OBJ)/imagen.o $(OBJ)/transformar.o

# ************ Limpieza ************
clean :
	-rm $(OBJ)/* $(SRC)/*~ $(INC)/*~ ./*~ $(LIB)/*

mrproper : clean
	-rm $(BIN)/negativo $(BIN)/desplazar doc/html/*

touch:
	touch $(SRC)/negativo.cpp
	touch $(SRC)/desplazar.cpp
	touch $(SRC)/transformar.cpp