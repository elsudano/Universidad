//**************************************************************************
// Práctica 4
//
// Domingo Martin Perandres 2013-2016
//
// GPL
//**************************************************************************
#include "stdlib.h"
#include "stdio.h"
#include <GL/glut.h>
#include <ctype.h>
#include "objeto3D.h"
#include "revolucion.h"
#include "revolucion_x.h"
#include "cubo.h"
#include "piramide.h"
#include "composicion.h"
#include "luz.h"

// tamaño de los ejes
const int AXIS_SIZE=500;
// Tipo de figura ha mostrar
int figura = 1;
// Tipo de visualización del objeto
int modo = 1;
// Grados de giro de la cintura
GLfloat giro = 0.0;
// Elevación del hombro
GLfloat eleva = 0.0;
// Elevación del hombro
GLfloat elevab = 0.0;
// variable que controla si poner o quitar el suavizado de los objetos
bool suavizado = false;

// variables que definen la posicion de la camara en coordenadas polares
GLfloat Observer_distance;
GLfloat Observer_angle_x;
GLfloat Observer_angle_y;
GLfloat Observer_angle_z;

// Mis Objetos propios
_cubo mi_cubo(3.2);
_piramide mi_piramide(3.2, 4.4);
_objeto3D mi_objeto3D;
_revolucion mi_revolucion;
_revolucion_x mi_revolucion_x;
_composicion mi_robot;
_luz mi_luz01(1);
// variable que se encarga del obturador de foco de la luz 1
GLfloat foco = mi_luz01.get_obturador_foco();
// variable para controlar la posición de la luz 1
_vertex4f posicion_foco = mi_luz01.get_posicion();
_luz mi_luz02(2);

// variables que controlan la ventana y la transformacion de perspectiva
GLfloat Window_width,Window_height,Front_plane,Back_plane;

// variables que determninan la posicion y tamaño de la ventana X
int UI_window_pos_x=500,UI_window_pos_y=10,UI_window_width=900,UI_window_height=900;

//**************************************************************************
// Función para limpiar la ventana donde se dibujan los objetos
//***************************************************************************
void clear_window(){
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT );
}

//**************************************************************************
// Funcion para definir la transformación de proyeccion
//***************************************************************************
void change_projection(){
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	// formato(x_minimo,x_maximo, y_minimo, y_maximo,Front_plane, plano_traser)
	//  Front_plane>0  Back_plane>PlanoDelantero)
	glFrustum(-Window_width,Window_width,-Window_height,Window_height,Front_plane,Back_plane);
}

//**************************************************************************
// Funcion para definir la transformación de vista (posicionar la camara)
//***************************************************************************
void change_observer(){
	// posicion del observador
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	glTranslatef(0,0,-Observer_distance);
	glRotatef(Observer_angle_x,1,0,0);
	glRotatef(Observer_angle_y,0,1,0);
	glRotatef(Observer_angle_z,0,0,1);
}

//**************************************************************************
// Funcion que dibuja los ejes utilizando la primitiva grafica de lineas
//***************************************************************************
void draw_axis(){
	glBegin(GL_LINES);
	// eje X, color rojo
	glColor3f(1,0,0);
	glVertex3f(-AXIS_SIZE,0,0);
	glVertex3f(AXIS_SIZE,0,0);
	// eje Y, color verde
	glColor3f(0,1,0);
	glVertex3f(0,-AXIS_SIZE,0);
	glVertex3f(0,AXIS_SIZE,0);
	// eje Z, color azul
	glColor3f(0,0,1);
	glVertex3f(0,0,-AXIS_SIZE);
	glVertex3f(0,0,AXIS_SIZE);
	glEnd();
}

//**************************************************************************
// Funcion que dibuja los ejes utilizando la primitiva grafica de lineas
//***************************************************************************
void draw_ligths(){
	mi_luz01.set_modo(LUZ_FOCO);
	mi_luz02.set_modo(LUZ_DIFUSA);
	mi_luz01.set_obturador_foco(foco);
	mi_luz01.set_posicion(posicion_foco);
	mi_luz01.interruptor();
	mi_luz02.interruptor();
}

