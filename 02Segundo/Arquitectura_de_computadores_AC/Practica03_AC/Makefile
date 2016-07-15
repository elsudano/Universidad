SHELL    = /bin/bash
DIRS     = src
SRCS     = $(foreach dir,$(DIRS),$(wildcard $(dir)/*.c))
EXES     = $(foreach exe,$(SRCS),$(basename $(exe)))
BIN      = bin
OBJ      = obj
RM       = rm -Rf
GCC      = gcc
OPTI     = -O2 #-g3 
PARALLEL = -fopenmp
OBJFLAG  = -lrt -c -Wall
BINFLAG  = -lrt -Wall

.SECONDARY:

all: $(EXES)

$(DIRS)/%: $(OBJ)/%.o
	$(GCC) $(PARALLEL) $(OPTI) $(BINFLAG) $^ -o $@
	mv $@ $(BIN)/

$(OBJ)/%.o: $(DIRS)/%.c
	$(GCC) $(PARALLEL) $(OPTI) $(OBJFLAG) $^ -o $@

touch:
	touch $(SRC)/*
	touch $(INC)/*

clean:
	$(RM) $(BIN)/* $(DIRS)/*.o $(OBJ)/*.o