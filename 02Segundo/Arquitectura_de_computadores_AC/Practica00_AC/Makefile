SHELL   = /bin/bash
DIRS    = src
SRCS    = $(foreach dir,$(DIRS),$(wildcard $(dir)/*.cpp))
OBJS    = $(SRCS:.cpp=.o)
SRC_DIR = src
BIN     = bin
LIB     = lib
RM      = rm -Rf
GCC     = g++
OPTI    = -O2
OBJFLAG = -fopenmp -g3 -lrt -L$(LIB) -I$(INC) -c -Wall
BINFLAG = -fopenmp -g3 -lrt -L$(LIB) -I$(INC) -Wall

.SECONDARY:

all: helloOMP SumaVectoresC

%.o: %.cpp
	$(GCC) $(OBJFLAG) $^ -o $@

helloOMP: $(SRC_DIR)/helloOMP.o
	$(GCC) $(BINFLAG) $^ -o $(BIN)/$@

SumaVectoresC: $(SRC_DIR)/SumaVectoresC.o
	$(GCC) $(BINFLAG) $^ -o $(BIN)/$@
	
SumaVectoresCpp: $(SRC_DIR)/SumaVectoresCpp.o
	gcc $(BINFLAG) $^ -o $(BIN)/$@
	
clean:
	$(RM) $(BIN)/* $(SRC_DIR)/*.o $(OBJ_DIR)/*.o $(LIB)/*.a

touch:
	touch $(SRC)/*
	touch $(INC)/*