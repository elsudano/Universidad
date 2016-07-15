# ********** Variables de MakeFiles **********
SRC = src
INC = include
OBJ = obj
BIN = bin
LIB = lib
CXX = g++
CPPFLAGS = -Wall -g -lrt -lpthread -L$(LIB) -I$(INC) -c 
BINFLAGS = -Wall -g -lrt -lpthread -L$(LIB) -I$(INC) -o

all: bin/fum bin/fumadores bin/fumadores2 bin/pro_con_lifo bin/pro_con_fifo bin/pclifo bin/pcfifo bin/pc2

# ********** Compilación de ejecutables **********
$(BIN)/pclifo: touch $(OBJ)/pclifo.o
	$(CXX) $(BINFLAGS) $(BIN)/pclifo $(OBJ)/pclifo.o
	
$(BIN)/pcfifo: touch $(OBJ)/pcfifo.o
	$(CXX) $(BINFLAGS) $(BIN)/pcfifo $(OBJ)/pcfifo.o
	
$(BIN)/pc2: touch $(OBJ)/pc2.o
	$(CXX) $(BINFLAGS) $(BIN)/pc2 $(OBJ)/pc2.o
		
$(BIN)/fumadores: touch $(OBJ)/fumadores.o $(OBJ)/comunes.o $(OBJ)/fun_tiempo.o
	$(CXX) $(BINFLAGS) $(BIN)/fumadores $(OBJ)/fumadores.o $(OBJ)/comunes.o $(OBJ)/fun_tiempo.o

$(BIN)/fum: touch $(OBJ)/fum.o $(OBJ)/comunes.o $(OBJ)/fun_tiempo.o
	$(CXX) $(BINFLAGS) $(BIN)/fum $(OBJ)/fum.o $(OBJ)/comunes.o $(OBJ)/fun_tiempo.o

$(BIN)/fumadores2: touch $(OBJ)/fumadores2.o
	$(CXX) $(BINFLAGS) $(BIN)/fumadores2 $(OBJ)/fumadores2.o
		
$(BIN)/pro_con_lifo: touch $(OBJ)/tipo_lifo.o $(OBJ)/comunes.o $(OBJ)/fun_tiempo.o
	$(CXX) $(BINFLAGS) $(BIN)/pro_con_lifo $(OBJ)/tipo_lifo.o $(OBJ)/comunes.o $(OBJ)/fun_tiempo.o

$(BIN)/pro_con_fifo: touch $(OBJ)/tipo_fifo.o $(OBJ)/comunes.o $(OBJ)/fun_tiempo.o
	$(CXX) $(BINFLAGS) $(BIN)/pro_con_fifo $(OBJ)/tipo_fifo.o $(OBJ)/comunes.o $(OBJ)/fun_tiempo.o
	
# ************ Compilación de módulos ************
$(OBJ)/fumadores.o: $(SRC)/fumadores.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/fumadores.cpp -o $(OBJ)/fumadores.o

$(OBJ)/fumadores2.o: $(SRC)/fumadores2.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/fumadores2.cpp -o $(OBJ)/fumadores2.o

$(OBJ)/pclifo.o: $(SRC)/pclifo.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/pclifo.cpp -o $(OBJ)/pclifo.o
	
$(OBJ)/pc2.o: $(SRC)/pc2.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/pc2.cpp -o $(OBJ)/pc2.o
	
$(OBJ)/pcfifo.o: $(SRC)/pcfifo.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/pcfifo.cpp -o $(OBJ)/pcfifo.o
		
$(OBJ)/tipo_lifo.o: $(SRC)/tipo_lifo.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/tipo_lifo.cpp -o $(OBJ)/tipo_lifo.o

$(OBJ)/tipo_fifo.o: $(SRC)/tipo_fifo.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/tipo_fifo.cpp -o $(OBJ)/tipo_fifo.o

$(OBJ)/comunes.o: $(SRC)/comunes.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/comunes.cpp -o $(OBJ)/comunes.o

$(OBJ)/fun_tiempo.o: $(SRC)/fun_tiempo.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/fun_tiempo.cpp -o $(OBJ)/fun_tiempo.o

$(OBJ)/fum.o: $(SRC)/fum.cpp
	$(CXX) $(CPPFLAGS) $(SRC)/fum.cpp -o $(OBJ)/fum.o

# ************ Limpieza ************
clean:
	-rm $(OBJ)/* $(BIN)/* #$(SRC)/*~ $(INC)/*~ ./*~ $(LIB)/*
	
rebuild: touch all

touch:
	touch $(SRC)/fumadores.cpp
	touch $(SRC)/tipo_lifo.cpp
	touch $(SRC)/tipo_fifo.cpp
	touch $(SRC)/fifo.cpp
	touch $(SRC)/lifo.cpp
	touch $(SRC)/pc2.cpp
	touch $(SRC)/comunes.cpp
	touch $(SRC)/fun_tiempo.cpp
	touch $(SRC)/fun.cpp