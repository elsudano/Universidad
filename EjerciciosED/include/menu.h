/*
 * menu.h
 *
 *  Created on: 17/11/2013
 *      Author: Carlos de la Torre
 */

#ifndef MENU_H_
#define MENU_H_
#include "default.h"
#include <vector>
#include <map>

using namespace std;

typedef string nombreItem;
typedef unsigned valorItem;
typedef pair<nombreItem,valorItem> Item;
typedef vector<Item> listaItems;

class itemMenu{
	private:
		listaItems miListaOpciones;
		nombreItem miNombre;
		valorItem miValor;

	public:
		/**
		 * @brief Constructor primitivo
		 */
		itemMenu(nombreItem pNombre, valorItem pValor);

		/**
		 * @brief Muestra el nombre del Menú
		 */
		nombreItem getNombre();

		/**
		 * @brief Muestra el valor que devuelve el menu al seleccionarlo.
		 */
		valorItem getValor();

		/**
		 * @brief Deveulve el valor de la Opción del Menú
		 * @return tipoitemOpcion es un pair con el nombre de la opcion y el valor de la misma.
		 */
		valorItem getOpcion(const nombreItem pCual);

		/**
		* @brief Deveulve todas las opciones de un menu
		* @return listaItems es un map con todos los items que tiene el menu.
		*/
		listaItems getOpciones();

		/**
		 * @brief Adigna el nombre de la Opción
		 */
		void setNombre(nombreItem &pNombre);

		/**
		 * @brief Asigna el valor que devolvera el menu al seleccionarlo.
		 */
		void setValor(valorItem &pValor);

		/**
		 * @brief Asigna el valor de la Opción
		 */
		void setOpcion(Item &pOpcion);

};

typedef vector<itemMenu> BarraMenu;

class menuTexto{
	private:
		BarraMenu miBarraMenu;
//		vector<itemMenu>::iterator it;

		/*
		 * @brief Muestra el menu por pantalla.
		 */
		void mostrarMenus();

		/*
		 * @brief Muesta todos los submenu de un menu dado.
		 */
		void mostrarSubMenus(itemMenu pMenu);

	public:

		/*
		 *  @brief constructor primitivo.
		 */
		menuTexto();

		/*
		 *  @brief constructor primitivo.
		 *  @param[in] nula representa a la entrada nula para el diccionario
		 *  @post define la entrada nula
		 */
		menuTexto(itemMenu &pMenu);

		/*
		 * @brief constructor de copia
		 * @param[in] menu, menu a copiar
		 */
		menuTexto(const menuTexto &pValor);

		/*
		 * @brief Añade un menu a la Barra de Menus.
		 * @param[in] pMenu es el nombre del Menu.
		 * @param[in] pValor es el valor que devolvera el menu al seleccionarlo.
		 * @post Se añade solo el menu con un submenu vacio.
		 */
		void addMenu(nombreItem pMenu, valorItem pValor);

		/*
		 * @brief Añade un Submenu al Menu.
		 * @param[in] pMenu es el nombre del menu al que vamos a añadir el submenu.
		 * @param[in] pSubMenu es el nombre del submenu que añadiremos.
		 * @param[in] pValor es el valor unsigned que devolvera el submenu al seleccionarla.
		 */
		void addSubMenu(nombreItem pMenu, nombreItem pSubMenu, valorItem pValor);

		/**
		 * @brief Muestra el menu y espera la elección del usuario.
		 */
		int seleccionar();

//		/*
//		 * @brief Consulta/Inserta una opcion completa con valor incluido al menu que elijamos.
//		 * @param[in] pMenu este es el nombre del menu al que queremos asignar la opcion.
//		 * @return[in/out] una referencia a la opcion que queremos crear/consultar.
//		 */
//		valorItem &operator[](const nombreItem &pMenu);
//
//		/*
//		 * @brief Consulta  un menu.
//		 */
//		const valorItem &operator[](const nombreItem &pMenu) const;
//
//		/*
//		 * @brief operador de asignación
//		 */
//		tipoItemMenu &operator=( const tipoItemMenu &pValor);

		/*
		 * @brief numero de menus
		 * @post  No se modifica el menuTexto.
		 */
		int size() const ;

		/*
		 * @brief vacia
		 * Chequea si el menu esta vacío.
		 */
		bool empty() const;
};

#endif /* MENU_H_ */
