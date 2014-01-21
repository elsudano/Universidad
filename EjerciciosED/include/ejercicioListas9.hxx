/*
 * ejercicioListas9.hxx
 *
 *  Created on: 19/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO9_HXX_
#define EJERCICIO9_HXX_

/**
 * @brief
 */
template<typename T>
vdisperso<T>::vdisperso(const vector<T> &v) {
	this->n = v.size();
	for (unsigned int i = 0; i<this->n;i++){
		this->coefs.push_back(make_pair(i,v[i]));
	}
}

/**
 * @brief
 */
template<typename T>
void vdisperso<T>::asignar_coeficiente(int i, const T &x) {
	typename list<pair<int, T> >::iterator it;
	bool encontrado = false;

	if (i > n)
		n = i;

	else {
		for (it = coefs.begin(); it != coefs.end() && !encontrado; it++)
			if ((*it).first == i) {
				(*it).second = x;
				encontrado = true;
			}
	}

	if (!encontrado) {
		coefs.push_back(make_pair(i, x));
		n++;
	}
}

/**
 * @brief
 */
template<typename T>
vector<T> vdisperso<T>::convertir() const {
	vector<T> v(n);
	typename list<pair<int, T> >::const_iterator it;
	bool encontrado;
	for (int i = 0; i < n; i++) {
		encontrado = false;
		for (it = coefs.begin(); it != coefs.end() && !encontrado; it++) {
			if (i == (*it).first) {
				v.at(i) = (*it).second;
				encontrado = true;
			}
		}
		if (!encontrado)
			v.at(i) = 0;
	}
	return v;
}
#endif /* EJERCICIO9_HXX_ */
