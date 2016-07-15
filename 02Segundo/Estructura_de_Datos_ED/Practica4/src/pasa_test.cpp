#include <iostream>
#include "conjuntopreguntas.h"
#include "test.h"
#include "solucion.h"
#include <fstream>
#include <cstdlib>
#include <cctype>

using namespace std;

int main(int argc, char * argv[]){
  if (argc!=4 ){
      cout<<"Los parametros son:"<<endl;
      cout<<"1.Dime el nombre del fichero con las preguntas y posibles respuestas"<<endl;
      cout<<"2.Dime el nombre del fichero con las soluciones"<<endl;
      cout<<"3.El numero de preguntas que quieres en el test"<<endl;
      
      return 0;
  }    
  
  ifstream fin(argv[1]);
  if (!fin){
      cout<<"No puedo abrir el fichero "<<argv[1]<<endl;
      return 0;
  }
  
  ConjuntoPreguntas CP;
  
  fin>>CP;
  cout<<"Leidas las preguntas. Numero Total : "<<CP.Size()<<endl;
  fin.close();
  
  Solucion S;
  fin.open(argv[2]);
  if (!fin){
    cout<<"No puedo abrir el fichero"<<argv[2]<<endl;
    return 0;
  } 
  fin>>S;
  cout<<"Leidas las soluciones. Numero de Soluciones:"<<S.Size()<<endl;
  
  srand(time(0));
  int np=atoi(argv[3]);
  int aciertos,fallos;
  int aciertos_totales=0,fallos_totales=0,totales_p=0;
  bool salir=false;
  set<int>escogidas; //almacenamos la preguntas ya escogidas para no repetir en diferentes test
  while (!salir){
        if (CP.Size()==escogidas.size()) // Si ya hemos hecho todas las preguntas
           escogidas.clear(); //vaciamos escogidas
        Test T(CP,np,escogidas); // Se genera el test de forma aleatoria con el conjunto de preguntas y que no estén ya en escogidas
        cout<<"Generado el test..."<<endl;
        aciertos=0; fallos=0;
       //Ahora le pasamos el test al usuario
       cout<<"Contesta a las siguientes preguntas"<<endl;
       Test::iterator it;
       int indice=1;
       for (it=T.begin();it!=T.end();++it,++indice){
	  cout<<"___________________________________________________"<<endl;
	  cout<<indice<<".-"<<*it<<endl;
	  cout<<"Inserta tu respuesta:";
	  char resp;
	  cin>>resp;
	  char contes =S.GetSolucion((*it).GetNumero()); //GetNumero obtiene el codigo de la pregunta
	  if (contes==resp || toupper(resp)==contes){
	    cout<<"Correcta"<<endl;
	    aciertos++;
	  }
	  else{
	    cout<<"Fallo la contestacion correcta es : "<<contes<<endl;
	    fallos++;
	  }
       }
       cout<<"Aciertos: "<<aciertos<< " Fallos: "<<fallos<< " Porcentaje aciertos: "<<aciertos*100.0/np;
       aciertos_totales +=aciertos;
       fallos_totales +=fallos;
       totales_p+=np; //numero de pureguntas totales
       cout<<"¿Quieres hacer otro test?[S/N]"<<endl;
       char seguir_test;
       cin>>seguir_test;
       if (!(seguir_test=='S' || seguir_test=='s') ) salir=true;
       
 } 	 
 cout<<"Resumen:"<<endl;
 cout<<"Numero de preguntas realizadas "<<totales_p<<endl;
 cout<<"Aciertos : "<<aciertos_totales<<endl;
 cout<<"Fallos: "<<fallos_totales<<endl;
 cout<<"Porcentaje aciertos:"<<aciertos_totales*100.0/totales_p<<endl<<endl;
 
       
 
}  