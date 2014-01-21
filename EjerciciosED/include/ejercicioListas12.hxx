/*
 * ejercicioListas12.hxx
 *
 *  Created on: 19/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO12_HXX_
#define EJERCICIO12_HXX_

template<typename T>
VDin<T>::VDin() {
	miLista.clear();
}

template<typename T>
VDin<T>::VDin(int n, const T & t) {
    for(int i=0; i<n; i++)
    	miLista.push_back(t);

}

template<typename T>
VDin<T>::VDin(const VDin<T> & org) {
	miLista = org.miLista;
}

template<typename T>
VDin<T>::~VDin() {

}

template<typename T>
int VDin<T>::size() const {
    return miLista.size();
}

template<typename T>
bool VDin<T>::empty() const {
    return (miLista.size() == 0);
}

template<typename T>
T & VDin<T>::operator[](const int pos) {
    typename VDin<T>::iterator i;
    i = miLista.begin();
    for(int j=0; j<pos; j++){
        i++;
    }
    return *i;
}

template<typename T>
const T & VDin<T>::operator[](const int pos) const {
    typename VDin<T>::iterator i;
    i = miLista.begin();
    for(int j=0; j<pos; j++){
        i++;
    }
    return *i;
}

template<typename T>
void VDin<T>::push_back(const T & t) {
	miLista.push_back(t);
}

template<typename T>
void VDin<T>::pop_back() {
	miLista.pop_back();
}

template<typename T>
void VDin<T>::clear() {
	miLista.clear();
}

template<typename T>
void VDin<T>::resize(int n, const T & t) {
    if(miLista.size() > n){
    	miLista.size() == n;
    }else{
        while(miLista.size() < n){
        	miLista.push_back(t);
        }
    }
}

template<typename T>
VDin<T>& VDin<T>::operator=(const VDin<T> & org) {
	if (this!=&org)
	  this->miLista = org.miLista;
	return *this;
}

template<typename T>
VDin<T>::iterator::iterator() {

}

template<typename T>
VDin<T>::iterator::iterator(const typename VDin<T>::iterator & it) {
    itv = it.itv;
}

template<typename T>
T & VDin<T>::iterator::operator*() {
    return *itv;
}

template<typename T>
typename VDin<T>::iterator & VDin<T>::iterator::operator++(int n) {
    itv++;
    return *this;
}

template<typename T>
typename VDin<T>::iterator & VDin<T>::iterator::operator--(int n) {
    itv--;
    return *this;
}

template<typename T>
typename VDin<T>::iterator & VDin<T>::iterator::operator=(const VDin<T>::iterator & it) {
    itv = it.itv;
    return *this;
}
template<typename T>
bool VDin<T>::iterator::operator==(const VDin<T>::iterator & it) const {
    return (itv == it.itv);
}

template<typename T>
bool VDin<T>::iterator::operator!=(const VDin<T>::iterator & it) const {
    return (itv != it.itv);
}

template<typename T>
typename VDin<T>::iterator VDin<T>::begin() {
    typename VDin<T>::iterator i;
    i.itv = miLista.begin();
    return i;

}

template<typename T>
typename VDin<T>::iterator VDin<T>::end() {
    typename VDin<T>::iterator i;
    i.itv = miLista.end();
    return i;
}
#endif /* EJERCICIO12_HXX_ */
