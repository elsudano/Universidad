#include "agent.h"
#include "environment.h"
#include <iostream>
#include <cstdlib>
#include <vector>
#include <utility>

using namespace std;

// -----------------------------------------------------------
Agent::ActionType Agent::Think()
{
    //debug = true;
    contador++;
    desde=1999; // desde que iteración se imprimira por pantalla
    hasta=2000; // hasta que iteración se imprimira por pantalla
	ActionType accion;
	if (debug and contador>desde and contador<=hasta)
        ImprimirMapa("P");

    // Con estas lineas lo que hago es cambiar la direccion de la casilla
    // en el mismo sentido que apunta la direccion del agente
    // de esa manera las reglas de producción solo tengo que
    // mirar la direccion 0
    if (Direccion==0){
        C0 = mapa[PosX+((PosY-1)*tamTablero)];
        C1 = mapa[(PosX+1)+(PosY*tamTablero)];
        C2 = mapa[PosX+((PosY+1)*tamTablero)];
        C3 = mapa[(PosX-1)+(PosY*tamTablero)];
    }else if (Direccion==1){
        C0 = mapa[(PosX+1)+(PosY*tamTablero)];
        C1 = mapa[PosX+((PosY+1)*tamTablero)];
        C2 = mapa[(PosX-1)+(PosY*tamTablero)];
        C3 = mapa[PosX+((PosY-1)*tamTablero)];
    }else if (Direccion==2){
        C0 = mapa[PosX+((PosY+1)*tamTablero)];
        C1 = mapa[(PosX-1)+(PosY*tamTablero)];
        C2 = mapa[PosX+((PosY-1)*tamTablero)];
        C3 = mapa[(PosX+1)+(PosY*tamTablero)];
    }else if (Direccion==3){
        C0 = mapa[(PosX-1)+(PosY*tamTablero)];
        C1 = mapa[PosX+((PosY-1)*tamTablero)];
        C2 = mapa[(PosX+1)+(PosY*tamTablero)];
        C3 = mapa[PosX+((PosY+1)*tamTablero)];
    }

    // Con todos estos if defino posibles situaciones del
    // mapa para decidir que tengo que hacer en cada una
    P1=P2=P3=P4 = false;
    if (C0==0 and C1>=0 and C2>=0 and C3>=0){
        P1 = true;
    }
    if (C0>=0 and C1==0 and C2>=0 and C3>=0){
        P2 = true;
    }
    if (C0>=0 and C1>=0 and C2==0 and C3>=0){
        P3 = true;
    }
    if (C0>=0 and C1>=0 and C2>=0 and C3==0){
        P4 = true;
    }

// Reglas de Producción principales:}else if (P2){
// 1ra.-  Lo primero que hago es limpiar si esta sucio
// 2da.-  Compruebo si he chocado con algun obstaculo
// 2da.A- Si he chocado con algun obstaculo actualizo el mapa y retrocedo hasta la pocisión áctual
// 3ra.-  Como no hay obstaculo ni basura Actualizo el mapa
// 3ra.A- Compruebo en la posición en la que estoy (P?) si es alguna de las predefinidas
// 3ra.B- Si es alguna de las que tengo predefinidas compruebo cual es el camino menos transitado.
// 3ra.C- Según el resultado giro el agente o hago que avance en la direccion en la que apunta.
    if (dirty_){
        ActualizarMapa(PosX,PosY);
        accion = actSUCK;
    }else if (bump_){
        ActualizarMapa(PosX,PosY);
        if (C1>C3){
            if (debug and contador>desde and contador<=hasta)
                cout << "Regla BUMPER C1>C3 and C1!=0 con if" << endl;
            accion = actTURN_L;
            Direccion = mod(Direccion-1,4);
        }else{
            if (debug and contador>desde and contador<=hasta)
                cout << "Regla BUMPER C1>C3 and C1!=0 con else" << endl;
            accion = actTURN_R;
            Direccion = mod(Direccion+1,4);
        }
    }else{
        if (P1 and P2 and !P3 and P4){
            if (debug and contador>desde and contador<=hasta)
                cout << "Regla P1 and P2 and !P3 and P4" << endl;
            accion = actTURN_R;
            Direccion = mod(Direccion+1,4);
        }else if (!P1 and P2 and P3 and P4){
            if (debug and contador>desde and contador<=hasta)
                cout << "Regla !P1 and P2 and P3 and P4" << endl;
            // Actualizo la posición DONDE ESTOY como visitada
            ActualizarMapa(PosX,PosY);
            accion = actFORWARD;
        }else if (P1 and P2 and !P4){
            if (debug and contador>desde and contador<=hasta)
                cout << "Regla P1 and P2 and !P4" << endl;
            accion = actTURN_L;
            Direccion = mod(Direccion-1,4);
        }else if (P1 and !P2 and P4){
            if (debug and contador>desde and contador<=hasta)
                cout << "Regla P1 and !P2 and P4" << endl;
            accion = actTURN_R;
            Direccion = mod(Direccion+1,4);
        }else if (!P1 and !P2 and P3 and P4){
            if (C0>C1 and C1!=0){
                if (debug and contador>desde and contador<=hasta)
                    cout << "Regla !P1 and !P2 and P3 and P4 con if" << endl;
                accion = actTURN_R;
                Direccion = mod(Direccion+1,4);
            }else{
                if (debug and contador>desde and contador<=hasta)
                    cout << "Regla !P1 and !P2 and P3 and P4 con else" << endl;
                // Actualizo la posición DONDE ESTOY como visitada
                ActualizarMapa(PosX,PosY);
                accion = actFORWARD;
            }
        }else if (!P1 and P2 and P3 and !P4){
            if (C0>C3 and C1!=0){
                if (debug and contador>desde and contador<=hasta)
                    cout << "Regla !P1 and P2 and P3 and !P4 con if" << endl;
                accion = actTURN_L;
                Direccion = mod(Direccion-1,4);
            }else{
                if (debug and contador>desde and contador<=hasta)
                    cout << "Regla !P1 and P2 and P3 and !P4 con else" << endl;
                // Actualizo la posición DONDE ESTOY como visitada
                ActualizarMapa(PosX,PosY);
                accion = actFORWARD;
            }
        }else if (P1 and !P2 and !P3 and !P4){
            //if (C1-C3>sensibilidad and C1!=0){
            if (C1>C3 and C1!=0){
                if (debug and contador>desde and contador<=hasta)
                    cout << "Regla P1 and !P2 and !P3 and !P4 con if" << endl;
                accion = actTURN_L;
                Direccion = mod(Direccion-1,4);
            }else{
                if (debug and contador>desde and contador<=hasta)
                    cout << "Regla P1 and !P2 and !P3 and !P4 con else" << endl;
                accion = actTURN_R;
                Direccion = mod(Direccion+1,4);
            }
        }else if (!P1 and P2 and !P3 and !P4){
            if (C0>C3 and C3!=0){
                if (debug and contador>desde and contador<=hasta)
                    cout << "Regla !P1 and P2 and !P3 and !P4 con if" << endl;
                accion = actTURN_L;
                Direccion = mod(Direccion-1,4);
            }else{
                if (debug and contador>desde and contador<=hasta)
                    cout << "Regla !P1 and P2 and !P3 and !P4 con else" << endl;
                // Actualizo la posición DONDE ESTOY como visitada
                ActualizarMapa(PosX,PosY);
                accion = actFORWARD;
            }
        }else if (!P1 and !P2 and !P3 and P4){
            if (C0>C1 and C1!=0){
                if (debug and contador>desde and contador<=hasta)
                    cout << "Regla !P1 and !P2 and !P3 and P4 con if" << endl;
                accion = actTURN_R;
                Direccion = mod(Direccion+1,4);
            }else{
                if (debug and contador>desde and contador<=hasta)
                    cout << "Regla !P1 and !P2 and !P3 and P4 con else" << endl;
                // Actualizo la posición DONDE ESTOY como visitada
                ActualizarMapa(PosX,PosY);
                accion = actFORWARD;
            }
        }else if (C0-C1>sensibilidad and C1!=0){
            if (debug and contador>desde and contador<=hasta)
                cout << "Regla C0>C1 and C1!=0" << endl;
            accion = actTURN_R;
            Direccion = mod(Direccion+1,4);
        }else{
            // Actualizo la posición DONDE ESTOY como visitada
            ActualizarMapa(PosX,PosY);
            accion = actFORWARD;
        }

        // Esto sirve para actualizar la posición de donde me encuentro
        if (Direccion==0 and accion==actFORWARD){
            PosY --;
        }else if (Direccion==1 and accion==actFORWARD){
            PosX ++;
        }else if (Direccion==2 and accion==actFORWARD){
            PosY ++;
        }else if (Direccion==3 and accion==actFORWARD){
            PosX --;
        }
    }

    if (debug and contador>desde and contador<=hasta){
        cout << "Cas0: " << C0 << " Cas1: " << C1 << " Cas2: " << C2 << " Cas3: " << C3 << endl;
        ImprimirMapa("F");
    }
	return accion;
}
//------------------------------------------------------------
void Agent::ImprimirMapa(char* val){
    if (val=="P"){
        cout << "------Valores ANTES de las reglas de Producción-------------" << endl;
        cout << "Dirección: " << Direccion << endl;
        cout << "Posición X/Y: " << PosX << "/" << PosY << endl;
        cout << "Bump: " << bump_ << endl;
        cout << "Dirty: " << dirty_ << endl;
        cout << "Contador: " << contador << endl;
        cout << "P01: " << P1 << " P02: " << P2 << " P03: " << P3 << " P04: " << P4 << endl;
        cout << "------------------------------------------------------------" << endl;
        cout << endl;
    }
        for (int Y=0; Y < tamTablero; ++Y){
            for (int X=0; X < tamTablero; ++X){
                cout << setw(2) << mapa[X+(Y*tamTablero)];
            }
            cout << endl;
        }
        cout << endl;
    if (val=="F"){
        cout << "------Valores DESPUES de las reglas de Producción------------" << endl;
        cout << "Dirección: " << Direccion << endl;
        cout << "Posición X/Y: " << PosX << "/" << PosY << endl;
        cout << "Bump: " << bump_ << endl;
        cout << "Dirty: " << dirty_ << endl;
        cout << "Contador: " << contador << endl;
        cout << "P01: " << P1 << " P02: " << P2 << " P03: " << P3 << " P04: " << P4 << endl;
        cout << "------------------------------------------------------------" << endl;
        cout << endl;
    }
}
//------------------------------------------------------------
void Agent::ActualizarMapa(int X, int Y){
    if (bump_ and Direccion==0){
        PosY++;
        mapa[X+(Y*tamTablero)]=0;
    }else if (bump_ and Direccion==1){
        PosX--;
        mapa[X+(Y*tamTablero)]=0;
    }else if (bump_ and Direccion==2){
        PosY--;
        mapa[X+(Y*tamTablero)]=0;
    }else if (bump_ and Direccion==3){
        PosX++;
        mapa[X+(Y*tamTablero)]=0;
    }else if (dirty_){
        ;
    }else{
        mapa[X+(Y*tamTablero)]++;
    }
}
//------------------------------------------------------------
 // esta funcion la implemento y no uso "%" por que el módulo
 // de C no funciona como tal para números negativos
int Agent::mod(int a, int b){
    int r = a % b;
    return r < 0 ? r + b : r;
}
// -----------------------------------------------------------
void Agent::Perceive(const Environment &env)
{
	bump_ = env.isJustBump();
	dirty_ = env.isCurrentPosDirty();
}
// -----------------------------------------------------------
string ActionStr(Agent::ActionType accion)
{
	switch (accion)
	{
	case Agent::actFORWARD: return "FORWARD";
	case Agent::actTURN_L: return "TURN LEFT";
	case Agent::actTURN_R: return "TURN RIGHT";
	case Agent::actSUCK: return "SUCK";
	case Agent::actIDLE: return "IDLE";
	default: return "???";
	}
}
