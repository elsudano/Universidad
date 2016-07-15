/*
 * ejercicioListas11.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioListas11.h"

template<typename T>
void ejercicioListas11::mostrarDesComprimida(const list<T> &l){
    typename list<T>::const_iterator i;
    for(i=l.begin(); i!=l.end(); i++){
        cout << *i << " ";
    }
    cout << endl;
}

template<typename T>
void ejercicioListas11::mostrarComprimida(const list<pair<T,int> > &l){
    typename list<pair<T,int> >::const_iterator li;
    for(li=l.begin(); li!=l.end(); li++){
        cout << "<" << (*li).first << "," << (*li).second << ">" << " ";
    }
    cout << endl;
}

template<typename T>
list<pair<T, int> > ejercicioListas11::comprimir(const list<T> &l){
	typename list<T>::const_iterator it;
	it = l.begin();
	typedef pair<T,int> entrada;
	entrada miEntrada;
	list<entrada> miLista;
	while (it!=l.end()){
		if (miLista.empty()){
			miEntrada.first=*it;
			miEntrada.second=1;
			miLista.push_back(miEntrada);
		}else{
			it++;
			T aux = miLista.back().first;
			if (aux==*it){
				miLista.back().second++;
			}else{
				miEntrada.first=*it;
				miEntrada.second=1;
				miLista.push_back(miEntrada);
			}
		}
	}
	return miLista;
}

template<typename T>
list<T> ejercicioListas11::descomprimir(const list<pair<T, int> > &lc){
	typename list<pair<T, int> >::const_iterator it;
	it = lc.begin();
	list<T> miLista;
	while (it!=lc.end()){
		int count;
		for (count=0;count<(*it).second;count++)
			miLista.push_back((*it).first);
		it++;
	}
	return miLista;
}

void ejercicioListas11::ejecutar(){
	typedef pair<char,int> entrada;
	list<entrada> miListaComprimida;
	list<char> miListaDesComprimida;
	entrada miEntrada;
	unsigned int count = 0;

	while (count<10){
		miEntrada.first = 'a'+count;
		miEntrada.second = 3+count;
		miListaComprimida.push_back(miEntrada);
		count++;
	}

	mostrarComprimida(miListaComprimida);
	cout << endl;
	miListaDesComprimida = descomprimir(miListaComprimida);
	cout << endl;
	mostrarDesComprimida(miListaDesComprimida);
	count=0;
	while(count<miListaDesComprimida.size()){
		if (count%2==0)
			miListaDesComprimida.pop_back();
		else
			miListaDesComprimida.pop_front();
		count++;
	}
	cout << endl;
	mostrarDesComprimida(miListaDesComprimida);
	cout << endl;
	miListaComprimida = comprimir(miListaDesComprimida);
	cout << endl;
	mostrarComprimida(miListaComprimida);

}
