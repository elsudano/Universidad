/*
 * ejercicioListas10.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioListas10.h"

template<typename T>
void ejercicioListas10::mostrar(vdisperso2<T> &vd) {
	vd.mostrar();
}

template<typename T>
void ejercicioListas10::loquesea(const T &p) {

}

void ejercicioListas10::ejecutar() {
	const int TAM = 20;

	vector<int> v(TAM), v_res(TAM + 10);

	v.at(0) = 1;
	v.at(2) = 2;
	v.at(11) = 3;
	v.at(8) = 1;

	vdisperso2<int> vd(v);

	vd.asignar_coeficiente(7, 10);
	vd.asignar_coeficiente(15, 10);
	vd.asignar_coeficiente(25, 10);

	cout << endl << "Total datos: " << v_res.size() << endl;
	cout << "Datos originales: " << endl;
	this->mostrar(vd);

	vd.cambiar_nulo(-1);

	v_res = vd.convertir();
	cout << endl << "Total datos: " << v_res.size() << endl;
	cout << "Datos reconvertidos: " << endl;
	for (unsigned int i = 0; i < v_res.size(); i++)
		cout << v_res.at(i) << " ";
}
