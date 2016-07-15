/*
 * ejercicio9.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO9_LISTAS_H_
#define EJERCICIO9_LISTAS_H_
#include <vector>

using namespace std;

template <typename T>
class vdisperso {
private:
	list<pair<int, T> > coefs;
	int n;

public:
	vdisperso(const vector<T> &v);
	void asignar_coeficiente(int i, const T &x);
	vector<T> convertir() const;

};
#include "ejercicioListas9.hxx"

class ejercicioListas9{
private:
	/**
	* @brief Muestra por pantalla el contenido de la lista "l"
	* @param[in] puntero a un un tipo de dato T
	*/
	template<typename T>
	void loquesea(const T &p);

	/**
	 * @brief Muestra por pantalla el contenido de la lista "l"
	 * @param[in] puntero a una lista
	 */
	template<typename T>
	void mostrar(const list<T> l);
public:
	void ejecutar();
};

#endif /* EJERCICIO9_LISTAS_H_ */
