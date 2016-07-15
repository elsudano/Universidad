
CC = mpicxx

CFLAGS = -g -Wall 

COMPILE = $(CC) $(CFLAGS)

EXECFILES := holamundo sendrecv1 sendrecv2 intercambio_sincrono multireceive intercambio_nobloq


all: $(EXECFILES)

%: %.cpp
	$(COMPILE) -o $@ $<

clean:
	rm *~ $(EXECFILES)