//**************************************************************************
// Funcion que dibuja los objetos
//***************************************************************************
void draw_objects() {
	char nombre_fichero[40];
	if (figura == 1) {
		if (modo == 1)
			mi_cubo.draw_puntos(0, 0, 0, 10);
		else if (modo == 2)
			mi_cubo.draw_aristas(0, 0, 0, 1);
		else if (modo == 3)
			mi_cubo.draw_solido(0.0, 1.0, 0.0);
		else if (modo == 4)
			mi_cubo.draw_solido_ajedrez(0.0, 0.0, 1.0, 1.0, 1.0, 0.0);
		else if (modo == 5)
			mi_cubo.draw_solido_colores();
		else if (modo == 6){
			mi_cubo.set_material(2);
			draw_ligths(); // con esto encendemos las luces
			mi_cubo.draw_solido_luz(suavizado);
			draw_ligths();// con esto apagamos las luces
		}
		else if (modo == 7)
			mi_cubo.draw_solido_tex("../datos/imagen.png");
	} else if (figura == 2) {
		if (modo == 1)
			mi_piramide.draw_puntos(0, 1.0, 0, 5);
		else if (modo == 2)
			mi_piramide.draw_aristas(1.0, 0, 1.0, 1);
		else if (modo == 3)
			mi_piramide.draw_solido(0, 0, 1.0);
		else if (modo == 4)
			mi_piramide.draw_solido_ajedrez(0.0, 1.0, 0.0, 1.0, 0.0, 0.0);
		else if (modo == 5)
			mi_piramide.draw_solido_colores();
		else if (modo == 6){
			mi_piramide.set_material(2);
			draw_ligths(); // con esto encendemos las luces
			mi_piramide.draw_solido_luz(suavizado);
			draw_ligths();// con esto apagamos las luces
		}
		else if (modo == 7)
			mi_piramide.draw_solido_tex("../datos/imagen.png");
	} else if (figura == 3) {
		if (!mi_objeto3D.in_use()) {
			// Guardo el nombre del fichero en un vector de char
			// con la ruta en donde se encuentra, esto normalmente
			// se pasa por parametros
			strcpy (nombre_fichero, "../datos/big_dodge.ply");
			// realizo la lectura de los datos en mi objeto
			mi_objeto3D.leer_objeto(nombre_fichero);
			if (DEBUG_MODE) {
				printf("%s %lu\n", "Tamaño del Vector Vertices: practica2.cpp->draw_objects->9", mi_objeto3D.vertices.size());
				printf("%s %lu\n", "Tamaño del Vector Caras: practica2.cpp->draw_objects->9", mi_objeto3D.caras.size());
			}
		}
		if (DEBUG_MODE) {
			printf("%s %lu\n", "(Fuera) Tamaño del Vector Vertices: practica2.cpp->draw_objects->10", mi_objeto3D.vertices.size());
			printf("%s %lu\n", "(Fuera) Tamaño del Vector Caras: practica2.cpp->draw_objects->10", mi_objeto3D.caras.size());
		}
		if (modo == 1)
			mi_objeto3D.draw_puntos(0, 1.0, 0, 5);
		else if (modo == 2)
			mi_objeto3D.draw_aristas(1.0, 0, 1.0, 1);
		else if (modo == 3)
			mi_objeto3D.draw_solido(0, 0, 1.0);
		else if (modo == 4)
			mi_objeto3D.draw_solido_ajedrez(0.0, 1.0, 0.0, 1.0, 0.0, 0.0);
		else if (modo == 5)
			mi_objeto3D.draw_solido_colores();
		else if (modo == 6){
			mi_objeto3D.set_material(1);
			draw_ligths(); // con esto encendemos las luces
			mi_objeto3D.draw_solido_luz(suavizado);
			draw_ligths();// con esto apagamos las luces
		}
		else if (modo == 7)
			mi_objeto3D.draw_solido_tex("../datos/imagen.png");
	} else if (figura == 4) {
		if (!mi_revolucion.in_use()) {
			// Guardo el nombre del fichero en un vector de char
			// con la ruta en donde se encuentra, esto normalmente
			// se pasa por parametros
			strcpy (nombre_fichero, "../datos/perfil.ply");
			// realizo la lectura de los datos en mi objeto
			mi_revolucion.leer_objeto(nombre_fichero);
			// realizamos la revolución del perfil para generar el objeto
			if (DEBUG_MODE) {
				mi_revolucion.print_puntos_perfil();
				printf("%s %lu\n", "Tamaño del Vector Vertices: practica2.cpp->draw_objects->11", mi_revolucion.vertices.size());
				printf("%s %lu\n", "Tamaño del Vector Caras: practica2.cpp->draw_objects->11", mi_revolucion.caras.size());
			}
			mi_revolucion.revolucion(5, true);
		}
		if (DEBUG_MODE) {
			mi_revolucion.print_puntos_perfil();
			printf("%s %lu\n", "(Fuera) Tamaño del Vector Vertices: practica2.cpp->draw_objects->12", mi_revolucion.vertices.size());
			printf("%s %lu\n", "(Fuera) Tamaño del Vector Caras: practica2.cpp->draw_objects->12", mi_revolucion.caras.size());
		}
		if (modo == 1)
			mi_revolucion.draw_puntos(0, 1.0, 0, 5);
		else if (modo == 2)
			mi_revolucion.draw_aristas(1.0, 0, 1.0, 1);
		else if (modo == 3)
			mi_revolucion.draw_solido(0, 0, 1.0);
		else if (modo == 4)
			mi_revolucion.draw_solido_ajedrez(0.3176, 0.4039, 0.3098, 0.7450, 0.5882, 0.8431);
		else if (modo == 5)
			mi_revolucion.draw_solido_colores();
		else if (modo == 6){
			mi_revolucion.set_material(2);
			draw_ligths(); // con esto encendemos las luces
			mi_revolucion.draw_solido_luz(suavizado);
			draw_ligths();// con esto apagamos las luces
		}
		else if (modo == 7)
			mi_revolucion.draw_solido_tex("../datos/imagen.png");
	} else if (figura == 5) {
		if (!mi_revolucion_x.in_use()) {
			// Guardo el nombre del fichero en un vector de char
			// con la ruta en donde se encuentra, esto normalmente
			// se pasa por parametros
			strcpy (nombre_fichero, "../datos/perfil_x.ply");
			// realizo la lectura de los datos en mi objeto
			mi_revolucion_x.leer_objeto(nombre_fichero);
			// realizamos la revolución del perfil para generar el objeto
			if (DEBUG_MODE) {
				mi_revolucion_x.print_puntos_perfil();
				printf("%s %lu\n", "Tamaño del Vector Vertices: practica2.cpp->draw_objects->13", mi_revolucion_x.vertices.size());
				printf("%s %lu\n", "Tamaño del Vector Caras: practica2.cpp->draw_objects->13", mi_revolucion_x.caras.size());
			}
			mi_revolucion_x.revolucion(5, true);
		}
		if (DEBUG_MODE) {
			mi_revolucion_x.print_puntos_perfil();
			printf("%s %lu\n", "(Fuera) Tamaño del Vector Vertices: practica2.cpp->draw_objects->14", mi_revolucion_x.vertices.size());
			printf("%s %lu\n", "(Fuera) Tamaño del Vector Caras: practica2.cpp->draw_objects->14", mi_revolucion_x.caras.size());
		}
		if (modo == 1)
			mi_revolucion_x.draw_puntos(0, 1.0, 0, 5);
		else if (modo == 2)
			mi_revolucion_x.draw_aristas(1.0, 0, 1.0, 1);
		else if (modo == 3)
			mi_revolucion_x.draw_solido(0, 0, 1.0);
		else if (modo == 4)
			mi_revolucion_x.draw_solido_ajedrez(0.3176, 0.4039, 0.3098, 0.7450, 0.5882, 0.8431);
		else if (modo == 5)
			mi_revolucion_x.draw_solido_colores();
		else if (modo == 6){
			mi_revolucion_x.set_material(2);
			draw_ligths(); // con esto encendemos las luces
			mi_revolucion_x.draw_solido_luz(suavizado);
			draw_ligths();// con esto apagamos las luces
		}
		else if (modo == 7)
			mi_revolucion_x.draw_solido_tex("../datos/imagen.png");
	} else if (figura == 6) {
			mi_robot.componer(modo);
			mi_robot.set_giro_cintura(giro);
			mi_robot.set_eleva_hombro(eleva);
			mi_robot.set_eleva_brazo(elevab);
		if (DEBUG_MODE) {
			printf("%s\n", "Final de Robot practica3.cpp->draw_objects->15");
		}
	}
}

