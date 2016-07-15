SHELL    = /bin/bash
DIRS     = src
BIN      = bin
OBJ      = obj
ASM      = asm
RM       = rm -Rf
GCC      = gcc
OPTI     = -Os#<<---- Cambiando esta variable tenemos las diferentes optimizaciones del compilador
OBJFLAG  = -c -Wall
BINFLAG  = -Wall
ASMFLAG  = -S -Wall

.SECONDARY:

all: bin/figura1$(OPTI) \
	 bin/figura1-mejora1$(OPTI) \
	 bin/figura1-mejora2$(OPTI) \
	 bin/figura1-mejora3$(OPTI) \
	 bin/figura1-mejora_mem$(OPTI) \
	 bin/figura1-mejora_tot$(OPTI) \
	 bin/pmm$(OPTI) \
	 bin/pmm-mejora1$(OPTI) \
	 bin/pmm-mejora2$(OPTI) \
	 bin/pmm-mejora_mem$(OPTI) \
	 bin/pmm-mejora_tot$(OPTI) \
	 bin/daxpy$(OPTI) \
#--------------------Figura1-Binarios----------------------------------------
$(BIN)/figura1$(OPTI): $(OBJ)/figura1.o
	$(GCC) $(OPTI) $(BINFLAG) $^ -o $@
#---------------Figura1-Eliminamos-un-For-interno----------------------------
$(BIN)/figura1-mejora1$(OPTI): $(OBJ)/figura1-modificado.o $(OBJ)/figura1-mejora1$(OPTI).o
	$(GCC) $(OPTI) $(BINFLAG) $^ -o $@
#-----------Figura1-Desenrollamos-el-bucle-mas-interno-----------------------	
$(BIN)/figura1-mejora2$(OPTI): $(OBJ)/figura1-modificado.o $(OBJ)/figura1-mejora2$(OPTI).o
	$(GCC) $(OPTI) $(BINFLAG) $^ -o $@
#------Figura1-Sustituimos-Multiplicaciones-por-desplazamiento-de-bits--------	
$(BIN)/figura1-mejora3$(OPTI): $(OBJ)/figura1-modificado.o $(OBJ)/figura1-mejora3$(OPTI).o
	$(GCC) $(OPTI) $(BINFLAG) $^ -o $@
#------------------Figura1-Realizamos-Alineamiento-de-Memoria-----------------
$(BIN)/figura1-mejora_mem$(OPTI): $(OBJ)/figura1-modificado1.o $(OBJ)/figura1-mejora$(OPTI).o
	$(GCC) $(OPTI) $(BINFLAG) $^ -o $@
#----------------Figura1-Integramos-las-mejoras-mas-rapidas-------------------
$(BIN)/figura1-mejora_tot$(OPTI): $(OBJ)/figura1-modificado1.o $(OBJ)/figura1-mejora2$(OPTI).o
	$(GCC) $(OPTI) $(BINFLAG) $^ -o $@
#---------------------Figura1-original----------------------------------------
$(OBJ)/figura1.o: src/figura1.c
	$(GCC) $(OPTI) $(OBJFLAG) $^ -o $@
#---------------Figura1-modificado-por-defecto--------------------------------
$(OBJ)/figura1-modificado.o: src/figura1-modificado.c
	$(GCC) $(OPTI) $(OBJFLAG) $^ -o $@
#---------------Figura1-modificado-mejora-alineamiento-memoria----------------
$(OBJ)/figura1-modificado1.o: src/figura1-modificado.c
	$(GCC) $(OPTI) $(OBJFLAG) -DMEJORA_MEM $^ -o $@
#---------------Figura1-sin-modificaciones-1-2-3------------------------------
$(OBJ)/figura1-mejora$(OPTI).o: $(ASM)/figura1-mejora$(OPTI).s
	$(GCC) $(OPTI) $(OBJFLAG) $^ -o $@

$(ASM)/figura1-mejora$(OPTI).s: src/figura1-mejoras.c
	$(GCC) $(OPTI) $(ASMFLAG) $^ -o $@
#---------------Figura1-modificaciones-1-2-3-separadas------------------------
$(OBJ)/figura1-mejora1$(OPTI).o: $(ASM)/figura1-mejora1$(OPTI).s
	$(GCC) $(OPTI) $(OBJFLAG) $^ -o $@

$(ASM)/figura1-mejora1$(OPTI).s: src/figura1-mejoras.c
	$(GCC) $(OPTI) $(ASMFLAG) -DMEJORA1 $^ -o $@
