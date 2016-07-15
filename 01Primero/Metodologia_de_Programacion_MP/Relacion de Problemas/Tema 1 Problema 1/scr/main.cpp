#include <iostream>

using namespace std;

int main() {
  int numeros[100];
  numeros[0]=0;
  int mayor=0;
  int count=0;
  int entrada;
  cout << "Por favor introduzca un numero diferente de cero." << endl;
  cin >> entrada;
  while (entrada!=0 && count<100){
    numeros[count]=entrada;
    if (entrada>numeros[mayor])
      mayor=count;
    cin >> entrada;
    count++;
  }
  cout << "Has introducido " << count << " numeros " << endl
       << "de los cuales el mayor es " << numeros[mayor] << endl
       << "que ha sido introducido en la posicion " << mayor+1 << endl;
}