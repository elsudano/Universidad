#include <iostream>

using namespace std;

int main (){
  /**
  int a = 5, *p;
  a = *p * a;
  if (a == *p)
  cout << "a es igual a *p" << endl;
  else
  cout << "a es diferente a *p" << endl;
/**----------------------------------------------**
  int a = 5, *p;
  cout << *p << endl;
  *p = *p * a;
  cout << *p << endl;
  if (a == *p)
  cout << "a es igual a *p" << endl;
  else
  cout << "a es diferente a *p" << endl;
/**----------------------------------------------**/
  int a = 5, *p = &a;
  cout << *p << endl;
  *p = *p * a;
  cout << *p << endl;
  cout << a << endl;
  if (a == *p)
  cout << "a es igual a *p" << endl;
  else
  cout << "a es diferente a *p" << endl;
/**----------------------------------------------**
  int a = 5, *p = &a, **p2 = &p;
  cout << **p2 << endl;
  **p2 = *p + (**p2 / a);
  cout << **p2 << endl;
  cout << *p << endl;
  *p = a+1;
  cout << *p << endl;
  cout << a << endl;
  cout << **p2 << endl;
  a = **p2 / 2;
  cout << "a es igual a: " << a << endl;**/
}