#----------------------------------------------------------------------------
$(OBJ)/figura1-mejora2$(OPTI).o: $(ASM)/figura1-mejora2$(OPTI).s
	$(GCC) $(OPTI) $(OBJFLAG) $^ -o $@

$(ASM)/figura1-mejora2$(OPTI).s: src/figura1-mejoras.c
	$(GCC) $(OPTI) $(ASMFLAG) -DMEJORA2 $^ -o $@
#----------------------------------------------------------------------------
$(OBJ)/figura1-mejora3$(OPTI).o: $(ASM)/figura1-mejora3$(OPTI).s
	$(GCC) $(OPTI) $(OBJFLAG) $^ -o $@

$(ASM)/figura1-mejora3$(OPTI).s: src/figura1-mejoras.c
	$(GCC) $(OPTI) $(ASMFLAG) -DMEJORA3 $^ -o $@
#--------------------PMM-Binarios--------------------------------------------
$(BIN)/pmm$(OPTI): $(OBJ)/pmm.o
	$(GCC) $(OPTI) $(BINFLAG) $^ -o $@

$(BIN)/pmm-mejora1$(OPTI): $(OBJ)/pmm-modificado.o $(OBJ)/pmm-mejora1$(OPTI).o
	$(GCC) $(OPTI) $(BINFLAG) $^ -o $@
	
$(BIN)/pmm-mejora2$(OPTI): $(OBJ)/pmm-modificado.o $(OBJ)/pmm-mejora2$(OPTI).o
	$(GCC) $(OPTI) $(BINFLAG) $^ -o $@

$(BIN)/pmm-mejora_mem$(OPTI): $(OBJ)/pmm-modificado1.o $(OBJ)/pmm-mejora$(OPTI).o
	$(GCC) $(OPTI) $(BINFLAG) $^ -o $@

$(BIN)/pmm-mejora_tot$(OPTI): $(OBJ)/pmm-modificado1.o $(OBJ)/pmm-mejora2$(OPTI).o
	$(GCC) $(OPTI) $(BINFLAG) $^ -o $@
#--------------------PMM-por-defecto------------------------------------------
$(OBJ)/pmm.o: src/pmm.c
	$(GCC) $(OPTI) $(OBJFLAG) $^ -o $@

$(OBJ)/pmm-modificado.o: src/pmm-modificado.c
	$(GCC) $(OPTI) $(OBJFLAG) $^ -o $@

$(OBJ)/pmm-modificado1.o: src/pmm-modificado.c
	$(GCC) $(OPTI) $(OBJFLAG) $^ -DMEJORA_MEM -o $@
#---------------PMM-modificaciones-1-2-separadas-------------------------------
$(OBJ)/pmm-mejora$(OPTI).o: $(ASM)/pmm-mejora$(OPTI).s
	$(GCC) $(OPTI) $(OBJFLAG) $^ -o $@

$(ASM)/pmm-mejora$(OPTI).s: src/pmm-mejoras.c
	$(GCC) $(OPTI) $(ASMFLAG) -DMEJORA1 $^ -o $@
#------------------------------------------------------------------------------
$(OBJ)/pmm-mejora1$(OPTI).o: $(ASM)/pmm-mejora1$(OPTI).s
	$(GCC) $(OPTI) $(OBJFLAG) $^ -o $@

$(ASM)/pmm-mejora1$(OPTI).s: src/pmm-mejoras.c
	$(GCC) $(OPTI) $(ASMFLAG) -DMEJORA1 $^ -o $@
#------------------------------------------------------------------------------
$(OBJ)/pmm-mejora2$(OPTI).o: $(ASM)/pmm-mejora2$(OPTI).s
	$(GCC) $(OPTI) $(OBJFLAG) $^ -o $@

$(ASM)/pmm-mejora2$(OPTI).s: src/pmm-mejoras.c
	$(GCC) $(OPTI) $(ASMFLAG) -DMEJORA2 $^ -o $@
#-------------------------------DAXPY------------------------------------------
$(BIN)/daxpy$(OPTI): $(OBJ)/daxpy$(OPTI).o
	$(GCC) $(OPTI) $(BINFLAG) $^ -o $@

$(OBJ)/daxpy$(OPTI).o: $(ASM)/daxpy$(OPTI).s
	$(GCC) $(OPTI) $(OBJFLAG) $^ -o $@

$(ASM)/daxpy$(OPTI).s: src/daxpy.c
	$(GCC) $(OPTI) $(ASMFLAG) $^ -o $@
#------------------------------------------------------------------------------
touch:
	touch $(SRC)/*
	touch $(INC)/*

clean:
	$(RM) $(BIN)/* $(DIRS)/*.o $(OBJ)/*.o $(ASM)/*.s