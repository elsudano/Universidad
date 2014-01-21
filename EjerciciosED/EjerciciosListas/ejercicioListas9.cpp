/*
 * ejercicioListas9.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioListas9.h"

template<typename T>
void ejercicioListas9::mostrar(const list<T> l) {
	typename list<T>::const_iterator i;
	for (i = l.begin(); i != l.end(); i++) {
		cout << *i << " ";
	}
	cout << endl;
}

template<typename T>
void ejercicioListas9::loquesea(const T &p) {

}

void ejercicioListas9::ejecutar() {
	const int TAM = 20;

	vector<int> v(TAM), v_res(TAM + 10);

	v.at(0) = 1;
	v.at(2) = 2;
	v.at(7) = 3;
	v.at(8) = 1;

	vdisperso<int> vd(v);

	vd.asignar_coeficiente(5, 10);
	vd.asignar_coeficiente(15, 10);
	vd.asignar_coeficiente(25, 10);

	v_res = vd.convertir();
	cout << endl << "Total datos: " << v_res.size() << endl;
	for (unsigned int i = 0; i < v_res.size(); i++)
		cout << v_res.at(i) << " ";
}
