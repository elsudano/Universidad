/* 
 * File:   QuitaComentarios.cpp
 * Author: Carlos de la Torre
 *
 * Created on 12 de diciembre de 2015, 15:21
 */

void QuitaComentarios(ifstream &in) {
    char caracter;
    int contador = 0,tamanio;
    in.seekg(0,in.end);
    tamanio = in.tellg();
    in.seekg(0, in.beg);
    while (contador < tamanio-1) {
        caracter = in.peek();
        contador = in.tellg();
        if (caracter == '#')
            in.ignore(500,'\n');
        else
            in.seekg(contador+1, in.beg);
    }
    in.seekg(0, in.beg);
}