//**************************************************************************
// Función para dibujar todas las partes de la Escena, objetos y otros
//***************************************************************************
void draw_scene(void) {
	clear_window();
	change_observer();
	draw_axis();
	draw_objects();
	glutSwapBuffers();
}

//***************************************************************************
// Funcion llamada cuando se produce un cambio en el tamaño de la ventana
//
// el evento manda a la funcion:
// nuevo ancho
// nuevo alto
//***************************************************************************
void change_window_size(int Ancho1,int Alto1) {
	change_projection();
	glViewport(0,0,Ancho1,Alto1);
	glutPostRedisplay();
}

//***************************************************************************
// Funcion llamada cuando se produce aprieta una tecla normal
//
// el evento manda a la funcion:
// codigo de la tecla
// posicion x del raton
// posicion y del raton
//***************************************************************************
void normal_keys(unsigned char Tecla1,int x,int y) {
	switch (toupper(Tecla1)){
		case 'Q': exit(0); break;// Salir
		case 'I': DEBUG_MODE = !DEBUG_MODE; break;// Activa/Desactiva el modo debug
		case 'D': modo = 1; break; // Solo los puntos
		case 'Z': modo = 2; break; // Solo los lineas/aristas
		case 'S': modo = 3; break; // El objeto en un color solido
		case 'A': modo = 4; break; // Modo Ajedrez
		case 'C': modo = 5; break; // Degradado según los colores de los vertices
		case 'X': modo = 6; break; // Modo de pintado solido con luces
		case 'E': modo = 7; break; // Objeto con textura
		case 'W': suavizado = !suavizado; break; // podemos poner el metodo de suavizado o quitarlo
		case 'F': foco = foco+2; break; // Ampliar el tamaño del foco
		case 'V': foco = foco-2; break; // Disminuir el tamaño del foco
		case 'H': posicion_foco._1 = posicion_foco._1+2; break; // Mover hacia arriba en el eje y la luz
		case 'N': posicion_foco._1 = posicion_foco._1-2; break; // Mover hacia abajo en el eje y la luz
		case 'M': posicion_foco._0 = posicion_foco._0+2; break; // Mover hacia derecha en el eje x la luz
		case 'B': posicion_foco._0 = posicion_foco._0-2; break; // Mover hacia izquierda en el eje x la luz
		case 'G': posicion_foco._2 = posicion_foco._2+2; break; // Mover hacia delante en el eje z la luz
		case 'T': posicion_foco._2 = posicion_foco._2-2; break; // Mover hacia atras en el eje z la luz
		case 'U': posicion_foco._3 = posicion_foco._3+2; break; // Giramos a derecha el foco en Y
		case 'Y': posicion_foco._3 = posicion_foco._3-2; break; // Giramos a izquierda el foco en Y
		case 'R':// Reiniciamos la posición de la luz al 10,10,10
			posicion_foco._0 = 10;
			posicion_foco._1 = 10;
			posicion_foco._2 = 10;
			break;
		//-----------------------------------------------------------------------
		case 'K': giro = giro+2.5; break; // Giro derecha del robot
		case 'J': giro = giro-2.5; break; // Giro izquierda del robot
		case 'O': eleva = eleva-2; break; // Eleva el hombro del robot
		case 'L': eleva = eleva+2; break; // Desciende el hombro del robot
		case '0': elevab = elevab-2; break; // Eleva el brazo del robot
		case 'P': elevab = elevab+2; break; // Desciende el brazo del robot
		//-----------------------------------------------------------------------
		case '1': Observer_angle_y--; break;// Movemos hacia la izquierda la camara del observador
		case '3': Observer_angle_y++; break;// Movemos hacia la derecha la camara del observador
		case '5': Observer_angle_x--; break;// Movemos hacia arriba la camara del observador
		case '2': Observer_angle_x++; break;// Movemos hacia abajo la camara del observador
		case '4': Observer_angle_z++; break;// Giramos el objeto en el eje Z
		case '7': Observer_angle_z--; break;// Giramos el objeto en el eje Z
		case '8': mi_luz01.interruptor(); break;// Apaga y enciende la luz 1
		case '9': mi_luz02.interruptor(); break;// Apaga y enciende la luz 2
	}
	glutPostRedisplay();
}

