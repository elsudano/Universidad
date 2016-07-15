#include <iostream>

using namespace std;

int main() {
  int vector[12];
  vector[0]=1;
  for (int i=1;i<12;i++)
    vector[i]=vector[i-1]+3;
  cout << "Respuesta A: " << endl;
  for (int z=0;z<12;z++)
    cout << "Valor de Vector[" << z << "]=" << vector[z] << endl;
//-----------------------------------------------------------------  
  char letras[]="Hola";
  cout << "Respuesta B: " << endl;
  for (int z=0;z<4;z++)
    cout << "Valor de Vector[" << z << "]=" << letras[z] << endl;
//-----------------------------------------------------------------
  float valor[6];
  valor[0]=1;
  for (int i=1;i<6;i++)
    valor[i]=valor[i-1]/2;
  cout << "Respuesta C: " << endl;
  for (int z=0;z<6;z++)
    cout << "Valor de valor[" << z << "]=" << valor[z] << endl;
}