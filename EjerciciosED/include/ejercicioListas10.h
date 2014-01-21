/*
 * ejercicioListas10.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO10_LISTAS_H_
#define EJERCICIO10_LISTAS_H_
#include <vector>

using namespace std;

template <typename T>
class vdisperso2 {
private:
    list<pair<int, T> > coefs;
    int n;
    T valor_nulo;

public:
	vdisperso2(const vector<T> &v);
	void cambiar_nulo(const T &n);
	void asignar_coeficiente(int i, const T &x);
	vector<T> convertir() const;
	void mostrar();
};
#include "ejercicioListas10.hxx"

class ejercicioListas10{
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
	void mostrar(vdisperso2<T> &vd);
public:
	void ejecutar();
};


#endif /* EJERCICIO10_LISTAS_H_ */
