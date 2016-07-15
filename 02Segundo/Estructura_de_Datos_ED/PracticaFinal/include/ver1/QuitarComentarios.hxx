/* 
 * File:   QuitaComentarios.cpp
 *
 * Created on 12 de diciembre de 2015, 15:21
 */
void QuitaComentarios(ifstream &in) {
    char caracter = in.peek();
    while (caracter == '#'|| caracter == '\n') {
        in.ignore(500,'\n');
        caracter = in.peek();
    }
}