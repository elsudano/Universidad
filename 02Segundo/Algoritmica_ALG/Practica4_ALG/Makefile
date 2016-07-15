SHELL    = /bin/bash
DIRS     = src
SRCS     = $(foreach dir,$(DIRS),$(wildcard $(dir)/*.c))
EXES     = $(foreach exe,$(SRCS),$(basename $(exe)))
BIN      = bin
OBJ      = obj
ASM      = asm
RM       = rm -Rf
GCC      = gcc
OPTI     = -g3 -O2
OBJFLAG  = -c -Wall
BINFLAG  = -Wall
ASMFLAG  = -S -Wall

.SECONDARY:

all: $(EXES)

$(DIRS)/%: $(OBJ)/%.o
	$(GCC) $(OPTI) $(BINFLAG) $^ -o $@
	mv $@ $(BIN)/

$(OBJ)/%.o: $(ASM)/%.s
	$(GCC) $(OPTI) $(OBJFLAG) $^ -o $@

$(ASM)/%.s: $(DIRS)/%.c
	$(GCC) $(OPTI) $(ASMFLAG) $^ -o $@

touch:
	touch $(SRC)/*
	touch $(INC)/*

clean:
	$(RM) $(BIN)/* $(ASM)/* $(DIRS)/*.o $(OBJ)/*.o
