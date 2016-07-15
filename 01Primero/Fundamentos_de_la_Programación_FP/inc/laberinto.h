#ifndef LABERINTO_H_INCLUDED
#define LABERINTO_H_INCLUDED
#include <vector>

class laberinto{
    private:
        int filas, columnas;
        std::vector<std::vector<char> > lab, resuelto;
        std::vector<int> entrada, salida;
        void busca_entrada_salida();
    public:
        void leer();
        void pintar() const;
        void pintar_resuelto() const;
        bool solucion();
};

#endif // LABERINTO_H_INCLUDED
