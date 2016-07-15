/*
 * ejercicio2.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioPilas2.h"

ejercicio2::ejercicio2(){
	parentesis=0;
	corchetes=0;
	llaves=0;
	valor = false;
}

bool ejercicio2::comprobar (string cadena){
	int cuenta=0;
	while (!cadena.empty() && cadena[cuenta] != '\0'){
		miPila.push(cadena[cuenta]);
		cuenta++;
	}
	while (!miPila.empty()){
		if (miPila.top()=='}'){
			llaves++;
			miPila.pop();
		}else if (miPila.top()=='{' && llaves>0){
			llaves--;
			miPila.pop();
		}else if (miPila.top()==')'){
			parentesis++;
			miPila.pop();
		}else if (miPila.top()=='(' && parentesis>0){
			parentesis--;
			miPila.pop();
		}else if (miPila.top()==']'){
			corchetes++;
			miPila.pop();
		}else if (miPila.top()=='[' && corchetes>0){
			corchetes--;
			miPila.pop();
		}else
			miPila.pop();
	}
	if (llaves==0 && corchetes==0 && parentesis==0)
		valor = true;
	else
		valor = false;
	return valor;
}

//int main(int argc,char *argv[])
void ejercicio2::ejecutar()
{
	string cadena1 = "[Esta] {es} [la] cadena (buena)";
	string cadena2 = "]Esta[ }es{ ]la[ cadena )mala(";
	cout << cadena1 << " la respuesa es: ";
	if (comprobar(cadena1))
		cout << " correcta" << endl;
	else
		cout << " incorrecta" << endl;
	cout << cadena2 << " la respuesa es: ";
	if (comprobar(cadena2))
		cout << " correcta" << endl;
	else
		cout << " incorrecta" << endl;
}
