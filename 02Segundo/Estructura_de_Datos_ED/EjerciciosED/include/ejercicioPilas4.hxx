/*
 * ejercicio4.hxx
 *
 *  Created on: 19/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef EJERCICIO4_HXX_
#define EJERCICIO4_HXX_

/**
 * @brief
 */
template<typename W>
laCola<W>::laCola() {

}

/**
 * @brief
 */
template<typename W>
void laCola<W>::push(W x) {
	s1.push(x);
}

/**
 * @brief
 */
template<typename W>
void laCola<W>::pop() {
	while (s1.size() != 0) {
		s2.push(s1.top());
		s1.pop();
	}
	s2.pop();
	while (s2.size() != 0) {
		s1.push(s2.top());
		s2.pop();
	}

}

/**
 * @brief
 */
template<typename W>
W laCola<W>::back() {
	return s1.top();
}

/**
 * @brief
 */
template<typename W>
W laCola<W>::front() {
	while (s1.size() != 0) {
		s2.push(s1.top());
		s1.pop();
	}
	W x = s2.top();
	while (s2.size() != 0) {
		s1.push(s2.top());
		s2.pop();
	}
	return x;
}

/**
 * @brief
 */
template<typename W>
bool laCola<W>::empty() {
	return (s1.size() == 0);
}

/**
 * @brief
 */
template<typename W>
int laCola<W>::size() {
	return s1.size();
}

#endif /* EJERCICIO4_HXX_ */
