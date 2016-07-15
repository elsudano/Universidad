/*
 * ejercicio12.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO12_LISTAS_H_
#define EJERCICIO12_LISTAS_H_

using namespace std;

template<typename T>
class VDin {
private:
	list<T> miLista;
public:
	class iterator;
	VDin();
	~VDin();
	VDin(const VDin<T> & v);
	VDin(int n, const T & t);
	void push_back(const T & t);
	void pop_back();
	int size() const;
	T & operator[](int pos);
	const T & operator[ ](int pos) const;
	//Método at deshabilitado
	//T & at(int pos);
	//const T & at(int pos) const;
	void clear();
	void resize(int n, const T & t = T());
	bool empty() const;
	VDin<T> & operator=(const VDin<T> & org);

	class iterator {
	public:
		iterator();
		iterator(const VDin<T>::iterator & it);
		T & operator*();
		iterator & operator++(int n);
		iterator & operator--(int n);
		iterator & operator=(const VDin<T>::iterator & it);
		bool operator==(const VDin<T>::iterator & it) const;
		bool operator!=(const VDin<T>::iterator & it) const;
	private:
		friend class VDin;
		typename list<T>::iterator itv;
	};
	iterator begin();
	iterator end();
};
#include "ejercicioListas12.hxx"

class ejercicioListas12{
private:

public:
	void ejecutar();
};


#endif /* EJERCICIO12_LISTAS_H_ */
