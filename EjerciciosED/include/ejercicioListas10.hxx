/*
 * ejercicioListas10.hxx
 *
 *  Created on: 19/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO10_HXX_
#define EJERCICIO10_HXX_

/**
 * @brief
 */
template<typename T>
vdisperso2<T>::vdisperso2(const vector<T> &v) {
	n = v.size();
	valor_nulo = 0;
	for (unsigned int i = 0; i < v.size(); i++)
		if (v.at(i) != valor_nulo)
			coefs.push_back(make_pair(i, v.at(i)));
}

/**
 * @brief
 */
template<typename T>
void vdisperso2<T>::cambiar_nulo(const T &n) {
	valor_nulo = n;
}

/**
 * @brief
 */
template<typename T>
void vdisperso2<T>::asignar_coeficiente(int i, const T &x) {
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
vector<T> vdisperso2<T>::convertir() const {
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
			v.at(i) = valor_nulo;
	}
	return v;
}

/**
 * @brief
 */
template<typename T>
void vdisperso2<T>::mostrar() {
	typename list<pair<int, T> >::iterator it;
	for (it = coefs.begin(); it != coefs.end(); it++)
		cout << (*it).first << "," << (*it).second << " ";

}
#endif /* EJERCICIO10_HXX_ */