//***************************************************************************
// Funcion llamada cuando se produce aprieta una tecla especial
//
// el evento manda a la funcion:
// codigo de la tecla
// posicion x del raton
// posicion y del raton
//***************************************************************************
void special_keys(int Tecla1,int x,int y) {
	switch (Tecla1) {
		case GLUT_KEY_F1: figura = 1; break;// Cubo con puntos desde método
		case GLUT_KEY_F2: figura = 2; break;// Piramide con puntos desde método
		case GLUT_KEY_F3: figura = 3; break;// Objeto cargado desde fichero ply
		case GLUT_KEY_F4: figura = 4; break;// Objeto creado por revolución en eje y
		case GLUT_KEY_F5: figura = 5; break;// Objeto creado por revolución en eje x
		case GLUT_KEY_F6: figura = 6; break;// Objeto multiple
		case GLUT_KEY_PAGE_UP: Observer_distance*=1.2; break;
		case GLUT_KEY_PAGE_DOWN: Observer_distance/=1.2; break;
	}
	if (DEBUG_MODE)
		printf("Value of: %d\nValue of: %d\n", x, y);
	glutPostRedisplay();
}

//***************************************************************************
// Funcion de incializacion
//***************************************************************************
void initialize(void) {
	// se inicalizan la ventana y los planos de corte
	Window_width=5;
	Window_height=5;
	Front_plane=10;
	Back_plane=1000;

	// se inicia la posicion del observador, en el eje z
	Observer_distance=2*Front_plane;
	Observer_angle_x=0;
	Observer_angle_y=0;
	Observer_angle_z=0;

	// se indica cual sera el color para limpiar la ventana	(r,v,a,al)
	// blanco=(1,1,1,1) rojo=(1,0,0,1), ...
	glClearColor(1,1,1,1);

	// se habilita el z-bufer
	glEnable(GL_DEPTH_TEST);
	change_projection();
	glViewport(0,0,UI_window_width,UI_window_height);
}

