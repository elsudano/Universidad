
.SUFFIXES:
.PHONY: msg_uso msg_clases

JAVASRCS:=$(wildcard *.java)
CLASSES_BASE:=$(shell grep class *.java | cut -d' ' -f2)
CLASSES:=$(addsuffix .class,$(CLASSES_BASE))

msg_uso:
	@echo "SCD: Ejemplos de monitores en Java."
	@echo "usar con:"
	@echo "   make comp    : compilar todos los archivos java si es necesario"
	@echo "   make recomp  : compilar todo incondicionalmente"
	@echo "   make pc_lifo : compilar todo y ejecutar ejemplo del Productor-Consumidor (20 10 5 2 4)"
	@echo "   make pc_fifo : compilar todo y ejecutar ejemplo del Productor-Consumidor (20 10 5 2 4)"
	@echo "   make le      : idem para el ejemplo de lectores-escritores (5 20 15 25)"
	@echo "   make cd      : idem para el ejemplo del contador de días"
	@echo "   make fum     : idem para el ejemplo de los Fumadores"
	@echo "   make bar     : idem para el ejemplo del Barbero"
	@echo "   make bar2    : idem para el ejemplo del Barbero modificado"
	@echo "   make clean   : elimina todos los archivos .class"
	@echo "   make zip     : crea archivo 'scd-p02-ejemplos.zip' con los fuentes"
	
comp: msg_clases monitor_classes $(CLASSES)

recomp:
	make clean	
	make comp

$(CLASSES): $(JAVASRCS)
	javac -g $(JAVASRCS)

monitor_classes: $(wildcard monitor/*.class)
	javac -g monitor/*.java -d .

clean:
	rm -rf *.class monitor/*.class scd-p02-ejemplos.zip
	
pc_lifo: pclifo.class
	java pclifo 3 3 5 5 5

pc_fifo: pcfifo.class
	java pcfifo 3 3 5 5 5
	
le: EjemploLectorEscritor.class
	java EjemploLectorEscritor 5 20 15 25

cd: EjemploContadorDias.class
	java EjemploContadorDias

fum: fum.class
	java fum

bar: barb.class
	java barb

bar2: barb2.class
	java barb2

msg_clases:
	@echo "fuentes java = " $(JAVASRCS)
	@echo "clases = " $(CLASSES_BASE)
	
zip:	scd-p02-ejemplos.zip

scd-p02-ejemplos.zip: $(JAVASRCS) $(wildcard monitor/*.java) makefile
	make clean
	zip scd-p02-ejemplos.zip * monitor/*

