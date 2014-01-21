/*
 * ejercicio3.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioPilas3.h"

laberinto::laberinto(){
	this->columnas=0;
	this->filas=0;
}

/**
 * @brief Con esta funcion leemos el fichero txt y llénamos un
 * TDA donde estan todos los caracteres del laberinto, la matriz
 * esta en memoria.
 */
bool laberinto::leer(const char* fichero){
	bool abierto = false;
	string linea;
	ifstream miFichero(fichero);
	if (miFichero.is_open()) {
		while (getline(miFichero, linea)){
			if (this->columnas==0)
				this->columnas=linea.length();
			for (int c=0;c<this->columnas;c++)
				this->lab.push_back(linea[c]);
			this->filas++;
		}
		miFichero.close();
		for (int f=0;f<this->filas*this->columnas;f++)
			this->vis.push_back('N');
		cout << "El Laberinto tiene " << this->filas << " Filas y " << this->columnas << " Columnas" << endl << endl;
		abierto = true;
	}else
		cout << "El fichero no se ha abierto" << endl;
	return abierto;
}

/**
 * @brief Con esta funcion buscamos un la entrada y la salida
 * del laberinto y la guardamos en dos variables
 */
void laberinto::busca_entrada_salida(){
	bool encontradaE = false, encontradaS = false;
	for (unsigned i=1;i<this->lab.size();i++){
		if (this->lab[i]=='E'){
			encontradaE=true;
			this->entrada.first=i/this->columnas;
			this->entrada.second=i%this->columnas;
		}
		if (this->lab[i]=='S'){
			encontradaS=true;
			this->salida.first=i/this->columnas;
			this->salida.second=i%this->columnas;
		}
		if (encontradaE && encontradaS)
			break;
	}
}

/**
 * @brief Con esta funcion pintamos la matriz compreta sustituyendo los
 * caracteres almacenados por su version impresa
 * @param[in] sol si queremos pintar el laberinto solucionado lo ponemos a true.
 */
void laberinto::pintar(bool sol) const{
    if (sol){
    	cout << "La entrada es: [" << this->entrada.first << "," << this->entrada.second << "] y la salida es: [" << this->salida.first << "," << this->salida.second << "]" << endl;
    	for (int i=0;i<this->filas;i++){
			for (int j=0;j<this->columnas;j++){
				if (lab[(i*this->columnas)+j]=='E')
					cout << "X";
				else if (lab[(i*this->columnas)+j]=='S')
					cout << "X";
				else if (lab[(i*this->columnas)+j]=='1')
					cout << "#";
				else if (lab[(i*this->columnas)+j]=='0')
					cout << " ";
				else
					cout << lab[(i*this->columnas)+j];
				//cout << lab[(i*this->columnas)+j] << "";
			}
			cout << endl;
		}
    	cout << endl;
    }else{
		for (int i=0;i<this->filas;i++){
			for (int j=0;j<this->columnas;j++)
				cout << lab[(i*this->columnas)+j];
			cout << endl;
		}
		cout << endl;
    }
}

/**
 * @brief con esta pequeña funcion comprobamos si
 * estamos en la pared del laberinto
 */
bool laberinto::esPared(const pos &p){
	bool valor = false;
	if (this->lab[(p.first*this->columnas)+p.second]=='1')
		valor=true;
	return valor;
}

/**
 * @brief con esta pequeña funcion ponemos la casilla
 * como visitada
 */
void laberinto::setVisitada(const pos &p){
	this->vis[(p.first*this->columnas)+p.second]='V';
}

/**
 * @brief con esta pequeña funcion comprobamos si
 * la posicion ha sido visitada
 */
bool laberinto::getVisitada(const pos &p){
	bool valor = false;
	if (this->vis[(p.first*this->columnas)+p.second]=='V')
		valor=true;
	return valor;
}

/**
 * @brief Con esta funcion realizamos la logica
 * de resolucion del laberinto usando una pila
 */
bool laberinto::resolver(){
	int mueve_fila[] = {0,-1,0,1};
	int mueve_columna[] = {-1,0,1,0};
	bool valor = false;
	pos actual;
	this->busca_entrada_salida();
	this->sol.push(this->entrada);
	while (!sol.empty()){
		actual = this->sol.top();
		setVisitada(actual);
		for (int i = 0; i < 4; ++i) {
			pos posible_camino(actual.first + mueve_fila[i],actual.second + mueve_columna[i]);
			if (!getVisitada(posible_camino) && !esPared(posible_camino)){
				this->sol.push(posible_camino);
				break;
			}
		}
		if (this->sol.top()==this->salida){
	    	while (!this->sol.empty()){
	    		this->lab[(this->sol.top().first*this->columnas)+this->sol.top().second]='+';
	    		this->sol.pop();
	    	}
			valor = true;
			break;
		}
		if (actual == this->sol.top())
			sol.pop();
	}
	if (!valor)
		cout << "El laberinto no tiene solución" << endl;
	return valor;
}


//int main(int argc,char *argv[])
void ejecutar_ejercicio3()
{
	laberinto lab1,lab2,lab3;
	if (lab1.leer("../datos/laberinto1.txt")){
		lab1.pintar(false);
		lab1.pintar(lab1.resolver());
	}
	if (lab2.leer("../datos/laberinto2.txt")){
		lab2.pintar(false);
		lab2.pintar(lab2.resolver());
	}
	if (lab3.leer("../datos/laberinto3.txt")){
		lab3.pintar(false);
		lab3.pintar(lab3.resolver());
	}
}
