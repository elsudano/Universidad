# ********** Variables de MakeFiles **********
SRC = src
INC = include
OBJ = obj
BIN = bin
LIB = lib
CXX = mpicxx
BINFLAGS = -Wl,-rpath,/usr/lib64/openmpi/lib -Wall -g3 -I$(INC) -o

EXECFILES := Camarero Filosofos ProConJesus ProductorConsumidor_1-1 ProductorConsumidor_5-4 holamundo sendrecv1 sendrecv2 intercambio_sincrono multireceive intercambio_nobloq

all: $(EXECFILES)

%: $(SRC)/%.cpp
	$(CXX) $(BINFLAGS) $(BIN)/$@ $^

# ************ Limpieza ************
clean:
	-rm $(OBJ)/* $(BIN)/* $(SRC)/*~ $(INC)/*~ $(LIB)/*

touch:
	touch $(SRC)/*.cpp