//***************************************************************************
// Función que presenta por pantalla todas las teclas que tiene el Programa
// y los diferentes parametros que podemos utilizar en el.
//***************************************************************************
void help(){
	printf("%s\n\n", "Teclas para el uso de la aplicación:");
	printf("\t%s\n", "Tecla Q Sale de la aplicación.");
	printf("\t%s\n", "Tecla D Muestra solo los vertices de la figura.");
	printf("\t%s\n", "Tecla Z Muestra solo las aristas/lados de la figura.");
	printf("\t%s\n", "Tecla S Muestra la figura con un color solido.");
	printf("\t%s\n", "Tecla A Muestra la figura en modo bicolor.");
	printf("\t%s\n", "Tecla C Muestra la figura en modo multicolor según el color de los vertices.");
	printf("\t%s\n", "Tecla X Muestra la figura con el material y las luces.");
	printf("\t%s\n", "Tecla E Muestra la figura con la textura aplicada.");
	printf("\t%s\n", "Tecla W Opción para poner o quitar el suavizado en los materiales.");
	printf("\t%s\n", "Tecla F Aumentar el radio del tamaño del foco.");
	printf("\t%s\n", "Tecla V Disminuir el radio del tamaño del foco.");
	printf("\t%s\n", "Tecla H Mover hacia arriba en el eje y el foco.");
	printf("\t%s\n", "Tecla N Mover hacia abajo en el eje y la foco.");
	printf("\t%s\n", "Tecla M Mover hacia derecha en el eje x la foco.");
	printf("\t%s\n", "Tecla B Mover hacia izquierda en el eje x la luz.");
	printf("\t%s\n", "Tecla G Mover hacia adelante en el eje z la luz.");
	printf("\t%s\n", "Tecla T Mover hacia atras en el eje z la luz.");
	printf("\t%s\n", "Tecla U Giramos a derecha el foco en Y.");
	printf("\t%s\n", "Tecla Y Giramos a izquierda el foco en Y.");
	printf("\t%s\n", "Tecla R Reiniciamos la posición de la luz al 10,10,10.");
	printf("\t%s\n", "Tecla K Giro derecha del robot.");
	printf("\t%s\n", "Tecla J Giro izquierda del robot.");
	printf("\t%s\n", "Tecla L Desciende el hombro del robot.");
	printf("\t%s\n", "Tecla O Eleva el hombro del robot.");
	printf("\t%s\n", "Tecla P Desciende el brazo del robot.");
	printf("\t%s\n", "Tecla 0 Eleva el brazo del robot.");
	printf("\t%s\n", "Tecla 8 Encender/Apagar el foco de la escena.");
	printf("\t%s\n", "Tecla 9 Encender/Apagar la luz ambiental de la escena.");
	printf("\n\t%s\n\n", "Se recomienda el uso del teclado númerico para el moviemiento de la figura");
	printf("\t\t%s\n", "Tecla 1 Movemos hacia la izquierda la cámara del observador.");
	printf("\t\t%s\n", "Tecla 3 Movemos hacia la derecha la cámara del observador.");
	printf("\t\t%s\n", "Tecla 5 Movemos hacia arriba la cámara del observador.");
	printf("\t\t%s\n", "Tecla 2 Movemos hacia abajo la cámara del observador.");
	printf("\t\t%s\n", "Tecla 4 Giramos el objeto en el eje Z.");
	printf("\t\t%s\n\n", "Tecla 7 Giramos el objeto en el eje Z.");
}

