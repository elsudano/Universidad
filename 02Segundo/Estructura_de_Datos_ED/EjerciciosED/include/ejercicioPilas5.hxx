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
laPila<W>::laPila() {

}

/**
 * @brief
 */
template<typename W>
W laPila<W>::top(){
	return s1.back();
}

/**
 * @brief
 */
template<typename W>
void laPila<W>::push(W x) {
	s1.push(x);
}

/**
 * @brief
 */
template<typename W>
void laPila<W>::pop() {
	while (s1.size() != 0) {
		s2.push(s1.back());
		s1.pop();
	}
	s2.pop();
	while (s2.size() != 0) {
		s1.push(s2.back());
		s2.pop();
	}

}

/**
 * @brief
 */
template<typename W>
bool laPila<W>::empty() {
	return (s1.size() == 0);
}

/**
 * @brief
 */
template<typename W>
int laPila<W>::size() {
	return s1.size();
}

#endif /* EJERCICIO4_HXX_ */
