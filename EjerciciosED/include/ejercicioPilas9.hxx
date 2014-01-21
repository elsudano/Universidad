/*
 * ejercicio9.hxx
 *
 *  Created on: 22/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO9_HXX_
#define EJERCICIO9_HXX_

template <typename Y>
ventana<Y>::ventana(){
	pilader = stack<Y>();
	pilaizq = stack<Y>();
}

template <typename Y>
void ventana<Y>::inserta(Y ele){
	pilaizq.push(ele);
}

template <typename Y>
void ventana<Y>::movDer(){
	if (!pilaizq.empty()){
		pilader.push(pilaizq.top());
		pilaizq.pop();
	}
}

template <typename Y>
void ventana<Y>::movIzq(){
	if (!pilader.empty()){
		pilaizq.push(pilader.top());
		pilader.pop();
	}
}

template <typename Y>
int ventana<Y>::size(){
	return  pilader.size()+pilaizq.size();
}

template <typename Y>
int ventana<Y>::getVal(){
	return  pilaizq.top();
}

template <typename Y>
int ventana<Y>::getPos(){
	return  pilaizq.size();
}

template <typename Y>
void ventana<Y>::elimina(){
	pilaizq.pop();
}
#endif /* EJERCICIO9_HXX_ */
