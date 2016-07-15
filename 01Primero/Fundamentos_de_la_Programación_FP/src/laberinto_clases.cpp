#include <iostream>
#include <stdlib.h>
#include <string>
#include <cmath>
#include "laberinto.h"

using namespace std;


// Con esta funcion leemos el fichero txt y llenamos un
// vector de vectores de char donde estan todos los
// caracteres del laberinto, la matriz esta en memoria.
void laberinto::leer(){
    (*this).lab.clear();
    cin >> (*this).filas >> (*this).columnas;
    for (int c=0; c<=(*this).filas; c++) {
        vector<char> columna;
        for (int c2=0; c2<=(*this).columnas; c2++) {
            char car=cin.get();
            if (car!='\n')
                columna.push_back(car);
        }
        (*this).lab.push_back(columna);
    }
}

// Con esta funcion pintamos la matriz compreta sustituyendo los
// caracteres almacenados por su version impresa, en esta ocaci�n
// no necesitamos de filas y columnas que nos ayuden.
void laberinto::pintar() const{
    for (int c=0;c<(*this).lab.size();c++){
        cout << " ";
        for (int c2=0;c2<(*this).lab.at(c).size();c2++)
            if ((*this).lab.at(c).at(c2)=='p')
                cout << "#";
            else if ((*this).lab.at(c).at(c2)=='l')
                cout << " ";
            else if ((*this).lab.at(c).at(c2)=='e')
                cout << "e";
            else if ((*this).lab.at(c).at(c2)=='s')
                cout << "s";
            else
                cout << (*this).lab.at(c).at(c2);
        cout << endl;
    }
}

void laberinto::pintar_resuelto() const{
    for (int c=0;c<(*this).resuelto.size();c++){
        cout << " ";
        for (int c2=0;c2<(*this).resuelto.at(c).size();c2++)
            if ((*this).resuelto.at(c).at(c2)=='p')
                cout << "#";
            else if ((*this).resuelto.at(c).at(c2)=='l')
                cout << " ";
            else if ((*this).resuelto.at(c).at(c2)=='e')
                cout << "e";
            else if ((*this).resuelto.at(c).at(c2)=='s')
                cout << "s";
            else
                cout << (*this).resuelto.at(c).at(c2);
        cout << endl;
    }
}

// Con esta funcion buscamos un caracter en particular,
// y la funci�n nos devuelve en que fila y en que columna
// se encuentra dicho caracter.
void laberinto::busca_entrada_salida(){
    bool encontrada_entrada=false;
    bool encontrada_salida=false;
    for (int c=0; (!encontrada_entrada || !encontrada_salida) && c<(*this).lab.size(); ++c){
        for (int c2=0; (!encontrada_entrada || !encontrada_salida) && c2<(*this).lab.at(c).size(); c2++){
            if ((*this).lab.at(c).at(c2)=='e') {
                encontrada_entrada = true;
                (*this).entrada.push_back(c);
                (*this).entrada.push_back(c2);
            }
            if ((*this).lab.at(c).at(c2)=='s') {
                encontrada_salida = true;
                (*this).salida.push_back(c);
                (*this).salida.push_back(c2);
            }
        }
    }
}

bool laberinto::solucion(){
    bool sin_solucion=true;
    vector<vector<bool> > vis;
    vector<vector<int> > sol;
    for (int v=0; v<(*this).filas; v++){
        vector<bool> columna;
        for (int w=0; w<(*this).columnas; w++)
            columna.push_back(false);
        vis.push_back(columna);
    }
    busca_entrada_salida();
    sol.push_back((*this).entrada);
    vis[sol.at(0).at(0)][sol.at(0).at(1)]=true;
    int contador=1;
    while (sol.size()>0 && sol.at(sol.size()-1)!=(*this).salida){
        vector<int> ultima_pos=sol.at(sol.size()-1);
        if((*this).lab[ultima_pos.at(0)][ultima_pos.at(1)-1]!='p' && vis[ultima_pos.at(0)][ultima_pos.at(1)-1]==false){
            vis[ultima_pos.at(0)][ultima_pos.at(1)-1]=true;
            ultima_pos.at(1)=ultima_pos.at(1)-1;
            sol.push_back(ultima_pos);
        }else if((*this).lab[ultima_pos.at(0)][ultima_pos.at(1)+1]!='p' && vis[ultima_pos.at(0)][ultima_pos.at(1)+1]==false){
            vis[ultima_pos.at(0)][ultima_pos.at(1)+1]=true;
            ultima_pos.at(1)=ultima_pos.at(1)+1;
            sol.push_back(ultima_pos);
        }else if((*this).lab[ultima_pos.at(0)-1][ultima_pos.at(1)]!='p' && vis[ultima_pos.at(0)-1][ultima_pos.at(1)]==false){
            vis[ultima_pos.at(0)-1][ultima_pos.at(1)]=true;
            ultima_pos.at(0)=ultima_pos.at(0)-1;
            sol.push_back(ultima_pos);
        }else if((*this).lab[ultima_pos.at(0)+1][ultima_pos.at(1)]!='p' && vis[ultima_pos.at(0)+1][ultima_pos.at(1)]==false){
            vis[ultima_pos.at(0)+1][ultima_pos.at(1)]=true;
            ultima_pos.at(0)=ultima_pos.at(0)+1;
            sol.push_back(ultima_pos);
        }else
            sol.pop_back();
    }
    (*this).resuelto=(*this).lab;
    if (sol.size()>0){
        for (int j=0; j<sol.size(); j++)
            if (j>0 && j<sol.size()-1)
                (*this).resuelto[sol.at(j).at(0)][sol.at(j).at(1)]='.';
    }else
        sin_solucion=false;
    return sin_solucion;
}

int main(){
    laberinto objeto_laberinto;
    objeto_laberinto.leer();
    cout << endl << " Leyendo datos... " << endl;
    cout << endl << " Laberinto sin Resolver:" << endl;
    objeto_laberinto.pintar();
    cout << endl << " Laberinto Resuelto:" << endl;
    if (objeto_laberinto.solucion())
        objeto_laberinto.pintar_resuelto();
    else
        cout << " El Laberinto no tiene soluci�n " << endl;
    return 0;
}
