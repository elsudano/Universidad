#ifndef AGENT__
#define AGENT__
#include <vector>
#include <string>

using namespace std;

// -----------------------------------------------------------
//				class Agent
// -----------------------------------------------------------
class Environment;
class Agent
{
public:
	Agent(){
	    tamTablero = 10; // Cambiando este valor es el tamaño predefinido del tablero
	    mapa = vector<int>(100, 1);
        bump_ = false;
        dirty_ = false;
        PosX=PosY=5;
        Direccion=0;
        P1=P2=P3=P4 = false;
        C0=C1=C2=C3=1;
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
	bool bump_, dirty_;
	vector<int> mapa;
	int PosX, PosY, Direccion;

// quitar estas variables para despues
//--------------------------------------
    int C0,C1,C2,C3;
    bool P1,P2,P3,P4;
//--------------------------------------
    int mod(int a, int b);
	void ImprimirMapa(char* val);
	void ActualizarMapa(int X, int Y, bool muro);
};

string ActionStr(Agent::ActionType);

#endif
