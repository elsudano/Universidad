//concolor.h
//v0.2
//Pequeña "librería" para mostrar mensajes con color en la consola.
//Funciona en Windows y Linux.

//Uso: cout<<color<<"mensaje";
//Para compilar en Windows, usar -DWIN
//Para compilar en Linux, usar -DLINUX

/*
Copying and distribution of this file, with or without modification,
are permitted in any medium without royalty provided the copyright
notice and this notice are preserved. This file is offered as-is,
without any warranty.
*/

//Creado por Johny
//http://www.johny65corp.com.ar

#ifndef _CONCOLOR_
#define _CONCOLOR_

#include <iostream>

#ifdef WIN

#include <windows.h>

static bool normalizado = false;
static HANDLE hStdout;
static WORD normalc;

void normalizar()
{
	if (!normalizado){
		hStdout = GetStdHandle(STD_OUTPUT_HANDLE);
		CONSOLE_SCREEN_BUFFER_INFO prop;
		GetConsoleScreenBufferInfo(hStdout, &prop);
		normalc = prop.wAttributes;
		normalizado = true;
	}
}

inline std::ostream& normal(std::ostream &s)
{
	normalizar();
	SetConsoleTextAttribute(hStdout, normalc);
	return s;
}

inline std::ostream& normalb(std::ostream &s)
{
	normalizar();
	SetConsoleTextAttribute(hStdout, normalc);
	return s;
}

inline std::ostream& black(std::ostream &s)
{
	normalizar();
	SetConsoleTextAttribute(hStdout, FOREGROUND_INTENSITY);
	return s;
}

inline std::ostream& blackb(std::ostream &s)
{
	normalizar();
	SetConsoleTextAttribute(hStdout, 0);
	return s;
}

inline std::ostream& blueb(std::ostream &s)
{
	normalizar();
	SetConsoleTextAttribute(hStdout, FOREGROUND_BLUE);
	return s;
}

inline std::ostream& blue(std::ostream &s)
{
	normalizar();
	SetConsoleTextAttribute(hStdout, FOREGROUND_BLUE | FOREGROUND_INTENSITY);
	return s;
}

inline std::ostream& greenb(std::ostream &s)
{
	normalizar();
	SetConsoleTextAttribute(hStdout, FOREGROUND_GREEN);
	return s;
}

inline std::ostream& green(std::ostream &s)
{
	normalizar();
	SetConsoleTextAttribute(hStdout, FOREGROUND_GREEN | FOREGROUND_INTENSITY);
	return s;
}

inline std::ostream& cyanb(std::ostream &s)
{
	normalizar();
	SetConsoleTextAttribute(hStdout, FOREGROUND_GREEN | FOREGROUND_BLUE);
	return s;
}

inline std::ostream& cyan(std::ostream &s)
{
	normalizar();
	SetConsoleTextAttribute(hStdout, FOREGROUND_GREEN | FOREGROUND_BLUE | FOREGROUND_INTENSITY);
	return s;
}

inline std::ostream& redb(std::ostream &s)
{
	normalizar();
	SetConsoleTextAttribute(hStdout, FOREGROUND_RED);
	return s;
}

inline std::ostream& red(std::ostream &s)
{
	normalizar();
	SetConsoleTextAttribute(hStdout, FOREGROUND_RED | FOREGROUND_INTENSITY);
	return s;
}

inline std::ostream& magentab(std::ostream &s)
{
	normalizar();
	SetConsoleTextAttribute(hStdout, FOREGROUND_RED | FOREGROUND_BLUE);
	return s;
}

inline std::ostream& magenta(std::ostream &s)
{
	normalizar();
	SetConsoleTextAttribute(hStdout, FOREGROUND_RED | FOREGROUND_BLUE | FOREGROUND_INTENSITY);
	return s;
}

inline std::ostream& yellowb(std::ostream &s)
{
	normalizar();
	SetConsoleTextAttribute(hStdout, FOREGROUND_RED | FOREGROUND_GREEN);
	return s;
}

inline std::ostream& yellow(std::ostream &s)
{
	normalizar();
	SetConsoleTextAttribute(hStdout, FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_INTENSITY);
	return s;
}

inline std::ostream& whiteb(std::ostream &s)
{
	normalizar();
	SetConsoleTextAttribute(hStdout, FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE);
	return s;
}

inline std::ostream& white(std::ostream &s)
{
	normalizar();
	SetConsoleTextAttribute(hStdout, FOREGROUND_RED | FOREGROUND_GREEN | FOREGROUND_BLUE | FOREGROUND_INTENSITY);
	return s;
}

struct color {
	color(WORD attribute):m_color(attribute){};
	WORD m_color;
};

template <class _Elem, class _Traits> std::basic_ostream<_Elem,_Traits>& operator<<(std::basic_ostream<_Elem,_Traits>& i, color& c)
{
	SetConsoleTextAttribute(hStdout, c.m_color);
	return i;
}

//Basado en código de Vincent Godin


#elif defined LINUX

char black[] = { 0x1b, '[', '0', ';', '3', '0', 'm', 0 };
char red[] = { 0x1b, '[', '0', ';', '3', '1', 'm', 0 };
char green[] = { 0x1b, '[', '0', ';', '3', '2', 'm', 0 };
char yellow[] = { 0x1b, '[', '0', ';', '3', '3', 'm', 0 };
char blue[] = { 0x1b, '[', '0', ';', '3', '4', 'm', 0 };
char magenta[] = { 0x1b, '[', '0', ';', '3', '5', 'm', 0 };
char cyan[] = { 0x1b, '[', '0', ';', '3', '6', 'm', 0 };
char white[] = { 0x1b, '[', '0', ';', '3', '7', 'm', 0 };
char normal[] = { 0x1b, '[', '0', ';', '3', '9', 'm', 0 };

char blackb[] = { 0x1b, '[', '1', ';', '3', '0', 'm', 0 };
char redb[] = { 0x1b, '[', '1', ';', '3', '1', 'm', 0 };
char greenb[] = { 0x1b, '[', '1', ';', '3', '2', 'm', 0 };
char yellowb[] = { 0x1b, '[', '1', ';', '3', '3', 'm', 0 };
char blueb[] = { 0x1b, '[', '1', ';', '3', '4', 'm', 0 };
char magentab[] = { 0x1b, '[', '1', ';', '3', '5', 'm', 0 };
char cyanb[] = { 0x1b, '[', '1', ';', '3', '6', 'm', 0 };
char whiteb[] = { 0x1b, '[', '1', ';', '3', '7', 'm', 0 };
char normalb[] = { 0x1b, '[', '1', ';', '3', '9', 'm', 0 };

#else
#error Falta definir sistema operativo! Definir WIN o LINUX
#endif
#endif
