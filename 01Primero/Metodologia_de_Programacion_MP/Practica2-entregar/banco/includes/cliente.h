// **************************************************************
// *** CLIENTE
// **************************************************************
#ifndef _CLIENTE_
#define _CLIENTE_

using namespace std;

struct Cliente {
  string nif;
  string nombre, apellidos;
  string direccion;
};

Cliente LeerCliente();
void EscribirCliente(const Cliente& cli);

#endif
