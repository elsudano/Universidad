/*
 * ejercicioListas12.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#include "default.h"
#include "ejercicioListas12.h"

void ejercicioListas12::ejecutar(){
	VDin<int> miVectorDinamico;
	VDin<int> miVectorDinamico2;
    for(int i=0; i<10; i++){
        miVectorDinamico.push_back(i*4+1);
    }

    for(int i=0; i<10; i++){
		miVectorDinamico2.push_back(i*2);
	}
    cout << "Vector Dinamico 1:" <<  endl;
    VDin<int>::iterator i = miVectorDinamico.begin();
    while(i!=miVectorDinamico.end()){
        cout << *i << " ";
        i++;
    }
    cout << "size()=" << miVectorDinamico.size() << " ";
    cout << "empty()=" << miVectorDinamico.empty() << endl;
    cout << "Vector Dinamico 2:" <<  endl;
    i=miVectorDinamico2.begin();
	while(i!=miVectorDinamico2.end()){
		cout << *i << " ";
		i++;
	}
	cout << "size()=" << miVectorDinamico2.size() << " ";
	cout << "empty()=" << miVectorDinamico2.empty() << endl;
	cout << "Igualamos los dos Vectores:" <<  endl;
    miVectorDinamico=miVectorDinamico2;
    cout << "Vector Dinamico 1:" <<  endl;
    i=miVectorDinamico.begin();
    while(i!=miVectorDinamico.end()){
		cout << *i << " ";
		i++;
	}
    cout << endl <<"Quitamos los tres ultimos valores" <<  endl;
    miVectorDinamico.pop_back();
    miVectorDinamico.pop_back();
    miVectorDinamico.pop_back();
    i=miVectorDinamico.begin();
    while(i!=miVectorDinamico.end()){
		cout << *i << " ";
		i++;
	}
    cout << endl << "Y ahora lo Limpiamos" <<  endl;
    miVectorDinamico.clear();
    cout << "Vector Dinamico 1 esta vacio:" <<  miVectorDinamico.empty() << endl;
}
