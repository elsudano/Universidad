#include <iostream>
#include <set>
#include "bintree.h"
#include <vector>

using namespace std;

void mostrar(const bintree<int> &A){
	typename bintree<int>::node N = A.root();
	bintree<int> B;
	typename bintree<int>::node aux;
	if (!N.null()) {
		aux = N.left();
		B.replace_subtree(B.root(),B,aux);
		if (!aux.null())
			mostrar(B);
		cout << *N;
		while (!aux.null()) {
			aux = aux.parent().right();
			mostrar(B);
		}
	}
}

bintree<int> llenar(bintree<int> arbol, const vector<int> &datos){
	bintree<int>::node nodo = arbol.root();
	for (unsigned d = 0; d < datos.size()-1; d++){
		if (!nodo.null() && nodo.parent().null()){
			bintree<int> arbTmp(datos.at(d));
			arbol.insert_left(nodo,arbTmp);
			nodo = nodo.left();
		}else if (!nodo.null() && !nodo.parent().null()){
			bintree<int> arbTmp(datos.at(d));
			if (nodo.parent().right().null())
				arbol.insert_right(nodo.parent(),arbTmp);
			else{
				arbol.insert_left(nodo,arbTmp);
				nodo = nodo.left();
			}
		}else{
			cout << "falla algo";
		}
	}
	return arbol;
}

int multi_interseccion(const bintree<int> &A, int num){
	return 0;
}

int main(int argc, char *argv[]) {
	int cantidad=20;
	bintree<int> miArbol(36);
	vector<int> datos(cantidad);
	for (int d=0;d<cantidad;d++)
		datos[d]=d*3;
	miArbol = llenar(miArbol,datos);
	mostrar(miArbol);
}
