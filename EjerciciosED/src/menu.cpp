/*
 * menu.cpp
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */
#include "menu.h"

/**
 * @brief Constructor primitivo
 * @param[in] pItem sera el nombre de la Opcion y su identificador unico.
 * @param[in] pValor sera el valor numerico que se dara al seleccionar esta opcion
 */
itemMenu::itemMenu(nombreItem pNombre, valorItem pValor){
	this->miNombre = pNombre;
	this->miValor = pValor;
}

/**
 * @brief Muestra el nombre de la Opción
 * @return item valor del nombre.
 */
nombreItem itemMenu::getNombre(){
	return this->miNombre;
}

/**
 * @brief Devuelve el valor de
 */
valorItem itemMenu::getValor(){
	return this->miValor;
}

/**
 * @brief Muestra el valor de la Opción
 * @return valor numero unsigned que tiene la opción.
 */
valorItem itemMenu::getOpcion(const nombreItem pCual){
	listaItems::iterator it;
	for (it=this->miListaOpciones.begin();it != this->miListaOpciones.end();it++)
		if (it->first==pCual)
			break;
	return it->second;
}

/**
 * @brief Devuelve la lista completa de opciones de un menu
 */
listaItems itemMenu::getOpciones(){
	return this->miListaOpciones;
}

/**
 * @brief Adigna el nombre al Menu
 */
void itemMenu::setNombre(nombreItem &pNombre){
	this->miNombre = pNombre;
}

/**
 * @brief Asigna el valor de la Opción
 */
void itemMenu::setOpcion(Item &pOpcion){
	this->miListaOpciones.push_back(pOpcion);
}

//----------------------------------------------------------------------------------
/*
 *  @brief constructor primitivo.
 */
menuTexto::menuTexto(){
	itemMenu miItemMenu("Salir",0);
	this->miBarraMenu.push_back(miItemMenu);
}

/*
 * @brief Añade un Submenu al Menu.
 * @param[in] pMenu es el nombre del menu que vamos a añadir.
 * @param[in] pValor es el valor unsigned que devolvera el menu al seleccionarlo.
 */
void menuTexto::addMenu(nombreItem pMenu, valorItem pValor){
	bool encontrado = false;
	unsigned count = 0;
	while (count<miBarraMenu.size()){
		if (miBarraMenu[count].getNombre()==pMenu){
			encontrado = true;
			break;
		}
		count++;
	}
	if (!encontrado){
		itemMenu miItemMenu(pMenu,pValor);
		vector<itemMenu>::iterator it = this->miBarraMenu.begin();
		this->miBarraMenu.insert(it,miItemMenu);
		//this->miBarraMenu.push_back(miItemMenu);
	}
}

/*
 * @brief Añade un Submenu al Menu.
 * @param[in] pMenu es el nombre del menu al que vamos a añadir el submenu.
 * @param[in] pSubMenu es el nombre del submenu que añadiremos.
 * @param[in] pValor es el valor unsigned que devolvera el submenu al seleccionarla.
 */
void menuTexto::addSubMenu(nombreItem pMenu, nombreItem pSubMenu, valorItem pValor){
	Item miItem(pSubMenu,pValor);
	unsigned count = 0;
	while (count<miBarraMenu.size()){
		if (miBarraMenu[count].getNombre()==pMenu)
			miBarraMenu[count].setOpcion(miItem);
		count++;
	}
}

/*
 * @brief Muestra todo el Menu por Pantalla
 */
void menuTexto::mostrarMenus(){
//	BarraMenu::iterator it;
//	for (it = this->miBarraMenu.begin();it != this->miBarraMenu.end();it++)
//		cout << (*it).getValor() << " Menu: " << (*it).getNombre() << endl;
	for (valorItem i=0;i<this->miBarraMenu.size();i++)
			cout << i << ". " << this->miBarraMenu[i].getNombre() << endl;
}

/*
 * @brief Muesta todos los submenu de un menu dado.
 */
void menuTexto::mostrarSubMenus(itemMenu &pMenu){
	listaItems::iterator it;
	if (!pMenu.getOpciones().empty())
		for (it=pMenu.getOpciones().begin();it != pMenu.getOpciones().end();it++)
			cout << (*it).second << " SubMenu: " << (*it).first  << endl;
}

/*
 * @brief Esto devuelve el valor del menu que el usuario seleccione.
 */
int menuTexto::seleccionar() {
	this->mostrarMenus();
	int opcion = -1;
	cin >> opcion;
	if (!this->miBarraMenu[opcion].getOpciones().empty()) {
		cout << "Por favor elija: " << endl;
		mostrarSubMenus(miBarraMenu[opcion]);
		cin >> opcion;
	}else
		opcion = this->miBarraMenu[opcion].getValor();
	return opcion;
}

///*
// * @brief constructor de copia
// * @param[in] menu, menu a copiar
// */
//menuTexto::menuTexto(const menuTexto &pMenu){
//
//}
//
///*
// * @brief Consulta/Inserta un menu.
// */
//valorItem & menuTexto::operator[](const nombreItem &pMenu){
//	// falta implementar para cuando se quiere introducir una lista de items de opciones
//	bool aux = false;
//	itemMenu miMenu;
//	for (itemMenu vMenu : this->miBarraMenu)
//		if (vMenu.getNombre()==pMenu){
//			aux = true;
//			miMenu=vMenu;
//			break;
//		}
//	if (!aux)
//		this->miBarraMenu.push_back(miMenu);
//	return miMenu.getValor();
//}
//
///*
// * @brief Consulta  un menu.
// */
//const valorItem &menuTexto::operator[](const nombreItem &pMenu) const{
//	listaItems aux;
//	for (itemMenu vMenu : miBarraMenu)
//		if (vMenu.getNombre()==pMenu)
//			aux = vMenu.getOpciones();
//	return aux;
//}

///*
// * @brief operador de asignación
// */
//menuTexto &menuTexto::operator=( const menuTexto &pBarra){
//	return pBarra;
//}

/*
 * @brief numero de menus
 * @post  No se modifica el menuTexto.
 */
int menuTexto::size() const{
	return this->miBarraMenu.size();
}

/*
 * @brief vacia
 * Chequea si el menu esta vacío.
 */
bool menuTexto::empty() const{
	return this->miBarraMenu.empty();
}
