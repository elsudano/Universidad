#ifndef AGENT__
#define AGENT__
#include <vector>
#include <string>
#include <iomanip> // esto es para formatear el texto de salida de consola

using namespace std;

// -----------------------------------------------------------
//				class Agent
// -----------------------------------------------------------
class Environment;
class Agent
{
public:
	Agent(){
        bump_ = false;
        dirty_ = false;
        tamTablero = 20; // Cambiando este valor es el tamaño predefinido del tablero
	    mapa = vector<int>(tamTablero*tamTablero, 1); // el tamaño del mapa ha de ser cuadrado
        PosX=PosY=tamTablero/2; // intento posicional el agente en mitad del tablero
        //PosX=PosY=1; // intento posicional el agente en mitad del tablero
        Direccion=0; // comienzo mirando al norte, 0=N, 1=E, 2=S, 3=O.
        P1=P2=P3=P4 = false; // variables para las reglas de producción
        C0=C1=C2=C3=1; // variables para las reglas de producción
//--------------------------------------------------------------------------
	    debug = false; // para hacer un debug en la consola ponerlo a true
        contador=0; // Esto es para un debug
        desde=0; // desde que iteración se imprimira por pantalla
        hasta=2000; // hasta que iteración se imprimira por pantalla
//--------------------------------------------------------------------------
	}

	enum ActionType
	{
	    actFORWARD,
	    actTURN_L,
	    actTURN_R,
	    actSUCK,
	    actIDLE
	};

	void Perceive(const Environment &env);
	ActionType Think();
private:
    int tamTablero;
    bool debug;
	bool bump_, dirty_;
	vector<int> mapa;
	int PosX, PosY, Direccion,contador,desde,hasta;

// quitar estas variables para despues
//--------------------------------------
    int C0,C1,C2,C3;
    bool P1,P2,P3,P4;
//--------------------------------------
    int mod(int a, int b); // funcion para realizar el modulo de numeros negativos
	void ImprimirMapa(char* val); // se usa para hacer un debug por consola
	void ActualizarMapa(int X, int Y); // Sirve para actualizar la posición del agente en el mapa
};

string ActionStr(Agent::ActionType);

#endif
