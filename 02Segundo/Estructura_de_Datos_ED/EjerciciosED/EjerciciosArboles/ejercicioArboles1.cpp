/*
 * ejercicioArboles1.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioArboles1.h"

template<typename T>
void ejercicioArboles1::mostrar(const bintree<T> &arb){
	cout << endl << "Tamaño: " << arb.size() << "\n";
	cout << "preorden: ";
	for (bintree<int>::const_preorderiterator it= arb.beginPreorder(); it!=arb.endPreorder(); ++it)
	  cout << *it << " ";
	cout << endl;

	cout << "postorden: ";
	for (bintree<int>::const_postorderiterator it= arb.beginPostorder(); it!=arb.endPostorder(); ++it)
	  cout << *it << " ";
	cout << endl;

	cout << "inorden: ";
	for (bintree<int>::const_inorderiterator it= arb.beginInorder(); it!=arb.endInorder(); ++it)
	  cout << *it << " ";
	cout << endl;

	cout << "niveles: ";
	for (bintree<int>::const_leveliterator it= arb.beginLevel(); it!=arb.endLevel(); ++it)
	  cout << *it << " ";
	cout << endl;
}

template<typename T>
bintree<T> ejercicioArboles1::llenar(bintree<T> arbol, const vector<T> &datos){
	bintree<int>::node nodo = arbol.root();
	for (int d = 0; d < datos.size()-1; d++){
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

template<typename T>
int ejercicioArboles1::altura(const bintree<T> &arbol, const typename bintree<T>::node &nodo){
	return 0;
}

void ejercicioArboles1::ejecutar(){
	int cantidad=20;
	bintree<int> miArbol(36);
	bintree<int>::node nodo_altura;
	*nodo_altura = 57;
	vector<int> datos(cantidad);
	for (int d=0;d<cantidad;d++)
		datos[d]=d*3;
	miArbol = llenar(miArbol,datos);
	mostrar(miArbol);
	cout << "La altura del Nodo 57 es: " << altura(miArbol,nodo_altura) << endl;

}
