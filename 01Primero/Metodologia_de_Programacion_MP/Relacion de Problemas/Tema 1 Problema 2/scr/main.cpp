#include <iostream>

using namespace std;

int main() {
  int cuenta_letras[26]={0};
  char letra='0';
  while (letra!='#'){
    for (int count=0;count<26;count++)
      if (letra==char(97+count)||letra==char(65+count))
	cuenta_letras[count]=cuenta_letras[count]++;
    letra=cin.get();
  }
  for (int count=0;count<26;count++)
    cout << "La letra " << char(97+count) << " sale " << cuenta_letras[count] << " veces en el texto" << endl;
}