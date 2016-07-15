# Titulo:	Fichero MakeFile para la generacion de los ejecutables de la ultima practica
# Fecha:	14/02/13
# Autor:	Carlos de la Torre
# Versión:	1.0
# Descripción:	Utilizamos las variables para realizar solo las modificaciones en un solo sitio
# Opciones: 
# clean = elimina los ficheros residuales
# mrproper = elimina los ejecutables y la documentación
# rebuild = esten como esten los ficheros vuelve a generar todos los ejecutables.
# Uso: make -f Makefile sin parámetros (Genera todos los ejecutables)

# ********** Variables de MakeFiles **********
SRC = src
INC = include
OBJ = obj
BIN = bin
LIB = lib
CXX = g++
CPPFLAGS = -Wall -g  -L$(LIB) -I$(INC) -c
BINFLAGS = -Wall -g -L$(LIB) -o

todo: bin/ocultar bin/revelar bin/transformar bin/generar bin/componer

# ************ Generación de documentación ******************
documentacion:
	doxygen doc/doxys/Doxyfile

# ********** Compilación de ejecutables **********
$(BIN)/ocultar: $(OBJ)/ocultar.o $(LIB)/libmodulos.a
	$(CXX) $(BINFLAGS) $(BIN)/ocultar $(OBJ)/ocultar.o -lmodulos
	
$(BIN)/revelar: $(OBJ)/revelar.o $(LIB)/libmodulos.a
	$(CXX) $(BINFLAGS) $(BIN)/revelar $(OBJ)/revelar.o -lmodulos

$(BIN)/transformar: $(OBJ)/transformar.o $(LIB)/libmodulos.a
	$(CXX) $(BINFLAGS) $(BIN)/transformar $(OBJ)/transformar.o -lmodulos
	
$(BIN)/generar: $(OBJ)/generar.o $(LIB)/libmodulos.a
	$(CXX) $(BINFLAGS) $(BIN)/generar $(OBJ)/generar.o -lmodulos

$(BIN)/componer: $(OBJ)/componer.o $(LIB)/libmodulos.a
	$(CXX) $(BINFLAGS) $(BIN)/componer $(OBJ)/componer.o -lmodulos

# ************ Compilación de módulos ************
$(OBJ)/ocultar.o : $(SRC)/ocultar.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/ocultar.cpp -o $(OBJ)/ocultar.o

$(OBJ)/revelar.o : $(SRC)/revelar.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/revelar.cpp -o $(OBJ)/revelar.o

$(OBJ)/transformar.o : $(SRC)/transformar.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/transformar.cpp -o $(OBJ)/transformar.o

$(OBJ)/generar.o : $(SRC)/generar.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/generar.cpp -o $(OBJ)/generar.o

$(OBJ)/componer.o : $(SRC)/componer.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/componer.cpp -o $(OBJ)/componer.o

$(OBJ)/imagenES.o : $(SRC)/imagenES.cpp $(INC)/imagenES.h
	$(CXX) $(CPPFLAGS) $(SRC)/imagenES.cpp -o $(OBJ)/imagenES.o

$(OBJ)/imagen.o : $(SRC)/imagen.cpp $(INC)/imagen.h
	$(CXX) $(CPPFLAGS) $(SRC)/imagen.cpp -o $(OBJ)/imagen.o

$(OBJ)/transformacion.o : $(SRC)/transformacion.cpp $(INC)/transformacion.h
	$(CXX) $(CPPFLAGS) $(SRC)/transformacion.cpp -o $(OBJ)/transformacion.o

$(OBJ)/Transformaciones.o : $(SRC)/Transformaciones.cpp $(INC)/Transformaciones.h
	$(CXX) $(CPPFLAGS) $(SRC)/Transformaciones.cpp -o $(OBJ)/Transformaciones.o

$(OBJ)/procesar.o : $(SRC)/procesar.cpp $(INC)/procesar.h
	$(CXX) $(CPPFLAGS) $(SRC)/procesar.cpp -o $(OBJ)/procesar.o

# ********** Creación de Librerias **********
$(LIB)/libmodulos.a: $(OBJ)/imagenES.o $(OBJ)/imagen.o $(OBJ)/transformacion.o $(OBJ)/Transformaciones.o $(OBJ)/procesar.o
	ar	-rvs $(LIB)/libmodulos.a $(OBJ)/imagenES.o $(OBJ)/imagen.o $(OBJ)/transformacion.o $(OBJ)/Transformaciones.o $(OBJ)/procesar.o

# ************ Limpieza ************
clean :
	-rm $(OBJ)/* $(SRC)/*~ $(INC)/*~ ./*~ $(LIB)/*

mrproper : clean
	-rm $(BIN)/* doc/html/*
	
rebuild : 
	touch $(SRC)/transformacion.cpp
	touch $(INC)/transformacion.h
