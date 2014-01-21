/*
 * ejercicio10.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO10_ARBOLES10_H_
#define EJERCICIO10_ARBOLES10_H_


using namespace std;

template <typename T>
class arbol {
private:
	list<pair<int, T> > coefs;
	int n;

public:
	arbol(const vector<T> &v);
	void cambiar_nulo(const T &n);
	void asignar_coeficiente(int i, const T &x);
	vector<T> convertir() const;

};
#include "ejercicioArboles10.hxx"

class ejercicioArboles10{
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


#endif /* EJERCICIO10_ARBOLES10_H_ */
