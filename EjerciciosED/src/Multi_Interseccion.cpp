// multiset::insert (C++98)
#include <iostream>
#include <set>

using namespace std;

void mostrar(const multiset<int> &datos){
	multiset<int>::iterator datos_it = datos.begin();
	cout << "Datos: ";
	while (datos_it!=datos.end()){
		cout << *datos_it << " ";
		datos_it++;
	}
	cout << endl;
}

multiset<int> multi_interseccion(const multiset<int> &m1 , const multiset<int> &m2){
	multiset<int> aux;
	multiset<int>::iterator aux_it = aux.begin();
	multiset<int>::const_iterator m1_it = m1.begin();
	multiset<int>::const_iterator m2_it = m2.begin();
	while (m1_it!=m1.end()){
		m2_it = m2.begin();
		while (m2_it!=m2.end()){
			if ( (*m1_it==*m2_it) && (aux.count(*m1_it)==0) ){
				if ((m1.count(*m1_it)<m2.count(*m1_it)))
					for (unsigned d=0;d<m1.count(*m1_it);d++)
						aux.insert(aux_it,*m1_it);
				else
					for (unsigned d=0;d<m2.count(*m1_it);d++)
						aux.insert(aux_it,*m2_it);
			}
			m2_it++;
		}
		m1_it++;
	}
	return aux;
}

int main(int argc, char *argv[]) {
	int miInts1[] = {2,2,3,3};
	multiset<int> m1(miInts1,miInts1+4);
	mostrar(m1);
	int miInts2[] = {1,2,3,3,3,4};
	multiset<int> m2(miInts2,miInts2+6);
	mostrar(m2);
	multiset<int> resultado = multi_interseccion(m1,m2);
	mostrar(resultado);
	return 0;
}