//***************************************************************************
// Programa principal
//
// Se encarga de iniciar la ventana, asignar las funciones e comenzar el
// bucle de eventos
//***************************************************************************
int main(int argc, char **argv) {
	int param_min = 1;
	int param_max = 2;
	if (argc > param_min && argc <= param_max){
		if (std::string(argv[1]) == "--help"){
			help();
			exit(0);
		}
	}
	// se llama a la inicialización de glut
	glutInit(&argc, argv);
	// se indica las caracteristicas que se desean para la visualización con OpenGL
	// Las posibilidades son:
	// GLUT_SIMPLE -> memoria de imagen simple
	// GLUT_DOUBLE -> memoria de imagen doble
	// GLUT_INDEX -> memoria de imagen con color indizado
	// GLUT_RGB -> memoria de imagen con componentes rojo, verde y azul para cada pixel
	// GLUT_RGBA -> memoria de imagen con componentes rojo, verde, azul y alfa para cada pixel
	// GLUT_DEPTH -> memoria de profundidad o z-bufer
	// GLUT_STENCIL -> memoria de estarcido
	glutInitDisplayMode(GLUT_RGB | GLUT_DOUBLE | GLUT_DEPTH);
	// posicion de la esquina inferior izquierdad de la ventana
	glutInitWindowPosition(UI_window_pos_x,UI_window_pos_y);
	// tamaño de la ventana (ancho y alto)
	glutInitWindowSize(UI_window_width,UI_window_height);
	// llamada para crear la ventana, indicando el titulo (no se visualiza hasta que se llama
	// al bucle de eventos)
	glutCreateWindow("Practica 5");
	// asignación de la funcion llamada "dibujar" al evento de dibujo
	glutDisplayFunc(draw_scene);
	// asignación de la funcion llamada "cambiar_tamanio_ventana" al evento correspondiente
	glutReshapeFunc(change_window_size);
	// asignación de la funcion llamada "tecla_normal" al evento correspondiente
	glutKeyboardFunc(normal_keys);
	// asignación de la funcion llamada "tecla_Especial" al evento correspondiente
	glutSpecialFunc(special_keys);
	// funcion de inicialización
	initialize();
	// inicio del bucle de eventos
	glutMainLoop();
	return 0;
}
