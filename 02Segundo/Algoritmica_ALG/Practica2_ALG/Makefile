SHELL    = /bin/bash
DIRS     = src
SRCS     = $(foreach dir,$(DIRS),$(wildcard $(dir)/*.cpp))
EXES     = $(foreach exe,$(SRCS),$(basename $(exe)))
BIN      = bin
OBJ      = obj
RM       = rm -Rf
GCC      = g++
OPTI     = -g3 #-O2
OBJFLAG  = -c -Wall -DLINUX
BINFLAG  = -Wall -DLINUX

.SECONDARY:

all: $(EXES)

$(DIRS)/%: $(OBJ)/%.o
	$(GCC) $(OPTI) $(BINFLAG) $^ -o $@
	mv $@ $(BIN)/

$(OBJ)/%.o: $(DIRS)/%.cpp
	$(GCC) $(OPTI) $(OBJFLAG) $^ -o $@

touch:
	touch $(SRC)/*
	touch $(INC)/*

clean:
	$(RM) $(BIN)/* $(DIRS)/*.o $(OBJ)/*.o