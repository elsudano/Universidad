/*
 * Ejercicio2.cpp
 *
 *  Created on: 18/02/2014
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "examen2.h"

template<typename T>
void examen2::mostrar(const multiset<T> &datos){
	multiset<int>::iterator datos_it = datos.begin();
	cout << "Datos: ";
	while (datos_it!=datos.end()){
		cout << *datos_it << " ";
		datos_it++;
	}
	cout << endl;
}

template<typename T>
multiset<T> examen2::multi_interseccion(const multiset<T> &m1 , const multiset<T> &m2){
	multiset<int> aux;
	multiset<int>::iterator aux_it = aux.begin();
	multiset<int>::const_iterator m1_it = m1.begin();
	multiset<int>::const_iterator m2_it = m2.begin();
	while (m1_it!=m1.end()){
		m2_it = m2.begin();
		while (m2_it!=m2.end()){
			if ( (*m1_it==*m2_it) && (aux.count(*m1_it)==0) ){
				if ((m1.count(*m1_it)<m2.count(*m1_it)))
					for (unsigned d=0;d<m1.count(*m1_it);d++)
						aux.insert(aux_it,*m1_it);
				else
					for (unsigned d=0;d<m2.count(*m1_it);d++)
						aux.insert(aux_it,*m2_it);
			}
			m2_it++;
		}
		m1_it++;
	}
	return aux;
}

void examen2::ejecutar(){
	int miInts1[] = {2,2,3,3};
	multiset<int> m1(miInts1,miInts1+4);
	mostrar(m1);
	int miInts2[] = {1,2,3,3,3,4};
	multiset<int> m2(miInts2,miInts2+6);
	mostrar(m2);
	multiset<int> resultado = multi_interseccion(m1,m2);
	mostrar(resultado);
}
