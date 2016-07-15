# Nombre archivo: makefile
# Uso: make
# Descripción: Mantiene todas las dependencias entre los módulos y la biblioteca
# 	       que utiliza el programa.

# Variable que indica el directorio en donde se encuentran los archivos de cabecera
INCLUDE_DIR= ./includes

programa: programa.o ordenacion.o array.o impresion.o
	g++	-g -o programa programa.o ordenacion.o array.o impresion.o

programa.o: programa.cpp
	g++	-g -I$(INCLUDE_DIR) -c programa.cpp 

ordenacion.o: ordenacion.cpp array.cpp
	g++	-g -I$(INCLUDE_DIR) -c ordenacion.cpp array.cpp

array.o: array.cpp
	g++	-g -I$(INCLUDE_DIR) -c array.cpp

impresion.o: impresion.cpp
	g++	-g -I$(INCLUDE_DIR) -c impresion.cpp

limpiar:
	rm *~
	rm *.o
	rm programa